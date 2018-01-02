// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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

public class b9 extends Dialog
{
    private final String case = "Help";
    private final String a = "PURE Player PRO for Java";
    private final String try = "http://www.immervision.com/multimedia";
    Font for;
    Font if;
    Font do;
    Color byte;
    Color new;
    private r int;
    
    public b9(final Frame frame, final String s, final r int1, final String s2) {
        super(frame, false);
        this.for = new Font("Arial", 1, 12);
        this.if = new Font("Arial", 2, 10);
        this.do = new Font("Arial", 1, 11);
        this.byte = new Color(0, 0, 99);
        this.new = new Color(255, 255, 255);
        this.int = null;
        this.int = int1;
        this.setTitle("Help");
        this.setSize(250, 370);
        this.setBackground(this.new);
        this.setResizable(false);
        final Panel panel = new Panel(new BorderLayout());
        final Label label = new Label("PURE Player PRO for Java " + s, 1);
        label.setFont(this.for);
        label.setBackground(this.byte);
        label.setForeground(this.new);
        panel.setBackground(this.byte);
        panel.add(label, "North");
        String s3;
        if (s2 != null && s2.length() != 0) {
            s3 = s2;
        }
        else {
            s3 = "\nMove :\n  - left mouse button drag\n\nZoom in :\n  - right mouse button drag up\n  - shift key\n  - Mouse wheel up\n\nZoom out :\n  - right mouse button drag down\n  - ctrl key with mouse drag down\n  - Mouse wheel down\n\nExit fullscreen:  press escape key\n";
        }
        final TextArea textArea = new TextArea(s3, 16, 30, 1);
        textArea.setEditable(false);
        textArea.setBackground(this.new);
        final Label label2 = new Label("http://www.immervision.com/multimedia", 1);
        label2.setFont(new Font("Arial", 1, 10));
        label2.setBackground(this.byte);
        label2.setForeground(this.new);
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add(label2, "North");
        this.setLayout(new BorderLayout());
        this.add(panel, "North");
        this.add(textArea, "Center");
        this.add(panel2, "South");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                b9.this.int.s = null;
                b9.this.dispose();
            }
        });
        final int n = 350;
        final int n2 = 275;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.show();
        this.setSize(n2, n);
        this.setLocation((screenSize.width - n2) / 2, (screenSize.height - n) / 2);
        this.hide();
        this.show();
    }
}
