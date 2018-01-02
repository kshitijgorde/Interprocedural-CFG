import java.awt.Graphics;
import java.awt.Image;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptroi extends Applet implements Runnable
{
    String fname;
    String PTViewer;
    int px;
    int py;
    ptviewer pv;
    Thread loadImage;
    boolean progress;
    int showSHS;
    
    public ptroi() {
        this.fname = "Image.jpg";
        this.PTViewer = "ptviewer";
        this.px = 0;
        this.py = 0;
        this.pv = null;
        this.loadImage = null;
        this.progress = false;
        this.showSHS = -1;
    }
    
    public ptroi(final ptviewer pv, final String s) {
        this.fname = "Image.jpg";
        this.PTViewer = "ptviewer";
        this.px = 0;
        this.py = 0;
        this.pv = null;
        this.loadImage = null;
        this.progress = false;
        this.showSHS = -1;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter = this.getParameter("PTViewer");
        if (parameter != null) {
            this.PTViewer = parameter;
        }
        final String parameter2 = this.getParameter("x");
        if (parameter2 != null) {
            this.px = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("y");
        if (parameter3 != null) {
            this.py = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("file");
        if (parameter4 != null) {
            this.fname = parameter4;
        }
        final String parameter5 = this.getParameter("progress");
        if (parameter5 != null && parameter5.equalsIgnoreCase("true")) {
            this.progress = true;
        }
        final String parameter6 = this.getParameter("showSHS");
        if (parameter6 != null) {
            this.showSHS = Integer.parseInt(parameter6);
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
        if (this.loadImage == null) {
            (this.loadImage = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        if (this.pv == null) {
            return;
        }
        if (this.loadImage != null) {
            this.pv.stopThread(this.loadImage);
            this.loadImage = null;
        }
    }
    
    public void run() {
        if (this.pv == null) {
            return;
        }
        Image image;
        if (this.progress) {
            this.pv.ready = false;
            this.pv.percent[0] = 0;
            this.pv.repaint();
            image = this.pv.loadImageProgress(this.fname);
        }
        else {
            image = this.pv.loadImage(this.fname);
        }
        if (image != null) {
            while (this.pv.pdata == null) {
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            this.pv.ptinsertImage(this.pv.pdata.data, this.px, this.py, image, 1);
            if (this.pv.hsready) {
                for (int i = 0; i < this.pv.numhs; ++i) {
                    if ((this.pv.hs_imode[i] & 0x4) > 0) {
                        final int n = (int)this.pv.hs_up[i];
                        final int n2 = (int)this.pv.hs_vp[i];
                        this.pv.im_extractRect(this.pv.pdata.data, (int)this.pv.hs_xp[i] - n / 2, (int)this.pv.hs_yp[i] - n2 / 2, (int[])this.pv.hs_him[i], n, 0, n2, n, n2);
                    }
                }
            }
        }
        if (this.showSHS >= 0 && this.showSHS < this.pv.numshs) {
            this.pv.shs_imode[this.showSHS] = 2;
        }
        if (this.progress) {
            this.pv.ready = true;
        }
        this.pv.repaint();
    }
    
    public void paint(final Graphics graphics) {
    }
}
