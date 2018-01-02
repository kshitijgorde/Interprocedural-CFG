// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import irc.channels.components.Myinfopanel;
import javax.swing.JDialog;

public class MessageInfo extends JDialog
{
    public MessageInfo() {
        this.setContentPane(new Myinfopanel());
        this.setUndecorated(true);
        this.getContentPane().setLayout(new BorderLayout());
        this.setBackground(Color.decode("0xADD8E6"));
        final JLabel label = new JLabel("Chargement en cours, veuillez patienter...");
        label.setFont(new Font("Times New Roman", 1, 20));
        label.setHorizontalAlignment(0);
        this.getContentPane().add(label, "Center");
        this.setSize(500, 160);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - 500) / 2, (screenSize.height - 160) / 2);
    }
}
