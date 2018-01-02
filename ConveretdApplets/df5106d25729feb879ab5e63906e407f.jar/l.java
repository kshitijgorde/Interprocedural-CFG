import java.awt.Event;
import java.awt.Component;
import java.awt.CheckboxMenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

class l extends CheckboxMenuItem
{
    private Component a;
    
    public void setState(final boolean state) {
        super.setState(state);
    }
    
    public boolean getState() {
        return super.getState();
    }
    
    public boolean postEvent(final Event event) {
        return this.a.isValid() && this.a.postEvent(event);
    }
    
    public l(final Component a, final String s) {
        super(s);
        this.a = a;
    }
}
