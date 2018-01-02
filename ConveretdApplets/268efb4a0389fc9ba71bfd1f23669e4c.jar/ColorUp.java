import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.FilteredImageSource;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorUp extends Applet implements Runnable
{
    Image orig_img;
    Image imgBuffer;
    Image[] new_img;
    ImageFilter[] filter;
    ImageProducer src;
    ImageProducer producer;
    boolean effect;
    boolean fillColor;
    int timeout;
    int lum;
    int step;
    URL link;
    Thread appearimg;
    int mEnter;
    int mExit;
    int mDown;
    boolean indexColor;
    
    public void init() {
        System.out.println("Applet from www.vivaorange.com");
        String at;
        if ((at = this.getParameter("image")) != null) {
            try {
                this.indexColor = !at.toLowerCase().endsWith("jpg");
                this.orig_img = (at.toLowerCase().startsWith("http") ? this.getImage(new URL(at)) : this.getImage(this.getDocumentBase(), at));
            }
            catch (MalformedURLException e) {
                this.showStatus("Fail to load image");
                System.exit(1);
            }
        }
        else {
            this.showStatus("Fail to load image");
            System.exit(1);
        }
        try {
            if ((at = this.getParameter("link")) != null) {
                this.link = (at.toLowerCase().startsWith("http") ? new URL(at) : new URL(this.getDocumentBase(), at));
            }
            else {
                this.link = this.getDocumentBase();
            }
        }
        catch (MalformedURLException e) {
            this.showStatus("Fail to load image");
            System.exit(1);
        }
        at = this.getParameter("mouseover");
        this.mEnter = ((at == null) ? 0 : Integer.valueOf(at));
        at = this.getParameter("mouseout");
        this.mExit = ((at == null) ? 0 : Integer.valueOf(at));
        at = this.getParameter("mouseclick");
        this.mDown = ((at == null) ? 0 : Integer.valueOf(at));
        at = this.getParameter("step");
        this.step = ((at == null) ? 5 : Integer.valueOf(at));
        at = this.getParameter("init");
        this.lum = ((at == null) ? 0 : Integer.valueOf(at));
        if (this.lum > 0) {
            this.lum = this.step;
        }
        at = this.getParameter("speed");
        this.timeout = 1000 / ((at == null) ? 5 : Integer.valueOf(at));
        if (this.mExit != 0 || this.mEnter != 0 || this.mDown != 0) {
            Object theFrame;
            for (theFrame = null, theFrame = this.getParent(); !(theFrame instanceof Frame) && theFrame != null; theFrame = ((Component)theFrame).getParent()) {}
            if (theFrame != null) {
                ((Frame)theFrame).setCursor(12);
            }
        }
        final MediaTracker MTracker = new MediaTracker(this);
        this.src = this.orig_img.getSource();
        for (int i = 0; i <= this.step; ++i) {
            this.filter[i] = new colorScale(i, this.step, false);
            MTracker.addImage(this.new_img[i] = this.createImage(new FilteredImageSource(this.src, this.filter[i])), 1);
        }
        try {
            MTracker.waitForID(1);
        }
        catch (InterruptedException IE) {
            System.out.println("Cannot load Image");
        }
        this.imgBuffer = this.createImage(this.size().width, this.size().height);
    }
    
    public void start() {
        if (this.appearimg == null) {
            (this.appearimg = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.appearimg != null) {
            this.appearimg.stop();
            this.appearimg = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(this.timeout);
            }
            catch (InterruptedException ex) {}
            if (this.effect) {
                if (this.fillColor) {
                    if (this.lum >= this.step) {
                        this.effect = false;
                    }
                    else {
                        ++this.lum;
                    }
                }
                if (!this.fillColor) {
                    if (this.lum <= 0) {
                        this.effect = false;
                    }
                    else {
                        --this.lum;
                    }
                }
            }
            this.repaint();
        }
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        if (this.mEnter == 1) {
            this.effect = true;
            this.fillColor = true;
        }
        if (this.mEnter == 2) {
            this.effect = true;
            this.fillColor = false;
        }
        return true;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        if (this.mExit == 1) {
            this.effect = true;
            this.fillColor = true;
        }
        if (this.mExit == 2) {
            this.effect = true;
            this.fillColor = false;
        }
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        System.out.println("here");
        if (this.mDown == 1) {
            this.effect = true;
            this.fillColor = true;
        }
        else if (this.mDown == 2) {
            this.effect = true;
            this.fillColor = false;
        }
        else if (this.mDown == 3) {
            this.effect = true;
            this.fillColor = !this.fillColor;
        }
        else if (this.mDown == 4) {
            this.getAppletContext().showDocument(this.link, "_self");
        }
        return true;
    }
    
    public int ColorStatus() {
        if (this.fillColor) {
            return 1;
        }
        return 0;
    }
    
    public void ColorOn() {
        this.effect = true;
        this.fillColor = true;
    }
    
    public void ColorOff() {
        this.effect = true;
        this.fillColor = false;
    }
    
    public void update(final Graphics g) {
        final Graphics gg = this.imgBuffer.getGraphics();
        gg.drawImage(this.new_img[this.lum], 0, 0, this.size().width, this.size().height, this);
        gg.setColor(Color.blue);
        gg.setFont(new Font("TimesRoman", 2, 14));
        gg.drawString("www.vivaorange.com", this.size().width - 130, this.size().height - 9);
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.imgBuffer, 0, 0, this);
    }
    
    public ColorUp() {
        this.new_img = new Image[51];
        this.filter = new ImageFilter[51];
        this.effect = false;
        this.fillColor = true;
        this.mEnter = 0;
        this.mExit = 0;
        this.mDown = 0;
        this.indexColor = false;
        try {
            this._$1713();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void _$1713() throws Exception {
        this.setBackground(Color.white);
    }
}
