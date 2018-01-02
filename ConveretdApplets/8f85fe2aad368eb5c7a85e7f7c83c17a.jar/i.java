import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class i implements Cloneable
{
    public static byte[] a;
    public int K;
    public int[] e;
    public int b;
    public int k;
    public boolean j;
    
    public final Object clone() throws CloneNotSupportedException {
        final i i;
        (i = (i)super.clone()).e = new int[this.b * this.k];
        System.arraycopy(this.e, 0, i.e, 0, this.e.length);
        return i;
    }
    
    public static final void a(final OutputStream outputStream, final int[] array, final int n, final int n2) throws IOException {
        final byte[] array2 = new byte[768];
        System.arraycopy(i.a, 0, array2, 0, i.a.length);
        array2[30] = (byte)(n >> 8 & 0xFF);
        array2[31] = (byte)(n & 0xFF);
        array2[42] = (array2[102] = (byte)(n2 >> 8 & 0xFF));
        array2[43] = (array2[103] = (byte)(n2 & 0xFF));
        final int n3 = n * n2 * 3;
        array2[114] = (byte)(n3 >> 24 & 0xFF);
        array2[115] = (byte)(n3 >> 16 & 0xFF);
        array2[116] = (byte)(n3 >> 8 & 0xFF);
        array2[117] = (byte)(n3 & 0xFF);
        outputStream.write(array2);
        for (int i = 0; i < array.length; ++i) {
            outputStream.write(array[i] >> 16 & 0xFF);
            outputStream.write(array[i] >> 8 & 0xFF);
            outputStream.write(array[i] & 0xFF);
        }
        outputStream.flush();
    }
    
    public static final void b(final OutputStream outputStream, final int[] array, final int n, final int n2) throws IOException {
        final byte[] array2;
        (array2 = new byte[18])[2] = 2;
        array2[12] = (byte)(n & 0xFF);
        array2[13] = (byte)(n >> 8);
        array2[14] = (byte)(n2 & 0xFF);
        array2[15] = (byte)(n2 >> 8);
        array2[16] = 32;
        array2[17] = 8;
        outputStream.write(array2);
        int n3 = (n2 - 1) * n;
        for (int i = n2 - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                final int n4 = array[n3 + j];
                outputStream.write(n4 & 0xFF);
                outputStream.write(n4 >> 8 & 0xFF);
                outputStream.write(n4 >> 16 & 0xFF);
                outputStream.write(n4 >>> 24 & 0xFF);
            }
            n3 -= n;
        }
        outputStream.flush();
    }
    
    public final void a(String string) {
        try {
            BufferedOutputStream bufferedOutputStream;
            if (string.toLowerCase().endsWith(".tga")) {
                b(bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 32768), this.e, this.b, this.k);
            }
            else {
                if (!string.toLowerCase().endsWith(".tif") && !string.toLowerCase().endsWith(".tiff")) {
                    string += ".tif";
                }
                a(bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 32768), this.e, this.b, this.k);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private final void q() {
        this.j = false;
    }
    
    public i() {
        this.q();
        this.K = 1;
    }
    
    public i(final int[] e, final int b, final int k, final int i) {
        this.q();
        this.e = e;
        this.b = b;
        this.k = k;
        this.K = i;
    }
    
    static {
        i.a = new byte[] { 77, 77, 0, 42, 0, 0, 0, 8, 0, 9, 0, -2, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 2, 0, 3, 0, 0, 0, 3, 0, 0, 0, 122, 1, 6, 0, 3, 0, 0, 0, 1, 0, 2, 0, 0, 1, 17, 0, 4, 0, 0, 0, 1, 0, 0, 3, 0, 1, 21, 0, 3, 0, 0, 0, 1, 0, 3, 0, 0, 1, 22, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 23, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0, 8 };
    }
}
