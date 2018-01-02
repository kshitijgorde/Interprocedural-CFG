// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;

public final class am extends fg
{
    private ag a;
    private dm a;
    private Vector a;
    private es[] a;
    private Object a;
    private static Color h;
    private static Color i;
    private int w;
    private int x;
    private int y;
    private int z;
    private Font b;
    private FontMetrics a;
    private FontMetrics b;
    
    public am(final dm a, final Object a2, final String s) {
        this.a = new Vector();
        this.w = -1;
        this.b(Color.white);
        this.a = a2;
        this.a = a.a;
        this.a = a;
        this.a = new es[5];
        final av av = new av((byte)0);
        this.a(av, 2, 1, 0, 0, false);
        final Color a3 = a.a("yahoo.games.tablelist_bg", 16777164);
        final Color a4 = a.a("yahoo.games.tablelist_fg", 0);
        av.b(a3);
        av.a(a4);
        av.a(new es(a.a(1716519137), 1, 32), 1, 1, 0, 0);
        av.a(new es("", 1, 40), 1, 1, 1, 0);
        final int min = Math.min(5, 4);
        for (int i = 0; i < min; ++i) {
            av.a(this.a[i] = new es(this.a.a(a, i), 0, 78), 1, 1, i + 2, 0);
        }
        av.a(new es(s, es.a, 1), 1, 1, min + 2, 0, true);
    }
    
    public final synchronized void a() {
        this.a.removeAllElements();
        this.w = -1;
        this.m();
    }
    
    private static int a(final fm fm) {
        if (fm.f > 0 && fm.h == 0) {
            return 2;
        }
        if (fm.h != 2) {
            return 1;
        }
        return 0;
    }
    
    private int a(final int n) {
        for (int i = 0; i < this.a.size(); ++i) {
            final fm fm = this.a.elementAt(i);
            if (n + 1 >= fm.d && n <= fm.d + fm.a) {
                return i;
            }
        }
        return -1;
    }
    
    public final synchronized void a(final ei ei, final int n, final int n2, int d, final int n3) {
        int a = this.a(d);
        ei.a(this.a());
        ei.b(n, d, n2 - n, n3 - d);
        if (a != -1) {
            fm fm;
            while (a < this.a.size() && (d = (fm = this.a.elementAt(a)).d) < n3) {
                final int n4 = fm.b * this.z;
                ei.a(Color.black);
                ei.a(0, d - 1, n2 - 1, d - 1);
                ei.a(0, d, 0, d + fm.a - 2);
                ei.a(n2 - 1, d, n2 - 1, d + fm.a - 2);
                ei.a(0, d + fm.a - 2, n2 - 1, d + fm.a - 2);
                ei.a(32, d, 32, d + fm.a - 2);
                ei.a(am.h);
                ei.b(1, d, 31, fm.a - 2);
                ei.a(Color.black);
                final String string = "#" + Integer.toString(fm.e);
                ei.a(string, (32 - this.a.stringWidth(string)) / 2, d + (fm.a - 1 + this.a.getAscent()) / 2);
                for (int i = 0; i < fm.g; ++i) {
                    this.a(ei, d, fm, a, i, n4);
                }
                this.a(ei, d, fm, a, -1, n4);
                ei.a(am.h);
                final int n5 = 72 + fm.c * 78 + 1;
                final int n6 = n2 - n5 - 1;
                ei.b(n5, d, n6, n4 - 1);
                final StringBuffer[] array = new StringBuffer[fm.b];
                for (int j = 0; j < fm.b; ++j) {
                    array[j] = new StringBuffer();
                }
                int n7 = 0;
                for (int k = 0; k < fm.a.size(); ++k) {
                    eu eu;
                    int n8;
                    for (eu = fm.a.elementAt(k), n8 = 0; n8 < fm.a.length && (fm.a[n8] == null || eu != fm.a[n8]); ++n8) {}
                    if (n8 == fm.a.length) {
                        if (n7 > 0) {
                            array[(n7 - 1) % fm.b].append(",");
                        }
                        array[n7 % fm.b].append(eu.b);
                        ++n7;
                    }
                }
                ei.a(Color.black);
                for (int l = 0; l < fm.b; ++l) {
                    ei.a(fg.a(array[l], this.a, n6), 72 + fm.c * 78 + 2, d + l * this.z + (this.z + this.a.getAscent()) / 2);
                }
                if (fm.a != null) {
                    ei.a(am.i);
                    ei.a(33, d + n4 - 1, n2 - 2, d + n4 - 1);
                    ei.a(am.h);
                    ei.b(33, d + n4, n2 - 34, this.y - 1);
                    ei.a(am.i);
                    ei.a(33, d + n4 - 1, n2 - 2, d + n4 - 1);
                    ei.a(this.b);
                    ei.a(Color.black);
                    ei.a(this.a.a(1716519139) + fm.a, 36, d + n4 + (this.y + this.b.getAscent()) / 2);
                    ei.a(y.u.a);
                }
                ei.a(am.i);
                for (int n9 = 0; n9 < fm.c; ++n9) {
                    if (n9 < fm.g) {
                        ei.a(72 + n9 * 78, d, 72 + n9 * 78, d + n4 - 2);
                    }
                }
                ei.a(72 + fm.c * 78, d, 72 + fm.c * 78, d + n4 - 2);
                for (int n10 = 1; n10 < fm.b; ++n10) {
                    ei.a(73, d + this.z * n10 - 1, 78 * fm.c + 32 + 40 - 1, d + this.z * n10 - 1);
                }
                ++a;
            }
        }
    }
    
