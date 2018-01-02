// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;

public final class ShellEvent extends TypedEvent
{
    public boolean doit;
    static final long serialVersionUID = 3257569490479888441L;
    
    public ShellEvent(final Event event) {
        super(event);
        this.doit = event.doit;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " doit=" + this.doit + "}";
    }
}
