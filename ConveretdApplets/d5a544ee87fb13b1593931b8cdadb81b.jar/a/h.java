// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class h extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private double br;
    private double bs;
    private boolean bt;
    private boolean bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;
    private String bA;
    private String bB;
    private String bC;
    private String bD;
    private String bE;
    private String bF;
    private String bG;
    private String[] bH;
    private int bI;
    private int bJ;
    private int bK;
    private double bL;
    private int bM;
    private int bN;
    private int bO;
    private boolean bP;
    private Color bQ;
    private Image bR;
    public double[] bS;
    public int[][] bT;
    private Color bU;
    private String[] bV;
    public boolean bW;
    public boolean bX;
    public boolean bY;
    public double bZ;
    public int[] ca;
    public int[][] cb;
    
    public h(final int bl, final int n, final int a, final int b, final Color g, final Color h, final int c) {
        this.bt = false;
        this.bu = false;
        this.bw = 4;
        this.bz = 1;
        this.bI = 1;
        this.bJ = -1;
        this.bM = 1;
        this.bN = 3;
        this.bP = true;
        this.bU = new Color(0, 0, 192);
        this.bW = false;
        this.bX = false;
        this.bY = false;
        this.bZ = 1.0;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bl;
        this.bm = n + 10;
        super.c = c;
        this.bp = super.a - 10;
        this.bq = super.b - 30;
    }
    
    public boolean a(final int l, final n k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        super.m = super.k.a.size();
        final double[] array = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bq -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bp -= 15 + (super.c + 2) / 2;
            if (super.ba) {
                this.bl += 15 + (super.c + 2) / 2;
            }
        }
        if (super.bh.compareTo(b("nf\u001aUJBc$OeIt")) == 0) {
            super.l += 2;
            this.bO = (this.bp - (this.bN + this.bN) * super.l + 1) / super.l;
        }
        if (super.bh.compareTo(b("ar\u0004Rb`n\u0006C")) == 0 || super.bh.compareTo(b("\u007fd\tR\u007fIu")) == 0 || super.bh.compareTo(b("nf\u001aUJBc$OeIt")) == 0) {
            if (!super.t || !super.v) {
                for (int i = 0; i < super.k.a.size(); ++i) {
                    final p p4 = super.k.a.elementAt(i);
                    for (int j = 0; j < super.k.c; ++j) {
                        if (p4.a[j] != null && !p4.a[j].s) {
                            if (!this.bu) {
                                this.bu = true;
                                this.br = p4.a[j].a;
                                this.bs = this.br;
                            }
                            else {
                                if (p4.a[j].a > this.br) {
                                    this.br = p4.a[j].a;
                                }
                                if (p4.a[j].a < this.bs) {
                                    this.bs = p4.a[j].a;
                                }
                            }
                        }
                    }
                }
                if (!this.bu || (this.br == 0.0 && this.bs == 0.0)) {
                    this.br = 5.0;
                    this.bs = 0.0;
                }
            }
            if (super.t) {
                this.bs = super.n;
            }
            if (super.v) {
                this.br = super.o;
            }
        }
        else if (super.bh.compareTo(b("ar\u0004Rbjn\u0004JnHK\u0001Hn")) == 0) {
            this.bS = new double[super.l];
            this.bT = new int[super.l][super.m + 1];
            for (int n = 0; n < super.l; ++n) {
                for (int n2 = 0; n2 <= super.m; ++n2) {
                    this.bT[n][n2] = 0;
                }
                this.bS[n] = 0.0;
            }
            for (int n3 = 0; n3 < super.m; ++n3) {
                final p p5 = super.k.a.elementAt(n3);
                for (int n4 = 0; n4 < super.l; ++n4) {
                    if (p5.a[n4] != null && !p5.a[n4].s) {
                        final double[] bs = this.bS;
                        final int n5 = n4;
                        bs[n5] += p5.a[n4].a;
                    }
                    else {
                        final double[] bs2 = this.bS;
                        final int n6 = n4;
                        bs2[n6] += 0.0;
                    }
                }
            }
            this.bs = 0.0;
            this.br = 0.0;
            for (int n7 = 0; n7 < super.l; ++n7) {
                if (this.bS[n7] > this.br) {
                    this.br = this.bS[n7];
                }
            }
            if (this.br == 0.0) {
                this.br = 5.0;
            }
        }
        else if (super.bh.compareTo(b("`n\u0006C")) == 0) {
            super.j = super.k.a.elementAt(0);
            int n8 = 0;
            if (!super.t || !super.v) {
                for (int n9 = 0; n9 < super.l; ++n9) {
                    if (super.j.a[n9] != null && !super.j.a[n9].s) {
                        if (n8 == 0) {
                            this.br = super.j.a[n9].a;
                            this.bs = this.br;
                            n8 = 1;
                        }
                        if (super.j.a[n9].a > this.br) {
                            this.br = super.j.a[n9].a;
                        }
                        if (super.j.a[n9].a < this.bs) {
                            this.bs = super.j.a[n9].a;
                        }
                    }
                }
                if (n8 == 0 || (this.br == 0.0 && this.bs == 0.0)) {
                    this.br = 5.0;
                    this.bs = 0.0;
                }
            }
            System.out.println(b("ku\u0001BXXf\u001aR6") + super.n);
            if (super.t) {
                this.bs = super.n;
            }
            if (super.v) {
                this.br = super.o;
            }
        }
        final double[] a = super.C.a(this.br, this.bs, super.t, super.v, super.R, super.S);
        this.br = a[0];
        this.bs = a[1];
        this.bL = a[2];
        this.bI = (int)a[3];
        final int n10 = (int)a[4];
        this.bH = new String[this.bI + 1];
        for (int bj = 0; bj <= this.bI; ++bj) {
            array[bj] = super.C.e(this.bs, super.C.d(this.bL, this.bI - bj));
            if (array[bj] == 0.0) {
                this.bJ = bj;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n10);
        d.setMinimumFractionDigits(n10);
        try {
            for (int n11 = 0; n11 <= this.bI; ++n11) {
                this.bH[n11] = String.valueOf(s2) + d.format(array[n11]) + s;
            }
        }
        catch (Exception ex) {}
        if (super.bh.compareTo(b("ar\u0004Rbjn\u0004JnHK\u0001Hn")) == 0 && super.be) {
            this.bV = new String[super.l];
            try {
                for (int n12 = 0; n12 < super.l; ++n12) {
                    this.bV[n12] = String.valueOf(s2) + d.format(this.bS[n12]) + s;
                }
            }
            catch (Exception ex2) {}
        }
        if (!super.bd) {
            this.bx = Math.max(super.C.c(String.valueOf(s2) + this.bH[0] + s, font), super.C.c(String.valueOf(s2) + this.bH[this.bI] + s, font));
            int n13 = 0;
            int n14 = 0;
            if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                if (super.j.a[0] != null && super.j.a[0].f != null) {
                    n13 = super.C.c(super.j.a[0].f, font);
                }
                if (super.j.a[super.l - 1] != null && super.j.a[super.l - 1].f != null) {
                    n14 = super.C.c(super.j.a[super.l - 1].f, font);
                }
            }
            else {
                if (super.k.b[0] != null) {
                    n13 = super.C.c(super.k.b[0], font);
                }
                if (super.k.b[super.l - 1] != null) {
                    n14 = super.C.c(super.k.b[super.l - 1], font);
                }
            }
            if (!super.ba) {
                this.by = Math.max(this.bx, n14);
                this.bl += n13 / 2;
                this.bp -= this.by / 2;
            }
            else {
                this.by = Math.max(this.bx, n13 / 2);
                this.bl += this.by;
                this.bp -= n14 / 2;
            }
        }
        if (super.H > 0 && super.I) {
            int n15 = 0;
            for (int n16 = 0; n16 < super.H; ++n16) {
                final q q = super.J[n16];
                if (q.e != null) {
                    final int c = super.C.c(q.e, font);
                    if (c > n15) {
                        n15 = c;
                    }
                }
            }
            if (!super.ba) {
                this.bl = this.bl + n15 + 3;
                this.bp = this.bp - n15 - 3;
            }
            else {
                this.bp = this.bp - n15 - 3;
            }
        }
        this.bp = this.bp - this.by / 2 - this.bx;
        int n17 = 0;
        if (super.bh.compareTo(b("`n\u0006C")) == 0) {
            for (int n18 = 0; n18 < super.l; ++n18) {
                if (super.j.a[n18].f != null) {
                    ++n17;
                }
            }
        }
        else {
            for (int n19 = 0; n19 < super.l; ++n19) {
                if (super.k.b[n19] != null) {
                    ++n17;
                }
            }
        }
        if (super.l > 1 && this.bp > 2) {
            final double n20 = super.l / this.bp;
            if (n20 <= 1.0) {
                this.bW = true;
                this.bX = false;
                this.bZ = n20;
                this.bn = this.bp;
                (this.ca = new int[super.l + 1])[0] = 0;
                this.ca[super.l - 1] = this.bn;
                final double n21 = this.bn / (super.l - 1);
                for (int n22 = 1; n22 < super.l - 1; ++n22) {
                    this.ca[n22] = (int)(n22 * n21);
                }
                final double n23 = n17 / super.l;
                if (n17 > 0 && n17 < 100 && n23 < 0.1) {
                    this.bY = true;
                    this.cb = new int[100][2];
                    n17 = 0;
                    for (int n24 = 0; n24 < super.l; ++n24) {
                        if (super.k.b[n24] != null) {
                            this.cb[n17][0] = this.ca[n24];
                            this.cb[n17][1] = n24;
                            ++n17;
                        }
                    }
                    for (int n25 = n17; n25 < 100; ++n25) {
                        this.cb[n25][0] = -1;
                    }
                }
                else if (n17 == 0) {
                    super.X = 1;
                    if (super.l > 20) {
                        final double n26 = this.bp / super.l;
                        if (n26 > 0.0) {
                            super.X = (int)Math.max(Math.round(20.0 / n26), 1L);
                        }
                    }
                }
                else if (n17 >= 2 && super.V == 1 && !super.L) {
                    final int n27 = (int)Math.max(Math.floor(n21), 1.0);
                    String[] b;
                    if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                        b = new String[super.l];
                        for (int n28 = 0; n28 < super.l; ++n28) {
                            b[n28] = super.j.a[n28].f;
                        }
                    }
                    else {
                        b = super.k.b;
                    }
                    final int a2 = super.C.a(b, super.l, n27, this.bl, super.a, font);
                    if (a2 == 2) {
                        super.K = true;
                    }
                    else if (a2 == 3) {
                        super.L = true;
                        super.M = super.C.a(b, super.l, font);
                        this.bq -= super.M;
                        super.X = (int)Math.max(Math.ceil(super.c * 2 / n27), 1.0);
                    }
                    if (super.K) {
                        this.bq -= super.c + 5;
                        if (super.N != null) {
                            this.bq -= super.c + 2;
                        }
                        this.bv = this.bq / this.bI;
                        this.bq = this.bv * this.bI;
                    }
                }
            }
            else {
                this.bW = true;
                this.bX = true;
                this.bZ = n20;
                this.bn = this.bp;
                (this.ca = new int[this.bp + 1])[0] = 0;
                this.ca[this.bp] = super.l - 1;
                for (int n29 = 1; n29 < this.bp; ++n29) {
                    this.ca[n29] = this.a(n29);
                }
                if (n17 > 0 && n17 < 100 && n17 / super.l / n20 < 0.1) {
                    this.bY = true;
                    this.cb = new int[100][2];
                    n17 = 0;
                    for (int n30 = 0; n30 < super.l; ++n30) {
                        String f;
                        if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                            f = super.j.a[n30].f;
                        }
                        else {
                            f = super.k.b[n30];
                        }
                        if (f != null) {
                            final int b2 = this.b(n30);
                            if (this.b(b2, n30) == -1) {
                                this.ca[b2] = n30;
                            }
                            this.cb[n17][0] = b2;
                            this.cb[n17][1] = n30;
                            ++n17;
                        }
                    }
                    for (int n31 = n17; n31 < 100; ++n31) {
                        this.cb[n31][0] = -1;
                    }
                }
                else if (n17 == 0) {
                    super.X = 20;
                }
                else if (n17 >= 2 && super.V == 1 && !super.L) {
                    super.L = true;
                    String[] b3;
                    if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                        b3 = new String[super.l];
                        for (int n32 = 0; n32 < super.l; ++n32) {
                            b3[n32] = super.j.a[n32].f;
                        }
                    }
                    else {
                        b3 = super.k.b;
                    }
                    this.bq -= super.C.a(b3, super.l, font);
                    super.X = super.c * 2;
                }
            }
        }
        if (!this.bY && super.l > 1 && super.U == 1 && n17 == 0) {
            if (this.bp / super.l < 2) {
                super.U = 2;
            }
            super.W = Math.round(this.bp / 10);
        }
        this.bv = this.bq / this.bI;
        this.bq = this.bv * this.bI;
        if (super.bh.compareTo(b("ar\u0004Rb`n\u0006C")) == 0 || super.bh.compareTo(b("\u007fd\tR\u007fIu")) == 0 || super.bh.compareTo(b("nf\u001aUJBc$OeIt")) == 0) {
            for (int n33 = 0; n33 < super.k.a.size(); ++n33) {
                final p p6 = super.k.a.elementAt(n33);
                for (int n34 = 0; n34 < super.k.c; ++n34) {
                    if (p6.a[n34] != null) {
                        if (!p6.a[n34].s && this.br - this.bs != 0.0) {
                            p6.a[n34].n = (int)(this.bq - (p6.a[n34].a - this.bs) / (this.br - this.bs) * this.bq);
                        }
                        else {
                            p6.a[n34].n = -1;
                        }
                    }
                }
            }
            if (super.H > 0 && super.m > 0) {
                for (int n35 = 0; n35 < super.H; ++n35) {
                    if (super.J[n35].f && (super.J[n35].g.startsWith(b("Xu\rHo\u0004")) || super.J[n35].g.startsWith(b("Mq\u000f\u000e")))) {
                        final int n36 = 0;
                        p p7 = null;
                        String s3;
                        if (super.J[n35].g.startsWith(b("Xu\rHo\u0004"))) {
                            s3 = String.valueOf(super.J[n35].g.charAt(6));
                        }
                        else {
                            s3 = String.valueOf(super.J[n35].g.charAt(4));
                        }
                        for (int n37 = 0; n37 < super.k.a.size(); ++n37) {
                            if (super.C.b(((p)super.k.a.elementAt(n37)).d, s3)) {
                                p7 = super.k.a.elementAt(n36);
                            }
                        }
                        if (p7 != null) {
                            if (super.J[n35].g.startsWith(b("Xu\rHo\u0004"))) {
                                double n38 = 0.0;
                                double n39 = 0.0;
                                double n40 = 0.0;
                                double n41 = 0.0;
                                double n42 = 0.0;
                                for (int n43 = 0; n43 < super.k.c; ++n43) {
                                    if (p7.a[n43] != null) {
                                        if (!p7.a[n43].s) {
                                            final double n44 = n43 + 1;
                                            final double a3 = p7.a[n43].a;
                                            n39 += n44;
                                            n40 += a3;
                                            n41 += n44 * a3;
                                            n42 += n44 * n44;
                                        }
                                        n38 = n43 + 1;
                                    }
                                }
                                final double n45 = (n38 * n41 - n39 * n40) / (n38 * n42 - n39 * n39);
                                final double n46 = (n40 - n45 * n39) / n38;
                                final double n47 = n45 + n46;
                                final double n48 = n45 * n38 + n46;
                                super.J[n35].i = (int)(this.bq - (n47 - this.bs) / (this.br - this.bs) * this.bq);
                                super.J[n35].j = (int)(this.bq - (n48 - this.bs) / (this.br - this.bs) * this.bq);
                                super.J[n35].h = true;
                            }
                            else {
                                double n49 = 0.0;
                                int n50 = 0;
                                for (int n51 = 0; n51 < super.k.c; ++n51) {
                                    if (p7.a[n51] != null && !p7.a[n51].s) {
                                        n49 += p7.a[n51].a;
                                        ++n50;
                                    }
                                }
                                if (n50 > 0) {
                                    super.J[n35].i = n49 / n50;
                                    super.J[n35].j = (int)(this.bq - (super.J[n35].i - this.bs) / (this.br - this.bs) * this.bq);
                                    super.J[n35].h = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (super.bh.compareTo(b("ar\u0004Rbjn\u0004JnHK\u0001Hn")) == 0) {
            for (int n52 = 0; n52 < super.m; ++n52) {
                final p p8 = super.k.a.elementAt(n52);
                for (int n53 = 0; n53 < super.l; ++n53) {
                    if (p8.a[n53] != null && !p8.a[n53].s) {
                        if (p8.a[n53].a != 0.0) {
                            this.bT[n53][n52] = this.bq - (int)(p8.a[n53].a / this.br * this.bq);
                        }
                        else {
                            this.bT[n53][n52] = this.bq;
                        }
                    }
                    else {
                        this.bT[n53][n52] = this.bq;
                    }
                }
            }
        }
        else if (super.bh.compareTo(b("`n\u0006C")) == 0) {
            super.j = super.k.a.elementAt(0);
            switch (this.bz) {
                case 1: {
                    for (int n54 = 0; n54 < super.l; ++n54) {
                        super.j.a[n54].n = (int)(this.bq - (super.j.a[n54].a - this.bs) / (this.br - this.bs) * this.bq);
                    }
                    break;
                }
                case 2: {
                    for (int n55 = 0; n55 < super.l; ++n55) {
                        super.j.a[n55].n = (int)(this.bq - (super.j.a[n55].a + this.br) / (this.br * 2.0) * this.bq);
                    }
                    break;
                }
                case 3: {
                    for (int n56 = 0; n56 < super.l; ++n56) {
                        super.j.a[n56].n = (int)(this.bq - (super.j.a[n56].a - this.bs) / (this.br - this.bs) * this.bq);
                    }
                    break;
                }
            }
        }
        if (this.br >= 0.0 && this.bs < 0.0) {
            this.bK = (int)(this.bq - (0.0 - this.bs) / (this.br - this.bs) * this.bq);
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.H > 0) {
            if (!super.bi) {
                super.bj = b("tH\u0006Jr");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null && !q.f) {
                    if (q.a(this.bs, this.br)) {
                        final int n4 = (int)(this.bq - (q.a() - this.bs) / (this.br - this.bs) * this.bq);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl, this.bm + n4, this.bl + this.bn, this.bm + n4);
                        if (q.e != null) {
                            int n5;
                            if (!super.ba) {
                                n5 = this.bl - super.C.c(q.e, font) - 3;
                            }
                            else {
                                n5 = this.bl + this.bn + 7;
                            }
                            final int n6 = this.bm + n4 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n5, n6);
                        }
                    }
                    else if (q.b(this.bs, this.br)) {
                        final double[] c = q.c(this.bs, this.br);
                        final double n7 = c[0];
                        final double n8 = c[1];
                        final int n9 = (int)(this.bq - (n7 - this.bs) / (this.br - this.bs) * this.bq);
                        final int n10 = (int)(this.bq - (n8 - this.bs) / (this.br - this.bs) * this.bq);
                        final int n11 = n9 - n10;
                        if (n11 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl, this.bm + n10, this.bn, n11);
                        }
                        if (q.e != null) {
                            int n12;
                            if (!super.ba) {
                                n12 = this.bl - super.C.c(q.e, font) - 3;
                            }
                            else {
                                n12 = this.bl + this.bn + 7;
                            }
                            final int n13 = this.bm + n9 - n11 / 2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n12, n13);
                        }
                    }
                }
            }
        }
        graphics.setColor(super.E);
        if (!super.C.b(super.bj, b("bh\u0006C"))) {
            if (super.Z) {
                graphics.drawLine(this.bl, this.bm, this.bl + this.bn, this.bm);
                graphics.drawLine(this.bl, this.bm + 1, this.bl + this.bn, this.bm + 1);
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
                graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bq);
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
                graphics.drawLine(this.bl + this.bn + 1, this.bm, this.bl + this.bn + 1, this.bm + this.bq);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl + this.bn, this.bm);
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
            }
        }
        if (super.l > 1 && (super.C.b(super.bj, b("mk\u0004")) || super.C.b(super.bj, b("tH\u0006Jr")))) {
            if (this.bY) {
                for (int j = 0; j < 100; ++j) {
                    if (this.cb[j][0] == -1) {
                        break;
                    }
                    if (super.Z) {
                        graphics.drawLine(this.bl + this.cb[j][0], this.bm, this.bl + this.cb[j][0], this.bm + this.bq);
                        graphics.drawLine(this.bl + this.cb[j][0] + 1, this.bm, this.bl + this.cb[j][0] + 1, this.bm + this.bq);
                    }
                    else {
                        graphics.drawLine(this.bl + this.cb[j][0], this.bm, this.bl + this.cb[j][0], this.bm + this.bq);
                    }
                }
            }
            else {
                int n14;
                if (!this.bX) {
                    n14 = super.l;
                }
                else {
                    n14 = this.bp;
                }
                for (int k = 0; k < n14; ++k) {
                    int n15;
                    if (!this.bX) {
                        n15 = this.bl + this.ca[k];
                    }
                    else {
                        final int n16 = this.ca[k];
                        n15 = this.bl + k;
                    }
                    if (n % super.X == 0) {
                        if (super.Z) {
                            graphics.drawLine(n15, this.bm, n15, this.bm + this.bq);
                            graphics.drawLine(n15 + 1, this.bm, n15 + 1, this.bm + this.bq);
                        }
                        else {
                            graphics.drawLine(n15, this.bm, n15, this.bm + this.bq);
                        }
                    }
                    ++n;
                }
            }
            if (super.C.b(super.bj, b("mk\u0004")) || super.C.b(super.bj, b("uH\u0006Jr"))) {
                for (int l = 1; l < this.bI; ++l) {
                    graphics.drawLine(this.bl, this.bm + this.bv * l, this.bl + this.bn, this.bm + this.bv * l);
                }
            }
        }
        if (super.bh.compareTo(b("ar\u0004Rb`n\u0006C")) == 0 || super.bh.compareTo(b("\u007fd\tR\u007fIu")) == 0) {
            for (int n17 = 0; n17 < super.k.a.size(); ++n17) {
                int n18 = 1;
                this.bt = true;
                final p p = super.k.a.elementAt(n17);
                this.bQ = p.c;
                int n19;
                if (!this.bX) {
                    n19 = super.l;
                }
                else {
                    n19 = this.bp;
                }
                for (int n20 = 0; n20 < n19; ++n20) {
                    int n21;
                    int n22;
                    if (!this.bX) {
                        n21 = n20;
                        n22 = this.bl + this.ca[n21];
                    }
                    else {
                        n21 = this.ca[n20];
                        n22 = this.bl + n20;
                    }
                    if (p.a[n21] != null) {
                        final int n23 = this.bm + p.a[n21].n;
                        if (p.a[n21].g != null) {
                            graphics.setColor(p.a[n21].g);
                        }
                        else {
                            graphics.setColor(this.bQ);
                        }
                        if (!p.a[n21].s) {
                            if (!super.z) {
                                if (super.G) {
                                    if (n18 == 0) {
                                        super.C.a(graphics, n2, n3, n22, n23, super.T, graphics.getColor());
                                    }
                                    else {
                                        n18 = 0;
                                    }
                                }
                                else {
                                    if (!this.bt) {
                                        super.C.a(graphics, n2, n3, n22, n23, super.T, graphics.getColor());
                                    }
                                    this.bt = false;
                                }
                            }
                            n2 = n22;
                            n3 = n23;
                            if (!super.F) {
                                p.a[n21].t = n2 - this.bw;
                                p.a[n21].u = n3 - this.bw;
                                p.a[n21].v = n2 + this.bw;
                                p.a[n21].w = n3 + this.bw;
                            }
                            if (super.be) {
                                int n24 = 3;
                                graphics.setColor(super.h);
                                if (super.z || super.y) {
                                    n24 = 7;
                                }
                                final String m = p.a[n21].k;
                                final int c2 = super.C.c(m, font);
                                int n25 = n2 - c2 / 2;
                                int n26 = n3 - n24;
                                if (n26 - super.c < this.bm) {
                                    n26 = n26 + n24 * 2 + super.c;
                                }
                                else if (n26 > this.bm + this.bq) {
                                    n26 = n26 - n24 * 2 - super.c;
                                }
                                if (n25 < this.bl) {
                                    n25 += c2 / 2;
                                }
                                else if (n25 + c2 > this.bl + this.bp) {
                                    n25 -= c2 / 2;
                                }
                                graphics.drawString(m, n25, n26);
                            }
                        }
                        else {
                            this.bt = true;
                        }
                    }
                }
            }
        }
        else if (super.bh.compareTo(b("ar\u0004Rbjn\u0004JnHK\u0001Hn")) == 0) {
            int n27;
            if (!this.bX) {
                n27 = super.l;
            }
            else {
                n27 = this.bp;
            }
            for (int n28 = 0; n28 < super.m; ++n28) {
                final p p2 = super.k.a.elementAt(n28);
                final Polygon polygon = new Polygon();
                graphics.setColor(p2.c);
                for (int n29 = n27 - 1; n29 > 0; --n29) {
                    int n30;
                    int n31;
                    if (!this.bX) {
                        n30 = n29;
                        n31 = this.bl + this.ca[n30];
                    }
                    else {
                        n30 = this.ca[n29];
                        n31 = this.bl + n29;
                    }
                    polygon.addPoint(n31, this.bm + (this.bq - this.bT[n30][super.m]));
                }
                polygon.addPoint(this.bl, this.bm + (this.bq - this.bT[0][super.m]));
                polygon.addPoint(this.bl, this.bm + this.bT[0][n28] - this.bT[0][super.m]);
                if (!super.F && p2.a[0] != null) {
                    p2.a[0].t = this.bl - this.bw;
                    p2.a[0].u = this.bm + this.bT[0][n28] - this.bT[0][super.m] - this.bw;
                    p2.a[0].v = this.bl + this.bw;
                    p2.a[0].w = this.bm + this.bT[0][n28] - this.bT[0][super.m] + this.bw;
                }
                this.bT[0][super.m] += this.bq - this.bT[0][n28];
                for (int n32 = 1; n32 < n27; ++n32) {
                    int n33;
                    int n34;
                    if (!this.bX) {
                        n33 = n32;
                        n34 = this.bl + this.ca[n33];
                    }
                    else {
                        n33 = this.ca[n32];
                        n34 = this.bl + n32;
                    }
                    polygon.addPoint(n34, this.bm + this.bT[n33][n28] - this.bT[n33][super.m]);
                    if (!super.F && p2.a[n33] != null && !p2.a[n33].s) {
                        p2.a[n33].t = n34 - this.bw;
                        p2.a[n33].u = this.bm + this.bT[n33][n28] - this.bT[n33][super.m] - this.bw;
                        p2.a[n33].v = n34 + this.bw;
                        p2.a[n33].w = this.bm + this.bT[n33][n28] - this.bT[n33][super.m] + this.bw;
                    }
                    this.bT[n33][super.m] += this.bq - this.bT[n33][n28];
                }
                graphics.fillPolygon(polygon);
            }
            if (super.be) {
                final int n35 = 3;
                graphics.setColor(super.h);
                for (int n36 = 0; n36 < super.l; ++n36) {
                    final String s = this.bV[n36];
                    final int c3 = super.C.c(s, font);
                    int n37;
                    if (!this.bX) {
                        n37 = this.bl + this.ca[n36];
                    }
                    else {
                        n37 = this.bl + n36;
                    }
                    int n38 = n37 - c3 / 2;
                    int n39 = this.bm - n35 + (this.bq - this.bT[n36][super.m]);
                    if (n39 - super.c < this.bm) {
                        n39 = n39 + n35 * 2 + super.c;
                    }
                    else if (n39 > this.bm + this.bq) {
                        n39 = n39 - n35 * 2 - super.c;
                    }
                    if (n38 < this.bl) {
                        n38 += c3 / 2;
                    }
                    else if (n38 + c3 > this.bl + this.bp) {
                        n38 -= c3 / 2;
                    }
                    graphics.drawString(s, n38, n39);
                }
            }
            for (int n40 = 0; n40 < super.l; ++n40) {
                this.bT[n40][super.m] = 0;
            }
        }
        else if (super.bh.compareTo(b("`n\u0006C")) == 0 && super.l > 1) {
            graphics.setColor(this.bU);
            super.j = super.k.a.elementAt(0);
            if (super.x) {
                if (super.l > 1) {
                    int n41;
                    if (!this.bX) {
                        n41 = super.l;
                    }
                    else {
                        n41 = this.bp;
                    }
                    final Polygon polygon2 = new Polygon();
                    polygon2.addPoint(this.bl, this.bm + super.j.a[0].n);
                    if (!super.F) {
                        super.j.a[0].t = this.bl - this.bw;
                        super.j.a[0].u = this.bm + super.j.a[0].n - this.bw;
                        super.j.a[0].v = this.bl + this.bw;
                        super.j.a[0].w = this.bm + super.j.a[0].n + this.bw;
                    }
                    for (int n42 = 1; n42 < n41; ++n42) {
                        int n43;
                        int n44;
                        if (!this.bX) {
                            n43 = n42;
                            n44 = this.bl + this.ca[n43];
                        }
                        else {
                            n43 = this.ca[n42];
                            n44 = this.bl + n42;
                        }
                        polygon2.addPoint(n44, this.bm + super.j.a[n43].n);
                        if (!super.F) {
                            super.j.a[n42].t = n44 - this.bw;
                            super.j.a[n42].u = this.bm + super.j.a[n43].n - this.bw;
                            super.j.a[n42].v = n44 + this.bw;
                            super.j.a[n42].w = this.bm + super.j.a[n43].n + this.bw;
                        }
                    }
                    if (!false) {
                        polygon2.addPoint(this.bl + this.bn, this.bm + this.bq);
                        polygon2.addPoint(this.bl, this.bm + this.bq);
                    }
                    else if (this.br == Math.abs(this.bs)) {
                        polygon2.addPoint(this.bl + this.bn, this.bm + this.bq / 2);
                        polygon2.addPoint(this.bl, this.bm + this.bq / 2);
                    }
                    else {
                        polygon2.addPoint(this.bl + this.bn, this.bm);
                        polygon2.addPoint(this.bl, this.bm);
                    }
                    graphics.fillPolygon(polygon2);
                }
            }
            else {
                int n45 = 0;
                int n46;
                if (!this.bX) {
                    n46 = super.l;
                }
                else {
                    n46 = this.bp;
                }
                int bl = this.bl;
                int n47 = this.bm + super.j.a[0].n;
                if (!super.F && !super.j.a[0].s) {
                    super.j.a[0].t = bl - this.bw;
                    super.j.a[0].u = n47 - this.bw;
                    super.j.a[0].v = bl + this.bw;
                    super.j.a[0].w = n47 + this.bw;
                }
                for (int n48 = 1; n48 < n46; ++n48) {
                    int n49;
                    int n50;
                    if (!this.bX) {
                        n49 = n48;
                        n50 = this.bl + this.ca[n49];
                    }
                    else {
                        n49 = this.ca[n48];
                        n50 = this.bl + n48;
                    }
                    final int n51 = this.bm + super.j.a[n48].n;
                    if (!super.j.a[n49].s) {
                        if ((super.G && n48 != 1) || !super.j.a[n45].s) {
                            super.C.a(graphics, bl, n47, n50, n51, super.T, graphics.getColor());
                        }
                        bl = n50;
                        n47 = n51;
                    }
                    else if (!super.G) {
                        bl = n50;
                        n47 = n51;
                    }
                    if (!super.F && !super.j.a[n49].s) {
                        super.j.a[n49].t = bl - this.bw;
                        super.j.a[n49].u = n47 - this.bw;
                        super.j.a[n49].v = bl + this.bw;
                        super.j.a[n49].w = n47 + this.bw;
                    }
                    n45 = n49;
                }
            }
            if (super.be) {
                graphics.setColor(super.h);
                int n52 = 3;
                if (super.y) {
                    n52 = 6;
                }
                for (int n53 = 0; n53 < super.l; ++n53) {
                    int n54;
                    if (!this.bX) {
                        n54 = this.bl + this.ca[n53];
                    }
                    else {
                        n54 = this.bl + n53;
                    }
                    final int n55 = this.bm + super.j.a[n53].n;
                    final String k2 = super.j.a[n53].k;
                    final int c4 = super.C.c(k2, font);
                    int n56 = n54 - c4 / 2;
                    int n57 = n55 - n52;
                    if (n57 - super.c < this.bm) {
                        n57 = n57 + n52 * 2 + super.c;
                    }
                    else if (n57 > this.bm + this.bq) {
                        n57 = n57 - n52 * 2 - super.c;
                    }
                    if (n56 < this.bl) {
                        n56 += c4 / 2;
                    }
                    else if (n56 + c4 > this.bl + this.bp) {
                        n56 -= c4 / 2;
                    }
                    graphics.drawString(k2, n56, n57);
                }
            }
        }
        else if (super.bh.compareTo(b("nf\u001aUJBc$OeIt")) == 0) {
            for (int n58 = 0; n58 < super.k.a.size(); ++n58) {
                int n59 = 1;
                this.bt = true;
                final p p3 = super.k.a.elementAt(n58);
                this.bQ = p3.c;
                int n60;
                if (!this.bX) {
                    n60 = super.l;
                }
                else {
                    n60 = this.bp;
                }
                for (int n61 = 0; n61 < n60; ++n61) {
                    int n62;
                    int n63;
                    if (!this.bX) {
                        n62 = n61;
                        n63 = this.bl + this.ca[n62 + 1];
                    }
                    else {
                        n62 = this.ca[n61];
                        n63 = this.bl + n61 + 1;
                    }
                    if (p3.a[n62] != null) {
                        final int u = this.bm + p3.a[n62].n;
                        if (p3.a[n62].g != null) {
                            graphics.setColor(p3.a[n62].g);
                        }
                        else {
                            graphics.setColor(this.bQ);
                        }
                        if (!p3.a[n62].s) {
                            if (n58 == 0) {
                                final int n64 = this.bq - u + this.bm;
                                graphics.fillRect(n63 - this.bO / 2, u, this.bO, n64);
                                if (!super.F) {
                                    p3.a[n62].t = n63 - this.bO / 2;
                                    p3.a[n62].u = u;
                                    p3.a[n62].v = n63 - this.bO / 2 + this.bO;
                                    p3.a[n62].w = u + n64;
                                }
                                if (super.be) {
                                    final int n65 = 3;
                                    graphics.setColor(super.h);
                                    final String k3 = p3.a[n62].k;
                                    final int c5 = super.C.c(k3, font);
                                    int n66 = n63 - c5 / 2;
                                    int n67 = u - n65;
                                    if (n67 - super.c < this.bm) {
                                        n67 = n67 + n65 * 2 + super.c;
                                    }
                                    else if (n67 > this.bm + this.bq) {
                                        n67 = n67 - n65 * 2 - super.c;
                                    }
                                    if (n66 < this.bl) {
                                        n66 += c5 / 2;
                                    }
                                    else if (n66 + c5 > this.bl + this.bp) {
                                        n66 -= c5 / 2;
                                    }
                                    graphics.drawString(k3, n66, n67);
                                }
                            }
                            else {
                                if (!super.z) {
                                    if (super.G) {
                                        if (n59 == 0) {
                                            super.C.a(graphics, n2, n3, n63, u, super.T, graphics.getColor());
                                        }
                                        else {
                                            n59 = 0;
                                        }
                                    }
                                    else {
                                        if (!this.bt) {
                                            super.C.a(graphics, n2, n3, n63, u, super.T, graphics.getColor());
                                        }
                                        this.bt = false;
                                    }
                                }
                                n2 = n63;
                                n3 = u;
                                if (!super.F) {
                                    p3.a[n62].t = n2 - this.bw;
                                    p3.a[n62].u = n3 - this.bw;
                                    p3.a[n62].v = n2 + this.bw;
                                    p3.a[n62].w = n3 + this.bw;
                                }
                                if (super.be) {
                                    int n68 = 3;
                                    graphics.setColor(super.h);
                                    if (super.z || super.y) {
                                        n68 = 7;
                                    }
                                    final String k4 = p3.a[n62].k;
                                    final int c6 = super.C.c(k4, font);
                                    int n69 = n2 - c6 / 2;
                                    int n70 = n3 - n68;
                                    if (n70 - super.c < this.bm) {
                                        n70 = n70 + n68 * 2 + super.c;
                                    }
                                    else if (n70 > this.bm + this.bq) {
                                        n70 = n70 - n68 * 2 - super.c;
                                    }
                                    if (n69 < this.bl) {
                                        n69 += c6 / 2;
                                    }
                                    else if (n69 + c6 > this.bl + this.bp) {
                                        n69 -= c6 / 2;
                                    }
                                    graphics.drawString(k4, n69, n70);
                                }
                            }
                        }
                        else {
                            this.bt = true;
                        }
                    }
                }
            }
        }
        graphics.setColor(super.g);
        graphics.fillRect(0, 0, super.d + 1, this.bm);
        graphics.fillRect(0, this.bm + this.bq, super.d + 1, super.e - (this.bm + this.bq));
        graphics.setColor(super.h);
        if (super.Z) {
            graphics.drawLine(this.bl, this.bm + this.bq, this.bl + this.bn, this.bm + this.bq);
            graphics.drawLine(this.bl, this.bm + 1 + this.bq, this.bl + this.bn, this.bm + 1 + this.bq);
            if (!super.ba) {
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
                graphics.drawLine(this.bl + 1 + this.bn, this.bm, this.bl + 1 + this.bn, this.bm + this.bq + 1);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
                graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bq + 1);
            }
        }
        else {
            graphics.drawLine(this.bl, this.bm + this.bq, this.bl + this.bn, this.bm + this.bq);
            if (!super.ba) {
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
            }
        }
        if ((this.br <= 0.0 || this.bs < 0.0) && super.l > 1) {
            this.bK += this.bm;
            if (super.Z) {
                graphics.drawLine(this.bl, this.bK, this.bl + this.bn, this.bK);
                graphics.drawLine(this.bl, this.bK + 1, this.bl + this.bn, this.bK + 1);
            }
            else {
                graphics.drawLine(this.bl, this.bK, this.bl + this.bn, this.bK);
            }
            if (this.bY) {
                for (int n71 = 0; n71 < 100; ++n71) {
                    if (this.cb[n71][0] == -1) {
                        break;
                    }
                    if (super.Z) {
                        graphics.drawLine(this.bl + this.cb[n71][0], this.bK - 2, this.bl + this.cb[n71][0], this.bK + 3);
                        graphics.drawLine(this.bl + this.cb[n71][0] + 1, this.bK - 2, this.bl + this.cb[n71][0] + 1, this.bK + 3);
                    }
                    else {
                        graphics.drawLine(this.bl + this.cb[n71][0], this.bK - 2, this.bl + this.cb[n71][0], this.bK + 2);
                    }
                }
            }
            else {
                int n72;
                if (!this.bX) {
                    n72 = super.l;
                }
                else {
                    n72 = this.bp;
                }
                for (int n73 = 0; n73 < n72; ++n73) {
                    int n74;
                    if (!this.bX) {
                        n74 = this.bl + this.ca[n73];
                    }
                    else {
                        final int n75 = this.ca[n73];
                        n74 = this.bl + n73;
                    }
                    if (n % super.X == 0) {
                        if (super.Z) {
                            graphics.drawLine(n74, this.bK - 2, n74, this.bK + 3);
                            graphics.drawLine(n74 + 1, this.bK - 2, n74 + 1, this.bK + 3);
                        }
                        else {
                            graphics.drawLine(n74, this.bK - 2, n74, this.bK + 2);
                        }
                    }
                    ++n;
                }
            }
        }
        if (super.Z) {
            for (int n76 = 0; n76 <= this.bI; ++n76) {
                if (!super.ba) {
                    graphics.drawLine(this.bl + this.bn - 2, this.bm + this.bv * n76, this.bl + this.bn + 3, this.bm + this.bv * n76);
                    graphics.drawLine(this.bl + this.bn - 2, this.bm + 1 + this.bv * n76, this.bl + this.bn + 3, this.bm + 1 + this.bv * n76);
                }
                else {
                    graphics.drawLine(this.bl - 2, this.bm + this.bv * n76, this.bl + 3, this.bm + this.bv * n76);
                    graphics.drawLine(this.bl - 2, this.bm + 1 + this.bv * n76, this.bl + 3, this.bm + 1 + this.bv * n76);
                }
            }
            for (int n77 = 0; n77 < super.l; ++n77) {
                if (super.X == 1 || n77 % super.X == 0) {
                    graphics.drawLine(this.bl + this.bo * n77, this.bm + this.bq - 2, this.bl + this.bo * n77, this.bm + this.bq + 3);
                    graphics.drawLine(this.bl + 1 + this.bo * n77, this.bm + this.bq - 2, this.bl + 1 + this.bo * n77, this.bm + this.bq + 3);
                }
            }
            if (super.l > 1) {
                if (this.bY) {
                    for (int n78 = 0; n78 < 100; ++n78) {
                        if (this.cb[n78][0] == -1) {
                            break;
                        }
                        graphics.drawLine(this.bl + this.cb[n78][0], this.bm + this.bq - 2, this.bl + this.cb[n78][0], this.bm + this.bq + 3);
                        graphics.drawLine(this.bl + this.cb[n78][0] + 1, this.bm + this.bq - 2, this.bl + this.cb[n78][0] + 1, this.bm + this.bq + 3);
                    }
                }
                else {
                    int n79;
                    if (!this.bX) {
                        n79 = super.l;
                    }
                    else {
                        n79 = this.bp;
                    }
                    int n80 = 0;
                    for (int n81 = 0; n81 < n79; ++n81) {
                        int n82;
                        if (!this.bX) {
                            n82 = this.bl + this.ca[n81];
                        }
                        else {
                            final int n83 = this.ca[n81];
                            n82 = this.bl + n81;
                        }
                        if (n80 % super.X == 0) {
                            graphics.drawLine(n82, this.bm + this.bq - 2, n82, this.bm + this.bq + 3);
                            graphics.drawLine(n82 + 1, this.bm + this.bq - 2, n82 + 1, this.bm + this.bq + 3);
                        }
                        ++n80;
                    }
                }
            }
        }
        else {
            for (int n84 = 0; n84 <= this.bI; ++n84) {
                if (!super.ba) {
                    graphics.drawLine(this.bl + this.bn - 2, this.bm + this.bv * n84, this.bl + this.bn + 2, this.bm + this.bv * n84);
                }
                else {
                    graphics.drawLine(this.bl - 2, this.bm + this.bv * n84, this.bl + 2, this.bm + this.bv * n84);
                }
            }
            if (super.l > 1) {
                if (this.bY) {
                    for (int n85 = 0; n85 < 100; ++n85) {
                        if (this.cb[n85][0] == -1) {
                            break;
                        }
                        graphics.drawLine(this.bl + this.cb[n85][0], this.bm + this.bq - 2, this.bl + this.cb[n85][0], this.bm + this.bq + 2);
                    }
                }
                else {
                    int n86;
                    if (!this.bX) {
                        n86 = super.l;
                    }
                    else {
                        n86 = this.bp;
                    }
                    int n87 = 0;
                    for (int n88 = 0; n88 < n86; ++n88) {
                        int n89;
                        if (!this.bX) {
                            n89 = this.bl + this.ca[n88];
                        }
                        else {
                            final int n90 = this.ca[n88];
                            n89 = this.bl + n88;
                        }
                        if (n87 % super.X == 0) {
                            graphics.drawLine(n89, this.bm + this.bq - 2, n89, this.bm + this.bq + 2);
                        }
                        ++n87;
                    }
                }
            }
        }
        if (!super.bd) {
            if (!super.ba) {
                for (int n91 = 0; n91 <= this.bI; ++n91) {
                    graphics.drawString(this.bH[n91], this.bl + this.bn + 5, this.bm + this.bv * n91 + 4);
                }
            }
            else {
                for (int n92 = 0; n92 <= this.bI; ++n92) {
                    graphics.drawString(this.bH[n92], this.bl - 5 - super.C.c(this.bH[n92], font), this.bm + this.bv * n92 + 4);
                }
            }
        }
        int n93 = 0;
        if (super.bh.compareTo(b("nf\u001aUJBc$OeIt")) == 0) {
            n93 = 1;
        }
        if (super.l > 1) {
            if (this.bY) {
                for (int n94 = 0; n94 < 100; ++n94) {
                    if (this.cb[n94][0] == -1) {
                        break;
                    }
                    String f;
                    if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                        f = super.j.a[this.cb[n94][1]].f;
                    }
                    else {
                        f = super.k.b[this.cb[n94][1]];
                    }
                    this.a(f, this.bl + this.cb[n94][0], this.bm + this.bq, font, graphics);
                }
            }
            else {
                int n95;
                if (!this.bX) {
                    n95 = super.l;
                }
                else {
                    n95 = this.bp;
                }
                int n96 = 0;
                for (int n97 = 0; n97 < n95; ++n97) {
                    int n98;
                    int n99;
                    if (!this.bX) {
                        n98 = n97;
                        n99 = this.bl + this.ca[n98 + n93];
                    }
                    else {
                        n98 = this.ca[n97];
                        n99 = this.bl + n97 + n93;
                    }
                    String f2;
                    if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                        f2 = super.j.a[n98].f;
                    }
                    else {
                        f2 = super.k.b[n98];
                    }
                    if (n96 % super.X == 0) {
                        this.a(f2, n99, this.bm + this.bq, font, graphics);
                    }
                    ++n96;
                }
            }
        }
        if (super.y) {
            if (super.bh.compareTo(b("`n\u0006C")) == 0) {
                graphics.setColor(this.bU);
                for (int n100 = 0; n100 < super.l; ++n100) {
                    if (!super.j.a[n100].s && (((!super.v || super.j.a[n100].a <= super.o) && (!super.t || super.j.a[n100].a >= super.n)) || !super.r) && super.j.a[n100].t != 0) {
                        if (super.j.a[n100].g != null) {
                            graphics.setColor(super.j.a[n100].g);
                            graphics.fillRect(super.j.a[n100].t, super.j.a[n100].u, this.bw * 2, this.bw * 2);
                            graphics.setColor(this.bU);
                        }
                        else {
                            graphics.fillRect(super.j.a[n100].t, super.j.a[n100].u, this.bw * 2, this.bw * 2);
                        }
                    }
                }
            }
            else {
                final boolean b = false;
                for (int n101 = 0; n101 < super.k.a.size(); ++n101) {
                    final p p4 = super.k.a.elementAt(n101);
                    graphics.setColor(p4.c);
                    for (int n102 = 0; n102 < super.k.c; ++n102) {
                        if (p4.a[n102] != null && !p4.a[n102].s && (((!super.v || p4.a[n102].a <= super.o) && (!super.t || p4.a[n102].a >= super.n)) || !super.r) && p4.a[n102].t != 0) {
                            if (p4.a[n102].g != null) {
                                graphics.setColor(p4.a[n102].g);
                                if (b) {
                                    graphics.fillRect(p4.a[n102].t, p4.a[n102].u, this.bw * 2, this.bw * 2);
                                }
                                else {
                                    graphics.fillRect(p4.a[n102].t, p4.a[n102].u, this.bw * 2, this.bw * 2);
                                }
                                graphics.setColor(p4.c);
                            }
                            else if (b) {
                                super.C.a(graphics, 4, p4.a[n102].t, p4.a[n102].u);
                            }
                            else {
                                graphics.fillRect(p4.a[n102].t, p4.a[n102].u, this.bw * 2, this.bw * 2);
                            }
                        }
                    }
                }
            }
        }
        if (super.H > 0) {
            for (int n103 = 0; n103 < super.H; ++n103) {
                final q q2 = super.J[n103];
                if (q2 != null && q2.f && q2.h && (super.bh.compareTo(b("ar\u0004Rb`n\u0006C")) == 0 || super.bh.compareTo(b("\u007fd\tR\u007fIu")) == 0)) {
                    int bl2;
                    int n104;
                    if (!this.bX) {
                        final int l2 = super.l;
                        bl2 = this.bl + this.ca[0];
                        n104 = this.bl + this.ca[l2 - 1];
                    }
                    else {
                        final int bp = this.bp;
                        final int n105 = this.ca[0];
                        bl2 = this.bl;
                        final int n106 = this.ca[bp - 1];
                        n104 = this.bl + bp - 1;
                    }
                    int n107;
                    int n108;
                    if (q2.g.startsWith(b("Xu\rHo\u0004"))) {
                        n107 = (int)(this.bm + q2.i);
                        n108 = (int)(this.bm + q2.j);
                    }
                    else {
                        n107 = (n108 = (int)(this.bm + q2.j));
                    }
                    graphics.setColor(q2.d);
                    super.C.a(graphics, bl2, n107, n104, n108, super.T, graphics.getColor());
                    if (q2.e != null) {
                        int n109;
                        int n110;
                        if (!super.ba) {
                            n109 = this.bl - super.C.c(q2.e, font) - 3;
                            n110 = n107 + super.C.b(q2.e, font) / 2 - 1;
                        }
                        else {
                            n109 = n104 + 7;
                            n110 = n108 + super.C.b(q2.e, font) / 2 - 1;
                        }
                        graphics.setColor(super.h);
                        graphics.drawString(q2.e, n109, n110);
                    }
                }
            }
        }
        this.b(graphics);
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        graphics.setColor(super.D);
        if (super.bh.compareTo(b("`n\u0006C")) == 0) {
            for (int i = 0; i < super.l; ++i) {
                if (o == super.j.a[i]) {
                    graphics.fillRect(super.j.a[i].t, super.j.a[i].u, this.bw * 2, this.bw * 2);
                    return;
                }
            }
            return;
        }
        if (super.bh.compareTo(b("nf\u001aUJBc$OeIt")) == 0) {
            for (int j = 0; j < super.k.a.size(); ++j) {
                final p p2 = super.k.a.elementAt(j);
                int k = 0;
                while (k < super.k.c) {
                    if (o == p2.a[k]) {
                        if (j != 0) {
                            graphics.fillRect(p2.a[k].t, p2.a[k].u, this.bw * 2, this.bw * 2);
                            break;
                        }
                        graphics.fillRect(p2.a[k].t, p2.a[k].u, p2.a[k].v - p2.a[k].t, p2.a[k].w - p2.a[k].u);
                        break;
                    }
                    else {
                        ++k;
                    }
                }
            }
            return;
        }
        for (int l = 0; l < super.k.a.size(); ++l) {
            final p p3 = super.k.a.elementAt(l);
            for (int n = 0; n < super.k.c; ++n) {
                if (o == p3.a[n]) {
                    graphics.fillRect(p3.a[n].t, p3.a[n].u, this.bw * 2, this.bw * 2);
                    break;
                }
            }
        }
    }
    
    public o a(final int n, final int n2) {
        for (int i = super.m - 1; i >= 0; --i) {
            final p p2 = super.k.a.elementAt(i);
            int j = 0;
            while (j < super.l) {
                if (p2.a[j] != null && n >= p2.a[j].t && n <= p2.a[j].v && n2 >= p2.a[j].u && n2 <= p2.a[j].w) {
                    if (((!super.v || p2.a[j].a <= super.o) && (!super.t || p2.a[j].a >= super.n)) || !super.r) {
                        return p2.a[j];
                    }
                    return null;
                }
                else {
                    ++j;
                }
            }
        }
        return null;
    }
    
    public void b(final Graphics graphics) {
        final Font font = new Font(super.f, 1, super.c + 2);
        graphics.setFont(font);
        graphics.setColor(super.h);
        if (super.N != null) {
            if (super.L) {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bp), this.bm + this.bq + super.M + (super.c + 17));
            }
            else {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bp), this.bm + super.b - (super.c + 2));
            }
        }
        if (super.O != null) {
            if (!super.ba) {
                super.C.a(super.a - (super.c + 2) / 2, this.bm, graphics, super.O, super.c + 2, this.bq);
                return;
            }
            super.C.a((super.c + 2) / 2, this.bm, graphics, super.O, super.c + 2, this.bq);
        }
    }
    
    public int a(final int n) {
        return (int)Math.round((super.l - 1) / this.bp * n);
    }
    
    public int b(final int n) {
        return (int)Math.round(n * (this.bp / (super.l - 1)));
    }
    
    public int b(final int n, final int n2) {
        int n3 = -1;
        if (this.ca[n] == n2) {
            n3 = n;
        }
        return n3;
    }
    
    public void a(final String s, final int n, final int n2, final Font font, final Graphics graphics) {
        if (s != null) {
            if (!super.L) {
                if (!super.K) {
                    graphics.drawString(s, n - super.C.a(s, font), n2 + 17);
                }
                else if (this.bP) {
                    graphics.drawString(s, n - super.C.a(s, font), n2 + 17);
                }
                else {
                    graphics.drawString(s, n - super.C.a(s, font), n2 + 23 + super.c);
                }
                if (this.bP) {
                    this.bP = false;
                    return;
                }
                this.bP = true;
            }
            else {
                this.bR = super.C.a(s, font, true, super.h, super.g);
                if (this.bR != null) {
                    graphics.drawImage(this.bR, n - super.c / 2, n2 + 10, null);
                }
                this.bR = null;
            }
        }
    }
    
    public void a(final Color bu) {
        this.bU = bu;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = ',';
                    break;
                }
                case 1: {
                    c2 = '\u0007';
                    break;
                }
                case 2: {
                    c2 = 'h';
                    break;
                }
                case 3: {
                    c2 = '&';
                    break;
                }
                default: {
                    c2 = '\u000b';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
