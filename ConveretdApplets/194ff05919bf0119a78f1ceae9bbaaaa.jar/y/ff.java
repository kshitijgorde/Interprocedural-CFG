// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;
import java.awt.FontMetrics;

public final class ff extends u
{
    private FontMetrics a;
    private char[] a;
    private int a;
    private int b;
    private int t;
    private int u;
    private int v;
    private boolean b;
    boolean a;
    
    public ff() {
        this(0);
    }
    
    public ff(final int n) {
        this("", n);
    }
    
    private ff(final String s, final int v) {
        this.a = new char[1024];
        this.a = 0;
        this.b = -1;
        this.t = 0;
        this.u = 0;
        this.a = true;
        this.b(Color.white);
        this.a(Color.black);
        this.v = v;
        this.a(s);
    }
    
    public final void b() {
        super.b();
        this.a = this.a(y.u.a);
    }
    
    public final void a(final String s) {
        final int min = Math.min(this.a.length, s.length());
        this.t = min;
        this.a = min;
        s.getChars(this.u = 0, this.a, this.a, 0);
        this.h();
    }
    
    private void a() {
        if (this.a > this.t) {
            this.a = this.t;
        }
        if (this.u > this.t) {
            this.u = this.t;
        }
        while (this.a > this.u && this.a.charsWidth(this.a, this.u, this.a - this.u) > super.e - 6) {
            ++this.u;
        }
        while (this.u > 0 && this.a.charsWidth(this.a, this.u - 1, this.t - this.u + 1) < super.e - 6) {
            --this.u;
        }
        if (this.a < this.u) {
            this.u = this.a;
        }
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(y.u.a);
        this.a();
        if (this.a) {
            ei.a(Color.white);
            ei.c(0, 0, super.e - 1, super.f - 1);
            ei.a(Color.black);
            ei.a(1, 1, super.e - 2, 1);
            ei.a(1, 1, 1, super.f - 2);
            ei.a(Color.lightGray);
            ei.a(super.e - 2, 1, super.e - 2, super.f - 2);
            ei.a(1, super.f - 2, super.e - 2, super.f - 2);
        }
        final int n = 3 + this.a.getAscent();
        ei.a(this.b());
        ei.a(this.a, this.u, this.t - this.u, 3, n, super.e - 6, this.a);
        ei.a(y.u.a);
        final int charsWidth = this.a.charsWidth(this.a, this.u, this.a - this.u);
        if (this.b >= 0) {
            final int n2 = (this.b < this.u) ? 0 : this.a.charsWidth(this.a, this.u, this.b - this.u);
            int n3;
            int n4;
            if (this.a < this.b) {
                n3 = this.a;
                n4 = charsWidth;
            }
            else {
                n3 = this.b;
                n4 = n2;
            }
            final int min = Math.min(Math.abs(charsWidth - n2), super.e - 6 - n4);
            ei.b(n4 + 3, 3, min, this.a.getHeight());
            ei.a(Color.white);
            ei.a(this.a, n3, this.t - n3, n4 + 3, n, min, this.a);
        }
        if (this.a && this.b) {
            ei.a(this.b());
            final int n5 = charsWidth + 3;
            final int n6 = n;
            final FontMetrics a = this.a;
            final int n7 = n6;
            final int n8 = n5;
            final int n9 = n7 - a.getAscent();
            final int n10 = n7 + a.getDescent();
            ei.a(n8, n10, n8, n9);
            ei.a(n8 - 2, n10, n8 + 2, n10);
            ei.a(n8 - 2, n9, n8 + 2, n9);
        }
    }
    
    private void a(final int n) {
        int charsWidth;
        int a;
        for (charsWidth = this.a.charsWidth(this.a, 0, this.u), a = 0; a < this.t && this.a.charsWidth(this.a, 0, a) <= n + charsWidth - 3; ++a) {}
        this.a = a;
        this.h();
    }
    
    public final String a() {
        return new String(this.a, 0, this.t);
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        if (this.a) {
            this.a(this, false);
            this.a(n);
            this.b = this.a;
        }
        return true;
    }
    
    public final boolean c(final Event event, final int n, final int n2) {
        this.a(n);
        return true;
    }
    
    public final boolean b(final Event event, final int n, final int n2) {
        this.a(n);
        return true;
    }
    
    private void l() {
        if (this.b >= 0) {
            int n;
            int n2;
            if (this.a < this.b) {
                n = this.a;
                n2 = this.b;
            }
            else {
                n = this.b;
                n2 = this.a;
            }
            System.arraycopy(this.a, n2, this.a, n, this.t - n2);
            this.t -= n2 - n;
            this.b = -1;
            this.a();
        }
    }
    
    public final boolean a(final Event event) {
        Event event2 = null;
        if (this.a) {
            if (event.id == 401) {
                this.l();
                if (event.key == 0) {
                    return true;
                }
                if (event.key == 8) {
                    if (this.a > 0) {
                        System.arraycopy(this.a, this.a, this.a, this.a - 1, this.t - this.a);
                        --this.a;
                        --this.t;
                    }
                }
                else if (event.key == 10) {
                    event2 = new Event(this, 1001, this.a());
                }
                else if (event.key == 1) {
                    this.a = 0;
                }
                else if (event.key == 4 || event.key == 127) {
                    if (this.a < this.t) {
                        System.arraycopy(this.a, this.a + 1, this.a, this.a, this.t - this.a + 1);
                        --this.t;
                    }
                }
                else if (event.key == 5) {
                    this.a = this.t;
                }
                else if (Character.isDefined((char)event.key) && this.t < this.a.length) {
                    System.arraycopy(this.a, this.a, this.a, this.a + 1, this.t - this.a);
                    this.a[this.a] = (char)event.key;
                    ++this.a;
                    ++this.t;
                }
            }
            else if (event.id == 403) {
                this.l();
                if (event.key == 1006) {
                    if (this.a > 0) {
                        --this.a;
                    }
                }
                else if (event.key == 1007) {
                    if (this.a < this.t) {
                        ++this.a;
                    }
                }
                else if (event.key == 1000) {
                    this.a = 0;
                }
                else if (event.key == 1001) {
                    this.a = this.t;
                }
            }
            else if (event.id == 1005) {
                this.b = false;
            }
            else {
                if (event.id != 1004) {
                    return super.a(event);
                }
                this.b = true;
            }
            this.h();
        }
        if (event2 != null) {
            this.a(event2);
            return true;
        }
        return super.a(event);
    }
    
    public final int a() {
        return this.v + 6;
    }
    
    public final int b() {
        return this.a.getHeight() + 6;
    }
}
