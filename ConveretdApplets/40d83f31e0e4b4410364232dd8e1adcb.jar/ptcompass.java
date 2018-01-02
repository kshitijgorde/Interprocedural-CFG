import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.AppletStub;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptcompass extends Applet
{
    String PTViewer;
    int vx;
    int vy;
    int nhor;
    int nver;
    ptviewer pv;
    int cnv;
    int cnh;
    Image compass;
    int[] cs_data;
    int[] cd_data;
    MemoryImageSource cp_source;
    double cp_pan;
    int cp_x;
    int cp_y;
    int cp_direction;
    boolean cp_visible;
    boolean cp_dirty;
    double yaw;
    double pitch;
    double hfov;
    
    public ptcompass() {
        this.PTViewer = "ptviewer";
        this.nhor = 24;
        this.nver = 1;
        this.cnv = -1;
        this.cnh = -1;
        this.cp_direction = 1;
        this.cp_visible = true;
        this.cp_dirty = true;
        this.hfov = 70.0;
    }
    
    public ptcompass(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.nhor = 24;
        this.nver = 1;
        this.cnv = -1;
        this.cnh = -1;
        this.cp_direction = 1;
        this.cp_visible = true;
        this.cp_dirty = true;
        this.hfov = 70.0;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter = this.getParameter("PTViewer");
        if (parameter != null) {
            this.PTViewer = parameter;
        }
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
        final String parameter2 = this.getParameter("compass");
        if (parameter2 != null) {
            this.Setcompass(parameter2);
            this.cp_direction = 1;
        }
        final String parameter3 = this.getParameter("compass_map");
        if (parameter3 != null) {
            this.Setcompass(parameter3);
            this.cp_direction = -1;
        }
        final String parameter4 = this.getParameter("compass_pan");
        if (parameter4 != null) {
            this.cp_pan = Double.valueOf(parameter4);
        }
        final String parameter5 = this.getParameter("compass_x");
        if (parameter5 != null) {
            this.cp_x = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("compass_y");
        if (parameter6 != null) {
            this.cp_y = Integer.parseInt(parameter6);
        }
    }
    
    public void start() {
        if (this.pv != null) {
            this.pv.startCommunicating(this);
        }
    }
    
    public void stop() {
        if (this.pv != null) {
            this.pv.stopCommunicating(this);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.pv == null || this.compass == null) {
            return;
        }
        if (this.pv.pitch != this.pitch || this.pv.yaw != this.yaw || this.pv.hfov != this.hfov) {
            this.pitch = this.pv.pitch;
            this.yaw = this.pv.yaw;
            this.hfov = this.pv.hfov;
            this.SetTheCompass();
            this.cp_source.newPixels();
        }
        graphics.drawImage(this.compass, this.cp_x, this.cp_y, this.pv);
    }
    
    public synchronized void Setcompass(final String s) {
        if (this.pv == null) {
            return;
        }
        this.compass = this.pv.loadImage(s);
        if (this.compass == null) {
            return;
        }
        this.cs_data = new int[this.compass.getWidth(null) * this.compass.getHeight(null)];
        this.cd_data = new int[this.compass.getWidth(null) * this.compass.getHeight(null)];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.compass, 0, 0, this.compass.getWidth(null), this.compass.getHeight(null), this.cs_data, 0, this.compass.getWidth(null));
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.SetTheCompass();
        (this.cp_source = new MemoryImageSource(this.compass.getWidth(null), this.compass.getHeight(null), this.cd_data, 0, this.compass.getWidth(null))).setAnimated(true);
        this.compass = null;
        this.compass = this.createImage(this.cp_source);
    }
    
    void SetTheCompass() {
        final int width = this.compass.getWidth(null);
        final int height = this.compass.getHeight(null);
        final int n = (int)(Math.cos((this.yaw - this.cp_pan) * 3.141592653589793 / 180.0) * 256.0);
        final int n2 = (int)(Math.sin((this.yaw - this.cp_pan) * 3.141592653589793 / 180.0) * 256.0) * this.cp_direction;
        final int n3 = height / 2;
        final int n4 = width / 2;
        final int n5 = height * 128;
        final int n6 = width * 128;
        for (int i = 0; i < height; ++i) {
            final int n7 = i * width;
            final int n8 = i - n3;
            for (int j = 0; j < width; ++j) {
                final int n9 = j - n4;
                final int n10 = n9 * n - n8 * n2 + n6 >> 8;
                final int n11 = n9 * n2 + n8 * n + n5 >> 8;
                if (n10 >= 0 && n10 < width && n11 >= 0 && n11 < height) {
                    this.cd_data[n7 + j] = this.cs_data[n11 * width + n10];
                }
                else {
                    this.cd_data[n7 + j] = 0;
                }
            }
        }
    }
}
