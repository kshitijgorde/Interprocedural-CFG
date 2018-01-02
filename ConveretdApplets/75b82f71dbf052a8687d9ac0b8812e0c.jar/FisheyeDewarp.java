import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class FisheyeDewarp extends BaseDewarp
{
    protected int seamSpacing;
    protected static final float TOO_BIG = 10000.0f;
    protected FisheyeTransform mXf;
    protected Point[] so;
    protected Point[] sp;
    protected Point[] sq;
    protected float[] seamF;
    protected int[] seam;
    protected static final boolean HORIZONTAL = true;
    protected static final boolean VERTICAL = false;
    protected boolean Orientation;
    protected float zoomMin;
    protected float zoomMax;
    protected float zDef;
    protected float vFovMin;
    protected float vFovMax;
    protected float hFovMin;
    protected float hFovMax;
    protected float[] refVp;
    protected float radius;
    protected int layout;
    protected static final int SPHERE = 0;
    protected static final int MIRRORED = 1;
    protected static final int HEMISPHERE = 2;
    protected final float DEG2RAD = 0.017453292f;
    private float[] _out;
    private float[] _in;
    private Point _p;
    
    FisheyeDewarp() {
        this.mXf = new FisheyeTransform();
        this.seamF = null;
        this.seam = null;
        this.Orientation = true;
        this.vFovMin = 0.0f;
        this.vFovMax = 0.0f;
        this.hFovMin = 0.0f;
        this.hFovMax = 0.0f;
        this._out = new float[2];
        this._in = new float[2];
        this._p = new Point(0, 0);
    }
    
    void setInput(final Frame src) {
        if (this.src == src) {
            return;
        }
        try {
            this.zoomMax = (float)src.getProperty("zmax");
            this.zoomMin = (float)src.getProperty("zmin");
            this.zDef = ((float[])src.getProperty("invp"))[3];
            this.radius = (float)src.getProperty("irad");
            this.refVp = (float[])src.getProperty("rfvp");
            try {
                this.vFovMin = (float)src.getProperty("vfmn");
            }
            catch (NullPointerException ex) {}
            try {
                this.vFovMax = (float)src.getProperty("vfmx");
            }
            catch (NullPointerException ex2) {}
            try {
                this.hFovMin = (float)src.getProperty("hfmn");
            }
            catch (NullPointerException ex3) {}
            try {
                this.hFovMax = (float)src.getProperty("hfmx");
            }
            catch (NullPointerException ex4) {}
            final float temp = this.vFovMin;
            this.vFovMin = -this.vFovMax;
            this.vFovMax = -temp;
            if (this.zoomMin == 0.0f) {
                this.zoomMin = 0.5f;
            }
            if (this.zoomMax == 0.0f) {
                this.zoomMax = (this.radius + 1.0f) * 2.8f / 512.0f;
            }
            if (this.hFovMin == this.hFovMax) {
                float hFov = 0.0f;
                try {
                    hFov = (float)src.getProperty("hfov");
                }
                catch (NullPointerException ex5) {}
                this.hFovMax = 0.5f * hFov;
                this.hFovMin = -this.hFovMax;
            }
            if (this.vFovMin == this.vFovMax) {
                float vFov = 0.0f;
                try {
                    vFov = (float)src.getProperty("vfov");
                }
                catch (NullPointerException ex6) {}
                if (vFov < 3.1414928f) {
                    this.vFovMax = 0.5f * vFov;
                    this.vFovMin = -this.vFovMax;
                }
            }
            final String _layout = (String)src.getProperty("frmt");
            if (_layout.equals("SPHERE")) {
                this.layout = 0;
            }
            else if (_layout.equals("MIRRORED")) {
                this.layout = 1;
            }
            else {
                this.layout = 2;
            }
            this.mXf.setRadius(this.radius);
            this.mXf.setRefViewpoint((float[])src.getProperty("rfvp"));
            super.setInput(src);
        }
        catch (NullPointerException ex7) {}
    }
    
    void setOutput(final Frame dst) {
        if (this.dst == dst) {
            return;
        }
        super.setOutput(dst);
        final Dimension size = dst.getSize();
        this.N = Math.min((int)Math.ceil(Math.log(size.width * size.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        this.GS = 1 << this.N;
        this.seamSpacing = this.GS << 1;
        this.prepareDewarp(dst.getSize());
    }
    
    protected void prepareDewarp(final Dimension size) {
        super.prepareDewarp(size);
        this.so = new Point[Math.max(this.CnrPtCols, this.CnrPtRows) + 1];
        this.sp = new Point[this.so.length];
        this.sq = new Point[this.so.length];
        for (int i = 0; i < this.so.length; ++i) {
            this.so[i] = new Point(0, 0);
            this.sp[i] = new Point(0, 0);
            this.sq[i] = new Point(0, 0);
        }
        this.seamF = new float[BaseDewarp.MULTOF(Math.max(size.width, size.height), this.seamSpacing) + this.seamSpacing];
        this.seam = new int[this.seamF.length];
    }
    
    void limitViewpoint(final float[] vp) {
        if (this.zoomMin < this.zoomMax) {
            vp[3] = Util.limit(vp[3], this.zoomMin, this.zoomMax);
        }
        final Dimension dstSize = this.dst.getSize();
        final float diagonal = (float)Math.sqrt(dstSize.width * dstSize.width + dstSize.height * dstSize.height);
        final float zs = 0.64f * diagonal / this.radius;
        final int n = 3;
        vp[n] *= zs;
        if (this.hFovMax > this.hFovMin && this.hFovMax - this.hFovMin <= 6.2831855f) {
            float fovd2 = (this.hFovMax - this.hFovMin) * 0.5f;
            fovd2 = Math.min(fovd2, 3.1414928f);
            final float winWidth = dstSize.width * 0.5f;
            final float zMin = winWidth / (this.radius * (float)Math.tan(fovd2));
            vp[3] = Math.max(vp[3], zMin);
            final float coverage = (float)Math.atan2(winWidth, vp[3] * this.radius);
            if (vp[0] + coverage > this.hFovMax) {
                vp[0] = this.hFovMax - coverage;
            }
            if (vp[0] - coverage < this.hFovMin) {
                vp[0] = this.hFovMin + coverage;
            }
        }
        else if (this.layout == 2 && this.refVp[1] > -0.017453292f && this.refVp[1] < 0.017453292f) {
            vp[0] = Util.limit(vp[0], -1.5707964f, 1.5707964f);
        }
        else {
            vp[0] = Util.wrap(vp[0], -3.1415927f, 3.1415927f);
        }
        if (this.vFovMax > this.vFovMin) {
            final float fovd2 = (this.vFovMax - this.vFovMin) * 0.5f;
            final float winHeight = dstSize.height * 0.5f;
            final float zMin = winHeight / (this.radius * (float)Math.tan(fovd2));
            vp[3] = Math.max(vp[3], zMin);
            final float coverage = (float)Math.atan2(winHeight, vp[3] * this.radius);
            if (vp[1] + coverage > this.vFovMax) {
                vp[1] = this.vFovMax - coverage;
            }
            if (vp[1] - coverage < this.vFovMin) {
                vp[1] = this.vFovMin + coverage;
            }
        }
        else if (this.layout == 2 && (this.refVp[1] < -1.553343f || this.refVp[1] > 1.553343f)) {
            if (this.refVp[1] > 1.553343f) {
                vp[1] = Util.limit(vp[1], 0.0f, 1.5707964f);
            }
            else {
                vp[1] = Util.limit(vp[1], -1.5707964f, 0.0f);
            }
        }
        else {
            vp[1] = Util.limit(vp[1], -1.5707964f, 1.5707964f);
        }
        final int n2 = 3;
        vp[n2] /= zs;
    }
    
    protected void forward(final Point out, final Point in) {
        this._out[0] = out.x - this.dst.getCenter()[0];
        this._out[1] = out.y - this.dst.getCenter()[1];
        this.mXf.forward(this._out, this._in);
        final float[] in2 = this._in;
        final int n = 0;
        in2[n] += this.src.getCenter()[0];
        final float[] in3 = this._in;
        final int n2 = 1;
        in3[n2] += this.src.getCenter()[1];
        in.x = (int)(this._in[0] * 4096.0f);
        in.y = (int)(this._in[1] * 4096.0f);
    }
    
    protected final void findSeam() {
        final int Umin = -this.seamSpacing;
        final int Vmin = -this.seamSpacing;
        final int Umax = BaseDewarp.MULTOF(this.dst.getSize().width, this.seamSpacing) + this.seamSpacing;
        final int Vmax = BaseDewarp.MULTOF(this.dst.getSize().height, this.seamSpacing) + this.seamSpacing;
        final float Go = this.mXf.n[2];
        final float Ho = this.mXf.o[2];
        final float Jo = this.mXf.a[2];
        final float mr = this.mXf.viewpoint[3] * this.mXf.radius;
        float m = -Go / Ho;
        if (Go == 0.0f) {
            m = 0.0f;
        }
        else if (Ho == 0.0f) {
            m = ((Go > 0.0f) ? -10000.0f : 10000.0f);
        }
        this.Orientation = (Math.abs(m) <= 1.0f);
        if (Math.abs(m) > 1000.0f) {
            float c = mr * -Jo / Go + this.dst.getCenter()[0];
            if (Go == 0.0) {
                c = ((Jo > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int Vi = 0; Vi < this.seamF.length; ++Vi) {
                this.seamF[Vi] = c;
            }
        }
        else if (Math.abs(m) < 0.001f) {
            float b = mr * -Jo / Ho + this.dst.getCenter()[1];
            if (Ho == 0.0) {
                b = ((Jo > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int Ui = 0; Ui < this.seamF.length; ++Ui) {
                this.seamF[Ui] = b;
            }
        }
        else if (!this.Orientation) {
            final float c = mr * -Jo / Go - this.dst.getCenter()[1] / m + this.dst.getCenter()[0];
            for (int Vi = 0; Vi < this.seamF.length; ++Vi) {
                this.seamF[Vi] = Vi / m + c;
            }
        }
        else {
            final float b = mr * -Jo / Ho - m * this.dst.getCenter()[0] + this.dst.getCenter()[1];
            for (int Ui = 0; Ui < this.seamF.length; ++Ui) {
                this.seamF[Ui] = m * Ui + b;
            }
        }
        for (int i = 0; i < this.seam.length; ++i) {
            this.seam[i] = Math.round(this.seamF[i]);
        }
    }
    
    protected final void calcSeamPts() {
        if (!this.Orientation) {
            for (int Vmax = BaseDewarp.MULTOF(this.dst.getSize().height, this.GS), v = 0; v <= Vmax; v += this.GS) {
                if (Math.abs(this.seam[v]) != 10000.0f) {
                    this._p.move(this.seam[v], v);
                    this.forward(this._p, this.so[v >> this.N]);
                    this._p.move(this.seam[v] - this.seamSpacing, v);
                    this.forward(this._p, this.sp[v >> this.N]);
                    this._p.move(this.seam[v] + this.seamSpacing, v);
                    this.forward(this._p, this.sq[v >> this.N]);
                }
            }
        }
        else {
            for (int Umax = BaseDewarp.MULTOF(this.dst.getSize().width, this.GS), u = 0; u <= Umax; u += this.GS) {
                if (Math.abs(this.seam[u]) != 10000.0f) {
                    this._p.move(u, this.seam[u]);
                    this.forward(this._p, this.so[u >> this.N]);
                    this._p.move(u, this.seam[u] - this.seamSpacing);
                    this.forward(this._p, this.sp[u >> this.N]);
                    this._p.move(u, this.seam[u] + this.seamSpacing);
                    this.forward(this._p, this.sq[u >> this.N]);
                }
            }
        }
    }
    
    protected final void interp() {
        final int Tmax = this.Orientation ? this.dst.getSize().width : this.dst.getSize().height;
        int Smax = this.Orientation ? this.dst.getSize().height : this.dst.getSize().width;
        Smax = (Smax + this.GS - 1 & ~(this.GS - 1));
        final boolean Nfront = (this.Orientation ? this.mXf.o[2] : this.mXf.n[2]) >= 0.0f;
        for (int T = 0; T < Tmax; T += this.GS) {
            if (this.seam[T] <= Smax || this.seam[T + this.GS] <= Smax) {
                if (this.seam[T] >= 0 || this.seam[T + this.GS] >= 0) {
                    this.seam(T, Math.min(T + this.GS, Tmax), Nfront);
                }
            }
        }
        int i = 0;
        for (int x = 0, v = 0; v < this.dst.getSize().height; v += this.GS, ++x) {
            for (int u = 0; u < this.dst.getSize().width; u += this.GS, ++x, ++i) {
                final int s = this.Orientation ? v : u;
                final int t = this.Orientation ? u : v;
                if (this.seamF[t] < s || this.seamF[t] > s + this.GS) {
                    if (this.seamF[t + this.GS] < s || this.seamF[t + this.GS] > s + this.GS) {
                        this.deltas(this.cornerPtIn[x], this.cornerPtIn[x + 1], this.cornerPtIn[x + this.CnrPtCols + 1], this.cornerPtIn[x + this.CnrPtCols + 2], this.N, this.N);
                        final boolean front = this.seamF[t + this.GS / 2] < s + this.GS / 2 ^ Nfront;
                        this.regular(this.bounds[i], front);
                    }
                }
            }
        }
    }
    
    protected void regular(final Rectangle bounds, final boolean front) {
        final Dimension srcSize = this.src.getSize();
        final Dimension dstSize = this.dst.getSize();
        final int[] srcMap = this.src.getPixmaps().elementAt(0);
        final int[] dstMap = this.dst.getPixmaps().elementAt(0);
        int dstBlkRowStart = bounds.y * dstSize.width + bounds.x;
        if (this.layout == 2 && front) {
            for (int v = 0; v < bounds.height; ++v) {
                int dstPix = dstBlkRowStart;
                for (int u = 0; u < bounds.width; ++u) {
                    dstMap[dstPix++] = -16777216;
                }
                dstBlkRowStart += dstSize.width;
            }
            return;
        }
        int srcPixStart = 0;
        if (front && this.layout == 0) {
            srcPixStart += srcSize.width * srcSize.height / 2;
        }
        for (int v2 = 0; v2 < bounds.height; ++v2) {
            int X = this.Xo;
            int Y = this.Yo;
            int dstPix2 = dstBlkRowStart;
            for (int u2 = 0; u2 < bounds.width; ++u2) {
                try {
                    dstMap[dstPix2] = this.getWeightedPixel(srcMap, srcSize, X, Y, srcPixStart);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    dstMap[dstPix2] = -16777216;
                }
                ++dstPix2;
                X += this.dxdu;
                Y += this.dydu;
            }
            this.Xo += this.dxdv;
            this.Yo += this.dydv;
            this.dxdu += this.dxdudv;
            this.dydu += this.dydudv;
            dstBlkRowStart += dstSize.width;
        }
    }
    
    protected void seam(final int To, final int Tmax, final boolean Nfront) {
        final Dimension srcSize = this.src.getSize();
        final Dimension dstSize = this.dst.getSize();
        final int[] srcMap = this.src.getPixmaps().elementAt(0);
        final int[] dstMap = this.dst.getPixmaps().elementAt(0);
        int front = 0;
        final int back = 0;
        if (this.layout == 0) {
            front = srcSize.width * srcSize.height / 2;
        }
        boolean Nblank = false;
        boolean Pblank = false;
        if (this.layout == 2) {
            Nblank = Nfront;
            Pblank = !Nfront;
        }
        final int Nsrc = Nfront ? front : back;
        final int Psrc = Nfront ? back : front;
        final int dT = this.Orientation ? 1 : dstSize.width;
        final int dS = this.Orientation ? dstSize.width : 1;
        final int Smax = this.Orientation ? dstSize.height : dstSize.width;
        final int j = To >> this.N;
        this.deltas(this.sp[j], this.so[j], this.sp[j + 1], this.so[j + 1], this.N + 1, this.N);
        for (int t = To; t < Tmax; ++t) {
            final int so = Math.max(this.seam[t] - this.GS - 1 & ~(this.GS - 1), 0);
            final int Se = Math.min(this.seam[t], Smax - 1);
            int dstPix = t * dT + so * dS;
            if (Nblank) {
                for (int s = so; s <= Se; ++s) {
                    dstMap[dstPix] = -16777216;
                    dstPix += dS;
                }
            }
            else {
                final int ds = this.seamSpacing - (this.seam[t] - so);
                int X = this.Xo + ds * this.dxdu;
                int Y = this.Yo + ds * this.dydu;
                for (int s2 = so; s2 <= Se; ++s2) {
                    try {
                        dstMap[dstPix] = this.getWeightedPixel(srcMap, srcSize, X, Y, Nsrc);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        dstMap[dstPix] = -16777216;
                    }
                    dstPix += dS;
                    X += this.dxdu;
                    Y += this.dydu;
                }
            }
            this.Xo += this.dxdv;
            this.Yo += this.dydv;
            this.dxdu += this.dxdudv;
            this.dydu += this.dydudv;
        }
        this.deltas(this.sq[j], this.so[j], this.sq[j + 1], this.so[j + 1], this.N + 1, this.N);
        for (int t = To; t < Tmax; ++t) {
            final int so = Math.min(this.seam[t] + this.seamSpacing & ~(this.GS - 1), Smax) - 1;
            final int Se = Math.max(this.seam[t] + 1, 0);
            int dstPix = t * dT + so * dS;
            if (Pblank) {
                for (int s = so; s >= Se; --s) {
                    dstMap[dstPix] = -16777216;
                    dstPix -= dS;
                }
            }
            else {
                final int ds = this.seamSpacing + (this.seam[t] - so);
                int X = this.Xo + ds * this.dxdu;
                int Y = this.Yo + ds * this.dydu;
                for (int s2 = so; s2 >= Se; --s2) {
                    try {
                        dstMap[dstPix] = this.getWeightedPixel(srcMap, srcSize, X, Y, Psrc);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        dstMap[dstPix] = -16777216;
                    }
                    dstPix -= dS;
                    X += this.dxdu;
                    Y += this.dydu;
                }
            }
            this.Xo += this.dxdv;
            this.Yo += this.dydv;
            this.dxdu += this.dxdudv;
            this.dydu += this.dydudv;
        }
    }
    
    protected int getWeightedPixel(final int[] srcMap, final Dimension srcSize, final int X, final int Y, final int srcPixelOffset) {
        final int srcPix = srcPixelOffset + (Y >>> 12) * srcSize.width + (X >>> 12);
        if (this.mVelocity > 20) {
            return srcMap[srcPix];
        }
        final int Pixel00 = srcMap[srcPix];
        final int Pixel2 = srcMap[srcPix + 1];
        final int Pixel3 = srcMap[srcPix + srcSize.width];
        final int Pixel4 = srcMap[srcPix + srcSize.width + 1];
        return this.average(Pixel00, Pixel2, Pixel3, Pixel4, X, Y);
    }
    
    protected int average(final int Pixel00, final int Pixel01, final int Pixel10, final int Pixel11, final int X, final int Y) {
        final int r00 = (Pixel00 & 0xFF0000) >> 16;
        final int g00 = (Pixel00 & 0xFF00) >> 8;
        final int b00 = Pixel00 & 0xFF;
        final int r2 = (Pixel01 & 0xFF0000) >> 16;
        final int g2 = (Pixel01 & 0xFF00) >> 8;
        final int b2 = Pixel01 & 0xFF;
        final int r3 = (Pixel10 & 0xFF0000) >> 16;
        final int g3 = (Pixel10 & 0xFF00) >> 8;
        final int b3 = Pixel10 & 0xFF;
        final int r4 = (Pixel11 & 0xFF0000) >> 16;
        final int g4 = (Pixel11 & 0xFF00) >> 8;
        final int b4 = Pixel11 & 0xFF;
        final int nAlongX = (X & 0xFFF) >> 4;
        final int nAlongY = (Y & 0xFFF) >> 4;
        final int r5 = ((r4 - r3) * nAlongX + (r3 << 8)) * nAlongY + ((r2 - r00) * nAlongX + (r00 << 8)) * (256 - nAlongY) & 0xFF0000;
        final int g5 = ((g4 - g3) * nAlongX + (g3 << 8)) * nAlongY + ((g2 - g00) * nAlongX + (g00 << 8)) * (256 - nAlongY) >> 8 & 0xFF00;
        final int b5 = ((b4 - b3) * nAlongX + (b3 << 8)) * nAlongY + ((b2 - b00) * nAlongX + (b00 << 8)) * (256 - nAlongY) >> 16 & 0xFF;
        return 0xFF000000 | r5 | g5 | b5;
    }
    
    protected void render() {
        final Dimension dstSize = this.dst.getSize();
        final float diagonal = (float)Math.sqrt(dstSize.width * dstSize.width + dstSize.height * dstSize.height);
        final float Zs = 0.64f * diagonal / this.mXf.radius;
        final float[] array;
        final float[] vp = array = (float[])this.getProperty("ptrz");
        final int n = 3;
        array[n] *= Zs;
        try {
            final float[] dp = Util.delta(vp, this.mXf.getViewpoint());
            final float da = (float)Math.sqrt(dp[0] * dp[0] + dp[1] * dp[1] + dp[3] * dp[3]);
            final float fov = 2.0f * (float)Math.atan2(1.0, 1.28f * vp[3] / Zs);
            this.mVelocity = (int)(da * fov * 573.0f);
        }
        catch (NullPointerException ex) {}
        this.mXf.setViewpoint(vp);
        this.calcCornerPts();
        this.findSeam();
        this.calcSeamPts();
        this.interp();
    }
    
    float getvFOVMin(final float zoom) {
        if (this.vFovMin == this.vFovMax) {
            return this.vFovMin;
        }
        final float[] vp = { 0.0f, -3.1415927f, 0.0f, zoom };
        this.limitViewpoint(vp);
        return vp[1];
    }
    
    float getvFOVMax(final float zoom) {
        if (this.vFovMin == this.vFovMax) {
            return this.vFovMax;
        }
        final float[] vp = { 0.0f, 3.1415927f, 0.0f, zoom };
        this.limitViewpoint(vp);
        return vp[1];
    }
    
    float gethFOVMin(final float zoom) {
        if (this.hFovMin == this.hFovMax) {
            return this.hFovMin;
        }
        final float[] vp = { -6.2831855f, 0.0f, 0.0f, zoom };
        this.limitViewpoint(vp);
        return vp[0];
    }
    
    float gethFOVMax(final float zoom) {
        if (this.hFovMin == this.hFovMax) {
            return this.hFovMax;
        }
        final float[] vp = { 6.2831855f, 0.0f, 0.0f, zoom };
        this.limitViewpoint(vp);
        return vp[0];
    }
    
    float getvFOV(final float zoom) {
        final Dimension dstSize = this.dst.getSize();
        final float diagonal = (float)Math.sqrt(dstSize.width * dstSize.width + dstSize.height * dstSize.height);
        return (float)Math.atan2(dstSize.height / 2.0, zoom * 0.64 * diagonal) * 2.0f;
    }
}
