// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Vector;

public final class cm
{
    private int a;
    private Vector b;
    
    public cm(final Object o) {
        this.b = (Vector)o;
        this.a = this.b.elementAt(0);
    }
    
    public cm(final int a) {
        this.a = a;
        (this.b = new Vector()).addElement(new Integer(a));
    }
    
    public cm(final int n, final Object o) {
        this(n);
        this.b.addElement(o);
    }
    
    public cm(final int n, final Object o, final Object o2) {
        this(n, o);
        this.b.addElement(o2);
    }
    
    public cm(final int n, final Object o, final Object o2, final Object o3) {
        this(n, o, o2);
        this.b.addElement(o3);
    }
    
    public cm(final int n, final Object o, final Object o2, final Object o3, final Object o4) {
        this(n, o, o2, o3);
        this.b.addElement(o4);
    }
    
    public cm(final int n, final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        this(n, o, o2, o3, o4);
        this.b.addElement(o5);
    }
    
    public cm(final int n, final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        this(n, o, o2, o3, o4, o5);
        this.b.addElement(o6);
    }
    
    public final Object a() {
        return this.b;
    }
    
    public final int b() {
        return this.a;
    }
    
    public final Object a(final int n) {
        if (n < this.b.size()) {
            return this.b.elementAt(n);
        }
        return null;
    }
    
    public final int b(final int n) {
        return (int)this.a(1);
    }
    
    public final String c(final int n) {
        return (String)this.a(n);
    }
    
    public final Vector d(final int n) {
        return (Vector)this.a(n);
    }
}
