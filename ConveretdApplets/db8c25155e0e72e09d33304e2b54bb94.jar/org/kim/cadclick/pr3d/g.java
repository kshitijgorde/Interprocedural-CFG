// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.util.Vector;

class g extends n implements o
{
    protected d bm;
    protected d bk;
    protected d bs;
    protected d bj;
    protected d bg;
    protected d bu;
    protected d bn;
    protected d bt;
    protected d bf;
    protected d bi;
    protected d br;
    protected d bq;
    protected d bh;
    protected float[] bo;
    protected float[] bp;
    protected float[] bl;
    
    protected g(final String s, final int n, final Prove3d prove3d) {
        super(s, n, prove3d);
        k.a(this.bo = new float[12]);
        this.bp = new float[12];
        this.bl = new float[12];
        final float[] array = new float[3];
        final float[] ck = { -1.0f, -1.0f, -1.0f };
        final float[] array2 = { 0.0f, 1.0f, 0.0f };
        final float[] ck2 = new float[3];
        final float[] ck3 = { 0.0f, 0.0f, 1.0f, 0.0f };
        final float[] ck4 = { 1.0f, 1.0f, 1.0f };
        final float[] ck5 = { 0.0f, 0.0f, 1.0f, 0.0f };
        final float[] ck6 = new float[3];
        this.bm = new d(this, 0, 11);
        this.bk = new d(this, 3, 18);
        this.bs = new d(this, 4, 18);
        this.bm.cx = new n[0];
        this.bk.ck = array;
        this.bs.ck = ck;
        switch (n) {
            case 0: {
                this.bg = new d(this, 5, 18);
                this.bu = new d(this, 6, 12);
                this.bn = new d(this, 7, 18);
                this.bt = new d(this, 8, 12);
                this.bf = new d(this, 9, 18);
                this.bg.ck = ck2;
                this.bu.ck = ck3;
                this.bn.ck = ck4;
                this.bt.ck = ck5;
                this.bf.ck = ck6;
            }
            case 2: {
                this.bj = new d(this, 2, 15);
                this.bj.cv = new String[0];
            }
            case 3: {
                this.bi = new d(this, 19, 11);
                this.bg = new d(this, 5, 18);
                this.br = new d(this, 20, 7);
                this.bg.ck = array;
            }
            case 4: {
                this.bq = new d(this, 24, 11);
                this.bq.cx = new n[0];
                this.bh = new d(this, 25, 8);
                this.bh.ch = -1;
            }
            default: {}
        }
    }
    
