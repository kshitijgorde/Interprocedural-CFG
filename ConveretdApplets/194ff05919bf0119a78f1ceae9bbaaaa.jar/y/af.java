// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;

public final class af extends av
{
    long a;
    public Vector a;
    int a;
    int b;
    int t;
    int u;
    String[] a;
    private double[] a;
    int[] a;
    public boolean[] a;
    private double a;
    public ax a;
    public int v;
    boolean a;
    boolean b;
    ct a;
    FontMetrics a;
    private Font b;
    public bu a;
    int w;
    private boolean k;
    int x;
    Color h;
    public fa a;
    private Vector b;
    boolean j;
    
    public af() {
        this(4, 50, 1, -1, null, false, true, true);
    }
    
    public af(final int a, final int b, final int u, final int v, final bu a2, final boolean b2, final boolean a3, final boolean b3) {
        super((byte)0);
        this.a = new Vector();
        this.a = 0.0;
        this.a = null;
        this.b = y.u.a;
        this.w = 0;
        this.k = false;
        this.h = Color.lightGray;
        this.b = new Vector();
        this.a = new fa(this, b3);
        this.a = new String[u];
        this.a = new double[u];
        this.a = new boolean[u];
        for (int i = 0; i < u; ++i) {
            this.a[i] = "";
            this.a[i] = 1.0;
        }
        this.a = a3;
        this.v = v;
        this.a = a2;
        if (v != -1 && a2 == null) {
            this.a = new ch();
        }
        this.b = b;
        final av av = new av((byte)0);
        this.a = new ct(this);
        if (b2) {
            av.a(this.a, 10, 2, 2, 1, 1, 0, 0);
            final u u2 = new u(1, 1);
            av.a(u2, 10, 2, 2, 1, 1, 0, 1);
            u2.b(this.h);
            this.b.addElement(u2);
        }
        this.a = a;
        this.u = u;
        this.a = new int[this.u + 1];
        final u u3 = new u(1, 1);
        this.b.addElement(u3);
        this.a(u3, 3, 1, 0, 0, false);
        final u u4 = new u(1, 1);
        this.b.addElement(u4);
        this.a(u4, 1, 2, 0, 1, false);
        final u u5 = new u(1, 1);
        this.b.addElement(u5);
        this.a(u5, 3, 1, 0, 3, false);
        final u u6 = new u(1, 1);
        this.b.addElement(u6);
        this.a(u6, 1, 2, 2, 1, false);
        this.a(av, 10, 2, 2, 1, 1, 1, 1);
        this.a(this.a, 1, 1, 1, 2, true);
        this.b(Color.white);
    }
    
    public final void a(final int n, final double n2) {
        this.a[n] = n2;
        this.k = false;
        this.h();
    }
    
    public final void a() {
        this.b = true;
        this.h();
    }
    
    public final ax a(final String s, final Object o) {
        final ax ax;
        (ax = new ax(this, o)).a[0] = s;
        final af af = this;
        final ax ax2 = ax;
        this = af;
        af.b(ax2, (this.a != null) ? this.a.a(this.a, ax2) : this.a.size());
        return ax;
    }
    
    private void b(final ax ax, final int n) {
        this.a.insertElementAt(ax, n);
        this.a.c(n * this.t, this.t);
    }
    
    public final void a(final String s) {
        this.a(s, this.b());
    }
    
    public final void a(final String s, final Color color) {
        this.a(s, this.a.size(), color);
    }
    
    public final void l() {
        this.j = false;
        this.a.n();
    }
    
    public final void a(String s, int n, Color b) {
        if (b == null) {
            b = this.b();
        }
        if (this.x == 0 || this.a == null) {
            this.a(s, (Object)null).a = b;
            return;
        }
        while (s.length() > 0) {
            int n2;
            for (n2 = s.length(); (this.a.stringWidth(s.substring(0, n2)) > this.x - 4 || (n2 != s.length() && s.charAt(n2) != ' ')) && n2 > 1; --n2) {}
            if (n2 == 1) {
                n2 = s.length();
            }
            final String substring = s.substring(0, n2);
            final int n3 = n;
            final ax ax;
            (ax = new ax(this, null)).a[0] = substring;
            this.b(ax, n3);
            ax.a = b;
            ++n;
            if (n2 == s.length()) {
                break;
            }
            s = s.substring(n2 + 1);
            if (this.x <= 50) {
                continue;
            }
            s = "  " + s;
        }
        if (this.a.size() > 256) {
            this.a(0);
        }
        if (!this.j) {
            this.a.n();
        }
    }
    
    public final void a(final ax ax, final int n) {
        if (this.a != null) {
            this.a.removeElementAt(n);
            final int a = this.a.a(this.a, ax);
            if (n != a) {
                this.a.d(n * this.t, this.t);
            }
            this.a.insertElementAt(ax, a);
            if (n != a) {
                this.a.c(a * this.t, this.t);
            }
        }
        this.h();
    }
    
    public final void a(final String s, final int n, final int n2) {
        this.a(s, this.a.elementAt(n), n2);
    }
    
    public final void a(final String s, final ax ax, final int n) {
        final int a = this.a(ax);
        ax.a[n] = s;
        this.a(ax, a);
    }
    
    public final void a(final u u, final ax ax, int a, final int n) {
        if (ax.a[0][n] == null) {
            a = this.a(ax);
            ax.a[0][n] = u;
            if (super.c) {
                u.b();
            }
            this.a(ax, a);
        }
    }
    
    public final void a(final ax ax, final int n, final int n2) {
        final u u;
        if ((u = ax.a[0][n2]) != null) {
            final int a = this.a(ax);
            ax.a[0][n2] = null;
            if (super.c) {
                u.g();
            }
            this.a(ax, a);
        }
    }
    
