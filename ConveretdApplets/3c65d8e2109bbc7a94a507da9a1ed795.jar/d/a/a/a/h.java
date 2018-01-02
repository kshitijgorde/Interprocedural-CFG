// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

public class h
{
    public void a(final a a, final short[] array, final short[] array2) throws ArrayIndexOutOfBoundsException {
        final short[] array3 = new short[8];
        final short byte1 = a.byte();
        final short n = (short)(byte1 ^ 0x1);
        a.do(n);
        if (byte1 < 0 || byte1 > 1 || n < 0 || n > 1) {
            throw new ArrayIndexOutOfBoundsException("Gsm_Short_Term_Synthesis_Filter: Indexing LARpp incorrectly. Should be >= 0 and <= 1");
        }
        final short[] do1 = a.do((int)byte1);
        final short[] do2 = a.do((int)n);
        a(array, do1);
        if(do2, do1, array3);
        a(array3);
        this.a(a, array3, 13, array2, 0);
        a(do2, do1, array3);
        a(array3);
        this.a(a, array3, 14, array2, 13);
        do(do2, do1, array3);
        a(array3);
        this.a(a, array3, 13, array2, 27);
        if(do1, array3);
        a(array3);
        this.a(a, array3, 120, array2, 40);
    }
    
    public void a(final a a, final short[] array, final short[] array2, final int[] array3) throws ArrayIndexOutOfBoundsException {
        final short[] array4 = new short[8];
        final short byte1 = a.byte();
        final short n = (short)(byte1 ^ 0x1);
        a.do(n);
        if (byte1 < 0 || byte1 > 1 || n < 0 || n > 1) {
            throw new ArrayIndexOutOfBoundsException("Gsm_Short_Term_Synthesis_Filter: Indexing LARpp incorrectly. Should be >= 0 and <= 1");
        }
        final short[] do1 = a.do((int)byte1);
        final short[] do2 = a.do((int)n);
        a(array, do1);
        if(do2, do1, array4);
        a(array4);
        a(a, array4, 13, array2, array3, 0);
        a(do2, do1, array4);
        a(array4);
        a(a, array4, 14, array2, array3, 13);
        do(do2, do1, array4);
        a(array4);
        a(a, array4, 13, array2, array3, 27);
        if(do1, array4);
        a(array4);
        a(a, array4, 120, array2, array3, 40);
        a.a(byte1, do1);
        a.a(n, do2);
    }
    
    public static void a(final short[] array, final short[] array2) {
        final short n = 0;
        int n2 = 0;
        a(array, array2, n2++, n, (short)0, (short)(-32), (short)13107);
        a(array, array2, n2++, n, (short)0, (short)(-32), (short)13107);
        a(array, array2, n2++, n, (short)2048, (short)(-16), (short)13107);
        a(array, array2, n2++, n, (short)(-2560), (short)(-16), (short)13107);
        a(array, array2, n2++, n, (short)94, (short)(-8), (short)19223);
        a(array, array2, n2++, n, (short)(-1792), (short)(-8), (short)17476);
        a(array, array2, n2++, n, (short)(-341), (short)(-4), (short)31454);
        a(array, array2, n2++, n, (short)(-1144), (short)(-4), (short)29708);
    }
    
    public static void a(final short[] array, final short[] array2, final int n, final short n2, final short n3, final short n4, final short n5) {
        final short do1 = j.do(n5, j.if((short)(j.new(array[n], n4) << 10), (short)(n3 << 1)));
        array2[n] = j.new(do1, do1);
    }
    
    public static void if(final short[] array, final short[] array2, final short[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = j.new(j.if((int)array[i], 2), j.if((int)array2[i], 2));
            array3[i] = j.new(array3[i], j.if((int)array[i], 1));
        }
    }
    
    public static void a(final short[] array, final short[] array2, final short[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = j.new(j.if((int)array[i], 1), j.if((int)array2[i], 1));
        }
    }
    
    public static void do(final short[] array, final short[] array2, final short[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = j.new(j.if((int)array[i], 2), j.if((int)array2[i], 2));
            array3[i] = j.new(array3[i], j.if((int)array2[i], 1));
        }
    }
    
    public static void if(final short[] array, final short[] array2) {
        for (int i = 0; i < 8; ++i) {
            array2[i] = array[i];
        }
    }
    
    public static void a(final short[] array) {
        for (int i = 0; i < 8; ++i) {
            if (array[i] < 0) {
                final short n = (short)((array[i] == -32768) ? 32767 : (-array[i]));
                array[i] = (short)(-((n < 11059) ? (n << 1) : ((n < 20070) ? (n + 11059) : j.new((short)(n >> 2), (short)26112))));
            }
            else {
                final short n2 = array[i];
                array[i] = (short)((n2 < 11059) ? (n2 << 1) : ((n2 < 20070) ? (n2 + 11059) : j.new((short)(n2 >> 2), (short)26112)));
            }
        }
    }
    
    private void a(final a a, final short[] array, int i, final short[] array2, int n) {
        final short[] int1 = a.int();
        while (i != 0) {
            --i;
            short new2;
            short new1 = new2 = array2[n];
            for (int j = 0; j < 8; ++j) {
                final short n2 = int1[j];
                final short n3 = array[j];
                int1[j] = new1;
                new1 = j.new(n2, j.do(n3, new2));
                new2 = j.new(new2, j.do(n3, n2));
            }
            array2[n++] = new2;
        }
        a.if(int1);
    }
    
    public static void a(final a a, final short[] array, int i, final short[] array2, final int[] array3, final int n) {
        final short[] if1 = a.if();
        int n2 = n;
        while (i != 0) {
            --i;
            short if2 = array2[n2];
            for (int j = 7; j >= 0; --j) {
                final short n3 = array[j];
                final short n4 = if1[j];
                if2 = j.if(if2, (short)((n3 == -32768 && n4 == -32768) ? 32767 : (0xFFFF & n3 * n4 + 16384 >> 15)));
                if1[j + 1] = j.new(if1[j], (short)((n3 == -32768 && if2 == -32768) ? 32767 : (0xFFFF & n3 * if2 + 16384 >> 15)));
            }
            array3[n2++] = (if1[0] = if2);
        }
        a.do(if1);
    }
}
