import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.AppletStub;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptmovies extends Applet implements Runnable
{
    Thread sendMovie;
    String fname;
    String PTViewer;
    int px;
    int py;
    ptviewer pv;
    double fps;
    int loop;
    boolean warp;
    Image view;
    long TimeOfLastDraw;
    boolean dirty;
    
    public ptmovies() {
        this.sendMovie = null;
        this.fname = "frame";
        this.PTViewer = "ptviewer";
        this.px = 0;
        this.py = 0;
        this.pv = null;
        this.fps = 20.0;
        this.loop = 1;
        this.warp = true;
        this.view = null;
        this.TimeOfLastDraw = 0L;
        this.dirty = false;
    }
    
    public ptmovies(final ptviewer pv, final String s) {
        this.sendMovie = null;
        this.fname = "frame";
        this.PTViewer = "ptviewer";
        this.px = 0;
        this.py = 0;
        this.pv = null;
        this.fps = 20.0;
        this.loop = 1;
        this.warp = true;
        this.view = null;
        this.TimeOfLastDraw = 0L;
        this.dirty = false;
        this.pv = pv;
        this.setStub(new ptstub(this.pv, s));
    }
    
    public void init() {
        final String parameter;
        if ((parameter = this.getParameter("frame")) != null) {
            this.fname = parameter;
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("PTViewer")) != null) {
            this.PTViewer = parameter2;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("px")) != null) {
            this.px = Integer.parseInt(parameter3);
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("py")) != null) {
            this.py = Integer.parseInt(parameter4);
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("fps")) != null) {
            this.fps = Double.valueOf(parameter5);
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("loop")) != null) {
            this.loop = Integer.parseInt(parameter6);
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("warp")) != null && parameter7.equalsIgnoreCase("false")) {
            this.warp = false;
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
            if (this.sendMovie == null) {
                (this.sendMovie = new Thread(this)).start();
            }
            if (!this.warp) {
                this.pv.startCommunicating(this);
            }
        }
    }
    
    public synchronized void stop() {
        if (this.pv != null) {
            if (this.sendMovie != null) {
                this.pv.stopThread(this.sendMovie);
                this.sendMovie = null;
            }
            if (!this.warp) {
                this.pv.stopCommunicating(this);
            }
        }
    }
    
    public void run() {
        if (this.pv != null) {
            int n = 0;
            boolean b = false;
            final long n2 = (long)(1000.0 / this.fps);
            int n3 = 1;
            while (!b) {
                final int index;
                String s;
                if ((index = this.fname.indexOf(35)) >= 0) {
                    s = this.fname.substring(0, index) + n + this.fname.substring(index + 1);
                }
                else {
                    s = this.fname + n;
                }
                final Image loadImage;
                if ((loadImage = this.pv.loadImage(s)) != null) {
                    if (this.warp) {
                        while (this.pv.dirty) {
                            try {
                                Thread.sleep(20L);
                            }
                            catch (InterruptedException ex) {
                                return;
                            }
                        }
                    }
                    else {
                        while (this.dirty) {
                            try {
                                Thread.sleep(20L);
                            }
                            catch (InterruptedException ex2) {
                                return;
                            }
                        }
                    }
                    final long n4;
                    if ((n4 = System.currentTimeMillis() - this.TimeOfLastDraw) < n2) {
                        try {
                            Thread.sleep(n2 - n4);
                        }
                        catch (InterruptedException ex3) {
                            return;
                        }
                    }
                    if (this.warp) {
                        this.pv.ptinsertImage(this.pv.pdata, this.px, this.py, loadImage, 1);
                        this.TimeOfLastDraw = System.currentTimeMillis();
                    }
                    else {
                        this.view = loadImage;
                        this.pv.repaint();
                        this.dirty = true;
                    }
                    n += n3;
                }
                else if (n == 0 || this.loop == 0) {
                    b = true;
                }
                else if (this.loop == 1) {
                    n = 0;
                }
                else {
                    if (this.loop != 2) {
                        continue;
                    }
                    n3 = -n3;
                    n += n3;
                }
            }
            if (!this.warp) {
                this.pv.stopCommunicating(this);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.pv == null || this.view == null || this.warp) {
            return;
        }
        graphics.drawImage(this.view, this.px, this.py, this.pv);
        this.TimeOfLastDraw = System.currentTimeMillis();
        this.dirty = false;
    }
}