import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class animatedIntroClass extends Applet implements Runnable
{
    Thread mee;
    public Graphics offScreenGC;
    public Image offScreen;
    public Graphics onScreenGC;
    Font titleFont;
    String gameTitle;
    Image tePic;
    int titleX;
    int titleY;
    Font authorFont;
    int versionX;
    int versionY;
    int byX;
    int byY;
    int authorX;
    int authorY;
    Font statusFont;
    int statusX1;
    int statusY;
    int statusX2;
    String flyer;
    int maxX;
    int maxY;
    int imgH;
    int imgW;
    int radius;
    int barSegmentW;
    int numQmarks;
    int[] qMarkX;
    int[] qMarkY;
    int[] qMarkXinc;
    int[] qMarkYinc;
    int[] qMarkCnt;
    int[] qMarkSteps;
    Color[] qMarkColor;
    Color loadingBar;
    Color[] textPalette;
    boolean fadeOut;
    boolean fadeIn;
    int fadeIndex;
    
    animatedIntroClass() {
        this.mee = null;
        this.numQmarks = 50;
        this.loadingBar = new Color(65, 100, 65);
        this.textPalette = new Color[6];
        this.fadeOut = false;
        this.fadeIn = false;
    }
    
    public void stop() {
        this.mee = null;
    }
    
    public void run() {
        this.repaint();
    }
    
    public void pause(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void start() {
        if (this.mee == null) {
            this.mee = new Thread(this);
        }
        for (int i = 0; i < this.numQmarks; ++i) {
            final int n = (int)(Math.random() * 360.0);
            final int n2 = (int)(Math.random() * 30.0) + 20;
            this.qMarkSteps[i] = n2;
            this.qMarkColor[i] = new Color((int)(Math.random() * 200.0), (int)(Math.random() * 200.0), (int)(Math.random() * 200.0));
            this.qMarkXinc[i] = (int)(Math.cos(n) * this.radius / n2);
            this.qMarkYinc[i] = (int)(Math.sin(n) * this.radius / n2);
            this.qMarkCnt[i] = 0 - i / 2;
            this.qMarkX[i] = this.maxX * 8 + 2 + this.qMarkXinc[i];
            this.qMarkY[i] = this.maxY * 8 + 2 + this.qMarkXinc[i];
        }
    }
    
    void updateBar(final Image offScreen) {
        this.fadeIn = true;
        this.fadeOut = false;
        this.fadeIndex = 0;
        this.offScreen = offScreen;
    }
    
    void setInfo(final Image offScreen, final int maxX, final int maxY, final int imgH, final int imgW, final String gameTitle, final int numQmarks, final Image tePic) {
        this.offScreen = offScreen;
        this.offScreenGC = this.offScreen.getGraphics();
        this.tePic = tePic;
        this.maxX = maxX;
        this.maxY = maxY;
        this.imgH = imgH;
        this.imgW = imgW;
        this.gameTitle = gameTitle;
        this.numQmarks = numQmarks;
        this.qMarkX = new int[this.numQmarks];
        this.qMarkY = new int[this.numQmarks];
        this.qMarkXinc = new int[this.numQmarks];
        this.qMarkYinc = new int[this.numQmarks];
        this.qMarkCnt = new int[this.numQmarks];
        this.qMarkSteps = new int[this.numQmarks];
        this.qMarkColor = new Color[this.numQmarks];
        this.barSegmentW = this.maxX * this.imgW / 24;
        final int n = imgH / 2;
        final int n2 = imgW / 2;
        this.radius = (int)Math.sqrt(this.maxX * n2 * this.maxX * n2 + this.maxY * n * this.maxY * n) + this.imgW;
        this.fadeIn = true;
        this.fadeOut = false;
        this.fadeIndex = 0;
        for (int i = 0; i < 6; ++i) {
            this.textPalette[i] = new Color(51 * i, 51 * i, 51 * i);
        }
        final int n3 = this.maxX * this.imgW / 2;
        final int n4 = this.maxY * this.imgH;
        this.titleFont = new Font("Helvetica", 0, 36);
        this.offScreenGC.setFont(this.titleFont);
        this.titleX = n3 - this.offScreenGC.getFontMetrics().stringWidth(this.gameTitle) / 2;
        this.titleY = (int)(n4 * 0.3);
        this.authorFont = new Font("Helvetica", 0, 18);
        this.offScreenGC.setFont(this.authorFont);
        this.versionX = n3 - this.offScreenGC.getFontMetrics().stringWidth("v1.0") / 2;
        this.versionY = (int)(n4 * 0.4);
        this.byX = n3 - this.offScreenGC.getFontMetrics().stringWidth("by") / 2;
        this.byY = (int)(n4 * 0.55);
        this.authorX = n3 - this.offScreenGC.getFontMetrics().stringWidth("Paul Olmstead") / 2;
        this.authorY = this.byY + 21;
        this.statusFont = new Font("Helvetica", 0, 12);
        this.offScreenGC.setFont(this.statusFont);
        this.statusX1 = n3 - this.offScreenGC.getFontMetrics().stringWidth("(please wait, loading data)") / 2;
        this.statusX2 = n3 - this.offScreenGC.getFontMetrics().stringWidth("(Click here to begin)") / 2;
        this.statusY = (int)(n4 * 0.8);
    }
    
    void startFadeIn() {
        this.fadeIn = true;
    }
    
    void runIntro(final Graphics onScreenGC) {
        this.onScreenGC = onScreenGC;
    }
    
    public void drawIntroScreen(final Graphics onScreenGC, final boolean b, final int n) {
        this.onScreenGC = onScreenGC;
        this.offScreenGC.setColor(Color.black);
        this.offScreenGC.fillRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH + 1 + 16);
        if (b) {
            this.offScreenGC.clipRect(0, 0, this.maxX * this.imgW, this.maxY * this.imgH + 1 + 16);
        }
        this.offScreenGC.setFont(new Font("Helvetica", 0, 10));
        for (int i = 0; i < this.numQmarks; ++i) {
            if (this.qMarkCnt[i] < 1) {
                this.qMarkCnt[i] = this.qMarkSteps[i];
                this.qMarkX[i] = this.maxX * this.imgW / 2 + 2 + this.qMarkXinc[i];
                this.qMarkY[i] = this.maxY * this.imgH / 2 + 2 + this.qMarkXinc[i];
            }
            this.offScreenGC.setColor(this.qMarkColor[i]);
            if (this.qMarkY[i] > 310) {
                this.qMarkCnt[i] = 0;
            }
            this.offScreenGC.fillRect(this.qMarkX[i], this.qMarkY[i], 6, 6);
            final int[] qMarkX = this.qMarkX;
            final int n2 = i;
            qMarkX[n2] += this.qMarkXinc[i];
            final int[] qMarkY = this.qMarkY;
            final int n3 = i;
            qMarkY[n3] += this.qMarkYinc[i];
            final int[] qMarkCnt = this.qMarkCnt;
            final int n4 = i;
            --qMarkCnt[n4];
        }
        this.offScreenGC.setColor(this.textPalette[this.fadeIndex]);
        if (this.fadeIn) {
            ++this.fadeIndex;
            if (this.fadeIndex == 5) {
                this.fadeIn = false;
            }
        }
        this.offScreenGC.setFont(this.titleFont);
        this.offScreenGC.drawString(this.gameTitle, this.titleX, this.titleY);
        this.offScreenGC.setFont(this.authorFont);
        this.offScreenGC.drawString("v1.0", this.versionX, this.versionY);
        this.offScreenGC.drawString("by", this.byX, this.byY);
        this.offScreenGC.drawString("Paul Olmstead", this.authorX, this.authorY);
        this.offScreenGC.setFont(this.statusFont);
        if (!b) {
            this.offScreenGC.drawString("(please wait, loading data)", this.statusX1, this.statusY);
            this.offScreenGC.setColor(Color.darkGray);
            this.offScreenGC.drawLine(0, this.maxY * this.imgH + 1, this.maxX * this.imgW, this.maxY * this.imgH + 1);
            this.offScreenGC.drawLine(0, this.maxY * this.imgH + 3, this.maxX * this.imgW, this.maxY * this.imgH + 3);
            this.offScreenGC.setColor(Color.lightGray);
            this.offScreenGC.drawLine(0, this.maxY * this.imgH + 2, this.maxX * this.imgW, this.maxY * this.imgH + 2);
            this.offScreenGC.setColor(this.loadingBar);
            this.offScreenGC.fillRect(0, this.maxY * this.imgH + 4, this.maxX * this.imgW, 21);
            this.offScreenGC.setColor(Color.black);
            this.offScreenGC.fillRect(0, this.maxY * this.imgH + 4, n * this.barSegmentW, 21);
        }
        else {
            this.offScreenGC.drawString("(Click here to begin)", this.statusX2, this.statusY);
            this.offScreenGC.drawImage(this.tePic, 0, 322, this);
        }
        this.onScreenGC.drawImage(this.offScreen, 0, 0, null);
        this.pause(50L);
    }
}
