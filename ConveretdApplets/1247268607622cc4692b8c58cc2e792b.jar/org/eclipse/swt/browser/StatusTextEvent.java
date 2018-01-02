// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.TypedEvent;

public class StatusTextEvent extends TypedEvent
{
    public String text;
    static final long serialVersionUID = 3258407348371600439L;
    
    public StatusTextEvent(final Widget widget) {
        super(widget);
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " text=" + this.text + "}";
    }
}
