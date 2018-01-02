import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.applet.AppletStub;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptflat extends Applet implements Runnable
{
    String PTViewer;
    int vx;
    int vy;
    int width;
    int height;
    ptviewer pv;
    int[] idata;
    int[] sdata;
    Thread loadImage;
    String filename;
    MemoryImageSource source;
    Image view;
    int cizf;
    int cxl;
    int cyt;
    
    public ptflat() {
        this.PTViewer = "ptviewer";
        this.filename = "image.jpg";
        this.cizf = -1;
        this.cxl = -1;
        this.cyt = -1;
    }
    
    public ptflat(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.filename = "image.jpg";
        this.cizf = -1;
        this.cxl = -1;
        this.cyt = -1;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter = this.getParameter("PTViewer");
        if (parameter != null) {
            this.PTViewer = parameter;
        }
        final String parameter2 = this.getParameter("file");
        if (parameter2 != null) {
            this.filename = parameter2;
        }
        final String parameter3 = this.getParameter("vx");
        if (parameter3 != null) {
            this.vx = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("vy");
        if (parameter4 != null) {
            this.vy = Integer.parseInt(parameter4);
        }
        while (this.pv == null) {
            try {
                this.pv = (ptviewer)this.getAppletContext().getApplet(this.PTViewer);
            }
            catch (Exception ex) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public void start() {
        if (this.pv != null) {
            this.pv.startCommunicating(this);
            if (this.loadImage == null) {
                (this.loadImage = new Thread(this)).start();
            }
        }
    }
    
    public void stop() {
        if (this.loadImage != null) {
            this.loadImage.stop();
            this.loadImage = null;
            if (this.pv != null) {
                this.pv.stopCommunicating(this);
            }
        }
    }
    
    public void run() {
        if (this.filename == null) {
            return;
        }
        final Image loadImage = this.pv.loadImage(this.filename);
        if (loadImage == null) {
            return;
        }
        this.width = loadImage.getWidth(null);
        this.height = loadImage.getHeight(null);
        try {
            this.sdata = new int[this.width * this.height];
            new PixelGrabber(loadImage, 0, 0, this.width, this.height, this.sdata, 0, this.width).grabPixels();
            this.idata = new int[this.pv.vwidth * this.pv.vheight];
            this.getView();
            (this.source = new MemoryImageSource(this.pv.vwidth, this.pv.vheight, this.idata, 0, this.pv.vwidth)).setAnimated(true);
            this.view = this.createImage(this.source);
            this.pv.repaint();
        }
        catch (Exception ex) {}
    }
    
    void getView() {
        final double n = this.pv.hfov / this.pv.hfov_max * this.width / this.pv.vwidth;
        final double n2 = n * this.pv.vwidth;
        final double n3 = n * this.pv.vheight;
        final double n4 = (this.pv.yaw - this.pv.yaw_min) / (this.pv.yaw_max - this.pv.yaw_min) * this.width;
        final double n5 = (-this.pv.pitch + this.pv.pitch_max) / (this.pv.pitch_max - this.pv.pitch_min) * this.height;
        int n6 = (int)(n4 - n2 / 2.0 + 0.5);
        if (n6 < 0) {
            n6 = 0;
        }
        if (n6 + (int)(n2 + 0.5) >= this.width) {
            n6 = this.width - 1 - (int)(n2 + 0.5);
            if (n6 < 0) {
                n6 = 0;
            }
        }
        int n7 = (int)(n5 - n3 / 2.0 + 0.5);
        if (n7 < 0) {
            n7 = 0;
        }
        if (n7 + (int)(n3 + 0.5) >= this.height) {
            n7 = this.height - 1 - (int)(n3 + 0.5);
            if (n7 < 0) {
                n7 = 0;
            }
        }
        final int cizf = (int)(255.0 * n + 0.5);
        final int n8 = n6 * 255;
        final int cyt = n7 * 255;
        if (n8 == this.cxl && cyt == this.cyt && this.cizf == cizf) {
            return;
        }
        for (int i = 0; i < this.pv.vheight; ++i) {
            final int n9 = i * this.pv.vwidth;
            for (int j = 0; j < this.pv.vwidth; ++j) {
                final int n10 = n9 + j;
                final int n11 = n8 + j * cizf;
                final int n12 = n11 & 0xFF;
                final int n13 = n11 >> 8;
                final int n14 = cyt + i * cizf;
                final int n15 = n14 & 0xFF;
                final int n16 = n14 >> 8;
                if (n13 >= 0 && n13 + 1 < this.width && n16 >= 0 && n16 + 1 < this.height) {
                    final int n18;
                    final int n17 = this.sdata[n18 = n16 * this.width + n13];
                    final int n19 = this.sdata[n18 + 1];
                    final int[] sdata = this.sdata;
                    final int n20 = n18 + this.width;
                    this.idata[n10] = this.pv.bil(n17, n19, sdata[n20], this.sdata[n20 + 1], n12, n15);
                }
                else {
                    this.idata[n10] = -7829368;
                }
            }
        }
        final int cxl = this.cxl;
        this.cyt = cyt;
        this.cizf = cizf;
        if (this.source != null) {
            this.source.newPixels();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.pv == null) {
            return;
        }
        if (this.view == null) {
            graphics.clearRect(this.pv.vx, this.pv.vy, this.pv.vx + this.pv.vwidth, this.pv.vy + this.pv.vheight);
            graphics.drawString("Loading Image...", this.vx + 30, this.pv.vy + this.pv.vheight / 2);
            return;
        }
        this.getView();
        graphics.drawImage(this.view, this.vx + this.pv.vx, this.vy + this.pv.vy, this.pv);
    }
}
