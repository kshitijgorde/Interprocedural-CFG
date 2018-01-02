// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

class o3 extends o2
{
    public static long a(final byte[] array, final int n, int i) {
        long n2 = 0L;
        int n3 = n;
        while (i >= 64) {
            n2 = n2 + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF) + (array[n3++] & 0xFF);
            i -= 64;
        }
        while (i > 0) {
            n2 += (array[n3++] & 0xFF);
            --i;
        }
        return n2;
    }
    
    public static int a(final char[] array, final int n) {
        return (array[n] << 8 ^ array[n + 1] << 4 ^ array[n + 2]) * 40543 >> 4 & 0xFFF;
    }
    
    public static long a(final byte[] array, int n) {
        return (array[n++] & 0xFF) | (array[n++] & 0xFF) << 8 | (array[n++] & 0xFF) << 16 | (array[n++] & 0xFF) << 24;
    }
}
