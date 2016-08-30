package threads;

import classes.Processo;
import java.util.logging.Level;
import java.util.logging.Logger;

//Classe para verificar o coordenador, monitora o status da flag de coordenador 
//ativo se ela esta ativa depois de 10 segundos, se o coordenador não estiver ativo
//inicia uma eleição
public class TimeOutThread extends Thread{
    boolean flagCordenadorAtivo;
    Processo p;

    public TimeOutThread(Processo p) {
        this.p = p;
    }
    @Override
    public void run(){
        while(true){
            try {
                TimeOutThread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeOutThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!this.isFlagCordenadorAtivo() && !p.getIt().isEmEleicao()){
                System.err.println("não foi detectado um lider! vou iniciar a eleicao");
                p.getIt().setEmEleicao(true);
                p.setStatus(2);
                p.iniciaEleicao();
            }
            this.setFlagCordenadorAtivo(false);
        }
    }

    public boolean isFlagCordenadorAtivo() {
        return flagCordenadorAtivo;
    }

    public void setFlagCordenadorAtivo(boolean flagCordenadorAtivo) {
        this.flagCordenadorAtivo = flagCordenadorAtivo;
    }
    
}
