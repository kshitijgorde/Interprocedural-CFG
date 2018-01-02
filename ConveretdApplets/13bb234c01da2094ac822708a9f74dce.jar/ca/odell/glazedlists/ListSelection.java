// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import java.util.Iterator;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.impl.adt.BarcodeIterator;
import ca.odell.glazedlists.event.ListEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import ca.odell.glazedlists.impl.adt.Barcode;
import ca.odell.glazedlists.event.ListEventListener;

public class ListSelection implements ListEventListener
{
    private static final Object a;
    private static final Object b;
    private final EventList c;
    private ListSelection$SelectedList d;
    private ListSelection$DeselectedList e;
    private ListSelection$SelectionToggleList f;
    private ListSelection$DeselectionToggleList g;
    private Barcode h;
    private int i;
    private int j;
    private int k;
    private final Collection l;
    private final List m;
    
    public ListSelection(final EventList c) {
        this.h = new Barcode();
        this.i = -1;
        this.j = -1;
        this.k = 103;
        this.l = new ArrayList();
        this.m = new ArrayList(1);
        this.c = c;
        this.h.a(0, ListSelection.b, c.size());
        c.a(this);
    }
    
    public void a(final ListEvent listEvent) {
        final int f = this.f();
        final int g = this.g();
        boolean b = false;
        if (listEvent.d()) {
            this.l();
            final int[] e = listEvent.e();
            final int[] array = new int[this.h.a(ListSelection.a)];
            final int[] array2 = new int[this.h.a(ListSelection.b)];
            final Barcode h = this.h;
            this.h = new Barcode();
            for (int i = 0; i < e.length; ++i) {
                final Object a = h.a(e[i]);
                final boolean b2 = a != ListSelection.b;
                this.h.a(i, a, 1);
                if (b2) {
                    array[this.h.b(i, ListSelection.a)] = h.b(e[i], ListSelection.a);
                }
                else {
                    array2[this.h.b(i, ListSelection.b)] = h.b(e[i], ListSelection.b);
                }
            }
            this.j = -1;
            this.i = -1;
            this.a(array);
            this.m();
            this.n();
            this.b(array2);
            this.o();
            b = true;
        }
        else {
            this.j();
            while (listEvent.b()) {
                final int f2 = listEvent.f();
                final int j = listEvent.i();
                final int b3 = this.h.b(f2, ListSelection.a);
                final boolean b4 = b3 != -1;
                if (j == 0) {
                    if (f2 <= g) {
                        b = true;
                    }
                    if (b4) {
                        this.h.e(f2, 1);
                        this.b(b3, listEvent.j());
                    }
                    else {
                        this.d(this.h.b(f2, ListSelection.b), listEvent.j());
                        this.h.e(f2, 1);
                    }
                }
                else if (j == 2) {
                    if (f2 <= g) {
                        b = true;
                    }
                    if (b4) {
                        if (this.k == 1 || this.k == 2) {
                            this.h.a(f2, ListSelection.a, 1);
                            this.a(b3, listEvent.k());
                        }
                        else {
                            this.h.a(f2, ListSelection.b, 1);
                            this.c(this.h.b(f2, ListSelection.b), listEvent.k());
                        }
                    }
                    else {
                        this.h.a(f2, ListSelection.b, 1);
                        this.c(this.h.b(f2, ListSelection.b), listEvent.k());
                    }
                }
                else if (j == 1) {
                    if (b4) {
                        this.a(b3, listEvent.j(), listEvent.k());
                    }
                    else {
                        this.b(this.h.b(f2, ListSelection.b), listEvent.j(), listEvent.k());
                    }
                }
                this.j = this.a(this.j, j, f2);
                this.i = this.a(this.i, j, f2);
            }
            this.k();
        }
        if (f != -1 && g != -1 && b) {
            final int f3 = this.f();
            final int g2 = this.g();
            int n = f;
            int n2 = g;
            if (f3 != -1 && f3 < n) {
                n = f3;
            }
            if (g2 != -1 && g2 > n2) {
                n2 = g2;
            }
            this.d(n, n2);
        }
    }
    
    private int a(final int n, final int n2, final int n3) {
        if (n == -1) {
            return -1;
        }
        if (n2 == 0) {
            if (n3 < n) {
                return n - 1;
            }
            if (n3 == n) {
                return -1;
            }
            return n;
        }
        else {
            if (n2 == 1) {
                return n;
            }
            if (n2 != 2) {
                throw new IllegalStateException();
            }
            if (n3 <= n) {
                return n + 1;
            }
            return n;
        }
    }
    
    public EventList a() {
        synchronized (this.m) {
            if (this.d == null) {
                this.d = new ListSelection$SelectedList(this, this.c);
                this.c.c().b(this.d, this);
            }
        }
        return this.d;
    }
    
