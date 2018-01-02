import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Dialog implements WindowListener, ActionListener
{
    private Button za;
    private boolean Ba;
    
    public b(final Frame frame, final String s, final String s2, String s3, final String s4) {
        super(frame, s, true);
        this.Ba = false;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final u u = new u("\n");
        u.m(s2);
        final a a = new a(new GridLayout(u.a(), 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        for (int i = 0; i < u.a(); ++i) {
            a.add(new Label(u.b(i)));
        }
        this.add(a, "Center");
        final a a2 = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        if (s3 == null || s3.length() == 0) {
            s3 = "OK";
        }
        this.za = new Button(s3);
        Button button = null;
        if (s4 != null && s4.length() > 0) {
            button = new Button(s4);
        }
        this.za.addActionListener(this);
        a2.add(this.za);
        if (button != null) {
            button.addActionListener(this);
            a2.add(button);
        }
        this.add(a2, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            this.Ba = true;
        }
        this.dispose();
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public boolean a() {
        return this.Ba;
    }
}
