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

public class default extends Dialog implements WindowListener, ActionListener
{
    private Button pa;
    private boolean wa;
    
    public default(final Frame frame, final String s, final String s2, String s3, final String s4) {
        super(frame, s, true);
        this.wa = false;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final try try1 = new try("\n");
        try1.l(s2);
        final continue continue1 = new continue(new GridLayout(try1.g(), 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        for (int i = 0; i < try1.g(); ++i) {
            continue1.add(new Label(try1.a(i)));
        }
        this.add(continue1, "Center");
        final continue continue2 = new continue(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        if (s3 == null || s3.length() == 0) {
            s3 = "OK";
        }
        this.pa = new Button(s3);
        Button button = null;
        if (s4 != null && s4.length() > 0) {
            button = new Button(s4);
        }
        this.pa.addActionListener(this);
        continue2.add(this.pa);
        if (button != null) {
            button.addActionListener(this);
            continue2.add(button);
        }
        this.add(continue2, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pa) {
            this.wa = true;
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
    
    public boolean _() {
        return this.wa;
    }
}
