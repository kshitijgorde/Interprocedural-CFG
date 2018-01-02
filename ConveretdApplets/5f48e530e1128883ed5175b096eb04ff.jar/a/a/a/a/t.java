// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Cursor;
import java.awt.Point;

public class t extends bm
{
    ae[] bM;
    boolean[] bL;
    Point[] bI;
    Cursor[] bK;
    static final char[] bJ;
    static final char[] bN;
    
    static {
        bJ = new char[] { 'a', 'd', 'd', '\0' };
        bN = new char[] { 'r', 'e', 'm', 'o', 'v', 'e', '\0' };
    }
    
    public t() {
        this.bM = new ae[4];
        this.bL = new boolean[4];
        this.bI = new Point[4];
        this.bK = new Cursor[4];
    }
    
    public void if() {
        super.if();
        for (int i = 0; i < 4; ++i) {
            if (this.bM != null) {
                this.bM[i].a();
                this.bM[i] = null;
            }
            if (this.bI != null) {
                this.bI[i] = null;
            }
            if (this.bK != null) {
                this.bK[i] = null;
            }
        }
        this.bM = null;
        this.bL = null;
        this.bI = null;
        this.bK = null;
    }
    
    public void a(final ac b, a2 a2, final ae long1, final v bs) {
        super.b = b;
        for (int i = 0; i < 4; ++i) {
            this.bL[i] = true;
            this.bK[i] = null;
        }
        super.byte = true;
        super.f = "cursor\u0000".toCharArray();
        super.bs = bs;
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
            this.bK[n] = null;
            return;
        }
        this.bM[n] = super.b.B.a(s, super.long, false, false, false);
        this.bL[n] = false;
        this.bI[n] = new Point(a3, a4);
        super.b.au = -1;
    }
    
    public boolean a(final long n) {
        this.void();
        for (int i = 0; i < 4; ++i) {
            if (!this.bL[i]) {
                if (this.bM[i].k != 0) {
                    this.bM[i] = this.bM[i].try[0];
                }
                if (this.bM[i].b) {
                    this.bL[i] = true;
                    this.bK[i] = null;
                    if (this.bM[i].l != null && this.bM[i].l.length != 0) {
                        try {
                            this.bK[i] = super.b.I.createCustomCursor(super.b.I.createImage(this.bM[i].l), this.bI[i], new StringBuffer().append(i).toString());
                        }
                        catch (Exception ex) {
                            this.bK[i] = null;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, t.bJ) == 0 && a3.char == 4) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int, g.a(a3.int));
                this.for(bf.do);
            }
            catch (Exception ex) {}
        }
    }
}
