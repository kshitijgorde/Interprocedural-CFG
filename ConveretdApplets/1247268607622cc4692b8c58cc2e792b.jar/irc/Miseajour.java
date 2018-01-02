// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import irc.channels.components.Myinfopanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class Miseajour extends JDialog
{
    JLabel lab;
    public JProgressBar bar;
    
    public Miseajour() {
        this.setTitle("Installation");
        this.setContentPane(new Myinfopanel());
        this.setBackground(Color.decode("0xADD8E6"));
        this.bar = new JProgressBar(0, 2500);
        (this.lab = new JLabel("Progression d'installation de Chat-land Messenger")).setHorizontalAlignment(0);
        this.lab.setFont(new Font("Times New Roman", 1, 16));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.getContentPane().setLayout(layout);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(40, 0, 0, 0);
        layout.setConstraints(this.lab, gridBagConstraints);
        this.getContentPane().add(this.lab);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 30, 0, 30);
        layout.setConstraints(this.bar, gridBagConstraints);
        this.getContentPane().add(this.bar);
        this.setSize(500, 160);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - 500) / 2, (screenSize.height - 160) / 2);
        this.validate();
    }
}
