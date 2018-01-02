// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Frame;

public class an extends Frame
{
    private Component if;
    private Dimension a;
    
    public an(final Component if1, final String s) {
        super(s);
        this.if = if1;
        this.a = null;
    }
    
    public void validate() {
        final Dimension minimumSize = this.getMinimumSize();
        if (minimumSize != null) {
            final Dimension size = this.getSize();
            if (size.width >= minimumSize.width && size.height >= minimumSize.height) {
                super.validate();
            }
        }
        else {
            super.validate();
        }
    }
    
    public void setMinimumSize(final Dimension a) {
        this.a = a;
    }
    
    public Dimension getMinimumSize() {
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        if (this.if != null) {
            this.if.handleEvent(event);
        }
        return super.handleEvent(event);
    }
}
