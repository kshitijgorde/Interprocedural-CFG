// 
// Decompiled by Procyon v0.5.30
// 

package ji.secure;

public class cg
{
    int a;
    int[] b;
    int c;
    
    public cg() {
        this.a = -1609550627;
        this.b = new int[255];
        this.c = 20;
        try {
            this.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void b() {
        final int[] array = new int[8];
        int a = this.a;
        for (int i = 0; i < 8; ++i) {
            array[i] = a;
            if ((a & 0x1) != 0x0) {
                a = (a >> 1 ^ this.a);
            }
            else {
                a >>= 1;
            }
        }
        for (int j = 0; j < 255; ++j) {
            int n = 128;
            int n2 = 0;
            for (int k = 0; k < 8; ++k) {
                if ((j & n) != 0x0) {
                    n2 ^= array[k];
                }
                n >>= 1;
            }
            this.b[j] = n2;
        }
    }
    
    private final void a(final byte[] array, final int n, final int n2) {
        array[n] = (byte)(n2 >> 24 & 0xFF);
        array[n + 1] = (byte)(n2 >> 16 & 0xFF);
        array[n + 2] = (byte)(n2 >> 8 & 0xFF);
        array[n + 3] = (byte)(n2 & 0xFF);
    }
    
    private final int a(final byte[] array, final int n) {
        return ((array[n] & 0xFF) << 24) + ((array[n + 1] & 0xFF) << 16) + ((array[n + 2] & 0xFF) << 8) + (array[n + 3] & 0xFF);
    }
    
    public final byte[] a(final byte[] array, final int n, final boolean b) throws Exception {
        byte[] array2 = null;
        switch (n) {
            case 0: {
                array2 = this.d(array);
                break;
            }
        }
        if (b) {
            array2 = this.b(array2);
        }
        return array2;
    }
    
    private final byte[] b(final byte[] array) {
        String concat = "";
        for (int length = array.length, i = 0; i < length; ++i) {
            final String upperCase = "00".concat(String.valueOf(String.valueOf(Integer.toHexString(array[i] & 0xFF)))).toUpperCase();
            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(upperCase.substring(upperCase.length() - 2, upperCase.length()))));
        }
        return concat.getBytes();
    }
    
    private final byte[] c(final byte[] array) {
        final int n = array.length / 2;
        final byte[] array2 = new byte[n];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            int n3 = (array[n2] & 0xFF) - 48;
            int n4 = (array[n2 + 1] & 0xFF) - 48;
            if (n3 > 9) {
                n3 -= 7;
            }
            if (n4 > 9) {
                n4 -= 7;
            }
            array2[i] = (byte)(n4 + n3 * 16 & 0xFF);
            n2 += 2;
        }
        return array2;
    }
    
    private final byte[] d(final byte[] array) throws Exception {
        final int length = array.length;
        final byte[] array2 = new byte[length + this.c];
        int i = 10000;
        while (i > 0) {
            try {
                int n2;
                final int n = n2 = (int)(2.147483647E9 * Math.random());
                for (int j = 0; j < length; ++j) {
                    n2 = (n2 >> 8 ^ this.b[n2 & 0xFF]);
                    array2[j + this.c] = (byte)((byte)((byte)((byte)(array[j] ^ (n2 & 0xFF)) ^ (n2 >> 8 & 0xFF)) ^ (n2 >> 16 & 0xFF)) ^ (n2 >> 24 & 0xFF));
                }
                final int b = this.b(array2, this.c, length);
                this.a(array2, 0, 0);
                this.a(array2, 4, this.c);
                this.a(array2, 8, length);
                this.a(array2, 12, n);
                this.a(array2, 16, b);
                i = 0;
                continue;
            }
            catch (Exception ex) {
                if (--i < 0) {
                    throw ex;
                }
                continue;
            }
            break;
        }
        return array2;
    }
    
    public final byte[] a(byte[] c, final boolean b) throws Exception {
        if (b) {
            c = this.c(c);
        }
        final int a = this.a(c, 0);
        byte[] a2 = null;
        switch (a) {
            case 0: {
                a2 = this.a(c);
                break;
            }
        }
        return a2;
    }
    
    public final byte[] a(final byte[] array) throws Exception {
        this.a(array, 0);
        final int a = this.a(array, 4);
        final int a2 = this.a(array, 8);
        final int a3 = this.a(array, 12);
        final int a4 = this.a(array, 16);
        final int b = this.b(array, a, a2);
        final byte[] array2 = new byte[a2];
        if (b != a4) {
            throw new Exception("Incorrect CRC");
        }
        int n = a3;
        for (int i = 0; i < a2; ++i) {
            n = (n >> 8 ^ this.b[n & 0xFF]);
            array2[i] = (byte)((byte)((byte)((byte)(array[i + a] ^ (n & 0xFF)) ^ (n >> 8 & 0xFF)) ^ (n >> 16 & 0xFF)) ^ (n >> 24 & 0xFF));
        }
        return array2;
    }
    
    private final int b(final byte[] array, final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < n2; ++i) {
            n3 = (n3 >> 8 ^ this.b[(n3 ^ array[i + n]) & 0xFF]);
        }
        return n3;
    }
    
    public final void a() {
        this.b = null;
    }
}
