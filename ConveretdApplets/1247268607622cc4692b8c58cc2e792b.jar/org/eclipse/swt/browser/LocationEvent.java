// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.TypedEvent;

public class LocationEvent extends TypedEvent
{
    public String location;
    public boolean top;
    public boolean doit;
    static final long serialVersionUID = 3906644198244299574L;
    
    public LocationEvent(final Widget widget) {
        super(widget);
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " location=" + this.location + " top=" + this.top + " doit=" + this.doit + "}";
    }
}
