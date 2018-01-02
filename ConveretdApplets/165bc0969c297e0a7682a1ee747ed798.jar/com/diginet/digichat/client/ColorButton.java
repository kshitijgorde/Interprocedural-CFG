// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Event;
import com.esial.util.c;
import java.awt.Color;
import com.diginet.digichat.awt.r;

public class ColorButton extends r
{
    private String strTitle;
    private Color clrTarget;
    private i iUser;
    
    public ColorButton(final i iUser, final String strTitle) {
        super(com.esial.util.c.a("Set Color"));
        this.iUser = iUser;
        this.strTitle = strTitle;
        this.clrTarget = null;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this) {
            Component parent = this;
            do {
                parent = parent.getParent();
            } while (!(parent instanceof Frame));
            final int result;
            if ((result = new ColorDialog((Frame)parent, this.iUser).getResult(this.strTitle, this.getForeground())) != 0) {
                this.setForeground(this.clrTarget = new Color(result));
                this.repaint();
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void setColor(final Color clrTarget, final Color color) {
        this.clrTarget = clrTarget;
        this.setForeground((clrTarget == null) ? color : clrTarget);
        this.repaint();
    }
    
    public Color getColor() {
        return this.clrTarget;
    }
}
