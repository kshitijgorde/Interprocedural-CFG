// 
// Decompiled by Procyon v0.5.30
// 

package ji.lzw;

import java.io.ByteArrayOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.ImageIO;
import ji.io.a8;
import ji.io.ac;
import java.awt.Component;

public class nt
{
    int a;
    
    public nt() {
        this.a = 1;
    }
    
    public int a(final byte[] array, int n, final int n2, final int n3, final int[] array2, final int n4, final String s, final Component component, final String s2) throws Exception {
        final ac ac = new ac(s, true, false, 0, false, component, true, s2);
        final a8 a8 = new a8(ac, component);
        final ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(a8);
        final nu nu = new nu(imageOutputStream, 8, true);
        final int length = array2.length;
        int n5 = 0;
        for (int i = 0; i < length; ++i) {
            n5 += array2[i];
        }
        final int n6 = (n5 * n2 + 7) / 8;
        final long streamPosition = imageOutputStream.getStreamPosition();
        boolean b = false;
        if (this.a == 2) {
            b = true;
        }
        if (n6 == n4 && !b) {
            nu.a(array, n, n6 * n3);
        }
        else {
            final byte[] array3 = (byte[])(b ? new byte[n6] : null);
            for (int j = 0; j < n3; ++j) {
                if (b) {
                    System.arraycopy(array, n, array3, 0, n6);
                    for (int k = n6 - 1; k >= length; --k) {
                        final byte[] array4 = array3;
                        final int n7 = k;
                        array4[n7] -= array3[k - length];
                    }
                    nu.a(array3, 0, n6);
                }
                else {
                    nu.a(array, n, n6);
                }
                n += n4;
            }
        }
        nu.a();
        final int n8 = (int)(imageOutputStream.getStreamPosition() - streamPosition);
        imageOutputStream.flush();
        a8.flush();
        a8.close();
        ac.a(component);
        return n8;
    }
    
    public byte[] a(final byte[] array, int n, final int n2, final int n3, final int[] array2, final int n4, final Component component, final String s) throws Exception {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(array.length);
        final ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
        final nu nu = new nu(imageOutputStream, 8, true);
        final int length = array2.length;
        int n5 = 0;
        for (int i = 0; i < length; ++i) {
            n5 += array2[i];
        }
        final int n6 = (n5 * n2 + 7) / 8;
        final long streamPosition = imageOutputStream.getStreamPosition();
        boolean b = false;
        if (this.a == 2) {
            b = true;
        }
        if (n6 == n4 && !b) {
            nu.a(array, n, n6 * n3);
        }
        else {
            final byte[] array3 = (byte[])(b ? new byte[n6] : null);
            for (int j = 0; j < n3; ++j) {
                if (b) {
                    System.arraycopy(array, n, array3, 0, n6);
                    for (int k = n6 - 1; k >= length; --k) {
                        final byte[] array4 = array3;
                        final int n7 = k;
                        array4[n7] -= array3[k - length];
                    }
                    nu.a(array3, 0, n6);
                }
                else {
                    nu.a(array, n, n6);
                }
                n += n4;
            }
        }
        nu.a();
        final int n8 = (int)(imageOutputStream.getStreamPosition() - streamPosition);
        imageOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
