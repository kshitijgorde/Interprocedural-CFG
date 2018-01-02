// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

class j extends n
{
    protected d a1;
    protected d a8;
    protected d a6;
    protected d a4;
    protected d be;
    protected d bc;
    protected d ba;
    protected d a9;
    protected d bb;
    protected float[][] a5;
    protected int[] a3;
    protected float[] a2;
    protected float[] a7;
    private boolean bd;
    
    private void int() {
        if (super.ay != 15) {
            return;
        }
        if (this.bc.b3 != null) {
            final int[] b3 = this.bc.b3;
            if (b3.length == 0) {
                return;
            }
            if (b3[b3.length - 1] != -1) {
                final int[] b4 = new int[b3.length + 1];
                System.arraycopy(b3, 0, b4, 0, b3.length);
                b4[b3.length] = -1;
                this.bc.b3 = b4;
            }
            final int[] b5 = this.bc.b3;
            int n = 0;
            int n2 = 0;
            final int length = b5.length;
            int n3 = 0;
            final int[] array = new int[length];
            for (int i = 0; i < length; ++i) {
                if (b5[i] == -1) {
                    if (n < 3) {
                        ++n2;
                    }
                    else {
                        System.arraycopy(b5, i - n, array, n3, n + 1);
                        n3 += n + 1;
                    }
                    n = 0;
                }
                else {
                    ++n;
                }
            }
            if (n2 > 0) {
                System.out.println("faces with less than 3 vertices in object: " + this.aB);
                System.arraycopy(array, 0, this.bc.b3 = new int[n3], 0, n3);
            }
        }
    }
    
    protected j(final String s, final int n, final Prove3d prove3d) {
        super(s, n, prove3d);
        this.a7 = new float[] { -1.0f, -1.0f, -1.0f };
        this.a3 = null;
        this.a2 = new float[3];
        this.bd = false;
        switch (n) {
            case 15: {
                this.a1 = new d(this, 34, 9);
                this.a8 = new d(this, 35, 9);
                this.a6 = new d(this, 36, 10);
                this.a4 = new d(this, 37, 10);
                this.be = new d(this, 38, 1);
                this.bc = new d(this, 39, 9);
                this.ba = new d(this, 40, 6);
                this.a9 = new d(this, 41, 1);
                this.bb = new d(this, 42, 9);
                this.bc.b3 = new int[0];
                this.bb.b3 = new int[0];
                this.be.cj = true;
                this.a9.cj = true;
                this.ba.ci = 0.0f;
            }
            case 16: {
                this.a1 = new d(this, 34, 9);
                this.a6 = new d(this, 36, 10);
                this.bc = new d(this, 39, 9);
            }
            case 17: {
                this.a6 = new d(this, 36, 10);
            }
            default: {}
        }
    }
    
    protected void if(final boolean b) {
        if (!this.bd) {
            this.int();
            k.a(this.a2, this.a7, ((h)this.a6.b6).aR.ck);
            this.bd = true;
        }
        if (!super.az.do(this.a2, this.a7)) {
            return;
        }
        switch (super.ay) {
            case 15: {
                if (this.bc.b3 == null) {
                    return;
                }
                if (this.a3 == null) {
                    this.new();
                }
                super.az.a(this, b);
            }
            case 16: {
                super.az.do(this, b);
            }
            case 17: {
                super.az.if(this, b);
            }
            default: {}
        }
    }
    