    private void o() {
        int d = 1;
        for (int i = 0; i < this.a.size(); ++i) {
            final fm fm;
            (fm = this.a.elementAt(i)).d = d;
            fm.c = ((fm.g > 5) ? ((fm.g + 1) / 2) : fm.g);
            fm.b = ((fm.g == 0) ? 2 : ((fm.g + fm.c - 1) / fm.c));
            fm.a = this.z * fm.b + ((fm.a == null) ? 0 : this.y) + 1;
            d += fm.a;
        }
    }
    
    public final synchronized void a(final fm fm) {
        int i = 0;
        int size = this.a.size();
        int n;
        if (this.a.size() == 0) {
            n = 0;
        }
        else {
            while (i < size) {
                final int n2 = (i + size) / 2;
                final fm fm2;
                final int a = a(fm2 = this.a.elementAt(n2));
                final int a2 = a(fm);
                boolean b;
                if (a == a2) {
                    b = (fm2.e < fm.e);
                }
                else {
                    b = (a > a2);
                }
                if (b) {
                    i = n2 + 1;
                }
                else {
                    size = n2;
                }
            }
            n = i;
        }
        final int n3 = n;
        this.a.insertElementAt(fm, n3);
        this.o();
        if (this.w != -1 && this.w >= n3) {
            ++this.w;
        }
        this.c(fm.d, fm.a);
    }
    
    public final synchronized void b(final fm fm) {
        final int index = this.a.indexOf(fm);
        this.a.removeElementAt(index);
        this.o();
        this.d(fm.d, fm.a);
        if (this.w != -1) {
            if (this.w == index) {
                this.w = -1;
                return;
            }
            if (this.w > index) {
                --this.w;
            }
        }
    }
    
    final void c(final fm fm) {
        final am am = this;
        final int d = fm.d;
        final int a = fm.a;
        final int n = d;
        this = am;
        if (n + a > super.a && n < super.a + super.u) {
            this.h();
        }
    }
    
    private void d(final fm fm) {
        this.b(fm);
        this.a(fm);
    }
    
    public final synchronized void a(final fm fm, int n, final eu eu) {
        fm.a[n] = eu;
        if (fm.a[n] == null) {
            ++fm.f;
            n = ((fm.f == 1) ? 1 : 0);
        }
        else {
            --fm.f;
            n = ((fm.f == 0) ? 1 : 0);
        }
        if (n != 0) {
            this.d(fm);
            return;
        }
        this.c(fm);
    }
    
    public final synchronized void a(final fm fm, final Hashtable a) {
        fm.a = a;
        fm.a = this.a.a(a, this.a);
        this.c(fm);
    }
    
    public final synchronized void a(final eu eu, final Color a) {
        eu.a = a;
        for (int i = 0; i < eu.a.size(); ++i) {
            final fm fm = eu.a.elementAt(i);
            for (int j = 0; j < fm.a.length; ++j) {
                if (fm.a[j] == eu) {
                    this.c(fm);
                }
            }
        }
    }
    
    public final synchronized void a(final fm fm, final int h) {
        fm.h = h;
        this.d(fm);
    }
    
    private int a(final ei ei, final int n, final int n2, int n3, final String s, final int n4, final Color color) {
        ei.a(color);
        n3 = n4 + 32 + 40 + n3 * 78 + (78 - this.a.stringWidth(s) - n4) / 2;
        ei.a(s, n3, n + n2 * this.z + (this.z + this.a.getAscent()) / 2);
        return n3;
    }
    
