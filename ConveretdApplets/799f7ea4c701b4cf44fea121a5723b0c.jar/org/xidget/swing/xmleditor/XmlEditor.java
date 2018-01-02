// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class XmlEditor extends JFrame
{
    public static void main(final String[] array) {
        new XmlEditor().setVisible(true);
    }
    
    public XmlEditor() {
        super("XML Text Editor Demo");
        this.setSize(800, 600);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.add(new XmlTextPane());
        this.add(panel);
    }
}
