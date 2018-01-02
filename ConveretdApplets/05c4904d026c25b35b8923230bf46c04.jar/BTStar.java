import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.awt.TextField;
import java.awt.image.IndexColorModel;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BTStar extends Applet implements MouseListener
{
    double maxIZI2;
    double Ymid;
    double Xmid;
    double DelX;
    double paramC;
    double paramD;
    double Rstar;
    double Istar;
    int MaxIt;
    int maxColor;
    int OrbIt;
    int culculatedPix;
    int w;
    int h;
    int[] pixArr;
    int StarLines;
    int StarFactor;
    int ColorWhite;
    Image img;
    IndexColorModel RainbowColor;
    long generTime;
    boolean showXY;
    Formula formBT;
    TextField tfXY;
    
    public BTStar() {
        this.maxIZI2 = 4.0;
        this.Ymid = 0.0;
        this.Xmid = -0.5;
        this.DelX = 3.0;
        this.MaxIt = 256;
        this.maxColor = 96;
        this.showXY = true;
    }
    
    public void init() {
        final String parameter = this.getParameter("XYmidDelCD");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter);
            this.Xmid = Double.valueOf(stringTokenizer.nextToken());
            this.Ymid = Double.valueOf(stringTokenizer.nextToken());
            this.DelX = Double.valueOf(stringTokenizer.nextToken());
            this.paramC = Double.valueOf(stringTokenizer.nextToken());
            this.paramD = Double.valueOf(stringTokenizer.nextToken());
        }
        int int1 = 0;
        final String parameter2 = this.getParameter("Star");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2);
            int1 = Integer.parseInt(stringTokenizer2.nextToken());
            this.StarLines = Integer.parseInt(stringTokenizer2.nextToken());
            this.StarFactor = Integer.parseInt(stringTokenizer2.nextToken());
        }
        final String parameter3 = this.getParameter("MaxIt");
        if (parameter3 != null) {
            this.MaxIt = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("OrbIt");
        if (parameter4 != null) {
            this.OrbIt = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("MaxColor");
        if (parameter5 != null) {
            this.maxColor = Integer.parseInt(parameter5);
        }
        final int n = this.maxColor / 3;
        final int n2 = 2 * n;
        this.maxColor = 3 * n;
        final long n3 = n * n * n * n;
        final byte[] array = new byte[this.maxColor + 2];
        final byte[] array2 = new byte[this.maxColor + 2];
        final byte[] array3 = new byte[this.maxColor + 2];
        for (int i = 1; i < n2; ++i) {
            final long n4 = n - i;
            final long n5 = n4 * n4;
            final long n6 = n5 * n5;
            final byte[] array4 = array3;
            final int n7 = i;
            final byte[] array5 = array2;
            final int n8 = (i + n) % this.maxColor;
            final byte[] array6 = array;
            final int n9 = (i + n2) % this.maxColor;
            final byte b = (byte)(255L - 255L * n6 / n3);
            array6[n9] = b;
            array4[n7] = (array5[n8] = b);
        }
        this.ColorWhite = this.maxColor + 1;
        final byte[] array7 = array3;
        final int colorWhite = this.ColorWhite;
        final byte[] array8 = array2;
        final int colorWhite2 = this.ColorWhite;
        final byte[] array9 = array;
        final int colorWhite3 = this.ColorWhite;
        final byte b2 = -1;
        array9[colorWhite3] = b2;
        array7[colorWhite] = (array8[colorWhite2] = b2);
        this.RainbowColor = new IndexColorModel(8, this.maxColor + 2, array, array3, array2);
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        final String parameter6 = this.getParameter("showXY");
        if (parameter6 != null && parameter6.equalsIgnoreCase("N")) {
            this.showXY = false;
        }
        else {
            this.setLayout(new BorderLayout());
            String s;
            if (this.Ymid < 0.0) {
                s = "" + this.Xmid + " " + this.Ymid + "*i; " + (float)this.DelX;
            }
            else {
                s = "" + this.Xmid + " +" + this.Ymid + "*i; " + (float)this.DelX;
            }
            this.add("South", this.tfXY = new TextField(s));
            this.h -= this.tfXY.getPreferredSize().height;
        }
        this.pixArr = new int[this.w * (this.h + 2)];
        final String parameter7 = this.getParameter("Formula");
        if (parameter7 != null) {
            try {
                this.formBT = (Formula)Class.forName(parameter7).newInstance();
            }
            catch (Exception ex) {}
        }
        else {
            this.formBT = new JuliaZ2();
        }
        this.formBT.set(this.MaxIt, this.maxColor, this.maxIZI2);
        for (int j = 0; j < int1; ++j) {
            final double n10 = this.Istar * this.Istar;
            this.Istar = (this.Rstar + this.Rstar) * this.Istar + this.paramD;
            this.Rstar = this.Rstar * this.Rstar - n10 + this.paramC;
        }
        this.generTime = System.currentTimeMillis();
        this.BTracing(this.Xmid, this.Ymid, this.DelX / this.w, this.formBT, this.paramC, this.paramD);
        this.Orbits();
        this.img = this.createImage(new MemoryImageSource(this.w, this.h, this.RainbowColor, this.pixArr, this.w, this.w));
        this.generTime = System.currentTimeMillis() - this.generTime;
        this.addMouseListener(this);
    }
    
    public void destroy() {
        this.removeMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.Xmid += (mouseEvent.getX() - this.w / 2) * this.DelX / this.w;
        this.Ymid -= (mouseEvent.getY() - this.h / 2) * this.DelX / this.w;
        if (mouseEvent.isControlDown()) {
            if (mouseEvent.isShiftDown()) {
                this.DelX *= 4.0;
            }
            else {
                this.DelX *= 2.0;
            }
        }
        else if (mouseEvent.isShiftDown()) {
            this.DelX /= 4.0;
        }
        else {
            this.DelX /= 2.0;
        }
        this.generTime = System.currentTimeMillis();
        this.BTracing(this.Xmid, this.Ymid, this.DelX / this.w, this.formBT, this.paramC, this.paramD);
        this.Orbits();
        this.img.flush();
        this.img = this.createImage(new MemoryImageSource(this.w, this.h, this.RainbowColor, this.pixArr, this.w, this.w));
        this.generTime = System.currentTimeMillis() - this.generTime;
        if (this.showXY) {
            if (this.Ymid < 0.0) {
                this.tfXY.setText("" + this.Xmid + " " + this.Ymid + "*i; " + (float)this.DelX);
            }
            else {
                this.tfXY.setText("" + this.Xmid + " +" + this.Ymid + "*i; " + (float)this.DelX);
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.img, 0, 0, this);
        if (this.DelX > 0.1) {
            this.drawLabel(graphics);
        }
        this.showStatus("Time=" + this.generTime + " msec, Culculated =" + this.culculatedPix + "%");
    }
    
    public void Orbits() {
        double n = 0.0;
        double n2 = 0.0;
        final double n3 = this.DelX / this.w;
        for (int i = 0; i < this.OrbIt; ++i) {
            final double n4 = n * n;
            n = (n2 + n2) * n + this.paramD;
            n2 = n2 * n2 - n4 + this.paramC;
            final int n5 = (int)((n2 - this.Xmid) / n3) + this.w / 2;
            final int n6 = -(int)((n - this.Ymid) / n3) + this.h / 2;
            if (n5 >= 0 && n5 < this.w && n6 >= 0 && n6 < this.h) {
                this.pixArr[n5 + this.w * (n6 + 1)] = this.ColorWhite;
            }
        }
    }
    
    public void drawLabel(final Graphics graphics) {
        final double n = this.DelX / this.w;
        graphics.setColor(Color.white);
        double istar = this.Istar;
        double rstar = this.Rstar;
        final double n2 = this.DelX / this.w;
        int n3 = (int)((rstar - this.Xmid) / n2) + this.w / 2;
        int n4 = -(int)((istar - this.Ymid) / n2) + this.h / 2;
        for (int i = 0; i < this.StarLines; ++i) {
            for (int j = this.StarFactor; j > 0; --j) {
                final double n5 = istar * istar;
                istar = (rstar + rstar) * istar + this.paramD;
                rstar = rstar * rstar - n5 + this.paramC;
            }
            final int n6 = (int)((rstar - this.Xmid) / n2) + this.w / 2;
            final int n7 = -(int)((istar - this.Ymid) / n2) + this.h / 2;
            graphics.drawLine(n3, n4, n6, n7);
            n3 = n6;
            n4 = n7;
        }
        final String parameter = this.getParameter("sqrRID");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter);
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            final double doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
            final int n8 = (int)(Double.valueOf(stringTokenizer.nextToken()) / n);
            graphics.drawRect((this.w - n8) / 2 + (int)((doubleValue - this.Xmid) / n), (this.h - n8) / 2 + (int)((this.Ymid - doubleValue2) / n), n8, n8);
        }
        graphics.setFont(new Font(graphics.getFont().getName(), 1, 15));
        int n9 = 0;
        while (true) {
            final String parameter2 = this.getParameter("lb" + n9);
            if (parameter2 == null) {
                break;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2);
            graphics.drawString(stringTokenizer2.nextToken(), this.w / 2 + (int)((Double.valueOf(stringTokenizer2.nextToken()) - this.Xmid) / n), this.h / 2 + (int)((this.Ymid - Double.valueOf(stringTokenizer2.nextToken())) / n));
            ++n9;
        }
    }
    
    public void BTracing(final double n, final double n2, final double n3, final Formula formula, final double n4, final double n5) {
        for (int i = 0; i < this.w; ++i) {
            this.pixArr[i] = (this.pixArr[i + this.w * (this.h + 1)] = -2);
        }
        for (int j = this.w; j < this.w * (this.h + 1); ++j) {
            this.pixArr[j] = -1;
        }
        for (int k = this.w; k < this.w * (this.h + 2); k += this.w) {
            this.pixArr[k] = (this.pixArr[k - 1] = -2);
        }
        final double[] array = { n3, 0.0, -n3, 0.0 };
        final double[] array2 = { 0.0, -n3, 0.0, n3 };
        int n6 = this.w + 1;
        final int[] array3 = { 1, this.w, -1, -this.w };
        this.culculatedPix = this.h * (this.w - 2);
        int l = 0;
        for (double n7 = n2 + n3 * this.h / 2.0; l < this.h; ++l, n7 -= n3, n6 += 2) {
            int n8 = 1;
            for (double n9 = n - n3 * (this.w / 2 - 1); n8 < this.w - 1; ++n8, n9 += n3, ++n6) {
                if (this.pixArr[n6] == -1) {
                    double n10 = n9;
                    double n11 = n7;
                    int n13;
                    int n12 = n13 = n6;
                    int n14 = 0;
                    final int[] pixArr = this.pixArr;
                    final int n15 = n6;
                    final int iterate = formula.iterate(n9, n7, n4, n5);
                    pixArr[n15] = iterate;
                    int n16;
                    for (n16 = iterate; this.pixArr[n12 - this.w] == n16; n12 = (n13 = n12 - this.w), n11 += n3) {}
                    do {
                        for (int n17 = n14 + 3; n17 < n14 + 7; ++n17) {
                            final int n18 = n17 & 0x3;
                            final int n19 = n13 + array3[n18];
                            final double n20 = n10 + array[n18];
                            final double n21 = n11 + array2[n18];
                            int n22;
                            if ((n22 = this.pixArr[n19]) == -1) {
                                final int[] pixArr2 = this.pixArr;
                                final int n23 = n19;
                                final int iterate2 = formula.iterate(n20, n21, n4, n5);
                                pixArr2[n23] = iterate2;
                                n22 = iterate2;
                            }
                            if (n22 == n16) {
                                n14 = n18;
                                n13 = n19;
                                n10 = n20;
                                n11 = n21;
                                break;
                            }
                        }
                    } while (n13 != n12);
                    int n24 = 0;
                    do {
                        for (int n25 = n24 + 3; n25 < n24 + 7; ++n25) {
                            final int n26 = n25 & 0x3;
                            final int n27 = n13 + array3[n26];
                            if (this.pixArr[n27] == n16) {
                                if ((n24 = n26) == 3) {
                                    int n28 = n13;
                                    int n29;
                                    while ((n29 = this.pixArr[++n28]) == -1 || n29 == n16) {
                                        this.pixArr[n28] = n16;
                                        --this.culculatedPix;
                                    }
                                }
                                n13 = n27;
                                break;
                            }
                        }
                    } while (n13 != n12);
                }
            }
        }
        for (int w = this.w; w < this.w * (this.h + 1); w += this.w) {
            this.pixArr[w] = this.pixArr[w + 1];
            this.pixArr[w + this.w - 1] = this.pixArr[w + this.w - 2];
        }
        this.culculatedPix = (this.culculatedPix * 100 + this.w * this.h / 2) / (this.w * this.h);
    }
}