    public boolean a(final int n) {
        return n >= 0 && n < this.c.size() && this.h.b(n, ListSelection.a) != -1;
    }
    
    public void b(final int n) {
        this.a(n, n);
    }
    
    public void a(final int j, int max) {
        if (j == -1 || max == -1) {
            return;
        }
        if (this.k == 0) {
            final int g = this.g();
            if (g >= j && g <= max) {
                this.b();
            }
            return;
        }
        if (this.k == 1 && j > this.f()) {
            max = Math.max(max, this.g());
        }
        this.a(false, this.j = j, this.i = max, -1, -1, (this.i == max) ? -1 : this.i, (this.j == j) ? -1 : this.j);
    }
    
    public void b() {
        this.a(ListSelection.b);
    }
    
    private void a(final Object o) {
        final Object o2 = (o == ListSelection.a) ? ListSelection.b : ListSelection.a;
        if (this.h.a(o2) == 0) {
            return;
        }
        int n = -1;
        int n2 = -1;
        this.j();
        final BarcodeIterator h = this.h.h();
        while (h.a(o2)) {
            h.b(o2);
            final int g = h.g();
            final Object value = this.c.get(g);
            if (o == ListSelection.a) {
                this.d(0, value);
                this.a(g, value);
            }
            else {
                this.b(0, value);
                this.c(g, value);
            }
            if (n == -1) {
                n = g;
            }
            n2 = g;
        }
        this.h.d();
        this.h.a(0, o, this.c.size());
        this.k();
        this.d(n, n2);
    }
    
    public void c(final int n) {
        this.b(n, n);
    }
    
    public void b(final int j, final int i) {
        if (j == -1 || i == -1) {
            return;
        }
        if (this.k == 0) {
            this.d(j);
            return;
        }
        if (this.k == 1) {
            boolean b = false;
            final int f = this.f();
            final int g = this.g();
            if (f - 1 <= j && j <= g + 1) {
                b = true;
            }
            if (f - 1 <= i && i <= g + 1) {
                b = true;
            }
            if (!b) {
                this.c(j, i);
                return;
            }
        }
        this.a(true, this.j = j, this.i = i, -1, -1, (this.i == i) ? -1 : this.i, (this.j == j) ? -1 : this.j);
    }
    
    public void d(final int n) {
        this.c(n, n);
    }
    
    public void c(final int j, int i) {
        if (j == -1 || i == -1) {
            this.b();
            return;
        }
        if (this.k == 0) {
            i = j;
        }
        this.a(true, this.j = j, this.i = i, this.f(), this.g(), (this.i == i) ? -1 : this.i, (this.j == j) ? -1 : this.j);
    }
    
    public int c() {
        return this.j;
    }
    
    public void e(final int j) {
        final int n = (this.j == j) ? -1 : j;
        this.j = j;
        if (j == -1 || this.i == -1) {
            this.b();
        }
        else if (this.k == 0) {
            this.a(true, j, j, this.f(), this.g(), -1, n);
        }
        else if (this.k == 1) {
            this.a(true, j, this.i, this.f(), this.g(), -1, n);
        }
        else {
            this.a(true, j, this.i, -1, -1, -1, n);
        }
    }
    
    public int d() {
        return this.i;
    }
    
    public void f(final int i) {
        final int n = (this.i == i) ? -1 : i;
        final int j = this.i;
        this.i = i;
        if (i == -1 || this.j == -1) {
            this.b();
        }
        else if (this.k == 0) {
            this.a(true, i, i, this.f(), this.g(), n, -1);
        }
        else if (this.k == 1) {
            this.a(true, this.j, i, this.f(), this.g(), n, -1);
        }
        else {
            this.a(true, this.j, i, this.j, j, n, -1);
        }
    }
    
    private void a(final int[] array) {
        if (this.d != null) {
            this.d.d().a(array);
        }
        if (this.f != null) {
            this.f.d().a(array);
        }
    }
    
    private void b(final int[] array) {
        if (this.e != null) {
            this.e.d().a(array);
        }
        if (this.g != null) {
            this.g.d().a(array);
        }
    }
    
    private void a(final int n, final int n2, final Object o) {
        this.d(n2, o);
        this.a(n, o);
    }
    
    private void b(final int n, final int n2, final Object o) {
        this.b(n, o);
        this.c(n2, o);
    }
    
    private void a(final int n, final Object o) {
        if (this.d != null) {
            this.d.d().a(n, o);
        }
        if (this.f != null) {
            this.f.d().a(n, o);
        }
    }
    
    private void a(final int n, final Object o, final Object o2) {
        if (this.d != null) {
            this.d.d().a(n, o, o2);
        }
        if (this.f != null) {
            this.f.d().a(n, o, o2);
        }
    }
    
    private void b(final int n, final Object o) {
        if (this.d != null) {
            this.d.d().b(n, o);
        }
        if (this.f != null) {
            this.f.d().b(n, o);
        }
    }
    
