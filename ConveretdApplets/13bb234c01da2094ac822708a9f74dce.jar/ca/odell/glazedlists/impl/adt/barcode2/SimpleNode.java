// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import java.util.List;
import java.util.Arrays;

class SimpleNode implements Element
{
    int a;
    Object b;
    byte c;
    SimpleNode d;
    SimpleNode e;
    SimpleNode f;
    int g;
    static final /* synthetic */ boolean h;
    
    public SimpleNode(final int n, final Object b, final SimpleNode f) {
        this.g = 0;
        if (!SimpleNode.h && n != 1) {
            throw new AssertionError();
        }
        this.b = b;
        this.c = 1;
        this.f = f;
        this.a += n;
    }
    
    public Object a() {
        return this.b;
    }
    
    public void a(final Object b) {
        this.b = b;
    }
    
    public byte b() {
        return 1;
    }
    
    final void a(final boolean b) {
        this.a = 0;
        if (this.d != null) {
            this.a += this.d.a;
        }
        if (this.e != null) {
            this.a += this.e.a;
        }
        this.a += (b ? 1 : 0);
    }
    
    public String toString() {
        return this.a(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
    }
    
    String a(final List list) {
        final StringBuffer sb = new StringBuffer();
        this.a(0, sb, list);
        return sb.toString();
    }
    
    void a(final int n, final StringBuffer sb, final List list) {
        if (this.d != null) {
            this.d.a(n + 1, sb, list);
        }
        for (int i = 0; i < n; ++i) {
            sb.append("   ");
        }
        if (this.b != null) {
            sb.append(": ");
            if (this.b instanceof SimpleNode) {
                sb.append("<Node>");
            }
            else {
                sb.append(this.b);
            }
        }
        sb.append("\n");
        if (this.e != null) {
            this.e.a(n + 1, sb, list);
        }
    }
    
    public void a(final int g) {
        this.g = g;
    }
    
    public int d() {
        return this.g;
    }
    
    public Element e() {
        return SimpleTree.a(this);
    }
    
    public Element f() {
        return SimpleTree.b(this);
    }
    
    static {
        h = !SimpleNode.class.desiredAssertionStatus();
    }
}
