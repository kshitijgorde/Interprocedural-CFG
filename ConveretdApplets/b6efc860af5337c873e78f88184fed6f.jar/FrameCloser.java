import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class FrameCloser extends Frame implements WindowListener
{
    public FrameCloser(final String name) {
        super(name);
    }
    
    public void closeWindow() {
        this.hide();
    }
    
    public void windowActivated(final WindowEvent event) {
    }
    
    public void windowClosed(final WindowEvent event) {
    }
    
    public void windowClosing(final WindowEvent event) {
        this.closeWindow();
    }
    
    public void windowDeactivated(final WindowEvent event) {
    }
    
    public void windowDeiconified(final WindowEvent event) {
    }
    
    public void windowIconified(final WindowEvent event) {
    }
    
    public void windowOpened(final WindowEvent event) {
    }
}
