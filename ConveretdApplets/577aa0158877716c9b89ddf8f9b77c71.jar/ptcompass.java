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
    boolean tilt;
    double yaw;
    double pitch;
    double hfov;
    
    public ptcompass() {
        this.PTViewer = "ptviewer";
        this.vx = 0;
        this.vy = 0;
        this.nhor = 24;
        this.nver = 1;
        this.pv = null;
        this.cnv = -1;
        this.cnh = -1;
        this.compass = null;
        this.cs_data = null;
        this.cd_data = null;
        this.cp_source = null;
        this.cp_pan = 0.0;
        this.cp_x = 0;
        this.cp_y = 0;
        this.cp_direction = 1;
        this.cp_visible = true;
        this.cp_dirty = true;
        this.tilt = false;
        this.yaw = 0.0;
        this.pitch = 0.0;
        this.hfov = 70.0;
    }
    
    public ptcompass(final ptviewer pv, final String s) {
        this.PTViewer = "ptviewer";
        this.vx = 0;
        this.vy = 0;
        this.nhor = 24;
        this.nver = 1;
        this.pv = null;
        this.cnv = -1;
        this.cnh = -1;
        this.compass = null;
        this.cs_data = null;
        this.cd_data = null;
        this.cp_source = null;
        this.cp_pan = 0.0;
        this.cp_x = 0;
        this.cp_y = 0;
        this.cp_direction = 1;
        this.cp_visible = true;
        this.cp_dirty = true;
        this.tilt = false;
        this.yaw = 0.0;
        this.pitch = 0.0;
        this.hfov = 70.0;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter;
        if ((parameter = this.getParameter("PTViewer")) != null) {
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
        final String parameter2;
        if ((parameter2 = this.getParameter("compass")) != null) {
            this.Setcompass(parameter2);
            this.cp_direction = 1;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("compass_map")) != null) {
            this.Setcompass(parameter3);
            this.cp_direction = -1;
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("compass_pan")) != null) {
            this.cp_pan = Double.valueOf(parameter4);
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("compass_x")) != null) {
            this.cp_x = Integer.parseInt(parameter5);
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("compass_y")) != null) {
            this.cp_y = Integer.parseInt(parameter6);
        }
        if (this.getParameter("tilt") != null) {
            this.tilt = true;
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
        if (this.pv != null) {
            this.compass = this.pv.loadImage(s);
            if (this.compass != null) {
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
        }
    }
    
    void SetTheCompass() {
        final double n = this.tilt ? this.pitch : this.yaw;
        final int width = this.compass.getWidth(null);
        final int height = this.compass.getHeight(null);
        final int n2 = (int)(Math.cos((n - this.cp_pan) * 3.141592653589793 / 180.0) * 256.0);
        final int n3 = (int)(Math.sin((n - this.cp_pan) * 3.141592653589793 / 180.0) * 256.0) * this.cp_direction;
        final int n4 = height >> 1;
        final int n5 = width >> 1;
        final int n6 = height << 7;
        final int n7 = width << 7;
        for (int i = 0; i < height; ++i) {
            final int n8 = i * width;
            final int n9 = i - n4;
            for (int j = 0; j < width; ++j) {
                final int n11;
                final int n10 = (n11 = j - n5) * n2 - n9 * n3 + n7 >> 8;
                final int n12 = n11 * n3 + n9 * n2 + n6 >> 8;
                if (n10 >= 0 && n10 < width && n12 >= 0 && n12 < height) {
                    this.cd_data[n8 + j] = this.cs_data[n12 * width + n10];
                }
                else {
                    this.cd_data[n8 + j] = 0;
                }
            }
        }
    }
}
