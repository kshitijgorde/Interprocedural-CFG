// 
// Decompiled by Procyon v0.5.30
// 

package I;

import java.io.InputStream;

public class I
{
    static byte[] getClass;
    static String[] getResourceAsStream;
    static int[] intern;
    
    public static final synchronized String I(int n) {
        final int n2 = n & 0xFF;
        if (I.intern[n2] != n) {
            if ((I.intern[n2] = n) < 0) {
                n &= 0xFFFF;
            }
            I.getResourceAsStream[n2] = new String(I.getClass, n, I.getClass[n - 1] & 0xFF).intern();
        }
        return I.getResourceAsStream[n2];
    }
    
    static {
        I.getResourceAsStream = new String[256];
        I.intern = new int[256];
        try {
            final InputStream resourceAsStream = new I().getClass().getResourceAsStream(new StringBuffer().append('I').append('.').append('g').append('i').append('f').toString());
            if (resourceAsStream != null) {
                int i = resourceAsStream.read() << 16 | resourceAsStream.read() << 8 | resourceAsStream.read();
                I.getClass = new byte[i];
                int j = 0;
                final byte b = (byte)i;
                final byte[] getClass = I.getClass;
                while (i != 0) {
                    final int read = resourceAsStream.read(getClass, j, i);
                    if (read == -1) {
                        break;
                    }
                    i -= read;
                    while (j < read + j) {
                        final byte[] array = getClass;
                        final int n = j;
                        array[n] ^= b;
                        ++j;
                    }
                }
                resourceAsStream.close();
            }
        }
        catch (Exception ex) {}
    }
}
