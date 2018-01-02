// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.util.Vector;

public final class s extends u
{
    private int a;
    private bl a;
    private boolean[] a;
    private boolean a;
    private boolean b;
    private fd a;
    private long a;
    private int b;
    private dp a;
    private boolean j;
    private boolean k;
    
    public static int a(final int n) {
        return 54 + (n - 1 << 4);
    }
    
    public s(final fd fd, final int n, final boolean b) {
        this(fd, n, new du(), b);
    }
    
    private s(final fd a, final int n, final dp a2, final boolean k) {
        super(true);
        this.a = new bl((byte)0);
        this.a = new boolean[20];
        this.a = false;
        this.b = true;
        this.a = 0L;
        this.b = -1;
        this.j = false;
        this.k = k;
        this.a = a2;
        this.a = a;
        this.a = a(n);
    }
    
    public final synchronized void a(final boolean a) {
        this.a = a;
    }
    
    private void l() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = false;
        }
        this.b = -1;
    }
    
    public final synchronized void a() {
        this.j = true;
    }
    
    private int g() {
        if (this.a.a.size() > 1) {
            return Math.min(16, (this.a - 54) / (this.a.a.size() - 1));
        }
        return 16;
    }
    
    private int h() {
        return (this.a - (54 + (this.a.a.size() - 1) * this.g())) / 2;
    }
    
    public final synchronized void a(final ei ei) {
        this.b(ei);
        final int h = this.h();
        for (int i = 0; i < this.a.a.size(); ++i) {
            final int n = h + i * this.g();
            final en a = this.a.a(i);
            final boolean b = this.a[i] || (!this.a && this.b == i);
            this.a.a(a, ei, n, b ? 0 : 2, b, false);
        }
    }
    
    public final synchronized void a(final bl bl) {
        this.a.a.removeAllElements();
        this.a.a(bl);
        if (this.a.a.size() > this.a.length) {
            this.a = new boolean[this.a.a.size()];
        }
        this.l();
        if (this.a != null) {
            this.a.a(this.a);
        }
        this.h();
    }
    
    public final synchronized void a(final en en) {
        for (int i = 0; i < this.a.a.size(); ++i) {
            if (en.equals(this.a.a(i))) {
                this.a[i] = true;
                break;
            }
        }
        this.h();
    }
    
    public final synchronized en[] a() {
        final Vector vector = new Vector<en>();
        for (int i = 0; i < this.a.a.size(); ++i) {
            if (this.a[i]) {
                vector.addElement(this.a.a(i));
            }
        }
        final en[] array;
        vector.copyInto(array = new en[vector.size()]);
        return array;
    }
    
    public final boolean a(final Event event, int b, int b2) {
        n = (int)null;
        synchronized (this) {
            if (!this.b || this.a.a.size() == 0) {
                return true;
            }
            final int n2 = b;
            final int h = this.h();
            if ((b = ((n2 < h || n2 >= h + 54 + (this.a.a.size() - 1) * this.g()) ? -1 : Math.min((n2 - this.h()) / this.g(), this.a.a.size() - 1))) == -1) {
                this.l();
            }
            else {
                if (this.a) {
                    if (this.j) {
                        this.l();
                        this.j = false;
                    }
                    this.a[b] = !this.a[b];
                    n = (int)new Event(this, 701, this.a.a(b));
                }
                else {
                    b2 = this.b;
                    this.l();
                    this.a[b] = true;
                    if (event.when - this.a < 2000L && b2 == b) {
                        n = (int)new Event(this, 1001, this.a.a(b));
                        this.b = -1;
                    }
                    else {
                        n = (int)new Event(this, 701, this.a.a(b));
                        this.b = b;
                    }
                }
                this.a = event.when;
                this.h();
            }
        }
        if (n != null) {
            final u u;
            u.a((Event)n);
        }
        return true;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final int b() {
        return 74 / (this.k ? 2 : 1);
    }
}
