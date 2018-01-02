// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Event;

public class JCButtonEvent extends JCAWTEvent
{
    Event event;
    
    public Event getSourceEvent() {
        return this.event;
    }
    
    public JCButtonEvent(final Event event) {
        super(event.target, event.id);
        this.event = event;
    }
}
