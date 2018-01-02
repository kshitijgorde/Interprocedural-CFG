// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.event;

import ca.odell.glazedlists.event.ListEvent;
import java.util.ArrayList;
import java.util.List;
import ca.odell.glazedlists.impl.adt.gnutrove.TIntArrayList;

public class BlockSequence
{
    private TIntArrayList a;
    private TIntArrayList b;
    private TIntArrayList c;
    private List d;
    private List e;
    
    public BlockSequence() {
        this.a = new TIntArrayList();
        this.b = new TIntArrayList();
        this.c = new TIntArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
    }
    
    public boolean a(final int n, final int n2, final int n3, final Object o, final Object o2) {
        final int a = this.c.a();
        int c;
        int c2;
        int c3;
        int n4;
        Object a2;
        Object o3;
        if (a == 0) {
            c = -1;
            c2 = -1;
            c3 = 0;
            n4 = 0;
            a2 = ListEvent.a;
            o3 = ListEvent.a;
        }
        else {
            c = this.c.c(a - 1);
            c2 = this.a.c(a - 1);
            c3 = this.b.c(a - 1);
            n4 = ((c == 0) ? c2 : c3);
            a2 = ((c == 0) ? this.d.get(a - 1) : ListEvent.a);
            o3 = this.e.get(a - 1);
        }
        if (n2 < n4) {
            return false;
        }
        if (n4 == n2 && c == n && o == a2 && o2 == o3) {
            this.b.a(a - 1, c2 + (c3 - c2 + (n3 - n2)));
            return true;
        }
        this.a.b(n2);
        this.b.b(n3);
        this.c.b(n);
        this.d.add(o);
        this.e.add(o2);
        return true;
    }
    
    public boolean a() {
        return this.c.b();
    }
    
    public void b() {
        this.a.c();
        this.b.c();
        this.c.c();
        this.d.clear();
        this.e.clear();
    }
    
    public BlockSequence$Iterator c() {
        return new BlockSequence$Iterator(this);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.c.a(); ++i) {
            if (i != 0) {
                sb.append(", ");
            }
            final int c = this.c.c(i);
            if (c == 2) {
                sb.append("+");
            }
            else if (c == 1) {
                sb.append("U");
            }
            else if (c == 0) {
                sb.append("X");
            }
            final int c2 = this.a.c(i);
            final int c3 = this.b.c(i);
            sb.append(c2);
            if (c3 != c2) {
                sb.append("-");
                sb.append(c3);
            }
        }
        return sb.toString();
    }
}
