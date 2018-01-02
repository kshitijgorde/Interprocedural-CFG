// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Cursor;
import java.awt.Point;

public class ab extends b5
{
    aq[] bM;
    boolean[] bL;
    Point[] bI;
    Cursor[] bK;
    static final char[] bJ;
    static final char[] bN;
    
    static {
        bJ = new char[] { 'a', 'd', 'd', '\0' };
        bN = new char[] { 'r', 'e', 'm', 'o', 'v', 'e', '\0' };
    }
    
    public ab() {
        this.bM = new aq[4];
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
    
    public void a(final an b, bh bh, final aq long1, final ae bs) {
        super.b = b;
        for (int i = 0; i < 4; ++i) {
            this.bL[i] = true;
            this.bK[i] = null;
        }
        super.byte = true;
        super.f = "cursor\u0000".toCharArray();
        super.bs = bs;
        super.long = long1;
        for (bh = bh.if; bh != null; bh = bh.for) {
            this.for(bh);
        }
    }
    
    public void for(final bh bh) {
        int a = 0;
        int a2 = 0;
        String s = "";
        int n = -1;
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("file") == 0) {
                s = bh.new[i];
            }
            else if (bh.try[i].toLowerCase().compareTo("x") == 0) {
                a = an.a(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("y") == 0) {
                a2 = an.a(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("type") == 0) {
                if (bh.new[i].toLowerCase().compareTo("default") == 0) {
                    n = 0;
                }
                else if (bh.new[i].toLowerCase().compareTo("over") == 0) {
                    n = 1;
                }
                else if (bh.new[i].toLowerCase().compareTo("move") == 0) {
                    n = 2;
                }
                else if (bh.new[i].toLowerCase().compareTo("zoom") == 0) {
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
        this.bI[n] = new Point(a, a2);
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
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, ab.bJ) == 0 && bi.char == 4) {
            final bw bw = new bw();
            try {
                aj.a(bw, bi.int, i.a(bi.int));
                this.for(bw.do);
            }
            catch (Exception ex) {}
        }
    }
}
