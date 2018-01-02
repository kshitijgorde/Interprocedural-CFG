import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class h
{
    public Color a;
    public byte[] a;
    public int[] a;
    public int[] b;
    public int a;
    public int b;
    public int c;
    public boolean a;
    public Color[] a;
    
    public h(final Image image, final int a, final int n) {
        this.a = new int[256];
        this.a = true;
        this.a = new Color[] { Color.black, Color.red, Color.green, Color.yellow, Color.blue, Color.magenta, Color.cyan, Color.white };
        this.a = a;
        this.b = a / 32;
        this.c = n / 8;
        this.b = new int[a * n];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, a, n, this.b, 0, a);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        int n2 = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 32; ++j) {
                n2 += this.a(j * this.b, i * this.c, 0, true);
            }
        }
        this.a = new byte[n2];
        int n3 = 0;
        for (int k = 0; k < 8; ++k) {
            for (int l = 0; l < 32; ++l) {
                this.a[k * 32 + l] = n3;
                n3 += this.a(l * this.b, k * this.c, n3, false);
            }
        }
    }
    
    private int a(final int n, final int n2, final int n3, final boolean b) {
        int n4 = 0;
        int n5 = 0;
        for (int i = n2; i < n2 + this.c; ++i) {
            int n6 = -1;
            int j;
            for (j = n; j < n + this.b; ++j) {
                if (this.b[i * this.a + j] != n6) {
                    if (n4 > 0) {
                        if (!b) {
                            this.a[n3 + n5] = (byte)(n4 | 0x80);
                        }
                        ++n5;
                        n4 = 0;
                    }
                    if (n6 == -1) {
                        if (!b) {
                            this.a[n3 + n5] = (byte)(j - n);
                        }
                        ++n5;
                    }
                    else {
                        if (!b) {
                            this.a[n3 + n5] = (byte)(j - n - 1);
                        }
                        ++n5;
                    }
                    n6 = this.b[i * this.a + j];
                }
            }
            if (n6 != -1) {
                if (!b) {
                    this.a[n3 + n5] = (byte)(j - n - 1);
                }
                ++n5;
            }
            ++n4;
        }
        if (!b) {
            this.a[n3 + n5] = -1;
        }
        return ++n5;
    }
    
    public final Point a(final Point point) {
        return new Point(point.x / this.b, point.y / this.c);
    }
    
    public final void a(final Graphics graphics, int n, int n2, final int n3, final int n4, byte b, byte b2, final boolean b3, final int n5, final boolean b4) {
        int n6 = this.a[0xFF & b];
        final int n7 = n3 * this.b;
        final int n8 = n4 * this.c;
        if (b4) {
            final int n9 = n;
            n = n2;
            n2 = n9;
        }
        if (this.a) {
            graphics.setColor(this.a[n2]);
        }
        else if (b4) {
            graphics.setColor(this.a[3]);
        }
        else {
            graphics.setColor(this.a);
        }
        graphics.fillRect(n7, n8, this.b, this.c);
        if (!b3) {
            return;
        }
        if (this.a) {
            graphics.setColor(this.a[n]);
        }
        else {
            if ((b2 & 0x1) > 0) {
                --b2;
            }
            if ((b2 & 0x2) > 0) {
                b2 -= 2;
                b = 32;
                n6 = this.a[32];
            }
            if (n5 >= 1) {
                if (b4) {
                    graphics.setColor(this.a);
                }
                else {
                    graphics.setColor(this.a[3]);
                }
            }
            else {
                graphics.setColor(this.a[7]);
            }
        }
        int n10 = 0;
        final boolean b5 = (0xFF & b) >= 128 && (0xFF & b) <= 192;
        if (!this.a && b5) {
            return;
        }
        if ((b2 & 0x3) > 0) {
            if ((b2 & 0x2) > 0) {
                while (this.a[n6] != -1 && n10 < this.c) {
                    if ((this.a[n6] & 0x80) == 0x80) {
                        n10 += 2 * (this.a[n6] & 0x7F);
                        ++n6;
                    }
                    else {
                        n6 += 2;
                    }
                }
                n10 -= this.c;
            }
            while (this.a[n6] != -1) {
                if (n10 >= this.c) {
                    return;
                }
                if ((this.a[n6] & 0x80) == 0x80) {
                    n10 += 2 * (this.a[n6] & 0x7F);
                    ++n6;
                }
                else {
                    graphics.drawLine(n7 + this.a[n6], n8 + n10, n7 + this.a[n6 + 1], n8 + n10);
                    graphics.drawLine(n7 + this.a[n6], n8 + n10 + 1, n7 + this.a[n6 + 1], n8 + n10 + 1);
                    n6 += 2;
                }
            }
        }
        else {
            if ((b2 & 0x4) > 0 && b5) {
                while (this.a[n6] != -1) {
                    if ((this.a[n6] & 0x80) == 0x80) {
                        n10 += (this.a[n6] & 0x7F);
                        ++n6;
                    }
                    else {
                        if (n10 != this.c - 1 && n10 != this.c / 3 - 1 && n10 != 2 * this.c / 3 - 1) {
                            int n11;
                            if ((n11 = this.a[n6]) == this.b / 2 - 1) {
                                n11 = this.b / 2;
                            }
                            int n12;
                            if ((n12 = this.a[n6 + 1]) == this.b / 2 - 1) {
                                n12 = this.b / 2 - 2;
                            }
                            else if (n12 == this.b - 1) {
                                n12 = this.b - 2;
                            }
                            if (n11 < this.b / 2 - 1) {
                                if (n12 >= this.b / 2) {
                                    graphics.drawLine(n7 + n11, n8 + n10, n7 + (this.b / 2 - 2), n8 + n10);
                                    n11 = this.b / 2;
                                }
                                else {
                                    graphics.drawLine(n7 + n11, n8 + n10, n7 + n12, n8 + n10);
                                }
                            }
                            if (n11 >= this.b / 2) {
                                graphics.drawLine(n7 + n11, n8 + n10, n7 + n12, n8 + n10);
                            }
                        }
                        n6 += 2;
                    }
                }
                return;
            }
            while (this.a[n6] != -1) {
                if ((this.a[n6] & 0x80) == 0x80) {
                    n10 += (this.a[n6] & 0x7F);
                    ++n6;
                }
                else {
                    graphics.drawLine(n7 + this.a[n6], n8 + n10, n7 + this.a[n6 + 1], n8 + n10);
                    n6 += 2;
                }
            }
        }
    }
}
