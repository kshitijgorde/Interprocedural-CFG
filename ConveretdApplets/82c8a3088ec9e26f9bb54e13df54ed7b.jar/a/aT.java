// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.WindowEvent;
import java.awt.event.ComponentEvent;
import java.awt.Image;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.WindowListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

public class aT extends Frame implements ComponentListener, WindowListener
{
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public aT(final boolean b) {
        this.setFont(bd.r);
        this.setBackground(ah.w);
        final Image q;
        if ((q = cs.q) != null) {
            this.setIconImage(q);
        }
        if (b) {
            this.addComponentListener(this);
            this.addWindowListener(this);
        }
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
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
