import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.IndexColorModel;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class plasmax extends Applet implements Runnable
{
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private int WinWidth;
    private int WinHeight;
    private int PlasmaW;
    private int PlasmaH;
    private byte[][] Couleurs;
    private byte[] PlasmaPixels;
    private IndexColorModel PlasmaIcm;
    private Image PlasmaIm;
    private wave2d Wave1;
    private wave2d Wave2;
    private wave2d Wave3;
    private double[][] Table1;
    private double[][] Table2;
    private double[][] Table3;
    private FontMetrics theFontsMetrics;
    private Font theFonts;
    private int Step;
    private boolean Ascend;
    private boolean Level;
    private int Count;
    private int Xc;
    private int Ycurrent;
    private int Yd;
    private int Yf;
    private String Text;
    private int IndA;
    private int IndB;
    private Thread myThread;
    private int FrameNumber;
    private String theColorType;
    private String thePlasmaType;
    private String theSize;
    private String Fonts;
    
    public void init() {
        this.WinWidth = this.size().width;
        this.WinHeight = this.size().height;
        this.theColorType = this.getParameter("colors");
        if (this.theColorType == null) {
            this.theColorType = "forest";
        }
        this.theColorType = this.theColorType.toUpperCase();
        this.thePlasmaType = this.getParameter("plasma");
        if (this.thePlasmaType == null) {
            this.thePlasmaType = "fluid";
        }
        this.thePlasmaType = this.thePlasmaType.toUpperCase();
        this.PlasmaW = this.WinWidth;
        this.PlasmaH = this.WinHeight;
        if (this.thePlasmaType.equals("EYE")) {
            this.Wave1 = new wave2d(new int[] { this.PlasmaW, this.PlasmaW }, this.PlasmaW, this.PlasmaH, 0.0, 100.0, 0, 1, wave2d.LINEAR);
            this.Table1 = this.Wave1.getTable();
            this.Wave2 = new wave2d(new int[] { 2 * this.PlasmaW, 2 * this.PlasmaH }, this.PlasmaW, this.PlasmaH, 0.0, 100.0, 2, 1, wave2d.ELLIPTIC);
            this.Table2 = this.Wave2.getTable();
            this.Wave3 = new wave2d(new int[] { 3 * this.PlasmaW, 5 * this.PlasmaH, 2 * this.PlasmaW, this.PlasmaH / 2 }, this.PlasmaW, this.PlasmaH, 0.0, 55.0, 1, 1, wave2d.ELLIPTIC);
            this.Table3 = this.Wave3.getTable();
        }
        else if (this.thePlasmaType.equals("ELLIPTIC")) {
            this.Wave1 = new wave2d(new int[] { 2 * this.PlasmaW, 2 * this.PlasmaW }, this.PlasmaW, this.PlasmaH, 0.0, 85.0, 1, 1, wave2d.ELLIPTIC);
            this.Table1 = this.Wave1.getTable();
            this.Wave2 = new wave2d(new int[] { this.PlasmaW, this.PlasmaW }, this.PlasmaW, this.PlasmaH, 0.0, 85.0, 2, 1, wave2d.ELLIPTIC);
            this.Table2 = this.Wave2.getTable();
            this.Wave3 = new wave2d(new int[] { this.PlasmaW / 2, this.PlasmaW / 2 }, this.PlasmaW, this.PlasmaH, 0.0, 85.0, 1, 2, wave2d.ELLIPTIC);
            this.Table3 = this.Wave3.getTable();
        }
        else if (this.thePlasmaType.equals("WAVELET")) {
            this.Wave1 = new wave2d(new int[] { 2 * this.PlasmaW, 2 * this.PlasmaW }, this.PlasmaW, this.PlasmaH, 0.0, 100.0, 1, 1, wave2d.LINEAR);
            this.Table1 = this.Wave1.getTable();
            this.Wave2 = new wave2d(new int[] { this.PlasmaW, this.PlasmaH, this.PlasmaW / 2, this.PlasmaH / 2 }, this.PlasmaW, this.PlasmaH, 0.0, 85.0, 2, 1, wave2d.LINEAR);
            this.Table2 = this.Wave2.getTable();
            this.Wave3 = new wave2d(new int[] { this.PlasmaW, 2 * this.PlasmaH, this.PlasmaW, this.PlasmaH / 2, this.PlasmaW / 2, this.PlasmaH / 3 }, this.PlasmaW, this.PlasmaH, 0.0, 70.0, 1, 2, wave2d.ELLIPTIC);
            this.Table3 = this.Wave3.getTable();
        }
        else {
            this.Wave1 = new wave2d(new int[] { 4 * this.PlasmaW, 4 * this.PlasmaH, this.PlasmaW, this.PlasmaH }, this.PlasmaW, this.PlasmaH, 0.0, 85.0, 1, 0, wave2d.LINEAR);
            this.Table1 = this.Wave1.getTable();
            this.Wave2 = new wave2d(new int[] { 2 * this.PlasmaW, 2 * this.PlasmaH }, this.PlasmaW, this.PlasmaH, 0.0, 110.0, 2, 1, wave2d.ELLIPTIC);
            this.Table2 = this.Wave2.getTable();
            this.Wave3 = new wave2d(new int[] { 3 * this.PlasmaH, 3 * this.PlasmaW, this.PlasmaH, this.PlasmaW }, this.PlasmaW, this.PlasmaH, 0.0, 60.0, 0, 1, wave2d.LINEAR);
            this.Table3 = this.Wave3.getTable();
        }
        this.Couleurs = new byte[3][256];
        if (this.theColorType.equals("FOREST")) {
            this.Fader(0, 0, 0, 0, 49, 0, 0, 49);
            this.Fader(10, 60, 0, 69, 119, 0, 50, 99);
            this.Fader(80, 130, 0, 70, 219, 99, 100, 169);
            this.Fader(70, 220, 100, 1, 1, 1, 170, 174);
            this.Fader(0, 0, 0, 240, 240, 50, 175, 179);
            this.Fader(241, 241, 50, 250, 250, 250, 180, 189);
            this.Fader(255, 255, 255, 1, 1, 1, 190, 194);
            this.Fader(0, 0, 0, 0, 0, 0, 195, 255);
        }
        else if (this.theColorType.equals("WATER")) {
            this.Fader(0, 0, 0, 0, 0, 79, 0, 79);
            this.Fader(0, 0, 80, 0, 49, 99, 80, 119);
            this.Fader(0, 50, 100, 19, 99, 139, 120, 179);
            this.Fader(20, 100, 140, 199, 254, 254, 180, 229);
            this.Fader(200, 255, 255, 240, 255, 240, 230, 255);
        }
        else if (this.theColorType.equals("RAINBOW")) {
            this.Fader(0, 0, 0, 254, 254, 254, 0, 29);
            this.Fader(255, 255, 255, 0, 254, 254, 30, 59);
            this.Fader(0, 255, 255, 0, 0, 254, 60, 89);
            this.Fader(0, 0, 255, 254, 0, 255, 90, 119);
            this.Fader(255, 0, 255, 255, 0, 0, 120, 149);
            this.Fader(255, 0, 0, 255, 254, 0, 150, 169);
            this.Fader(255, 255, 0, 0, 255, 0, 170, 209);
            this.Fader(0, 255, 0, 1, 1, 1, 210, 255);
        }
        else {
            this.Fader(0, 0, 0, 0, 0, 29, 0, 29);
            this.Fader(0, 0, 30, 29, 0, 59, 30, 59);
            this.Fader(30, 0, 60, 59, 29, 89, 60, 89);
            this.Fader(60, 30, 90, 129, 99, 159, 90, 119);
            this.Fader(130, 100, 160, 204, 204, 160, 120, 149);
            this.Fader(205, 205, 160, 1, 1, 1, 150, 169);
            this.Fader(0, 0, 0, 39, 69, 99, 170, 209);
            this.Fader(40, 70, 100, 149, 189, 199, 210, 255);
        }
        this.PlasmaPixels = new byte[this.PlasmaH * this.PlasmaW];
        for (int i = 0; i < this.PlasmaH; ++i) {
            for (int j = 0; j < this.PlasmaW; ++j) {
                this.PlasmaPixels[i * this.PlasmaW + j] = (byte)Math.round(this.Table1[i][j] + this.Table2[i][j] + this.Table3[i][j]);
            }
        }
        this.PlasmaIcm = new IndexColorModel(8, 256, this.Couleurs[0], this.Couleurs[1], this.Couleurs[2]);
        this.PlasmaIm = this.createImage(new MemoryImageSource(this.PlasmaW, this.PlasmaH, this.PlasmaIcm, this.PlasmaPixels, 0, this.PlasmaW));
        this.offScreenImage = this.createImage(this.WinWidth, this.WinHeight);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
        this.Fonts = this.getParameter("font");
        if (this.Fonts == null) {
            this.Fonts = "Helvetica";
        }
        this.theSize = this.getParameter("size");
        if (this.theSize == null) {
            this.theSize = "30";
        }
        this.theFonts = new Font(this.Fonts, 1, Math.abs(Integer.parseInt(this.theSize)));
        this.offScreenGraphics.setFont(this.theFonts);
        this.theFontsMetrics = this.offScreenGraphics.getFontMetrics();
        this.Step = 3;
        this.Yd = -(this.theFontsMetrics.getMaxDescent() + this.theFontsMetrics.getMaxAscent());
        this.Ycurrent = this.Yd;
        this.Yf = this.WinHeight + (this.theFontsMetrics.getMaxDescent() + this.theFontsMetrics.getMaxAscent());
        this.Count = 30;
        this.Ascend = true;
        this.Level = false;
        this.Text = this.getParameter("text");
        if (this.Text == null) {
            this.Text = "NO Text Parameter !#- PlasmaX Applet -#Copyright E.B 1998#Contact : ebsp@iname.com#http://ebsp.home.ml.org#";
        }
        this.Text = String.valueOf(this.Text) + " #_#";
        this.IndA = 0;
        this.IndB = this.Text.indexOf("#");
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this)).setPriority(10);
            this.FrameNumber = 0;
            this.myThread.start();
        }
    }
    
    public void stop() {
        if (this.myThread != null) {
            this.myThread.stop();
            this.myThread = null;
        }
    }
    
    public void run() {
        while (true) {
            ++this.FrameNumber;
            this.repaint();
            try {
                Thread.sleep(40L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreenImage != null) {
            graphics.drawImage(this.offScreenImage, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.PlasmaIm.flush();
        for (int i = 0; i < this.PlasmaH; ++i) {
            for (int j = 0; j < this.PlasmaW; ++j) {
                this.PlasmaPixels[i * this.PlasmaW + j] = (byte)Math.round(this.Table1[i + this.Wave1.getoY()][j + this.Wave1.getoX()] + this.Table2[i + this.Wave2.getoY()][j + this.Wave2.getoX()] + this.Table3[i + this.Wave3.getoY()][j + this.Wave3.getoX()]);
            }
        }
        this.Wave1.Propag();
        this.Wave2.Propag();
        this.Wave3.Propag();
        this.offScreenGraphics.drawImage(this.PlasmaIm, 0, 0, this.WinWidth, this.WinHeight, null);
        final String s = new String(this.Text.substring(this.IndA, this.IndB));
        this.Xc = this.WinWidth / 2 - this.theFontsMetrics.stringWidth(s) / 2;
        this.offScreenGraphics.setColor(Color.black);
        this.offScreenGraphics.drawString(s, this.Xc + 1, this.Ycurrent + 1);
        this.offScreenGraphics.setColor(Color.white);
        this.offScreenGraphics.drawString(s, this.Xc, this.Ycurrent);
        if (!this.Level) {
            if (this.Ascend) {
                this.Ycurrent += this.Step;
                if (this.Ycurrent > this.WinHeight / 2 + this.Step + 3) {
                    this.Ascend = false;
                    this.Level = true;
                }
            }
            else {
                this.Ycurrent += this.Step;
                if (this.Ycurrent > this.Yf) {
                    this.Ycurrent = this.Yd;
                    this.Ascend = true;
                    this.IndA = this.IndB + 1;
                    this.IndB = this.Text.indexOf("#", this.IndA);
                    if (this.Text.charAt(this.IndB - 1) == "_".charAt(0)) {
                        this.IndA = 0;
                        this.IndB = this.Text.indexOf("#");
                    }
                }
            }
        }
        else {
            --this.Count;
            if (this.Count == 0) {
                this.Count = 30;
                this.Level = false;
            }
        }
        this.paint(graphics);
    }
    
    private void Fader(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n8 - n7 + 1;
        final int n10 = n4 - n;
        final int n11 = n5 - n2;
        final int n12 = n6 - n3;
        final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
        final double n13 = n10 / sqrt;
        final double n14 = n11 / sqrt;
        final double n15 = n12 / sqrt;
        final double n16 = (sqrt + 1.0) / n9;
        double n17 = 0.0;
        for (int i = n7; i <= n8; ++i) {
            this.Couleurs[0][i] = (byte)Math.round(n + n17 * n13);
            this.Couleurs[1][i] = (byte)Math.round(n2 + n17 * n14);
            this.Couleurs[2][i] = (byte)Math.round(n3 + n17 * n15);
            n17 += n16;
        }
    }
}
