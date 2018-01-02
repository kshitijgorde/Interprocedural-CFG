// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Color;
import irc.com.utils.MD5Nick;
import irc.com.utils.MySQL;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class suggestion extends JDialog implements ActionListener
{
    private JComboBox listesug;
    private JLabel listsuglabel;
    private GridBagLayout layout;
    private JTextArea text;
    private GridBagConstraints c;
    private JButton envoyer;
    private JScrollPane scroll;
    private EIRC eirc1;
    
    public suggestion(final EIRC eirc1) {
        super(eirc1.getFrame(), "Suggestion", true);
        this.eirc1 = eirc1;
        this.init();
        this.setLocation(this.eirc1.getFrame().getLocation().x - 50, this.eirc1.getFrame().getLocation().y + 100);
        this.setResizable(false);
    }
    
    public suggestion(final main main) {
        super(main.Application, "Suggestion", true);
        if (main.eirc != null) {
            this.eirc1 = main.eirc;
        }
        this.init();
        if (this.eirc1 != null) {
            this.setLocation(this.eirc1.getFrame().getLocation().x - 50, this.eirc1.getFrame().getLocation().y + 100);
        }
        this.setResizable(false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.envoyer)) {
            if (this.text.getText().length() < 2) {
                JOptionPane.showMessageDialog(this, "Veuillez introduire votre suggestion ");
                this.text.requestFocus();
                return;
            }
            final MySQL mySQL = new MySQL("http://java.chat-land.org:8080/suggestion/get");
            String nick = "sanspseudo";
            if (this.eirc1 != null && this.eirc1.getNick() != null) {
                nick = this.eirc1.getNick();
            }
            mySQL.addParam("a", nick);
            mySQL.addParam("b", MD5Nick.getMD5_3(nick).substring(1, 6));
            mySQL.addParam("c", (String)this.listesug.getSelectedItem());
            mySQL.addParam("d", this.text.getText());
            mySQL.execute();
            JOptionPane.showMessageDialog(this, "Votre suggestion a \u00e9t\u00e9 envoy\u00e9 avec succ\u00e8s.");
            this.dispose();
        }
    }
    
    public void init() {
        this.listsuglabel = new JLabel("Je voudrais : ");
        (this.listesug = new JComboBox()).addItem("Proposer l'am\u00e9lioration d'un service");
        this.listesug.addItem("Proposer l'ajout d'une fonctionnalit\u00e9");
        this.listesug.addItem("Signaler un bug");
        this.listesug.addItem("Contacter le service client");
        this.listesug.addItem("Autre");
        (this.text = new JTextArea()).setBackground(Color.white);
        this.layout = new GridBagLayout();
        this.envoyer = new JButton("Envoyer");
        this.c = new GridBagConstraints();
        (this.scroll = new JScrollPane()).setVerticalScrollBarPolicy(22);
        this.scroll.getViewport().add(this.text);
        this.getContentPane().setLayout(this.layout);
        this.c.insets = new Insets(5, 5, 5, 5);
        this.c.fill = 2;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.gridwidth = 1;
        this.getContentPane().add(this.listsuglabel, this.c);
        this.c.insets = new Insets(5, 5, 5, 5);
        this.c.fill = 2;
        this.c.gridx = 1;
        this.c.gridy = 0;
        this.c.gridwidth = 2;
        this.getContentPane().add(this.listesug, this.c);
        this.c.insets = new Insets(5, 5, 5, 5);
        this.c.fill = 2;
        this.c.gridx = 3;
        this.c.gridy = 0;
        this.c.gridwidth = 1;
        this.getContentPane().add(this.envoyer, this.c);
        this.c.insets = new Insets(10, 10, 10, 10);
        this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.gridwidth = 4;
        this.c.gridheight = 4;
        this.c.weightx = 1.0;
        this.c.weighty = 1.0;
        this.c.fill = 1;
        this.c.anchor = 10;
        this.getContentPane().add(this.scroll, this.c);
        this.envoyer.addActionListener(this);
        this.setSize(500, 400);
    }
}
