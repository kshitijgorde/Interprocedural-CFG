// 
// Decompiled by Procyon v0.5.30
// 

final class n extends Class43 implements Interface9
{
    long nativeid;
    
    private final native void PA(final char p0, final int p1, final int p2, final int p3, final boolean p4, final aa p5, final int p6, final int p7);
    
    @Override
    public final native void w(final boolean p0);
    
    @Override
    final native void fa(final char p0, final int p1, final int p2, final int p3, final boolean p4);
    
    private final native void S(final oa p0, final ya p1, final byte[][] p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7);
    
    n(final oa oa, final ya ya, final Class197 class197, final Class324[] array, final Class332[] array2) {
        super(oa, class197);
        final byte[][] array3 = new byte[array.length][];
        final int[] array4 = new int[array.length];
        final int[] array5 = new int[array.length];
        final int[] array6 = new int[array.length];
        final int[] array7 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = array[i].aByteArray2717;
            array4[i] = array[i].anInt2722;
            array5[i] = array[i].anInt2720;
            array6[i] = array[i].anInt2725;
            array7[i] = array[i].anInt2721;
        }
        this.S(oa, ya, array3, array[0].anIntArray2718, array4, array5, array6, array7);
    }
    
    @Override
    final void method409(final char c, final int n, final int n2, final int n3, final boolean b, final aa aa, final int n4, final int n5) {
        this.PA(c, n, n2, n3, b, aa, n4, n5);
    }
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
}
