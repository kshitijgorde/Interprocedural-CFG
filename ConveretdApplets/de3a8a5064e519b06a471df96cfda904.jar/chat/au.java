// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Image;

public class au extends aj implements a
{
    public b a;
    public F a;
    public int i;
    public Image b;
    private int j;
    
    public Object a(final String s) {
        if ("room".equals(s)) {
            return new Integer(super.b);
        }
        if (this.a != null && "stars".equals(s)) {
            return this.a.a;
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
            this.j = 6;
            return;
        }
        if (this.a(52)) {
            this.j = 5;
            return;
        }
        if (this.a(5) && this.a(6)) {
            this.j = 4;
            return;
        }
        if (this.a(24)) {
            this.j = 3;
            return;
        }
        if (this.a(59)) {
            this.j = 2;
            return;
        }
        if (this.a(61)) {
            this.j = 1;
            return;
        }
        if (this.a(119)) {
            this.j = -1;
            return;
        }
        this.j = 0;
    }
    
    public final int a(final a a, final String s) {
        if ("name".equals(s)) {
            final int n = (this.a(41) && super.e != 0) ? super.e : 0;
            final int n2 = (((an)a).a(41) && ((au)a).e != 0) ? ((au)a).e : 0;
            if (n == n2) {
                if (n != 0 || ((au)a).j == this.j || (this.j != -1 && ((au)a).j != -1)) {
                    return super.c.toLowerCase().compareTo(((an)a).c.toLowerCase());
                }
                if (((au)a).j > this.j) {
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
                return ((au)a).i - this.i;
            }
            if (!"name2".equals(s)) {
                return super.a(a, s);
            }
            if (((au)a).j == this.j) {
                return super.c.toLowerCase().compareTo(((an)a).c.toLowerCase());
            }
            if (this.j == -1 && ((au)a).j == 0) {
                return 1;
            }
            if (((au)a).j < this.j) {
                return -1;
            }
            return 1;
        }
    }
    
    public String a() {
        return ak.a(ak.a(73), new String[] { super.c });
    }
    
    public au(final int n, final String s) {
        super(n, s);
        this.i = 0;
    }
}
