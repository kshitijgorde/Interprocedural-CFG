// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.event;

import java.util.List;
import java.util.Arrays;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.impl.adt.barcode2.Element;
import ca.odell.glazedlists.impl.adt.barcode2.FourColorTree;
import ca.odell.glazedlists.impl.adt.barcode2.ListToByteCoder;

public class Tree4Deltas
{
    private static final ListToByteCoder f;
    public static final byte a;
    public static final byte b;
    public static final byte c;
    public static final byte d;
    private static final byte g;
    private static final byte h;
    private static final byte i;
    private static final byte j;
    private FourColorTree k;
    private boolean l;
    private boolean m;
    public boolean e;
    
    public Tree4Deltas() {
        this.k = new FourColorTree(Tree4Deltas.f);
        this.l = false;
        this.m = false;
        this.e = false;
    }
    
    public void a(final boolean l) {
        this.l = l;
    }
    
    public void a(final int n, final int n2, Object a, final Object o) {
        if (!this.m) {
            this.b(n2);
        }
        for (int i = n; i < n2; ++i) {
            final int a2 = this.k.a(i, Tree4Deltas.h, Tree4Deltas.i);
            final Element a3 = this.k.a(a2, Tree4Deltas.i);
            if (this.e) {
                this.k.b(a2, Tree4Deltas.i, (a3.b() == Tree4Deltas.a) ? Tree4Deltas.a : Tree4Deltas.b, a, 1);
            }
            else if (a3.b() != Tree4Deltas.a) {
                if (a3.b() == Tree4Deltas.b) {
                    a = a3.a();
                }
                this.k.b(a2, Tree4Deltas.i, Tree4Deltas.b, a, 1);
            }
        }
    }
    
    public void a(final int n, final int n2, final Object o) {
        if (!this.m) {
            this.b(n2);
        }
        this.k.a(n, Tree4Deltas.h, Tree4Deltas.a, o, n2 - n);
    }
    
    public void b(final int n, final int n2, Object a) {
        if (!this.m) {
            this.b(n2);
        }
        for (int i = n; i < n2; ++i) {
            if (n > 0 && n > this.k.a(Tree4Deltas.h)) {
                throw new IllegalArgumentException();
            }
            final int a2 = this.k.a(n, Tree4Deltas.h, Tree4Deltas.i);
            final Element a3 = this.k.a(a2, Tree4Deltas.i);
            if (a3.b() == Tree4Deltas.a) {
                if (!this.l) {
                    throw new IllegalStateException("Remove " + i + " undoes prior insert at the same index! Consider enabling contradicting events.");
                }
                this.k.a(a2, Tree4Deltas.i, 1);
            }
            else {
                if (a3.b() == Tree4Deltas.b) {
                    a = a3.a();
                }
                this.k.b(a2, Tree4Deltas.i, Tree4Deltas.c, a, 1);
            }
        }
    }
    
    public void a(final int n) {
        this.k.a();
        this.m = true;
        this.b(n);
    }
    
    private void b(final int n) {
        final int n2 = n - this.k.a(Tree4Deltas.h);
        if (n2 > 0) {
            this.k.a(this.k.a(Tree4Deltas.i), Tree4Deltas.i, Tree4Deltas.d, ListEvent.a, n2);
        }
    }
    
    public void a(final BlockSequence blockSequence) {
        final BlockSequence$Iterator c = blockSequence.c();
        while (c.h()) {
            final int b = c.b();
            final int c2 = c.c();
            final int d = c.d();
            final Object e = c.e();
            final Object f = c.f();
            if (d == 2) {
                this.a(b, c2, f);
            }
            else if (d == 1) {
                this.a(b, c2, e, f);
            }
            else {
                if (d != 0) {
                    throw new IllegalStateException();
                }
                this.b(b, c2, e);
            }
        }
    }
    
    public boolean a() {
        return this.k.a(Tree4Deltas.j) == 0;
    }
    
    public Tree4Deltas$Iterator b() {
        return new Tree4Deltas$Iterator(this.k, null);
    }
    
    public String toString() {
        return this.k.b();
    }
    
    static {
        f = new ListToByteCoder(Arrays.asList("+", "U", "X", "_"));
        a = Tree4Deltas.f.a("+");
        b = Tree4Deltas.f.a("U");
        c = Tree4Deltas.f.a("X");
        d = Tree4Deltas.f.a("_");
        g = Tree4Deltas.f.a(Arrays.asList("U", "X", "_"));
        h = Tree4Deltas.f.a(Arrays.asList("U", "+", "_"));
        i = Tree4Deltas.f.a(Arrays.asList("U", "X", "+", "_"));
        j = Tree4Deltas.f.a(Arrays.asList("U", "X", "+"));
    }
}
