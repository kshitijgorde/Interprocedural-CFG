// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Vector;

class h extends n
{
    protected d aS;
    protected d aG;
    protected d aE;
    protected d aT;
    protected d aM;
    protected d aP;
    protected d aC;
    protected d aX;
    protected d aR;
    protected d aY;
    protected d aJ;
    protected d aL;
    protected d aO;
    protected d aV;
    protected d aQ;
    protected d aN;
    protected d aI;
    protected d aW;
    protected d aF;
    protected d aD;
    protected d aZ;
    protected d aK;
    protected d aU;
    protected d a0;
    protected int[] aH;
    
    protected h(final String s, final int n, final Prove3d prove3d) {
        super(s, n, prove3d);
        final float[] ck = { 0.0f, 0.0f, -1.0f };
        final float[] array = new float[3];
        final float[] ck2 = { 0.8f, 0.8f, 0.8f };
        final float[] ck3 = { 0.0f, 0.0f, 1.0f, 0.0f };
        final float[] ck4 = { 0.0f, 0.0f, 10.0f };
        switch (n) {
            case 5: {
                this.aM = new d(this, 10, 10);
                this.aP = new d(this, 11, 10);
            }
            case 6: {
                this.aT = new d(this, 44, 1);
                this.aC = new d(this, 12, 15);
                this.aX = new d(this, 13, 5);
                this.aE = new d(this, 14, 1);
                this.aX.ck = array;
                this.aC.cv = new String[0];
            }
            case 7: {
                this.aR = new d(this, 15, 19);
                this.aR.ck = new float[0];
            }
            case 8: {
                this.aY = new d(this, 16, 18);
                this.aJ = new d(this, 17, 6);
                this.aL = new d(this, 18, 1);
                this.aY.ck = ck;
                this.aJ.ci = 1.0f;
                this.aL.cj = true;
            }
            case 9: {
                this.aG = new d(this, 2, 15);
                this.aG.cv = new String[0];
            }
            case 10: {
                this.aO = new d(this, 21, 4);
                this.aV = new d(this, 45, 4);
                this.aQ = new d(this, 43, 6);
                this.aO.ck = ck2;
                this.aV.ck = array;
            }
            case 11: {
                this.aN = new d(this, 22, 10);
                this.aI = new d(this, 23, 10);
            }
            case 12: {
                this.aT = new d(this, 44, 1);
                this.aW = new d(this, 26, 6);
                this.aF = new d(this, 27, 1);
                this.aD = new d(this, 28, 12);
                this.aZ = new d(this, 29, 18);
                this.aS = new d(this, 1, 14);
                this.aK = new d(this, 30, 3);
                this.aE = new d(this, 14, 1);
                this.aW.ci = 0.785398f;
                this.aF.cj = true;
                this.aD.ck = ck3;
                this.aZ.ck = ck4;
            }
            case 13: {
                this.aU = new d(this, 31, 15);
                this.a0 = new d(this, 32, 14);
            }
            case 14: {
                this.aR = new d(this, 33, 17);
                this.aR.ck = new float[0];
            }
            default: {}
        }
    }
    
