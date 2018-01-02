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

public class bg extends Dialog
{
    private final String else = "Information";
    private l int;
    private final String a = "ImmerVision PURE Java Player";
    private final String goto = "copyright © ImmerVision 2002-2005";
    private final String byte = "http://www.immervision.com";
    private final String char = "Graphical User Interface Information";
    private final String for = "Panorama information";
    Font do;
    Font if;
    Font case;
    Color new;
    Color try;
    
    public bg(final l int1, final Frame frame, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        super(frame, false);
        this.do = new Font("Arial", 1, 12);
        this.if = new Font("Arial", 2, 10);
        this.case = new Font("Arial", 1, 11);
        this.new = new Color(164, 48, 53);
        this.try = new Color(255, 255, 255);
        this.int = int1;
        this.setTitle("Information");
        this.setSize(250, 400);
        this.setBackground(this.try);
        this.setResizable(false);
        final Panel panel = new Panel(new BorderLayout());
        final Label label = new Label("ImmerVision PURE Java Player " + s, 1);
        label.setFont(this.do);
        label.setBackground(this.new);
        label.setForeground(this.try);
        panel.add(label, "North");
        final Label label2 = new Label("copyright © ImmerVision 2002-2005", 1);
        label2.setFont(this.if);
        panel.add(label2, "Center");
        panel.add(new Label(" "), "South");
        final Panel panel2 = new Panel(new BorderLayout());
        final Label label3 = new Label("Graphical User Interface Information", 0);
        label3.setFont(this.case);
        panel2.add(label3, "North");
        final TextArea textArea = new TextArea(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(s2)).append("\n\n").toString())).append("Author(s) :\n").append(s3).toString()) + ".\n\nLicense :\n" + s4, 6, 30, 1);
        textArea.setEditable(false);
        panel2.add(textArea, "Center");
        panel2.add(new Label(" "), "South");
        final Panel panel3 = new Panel(new BorderLayout());
        final Label label4 = new Label("Panorama information", 0);
        label4.setFont(this.case);
        panel3.add(label4, "North");
        final TextArea textArea2 = new TextArea(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(s5)).append("\n\n").toString())).append("Author(s) :\n").append(s6).toString()) + ".\n\nLicense :\n" + s7, 6, 30, 1);
        textArea2.setEditable(false);
        panel3.add(textArea2, "Center");
        panel3.add(new Label(" "), "South");
        final Label label5 = new Label("http://www.immervision.com", 1);
        label5.setFont(new Font("Arial", 1, 10));
        label5.setBackground(this.new);
        label5.setForeground(this.try);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        this.add(label5);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                bg.this.int.c = null;
                bg.this.dispose();
            }
        });
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.show();
        final Dimension size = textArea.getSize();
        this.setSize(size.width + 20, 400);
        this.setLocation((screenSize.width - size.width + 20) / 2, (screenSize.height - 400) / 2);
        this.hide();
        this.show();
    }
}
