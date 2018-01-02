// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Frame;

public class aO extends Frame
{
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top + 3, insets.left + 3, insets.bottom + 3, insets.right + 3);
    }
    
    public void c() {
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
    
    public aO(final String title) {
        this();
        this.setTitle(title);
    }
    
    public aO() {
        this.setFont(bL.f);
        this.setBackground(bR.h);
    }
}
