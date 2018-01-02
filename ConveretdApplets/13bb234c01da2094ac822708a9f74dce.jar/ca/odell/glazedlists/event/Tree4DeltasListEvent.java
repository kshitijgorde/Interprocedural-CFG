// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.impl.event.BlockSequence$Iterator;
import ca.odell.glazedlists.impl.event.Tree4Deltas$Iterator;

class Tree4DeltasListEvent extends ListEvent
{
    private Tree4Deltas$Iterator c;
    private BlockSequence$Iterator d;
    private ListEventAssembler e;
    
    public Tree4DeltasListEvent(final ListEventAssembler e, final EventList list) {
        super(list);
        this.e = e;
    }
    
    public void a() {
        if (this.e.e()) {
            this.d = this.e.g().c();
            this.c = null;
        }
        else {
            this.c = this.e.f().b();
            this.d = null;
        }
    }
    
    public boolean b() {
        if (this.d != null) {
            return this.d.g();
        }
        return this.c.d();
    }
    
    public boolean c() {
        if (this.d != null) {
            return this.d.h();
        }
        return this.c.e();
    }
    
    public boolean d() {
        return this.e.h() != null;
    }
    
    public int[] e() {
        final int[] h = this.e.h();
        if (h == null) {
            throw new IllegalStateException("Cannot get reorder map for a non-reordering change");
        }
        return h;
    }
    
    public int f() {
        if (this.d != null) {
            return this.d.a();
        }
        return this.c.a();
    }
    
    public int g() {
        if (this.d != null) {
            return this.d.b();
        }
        return this.c.a();
    }
    
    public int h() {
        if (this.d != null) {
            return this.d.c() - 1;
        }
        return this.c.b() - 1;
    }
    
    public int i() {
        if (this.d != null) {
            return this.d.d();
        }
        return this.c.c();
    }
    
    public Object j() {
        if (this.d != null) {
            return this.d.e();
        }
        return this.c.h();
    }
    
    public Object k() {
        return ListEvent.a;
    }
    
    public String toString() {
        if (this.d != null) {
            return "ListEvent: " + this.e.g().toString();
        }
        return "ListEvent: " + this.e.f().toString();
    }
}
