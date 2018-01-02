import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class BaseDewarp extends Dewarp
{
    protected int N;
    protected int GS;
    protected static final int REMAINDER_SIZE = 12;
    protected static final int REMAINDER_SCALE = 4096;
    protected static final int REMAINDER_MASK = 4095;
    protected Point[] cornerPtIn;
    protected Point[] cornerPtOut;
    protected int CnrPtCols;
    protected int CnrPtRows;
    protected Rectangle[] bounds;
    protected int mVelocity;
    protected int Xo;
    protected int Yo;
    protected int dxdu;
    protected int dydu;
    protected int dxdv;
    protected int dydv;
    protected int dxdudv;
    protected int dydudv;
    
    BaseDewarp() {
        this.N = 5;
        this.GS = 1 << this.N;
        this.mVelocity = 0;
        final float[] vp = { 0.0f, 0.0f, 0.0f, 1.0f };
        super.setProperty("ptrz", vp);
    }
    
    protected static final int MULTOF(final int a, final int gs) {
        return a + gs - 1 & ~(gs - 1);
    }
    
    protected void prepareDewarp(final Dimension size) {
        final int width = MULTOF(size.width, this.GS);
        final int height = MULTOF(size.height, this.GS);
        this.CnrPtCols = width >> this.N;
        this.CnrPtRows = height >> this.N;
        if (size.width == 0 || size.height == 0) {
            return;
        }
        this.cornerPtIn = new Point[(this.CnrPtCols + 1) * (this.CnrPtRows + 1)];
        this.cornerPtOut = new Point[this.cornerPtIn.length];
        int x = 0;
        for (int j = 0; j <= height; j += this.GS) {
            for (int i = 0; i <= width; i += this.GS, ++x) {
                this.cornerPtOut[x] = new Point(i, j);
                this.cornerPtIn[x] = new Point(0, 0);
            }
        }
        this.bounds = new Rectangle[this.CnrPtCols * this.CnrPtRows];
        int c = 0;
        int x2 = 0;
        for (int k = 0; k < height; k += this.GS, ++c) {
            for (int l = 0; l < width; l += this.GS, ++x2, ++c) {
                this.bounds[x2] = new Rectangle();
                this.bounds[x2].x = l;
                this.bounds[x2].y = k;
                this.bounds[x2].width = Math.min(this.GS, size.width - l);
                this.bounds[x2].height = Math.min(this.GS, size.height - k);
            }
        }
    }
    
    protected final void calcCornerPts() {
        for (int x = 0; x < this.cornerPtIn.length; ++x) {
            this.forward(this.cornerPtOut[x], this.cornerPtIn[x]);
        }
    }
    
    protected abstract void forward(final Point p0, final Point p1);
    
    protected void deltas(final Point a, final Point b, final Point c, final Point d, final int Nh, final int Nv) {
        this.Xo = a.x;
        this.Yo = a.y;
        this.dxdu = b.x - a.x >> Nh;
        this.dydu = b.y - a.y >> Nh;
        this.dxdv = c.x - a.x >> Nv;
        this.dydv = c.y - a.y >> Nv;
        this.dxdudv = d.x - c.x - b.x + a.x >> Nh + Nv;
        this.dydudv = d.y - c.y - b.y + a.y >> Nh + Nv;
    }
}