    private void c(final int n, final Object o) {
        if (this.e != null) {
            this.e.d().a(n, o);
        }
        if (this.g != null) {
            this.g.d().a(n, o);
        }
    }
    
    private void d(final int n, final Object o) {
        if (this.e != null) {
            this.e.d().b(n, o);
        }
        if (this.g != null) {
            this.g.d().b(n, o);
        }
    }
    
    private void b(final int n, final Object o, final Object o2) {
        if (this.e != null) {
            this.e.d().a(n, o, o2);
        }
        if (this.g != null) {
            this.g.d().a(n, o, o2);
        }
    }
    
    private void j() {
        this.l();
        this.n();
    }
    
    private void k() {
        this.m();
        this.o();
    }
    
    private void l() {
        if (this.d != null) {
            this.d.d().b();
        }
        if (this.f != null) {
            this.f.d().b();
        }
    }
    
    private void m() {
        if (this.d != null) {
            this.d.d().c();
        }
        if (this.f != null) {
            this.f.d().c();
        }
    }
    
    private void n() {
        if (this.e != null) {
            this.e.d().b();
        }
        if (this.g != null) {
            this.g.d().b();
        }
    }
    
    private void o() {
        if (this.e != null) {
            this.e.d().c();
        }
        if (this.g != null) {
            this.g.d().c();
        }
    }
    
    public void g(final int k) {
        this.k = k;
        this.c(this.f(), this.g());
    }
    
    public int e() {
        return this.k;
    }
    
    public int f() {
        if (this.h.a(ListSelection.a) == 0) {
            return -1;
        }
        return this.h.a(0, ListSelection.a);
    }
    
    public int g() {
        if (this.h.a(ListSelection.a) == 0) {
            return -1;
        }
        return this.h.a(this.h.a(ListSelection.a) - 1, ListSelection.a);
    }
    
    private void a(boolean b, int n, int n2, int n3, int n4, final int n5, final int n6) {
        if (n >= this.c.size() || n2 >= this.c.size() || ((n == -1 || n2 == -1) && n != n2)) {
            throw new IndexOutOfBoundsException("Invalid range for selection: " + n + "-" + n2 + ", list size is " + this.c.size());
        }
        if (n3 >= this.c.size() || n4 >= this.c.size() || ((n3 == -1 || n4 == -1) && n3 != n4)) {
            throw new IndexOutOfBoundsException("Invalid range for invert selection: " + n3 + "-" + n4 + ", list size is " + this.c.size());
        }
        if (n == -1 && n2 == -1) {
            if (n3 == -1 && n4 == -1) {
                return;
            }
            n = n3;
            n2 = n4;
            b = !b;
        }
        if (n3 == -1 && n4 == -1) {
            n3 = n;
            n4 = n2;
        }
        final int min = Math.min(n, n2);
        final int max = Math.max(n, n2);
        final int min2 = Math.min(n3, n4);
        final int max2 = Math.max(n3, n4);
        final int min3 = Math.min(min, min2);
        final int max3 = Math.max(max, max2);
        int n7 = max3 + 1;
        int n8 = min3 - 1;
        this.j();
        for (int i = min3; i <= max3; ++i) {
            final int b2 = this.h.b(i, ListSelection.a);
            final boolean b3 = b2 != -1;
            if (b3 != ((i >= min && i <= max) == b && this.h(i))) {
                final Object value = this.c.get(i);
                if (i < n7) {
                    n7 = i;
                }
                if (i > n8) {
                    n8 = i;
                }
                if (b3) {
                    this.h.b(i, ListSelection.b, 1);
                    this.b(b2, i - b2, value);
                }
                else {
                    this.h.b(i, ListSelection.a, 1);
                    final int b4 = this.h.b(i, ListSelection.a);
                    this.a(b4, i - b4, value);
                }
            }
        }
        this.k();
        if (n5 != -1) {
            n7 = Math.min(n7, n5);
            n8 = Math.max(n8, n5);
        }
        if (n6 != -1) {
            n7 = Math.min(n7, n6);
            n8 = Math.max(n8, n6);
        }
        if (n7 <= n8) {
            this.d(n7, n8);
        }
    }
    
    private boolean h(final int n) {
        if (this.l.isEmpty()) {
            return true;
        }
        final Object value = this.c.get(n);
        final Iterator<Matcher> iterator = this.l.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().a(value)) {
                return false;
            }
        }
        return true;
    }
    
    public void a(final ListSelection$Listener listSelection$Listener) {
        this.m.add(listSelection$Listener);
    }
    
    private void d(final int n, final int n2) {
        final Iterator<ListSelection$Listener> iterator = this.m.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(n, n2);
        }
    }
    
    static {
        a = Barcode.b;
        b = Barcode.a;
    }
}
