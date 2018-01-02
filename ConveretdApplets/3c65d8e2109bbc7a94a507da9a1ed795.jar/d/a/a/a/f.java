// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

public class f
{
    private short new;
    private short do;
    private short byte;
    private short if;
    private int int;
    private static final int for = 0;
    private static final int try = 1;
    private short[] a;
    
    public f() {
        this.int = 0;
        this.a = new short[40];
    }
    
    public void a(final short[] array, final short[] array2, final short[] array3, final int n, final short[] array4, final int n2) {
        final short[] array5 = new short[13];
        final short[] array6 = new short[13];
        this.a(array);
        this.a(array5, array3, n);
        this.a(array5, array4, n2, array2, n);
        this.a(array4, array6, n2, 0);
        a(array3[n], array6, array, 0);
    }
    
    private void a(final short[] array) {
        for (int i = 0; i <= 39; ++i) {
            final short if1 = j.if(4096 + (array[i + 0] * -134 + array[i + 1] * -374 + array[i + 3] * 2054 + array[i + 4] * 5741 + array[i + 5] * 8192 + array[i + 6] * 5741 + array[i + 7] * 2054 + array[i + 9] * -374 + array[i + 10] * -134), 13);
            this.a[i] = (short)((if1 < -32768) ? -32768 : ((if1 > 32767) ? 32767 : if1));
        }
    }
    
    private void a(final short[] array, final short[] array2, final int n) {
        short n2 = 0;
        final int n4;
        int n3 = (n4 = 0 + (this.a(0, 1) + this.a(0, 2) + this.a(0, 3) + this.a(0, 4) + this.a(0, 5) + this.a(0, 6) + this.a(0, 7) + this.a(0, 8) + this.a(0, 9) + this.a(0, 10) + this.a(0, 11) + this.a(0, 12))) + this.a(0, 0) << 1;
        final int n5 = 0 + (this.a(1, 0) + this.a(1, 1) + this.a(1, 2) + this.a(1, 3) + this.a(1, 4) + this.a(1, 5) + this.a(1, 6) + this.a(1, 7) + this.a(1, 8) + this.a(1, 9) + this.a(1, 10) + this.a(1, 11) + this.a(1, 12)) << 1;
        if (n5 > n3) {
            n2 = 1;
            n3 = n5;
        }
        final int n6 = 0 + (this.a(2, 0) + this.a(2, 1) + this.a(2, 2) + this.a(2, 3) + this.a(2, 4) + this.a(2, 5) + this.a(2, 6) + this.a(2, 7) + this.a(2, 8) + this.a(2, 9) + this.a(2, 10) + this.a(2, 11) + this.a(2, 12)) << 1;
        if (n6 > n3) {
            n2 = 2;
            n3 = n6;
        }
        if (n4 + this.a(3, 12) << 1 > n3) {
            n2 = 3;
        }
        for (int i = 0; i <= 12; ++i) {
            array[i] = this.a[n2 + 3 * i];
        }
        array2[n] = n2;
    }
    
    private int a(final int n, final int n2) {
        final short if1 = j.if((int)this.a[n + 3 * n2], 2);
        return if1 * if1;
    }
    
    private void a(final short[] array, final short[] array2, final int n, final short[] array3, final int n2) throws IllegalArgumentException {
        short n3 = 0;
        for (int i = 0; i <= 12; ++i) {
            final short a = j.a(array[i]);
            if (a > n3) {
                n3 = a;
            }
        }
        int n4 = 0;
        short n5 = j.if((int)n3, 9);
        int n6 = 0;
        for (int j = 0; j <= 5; ++j) {
            if (n5 <= 0) {
                n6 |= 0x1;
            }
            else {
                n6 |= 0x0;
            }
            n5 = j.if((int)n5, 1);
            if (n4 > 5) {
                throw new IllegalArgumentException("APCM_quantization: exp = " + n4 + " is out of range. Should be <= 5");
            }
            if (n6 == 0) {
                n4 = (short)(n4 + 1);
            }
        }
        if (n4 > 6 || n4 < 0) {
            throw new IllegalArgumentException("APCM_quantization: exp = " + n4 + " is out of range. Should be >= -4 and <= 6");
        }
        short if1 = (short)(n4 + 5);
        if (if1 > 11 || if1 < 0) {
            throw new IllegalArgumentException("APCM_quantization: temp = " + if1 + " is out of range. Should be >= 0 and <= 11");
        }
        final short new1 = j.new(j.if((int)n3, if1), (short)(n4 << 3));
        this.a(new1, 0);
        final short new2 = this.new;
        final short do1 = this.do;
        if (new2 > 4096 || new2 < -4096) {
            throw new IllegalArgumentException("APCM_quantization: exp = " + new2 + " is out of range. Should be >= -4096 and <= 4096");
        }
        if (do1 < 0 || do1 > 7) {
            throw new IllegalArgumentException("APCM_quantization: mant = " + do1 + " is out of range. Should be >= 0 and <= 7");
        }
        final short n7 = (short)(6 - new2);
        final short n8 = i.void[do1];
        for (int k = 0; k <= 12; ++k) {
            if (n7 < 0 || n7 >= 16) {
                throw new IllegalArgumentException("APCM_quantization: temp = " + if1 + " is out of range. Should be >= 0 and < 16");
            }
            if1 = j.if((int)j.for((short)(array[k] << n7), n8), 12);
            array2[k + n] = (short)(if1 + 4);
        }
        this.do = do1;
        this.new = new2;
        array3[n2] = new1;
    }
    
