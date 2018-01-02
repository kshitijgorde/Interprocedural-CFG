// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class k extends Frame implements WindowListener
{
    private FlaxChat a;
    
    public k(final FlaxChat a) {
        this.a = a;
        this.addWindowListener(this);
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
        this.toFront();
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
        this.a.m();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
}
