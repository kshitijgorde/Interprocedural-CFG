// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.a;

import java.awt.Component;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import a.a.b.b;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Image;

public class a extends d
{
    private Image bI;
    private int bN;
    private int bK;
    private int[] bR;
    private Vector bL;
    private float[][][] bH;
    private boolean bD;
    private MediaTracker bJ;
    private a.a.b.a.a bE;
    private String bP;
    private int bB;
    private int bO;
    private int bG;
    private float bC;
    private Dimension[] bM;
    private boolean bQ;
    private boolean bF;
    
    public a(final a.a.a.a.e.a a, final String s) {
        super(a, s);
        this.bI = null;
        this.bE = new a.a.b.a.a();
        this.bB = -1;
        this.bO = 0;
        this.bG = 5;
        this.bC = 0.7f;
        this.bM = new Dimension[5];
        this.bQ = true;
        this.bF = false;
    }
    
    private void a(final a.a.a.a.a.c c) {
        final int a = c.a;
        float n2;
        float n = n2 = 1000000.0f;
        float n4;
        float n3 = n4 = -1000000.0f;
        int n5 = 0;
        for (int i = 0; i < a; ++i) {
            c.if[i][0] = c.if[i][0] * super.aS + super.aS;
            c.if[i][1] = -c.if[i][1] * super.aQ + super.aQ;
            c.if[i][2] *= super.aV - 1;
            c.if[i][3] *= super.bv - 1;
            if (c.if[i][0] < n2) {
                n2 = c.if[i][0];
            }
            if (c.if[i][0] > n4) {
                n4 = c.if[i][0];
            }
            if (c.if[i][1] < n) {
                n = c.if[i][1];
                n5 = i;
            }
            if (c.if[i][1] > n3) {
                n3 = c.if[i][1];
            }
        }
        if (n2 > super.a5) {
            return;
        }
        if (n > super.bq) {
            return;
        }
        if (n4 < 0.0f) {
            return;
        }
        if (n3 < 0.0f) {
            return;
        }
        int n7;
        int n6 = n7 = n5;
        int j = a;
        final float n8 = n - 0.5f;
        int n9 = (int)n8;
        if (n8 > 0.0f) {
            ++n9;
        }
        int l;
        int k = l = n9 - 1;
        final int do1 = super.bf.do;
        while (j > 0) {
            while (l <= n9) {
                if (j <= 0) {
                    break;
                }
                --j;
                int n10 = n7 - 1;
                if (n10 < 0) {
                    n10 = a - 1;
                }
                this.a(c.if[n7], c.if[n10], super.aL, super.bi, n9);
                final float n11 = c.if[n10][1] + 0.5f;
                l = (int)n11;
                if (n11 < 0.0f) {
                    --l;
                }
                n7 = n10;
            }
            while (k <= n9) {
                if (j <= 0) {
                    break;
                }
                --j;
                int n12 = n6 + 1;
                if (n12 >= a) {
                    n12 = 0;
                }
                this.a(c.if[n6], c.if[n12], super.by, super.bc, n9);
                final float n13 = c.if[n12][1] + 0.5f;
                k = (int)n13;
                if (n13 < 0.0f) {
                    --k;
                }
                n6 = n12;
            }
            while (n9 < l && n9 < k && n9 < do1) {
                if (n9 >= 0) {
                    if (super.aL[0] < super.by[0]) {
                        this.a(n9, super.aL, super.by);
                    }
                    else {
                        this.a(n9, super.by, super.aL);
                    }
                }
                ++n9;
                final float[] al = super.aL;
                final int n14 = 0;
                al[n14] += super.bi[0];
                final float[] by = super.by;
                final int n15 = 0;
                by[n15] += super.bc[0];
                final float[] al2 = super.aL;
                final int n16 = 1;
                al2[n16] += super.bi[1];
                final float[] by2 = super.by;
                final int n17 = 1;
                by2[n17] += super.bc[1];
                final float[] al3 = super.aL;
                final int n18 = 2;
                al3[n18] += super.bi[2];
                final float[] by3 = super.by;
                final int n19 = 2;
                by3[n19] += super.bc[2];
            }
            if (n9 >= do1) {
                break;
            }
        }
    }
    
