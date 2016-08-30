package threads;

import classes.Processo;
import classesMulticast.MulticastGroup;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

//clase que envia pacotes na rede
public class OutputThread extends Thread {
    MulticastGroup m;
    DatagramPacket outgoing;
    InetAddress ia;
    int portS;
    String s;
    Processo p;
    public OutputThread(MulticastGroup m, InetAddress ia, int portS, String s, Processo p) throws IOException{
        this.m = m;
        this.portS = portS;
        this.ia = ia;
        this.s = s;
        this.p = p;
    }
    //função de enviar, cria um datagrama, e evia ele na rede
    public void enviar(){
            byte[] data1 = new byte[s.length()];
            s.getBytes(0, s.length(), data1, 0);
            outgoing = new DatagramPacket(data1, data1.length, ia, portS);
            try {
                m.getSocket().send(outgoing);
            } catch (IOException e) {
                System.err.println("erro: " + e);
            }
            this.p.getJanela().setSaida("Pacote enviado: \t(" + s + ")\n");
    }
    
    @Override
    public void run() {
        this.enviar();
    }
}