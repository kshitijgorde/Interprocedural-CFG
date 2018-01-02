import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fire extends Applet implements Runnable
{
    boolean first;
    int ROWS;
    int COLS;
    int HIDDEN;
    int ROWS_SEED;
    int ROWS_RESEED;
    int MAX_SEED;
    int PALETTE_SIZE;
    int COOLING_LIMIT;
    int COOLING_ROWS;
    int COOLING_FACTOR;
    Color[] palette;
    byte[] Buffer;
    byte[] Buffer2;
    String message;
    String textfont;
    int textsize;
    int textX;
    int textY;
    Color textcolor;
    Image offScrImage;
    Graphics offScrGC;
    Dimension offScrSize;
    Thread kicker;
    
    public String getAppletInfo() {
        return "Fire applet by Javier Rodriguez <jrodrig@data.net.mx>";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "coolingrows", "int", "number of rows to cool" }, { "coolingfactor", "int", "cooling factor" }, { "coolinglimit", "int", "cooling threshold" }, { "soundtrack", "url", "background sound" }, { "text", "String", "message" }, { "textcolor", "String", "text color" }, { "textfont", "String", "text font" }, { "textsize", "int", "text size" } };
    }
    
    public void init() {
        this.COLS = this.size().width;
        this.ROWS = this.size().height + this.HIDDEN;
        String s = this.getParameter("coolinglimit");
        if (s != null && s.endsWith("%")) {
            s = s.substring(0, s.length() - 1);
        }
        this.COOLING_LIMIT = ((s == null) ? ((int)(this.PALETTE_SIZE * 0.5)) : (this.PALETTE_SIZE * Integer.valueOf(s) / 100));
        String s2 = this.getParameter("coolingrows");
        if (s2 != null && s2.endsWith("%")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        this.COOLING_ROWS = ((s2 == null) ? ((int)(this.ROWS * 0.8)) : (this.ROWS * Integer.valueOf(s2) / 100));
        final String parameter = this.getParameter("coolingfactor");
        this.COOLING_FACTOR = ((parameter == null) ? 2 : Integer.valueOf(parameter));
        this.ROWS_RESEED = (int)(this.ROWS * 0.96);
        final String parameter2 = this.getParameter("text");
        this.message = ((parameter2 == null) ? "" : parameter2);
        final String parameter3 = this.getParameter("textfont");
        this.textfont = ((parameter3 == null) ? "TimesRoman" : parameter3);
        final String parameter4 = this.getParameter("textsize");
        this.textsize = ((parameter4 == null) ? 18 : Integer.valueOf(parameter4));
        this.textcolor = this.hexColor(this.getParameter("textcolor"), Color.white);
        this.Buffer = new byte[this.COLS * this.ROWS];
        this.Buffer2 = new byte[this.COLS * this.ROWS];
        for (int i = 0; i < 16; ++i) {
            this.palette[i] = new Color(16 * i, 0, 0);
        }
        for (int j = 0; j < 16; ++j) {
            this.palette[16 + j] = new Color(255, 16 * j, 0);
        }
        for (int k = 0; k < 32; ++k) {
            this.palette[32 + k] = new Color(255, 255, 8 * k);
        }
        final Font font = new Font(this.textfont, 1, this.textsize);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final int height = fontMetrics.getHeight();
        this.textX = (this.COLS - fontMetrics.stringWidth(this.message)) / 2;
        this.textY = this.ROWS - this.HIDDEN - (this.ROWS - this.HIDDEN - height) / 2 - fontMetrics.getDescent();
        this.setFont(font);
        for (int l = this.COLS * (this.ROWS - this.ROWS_SEED); l < this.ROWS * this.COLS; ++l) {
            this.Buffer[l] = (byte)(Math.random() * (this.PALETTE_SIZE - 1));
        }
    }
    
    void MainLoop() {
        for (int i = this.COLS + 1; i < this.COLS * (this.ROWS - 1) - 1; ++i) {
            int n = this.Buffer[i - this.COLS - 1] + this.Buffer[i - this.COLS] + this.Buffer[i - this.COLS + 1] + this.Buffer[i - 1] + this.Buffer[i + 1] + this.Buffer[i + this.COLS - 1] + this.Buffer[i + this.COLS] + this.Buffer[i + this.COLS + 1] >> 3;
            if (n < this.COOLING_LIMIT && i < this.COOLING_ROWS * this.COLS && n > this.COOLING_FACTOR) {
                n -= this.COOLING_FACTOR;
            }
            this.Buffer2[i] = (byte)n;
        }
        for (int j = this.COLS * this.ROWS_RESEED; j < this.COLS * this.ROWS; ++j) {
            this.Buffer2[j] = (byte)((this.Buffer2[j] - Math.random() * this.MAX_SEED) % (this.PALETTE_SIZE * 1.1));
        }
        for (int k = 0; k < this.COLS * (this.ROWS - 1); ++k) {
            this.Buffer[k] = this.Buffer2[k + this.COLS];
        }
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offScrImage == null || size.width != this.offScrSize.width || size.height != this.offScrSize.height) {
            this.offScrImage = this.createImage(size.width, size.height);
            this.offScrSize = size;
            (this.offScrGC = this.offScrImage.getGraphics()).setFont(this.getFont());
        }
        if (this.offScrGC != null) {
            this.offScrGC.fillRect(0, 0, size.width, size.height);
            this.paint(this.offScrGC);
            graphics.drawImage(this.offScrImage, 0, 0, null);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.MainLoop();
        for (int i = 0; i < this.ROWS - this.HIDDEN; ++i) {
            for (int j = 0; j < this.COLS; ++j) {
                final byte b = this.Buffer[i * this.COLS + j];
                final byte b2 = (byte)((b < 0) ? (-b) : b);
                final Color color = this.palette[(b2 < this.PALETTE_SIZE - 1) ? b2 : (this.PALETTE_SIZE - 1)];
                try {
                    this.offScrGC.setColor(color);
                    this.offScrGC.drawLine(j, i, j + 1, i);
                }
                catch (Exception ex) {}
            }
        }
        try {
            this.offScrGC.setColor(this.textcolor);
            this.offScrGC.drawString(this.message, this.textX, this.textY);
            graphics.drawImage(this.offScrImage, 0, 0, this);
        }
        catch (Exception ex2) {}
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.kicker = null;
    }
    
    public void run() {
        while (this.kicker != null) {
            this.repaint();
            try {
                Thread.sleep(15L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int n3 = n + n2 * this.COLS;
        if (n3 > 81) {
            this.Buffer[n3] = -1;
            this.Buffer[n3 - this.COLS] = -1;
            this.Buffer[n3 + this.COLS] = -1;
            this.Buffer[n3 - 1] = -1;
            this.Buffer[n3 + 1] = -1;
        }
        return true;
    }
    
    public Color hexColor(final String s, final Color color) {
        try {
            final Integer n = new Integer(0);
            s.replace('#', ' ');
            s.trim();
            return new Color(Integer.valueOf(s, 16));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public fire() {
        this.first = true;
        this.ROWS = 50;
        this.COLS = 64;
        this.HIDDEN = 4;
        this.ROWS_SEED = 4;
        this.ROWS_RESEED = 48;
        this.MAX_SEED = 8;
        this.PALETTE_SIZE = 64;
        this.COOLING_LIMIT = 32;
        this.COOLING_ROWS = 42;
        this.COOLING_FACTOR = 2;
        this.palette = new Color[this.PALETTE_SIZE];
    }
}
