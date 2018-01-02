import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.applet.AppletStub;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptobject extends Applet implements Runnable
{
    String PTViewer;
    int vx;
    int vy;
    int nhor;
    int nver;
    ptviewer pv;
    int cnv;
    int cnh;
    Image[] frame;
    int[][] idata;
    MemoryImageSource[] source;
    Thread loadImages;
    String filename;
    String maskname;
    boolean ready;
    
    public ptobject() {
        this.PTViewer = "ptviewer";
        this.nhor = 24;
        this.nver = 1;
        this.cnv = -1;
        this.cnh = -1;
        this.filename = "image";
        this.ready = false;
    }
    
    public ptobject(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.nhor = 24;
        this.nver = 1;
        this.cnv = -1;
        this.cnh = -1;
        this.filename = "image";
        this.ready = false;
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
        final String parameter3 = this.getParameter("mask");
        if (parameter3 != null) {
            this.maskname = parameter3;
        }
        final String parameter4 = this.getParameter("nhor");
        if (parameter4 != null) {
            this.nhor = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("nver");
        if (parameter5 != null) {
            this.nver = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("vx");
        if (parameter6 != null) {
            this.vx = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("vy");
        if (parameter7 != null) {
            this.vy = Integer.parseInt(parameter7);
        }
        this.frame = new Image[this.nhor * this.nver];
        this.idata = new int[this.nhor * this.nver][];
        this.source = new MemoryImageSource[this.nhor * this.nver];
        for (int i = 0; i < this.nhor * this.nver; ++i) {
            this.frame[i] = null;
        }
    }
    
    public void start() {
        while (this.pv == null) {
            try {
                this.pv = (ptviewer)this.getAppletContext().getApplet(this.PTViewer);
            }
            catch (Exception ex) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex2) {
                    return;
                }
            }
        }
        if (this.pv != null) {
            this.pv.startCommunicating(this);
            if (this.loadImages == null) {
                (this.loadImages = new Thread(this)).start();
            }
        }
    }
    
    public void stop() {
        if (this.loadImages != null) {
            this.loadImages.stop();
            this.loadImages = null;
            if (this.pv != null) {
                this.pv.stopCommunicating(this);
            }
        }
    }
    
    public void run() {
        for (int i = this.nver / 2, n = this.nver / 2 - 1; i < this.nver; ++i, --n) {
            this.loadStripe(i);
            if (this.pv != null) {
                this.pv.repaint();
            }
            if (n >= 0) {
                this.loadStripe(n);
                if (this.pv != null) {
                    this.pv.repaint();
                }
            }
        }
    }
    
    void loadStripe(final int n) {
        if (this.pv == null) {
            return;
        }
        String s;
        if (this.nver == 1) {
            s = this.filename;
        }
        else {
            final int index = this.filename.indexOf(35);
            if (index >= 0) {
                s = String.valueOf(this.filename.substring(0, index)) + n + this.filename.substring(index + 1);
            }
            else {
                s = String.valueOf(this.filename) + n;
            }
        }
        final Image loadImage = this.pv.loadImage(s);
        if (loadImage == null) {
            return;
        }
        final int n2 = loadImage.getWidth(null) / this.nhor;
        final int height = loadImage.getHeight(null);
        for (int i = 0; i < this.nhor; ++i) {
            try {
                this.idata[n * this.nhor + i] = new int[n2 * height];
                new PixelGrabber(loadImage, i * n2, 0, n2, height, this.idata[n * this.nhor + i], 0, n2).grabPixels();
                (this.source[n * this.nhor + i] = new MemoryImageSource(n2, height, this.idata[n * this.nhor + i], 0, n2)).setAnimated(true);
                this.frame[n * this.nhor + i] = this.createImage(this.source[n * this.nhor + i]);
            }
            catch (Exception ex) {
                return;
            }
        }
        if (this.maskname == null) {
            return;
        }
        String s2;
        if (this.nver == 1) {
            s2 = this.maskname;
        }
        else {
            final int index2 = this.maskname.indexOf(35);
            if (index2 >= 0) {
                s2 = String.valueOf(this.maskname.substring(0, index2)) + n + this.maskname.substring(index2 + 1);
            }
            else {
                s2 = String.valueOf(this.maskname) + n;
            }
        }
        final Image loadImage2 = this.pv.loadImage(s2);
        if (loadImage2 == null || loadImage2.getWidth(null) / this.nhor != n2 || loadImage2.getHeight(null) != height) {
            return;
        }
        final int[] array = new int[n2 * height];
        for (int j = 0; j < this.nhor; ++j) {
            try {
                new PixelGrabber(loadImage2, j * n2, 0, n2, height, array, 0, n2).grabPixels();
                this.SetMask(array, n * this.nhor + j, n2, height);
            }
            catch (Exception ex2) {
                return;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.pv == null) {
            return;
        }
        int n = (int)((this.pv.pitch - this.pv.pitch_min) / (this.pv.pitch_max - this.pv.pitch_min) * this.nver);
        if (n >= this.nver) {
            n = this.nver - 1;
        }
        if (n < 0) {
            n = 0;
        }
        int n2 = (int)((this.pv.yaw_max - this.pv.yaw) / (this.pv.yaw_max - this.pv.yaw_min) * this.nhor);
        if (n2 >= this.nhor) {
            n2 = this.nhor - 1;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (this.frame[n * this.nhor + n2] == null) {
            return;
        }
        graphics.drawImage(this.frame[n * this.nhor + n2], this.vx + this.pv.vx, this.vy + this.pv.vy, this.pv);
    }
    
    void SetMask(final int[] array, final int n, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            final int n4 = i * n2;
            for (int j = 0; j < n2; ++j) {
                final int n5 = n4 + j;
                if ((array[n5] & 0xFF) != 0xFF) {
                    this.idata[n][n5] = 0;
                }
            }
        }
        this.source[n].newPixels();
    }
}
