// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.event;

import java.util.List;
import java.util.Arrays;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.impl.adt.barcode2.Element;
import ca.odell.glazedlists.impl.adt.barcode2.ListToByteCoder;
import ca.odell.glazedlists.impl.adt.barcode2.FourColorTreeIterator;
import ca.odell.glazedlists.impl.adt.barcode2.FourColorTree;

public class Tree4Deltas$Iterator
{
    private final FourColorTree a;
    private final FourColorTreeIterator b;
    
    private Tree4Deltas$Iterator(final FourColorTree a) {
        this.a = a;
        this.b = new FourColorTreeIterator(a);
    }
    
    public int a() {
        return this.b.f(Tree4Deltas.h);
    }
    
    public int b() {
        return this.b.g(Tree4Deltas.h) + this.b.e(Tree4Deltas.i);
    }
    
    public int c() {
        final byte a = this.b.a();
        if (a == Tree4Deltas.a) {
            return 2;
        }
        if (a == Tree4Deltas.b) {
            return 1;
        }
        if (a == Tree4Deltas.c) {
            return 0;
        }
        throw new IllegalStateException();
    }
    
    public boolean d() {
        if (!this.f()) {
            return false;
        }
        this.b.c(Tree4Deltas.j);
        return true;
    }
    
    public boolean e() {
        if (!this.g()) {
            return false;
        }
        this.b.d(Tree4Deltas.j);
        return true;
    }
    
    public boolean f() {
        return this.b.a(Tree4Deltas.j);
    }
    
    public boolean g() {
        return this.b.b(Tree4Deltas.j);
    }
    
    public Object h() {
        return this.b.b().a();
    }
}
