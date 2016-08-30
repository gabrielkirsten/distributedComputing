package main;

// Bibliotecas
import classes.Processo;
import classesMulticast.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Main {
    // Declaracao de constantes
    protected static int defaultPortS = 0, defaultPortR = 0; //portas de envio e recebimento
    protected static int bufferLength = 8192;// tamanho do buffer

    public static void main(String[] args){
        // Declaracao de variaveis;
        int idProcesso = Integer.parseInt(JOptionPane.showInputDialog(null, "Id do Processo: ", "1")); //JOpitionPane para inserir a identificação do processo
        String hostname, validacao;    // Armazena o nome 'host' e string que valida o pacote no multicast
        int portS,          // Armazena a porta pela qual os dados serao enviados
            portR;          // Armazena a porta pela qual os dados serao recebidos       
        hostname = JOptionPane.showInputDialog(null, "Ip Multicast: ", "224.4.4.4"); //JOpitionPane para inserir o ip do multicast
        System.out.println("HostName: " + hostname);
        validacao = JOptionPane.showInputDialog(null, "StringValidacao: ", "123456");//JOpitionPane para inserir a string de validação
        portS = Integer.parseInt(JOptionPane.showInputDialog(null, "Porta Origem: ", "1234")); //JOpitionPane para inserir a porta de origem
        System.out.println("Enviara msgs para porta " + portS);
        portR = Integer.parseInt(JOptionPane.showInputDialog(null, "Porta Destino: ", "1234")); //JOpitionPane para inserir a porta de destino
        try { 
            MulticastGroup ms = new MulticastGroup(portR, hostname, validacao); // cria o grupo multicast
            System.out.println("Escutando a porta " + ms.getPort());
            InetAddress ia = InetAddress.getByName(hostname);
            System.out.println("Inet Address: " + ia);
            Processo p = new Processo(idProcesso, ms, ia, portS); // cria um novo processo
            p.iniciaProcesso(); // inicia o prcesso
        } catch (UnknownHostException e) {
            System.err.println(e);
        }        
    } 
}