// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

import java.util.ConcurrentModificationException;
import ca.odell.glazedlists.impl.event.Tree4Deltas;
import ca.odell.glazedlists.impl.event.BlockSequence;
import ca.odell.glazedlists.EventList;

public final class ListEventAssembler
{
    protected EventList a;
    private Thread e;
    protected int b;
    protected boolean c;
    protected int[] d;
    private BlockSequence f;
    private boolean g;
    private Tree4Deltas h;
    private final SequenceDependenciesEventPublisher i;
    private final ListEvent j;
    private final ListEventAssembler$ListEventFormat k;
    private boolean l;
    
    public static ListEventPublisher a() {
        return new SequenceDependenciesEventPublisher();
    }
    
    public ListEventAssembler(final EventList a, final ListEventPublisher listEventPublisher) {
        this.b = 0;
        this.c = true;
        this.d = null;
        this.f = new BlockSequence();
        this.g = false;
        this.h = new Tree4Deltas();
        this.k = new ListEventAssembler$ListEventFormat(this, null);
        this.l = false;
        this.a = a;
        this.i = (SequenceDependenciesEventPublisher)listEventPublisher;
        this.j = new Tree4DeltasListEvent(this, a);
    }
    
    public void b() {
        this.a(false);
    }
    
    public synchronized void a(final boolean c) {
        if (!this.c) {
            throw new ConcurrentModificationException("Cannot begin a new event while another event is in progress by thread, " + this.e.getName());
        }
        this.c = c;
        if (c || (this.b == 0 && this.e != null)) {
            this.h.a(true);
        }
        if (this.e == null) {
            this.e = Thread.currentThread();
            this.g = true;
        }
        ++this.b;
    }
    
    public void a(final int n, final Object o) {
        this.a(2, n, n, ListEvent.a, o);
    }
    
    public void a(final int n, final Object o, final Object o2) {
        this.a(1, n, n, o, o2);
    }
    
    public void b(final int n, final Object o) {
        this.a(0, n, n, o, ListEvent.a);
    }
    
    public void c(final int n, final Object o) {
        this.a(n, o, ListEvent.a);
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.a(n, n2, n3, ListEvent.a, ListEvent.a);
    }
    
    public void a(final int n, final int n2) {
        this.a(n, n2, n2);
    }
    
    public void a(final int n) {
        this.a(2, n);
    }
    
    private void a(final int n, final int n2, final int n3, final Object o, final Object o2) {
        if (this.g) {
            if (this.f.a(n, n2, n3 + 1, o, o2)) {
                return;
            }
            this.h.a(this.f);
            this.g = false;
        }
        switch (n) {
            case 2: {
                this.h.a(n2, n3 + 1, o2);
                break;
            }
            case 1: {
                this.h.a(n2, n3 + 1, o, o2);
                break;
            }
            case 0: {
                this.h.b(n2, n3 + 1, o);
                break;
            }
        }
    }
    
    public void a(final int[] d) {
        if (!this.d()) {
            throw new IllegalStateException("Cannot combine reorder with other change events");
        }
        if (d.length == 0) {
            return;
        }
        this.a(0, 0, d.length - 1, ListEvent.a, ListEvent.a);
        this.a(2, 0, d.length - 1, ListEvent.a, ListEvent.a);
        this.d = d;
    }
    
    public void a(final ListEvent listEvent) {
        this.a(false);
        this.d = null;
        if (this.d() && listEvent.d()) {
            this.a(listEvent.e());
        }
        else {
            while (listEvent.b()) {
                final int i = listEvent.i();
                final int f = listEvent.f();
                this.a(i, f, f, listEvent.j(), listEvent.k());
            }
            listEvent.a();
        }
        this.c();
    }
    
    public synchronized void c() {
        if (this.b == 0) {
            throw new IllegalStateException("Cannot commit without an event in progress");
        }
        --this.b;
        this.c = true;
        if (this.b != 0) {
            return;
        }
        if (this.d()) {
            this.i();
            return;
        }
        if (this.l) {
            return;
        }
        this.l = true;
        this.i.b(this.a, this.j, this.k);
    }
    
    public boolean d() {
        return this.g ? this.f.a() : this.h.a();
    }
    
    public synchronized void a(final ListEventListener listEventListener) {
        this.i.a(this.a, listEventListener, this.k);
    }
    
    public synchronized void b(final ListEventListener listEventListener) {
        this.i.a(this.a, (Object)listEventListener);
    }
    
    boolean e() {
        return this.g;
    }
    
    Tree4Deltas f() {
        return this.h;
    }
    
    BlockSequence g() {
        return this.f;
    }
    
    int[] h() {
        return this.d;
    }
    
    private void i() {
        this.e = null;
        this.f.b();
        this.h.a(this.a.size());
        this.d = null;
        this.h.a(false);
    }
}
