// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.dialog;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import irc.managers.Resources;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class PasswordDialog extends JDialog
{
    private JPanel panel1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPasswordField jPasswordField1;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel4;
    private String passe;
    
    public PasswordDialog() {
        this(new Frame(), "Chat-Land.org", false);
    }
    
    public PasswordDialog(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
        this.panel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jPasswordField1 = new JPasswordField();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jLabel4 = new JLabel();
        try {
            this.setDefaultCloseOperation(2);
            this.jbInit();
            this.pack();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String getPasse() {
        return this.passe;
    }
    
    private void jbInit() throws Exception {
        this.panel1.setLayout(null);
        this.jLabel1.setHorizontalAlignment(2);
        this.jLabel1.setHorizontalTextPosition(2);
        this.jLabel1.setText("mot de passe puis cliquez sur ok sinon cliquez sur annuler");
        this.jLabel1.setBounds(new Rectangle(88, 29, 338, 23));
        this.jLabel2.setHorizontalAlignment(2);
        this.jLabel2.setHorizontalTextPosition(2);
        this.jLabel2.setText("pour choisir un autre pseudo.");
        this.jLabel2.setBounds(new Rectangle(87, 52, 321, 23));
        this.jLabel3.setHorizontalAlignment(2);
        this.jLabel3.setHorizontalTextPosition(2);
        this.jLabel3.setText("Ce pseudo est enregistr\u00e9 si c'est le votre veuillez saisir votre");
        this.jLabel3.setBounds(new Rectangle(87, 6, 338, 23));
        this.jButton1.setBounds(new Rectangle(116, 109, 99, 23));
        this.jButton1.setText("OK");
        this.jButton1.addActionListener(new PasswordDialog_jButton1_actionAdapter(this));
        this.jButton2.setBounds(new Rectangle(242, 109, 99, 23));
        this.jButton2.setText("Annuler");
        this.jButton2.addActionListener(new PasswordDialog_jButton2_actionAdapter(this));
        this.jLabel4.setIcon(new ImageIcon(Resources.getImages("icone.password")));
        this.jLabel4.setBounds(new Rectangle(10, 8, 67, 88));
        this.jPasswordField1.setBounds(new Rectangle(88, 75, 302, 24));
        this.jPasswordField1.addKeyListener(new PasswordDialog_jPasswordField1_keyAdapter(this));
        this.getContentPane().add(this.panel1);
        this.panel1.add(this.jLabel3, null);
        this.panel1.add(this.jLabel1, null);
        this.panel1.add(this.jLabel2, null);
        this.panel1.add(this.jPasswordField1, null);
        this.panel1.add(this.jButton1, null);
        this.panel1.add(this.jButton2, null);
        this.panel1.add(this.jLabel4, null);
    }
    
    public void jButton1_actionPerformed(final ActionEvent actionEvent) {
        this.jPasswordField1.selectAll();
        this.setPasse(this.jPasswordField1.getSelectedText());
        this.dispose();
    }
    
    public void jButton2_actionPerformed(final ActionEvent actionEvent) {
        this.setPasse(null);
        this.dispose();
    }
    
    public void jPasswordField1_keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.jPasswordField1.selectAll();
            this.setPasse(this.jPasswordField1.getSelectedText());
            this.dispose();
        }
    }
    
    public void setPasse(final String passe) {
        this.passe = passe;
    }
}
