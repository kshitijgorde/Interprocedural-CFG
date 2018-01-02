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

class p extends Frame implements WindowListener
{
    private FnChartsApplet Nsa;
    private n Osa;
    private Panel Psa;
    private Dimension Qsa;
    
    p(final String s, final FnChartsApplet nsa, final n osa, final Panel psa) {
        super(s);
        this.Qsa = Toolkit.getDefaultToolkit().getScreenSize();
        this.Nsa = nsa;
        this.Osa = osa;
        this.Psa = psa;
        this.addWindowListener(this);
        this.setBounds(0, 0, this.Qsa.width, this.Qsa.height - 28);
        this.add(osa, "Center");
        osa.setVisible(true);
        this.setVisible(true);
        this.invalidate();
        this.validate();
        osa.Kb();
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
            this.Nsa.l();
        }
    }
}
