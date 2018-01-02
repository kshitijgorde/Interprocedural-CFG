import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.MediaTracker;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class BandCanvas extends Canvas
{
    BandThread bt;
    MediaTracker mt;
    protected Vector vis;
    protected Vector vms;
    protected int wait;
    public Image imgOff;
    public Graphics gOff;
    public Dimension last;
    protected int delay;
    public ImageMorpher act;
    public int iact;
    public Image imgnext;
    public Graphics gnext;
    public int iimg;
    public int nbimg;
    public boolean stretch;
    
    public void setStretch(final boolean stretch) {
        this.stretch = stretch;
    }
    
    public boolean getStretch() {
        return this.stretch;
    }
    
    public void setDelay(final int n) {
        this.delay = ((n < 10) ? 1000 : n);
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public BandCanvas() {
        this.wait = 20;
        this.last = this.getSize();
        this.delay = 3000;
        this.stretch = false;
        this.mt = new MediaTracker(this);
        this.vis = new Vector(20);
        this.vms = new Vector(20);
    }
    
    public void addMorpher(final ImageMorpher imageMorpher) {
        this.vms.addElement(imageMorpher);
    }
    
    public void addImage(final Image image) {
        this.vis.addElement(image);
        this.mt.addImage(image, this.nbimg++);
    }
    
    public void start() {
        try {
            if (this.bt == null) {
                this.bt = new BandThread();
            }
            this.iimg = 0;
            this.mt.waitForID(this.iimg);
            if (!this.bt.isAlive()) {
                this.bt.start();
            }
            if (this.vms.isEmpty()) {
                this.addMorpher(new ImageMorpher());
            }
            this.bt.resume();
        }
        catch (Exception ex) {
            System.out.println("BadCancas start : " + ex);
        }
    }
    
    public void stop() {
        this.bt.suspend();
    }
    
    public void paint(final Graphics graphics) {
        if (this.gOff == null || this.last != this.getSize()) {
            this.last = this.getSize();
            this.imgOff = this.createImage(this.last.width, this.last.height);
            this.gOff = this.imgOff.getGraphics();
            this.imgnext = this.createImage(this.last.width, this.last.height);
            this.gnext = this.imgnext.getGraphics();
        }
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.imgOff != null) {
            graphics.drawImage(this.imgOff, 0, 0, this);
        }
    }
    
    public void nextImage() {
        boolean b = true;
        while (b) {
            ++this.iimg;
            if (this.iimg == this.vis.size()) {
                this.iimg = 0;
            }
            if (this.mt.isErrorID(this.iimg)) {
                System.out.println("Error img " + this.iimg);
            }
            else {
                if (!this.mt.checkID(this.iimg, true)) {
                    continue;
                }
                this.setDefaultImg();
                b = false;
            }
        }
    }
    
    public void setDefaultImg() {
        final Image image = this.vis.elementAt(this.iimg);
        final Dimension dimension = new Dimension(image.getWidth(this), image.getHeight(this));
        if (dimension.width > 0 && dimension.height > 0) {
            if (this.stretch) {
                this.gnext.drawImage(image, 0, 0, this.last.width, this.last.height, this);
                return;
            }
            this.gnext.clearRect(0, 0, this.last.width, this.last.height);
            this.gnext.drawImage(image, (this.last.width - dimension.width) / 2, (this.last.height - dimension.height) / 2, this);
        }
    }
    
    protected boolean onTip() {
        boolean tipMorpher = true;
        try {
            if (this.act == null) {
                if (this.iact == this.vms.size()) {
                    this.iact = 0;
                }
                this.act = this.vms.elementAt(this.iact++);
                this.nextImage();
                this.act.init(this.last);
            }
            if (tipMorpher = this.act.tipMorpher(this.gOff, this.imgnext, this.last)) {
                this.act = null;
            }
            this.repaint();
        }
        catch (Exception ex) {
            System.out.println("OnTip :" + ex);
        }
        return tipMorpher;
    }
    
    public class BandThread extends Thread
    {
        public void run() {
            int i = 1;
            BandCanvas.this.repaint();
        Label_0076:
            while (true) {
                try {
                    while (BandCanvas.this.gOff == null || BandCanvas.this.gnext == null) {
                        Thread.sleep(500L);
                    }
                    BandCanvas.this.setDefaultImg();
                    break Label_0076;
                }
                catch (Exception ex) {
                    System.out.println("BandThread init :" + ex);
                }
                while (true) {
                    try {
                        while (true) {
                            while (i == 0) {
                                final long currentTimeMillis = System.currentTimeMillis();
                                i = (BandCanvas.this.onTip() ? 1 : 0);
                                long n = BandCanvas.this.wait - (System.currentTimeMillis() - currentTimeMillis);
                                if (n < 0L) {
                                    n = 0L;
                                }
                                Thread.sleep(n);
                            }
                            i = 0;
                            Thread.sleep(BandCanvas.this.delay);
                        }
                    }
                    catch (Exception ex2) {
                        System.out.println("BandThread : " + ex2);
                        continue;
                    }
                    continue Label_0076;
                }
                break;
            }
        }
    }
}
