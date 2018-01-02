// 
// Decompiled by Procyon v0.5.30
// 

final class t extends s implements Interface9
{
    private Class148 aClass148_3324;
    private oa anOa3325;
    long nativeid;
    private int anInt3326;
    
    @Override
    final void method3422(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean[][] array) {
        this.anOa3325.method1930().method148(this, n, n2, n3, n4, n5, n6, n7, array);
    }
    
    private final native void V(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int[] p6);
    
    private final native void ga(final oa p0, final ya p1, final int p2, final int p3, final int[][] p4, final int[][] p5, final int p6, final int p7, final int p8);
    
    @Override
    final void method3416(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int anInt3326, final int n4) {
        this.anInt3326 = anInt3326;
        int n5 = 0;
        final float[] array2 = new float[this.aClass148_3324.method2424(0)];
        for (Class98_Sub5 class98_Sub5 = (Class98_Sub5)this.aClass148_3324.method2418(32); class98_Sub5 != null; class98_Sub5 = (Class98_Sub5)this.aClass148_3324.method2417(92)) {
            array2[n5++] = class98_Sub5.method956(false);
        }
        this.q(array2);
        for (int i = 0; i < n3 + n3; ++i) {
            for (int j = 0; j < n3 + n3; ++j) {
                if (array[i][j]) {
                    final int n6 = n - n3 + i;
                    final int n7 = n2 - n3 + j;
                    if (n6 >= 0 && n6 < super.anInt2203 && n7 >= 0 && n7 < super.anInt2204) {
                        this.method3425(n6, n7);
                    }
                }
            }
        }
    }
    
    @Override
    final native void wa(final r p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    @Override
    final native r fa(final int p0, final int p1, final r p2);
    
    @Override
    final void method3426(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int n4) {
        this.anInt3326 = -1;
        int n5 = 0;
        final float[] array2 = new float[this.aClass148_3324.method2424(0)];
        for (Class98_Sub5 class98_Sub5 = (Class98_Sub5)this.aClass148_3324.method2418(32); class98_Sub5 != null; class98_Sub5 = (Class98_Sub5)this.aClass148_3324.method2417(102)) {
            array2[n5++] = class98_Sub5.method956(false);
        }
        this.q(array2);
        for (int i = 0; i < n3 + n3; ++i) {
            for (int j = 0; j < n3 + n3; ++j) {
                if (array[i][j]) {
                    final int n6 = n - n3 + i;
                    final int n7 = n2 - n3 + j;
                    if (n6 >= 0 && n6 < super.anInt2203 && n7 >= 0 && n7 < super.anInt2204) {
                        this.method3425(n6, n7);
                    }
                }
            }
        }
    }
    
    @Override
    final void method3425(final int n, final int n2) {
        if (this.anInt3326 < 0) {
            this.anOa3325.method1930().method145(this, n, n2);
        }
        else {
            this.anOa3325.method1930().method136(this, n, n2, this.anInt3326);
        }
    }
    
    @Override
    final native void ka(final int p0, final int p1, final int p2);
    
    @Override
    final native void U(final int p0, final int p1, final int[] p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final int[] p8, final int[] p9, final int p10, final int p11, final int p12, final boolean p13);
    
    @Override
    public final native void w(final boolean p0);
    
    @Override
    final native void CA(final r p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    @Override
    final void method3424(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int[] array9, final int[] array10, final int[] array11, final int n3, final int n4, final int n5, final boolean b) {
        boolean b2 = false;
        if (array8 != null) {
            for (int i = 0; i < array8.length; ++i) {
                if (array8[i] != -1) {
                    b2 = true;
                    break;
                }
            }
        }
        final int length = array8.length;
        final int[] array12 = new int[length * 3];
        final int[] array13 = new int[length * 3];
        final int[] array14 = new int[length * 3];
        final int[] array15 = new int[length * 3];
        final int[] array16 = new int[length * 3];
        final int[] array17 = (int[])((array9 != null) ? new int[length * 3] : null);
        final int[] array18 = (int[])((array2 != null) ? new int[length * 3] : null);
        final int[] array19 = (int[])((array4 != null) ? new int[length * 3] : null);
        int n6 = 0;
        for (int j = 0; j < length; ++j) {
            final int n7 = array5[j];
            final int n8 = array6[j];
            final int n9 = array7[j];
            array12[n6] = array[n7];
            array13[n6] = array3[n7];
            array14[n6] = array8[j];
            array15[n6] = array10[j];
            array16[n6] = array11[j];
            if (array9 != null) {
                array17[n6] = array9[j];
            }
            if (array2 != null) {
                array18[n6] = array2[n7];
            }
            if (array4 != null) {
                array19[n6] = array4[n7];
            }
            ++n6;
            array12[n6] = array[n8];
            array13[n6] = array3[n8];
            array14[n6] = array8[j];
            array15[n6] = array10[j];
            array16[n6] = array11[j];
            if (array9 != null) {
                array17[n6] = array9[j];
            }
            if (array2 != null) {
                array18[n6] = array2[n8];
            }
            if (array4 != null) {
                array19[n6] = array4[n8];
            }
            ++n6;
            array12[n6] = array[n9];
            array13[n6] = array3[n9];
            array14[n6] = array8[j];
            array15[n6] = array10[j];
            array16[n6] = array11[j];
            if (array9 != null) {
                array17[n6] = array9[j];
            }
            if (array2 != null) {
                array18[n6] = array2[n9];
            }
            if (array4 != null) {
                array19[n6] = array4[n9];
            }
            ++n6;
        }
        if (b2 || array17 != null) {
            this.U(n, n2, array12, array18, array13, array19, array14, array17, array15, array16, n3, n4, n5, b);
        }
    }
    
    @Override
    final void method3421(final Class98_Sub5 class98_Sub5, final int[] array) {
        this.aClass148_3324.method2419(class98_Sub5, -20911);
        this.V(class98_Sub5.hashCode(), class98_Sub5.method954(7019), class98_Sub5.method963((byte)94), class98_Sub5.method962(28699), class98_Sub5.method958(-14), class98_Sub5.method961((byte)(-78)), array);
    }
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    private final native void q(final float[] p0);
    
    @Override
    final native void YA();
    
    @Override
    final boolean method3418(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        return true;
    }
    
    t(final oa anOa3325, final ya ya, final int n, final int n2, final int[][] array, final int[][] array2, final int n3, final int n4, final int n5) {
        super(n, n2, n3, array);
        this.aClass148_3324 = new Class148();
        this.anInt3326 = -1;
        this.ga(this.anOa3325 = anOa3325, ya, n, n2, super.anIntArrayArray2201, array2, n3, n4, n5);
    }
}
