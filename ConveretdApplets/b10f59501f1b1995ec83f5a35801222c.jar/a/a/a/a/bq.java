// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dialog;

public class bq extends Dialog
{
    private final String char = "Help Window";
    private final String a = "ImmerVision PURE Player PRO";
    private final String else = "copyright © ImmerVision 2002-2006";
    private final String case = "http://www.immervision.com";
    private final String int = "HELP WINDOW";
    Font for;
    Font if;
    Font do;
    Color try;
    Color byte;
    private l new;
    
    public bq(final Frame frame, final String s, final l new1, final String s2) {
        super(frame, false);
        this.for = new Font("Arial", 1, 12);
        this.if = new Font("Arial", 2, 10);
        this.do = new Font("Arial", 1, 11);
        this.try = new Color(0, 0, 99);
        this.byte = new Color(255, 255, 255);
        this.new = null;
        this.new = new1;
        this.setTitle("Help Window");
        this.setSize(250, 370);
        this.setBackground(this.byte);
        this.setResizable(false);
        final Panel panel = new Panel(new BorderLayout());
        final Label label = new Label("ImmerVision PURE Player PRO " + s, 1);
        label.setFont(this.for);
        label.setBackground(this.try);
        label.setForeground(this.byte);
        panel.add(label, "North");
        final Label label2 = new Label("copyright © ImmerVision 2002-2006", 1);
        label2.setFont(this.if);
        panel.add(label2, "Center");
        final Panel panel2 = new Panel(new BorderLayout());
        final Label label3 = new Label("HELP WINDOW", 1);
        label3.setFont(this.do);
        panel2.add(label3, "North");
        String s3;
        if (s2.length() != 0) {
            s3 = s2;
        }
        else {
            s3 = "Move :\n  - left mouse button drag\n\nZoom in :\n  - right mouse button drag up\n  - shift key\n  - Mouse wheel up\n\nZoom out :\n  - right mouse button drag down\n  - ctrl key with mouse drag down\n  - Mouse wheel down\n\nExit fullscreen:  press escape key\n";
        }
        final TextArea textArea = new TextArea(s3, 16, 30, 1);
        textArea.setEditable(false);
        panel2.add(textArea, "Center");
        panel2.add(new Label(" "), "South");
        final Label label4 = new Label("http://www.immervision.com", 1);
        label4.setFont(new Font("Arial", 1, 10));
        label4.setBackground(this.try);
        label4.setForeground(this.byte);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.add(panel);
        this.add(panel2);
        this.add(label4);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                bq.this.new.t = null;
                bq.this.dispose();
            }
        });
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.show();
        final Dimension size = textArea.getSize();
        this.setSize(size.width + 20, 370);
        this.setLocation((screenSize.width - size.width + 20) / 2, (screenSize.height - 400) / 2);
        this.hide();
        this.show();
    }
}