    public final void b() {
        super.b();
        this.m();
        this.a = this.a(y.u.a);
        this.b = new Font(y.u.a.getName(), 0, 8);
        this.b = this.a(this.b);
        this.y = this.b.getHeight() + 2;
        this.b(this.z = 4 + this.a.getHeight());
        this.o();
        if (this.a.size() > 0) {
            final fm fm = this.a.lastElement();
            this.c(0, fm.d + fm.a);
        }
    }
    
    private void a(final ei ei, final int n, final fm fm, int a, final int n2, int n3) {
        if (n2 == -1) {
            ei.a(am.h);
            ei.b(33, n, 39, n3 - 1);
            ei.a(Color.lightGray);
            if (fm.h != 2) {
                ei.a(33, n, 39, n3 - 1, this.w != a || this.x != -1);
                ei.a(Color.black);
                if (this.a instanceof Image) {
                    final Image image = (Image)this.a;
                    ei.a(image, 32 + (40 - image.getWidth(null)) / 2, n + (n3 - image.getHeight(null)) / 2, null);
                    return;
                }
                final String s = (String)this.a;
                ei.a(s, 32 + (40 - this.a.stringWidth(s)) / 2, n + (n3 + this.a.getAscent()) / 2);
            }
        }
        else {
            final int n4 = n2 / fm.c;
            n3 = n2 % fm.c;
            final int n5 = 72 + n3 * 78;
            if (n2 < fm.g) {
                if (fm.a[n2] == null && fm.h == 0) {
                    ei.a(Color.lightGray);
                    ei.a(n5 + 1, n + n4 * this.z, 77, this.z - 1, this.w != a || this.x != n2);
                    this.a(ei, n, n4, n3, this.a.a(1716519140), 0, Color.black);
                    return;
                }
                ei.a(am.h);
                ei.b(n5 + 1, n + n4 * this.z, 77, this.z - 1);
                if (fm.a[n2] != null) {
                    final int n6 = (fm.a[n2].a != null) ? 12 : 0;
                    a = this.a(ei, n, n4, n3, fg.a(new StringBuffer(fm.a[n2].b), this.a, 78 - n6), n6, (this.w == a && this.x == n2) ? Color.red : Color.black);
                    if (fm.a[n2].a != null) {
                        ei.a(fm.a[n2].a);
                        ei.b(a - 10, n + n4 * this.z + this.z / 2 - 4, 8, 8);
                    }
                }
            }
            else {
                ei.a(am.h);
                ei.b(n5, n + n4 * this.z, 78, this.z - 1);
            }
        }
    }
    
    private aa a(final int n, final int n2) {
        final int a;
        if ((a = this.a(n2)) == -1) {
            return null;
        }
        final fm fm = this.a.elementAt(a);
        int n3;
        if (n >= 32 && n < 72) {
            n3 = -1;
        }
        else {
            final int n4;
            if ((n4 = (n - 72) / 78) >= fm.c || n < 72) {
                return null;
            }
            if ((n3 = n4 + (n2 - fm.d) / this.z * fm.c) < 0 || n3 >= fm.g) {
                return null;
            }
        }
        return new aa(n3, a);
    }
    
    public final synchronized void a(final Event event, final int n, final int n2) {
        final aa a;
        if ((a = this.a(n, n2)) != null) {
            this.w = a.b;
            this.x = a.a;
            this.h();
        }
    }
    
    public final void b(Event event, final int n, final int n2) {
        event = null;
        synchronized (this) {
            final aa a;
            if ((a = this.a(n, n2)) != null && a.b == this.w && a.a == this.x) {
                final fm fm;
                if (((fm = this.a.elementAt(this.w)).h != 2 && this.x == -1) || (fm.h == 0 && fm.a[this.x] == null && this.x < fm.g)) {
                    event = new Event(this, 1001, new r(this.a.elementAt(this.w), new Integer(this.x)));
                }
                else if (this.x != -1 && fm.a[this.x] != null) {
                    event = new Event(this, 701, fm.a[this.x].a);
                }
            }
            this.w = -1;
            this.h();
        }
        if (event != null) {
            final u u;
            u.a(event);
        }
    }
    
    public final int g() {
        return 10;
    }
    
    public final int h() {
        return 10;
    }
    
    static {
        am.h = new Color(14474460);
        am.i = new Color(10066329);
    }
}
