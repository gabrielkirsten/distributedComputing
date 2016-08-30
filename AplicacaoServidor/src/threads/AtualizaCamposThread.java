package threads;

import classes.Interface;
import classes.Processo;

//CLASSE que atualiza os campos da janela 
public class AtualizaCamposThread extends Thread {
    String idProcesso, statusProcesso, liderAtual, tokenArmazenado, stringValidacao, ipMulticast, saida, ipExterno;
    boolean saidaAlterada;
    Processo p;
    
    public AtualizaCamposThread(String idProcesso, String statusProcesso, String liderAtual, String tokenArmazenado, String stringValidacao, String ipMulticast, String porta, Processo p) {
        this.idProcesso = idProcesso;
        this.statusProcesso = statusProcesso;
        this.liderAtual = liderAtual;
        this.tokenArmazenado = tokenArmazenado;
        this.stringValidacao = stringValidacao;
        this.ipMulticast = ipMulticast + ":" + porta;   
        this.saidaAlterada = false;
        this.p = p;
    }
    
    @Override
    public void run(){
        //instancia um objeto de interface que será atualizado
        Interface inter = new Interface(idProcesso, statusProcesso, liderAtual, tokenArmazenado, stringValidacao, ipMulticast, p);
        inter.setVisible(true);
        String auxId = idProcesso;
        boolean lider;
        while(true){//fica atualizando para sempre
            if(idProcesso.equals(liderAtual)){//se o processo for o lider atual imprime "LIDER" do lado do id
                auxId = idProcesso + " - LIDER"; 
                lider = true;
            }else{
                auxId = idProcesso;
                lider = false;
            }
            if(this.isSaidaAlterada()){// só atualiza a saida se ela for alterada
                this.setSaidaAlterada(false);
                inter.atualizaSaida(auxId, statusProcesso, liderAtual, tokenArmazenado, stringValidacao, ipMulticast, saida, ipExterno, lider);
            }else{
                inter.atualizaSaida(auxId, statusProcesso, liderAtual, tokenArmazenado, stringValidacao, ipMulticast, ipExterno, lider);
            }
        }
        
    }    

    public boolean isSaidaAlterada() {
        return saidaAlterada;
    }

    public void setSaidaAlterada(boolean saidaAlterada) {
        this.saidaAlterada = saidaAlterada;
    }

    public String getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(String idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getStatusProcesso() {
        return statusProcesso;
    }

    public void setStatusProcesso(String statusProcesso) {
        this.statusProcesso = statusProcesso;
    }

    public String getLiderAtual() {
        return liderAtual;
    }

    public void setLiderAtual(String liderAtual) {
        this.liderAtual = liderAtual;
    }

    public String getTokenArmazenado() {
        return tokenArmazenado;
    }

    public void setTokenArmazenado(String tokenArmazenado) {
        this.tokenArmazenado = tokenArmazenado;
    }

    public String getStringValidacao() {
        return stringValidacao;
    }

    public void setStringValidacao(String stringValidacao) {
        this.stringValidacao = stringValidacao;
    }

    public String getIpMulticast() {
        return ipMulticast;
    }

    public void setIpMulticast(String ipMulticast) {
        this.ipMulticast = ipMulticast;
    }

    public synchronized String getSaida() {
        return saida;
    }

    public synchronized void setSaida(String saida) {        
        if(this.isSaidaAlterada()){
            this.saida = this.getSaida() + saida;
        }else{
            this.saida = saida;
        }
        this.setSaidaAlterada(true);
    }

    public String getIpExterno() {
        return ipExterno;
    }

    public void setIpExterno(String ipExterno) {
        this.ipExterno = ipExterno;
    } 
    
    
}