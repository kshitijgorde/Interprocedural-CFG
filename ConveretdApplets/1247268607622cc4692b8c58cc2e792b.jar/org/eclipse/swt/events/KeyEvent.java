// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;

public class KeyEvent extends TypedEvent
{
    public char character;
    public int keyCode;
    public int keyLocation;
    public int stateMask;
    public boolean doit;
    static final long serialVersionUID = 3256442491011412789L;
    
    public KeyEvent(final Event event) {
        super(event);
        this.character = event.character;
        this.keyCode = event.keyCode;
        this.keyLocation = event.keyLocation;
        this.stateMask = event.stateMask;
        this.doit = event.doit;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " character='" + ((this.character == '\0') ? "\\0" : new StringBuffer().append(this.character).toString()) + "'" + " keyCode=" + this.keyCode + " keyLocation=" + this.keyLocation + " stateMask=" + this.stateMask + " doit=" + this.doit + "}";
    }
}
