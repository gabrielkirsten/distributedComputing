/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import threads.OutputThread;


/**
 *
 * @author gabriel
 */
public class Interface extends javax.swing.JFrame {
    Processo p;
    
    /**
     * Creates new form Interface
     * @param idProcesso
     * @param statusProcesso
     * @param liderAtual
     * @param tokenArmazenado
     * @param stringValidacao
     * @param ipMulticast
     */
    public Interface(String idProcesso, String statusProcesso, String liderAtual, String tokenArmazenado, String stringValidacao, String ipMulticast, Processo p) {
        initComponents();
        this.idProcesso.setText(idProcesso);
        this.statusProcesso.setText(statusProcesso);
        this.liderAtual.setText(liderAtual);
        this.tokenArmazenado.setText(tokenArmazenado);
        this.stringValidacao.setText(stringValidacao);
        this.ipMulticast.setText(ipMulticast);
        this.p = p;       
    }
    
    //método para atualizar as saidas, tem uma sobrecarga, uma atualiza a saida 
    //e outro só atualiza os campos
    public void atualizaSaida(String idProcesso, String statusProcesso, String liderAtual, String tokenArmazenado, String stringValidacao, String ipMulticast, String saida, String ipExterno, boolean lider) {
        this.idProcesso.setText(idProcesso);
        this.statusProcesso.setText(statusProcesso);
        this.liderAtual.setText(liderAtual);
        this.tokenArmazenado.setText(tokenArmazenado);
        this.stringValidacao.setText(stringValidacao);
        this.ipMulticast.setText(ipMulticast);   
        this.ipExterno.setText(ipExterno);        
        this.saida.setText(this.saida.getText() + saida);        
        this.saida.setCaretPosition(this.saida.getDocument().getLength());
        if(lider){// o endereço de ip só é exibido se o processo é lider
            this.ipExterno.setVisible(true);
        }else{
            this.ipExterno.setVisible(false);
        }
    }
    
    public void atualizaSaida(String idProcesso, String statusProcesso, String liderAtual, String tokenArmazenado, String stringValidacao, String ipMulticast, String ipExterno, boolean lider) {
        this.idProcesso.setText(idProcesso);
        this.statusProcesso.setText(statusProcesso);
        this.liderAtual.setText(liderAtual);
        this.tokenArmazenado.setText(tokenArmazenado);
        this.stringValidacao.setText(stringValidacao);
        this.ipMulticast.setText(ipMulticast);
        this.ipExterno.setText(ipExterno);
        if(lider){
            this.ipExterno.setVisible(true);
        }else{
            this.ipExterno.setVisible(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        liderAtual = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        idProcesso = new javax.swing.JLabel();
        tokenArmazenado = new javax.swing.JLabel();
        statusProcesso = new javax.swing.JLabel();
        stringValidacao = new javax.swing.JLabel();
        ipMulticast = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        saida = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        ipExterno = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        entrada = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel1.setText("Lider Atual:");

        liderAtual.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        liderAtual.setText("null");
        liderAtual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                liderAtualMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel2.setText("Fluxo de Pacotes e Status");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel3.setText("Token Armazenado: ");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel4.setText("String de validação: ");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel5.setText("Ip Multicast:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel6.setText("Status do Processo:");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel7.setText("ID do processo:");

        idProcesso.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        idProcesso.setText("null");

        tokenArmazenado.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        tokenArmazenado.setText("null");

        statusProcesso.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        statusProcesso.setText("null");

        stringValidacao.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        stringValidacao.setText("null");

        ipMulticast.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        ipMulticast.setText("null");

        saida.setEditable(false);
        saida.setBackground(new java.awt.Color(60, 60, 60));
        saida.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        saida.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        saida.setForeground(new java.awt.Color(254, 254, 254));
        jScrollPane2.setViewportView(saida);

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel8.setText("IP para conexão externa:");

        ipExterno.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        ipExterno.setText("null");

        entrada.setBackground(new java.awt.Color(60, 60, 60));
        entrada.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        entrada.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        entrada.setForeground(new java.awt.Color(254, 254, 254));
        entrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                entradaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(entrada);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 9)); // NOI18N
        jLabel10.setText("Mensagem para multicast");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stringValidacao))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(liderAtual))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tokenArmazenado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idProcesso))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusProcesso))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ipMulticast)
                                .addGap(214, 214, 214)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ipExterno))
                            .addComponent(jLabel10))
                        .addGap(0, 210, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(idProcesso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(statusProcesso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(liderAtual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tokenArmazenado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stringValidacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ipMulticast)
                    .addComponent(jLabel8)
                    .addComponent(ipExterno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(120, 120, 120)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(319, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(35, 35, 35)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void liderAtualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_liderAtualMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_liderAtualMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String enviar = entrada.getText();
        p.comunicaProcesso(enviar);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void entradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entradaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_entradaKeyTyped



    /**
     * @param args the command line arguments
     */  
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane entrada;
    private javax.swing.JLabel idProcesso;
    private javax.swing.JLabel ipExterno;
    private javax.swing.JLabel ipMulticast;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel liderAtual;
    private javax.swing.JTextPane saida;
    private javax.swing.JLabel statusProcesso;
    private javax.swing.JLabel stringValidacao;
    private javax.swing.JLabel tokenArmazenado;
    // End of variables declaration//GEN-END:variables
}
