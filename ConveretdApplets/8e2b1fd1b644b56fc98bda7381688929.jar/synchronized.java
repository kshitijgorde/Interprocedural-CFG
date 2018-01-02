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

class synchronized extends Frame implements WindowListener
{
    private FnChartsApplet Zwa;
    private super _xa;
    private Panel axa;
    private Dimension bxa;
    
    synchronized(final String s, final FnChartsApplet zwa, final super xa, final Panel axa) {
        super(s);
        this.bxa = Toolkit.getDefaultToolkit().getScreenSize();
        this.Zwa = zwa;
        this._xa = xa;
        this.axa = axa;
        this.addWindowListener(this);
        this.setBounds(0, 0, this.bxa.width, this.bxa.height - 28);
        this.add(xa, "Center");
        xa.setVisible(true);
        this.setVisible(true);
        this.invalidate();
        this.validate();
        xa.cb();
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
            this.Zwa.l();
        }
    }
}
