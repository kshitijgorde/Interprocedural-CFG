// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Cursor;
import java.awt.Point;

public class t extends bm
{
    ae[] bL;
    boolean[] bK;
    Point[] bH;
    Cursor[] bJ;
    static final char[] bI;
    static final char[] bM;
    
    static {
        bI = new char[] { 'a', 'd', 'd', '\0' };
        bM = new char[] { 'r', 'e', 'm', 'o', 'v', 'e', '\0' };
    }
    
    public t() {
        this.bL = new ae[4];
        this.bK = new boolean[4];
        this.bH = new Point[4];
        this.bJ = new Cursor[4];
    }
    
    public void if() {
        super.if();
        for (int i = 0; i < 4; ++i) {
            if (this.bL != null) {
                this.bL[i].a();
                this.bL[i] = null;
            }
            if (this.bH != null) {
                this.bH[i] = null;
            }
            if (this.bJ != null) {
                this.bJ[i] = null;
            }
        }
        this.bL = null;
        this.bK = null;
        this.bH = null;
        this.bJ = null;
    }
    
    public void a(final ac b, a2 a2, final ae long1, final v br) {
        super.b = b;
        for (int i = 0; i < 4; ++i) {
            this.bK[i] = true;
            this.bJ[i] = null;
        }
        super.byte = true;
        super.f = "cursor\u0000".toCharArray();
        super.br = br;
        super.long = long1;
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
            this.bJ[n] = null;
            return;
        }
        this.bL[n] = super.b.A.a(s, super.long, false, false, false);
        this.bK[n] = false;
        this.bH[n] = new Point(a3, a4);
        super.b.as = -1;
    }
    
    public boolean a(final long n) {
        this.d();
        for (int i = 0; i < 4; ++i) {
            if (!this.bK[i]) {
                if (this.bL[i].k != 0) {
                    this.bL[i] = this.bL[i].try[0];
                }
                if (this.bL[i].b) {
                    this.bK[i] = true;
                    this.bJ[i] = null;
                    if (this.bL[i].l != null && this.bL[i].l.length != 0) {
                        try {
                            this.bJ[i] = super.b.H.createCustomCursor(super.b.H.createImage(this.bL[i].l), this.bH[i], new StringBuffer().append(i).toString());
                        }
                        catch (Exception ex) {
                            this.bJ[i] = null;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, t.bI) == 0 && a3.char == 4) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int, g.a(a3.int));
                this.for(bf.do);
            }
            catch (Exception ex) {}
        }
    }
}
