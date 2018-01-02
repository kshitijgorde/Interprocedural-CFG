import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Event
{
    protected boolean a;
    
    a(final Event event) {
        super(event.target, event.when, event.id, event.x, event.y, event.key, event.modifiers, event.arg);
        this.a = false;
        this.clickCount = event.clickCount;
        this.evt = event.evt;
    }
    
    void consume() {
        this.a = true;
    }
    
    boolean isConsumed() {
        return this.a;
    }
}
