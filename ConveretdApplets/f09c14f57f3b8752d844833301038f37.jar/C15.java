import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Font;
import java.awt.AWTException;
import java.util.Vector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class C15
{
    int K;
    public Graphics L;
    Component M;
    int[] N;
    public int O;
    public boolean P;
    public boolean Q;
    boolean R;
    int S;
    public Color T;
    int U;
    int V;
    int W;
    int[] X;
    Color Y;
    static Color Z;
    boolean ba;
    int[] bc;
    Color bd;
    int be;
    public static int bf;
    double bh;
    public boolean bi;
    double bj;
    double bk;
    double bl;
    C38 bn;
    Vector bo;
    int bp;
    int bq;
    Color br;
    public int bs;
    public boolean bt;
    int bv;
    int bw;
    double bx;
    double by;
    boolean bz;
    public Graphics bA;
    int bB;
    int bC;
    int[] bD;
    boolean bE;
    int bF;
    boolean bG;
    int bH;
    int[] bJ;
    int[] bK;
    int bL;
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.L.drawLine(n, n2, n3, n4);
    }
    
    public C15(final Component m, final Graphics graphics, final int bs, final int o) {
        this.Q = false;
        this.bt = false;
        this.bi = false;
        this.P = false;
        this.bK = new int[4];
        this.N = new int[4];
        this.R = false;
        this.bD = new int[200];
        this.bJ = new int[200];
        this.bC = 0;
        this.bv = 0;
        this.ba = false;
        this.L = graphics;
        this.bA = graphics;
        this.bs = bs;
        this.O = o;
        this.M = m;
        this.bo = new Vector();
        this.X = new int[5];
        this.bc = new int[5];
    }
    
    public void c(final int[] array, final int[] array2, final int n) {
        if (this.bz) {
            this.u(array, array2, n);
        }
        if (C15.bf == 0) {
            final String property = System.getProperty("java.vendor");
            final String property2 = System.getProperty("java.version");
            if (property != null && property.toLowerCase().indexOf("netscape") != -1 && property2 != null && property2.equals("1.02")) {
                C15.bf = 1;
            }
            else {
                try {
                    this.L.drawPolyline(array, array2, n);
                    C15.bf = 2;
                    return;
                }
                catch (Throwable t) {
                    C15.bf = 1;
                }
            }
        }
        if (C15.bf == 2) {
            try {
                this.L.drawPolyline(array, array2, n);
                return;
            }
            catch (Throwable t2) {
                C15.bf = 1;
            }
        }
        if (C15.bf == 1) {
            this.L.drawPolygon(array, array2, n);
        }
    }
    
    public void d(final Color z) {
        this.br = z;
        this.Y = new Color(255 - z.getRed(), 255 - z.getGreen(), 255 - z.getBlue());
        final Color color = this.L.getColor();
        this.L.setColor(z);
        this.L.fillRect(0, 0, this.bs, this.O);
        this.L.setColor(color);
        C15.Z = z;
    }
    
    public boolean e() {
        return this.bz;
    }
    
    public void f(final int n, final int n2, final int n3, final int n4) {
        if (this.L != this.bA) {
            this.m();
        }
        (this.L = this.bA.create()).clipRect(n, n2, n3, n4);
    }
    
    public void g(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (Math.abs(n6 - n5) > 1 || n3 > 200 || n4 > 200) {
            if (n5 == 0 && n6 > 329 && n6 < 360) {
                switch (n6) {
                    case 359: {
                        if (n4 < 70) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                    case 358: {
                        if (n4 < 35) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                    case 357: {
                        if (n4 < 24) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                    case 356: {
                        if (n4 < 17) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                    case 355: {
                        if (n4 < 14) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                    case 354: {
                        if (n4 < 12) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                    case 350:
                    case 351:
                    case 352:
                    case 353: {
                        if (n4 < 9) {
                            this.x(n, n2, n3, n4);
                            return;
                        }
                        break;
                    }
                }
                if (n4 < 4) {
                    this.x(n, n2, n3, n4);
                    return;
                }
            }
            if (n6 < n5) {
                if (this.bz) {
                    this.L.fillArc(n, n2, n3, n4, 0, n6);
                    this.L.fillArc(n, n2, n3, n4, n5, 360 - n5);
                }
                else {
                    this.L.drawArc(n, n2, n3, n4, 0, n6);
                    this.L.drawArc(n, n2, n3, n4, n5, 360 - n5);
                }
                return;
            }
            if (this.bz) {
                this.L.fillArc(n, n2, n3, n4, n5, n6 - n5);
            }
            else {
                this.L.drawArc(n, n2, n3, n4, n5, n6 - n5);
            }
        }
    }
    
    public void i(final C38 bn) {
        this.bn = bn;
    }
    
    public void j(final int n, final int n2, final int n3, final int n4, final int[] array, int n5, final int n6) {
        this.bC = 0;
        this.bv = 0;
        this.ba = false;
        while (!this.ba) {
            this.bC = array[this.bv++];
            if (this.bC + n5 >= n6) {
                this.bC = n6 - 10 - n5;
                this.ba = true;
            }
            if (this.bv >= array.length) {
                this.bv = 0;
            }
            if (this.bC == 1 && (n3 < 70 || n4 < 70)) {
                this.L.drawArc(n, n2, n3, n4, n5, this.bC + 4);
            }
            else {
                this.L.drawArc(n, n2, n3, n4, n5, this.bC);
            }
            n5 = n5 + this.bC + array[this.bv++];
            if (this.bv >= array.length) {
                this.bv = 0;
            }
            if (n5 >= n6) {
                this.ba = true;
            }
        }
    }
    
    public Color k() {
        return this.L.getColor();
    }
    
    public void l(final int n, final int n2, final int n3, final int n4) {
        if (this.bz) {
            this.L.fillRect(n, n2, n3, n4);
        }
        else {
            this.L.drawRect(n, n2, n3, n4);
        }
    }
    
    public void m() {
        this.bo.addElement(this.L);
        this.L = this.bA;
    }
    
    public void n(final boolean bz) {
        this.bz = bz;
    }
    
    public void o(final Color z) {
        if (this.br != null) {
            return;
        }
        if (z.equals(this.bd)) {
            return;
        }
        final Color color = this.L.getColor();
        this.L.setColor(z);
        this.L.fillRect(0, 0, this.bs, this.O);
        this.L.setColor(color);
        this.bd = z;
        C15.Z = z;
    }
    
    private void p(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9) {
        if (n <= 5) {
            this.L.drawLine(n2, n3, n4, n5);
        }
        this.bK[0] = n4 + (int)(n * n7);
        this.N[0] = n5 + (int)(n * n6);
        this.bK[1] = n4 + (int)(n * n9);
        this.N[1] = n5 + (int)(n * this.by);
        this.bK[2] = n2 + (int)(n * n9);
        this.N[2] = n3 + (int)(n * this.by);
        this.bK[3] = n2 + (int)(n * n7);
        this.N[3] = n3 + (int)(n * n6);
        this.v(this.bK, this.N, this.N.length);
    }
    
    public int q(final int bb, final int bp, final int n, final int n2, int[] array, final int n3, int n4, final boolean b) {
        this.W = n - bb;
        this.bL = n2 - bp;
        this.bB = bb;
        this.bp = bp;
        this.bk = 1.0;
        this.bh = 0.0;
        this.bl = 0.0;
        this.bj = 1.0;
        this.bx = -1.0;
        this.by = 0.0;
        if (this.W != 0) {
            final double atan = Math.atan(this.bL / this.W);
            this.bh = Math.cos(atan);
            this.bk = Math.sin(atan);
            this.bl = Math.sin(atan + 1.5707963267948966);
            this.bj = Math.cos(atan + 1.5707963267948966);
            this.by = Math.sin(atan - 1.5707963267948966);
            this.bx = Math.cos(atan - 1.5707963267948966);
        }
        else if (n2 < bp) {
            this.bk = -1.0;
        }
        if (this.W > 0) {
            this.bF = 1;
        }
        else {
            this.bF = -1;
        }
        this.bq = 1;
        this.be = bb;
        this.U = bp;
        this.bH = 0;
        this.S = (int)Math.sqrt(Math.pow(this.W, 2.0) + Math.pow(this.bL, 2.0));
        this.K = n3 / 2;
        if (array == null) {
            array = new int[] { this.S, 0 };
        }
        if (this.V > 0 && !this.bG) {
            this.bB = bb;
            this.bp = bp;
            this.bK[3] = this.bB + (int)(this.K * this.bj);
            this.N[3] = this.bp + (int)(this.K * this.bl);
            this.bK[2] = this.bB + (int)(this.K * this.bx);
            this.N[2] = this.bp + (int)(this.K * this.by);
            this.L.fillPolygon(this.bK, this.N, this.bK.length);
        }
        if (this.V > 0 && this.bG) {
            this.bH += this.V;
            if (this.bH > this.S) {
                this.bH = this.S;
                this.V -= this.bH;
                return n4;
            }
            this.V -= this.bH;
        }
        while (this.bH < this.S) {
            if (this.V > 0 && !this.bG) {
                this.bH += this.V;
                this.V -= this.bH;
            }
            else {
                this.bH += array[n4];
                if (++n4 >= array.length) {
                    n4 = 0;
                }
                if (array[n4] == 0) {
                    this.bH = this.S;
                }
            }
            if (this.bH > this.S) {
                this.V = this.bH - this.S;
                this.bG = false;
                this.bH = this.S;
            }
            this.bB = bb + (int)(this.bF * this.bH * this.bh);
            if (this.W >= 0) {
                this.bp = bp + (int)(this.bq * this.bH * this.bk);
            }
            else {
                this.bp = bp - (int)(this.bH * this.bk);
            }
            this.p(this.K, this.be, this.U, this.bB, this.bp, this.bl, this.bj, this.by, this.bx);
            if (this.bH < this.S) {
                this.bH += array[n4];
                if (this.bH > this.S) {
                    this.V = this.bH - this.S;
                    this.bG = true;
                    this.bH = this.S;
                }
                if (++n4 >= array.length) {
                    n4 = 0;
                }
                this.be = bb + (int)(this.bH * this.bh);
                this.U = bp + (int)(this.bH * this.bk);
                this.be = bb + (int)(this.bF * this.bH * this.bh);
                if (this.W >= 0) {
                    this.U = bp + (int)(this.bq * this.bH * this.bk);
                }
                else {
                    this.U = bp - (int)(this.bH * this.bk);
                }
            }
        }
        return n4;
    }
    
    public void r(final int n) {
        if (this.br != null) {
            return;
        }
        final Color color = this.L.getColor();
        try {
            this.H(n);
            if (this.k().equals(this.bd)) {
                return;
            }
            this.bd = this.k();
            C15.Z = this.bd;
            this.L.fillRect(0, 0, this.bs, this.O);
            this.L.setColor(color);
        }
        catch (AWTException ex) {
            System.out.println(ex + " ERROR 300231");
            ex.printStackTrace();
        }
    }
    
    public void t(final int n, final int n2) {
        this.L.drawLine(n - 1, n2, n + 1, n2);
        this.L.drawLine(n, n2 - 1, n, n2 + 1);
    }
    
    public void u(final int[] array, final int[] array2, final int n) {
        if (this.bz) {
            this.L.fillPolygon(array, array2, n);
        }
        else {
            this.L.drawPolygon(array, array2, n);
        }
    }
    
    protected void finalize() throws Throwable {
        try {
            for (int i = 0; i < this.bo.size(); ++i) {
                ((Graphics)this.bo.elementAt(i)).dispose();
            }
        }
        catch (Throwable t) {
            System.out.println("Error #442009 " + t);
        }
    }
    
    public void v(final int[] array, final int[] array2, final int n) {
        this.L.fillPolygon(array, array2, n);
    }
    
    public void x(final int n, final int n2, final int n3, final int n4) {
        if (this.bz) {
            this.L.fillOval(n, n2, n3, n4);
        }
        else {
            this.L.drawOval(n, n2, n3, n4);
        }
    }
    
    static {
        C15.bf = 0;
    }
    
    public void y(final boolean r) {
        this.R = r;
    }
    
    public void z(final String s, final int n, final int n2, final int n3) {
        final Font font = this.L.getFont();
        if (font.getSize() != n3) {
            Font font2 = new Font(font.getName(), font.getStyle(), n3);
            if (font2.getSize() != n3) {
                font2 = new Font("TimesRoman", font.getStyle(), n3);
            }
            this.L.setFont(font2);
            this.L.drawString(s, n, n2);
            this.L.setFont(font);
        }
        else {
            this.L.drawString(s, n, n2);
        }
    }
    
    public void A(final Font font) {
        if (this.L.getFont() == null || !this.L.getFont().equals(font)) {
            this.L.setFont(font);
            this.bA.setFont(font);
        }
    }
    
    public void B(Color white) {
        if (this.br != null && this.R && !this.bz) {
            this.L.setColor(Color.black);
            return;
        }
        if (C13.Q < 25 && white.getRed() == 0 && white.getGreen() == 0 && white.getBlue() == 0) {
            white = Color.white;
        }
        if (this.br != null && this.br.equals(white)) {
            this.L.setColor(this.Y);
            this.bA.setColor(this.Y);
        }
        else {
            this.L.setColor(white);
            this.bA.setColor(white);
        }
    }
    
    public void C(final int n, final int n2, int n3) {
        final double n4 = 0.6283185307179586;
        final int n5 = (int)(Math.sin(n4) * n3);
        n3 *= (int)0.9;
        for (int i = 0; i < 10; ++i) {
            final int n6 = 36 * i;
            final double n7 = n4 * i;
            this.L.drawArc((int)(n + Math.cos(n7) * n3) - n5 / 2, (int)(n2 - Math.sin(n7) * n3) - n5 / 2, n5, n5, 360 - (90 - n6), 180);
        }
    }
    
    public void D(final Color color) {
        this.L.setXORMode(color);
        this.bA.setXORMode(color);
    }
    
    public void F(final int[] array, final int[] array2, final int n, final int[] array3, final int n2) {
        this.bw = 0;
        this.K = n2 / 2;
        this.bE = false;
        this.V = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (i == 1) {
                this.bE = true;
            }
            this.bw = this.q(array[i], array2[i], array[i + 1], array2[i + 1], array3, n2, this.bw, i < n - 2);
        }
    }
    
    public void H(final int n) throws AWTException {
        if (this.bn == null) {
            this.bn = new C38(C13.x, C13.P);
        }
        this.B(this.bn.b(n));
    }
    
    public void I(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5) {
        this.q(n, n2, n3, n4, array, n5, this.V = 0, true);
    }
    
    public void J(final Image image, final int n, final int n2, final int n3, final int n4, final ImageObserver imageObserver) {
        if (this.bd != null) {
            this.L.drawImage(image, n, n2, n3, n4, this.bd, imageObserver);
        }
        else {
            this.L.drawImage(image, n, n2, n3, n4, Color.white, imageObserver);
        }
    }
}
