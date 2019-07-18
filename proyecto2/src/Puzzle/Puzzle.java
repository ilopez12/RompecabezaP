package Puzzle;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import java.net.URL;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Puzzle extends javax.swing.JFrame implements ActionListener  {
    
    JButton btnBoton[]= new JButton[16], temp,btnTemp, Verificar;
    int t, i=0,x,y,j=0, x1, y1,r,g,b,comparar, segundos=0, minutos=0, horas=0,direccion =0,direccion2 =0, cont=0,
        arregloSec[]= new int[6], arregloMin[]=new int[6], arregloHora[] = new int[6];
    Random rnd = new Random();
    String jugador, arregloNombres[] = new String[6];
    FileWriter prt;
    File f;;
    
    public void actionPerformed(ActionEvent e)
    {
        if(!timer.isRunning()){
            
         btnTemp = (JButton) e.getSource();
         int r = (int) (Math.random()*1024);
         int g = (int) (Math.random()*1024);
         int b = (int) (Math.random()*1024);
         
         x = btnTemp.getLocation().x;
         y = btnTemp.getLocation().y;
         x1=btnBoton[15].getLocation().x;
         y1=btnBoton[15].getLocation().y;
         
         if((x+50)==x1 && y==y1){
            btnTemp.setBackground(Color.getHSBColor(r, g, b));
             direccion=1;
             direccion2=-50;
             timer.start();
         }else
         if((x-50)==x1 && y==y1){
             btnTemp.setBackground(Color.getHSBColor(r, g, b));
             direccion=-1;
             direccion2=50;
             timer.start();
         }else              
         if(y+50==y1 && x==x1){
             btnTemp.setBackground(Color.getHSBColor(r, g, b));
             direccion=1;
             direccion2=-50;
             timer.start();
            }else
         if(y-50==y1 && x==x1){
             btnTemp.setBackground(Color.getHSBColor(r, g, b));
             direccion=-1;
             direccion2=50;
             timer.start();
            }
       }
    }
    //timer movimiento
    Timer timer = new Timer(2,new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
         x = btnTemp.getLocation().x;
         y = btnTemp.getLocation().y;
         
         if(x!=x1)
         {
             btnBoton[15].setLocation(x1+direccion2, y1);
             btnTemp.setLocation(x+direccion, y);
             Verificar();
         }else
          if(y!=y1)
          {
              btnBoton[15].setLocation(x1, y1+direccion2);
              btnTemp.setLocation(x, y+direccion);
              Verificar();
          }else
         if(x==x1)
         {
             timer.stop();
             x1=x1+direccion2;
             direccion=0;
             direccion2=0;
             Verificar();
         }else
          if(y==y1)
          {
              timer.stop();
              y1=y1+direccion2;
              direccion=0;
              direccion2=0;
              Verificar();
          }          
      }
    });
    // timer cronometro
    Timer contador = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            if(segundos < 60 && horas<5)
            {
                segundos +=1;
                txtSegundos.setText(String.valueOf("0"+segundos));
            }else
            if(segundos <= 60 && minutos<60 && horas<5)
            {
                segundos =0;
                minutos += 1;
                txtMinutos.setText(String.valueOf("0"+minutos));
            }else
            if(minutos <= 60 && horas<5)
            {
                minutos=0;
                horas+=1;
                txtHoras.setText(String.valueOf("0"+horas));
            }else
            if(horas<=5)
            { 
                txtSegundos.setText("00");
                txtMinutos.setText("00");
                txtHoras.setText("00");
                JOptionPane.showMessageDialog(null, "Ups!! the time has run out. Try Again.","Puzzle V 3.0.1",
                   JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Images/emoticon1.jpg"));
                contador.stop();
                for(i=0; i<15; i++)
                {
                    JButton btnReiniciar;
                    btnReiniciar = new JButton();
                    btnReiniciar.setBounds(0+50*(i%4), 0+50*(i/4), 50, 50);
                    x=btnReiniciar.getLocation().x;
                    y=btnReiniciar.getLocation().y;
                    btnBoton[i].setLocation(x,y);
                }
            }
        }
    }
    );
    
    public Puzzle() {
        initComponents();
        Iniciar();
        txtArea.setEditable(false);
        panel2.setForeground(Color.white);
        panel2.setBorder(new TitledBorder(new EtchedBorder()," TOP 5, the Best Gamers"));
        panel2.setBackground(Color.lightGray);
        MenuItemVerificar.setEnabled(false);
        MenuItemRevolver.setEnabled(false);
        MenuItemOrdenar.setEnabled(false);
        
        imprimirJugadores();
    }

    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtHoras = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMinutos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSegundos = new javax.swing.JTextField();
        Panel = new javax.swing.JPanel();
        panel2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuItemIniciar = new javax.swing.JMenu();
        MenuItemNewGame = new javax.swing.JMenuItem();
        MenuItemTest = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MenuItemOrdenar = new javax.swing.JMenuItem();
        MenuItemRevolver = new javax.swing.JMenuItem();
        MenuItemVerificar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rompecabezas");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Time:");

        txtHoras.setEditable(false);

        jLabel3.setText(":");

        txtMinutos.setEditable(false);

        jLabel2.setText(":");

        txtSegundos.setEditable(false);

        Panel.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        txtArea.setBackground(new java.awt.Color(204, 255, 204));
        txtArea.setColumns(20);
        txtArea.setRows(5);
        panel2.setViewportView(txtArea);

        MenuItemIniciar.setText("Game");

        MenuItemNewGame.setText("New Game");
        MenuItemNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemNewGameActionPerformed(evt);
            }
        });
        MenuItemIniciar.add(MenuItemNewGame);

        MenuItemTest.setText("Test");
        MenuItemTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemTestActionPerformed(evt);
            }
        });
        MenuItemIniciar.add(MenuItemTest);
        MenuItemIniciar.add(jSeparator1);

        jMenuItem1.setText("Reset");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuItemIniciar.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MenuItemIniciar.add(jMenuItem2);

        jMenuBar1.add(MenuItemIniciar);

        jMenu5.setText("Options");

        MenuItemOrdenar.setText("Order");
        MenuItemOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemOrdenarActionPerformed(evt);
            }
        });
        jMenu5.add(MenuItemOrdenar);

        MenuItemRevolver.setText("stir");
        MenuItemRevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemRevolverActionPerformed(evt);
            }
        });
        jMenu5.add(MenuItemRevolver);

        MenuItemVerificar.setText("Verify");
        MenuItemVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemVerificarActionPerformed(evt);
            }
        });
        jMenu5.add(MenuItemVerificar);

        jMenuBar1.add(jMenu5);

        jMenu1.setText("Help");

        MenuItemAbout.setText("About");
        MenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAboutActionPerformed(evt);
            }
        });
        jMenu1.add(MenuItemAbout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemNewGameActionPerformed
        this.Panel.removeAll();
        Iniciar();
        MenuItemVerificar.setEnabled(true);
        MenuItemRevolver.setEnabled(true);
        MenuItemOrdenar.setEnabled(true);
        this.paintAll(this.getGraphics());
        contador.start();
        Revolver();

        for (i=0; i<16; i++)
        {
            btnBoton[i].addActionListener(this);
        }
    }//GEN-LAST:event_MenuItemNewGameActionPerformed

    private void MenuItemTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemTestActionPerformed
        MenuItemVerificar.setEnabled(true);
        MenuItemRevolver.setEnabled(true);
        MenuItemOrdenar.setEnabled(true);
        this.Panel.removeAll();
        Iniciar();
        contador.start();
        for(i=0; i<15; i++){
            btnBoton[i].addActionListener(this);
        }

        x= btnBoton[14].getLocation().x;
        y= btnBoton[14].getLocation().y;
        x1= btnBoton[15].getLocation().x;
        y1= btnBoton[15].getLocation().y;
        btnBoton[14].setLocation(x1,y1);
        btnBoton[15].setLocation(x, y);
    }//GEN-LAST:event_MenuItemTestActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
        Puzzle.main(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void MenuItemOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemOrdenarActionPerformed
        for(i=0; i<16; i++)
        {
            JButton btnOrdenar;
            btnOrdenar= new JButton();
            btnOrdenar.setBounds(0+50*(i%4), 0+50*(i/4), 50, 50);
            x=btnOrdenar.getLocation().x;
            y=btnOrdenar.getLocation().y;
            btnBoton[i].setLocation(x,y);
        }
    }//GEN-LAST:event_MenuItemOrdenarActionPerformed

    private void MenuItemRevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemRevolverActionPerformed
        Revolver();
    }//GEN-LAST:event_MenuItemRevolverActionPerformed

    private void MenuItemVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemVerificarActionPerformed
        Verificar();
        cont=0;
    }//GEN-LAST:event_MenuItemVerificarActionPerformed

    private void MenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAboutActionPerformed
        
    }//GEN-LAST:event_MenuItemAboutActionPerformed

    //Iniciar
    private void Iniciar()
    {
        this.Panel.removeAll();
        segundos =0;
        minutos =0;
        horas=0;
        cont=0;
        txtSegundos.setText("00");
        txtMinutos.setText("00");
        txtHoras.setText("00");
        contador.stop();
        timer.stop();
        
        for (i=0; i<16; i++)
            {   
                btnBoton[i]= new JButton(String.valueOf(i+1));
                btnBoton[i].setBounds(0+50*(i%4), 0+50*(i/4), 50, 50);
                btnBoton[i].setForeground(Color.black);
                Panel.add(btnBoton[i]);
            }
        btnBoton[15].setVisible(false);
    }
    //Revolver numeros
   private void Revolver()
    {
        for (i=0; i <15; i++)
            {
                t= rnd.nextInt(15);
                temp = btnBoton[i];
                x= temp.getLocation().x;
                y= temp.getLocation().y;
                x1= btnBoton[t].getLocation().x;
                y1= btnBoton[t].getLocation().y;
                btnBoton[t].setLocation(x,y);
                temp.setLocation(x1, y1);
            }
    }
   //Verificar orden
   private void Verificar()
   {
       for(i=0; i<15; i++){
            Verificar=new JButton();
            Verificar.setBounds(0+50*(i%4), 0+50*(i/4), 50, 50);
                
            x = Verificar.getLocation().x;
            y = Verificar.getLocation().y;
            
            if(((btnBoton[i].getLocation().x)==x) && ((btnBoton[i].getLocation().y)==y))
            {
                cont+=1;
            }else{
                cont=0;
            }
        }
       
        if(cont==15){
            timer.stop();
            contador.stop();
            estaOrdenadoElpuzzle();
        }else
        {
            cont=0;
        }
   }
   //metodo que verifica si el jugador entra en el puntaje
   private void estaOrdenadoElpuzzle(){
    for(i=0; i<5; i++){
        if(horas<=arregloHora[i] && minutos<=arregloMin[i] && segundos<=arregloSec[i] || horas<=arregloHora[i] && minutos>=arregloMin[i] && segundos<=arregloSec[i]){
            OrdenarJugador();
        }else
        if(horas<=arregloHora[i] && minutos<=arregloMin[i] && segundos>=arregloSec[i] || horas<=arregloHora[i] && minutos>=arregloMin[i] && segundos>=arregloSec[i]){
            OrdenarJugador();
        }
    }
  }
   
   public void OrdenarJugador(){
           jugador = (String) JOptionPane.showInputDialog(this, new JLabel("Congratulations you've ordered the puzzle. Enter your name: ",JLabel.CENTER),
              "Puzzle V 3.0.1",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Images/emoticon3.jpg"),null,"");
           if(jugador!=null && !jugador.equals(" ") && !jugador.equals("  ") && !jugador.equals("   ") && !jugador.equals("    ")
              && !jugador.equals("     ") &&  !jugador.equals("      ")){ 
           JOptionPane.showMessageDialog(this, "Thanks for playing. You can start a new game wherever you want.","Puzzle V 3.0.1", 
              JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Images/emoticon2.jpg"));
           arregloSec[5]=segundos;
           arregloMin[5]=minutos;
           arregloHora[5]=horas;
           arregloNombres[5]=jugador;
           
           for (i=0; i<15; i++){
           btnBoton[i].removeActionListener(this);
           }
           this.Panel.removeAll();
           Iniciar();
           this.paintAll(this.getGraphics());
           MenuItemVerificar.setEnabled(false);
           MenuItemRevolver.setEnabled(false);
           MenuItemOrdenar.setEnabled(false);
           segundos =0;
           minutos =0;
           horas=0;
           txtSegundos.setText("00");
           txtMinutos.setText("00");
           txtHoras.setText("00");
           cont=0;
           //ordenar por hora
           for(i=0; i<=5 - 1; i++){ 
               for(j=0; j<=5 - 1; j++){
                    if(arregloHora[j]>=arregloHora[j+1])
                        {
                            int tmp1 = arregloHora[j+1];
                            arregloHora[j+1]=arregloHora[j];
                            arregloHora[j]=tmp1;
                            
                            int tmp2 = arregloMin[j+1];
                            arregloMin[j+1]=arregloMin[j];
                            arregloMin[j]=tmp2;
                            
                            int tmp3 = arregloSec[j+1];
                            arregloSec[j+1] = arregloSec[j];
                            arregloSec[j] = tmp3;
                            
                            String tmp4=arregloNombres[j+1];
                            arregloNombres[j+1]=arregloNombres[j];
                            arregloNombres[j]=tmp4;
                        }
                }
          }
           //ordena por minutos
           for(i=0; i<=5 - 1; i++){ 
               for(j=0; j<=5 - 1; j++){
                    if(arregloMin[j]>=arregloMin[j+1] && arregloHora[j]==arregloHora[j+1])
                        {
                            int tmp1 = arregloHora[j+1];
                            arregloHora[j+1]=arregloHora[j];
                            arregloHora[j]=tmp1;
                            
                            int tmp2 = arregloMin[j+1];
                            arregloMin[j+1]=arregloMin[j];
                            arregloMin[j]=tmp2;
                            
                            int tmp3 = arregloSec[j+1];
                            arregloSec[j+1] = arregloSec[j];
                            arregloSec[j] = tmp3;
                            
                            String tmp4=arregloNombres[j+1];
                            arregloNombres[j+1]=arregloNombres[j];
                            arregloNombres[j]=tmp4;
                        }
                }
          }
           // ordena por segundos
           for(i=0; i<=5 - 1; i++){ 
               for(j=0; j<=5 - 1; j++){
                    if(arregloSec[j]>=arregloSec[j+1] && arregloMin[j]==arregloMin[j+1])
                        {
                            int tmp1 = arregloHora[j+1];
                            arregloHora[j+1]=arregloHora[j];
                            arregloHora[j]=tmp1;
                            
                            int tmp2 = arregloMin[j+1];
                            arregloMin[j+1]=arregloMin[j];
                            arregloMin[j]=tmp2;
                            
                            int tmp3 = arregloSec[j+1];
                            arregloSec[j+1] = arregloSec[j];
                            arregloSec[j] = tmp3;
                            
                            String tmp4=arregloNombres[j+1];
                            arregloNombres[j+1]=arregloNombres[j];
                            arregloNombres[j]=tmp4;
                        }
                }
          }
        }else
          {
           JOptionPane.showMessageDialog(this, "Ups!, you haven't introduced your name. Try again with another game.","Puzzle V 3.0.1",
              JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Images/emoticon1.jpg"));
           for (i=0; i<15; i++){
           btnBoton[i].removeActionListener(this);
           }
           this.Panel.removeAll();
           Iniciar();
           this.paintAll(this.getGraphics());
           MenuItemVerificar.setEnabled(false);
           MenuItemRevolver.setEnabled(false);
           MenuItemOrdenar.setEnabled(false);
           segundos =0;
           minutos =0;
           horas=0;
           txtSegundos.setText("00");
           txtMinutos.setText("00");
           txtHoras.setText("00");
           cont=0;
           //ordenar por hora
           for(i=0; i<5 - 1; i++){ 
               for(j=0; j<5 - 1; j++){
                    if(arregloHora[j]>=arregloHora[j+1])
                        {
                            int tmp1 = arregloHora[j+1];
                            arregloHora[j+1]=arregloHora[j];
                            arregloHora[j]=tmp1;
                            
                            int tmp2 = arregloMin[j+1];
                            arregloMin[j+1]=arregloMin[j];
                            arregloMin[j]=tmp2;
                            
                            int tmp3 = arregloSec[j+1];
                            arregloSec[j+1] = arregloSec[j];
                            arregloSec[j] = tmp3;
                            
                            String tmp4=arregloNombres[j+1];
                            arregloNombres[j+1]=arregloNombres[j];
                            arregloNombres[j]=tmp4;
                        }
                }
          }
           //ordena por minutos
           for(i=0; i<5 - 1; i++){ 
               for(j=0; j<5 - 1; j++){
                    if(arregloMin[j]>=arregloMin[j+1] && arregloHora[j]==arregloHora[j+1])
                        {
                            int tmp1 = arregloHora[j+1];
                            arregloHora[j+1]=arregloHora[j];
                            arregloHora[j]=tmp1;
                            
                            int tmp2 = arregloMin[j+1];
                            arregloMin[j+1]=arregloMin[j];
                            arregloMin[j]=tmp2;
                            
                            int tmp3 = arregloSec[j+1];
                            arregloSec[j+1] = arregloSec[j];
                            arregloSec[j] = tmp3;
                            
                            String tmp4=arregloNombres[j+1];
                            arregloNombres[j+1]=arregloNombres[j];
                            arregloNombres[j]=tmp4;
                        }
                }
          }
           // ordena por segundos
           for(i=0; i<5 - 1; i++){ 
               for(j=0; j<5 - 1; j++){
                    if(arregloSec[j]>=arregloSec[j+1] && arregloMin[j]==arregloMin[j+1])
                        {
                            int tmp1 = arregloHora[j+1];
                            arregloHora[j+1]=arregloHora[j];
                            arregloHora[j]=tmp1;
                            
                            int tmp2 = arregloMin[j+1];
                            arregloMin[j+1]=arregloMin[j];
                            arregloMin[j]=tmp2;
                            
                            int tmp3 = arregloSec[j+1];
                            arregloSec[j+1] = arregloSec[j];
                            arregloSec[j] = tmp3;
                            
                            String tmp4=arregloNombres[j+1];
                            arregloNombres[j+1]=arregloNombres[j];
                            arregloNombres[j]=tmp4;
                        }
                }
          }
        }
        // guarda el nombre del jugador
        try{
            String ruta="Jugadores\\Jugadores.txt";
            prt = new FileWriter(ruta,false);
            prt = new FileWriter(ruta,true);
            for(i=0; i<5; i++){
            prt.write(arregloNombres[i]+"\r\n");
            prt.write(arregloHora[i]+"\r\n");
            prt.write(arregloMin[i]+"\r\n");
            prt.write(arregloSec[i]+"\r\n");
            }
            prt.close();
        }catch (Exception e)
         {
            JOptionPane.showMessageDialog(this, "Error al grabar el nombre","Rompecabezas",JOptionPane.ERROR_MESSAGE);
         }
          imprimirJugadores(); 
}
   
   private void imprimirJugadores(){
       // imprime el nombre y el tiempo que le tomo al jugador completar el puzzle en un txtArea
        int m=0;
        txtArea.setText(null);
        String ruta="Jugadores\\Jugadores.txt";
        f= new File(ruta);
        Scanner sc;
        String leerNombre;
        int leerPuntajeSec, leerPuntajeMin, leerPuntajeHora;
        try{
            txtArea.setText("Name"+"\t"+"Time"+"\n\n");
            sc = new Scanner(f);
            while(sc.hasNextLine())
            {
               if(m<5){
               leerNombre=sc.nextLine();
               leerPuntajeHora=Integer.parseInt(sc.nextLine());
               leerPuntajeMin=Integer.parseInt(sc.nextLine());
               leerPuntajeSec=Integer.parseInt(sc.nextLine());
               txtArea.setFont(new Font("Tahoma",1,13));
               if(leerPuntajeMin<10 && leerPuntajeSec<10){
               txtArea.setText(txtArea.getText()+leerNombre+"\t"+"0"+leerPuntajeHora+":0"+leerPuntajeMin+":0"+leerPuntajeSec+"\n");   
               }else
               if(leerPuntajeMin<10 && leerPuntajeSec>=10){
               txtArea.setText(txtArea.getText()+leerNombre+"\t"+"0"+leerPuntajeHora+":0"+leerPuntajeMin+":"+leerPuntajeSec+"\n");
               }
               if(leerPuntajeMin>=10 && leerPuntajeSec<10){
               txtArea.setText(txtArea.getText()+leerNombre+"\t"+"0"+leerPuntajeHora+":"+leerPuntajeMin+":0"+leerPuntajeSec+"\n");
               }else 
               if(leerPuntajeMin>=10 && leerPuntajeSec>=10){
               txtArea.setText(txtArea.getText()+leerNombre+"\t"+"0"+leerPuntajeHora+":"+leerPuntajeMin+":"+leerPuntajeSec+"\n");
               }
               arregloNombres[m]=leerNombre;
               arregloHora[m]=leerPuntajeHora;
               arregloMin[m]=leerPuntajeMin;
               arregloSec[m]=leerPuntajeSec;
               m+=1;
               }
            }
            sc.close();
        }catch (Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Error al leer los datos","Rompecabezas", JOptionPane.ERROR_MESSAGE);
        }
   }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Puzzle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemAbout;
    private javax.swing.JMenu MenuItemIniciar;
    private javax.swing.JMenuItem MenuItemNewGame;
    private javax.swing.JMenuItem MenuItemOrdenar;
    private javax.swing.JMenuItem MenuItemRevolver;
    private javax.swing.JMenuItem MenuItemTest;
    private javax.swing.JMenuItem MenuItemVerificar;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JScrollPane panel2;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtHoras;
    private javax.swing.JTextField txtMinutos;
    private javax.swing.JTextField txtSegundos;
    // End of variables declaration//GEN-END:variables
}
