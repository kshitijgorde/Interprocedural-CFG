import ABLjemsty.Styler;
import ABLjemsty.StyleEventArg;
import java.awt.Graphics;
import java.awt.Event;
import ABLwidgets.new_font;
import java.awt.Font;
import ABLjemsty.jemQuadrantPanel;
import ABLwidgets.utils;
import java.util.Vector;
import java.awt.CheckboxGroup;
import ABLjemsty.EmuPanel;
import java.awt.Component;
import java.awt.Color;
import ABLjemsty.Enhanced;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemtf extends abljemgt
{
    public boolean a;
    public boolean b;
    public char c;
    public boolean d;
    public char e;
    public Enhanced.Selfield f;
    public Enhanced.Rollbar g;
    public boolean h;
    public abljemp i;
    public Color j;
    public String k;
    public String l;
    public char m;
    public String n;
    public String o;
    public Component p;
    public abljemtf q;
    public int r;
    public String s;
    abljema t;
    boolean u;
    abljemtf v;
    boolean w;
    boolean x;
    boolean y;
    EmuPanel z;
    int aa;
    int ab;
    int ac;
    int ad;
    public int ae;
    int af;
    byte ag;
    byte ah;
    byte ai;
    byte aj;
    byte ak;
    byte al;
    byte am;
    byte an;
    byte ao;
    int ap;
    byte aq;
    String[] ar;
    int[] as;
    int[] at;
    int au;
    int av;
    int aw;
    int ax;
    int ay;
    int az;
    boolean a0;
    int a1;
    String a2;
    String a3;
    boolean a4;
    boolean a5;
    boolean a6;
    private abljemfs a7;
    private int a8;
    private int a9;
    private Color ba;
    private Color bb;
    private Color bc;
    private Color bd;
    private boolean be;
    private boolean bf;
    private boolean bg;
    private abljembx bh;
    private boolean bi;
    private abljembx bj;
    private boolean bk;
    private int bl;
    private int[] bm;
    private int[] bn;
    private String[] bo;
    private String[] bp;
    private abljemrb bq;
    private abljemrb[] br;
    private CheckboxGroup bs;
    public String[] bt;
    private abljemct bu;
    static int bv;
    static int bw;
    static int bx;
    static int by;
    static abljemtf bz;
    
    abljemtf() {
        super((abljema)null);
        this.a = false;
        this.b = false;
        this.c = 'A';
        this.d = false;
        this.e = 'A';
        this.n = "Y";
        this.o = "N";
        this.y = false;
        this.a0 = false;
        this.a5 = false;
        this.a6 = false;
        this.be = false;
        this.bf = false;
        this.bi = false;
        this.bk = false;
        throw new RuntimeException("No-argument abljemtf constructor called");
    }
    
    abljemtf(final abljema t) {
        super(t);
        this.a = false;
        this.b = false;
        this.c = 'A';
        this.d = false;
        this.e = 'A';
        this.n = "Y";
        this.o = "N";
        this.y = false;
        this.a0 = false;
        this.a5 = false;
        this.a6 = false;
        this.be = false;
        this.bf = false;
        this.bi = false;
        this.bk = false;
        this.t = t;
        this.a7 = new abljemfs(this.t, this, true);
        this.hide();
        this.a();
    }
    
    abljemtf(final int n, final byte[] array, final int n2, final int n3, final abljema t) {
        super(t);
        this.a = false;
        this.b = false;
        this.c = 'A';
        this.d = false;
        this.e = 'A';
        this.n = "Y";
        this.o = "N";
        this.y = false;
        this.a0 = false;
        this.a5 = false;
        this.a6 = false;
        this.be = false;
        this.bf = false;
        this.bi = false;
        this.bk = false;
        this.t = t;
        this.a7 = new abljemfs(this.t, this, true);
        this.hide();
        if (n < 1) {
            return;
        }
        if (abljemtf.bz == null) {
            abljemtf.bz = new abljemtf(0, new byte[1], 0, 0, t);
        }
        this.ab = abljema.c(array, n2 + 1, 2);
        this.ac = abljema.c(array, n2 + 3, 3);
        this.ad = (this.ab - 1) * this.t.es + (this.ac - 1);
        this.ae = n;
        this.af = n;
        this.ag = array[n2 + 10];
        this.ah = array[n2 + 11];
        this.ai = array[n2 + 12];
        this.aj = array[n2 + 13];
        this.ak = array[n2 + 14];
        if (this.ak == 80) {
            this.disable();
        }
        this.am = 78;
        if (this.ak == 68) {
            this.ak = 78;
            this.am = 68;
        }
        this.al = array[n2 + 15];
        this.an = array[n2 + 16];
        if (this.an == 68) {
            this.an = (byte)((this.ae <= 2) ? 78 : 89);
        }
        if (!this.t.bx && this.ae <= 2 && !this.t.bu) {
            this.an = 78;
        }
        this.ao = array[n2 + 17];
        this.ap = abljema.e(new String(array, 0, n2 + 18, 2));
        this.av = 0;
        this.aw = 0;
        this.ax = 0;
        this.az = 0;
        this.a1 = 0;
        this.a2 = new String(array, 0, n2 + abljemtf.by, abljemtf.bw);
        this.a4 = false;
        if (this.ag == 83) {
            final byte[] array2 = new byte[abljemtf.bw];
            final int n4 = abljemtf.bw - 1;
            this.a2.getBytes(0, abljemtf.bw, array2, 0);
            if (n4 >= 1 && array2[n4] == 48) {
                final int n5 = n4 - 1;
                final byte[] array3 = array2;
                final int n6 = n5;
                --array3[n6];
                array2[n4] = 57;
            }
            else {
                final byte[] array4 = array2;
                final int n7 = n4;
                --array4[n7];
            }
            this.a2 = new String(array2, 0, 0, abljemtf.bw);
            --this.ae;
            if (this.ai != 90) {
                this.ai = 66;
            }
            this.an = 89;
        }
        this.w();
        if (this.ac + this.af > this.t.es) {
            this.af = this.t.es + 1 - this.ac;
        }
        int i;
        for (i = n; i > 0; --i) {
            if (array[n3 + this.ad + i - 1] != 95) {
                break;
            }
        }
        while (i > 0 && array[n3 + this.ad + i - 1] == 32) {
            --i;
        }
        String s;
        if (i > 0) {
            s = new String(array, 0, n3 + this.ad, i);
        }
        else {
            s = "";
        }
        if (this.ag == 83 && !this.t.bu) {
            if (i > 1 && s.charAt(i - 1) == '-') {
                int n8;
                for (n8 = 0; n8 < i && s.charAt(n8) == ' '; ++n8) {}
                final String s2 = new String(s);
                s = "-";
                if (n8 < i - 1) {
                    s = s.concat(s2.substring(n8, i - 1));
                }
                if (n8 > 0) {
                    s = abljema.iv.substring(0, n8).concat(s);
                }
            }
            else if (i > 0) {
                s = " ".concat(s);
            }
        }
        this.c(s);
        this.x();
        if (this.aj == 72) {
            this.b('*');
            for (int j = 0; j < this.ae; ++j) {
                array[n3 + this.ad + j] = 32;
            }
        }
        if (this.ak != 80 && this.ak != 69 && this.ab > 0 && this.ab <= this.t.fg.length && this.ae <= 2 && this.aj != 72) {
            if (this.t.fg[this.ab] == null) {
                this.t.fg[this.ab] = this;
                final abljema t2 = this.t;
                ++t2.ff;
            }
            else if (this.t.fg[this.ab] != abljemtf.bz) {
                this.t.fg[this.ab] = abljemtf.bz;
                final abljema t3 = this.t;
                --t3.ff;
            }
        }
        this.y();
        this.a();
        if (this.t.e4 == null) {
            this.t.e4 = this;
        }
    }
    
    private void w() {
        if (this.t.cm) {
            this.b(this.ae, this.ag == 83);
            this.c('T');
            super.n = false;
        }
    }
    
    public void a() {
        this.u = false;
        this.v = null;
        this.w = false;
    }
    
    public void a(final Enhanced.Rollbar g, final Vector vector) {
        this.g = g;
        this.i = new abljemp(vector);
        this.t.fb.add(this.i, 0);
    }
    
    public void a(final Enhanced.Selfield f, final Vector vector, final StringBuffer sb) {
        this.f = f;
        this.i = new abljemp(vector);
        this.c(utils.a(sb.toString()));
        this.x();
        this.t.fb.add(this.i, 0);
        if (this.ac > 2) {
            this.ac -= 2;
            this.ad -= 2;
        }
    }
    
    public void a(final String s) {
        this.c(s);
        this.x();
    }
    
    private void x() {
        this.a3 = super.av;
        this.a4 = false;
    }
    
    private void y() {
        final abljemf fb = this.t.fb;
        final jemQuadrantPanel ap = this.t.fb.ap;
        final Color black = Color.black;
        final Color white = Color.white;
        final Font ao = fb.ao;
        Color color;
        Color color2;
        if (fb.cb) {
            color = fb.b0;
            color2 = fb.by;
        }
        else {
            color = fb.c(this.ap);
            color2 = fb.a(this.ap);
        }
        if ((this.ap & 0x1) != 0x0) {
            if (ap != null && ap.i != null && ap.j != null) {
                color = ap.i;
                color2 = ap.j;
            }
            else {
                final Color color3 = color;
                color = color2;
                color2 = color3;
            }
        }
        this.c(fb.a(this.ap, color), fb.b(this.ap, color2));
        this.a(this.t.fb.ao);
    }
    
    public void a(final Font font) {
        this.setFont(this.t.fb.a(this.ap, font));
    }
    
    private void c(final Color ba, final Color bb) {
        final jemQuadrantPanel ap = this.t.fb.ap;
        this.ba = ba;
        this.bb = bb;
        this.bc = ((ap == null) ? null : ap.k);
        this.bd = ((ap == null) ? null : ap.l);
        if (this.bc == null) {
            this.bc = this.ba;
        }
        if (this.bd == null) {
            this.bd = this.bb;
        }
        this.setForeground(this.ba);
        this.setBackground(this.bb);
    }
    
    public void a(final char c) {
        this.y = (c == '1');
        if (this.y) {
            this.e = 'B';
            if (this.aj == 72) {
                this.b(' ');
            }
            if (this.t.cm) {
                this.aa = this.c('P');
                super.n = true;
            }
            this.x();
        }
        else if (this.t.cm && c == '2') {
            super.n = true;
        }
        this.c = this.e;
    }
    
    public void a(final abljemtf abljemtf, final byte aq) {
        abljemtf.aq = aq;
        abljemtf.al = (byte)((aq == 76) ? this.al : 78);
        abljemtf.an = (byte)((aq == 76) ? this.an : 78);
        if (abljemtf == this) {
            return;
        }
        abljemtf.ak = this.ak;
        abljemtf.am = this.am;
        abljemtf.au = this.au;
        abljemtf.ag = this.ag;
        abljemtf.ah = this.ah;
        if ("L123".indexOf((char)abljemtf.ai) < 0) {
            abljem.d("Continued field at " + abljemtf.ab + "," + abljemtf.ac + " has aln=" + abljemtf.ai);
        }
        abljemtf.aj = this.aj;
        abljemtf.ao = this.ao;
    }
    
    public abljemtf a(final abljema abljema, final int n, final int n2) {
        final abljemtf v = new abljemtf(abljema);
        final jemQuadrantPanel ap = v.t.fb.ap;
        v.c(super.av);
        if (this.p()) {
            v.b(super.ay);
        }
        v.w = this.w;
        v.x = this.x;
        v.ab = this.ab + n;
        v.ac = this.ac + n2;
        v.ad = (v.ab - 1) * abljema.es + (v.ac - 1);
        v.ae = this.ae;
        v.af = this.af;
        if (this.t.cm) {
            v.b(this.ae, this.ag == 83);
        }
        v.ag = this.ag;
        v.ah = this.ah;
        v.ai = this.ai;
        v.aj = this.aj;
        v.ak = this.ak;
        v.al = this.al;
        v.am = this.am;
        v.an = this.an;
        v.ao = this.ao;
        v.ap = this.ap;
        v.aq = this.aq;
        v.au = this.au;
        v.av = this.av;
        v.aw = this.aw;
        v.ax = this.ax;
        v.az = this.az;
        v.a0 = this.a0;
        v.a1 = this.a1;
        v.a2 = this.a2;
        v.a3 = this.a3;
        v.a4 = this.a4;
        v.a5 = this.a5;
        v.a6 = this.a6;
        v.f = this.f;
        v.g = this.g;
        v.h = this.h;
        v.j = this.j;
        v.be = this.be;
        v.n = this.n;
        v.o = this.o;
        v.bf = this.bf;
        v.bg = this.bg;
        v.q = this.q;
        v.r = this.r;
        v.bi = this.bi;
        v.bk = this.bk;
        v.bl = this.bl;
        v.bm = this.bm;
        v.bn = this.bn;
        v.bo = this.bo;
        v.bp = this.bp;
        v.bq = this.bq;
        v.bt = this.bt;
        v.s = this.s;
        v.k = this.k;
        v.l = this.l;
        v.m = this.m;
        if (ap != null && this.t.cm) {
            boolean b = false;
            boolean[] array = null;
            v.w();
            v.a(ap.c, ap.d);
            v.b(ap.e, ap.f);
            v.a(abljema.fb.bn);
            v.ah = (ap.o == '0');
            v.p = ((v.ap & 0x2) != 0x0 && v.t.fb.ca);
            if (this.t.er) {
                int n3;
                int ad;
                for (n3 = this.ad + this.ae, ad = this.ad; ad < n3 && this.t.eq[ad] == 0; ++ad) {}
                b = (ad < n3);
            }
            if (ap.d != null && ap.r == '1') {
                final Font[] d = ap.d;
                final int length = d.length;
                final int n4;
                if ((n4 = v.ap - 32) >= 0 && n4 < length && d[n4] != null) {
                    v.p = d[n4].isBold();
                }
                if (b) {
                    array = new boolean[length];
                    for (int i = 0; i < length; ++i) {
                        array[i] = (d[i] != null && d[i].isBold());
                    }
                }
            }
            if (b) {
                final char[] charArray = v.av.toCharArray();
                for (int length2 = charArray.length, j = 0, ad2 = this.ad; j < length2; ++j, ++ad2) {
                    final int n5 = this.t.eq[ad2];
                    if (n5 != 0) {
                        charArray[j] = (char)(63488 + n5);
                    }
                }
                v.c(new String(charArray));
                v.x();
                v.a(null, ap.f, null, array);
            }
            if (this.ai >= 49 && this.ai <= 51) {
                this.a(v);
            }
        }
        v.y();
        v.a8 = 0;
        v.a9 = 0;
        this.u = true;
        return this.v = v;
    }
    
    private void a(final abljemtf abljemtf) {
        int n = 0;
        final Vector vector = new Vector<abljemtf>();
        vector.addElement(this);
        int n2;
        if (this.aq <= 32) {
            n2 = this.z();
            n = this.aa();
        }
        else {
            if (this.aq != 70) {
                abljem.d("Field at " + this.ab + "," + this.ac + " aln=" + this.ai + " but conent=" + this.aq);
                return;
            }
            if (this.ai != 49 && this.ai != 50) {
                abljem.d("editmask error F12=" + this.ai);
                return;
            }
            n2 = this.z();
            for (int i = this.az + 1; i <= this.t.e2; ++i) {
                final abljemtf abljemtf2 = this.t.e0[i];
                vector.addElement(abljemtf2);
                if (abljemtf2.u) {
                    abljem.d("editmask error cloned");
                    return;
                }
                if (abljemtf2.ab != this.ab) {
                    abljem.d("editmask error in row " + abljemtf2.ab + "!=" + this.ab);
                    return;
                }
                switch (abljemtf2.aq) {
                    case 77: {
                        if (abljemtf2.ai != 50) {
                            abljem.d("editmask error M2=" + abljemtf2.ai);
                            return;
                        }
                        break;
                    }
                    case 76: {
                        if (abljemtf2.ai != 50 && abljemtf2.ai != 51) {
                            abljem.d("editmask error L23=" + abljemtf2.ai);
                            return;
                        }
                        break;
                    }
                    default: {
                        abljem.d("editmask error ML=" + abljemtf2.aq);
                        return;
                    }
                }
                if (abljemtf2.aq == 76) {
                    n = abljemtf2.aa();
                    break;
                }
            }
        }
        if (n <= n2) {
            abljem.d("editmask error EB " + n + "<=" + n2);
            return;
        }
        abljemtf.aq = 32;
        abljemtf.ae = n - n2;
        abljemtf.af = abljemtf.ae;
        abljemtf.ac -= this.ad - n2;
        abljemtf.ad -= this.ad - n2;
        if (this.t.cm) {
            abljemtf.b(abljemtf.ae, this.ag == 83);
        }
        final String s = new String(this.t.el, 0, n2, abljemtf.ae);
        abljemtf.c(utils.a(s));
        abljemtf.x();
        final int size = vector.size();
        abljemtf.ar = new String[size];
        abljemtf.as = new int[size];
        abljemtf.at = new int[size];
        for (int j = 0; j < size; ++j) {
            final abljemtf abljemtf3 = vector.elementAt(j);
            abljemtf.ar[j] = abljemtf3.a2;
            abljemtf.as[j] = abljemtf3.ad - n2;
            abljemtf.at[j] = abljemtf3.ae;
            abljemtf3.u = true;
        }
        final boolean[] array = new boolean[abljemtf.ae];
        for (int k = 0; k < abljemtf.ae; ++k) {
            array[k] = true;
        }
        for (int l = 0; l < size; ++l) {
            int n3 = abljemtf.as[l];
            for (int n4 = abljemtf.at[l]; n4 > 0; --n4) {
                array[n3] = false;
                ++n3;
            }
        }
        final char[] array2 = new char[abljemtf.ae];
        for (int n5 = 0; n5 < abljemtf.ae; ++n5) {
            array2[n5] = (array[n5] ? s.charAt(n5) : ' ');
        }
        if (this.t.cm) {
            abljemtf.a(array2, array);
        }
    }
    
    private int z() {
        final int ad = this.ad;
        if (this.ai != 50 && this.ai != 51) {
            return ad;
        }
        int n;
        int n2;
        for (n = (this.ab - 1) * this.t.es, n2 = ad - 2; n2 >= n && this.t.el[n2] != 32; --n2) {}
        if (n2 < n) {
            n2 = n - 1;
        }
        return ++n2;
    }
    
    private int aa() {
        final int n = this.ad + this.ae;
        if (this.ai != 49 && this.ai != 50) {
            return n;
        }
        int n2;
        int n3;
        for (n2 = this.ab * this.t.es, n3 = n + 1; n3 < n2 && this.t.el[n3] != 32; ++n3) {}
        if (n3 > n2) {
            n3 = n2;
        }
        return n3;
    }
    
    public abljemtf[] b() {
        if (this.f == null) {
            return new abljemtf[0];
        }
        Label_0186: {
            switch (this.f.g) {
                case 'S': {
                    switch (this.f.h) {
                        case 'F': {
                            return this.ac();
                        }
                        case 'L': {
                            return this.ac();
                        }
                        case 'B': {
                            return this.ad();
                        }
                        case 'P': {
                            return this.ae();
                        }
                        default: {
                            break Label_0186;
                        }
                    }
                    break;
                }
                case 'M': {
                    switch (this.f.h) {
                        case 'F': {
                            return this.ab();
                        }
                        case 'L': {
                            return this.ab();
                        }
                        case 'B': {
                            abljem.d("Multiple choice menubar not supported");
                            break Label_0186;
                        }
                        case 'P': {
                            abljem.d("Multiple choice pulldown not supported");
                            break Label_0186;
                        }
                    }
                    break;
                }
            }
        }
        return new abljemtf[0];
    }
    
    private abljemtf[] ab() {
        final int o = this.f.o;
        final abljemtf[] array = new abljemtf[o];
        this.c((String.valueOf(super.av) + abljema.iv.substring(0, this.ae)).substring(0, this.ae));
        this.a("S", " ", super.av.charAt(0) == 'S');
        this.q = this;
        for (int i = 0; i < o; ++i) {
            final abljemtf abljemtf = (i == 0) ? this : this.a(this.t, this.f.s[i].o, this.f.s[i].p);
            final char char1 = super.av.charAt(i);
            array[i] = abljemtf;
            abljemtf.r = i;
            abljemtf.a("S", " ", char1 == 'S');
            if (i > 0) {
                abljemtf.c(String.valueOf(char1));
            }
            abljemtf.x();
        }
        return array;
    }
    
    private abljemtf[] ac() {
        final int o = this.f.o;
        final int[] array = new int[o];
        final int[] array2 = new int[o];
        final String[] array3 = new String[o];
        final String[] array4 = new String[o];
        this.c((String.valueOf(super.av) + abljema.iv.substring(0, this.ae)).substring(0, this.ae));
        for (int i = 0; i < o; ++i) {
            super.av.charAt(i);
            array[i] = this.f.s[i].o;
            array2[i] = this.f.s[i].p;
            array3[i] = "";
            array4[i] = this.f.s[i].g;
        }
        this.a(0, array2, array, array3, array4);
        this.x();
        return new abljemtf[] { this };
    }
    
    private abljemtf[] ad() {
        return new abljemtf[0];
    }
    
    private abljemtf[] ae() {
        return new abljemtf[0];
    }
    
    public void enable() {
        super.enable();
        this.a7.b();
    }
    
    public void disable() {
        if (this.t != null && !this.a7.c(this.t.cm && super.a)) {
            return;
        }
        super.disable();
    }
    
    public void hide() {
        if (this.be && this.bh != null) {
            this.bh.hide();
        }
        else if (this.bi && this.bj != null) {
            this.bj.hide();
        }
        else if (this.bk && this.br != null) {
            for (int i = 0; i < this.br.length; ++i) {
                this.br[i].hide();
            }
        }
        else if (this.t != null && this.a7.a(this.t.cm && super.a)) {
            super.hide();
        }
        if (this.bu != null) {
            this.bu.hide();
        }
    }
    
    public void show() {
        if (this.be && this.t.i == 0) {
            this.be = false;
        }
        if (this.bi && this.t.i == 0) {
            this.bi = false;
        }
        if (this.bk && this.t.i == 0) {
            this.bk = false;
        }
        if (this.be) {
            if (this.bh == null) {
                this.af();
            }
            this.bh.show();
        }
        else if (this.bi) {
            if (this.bj == null) {
                this.ag();
            }
            this.bj.show();
        }
        else if (this.bk) {
            if (this.bs == null) {
                this.ai();
            }
            for (int i = 0; i < this.br.length; ++i) {
                this.br[i].show();
            }
        }
        else {
            super.show();
            this.a7.a();
        }
        if (this.bt != null && this.t.i > 0) {
            if (this.bu == null) {
                this.aj();
            }
            this.bu.show();
        }
    }
    
    public void c() {
        this.be = false;
        this.bi = false;
        this.bk = false;
        this.bt = null;
        this.s = null;
        this.k = null;
    }
    
    public void a(final String k, final String l, final char m) {
        this.k = k;
        this.l = l;
        this.m = m;
    }
    
    public void a(final String n, final String o, final boolean bf) {
        this.be = true;
        this.n = n;
        this.o = o;
        this.bf = bf;
    }
    
    public void d() {
        this.bi = true;
    }
    
    public void a(final boolean state) {
        if (this.bj != null && this.bj.getState() != state) {
            this.bj.setState(state);
        }
    }
    
    public void a(final int bl, final int[] bm, final int[] bn, final String[] bo, final String[] bp) {
        this.bk = true;
        this.bl = bl;
        this.bm = bm;
        this.bn = bn;
        this.bo = bo;
        this.bp = bp;
    }
    
    public void e() {
        if (this.bk && this.bl != 0) {
            for (int i = 0, ad = this.ad, n = this.ad + this.bl; i < this.af; ++i, ++ad, ++n) {
                this.t.el[n] = this.t.el[ad];
                this.t.el[ad] = 32;
            }
            this.ac += this.bl;
            this.ad += this.bl;
            this.bl = 0;
        }
    }
    
    private void af() {
        this.bh = this.ah();
        this.ak();
        this.bg = this.bh.getState();
    }
    
    private void ag() {
        (this.bj = this.ah()).a();
        if (!utils.b(super.av, "")) {
            this.c("");
        }
    }
    
    private abljembx ah() {
        this.am = 78;
        final abljembx abljembx = new abljembx(this.t, this, this.ab, this.ac);
        abljembx.a(null, this.j);
        abljembx.hide();
        this.getParent().add(abljembx);
        return abljembx;
    }
    
    private void ai() {
        this.am = 78;
        if (this.bm == null) {
            this.bk = false;
            return;
        }
        final int length = this.bm.length;
        if (length < 1) {
            this.bk = false;
            return;
        }
        this.bs = new CheckboxGroup();
        this.br = new abljemrb[length];
        for (int i = 0; i < length; ++i) {
            if (i == length - 1) {
                this.af = this.bm[i] + this.bp[i].length() + 2;
            }
            final abljemrb abljemrb = new abljemrb(this.t, this.ab + this.bn[i], this.ac + this.bm[i], this, this.bo[i], this.bp[i], this.bs);
            abljemrb.setFont(this.t.fb.y);
            abljemrb.hide();
            abljemrb.a(null, this.j);
            this.br[i] = abljemrb;
            this.getParent().add(abljemrb);
        }
        this.al();
        this.bq = (abljemrb)this.bs.getCurrent();
    }
    
    private void aj() {
        Component[] components;
        int n;
        for (components = this.getParent().getComponents(), n = components.length - 1; n > 0 && components[n] != this; --n) {}
        final abljemct bu = new abljemct(this.t, this, this.bt);
        this.bu = bu;
        this.getParent().add(bu, n);
    }
    
    public void a(final abljemrb abljemrb, final boolean b) {
        final char c = b ? 'S' : ' ';
        for (int i = 0; i < this.br.length; ++i) {
            if (this.br[i] == abljemrb) {
                if (c != super.av.charAt(i)) {
                    this.c(utils.a(super.av, i, c));
                }
                return;
            }
        }
    }
    
    public void a(final int n, final boolean b) {
        final char char1 = (b ? this.n : this.o).charAt(0);
        if (char1 != super.av.charAt(n)) {
            this.c(utils.a(super.av, n, char1));
        }
    }
    
    private void ak() {
        if (this.bh == null) {
            return;
        }
        String s = super.av;
        if (this.q != null && s.length() > 1) {
            s = s.substring(0, 1);
        }
        if (utils.d(s, this.n)) {
            this.bh.setState(true);
        }
        else if (utils.d(s, this.o)) {
            this.bh.setState(false);
        }
        else {
            this.bh.setState(this.bf);
            this.a4 = true;
        }
    }
    
    private void al() {
        if (this.br == null) {
            return;
        }
        final String av = super.av;
        for (int i = 0; i < this.br.length; ++i) {
            if (this.f != null) {
                if (av.charAt(i) == 'S') {
                    this.br[i].setState(true);
                    return;
                }
            }
            else if (av.equals(this.bo[i])) {
                this.br[i].setState(true);
                return;
            }
        }
    }
    
    public void a(final abljemrb abljemrb, final int n) {
        for (int i = 0; i < this.br.length; ++i) {
            if (this.br[i] == abljemrb) {
                int n2 = -1;
                switch (n) {
                    case 1004: {
                        for (int j = 0; j < this.br.length; ++j) {
                            if (this.bm[j] == this.bm[i] && this.bn[j] < this.bn[i] && (n2 == -1 || this.bn[j] > this.bn[n2])) {
                                n2 = j;
                            }
                        }
                        break;
                    }
                    case 1005: {
                        for (int k = 0; k < this.br.length; ++k) {
                            if (this.bm[k] == this.bm[i] && this.bn[k] > this.bn[i] && (n2 == -1 || this.bn[k] < this.bn[n2])) {
                                n2 = k;
                            }
                        }
                        break;
                    }
                    case 1006: {
                        for (int l = 0; l < this.br.length; ++l) {
                            if (this.bn[l] == this.bn[i] && this.bm[l] < this.bm[i] && (n2 == -1 || this.bm[l] > this.bm[n2])) {
                                n2 = l;
                            }
                        }
                        break;
                    }
                    case 1007: {
                        for (int n3 = 0; n3 < this.br.length; ++n3) {
                            if (this.bn[n3] == this.bn[i] && this.bm[n3] > this.bm[i] && (n2 == -1 || this.bm[n3] < this.bm[n2])) {
                                n2 = n3;
                            }
                        }
                        break;
                    }
                }
                if (n2 >= 0) {
                    this.br[n2].requestFocus();
                }
            }
        }
    }
    
    public void a(int n, final int n2, int n3, final int n4, final int n5, final int n6) {
        if (this.be && this.t.i == 0) {
            this.be = false;
        }
        if (this.bi && this.t.i == 0) {
            this.bi = false;
        }
        if (this.bk && this.t.i == 0) {
            this.bk = false;
        }
        if (this.be) {
            if (this.bh == null) {
                this.af();
            }
            if (this.q != null) {
                n3 -= (this.af - 1) * n5;
            }
            this.bh.setFont(new_font.a(this.getFont().getName(), 0, 10));
            if (n3 > 12) {
                final int n7 = (n3 - 12) / 2;
                n += n7;
                n3 -= n7;
            }
            this.bh.setBounds(n, n2, n3, n4);
        }
        else if (this.bi) {
            if (this.bj == null) {
                this.ag();
            }
            this.bj.setFont(new_font.a(this.getFont().getName(), 0, 10));
            this.bj.setBounds(n, n2, n3, n4);
            final abljembx c7 = this.t.fb.c7;
            if (c7 != null && c7.b == this.ab - 1) {
                c7.setBounds(n, n2 - n6, n3, n4);
                c7.c = this.ac;
            }
        }
        else if (this.bk) {
            if (this.br == null) {
                this.ai();
            }
            for (int i = 0; i < this.br.length; ++i) {
                final abljemrb abljemrb = this.br[i];
                final Font y = this.t.fb.y;
                final int size = y.getSize();
                abljemrb.setFont(new_font.a(y.getName(), y.getStyle(), size));
                abljemrb.setForeground(this.t.fb.b2[0]);
                final int n8 = n + this.bm[i] * n5;
                int n9 = n2 + this.bn[i] * n6;
                final int n10 = (this.bp[i].length() + 3) * n5;
                if (size <= 5) {
                    ++n9;
                }
                else if (size <= 6) {
                    n9 += 0;
                }
                else if (size <= 8) {
                    n9 += 2;
                }
                else if (size <= 10) {
                    n9 += 3;
                }
                else if (size <= 11) {
                    ++n9;
                }
                else if (size <= 12) {
                    n9 += 2;
                }
                else if (size <= 14) {
                    ++n9;
                }
                else if (size <= 18) {
                    n9 += 0;
                }
                else if (size <= 25) {
                    --n9;
                }
                else if (size <= 28) {
                    n9 -= 3;
                }
                else {
                    n9 -= 3;
                }
                abljemrb.setBounds(n8, n9, n10, n4 - 2);
            }
        }
        else if (this.bt != null && this.t.i > 0) {
            final int n11 = n4 - 4;
            n3 += n11;
            super.reshape(n, n2, n3, n4);
            if (this.bu == null) {
                this.aj();
            }
            this.bu.a(n + n3 - 2 - n11, n2 + 2, n11);
        }
        else {
            super.reshape(n, n2, n3, n4);
        }
    }
    
    public boolean getFocusTraversalKeysEnabled() {
        return false;
    }
    
    void a(final int ab, final int ac) {
        final int ad = this.ad;
        if (this.t.bu) {
            return;
        }
        this.ab = ab;
        this.ac = ac;
        this.ad = (this.ab - 1) * this.t.es + (this.ac - 1);
        this.a1 = this.ad - ad;
        this.c("");
        this.x();
    }
    
    public void b(final String s) {
        this.c(s);
        this.k();
    }
    
    public void c(final String s) {
        super.c(s);
        this.a4 = true;
    }
    
    public boolean f() {
        return this.a4 || super.av.compareTo(this.a3) != 0 || (this.bh != null && this.bh.getState() != this.bg) || (this.bs != null && this.bs.getCurrent() != this.bq);
    }
    
    public boolean g() {
        return super.av.trim().length() == 0;
    }
    
    public boolean keyDown(final Event event, int n) {
        final int length = super.av.length();
        int n2 = this.n();
        int o = this.o();
        if (this.t.fb.e0.a(event, this.t.fb.e1)) {
            return true;
        }
        if (n2 > 65535) {
            o = n2 / 65536;
            n2 &= 0xFFFF;
        }
        if (event.modifiers > 1 && n != 10 && (event.modifiers != 8 || (n != 68 && n != 100)) && (event.modifiers != 8 || n != 46)) {
            if (this.t.bu) {
                return this.a(event, event.key);
            }
            return super.keyDown(event, n);
        }
        else {
            if (n == 1017 && event.modifiers == 1 && this.t.cc && !this.t.bu) {
                this.disable();
                this.enable();
            }
            if (n > 0 && n < 256) {
                this.a6 = false;
            }
            if (this.t.bu) {
                n2 = this.t.fb.et - this.ad;
                if (n2 < 0) {
                    n2 = 0;
                }
                if (n2 >= this.ae) {
                    n2 = this.ae - 1;
                }
                o = n2 + (this.t.fb.er ? 0 : 1);
            }
            this.k();
            if (event.modifiers == 8 && (n == 68 || n == 100)) {
                if (this.am == 68) {
                    this.c(n2);
                    if (this.t.bu) {
                        this.a((Graphics)null);
                    }
                    if (this.al == 65) {
                        this.t.e();
                        return true;
                    }
                    this.b(event, 0, true);
                }
                return true;
            }
            if (n == 43 && event.modifiers == 0 && this.t.j.a5) {
                this.a6 = true;
                this.d(n2);
                n = 9;
            }
            if (n == 45 && event.modifiers == 0 && this.ag == 83 && (this.t.bu || (!this.g() && o - n2 != length))) {
                this.a6 = true;
                this.d(n2);
                this.c(super.av.concat("-"));
                n = 9;
            }
            if (n == 46) {
                if (event.modifiers == 8) {
                    event.modifiers = 0;
                }
                else if (this.t.j.t == '1' && event.modifiers == 0) {
                    n = 44;
                    event.key = n;
                }
            }
            if (event.modifiers == 8) {
                switch (n) {
                    case 92: {
                        n = 124;
                        event.key = n;
                        event.modifiers = 1;
                        this.t.b8 = this;
                        break;
                    }
                }
            }
            if (!this.t.cm && length >= this.ae && n2 == o && n >= 32 && n != 127 && n < 256) {
                if (this.ag != 83) {
                    return true;
                }
                if (length > this.ae) {
                    return true;
                }
                if (n != 45 && n != 43 && super.av.indexOf(45) < 0 && super.av.indexOf(43) < 0) {
                    return true;
                }
            }
            if (n == 9) {
                if (this.t.bu && event.modifiers == 1 && this.t.fb.et > this.ad) {
                    this.t.fb.et = this.ad;
                    this.a((Graphics)null);
                    return true;
                }
                if (event.key != 9 && this.al == 65 && (this.ai == 66 || this.ai == 90)) {
                    this.t.e();
                    return true;
                }
                event.key = n;
                this.b(event, event.modifiers, true);
                return true;
            }
            else {
                if (n == 10) {
                    switch (event.modifiers) {
                        case 0: {
                            if (this.t.j.a4) {
                                this.a6 = true;
                                if (this.t.bu) {
                                    this.d(n2);
                                }
                                if (this.al == 65 && (this.ai == 66 || this.ai == 90)) {
                                    this.t.e();
                                    return true;
                                }
                                this.b(event, 0, true);
                            }
                            else {
                                this.t.e();
                            }
                            return true;
                        }
                        case 2: {
                            if (this.t.ca) {
                                this.t.cb = !this.t.cb;
                                if (this.t.cb) {
                                    return true;
                                }
                            }
                            if (this.t.j.a4) {
                                this.t.e();
                            }
                            else {
                                this.a6 = true;
                                if (this.t.bu) {
                                    this.d(n2);
                                }
                                if (this.al == 65 && (this.ai == 66 || this.ai == 90)) {
                                    this.t.e();
                                    return true;
                                }
                                this.b(event, 0, true);
                            }
                            return true;
                        }
                        case 9:
                        case 10: {
                            this.t.fb.t();
                            return true;
                        }
                    }
                }
                if (n >= 32 && n < 256 && !this.b(n)) {
                    return true;
                }
                if (this.ah == 85 && n >= 97 && n <= 122) {
                    n = n + 65 - 97;
                    event.key = n;
                    if (this.t.b4) {
                        this.t.b5 = this;
                    }
                }
                if (n == 8 || (n >= 32 && n < 256)) {
                    this.a4 = true;
                }
                if (this.t.bu) {
                    return this.a(event, event.key);
                }
                final boolean keyDown = super.keyDown(event, event.key);
                if (event.key >= 32 && event.key < 256 && event.modifiers <= 1) {
                    this.a(event, event.key, false);
                }
                return keyDown;
            }
        }
    }
    
    private boolean b(final int n) {
        if (n == 127) {
            return true;
        }
        if (this.t.cm && !this.t.bu && this.a(n)) {
            return true;
        }
        switch (this.ag) {
            case 65: {
                return (n >= 97 && n <= 122) || (n >= 65 && n <= 90) || n == 44 || n == 46 || n == 45 || n == 32;
            }
            case 78: {
                return (n >= 48 && n <= 57) || n == 44 || n == 46 || n == 45 || n == 32 || n == 43;
            }
            case 68: {
                return n >= 48 && n <= 57;
            }
            case 83: {
                return (n >= 48 && n <= 57) || n == 45 || n == 43;
            }
            default: {
                return true;
            }
        }
    }
    
    public void a(final Event event) {
        if ((!this.t.j.b9 || event.id != 404 || (event.key != 1002 && event.key != 1003)) && event.id != 401 && event.id != 403) {
            return;
        }
        if (event.key == 9 || event.key == 10 || (event.key == 43 && event.modifiers == 0 && this.t.j.a5) || (event.key == 45 && event.modifiers == 0 && this.ag == 83) || (event.key == 127 && event.modifiers > 0)) {
            this.keyDown(event, event.key);
            return;
        }
        if (event.key == 1006 || event.key == 1007) {
            final int length = super.av.length();
            int n = this.n();
            int o = this.o();
            if (n > 65535) {
                o = n / 65536;
                n &= 0xFFFF;
            }
            if (n != o) {
                n = o;
            }
            switch (event.key) {
                case 1006: {
                    if (n > 0) {
                        --n;
                        break;
                    }
                    break;
                }
                case 1007: {
                    if (n < length) {
                        ++n;
                        break;
                    }
                    break;
                }
            }
            this.b(n, n);
            return;
        }
        if ((event.key == 8 || (event.key >= 32 && event.key < 255)) && event.modifiers < 2) {
            final char[] array = { '\0' };
            String s = super.av;
            final int length2 = s.length();
            int n2 = this.n();
            int o2 = this.o();
            if (n2 > 65535) {
                o2 = n2 / 65536;
                n2 &= 0xFFFF;
            }
            if (event.key == 8) {
                if (n2 > 0 && n2 == o2) {
                    --n2;
                }
                if (this.t.cm && !this.t.bu) {
                    this.a(n2, o2, "", true);
                }
                s = String.valueOf(s.substring(0, n2)) + ((o2 < length2) ? s.substring(o2) : "");
            }
            else if (event.key == 127) {
                if (n2 < length2 && n2 == o2) {
                    ++o2;
                }
                if (this.t.cm && !this.t.bu) {
                    this.a(n2, o2, "", false);
                }
                s = String.valueOf(s.substring(0, n2)) + ((o2 < length2) ? s.substring(o2) : "");
            }
            else {
                array[0] = (char)event.key;
                if (this.t() && o2 == n2 && o2 < length2) {
                    ++o2;
                }
                if (this.t.cm && !this.t.bu) {
                    this.a(n2, o2, new String(array, 0, 1), false);
                }
                if (length2 + n2 - o2 + 1 <= this.ae) {
                    s = String.valueOf(s.substring(0, n2)) + new String(array) + s.substring(o2);
                }
                if (n2 < this.ae) {
                    ++n2;
                }
            }
            if (!this.t.cm || this.t.bu) {
                this.c(s);
                this.b(n2, n2);
            }
            this.a4 = true;
            this.a(event, event.key, false);
            return;
        }
        event.target = this;
        this.t.fb.keyDown(event, event.key);
    }
    
    public boolean a(final Event event, final int n) {
        String s = super.av;
        final int length = s.length();
        int n2 = this.t.fb.et - this.ad;
        this.a5 = false;
        if (event.key == 22 && !this.t.fb.e0.h) {
            this.t.fb.ae();
            return true;
        }
        if (event.modifiers == 0 && n >= 1000 && n <= 1007) {
            if (n == 1004 && n2 >= this.ad + this.t.es) {
                final abljemf fb = this.t.fb;
                fb.et -= this.t.es;
            }
            else if (n == 1005 && n2 + this.t.es < this.ae) {
                final abljemf fb2 = this.t.fb;
                fb2.et += this.t.es;
            }
            else if (n == 1006 && n2 > 0) {
                final abljemf fb3 = this.t.fb;
                --fb3.et;
            }
            else {
                if (n != 1007 || n2 >= this.ae - 1) {
                    return false;
                }
                final abljemf fb4 = this.t.fb;
                ++fb4.et;
            }
            this.a((Graphics)null);
            return true;
        }
        if (event.modifiers >= 2 || n >= 256) {
            return false;
        }
        if (n2 < 0 || n2 >= this.ae) {
            return true;
        }
        if (n == 8 && n2 > 0) {
            final abljemf fb5 = this.t.fb;
            --fb5.et;
        }
        else if (n == 127) {
            if (n2 + 1 < length) {
                s = s.substring(0, n2).concat(s.substring(n2 + 1));
            }
            else if (n2 < length) {
                s = s.substring(0, n2);
            }
            if (this.a0) {
                this.t.c(this.ad + n2, this.ae - n2);
            }
        }
        else if (n != 8) {
            if (n == 27) {
                return true;
            }
            int n3 = n2;
            if (n3 < 0) {
                n3 = 0;
            }
            if (n3 >= this.ae) {
                n3 = this.ae - 1;
            }
            if (this.a0) {
                if (this.t.fb.er) {
                    this.t.d(this.ad + n3, this.ae - n3);
                }
                else {
                    this.t.b(this.ad + n3);
                }
            }
            if (n3 > length) {
                s = s.concat(abljema.iv.substring(0, n3 - length));
            }
            final String substring = s.substring(0, n3);
            final int n4 = this.t.fb.er ? n3 : (n3 + 1);
            String substring2;
            if (n4 >= length) {
                substring2 = "";
            }
            else {
                substring2 = s.substring(n4);
            }
            s = substring.concat(String.valueOf((char)n)).concat(substring2);
            s.length();
            if (++n2 >= this.ae) {
                this.a5 = true;
                n2 = this.ae - 1;
            }
            this.t.fb.et = this.ad + n2;
        }
        this.c(s);
        if (this.a5 && this.an == 78 && n != 127) {
            this.a((Graphics)null);
            if (this.al == 65) {
                this.t.e();
            }
            else {
                this.b(event, 0, false);
            }
            return true;
        }
        this.a((Graphics)null);
        return true;
    }
    
    private void c(int n) {
        final String av = super.av;
        if (this.a5 && n == this.ae - 1) {
            ++n;
        }
        if (this.t.cm && !this.t.bu) {
            this.a(n, av.length(), this.t.ir.substring(0, this.ae - n), false);
        }
        else {
            String s = av.substring(0, n);
            if (n < this.ae) {
                s = String.valueOf(s) + this.t.ir.substring(0, this.ae - n);
            }
            this.c(s);
        }
        this.a6 = true;
        this.a4 = true;
        this.a5 = true;
        this.d(this.t.ir);
    }
    
    private void d(int n) {
        final String av = super.av;
        final int length = av.length();
        if (this.a5 && n == this.ae - 1) {
            ++n;
        }
        if (n < length) {
            if (this.t.cm && !this.t.bu) {
                this.a(n, length, "", false);
            }
            else {
                this.c(av.substring(0, n));
            }
        }
        this.d(null);
    }
    
    private void d(final String s) {
        if (this.aq == 70 || this.aq == 77) {
            for (int i = this.az + 1; i <= this.t.e2; ++i) {
                final abljemtf abljemtf = this.t.e0[i];
                if (abljemtf.aq != 77 && abljemtf.aq != 76) {
                    abljem.d("wipe error ML=" + abljemtf.aq);
                    return;
                }
                abljemtf.c((s == null) ? "" : s.substring(0, abljemtf.ae));
                if (abljemtf.aq == 76) {
                    break;
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n >= 32 && n < 256 && event.modifiers <= 1) {
            return super.keyUp(event, n);
        }
        return this.a(event, n, true);
    }
    
    public boolean a(final Event event, int key, final boolean b) {
        if (this.t.ew) {
            return b && super.keyUp(event, key);
        }
        final String av = super.av;
        int length = av.length();
        int n = this.n() & 0xFFF;
        if (event.modifiers > 1) {
            return b && super.keyUp(event, key);
        }
        if (this.ae == length && this.an == 78 && n == this.ae && key >= 32 && key < 256 && key != 127) {
            if (this.al == 65) {
                this.t.e();
                return true;
            }
            if (this.t.e2 == 1) {
                this.b(0, 0);
            }
            else {
                this.b(event, 0, false);
            }
        }
        else {
            int ae = this.ae;
            if (this.ag == 83 && av.length() > 0 && (av.charAt(0) == ' ' || av.charAt(0) == '0' || av.indexOf(45) >= 0 || av.indexOf(43) >= 0)) {
                ++ae;
            }
            if (length > ae && n > 0) {
                if (this.t.cm && !this.t.bu) {
                    this.a(n - 1, n, "", false);
                }
                else {
                    String s = av.substring(0, n - 1);
                    if (length > n) {
                        s = s.concat(super.av.substring(n));
                    }
                    --n;
                    --length;
                    this.c(s);
                    this.b(n, n);
                }
            }
            final String av2 = super.av;
            final int length2 = av2.length();
            if (length2 > ae) {
                if (this.t.cm && !this.t.bu) {
                    this.a(ae - 1, length2, "", false);
                }
                else {
                    final String substring = av2.substring(0, ae);
                    final int n2 = ae;
                    this.c(substring);
                    this.b(n2, n2);
                }
            }
        }
        if (this.ah == 85 && key >= 97 && key <= 122) {
            key = key + 65 - 97;
            event.key = key;
        }
        return b && super.keyUp(event, key);
    }
    
    public void requestFocus() {
        if (this.t.j.ap) {
            return;
        }
        this.a = false;
        this.k();
        if (!this.t.bu && this.bh != null) {
            this.t.fx.b();
            this.bh.requestFocus();
            return;
        }
        if (!this.t.bu && this.bj != null) {
            this.t.fx.b();
            this.bj.requestFocus();
            return;
        }
        if (!this.t.bu && this.bs != null) {
            this.t.fx.b();
            abljemrb abljemrb = (abljemrb)this.bs.getCurrent();
            if (abljemrb == null) {
                abljemrb = this.br[0];
            }
            abljemrb.requestFocus();
            return;
        }
        if (this.b) {
            this.t.fx.b();
        }
        if (!this.t.bu) {
            this.t.fb.a("PI", 'F');
        }
        if (this.t.bu) {
            this.a5 = false;
            this.t.fx.b();
            if (this.t.fb.et < this.ad || this.t.fb.et >= this.ad + this.ae) {
                this.t.fb.et = this.ad;
            }
            if (this.t.fb.es != null) {
                this.t.fb.es.lostFocus(null, null);
            }
            (this.t.fb.es = this).a((Graphics)null);
        }
        else {
            super.requestFocus();
        }
    }
    
    protected void b(final boolean b) {
        final abljemtf d9 = this.t.fb.d9;
        if (d9 != null && d9 != this) {
            d9.b(false);
        }
        this.t.fb.d9 = (b ? this : null);
        if (b) {
            if (this.bc != this.ba) {
                this.setForeground(this.bc);
            }
            if (this.bd != this.bb) {
                this.setBackground(this.bd);
            }
        }
        else {
            if (this.bc != this.ba) {
                this.setForeground(this.ba);
            }
            if (this.bd != this.bb) {
                this.setBackground(this.bb);
            }
        }
        super.b(b);
    }
    
    public void h() {
        this.c = ' ';
    }
    
    public void i() {
        if (this.y) {
            this.c = 'C';
        }
        else {
            this.c = 'A';
        }
    }
    
    public void c(final boolean b) {
        if (this.y) {
            this.c = (b ? 'L' : 'R');
        }
        else {
            this.c = 'A';
        }
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        try {
            this.t.fb.i();
            if (!this.t.bu) {
                this.t.fb.a("F", 'P');
            }
            this.t.fx.b();
            this.k();
        }
        catch (Throwable t2) {
            abljem.d("TextField gotFocus ignored");
        }
        if (!this.t.bu) {
            this.t.fb.a("F", 'P');
        }
        this.j();
        final Event e2 = this.t.fb.e2;
        if (e2 != null) {
            try {
                final StyleEventArg styleEventArg = (StyleEventArg)e2.arg;
                if (styleEventArg.b == this.ab && styleEventArg.c == this.ac) {
                    styleEventArg.a(StyleEventArg.e, super.av);
                    styleEventArg.b = 0;
                    styleEventArg.c = 0;
                    this.t.fb.handleEvent(e2);
                }
            }
            catch (Throwable t) {
                abljem.d("field_focus_style_event failed");
                t.printStackTrace();
            }
            this.t.fb.e2 = null;
        }
        if (this.i != null) {
            this.i.a(this.getBounds(), this.t.fb.p());
            this.i.show();
        }
        return super.gotFocus(event, o);
    }
    
    public void j() {
        final boolean d = this.d;
        this.d = false;
        switch (this.c) {
            case 'A': {
                this.b(0, 0);
                this.d(d);
                break;
            }
            case 'L': {
                this.b(0, 0);
                break;
            }
            case 'R': {
                final int length = super.av.length();
                if (this.t() && this.ae > 0) {
                    this.b(length - 1, length - 1);
                    break;
                }
                this.b(length, length);
                break;
            }
            case 'C': {
                int n = this.t.e6 - this.ac;
                final int length2 = super.av.length();
                if (n < 0) {
                    n = 0;
                }
                if (n < length2) {
                    this.b(n, n);
                    break;
                }
                this.b(length2, length2);
                break;
            }
            default: {
                if (!this.t.cm) {
                    this.b(this.a8, this.a9);
                    break;
                }
                break;
            }
        }
        this.c = this.e;
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        Boolean b = null;
        if (!this.t.ew) {
            b = this.b(event);
        }
        if (b != null && b) {
            return true;
        }
        if (event == null) {
            return true;
        }
        if (this.i != null) {
            this.i.hide();
        }
        return super.lostFocus(event, o);
    }
    
    public Boolean b(final Event event) {
        this.a8 = this.n();
        this.a9 = this.o();
        if (this.a8 > 65535) {
            this.a9 = this.a8 / 65536;
            this.a8 &= 0xFFFF;
        }
        if (this.t.bu) {
            final int et = this.t.fb.et;
            this.t.fb.et = 0;
            this.a5 = false;
            if (this.a6 || (this.f() && !this.g())) {
                this.am();
            }
            this.a((Graphics)null);
            this.t.fb.et = et;
            this.t.fb.er = false;
            if (this.t.fb.es == this) {
                this.t.fb.es = null;
            }
            return Boolean.TRUE;
        }
        if (!this.t.cm) {
            try {
                this.b(0, 0);
            }
            catch (Throwable t) {}
        }
        if (this.t.b5 == this && !this.t.cm) {
            this.c(super.av.toUpperCase());
            this.t.b5 = null;
        }
        if (this.t.b8 == this) {
            this.c(super.av.replace('|', '¦'));
            this.t.b8 = null;
        }
        if (!this.t.bu && this.getParent() == this.t.fb) {
            this.a((Graphics)null);
        }
        return null;
    }
    
    public boolean action(final Event event, final Object o) {
        return !(event.target instanceof abljempu) || super.action(event, o);
    }
    
    public void a(final Graphics graphics) {
        String av = super.av;
        final int n = this.ae + ((this.ag == 83) ? 1 : 0);
        if (this.aj == 72) {
            av = "";
        }
        if (abljema.iv.length() >= n) {
            abljema.iv.getBytes(0, n, this.t.el, this.ad);
        }
        av.getBytes(0, av.length(), this.t.el, this.ad);
        if (this.t.bu) {
            if (this.a0) {
                this.t.fb.a(graphics, this.ap, this.ac, this.ad, n, this.av, this.aw);
            }
            else {
                this.t.fb.a(graphics, this.ap, '0', null, null, null, this.ac, this.ad, n, this.av, this.aw);
            }
        }
    }
    
    public void k() {
        final int n = (this.t.e5 - 1) * this.t.es + (this.t.e6 - 1);
        if (n < this.ad || n >= this.ad + this.ae) {
            this.t.e5 = this.ab;
            this.t.e6 = this.ac;
        }
    }
    
    private void b(final Event event, final int n, final boolean b) {
        if (this.t.bu) {
            this.lostFocus(null, null);
            this.t.fb.et = 0;
        }
        this.t.a(this.az, n, b);
    }
    
    private void am() {
        final String av = super.av;
        final int length = av.length();
        int ae = this.ae;
        if (this.ag == 83 && (av.indexOf(45) >= 0 || av.indexOf(43) >= 0)) {
            ++ae;
        }
        if (length < ae) {
            switch (this.ai) {
                case 66: {
                    this.c(abljema.iv.substring(0, ae - length).concat(av));
                    break;
                }
                case 90: {
                    this.c(abljema.iw.substring(0, ae - length).concat(av));
                    break;
                }
            }
        }
    }
    
    public String l() {
        int ai = 76;
        boolean b = false;
        boolean b2 = false;
        String s = "";
        if (this.ar != null) {
            final StringBuffer sb = new StringBuffer();
            final String av = super.av;
            final int length = av.length();
            for (int i = 0; i < this.ar.length; ++i) {
                sb.append(this.ar[i]);
                int n = this.as[i];
                for (int j = this.at[i]; j > 0; --j) {
                    sb.append((n < length) ? av.charAt(n) : ' ');
                    ++n;
                }
            }
            return sb.toString();
        }
        if (this.bh != null && this.f == null && (this.a4 || this.bh.getState() != this.bg)) {
            this.c(this.bh.getState() ? this.n : this.o);
            this.a4 = true;
        }
        if (this.bs != null && this.f == null && (this.a4 || this.bs.getCurrent() != this.bq)) {
            final abljemrb abljemrb = (abljemrb)this.bs.getCurrent();
            if (abljemrb != null) {
                this.c(abljemrb.c);
            }
            this.a4 = true;
        }
        String s2 = this.a2;
        String s3 = super.av;
        if (this.aa > 0) {
            final char[] charArray = s3.toCharArray();
            int n2 = charArray.length - 1;
            for (int aa = this.aa; aa > 0 && n2 >= 0 && charArray[n2] == ' '; --aa, --n2) {}
            s3 = new String(charArray, 0, n2 + 1);
        }
        if (this.am != 68 || s3.length() != this.ae || s3.charAt(this.ae - 1) != this.t.ir.charAt(0)) {
            if (this.a6 || (this.f() && !this.g())) {
                ai = this.ai;
            }
            if (this.ag == 78) {
                b2 = true;
                if (s3.indexOf(45) >= 0 && this.t.j.a6 == 'A') {
                    s3 = abljema.b(s3, '-');
                    b = true;
                }
                s3 = abljema.b(s3, '+');
            }
            if (this.ag == 83) {
                b2 = true;
                if (s3.indexOf(45) >= 0) {
                    s = "-";
                    s2 = this.a2.substring(0, abljemtf.bx).concat("-").concat(this.a2.substring(abljemtf.bx + 1));
                    s3 = abljema.b(s3, '-');
                }
                else if (this.t.bu) {
                    s = " ";
                }
                s3 = abljema.b(s3, '+');
            }
        }
        if (this.a1 > 0) {
            s3 = abljema.iv.substring(0, this.a1).concat(s3);
        }
        final int length2 = s3.length();
        if (length2 > this.ae) {
            s3 = s3.substring(0, this.ae);
        }
        if (length2 < this.ae) {
            b2 = true;
            switch (ai) {
                case 66: {
                    s3 = abljema.iv.substring(0, this.ae - length2).concat(s3);
                    break;
                }
                case 90: {
                    s3 = abljema.iw.substring(0, this.ae - length2).concat(s3);
                    break;
                }
                default: {
                    s3 = s3.concat(abljema.iv.substring(0, this.ae - length2));
                    break;
                }
            }
        }
        this.t.b5 = null;
        if (this.t.b4 && this.ag == 84 && this.ah == 85) {
            s3 = s3.toUpperCase();
        }
        final int length3 = s3.length();
        if (b && length3 > 0) {
            String s4 = null;
            switch (s3.charAt(length3 - 1)) {
                case ' ': {
                    s4 = "-";
                    break;
                }
                case '0': {
                    s4 = "}";
                    break;
                }
                case '1': {
                    s4 = "J";
                    break;
                }
                case '2': {
                    s4 = "K";
                    break;
                }
                case '3': {
                    s4 = "L";
                    break;
                }
                case '4': {
                    s4 = "M";
                    break;
                }
                case '5': {
                    s4 = "N";
                    break;
                }
                case '6': {
                    s4 = "O";
                    break;
                }
                case '7': {
                    s4 = "P";
                    break;
                }
                case '8': {
                    s4 = "Q";
                    break;
                }
                case '9': {
                    s4 = "R";
                    break;
                }
            }
            if (s4 != null) {
                s3 = s3.substring(0, length3 - 1).concat(s4);
            }
        }
        if (b2 && this.a1 == 0 && this.t.bu) {
            this.c(String.valueOf(s3) + s);
            this.a((Graphics)null);
        }
        if (this.t.cm && this.r()) {
            final char[] charArray2 = s3.toCharArray();
            for (int length4 = charArray2.length, k = 0; k < length4; ++k) {
                if ((charArray2[k] & '\uf800') == '\uf800') {
                    charArray2[k] = ' ';
                }
            }
            s3 = new String(charArray2);
        }
        return s2.concat(s3);
    }
    
    public String m() {
        if (!this.a0) {
            return null;
        }
        StringBuffer sb;
        try {
            sb = new StringBuffer(Styler.dd);
        }
        catch (Exception ex) {
            abljem.d("sai buffer failed " + ex);
            return null;
        }
        final int n = this.ad + this.ae;
        int n2 = 0;
        for (int i = this.ad; i <= n; ++i) {
            final int n3 = (i < n) ? this.t.eo[i] : 1;
            if (n3 == 0) {
                ++n2;
            }
            else {
                int j = n2 / 20;
                int n4 = n2 - j * 20;
                if (n4 == 0 && j > 0) {
                    n4 = 20;
                    --j;
                }
                while (j >= 20) {
                    sb.append("A");
                    sb.append("U");
                    j -= 20;
                }
                if (j > 0) {
                    sb.append("A");
                    sb.append((char)(65 + j));
                }
                if (n4 > 0) {
                    sb.append((char)(65 + n4));
                }
                n2 = 0;
                if (i < n) {
                    sb.append((char)(n3 & 0xFF));
                }
            }
        }
        final int length = sb.length();
        if (length < 1) {
            return null;
        }
        return "D    " + abljema.e(length, 4) + sb.toString();
    }
    
    static int a(final byte[] array, final int n, final int n2, final abljema abljema) {
        final int c = abljema.c(array, n + 6, 4);
        if (c < 1 || c > 2000) {
            abljem.d("jem error 51, " + c);
        }
        else {
            final abljemtf abljemtf = new abljemtf(c, array, n, n2, abljema);
            if (abljemtf == null) {
                abljem.d("jem error 52");
            }
            else if (abljemtf.ad > 0 && abljema.el[abljemtf.ad - 1] == 60) {
                abljema.el[abljemtf.ad - 1] = 32;
                for (int i = abljemtf.ad; i < abljema.eu; ++i) {
                    if (abljema.el[i] == 62) {
                        abljema.el[i] = 32;
                        break;
                    }
                }
                if (abljema.e1 > 0) {
                    final abljemtf abljemtf2 = abljema.ez[abljema.e1 - 1];
                    if (abljema.j.a9 && abljemtf2.aj == 72 && abljemtf2.ak != 80 && abljemtf2.ae == 1 && abljemtf2.ab == abljemtf.ab) {
                        if (abljemtf2.az == abljema.e2) {
                            abljema.e0[abljema.e2--] = null;
                        }
                        abljema.ez[abljema.e1--] = null;
                        if (!abljema.bu) {
                            abljema.fb.remove(abljemtf2);
                        }
                    }
                }
            }
            else {
                if (abljema.i == 0) {
                    abljemtf.a(abljema.fb.b4, abljema.fb.b5);
                    abljemtf.a(abljema.fb.bn);
                }
                if (abljemtf.ak != 80) {
                    ++abljema.e2;
                    abljemtf.az = abljema.e2;
                    abljema.e0[abljema.e2] = abljemtf;
                }
                abljema.ez[abljema.e1++] = abljemtf;
                if (!abljema.bu) {
                    abljema.fb.add(abljemtf);
                }
            }
        }
        return abljemtf.bv;
    }
    
    static {
        abljemtf.bv = 20;
        abljemtf.bw = 9;
        abljemtf.bx = 5;
        abljemtf.by = 1;
        abljemtf.bz = null;
    }
}