    protected void new() {
        if (super.ay != 15) {
            return;
        }
        final boolean b = this.ba.ci > 0.0f;
        float[][] a5 = null;
        float[] array = null;
        final float[] ck = ((h)this.a6.b6).aR.ck;
        final int[] b2 = this.bc.b3;
        final int length = b2.length;
        if (length == 0) {
            return;
        }
        this.a3 = new int[length];
        int n = 0;
        final int n2 = ck.length / 3;
        if (b) {
            a5 = new float[n2][4];
            array = new float[n2];
        }
        int i = 0;
        int n3 = 0;
        while (i < length) {
            if (b2[i++] == -1) {
                ++n3;
            }
        }
        if (b2[length - 1] != -1) {
            ++n3;
        }
        this.a5 = new float[n3][4];
        int j = 0;
        while (j < length) {
            final int n4 = b2[j];
            final int n5 = (n4 << 1) + n4;
            final float n6 = ck[n5];
            final float n7 = ck[n5 + 1];
            final float n8 = ck[n5 + 2];
            final int n9 = b2[j + 1];
            final int n10 = (n9 << 1) + n9;
            final float n11 = ck[n10];
            final float n12 = ck[n10 + 1];
            final float n13 = ck[n10 + 2];
            final int n14 = b2[j + 2];
            final int n15 = (n14 << 1) + n14;
            final float[] a6 = k.a(new float[] { n11 - n6, n12 - n7, n13 - n8 }, new float[] { ck[n15] - n6, ck[n15 + 1] - n7, ck[n15 + 2] - n8 }, !this.be.cj);
            final float[] array2 = this.a5[n];
            for (int k = 0; k < 3; ++k) {
                array2[k] = a6[k];
            }
            this.a3[j] = n;
            this.a3[j + 1] = n;
            this.a3[j + 2] = n;
            if (b) {
                final float[] array3 = a5[n4];
                final int n16 = 0;
                array3[n16] += a6[0];
                final float[] array4 = a5[n4];
                final int n17 = 1;
                array4[n17] += a6[1];
                final float[] array5 = a5[n4];
                final int n18 = 2;
                array5[n18] += a6[2];
                final float[] array6 = array;
                final int n19 = n4;
                ++array6[n19];
                final float[] array7 = a5[n9];
                final int n20 = 0;
                array7[n20] += a6[0];
                final float[] array8 = a5[n9];
                final int n21 = 1;
                array8[n21] += a6[1];
                final float[] array9 = a5[n9];
                final int n22 = 2;
                array9[n22] += a6[2];
                final float[] array10 = array;
                final int n23 = n9;
                ++array10[n23];
                final float[] array11 = a5[n14];
                final int n24 = 0;
                array11[n24] += a6[0];
                final float[] array12 = a5[n14];
                final int n25 = 1;
                array12[n25] += a6[1];
                final float[] array13 = a5[n14];
                final int n26 = 2;
                array13[n26] += a6[2];
                final float[] array14 = array;
                final int n27 = n14;
                ++array14[n27];
            }
            j += 3;
            while (b2[j++] != -1) {
                this.a3[j - 1] = n;
                if (b) {
                    final int n28 = b2[j - 1];
                    final float[] array15 = a5[n28];
                    final int n29 = 0;
                    array15[n29] += a6[0];
                    final float[] array16 = a5[n28];
                    final int n30 = 1;
                    array16[n30] += a6[1];
                    final float[] array17 = a5[n28];
                    final int n31 = 2;
                    array17[n31] += a6[2];
                    final float[] array18 = array;
                    final int n32 = n28;
                    ++array18[n32];
                }
            }
            ++n;
        }
        if (b) {
            for (int l = 0; l < n2; ++l) {
                if (array[l] != 0.0f) {
                    array[l] = 1.0f / array[l];
                    final float[] array19 = a5[l];
                    final int n33 = 0;
                    array19[n33] *= array[l];
                    final float[] array20 = a5[l];
                    final int n34 = 1;
                    array20[n34] *= array[l];
                    final float[] array21 = a5[l];
                    final int n35 = 2;
                    array21[n35] *= array[l];
                }
                k.a(a5[l], true);
            }
            System.arraycopy(b2, 0, this.a3, 0, b2.length);
            this.a5 = a5;
        }
    }
    
    public boolean if(final float[] array, final float[] array2) {
        if (!this.bd) {
            k.a(this.a2, this.a7, ((h)this.a6.b6).aR.ck);
            this.bd = true;
        }
        System.arraycopy(this.a2, 0, array, 0, 3);
        System.arraycopy(this.a7, 0, array2, 0, 3);
        return true;
    }
}
