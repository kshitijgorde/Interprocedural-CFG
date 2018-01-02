// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class MessageShow extends JDialog implements ActionListener
{
    JLabel text;
    JButton ok;
    JButton no;
    main lan;
    res r;
    
    public MessageShow(final main lan) {
        super(lan.messageinfo, "Chat-land", true);
        this.text = new JLabel("<html><body><center><font size=\"4\" color=\"#0000FF\">Votre connexion internet semble rencontrer un probl\u00e9me.</center> </br><center>Cliquez sur le bouton Oui pour r\u00e9essayer </font></center></body></html>");
        this.ok = new JButton("Oui");
        this.no = new JButton("Non");
        this.lan = lan;
        this.no.addActionListener(this);
        this.ok.addActionListener(this);
        this.r = new res();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.text, "Center");
        final JPanel panel = new JPanel(new GridLayout(2, 5));
        panel.add(new JLabel());
        panel.add(this.ok);
        panel.add(new JLabel());
        panel.add(this.no);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        this.setResizable(false);
        this.setSize(350, 140);
        this.setLocation(lan.messageinfo.getLocation().x + 75, lan.messageinfo.getLocation().y + 10);
        this.getContentPane().add(panel, "South");
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.no)) {
            System.exit(0);
        }
        else {
            this.r.interrupt();
            this.dispose();
        }
    }
    
    class res extends Thread
    {
        public res() {
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(60000L);
            }
            catch (InterruptedException ex) {}
            MessageShow.this.dispose();
        }
    }
}
