import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Qh extends Dialog implements WindowListener, ActionListener
{
    private Color Qja;
    Button za;
    Rh Rja;
    Panel Aa;
    
    public Qh(final Frame frame, final String s, final o o) {
        super(frame, s, true);
        this.Qja = null;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.Rja = new Rh(), "Center");
        this.Aa = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.za = new Button(o.b("btnOK"));
        final Button button = new Button(o.b("btnCancel"));
        this.za.addActionListener(this);
        button.addActionListener(this);
        this.Aa.add(this.za);
        this.Aa.add(button);
        this.add(this.Aa, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public Color getColor() {
        return this.Qja;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            this.Qja = this.Rja._();
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
}
