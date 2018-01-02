// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Dialog;

public class F extends Dialog
{
    public Component q;
    protected Frame q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public final void w() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.getSize();
        int n;
        if ((n = (screenSize.height - size.height) / 2) > 100) {
            n = 100;
        }
        this.setLocation((screenSize.width - size.width) / 2, n);
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
    
    public void dispose() {
        try {
            super.dispose();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            super.hide();
        }
        if (this.q != null && this.q != this.getParent()) {
            this.q.dispose();
        }
    }
    
    public F(final Frame frame, final String title, final boolean b) {
        this(frame, b);
        this.setTitle(title);
    }
    
    public F(final Frame frame, final boolean b) {
        super((frame != null) ? frame : new Frame(), b);
        this.q = null;
        this.q = null;
        if (frame == null) {
            this.q = (Frame)this.getParent();
        }
        this.setFont(cb.r);
        this.setBackground(aB.w);
    }
}
