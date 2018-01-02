// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class F extends J
{
    private static final long N = -8935819774751017389L;
    public static final int H = 0;
    public static final int M = 1;
    public static final int G = 2;
    private float K;
    private float L;
    private int J;
    private int I;
    
    public F() {
        this.K = 0.0f;
        this.L = 1.0f;
        this.J = 4;
        this.I = 0;
    }
    
    public void A(final int i) {
        this.I = i;
    }
    
    public int A() {
        return this.I;
    }
    
    public void A(final float k) {
        this.K = k;
    }
    
    public float D() {
        return this.K;
    }
    
    public void B(final float l) {
        this.L = l;
    }
    
    public float C() {
        return this.L;
    }
    
    public void B(final int j) {
        this.J = j;
    }
    
    public int B() {
        return this.J;
    }
    
    public void imageComplete(final int n) {
        if (n == 1 || n == 4) {
            this.consumer.imageComplete(n);
            return;
        }
        final int width = this.A.width;
        final int height = this.A.height;
        int n2 = 0;
        final int[] array = new int[width * height];
        final float n3 = (float)Math.sin(this.K);
        final float n4 = (float)Math.cos(this.K);
        final int n5 = width / 2;
        final int n6 = height / 2;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int n7 = 0;
                int n8 = 0;
                int n9 = 0;
                int n10 = 0;
                int n11 = 0;
                for (int k = 0; k < this.J; ++k) {
                    int n12 = j;
                    int n13 = i;
                    if (k != 0) {
                        switch (this.I) {
                            default: {
                                n12 = (int)(j + k * this.L * n3);
                                n13 = (int)(i + k * this.L * n4);
                                break;
                            }
                            case 1: {
                                final float n14 = j - n5;
                                final float n15 = i - n6;
                                final float n16 = (float)Math.sqrt(n14 * n14 + n15 * n15);
                                final float n17 = (float)((float)Math.atan2(n15, n14) + this.L * (k - this.J / 2) / 3.1415927f / 10.0);
                                n12 = (int)(n5 + n16 * Math.cos(n17));
                                n13 = (int)(n6 + n16 * Math.sin(n17));
                                break;
                            }
                            case 2: {
                                final float n18 = j - n5;
                                final float n19 = i - n6;
                                n12 = (int)(n5 + (1.0 - k * this.L / 100.0) * n18);
                                n13 = (int)(n6 + (1.0 - k * this.L / 100.0) * n19);
                                break;
                            }
                        }
                        if (n12 < 0) {
                            break;
                        }
                        if (n12 >= width) {
                            break;
                        }
                        if (n13 < 0) {
                            break;
                        }
                        if (n13 >= height) {
                            break;
                        }
                    }
                    ++n11;
                    final int n20 = this.C[n13 * width + n12];
                    n7 += (n20 >> 24 & 0xFF);
                    n8 += (n20 >> 16 & 0xFF);
                    n9 += (n20 >> 8 & 0xFF);
                    n10 += (n20 & 0xFF);
                }
                if (n11 == 0) {
                    array[n2] = this.C[n2];
                }
                else {
                    array[n2] = (jmaster.util.D.A.D.B(n7 / n11) << 24 | jmaster.util.D.A.D.B(n8 / n11) << 16 | jmaster.util.D.A.D.B(n9 / n11) << 8 | jmaster.util.D.A.D.B(n10 / n11));
                }
                ++n2;
            }
        }
        this.consumer.setPixels(0, 0, width, height, this.B, array, 0, width);
        this.consumer.imageComplete(n);
        this.C = null;
    }
    
    public String toString() {
        return "Blur/Motion Blur...";
    }
}
