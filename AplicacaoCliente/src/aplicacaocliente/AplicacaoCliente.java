package aplicacaocliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AplicacaoCliente {
    public static void main(String[] args) throws FileNotFoundException  {
        String host = JOptionPane.showInputDialog(null, "Ip do servidor:", "xxx.xxx.xxx.xxx");
        Socket cliente = null;
        try {
            int porta = Integer.parseInt(JOptionPane.showInputDialog(null, "Porta: ", "xxxx"));
            cliente = new Socket(InetAddress.getByName(host).getHostAddress(), porta);
        } catch (IOException ex) {
            Logger.getLogger(AplicacaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner scan = new Scanner(System.in);
        PrintStream saida = null;
        try {
            saida = new PrintStream(cliente.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(AplicacaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        String menssagem;
        while(true){
            menssagem = JOptionPane.showInputDialog(null, "Mensagem:", "teste");
            saida.println(menssagem);   
        }
    }
    
}
