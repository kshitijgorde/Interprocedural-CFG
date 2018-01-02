import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class k
{
    public final short[] a(final String s, final x x) {
        byte[] array = new byte[2048];
        byte[] array4;
        int length;
        try {
            InputStream resourceAsStream;
            if ((resourceAsStream = this.getClass().getResourceAsStream(s)) == null) {
                resourceAsStream = new FileInputStream(s);
            }
            ZipInputStream zipInputStream = null;
            boolean b = false;
            int n = 0;
            int i = 0;
            if (!(resourceAsStream instanceof FileInputStream)) {
                long size = -1L;
                if (s.endsWith(".zip")) {
                    size = (zipInputStream = new ZipInputStream(resourceAsStream)).getNextEntry().getSize();
                    b = true;
                }
                else if (W.e && x != null) {
                    size = x.e();
                }
                long n2 = -1L;
                while (i != -1) {
                    if ((i = (b ? zipInputStream.read(array, n, array.length - n) : resourceAsStream.read(array, n, array.length - n))) != -1) {
                        if (n >= array.length) {
                            final byte[] array2 = new byte[array.length + 32768];
                            for (int j = 0; j < array.length; ++j) {
                                array2[j] = array[j];
                            }
                            array = array2;
                        }
                        n += i;
                    }
                    if (size > 0L && n * 100 / size > n2) {
                        n2 = n * 100 / size;
                        if (x == null) {
                            continue;
                        }
                        x.a((int)n2);
                    }
                }
            }
            else {
                final File file = new File(s);
                int k;
                int n3;
                for (k = 0, array = new byte[n3 = (int)file.length()]; k < n3; k += resourceAsStream.read(array, k, n3 - k)) {}
                n = n3;
            }
            final byte[] array3 = new byte[n];
            for (int l = 0; l < n; ++l) {
                array3[l] = array[l];
            }
            length = (array4 = array3).length;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        final short[] array5 = new short[length];
        for (int n4 = 0; n4 < length; ++n4) {
            array5[n4] = (short)(array4[n4] & 0xFF);
        }
        return array5;
    }
}