    protected void a(final boolean b) {
        switch (super.ay) {
            case 11: {
                super.az.currShapeHasTexture = false;
                super.az.currShapeIsNotTransparent = true;
                super.az.currShapeIsEmissive = false;
                super.az.currShapeRGBAlfa = -1;
                super.az.currShapeREDComp = 255;
                super.az.currShapeGREENComp = 255;
                super.az.currShapeBLUEComp = 255;
                super.az.currShapeEmissAlfaRGB = -8355712;
                super.az.currShapeEmissRED = 128;
                super.az.currShapeEmissGREEN = 128;
                super.az.currShapeEmissBLUE = 128;
                super.az.currShapeAlfa = 65280;
                if (this.aN.b6 != null) {
                    ((h)this.aN.b6).a(true);
                }
                if (this.aI.b6 != null) {
                    ((j)this.aI.b6).if(b);
                    return;
                }
                break;
            }
            case 5: {
                if (this.aM.b6 != null) {
                    ((h)this.aM.b6).a(true);
                }
                if (this.aP.b6 == null) {
                    break;
                }
                ((h)this.aP.b6).a(true);
                if (!super.az.currShapeIsEmissive && this.aM.b6 != null) {
                    super.az.currShapeIsEmissive = true;
                    return;
                }
                break;
            }
            case 10: {
                final int n = (int)((1.0f - this.aQ.ci) * 255.0f);
                final float[] ck = this.aO.ck;
                final Prove3d az = super.az;
                final int currShapeREDComp = (int)(ck[0] * 255.0f);
                az.currShapeREDComp = currShapeREDComp;
                final int n2 = currShapeREDComp;
                final Prove3d az2 = super.az;
                final int currShapeGREENComp = (int)(ck[1] * 255.0f);
                az2.currShapeGREENComp = currShapeGREENComp;
                final int n3 = currShapeGREENComp;
                final Prove3d az3 = super.az;
                final int currShapeBLUEComp = (int)(ck[2] * 255.0f);
                az3.currShapeBLUEComp = currShapeBLUEComp;
                final int n4 = currShapeBLUEComp;
                super.az.currShapeRGBAlfa = (n << 24 | n2 << 16 | n3 << 8 | n4);
                final float[] ck2 = this.aV.ck;
                final int currShapeEmissRED = (int)(ck2[0] * 255.0f);
                final int currShapeEmissGREEN = (int)(ck2[1] * 255.0f);
                final int currShapeEmissBLUE = (int)(ck2[2] * 255.0f);
                super.az.currShapeEmissAlfaRGB = (n << 24 | currShapeEmissRED << 16 | currShapeEmissGREEN << 8 | currShapeEmissBLUE);
                super.az.currShapeEmissRED = currShapeEmissRED;
                super.az.currShapeEmissGREEN = currShapeEmissGREEN;
                super.az.currShapeEmissBLUE = currShapeEmissBLUE;
                if (n != 255) {
                    super.az.currShapeTransparency = (int)(this.aQ.ci * 255.0f) << 8;
                    super.az.currShapeAlfa = n << 8;
                    super.az.currShapeIsNotTransparent = false;
                }
                if (n2 != 0 || n3 != 0 || n4 != 0) {
                    super.az.currShapeIsEmissive = true;
                    return;
                }
                break;
            }
            case 9: {
                if (this.aH != null) {
                    super.az.currShapeTexture = this.aH;
                    super.az.currShapeHasTexture = true;
                    return;
                }
                break;
            }
        }
    }
    
    protected void if(final String s) {
        switch (super.ay) {
            case 6: {
                if (this.aC.cv.length > 0) {
                    this.aH = super.az.a(String.valueOf(s) + this.aC.cv[0], false);
                }
                super.az.a((n)this);
                break;
            }
            case 9: {
                if (this.aG.cv.length > 0) {
                    this.aH = super.az.a(String.valueOf(s) + this.aG.cv[0], true);
                    break;
                }
                break;
            }
        }
    }
    
    protected boolean if(final float[] array, final float[] array2) {
        if (super.ay == 11 && this.aI.b6 != null) {
            this.aI.b6.if(array, array2);
            return true;
        }
        return false;
    }
    
    protected void a(final Vector vector) {
        if (super.ay == 11) {
            final h h = (h)this.aN.b6;
            if (h != null) {
                final h h2 = (h)h.aM.b6;
                if (h2 != null) {
                    vector.addElement(new Float(h2.aQ.ci));
                }
            }
        }
    }
    
    protected int a(final Vector vector, final float n, int n2) {
        if (super.ay == 11) {
            final h h = (h)this.aN.b6;
            if (h != null) {
                final h h2 = (h)h.aM.b6;
                if (h2 != null) {
                    if (n >= 0.0f && 1.0f >= n) {
                        h2.aQ.do(n);
                    }
                    else {
                        h2.aQ.do(vector.elementAt(n2++));
                    }
                }
            }
        }
        return n2;
    }
}
