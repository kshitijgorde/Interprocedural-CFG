// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class aa extends JFrame
{
    BorderLayout do;
    JScrollPane if;
    JTextArea a;
    
    public aa() {
        this.do = new BorderLayout();
        this.if = new JScrollPane();
        this.a = new JTextArea();
        try {
            this.a();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void a() throws Exception {
        this.getContentPane().setLayout(this.do);
        this.setTitle("Logs");
        this.if.getViewport().setBackground(Color.pink);
        this.if.setForeground(Color.orange);
        this.if.setAutoscrolls(true);
        this.if.setDoubleBuffered(true);
        this.a.setBackground(Color.black);
        this.a.setForeground(Color.white);
        this.a.setDoubleBuffered(true);
        this.a.setToolTipText("");
        this.a.setCaretColor(Color.white);
        this.a.setEditable(false);
        this.a.setSelectedTextColor(Color.red);
        this.a.setSelectionColor(SystemColor.inactiveCaptionText);
        this.getContentPane().add(this.if, "Center");
        this.if.getViewport().add(this.a);
        this.a.setText("Logs: ");
    }
}
