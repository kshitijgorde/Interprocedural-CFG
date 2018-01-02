// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.awt.Point;
import java.util.EventObject;

public class oj extends EventObject
{
    private String a;
    private int b;
    private Point c;
    private boolean d;
    
    public boolean a() {
        return this.d;
    }
    
    public oj(final Object o, final int b, final boolean d) {
        super(o);
        this.b = b;
        this.d = d;
    }
    
    public oj(final Object o, final int n) {
        this(o, n, false);
    }
    
    public oj(final Object o, final String a) {
        super(o);
        this.d = false;
        this.b = 0;
        this.a = a;
    }
    
    public oj(final Object o, final int n, final Point c) {
        this(o, n);
        this.c = c;
        this.d = false;
    }
    
    public int b() {
        return this.b;
    }
    
    public String c() {
        return this.a;
    }
    
    public Point d() {
        return this.c;
    }
}
