package threads;

import classes.Processo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Classe de thread para eleição, cria a eleição, difunde um pacote avisando 
// a intenção de realizar a eleição e depois verifica o resultado
public class IniciaEleicaoThread extends Thread {
    Processo p;
    OutputThread output;

    public IniciaEleicaoThread(Processo p, OutputThread output) {
        this.p = p;
        this.output = output;
    }
 
    @Override
    public void run(){
        System.out.println("Eleição Iniciada...");  
        this.p.getJanela().setSaida("Eleição Iniciada...\n");
        output.start();
        p.getIt().setEmEleicao(true);
        p.setStatus(2); 
        try {
            IniciaEleicaoThread.sleep(8000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IniciaEleicaoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setStatus(0);
        // se depois de 8 segundos o processo continuar em eleição
        // o processo é eleito lider.
        if(!this.p.getIt().isEmEleicao()){//definiu um lider
            this.p.getJanela().setSaida("Perdi a eleição\n");
        }else{//eu sou o lider
            this.p.getJanela().setSaida("Ganhei a eleição!\n");
            this.p.setToken(this.p.getToken()+1);
            this.p.setLider(true);
            try {
                this.p.avisarLideraca();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(IniciaEleicaoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
