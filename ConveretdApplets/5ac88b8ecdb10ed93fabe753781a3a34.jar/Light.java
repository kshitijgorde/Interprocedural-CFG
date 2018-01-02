// 
// Decompiled by Procyon v0.5.30
// 

public final class Light extends Screen32
{
    int z1;
    int z2;
    public double z0;
    int z3;
    public double z4;
    int z5;
    
    public void z0(final int n, final int n2, final int n3, final int n4) {
        final int n5 = 190;
        final int n6 = 0;
        final int[] array = new int[256];
        for (int i = 0; i < 256; ++i) {
            final double cos = Math.cos((255 - i) / 512.0 * 3.141592653589793);
            final double n7 = n * n6 / 255 + n * cos + Math.pow(cos, n5) * n4;
            final double n8 = n2 * n6 / 255 + n2 * cos + Math.pow(cos, n5) * n4;
            final double n9 = n3 * n6 / 255 + n3 * cos + Math.pow(cos, n5) * n4;
            int n10 = (int)n7;
            int n11 = (int)n8;
            int n12 = (int)n9;
            if (n10 > 255) {
                n10 = 255;
            }
            if (n11 > 255) {
                n11 = 255;
            }
            if (n12 > 255) {
                n12 = 255;
            }
            array[i] = (n12 | n11 << 8 | n10 << 16);
        }
        for (int j = 0; j < super.z1; ++j) {
            for (int k = 0; k < super.z4; ++k) {
                final double n13 = (k - super.z3) / super.z3;
                final double n14 = (j - super.z2) / super.z2;
                double n15 = 1.0 - Math.sqrt(n13 * n13 + n14 * n14);
                if (n15 < 0.0) {
                    n15 = 0.0;
                }
                super.z0[k + super.z5[j]] = array[(int)(n15 * 255.0)];
            }
        }
    }
    
    public void z0(final Screen32 screen32, final Screen32 screen33) {
        final int z2 = screen32.z2();
        final int n = z2 * screen32.z1();
        int n2 = this.z3 - super.z3 + (this.z5 - super.z2) * screen32.z4;
        for (int i = 0; i < super.z1; ++i) {
            for (int j = 0; j < super.z4; ++j) {
                if (n2 >= 0 && n2 < n && this.z3 - super.z3 + j < screen32.z4 && this.z3 - super.z3 + j >= 0) {
                    final int n3 = super.z0[super.z5[i] + j];
                    final int n4 = n3 >> 16 & 0xFF;
                    final int n5 = n3 >> 8 & 0xFF;
                    final int n6 = n3 & 0xFF;
                    final int n7 = screen32.z0[n2];
                    final int n8 = n7 >> 16 & 0xFF;
                    final int n9 = n7 >> 8 & 0xFF;
                    final int n10 = n7 & 0xFF;
                    final int n11 = screen33.z0[n2];
                    final int n12 = n11 >> 16 & 0xFF;
                    final int n13 = n11 >> 8 & 0xFF;
                    final int n14 = n11 & 0xFF;
                    int n15 = n12 + (n8 * n4 >> 8);
                    int n16 = n13 + (n9 * n5 >> 8);
                    int n17 = n14 + (n10 * n6 >> 8);
                    if (n15 > 255) {
                        n15 = 255;
                    }
                    if (n16 > 255) {
                        n16 = 255;
                    }
                    if (n17 > 255) {
                        n17 = 255;
                    }
                    screen33.z0[n2] = (n15 << 16) + (n16 << 8) + n17;
                }
                ++n2;
            }
            n2 += z2 - super.z4;
        }
    }
    
    public void z0(final Screen32 screen32, final Screen32 screen33, final int n, final int n2, final int n3) {
        for (int i = 0; i < screen33.z4 * screen33.z1; ++i) {
            final int n4 = screen32.z0[i];
            int n5 = (n4 >> 16 & 0xFF) * n;
            int n6 = (n4 >> 8 & 0xFF) * n2;
            int n7 = (n4 & 0xFF) * n3;
            if (n5 > 65535) {
                n5 = 65535;
            }
            if (n6 > 65535) {
                n6 = 65535;
            }
            if (n7 > 65535) {
                n7 = 65535;
            }
            screen33.z0[i] = (n5 << 8 & 0xFF0000) + (n6 & 0xFF00) + (n7 >> 8 & 0xFF);
        }
    }
    
    public void z0(final Screen32 screen32, final Screen32 screen33, final BumpMap bumpMap) {
        final int z4 = screen32.z4;
        final int n = z4 * screen32.z1;
        int n2 = this.z3 - super.z3 + (this.z5 - super.z2) * screen32.z4;
        for (int i = 0; i < super.z1; ++i) {
            for (int j = 0; j < super.z4; ++j) {
                if (n2 >= 0 && n2 < n && this.z3 - super.z3 + j < screen32.z4 && this.z3 - super.z3 + j >= 0) {
                    final int n3 = screen32.z0[n2];
                    final int n4 = n3 >> 16 & 0xFF;
                    final int n5 = n3 >> 8 & 0xFF;
                    final int n6 = n3 & 0xFF;
                    final int n7 = screen33.z0[n2];
                    final int n8 = n7 >> 16 & 0xFF;
                    final int n9 = n7 >> 8 & 0xFF;
                    final int n10 = n7 & 0xFF;
                    int n11 = j - super.z3 - (bumpMap.z0[n2] - super.z3);
                    int n12 = i - super.z2 - (bumpMap.z1[n2] - super.z2);
                    if (n11 < 0 || n11 >= super.z4) {
                        n11 = 0;
                    }
                    if (n12 < 0 || n12 >= super.z4) {
                        n12 = 0;
                    }
                    final int n13 = super.z0[super.z5[n12] + n11] >> 16 & 0xFF;
                    final int n14 = super.z0[super.z5[n12] + n11] >> 8 & 0xFF;
                    final int n15 = super.z0[super.z5[n12] + n11] & 0xFF;
                    int n16 = n8 + (n4 * n13 >> 8);
                    int n17 = n9 + (n5 * n14 >> 8);
                    int n18 = n10 + (n6 * n15 >> 8);
                    if (n16 > 255) {
                        n16 = 255;
                    }
                    if (n17 > 255) {
                        n17 = 255;
                    }
                    if (n18 > 255) {
                        n18 = 255;
                    }
                    screen33.z0[n2] = (n16 << 16) + (n17 << 8) + n18;
                }
                ++n2;
            }
            n2 += z4 - super.z4;
        }
    }
    
    public Light(final int n, final int n2, final int n3, final int n4) {
        super(n, n2);
        this.z3 = n3;
        this.z5 = n4;
        this.z1 = n3;
        this.z2 = n4;
    }
    
    public void z1(final Screen32 screen32, final Screen32 screen33) {
        final int n = screen32.z2() * screen32.z1();
        int n2 = this.z1 - super.z3 + (this.z2 - super.z2) * screen32.z4;
        for (int i = 0; i < super.z1; ++i) {
            for (int j = 0; j < super.z4; ++j) {
                if (n2 >= 0 && n2 < n) {
                    screen33.z0[n2] = screen32.z0[n2];
                }
                ++n2;
            }
            n2 += screen32.z4 - super.z4;
        }
    }
    
    public void z0(final int z3, final int z4) {
        this.z1 = this.z3;
        this.z2 = this.z5;
        this.z3 = z3;
        this.z5 = z4;
    }
}
