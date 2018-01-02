// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Event;

public class ImageButtonEvent extends Event
{
    public static final int ARM = 1;
    public static final int DISARM = 2;
    public static final int ACTIVATE = 3;
    private int eventType;
    
    public ImageButtonEvent(final ImageButton button, final Event event, final int type) {
        super(button, event.when, event.id, event.x, event.y, event.key, event.modifiers, event.arg);
        Assert.notFalse(type == 1 || type == 2 || type == 3);
        this.eventType = type;
        super.id = -1;
    }
    
    public boolean isArmed() {
        return this.eventType == 1;
    }
    
    public boolean isDisarmed() {
        return this.eventType == 2;
    }
    
    public boolean isActivated() {
        return this.eventType == 3;
    }
    
    protected String paramString() {
        String str = new String();
        if (this.eventType == 1) {
            str = "ARM";
        }
        else if (this.eventType == 2) {
            str = "DISARM";
        }
        else if (this.eventType == 3) {
            str = "ACTIVATE";
        }
        return super.paramString() + str;
    }
}
