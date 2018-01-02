// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

class RLEUtilities
{
    static final boolean debug = false;
    
    static byte[] readRLE(final byte[] array) {
        byte[] array2 = new byte[array.length * 4];
        int n = 0;
        int n2;
        for (int i = 0; i < array.length; i += n2, n += n2) {
            final byte b = array[i++];
            n2 = (b & 0x7F);
            if (n + n2 > array2.length) {
                final byte[] array3 = new byte[Math.max(n + n2, array2.length * 2)];
                System.arraycopy(array2, 0, array3, 0, n);
                array2 = array3;
            }
            if ((b & 0x80) != 0x0) {
                final byte b2 = array[i++];
                while (--n2 >= 0) {
                    array2[n++] = b2;
                }
            }
            else {
                System.arraycopy(array, i, array2, n, n2);
            }
        }
        final byte[] array4 = new byte[n];
        System.arraycopy(array2, 0, array4, 0, n);
        return array4;
    }
    
    static byte[] writeRLE(final byte[] array) {
        final byte[] array2 = new byte[array.length + array.length / 126 + 1];
        int n = 0;
        int i = 0;
        while (i < array.length) {
            byte b = array[i];
            int j = i + 1;
            for (int min = Math.min(array.length, i + 126); j < min; ++j) {
                if (array[j] == b) {
                    if (++j >= min) {
                        break;
                    }
                    if (array[j] == b) {
                        j -= 2;
                        break;
                    }
                }
                b = array[j];
            }
            if (j > i) {
                final int n2 = j - i;
                array2[n++] = (byte)n2;
                try {
                    System.arraycopy(array, i, array2, n, n2);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("src len: " + array.length + " p: " + i + " res len: " + array2.length + " w: " + n + " n: " + n2);
                    throw ex;
                }
                n += n2;
            }
            if (j >= array.length) {
                break;
            }
            i = j;
            int n3;
            int min2;
            byte b2;
            for (n3 = i + 1, min2 = Math.min(array.length, i + 126), b2 = array[i]; n3 < min2 && array[n3] == b2; ++n3) {}
            if (n3 <= i + 2) {
                continue;
            }
            array2[n++] = (byte)(0x80 | n3 - i);
            array2[n++] = b2;
            i = n3;
        }
        final byte[] array3 = new byte[n];
        System.arraycopy(array2, 0, array3, 0, n);
        return array3;
    }
}
