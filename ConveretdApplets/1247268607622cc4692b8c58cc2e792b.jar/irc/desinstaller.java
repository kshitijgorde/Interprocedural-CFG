// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import irc.com.utils.MD5Nick;
import irc.com.utils.MySQL;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class desinstaller extends JDialog implements ActionListener
{
    private JLabel listsuglabel;
    private GridBagLayout layout;
    private JTextArea text;
    private GridBagConstraints c;
    private JScrollPane scroll;
    private EIRC eirc1;
    private JButton valider;
    private JButton annuler;
    private main m;
    
    public desinstaller(final EIRC eirc1) {
        super(eirc1.getFrame(), "D\u00e9sinstaller", true);
        this.m = null;
        this.eirc1 = eirc1;
        this.init();
        this.setLocation(this.eirc1.getFrame().getLocation().x - 50, this.eirc1.getFrame().getLocation().y + 100);
        this.setResizable(false);
    }
    
    public desinstaller(final main m) {
        super(m.Application, "D\u00e9sinstaller", true);
        this.m = null;
        this.m = m;
        this.init();
        this.setLocation(this.m.Application.getLocation().x - 50, this.m.Application.getLocation().y + 100);
        this.setResizable(false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.valider)) {
            if (this.text.getText().length() < 2) {
                JOptionPane.showMessageDialog(this, "Veuillez introduire le motif de d\u00e9sinstallation");
                this.text.requestFocus();
                return;
            }
            final MySQL mySQL = new MySQL("http://java.chat-land.org:8080/suggestion/get");
            if (this.eirc1 != null) {
                mySQL.addParam("a", this.eirc1.getNick());
                mySQL.addParam("b", MD5Nick.getMD5_3(this.eirc1.getNick()).substring(1, 6));
            }
            mySQL.addParam("c", "desinstaller");
            mySQL.addParam("d", this.text.getText());
            mySQL.execute();
            final Runtime runtime = Runtime.getRuntime();
            final Date date = new Date(System.currentTimeMillis());
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            final MySQL mySQL2 = new MySQL("http://chat-land.org/clmessenger/savelancer.php");
            mySQL2.addParam("a", "desinstall");
            String s;
            if (this.eirc1 != null) {
                s = this.eirc1.getNick() + ",," + simpleDateFormat.format(date) + ",," + System.getProperty("java.version") + ",," + System.getProperty("os.name");
            }
            else {
                s = " ,," + simpleDateFormat.format(date) + ",," + System.getProperty("java.version") + ",," + System.getProperty("os.name");
            }
            mySQL2.addParam("b", s.replaceAll(" ", ""));
            mySQL2.execute();
            if (new File(main.homeapp + "\\UChatLand.jar").exists()) {
                try {
                    runtime.exec("cmd /c \"" + main.homeapp + "\\UChatLand.jar\"");
                }
                catch (Exception ex) {}
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
            if (this.m != null && this.m.eirc != null) {
                JOptionPane.showMessageDialog(this.m, "D\u00e9sinstallation termin\u00e9e avec succ\u00e8s, ce Produit est totalement supprim\u00e9 de votre Ordinateur");
                this.m.eirc.end();
            }
            else if (this.eirc1 != null) {
                JOptionPane.showMessageDialog(this.eirc1, "D\u00e9sinstallation termin\u00e9e avec succ\u00e8s, ce Produit est totalement supprim\u00e9 de votre Ordinateur");
                this.eirc1.end();
            }
            else {
                if (this.m != null) {
                    JOptionPane.showMessageDialog(this.m, "D\u00e9sinstallation termin\u00e9e avec succ\u00e8s, ce Produit est totalement supprim\u00e9 de votre Ordinateur");
                }
                else {
                    JOptionPane.showMessageDialog(null, "D\u00e9sinstallation termin\u00e9e avec succ\u00e8s, ce Produit est totalement supprim\u00e9 de votre Ordinateur");
                }
                System.exit(0);
            }
        }
        if (actionEvent.getSource().equals(this.annuler)) {
            this.dispose();
        }
    }
    
    public void init() {
        this.listsuglabel = new JLabel("<html><center><font size=\"3\">Vous \u00eates sur le point de d\u00e9sinstaller Chat-land Messenger ! <br/>Veuillez introduire le motif de d\u00e9sinstallation dans le champ ci-dessous </font></center></html>");
        this.valider = new JButton("Valider");
        this.annuler = new JButton("Annuler");
        this.text = new JTextArea();
        this.layout = new GridBagLayout();
        this.c = new GridBagConstraints();
        (this.scroll = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.scroll.getViewport().add(this.text);
        this.getContentPane().setLayout(this.layout);
        this.text.setBackground(Color.white);
        this.c.insets = new Insets(10, 5, 5, 5);
        this.c.fill = 10;
        this.c.anchor = 10;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.gridwidth = 4;
        this.getContentPane().add(this.listsuglabel, this.c);
        this.c.insets = new Insets(10, 10, 10, 10);
        this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.gridwidth = 4;
        this.c.gridheight = 4;
        this.c.weightx = 4.0;
        this.c.weighty = 4.0;
        this.c.fill = 1;
        this.c.anchor = 10;
        this.getContentPane().add(this.scroll, this.c);
        this.c.insets = new Insets(10, 10, 10, 10);
        this.c.gridx = 0;
        this.c.gridy = 5;
        this.c.gridwidth = 1;
        this.c.gridheight = 1;
        this.c.weightx = 1.0;
        this.c.weighty = 1.0;
        this.c.fill = 2;
        this.c.anchor = 10;
        this.getContentPane().add(this.valider, this.c);
        this.c.insets = new Insets(10, 10, 10, 10);
        this.c.gridx = 3;
        this.c.gridy = 5;
        this.c.gridwidth = 1;
        this.c.gridheight = 1;
        this.c.weightx = 1.0;
        this.c.weighty = 1.0;
        this.c.fill = 2;
        this.c.anchor = 10;
        this.getContentPane().add(this.annuler, this.c);
        this.setSize(500, 400);
        this.valider.addActionListener(this);
        this.annuler.addActionListener(this);
    }
}
