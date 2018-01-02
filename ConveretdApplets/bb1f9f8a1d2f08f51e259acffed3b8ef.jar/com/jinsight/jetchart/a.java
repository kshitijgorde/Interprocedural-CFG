// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

class a extends ImageFilter
{
    private static ColorModel a;
    private double b;
    private double c;
    private double d;
    private double[] e;
    private int[] f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    
    public void setAngle(final double b) {
        this.b = b;
        this.c = Math.sin(b);
        this.d = Math.cos(b);
    }
    
    public void transform(final double n, final double n2, final double[] array) {
        array[0] = this.d * n + this.c * n2;
        array[1] = this.d * n2 - this.c * n;
    }
    
    public void itransform(final double n, final double n2, final double[] array) {
        array[0] = this.d * n - this.c * n2;
        array[1] = this.d * n2 + this.c * n;
    }
    
    public void transformBBox(final Rectangle rectangle) {
        final boolean g = GraphSerie.G;
        double min = Double.POSITIVE_INFINITY;
        double min2 = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double max2 = Double.NEGATIVE_INFINITY;
        int n = 0;
    Label_0124_Outer:
        while (true) {
            Label_0141: {
                if (!g) {
                    break Label_0141;
                }
                int n2 = 0;
                while (true) {
                    while (true) {
                        Label_0127: {
                            if (!g) {
                                break Label_0127;
                            }
                            this.transform(rectangle.x + n2 * rectangle.width, rectangle.y + n * rectangle.height, this.e);
                            min = Math.min(min, this.e[0]);
                            min2 = Math.min(min2, this.e[1]);
                            max = Math.max(max, this.e[0]);
                            max2 = Math.max(max2, this.e[1]);
                            ++n2;
                        }
                        if (n2 <= 1) {
                            continue Label_0124_Outer;
                        }
                        break;
                    }
                    if (g) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n > 1) {
                rectangle.x = (int)Math.floor(min);
                rectangle.y = (int)Math.floor(min2);
                rectangle.width = (int)Math.ceil(max) - rectangle.x + 1;
                rectangle.height = (int)Math.ceil(max2) - rectangle.y + 1;
                return;
            }
            continue;
        }
    }
    
    public void setDimensions(final int i, final int j) {
        final Rectangle rectangle = new Rectangle(0, 0, i, j);
        this.transformBBox(rectangle);
        this.g = -rectangle.x;
        this.h = -rectangle.y;
        this.i = i;
        this.j = j;
        this.k = rectangle.width;
        this.l = rectangle.height;
        this.f = new int[this.i * this.j];
        super.consumer.setDimensions(this.k, this.l);
    }
    
    public void setColorModel(final ColorModel colorModel) {
        super.consumer.setColorModel(com.jinsight.jetchart.a.a);
    }
    
    public void setHints(final int n) {
        super.consumer.setHints(0xE | (n & 0x10));
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final boolean g = GraphSerie.G;
        int n7 = n5;
        int n8 = n2 * this.i + n;
        int n9 = 0;
    Label_0062_Outer:
        while (true) {
            Label_0099: {
                if (!g) {
                    break Label_0099;
                }
                int n10 = 0;
                while (true) {
                    while (true) {
                        Label_0065: {
                            if (!g) {
                                break Label_0065;
                            }
                            this.f[n8++] = colorModel.getRGB(array[n7++] & 0xFF);
                            ++n10;
                        }
                        if (n10 < n3) {
                            continue Label_0062_Outer;
                        }
                        break;
                    }
                    n7 += n6 - n3;
                    n8 += this.i - n3;
                    if (g) {
                        continue;
                    }
                    break;
                }
                ++n9;
            }
            if (n9 >= n4) {
                return;
            }
            continue;
        }
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final boolean g = GraphSerie.G;
        int n7 = n5;
        int n8 = n2 * this.i + n;
        if (colorModel == com.jinsight.jetchart.a.a) {
            int n9 = 0;
            while (true) {
                Label_0068: {
                    if (!g) {
                        break Label_0068;
                    }
                    System.arraycopy(array, n7, this.f, n8, n3);
                    n7 += n6;
                    n8 += this.i;
                    ++n9;
                }
                if (n9 < n4) {
                    continue;
                }
                break;
            }
        }
        else {
            int n10 = 0;
        Label_0117_Outer:
            while (true) {
                Label_0154: {
                    if (!g) {
                        break Label_0154;
                    }
                    int n11 = 0;
                    while (true) {
                        while (true) {
                            Label_0120: {
                                if (!g) {
                                    break Label_0120;
                                }
                                this.f[n8++] = colorModel.getRGB(array[n7++]);
                                ++n11;
                            }
                            if (n11 < n3) {
                                continue Label_0117_Outer;
                            }
                            break;
                        }
                        n7 += n6 - n3;
                        n8 += this.i - n3;
                        if (g) {
                            continue;
                        }
                        break;
                    }
                    ++n10;
                }
                if (n10 < n4) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void imageComplete(final int n) {
        final boolean g = GraphSerie.G;
        if (n == 1 || n == 4) {
            super.consumer.imageComplete(n);
            return;
        }
        final int[] array = new int[this.k];
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0280: {
                    if (!g) {
                        break Label_0280;
                    }
                    this.itransform(0 - this.g, n2 - this.h, this.e);
                    double n3 = this.e[0];
                    double n4 = this.e[1];
                    this.itransform(this.k - this.g, n2 - this.h, this.e);
                    final double n5 = this.e[0];
                    final double n6 = this.e[1];
                    final double n7 = (n5 - n3) / this.k;
                    final double n8 = (n6 - n4) / this.k;
                    int n9 = 0;
                    while (true) {
                        Label_0243: {
                            if (!g) {
                                break Label_0243;
                            }
                            final int n10 = (int)Math.round(n3);
                            final int n11 = (int)Math.round(n4);
                            Label_0226: {
                                if (n10 < 0 || n11 < 0 || n10 >= this.i || n11 >= this.j) {
                                    array[n9] = 0;
                                    if (!g) {
                                        break Label_0226;
                                    }
                                }
                                array[n9] = this.f[n11 * this.i + n10];
                            }
                            n3 += n7;
                            n4 += n8;
                            ++n9;
                        }
                        if (n9 < this.k) {
                            continue;
                        }
                        break;
                    }
                    super.consumer.setPixels(0, n2, this.k, 1, com.jinsight.jetchart.a.a, array, 0, this.k);
                    ++n2;
                }
                if (n2 < this.l) {
                    continue;
                }
                break;
            }
            super.consumer.imageComplete(n);
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    a(final double b) {
        this.e = new double[2];
        this.b = b;
        this.c = Math.sin(b);
        this.d = Math.cos(b);
    }
    
    static {
        com.jinsight.jetchart.a.a = ColorModel.getRGBdefault();
    }
}
