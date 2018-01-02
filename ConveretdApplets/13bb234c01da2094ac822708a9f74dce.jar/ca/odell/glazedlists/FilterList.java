// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import ca.odell.glazedlists.impl.adt.BarcodeIterator;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.matchers.Matchers;
import ca.odell.glazedlists.matchers.MatcherEditor$Listener;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.impl.adt.Barcode;

public final class FilterList extends TransformedList
{
    private Barcode b;
    private Matcher f;
    private MatcherEditor g;
    private final MatcherEditor$Listener h;
    
    public FilterList(final EventList list) {
        super(list);
        this.b = new Barcode();
        this.f = Matchers.a();
        this.g = null;
        this.h = new FilterList$PrivateMatcherEditorListener(this, null);
        this.b.b(0, list.size());
        list.a(this);
    }
    
    public FilterList(final EventList list, final MatcherEditor g) {
        this(list);
        if (g == null) {
            return;
        }
        (this.g = g).a(this.h);
        this.f = this.g.a();
        this.h();
    }
    
    public final void a(final ListEvent listEvent) {
        this.c.b();
        if (listEvent.d()) {
            final int[] e = listEvent.e();
            final int[] array = new int[this.b.c()];
            final Barcode b = this.b;
            this.b = new Barcode();
            for (int i = 0; i < e.length; ++i) {
                final Object a = b.a(e[i]);
                this.b.a(i, a, 1);
                if (a != Barcode.a) {
                    array[this.b.c(i)] = b.c(e[i]);
                }
            }
            this.c.a(array);
        }
        else {
            while (listEvent.b()) {
                final int f = listEvent.f();
                final int j = listEvent.i();
                if (j == 0) {
                    final int c = this.b.c(f);
                    if (c != -1) {
                        this.c.b(c, listEvent.j());
                    }
                    this.b.e(f, 1);
                }
                else if (j == 2) {
                    final Object value = this.a.get(f);
                    if (this.f.a(value)) {
                        this.b.b(f, 1);
                        this.c.a(this.b.c(f), value);
                    }
                    else {
                        this.b.a(f, 1);
                    }
                }
                else {
                    if (j != 1) {
                        continue;
                    }
                    final int c2 = this.b.c(f);
                    final boolean b2 = c2 != -1;
                    final Object value2 = this.a.get(f);
                    final boolean a2 = this.f.a(value2);
                    if (b2 && !a2) {
                        this.b.c(f, 1);
                        this.c.b(c2, listEvent.j());
                    }
                    else if (!b2 && a2) {
                        this.b.d(f, 1);
                        this.c.a(this.b.c(f), value2);
                    }
                    else {
                        if (!b2 || !a2) {
                            continue;
                        }
                        this.c.a(c2, listEvent.j(), value2);
                    }
                }
            }
        }
        this.c.c();
    }
    
    private void a(final MatcherEditor matcherEditor, final Matcher matcher, final int n) {
        this.b().a().a();
        try {
            this.b(matcherEditor, matcher, n);
        }
        finally {
            this.b().a().b();
        }
    }
    
    private void b(final MatcherEditor matcherEditor, final Matcher f, final int n) {
        if (this.g != matcherEditor) {
            throw new IllegalStateException();
        }
        switch (n) {
            case 2: {
                this.f = f;
                this.g();
                break;
            }
            case 3: {
                this.f = f;
                this.f();
                break;
            }
            case 4: {
                this.f = f;
                this.h();
                break;
            }
            case 0: {
                this.f = Matchers.a();
                this.e();
                break;
            }
            case 1: {
                this.f = Matchers.b();
                this.d();
                break;
            }
        }
    }
    
    private void d() {
        this.c.b();
        for (int i = 0; i < this.size(); ++i) {
            this.c.b(0, this.get(i));
        }
        this.b.d();
        this.b.a(0, this.a.size());
        this.c.c();
    }
    
    private void e() {
        this.c.b();
        final BarcodeIterator h = this.b.h();
        while (h.b()) {
            h.d();
            final int g = h.g();
            this.c.a(g, this.a.get(g));
        }
        this.b.d();
        this.b.b(0, this.a.size());
        this.c.c();
    }
    
    private void f() {
        this.c.b();
        final BarcodeIterator h = this.b.h();
        while (h.b()) {
            h.d();
            final Object value = this.a.get(h.g());
            if (this.f.a(value)) {
                this.c.a(h.f(), value);
            }
        }
        this.c.c();
    }
    
    private void g() {
        this.c.b();
        final BarcodeIterator h = this.b.h();
        while (h.a()) {
            h.c();
            final Object value = this.a.get(h.g());
            if (!this.f.a(value)) {
                final int h2 = h.h();
                h.e();
                this.c.b(h2, value);
            }
        }
        this.c.c();
    }
    
    private void h() {
        this.c.b();
        final BarcodeIterator h = this.b.h();
        while (h.hasNext()) {
            h.next();
            final int h2 = h.h();
            final boolean b = h2 != -1;
            final Object value = this.a.get(h.g());
            final boolean a = this.f.a(value);
            if (b && !a) {
                h.e();
                this.c.b(h2, value);
            }
            else {
                if (b || !a) {
                    continue;
                }
                this.c.a(h.f(), value);
            }
        }
        this.c.c();
    }
    
    public final int size() {
        return this.b.c();
    }
    
    protected final int a(final int n) {
        return this.b.a(n, Barcode.b);
    }
    
    protected boolean a() {
        return true;
    }
}
