// 
// Decompiled by Procyon v0.5.30
// 

package inscripcion;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.EventQueue;
import javax.swing.text.Document;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JApplet;

public class Buscador extends JApplet
{
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLdni;
    private JTextField jTFdni;
    
    public void init() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    Buscador.this.initComponents();
                    Buscador.this.jTFdni.setDocument(new JTextFieldLimit(8, true));
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        this.jTFdni = new JTextField();
        this.jLdni = new JLabel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jButton1 = new JButton();
        this.jTFdni.setColumns(8);
        this.jLdni.setText("DNI (sin letra)");
        this.jLabel1.setText("jLabel1");
        this.jLabel2.setText("jLabel2");
        this.jLabel3.setText("jLabel3");
        this.jButton1.setText("jButton1");
        this.jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                Buscador.this.jButton1MouseClicked(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLdni).addGroup(layout.createSequentialGroup().addComponent(this.jTFdni, -2, -1, -2).addGap(83, 83, 83)).addComponent(this.jButton1).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jLabel3)).addContainerGap(28, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLdni).addGap(1, 1, 1).addComponent(this.jTFdni, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jLabel3).addContainerGap(145, 32767)));
    }
    
    private void jButton1MouseClicked(final MouseEvent evt) {
        if (this.jTFdni.getText().length() == 8 && Integer.valueOf(this.jTFdni.getText()) > 10000000) {
            try {
                Driver.class.newInstance();
            }
            catch (InstantiationException ex) {
                this.jLabel1.setText(ex.toString());
            }
            catch (IllegalAccessException ex2) {
                this.jLabel1.setText(ex2.toString());
            }
            try {
                final Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/carrera?user=root&password=novell");
                final Statement stm = cnx.createStatement();
                ResultSet rst = null;
                rst = stm.executeQuery("SELECT nombre, apellido1, apellido2 FROM inscripciones WHERE dni='" + this.jTFdni.getText() + "'");
                if (rst.next()) {
                    this.jLabel1.setText(rst.getString(1));
                    this.jLabel2.setText(rst.getString(2));
                    this.jLabel3.setText(rst.getString(3));
                }
                else {
                    this.jLabel1.setText("");
                    this.jLabel2.setText("");
                    this.jLabel3.setText("");
                }
                rst.close();
                stm.close();
                cnx.close();
            }
            catch (SQLException ex3) {
                this.jLabel1.setText(ex3.toString());
            }
        }
    }
}
