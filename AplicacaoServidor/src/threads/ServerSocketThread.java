package threads;

import classes.Processo;
import classesMulticast.MulticastGroup;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//classe para aceitar conexoes externas, instanciada pelo coordenador
public class ServerSocketThread extends Thread{
    MulticastGroup m;
    InetAddress ia;
    int portS;
    Processo p;
    ServerSocket conexaoExterna;
    
    public ServerSocketThread(MulticastGroup m, InetAddress ia, int portS, Processo p) {
        this.m = m;
        this.ia = ia;
        this.portS = portS;
        this.p = p;
    }
    
    @Override
    public void run(){
        try {
            conexaoExterna = new ServerSocket(0); // porta aleatoria
            this.p.getJanela().setIpExterno(InetAddress.getLocalHost().getHostAddress() + ":" + conexaoExterna.getLocalPort());
            while (true) {
                Socket cliente = conexaoExterna.accept();
                this.p.getJanela().setSaida("Nova conexão, IP: " + cliente.getInetAddress().getHostAddress()+"\n");
                Scanner entrada = new Scanner(cliente.getInputStream());
                while (entrada.hasNextLine()) {
                    String mensagem = entrada.nextLine();
                    this.p.getJanela().setSaida("Mensagem externa recebida: " + mensagem+"\n");  
                    this.p.getJanela().setSaida("### Mensagem externa recebida: " + mensagem+" ####\n");
                    OutputThread ot = new OutputThread(m, ia, portS, this.p.getpId()+"#03#"+m.getValidacao()+"#"+this.p.getToken()+"#"+mensagem, p);
                    ot.start();
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    
    //GET E SET
    public ServerSocket getConexaoExterna() {
        return conexaoExterna;
    }
    
}
    