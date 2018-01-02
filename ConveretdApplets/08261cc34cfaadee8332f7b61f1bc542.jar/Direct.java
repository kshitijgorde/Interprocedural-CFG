import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
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

public class Direct extends Applet implements MouseListener
{
    double maxIZI2;
    double Ymid;
    double Xmid;
    double DelX;
    double paramC;
    double paramD;
    int MaxIt;
    int maxColor;
    int w;
    int h;
    int[] pixArr;
    Image img;
    IndexColorModel RainbowColor;
    long generTime;
    boolean showXY;
    FormulaD formD;
    TextField tfXY;
    
    public Direct() {
        this.maxIZI2 = 10.0;
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
        final String parameter2 = this.getParameter("MaxIt");
        if (parameter2 != null) {
            this.MaxIt = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("MaxIZI2");
        if (parameter3 != null) {
            this.maxIZI2 = Double.valueOf(parameter3);
        }
        final String parameter4 = this.getParameter("MaxColor");
        if (parameter4 != null) {
            this.maxColor = Integer.parseInt(parameter4);
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
        final byte[] array7 = array;
        final int n10 = this.maxColor + 1;
        final byte[] array8 = array3;
        final int n11 = this.maxColor + 1;
        final byte[] array9 = array2;
        final int n12 = this.maxColor + 1;
        final byte b2 = -56;
        array9[n12] = b2;
        array7[n10] = (array8[n11] = b2);
        this.RainbowColor = new IndexColorModel(8, this.maxColor + 2, array, array3, array2);
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        final String parameter5 = this.getParameter("showXY");
        if (parameter5 != null && parameter5.equalsIgnoreCase("N")) {
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
        final String parameter6 = this.getParameter("Formula");
        if (parameter6 != null) {
            try {
                this.formD = (FormulaD)Class.forName(parameter6).newInstance();
            }
            catch (Exception ex) {}
        }
        else {
            this.formD = new DirectJ3();
        }
        this.Draw(this.Xmid, this.Ymid, this.DelX / this.w, this.formD, this.paramC, this.paramD);
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
        this.Draw(this.Xmid, this.Ymid, this.DelX / this.w, this.formD, this.paramC, this.paramD);
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
        if (this.DelX > 0.01) {
            this.drawLabel(graphics);
        }
        this.showStatus("Time=" + this.generTime + " msec");
    }
    
    public void drawLabel(final Graphics graphics) {
        final double n = this.DelX / this.w;
        graphics.setColor(Color.white);
        final String parameter = this.getParameter("sqrRID");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter);
            final double doubleValue = Double.valueOf(stringTokenizer.nextToken());
            final double doubleValue2 = Double.valueOf(stringTokenizer.nextToken());
            final int n2 = (int)(Double.valueOf(stringTokenizer.nextToken()) / n);
            graphics.drawRect((this.w - n2) / 2 + (int)((doubleValue - this.Xmid) / n), (this.h - n2) / 2 + (int)((this.Ymid - doubleValue2) / n), n2, n2);
        }
        graphics.setFont(new Font(graphics.getFont().getName(), 1, 15));
        int n3 = 0;
        while (true) {
            final String parameter2 = this.getParameter("lb" + n3);
            if (parameter2 == null) {
                break;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2);
            graphics.drawString(stringTokenizer2.nextToken(), this.w / 2 + (int)((Double.valueOf(stringTokenizer2.nextToken()) - this.Xmid) / n), this.h / 2 + (int)((this.Ymid - Double.valueOf(stringTokenizer2.nextToken())) / n));
            ++n3;
        }
    }
    
    public void Draw(final double n, final double n2, final double n3, final FormulaD formulaD, final double n4, final double n5) {
        int w = this.w;
        this.generTime = System.currentTimeMillis();
        int i = 0;
        for (double n6 = n2 + n3 * this.h / 2.0; i < this.h; ++i, n6 -= n3) {
            int j = 0;
            for (double n7 = n - n3 * (this.w / 2 - 1); j < this.w; ++j, n7 += n3, ++w) {
                this.pixArr[w] = formulaD.iterate(n7, n6, n4, n5, this.maxIZI2, this.MaxIt, this.maxColor);
            }
        }
        this.img = this.createImage(new MemoryImageSource(this.w, this.h, this.RainbowColor, this.pixArr, this.w, this.w));
        this.generTime = System.currentTimeMillis() - this.generTime;
    }
}
