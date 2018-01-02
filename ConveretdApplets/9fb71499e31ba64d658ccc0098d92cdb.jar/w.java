import java.awt.Rectangle;
import java.util.Vector;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class w extends l
{
    public static final int[] a;
    public static final int[] b;
    public static final int[] c;
    public static final int[] d;
    public static final int[] e;
    public static final int[] f;
    public static final int[] g;
    public static final double[] h;
    public static final double[] i;
    public WordFallApplet j;
    public ad[][] k;
    public Vector l;
    public Vector m;
    public Vector n;
    public double o;
    public x p;
    public ac q;
    public ac r;
    public aq s;
    public aq t;
    public f u;
    public double v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int aa;
    public double ab;
    public boolean ac;
    public int ad;
    public int ae;
    public int af;
    public int ag;
    public int ah;
    public boolean ai;
    public int aj;
    public int ak;
    public double al;
    public double am;
    public int an;
    public int ao;
    public boolean ap;
    public Vector aq;
    public int ar;
    public int as;
    public double at;
    public int[] au;
    public int av;
    public int aw;
    public boolean ax;
    public double ay;
    public boolean az;
    public int a0;
    public boolean a1;
    public int a2;
    public ad a3;
    public int a4;
    public int a5;
    public double a6;
    public boolean a7;
    public int a8;
    public int a9;
    public int ba;
    public int bb;
    public ad bc;
    public double bd;
    public int be;
    public String bf;
    public static final String[] bg;
    
    public ad f(final int n, final int n2) {
        int n3 = 0;
        do {
            int n4 = 0;
            do {
                final ad ad = this.k[n3][n4];
                if (ad != null && new Rectangle((int)ad.c, (int)ad.d, 40, 40).contains(n, n2)) {
                    return ad;
                }
            } while (++n4 < 7);
        } while (++n3 < 8);
        return null;
    }
    
    public void k() {
        this.j.d("GameStats");
        this.j.a("Level", this.aj);
        this.j.b("BestWordString", this.q.l);
        this.j.a("BestWordPoints", this.q.m);
        this.j.b("LongestWordString", this.q.n);
        this.j.a("LongestWordPoints", this.q.o);
        this.j.a("ScrambleCount", this.q.j);
        this.j.a("BonusWordCount", this.q.k);
        this.j.a("FireTileCount", this.q.f);
        this.j.a("BonusTileCount", this.q.g);
        this.j.a("SuperTileCount", this.q.h);
        this.j.a("LostTileCount", this.q.i);
        this.j.a("WordCount", this.q.c);
        this.j.s();
        this.w();
        this.al = 0.0;
        this.z = 5;
    }
    
    public void b() {
        if (this.j.f.am == null && this.j.f.ab.j()) {
            this.j.f.am = this.j.f.b(this.j.f.ab);
        }
        if (this.j.f.an == null && this.j.f.ac.j()) {
            this.j.f.an = this.j.f.b(this.j.f.ac);
        }
        if (this.j.f.ao == null && this.j.f.ad.j()) {
            this.j.f.ao = this.j.f.b(this.j.f.ad);
        }
        if (this.j.f.ap == null && this.j.f.ae.j()) {
            this.j.f.ap = this.j.f.ae;
        }
        if (this.ao > 0) {
            return;
        }
        super.b();
        if (this.z == 0 && this.bf == null && (this.aj > 1 || (this.aj == 1 && this.r.c >= 1)) && !this.j.o()) {
            this.bf = this.j.j();
            if (this.bf != null) {
                this.f(true);
            }
        }
        boolean b = false;
        if (this.z == 4 && this.ar == 0) {
            this.j.i();
            this.z = 0;
        }
        else if (this.z == 0 || this.z == 2 || this.z == 3 || this.z == 5) {
            boolean b2 = false;
            int n = 0;
            boolean b3 = false;
            if (this.j.au > 0 && this.s != null && this.s.j && this.aa != 2 && super.q % 16 == 0) {
                if (this.j.t.d == this.j.f.at) {
                    this.j.t.d = this.j.f.as;
                }
                else {
                    this.j.t.d = this.j.f.at;
                }
                this.j();
            }
            int n2 = 0;
            do {
                int h = this.h(n2, 7);
                int n3 = 7;
                do {
                    final ad bc = this.k[n3][n2];
                    if (bc != null) {
                        if (bc.c() && n3 < 7) {
                            final ad ad = this.k[n3 + 1][n2];
                            if (ad != null && ad.a()) {
                                ad.p = true;
                                ad.q = 2.5 / Math.max(ad.u, 1);
                            }
                        }
                        else if (bc.a() && bc.p) {
                            final ad ad2 = this.k[n3 - 1][n2];
                            if (ad2 != null && !ad2.c()) {
                                bc.p = false;
                                bc.q = 0.0;
                                bc.c = this.a(bc.a);
                                bc.d = this.h(bc.a, bc.b);
                            }
                        }
                        if (bc.r) {
                            final ad ad3 = bc;
                            ad3.s += 0.01;
                            if (bc.s >= 1.0) {
                                bc.s = 0.0;
                                bc.r = false;
                            }
                            this.j();
                        }
                        if (bc.n && super.q % 4 == 0) {
                            bc.o = (bc.o + 1) % 8;
                            this.j();
                        }
                        if (n3 == 7 && bc.c()) {
                            if (this.z == 3) {
                                int n4 = 0;
                                do {
                                    final aj aj = new aj();
                                    aj.a = bc.c + this.p.a() % 40;
                                    aj.b = bc.d + 40.0;
                                    aj.c = (this.p.a() % 1000 - 500) / 300.0;
                                    aj.d = (this.p.a() % 500 - 500) / 300.0;
                                    aj.f = 0.2;
                                    aj.g = 0.002 + this.p.a() % 1000 / 1000.0 * 0.002;
                                    aj.i = true;
                                    aj.k = 0;
                                    this.aq.addElement(aj);
                                } while (++n4 < 2);
                                this.bc = bc;
                                final ad ad4 = bc;
                                ad4.q += 0.075;
                            }
                            else if (this.z == 0) {
                                if (this.p.a() % 8 == 0) {
                                    final aj aj2 = new aj();
                                    aj2.a = bc.c + this.p.a() % 40;
                                    aj2.b = bc.d + 40.0 - 3.0;
                                    aj2.c = (this.p.a() % 1000 - 500) / 800.0;
                                    aj2.d = (this.p.a() % 1000 - 1000) / 1500.0;
                                    aj2.f = 0.2;
                                    aj2.g = 0.002 + this.p.a() % 1000 / 1000.0 * 0.002;
                                    this.aq.addElement(aj2);
                                }
                                if (this.p.a() % 16 == 0) {
                                    final aj aj3 = new aj();
                                    aj3.a = bc.c + this.p.a() % 40;
                                    aj3.b = bc.d + 40.0 - 3.0;
                                    aj3.k = 3;
                                    aj3.c = 0.0;
                                    aj3.d = -0.25;
                                    aj3.e = 0.0;
                                    aj3.f = this.p.a() % 1000 / 1000.0;
                                    aj3.g = 0.004 + this.p.a() % 1000 / 1000.0 * 0.01;
                                    this.aq.addElement(aj3);
                                }
                            }
                            if (!bc.p) {
                                bc.p = true;
                                bc.q = 1.5;
                            }
                            b3 = true;
                        }
                        if (!bc.f() && super.q % 16 == 0) {
                            this.j();
                        }
                        if (bc.d < h) {
                            final ad ad5 = bc;
                            ad5.f += 0.2;
                            final ad ad6 = bc;
                            ad6.d += bc.f;
                            this.j();
                            b = true;
                        }
                        else {
                            if (bc.p && super.q % 4 == 0) {
                                bc.c = this.a(bc.a) + (this.p.a() % 1000 - 500) / 500.0 * bc.q;
                                bc.d = this.h(bc.a, bc.b) + (this.p.a() % 1000 - 500) / 500.0 * bc.q;
                                this.j();
                            }
                            if (bc.k) {
                                if (bc.m > 0) {
                                    final ad ad7 = bc;
                                    --ad7.m;
                                }
                                else {
                                    if (bc.l == 0 && n == 0) {
                                        this.j.c(18);
                                        n = 1;
                                    }
                                    final ad ad8 = bc;
                                    ++ad8.l;
                                }
                                if (bc.l >= 40) {
                                    this.a(bc);
                                    b2 = true;
                                }
                                int n5 = 0;
                                do {
                                    final aj aj4 = new aj();
                                    final int n6 = this.p.a() % 40;
                                    aj4.a = bc.c + n6;
                                    aj4.b = bc.d + bc.l;
                                    aj4.c = (n6 - 20) / 40.0 * 4.0;
                                    aj4.d = (this.p.a() % 1000 - 300) / 1000.0;
                                    aj4.f = 1.0;
                                    aj4.g = 0.02 + this.p.a() % 1000 / 1000.0 * 0.05;
                                    this.aq.addElement(aj4);
                                } while (++n5 < 4);
                            }
                        }
                        if (bc.d > h) {
                            if (bc.f > 4.0 && super.q - this.af >= 4) {
                                this.j.c(5);
                                this.af = super.q;
                            }
                            bc.f = 0.0;
                            bc.d = h;
                            this.j();
                            b = true;
                        }
                        if (bc.d == h) {
                            bc.f = 0.0;
                        }
                        h -= 40 - bc.l;
                    }
                } while (--n3 >= 0);
            } while (++n2 < 7);
            if (!b) {
                if (this.ag > 0 && !this.j.af && this.ar == 0) {
                    this.ad = 1;
                    this.ar = 80;
                    this.as = 1;
                    this.j.af = true;
                }
                int n7 = 0;
                do {
                    final ad ad9 = this.k[7][n7];
                    if (ad9 != null && ad9.c() && !this.j.ag) {
                        this.j.ag = true;
                        this.j.b("Fire Alert!", "That burning tile is too close!\n\nUse it this turn or your\nlibrary will catch fire!", "Click here to continue", 2);
                    }
                } while (++n7 < 7);
            }
            if (b2) {
                this.o();
            }
            if (b3) {
                this.a1 = true;
                if (super.q % 8 == 0) {
                    this.a2 = (this.a2 + 1) % 8;
                }
            }
            else {
                this.a1 = false;
                this.a2 = 0;
            }
        }
        if (this.z == 5 && this.n.size() == 0) {
            if (this.j.m == null) {
                this.j.i();
            }
            if (super.d > 0) {
                this.j();
            }
            else {
                this.b(false);
            }
        }
        for (int i = 0; i < this.m.size(); ++i) {
            final ap ap = this.m.elementAt(i);
            if (super.q % 4 == 0) {
                if (ap.c > 8) {
                    final ap ap2 = ap;
                    --ap2.c;
                }
                else if (ap.d > 75) {
                    ap.d = 75;
                }
                this.j();
            }
            if (--ap.d <= 0) {
                this.m.removeElementAt(i);
                --i;
                this.j();
            }
        }
        if (this.n.size() > 0) {
            final Vector vector = new Vector<ad>();
            for (int j = 0; j < this.n.size(); ++j) {
                int n8;
                int n9;
                if (j == 0) {
                    n8 = 36;
                    n9 = 178;
                }
                else {
                    n8 = (int)this.n.elementAt(j - 1).c;
                    n9 = (int)this.n.elementAt(j - 1).d;
                }
                final ad ad10 = this.n.elementAt(j);
                if (ad10 != null) {
                    final ad ad11 = ad10;
                    ad11.j *= 0.99;
                    final int n10 = (int)(n8 + 40.0 * ad10.j);
                    final int n11 = (int)(n9 + 40.0 * ad10.j);
                    if (ad10.c != n10) {
                        final ad ad12 = ad10;
                        ad12.c += (int)(n10 - ad10.c) * ad10.e;
                        final ad ad13 = ad10;
                        ad13.e += 0.003;
                        this.j();
                    }
                    if (ad10.d != n11) {
                        final ad ad14 = ad10;
                        ad14.d += (int)(n11 - ad10.d) * ad10.e;
                        final ad ad15 = ad10;
                        ad15.f += 0.003;
                        this.j();
                    }
                    if (Math.abs(ad10.d - n11) < 2.0 && Math.abs(ad10.c - n10) < 2.0) {
                        if (ad10.c()) {
                            this.aw = 50;
                        }
                        ad10.e = 0.0;
                        ad10.f = 0.0;
                        vector.addElement(ad10);
                        if (super.q - this.af >= 4) {
                            this.j.c(6);
                            this.af = super.q;
                        }
                    }
                    if (ad10.c() && super.q % 1 == 0) {
                        final aj aj5 = new aj();
                        aj5.a = ad10.c + this.p.a() % 40;
                        aj5.b = ad10.d + this.p.a() % 40;
                        aj5.c = (this.p.a() % 1000 - 500) / 800.0;
                        aj5.d = (this.p.a() % 1000 - 1000) / 1500.0;
                        aj5.f = 0.2;
                        aj5.g = 0.002 + this.p.a() % 1000 / 1000.0 * 0.002;
                        this.aq.addElement(aj5);
                    }
                }
            }
            if (vector.size() > 0) {
                for (int k = 0; k < vector.size(); ++k) {
                    final ad ad16 = vector.elementAt(k);
                    if (ad16 != null) {
                        this.n.removeElement(ad16);
                    }
                }
            }
        }
        else if (this.aw > 0 && --this.aw == 0) {
            this.ax = true;
            this.ay = 1.0;
            if (this.az) {
                this.j.c(26);
                this.az = false;
            }
        }
        if (this.ay > 0.0) {
            this.ay -= 0.005;
            if (this.ay < 0.0) {
                this.ax = false;
            }
            this.j();
        }
        if (this.ad > 0 && this.z == 0) {
            if (this.ae > 0) {
                --this.ae;
            }
            if (this.ae == 0) {
                --this.ad;
                this.j.c(8);
                if (this.ad > 0) {
                    this.ae = 64;
                }
            }
        }
        if (this.w < this.x) {
            if (this.y > 0) {
                --this.y;
            }
            if (this.y == 0) {
                this.j.c(9 + Math.min(this.w, 7));
                ++this.w;
                this.y = 10;
            }
        }
        if (this.am != this.al && super.q % 2 == 0 && this.n.size() <= 3) {
            this.j();
            final double n12 = this.al - this.am;
            if (n12 > 0.0) {
                this.am += 0.01 + n12 / 25.0;
            }
            else if (n12 < 0.0) {
                this.am -= 0.05;
                if (this.am < this.al) {
                    this.am = this.al;
                }
            }
            if (this.al > 0.0 && this.am > this.al) {
                this.am = this.al;
            }
        }
        if (this.ak != this.q.b && super.q % 2 == 0) {
            this.j();
            final double n13 = (this.q.b - this.ak) / this.o;
            if (this.ak < this.q.b) {
                this.ak += (int)((4.0 + n13 / 10.0) * this.o);
            }
            if (this.ak > this.q.b) {
                this.ak = this.q.b;
            }
        }
        if (this.z == 2 && this.n.size() == 0) {
            this.z = 4;
        }
        if (this.l.size() > 0) {
            this.t = this.s;
            this.v = 1.0;
        }
        else if (this.v > 0.0 && super.q % 8 == 0) {
            this.v -= 0.08;
            this.j();
        }
        if (this.ar > 0 && this.aa != 2 && --this.ar == 0) {
            switch (this.as) {
                case 0: {
                    this.j.b("How to Play", "Link letters together to\n make words. Longer words\nare worth more points!", "Click here to continue", 2);
                    break;
                }
                case 1: {
                    this.j.b("Watch out!", "Don't let burning tiles reach\n the bottom, they will set\nthe library on fire!\n\nHINT: Make longer words and\nyou get fewer red tiles.", "Click here to continue", 2);
                    break;
                }
                case 2: {
                    this.j.b("Bonus Tile!", "Use that green tile in a word\nfor extra bonus points.", "Click here to continue", 2);
                    break;
                }
                case 3: {
                    this.j.b("Super Bonus Tile!", "Gold tiles are worth\nmega points!\n\nCreate words with 5+ letters\nfor a chance to earn more\ngold tiles!", "Click here to continue", 2);
                    break;
                }
                case 4: {
                    this.j.b("Scramble Warning!", "Scrambling letters causes\nburning tiles to appear,\nso do it sparingly.", "Click here to continue", 2);
                    break;
                }
                case 5: {
                    this.j.b("Submit Button", "You may also submit\nselected words by clicking on\nthe SUBMIT button.", "Click here to continue", 2);
                    break;
                }
                case 6: {
                    this.j.b("Submit by Clicking", "You may also submit\nselected words by clicking on\nthe last tile in the word.", "Click here to continue", 2);
                    break;
                }
                case 7: {
                    this.j.b("Submit by Dragging", "You may also submit words by\nclicking the left mouse button\nand dragging to select tiles.\n\nRelease the mouse button to\nsubmit the word.", "Click here to continue", 2);
                    break;
                }
                case 8: {
                    this.j.b("Submit by Keyboard", "You may also submit\nselected words by pressing the\nENTER key on the keyboard.", "Click here to continue", 2);
                    break;
                }
                case 9: {
                    this.j.b("Bonus Word!", "For even more points,\nsubmit a word that matches\nthe Bonus Word.", "Click here to continue", 2);
                    break;
                }
                case 10: {
                    this.j.b("How to Deselect", "You may press the right\nmouse button to deselect\nany highlighted tiles.", "Click here to continue", 2);
                    break;
                }
            }
        }
        if (this.z == 1) {
            this.at += 0.01;
            if (this.at >= 1.0) {
                int n14 = 0;
                do {
                    int n15 = 0;
                    do {
                        final ad ad17 = this.k[n15][n14];
                        if (ad17 != null) {
                            ad17.g = ad17.h;
                        }
                    } while (++n15 < 8);
                } while (++n14 < 7);
                this.at = 0.0;
                this.z = 0;
            }
            this.j();
        }
        if (this.aq.size() > 0) {
            for (int l = 0; l < this.aq.size(); ++l) {
                final aj aj6 = this.aq.elementAt(l);
                if (aj6.h > 0) {
                    final aj aj7 = aj6;
                    --aj7.h;
                }
                else {
                    if (aj6.k == 0 && !aj6.i) {
                        aj6.j = (int)Math.min(aj6.f * 7.0, 5.0);
                    }
                    if (aj6.k != 0 && super.q % 4 == 0) {
                        aj6.j = (aj6.j + 1) % 7;
                    }
                    final aj aj8 = aj6;
                    aj8.a += aj6.c;
                    final aj aj9 = aj6;
                    aj9.d += aj6.e;
                    final aj aj10 = aj6;
                    aj10.b += aj6.d;
                    final aj aj11 = aj6;
                    aj11.f -= aj6.g;
                    if (aj6.f < 0.0) {
                        this.aq.removeElementAt(l);
                        --l;
                    }
                }
            }
        }
        if (this.a8 > 0) {
            if (--this.a8 == 0) {
                this.a0 = 20;
                this.j();
            }
        }
        else if (this.a9 > 0) {
            --this.a9;
            this.a8 = 40;
        }
        else {
            this.a8 = this.p.a() % 300;
        }
        if (this.a0 > 0 && --this.a0 == 0) {
            this.j();
        }
        if (this.a4 > 0 && --this.a4 == 0) {
            int n16 = 0;
            do {
                final double n17 = -(n16 / 20.0) * 3.14159;
                final aj aj12 = new aj();
                aj12.a = this.a3.c + this.p.a() % 40;
                aj12.b = this.a3.d + 40.0;
                aj12.c = Math.cos(n17) * 4.5;
                aj12.d = Math.sin(n17) * 4.5;
                aj12.f = 1.0;
                aj12.g = 0.005 + this.p.a() % 1000 / 1000.0 * 0.004;
                aj12.i = true;
                aj12.k = 0;
                aj12.h = 8;
                this.aq.addElement(aj12);
                final aj aj13 = new aj();
                aj13.a = aj12.a;
                aj13.b = aj12.b;
                aj13.c = aj12.c;
                aj13.d = aj12.d;
                aj13.f = aj12.f;
                aj13.g = aj12.g;
                aj13.i = true;
                aj13.k = 1;
                aj13.h = 4;
                this.aq.addElement(aj13);
                final aj aj14 = new aj();
                aj14.a = aj13.a;
                aj14.b = aj13.b;
                aj14.c = aj13.c;
                aj14.d = aj13.d;
                aj14.f = aj13.f;
                aj14.g = aj13.g;
                aj14.i = true;
                aj14.k = 2;
                aj14.h = 0;
                this.aq.addElement(aj14);
            } while (++n16 < 20);
        }
        if (this.a6 > 0.0) {
            this.a6 -= 0.01;
            this.j();
        }
        if (this.ba > 0 && --this.ba == 0) {
            this.k();
        }
        if (this.a5 > 0 && --this.a5 == 0) {
            int n18 = 0;
            do {
                int n19 = 0;
                do {
                    final ad ad18 = this.k[n18][n19];
                    if (ad18 != null) {
                        if (this.p.a() % 16 == 0 || n18 == 0) {
                            ad18.n = true;
                            ad18.o = this.p.a() % 8;
                        }
                        ad18.c = this.a(ad18.a);
                        ad18.d = this.h(ad18.a, ad18.b);
                        ad18.k = false;
                        ad18.t = 0;
                        ad18.p = false;
                    }
                } while (++n19 < 7);
            } while (++n18 < 8);
            int n20 = 0;
            do {
                final aj aj15 = new aj();
                aj15.a = this.p.a() % super.c;
                aj15.b = this.p.a() % super.d;
                aj15.c = 0.0;
                aj15.d = 0.0;
                aj15.e = 0.002 + this.p.a() % 1000 / 1000.0 * 0.005;
                aj15.f = 0.2;
                aj15.g = 7.5E-4 + this.p.a() % 1000 / 1000.0 * 0.001;
                this.aq.addElement(aj15);
            } while (++n20 < 64);
            this.bc = null;
            this.bd = 0.0;
            this.a6 = 1.0;
            this.a7 = true;
            this.j.x.g = false;
            this.j();
        }
        if (this.bb > 0) {
            --this.bb;
            if (this.bb == 0) {
                this.d(false);
                this.j.c(28);
            }
        }
        else if (this.bc != null && this.bb == 0) {
            if (this.bd < 1.0) {
                this.bd += 0.0125;
                if (this.bd > 1.0) {
                    this.bd = 1.0;
                }
            }
            if (super.q % 8 == 0) {
                this.be = (this.be + 48 + this.p.a() % 48) % 600;
            }
        }
    }
    
    public void l() {
        int n = 0;
        do {
            for (int i = 7; i >= ((n % 2 == 0) ? 1 : 0); --i) {
                final ad a3 = this.k[i][n];
                if (a3 != null && a3.c()) {
                    if (i < 7) {
                        final ad ad = this.k[i + 1][n];
                        if (ad != null && ad.g() && !a3.i) {
                            final ad ad2 = ad;
                            --ad2.u;
                            if (ad.u == 0 && !ad.i) {
                                this.r.a();
                                this.q.a();
                                ad.b();
                            }
                        }
                    }
                    else if (!a3.i) {
                        this.a3 = a3;
                        this.a5 = 160;
                        this.a8 = this.a5 + 150;
                        this.a9 = 1;
                        this.ba = this.a5 + 100;
                        this.bb = 85;
                        a3.p = true;
                        if (this.j.ax != null) {
                            this.j.ax.m();
                            this.f(false);
                        }
                        this.c(true);
                        this.z = 3;
                    }
                }
            }
        } while (++n < 7);
    }
    
    public void g(final int n, final int n2) {
        int n3 = 0;
        if (this.j.ax != null) {
            this.j.ax.m();
        }
        if (this.s.a.length() > 8) {
            this.j.a("OOPS!", "9+ letter words aren't\navailable in this web version.\n\nDownload BookWorm Deluxe,\nwhich includes this\nand many more features!", "Okay", 2);
            this.j.av = 2;
        }
        else if (this.s.j) {
            if (this.j.az) {
                this.j.a0 = true;
            }
            if (this.j.au > 0) {
                final WordFallApplet j = this.j;
                --j.au;
            }
            this.r.a(this.s);
            this.q.a(this.s);
            final int length = this.s.a.length();
            if (length >= 6 || (length == 5 && (this.p.a() % 5 == 0 || !this.j.ai)) || (length == 4 && this.p.a() % 100 == 0)) {
                this.ai = true;
            }
            if (this.s.e > 1) {
                this.az = true;
            }
            Math.min(length - 3, w.i.length - 1);
            final double n4 = w.i[Math.min(length - 3, w.i.length - 1)];
            if (this.s.e == 0 || n4 > 0.0) {
                this.ab += w.i[Math.min(length - 3, w.i.length - 1)];
            }
            if (this.s.a.equalsIgnoreCase(this.bf)) {
                n3 = w.e[Math.min(this.q.k, w.e.length - 1)];
                this.c(n3);
            }
            if (!this.j.aa.contains(this.s.a)) {
                this.j.aa.addElement(this.s.a);
            }
            this.v = 1.0;
            this.t = this.s;
            this.l();
            this.ac = false;
            ++this.ah;
            --this.ab;
            this.w = 0;
            this.x = this.s.a.length();
            this.y = 20;
            if (this.s.e == 0) {
                this.j.c(2);
            }
            else {
                this.j.c(22);
            }
            this.b(this.l);
            this.a(this.l);
            int n5 = w.f[Math.min(this.aj, w.f.length - 1)];
            if (this.aj >= w.f.length) {
                n5 += (this.aj - w.f.length) * 10000;
            }
            this.al += this.s.b / n5;
            if (this.al >= 1.0 && this.z != 3) {
                this.z = 2;
            }
            this.o();
            this.s();
            this.c(this.s.b, n, n2);
            if (n3 > 0) {
                this.a("+", " BONUS", n3, n, n2 + 30);
            }
            this.w();
            this.t();
            this.aa = 0;
        }
        else {
            if (this.s.a.length() >= 3 && !this.j.ab.contains(this.s.a)) {
                this.j.ab.addElement(this.s.a);
            }
            if (this.aa == 3 && this.l.size() > 0) {
                this.j.c(4);
            }
            this.aa = 1;
        }
        this.j();
    }
    
    public void m() {
        this.j.d("LevelStats");
        this.j.a("Level", this.aj);
        this.j.a("WordCount", this.r.c);
        this.j.s();
        this.r = new ac(this.j);
        this.ac = true;
        this.al = 0.0;
        if (this.z == 5) {
            this.j.c(29);
        }
        else {
            this.j.c(27);
        }
        ++this.aj;
        this.ah = 0;
        this.c(false);
        this.b(this.aj);
    }
    
    public void n() {
        this.ah = 0;
        this.ag = 0;
        this.al = 0.0;
        this.am = 0.0;
        int a = 0;
        do {
            int n = -64 - this.p.a() % 64;
            for (int i = 7; i >= ((a % 2 == 0) ? 1 : 0); --i) {
                final ad ad = new ad();
                ad.a = a;
                ad.b = i;
                ad.c = this.a(a);
                ad.d = n;
                ad.g = this.r();
                ad.f = 0.0;
                this.k[i][a] = ad;
                n -= 40 + this.p.a() % 80;
            }
        } while (++a < 7);
    }
    
    public int a(final int n) {
        return n * 40 + 127;
    }
    
    public void o() {
        int n = 0;
        final int n2 = w.g[Math.min(this.aj, w.g.length - 1)];
        if (n2 > 0 && this.p.a() % 100 < n2) {
            ++n;
        }
        if (!this.ac && this.ab <= 0.0) {
            ++n;
            this.ab += w.h[Math.min(this.aj, w.h.length - 1)];
        }
        if (this.ap) {
            ++n;
            this.ap = false;
        }
        int a = 0;
        do {
            int b = 7;
            int n3 = 7;
            do {
                final ad ad = this.k[n3][a];
                if (ad != null) {
                    ad.b = b;
                    this.k[ad.b][ad.a] = ad;
                    --b;
                }
            } while (--n3 >= 0);
            int n4 = -64 - this.p.a() % 64;
            for (int i = b; i >= ((a % 2 == 0) ? 1 : 0); --i) {
                final ad ad2 = new ad();
                ad2.a = a;
                ad2.b = i;
                ad2.c = this.a(a);
                ad2.d = n4;
                ad2.g = this.r();
                ad2.f = 0.0;
                if (n > 0 && b == ((a % 2 == 0) ? 1 : 0)) {
                    this.b(ad2);
                    ad2.g = this.a(this.au, this.av);
                    --n;
                }
                if (!ad2.c() && !ad2.a() && this.p.a() % 20 == 0) {
                    ad2.c(true);
                    this.j.c(19);
                    if (!this.j.ah && this.ar == 0) {
                        this.j.ah = true;
                        this.ar = 80;
                        this.as = 2;
                    }
                }
                this.k[i][a] = ad2;
                n4 -= 40 + this.p.a() % 80;
            }
            int n5 = 7;
            do {
                final ad ad3 = this.k[n5][a];
                if (ad3 != null && ad3.c() && n5 < 7) {
                    final ad ad4 = this.k[n5 + 1][a];
                    if (ad4 == null || ad4.g()) {
                        continue;
                    }
                    ad4.e();
                }
            } while (--n5 >= 0);
        } while (++a < 7);
    }
    
    public Vector p() {
        final Vector<ad> vector = new Vector<ad>();
        int n = 0;
        do {
            for (int i = 7; i >= ((n % 2 == 0) ? 1 : 0); --i) {
                final ad ad = this.k[i][n];
                if (ad != null && !ad.c() && !ad.d() && !ad.a() && !ad.k) {
                    vector.addElement(ad);
                }
            }
        } while (++n < 7);
        return vector;
    }
    
    public void a(final ad ad, final boolean b) {
        for (int i = 0; i < this.l.size(); ++i) {
            if (this.l.elementAt(i) == ad) {
                for (int j = i; j < this.l.size(); ++j) {
                    ((ad)this.l.elementAt(j)).i = false;
                }
                this.l.setSize(i);
                break;
            }
        }
        final boolean b2 = !ad.i;
        ad.i = true;
        this.l.addElement(ad);
        this.s = new aq(this.l, this.j.ac, this.aj, this.bf);
        final boolean b3 = this.s.f > 0 || this.s.g > 0;
        this.s.a(this.j.f.e);
        if (this.s.j) {
            this.j.t.d = this.j.f.at;
        }
        else {
            this.j.t.d = this.j.f.as;
        }
        if (b) {
            if (this.s.j && b2 && b3) {
                this.j.c(3);
            }
            else {
                this.j.c(1);
            }
        }
        this.j();
    }
    
    public void b(final int n) {
        final double min = Math.min(1.0, n / 5.0);
        this.av = 0;
        this.au = new int[w.a.length];
        for (int i = 0; i < w.a.length; ++i) {
            this.au[i] = (int)(w.b[i] + w.b[i] * min * (w.b[i] / 304835.0 - w.a[i] / 96000.0));
            this.av += this.au[i];
        }
    }
    
    public void d(final boolean b) {
        if (this.j.t != null) {
            this.j.t.b(b);
        }
        if (this.j.u != null) {
            this.j.u.b(b);
        }
        if (this.j.v != null) {
            this.j.v.b(b);
        }
        if (this.j.w != null) {
            this.j.w.b(b);
        }
    }
    
    public void b(final boolean b) {
        super.b(b);
        this.d(b);
    }
    
    public void q() {
        if (!this.j.ae && this.ar == 0) {
            this.as = 0;
            this.ar = 100;
            this.j.ae = true;
        }
        this.q = new ac(this.j);
        this.r = new ac(this.j);
        this.g(false);
        if (this.l != null && this.l.size() > 0) {
            this.w();
        }
        this.d(255);
        this.z = 0;
        this.aa = 0;
        this.al = 0.0;
        this.am = 0.0;
        this.aj = 0;
        this.ak = 0;
        this.ah = 0;
        this.ag = 0;
        this.ab = w.h[Math.min(this.aj, w.h.length - 1)];
        this.b(this.aj);
        this.n();
        this.f(false);
        this.j.a();
        if (this.j.ax != null) {
            this.j.ax.k = false;
        }
        this.j();
        this.j.x.g = true;
    }
    
    public void e(final boolean b) {
        if (b) {
            ++this.ao;
            return;
        }
        --this.ao;
    }
    
    public char a(final int[] array, final int n) {
        int n2 = this.p.a() % n;
        int an = 0;
        do {
            n2 -= array[an];
        } while (n2 >= 0 && ++an < 25);
        if (an == this.an) {
            return this.a(array, n);
        }
        this.an = an;
        return (char)(65 + an);
    }
    
    public char r() {
        int n = this.p.a() % 96000;
        int an = 0;
        do {
            n -= w.a[an];
        } while (n >= 0 && ++an < 25);
        if (an == this.an) {
            return this.r();
        }
        this.an = an;
        return (char)(65 + an);
    }
    
    public void s() {
        final Vector p = this.p();
        if (p != null && p.size() > 0) {
            final ad ad = p.elementAt(this.p.a() % p.size());
            if (ad != null && this.ai) {
                this.ai = false;
                ad.a(true);
                this.j.c(24);
                ad.h = ad.g;
                ad.s = 0.0;
                ad.r = true;
                if (!this.j.ai && this.ar == 0) {
                    this.j.ai = true;
                    this.ar = 80;
                    this.as = 3;
                }
            }
        }
    }
    
    public void t() {
        int n = 0;
        do {
            for (int i = 7; i >= ((n % 2 == 0) ? 1 : 0); --i) {
                final ad ad = this.k[i][n];
                if (ad != null && ad.c() && i == 7) {
                    this.ad = 3;
                }
            }
        } while (++n < 7);
    }
    
    public int h(final int n, final int n2) {
        if (n % 2 == 0) {
            return n2 * 40 - 15;
        }
        return n2 * 40 + 3;
    }
    
    public void u() {
        if (this.z != 0) {
            return;
        }
        if (this.ag >= 10) {
            this.j.a("Can't Scramble!", "Scrambling now would put too\nmany fire tiles on the board!", "OK", 2);
            this.j.av = 0;
            return;
        }
        int n = 0;
        do {
            for (int i = 7; i >= ((n % 2 == 0) ? 1 : 0); --i) {
                final ad ad = this.k[i][n];
                if (ad != null) {
                    if (ad.c() && i == 7) {
                        this.j.a("Can't Scramble!", "Scrambling now would cause\nyou to lose the game!", "OK", 2);
                        this.j.av = 0;
                        return;
                    }
                    if (ad.k) {
                        return;
                    }
                }
            }
        } while (++n < 7);
        this.g(false);
        this.w();
        this.r.b();
        this.q.b();
        final Vector vector = new Vector<ad>();
        int n2 = 0;
        do {
            final ad ad2 = this.k[n2 % 2 == 0][n2];
            if (ad2 != null && !ad2.c() && !ad2.d() && !ad2.a()) {
                vector.addElement(ad2);
            }
        } while (++n2 < 7);
        if (vector.size() > 0) {
            for (int min = Math.min(this.q.j, vector.size()), j = 0; j < min; ++j) {
                final int n3 = this.p.a() % vector.size();
                final ad ad3 = vector.elementAt(n3);
                this.b(ad3);
                vector.removeElementAt(n3);
                int n4 = 0;
                do {
                    final aj aj = new aj();
                    aj.a = ad3.c + this.p.a() % 40;
                    aj.b = ad3.d + this.p.a() % 40;
                    aj.c = (this.p.a() % 1000 - 500) / 250.0;
                    aj.d = (this.p.a() % 1000 - 500) / 250.0;
                    aj.f = 1.0;
                    aj.g = 0.02 + this.p.a() % 1000 / 1000.0 * 0.05;
                    this.aq.addElement(aj);
                } while (++n4 < 100);
            }
        }
        int n5 = 0;
        do {
            for (int k = 7; k >= ((n5 % 2 == 0) ? 1 : 0); --k) {
                final ad ad4 = this.k[k][n5];
                if (!ad4.c()) {
                    ad4.t = 0;
                    ad4.h = this.r();
                }
                else {
                    ad4.h = ad4.g;
                }
            }
        } while (++n5 < 7);
        if (!this.j.aj && this.ar == 0) {
            this.ar = 100;
            this.as = 4;
            this.j.aj = true;
        }
        this.l();
        this.o();
        this.t();
        this.z = 1;
        this.at = 0.0;
        this.j();
    }
    
    public void a(final int n, final boolean b, final boolean b2) {
        super.a(n, b, b2);
        if (this.z == 0) {
            switch (n) {
                case 10: {
                    if (this.l.size() <= 0) {
                        break;
                    }
                    final ad ad = this.l.elementAt(this.l.size() - 1);
                    if (ad != null) {
                        this.g((int)ad.c + 20, (int)ad.d + 20);
                        this.j.as = true;
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public boolean v() {
        return this.bf != null && this.bf.length() >= 3;
    }
    
    public void f(final boolean b) {
        if (this.j.ax != null) {
            if (this.j.ax.j == 0) {
                this.j.ax.j = super.d;
            }
            if (b) {
                this.j.ax.i = 290;
                this.j.ax.f = 0;
                this.j.ax.m();
                this.j.ax.a(1);
                this.j();
                return;
            }
            this.j.ax.i = super.d;
        }
    }
    
    public void w() {
        this.l.removeAllElements();
        this.s = null;
        this.aa = 0;
        this.j.t.d = this.j.f.as;
        this.j();
    }
    
    public void a(final n n) {
        final Vector vector = new Vector<ad>();
        final Vector vector2 = new Vector<ad>();
        final Vector vector3 = new Vector<ad>();
        final Vector vector4 = new Vector<ad>();
        if (this.j.s) {
            if (this.j.f == null) {
                this.j.e("mRes is null");
            }
            if (this.j.f.a[0] == null) {
                this.j.e("mImages[Res.IMAGE_BACKGROUND] is null");
            }
        }
        if (this.a7 && this.j.f.af.j()) {
            n.a(this.j.f.af, 0, 0);
        }
        else {
            n.a(this.j.f.a[0], 0, 0);
        }
        final boolean x = this.x();
        int n2 = 7;
        do {
            int n3 = 0;
            do {
                final ad ad = this.k[n2][n3];
                if (ad != null) {
                    j j;
                    if (this.a7) {
                        j = this.j.f.az[8];
                    }
                    else if (ad.i) {
                        if (x && !ad.c() && !ad.a()) {
                            vector2.addElement(ad);
                        }
                        if (ad.c()) {
                            j = this.j.f.az[3];
                        }
                        else if (ad.d()) {
                            j = this.j.f.az[5];
                        }
                        else if (ad.a()) {
                            j = this.j.f.az[7];
                        }
                        else {
                            j = this.j.f.az[1];
                        }
                    }
                    else if (ad.c()) {
                        j = this.j.f.az[2];
                    }
                    else if (ad.d()) {
                        j = this.j.f.az[4];
                    }
                    else if (ad.a()) {
                        j = this.j.f.az[6];
                    }
                    else {
                        j = this.j.f.az[0];
                    }
                    int n4 = 0;
                    if (this.z == 1 && !ad.c()) {
                        n4 = (int)((this.at * 19.0 / 4.0 - (n3 + n2) / 4.0) * 8.0);
                    }
                    else if (ad.r) {
                        n4 = (int)((ad.s * 19.0 / 4.0 - (n3 + n2) / 4.0) * 8.0);
                        if (n4 < 8) {
                            j = this.j.f.az[0];
                        }
                    }
                    if (this.j.s) {
                        if (j == null) {
                            this.j.e("aTileStripImage is null");
                        }
                        if (this.j.f.a[25] == null) {
                            this.j.e("mImages[Res.IMAGE_SPINNING_TILE] is null");
                        }
                    }
                    if (n4 > 0 && n4 < 8) {
                        n.b(this.j.f.a[25], (int)ad.c, (int)ad.d, new Rectangle(0, (n4 - 1) * 40, 40, 40));
                    }
                    else {
                        n.b(j, (int)ad.c, (int)ad.d, new Rectangle('(' * (((n4 <= 0) ? ad.g : ad.h) - 'A'), 0, 40, 40));
                    }
                    if (ad.n) {
                        vector4.addElement(ad);
                    }
                    if (ad.c()) {
                        vector.addElement(ad);
                        if (ad.b >= 7) {
                            continue;
                        }
                        final ad ad2 = this.k[ad.b + 1][ad.a];
                        if (ad2 == null || !ad2.g() || ad.d != this.h(ad.a, ad.b)) {
                            continue;
                        }
                        vector.addElement(ad2);
                    }
                    else if (ad.d()) {
                        vector2.addElement(ad);
                    }
                    else {
                        if (!ad.a()) {
                            continue;
                        }
                        vector3.addElement(ad);
                    }
                }
            } while (++n3 < 7);
        } while (--n2 >= 0);
        for (int i = 0; i < vector2.size(); ++i) {
            final ad ad3 = vector2.elementAt(i);
            if (ad3 != null) {
                final int n5 = 176 - Math.abs(super.q * 2 % 256 - 128);
                n.e = new f(n5, n5, n5);
                n.d = true;
                n.i = 1;
                final j k = this.j.f.a[2];
                if (this.j.s && k == null) {
                    this.j.e("Bonus: aGlowImage is null");
                }
                n.a(k, (int)ad3.c - 10, (int)ad3.d - 10);
                n.i = 0;
                n.d = false;
            }
        }
        for (int l = 0; l < vector3.size(); ++l) {
            final ad ad4 = vector3.elementAt(l);
            if (ad4 != null) {
                final int n6 = 128 - Math.abs(super.q % 128 - 64);
                n.e = new f(n6, n6, n6);
                n.d = true;
                n.i = 1;
                final j m = this.j.f.a[4];
                if (this.j.s && m == null) {
                    this.j.e("SuperBonus: aGlowImage is null");
                }
                n.a(m, (int)ad4.c - 10, (int)ad4.d - 10);
                n.i = 0;
                n.d = false;
            }
        }
        for (int n7 = 0; n7 < vector.size(); ++n7) {
            final ad ad5 = vector.elementAt(n7);
            if (ad5 != null) {
                final int n8 = 256 - Math.abs(super.q * 4 % 512 - 256);
                n.e = new f(n8, n8, n8);
                n.i = 1;
                n.d = true;
                if (this.j.s && this.j.f.a[6] == null) {
                    this.j.e("mApplet.mRes.mImages[ Res.IMAGE_TILE_FIRE_GLOW ] is null");
                }
                n.a(this.j.f.a[6], (int)ad5.c - 10, (int)ad5.d - 10);
                n.d = false;
                if (ad5.k) {}
                if (ad5.c()) {
                    final n n9 = new n(n);
                    n9.i = 1;
                    final j j2 = this.j.f.a[16];
                    final j j3 = this.j.f.a[17];
                    if (this.j.s) {
                        if (j2 == null) {
                            this.j.e("aSpark1 is null");
                        }
                        if (j3 == null) {
                            this.j.e("aSpark2 is null");
                        }
                    }
                    final int n10 = j2.c() / 40;
                    final int n11 = super.q / 16 % n10 * 40;
                    final int n12 = super.q / 18 % n10 * 40;
                    n9.a((int)ad5.c, (int)ad5.d, 40, 40);
                    n9.b(j2, (int)ad5.c, (int)ad5.d, new Rectangle(0, n11, 40, 40));
                    n9.b(j3, (int)ad5.c, (int)ad5.d, new Rectangle(0, n12, 40, 40));
                    n9.i = 0;
                }
                n.i = 0;
            }
        }
        if (this.j.f.ag.j()) {
            for (int n13 = 0; n13 < vector4.size(); ++n13) {
                final ad ad6 = vector4.elementAt(n13);
                final int n14 = this.j.f.ag.c() / 8;
                if (this.j.s && this.j.f.ag == null) {
                    this.j.e("mApplet.mRes.mStreamTileFlamesFx is null");
                }
                n.i = 1;
                n.b(this.j.f.ag, (int)ad6.c, (int)ad6.d - n14 + 40, new Rectangle(0, ad6.o * n14, this.j.f.ag.h(), n14));
                n.i = 0;
            }
        }
        if (!this.a7) {
            for (int n15 = 0; n15 < this.l.size(); ++n15) {
                final ad ad7 = this.l.elementAt(n15);
                if (n15 < this.l.size() - 1) {
                    final ad ad8 = this.l.elementAt(n15 + 1);
                    final j ai = this.j.f.ai;
                    if (this.j.s && ai == null) {
                        this.j.e("aCompassImage is null");
                    }
                    final int n16 = ad8.a - ad7.a;
                    int n17 = ad8.b - ad7.b;
                    if (n16 != 0) {
                        if (ad7.a % 2 == 0 && n17 == 0) {
                            n17 = 1;
                        }
                        else if (ad7.a % 2 == 1 && n17 == 0) {
                            n17 = -1;
                        }
                    }
                    final n n18 = new n(n);
                    final int n19 = ai.h() / 3;
                    final int n20 = ai.c() / 2;
                    final int n21 = n16 + 1;
                    final int n22 = (n17 >= 0) ? 1 : 0;
                    n18.a((int)ad7.c + (40 - ai.h()) / 2, (int)ad7.d + (40 - ai.c()) / 2);
                    n18.a(n21 * n19, n22 * n20, n19, n20);
                    n18.a(ai, 0, 0);
                }
            }
            for (int n23 = 0; n23 < this.m.size(); ++n23) {
                final ap ap = this.m.elementAt(n23);
                final int min = Math.min(ap.d * 255 / 10, 255);
                final int a = this.j.f.a6.a(ap.a);
                this.j.f.a6.a(n, ap.a, Math.max(127, Math.min(super.c - a, ap.b - a / 2)), ap.c + this.j.f.a4 / 2, min);
            }
            if (this.v > 0.0 && this.t != null) {
                final aq t = this.t;
                String s = this.t.a;
                if (t.a.length() > 0) {
                    if (t.j) {
                        s = s + " = " + Integer.toString(t.b);
                    }
                    else if (t.a.length() > 8) {
                        s = s.substring(0, 8);
                    }
                    if (s.length() < 3) {
                        this.u = new f(255, 255, 255, 255);
                    }
                    else if (t.d == t.c) {
                        this.u = new f(255, 255, 255, 255);
                    }
                    else if (t.g > 0) {
                        this.u = new f(255, 220, 0, 255);
                    }
                    else if (t.f > 0) {
                        this.u = new f(0, 255, 0, 255);
                    }
                    else if (t.e > 0) {
                        this.u = new f(255, 100, 0, 255);
                    }
                }
                if (s != null && s.length() > 0) {
                    final int n24 = (int)(this.v * 255.0);
                    final int d = (n24 > 127) ? 255 : (n24 << 1);
                    final u j4 = this.j.f.j;
                    n.f = j4;
                    n.e = new f(0, 0, 0, d);
                    n.a(s, 66 - j4.a(s) / 2, 96);
                    this.u.d = d;
                    n.e = this.u;
                    n.a(s, 65 - j4.a(s) / 2, 95);
                }
            }
            final int min2 = Math.min(Math.max(0, 255 - (int)(this.v * 255.0)), 255);
            n.f = this.j.f.m;
            n.e = new f(255, 255, 255, min2);
            final j j5 = this.j.f.a[19];
            final String string = "" + Integer.toString(this.aj + 1);
            final int n25 = 59 - (j5.h() + n.f.a(string)) / 2;
            n.d = true;
            n.a(this.j.f.a[19], n25, 83);
            n.d = false;
            n.a(string, n25 + j5.h(), 92);
            if (this.am > 0.0) {
                final n n26 = new n(n);
                final int h = this.j.f.aj.h();
                final int min3 = Math.min(13, -h + (int)(13.0 + this.am * h));
                n26.a(13, 137, h, this.j.f.aj.c());
                if (this.j.s && this.j.f.aj == null) {
                    this.j.e("mApplet.mRes.mProgressBarImage is null");
                }
                n26.a(this.j.f.aj, min3, 137);
            }
        }
        int n27 = 0;
        if (this.a1) {
            n27 = 2;
        }
        if (this.ax && this.ay > 0.9) {
            n27 = 1;
        }
        if (this.a7) {
            n27 = 3;
        }
        if (this.n.size() > 0) {
            final Rectangle rectangle = new Rectangle(28, 158, this.j.f.al.h(), this.j.f.al.c());
            final ad ad9 = this.n.elementAt(0);
            if (ad9 != null && rectangle.contains((int)ad9.c, (int)ad9.d)) {
                n27 = 1;
            }
        }
        if (n27 == 1 && this.j.f.al != null) {
            n.a(this.j.f.al, 28, 154);
        }
        else if (n27 == 2 && this.j.f.am != null && this.j.f.an != null) {
            n.a(this.j.f.am, 28, 175);
            final int n28 = this.j.f.an.h() / 8;
            n.b(this.j.f.an, 28, 174, new Rectangle(this.a2 * n28, 0, n28, this.j.f.an.c()));
        }
        else if (n27 == 3 && this.j.f.ao != null) {
            n.a(this.j.f.ao, 28, 175);
            if (this.a0 > 0 && this.j.f.ap != null) {
                n.a(this.j.f.ap, 28, 175);
            }
        }
        else {
            n.a(this.j.f.ak, 28, 175);
            if (this.a0 > 0 && this.j.f.a[27] != null) {
                n.a(this.j.f.a[27], 28, 175);
            }
        }
        for (int n29 = 0; n29 < this.aq.size(); ++n29) {
            final aj aj = this.aq.elementAt(n29);
            switch (aj.k) {
                case 0: {
                    n.i = 1;
                    n.b(this.j.f.a[24], (int)aj.a - 2, (int)aj.b - 2, new Rectangle(0, aj.j * 5, 5, 5));
                    n.i = 0;
                    break;
                }
                case 1: {
                    n.i = 1;
                    n.b(this.j.f.a[28], (int)aj.a - 6, (int)aj.b - 6, new Rectangle(0, aj.j * 13, 13, 13));
                    n.i = 0;
                    break;
                }
                case 2: {
                    n.i = 1;
                    n.b(this.j.f.a[29], (int)aj.a - 8, (int)aj.b - 8, new Rectangle(0, aj.j * 17, 17, 17));
                    n.i = 0;
                    break;
                }
                case 3: {
                    n.d = true;
                    n.e = new f(255, 255, 255, (int)(aj.f * 255.0));
                    n.a(this.j.f.a[26], (int)aj.a - 15, (int)aj.b - 15);
                    n.d = false;
                    break;
                }
            }
        }
        if (this.ax) {
            n.d = true;
            n.e = new f(255, 255, 255, (int)(this.ay * 255.0));
            n.a(this.j.f.a[26], 72, 196 - (int)((1.0 - this.ay) * 48.0));
            n.d = false;
        }
        if (this.n.size() > 0) {
            for (int n30 = 0; n30 < this.n.size(); ++n30) {
                final ad ad10 = this.n.elementAt(n30);
                if (ad10 != null) {
                    j j6;
                    if (ad10.c()) {
                        j6 = this.j.f.az[3];
                    }
                    else if (ad10.d()) {
                        j6 = this.j.f.az[5];
                    }
                    else if (ad10.a()) {
                        j6 = this.j.f.az[7];
                    }
                    else {
                        j6 = this.j.f.az[1];
                    }
                    n.a(j6, new Rectangle((int)ad10.c, (int)ad10.d, (int)(40.0 * ad10.j), (int)(40.0 * ad10.j)), new Rectangle('(' * (ad10.g - 'A'), 0, 40, 40));
                }
            }
        }
        if (this.bc != null && this.j.f.ah.j()) {
            final int n31 = (int)(this.bd * super.c * 2.0);
            final Rectangle rectangle2 = new Rectangle(this.a(this.bc.a) + 20 - n31 / 2, 0, n31, super.d);
            final j ah = this.j.f.ah;
            int n32 = 1;
            do {
                int n33 = 0;
                do {
                    final int n34 = (n33 == 0) ? (rectangle2.x + (3 - n32) * 8) : (rectangle2.x + (rectangle2.width - (3 - n32) * 8 - 8));
                    final int n35 = n34 + 8;
                    final n n36 = new n(n);
                    n36.a(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                    n36.a(n34, rectangle2.y, n35 - n34, rectangle2.height);
                    final int n37 = ah.c() * (4 - n32) / 4;
                    final int n38 = this.h(this.bc.a, this.bc.b) + 40 - n37 * 50 / 70;
                    n36.a(ah, new Rectangle(-this.be, n38, ah.h(), n37), new Rectangle(0, 0, ah.h(), ah.c()));
                    n36.a(ah, new Rectangle(-this.be + ah.h(), n38, ah.h(), n37), new Rectangle(0, 0, ah.h(), ah.c()));
                } while (++n33 < 2);
            } while (++n32 < 4);
            final n n39 = new n(n);
            n39.a(rectangle2.x + 24, rectangle2.y, rectangle2.width - 48, rectangle2.height);
            final int n40 = this.h(this.bc.a, this.bc.b) + 40 - ah.c() * 50 / 70;
            n39.b(ah, -this.be, n40, new Rectangle(0, 0, ah.h(), ah.c()));
            n39.b(ah, -this.be + ah.h(), n40, new Rectangle(0, 0, ah.h(), ah.c()));
        }
        if (this.a6 > 0.0) {
            final int n41 = (int)(this.a6 * 255.0);
            n.i = 1;
            n.e = new f(n41, n41, n41);
            n.b(0, 0, super.c, super.d);
            n.i = 0;
        }
    }
    
    public w(final WordFallApplet j) {
        this.k = new ad[8][7];
        this.l = new Vector();
        this.m = new Vector();
        this.n = new Vector();
        this.o = 1.0;
        this.z = 0;
        this.aa = 0;
        this.ac = false;
        this.an = -1;
        this.aq = new Vector();
        this.p = new x((int)(Math.random() * 1000000.0));
        this.j = j;
    }
    
    public boolean a(final ad ad, final ad ad2) {
        final int n = ad.a - ad2.a;
        final int n2 = ad.b - ad2.b;
        return (Math.abs(n) == 1 && ((ad2.a % 2 == 0 && (n2 == 0 || n2 == -1)) || (ad2.a % 2 == 1 && (n2 == 0 || n2 == 1)))) || (n == 0 && Math.abs(n2) == 1);
    }
    
    public boolean x() {
        boolean b = false;
        for (int i = 0; i < this.l.size(); ++i) {
            final ad ad = this.l.elementAt(i);
            if (ad != null && ad.d()) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public void c(final boolean b) {
        super.c(b);
        if (this.j.x != null) {
            this.j.x.c(b);
        }
        if (this.j.t != null) {
            this.j.t.c(b);
        }
        if (this.j.u != null) {
            this.j.u.c(b);
        }
        if (this.j.v != null) {
            this.j.v.c(b);
        }
        if (this.j.w != null) {
            this.j.w.c(b);
        }
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (this.j.ax != null) {
            this.j.ax.l();
        }
        if (this.z == 0) {
            if (n3 > 0) {
                if (this.f(n, n2) == null) {
                    this.g(false);
                    this.w();
                    this.t = this.s;
                }
                else if (this.aa == 0) {
                    if (this.l.size() > 0) {
                        this.aa = 1;
                    }
                }
                else if (this.aa == 2 || this.aa == 3) {
                    if (this.aa == 2) {
                        this.j.aq = true;
                    }
                    else if (this.aa == 3) {
                        this.j.ar = true;
                    }
                    this.g(n, n2);
                    this.aa = 1;
                }
            }
            else if (n3 < 0) {
                this.g(false);
                this.w();
                this.t = this.s;
            }
        }
        this.j();
    }
    
    public void c(final int n) {
        this.j.c(25);
        this.r.a(n);
        this.q.a(n);
        if (this.bf != null) {
            this.j.r.addElement(this.bf);
            this.j.a();
            this.bf = null;
        }
        if (this.j.ax != null) {
            this.j.ax.m();
            this.f(false);
            this.j.ax.i = super.d;
        }
    }
    
    public void a(final String s, final String s2, final int n, final int b, final int c) {
        final int n2 = (int)(n * 1.0);
        final int max = Math.max(150, Math.min(600, (int)(n / 3.0)));
        final String string = s + n2 + s2;
        final ap ap = new ap();
        ap.a = string;
        ap.b = b;
        ap.c = c;
        ap.d = max;
        this.m.addElement(ap);
    }
    
    public void c(final int n, final int n2, final int n3) {
        this.a("", "", n, n2, n3);
    }
    
    public void a(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            this.a(vector.elementAt(i));
        }
    }
    
    static {
        a = new int[] { 9000, 2200, 2200, 4200, 12300, 2300, 3000, 2000, 7500, 900, 900, 4000, 2300, 6000, 7000, 2300, 700, 6000, 4400, 6000, 4400, 1600, 1800, 500, 2000, 500 };
        b = new int[] { 24067, 5109, 6794, 15180, 36762, 6206, 6781, 19884, 19443, 677, 3061, 12482, 7365, 20719, 23963, 4903, 190, 15886, 18187, 29775, 9226, 2444, 8172, 432, 6972, 155 };
        c = new int[] { 1, 4, 4, 3, 1, 5, 3, 5, 1, 8, 7, 2, 4, 2, 1, 4, 7, 2, 1, 2, 2, 5, 6, 8, 6, 10 };
        d = new int[] { 0, 0, 0, 50, 100, 200, 400, 600, 1000 };
        e = new int[] { 1000, 2000, 4000, 6000, 10000, 15000, 20000, 25000, 30000, 40000, 50000 };
        f = new int[] { 3000, 6500, 10000, 14000, 19000, 25000, 32000, 40000 };
        g = new int[] { 0, 3, 5, 7, 10, 15, 20, 25, 30, 35 };
        h = new double[] { 4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.3, 1.2, 1.1, 1.1, 1.1 };
        i = new double[] { -0.5, 0.25, 1.25, 1.75, 3.0, 4.75 };
        bg = new String[] { "Book Burner", "Encyclopedia Salesman", "Trainee", "Scribe", "Bookkeeper", "Assistant Librarian", "Librarian", "Senior Librarian", "Master Librarian", "Archivist", "Grand Archivist", "Supreme Archivist", "Wordhoarder", "Vocabulist", "Lexographer", "Lexographer 1st Class", "Dictionarian", "Super Dictionarian" };
    }
    
    public void a(final ad ad) {
        if (ad != null) {
            this.i(ad.b, ad.a);
        }
    }
    
    public void i(final int n, final int n2) {
        final ad ad = this.k[n][n2];
        if (ad != null) {
            switch (ad.t) {
                case 3: {
                    if (this.ag > 0) {
                        --this.ag;
                        break;
                    }
                    break;
                }
            }
            this.k[n][n2] = null;
        }
    }
    
    public void b(final ad ad) {
        if (ad != null) {
            ad.b(true);
            ++this.ag;
            this.ah = 0;
            this.j.c(7);
        }
    }
    
    public void b(final int n, final int n2, final int n3) {
        if (this.j.ax != null) {
            this.j.ax.m();
        }
        if (this.z == 0) {
            if (n3 < 0) {
                if (this.j.ax != null) {
                    this.j.ax.m();
                }
                this.g(false);
                this.w();
                this.t = this.s;
                return;
            }
            final ad f = this.f(n, n2);
            if (f != null) {
                if (this.j.ax != null) {
                    this.j.ax.m();
                }
                for (int i = f.b; i <= 7; ++i) {
                    final ad ad = this.k[i][f.a];
                    if (ad != null && ad.k) {
                        return;
                    }
                }
                if (this.aa == 0) {
                    this.a(f, true);
                }
                else if (n3 > 1) {
                    if (this.l.size() > 1) {
                        this.aa = 3;
                    }
                    else {
                        this.g(false);
                        this.w();
                        this.t = this.s;
                    }
                }
                else if (this.l.size() == 0) {
                    this.a(f, true);
                }
                else if (this.l.size() > 0) {
                    final ad ad2 = this.l.elementAt(0);
                    final ad ad3 = this.l.elementAt(this.l.size() - 1);
                    if (f == ad2) {
                        if (this.l.size() > 1) {
                            this.a(f, true);
                        }
                        else {
                            this.g(false);
                            this.w();
                            this.t = this.s;
                        }
                        this.aa = 0;
                    }
                    else if (f == ad3) {
                        if (this.l.size() > 1) {
                            this.aa = 3;
                        }
                        else {
                            this.g(false);
                            this.w();
                        }
                    }
                    else {
                        boolean b = false;
                        for (int j = this.l.size(); j > 0; --j) {
                            final ad ad4 = this.l.elementAt(j - 1);
                            if (this.a(f, ad4) || f.i) {
                                this.a(ad4, false);
                                this.a(f, true);
                                b = true;
                                break;
                            }
                        }
                        if (!b) {
                            this.g(false);
                            this.w();
                            this.a(f, true);
                        }
                    }
                }
            }
        }
        this.j();
    }
    
    public void b(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            final ad ad = vector.elementAt(i);
            if (ad != null) {
                this.n.addElement(ad);
            }
        }
    }
    
    public void d(final int n, final int n2) {
        if (this.j.ax != null) {
            this.j.ax.l();
        }
        if (this.z == 0 && this.l.size() > 0) {
            final ad f = this.f(n, n2);
            if (f != null) {
                final ad ad = this.l.elementAt(this.l.size() - 1);
                if (ad != f) {
                    this.aa = 2;
                    if (this.a(f, ad)) {
                        this.a(f, true);
                    }
                }
            }
        }
    }
    
    public void g(final boolean i) {
        for (int j = 0; j < this.l.size(); ++j) {
            ((ad)this.l.elementAt(j)).i = i;
        }
    }
    
    public void d(final int n) {
        if (n == 255) {
            int n2 = 0;
            do {
                for (int i = 7; i >= ((n2 % 2 == 0) ? 1 : 0); --i) {
                    this.i(i, n2);
                }
            } while (++n2 < 7);
            this.ag = 0;
            return;
        }
        int n3 = 0;
        do {
            for (int j = 7; j >= ((n3 % 2 == 0) ? 1 : 0); --j) {
                final ad ad = this.k[j][n3];
                if (ad != null) {
                    if ((n & 0x1) != 0x0 && !ad.d() && !ad.c()) {
                        this.i(j, n3);
                    }
                    if ((n & 0x2) != 0x0 && ad.d()) {
                        this.i(j, n3);
                    }
                    if ((n & 0x4) != 0x0 && ad.c()) {
                        this.i(j, n3);
                    }
                }
            }
        } while (++n3 < 7);
    }
}
