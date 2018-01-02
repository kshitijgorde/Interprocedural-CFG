// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import java.awt.Event;

final class ChangeValueEvent extends Event
{
    public static final int NEWVALUE = 1;
    private int eventType;
    
    public ChangeValueEvent(final Object o, final Event event, final int eventType) {
        super(o, event.when, event.id, event.x, event.y, event.key, event.modifiers, event.arg);
        this.eventType = eventType;
        super.id = -1;
    }
    
    protected String paramString() {
        final String s = new String();
        return String.valueOf(super.paramString()).concat(String.valueOf("NEWVALUE"));
    }
}
