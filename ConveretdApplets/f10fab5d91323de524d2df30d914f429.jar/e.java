import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class e extends Canvas
{
    CHAT a;
    Color a;
    Font a;
    String a;
    Graphics b;
    Graphics a;
    Image[] a;
    int c;
    int b;
    boolean b;
    boolean c;
    boolean a;
    int d;
    int a;
    
    void a(final int n, final int n2) {
        this.a[0] = this.a.createImage(n, n2);
        this.b = this.a[0].getGraphics();
    }
    
    public e(final int d, final CHAT a) {
        this.a = Color.black;
        this.a = new Font("Dialog", 1, 12);
        this.a = new Image[3];
        this.b = false;
        this.a = 145;
        this.d = d;
        this.a = a;
        switch (d) {
            case 2: {
                this.a(this.a, 30);
                this.setSize(this.a, 30);
                break;
            }
            case 3: {
                this.a(50, 5);
                this.setSize(50, 5);
                break;
            }
            case 11: {
                this.a = a.c[36];
                this.c = a.getBounds().width;
                this.b = a.getBounds().height;
                this.a(this.c, this.b);
                this.setSize(this.c, this.b);
                break;
            }
            case 4:
            case 9: {
                this.a(45, 23);
                this.setSize(45, 23);
                break;
            }
            case 5: {
                this.a(23, 23);
                this.setSize(23, 23);
                break;
            }
            case 6: {
                this.a(23, 23);
                this.setSize(23, 23);
                break;
            }
            case 7: {
                this.a = a.c[29];
                this.a(this.c = a.getGraphics().getFontMetrics().stringWidth(this.a) + 20, 23);
                this.setSize(this.c, 23);
                break;
            }
            case 8: {
                this.a(288, 56);
                this.setSize(288, 56);
                break;
            }
        }
        this.a();
    }
    
    public void paint(final Graphics graphics) {
        switch (this.d) {
            case 1: {
                this.a.a(6, 0, "", graphics);
                break;
            }
            case 3:
            case 8: {
                graphics.drawImage(this.a[0], 0, 0, null);
                break;
            }
            case 10: {
                this.c = 1 + this.getSize().width / this.a.o;
                for (int i = 0; i < this.c; ++i) {
                    graphics.drawImage(this.a.a, this.a.o * i, 0, null);
                }
                graphics.setColor(new Color(0xFF000000 | this.a.b[37]));
                graphics.drawString(this.a.c[35], 10, 15);
                break;
            }
            default: {
                if (this.a) {
                    this.b = true;
                    graphics.drawImage(this.a[2], 0, 0, null);
                }
                else {
                    graphics.drawImage(this.a[1], 0, 0, null);
                }
                this.a(graphics);
                break;
            }
        }
    }
    
    public void a(final Graphics graphics) {
        switch (this.d) {
            case 4: {
                final int[] array = { 5, 26, 23, 2, 5, 23 };
                final int[] array2 = { 2, 8, 19, 14, 2, 19 };
                graphics.setColor(new Color(16777215));
                graphics.fillPolygon(array, array2, 5);
                graphics.setPaintMode();
                graphics.setColor(new Color(this.a.b[37]));
                graphics.drawPolyline(array, array2, 6);
                graphics.drawLine(2, 14, 26, 8);
                graphics.drawLine(32, 18, 40, 18);
                graphics.drawLine(32, 12, 40, 12);
                graphics.drawLine(32, 6, 40, 6);
                break;
            }
            case 2: {
                graphics.setColor(new Color(13421772));
                graphics.setFont(new Font("Dialog", 1, 20));
                graphics.drawString(this.a.c[30], 15, 18);
                graphics.setColor(new Color(16777215));
                graphics.setFont(new Font("Dialog", 1, 16));
                graphics.drawString(this.a.c[31], 45, 25);
                break;
            }
            case 11: {
                graphics.setFont(this.a.a[0]);
                graphics.setColor(new Color(this.a.b[37]));
                graphics.drawString(this.a, (this.c - graphics.getFontMetrics().stringWidth(this.a)) / 2, this.b / 2 + 4);
                break;
            }
            case 6: {
                graphics.setColor(this.a.a[37]);
                graphics.setFont(new Font("SansSerif", 0, 15));
                graphics.drawString(this.a.h, 7, 16);
                break;
            }
            case 7: {
                graphics.setFont(new Font("SansSerif", 1, 15));
                graphics.setColor(this.a);
                graphics.drawString(this.a, 10, 15);
                break;
            }
            case 9: {
                graphics.setColor(this.a.a[37]);
                graphics.drawString(this.a.c[16], 13, 16);
                break;
            }
        }
    }
    
    public void a() {
        final boolean a = d.a;
        int n;
        final boolean b = (n = (this.c ? 1 : 0)) != 0;
    Label_0268_Outer:
        while (true) {
            e e = null;
            Label_0085: {
                if (!a) {
                    if (b) {
                        return;
                    }
                    this.c = true;
                    e = this;
                    if (a) {
                        break Label_0085;
                    }
                    n = this.d;
                }
                Label_0356: {
                    switch (n) {
                        case 4: {
                            e = this;
                            break;
                        }
                        case 2: {
                            this.a(this.a[0], 2, this.a, 30);
                            if (a) {
                                break Label_0356;
                            }
                            return;
                        }
                        case 5:
                        case 6: {
                            this.a(this.a[0], 2, 23, 23);
                            if (a) {
                                break Label_0356;
                            }
                            return;
                        }
                        case 3: {
                            this.c += 20;
                            this.b.setColor(new Color(2, 2, 2));
                            this.b.fillRect(0, 0, 1, 5);
                            this.a(this.a[0], 2, 1, 5);
                            this.a[0] = this.a.createImage(50, 5);
                            this.a.a = this.a.createImage(50, 5);
                            this.b = this.a[0].getGraphics();
                            int n2 = 0;
                            while (true) {
                                while (true) {
                                    Label_0271: {
                                        if (!a) {
                                            break Label_0271;
                                        }
                                        this.b.drawImage(this.a[1], n2, 0, null);
                                        ++n2;
                                    }
                                    if (n2 < 50) {
                                        continue Label_0268_Outer;
                                    }
                                    break;
                                }
                                (this.a = this.a.a.getGraphics()).drawImage(this.a[0], 0, 0, null);
                                this.a.o = 50;
                                if (a) {
                                    continue;
                                }
                                break;
                            }
                            if (a) {
                                break Label_0356;
                            }
                            return;
                        }
                        case 11: {
                            this.a(this.a[0], 2, this.c, this.b);
                            if (a) {
                                break Label_0356;
                            }
                            return;
                        }
                        case 7: {
                            if (a) {
                                break Label_0356;
                            }
                            return;
                        }
                        case 8: {
                            this.b.setColor(this.a.b.getBackground());
                            this.b.fillRect(0, 0, 288, 56);
                            this.b.setColor(Color.white);
                            this.b.fillRect(60, 25, 168, 26);
                            this.b.setColor(this.a.a[37]);
                            this.b.drawRect(120, 0, 24, 22);
                            this.b.drawRect(144, 0, 24, 22);
                            this.b.drawString(this.a.c[32], 175, 18);
                            this.b.setFont(this.a.a[1]);
                            this.b.drawString("B", 130, 17);
                            this.b.setFont(this.a.a[2]);
                            this.b.drawString("I", 154, 17);
                            int n3 = 0;
                            while (true) {
                                Label_0613: {
                                    if (!a) {
                                        break Label_0613;
                                    }
                                    int n4 = 0;
                                    while (true) {
                                        Label_0604: {
                                            if (!a) {
                                                break Label_0604;
                                            }
                                            this.b.setColor(this.a.a[n4 + n3 * 7]);
                                            this.b.fillRect(n4 * 24 + 62, 14 * n3 + 26, 20, 10);
                                            ++n4;
                                        }
                                        if (n4 < 7) {
                                            continue;
                                        }
                                        break;
                                    }
                                    ++n3;
                                }
                                if (n3 >= 2) {
                                    return;
                                }
                                continue;
                            }
                        }
                    }
                }
            }
            e.a(this.a[0], 2, 45, 25);
            if (a) {
                continue;
            }
            break;
        }
    }
    
    public void a(final Image image, final int n, final int n2, final int n3) {
        final int[][] array = new int[3][n2 * n3];
        final double[] array2 = new double[n2];
        final double[] array3 = new double[n3];
        final int[] array4 = { -1, -256, 0xFF000000 | this.a.b[38], -3355444, -65536, 0xFF000000 | this.a.b[37], -16711681, -10496 };
        final double n4 = 0.39269908169872414;
        for (int i = 1; i < 3; ++i) {
            final double n5 = i * n3;
            final double n6 = i * n2;
            for (int j = 0; j < n2; ++j) {
                array2[j] = Math.cos(Math.asin((j - n2 / 2) / n6) + n4);
            }
            for (int k = 0; k < n3; ++k) {
                array3[k] = Math.cos(Math.asin((k - n3 / 2) / n5) + n4);
            }
            for (int l = 0; l < n3; ++l) {
                for (int n7 = 0; n7 < n2; ++n7) {
                    final int n8 = array4[n];
                    array[i][n2 * l + n7] = -16777216;
                    for (int n9 = 0; n9 < 3; ++n9) {
                        final int[] array5 = array[i];
                        final int n10 = n2 * l + n7;
                        array5[n10] |= (int)((n8 >> (n9 << 3) & 0xFF) * array3[l] * array2[n7]) << (n9 << 3);
                    }
                }
            }
            this.a[i] = this.createImage(new MemoryImageSource(n2, n3, array[i], 0, n2));
            array[i] = null;
        }
        array[0] = null;
    }
}