    public void a(final short n, final int n2) throws IllegalArgumentException {
        short n3 = 0;
        if (n > 15) {
            n3 = (short)(j.if((int)n, 3) - 1);
        }
        short n4 = (short)(n - (n3 << 3));
        short n5;
        if (n4 == 0) {
            n3 = -4;
            n5 = 7;
        }
        else {
            while (n4 <= 7) {
                n4 = (short)(n4 << 1 | 0x1);
                --n3;
            }
            n5 = (short)(n4 - 8);
        }
        if (n3 < -4 || n3 > 6) {
            throw new IllegalArgumentException("APCM_quantization_xmaxc_to_exp_mant: exp = " + n3 + " is out of range. Should be >= -4 and <= 6");
        }
        if (n5 < 0 || n5 > 7) {
            throw new IllegalArgumentException("APCM_quantization_xmaxc_to_exp_mant: mant = " + n5 + " is out of range. Should be >= 0 and <= 7");
        }
        if (n2 == 0) {
            this.new = n3;
            this.do = n5;
        }
        else {
            this.byte = n3;
            this.if = n5;
        }
    }
    
    public void a(final short n, final short n2, final int n3, final short[] array, final short[] array2) {
        final short[] array3 = new short[13];
        this.a(n, 1);
        this.a(array, array3, n3, 1);
        a(n2, array3, array2, 1);
    }
    
    public void a(final short[] array, final short[] array2, int n, final int n2) throws IllegalArgumentException {
        short n3;
        short n4;
        if (n2 == 0) {
            n3 = i.goto[this.do];
            n4 = j.if((short)6, this.new);
        }
        else {
            n3 = i.goto[this.if];
            n4 = j.if((short)6, this.byte);
        }
        final short if1 = j.if((short)1, (int)j.if(n4, (short)1));
        this.int = 0;
        for (int i = 0; i < 13; ++i) {
            final short n5 = (short)((array[n++] << 1) - 7);
            if (n5 > 7 || n5 < -7) {
                throw new IllegalArgumentException("APCM_inverse_quantization: temp = " + n5 + " is out of range. Should be >= -7 and <= 7");
            }
            array2[this.int++] = j.a(j.new(j.do(n3, (short)(n5 << 12)), if1), (int)n4);
        }
    }
    
    public static void a(final short n, final short[] array, final short[] array2, final int n2) throws IllegalArgumentException {
        int n3 = 13;
        int n4 = 0;
        int n5;
        if (n2 == 0) {
            n5 = 5;
        }
        else {
            n5 = 0;
        }
        if (0 > n || n > 3) {
            throw new IllegalArgumentException("RPE_grid_positioning: Mc = " + n + " is out of range. Should be >= 0 and <= 3");
        }
        switch (n) {
            case 3: {
                array2[n5++] = 0;
                do {
                    array2[n5++] = 0;
                    array2[n5++] = 0;
                    array2[n5++] = array[n4++];
                } while (--n3 != 0);
                break;
            }
            case 2: {
                do {
                    array2[n5++] = 0;
                    array2[n5++] = 0;
                    array2[n5++] = array[n4++];
                } while (--n3 != 0);
                break;
            }
            case 1: {
                do {
                    array2[n5++] = 0;
                    array2[n5++] = array[n4++];
                    array2[n5++] = 0;
                } while (--n3 != 0);
                break;
            }
            case 0: {
                do {
                    array2[n5++] = array[n4++];
                    array2[n5++] = 0;
                    array2[n5++] = 0;
                } while (--n3 != 0);
                break;
            }
        }
        if (n2 == 0) {
            array2[n5++] = 0;
        }
    }
}
