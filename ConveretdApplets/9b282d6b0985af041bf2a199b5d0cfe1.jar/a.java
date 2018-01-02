import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Event
{
    protected boolean a;
    
    boolean isConsumed() {
        return this.a;
    }
    
    a(final Event event) {
        super(event.target, event.when, event.id, event.x, event.y, event.key, event.modifiers, event.arg);
        this.a = false;
        super.clickCount = event.clickCount;
        super.evt = event.evt;
    }
    
    void consume() {
        this.a = true;
    }
}
