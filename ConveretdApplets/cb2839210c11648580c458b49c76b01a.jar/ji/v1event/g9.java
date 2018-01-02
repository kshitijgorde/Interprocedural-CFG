// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class g9 extends EventObject
{
    private boolean a;
    int b;
    int c;
    int d;
    int e;
    int f;
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiMouseWheelEvent(").append(this.b).append(", ").append(this.c).append(", ").append(this.d).append(", ").append(this.e).append(", ").append(this.f).append(") - ").append(this.getSource())));
    }
    
    public g9(final Object o, final int b, final int c, final int d, final int e, final int f) {
        super(o);
        this.a = false;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public int a() {
        return this.e;
    }
    
    public int b() {
        return this.f;
    }
}