    public final void a(final Color color, final ax ax) {
        if (ax.a[0][3] != null) {
            this.a(ax, 0, 3);
        }
        if (color != null) {
            this.a(new ce(color), ax, 0, 3);
        }
    }
    
    public final void b(final Color a, final ax ax) {
        final int a2 = this.a(ax);
        ax.a = a;
        this.a(ax, a2);
    }
    
    public final void a(final int n) {
        if (n == -1) {
            return;
        }
        if (this.a.elementAt(n) == this.a) {
            this.a = null;
        }
        this.a.removeElementAt(n);
        this.a.d(n * this.t, this.t);
    }
    
    public final void a(final ax ax) {
        this.a(this.a(ax));
    }
    
    public final void m() {
        this.a.removeAllElements();
        ((fg)(this.a = null)).m();
    }
    
    public final void a(final int n, final String s) {
        this.a[n] = s;
        this.a.h();
    }
    
    public final int a(final ax ax) {
        if (this.a != null) {
            final int a = this.a.a(this.a, ax);
            if (this.a.elementAt(a) == ax) {
                return a;
            }
        }
        else {
            for (int i = 0; i < this.a.size(); ++i) {
                if (this.a.elementAt(i) == ax) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public final ax a(final String s, int i) {
        ax ax;
        for (i = 0; i < this.a.size(); ++i) {
            if ((ax = this.a.elementAt(i)).a[0].equals(s)) {
                return ax;
            }
        }
        return null;
    }
    
    final void b(final int n) {
        this.a = 0.0;
        for (int i = 0; i < this.u; ++i) {
            this.a += this.a[i];
        }
        double n2 = 0.0;
        for (int j = 0; j <= this.u; ++j) {
            this.a[j] = (int)(n * n2 / this.a);
            if (j < this.u) {
                n2 += this.a[j];
            }
        }
        this.k = true;
    }
    
    public final void a(final ei ei, int i, int n, final int n2, final int n3) {
        final int n4 = n - i;
        ei.a(this.b);
        ei.a(this.a.a());
        ei.b(i, n2, n4, n3 - n2);
        if (!this.k) {
            this.b(n);
        }
        ei.a(this.h);
        int n5;
        for (i = 1; i < this.u; ++i) {
            n = this.a[i];
            n5 = this.a[i + 1];
            ei.a(n, n2, n, n3);
            ei.a(n5, n2, n5, n3);
        }
        ax ax;
        Color color;
        int j;
        int n6;
        int k;
        u u;
        String a;
        int n7;
        int a2;
        int b;
        int l;
        u u2;
        int n8;
        int n9;
        int a3;
        int a4;
        Color color2;
        String substring;
        int n10;
        String substring2;
        int stringWidth;
        for (n = (i = n2 / this.t) * this.t; i < this.a.size() && i * this.t < n3; ++i, n += this.t) {
            if ((color = (ax = this.a.elementAt(i)).a) == null) {
                color = this.b();
            }
            if (ax == this.a) {
                ei.a(color);
                ei.b(0, n, n4, this.t);
                color = this.a.a();
            }
            for (j = 0; j < this.u; ++j) {
                n6 = 0;
                for (k = 0; k < 4; ++k) {
                    if ((u = ax.a[j][k]) != null) {
                        n6 += u.a();
                    }
                }
                a = fg.a(new StringBuffer(ax.a[j]), this.a, (int)(n4 * this.a[j] / this.a) - n6);
                n7 = (this.a[j] ? (this.a[j + 1] - 2 - this.a.stringWidth(a) - n6) : (this.a[j] + 2));
                a2 = ei.a;
                b = ei.b;
                for (l = 0; l < 4; ++l) {
                    if ((u2 = ax.a[j][l]) != null) {
                        ei.a = a2 + n7;
                        ei.b = b + n + (this.t - u2.b()) / 2;
                        u2.a(ei);
                        n7 += u2.a();
                    }
                }
                ei.a = a2;
                ei.b = b;
                ei.a(color);
                if (ax.a.size() == 0) {
                    ei.a(a, n7, n + this.t - this.a.getDescent());
                }
                else {
                    n8 = 0;
                    for (n9 = 0; n9 < ax.a.size(); ++n9) {
                        a3 = ax.a.a(n9);
                        a4 = ax.b.a(n9);
                        color2 = ax.a.elementAt(n9);
                        substring = a.substring(n8, a3);
                        ei.a(substring, n7, n + this.t - this.a.getDescent());
                        n10 = n7 + this.a.stringWidth(substring);
                        substring2 = a.substring(a3, a4);
                        ei.a(color2);
                        stringWidth = this.a.stringWidth(substring2);
                        ei.b(n10, n, stringWidth, this.t);
                        ei.a(Color.white);
                        ei.a(substring2, n10, n + this.t - this.a.getDescent());
                        n7 = n10 + stringWidth;
                        n8 = a4;
                    }
                    ei.a(color);
                    ei.a(a.substring(n8), n7, n + this.t - this.a.getDescent());
                }
            }
        }
    }
    
    public final void b() {
        this.a = this.a(this.b);
        this.t = this.a.getHeight();
        this.a.b(this.t);
        this.a.m();
        this.a.c(0, this.a.size() * this.t);
        for (int i = 0; i < this.a.size(); ++i) {
            final ax ax = this.a.elementAt(i);
            for (int j = 0; j < this.u; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (ax.a[j][k] != null) {
                        ax.a[j][k].b();
                    }
                }
            }
        }
        super.b();
    }
}
