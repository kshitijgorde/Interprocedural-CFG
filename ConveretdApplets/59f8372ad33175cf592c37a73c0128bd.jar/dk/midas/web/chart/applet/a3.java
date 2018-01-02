// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class a3 extends Dialog implements ActionListener
{
    static String a;
    TextArea if;
    Button do;
    
    a3(final Frame frame, final String s, final int n) {
        super(frame, "About Box");
        this.setResizable(false);
        this.setModal(true);
        final Dimension size = frame.getSize();
        this.setLocation(size.width / 2, size.height / 2);
        final Panel panel = new Panel();
        this.add("North", panel);
        (this.if = new TextArea(s, s.length() / n, n, 3) {
            public boolean isFocusTraversable() {
                return false;
            }
        }).setEditable(false);
        panel.add(this.if);
        final Panel panel2 = new Panel();
        this.add("South", panel2);
        panel2.add("Center", this.do = new Button(a3.a));
        this.do.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                a3.this.setVisible(false);
            }
        });
    }
    
    public void a(final String s) {
        this.if.replaceRange(s, 0, this.if.getText().length());
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.do) {
            this.setVisible(false);
        }
    }
    
    static {
        a3.a = new String("Ok");
    }
}
