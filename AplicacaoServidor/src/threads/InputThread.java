package threads;

import classes.Processo;
import classesMulticast.MulticastGroup;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;


//Thread de entrada, escuta a porta determinada
public class InputThread extends Thread {
    MulticastGroup m;
    String s;
    protected static int bufferLength = 8192;
    Processo p;
    boolean emEleicao;
    
    public InputThread(MulticastGroup m, Processo p){
        this.m = m;
        this.p = p;
        this.emEleicao = false;
    }
    
    @Override
    public void run() {
        DatagramPacket incoming = new DatagramPacket(new byte[bufferLength], bufferLength);
        while(true){ 
            try {
                m.getSocket().receive(incoming); // Recebe um pacote da rede                
            } catch (IOException ex) { 
                Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            String recebida = new String(incoming.getData(), 0, 0, incoming.getLength());
            String[] tokens = recebida.split("#");//separa os campos pelos identificadores
            try{
                if("01".equals(tokens[1]) && tokens[2].equals(m.getValidacao())){ // recebeu um pacote de um processo que deseja iniciar uma eleição
                    p.getToT().setFlagCordenadorAtivo(true);//não preciso verificar se o coordenador esta ativo
                    if(Integer.parseInt(tokens[0]) < p.getpId()){ // meu id é maior que o processo que deseja iniciar
                        this.p.getJanela().setSaida("Sou maior, agora vou me candidatar a eleição...\n");
                        this.setEmEleicao(true);
                        p.iniciaEleicao();
                    }else if(Integer.parseInt(tokens[0]) > p.getpId()){
                        p.setStatus(1); 
                        this.setEmEleicao(false);//saio da eleição
                        this.p.getJanela().setSaida("Sou menor, não vou me candidatar...\n");
                    }
                } else if("02".equals(tokens[1]) && tokens[2].equals(m.getValidacao())){
                    p.getToT().setFlagCordenadorAtivo(true);
                    p.setStatus(0);
                    if(Integer.parseInt(tokens[3]) > p.getToken()){//estou com o coordenador desatualizado
                        if(p.isLider()){
                            p.setLider(false);
                        }
                        p.setCoordenadorAtual(Integer.parseInt(tokens[0]));
                        p.setToken(Integer.parseInt(tokens[3])); 
                    } else if(Integer.parseInt(tokens[3]) == p.getToken() && p.isLider()){
                        if(Integer.parseInt(tokens[0]) > p.getpId()){
                            p.setLider(false);
                        }
                        p.setCoordenadorAtual(Integer.parseInt(tokens[0]));
                    }                    
                 } else if("03".equals(tokens[1]) && tokens[2].equals(m.getValidacao())){// mensagem externa
                     this.p.getJanela().setSaida("#### MENSAGEM EXTERNA:" +tokens[4]+ " #### \n");
                 } else if("04".equals(tokens[1]) && tokens[2].equals(m.getValidacao())){//mensagem interna
                     this.p.getJanela().setSaida("#### MENSAGEM INTERNA: \"" +tokens[4]+ "\" do processo: "+tokens[0]+" #### \n");
                 }
            } catch (ArrayIndexOutOfBoundsException ex){
                System.err.println("pacote recusado: "+recebida);
            }
            try{
                this.p.getJanela().setSaida("Pacote recebido:\t("+recebida+ ") \tProcesso: " + tokens[0] + "\t");
                switch(tokens[1]){ // Escolhe a inteção do processo
                    case "01": // INICIAR ELEICAO
                        this.p.getJanela().setSaida("Deseja iniciar uma eleição"+ "\t");
                        break;
                    case "02": // AVISAR LIDERANCA
                        this.p.getJanela().setSaida("Avisou que é o lider");
                }
                if(!tokens[2].equals(m.getValidacao())){// Senha regeitada
                    this.p.getJanela().setSaida("Senha Regeitada!!!\n");
                }else{
                    this.p.getJanela().setSaida("\n");
                }
            }catch(ArrayIndexOutOfBoundsException ex){
                System.err.println("pacote recusado: "+recebida);
            }      
        }
    }

    // METODOS SET E GET
    
    public MulticastGroup getM() {
        return m;
    }

    public void setM(MulticastGroup m) {
        this.m = m;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public static int getBufferLength() {
        return bufferLength;
    }

    public static void setBufferLength(int bufferLength) {
        InputThread.bufferLength = bufferLength;
    }

    public Processo getP() {
        return p;
    }

    public void setP(Processo p) {
        this.p = p;
    }

    public boolean isEmEleicao() {
        return emEleicao;
    }

    public void setEmEleicao(boolean emEleicao) {
        this.emEleicao = emEleicao;
    }
    
}