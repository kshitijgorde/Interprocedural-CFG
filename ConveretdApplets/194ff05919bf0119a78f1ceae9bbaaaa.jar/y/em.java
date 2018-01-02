// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;

public class em extends Frame
{
    public ac a;
    public u a;
    private boolean a;
    
    public em(final dh dh) {
        this("", dh);
    }
    
    public em(final String s, final dh dh) {
        this(s, false, dh);
    }
    
    public em() {
        this.a = new cb(this, (byte)0).a;
    }
    
    public em(final String s, final boolean a, final dh dh) {
        super(s);
        this.a = new ac(this, dh);
        this.a = a;
    }
    
    public void a() {
        this.hide();
        if (this.a) {
            this.dispose();
        }
    }
    
    public void dispose() {
        this.a.a();
        this.a.a.a.a(this);
    }
    
    final void b() {
        super.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        if ((event.id == 201 && event.target == this) || (event.id == 1001 && event.target == this.a)) {
            this.a();
            return true;
        }
        return super.handleEvent(event);
    }
}
