// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Dimension;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import pclient.shd.Config;
import javax.swing.JPanel;

public class AboutBox extends JPanel
{
    private Config paraConf;
    
    public AboutBox(final Config paraConf, final boolean b) {
        this.paraConf = paraConf;
        this.buildGUI(b);
    }
    
    private void buildGUI(final boolean b) {
        this.setLayout(new BorderLayout());
        this.add("Center", this.createAboutPanel());
    }
    
    public JPanel createAboutPanel() {
        final JLabel label = new JLabel(this.paraConf.serverConf("Abt.Head", "ParaChat"), 0);
        final Font font = new Font("Dialog", 1, 12);
        if (font != null) {
            label.setFont(font);
        }
        final JLabel label2 = new JLabel(this.paraConf.serverConf("Abt.Site", "http://www.parachat.com"), 0);
        label2.setForeground(Color.blue);
        final JLabel label3 = new JLabel(this.paraConf.serverConf("Abt.Copy", "Copyright 1996-2010 by ParaChat Group"), 0);
        final JLabel label4 = new JLabel("All Rights Reserved", 0);
        final JPanel panel = new JPanel(new GridLayout(4, 1, 1, 3));
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        final JPanel aboutBottom = this.getAboutBottom(this.paraConf);
        final BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(16);
        final JPanel panel2 = new JPanel(borderLayout);
        panel2.add("North", panel);
        panel2.add("Center", aboutBottom);
        return panel2;
    }
    
    private JPanel getAboutBottom(final Config config) {
        final JLabel label = new JLabel(config.serverConf("Abt.Version", "ParaChat Version:"), 2);
        final JLabel label2 = new JLabel("Your Operating System:", 2);
        final JLabel label3 = new JLabel("Your Java Version:", 2);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 3, 5));
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.setAlignmentX(0.5f);
        final String property = System.getProperty("java.vendor", "unknown");
        final String property2 = System.getProperty("java.version", "unknown");
        final String property3 = System.getProperty("os.name", "unknown");
        final String property4 = System.getProperty("os.version", "unknown");
        final JLabel label4 = new JLabel("9.12", 2);
        final JLabel label5 = new JLabel(property3 + " " + property4, 2);
        final JLabel label6 = new JLabel(property + " " + property2, 2);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6, 1, 3, 5));
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(label6);
        panel2.setAlignmentX(0.5f);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        panel3.add(Box.createHorizontalGlue());
        panel3.add(Box.createRigidArea(new Dimension(30, 30)));
        panel3.add(panel);
        panel3.add(Box.createRigidArea(new Dimension(3, 3)));
        panel3.add(panel2);
        panel3.add(Box.createRigidArea(new Dimension(30, 30)));
        panel3.add(Box.createHorizontalGlue());
        return panel3;
    }
}
