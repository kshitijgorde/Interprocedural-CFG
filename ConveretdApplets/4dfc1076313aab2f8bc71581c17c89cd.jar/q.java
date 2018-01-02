import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class q
{
    public int p;
    public int d;
    public int a;
    public int n;
    public int[] p;
    private int v;
    private int i;
    
    public q(final Image image, final int a, final int n, final int v, final int i) {
        this.v = 26;
        this.i = -64;
        this.d = image.getHeight(null);
        this.p = image.getWidth(null);
        this.a = a;
        this.n = n;
        this.p = new int[this.p * this.d];
        this.v = v;
        this.i = i;
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.p, this.d, this.p, 0, this.p);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final double p(final int[] array, final double n, double n2, int n3) {
        final int n4 = -(this.a >> 1);
        int i = -(this.n >> 1);
        final int n5 = i + this.n;
        final int n6 = n4 + this.a >> 3;
        int n7 = this.p;
        final int d = this.d;
        final int[] p4 = this.p;
        if (n3 < -i) {
            n3 = -i;
        }
        final double n8 = Math.atan2(-i, n3) * 180 / 3.141592653589793;
        if (n2 - n8 <= -this.v) {
            n2 = n8 - this.v;
        }
        else if (n2 + n8 >= -this.i) {
            n2 = -this.i - n8;
        }
        final int n9 = (int)(Math.cos(n2 * 3.141592653589793 / 180) * 4096);
        final int n10 = (int)(Math.sin(n2 * 3.141592653589793 / 180) * 4096);
        final int n11 = (int)(n * 4096.0 / 90.0);
        final int n12 = (this.a >> 3) + 1;
        final int n13 = (this.n >> 3) + 1;
        final int[] array2 = new int[n13 * n12];
        final int[] array3 = new int[n13 * n12];
        int n14 = n6;
        while (i <= n5) {
            final int n15 = n3 * n9 - i * n10 >> 12;
            final int n16 = n3 * n10 + i * n9 >> 7;
            final int n17 = i * i + n3 * n3;
            final boolean b = n16 > 0;
            final int n18 = n15 * n15;
            final int n19 = n16 * n16;
            for (int j = 0; j <= n6; ++j) {
                final int n20 = j * j << 6;
                final int n21 = n18 + n20 >> 2;
                final int n22 = n17 + n20 >> 2;
                final int n23 = (n20 << 6 < n21) ? v.d[(n20 << 18) / n21] : v.p[(n20 << 10) / n21];
                final int n24 = (n19 < n22 << 4) ? v.d[(n19 << 8) / n22] : v.p[n19 / n22];
                array2[n14 + j] = (n11 + n23) * n7 >> 2;
                array2[n14 - j] = (n11 - n23) * n7 >> 2;
                int n25 = ((b ? n24 : (-n24 - 1)) + this.v * 4096 / 90) * d;
                if (n25 < 0) {
                    n25 = 0;
                }
                if (n25 >= d << 12) {
                    n25 = (d << 12) - 1;
                }
                array3[n14 - j] = (array3[n14 + j] = n25);
            }
            n14 += n12;
            i += 8;
        }
        for (int k = n13 - 1; k > 0; --k) {
            for (int l = n12 - 1; l > 0; --l) {
                int n26 = array2[(k - 1) * n12 + l - 1];
                int n27 = array2[(k - 1) * n12 + l];
                int n28 = array2[k * n12 + l - 1];
                int n29 = array2[k * n12 + l];
                final int n30 = array3[(k - 1) * n12 + l - 1];
                final int n31 = array3[(k - 1) * n12 + l];
                final int n32 = array3[k * n12 + l - 1];
                final int n33 = array3[k * n12 + l];
                boolean b2 = false;
                if ((n26 | n28 | n27 | n29) < 0) {
                    n26 += n7 << 12;
                    n28 += n7 << 12;
                    n27 += n7 << 12;
                    n29 += n7 << 12;
                    b2 = true;
                }
                n7 = this.p;
                if (b2) {
                    int n34 = 8;
                    int n35 = 1;
                    while (--n34 >= 0) {
                        int n36 = (k * 8 - n35) * this.a + (l * 8 - 1);
                        final int n37 = n30 * n35 + n32 * n34;
                        final int n38 = n31 * n35 + n33 * n34;
                        final int n39 = n26 * n35 + n28 * n34;
                        final int n40 = n27 * n35 + n29 * n34;
                        final int n41 = n37 - n38;
                        final int n42 = n39 - n40;
                        final int n43;
                        final int n44;
                        array[n36--] = p4[((n43 = (n38 << 3) + n41) >> 18) * n7 + ((n44 = (n40 << 3) + n42) >> 18) % n7];
                        final int n45;
                        final int n46;
                        array[n36--] = p4[((n45 = n43 + n41) >> 18) * n7 + ((n46 = n44 + n42) >> 18) % n7];
                        final int n47;
                        final int n48;
                        array[n36--] = p4[((n47 = n45 + n41) >> 18) * n7 + ((n48 = n46 + n42) >> 18) % n7];
                        final int n49;
                        final int n50;
                        array[n36--] = p4[((n49 = n47 + n41) >> 18) * n7 + ((n50 = n48 + n42) >> 18) % n7];
                        final int n51;
                        final int n52;
                        array[n36--] = p4[((n51 = n49 + n41) >> 18) * n7 + ((n52 = n50 + n42) >> 18) % n7];
                        final int n53;
                        final int n54;
                        array[n36--] = p4[((n53 = n51 + n41) >> 18) * n7 + ((n54 = n52 + n42) >> 18) % n7];
                        final int n55;
                        final int n56;
                        array[n36--] = p4[((n55 = n53 + n41) >> 18) * n7 + ((n56 = n54 + n42) >> 18) % n7];
                        array[n36] = p4[(n55 + n41 >> 18) * n7 + (n56 + n42 >> 18) % n7];
                        ++n35;
                    }
                }
                else {
                    int n57 = 8;
                    int n58 = 1;
                    while (--n57 >= 0) {
                        int n59 = (k * 8 - n58) * this.a + (l * 8 - 1);
                        final int n60 = n30 * n58 + n32 * n57;
                        final int n61 = n31 * n58 + n33 * n57;
                        final int n62 = n26 * n58 + n28 * n57;
                        final int n63 = n27 * n58 + n29 * n57;
                        final int n64 = n60 - n61;
                        final int n65 = n62 - n63;
                        final int n66;
                        final int n67;
                        array[n59--] = p4[((n66 = (n61 << 3) + n64) >> 18) * n7 + ((n67 = (n63 << 3) + n65) >> 18)];
                        final int n68;
                        final int n69;
                        array[n59--] = p4[((n68 = n66 + n64) >> 18) * n7 + ((n69 = n67 + n65) >> 18)];
                        final int n70;
                        final int n71;
                        array[n59--] = p4[((n70 = n68 + n64) >> 18) * n7 + ((n71 = n69 + n65) >> 18)];
                        final int n72;
                        final int n73;
                        array[n59--] = p4[((n72 = n70 + n64) >> 18) * n7 + ((n73 = n71 + n65) >> 18)];
                        final int n74;
                        final int n75;
                        array[n59--] = p4[((n74 = n72 + n64) >> 18) * n7 + ((n75 = n73 + n65) >> 18)];
                        final int n76;
                        final int n77;
                        array[n59--] = p4[((n76 = n74 + n64) >> 18) * n7 + ((n77 = n75 + n65) >> 18)];
                        final int n78;
                        final int n79;
                        array[n59--] = p4[((n78 = n76 + n64) >> 18) * n7 + ((n79 = n77 + n65) >> 18)];
                        array[n59] = p4[(n78 + n64 >> 18) * n7 + (n79 + n65 >> 18)];
                        ++n58;
                    }
                }
            }
        }
        return n2;
    }
}