    private void H() {
        if (this.bI == null) {
            return;
        }
        try {
            super.aV = this.bI.getWidth(this);
            super.bv = this.bI.getHeight(this);
            if (this.bL == null && super.aV > 0 && super.bv > 0) {
                this.bR = new int[super.aV * super.bv];
                if (this.bQ) {
                    this.bL = new Vector();
                    for (int i = 0; i < this.bG; ++i) {
                        this.bL.addElement(new int[(super.aV >> i) * (super.bv >> i)]);
                        this.bM[i] = new Dimension(super.aV >> i, super.bv >> i);
                    }
                }
            }
        }
        catch (Exception ex) {
            this.bR = null;
            this.try(this.int("iip04"));
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.try(this.int("iip19"));
        }
        if (this.bR != null) {
            try {
                new PixelGrabber(this.bI.getSource(), 0, 0, super.aV, super.bv, this.bR, 0, super.aV).grabPixels();
                if (this.bQ) {
                    this.if(this.bL.elementAt(1), this.bR, super.aV, super.bv);
                    for (int j = 2; j < this.bG; ++j) {
                        this.if((int[])this.bL.elementAt(j), (int[])this.bL.elementAt(j - 1), this.bM[j - 1].width, this.bM[j - 1].height);
                    }
                    this.a(this.bL.elementAt(0), this.bR, super.aV, super.bv);
                    this.bR = this.bL.elementAt(0);
                    System.out.println("The soften iamge is true");
                }
            }
            catch (InterruptedException ex2) {}
            catch (OutOfMemoryError outOfMemoryError2) {
                this.try(this.int("iip04"));
            }
        }
    }
    
