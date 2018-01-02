// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.GridLayout;
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

public class bx extends Dialog
{
    private final String else = "Panorama and GUI Information";
    private r int;
    private final String a = "PURE Player PRO for Java";
    private final String byte = "http://www.immervision.com/multimedia";
    private final String char = "Graphical User Interface Information";
    private final String for = "Panorama information";
    Font do;
    Font if;
    Font case;
    Color try;
    Color new;
    
    public bx(final r int1, final Frame frame, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        super(frame, false);
        this.do = new Font("Arial", 1, 12);
        this.if = new Font("Arial", 2, 10);
        this.case = new Font("Arial", 1, 11);
        this.try = new Color(0, 0, 99);
        this.new = new Color(255, 255, 255);
        this.int = int1;
        this.setTitle("Panorama and GUI Information");
        this.setBackground(this.new);
        this.setResizable(false);
        final Panel panel = new Panel(new BorderLayout());
        final Label label = new Label("PURE Player PRO for Java " + s, 1);
        label.setFont(this.do);
        label.setBackground(this.try);
        label.setForeground(this.new);
        panel.setBackground(this.try);
        panel.add(label, "North");
        final Panel panel2 = new Panel(new BorderLayout());
        final Label label2 = new Label("Graphical User Interface Information", 0);
        label2.setFont(this.case);
        panel2.add(label2, "North");
        String s8 = "";
        if (s2 != null && s2.length() > 0) {
            s8 = String.valueOf(s2) + "\n\n";
        }
        if (s3 != null && s3.length() > 0) {
            s8 = String.valueOf(s8) + "Author(s) :\n" + s3 + "\n\n";
        }
        if (s4 != null && s4.length() > 0) {
            s8 = String.valueOf(s8) + "License :\n" + s4 + "\n";
        }
        final TextArea textArea = new TextArea(s8, 6, 30, 1);
        textArea.setEditable(false);
        textArea.setBackground(this.new);
        panel2.add(textArea, "Center");
        final Panel panel3 = new Panel(new BorderLayout());
        final Label label3 = new Label("Panorama information", 0);
        label3.setFont(this.case);
        panel3.add(label3, "North");
        String s9 = "";
        if (s5 != null && s5.length() > 0) {
            s9 = String.valueOf(s5) + "\n\n";
        }
        if (s6 != null && s6.length() > 0) {
            s9 = String.valueOf(s9) + "Author(s) :\n" + s6 + "\n\n";
        }
        if (s7 != null && s7.length() > 0) {
            s9 = String.valueOf(s9) + "License :\n" + s7 + "\n";
        }
        final TextArea textArea2 = new TextArea(s9, 6, 30, 1);
        textArea2.setEditable(false);
        textArea2.setBackground(this.new);
        panel3.add(textArea2, "Center");
        final Panel panel4 = new Panel(new GridLayout(2, 1, 5, 5));
        panel4.add(panel2);
        panel4.add(panel3);
        final Label label4 = new Label("http://www.immervision.com/multimedia", 1);
        label4.setFont(new Font("Arial", 1, 10));
        label4.setBackground(this.try);
        label4.setForeground(this.new);
        final Panel panel5 = new Panel(new BorderLayout());
        panel5.add(label4, "North");
        this.setLayout(new BorderLayout());
        this.add(panel, "North");
        this.add(panel4, "Center");
        this.add(panel5, "South");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                bx.this.int.i = null;
                bx.this.dispose();
            }
        });
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.show();
        final int n = 350;
        final int n2 = 275;
        this.setSize(n2, n);
        this.setLocation((screenSize.width - n2) / 2, (screenSize.height - n) / 2);
        this.hide();
        this.show();
    }
}
