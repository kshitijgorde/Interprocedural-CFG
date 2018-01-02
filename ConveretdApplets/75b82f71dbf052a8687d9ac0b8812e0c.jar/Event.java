// 
// Decompiled by Procyon v0.5.30
// 

class Event extends java.awt.Event
{
    protected boolean consumed;
    
    Event(final java.awt.Event e) {
        super(e.target, e.when, e.id, e.x, e.y, e.key, e.modifiers, e.arg);
        this.consumed = false;
        this.clickCount = e.clickCount;
        this.evt = e.evt;
    }
    
    void consume() {
        this.consumed = true;
    }
    
    boolean isConsumed() {
        return this.consumed;
    }
}
