// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.event;

import ca.odell.glazedlists.event.ListEvent;
import java.util.ArrayList;
import java.util.List;
import ca.odell.glazedlists.impl.adt.gnutrove.TIntArrayList;

public class BlockSequence$Iterator
{
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    final /* synthetic */ BlockSequence a;
    
    public BlockSequence$Iterator(final BlockSequence a) {
        this.a = a;
        this.b = -1;
        this.c = 0;
        this.d = -1;
        this.e = -1;
        this.f = -1;
    }
    
    public int a() {
        if (this.f == 2 || this.f == 1) {
            return this.d + this.c;
        }
        if (this.f == 0) {
            return this.d;
        }
        throw new IllegalStateException();
    }
    
    public int b() {
        if (this.d == -1) {
            throw new IllegalStateException("The ListEvent is not currently in a state to return a block start index");
        }
        return this.d;
    }
    
    public int c() {
        if (this.e == -1) {
            throw new IllegalStateException("The ListEvent is not currently in a state to return a block end index");
        }
        return this.e;
    }
    
    public int d() {
        if (this.f == -1) {
            throw new IllegalStateException("The ListEvent is not currently in a state to return a type");
        }
        return this.f;
    }
    
    public Object e() {
        return this.a.d.get(this.b);
    }
    
    public Object f() {
        return this.a.e.get(this.b);
    }
    
    public boolean g() {
        if (this.c + 1 < this.e - this.d) {
            ++this.c;
            return true;
        }
        if (this.b + 1 < this.a.c.a()) {
            ++this.b;
            this.c = 0;
            this.d = this.a.a.c(this.b);
            this.e = this.a.b.c(this.b);
            this.f = this.a.c.c(this.b);
            return true;
        }
        return false;
    }
    
    public boolean h() {
        if (this.b + 1 < this.a.c.a()) {
            ++this.b;
            this.c = 0;
            this.d = this.a.a.c(this.b);
            this.e = this.a.b.c(this.b);
            this.f = this.a.c.c(this.b);
            return true;
        }
        return false;
    }
}
