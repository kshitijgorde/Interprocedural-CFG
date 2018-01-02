// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.gui;

import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import java.util.Collection;
import java.util.ArrayList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEventAssembler;
import java.util.List;
import java.util.RandomAccess;
import ca.odell.glazedlists.TransformedList;

public abstract class ThreadProxyEventList extends TransformedList implements RandomAccess
{
    private List b;
    private ThreadProxyEventList$UpdateRunner f;
    private final ListEventAssembler g;
    private boolean h;
    
    public ThreadProxyEventList(final EventList list) {
        super(list);
        this.b = new ArrayList();
        this.f = new ThreadProxyEventList$UpdateRunner(this, null);
        this.g = new ListEventAssembler(this, ListEventAssembler.a());
        this.h = false;
        this.b.addAll(list);
        this.g.a(this.f);
        list.a(this);
    }
    
    public final void a(final ListEvent listEvent) {
        if (!this.h) {
            this.c.a(true);
            this.g.a(true);
        }
        this.c.a(listEvent);
        this.g.a(listEvent);
        if (!this.h) {
            this.h = true;
            this.a((Runnable)this.f);
        }
    }
    
    protected abstract void a(final Runnable p0);
    
    public final int size() {
        return this.b.size();
    }
    
    public final Object get(final int n) {
        return this.b.get(n);
    }
    
    protected final boolean a() {
        return true;
    }
    
    private List a(final EventList list, final ListEvent listEvent, final List list2) {
        final ArrayList<Object> list3 = new ArrayList<Object>(list.size());
        int i = 0;
        int n = 0;
        while (true) {
            int n2;
            int j;
            if (listEvent.b()) {
                n2 = listEvent.f();
                j = listEvent.i();
            }
            else {
                n2 = list.size();
                j = -1;
            }
            while (i < n2) {
                list3.add(i, list2.get(i + n));
                ++i;
            }
            if (j == 0) {
                ++n;
            }
            else if (j == 1) {
                list3.add(i, list.get(n2));
                ++i;
            }
            else if (j == 2) {
                list3.add(i, list.get(n2));
                ++i;
                --n;
            }
            else {
                if (j == -1) {
                    break;
                }
                continue;
            }
        }
        return list3;
    }
}
