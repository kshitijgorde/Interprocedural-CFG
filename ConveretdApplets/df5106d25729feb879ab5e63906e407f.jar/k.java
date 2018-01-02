import java.awt.Event;
import java.awt.Component;
import java.awt.MenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

class k extends MenuItem
{
    private Component a;
    
    public boolean postEvent(final Event event) {
        return this.a.isValid() && this.a.postEvent(event);
    }
    
    public k(final Component a, final String s) {
        super(s);
        this.a = a;
    }
}
