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

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends Dialog
{
    private final String case = "Information";
    private ak for;
    private final String a = "ImmerVision PURE Player For Java";
    private final String new = "http://www.immervision.com";
    Font do;
    Font if;
    Font byte;
    Color try;
    Color int;
    
    public s(final ak for1, final Frame frame, final String s, final String s2, final String s3, final String s4) {
        super(frame, false);
        this.do = new Font("Arial", 1, 12);
        this.if = new Font("Arial", 2, 10);
        this.byte = new Font("Arial", 1, 11);
        this.try = new Color(0, 0, 99);
        this.int = new Color(255, 255, 255);
        this.for = for1;
        this.setTitle("Information");
        this.setBackground(this.int);
        this.setResizable(false);
        final Panel panel = new Panel(new BorderLayout());
        final Label label = new Label("ImmerVision PURE Player For Java " + s, 1);
        label.setFont(this.do);
        label.setBackground(this.try);
        label.setForeground(this.int);
        panel.setBackground(this.try);
        panel.add(label, "North");
        String s5 = "";
        if (s2 != null && s2.length() > 0) {
            s5 = String.valueOf(s2) + "\n\n";
        }
        if (s3 != null && s3.length() > 0) {
            s5 = String.valueOf(s5) + "Author(s) :\n" + s3 + "\n\n";
        }
        if (s4 != null && s4.length() > 0) {
            s5 = String.valueOf(s5) + "License :\n" + s4 + "\n";
        }
        final TextArea textArea = new TextArea(s5, 16, 30, 1);
        textArea.setEditable(false);
        textArea.setBackground(this.int);
        final Label label2 = new Label("http://www.immervision.com", 1);
        label2.setFont(new Font("Arial", 1, 10));
        label2.setBackground(this.try);
        label2.setForeground(this.int);
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add(label2, "North");
        this.setLayout(new BorderLayout());
        this.add(panel, "North");
        this.add(textArea, "Center");
        this.add(panel2, "South");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                s.this.for.d = null;
                s.this.dispose();
            }
        });
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.show();
        final int n = 300;
        final int n2 = 250;
        this.setSize(n2, n);
        this.setLocation((screenSize.width - n2) / 2, (screenSize.height - n) / 2);
        this.hide();
        this.show();
    }
}
