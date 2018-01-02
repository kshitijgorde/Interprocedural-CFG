// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;

public final class TraverseEvent extends KeyEvent
{
    public int detail;
    static final long serialVersionUID = 3257565105301239349L;
    
    public TraverseEvent(final Event event) {
        super(event);
        this.detail = event.detail;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " detail=" + this.detail + "}";
    }
}
