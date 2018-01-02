import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class announceit extends Applet implements Runnable
{
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 3;
    public static final int BOTTOM = 4;
    private int WinWidth;
    private int WinHeight;
    private Graphics offScreenGraphics;
    private Image offScreenImage;
    private PixelGrabber pg;
    private Image mask;
    private Graphics maskGraphics;
    private int[] maskdata;
    private Image image;
    private MemoryImageSource imagesource;
    private int[] pixeldata;
    private float[][] dummyFilter;
    private float[][] toLeftFilter;
    private float[][] toRightFilter;
    private float[][] toRightLongFilter;
    private float[][] blurFilter;
    private float[][] toLeftShadowFilter;
    private float[][] puzzleFilter;
    private float[][] isospreadFilter;
    private float[][] toBottomBloodFilter;
    private float[][][] allFades;
    private float[][][] fadeOut;
    private float[][][] fadeIn;
    private FontMetrics theFontsMetrics;
    private Font theFonts;
    private String FontName;
    private int FontStyle;
    private int FontSize;
    private int FontYspace;
    private int FontJustif;
    private int FontVJustif;
    private int nbText;
    private Color FontColor;
    private Color BgColor;
    private Color CurrentColor;
    private String[] Text;
    private int[] nbTextLines;
    private Thread thread;
    private int SleepTime;
    private int Time;
    private int TimeOut;
    private int[] Wait;
    private int frameToWait;
    private int currentStep;
    private int FrameNumber;
    private boolean isVanished;
    private int total_frames;
    private int total_frame_time;
    
    public void init() {
        String parameter = this.getParameter("fps");
        if (parameter == null) {
            parameter = "20";
        }
        final int abs = Math.abs(Integer.parseInt(parameter));
        if (abs != 0) {
            this.SleepTime = Math.round(1000 / abs);
        }
        else {
            this.SleepTime = 50;
        }
        final String parameter2 = this.getParameter("timeout");
        if (parameter2 == null) {
            this.TimeOut = 80;
        }
        else {
            this.TimeOut = Math.abs(Integer.parseInt(parameter2));
        }
        int n = 0;
        boolean b = false;
        while (!b) {
            if (this.getParameter("text_" + n) == null) {
                b = true;
            }
            else {
                ++n;
            }
        }
        if (n > 0) {
            this.Text = new String[n];
            this.nbTextLines = new int[n];
            this.Wait = new int[n];
            this.fadeOut = new float[n][1][1];
            this.fadeIn = new float[n][1][1];
            for (int i = 0; i < n; ++i) {
                this.Text[i] = this.getParameter("text_" + i);
                this.nbTextLines[i] = this.getNbLines(this.Text[i]);
                this.Wait[i] = Math.abs(Integer.parseInt(this.getParameter("wait_" + i)));
                this.fadeOut[i] = this.allFades[this.selectEffect(this.getParameter("fadeout_" + i).toUpperCase())];
            }
        }
        final Dimension size = this.size();
        this.WinWidth = size.width;
        this.WinHeight = size.height;
        this.getFontParameters();
        this.theFonts = new Font(this.FontName, this.FontStyle, this.FontSize);
        this.CurrentColor = this.FontColor;
        this.offScreenImage = this.createImage(this.WinWidth, this.WinHeight);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
        this.pixeldata = new int[this.WinWidth * this.WinHeight];
        this.imagesource = new MemoryImageSource(this.WinWidth, this.WinHeight, this.pixeldata, 0, this.WinWidth);
        this.image = this.createImage(this.imagesource);
        this.maskdata = new int[this.WinWidth * this.WinHeight];
        this.mask = this.createImage(this.WinWidth, this.WinHeight);
        (this.maskGraphics = this.mask.getGraphics()).setFont(this.theFonts);
        this.theFontsMetrics = this.maskGraphics.getFontMetrics();
    }
    
    public void start() {
        this.FrameNumber = 0;
        this.displayTextToMask(this.currentStep = 0);
        this.iterativeFilter(1, this.dummyFilter);
        this.frameToWait = this.Wait[this.currentStep];
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.stop();
        }
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread != null && this.thread.isAlive()) {
            this.thread.stop();
        }
    }
    
    public void run() {
        final boolean b = false;
        this.total_frame_time = (b ? 1 : 0);
        this.total_frames = (b ? 1 : 0);
        while (true) {
            ++this.FrameNumber;
            this.repaint();
            try {
                Thread.sleep(this.SleepTime);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void update(final Graphics graphics) {
        if (graphics != null) {
            this.image.flush();
            if (this.frameToWait > 0) {
                --this.frameToWait;
            }
            else {
                ++this.Time;
                this.isVanished = this.iterativeFilter(1, this.fadeOut[this.currentStep]);
                if (this.Time > this.TimeOut || this.isVanished) {
                    this.Time = 0;
                    if (this.currentStep < this.Wait.length - 1) {
                        ++this.currentStep;
                        this.frameToWait = this.Wait[this.currentStep];
                    }
                    else {
                        this.currentStep = 0;
                        this.frameToWait = this.Wait[this.currentStep];
                    }
                    this.displayTextToMask(this.currentStep);
                    this.iterativeFilter(1, this.dummyFilter);
                }
            }
            if (this.image != null) {
                this.offScreenGraphics.drawImage(this.image, 0, 0, this);
            }
            this.paint(graphics);
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.offScreenImage != null) {
            graphics.drawImage(this.offScreenImage, 0, 0, this);
        }
    }
    
    private void displayTextToMask(final int n) {
        this.maskGraphics.setColor(Color.white);
        this.maskGraphics.fillRect(0, 0, this.WinWidth, this.WinHeight);
        int ascent;
        if (this.FontVJustif == 3) {
            ascent = this.theFontsMetrics.getAscent();
        }
        else if (this.FontVJustif == 4) {
            ascent = (int)Math.round(this.WinHeight - this.nbTextLines[n] * (this.theFontsMetrics.getHeight() + this.FontYspace) + this.FontYspace + this.theFontsMetrics.getAscent());
        }
        else {
            ascent = (int)Math.round(this.WinHeight / 2.0 - (this.nbTextLines[n] * (this.theFontsMetrics.getHeight() + this.FontYspace) - this.FontYspace) / 2.0 + this.theFontsMetrics.getAscent());
        }
        int n2 = 0;
        int n3 = ascent;
        boolean b = false;
        while (!b) {
            final int index = this.Text[n].indexOf("#", n2);
            String s;
            if (index != -1) {
                s = new String(this.Text[n].substring(n2, index));
            }
            else {
                b = true;
                s = new String(this.Text[n].substring(n2, this.Text[n].length()));
            }
            n2 = index + 1;
            int n4;
            if (this.FontJustif == 0) {
                n4 = 0;
            }
            else if (this.FontJustif == 2) {
                n4 = (int)Math.round(this.WinWidth - this.theFontsMetrics.stringWidth(s));
            }
            else {
                n4 = (int)Math.round(this.WinWidth / 2.0 - this.theFontsMetrics.stringWidth(s) / 2.0);
            }
            this.maskGraphics.setColor(Color.black);
            this.maskGraphics.drawString(s, n4, n3);
            n3 = n3 + this.FontYspace + this.theFontsMetrics.getHeight();
        }
        this.pg = new PixelGrabber(this.mask.getSource(), 0, 0, this.WinWidth, this.WinHeight, this.maskdata, 0, this.WinWidth);
        try {
            this.pg.grabPixels();
        }
        catch (Exception ex) {
            for (int i = 0; i < this.WinWidth; ++i) {
                for (int j = 0; j < this.WinHeight; ++j) {
                    this.maskdata[j * 256 + i] = ((j << 16) + (i << 8) + (i + j & 0xFF) | 0xFF000000);
                }
            }
        }
        for (int k = 0; k < this.maskdata.length; ++k) {
            if ((this.maskdata[k] & 0xFF) != 0x0) {
                this.maskdata[k] = 0;
            }
            else {
                this.maskdata[k] = 255;
            }
        }
    }
    
    private int getNbLines(final String s) {
        int n = 1;
        for (int i = s.indexOf("#"); i != -1; i = s.indexOf("#", i + 1), ++n) {}
        return n;
    }
    
    private int selectEffect(final String s) {
        if (s.equals("FOG&LEFT")) {
            return 0;
        }
        if (s.equals("FOG&RIGHT")) {
            return 1;
        }
        if (s.equals("WIND&RIGHT")) {
            return 2;
        }
        if (s.equals("BLUR")) {
            return 3;
        }
        if (s.equals("SHADOW&LEFT")) {
            return 4;
        }
        if (s.equals("PUZZLE")) {
            return 5;
        }
        if (s.equals("ISO")) {
            return 6;
        }
        return 7;
    }
    
    private String getFontParameters() {
        final String s = "WARN|";
        final String parameter = this.getParameter("align");
        if (parameter == null) {
            this.FontJustif = 1;
        }
        else if (parameter.toUpperCase().equals("LEFT")) {
            this.FontJustif = 0;
        }
        else if (parameter.toUpperCase().equals("RIGHT")) {
            this.FontJustif = 2;
        }
        else {
            this.FontJustif = 1;
        }
        final String parameter2 = this.getParameter("valign");
        if (parameter2 == null) {
            this.FontVJustif = 1;
        }
        else if (parameter2.toUpperCase().equals("TOP")) {
            this.FontVJustif = 3;
        }
        else if (parameter2.toUpperCase().equals("BOTTOM")) {
            this.FontVJustif = 4;
        }
        else {
            this.FontVJustif = 1;
        }
        final String parameter3 = this.getParameter("fontsize");
        if (parameter3 == null) {
            this.FontSize = 30;
        }
        else {
            try {
                this.FontSize = Math.abs(Integer.parseInt(parameter3));
            }
            catch (NumberFormatException ex) {
                return "STOP|fontsize parameter not an integer";
            }
        }
        final String parameter4 = this.getParameter("yspacing");
        if (parameter4 == null) {
            this.FontYspace = 0;
        }
        else {
            try {
                this.FontYspace = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex2) {
                return "STOP|yspacing parameter not an integer";
            }
        }
        final String parameter5 = this.getParameter("fontstyle");
        if (parameter5 == null) {
            this.FontStyle = 0;
        }
        else if (parameter5.toUpperCase().equals("BOLD")) {
            this.FontStyle = 1;
        }
        else if (parameter5.toUpperCase().equals("ITALIC")) {
            this.FontStyle = 2;
        }
        else {
            this.FontStyle = 0;
        }
        final String parameter6 = this.getParameter("fontname");
        if (parameter6 == null) {
            this.FontName = "Helvetica";
        }
        else {
            this.FontName = parameter6;
        }
        final String parameter7 = this.getParameter("fontcolor");
        if (parameter7 == null) {
            this.FontColor = new Color(16777215);
        }
        else {
            try {
                this.FontColor = new Color(Integer.parseInt(parameter7, 16));
            }
            catch (NumberFormatException ex3) {
                return "STOP|fontcolor parameter not valid";
            }
        }
        final String parameter8 = this.getParameter("bgcolor");
        if (parameter8 == null) {
            this.BgColor = new Color(0);
        }
        else {
            try {
                this.BgColor = new Color(Integer.parseInt(parameter8, 16));
            }
            catch (NumberFormatException ex4) {
                return "STOP|bgcolor parameter not valid";
            }
        }
        return s;
    }
    
    private boolean iterativeFilter(final int n, final float[][] array) {
        boolean b = true;
        final int rgb = this.BgColor.getRGB();
        final int rgb2 = this.CurrentColor.getRGB();
        final int red = this.BgColor.getRed();
        final int green = this.BgColor.getGreen();
        final int blue = this.BgColor.getBlue();
        final int red2 = this.CurrentColor.getRed();
        final int green2 = this.CurrentColor.getGreen();
        final int blue2 = this.CurrentColor.getBlue();
        for (int i = 0; i < n; ++i) {
            b = true;
            this.fastMaskConv2D(3, this.WinWidth, this.WinHeight, array);
            for (int j = 0; j < this.pixeldata.length; ++j) {
                final double n2 = this.maskdata[j] / 255.0;
                if (n2 == 0.0) {
                    this.pixeldata[j] = (rgb | 0xFF000000);
                }
                else if (n2 == 1.0) {
                    b = false;
                    this.pixeldata[j] = (rgb2 | 0xFF000000);
                }
                else {
                    b = false;
                    this.pixeldata[j] = (0xFF000000 | (int)(n2 * red2 + (1.0 - n2) * red) << 16 | (int)(n2 * green2 + (1.0 - n2) * green) << 8 | (int)(n2 * blue2 + (1.0 - n2) * blue));
                }
            }
        }
        return b;
    }
    
    public void fastMaskConv2D(final int n, final int n2, final int n3, final float[][] array) {
        int i;
        for (int n4 = i = n / 2; i < n3 - n4; ++i) {
            for (int j = n4; j < n2 - n4; ++j) {
                float n5 = 0.0f;
                for (int k = -n4; k <= n4; ++k) {
                    for (int l = -n4; l <= n4; ++l) {
                        n5 += this.maskdata[(i + l) * n2 + j + k] * array[k + n4][l + n4];
                    }
                }
                final int n6 = i * n2 + j;
                if (n5 > 255.0f) {
                    this.maskdata[n6] = 255;
                }
                else if (n5 < 0.0f) {
                    this.maskdata[n6] = 0;
                }
                else {
                    this.maskdata[n6] = (int)n5;
                }
            }
        }
    }
    
    public announceit() {
        this.dummyFilter = new float[][] { new float[3], { 0.0f, 1.0f, 0.0f }, new float[3] };
        this.toLeftFilter = new float[][] { { 0.0f, 0.19f, 0.0f }, new float[3], { 0.0f, 0.75f, 0.0f } };
        this.toRightFilter = new float[][] { { 0.0f, 0.75f, 0.0f }, new float[3], { 0.0f, 0.19f, 0.0f } };
        this.toRightLongFilter = new float[][] { { 0.0f, 0.89f, 0.0f }, { 0.0f, 0.6f, 0.0f }, { 0.0f, -0.5f, 0.0f } };
        this.blurFilter = new float[][] { { 0.0f, 0.49f, 0.0f }, new float[3], { 0.0f, 0.49f, 0.0f } };
        this.toLeftShadowFilter = new float[][] { { 0.0f, 0.99f, 0.0f }, { 0.0f, -1.1f, 0.0f }, { 0.0f, 0.99f, 0.0f } };
        this.puzzleFilter = new float[][] { { 0.2f, -0.1f, 0.2f }, { -0.1f, 0.39f, -0.1f }, { 0.2f, -0.1f, 0.2f } };
        this.isospreadFilter = new float[][] { { 0.01f, 0.1f, 0.01f }, { 0.1f, 1.0f, 0.1f }, { 0.01f, 0.1f, 0.01f } };
        this.toBottomBloodFilter = new float[][] { new float[3], { 0.89f, 0.6f, -0.45f }, new float[3] };
        this.allFades = new float[][][] { this.toLeftFilter, this.toRightFilter, this.toRightLongFilter, this.blurFilter, this.toLeftShadowFilter, this.puzzleFilter, this.isospreadFilter, this.toBottomBloodFilter };
        this.TimeOut = 80;
        this.isVanished = true;
    }
}
