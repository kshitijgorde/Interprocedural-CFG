// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Image;

public class aZ extends ai implements a
{
    public b a;
    public Y a;
    public int g;
    public Image b;
    private int h;
    
    public Object a(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.b);
        }
        if (this.a != null && "stars".equals(s)) {
            return this.a.a;
        }
        if ("flag".equals(s) && super.b != null) {
            return cx.a(super.b);
        }
        if (this.a != null && "icon".equals(s)) {
            return this.a.a;
        }
        if (this.b != null && "sImage".equals(s)) {
            return this.b;
        }
        return super.a(s);
    }
    
    public final void a() {
        if (this.a(62)) {
            this.h = 6;
            return;
        }
        if (this.a(52)) {
            this.h = 5;
            return;
        }
        if (this.a(5) && this.a(6)) {
            this.h = 4;
            return;
        }
        if (this.a(24)) {
            this.h = 3;
            return;
        }
        if (this.a(59)) {
            this.h = 2;
            return;
        }
        if (this.a(61)) {
            this.h = 1;
            return;
        }
        if (this.a(119)) {
            this.h = -1;
            return;
        }
        this.h = 0;
    }
    
    public final int a(final a a, final String s) {
        if ("name".equals(s)) {
            final int n = (this.a(41) && super.e != 0) ? super.e : 0;
            final int n2 = (((U)a).a(41) && ((aZ)a).e != 0) ? ((aZ)a).e : 0;
            if (n == n2) {
                if (n != 0 || ((aZ)a).h == this.h || (this.h != -1 && ((aZ)a).h != -1)) {
                    return super.d.toLowerCase().compareTo(((U)a).d.toLowerCase());
                }
                if (((aZ)a).h > this.h) {
                    return -1;
                }
                return 1;
            }
            else {
                if (n == 0) {
                    return 1;
                }
                if (n2 == 0) {
                    return -1;
                }
                if (n < n2) {
                    return -1;
                }
                return 1;
            }
        }
        else {
            if ("rp".equals(s)) {
                return ((aZ)a).g - this.g;
            }
            if (!"name2".equals(s)) {
                return super.a(a, s);
            }
            if (((aZ)a).h == this.h) {
                return super.d.toLowerCase().compareTo(((U)a).d.toLowerCase());
            }
            if (this.h == -1 && ((aZ)a).h == 0) {
                return 1;
            }
            if (((aZ)a).h < this.h) {
                return -1;
            }
            return 1;
        }
    }
    
    public String a() {
        return bm.a(aS.a(73), new String[] { super.d });
    }
    
    public aZ(final int n, final String s) {
        super(n, s);
        this.g = 0;
    }
}
