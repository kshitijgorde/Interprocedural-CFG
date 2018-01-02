// 
// Decompiled by Procyon v0.5.30
// 

package a3dAPI;

import java.awt.image.PixelGrabber;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Image;

public final class c
{
    public static final int[] a(final Object o) {
        return (int[])((Object[])o)[1];
    }
    
    public static final Object a(final float[] array, final int[] array2, final float n) {
        final boolean b = n > 0.0f;
        float[] array3 = null;
        float[] array4 = null;
        final int length;
        final int[] array5 = new int[length = array2.length];
        int n2 = 0;
        final int n3 = array.length / 3;
        if (b) {
            array3 = new float[array.length];
            array4 = new float[n3];
        }
        float[] array6 = new float[array2.length / 3 * 3];
        final float n4 = -1.0f;
        for (int i = 0; i < length; i += 3, ++n2) {
            final int n6;
            final int n5 = ((n6 = array2[i]) << 1) + n6;
            final float n7 = array[n5];
            final float n8 = array[n5 + 1];
            final float n9 = array[n5 + 2];
            final int n11;
            final int n10 = ((n11 = array2[i + 1]) << 1) + n11;
            final float n12 = array[n10];
            final float n13 = array[n10 + 1];
            final float n14 = array[n10 + 2];
            final int n16;
            final int n15 = ((n16 = array2[i + 2]) << 1) + n16;
            final float n17 = array[n15];
            final float n18 = array[n15 + 1];
            final float n19 = array[n15 + 2];
            final float n20 = n12 - n7;
            final float n21 = n13 - n8;
            final float n22 = n14 - n9;
            final float n23 = n17 - n7;
            final float n24 = n18 - n8;
            final float n25 = n19 - n9;
            float n26 = n21 * n25 - n22 * n24;
            float n27 = n22 * n23 - n20 * n25;
            float n28 = n20 * n24 - n21 * n23;
            final float n29;
            if ((n29 = (float)Math.sqrt(n26 * n26 + n27 * n27 + n28 * n28)) != 0.0f) {
                final float n30 = n4 / n29;
                n26 *= n30;
                n27 *= n30;
                n28 *= n30;
            }
            array6[n2 * 3] = n26;
            array6[n2 * 3 + 1] = n27;
            array6[n2 * 3 + 2] = n28;
            array5[i] = n2;
            array5[i + 2] = (array5[i + 1] = n2);
            if (b) {
                final float[] array7 = array3;
                final int n31 = n5;
                array7[n31] += n26;
                final float[] array8 = array3;
                final int n32 = n5 + 1;
                array8[n32] += n27;
                final float[] array9 = array3;
                final int n33 = n5 + 2;
                array9[n33] += n28;
                final float[] array10 = array4;
                final int n34 = n6;
                ++array10[n34];
                final float[] array11 = array3;
                final int n35 = n10;
                array11[n35] += n26;
                final float[] array12 = array3;
                final int n36 = n10 + 1;
                array12[n36] += n27;
                final float[] array13 = array3;
                final int n37 = n10 + 2;
                array13[n37] += n28;
                final float[] array14 = array4;
                final int n38 = n11;
                ++array14[n38];
                final float[] array15 = array3;
                final int n39 = n15;
                array15[n39] += n26;
                final float[] array16 = array3;
                final int n40 = n15 + 1;
                array16[n40] += n27;
                final float[] array17 = array3;
                final int n41 = n15 + 2;
                array17[n41] += n28;
                final float[] array18 = array4;
                final int n42 = n16;
                ++array18[n42];
            }
        }
        if (b) {
            for (int j = 0; j < n3; ++j) {
                float n43 = array3[j * 3];
                float n44 = array3[j * 3 + 1];
                float n45 = array3[j * 3 + 2];
                final float n46;
                if ((n46 = array4[j]) != 0.0f) {
                    final float n47 = 1.0f / n46;
                    n43 *= n47;
                    n44 *= n47;
                    n45 *= n47;
                }
                final float n48;
                if ((n48 = (float)Math.sqrt(n43 * n43 + n44 * n44 + n45 * n45)) != 0.0f) {
                    final float n49 = 1.0f / n48;
                    n43 *= n49;
                    n44 *= n49;
                    n45 *= n49;
                }
                array3[j * 3] = n43;
                array3[j * 3 + 1] = n44;
                array3[j * 3 + 2] = n45;
            }
            System.arraycopy(array2, 0, array5, 0, array2.length);
            array6 = array3;
        }
        final Object[] array19;
        (array19 = new Object[2])[0] = array6;
        array19[1] = array5;
        return array19;
    }
    
