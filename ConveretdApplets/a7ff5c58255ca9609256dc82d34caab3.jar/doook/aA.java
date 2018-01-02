// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Dialog;

public class aA extends Dialog
{
    protected Component c;
    protected Frame d;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public void f() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        int n = (screenSize.height - size.height) / 2;
        if (n > 100) {
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
    
    public void dispose() {
        try {
            super.dispose();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            super.hide();
        }
        if (this.d != null && this.d != this.getParent()) {
            this.d.dispose();
        }
    }
    
    public aA(final Frame frame, final String title, final boolean b) {
        this(frame, b);
        this.setTitle(title);
    }
    
    public aA(final Frame frame, final boolean b) {
        super((frame == null) ? new Frame() : frame, b);
        this.c = null;
        this.d = null;
        if (frame == null) {
            this.d = (Frame)this.getParent();
        }
        this.setFont(ay.e);
        this.setBackground(ah.i);
    }
}
