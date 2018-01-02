// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

import ca.odell.glazedlists.EventList;
import java.util.EventObject;

public abstract class ListEvent extends EventObject
{
    public static final Object a;
    protected EventList b;
    
    ListEvent(final EventList b) {
        super(b);
        this.b = b;
    }
    
    public abstract void a();
    
    public abstract boolean b();
    
    public abstract boolean c();
    
    public abstract boolean d();
    
    public abstract int[] e();
    
    public abstract int f();
    
    public abstract int g();
    
    public abstract int h();
    
    public abstract int i();
    
    public abstract Object j();
    
    public abstract Object k();
    
    public abstract String toString();
    
    static {
        a = new String("UNKNOWN VALUE");
    }
}