    public static final Image a(final Image image, final Component component) {
        try {
            final MediaTracker mediaTracker;
            (mediaTracker = new MediaTracker(component)).addImage(image, 0);
            mediaTracker.waitForID(0);
            if (image.getWidth(null) < 0 || image.getHeight(null) < 0) {
                throw new Exception();
            }
            return image;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final int[] a(final URL url, final int[] array, final Component component) {
        final Image a;
        if ((a = a(component.getToolkit().getImage(url), component)) != null) {
            return a(a, array);
        }
        return null;
    }
    
    private static void a(final float[] array) {
        final int n = 0;
        final int n2 = 4;
        final int n3 = 8;
        final float n4 = 1.0f;
        array[n3] = n4;
        array[n] = (array[n2] = n4);
        final int n5 = 1;
        final int n6 = 2;
        final int n7 = 3;
        final int n8 = 5;
        final int n9 = 6;
        final int n10 = 7;
        final int n11 = 9;
        final int n12 = 10;
        final int n13 = 11;
        final float n14 = 0.0f;
        array[n13] = n14;
        array[n11] = (array[n12] = n14);
        array[n9] = (array[n10] = n14);
        array[n7] = (array[n8] = n14);
        array[n5] = (array[n6] = n14);
    }
    
    public static final void a(final float n, final float[] array) {
        a(array);
        array[8] = (array[4] = (float)Math.cos(n));
        array[5] = (float)Math.sin(n);
        array[7] = -array[5];
    }
    
    public static final void b(final float n, final float[] array) {
        a(array);
        array[4] = (array[0] = (float)Math.cos(n));
        array[1] = (float)Math.sin(n);
        array[3] = -array[1];
    }
    
    public static final int[] a(final int[] array, final boolean b) {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == -1) {
                n += n2 - 2;
                n2 = 0;
            }
            else {
                ++n2;
            }
        }
        final int[] array2 = new int[n * 3];
        int j = 0;
        int n3 = 0;
        while (j < array.length - 1) {
            for (int n4 = array[j++]; array[j + 1] != -1; array2[n3++] = array[j++], array2[n3++] = array[j]) {
                array2[n3++] = n4;
            }
            j += 2;
        }
        if (!b) {
            for (int k = 0; k < array2.length; k += 3) {
                final int n5 = array2[k + 2];
                array2[k + 2] = array2[k];
                array2[k] = n5;
            }
        }
        return array2;
    }
    
    public static final float[] b(final Object o) {
        return (float[])((Object[])o)[0];
    }
    
    public static final void a(final float n, final float n2, final float n3, final float[] array) {
        a(array);
        array[0] = n;
        array[4] = n2;
        array[8] = n3;
    }
    
    public static final void c(final float n, final float[] array) {
        a(array);
        array[8] = (array[0] = (float)Math.cos(n));
        array[6] = (float)Math.sin(n);
        array[2] = -array[6];
    }
    
    private static int[] a(final Image image, final int[] array) {
        final int n = 0;
        final int width = image.getWidth(null);
        array[n] = width;
        final int n2 = width;
        final int n3 = 1;
        final int height = image.getHeight(null);
        array[n3] = height;
        final int n4 = height;
        final int[] array2 = new int[n2 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n2, n4, array2, 0, n2);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        pixelGrabber.status();
        return array2;
    }
    
    public static final void a(final float[] array, final float[] array2, final float[] array3) {
        final float n = array[0] * array2[0] + array[1] * array2[3] + array[2] * array2[6];
        final float n2 = array[0] * array2[1] + array[1] * array2[4] + array[2] * array2[7];
        final float n3 = array[0] * array2[2] + array[1] * array2[5] + array[2] * array2[8];
        final float n4 = array[3] * array2[0] + array[4] * array2[3] + array[5] * array2[6];
        final float n5 = array[3] * array2[1] + array[4] * array2[4] + array[5] * array2[7];
        final float n6 = array[3] * array2[2] + array[4] * array2[5] + array[5] * array2[8];
        final float n7 = array[6] * array2[0] + array[7] * array2[3] + array[8] * array2[6];
        final float n8 = array[6] * array2[1] + array[7] * array2[4] + array[8] * array2[7];
        final float n9 = array[6] * array2[2] + array[7] * array2[5] + array[8] * array2[8];
        final float n10 = array[9] * array2[0] + array[10] * array2[3] + array[11] * array2[6] + array2[9];
        final float n11 = array[9] * array2[1] + array[10] * array2[4] + array[11] * array2[7] + array2[10];
        final float n12 = array[9] * array2[2] + array[10] * array2[5] + array[11] * array2[8] + array2[11];
        array3[0] = n;
        array3[1] = n2;
        array3[2] = n3;
        array3[3] = n4;
        array3[4] = n5;
        array3[5] = n6;
        array3[6] = n7;
        array3[7] = n8;
        array3[8] = n9;
        array3[9] = n10;
        array3[10] = n11;
        array3[11] = n12;
    }
    
    public static final void b(final float n, final float n2, final float n3, final float[] array) {
        a(array);
        array[9] = n;
        array[10] = n2;
        array[11] = n3;
    }
}
