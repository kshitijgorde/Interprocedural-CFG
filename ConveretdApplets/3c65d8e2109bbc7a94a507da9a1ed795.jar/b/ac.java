// 
// Decompiled by Procyon v0.5.30
// 

package b;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class ac
{
    public static void a(final String[] array) {
        final float[] array2 = new float[90];
        final int[] array3 = new int[12];
        final short[] array4 = new short[82];
        final float[] array5 = new float[22];
        final af af = new af();
        final float[] array6 = new float[80];
        final af af2 = new af(new Integer(0));
        InputStream inputStream = null;
        OutputStream outputStream = null;
        final q q = new q();
        final l l = new l();
        final ae ae = new ae();
        if (array.length != 2) {
            throw new RuntimeException("I need 2 args");
        }
        try {
            inputStream = new FileInputStream(array[0]);
            outputStream = new FileOutputStream(array[1]);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < 10; ++i) {
            array2[i] = 0.0f;
        }
        final int n = 10;
        q.a();
        l.a();
        ae.a();
        int intValue = 60;
        int n2 = 0;
        final byte[] array7 = new byte[164];
        try {
            while (inputStream.read(array7, 0, 164) == 164) {
                final short[] if1 = j.if(array7);
                ++n2;
                ai.a(if1, 2, array3, 1);
                array3[0] = 0;
                for (int j = 2; j < 82; ++j) {
                    if (if1[j] == 0) {
                        array3[0] = 1;
                    }
                }
                array3[4] = ab.a(array3[3], array3[4]);
                q.a(array3, 0, intValue, array2, n, array5, af);
                intValue = 0;
                int n3 = 0;
                for (int k = 0; k < 80; k += 40) {
                    l.a(af.a, array2, n + k, array5, n3, array6, k, af2);
                    if (af2.a != 0) {
                        intValue = af2.a;
                    }
                    n3 += 11;
                }
                j.a(array2, 80, array2, 0, 10);
                ae.a(array6, 80);
                j.a(array6, 80, outputStream);
            }
        }
        catch (Exception ex2) {
            throw new RuntimeException(ex2);
        }
        finally {
            try {
                outputStream.close();
                inputStream.close();
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
    }
}
