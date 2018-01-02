// 
// Decompiled by Procyon v0.5.30
// 

public abstract class f implements d
{
    public void a() {
    }
    
    public static final void a(final int[] array, final int[] array2, final int[] array3, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n5 * n + n4;
        final int n10 = n7 * n + n6;
        final int n11 = n3 * n + n2;
        final int n12 = array2[n9] >> 16 & 0xFF;
        final int n13 = array2[n9] >> 8 & 0xFF;
        final int n14 = array2[n9] & 0xFF;
        final int n15 = array3[n10] >> 16 & 0xFF;
        final int n16 = array3[n10] >> 8 & 0xFF;
        final int n17 = array3[n10] & 0xFF;
        array[n11] = ((n8 * (n12 - n15) >> 8) + n15 << 16) + ((n8 * (n13 - n16) >> 8) + n16 << 8) + ((n8 * (n14 - n17) >> 8) + n17);
    }
}
