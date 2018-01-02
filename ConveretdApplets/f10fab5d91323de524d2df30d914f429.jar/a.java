import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Component
{
    Image b;
    Image d;
    Image c;
    Image a;
    protected ActionListener a;
    String a;
    protected Color b;
    protected Color a;
    int f;
    int g;
    int a;
    int d;
    int b;
    double b;
    double c;
    double a;
    int e;
    int c;
    
    public a(final String a, final int b, final int d) {
        this.b = Color.lightGray;
        this.a = Color.black;
        this.f = 0;
        this.g = 0;
        this.a = 0;
        this.b = 1.3;
        this.c = 1.3;
        this.a = 2.0;
        this.e = 0;
        this.c = 0;
        this.a = a;
        this.d = d;
        this.b = b;
        this.enableEvents(16L);
    }
    
    void a() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final Color background = this.getBackground();
        if (width != this.a || height != this.g || !background.equals(this.b)) {
            final int rgb = background.getRGB();
            if (this.a.trim().equals("+") || this.a.trim().equals("-")) {
                final int n = width * height;
                final int[] array = new int[n];
                for (int i = 0; i < n; ++i) {
                    array[i] = rgb;
                }
                final Image image = this.createImage(new MemoryImageSource(width, height, array, 0, width));
                this.d = image;
                this.b = image;
            }
            else {
                final Image image2 = this.createImage(new MemoryImageSource(width, height, a(width, height, this.c, rgb, this.b), 0, width));
                this.d = image2;
                this.b = image2;
            }
            this.c = this.createImage(new MemoryImageSource(width, height, a(width, height, this.a, rgb, this.d), 0, width));
            this.a = this.createImage(new MemoryImageSource(width, height, a(width, height, this.b, rgb, this.d), 0, width));
            this.a = width;
            this.g = height;
            this.b = background;
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(40, 20);
    }
    
    public void a(final int e, final int c) {
        this.e = e;
        this.c = c;
    }
    
    public Dimension getPreferredSize() {
        final Font font = this.getFont();
        if (font != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            int e;
            if (this.e == 0) {
                e = fontMetrics.stringWidth(this.a) << 1;
            }
            else {
                e = this.e;
            }
            int c;
            if (this.c == 0) {
                c = fontMetrics.getHeight() << 1;
            }
            else {
                c = this.c;
            }
            if (e > 145) {
                e = 145;
            }
            return new Dimension(e, c);
        }
        return new Dimension(40, 20);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                if (mouseEvent.getModifiers() != 16) {
                    break;
                }
                this.b = this.c;
                this.f = 1;
                this.update(this.getGraphics());
                break;
            }
            case 502: {
                if (mouseEvent.getModifiers() != 16) {
                    return;
                }
                this.f = 0;
                if (mouseEvent.getX() < 0 || mouseEvent.getX() > this.a || mouseEvent.getY() < 0 || mouseEvent.getY() > this.g) {
                    this.b = this.d;
                    if (this.getGraphics() != null) {
                        this.update(this.getGraphics());
                        break;
                    }
                    break;
                }
                else {
                    this.b = this.a;
                    this.update(this.getGraphics());
                    if (this.a != null) {
                        this.a.actionPerformed(new ActionEvent(this, 1001, this.a));
                    }
                    if (this.getGraphics() != null) {
                        this.update(this.getGraphics());
                        break;
                    }
                    this.b = this.d;
                    break;
                }
                break;
            }
            case 504: {
                if (this.f == 0) {
                    this.b = this.a;
                }
                else {
                    this.b = this.c;
                }
                this.update(this.getGraphics());
                break;
            }
            case 505: {
                if (this.f != 0) {
                    break;
                }
                this.b = this.d;
                if (this.getGraphics() != null) {
                    this.update(this.getGraphics());
                    break;
                }
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void a(final ActionListener actionListener) {
        this.a = AWTEventMulticaster.add(this.a, actionListener);
        this.enableEvents(16L);
    }
    
    public void paint(final Graphics graphics) {
        this.a();
        final Font font = this.getFont();
        graphics.drawImage(this.b, 0, 0, null);
        graphics.setFont(font);
        graphics.setColor(this.getForeground());
        graphics.drawString(this.a, (this.a - this.getFontMetrics(font).stringWidth(this.a) >> 1) + this.f, (this.g >> 1) + this.getFontMetrics(font).getDescent() + this.f);
    }
    
    static int[] a(int n, int n2, final double n3, final int n4, final int n5) {
        final boolean a = d.a;
        n <<= 1;
        n2 <<= 1;
        final int[] array = new int[n * n2];
        final int n6 = n2 / 2;
        final int n7 = n / 2;
        final int n8 = n * n2;
        final int n9 = n * n2 / 2 - n;
        final int[] array2 = new int[n7 * n6];
        final double n10 = n2 / n;
        final double[] array3 = new double[n];
        final double[] array4 = new double[n2];
        final double n11 = n3 * n;
        final double n12 = n3 * n2;
        double n13 = 0.39269908169872414;
        int n14 = 0;
        int n15;
        while (true) {
            while (true) {
                Label_0125: {
                    if (!a) {
                        break Label_0125;
                    }
                    array3[n14] = Math.cos(2.0 * Math.asin((n14 - n7) / n11) - n13);
                    ++n14;
                }
                if (n14 < n) {
                    continue;
                }
                break;
            }
            n15 = 0;
            if (a) {
                continue;
            }
            break;
        }
        int n16;
        while (true) {
            while (true) {
                Label_0174: {
                    if (!a) {
                        break Label_0174;
                    }
                    array4[n15] = Math.cos(2.0 * Math.asin((n15 - n6) / n12) - n13);
                    ++n15;
                }
                if (n15 < n2) {
                    continue;
                }
                break;
            }
            n16 = 0;
            if (a) {
                continue;
            }
            break;
        }
        int n17;
        while (true) {
            while (true) {
                Label_0206: {
                    if (!a) {
                        break Label_0206;
                    }
                    array[n16] = (0xFF000000 | n4);
                    ++n16;
                }
                if (n16 < n8) {
                    continue;
                }
                break;
            }
            n17 = 0;
            if (a) {
                continue;
            }
            break;
        }
        int n21;
        while (true) {
            while (true) {
                Label_0337: {
                    if (!a) {
                        break Label_0337;
                    }
                    n13 = array3[n - n17 - 1];
                    final int n18 = (int)(n10 * Math.sqrt(n17 * (2 * n7 - n17)));
                    final int n19 = n17 + n9;
                    int n20 = 0;
                    while (true) {
                        Label_0327: {
                            if (!a) {
                                break Label_0327;
                            }
                            array[n19 + n * (n20 + 1)] = a(n13, array4[n6 - n20], n5);
                            array[n19 - n * n20] = a(n13, array4[n6 + n20], n5);
                            ++n20;
                        }
                        if (n20 < n18) {
                            continue;
                        }
                        break;
                    }
                    ++n17;
                }
                if (n17 < n) {
                    continue;
                }
                break;
            }
            n21 = 0;
            if (a) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0531: {
                if (!a) {
                    break Label_0531;
                }
                final int n22 = n7 * n21;
                int n23 = 0;
            Label_0498_Outer:
                while (true) {
                    Label_0522: {
                        if (!a) {
                            break Label_0522;
                        }
                        final int n24 = array[n * n21 + n23];
                        final int n25 = array[n * n21 + n23 + 1];
                        final int n26 = array[n * n21 + n + n23];
                        final int n27 = array[n * n21 + n + n23 + 1];
                        int n28 = -16777216;
                        final int n29 = n22 + n23 >> 1;
                        int n30 = 0;
                        while (true) {
                            while (true) {
                                Label_0501: {
                                    if (!a) {
                                        break Label_0501;
                                    }
                                    final int n31 = 255 << 8 * n30;
                                    n28 |= ((n24 & n31) + (n25 & n31) + (n26 & n31) + (n27 & n31) >> 2 & n31);
                                    ++n30;
                                }
                                if (n30 < 3) {
                                    continue Label_0498_Outer;
                                }
                                break;
                            }
                            array2[n29] = n28;
                            if (a) {
                                continue;
                            }
                            break;
                        }
                        n23 += 2;
                    }
                    if (n23 < n) {
                        continue;
                    }
                    break;
                }
                n21 += 2;
            }
            if (n21 >= n2) {
                return array2;
            }
            continue;
        }
    }
    
    static int a(final double n, final double n2, final int n3) {
        int n4 = -16777216;
        for (int i = 0; i < 3; ++i) {
            n4 |= (int)((n3 >> (i << 3) & 0xFF) * n * n2) << (i << 3);
        }
        return n4;
    }
}
