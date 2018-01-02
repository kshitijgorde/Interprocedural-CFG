import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.image.PixelGrabber;
import java.util.Random;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class jKAAanimate
{
    private Component app;
    private Image Img;
    private Image OutImg;
    int Ni;
    int Nj;
    int sx;
    int sy;
    int sz;
    private Random rnd;
    private int[] OriginRastr;
    private int[] Two;
    private int[] One;
    private boolean first;
    private PixelGrabber pg;
    public Polygon PolygonBound;
    public boolean Ready;
    private boolean maskff;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    
    jKAAanimate(final Image img, final Component a, final int bug) {
        this.Ni = -1;
        this.Nj = -1;
        this.first = true;
        this.Ready = true;
        this.maskff = false;
        this.app = a;
        this.Img = img;
        while (this.Ni == -1 || this.Nj == -1) {
            this.Ni = this.Img.getHeight(null);
            this.Nj = this.Img.getWidth(null);
        }
        this.Constr(bug);
    }
    
    jKAAanimate(final Image img, final Component a) {
        this.Ni = -1;
        this.Nj = -1;
        this.first = true;
        this.Ready = true;
        this.maskff = false;
        this.app = a;
        this.Img = img;
        while (this.Ni == -1 || this.Nj == -1) {
            this.Ni = this.Img.getHeight(null);
            this.Nj = this.Img.getWidth(null);
        }
        this.Constr(0);
    }
    
    private void Constr(final int sm) {
        this.OriginRastr = new int[this.Nj * this.Ni];
        this.pg = new PixelGrabber(this.Img, 0, 0, this.Nj - sm, this.Ni, this.OriginRastr, 0, this.Nj);
        try {
            this.pg.grabPixels(500L);
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
        }
        if ((this.pg.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
        }
        (this.PolygonBound = new Polygon()).addPoint(0, 0);
        this.PolygonBound.addPoint(0, 0);
        this.PolygonBound.addPoint(0, 0);
        this.PolygonBound.addPoint(0, 0);
    }
    
    public int Begin() {
        this.sx = this.Nj;
        this.sy = this.Ni;
        this.sz = this.sx * this.sy;
        this.Two = new int[this.sz];
        this.first = true;
        this.maskff = false;
        return this.sz;
    }
    
    public Image Exec() {
        return this.app.createImage(new MemoryImageSource(this.sx, this.sy, this.Two, 0, this.sx));
    }
    
    public Color getColor(final int x, final int y) {
        int p;
        if (this.first) {
            p = this.OriginRastr[y * this.Nj + x];
        }
        else {
            p = this.Two[y * this.Nj + x];
        }
        final int r = (p & 0xFF0000) >> 16;
        final int g = (p & 0xFF00) >> 8;
        final int b = p & 0xFF;
        return new Color(r, g, b);
    }
    
    public Image getImage(final Polygon pl) {
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        this.getBorder(pl);
        final int ix = this.xmax - this.xmin;
        final int iy = this.ymax - this.ymin;
        final int[] Twoi = new int[(ix + 1) * (iy + 1)];
        for (int i = this.ymin, y = 0; i <= this.ymax; ++i, ++y) {
            for (int j = this.xmin, x = 0; j <= this.xmax; ++j, ++x) {
                if (pl.inside(j, i)) {
                    Twoi[y * ix + x] = this.One[i * this.Nj + j];
                }
                else {
                    Twoi[y * ix + x] = 0;
                }
            }
        }
        return this.app.createImage(new MemoryImageSource(ix, iy, Twoi, 0, ix));
    }
    
    public boolean RotatePlane(final double Angle) {
        return this.RotatePlane(this.Ni / 2, this.Nj / 2, Angle, null, 1.0, 1.0);
    }
    
    public boolean RotatePlane(final double Angle, final double ki, final double kj) {
        return this.RotatePlane(this.Ni / 2, this.Nj / 2, Angle, null, ki, kj);
    }
    
    public boolean RotatePlane(final int Yn, final int Xn, double Angle, final Polygon pl, final double ki, final double kj) {
        final int Nu = this.Ni;
        final int Nv = this.Nj;
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        Angle = Angle * 3.1415926 / 180.0;
        final double sn = Math.sin(Angle);
        final double cs = Math.cos(Angle);
        final double a = ki * cs;
        final double b = kj * sn;
        final double d = -ki * sn;
        final double e = kj * cs;
        final double c = (1.0 - a) * Xn - b * Yn;
        final double f = (1.0 - e) * Yn - d * Xn;
        this.PolygonBound.xpoints[0] = (int)f;
        this.PolygonBound.ypoints[0] = (int)c;
        this.PolygonBound.xpoints[1] = (int)(f + this.Nj * cs * kj);
        this.PolygonBound.ypoints[1] = (int)(c + this.Nj * sn * kj);
        this.PolygonBound.xpoints[2] = (int)(this.PolygonBound.xpoints[1] - this.Ni * sn * ki);
        this.PolygonBound.ypoints[2] = (int)(this.PolygonBound.ypoints[1] + this.Ni * cs * ki);
        this.PolygonBound.xpoints[3] = (int)(f - this.Ni * sn * ki);
        this.PolygonBound.ypoints[3] = (int)(c + this.Ni * cs * ki);
        final double det = a * e - b * d;
        final double a_ = e / det;
        final double b_ = -b / det;
        final double c_ = (b * f - e * c) / det;
        final double d_ = -d / det;
        final double e_ = a / det;
        final double f_ = (c * d - a * f) / det;
        double ui = c_ + 0.0;
        double vj = f_ + 0.0;
        int k = 0;
        if (pl == null) {
            for (int u = 1; u <= Nu; ++u) {
                double Si;
                ui = (Si = ui + a_);
                double Sj;
                vj = (Sj = vj + d_);
                for (int v = 1; v <= Nv; ++v, ++k) {
                    Si += b_;
                    Sj += e_;
                    final int i = (int)Si;
                    final int j = (int)Sj;
                    if (i < 1 || i > this.Ni || j < 1 || j > this.Nj) {
                        this.Two[k] = -16777216;
                    }
                    else {
                        this.Two[k] = this.One[(i - 1) * this.Nj + (j - 1)];
                    }
                }
            }
        }
        else {
            for (int u = 1; u <= Nu; ++u) {
                double Si;
                ui = (Si = ui + a_);
                double Sj;
                vj = (Sj = vj + d_);
                for (int v = 1; v <= Nv; ++v, ++k) {
                    Si += b_;
                    Sj += e_;
                    final int i = (int)Si;
                    final int j = (int)Sj;
                    if (pl.inside(j, i)) {
                        this.Two[k] = this.One[(i - 1) * this.Nj + (j - 1)];
                    }
                    else {
                        this.Two[k] = this.One[k];
                    }
                }
            }
        }
        this.first = false;
        return true;
    }
    
    public void getBorder(final Polygon pl) {
        this.xmin = this.Nj + 1;
        this.ymin = this.Ni + 1;
        this.xmax = -1;
        this.ymax = -1;
        for (int i = 0; i < pl.npoints; ++i) {
            this.xmin = Math.min(pl.xpoints[i], this.xmin);
            this.xmax = Math.max(pl.xpoints[i], this.xmax);
            this.ymin = Math.min(pl.ypoints[i], this.ymin);
            this.ymax = Math.max(pl.ypoints[i], this.ymax);
        }
    }
    
    public boolean RotateS(final int Xn, final int Yn, double Angle, final int mel, final double ki, final double kj) {
        int cr = 0;
        int cg = 0;
        int cb = 0;
        final float[] Aui = new float[9];
        final float[] Auj = new float[9];
        final float[] Avi = new float[9];
        final float[] Avj = new float[9];
        final int Nu = this.Ni;
        final int Nv = this.Nj;
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        Angle = Angle * 3.1415926 / 180.0;
        final float sn = (float)Math.sin(Angle);
        final float cs = (float)Math.cos(Angle);
        final float a = (float)ki * cs;
        final float b = (float)kj * sn;
        final float d = -(float)ki * sn;
        final float e = (float)kj * cs;
        final float c = (1.0f - a) * Xn - b * Yn;
        final float f = (1.0f - e) * Yn - d * Xn;
        this.PolygonBound.xpoints[0] = (int)f;
        this.PolygonBound.ypoints[0] = (int)c;
        this.PolygonBound.xpoints[1] = (int)(f + this.Nj * cs);
        this.PolygonBound.ypoints[1] = (int)(c + this.Nj * sn);
        this.PolygonBound.xpoints[2] = (int)(this.PolygonBound.xpoints[1] - this.Ni * sn);
        this.PolygonBound.ypoints[2] = (int)(this.PolygonBound.ypoints[1] + this.Ni * cs);
        this.PolygonBound.xpoints[3] = (int)(f - this.Ni * sn);
        this.PolygonBound.ypoints[3] = (int)(c + this.Ni * cs);
        final float det = a * e - b * d;
        final float a_ = e / det;
        final float b_ = -b / det;
        final float c_ = (b * f - e * c) / det;
        final float d_ = -d / det;
        final float e_ = a / det;
        final float f_ = (c * d - a * f) / det;
        float ui = c_ + 0.0f;
        float vj = f_ + 0.0f;
        if (mel > 1) {
            for (int k = 1; k <= mel; ++k) {
                final float A = k / mel;
                Aui[k] = A * a_;
                Avi[k] = A * d_;
                Auj[k] = A * b_;
                Avj[k] = A * e_;
            }
        }
        int k = 0;
        for (int u = 1; u <= Nu; ++u) {
            float Si;
            ui = (Si = ui + a_);
            float Sj;
            vj = (Sj = vj + d_);
            for (int v = 1; v <= Nv; ++v, ++k) {
                Si += b_;
                Sj += e_;
                this.Two[k] = 0;
                int i = (int)Si;
                int j = (int)Sj;
                if (i >= 1 && i <= this.Ni && j >= 1) {
                    if (j <= this.Nj) {
                        if (mel == 1) {
                            this.Two[k] = this.One[(i - 1) * this.Nj + (j - 1)];
                        }
                        int kol = 0;
                        int jsm = 0;
                        int sumr = 0;
                        int sumg = 0;
                        int sumb = 0;
                        for (int ku = 1; ku <= mel; ++ku) {
                            final float ssi = Si + Aui[ku];
                            final float ssj = Sj + Avi[ku];
                            for (int kv = 1; kv <= mel; ++kv) {
                                i = (int)(ssi + Auj[kv]);
                                j = (int)(ssj + Avj[kv]);
                                if (i >= 1 && i <= this.Ni && j >= 1) {
                                    if (j <= this.Nj) {
                                        jsm += this.One[(i - 1) * this.Nj + (j - 1)];
                                        final int cp = this.One[(i - 1) * this.Nj + (j - 1)] & 0xFFFFFF;
                                        cr = (cp & 0xFF0000) >> 16;
                                        cg = (cp & 0xFF00) >> 8;
                                        cb = (cp & 0xFF);
                                        sumr += cr;
                                        sumg += cg;
                                        sumb += cb;
                                        ++kol;
                                    }
                                }
                            }
                        }
                        if (kol >= mel) {
                            cr = (sumr + kol / 2) / kol;
                        }
                        cg = (sumg + kol / 2) / kol;
                        cb = (sumb + kol / 2) / kol;
                        this.Two[k] = (-16777216 + (cr << 16) + (cg << 8) + cb | 0xFF000000);
                    }
                }
            }
        }
        this.first = false;
        return true;
    }
    
    public boolean RotateSphere(final int Xo, final int Yo, final int Ro, final double Angle) {
        if (!this.Ready) {
            return false;
        }
        this.Ready = false;
        final double Ang = Angle * 3.1415926 / 180.0;
        if (this.first) {
            this.One = this.OriginRastr;
            this.copyTwo();
        }
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        int x = 1;
        int y = 0;
        this.maskff = true;
        this.xmin = Xo - Ro - 1;
        if (this.xmin < 0) {
            this.xmin = 0;
        }
        this.xmax = Xo + Ro;
        if (this.xmax > this.Nj) {
            this.xmax = this.Nj;
        }
        this.ymin = Yo - Ro - 1;
        if (this.ymin < 0) {
            this.ymin = 0;
        }
        this.ymax = Yo + Ro;
        if (this.ymax > this.Ni) {
            this.ymax = this.Ni;
        }
        for (int i = this.ymin + 1; i <= this.ymax; ++i) {
            final int Oy = Yo - i;
            final double r = Math.sqrt(Math.abs(Ro * Ro - Oy * Oy));
            y = i;
            for (int j = this.xmin + 1; j <= this.xmax; ++j) {
                final int Ox = j - Xo;
                if (Ox * Ox + Oy * Oy > Ro * Ro) {
                    this.Two[(i - 1) * this.Nj + (j - 1)] = this.One[(i - 1) * this.Nj + (j - 1)];
                }
                else {
                    final double cs_ = Ox / r;
                    final double beta = Math.acos(cs_);
                    x = Xo + (int)(r * Math.cos(beta - Ang));
                    if (x > this.Nj) {
                        x = this.Nj - 1;
                    }
                    if (x < 1) {
                        x = 1;
                    }
                    if (y > this.Ni) {
                        y = this.Ni - 2;
                    }
                    if (y < 1) {
                        y = 1;
                    }
                    this.Two[(i - 1) * this.Nj + (j - 1)] = (this.One[x - 1 + (y - 1) * this.Nj] | 0xFF000000);
                }
            }
        }
        this.Ready = true;
        this.first = false;
        return true;
    }
    
    private void copyTwo() {
        for (int i = 0; i < this.sz; ++i) {
            this.Two[i] = this.OriginRastr[i];
        }
    }
    
    private void setBound() {
        this.PolygonBound.xpoints[0] = 0;
        this.PolygonBound.ypoints[0] = 0;
        this.PolygonBound.xpoints[1] = this.Nj;
        this.PolygonBound.ypoints[1] = 0;
        this.PolygonBound.xpoints[2] = this.Nj;
        this.PolygonBound.ypoints[2] = this.Ni;
        this.PolygonBound.xpoints[3] = 0;
        this.PolygonBound.ypoints[3] = this.Ni;
    }
    
    public boolean Life(final int sigma, final Polygon pl) {
        boolean InSide = false;
        int m = 71;
        if (this.rnd == null) {
            this.rnd = new Random();
        }
        final int[] rndm = new int[m];
        if (this.first) {
            this.One = this.OriginRastr;
            this.copyTwo();
        }
        else {
            this.One = this.Two;
        }
        this.getBorder(pl);
        if ((this.xmax - this.xmin) % m == 0) {
            m = 83;
        }
        for (int i = 0; i < m; ++i) {
            rndm[i] = (int)(this.rnd.nextFloat() * sigma + 0.0f);
        }
        int r = 0;
        for (int j = this.ymin + 1; j <= this.ymax; ++j) {
            int y = j;
            y = rndm[r++];
            y += j;
            if (y > this.ymax) {
                y = this.ymax;
            }
            if (y < this.ymin) {
                y = this.ymin + 1;
            }
            for (int k = this.xmin + 1; k <= this.xmax; ++k) {
                int x = k;
                x = rndm[r++];
                x += k;
                if (x > this.xmax) {
                    x = this.xmax - 1;
                }
                if (x < this.xmin) {
                    x = this.xmin + 1;
                }
                InSide = pl.inside(k, j);
                if (!InSide) {
                    y = j;
                    x = k;
                }
                this.Two[(j - 1) * this.Nj + (k - 1)] = (this.One[x - 1 + (y - 1) * this.Nj] | 0xFF000000);
                if (r >= m - 1) {
                    r = 0;
                }
            }
        }
        this.first = false;
        return true;
    }
    
    public boolean insidePolygon(final Polygon pl) {
        boolean r = false;
        for (int i = 0; i < 3; ++i) {
            r |= this.insideLineInPolygon(this.PolygonBound.xpoints[i], this.PolygonBound.ypoints[i], this.PolygonBound.xpoints[i + 1], this.PolygonBound.ypoints[i + 1], pl);
        }
        return r | this.insideLineInPolygon(this.PolygonBound.xpoints[3], this.PolygonBound.ypoints[3], this.PolygonBound.xpoints[0], this.PolygonBound.ypoints[0], pl);
    }
    
    public boolean insideLineInPolygon(final int x1, final int y1, final int x2, final int y2, final Polygon pl) {
        final float a = (y1 - y2) / (x1 - x2);
        final float b = y1 - a * x1;
        final int xb = Math.min(x1, x2);
        for (int xe = Math.max(x1, x2), x3 = xb; x3 <= xe; ++x3) {
            final int y3 = (int)(a * x3 + b);
            if (pl.inside(x3, y3)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean Filter(final int[] win, final int Mp, final int Mq) {
        return this.Filter(win, Mp, Mq, null);
    }
    
    public boolean Filter(final int[] win, final int Mp, final int Mq, final Polygon pl) {
        int winzo = 0;
        int cr = 0;
        int cg = 0;
        int cb = 0;
        int cp = 0;
        int sumr = 0;
        int sumg = 0;
        int sumb = 0;
        final int pm = (Mp + 1) / 2;
        final int qm = (Mq + 1) / 2;
        if (this.first) {
            this.One = this.OriginRastr;
            this.copyTwo();
        }
        else {
            this.One = this.Two;
        }
        for (int r = Mp * Mq; r > 0; winzo += win[--r]) {}
        if (winzo == 0) {
            winzo = 1;
        }
        int ko = 0;
        for (int io = 1; io <= this.Ni; ++io) {
            int p1 = 1 + pm - io;
            if (p1 < 1) {
                p1 = 1;
            }
            int p2 = this.Ni + pm - io;
            if (p2 > Mp) {
                p2 = Mp;
            }
            final boolean zon = p1 == 1 && p2 == Mp;
            for (int jo = 1; jo <= this.Nj; ++jo, ++ko) {
                if (pl == null || pl.inside(jo, io)) {
                    int q1 = 1 + qm - jo;
                    if (q1 < 1) {
                        q1 = 1;
                    }
                    int q2 = this.Nj + qm - jo;
                    if (q2 > Mq) {
                        q2 = Mq;
                    }
                    int winsm;
                    if (zon && q1 == 1 && q2 == Mq) {
                        winsm = winzo;
                    }
                    else {
                        winsm = 0;
                        for (int rs = (p1 - 1) * Mp + q1 - 1, p3 = p1; p3 <= p2; ++p3, rs += Mq) {
                            for (int r = rs, q3 = q1; q3 <= q2; ++q3, ++r) {
                                winsm += win[r];
                            }
                        }
                        if (winsm == 0) {
                            winsm = 1;
                        }
                    }
                    final long sum = 0L;
                    sumr = 0;
                    sumg = 0;
                    sumb = 0;
                    for (int ks = (io - pm + p1 - 1) * this.Nj + (jo - qm + q1 - 1), rs = (p1 - 1) * Mp + q1 - 1, p3 = p1; p3 <= p2; ++p3, rs += Mq, ks += this.Nj) {
                        for (int k = ks, r = rs, q3 = q1; q3 <= q2; ++q3, ++r, ++k) {
                            cp = (this.One[k] & 0xFFFFFF);
                            cr = (cp & 0xFF0000) >> 16;
                            cg = (cp & 0xFF00) >> 8;
                            cb = (cp & 0xFF);
                            sumr += cr * win[r];
                            sumg += cg * win[r];
                            sumb += cb * win[r];
                        }
                    }
                    cr = (int)(sumr / winsm + 0.0);
                    cg = (int)(sumg / winsm + 0.0);
                    cb = (int)(sumb / winsm + 0.0);
                    if (cr > 255) {
                        cr = 255;
                    }
                    if (cg > 255) {
                        cg = 255;
                    }
                    if (cb > 255) {
                        cb = 255;
                    }
                    if (cr < 0) {
                        cr = 0;
                    }
                    if (cg < 0) {
                        cg = 0;
                    }
                    if (cb < 0) {
                        cb = 0;
                    }
                    this.Two[ko] = -16777216 + (cr << 16) + (cg << 8) + cb;
                }
            }
        }
        this.setBound();
        this.first = false;
        return true;
    }
    
    public boolean Brightness(final int br, final Polygon pl) {
        int p = 0;
        if (this.first) {
            this.One = this.OriginRastr;
            this.copyTwo();
        }
        else {
            this.One = this.Two;
        }
        this.getBorder(pl);
        for (int i = this.ymin + 1; i <= this.ymax; ++i) {
            for (int j = this.xmin + 1; j <= this.xmax; ++j) {
                final int kc = this.Nj * (i - 1) + j - 1;
                p = this.One[kc];
                if (!pl.inside(j, i)) {
                    this.Two[kc] = this.One[kc];
                }
                else {
                    int r = (p & 0xFF0000) >> 16;
                    int g = (p & 0xFF00) >> 8;
                    int b = p & 0xFF;
                    if (br >= 0) {
                        if (r + br > 255) {
                            r = 255;
                        }
                        else {
                            r += br;
                        }
                        if (g + br > 255) {
                            g = 255;
                        }
                        else {
                            g += br;
                        }
                        if (b + br > 255) {
                            b = 255;
                        }
                        else {
                            b += br;
                        }
                    }
                    else {
                        if (r + br < 0) {
                            r = 0;
                        }
                        else {
                            r += br;
                        }
                        if (g + br < 0) {
                            g = 0;
                        }
                        else {
                            g += br;
                        }
                        if (b + br < 0) {
                            b = 0;
                        }
                        else {
                            b += br;
                        }
                    }
                    this.Two[kc] = -16777216 + (r << 16) + (g << 8) + b;
                }
            }
        }
        this.PolygonBound = pl;
        this.first = false;
        return true;
    }
    
    public boolean BrightnessColor(final int Br, final Polygon pl, final Color cl) {
        int p = 0;
        int br = 1;
        final int fr = (cl.getRGB() & 0xFF0000) >> 16;
        final int fg = (cl.getRGB() & 0xFF00) >> 8;
        final int fb = cl.getRGB() & 0xFF;
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        for (int i = 1; i <= this.Ni; ++i) {
            for (int j = 1; j <= this.Nj; ++j) {
                final int kc = this.Nj * (i - 1) + j - 1;
                p = this.One[kc];
                if (!pl.inside(j, i)) {
                    this.Two[kc] = this.One[kc];
                }
                else {
                    int r = (p & 0xFF0000) >> 16;
                    int g = (p & 0xFF00) >> 8;
                    int b = p & 0xFF;
                    br = Br * ((fr - r) * (fr - r) + (fg - g) * (fg - g) + (fb - b) * (fb - b)) / 65025;
                    if (br >= 0) {
                        if (r + br > 255) {
                            r = 255;
                        }
                        else {
                            r += br;
                        }
                        if (g + br > 255) {
                            g = 255;
                        }
                        else {
                            g += br;
                        }
                        if (b + br > 255) {
                            b = 255;
                        }
                        else {
                            b += br;
                        }
                    }
                    else {
                        if (r + br < 0) {
                            r = 0;
                        }
                        else {
                            r += br;
                        }
                        if (g + br < 0) {
                            g = 0;
                        }
                        else {
                            g += br;
                        }
                        if (b + br < 0) {
                            b = 0;
                        }
                        else {
                            b += br;
                        }
                    }
                    this.Two[kc] = -16777216 + (r << 16) + (g << 8) + b;
                }
            }
        }
        this.PolygonBound = pl;
        this.first = false;
        return true;
    }
    
    public boolean Blick(final int x, final int y, final int Cbr, final int Rbr, final Color col) {
        final int[] m = new int[250];
        int p = 0;
        final int yb = 20;
        int brr = 0;
        int brg = 0;
        int brb = 0;
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        for (int i = 1; i <= this.Ni; ++i) {
            int y2 = i - y;
            for (int j = 1; j <= this.Nj; ++j) {
                final int kc = this.Nj * (i - 1) + j - 1;
                p = this.One[kc];
                int x2 = j - x;
                final int R2 = x2 * x2 + y2 * y2;
                if (y2 == 0) {
                    y2 = 1;
                }
                if (x2 == 0) {
                    x2 = 1;
                }
                if (y2 - yb == 0) {
                    y2 = yb + 1;
                }
                final int f = Cbr + (int)Math.sqrt(R2);
                brr = col.getRed() * 20 / f - Rbr;
                brg = col.getGreen() * 20 / f - Rbr;
                brb = col.getBlue() * 20 / f - Rbr;
                int r = (p & 0xFF0000) >> 16;
                int g = (p & 0xFF00) >> 8;
                int b = p & 0xFF;
                if (r + brr > 255) {
                    r = 255;
                }
                else if (brr > 0) {
                    r += brr;
                }
                if (g + brg > 255) {
                    g = 255;
                }
                else if (brg > 0) {
                    g += brg;
                }
                if (b + brb > 255) {
                    b = 255;
                }
                else if (brb > 0) {
                    b += brb;
                }
                this.Two[kc] = -16777216 + (r << 16) + (g << 8) + b;
            }
        }
        for (int i = 1; i <= this.Ni; ++i) {
            if (this.Nj * (i - 1) + m[i] < this.Ni * this.Nj) {
                this.Two[this.Nj * (i - 1) + m[i]] = -256;
            }
        }
        this.setBound();
        this.first = false;
        return true;
    }
    
    public boolean FilterBorder() {
        if (this.first) {
            this.One = this.OriginRastr;
        }
        else {
            this.One = this.Two;
        }
        for (int ks = 0, io = 1; io < this.Ni; ++io, ks += this.Nj) {
            for (int ko = ks, jo = 1; jo < this.Nj; ++jo, ++ko) {
                int cp = this.One[ko] & 0xFFFFFF;
                int cr = (cp & 0xFF0000) >> 16;
                int cg = (cp & 0xFF00) >> 8;
                int cb = cp & 0xFF;
                cp = (this.One[ko + this.Nj + 1] & 0xFFFFFF);
                final int cr2 = (cp & 0xFF0000) >> 16;
                final int cg2 = (cp & 0xFF00) >> 8;
                final int cb2 = cp & 0xFF;
                cp = (this.One[ko + 1] & 0xFFFFFF);
                final int cr3 = (cp & 0xFF0000) >> 16;
                final int cg3 = (cp & 0xFF00) >> 8;
                final int cb3 = cp & 0xFF;
                cp = (this.One[ko + this.Nj] & 0xFFFFFF);
                final int cr4 = (cp & 0xFF0000) >> 16;
                final int cg4 = (cp & 0xFF00) >> 8;
                final int cb4 = cp & 0xFF;
                final int ur = cr - cr2;
                final int vr = cr3 - cr4;
                final int ug = cg - cg2;
                final int vg = cg3 - cg4;
                final int ub = cb - cb2;
                final int vb = cb3 - cb4;
                cr = Math.abs(ur) + Math.abs(vr);
                cg = Math.abs(ug) + Math.abs(vg);
                cb = Math.abs(ub) + Math.abs(vb);
                if (cr > 255) {
                    cr = 255;
                }
                if (cg > 255) {
                    cg = 255;
                }
                if (cb > 255) {
                    cb = 255;
                }
                if (cr < 0) {
                    cr = 0;
                }
                if (cg < 0) {
                    cg = 0;
                }
                if (cb < 0) {
                    cb = 0;
                }
                this.Two[ko] = -16777216 + (cr << 16) + (cg << 8) + cb;
            }
        }
        this.setBound();
        this.first = false;
        return true;
    }
}
