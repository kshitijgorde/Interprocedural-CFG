import java.awt.FontMetrics;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fountain extends Applet implements Runnable
{
    final String author;
    int xw;
    int yw;
    int sleep;
    int longSleep;
    int step;
    int stepMax;
    int textN;
    int textI;
    int intColorBgrnd;
    int RedBgrnd;
    int GreenBgrnd;
    int BlueBgrnd;
    Image buf_image;
    Image image;
    private Graphics buf_g;
    private Graphics gr;
    Dimension dimImage;
    Thread timer;
    int[] rgbImage;
    int[] rgbOld;
    int[] rgb;
    int[] delR;
    int[] delG;
    int[] delB;
    int[] random;
    int[][] rgbText;
    int[] mode;
    int[] downUp;
    int[] track;
    int[] dissolveMode;
    int[] dissolveStep;
    int[] dissolveDelay;
    int[] shadowShift;
    boolean[] colorYes;
    double[] trajectory;
    Color[] colorBgrnd;
    Color[] colorRight;
    Color[] colorLeft;
    Color[] colorShadow;
    URL[] url;
    
    public void stop() {
        this.timer = null;
    }
    
    void RGB(final int step) {
        final int red = -65536;
        final int green = -16711936;
        final int blue = -16776961;
        final int rx = this.xw / 4;
        final int gx = this.xw / 2;
        final int bx = this.xw * 3 / 4;
        final double time = step / this.stepMax;
        final double timeStart = 0.5;
        final double timeFlight = 0.5;
        final boolean dissolve = step >= this.dissolveDelay[this.textI] - this.dissolveStep[this.textI] && step < this.dissolveDelay[this.textI];
        if (this.dissolveMode[this.textI] == 0) {
            for (int ixy = 0; ixy < this.xw * this.yw; ++ixy) {
                if (this.track[this.textI] == 0 && step == 0) {
                    final int tmp = this.rgbOld[ixy];
                    this.delR[ixy] = (this.RedBgrnd - (tmp << 8 >>> 24)) / this.dissolveStep[this.textI];
                    this.delG[ixy] = (this.GreenBgrnd - (tmp << 16 >>> 24)) / this.dissolveStep[this.textI];
                    this.delB[ixy] = (this.BlueBgrnd - (tmp & 0xFF)) / this.dissolveStep[this.textI];
                }
                if (this.track[this.textI] == 1 && step == this.dissolveDelay[this.textI] - this.dissolveStep[this.textI]) {
                    final int tmp = this.rgbImage[ixy];
                    this.delR[ixy] = (this.RedBgrnd - (tmp << 8 >>> 24)) / this.dissolveStep[this.textI];
                    this.delG[ixy] = (this.GreenBgrnd - (tmp << 16 >>> 24)) / this.dissolveStep[this.textI];
                    this.delB[ixy] = (this.BlueBgrnd - (tmp & 0xFF)) / this.dissolveStep[this.textI];
                    this.rgbOld[ixy] = tmp;
                }
                if (dissolve) {
                    final int[] rgbOld = this.rgbOld;
                    final int n = ixy;
                    rgbOld[n] += ((this.delR[ixy] > 0) ? (this.delR[ixy] << 16) : (-(-this.delR[ixy] << 16)));
                    final int[] rgbOld2 = this.rgbOld;
                    final int n2 = ixy;
                    rgbOld2[n2] += ((this.delG[ixy] > 0) ? (this.delG[ixy] << 8) : (-(-this.delG[ixy] << 8)));
                    final int[] rgbOld3 = this.rgbOld;
                    final int n3 = ixy;
                    rgbOld3[n3] += this.delB[ixy];
                }
                if (this.track[this.textI] == 0 || step >= this.dissolveDelay[this.textI] - this.dissolveStep[this.textI]) {
                    this.rgbImage[ixy] = ((step >= this.dissolveDelay[this.textI]) ? this.intColorBgrnd : this.rgbOld[ixy]);
                }
            }
        }
        else {
            final int tmp = (step - this.dissolveDelay[this.textI] + this.dissolveStep[this.textI]) * 100 / this.dissolveStep[this.textI];
            for (int ixy = 0; ixy < this.xw * this.yw; ++ixy) {
                if (this.track[this.textI] == 0) {
                    if (this.random[ixy] < tmp) {
                        this.rgbOld[ixy] = this.intColorBgrnd;
                    }
                    this.rgbImage[ixy] = this.rgbOld[ixy];
                }
                else if (this.random[ixy] < tmp) {
                    this.rgbOld[ixy] = this.intColorBgrnd;
                    this.rgbImage[ixy] = this.rgbOld[ixy];
                }
            }
        }
        if (this.mode[this.textI] == 0) {
            for (int iy = 0; iy < this.yw; ++iy) {
                final double timeYstart = timeStart * ((this.yw - 2 * iy) * this.downUp[this.textI] + iy) / this.yw;
                if (timeYstart < time) {
                    final double time2 = (time - timeYstart < timeFlight) ? ((time - timeYstart) / timeFlight) : 1.0;
                    final double time3 = 1.0 - time2;
                    final int iy2 = (int)(this.yw * time3 + iy * time2 - this.trajectory[this.textI] * this.yw * time3 * time2);
                    final int iy1_xw = iy2 * this.xw;
                    for (int ix = 0; ix < this.xw; ++ix) {
                        final int ixy = ix + iy * this.xw;
                        if (this.rgbText[this.textI][ixy] > 0) {
                            if (time - timeYstart >= timeFlight) {
                                this.rgbImage[ixy] = this.rgb[ixy];
                            }
                            else if (iy2 >= 0) {
                                this.rgbImage[(int)(this.xw / 2 * time3 + ix * time2) + iy1_xw] = this.rgb[ixy];
                            }
                        }
                    }
                }
            }
        }
        else {
            for (int iy = 0; iy < this.yw; ++iy) {
                final double timeYstart = timeStart * ((this.yw - 2 * iy) * this.downUp[this.textI] + iy) / this.yw;
                if (timeYstart < time) {
                    final double time2 = (time - timeYstart < timeFlight) ? ((time - timeYstart) / timeFlight) : 1.0;
                    final double time3 = 1.0 - time2;
                    final double rx_time0 = rx * time3;
                    final double gx_time0 = gx * time3;
                    final double bx_time0 = bx * time3;
                    final int iy2 = (int)(this.yw * time3 + iy * time2 - this.trajectory[this.textI] * this.yw * time3 * time2);
                    final int iy1_xw = iy2 * this.xw;
                    for (int ix = 0; ix < this.xw; ++ix) {
                        final int ixy = ix + iy * this.xw;
                        if (this.rgbText[this.textI][ixy] > 0) {
                            if (time - timeYstart >= timeFlight) {
                                this.rgbImage[ixy] = this.rgb[ixy];
                            }
                            else if (iy2 >= 0) {
                                this.rgbImage[(int)(rx_time0 + ix * time2) + iy1_xw] = red;
                                this.rgbImage[(int)(gx_time0 + ix * time2) + iy1_xw] = green;
                                this.rgbImage[(int)(bx_time0 + ix * time2) + iy1_xw] = blue;
                            }
                        }
                    }
                }
            }
        }
    }
    
    void colorChoice() {
        final int[] col = new int[3];
        this.intColorBgrnd = this.colorBgrnd[this.textI].getRGB();
        this.RedBgrnd = this.colorBgrnd[this.textI].getRed();
        this.GreenBgrnd = this.colorBgrnd[this.textI].getGreen();
        this.BlueBgrnd = this.colorBgrnd[this.textI].getBlue();
        final int intColorShadow = (this.shadowShift[this.textI] > 0) ? this.colorShadow[this.textI].getRGB() : -1;
        Color color_Left;
        Color color_Right;
        if (this.colorYes[this.textI]) {
            color_Left = this.colorLeft[this.textI];
            color_Right = this.colorRight[this.textI];
        }
        else {
            int i1 = (int)Math.floor(3.0 * Math.random());
            int i2 = (int)((i1 + Math.ceil(2.0 * Math.random())) % 3.0);
            col[i1] = 255;
            col[i2] = (int)(255.0 * Math.random());
            col[3 - (i1 + i2)] = 0;
            color_Right = new Color(col[0], col[1], col[2]);
            i1 = (int)Math.floor(3.0 * Math.random());
            i2 = (int)((i1 + Math.ceil(2.0 * Math.random())) % 3.0);
            col[i1] = 255;
            col[i2] = (int)(255.0 * Math.random());
            col[3 - (i1 + i2)] = 0;
            color_Left = new Color(col[0], col[1], col[2]);
        }
        final int rl = color_Left.getRed();
        final int rr = color_Right.getRed();
        final int gl = color_Left.getGreen();
        final int gr = color_Right.getGreen();
        final int bl = color_Left.getBlue();
        final int br = color_Right.getBlue();
        for (int j = 0; j < this.xw * this.yw; ++j) {
            final double z = j % this.xw / this.xw;
            if (this.rgbText[this.textI][j] == 1) {
                final int r = rl + (int)((rr - rl) * z);
                final int g = gl + (int)((gr - gl) * z);
                final int b = bl + (int)((br - bl) * z);
                this.rgb[j] = (0xFF000000 | r << 16 | g << 8 | b);
            }
            else {
                this.rgb[j] = ((this.rgbText[this.textI][j] == 2) ? intColorShadow : this.intColorBgrnd);
            }
        }
    }
    
    public void paint(final Graphics g) {
        if (this.buf_image != null) {
            g.drawImage(this.buf_image, 0, 0, null);
        }
    }
    
    public void update(final Graphics g) {
        this.paintBuffer(this.buf_g);
        this.paint(g);
    }
    
    public Fountain() {
        this.author = new String("Author: Vladimir N. Korotkov; www.geocities.com/SiliconValley/Byte/3392");
        this.textI = -1;
        this.mode = new int[10];
        this.downUp = new int[10];
        this.track = new int[10];
        this.dissolveMode = new int[10];
        this.dissolveStep = new int[10];
        this.dissolveDelay = new int[10];
        this.shadowShift = new int[10];
        this.colorYes = new boolean[10];
        this.trajectory = new double[10];
        this.colorBgrnd = new Color[10];
        this.colorRight = new Color[10];
        this.colorLeft = new Color[10];
        this.colorShadow = new Color[10];
        this.url = new URL[10];
    }
    
    public void start() {
        (this.timer = new Thread(this)).start();
    }
    
    public void paintBuffer(final Graphics g) {
        this.RGB(this.step);
        g.drawImage(this.createImage(new MemoryImageSource(this.xw, this.yw, this.rgbImage, 0, this.xw)), 0, 0, this);
        if (this.step == this.stepMax) {
            this.textI = ((this.textI == this.textN) ? 0 : (++this.textI));
            for (int i = 0; i < this.xw * this.yw; ++i) {
                this.rgbOld[i] = this.rgb[i];
            }
        }
        this.step = ((this.step == this.stepMax) ? 0 : (++this.step));
        try {
            Thread.sleep(this.sleep);
        }
        catch (InterruptedException ex) {}
        if (this.step == this.stepMax) {
            try {
                Thread.sleep(this.longSleep);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        super.getAppletContext().showDocument(this.url[this.textI]);
        return true;
    }
    
    public void run() {
        while (this.timer != null) {
            if (this.step == 0) {
                this.colorChoice();
            }
            this.repaint();
        }
        this.timer = null;
    }
    
    public void init() {
        Color colorShadowD = Color.white;
        final int[] fontStyle = new int[10];
        final int[] fontSize = new int[10];
        final int[] ground = new int[10];
        final String[] text = new String[10];
        final String[] fontName = new String[10];
        this.xw = this.size().width;
        this.yw = this.size().height;
        this.image = this.createImage(this.xw, this.yw);
        this.gr = this.image.getGraphics();
        this.rgbOld = new int[this.xw * this.yw];
        this.rgb = new int[this.xw * this.yw];
        this.delR = new int[this.xw * this.yw];
        this.delG = new int[this.xw * this.yw];
        this.delB = new int[this.xw * this.yw];
        this.rgbText = new int[10][this.xw * this.yw];
        this.rgbImage = new int[2 * this.xw * this.yw];
        this.random = new int[this.xw * this.yw];
        int intAuthor = 0;
        for (int i = 0; i < this.author.length(); ++i) {
            intAuthor += Character.digit(this.getParameter("Author").toCharArray()[i], 32);
        }
        if (intAuthor != 1045) {
            this.xw = 0;
        }
        this.stepMax = Integer.parseInt(this.getParameter("stepMax"));
        this.sleep = Integer.parseInt(this.getParameter("sleep"));
        this.longSleep = Integer.parseInt(this.getParameter("longSleep"));
        final double trajectoryD = Double.valueOf(this.getParameter("trajectoryD"));
        final int modeD = Integer.parseInt(this.getParameter("modeD"));
        final int downUpD = Integer.parseInt(this.getParameter("downUpD"));
        final int trackD = Integer.parseInt(this.getParameter("trackD"));
        final int dissolveModeD = Integer.parseInt(this.getParameter("dissolveModeD"));
        final int dissolveStepD = Integer.parseInt(this.getParameter("dissolveStepD"));
        final int dissolveDelayD = Integer.parseInt(this.getParameter("dissolveDelayD")) * this.stepMax / 100;
        final Color colorBgrndD = new Color(Integer.parseInt(this.getParameter("colorBgrndD"), 16));
        final int shadowShiftD = Integer.parseInt(this.getParameter("shadowShiftD"));
        colorShadowD = new Color(Integer.parseInt(this.getParameter("colorShadowD"), 16));
        final String fontNameD = this.getParameter("fontNameD");
        final int fontStyleD = Integer.parseInt(this.getParameter("fontStyleD"));
        final int fontSizeD = Integer.parseInt(this.getParameter("fontSizeD"));
        final int groundD = Integer.parseInt(this.getParameter("groundD"));
        final String urlDstring = this.getParameter("urlD");
        try {
            final URL urlD = new URL(urlDstring);
        }
        catch (MalformedURLException ex) {}
        String paramText = this.getParameter("text0");
        while (paramText != null) {
            text[++this.textI] = new String(paramText);
            String param = this.getParameter("trajectory" + this.textI);
            this.trajectory[this.textI] = ((param != null) ? Double.valueOf(param) : trajectoryD);
            param = this.getParameter("mode" + this.textI);
            this.mode[this.textI] = ((param != null) ? Integer.parseInt(param) : modeD);
            param = this.getParameter("downUp" + this.textI);
            this.downUp[this.textI] = ((param != null) ? Integer.parseInt(param) : downUpD);
            param = this.getParameter("track" + this.textI);
            this.track[this.textI] = ((param != null) ? Integer.parseInt(param) : trackD);
            param = this.getParameter("dissolveMode" + this.textI);
            this.dissolveMode[this.textI] = ((param != null) ? Integer.parseInt(param) : dissolveModeD);
            param = this.getParameter("dissolveStep" + this.textI);
            this.dissolveStep[this.textI] = ((param != null) ? Integer.parseInt(param) : dissolveStepD);
            param = this.getParameter("dissolveDelay" + this.textI);
            this.dissolveDelay[this.textI] = ((param != null) ? Integer.parseInt(param) : dissolveDelayD);
            param = this.getParameter("colorBgrnd" + this.textI);
            this.colorBgrnd[this.textI] = ((param != null) ? new Color(Integer.parseInt(param, 16)) : new Color(colorBgrndD.getRGB()));
            param = this.getParameter("colorLeft" + this.textI);
            if (param != null) {
                this.colorLeft[this.textI] = new Color(Integer.parseInt(param, 16));
                this.colorYes[this.textI] = true;
                param = this.getParameter("colorRight" + this.textI);
                if (param != null) {
                    this.colorRight[this.textI] = new Color(Integer.parseInt(param, 16));
                }
                else {
                    this.colorRight[this.textI] = this.colorLeft[this.textI];
                }
            }
            else {
                this.colorYes[this.textI] = false;
            }
            param = this.getParameter("shadowShift" + this.textI);
            this.shadowShift[this.textI] = ((param != null) ? Integer.parseInt(param) : shadowShiftD);
            param = this.getParameter("colorShadow" + this.textI);
            this.colorShadow[this.textI] = ((param != null) ? new Color(Integer.parseInt(param, 16)) : new Color(colorShadowD.getRGB()));
            param = this.getParameter("fontName" + this.textI);
            fontName[this.textI] = ((param != null) ? new String(param) : new String(fontNameD));
            param = this.getParameter("fontStyle" + this.textI);
            fontStyle[this.textI] = ((param != null) ? Integer.parseInt(param) : fontStyleD);
            param = this.getParameter("fontSize" + this.textI);
            fontSize[this.textI] = ((param != null) ? Integer.parseInt(param) : fontSizeD);
            param = this.getParameter("ground" + this.textI);
            ground[this.textI] = ((param != null) ? Integer.parseInt(param) : groundD);
            param = this.getParameter("url" + this.textI);
            try {
                this.url[this.textI] = ((param != null) ? new URL(param) : new URL(urlDstring));
            }
            catch (MalformedURLException ex2) {}
            paramText = this.getParameter("text" + (this.textI + 1));
            this.gr.setColor(Color.white);
            this.gr.fillRect(0, 0, this.xw, this.yw);
            this.gr.setFont(new Font(fontName[this.textI], fontStyle[this.textI], fontSize[this.textI]));
            final FontMetrics fm = this.getFontMetrics(this.gr.getFont());
            final int indexLineEnd = text[this.textI].indexOf(124);
            if (this.shadowShift[this.textI] > 0) {
                this.gr.setColor(Color.red);
                for (int i = 1; i <= this.shadowShift[this.textI]; ++i) {
                    if (indexLineEnd == -1) {
                        this.gr.drawString(text[this.textI], (this.xw - fm.stringWidth(text[this.textI])) / 2 + i, this.yw - ground[this.textI] + i);
                    }
                    else {
                        this.gr.drawString(text[this.textI].substring(0, indexLineEnd), (this.xw - fm.stringWidth(text[this.textI].substring(0, indexLineEnd))) / 2 + i, this.yw / 2 - ground[this.textI] + i);
                        this.gr.drawString(text[this.textI].substring(indexLineEnd + 1), (this.xw - fm.stringWidth(text[this.textI].substring(indexLineEnd + 1))) / 2 + i, this.yw - ground[this.textI] + i);
                    }
                }
            }
            this.gr.setColor(Color.black);
            if (indexLineEnd == -1) {
                this.gr.drawString(text[this.textI], (this.xw - fm.stringWidth(text[this.textI])) / 2, this.yw - ground[this.textI]);
            }
            else {
                this.gr.drawString(text[this.textI].substring(0, indexLineEnd), (this.xw - fm.stringWidth(text[this.textI].substring(0, indexLineEnd))) / 2, this.yw / 2 - ground[this.textI]);
                this.gr.drawString(text[this.textI].substring(indexLineEnd + 1), (this.xw - fm.stringWidth(text[this.textI].substring(indexLineEnd + 1))) / 2, this.yw - ground[this.textI]);
            }
            final PixelGrabber pg = new PixelGrabber(this.image, 0, 0, this.xw, this.yw, this.rgb, 0, this.xw);
            try {
                pg.grabPixels();
            }
            catch (InterruptedException ex3) {}
            if (this.shadowShift[this.textI] > 0) {
                for (int i = 0; i < this.xw * this.yw; ++i) {
                    this.rgbText[this.textI][i] = ((this.rgb[i] == -1) ? 0 : ((this.rgb[i] == -16777216) ? 1 : 2));
                }
            }
            else {
                for (int i = 0; i < this.xw * this.yw; ++i) {
                    this.rgbText[this.textI][i] = ((this.rgb[i] != -1) ? 1 : 0);
                }
            }
        }
        this.textN = this.textI;
        this.textI = 0;
        for (int i = 0; i < this.xw * this.yw; ++i) {
            this.rgbOld[i] = this.colorBgrnd[0].getRGB();
            this.rgbImage[i] = this.rgbOld[i];
            this.random[i] = (int)(Math.random() * 100.0);
        }
        final Dimension dim = this.size();
        final int nWidth = dim.width;
        final int nHeight = dim.height;
        this.dimImage = new Dimension(nWidth, nHeight);
        this.buf_image = this.createImage(nWidth, nHeight);
        this.buf_g = this.buf_image.getGraphics();
    }
}
