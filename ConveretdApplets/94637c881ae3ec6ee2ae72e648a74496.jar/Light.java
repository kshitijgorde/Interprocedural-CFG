// 
// Decompiled by Procyon v0.5.30
// 

public final class Light extends Screen32
{
    int xpos;
    int ypos;
    int oldx;
    int oldy;
    private double dblXDegree;
    private double dblYDegree;
    private double dblXAdd;
    private double dblYAdd;
    private int[] phongTab;
    
    public Light(final int n, final double n2) {
        super(n, n);
        this.dblXDegree = 0.0;
        this.dblYDegree = 0.0;
        this.dblXAdd = 0.0;
        this.dblYAdd = 0.0;
        this.dblXAdd = Math.random() - 0.5;
        if (this.dblXAdd < 0.09 || this.dblXAdd > -0.09) {
            this.dblXAdd += 0.09;
        }
        this.dblXAdd /= n2;
        this.dblYAdd = Math.random() - 0.5;
        if (this.dblYAdd < 0.09 || this.dblYAdd > -0.09) {
            this.dblYAdd += 0.09;
        }
        this.dblYAdd /= n2;
        final boolean b = false;
        this.oldy = (b ? 1 : 0);
        this.ypos = (b ? 1 : 0);
        this.oldx = (b ? 1 : 0);
        this.xpos = (b ? 1 : 0);
        this.phongTab = new int[256];
    }
    
    public final void addFrame(final int n, final int n2, final double n3) {
        this.oldx = this.xpos;
        this.oldy = this.ypos;
        this.dblXDegree += this.dblXAdd;
        this.dblYDegree += this.dblYAdd;
        final int n4 = (int)(n * n3);
        final int n5 = (int)(n2 * n3);
        this.xpos = (int)(Math.sin(this.dblXDegree) * (n4 >> 1)) + (n >> 1);
        this.ypos = (int)(Math.sin(this.dblYDegree) * (n5 >> 1)) + (n2 >> 1);
    }
    
    public final void createPhongBall(final int n, final int n2, final int n3) {
        final int n4 = 512;
        final int n5 = 0;
        final int[] array = new int[256];
        for (int i = 0; i < 256; ++i) {
            final double cos = Math.cos((255 - i) / 512.0 * 3.141592653589793);
            final double n6 = n * n5 / 255 + n * cos + Math.pow(cos, n4) * 200.0;
            final double n7 = n2 * n5 / 255 + n2 * cos + Math.pow(cos, n4) * 200.0;
            final double n8 = n3 * n5 / 255 + n3 * cos + Math.pow(cos, n4) * 200.0;
            int n9 = (int)n6;
            int n10 = (int)n7;
            int n11 = (int)n8;
            if (n9 > 255) {
                n9 = 255;
            }
            if (n10 > 255) {
                n10 = 255;
            }
            if (n11 > 255) {
                n11 = 255;
            }
            array[i] = (n11 | n10 << 8 | n9 << 16);
        }
        for (int j = 0; j < this.height; ++j) {
            for (int k = 0; k < this.width; ++k) {
                final double n12 = (k - this.hwidth) / this.hwidth;
                final double n13 = (j - this.hheight) / this.hheight;
                double n14 = 1.0 - Math.sqrt(n12 * n12 + n13 * n13);
                if (n14 < 0.0) {
                    n14 = 0.0;
                }
                this.data[k + this.ytab[j]] = array[(int)(n14 * 255.0)];
            }
        }
    }
    
    public final void illuminateAmbient(final Screen32 screen32, final Screen32 screen33, final int n, final int n2, final int n3) {
        for (int i = 0; i < screen33.getwidthheight(); ++i) {
            final int n4 = screen32.data[i];
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
            screen33.data[i] = (n5 << 8 & 0xFF0000) + (n6 & 0xFF00) + (n7 >> 8 & 0xFF);
        }
    }
    
    public final void restoreOld(final Screen32 screen32, final Screen32 screen33) {
        final int n = screen32.getwidth() * screen32.getheight();
        final int n2 = screen32.getwidth() - this.width;
        int n3 = this.oldx - this.hwidth + (this.oldy - this.hheight) * screen32.width;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (n3 >= 0 && n3 < n) {
                    screen33.data[n3] = screen32.data[n3];
                }
                ++n3;
            }
            n3 += n2;
        }
    }
    
    public final void illuminate(final Screen32 screen32, final Screen32 screen33) {
        final int n = screen32.width * screen32.height;
        int n2 = this.xpos - this.hwidth + (this.ypos - this.hheight) * screen32.width;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (n2 >= 0 && n2 < n && this.xpos - this.hwidth + j < screen32.width && this.xpos - this.hwidth + j >= 0) {
                    final int n3 = this.data[this.ytab[i] + j];
                    final int n4 = n3 >> 16 & 0xFF;
                    final int n5 = n3 >> 8 & 0xFF;
                    final int n6 = n3 & 0xFF;
                    final int n7 = screen33.data[n2];
                    final int n8 = n7 >> 16 & 0xFF;
                    final int n9 = n7 >> 8 & 0xFF;
                    final int n10 = n7 & 0xFF;
                    int n11 = n8 + n4;
                    int n12 = n9 + n5;
                    int n13 = n10 + n6;
                    if (n11 > 255) {
                        n11 = 255;
                    }
                    if (n12 > 255) {
                        n12 = 255;
                    }
                    if (n13 > 255) {
                        n13 = 255;
                    }
                    screen33.data[n2] = (n11 << 16) + (n12 << 8) + n13;
                }
                ++n2;
            }
            n2 += screen32.width - this.width;
        }
    }
    
    public final void illuminateBump(final Screen32 screen32, final Screen32 screen33, final BumpMap bumpMap) {
        final int n = screen32.width * screen32.height;
        int n2 = this.xpos - this.hwidth + (this.ypos - this.hheight) * screen32.width;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (n2 >= 0 && n2 < n && this.xpos - this.hwidth + j < screen32.width && this.xpos - this.hwidth + j >= 0) {
                    final int n3 = screen33.data[n2];
                    final int n4 = n3 >> 16 & 0xFF;
                    final int n5 = n3 >> 8 & 0xFF;
                    final int n6 = n3 & 0xFF;
                    int n7 = j - this.hwidth - (bumpMap.mDeltaX[n2] - this.hwidth);
                    int n8 = i - this.hheight - (bumpMap.mDeltaY[n2] - this.hheight);
                    if (n7 < 0 || n7 >= this.width) {
                        n7 = 0;
                    }
                    if (n8 < 0 || n8 >= this.width) {
                        n8 = 0;
                    }
                    final int n9 = this.ytab[n8] + n7;
                    int n10 = n4 + (this.data[n9] >> 16 & 0xFF);
                    int n11 = n5 + (this.data[n9] >> 8 & 0xFF);
                    int n12 = n6 + (this.data[n9] & 0xFF);
                    if (n10 > 255) {
                        n10 = 255;
                    }
                    if (n11 > 255) {
                        n11 = 255;
                    }
                    if (n12 > 255) {
                        n12 = 255;
                    }
                    screen33.data[n2] = (n10 << 16) + (n11 << 8) + n12;
                }
                ++n2;
            }
            n2 += screen32.width - this.width;
        }
    }
}
