import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lake extends Applet implements Runnable
{
    Thread m_Lake;
    private Graphics m_Graphics;
    private Graphics m_WaveGraphics;
    private Image m_Image;
    private Image m_Overlay;
    private Image m_WaveImage;
    private int m_nCurrImage;
    private int m_nImgWidth;
    private int m_nImgHeight;
    private int m_nOvlWidth;
    private int m_nOvlHeight;
    private boolean m_fAllLoaded;
    private boolean m_tAnimate;
    private final int NUM_FRAMES = 12;
    private String m_ImageName;
    private String m_OverlayName;
    private URL m_HRef;
    private String m_Frame;
    private final String PARAM_image = "image";
    private final String PARAM_overlay = "overlay";
    private final String PARAM_href = "href";
    private final String PARAM_target = "target";
    
    public void createAnimation() {
        final Image image = this.createImage(this.m_nImgWidth, this.m_nImgHeight + 1);
        final Graphics graphics = image.getGraphics();
        graphics.drawImage(this.m_Image, 0, 1, this);
        for (int i = 0; i < this.m_nImgHeight >> 1; ++i) {
            graphics.copyArea(0, i, this.m_nImgWidth, 1, 0, this.m_nImgHeight - i);
            graphics.copyArea(0, this.m_nImgHeight - 1 - i, this.m_nImgWidth, 1, 0, -this.m_nImgHeight + 1 + (i << 1));
            graphics.copyArea(0, this.m_nImgHeight, this.m_nImgWidth, 1, 0, -1 - i);
        }
        this.m_WaveImage = this.createImage(13 * this.m_nImgWidth, this.m_nImgHeight);
        (this.m_WaveGraphics = this.m_WaveImage.getGraphics()).drawImage(image, 12 * this.m_nImgWidth, 0, this);
        int n = 0;
        do {
            this.makeWaves(this.m_WaveGraphics, n);
        } while (++n < 12);
        graphics.drawImage(this.m_Image, 0, 1, this);
        if (!"".equals(this.m_OverlayName)) {
            graphics.drawImage(this.m_Overlay, this.m_nImgWidth - this.m_nOvlWidth >> 1, this.m_nImgHeight - (this.m_nOvlHeight >> 1), this);
        }
        this.m_Image = image;
    }
    
    public void start() {
        if (this.m_Lake == null) {
            (this.m_Lake = new Thread(this)).start();
        }
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "image", "String", "JPG or GIF file to reflect" }, { "overlay", "String", "JPG or GIF file to use as overlay" }, { "href", "URL", "URL to link to" }, { "target", "String", "Target frame" } };
    }
    
    public void stop() {
        if (this.m_Lake != null) {
            this.m_Lake.stop();
            this.m_Lake = null;
        }
    }
    
    private void displayImage(final Graphics graphics) {
        if (!this.m_fAllLoaded) {
            return;
        }
        if (this.m_WaveImage != null) {
            graphics.drawImage(this.m_WaveImage, -this.m_nCurrImage * this.m_nImgWidth, this.m_nImgHeight, this);
            graphics.drawImage(this.m_WaveImage, (12 - this.m_nCurrImage) * this.m_nImgWidth, this.m_nImgHeight, this);
        }
        graphics.drawImage(this.m_Image, 0, -1, this);
    }
    
    public String getAppletInfo() {
        return "Name: Lake v3.0\r\n" + "Author: David Griffiths\r\n" + "Created with Microsoft Visual J++ Version 1.0";
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        super.mouseUp(event, n, n2);
        if (this.m_HRef == null) {
            this.m_tAnimate = !this.m_tAnimate;
        }
        else {
            this.showStatus("" + this.m_HRef);
            this.getAppletContext().showDocument(this.m_HRef, this.m_Frame);
        }
        return true;
    }
    
    public void run() {
        this.m_nCurrImage = 0;
        if (!this.m_fAllLoaded) {
            this.repaint();
            this.m_Graphics = this.getGraphics();
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.m_Image = this.getImage(this.getDocumentBase(), this.m_ImageName);
            if (!"".equals(this.m_OverlayName)) {
                this.m_Overlay = this.getImage(this.getDocumentBase(), this.m_OverlayName);
            }
            mediaTracker.addImage(this.m_Image, 0);
            if (!"".equals(this.m_OverlayName)) {
                mediaTracker.addImage(this.m_Overlay, 1);
            }
            try {
                mediaTracker.waitForAll();
                this.m_fAllLoaded = !mediaTracker.isErrorAny();
            }
            catch (InterruptedException ex) {}
            if (!this.m_fAllLoaded) {
                this.stop();
                this.m_Graphics.drawString("Error loading images!", 10, 40);
                return;
            }
            this.m_nImgWidth = this.m_Image.getWidth(this);
            this.m_nImgHeight = this.m_Image.getHeight(this);
            if (!"".equals(this.m_OverlayName)) {
                this.m_nOvlWidth = this.m_Overlay.getWidth(this);
                this.m_nOvlHeight = this.m_Overlay.getHeight(this);
            }
            this.createAnimation();
        }
        this.repaint();
    Label_0224_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        if (this.m_tAnimate) {
                            this.displayImage(this.m_Graphics);
                            if (++this.m_nCurrImage == 12) {
                                this.m_nCurrImage = 0;
                            }
                            Thread.sleep(50L);
                        }
                        else {
                            Thread.sleep(500L);
                        }
                    }
                }
                catch (InterruptedException ex2) {
                    this.stop();
                    continue Label_0224_Outer;
                }
                continue;
            }
        }
    }
    
    public void makeWaves(final Graphics graphics, final int n) {
        final double n2 = 6.283185307179586 * n / 12.0;
        final int n3 = (12 - n) * this.m_nImgWidth;
        for (int i = 0; i < this.m_nImgHeight; ++i) {
            final int n4 = (int)(this.m_nImgHeight / 14 * (i + 28.0) * Math.sin(this.m_nImgHeight / 14 * (this.m_nImgHeight - i) / (i + 1) + n2) / this.m_nImgHeight);
            if (i < -n4) {
                graphics.copyArea(12 * this.m_nImgWidth, i, this.m_nImgWidth, 1, -n3, 0);
            }
            else {
                graphics.copyArea(12 * this.m_nImgWidth, i + n4, this.m_nImgWidth, 1, -n3, -n4);
            }
        }
        if (!"".equals(this.m_OverlayName)) {
            graphics.drawImage(this.m_Overlay, n * this.m_nImgWidth + (this.m_nImgWidth - this.m_nOvlWidth >> 1), -this.m_nOvlHeight >> 1, this);
        }
    }
    
    public Lake() {
        this.m_tAnimate = true;
        this.m_ImageName = "";
        this.m_OverlayName = "";
        this.m_Frame = "_self";
    }
    
    public void destroy() {
    }
    
    public void init() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            this.m_ImageName = parameter;
        }
        final String parameter2 = this.getParameter("overlay");
        if (parameter2 != null) {
            this.m_OverlayName = parameter2;
        }
        final String parameter3 = this.getParameter("href");
        if (parameter3 != null) {
            try {
                this.m_HRef = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex) {
                this.getAppletContext().showStatus("Bad URL: " + parameter3);
                return;
            }
        }
        final String parameter4 = this.getParameter("target");
        if (parameter4 != null) {
            this.m_Frame = parameter4;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_fAllLoaded) {
            this.displayImage(graphics);
            return;
        }
        graphics.drawString("Loading images...", 10, 20);
    }
}
