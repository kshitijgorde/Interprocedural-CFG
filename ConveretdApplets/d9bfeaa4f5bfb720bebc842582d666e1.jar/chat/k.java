// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.Insets;
import java.awt.Frame;

public class k extends Frame
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
    
    public k() {
        this.setFont(ay.c);
        this.setBackground(j.b);
        if (bh.h != null) {
            this.setIconImage(bh.h);
        }
    }
}
