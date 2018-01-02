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
    int width;
    int height;
    Thread loadImages;
    String filename;
    String maskname;
    int showSHS;
    boolean ready;
    
    public ptobject() {
        this.PTViewer = "ptviewer";
        this.vx = 0;
        this.vy = 0;
        this.nhor = 24;
        this.nver = 1;
        this.pv = null;
        this.cnv = -1;
        this.cnh = -1;
        this.frame = null;
        this.idata = null;
        this.source = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.showSHS = -1;
        this.ready = false;
    }
    
    public ptobject(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.vx = 0;
        this.vy = 0;
        this.nhor = 24;
        this.nver = 1;
        this.pv = null;
        this.cnv = -1;
        this.cnh = -1;
        this.frame = null;
        this.idata = null;
        this.source = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.showSHS = -1;
        this.ready = false;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public ptobject(final ptviewer pv, final Image[] frame, final String s) {
        this.PTViewer = "ptviewer";
        this.vx = 0;
        this.vy = 0;
        this.nhor = 24;
        this.nver = 1;
        this.pv = null;
        this.cnv = -1;
        this.cnh = -1;
        this.frame = null;
        this.idata = null;
        this.source = null;
        this.loadImages = null;
        this.filename = "image";
        this.maskname = null;
        this.showSHS = -1;
        this.ready = false;
        this.pv = pv;
        this.frame = frame;
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
        final String parameter8 = this.getParameter("showSHS");
        if (parameter8 != null) {
            this.showSHS = Integer.parseInt(parameter8);
        }
        if (this.frame == null) {
            this.frame = new Image[this.nhor * this.nver];
            this.idata = new int[this.nhor * this.nver][];
            this.source = new MemoryImageSource[this.nhor * this.nver];
            for (int i = 0; i < this.nhor * this.nver; ++i) {
                this.frame[i] = null;
            }
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
            if (this.pv.yaw_max == 180.0 && this.pv.yaw_min == -180.0) {
                this.pv.pan_steps = this.pv.hfov / (this.pv.yaw_max - this.pv.yaw_min) * this.nhor;
                final ptviewer pv = this.pv;
                pv.yaw -= (this.pv.yaw_max - this.pv.yaw_min) / (2.0 * this.nhor);
            }
            else {
                this.pv.pan_steps = this.pv.hfov / (this.pv.yaw_max - this.pv.yaw_min - this.pv.hfov) * this.nhor;
                final ptviewer pv2 = this.pv;
                pv2.yaw -= (this.pv.yaw_max - this.pv.yaw_min - this.pv.hfov) / (2.0 * this.nhor);
            }
            if (this.frame[0] == null) {
                if (this.loadImages == null) {
                    (this.loadImages = new Thread(this)).start();
                }
            }
            else {
                this.ready = true;
                this.pv.dirty = true;
                this.pv.repaint();
            }
        }
    }
    
    public void stop() {
        if (this.pv != null) {
            if (this.loadImages != null) {
                this.pv.stopThread(this.loadImages);
                this.loadImages = null;
            }
            if (this.showSHS >= 0 && this.showSHS < this.pv.numshs) {
                this.pv.shs_imode[this.showSHS] = 0;
            }
            this.pv.stopCommunicating(this);
        }
    }
    
    public void run() {
        for (int i = this.nver / 2, n = this.nver / 2 - 1; i < this.nver; ++i, --n) {
            this.loadStripe(i);
            if (this.pv != null) {
                this.pv.dirty = true;
                this.pv.repaint();
            }
            if (n >= 0) {
                this.loadStripe(n);
                if (this.pv != null) {
                    this.pv.dirty = true;
                    this.pv.repaint();
                }
            }
        }
        if (this.pv != null && this.showSHS >= 0 && this.showSHS < this.pv.numshs) {
            this.pv.shs_imode[this.showSHS] = 2;
            this.pv.repaint();
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
                s = this.filename.substring(0, index) + n + this.filename.substring(index + 1);
            }
            else {
                s = this.filename + n;
            }
        }
        final Image loadImage = this.pv.loadImage(s);
        if (loadImage == null) {
            return;
        }
        this.width = loadImage.getWidth(null) / this.nhor;
        this.height = loadImage.getHeight(null);
        for (int i = 0; i < this.nhor; ++i) {
            try {
                this.idata[n * this.nhor + i] = new int[this.width * this.height];
                new PixelGrabber(loadImage, i * this.width, 0, this.width, this.height, this.idata[n * this.nhor + i], 0, this.width).grabPixels();
                (this.source[n * this.nhor + i] = new MemoryImageSource(this.width, this.height, this.idata[n * this.nhor + i], 0, this.width)).setAnimated(true);
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
                s2 = this.maskname.substring(0, index2) + n + this.maskname.substring(index2 + 1);
            }
            else {
                s2 = this.maskname + n;
            }
        }
        final Image loadImage2 = this.pv.loadImage(s2);
        if (loadImage2 == null || loadImage2.getWidth(null) / this.nhor != this.width || loadImage2.getHeight(null) != this.height) {
            return;
        }
        final int[] array = new int[this.width * this.height];
        for (int j = 0; j < this.nhor; ++j) {
            try {
                new PixelGrabber(loadImage2, j * this.width, 0, this.width, this.height, array, 0, this.width).grabPixels();
                this.SetMask(array, n * this.nhor + j, this.width, this.height);
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
        int n2;
        if (this.pv.yaw_max == 180.0 && this.pv.yaw_min == -180.0) {
            n2 = (int)((this.pv.yaw_max - this.pv.yaw) / (this.pv.yaw_max - this.pv.yaw_min) * this.nhor);
        }
        else {
            n2 = (int)((this.pv.yaw_max - this.pv.yaw - this.pv.hfov / 2.0) / (this.pv.yaw_max - this.pv.yaw_min - this.pv.hfov) * this.nhor);
        }
        if (n2 >= this.nhor) {
            n2 = this.nhor - 1;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (this.frame[n * this.nhor + n2] == null) {
            return;
        }
        if (this.pv.hfov == 70.0) {
            graphics.drawImage(this.frame[n * this.nhor + n2], this.vx + this.pv.vx, this.vy + this.pv.vy, this.pv);
            return;
        }
        final double n3 = 70.0 / this.pv.hfov;
        final double n4 = (1.0 - n3) / 2.0;
        final Image image = this.frame[n * this.nhor + n2];
        graphics.drawImage(image, this.vx + this.pv.vx + (int)(n4 * image.getWidth(null)), this.vy + this.pv.vy + (int)(n4 * image.getHeight(null)), this.vx + this.pv.vx + (int)(n4 * image.getWidth(null)) + (int)(n3 * image.getWidth(null)), this.vy + this.pv.vy + (int)(n4 * image.getHeight(null)) + (int)(n3 * image.getHeight(null)), 0, 0, image.getWidth(null), image.getHeight(null), this.pv);
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
