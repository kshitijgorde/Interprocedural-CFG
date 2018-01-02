// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TransformedList;

public final class SubEventList extends TransformedList
{
    private int b;
    private int f;
    
    public SubEventList(final EventList list, final int b, final int f, final boolean b2) {
        super(list);
        if (b < 0 || f < b || f > list.size()) {
            throw new IllegalArgumentException("The range " + b + "-" + f + " is not valid over a list of size " + list.size());
        }
        this.b = b;
        this.f = f;
        if (b2) {
            list.a(new WeakReferenceProxy(list, this));
        }
        else {
            list.a(this);
        }
    }
    
    public int size() {
        return this.f - this.b;
    }
    
    protected int a(final int n) {
        return n + this.b;
    }
    
    protected boolean a() {
        return true;
    }
    
    public void a(final ListEvent listEvent) {
        this.c.b();
        if (listEvent.d() && this.size() == 1) {
            final int[] e = listEvent.e();
            for (int i = 0; i < e.length; ++i) {
                if (e[i] == this.b) {
                    this.b = i;
                    this.f = this.b + 1;
                    break;
                }
            }
        }
        else {
            while (listEvent.b()) {
                final int f = listEvent.f();
                final int j = listEvent.i();
                if (f < this.b || (j == 2 && f == this.b)) {
                    if (j == 2) {
                        ++this.b;
                        ++this.f;
                    }
                    else {
                        if (j != 0) {
                            continue;
                        }
                        --this.b;
                        --this.f;
                    }
                }
                else {
                    if (f >= this.f) {
                        continue;
                    }
                    if (j == 2) {
                        ++this.f;
                        this.c.a(f - this.b, listEvent.k());
                    }
                    else if (j == 1) {
                        this.c.a(f - this.b, listEvent.j(), listEvent.k());
                    }
                    else {
                        if (j != 0) {
                            continue;
                        }
                        --this.f;
                        this.c.b(f - this.b, listEvent.j());
                    }
                }
            }
        }
        if (this.b > this.f) {
            throw new IllegalStateException();
        }
        this.c.c();
    }
}
