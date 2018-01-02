// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import java.util.NoSuchElementException;

public class SimpleTreeIterator
{
    int a;
    private SimpleTree b;
    private SimpleNode c;
    private int d;
    
    public SimpleTreeIterator(final SimpleTree simpleTree) {
        this(simpleTree, 0, (byte)0);
    }
    
    public SimpleTreeIterator(final SimpleTree b, final int n, final byte b2) {
        this.b = b;
        if (n != 0) {
            final int a = n - 1;
            this.c = (SimpleNode)b.a(a);
            this.a = a;
            this.d = this.a - b.a((Element)this.c, (byte)1);
        }
        else {
            this.c = null;
            this.d = 0;
        }
    }
    
    public boolean a() {
        if (this.c == null) {
            return this.b.b() > 0;
        }
        return this.c() < this.b.b() - 1;
    }
    
    public void b() {
        if (!this.a()) {
            throw new NoSuchElementException();
        }
        if (this.c == null) {
            this.c = this.b.c();
            this.d = 0;
            return;
        }
        if (this.d < 0) {
            ++this.a;
            ++this.d;
            return;
        }
        this.a += 1 - this.d;
        this.c = SimpleTree.a(this.c);
        this.d = 0;
    }
    
    public int c() {
        if (this.c == null) {
            throw new NoSuchElementException();
        }
        return 0 + this.a;
    }
    
    public Object d() {
        if (this.c == null) {
            throw new IllegalStateException();
        }
        return this.c.a();
    }
    
    public Element e() {
        if (this.c == null) {
            throw new IllegalStateException();
        }
        return this.c;
    }
}