    protected void a(final float[] array, final float[] array2) {
        switch (super.ay) {
            case 0: {
                this.try();
                k.do(this.bo, array, this.bp);
                k.do(this.bl, array2, this.bl);
                break;
            }
            case 1:
            case 2: {
                System.arraycopy(array, 0, this.bp, 0, 12);
                System.arraycopy(array2, 0, this.bl, 0, 12);
                break;
            }
            case 3: {
                System.arraycopy(array, 0, this.bp, 0, 12);
                System.arraycopy(array2, 0, this.bl, 0, 12);
                final float n = super.az.actualAvatarMatrix[9] + this.bg.ck[0];
                final float n2 = super.az.actualAvatarMatrix[10] + this.bg.ck[1];
                final float n3 = super.az.actualAvatarMatrix[11] + this.bg.ck[2];
                final float n4 = (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
                final int length = this.br.ck.length;
                final float n5 = 0.0f;
                float n6 = 0.0f;
                int i;
                for (i = 0; i < length; ++i) {
                    n6 = this.br.ck[i];
                    if (n4 >= n5 && n4 < n6) {
                        this.bm.cx[0] = this.bi.cx[i];
                        break;
                    }
                }
                if (i == length && n4 >= n6) {
                    this.bm.cx[0] = this.bi.cx[length - 1];
                    break;
                }
                break;
            }
            case 4: {
                System.arraycopy(array, 0, this.bp, 0, 12);
                System.arraycopy(array2, 0, this.bl, 0, 12);
                final int ch = this.bh.ch;
                if (ch >= 0 && ch < this.bq.cx.length) {
                    this.bm.cx[0] = this.bq.cx[ch];
                    break;
                }
                this.bm.cx = null;
                break;
            }
        }
        super.az.actualAvatarMatrix = this.bp;
        if (super.az.do(this.bk.ck, this.bs.ck)) {
            for (int j = 0; j < this.bm.cx.length; ++j) {
                final n n7 = this.bm.cx[j];
                if (n7 != null && n7.ay == 8 && super.az.actualLightsNumber < 10) {
                    final h h = (h)n7;
                    if (h.aL.cj) {
                        float n8 = h.aY.ck[0];
                        float n9 = h.aY.ck[1];
                        float n10 = h.aY.ck[2];
                        final float n11 = (float)Math.sqrt(n8 * n8 + n9 * n9 + n10 * n10);
                        if (n11 != 0.0f) {
                            final float n12 = 1.0f / n11;
                            n8 *= n12;
                            n9 *= n12;
                            n10 *= n12;
                        }
                        final float n13 = n8 * this.bl[0] + n9 * this.bl[3] + n10 * this.bl[6];
                        final float n14 = n8 * this.bl[1] + n9 * this.bl[4] + n10 * this.bl[7];
                        final float n15 = n8 * this.bl[2] + n9 * this.bl[5] + n10 * this.bl[8];
                        final int n16 = super.az.actualLightsNumber++ << 2;
                        final float[] actualLightining = super.az.actualLightining;
                        actualLightining[n16] = n13;
                        actualLightining[n16 + 1] = n14;
                        actualLightining[n16 + 2] = n15;
                        actualLightining[n16 + 3] = h.aJ.ci;
                    }
                }
            }
            for (int k = 0; k < this.bm.cx.length; ++k) {
                super.az.actualAvatarMatrix = this.bp;
                super.az.actualTranslateMatrix = this.bl;
                final n n17 = this.bm.cx[k];
                if (n17 != null) {
                    if (n17.ay == 11) {
                        super.az.a((h)n17);
                    }
                    else {
                        n17.a(this.bp, this.bl);
                    }
                }
            }
        }
    }
    
    protected void try() {
        switch (super.ay) {
            case 0: {
                final float n = this.bf.ck[0];
                final float n2 = this.bf.ck[1];
                final float n3 = this.bf.ck[2];
                final float n4 = this.bg.ck[0];
                final float n5 = this.bg.ck[1];
                final float n6 = this.bg.ck[2];
                final float n7 = this.bu.ck[0];
                final float n8 = this.bu.ck[1];
                final float n9 = this.bu.ck[2];
                final float n10 = this.bu.ck[3];
                final float n11 = this.bn.ck[0];
                final float n12 = this.bn.ck[1];
                final float n13 = this.bn.ck[2];
                final float n14 = this.bt.ck[0];
                final float n15 = this.bt.ck[1];
                final float n16 = this.bt.ck[2];
                final float n17 = this.bt.ck[3];
                final float[] bo = this.bo;
                k.a(bo);
                final float[] array = new float[12];
                bo[9] = n + n4;
                bo[10] = n2 + n5;
                bo[11] = n3 + n6;
                k.a(n7, n8, n9, n10, bo);
                System.arraycopy(bo, 0, this.bl, 0, 12);
                if (n14 != 0.0f || n15 != 0.0f || n16 != 1.0f || n17 != 0.0f) {
                    k.a(n14, n15, n16, n17, array);
                    array[9] = 0.0f;
                    array[11] = (array[10] = 0.0f);
                    k.do(array, bo, bo);
                }
                final float[] array2 = bo;
                final int n18 = 0;
                array2[n18] *= n11;
                final float[] array3 = bo;
                final int n19 = 1;
                array3[n19] *= n11;
                final float[] array4 = bo;
                final int n20 = 2;
                array4[n20] *= n11;
                final float[] array5 = bo;
                final int n21 = 3;
                array5[n21] *= n12;
                final float[] array6 = bo;
                final int n22 = 4;
                array6[n22] *= n12;
                final float[] array7 = bo;
                final int n23 = 5;
                array7[n23] *= n12;
                final float[] array8 = bo;
                final int n24 = 6;
                array8[n24] *= n13;
                final float[] array9 = bo;
                final int n25 = 7;
                array9[n25] *= n13;
                final float[] array10 = bo;
                final int n26 = 8;
                array10[n26] *= n13;
                if (n14 != 0.0f || n15 != 0.0f || n16 != 1.0f || n17 != 0.0f) {
                    k.if(array, array);
                    k.do(array, bo, bo);
                }
                final float[] array11 = bo;
                final int n27 = 9;
                array11[n27] -= n4 * bo[0] + n5 * bo[3] + n6 * bo[6];
                final float[] array12 = bo;
                final int n28 = 10;
                array12[n28] -= n4 * bo[1] + n5 * bo[4] + n6 * bo[7];
                final float[] array13 = bo;
                final int n29 = 11;
                array13[n29] -= n4 * bo[2] + n5 * bo[5] + n6 * bo[8];
            }
            case 1: {
                k.a(this.bo);
            }
            default: {}
        }
    }
    
    protected void if(final String s) {
        if (super.ay == 2 && this.bj.cv.length > 0) {
            System.out.println("vrmlCodeBase: " + s);
            System.out.println("stringArrayValue: " + this.bj.cv[0]);
            final e e = new e(super.az, false);
            final g g = (g)e.a(String.valueOf(s) + this.bj.cv[0], " ");
            if (g != null) {
                e.a(g);
                System.arraycopy(g.bm.cx, 0, this.bm, 0, g.bm.cx.length);
            }
            System.gc();
        }
    }
    
    protected void a(final Vector vector) {
        final n[] cx = this.bm.cx;
        for (int i = 0; i < cx.length; ++i) {
            cx[i].a(vector);
        }
    }
    
    protected int a(final Vector vector, final float n, int a) {
        final n[] cx = this.bm.cx;
        for (int i = 0; i < cx.length; ++i) {
            a = cx[i].a(vector, n, a);
        }
        return a;
    }
    
    protected boolean if(final float[] array, final float[] array2) {
        this.try();
        final float[] array3 = new float[3];
        final float[] array4 = new float[3];
        int n = 1;
        if (this.bs.ck[0] != -1.0f && this.bs.ck[1] != -1.0f && this.bs.ck[2] != -1.0f) {
            System.arraycopy(this.bk.ck, 0, array, 0, 3);
            System.arraycopy(this.bs.ck, 0, array2, 0, 3);
        }
        final n[] cx = this.bm.cx;
        for (int i = 0; i < cx.length; ++i) {
            final boolean if1 = cx[i].if(array, array2);
            if (n != 0 && if1) {
                k.if(array3, array4, array, array2);
                n = 0;
            }
            else if (if1) {
                k.a(array3, array4, array, array2);
            }
        }
        if (array == null || array2 == null) {
            return true;
        }
        final float n2 = array3[0];
        final float n3 = array3[1];
        final float n4 = array3[2];
        final float n5 = array4[0];
        final float n6 = array4[1];
        final float n7 = array4[2];
        final float[] array5 = new float[24];
        System.arraycopy(k.a(n2, n3, n4, this.bo, true), 0, array5, 0, 3);
        System.arraycopy(k.a(n2, n3, n7, this.bo, true), 0, array5, 3, 3);
        System.arraycopy(k.a(n2, n6, n7, this.bo, true), 0, array5, 6, 3);
        System.arraycopy(k.a(n2, n6, n4, this.bo, true), 0, array5, 9, 3);
        System.arraycopy(k.a(n5, n3, n4, this.bo, true), 0, array5, 12, 3);
        System.arraycopy(k.a(n5, n6, n4, this.bo, true), 0, array5, 15, 3);
        System.arraycopy(k.a(n5, n3, n7, this.bo, true), 0, array5, 18, 3);
        System.arraycopy(k.a(n5, n6, n7, this.bo, true), 0, array5, 21, 3);
        k.a(array, array2, array5);
        System.arraycopy(array, 0, this.bk.ck, 0, 3);
        return true;
    }
}
