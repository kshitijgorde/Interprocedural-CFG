import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class while extends Frame implements WindowListener
{
    private FnChartsApplet SKb;
    private var TKb;
    private Panel UKb;
    private Dimension VKb;
    
    while(final String s, final FnChartsApplet sKb, final var tKb, final Panel uKb) {
        super(s);
        this.VKb = Toolkit.getDefaultToolkit().getScreenSize();
        this.SKb = sKb;
        this.TKb = tKb;
        this.UKb = uKb;
        this.addWindowListener(this);
        this.setBounds(0, 0, this.VKb.width, this.VKb.height - 28);
        this.add(tKb, "Center");
        tKb.setVisible(true);
        this.setVisible(true);
        this.invalidate();
        this.validate();
        tKb.tc();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.getCursor().equals(Cursor.getPredefinedCursor(3))) {
            Toolkit.getDefaultToolkit().beep();
        }
        else {
            this.SKb.Z();
        }
    }
}
