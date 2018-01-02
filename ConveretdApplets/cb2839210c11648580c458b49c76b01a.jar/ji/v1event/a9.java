// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class a9 extends EventObject
{
    Object a;
    boolean b;
    boolean c;
    boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private Throwable h;
    
    public a9(final Object o, final Object a, final boolean b) {
        super(o);
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = null;
        this.h = null;
        this.a = a;
        this.b = b;
    }
    
    public final void a(final boolean d) {
        this.d = d;
    }
    
    public final void a() {
        this.c = true;
    }
    
    public final boolean b() {
        return this.c;
    }
    
    public final boolean c() {
        return this.b;
    }
    
    public final String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiDispatchEvent(").append(this.a).append(")")));
    }
    
    public final Object d() {
        return this.a;
    }
    
    public final boolean e() {
        return this.e;
    }
    
    public void f() {
        this.f = true;
    }
    
    public void a(final Throwable h) {
        this.h = h;
        this.f = true;
    }
}
