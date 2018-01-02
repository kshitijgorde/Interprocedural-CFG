// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.WindowEvent;
import java.awt.event.ComponentEvent;
import java.awt.Image;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.event.WindowListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

public class bE extends Frame implements ComponentListener, WindowListener
{
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public final void e() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        int n;
        if ((n = (screenSize.height - size.height) / 2) > 100) {
            n = 100;
        }
        this.move((screenSize.width - size.width) / 2, n);
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
    
    public bE(final String title, final boolean b) {
        this(false);
        this.setTitle(title);
    }
    
    public bE(final boolean b) {
        this.setFont(cb.r);
        this.setBackground(aB.w);
        final Image q;
        if ((q = dN.q) != null) {
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
