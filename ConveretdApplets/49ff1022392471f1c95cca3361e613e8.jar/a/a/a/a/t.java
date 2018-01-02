// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Cursor;
import java.awt.Point;

public class t extends bm
{
    ae[] bz;
    boolean[] a3;
    Point[] bw;
    Cursor[] by;
    static char[] bx;
    static char[] bA;
    
    static {
        t.bx = new char[] { 'a', 'd', 'd', '\0' };
        t.bA = new char[] { 'r', 'e', 'm', 'o', 'v', 'e', '\0' };
    }
    
    public t() {
        this.bz = new ae[4];
        this.a3 = new boolean[4];
        this.bw = new Point[4];
        this.by = new Cursor[4];
    }
    
    public void a(final ac void1, a2 a2, final ae goto1, final v bh) {
        super.void = void1;
        for (int i = 0; i < 4; ++i) {
            this.a3[i] = true;
            this.by[i] = null;
        }
        super.try = true;
        super.e = "cursor\u0000".toCharArray();
        super.bh = bh;
        super.goto = goto1;
        for (a2 = a2.if; a2 != null; a2 = a2.for) {
            this.for(a2);
        }
    }
    
    public void for(final a2 a2) {
        int a3 = 0;
        int a4 = 0;
        String s = "";
        int n = -1;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                s = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("x") == 0) {
                a3 = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("y") == 0) {
                a4 = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("type") == 0) {
                if (a2.new[i].toLowerCase().compareTo("default") == 0) {
                    n = 0;
                }
                else if (a2.new[i].toLowerCase().compareTo("over") == 0) {
                    n = 1;
                }
                else if (a2.new[i].toLowerCase().compareTo("move") == 0) {
                    n = 2;
                }
                else if (a2.new[i].toLowerCase().compareTo("zoom") == 0) {
                    n = 3;
                }
            }
        }
        if (n == -1) {
            return;
        }
        if (s.length() == 0) {
            this.by[n] = null;
            return;
        }
        this.bz[n] = super.void.w.a(s, super.goto, false, false, false);
        this.a3[n] = false;
        this.bw[n] = new Point(a3, a4);
        super.void.al = -1;
    }
    
    public boolean a(final long n) {
        for (int i = 0; i < 4; ++i) {
            if (!this.a3[i]) {
                if (this.bz[i].j != 0) {
                    this.bz[i] = this.bz[i].new[0];
                }
                if (this.bz[i].b) {
                    this.a3[i] = true;
                    this.by[i] = null;
                    if (this.bz[i].k != null && this.bz[i].k.length != 0) {
                        try {
                            this.by[i] = super.void.C.createCustomCursor(super.void.C.createImage(this.bz[i].k), this.bw[i], new StringBuffer().append(i).toString());
                        }
                        catch (Exception ex) {
                            this.by[i] = null;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, t.bx) == 0 && a3.char == 4) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int);
                this.for(bf.do);
            }
            catch (Exception ex) {}
        }
    }
}