    private void a(final int n, final float[] array, final float[] array2) {
        final float n2 = array[0] - 0.5f;
        int n3 = (int)n2;
        if (n2 > 0.0f) {
            ++n3;
        }
        final float n4 = array2[0] - 0.5f;
        int n5 = (int)n4;
        if (n4 < 0.0f) {
            --n5;
        }
        if (n3 > n5) {
            return;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n5 >= super.bf.new) {
            n5 = super.bf.new - 1;
        }
        float n6 = array2[0] - array[0];
        if (n6 == 0.0f) {
            n6 = 1.0f;
        }
        final float n7 = n3 + 0.5f - array[0];
        final float n8 = 1.0f / n6;
        super.aP[1] = (array2[1] - array[1]) * n8;
        super.aP[2] = (array2[2] - array[2]) * n8;
        super.aK[1] = array[1] + super.aP[1] * n7;
        super.aK[2] = array[2] + super.aP[2] * n7;
        int n9 = (int)(super.aK[1] * 65536.0f);
        int n10 = (int)(super.aK[2] * 65536.0f);
        final int n11 = (int)(super.aP[1] * 65536.0f);
        final int n12 = (int)(super.aP[2] * 65536.0f);
        final int n13 = 1 + n5 - n3;
        final int n14 = n * super.bf.new + n3;
        final int n15 = n13 + n14;
        final int[] a = super.bf.a;
        final boolean b = this.bE.if() > 0;
        for (int i = n14; i < n15; ++i) {
            final int n16 = n9 >> 16;
            final int n17 = (n10 >> 16) * super.aV;
            if (n16 < super.aV - 1 && n10 >> 16 < super.aV - 1) {
                switch ((n9 >> 13 & 0x7) | (n10 >> 13 & 0x7) << 3) {
                    case 0: {
                        a[i] = (this.bR[n16 + n17] | 0xFF000000);
                        break;
                    }
                    case 1: {
                        a[i] = (3 * (this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 2: {
                        a[i] = (3 * (this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 3: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 4: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 5: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 6: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 7: {
                        a[i] = (this.bR[n16 + n17 + 1] | 0xFF000000);
                        break;
                    }
                    case 8: {
                        a[i] = (3 * (this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 9: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 10: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 11: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 12: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 13: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 14: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 15: {
                        a[i] = (3 * (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 16: {
                        a[i] = (3 * (this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 17: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 18: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 19: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 20: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 21: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 22: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 23: {
                        a[i] = (3 * (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 24: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 25: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 26: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 27: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 28: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 29: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 30: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 31: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 32: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 33: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 34: {
                        a[i] = ((this.bR[n16 + n17] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 35: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 36: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 37: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 38: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 39: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 40: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 41: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 42: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 43: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 44: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 45: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 46: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 47: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 48: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 49: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 50: {
                        a[i] = ((this.bR[n16 + n17] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 51: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 52: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 53: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 54: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 55: {
                        a[i] = ((this.bR[n16 + n17 + 1] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 56: {
                        a[i] = (this.bR[n16 + n17 + super.aV] | 0xFF000000);
                        break;
                    }
                    case 57: {
                        a[i] = (3 * (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 58: {
                        a[i] = (3 * (this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 59: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 60: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 1 & 0x7F7F7F7F) + (this.bR[n16 + n17 + super.aV + 1] >> 1 & 0x7F7F7F7F) | 0xFF000000);
                        break;
                    }
                    case 61: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 62: {
                        a[i] = ((this.bR[n16 + n17 + super.aV] >> 2 & 0x3F3F3F3F) + 3 * (this.bR[n16 + n17 + super.aV + 1] >> 2 & 0x3F3F3F3F) | 0xFF000000);
                        break;
                    }
                    case 63: {
                        a[i] = (this.bR[n16 + n17 + super.aV + 1] | 0xFF000000);
                        break;
                    }
                }
            }
            else {
                a[i] = this.bR[(n9 >> 16) + (n10 >> 16) * super.aV];
            }
            if (b) {
                a[i] = this.bE.filterRGB(0, 0, a[i]);
            }
            n9 += n11;
            n10 += n12;
        }
    }
    
    protected synchronized Image E() {
        if (super.bf == null) {
            try {
                super.bf = new a.a.b.b(super.al.width, super.al.height);
            }
            catch (Exception ex) {
                this.try(this.int("iip04"));
                return null;
            }
            super.aS = super.al.width * 0.5f;
            super.aQ = super.al.height * 0.5f;
            super.a5 = super.al.width;
            super.bq = super.al.height;
        }
        if (this.bD && this.bJ != null) {
            if ((this.bJ.statusAll(true) & 0x8) != 0x0) {
                this.H();
                this.bD = false;
                this.bJ = null;
                this.bI.flush();
                this.bI = null;
                System.gc();
            }
            else if ((this.bJ.statusAll(true) & 0x4) != 0x0) {
                this.bD = false;
                this.bJ = null;
                this.bI.flush();
                this.bI = null;
                System.gc();
                this.a(this.int("iip01"), this.int("iip02"));
                return null;
            }
        }
        if (this.bR == null) {
            return null;
        }
        try {
            if (!this.G()) {
                return null;
            }
            if (super.aA.byte() == 1) {
                this.y();
            }
            super.bf.a();
        }
        catch (Exception ex2) {
            return null;
        }
        return super.bf.if;
    }
    
    public void j() {
        if (this.bL != null) {
            this.bL.removeAllElements();
            this.bL = null;
        }
        super.j();
    }
    
    protected void i() {
        if (super.bf != null && super.bf.if != null) {
            super.bf.if.flush();
        }
        super.bf = null;
        this.bI = null;
        this.bR = null;
    }
    
    private synchronized boolean G() {
        if (this.bQ) {
            this.F();
        }
        final a.a.a.a.a.c c = new a.a.a.a.a.c();
        if (super.bf == null || this.bR == null) {
            return false;
        }
        if (super.bf.do <= 0 || super.bf.new <= 0) {
            return false;
        }
        final float n = 1.0f / (float)Math.tan(super.br * 0.5f);
        final float n2 = n / (super.aS / super.aQ);
        final float n3 = (float)Math.cos(super.bl);
        final float n4 = (float)Math.sin(super.bl);
        final float n5 = (float)Math.cos(super.aM);
        final float n6 = (float)Math.sin(super.aM);
        final float n7 = (float)Math.cos(super.bd);
        final float n8 = (float)Math.sin(super.bd);
        for (int i = 0; i < this.bN; ++i) {
            for (int j = 0; j < this.bK; ++j) {
                this.bH[i][j][5] = -(n3 * this.bH[i][j][0] + n4 * this.bH[i][j][2]);
                this.bH[i][j][6] = this.bH[i][j][1];
                this.bH[i][j][7] = n3 * this.bH[i][j][2] - n4 * this.bH[i][j][0];
                final float n9 = this.bH[i][j][6];
                final float n10 = this.bH[i][j][7];
                this.bH[i][j][6] = n5 * n9 - n6 * n10;
                this.bH[i][j][7] = n5 * n10 + n6 * n9;
                final float n11 = this.bH[i][j][5];
                final float n12 = this.bH[i][j][6];
                this.bH[i][j][5] = n7 * n11 - n8 * n12;
                this.bH[i][j][6] = n7 * n12 + n8 * n11;
                if (this.bH[i][j][7] > 0.1f) {
                    this.bH[i][j][5] = this.bH[i][j][5] * n2 / this.bH[i][j][7];
                    this.bH[i][j][6] = this.bH[i][j][6] * n / this.bH[i][j][7];
                }
            }
        }
        for (int k = 0; k < this.bN - 1; ++k) {
            if (super.aW != 2 || (k + 1) % this.bK != 0) {
                for (int l = 0; l < this.bK - 1; ++l) {
                    if (this.bH[k][l][7] >= 0.1f && this.bH[k][l + 1][7] >= 0.1f && this.bH[k + 1][l][7] >= 0.1f) {
                        if (this.bH[k + 1][l + 1][7] >= 0.1f) {
                            c.a = 4;
                            c.if[0][0] = this.bH[k][l][5];
                            c.if[0][1] = this.bH[k][l][6];
                            c.if[0][2] = this.bH[k][l][3];
                            c.if[0][3] = this.bH[k][l][4];
                            c.if[1][0] = this.bH[k][l + 1][5];
                            c.if[1][1] = this.bH[k][l + 1][6];
                            c.if[1][2] = this.bH[k][l + 1][3];
                            c.if[1][3] = this.bH[k][l + 1][4];
                            c.if[2][0] = this.bH[k + 1][l + 1][5];
                            c.if[2][1] = this.bH[k + 1][l + 1][6];
                            c.if[2][2] = this.bH[k + 1][l + 1][3];
                            c.if[2][3] = this.bH[k + 1][l + 1][4];
                            c.if[3][0] = this.bH[k + 1][l][5];
                            c.if[3][1] = this.bH[k + 1][l][6];
                            c.if[3][2] = this.bH[k + 1][l][3];
                            c.if[3][3] = this.bH[k + 1][l][4];
                            this.a(c);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void if(final int[] array, final int[] array2, final int n, final int n2) {
        final int n3 = n2 / 2;
        final int n4 = n / 2;
        for (int i = 0, n5 = 0, n6 = 1; i < n3; ++i, n5 += 2, n6 += 2) {
            final int n7 = n5 * n;
            final int n8 = n6 * n;
            final int n9 = i * n4;
            for (int j = 0, n10 = 0, n11 = 1; j < n4; ++j, n10 += 2, n11 += 2) {
                array[n9 + j] = a(array2[n7 + n10], array2[n7 + n11], array2[n8 + n10], array2[n8 + n11]);
            }
        }
    }
    
    public void s() {
        if (super.aW == 1) {
            super.bw = this.a(super.bw, -1.4137167f, 1.4137167f);
        }
        else if (super.aW == 2) {
            super.bw = -1.5707964f;
            super.aO = 1.5707964f;
            final float n = 0.0f;
            super.bb = n;
            super.aX = n;
        }
        else {
            super.aW = 0;
        }
        if (super.aW == 0) {
            this.bN = 17;
            this.bK = 33;
            this.bH = new float[this.bN][this.bK][8];
            for (int i = 0; i < this.bN; ++i) {
                final float n2 = 1.5707964f - (super.aO + (super.bw - super.aO) * i / (this.bN - 1));
                final float n3 = (float)Math.sin(n2);
                for (int j = 0; j < this.bK; ++j) {
                    float n4;
                    if (super.bb - super.aX < 0.001f) {
                        n4 = 6.2831855f * j / (this.bK - 1);
                    }
                    else {
                        n4 = super.aX + (super.bb - super.aX) * j / (this.bK - 1);
                    }
                    this.bH[i][j][0] = -n3 * (float)Math.sin(n4);
                    this.bH[i][j][1] = (float)Math.cos(n2);
                    this.bH[i][j][2] = n3 * (float)Math.cos(n4);
                    this.bH[i][j][3] = j / (this.bK - 1);
                    this.bH[i][j][4] = i / (this.bN - 1);
                    this.bH[i][j][5] = 0.0f;
                    this.bH[i][j][6] = 0.0f;
                    this.bH[i][j][7] = 0.0f;
                }
            }
        }
        else if (super.aW == 1) {
            this.bN = 17;
            this.bK = 33;
            this.bH = new float[this.bN][this.bK][8];
            final float n5 = (float)Math.tan(super.bw);
            final float n6 = (float)Math.tan(super.aO);
            for (int k = 0; k < this.bN; ++k) {
                for (int l = 0; l < this.bK; ++l) {
                    float n7;
                    if (super.bb - super.aX < 0.001f) {
                        n7 = 6.2831855f * l / (this.bK - 1);
                    }
                    else {
                        n7 = super.aX + (super.bb - super.aX) * l / (this.bK - 1);
                    }
                    this.bH[k][l][0] = -(float)Math.sin(n7);
                    this.bH[k][l][1] = n6 + (n5 - n6) * k / (this.bN - 1);
                    this.bH[k][l][2] = (float)Math.cos(n7);
                    this.bH[k][l][3] = l / (this.bK - 1);
                    this.bH[k][l][4] = k / (this.bN - 1);
                    this.bH[k][l][5] = 0.0f;
                    this.bH[k][l][6] = 0.0f;
                    this.bH[k][l][7] = 0.0f;
                }
            }
        }
        else if (super.aW == 2) {
            this.bN = 54;
            this.bK = 9;
            this.bH = new float[this.bN][this.bK][8];
            final int n8 = 0;
            final int n9 = this.bN / 6;
            for (int n10 = n8; n10 < n9; ++n10) {
                float n12;
                final float n11 = n12 = (n10 - n8) / (n9 - n8 - 1);
                if (n10 == n8) {
                    n12 += 0.01f;
                }
                if (n10 == n9 - 1) {
                    n12 -= 0.01f;
                }
                for (int n13 = 0; n13 < this.bK; ++n13) {
                    final float n14 = n13 / (this.bK - 1);
                    this.bH[n10][n13][0] = n14 * 2.0f - 1.0f;
                    this.bH[n10][n13][1] = 1.0f - n11 * 2.0f;
                    this.bH[n10][n13][2] = -1.0f;
                    this.bH[n10][n13][3] = n14;
                    this.bH[n10][n13][4] = n12 / 6.0f;
                    this.bH[n10][n13][5] = 0.0f;
                    this.bH[n10][n13][6] = 0.0f;
                    this.bH[n10][n13][7] = 0.0f;
                }
            }
            final int n15 = n9;
            final int n16 = n9 + this.bN / 6;
            for (int n17 = n15; n17 < n16; ++n17) {
                float n19;
                final float n18 = n19 = (n17 - n15) / (n16 - n15 - 1);
                if (n17 == n15) {
                    n19 += 0.01f;
                }
                if (n17 == n16 - 1) {
                    n19 -= 0.01f;
                }
                for (int n20 = 0; n20 < this.bK; ++n20) {
                    final float n21 = n20 / (this.bK - 1);
                    this.bH[n17][n20][0] = 1.0f;
                    this.bH[n17][n20][1] = 1.0f - n18 * 2.0f;
                    this.bH[n17][n20][2] = n21 * 2.0f - 1.0f;
                    this.bH[n17][n20][3] = n21;
                    this.bH[n17][n20][4] = 0.16666667f + n19 / 6.0f;
                    this.bH[n17][n20][5] = 0.0f;
                    this.bH[n17][n20][6] = 0.0f;
                    this.bH[n17][n20][7] = 0.0f;
                }
            }
            final int n22 = n16;
            final int n23 = n16 + this.bN / 6;
            for (int n24 = n22; n24 < n23; ++n24) {
                float n26;
                final float n25 = n26 = (n24 - n22) / (n23 - n22 - 1);
                if (n24 == n22) {
                    n26 += 0.01f;
                }
                if (n24 == n23 - 1) {
                    n26 -= 0.01f;
                }
                for (int n27 = 0; n27 < this.bK; ++n27) {
                    final float n28 = n27 / (this.bK - 1);
                    this.bH[n24][n27][0] = 1.0f - n28 * 2.0f;
                    this.bH[n24][n27][1] = 1.0f - n25 * 2.0f;
                    this.bH[n24][n27][2] = 1.0f;
                    this.bH[n24][n27][3] = n28;
                    this.bH[n24][n27][4] = 0.33333334f + n26 / 6.0f;
                    this.bH[n24][n27][5] = 0.0f;
                    this.bH[n24][n27][6] = 0.0f;
                    this.bH[n24][n27][7] = 0.0f;
                }
            }
            final int n29 = n23;
            final int n30 = n23 + this.bN / 6;
            for (int n31 = n29; n31 < n30; ++n31) {
                float n33;
                final float n32 = n33 = (n31 - n29) / (n30 - n29 - 1);
                if (n31 == n29) {
                    n33 += 0.01f;
                }
                if (n31 == n30 - 1) {
                    n33 -= 0.01f;
                }
                for (int n34 = 0; n34 < this.bK; ++n34) {
                    final float n35 = n34 / (this.bK - 1);
                    this.bH[n31][n34][0] = -1.0f;
                    this.bH[n31][n34][1] = 1.0f - n32 * 2.0f;
                    this.bH[n31][n34][2] = 1.0f - n35 * 2.0f;
                    this.bH[n31][n34][3] = n35;
                    this.bH[n31][n34][4] = 0.5f + n33 / 6.0f;
                    this.bH[n31][n34][5] = 0.0f;
                    this.bH[n31][n34][6] = 0.0f;
                    this.bH[n31][n34][7] = 0.0f;
                }
            }
            final int n36 = n30;
            final int n37 = n30 + this.bN / 6;
            for (int n38 = n36; n38 < n37; ++n38) {
                float n40;
                final float n39 = n40 = (n38 - n36) / (n37 - n36 - 1);
                if (n38 == n36) {
                    n40 += 0.01f;
                }
                if (n38 == n37 - 1) {
                    n40 -= 0.01f;
                }
                for (int n41 = 0; n41 < this.bK; ++n41) {
                    final float n42 = n41 / (this.bK - 1);
                    this.bH[n38][n41][0] = n42 * 2.0f - 1.0f;
                    this.bH[n38][n41][1] = 1.0f;
                    this.bH[n38][n41][2] = 1.0f - n39 * 2.0f;
                    this.bH[n38][n41][3] = n42;
                    this.bH[n38][n41][4] = 0.6666667f + n40 / 6.0f;
                    this.bH[n38][n41][5] = 0.0f;
                    this.bH[n38][n41][6] = 0.0f;
                    this.bH[n38][n41][7] = 0.0f;
                }
            }
            final int n43 = n37;
            for (int n44 = n37 + this.bN / 6, n45 = n43; n45 < n44; ++n45) {
                float n47;
                final float n46 = n47 = (n45 - n43) / (n44 - n43 - 1);
                if (n45 == n43) {
                    n47 += 0.01f;
                }
                if (n45 == n44 - 1) {
                    n47 -= 0.01f;
                }
                for (int n48 = 0; n48 < this.bK; ++n48) {
                    final float n49 = n48 / (this.bK - 1);
                    this.bH[n45][n48][0] = n49 * 2.0f - 1.0f;
                    this.bH[n45][n48][1] = -1.0f;
                    this.bH[n45][n48][2] = n46 * 2.0f - 1.0f;
                    this.bH[n45][n48][3] = n49;
                    this.bH[n45][n48][4] = 0.8333333f + n47 / 6.0f;
                    this.bH[n45][n48][5] = 0.0f;
                    this.bH[n45][n48][6] = 0.0f;
                    this.bH[n45][n48][7] = 0.0f;
                }
            }
        }
        this.bP = (String)super.X.byte("param/verifi");
        if (!(this.bF = (boolean)super.X.byte("param/moirefilter"))) {
            this.bQ = false;
        }
        else {
            this.bQ = (boolean)super.X.byte("param/antialias");
        }
        super.s();
    }
    
    protected void m() {
    }
    
    private static final int a(final int n, final int n2, final int n3, final int n4) {
        int n5 = (n >> 16 & 0xFF) + (n2 >> 16 & 0xFF) + (n3 >> 16 & 0xFF) + (n4 >> 16 & 0xFF) >> 2;
        if (n5 < 0) {
            n5 = 0;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        int n6 = (n >> 8 & 0xFF) + (n2 >> 8 & 0xFF) + (n3 >> 8 & 0xFF) + (n4 >> 8 & 0xFF) >> 2;
        if (n6 < 0) {
            n6 = 0;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        int n7 = (n & 0xFF) + (n2 & 0xFF) + (n3 & 0xFF) + (n4 & 0xFF) >> 2;
        if (n7 < 0) {
            n7 = 0;
        }
        if (n7 > 255) {
            n7 = 255;
        }
        return (n & 0xFF000000) + (n5 << 16) + (n6 << 8) + n7;
    }
    
    protected void f() {
        if (this.bP != null) {
            try {
                this.bE.a(new DataInputStream(new BufferedInputStream(new URL(String.valueOf(this.bP) + "?lookuptable").openStream(), 4096)));
            }
            catch (Exception ex2) {
                this.bE.a(0);
            }
        }
        try {
            this.bI = Toolkit.getDefaultToolkit().getImage(new URL((URL)super.X.byte("param/documentbase"), b.au));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.bI = null;
            this.try(ex.getMessage());
        }
        if (this.bI != null) {
            (this.bJ = new MediaTracker(this)).addImage(this.bI, 0);
            this.bD = true;
        }
    }
    
    public void r() {
        super.r();
        this.bR = null;
        this.bL = null;
    }
    
    private void F() {
        if (super.aV <= 0) {
            return;
        }
        final float[] array = new float[3];
        final float[] array2 = new float[3];
        this.if(array);
        array2[0] = 0.0f;
        array2[1] = array[1];
        array2[2] = array[2];
        this.a(array2);
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        final float[] array5 = new float[2];
        final float[] array6 = new float[2];
        this.a(0, super.bf.do - 1, array3);
        this.a(super.bf.new, super.bf.do, array4);
        this.a(super.bf.new, 0, array6);
        this.a(0, 0, array5);
        float n = Math.min(array3[0], array5[0]);
        float n2 = Math.max(array4[0], array6[0]);
        if (Math.max(array3[0], array5[0]) - Math.min(array3[0], array5[0]) > 0.5f) {
            n = Math.max(array3[0], array5[0]);
        }
        else if (Math.max(array4[0], array6[0]) - Math.min(array4[0], array6[0]) > 0.5f) {
            n2 = Math.min(array4[0], array6[0]);
        }
        final float a = this.a(n, 0.0f, 1.0f);
        final float a2 = this.a(n2, 0.0f, 1.0f);
        this.bO = 0;
        float n3;
        do {
            if (a > a2) {
                n3 = (a2 - a + 1.0f) * this.bM[this.bO + 1].width;
            }
            else {
                n3 = (a2 - a) * this.bM[this.bO + 1].width;
            }
            ++this.bO;
        } while (n3 > super.a5 * this.bC && this.bO <= this.bG);
        if (this.bO > 0) {
            --this.bO;
        }
        this.a(array);
        if (this.bO != this.bB) {
            this.bR = this.bL.elementAt(this.bO);
            super.aV = this.bM[this.bO].width;
            super.bv = this.bM[this.bO].height;
            this.bB = this.bO;
        }
    }
    
    private static final int a(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = (n >> 16 & 0xFF) * 4 + (n2 >> 16 & 0xFF) + (n3 >> 16 & 0xFF) + (n4 >> 16 & 0xFF) + (n5 >> 16 & 0xFF) >> 3;
        if (n6 < 0) {
            n6 = 0;
        }
        if (n6 > 255) {
            n6 = 255;
        }
        int n7 = (n >> 8 & 0xFF) * 4 + (n2 >> 8 & 0xFF) + (n3 >> 8 & 0xFF) + (n4 >> 8 & 0xFF) + (n5 >> 8 & 0xFF) >> 3;
        if (n7 < 0) {
            n7 = 0;
        }
        if (n7 > 255) {
            n7 = 255;
        }
        int n8 = (n & 0xFF) * 4 + (n2 & 0xFF) + (n3 & 0xFF) + (n4 & 0xFF) + (n5 & 0xFF) >> 3;
        if (n8 < 0) {
            n8 = 0;
        }
        if (n8 > 255) {
            n8 = 255;
        }
        return (n2 & 0xFF000000) + (n6 << 16) + (n7 << 8) + n8;
    }
    
    private void a(final int[] array, final int[] array2, final int n, final int n2) {
        for (int i = 0; i < n; ++i) {
            array[i] = array2[i];
        }
        for (int j = 1; j < n2 - 1; ++j) {
            final int n3 = j * n;
            final int n4 = n3 - n;
            final int n5 = n3 + n;
            array[n3] = a(array2[n3], array2[n4], array2[n3 + n - 1], array2[n5], array2[n3 + 1]);
            for (int k = 1; k < n - 1; ++k) {
                array[n3 + k] = a(array2[n3 + k], array2[n4 + k], array2[n3 + k - 1], array2[n5 + k], array2[n3 + k + 1]);
            }
            array[n3 + n - 1] = a(array2[n3 + n - 1], array2[n4 + n - 1], array2[n3 + n - 2], array2[n5 + n - 1], array2[n3]);
        }
        final int n6 = (n2 - 1) * n;
        for (int l = 0; l < n; ++l) {
            array[n6 + l] = array2[n6 + l];
        }
    }
}