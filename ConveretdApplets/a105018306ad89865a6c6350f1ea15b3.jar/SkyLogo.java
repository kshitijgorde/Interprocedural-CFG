import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SkyLogo extends Applet implements Runnable
{
    private String paramStr;
    private String textStr;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private int sleepTime;
    private int strlen;
    private Thread runner;
    private Color bgColor;
    private char[] charStr;
    private int[] charOffsets;
    private Color[] colors;
    private int yOffset;
    private int phase;
    private Image bgImage;
    private Image offScreenImage;
    private Graphics offScreenGC;
    private Image virtualImage;
    private Graphics virtualGC;
    private Font f;
    private FontMetrics fm;
    private boolean stopped;
    private MediaTracker tracker;
    private int mx;
    private int my;
    private int X;
    private int Y;
    
    public void init() {
        this.mx = this.size().width;
        this.my = this.size().height;
        this.virtualImage = this.createImage(this.mx * 3, this.my * 3);
        this.virtualGC = this.virtualImage.getGraphics();
        this.offScreenImage = this.createImage(this.mx, this.my);
        this.offScreenGC = this.offScreenImage.getGraphics();
        this.paramStr = this.getParameter("bgcolor");
        if (this.paramStr == null) {
            this.bgColor = Color.blue;
        }
        else {
            try {
                this.bgColor = new Color(Integer.parseInt(this.paramStr, 16));
            }
            catch (NumberFormatException ex) {
                this.bgColor = Color.blue;
            }
        }
        this.setBackground(this.bgColor);
        this.tracker = new MediaTracker(this);
        this.paramStr = this.getParameter("bgimage");
        if (this.paramStr != null) {
            this.bgImage = this.getImage(this.getDocumentBase(), this.paramStr);
            this.tracker.addImage(this.bgImage, 0);
        }
        this.textStr = this.getParameter("text");
        if (this.textStr == null) {
            this.textStr = "Colorful Logo";
        }
        this.fontName = this.getParameter("fontname");
        if (this.fontName == null) {
            this.fontName = "TimesRoman";
        }
        this.paramStr = this.getParameter("fontstyle");
        if (this.paramStr == null) {
            this.fontStyle = 0;
        }
        else if (this.paramStr.equals("B")) {
            this.fontStyle = 1;
        }
        else if (this.paramStr.equals("I")) {
            this.fontStyle = 2;
        }
        else if (this.paramStr.equals("BI")) {
            this.fontStyle = 3;
        }
        else {
            this.fontStyle = 0;
        }
        this.paramStr = this.getParameter("fontsize");
        if (this.paramStr == null) {
            this.fontSize = 36;
        }
        else {
            try {
                this.fontSize = Integer.parseInt(this.paramStr);
            }
            catch (Exception ex2) {
                this.fontSize = 36;
            }
        }
        this.paramStr = this.getParameter("sleeptime");
        if (this.paramStr == null) {
            this.sleepTime = 100;
        }
        else {
            try {
                this.sleepTime = Integer.parseInt(this.paramStr);
            }
            catch (Exception ex3) {
                this.sleepTime = 100;
            }
        }
        this.f = new Font(this.fontName, this.fontStyle, this.fontSize);
        this.fm = this.getFontMetrics(this.f);
        this.yOffset = this.fm.getAscent() + (this.my - (this.fm.getAscent() + this.fm.getDescent())) / 2;
        this.strlen = this.textStr.length();
        this.charStr = new char[this.strlen];
        this.charOffsets = new int[this.strlen];
        this.textStr.getChars(0, this.strlen, this.charStr, 0);
        this.colors = new Color[this.strlen];
        int n = 0;
        for (int i = 0; i < this.strlen; ++i) {
            this.colors[i] = new Color(Color.HSBtoRGB(i / this.strlen, 1.0f, 1.0f));
            this.charOffsets[i] = n;
            n += this.fm.charWidth(this.charStr[i]);
        }
        final int n2 = (this.mx - n) / 2;
        for (int j = 0; j < this.strlen; ++j) {
            final int[] charOffsets = this.charOffsets;
            final int n3 = j;
            charOffsets[n3] += n2;
        }
        this.offScreenGC.setColor(this.bgColor);
        this.offScreenGC.fillRect(0, 0, this.mx, this.my);
        this.offScreenGC.setFont(this.f);
        final boolean b = false;
        this.Y = (b ? 1 : 0);
        this.X = (b ? 1 : 0);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void run() {
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return;
        }
        Thread.currentThread().setPriority(1);
        int n = 0;
        int n2 = 0;
        final int width = this.bgImage.getWidth(this);
        final int height = this.bgImage.getHeight(this);
        if (width > 0) {
            n = this.mx / width;
            n2 = this.my / height;
            for (int i = 0; i < this.virtualImage.getHeight(this); i += height) {
                for (int j = 0; j < this.virtualImage.getWidth(this); j += width) {
                    this.virtualGC.drawImage(this.bgImage, j, i, this);
                }
            }
        }
        else {
            this.bgImage = null;
        }
        while (this.runner != null) {
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex2) {}
            if (this.bgImage != null) {
                final int x = this.X - 1;
                this.X = x;
                if (-x > ((n == 0) ? this.mx : (n * width))) {
                    this.X = 0;
                }
                final int y = this.Y - 1;
                this.Y = y;
                if (-y > ((n2 == 0) ? this.my : (n2 * height))) {
                    this.Y = 0;
                }
            }
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        --this.phase;
        if (this.phase < 0) {
            this.phase = this.strlen - 1;
        }
        if (this.bgImage != null) {
            this.offScreenGC.drawImage(this.virtualImage, this.X, this.Y, this);
        }
        else {
            this.offScreenGC.setColor(this.bgColor);
            this.offScreenGC.fillRect(0, 0, this.mx, this.my);
        }
        for (int i = 0; i < this.strlen; ++i) {
            final int n = this.charOffsets[i];
            this.offScreenGC.setColor(this.colors[(this.phase + i) % this.strlen]);
            this.offScreenGC.drawChars(this.charStr, i, 1, n, this.yOffset);
        }
        graphics.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public SkyLogo() {
        this.stopped = false;
    }
}
