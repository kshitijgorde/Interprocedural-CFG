// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.FlowLayout;
import pclient.shd.Config;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import pclient.bsc.BaseChat;
import java.awt.Panel;

public class AboutPanel extends Panel
{
    private BaseChat pChat;
    
    public AboutPanel(final BaseChat pChat, final boolean b) {
        this.pChat = pChat;
        this.buildGUI(b);
    }
    
    private void buildGUI(final boolean b) {
        this.setLayout(new BorderLayout());
        this.add("Center", this.createAboutPanel());
    }
    
    public Panel createAboutPanel() {
        final Label label = new Label(this.pChat.paraConf.serverConf("Abt.Head", "ParaChat"), 1);
        final Font font = new Font("Dialog", 1, 12);
        if (font != null) {
            label.setFont(font);
        }
        final Label label2 = new Label(this.pChat.paraConf.serverConf("Abt.Site", "http://www.parachat.com"), 1);
        label2.setForeground(Color.blue);
        final Label label3 = new Label(this.pChat.paraConf.serverConf("Abt.Copy", "Copyright 1996-2010 by ParaChat Group"), 1);
        final Label label4 = new Label("All Rights Reserved", 1);
        final Panel panel = new Panel(new GridLayout(4, 1, 1, 3));
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        final Panel aboutBottom = this.getAboutBottom(this.pChat.paraConf);
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add("North", panel);
        panel2.add("Center", aboutBottom);
        return panel2;
    }
    
    private Panel getAboutBottom(final Config config) {
        final Label label = new Label(this.pChat.paraConf.serverConf("Abt.Version", "ParaChat Version:"), 0);
        final Label label2 = new Label("Your Operating System:", 0);
        final Label label3 = new Label("Your Java Version:", 0);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(6, 1, 3, 5));
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        final String property = System.getProperty("java.vendor", "unknown");
        final String property2 = System.getProperty("java.version", "unknown");
        final String property3 = System.getProperty("os.name", "unknown");
        final String property4 = System.getProperty("os.version", "unknown");
        final Label label4 = new Label("9.12", 0);
        final Label label5 = new Label(property3 + " " + property4, 0);
        final Label label6 = new Label(property + " " + property2, 0);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(6, 1, 3, 5));
        panel2.add(label4);
        panel2.add(label5);
        panel2.add(label6);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1));
        panel3.add(panel);
        panel3.add(panel2);
        return panel3;
    }
}
