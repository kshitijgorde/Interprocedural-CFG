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

public class Kp extends Dialog implements WindowListener, ActionListener
{
    private Color WDb;
    Button uDb;
    Lp XDb;
    Panel wDb;
    
    public Kp(final Frame frame, final String s, final volatile volatile1) {
        super(frame, s, true);
        this.WDb = null;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.XDb = new Lp(this), "Center");
        this.wDb = new Vo(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.uDb = new Button(volatile1._("btnOK"));
        final Button button = new Button(volatile1._("btnCancel"));
        this.uDb.addActionListener(this);
        button.addActionListener(this);
        this.wDb.add(this.uDb);
        this.wDb.add(button);
        this.add(this.wDb, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public Color getColor() {
        return this.WDb;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.uDb) {
            this.WDb = this.XDb.b();
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
