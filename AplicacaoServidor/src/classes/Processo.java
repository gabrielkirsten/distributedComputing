package classes;

import classesMulticast.MulticastGroup;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.AtualizaCamposThread;
import threads.InputThread;
import threads.OutputThread;
import threads.TimeOutThread;
import threads.IniciaEleicaoThread;
import threads.ServerSocketThread;

public class Processo extends Thread{
    int pId, status, coordenadorAtual, token, portS;
    boolean lider;
    MulticastGroup ms;
    InetAddress ia;
    float fim;
    InputThread it;
    TimeOutThread toT;
    AtualizaCamposThread janela; 
    
    public Processo(int pId, MulticastGroup ms, InetAddress ia, int portS) {
        this.pId = pId;
        this.lider = false;
        this.ia = ia;
        this.ms = ms;
        this.portS = portS;
        this.token = 0;
    }
    
    // Função para iniciar uma eleição, difunde um pacote via muticast informando a intenção de participar da eleição e cria 
    // um thread para contar o tempo de eleição
    public void iniciaEleicao(){
        OutputThread output;
        try {
            output = new OutputThread(ms, ia, portS, this.getpId()+"#01#"+ms.getValidacao(), this); // pacote de multicast
            IniciaEleicaoThread eleicao = new IniciaEleicaoThread(this, output); // Thread que conta o tempo de eleição
            eleicao.start();
        } catch (IOException ex) {
            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    //Processo que inicia o processo, inicia a janela de interface, o thread de entrada e o thread de monitoramento
    public void iniciaProcesso(){
        janela = new AtualizaCamposThread(String.valueOf(this.getpId()), "", String.valueOf(this.getCoordenadorAtual()), String.valueOf(this.getToken()), ms.getValidacao(), this.getIa().toString(), String.valueOf(this.getPortS()), this);
        janela.start(); 
        janela.setSaida("Inicia Thread de entrada..." + "\n");
        it = new InputThread(ms, this);
        it.start();
        janela.setSaida("Inicia Thread de monitoramento..." + "\n");
        toT = new TimeOutThread(this);
        toT.start();
    }
    
    //Processo que é chamado pelo processo lider, cria um pacote para avisar a lideraça com um thread de saida, inicia um thread ServerSocket para o acesso externo
    // e manda mensagens com intervalos de 2500 ms
    public void avisarLideraca() throws IOException, InterruptedException {
        if(this.lider == true){            
            OutputThread ot = new OutputThread(ms, ia, portS, this.getpId()+"#02#"+ms.getValidacao()+"#"+this.getToken(), this);
            janela.setSaida("Inicia Thread de saida..." + "\n");
            ot.start();   
            System.out.println("Inicia Servidor de acesso externo...");
            janela.setSaida("Inicia Servidor de acesso externo..." + "\n");
            ServerSocketThread acessoExterno = new ServerSocketThread(ms, ia, portS, this);
            acessoExterno.start();
            while(this.lider == true){ // enquanto o processo for o lider
                Processo.sleep(2500);
                ot.enviar();
            }
            acessoExterno.getConexaoExterna().close();
            acessoExterno.stop();
        }
    }
    
    //metodo para difundir uma mensagem na rede
    public void comunicaProcesso(String saida){
        try {
            OutputThread comunica = new OutputThread(ms, ia, portS, this.getpId()+"#04#"+ms.getValidacao()+"#"+this.getToken()+"#"+saida, this);
            comunica.start();
        } catch (IOException ex) {
            Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // METODOS GET E SET    
    public synchronized AtualizaCamposThread getJanela() {
        return janela;
    }

    public void setJanela(AtualizaCamposThread janela) {
        this.janela = janela;
    }
    
    public int getpId() {
        return pId;
    }
    
    public boolean isLider() {
        return lider;
    }

    public void setLider(boolean lider) {
        this.lider = lider;
    }

    public MulticastGroup getMs() {
        return ms;
    }

    public void setMs(MulticastGroup ms) {
        this.ms = ms;
    }

    public InetAddress getIa() {
        return ia;
    }

    public void setIa(InetAddress ia) {
        this.ia = ia;
    }

    public int getPortS() {
        return portS;
    }

    public void setPortS(int portS) {
        this.portS = portS;
    }

    public float getFim() {
        return fim;
    }

    public void setFim(float fim) {
        this.fim = fim;
    }

    public InputThread getIt() {
        return it;
    }

    public void setIt(InputThread it) {
        this.it = it;
    }

    public TimeOutThread getToT() {
        return toT;
    }

    public void setToT(TimeOutThread toT) {
        this.toT = toT;
    }

    public int getToken() {
        return token;
    }

    // seta o token e muda a mensagem exibida na janela de interface
    public void setToken(int token) {
        this.janela.setTokenArmazenado(String.valueOf(token));
        this.token = token;
    }

    public int getCoordenadorAtual() {
        return coordenadorAtual;
    }

    // seta o cordanador atual e muda a mensagem exibida na janela de interface
    public void setCoordenadorAtual(int coordenadorAtual) {
        this.janela.setLiderAtual(String.valueOf(coordenadorAtual));
        this.coordenadorAtual = coordenadorAtual;
    }

    public int getStatus() {
        return status;
    }

    // seta o status e muda a mensagem exibida na janela de interface
    public void setStatus(int status) {
        this.status = status;
        switch(status){
            case 0:    
                this.janela.setStatusProcesso("Processo rodando - Coordenador Ativo");
                break;
            case 1:
                this.janela.setStatusProcesso("Em eleição");
                break;
            case 2:
                this.janela.setStatusProcesso("Candidato");
                break;
            default:                
                this.janela.setStatusProcesso("Erro - Status Invalido");
                break;
        }
    }
    
    
    
}
