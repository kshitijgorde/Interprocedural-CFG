// 
// Decompiled by Procyon v0.5.30
// 

package a.b.b;

import java.util.EventObject;

public class c extends EventObject
{
    private Object a;
    private int b;
    private long c;
    private Object d;
    
    public c(final Object o, final int n, final Object o2) {
        this(o, n, System.currentTimeMillis(), o2);
    }
    
    public c(final Object a, final int b, final long c, final Object d) {
        super(a);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public Object getSource() {
        return this.a;
    }
    
    public int a() {
        return this.b;
    }
    
    public Object b() {
        return this.d;
    }
}
