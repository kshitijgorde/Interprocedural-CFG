// 
// Decompiled by Procyon v0.5.30
// 

class Wipe
{
    Bitmap bitmap;
    int[][] data;
    
    public Wipe(final Bitmap bitmap) {
        this.bitmap = bitmap;
        this.data = new int[256][];
        for (int i = 0; i < 256; ++i) {
            this.data[i] = null;
        }
    }
    
    public void go(final Bitmap bitmap, final Bitmap bitmap2, final int n) {
        int i = 0;
        int n2 = 0;
        int n3;
        for (int[] rle = this.rle(n); i < rle.length; n2 = n3 + rle[i++]) {
            n3 = n2 + rle[i++];
            bitmap.paste(bitmap2, n3, rle[i]);
        }
    }
    
    public int get(final int n, final int n2) {
        return this.bitmap.get(n, n2);
    }
    
    int[] rle(final int n) {
        boolean b = false;
        if (this.data[n] != null) {
            return this.data[n];
        }
        int n2 = 0;
        int n3 = 0;
        int i;
        int[] array = new int[i = this.bitmap.pixels()];
        if (this.bitmap.ready() != 0) {
            b = true;
        }
        while (i > 0) {
            int n5;
            int n4 = n5 = 0;
            while (i > 0) {
                if (this.bitmap.get(n3) == n) {
                    break;
                }
                ++n5;
                ++n3;
                --i;
            }
            while (i > 0 && this.bitmap.get(n3) == n) {
                ++n4;
                ++n3;
                --i;
            }
            if (n2 == array.length) {
                final int[] array2 = new int[n2 + this.bitmap.pixels()];
                System.arraycopy(array, 0, array2, 0, n2);
                array = array2;
            }
            array[n2++] = n5;
            array[n2++] = n4;
        }
        if (n2 != array.length) {
            final int[] array3 = new int[n2];
            System.arraycopy(array, 0, array3, 0, n2);
            array = array3;
        }
        if (b) {
            this.data[n] = array;
        }
        return array;
    }
}
