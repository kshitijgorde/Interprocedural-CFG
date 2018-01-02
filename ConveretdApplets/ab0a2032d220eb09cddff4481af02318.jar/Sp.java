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
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sp extends Dialog implements WindowListener, ActionListener
{
    private Frame tDb;
    private Button uDb;
    private boolean vDb;
    
    public Sp(final Frame frame, final String s, final String s2, String s3, final String s4) {
        super(frame, s, true);
        this.vDb = false;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final d d = new d("\n");
        d.e(s2);
        final Vo vo = new Vo(new GridLayout(d.z(), 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        for (int i = 0; i < d.z(); ++i) {
            vo.add(new Label(d._(i)));
        }
        this.add(vo, "Center");
        final Vo vo2 = new Vo(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        if (s3 == null || s3.length() == 0) {
            s3 = "OK";
        }
        this.uDb = new Button(s3);
        Button button = null;
        if (s4 != null && s4.length() > 0) {
            button = new Button(s4);
        }
        this.uDb.addActionListener(this);
        vo2.add(this.uDb);
        if (button != null) {
            button.addActionListener(this);
            vo2.add(button);
        }
        this.add(vo2, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.uDb) {
            this.vDb = true;
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
    
    public boolean l() {
        return this.vDb;
    }
}
