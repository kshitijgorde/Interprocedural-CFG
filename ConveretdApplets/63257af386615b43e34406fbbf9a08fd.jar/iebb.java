import java.util.Date;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class iebb extends Applet implements Runnable
{
    Thread t;
    boolean suspended;
    Image photo0;
    Image photo1;
    Image oimg;
    Image[] hi;
    Graphics og;
    int width;
    int height;
    MediaTracker tracker;
    boolean loaded;
    boolean copyright;
    boolean registered;
    String infile0;
    String infile1;
    int nitems;
    int id;
    int state;
    int[] XL;
    int[] XH;
    int[] YL;
    int[] YH;
    String[] url;
    String[] frame;
    int delay;
    static int[] data;
    
    public iebb() {
        this.t = null;
        this.suspended = false;
        this.loaded = false;
        this.copyright = false;
        this.registered = false;
        this.nitems = 1;
        this.id = -1;
        this.state = 0;
        this.delay = 100;
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.photo1, 0, 0, this.width, this.height, iebb.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public void init() {
        super.init();
        this.width = this.size().width;
        this.height = this.size().height;
        this.infile1 = this.getParameter("onimage");
        this.infile0 = this.getParameter("offimage");
        this.nitems = new Integer(this.getParameter("nbuttons"));
        this.XL = new int[this.nitems];
        this.YL = new int[this.nitems];
        this.XH = new int[this.nitems];
        this.YH = new int[this.nitems];
        this.url = new String[this.nitems];
        this.frame = new String[this.nitems];
        for (int i = 0; i < this.nitems; ++i) {
            final String[] parse = this.parse(this.getParameter("button" + i));
            final int[] int1 = this.parseInt(parse[0]);
            this.XL[i] = int1[0];
            this.XH[i] = int1[1];
            this.YL[i] = int1[2];
            this.YH[i] = int1[3];
            this.url[i] = parse[1];
            this.frame[i] = parse[2];
        }
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        boolean b = false;
        for (int i = 0; i < this.nitems; ++i) {
            if (n >= this.XL[i] && n <= this.XH[i] && n2 >= this.YL[i] && n2 <= this.YH[i]) {
                this.id = i;
                this.showStatus(this.url[this.id]);
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.url[this.id]), this.frame[this.id]);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                b = true;
                break;
            }
        }
        if (b) {
            this.state = 2;
            this.showStatus(this.url[this.id]);
        }
        else {
            this.state = 0;
            this.id = -1;
        }
        this.copyright = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.state = 0;
        this.id = -1;
        this.copyright = false;
        if (this.registered) {
            this.showStatus(" iebb (C) 2000 The J Maker");
        }
        else {
            this.showStatus(" iebb (C) 2000 The J Maker - unregistered version");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        boolean b = false;
        for (int i = 0; i < this.nitems; ++i) {
            if (n >= this.XL[i] && n <= this.XH[i] && n2 >= this.YL[i] && n2 <= this.YH[i]) {
                b = true;
                this.id = i;
                break;
            }
        }
        if (b) {
            this.state = 1;
            this.showStatus(this.url[this.id]);
        }
        else {
            this.state = 0;
            this.id = -1;
        }
        this.copyright = true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.loaded) {
            return;
        }
        if (this.photo0 != null) {
            this.og.drawImage(this.photo0, 0, 0, this);
            if (this.state > 0 && this.id > -1) {
                this.og.drawImage(this.hi[this.id], this.XL[this.id], this.YL[this.id], this);
            }
        }
        if (this.id > -1 && this.state > 0) {
            this.og.setColor(new Color(128, 128, 128));
            for (int i = 0; i < 2; ++i) {
                this.og.draw3DRect(this.XL[this.id] + i, this.YL[this.id] + i, this.XH[this.id] - this.XL[this.id] - 2 * i, this.YH[this.id] - this.YL[this.id] - 2 * i, this.state == 1);
            }
        }
        graphics.drawImage(this.oimg, 0, 0, this);
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void run() {
        if (!this.loaded) {
            final Graphics graphics = this.getGraphics();
            graphics.setColor(Color.blue);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(Color.black);
            graphics.drawString("Loading \"" + this.infile0 + "\"...", 4, 29);
            graphics.setColor(Color.white);
            graphics.drawString("Loading \"" + this.infile0 + "\"...", 2, 27);
            this.tracker = new MediaTracker(this);
            this.photo0 = this.getImage(this.getDocumentBase(), this.infile0);
            this.tracker.addImage(this.photo0, 0);
            this.waitForImage(0);
            this.width = this.photo0.getWidth(this);
            this.height = this.photo0.getHeight(this);
            this.resize(this.width, this.height);
            graphics.setColor(Color.red);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(Color.black);
            graphics.drawString("Loading \"" + this.infile1 + "\"...", 4, 29);
            graphics.setColor(Color.white);
            graphics.drawString("Loading \"" + this.infile1 + "\"...", 2, 27);
            this.tracker = new MediaTracker(this);
            this.photo1 = this.getImage(this.getDocumentBase(), this.infile1);
            this.tracker.addImage(this.photo1, 1);
            this.waitForImage(1);
            iebb.data = new int[this.height * this.width];
            this.grabPixels();
            this.oimg = this.createImage(this.width, this.height);
            this.og = this.oimg.getGraphics();
            this.hi = new Image[this.nitems];
            for (int i = 0; i < this.nitems; ++i) {
                final int n = this.XH[i] - this.XL[i] + 1;
                final int n2 = this.YH[i] - this.YL[i] + 1;
                final int[] array = new int[n * n2];
                for (int j = 0; j < n2; ++j) {
                    for (int k = 0; k < n; ++k) {
                        final int n3 = (j + this.YL[i]) * this.width + (k + this.XL[i]);
                        array[j * n + k] = new Color((iebb.data[n3] & 0xFF0000) >> 16, (iebb.data[n3] & 0xFF00) >> 8, iebb.data[n3] & 0xFF).getRGB();
                    }
                }
                this.hi[i] = this.createImage(new MemoryImageSource(n, n2, array, 0, n));
                int width;
                for (int height = width = -1; width < 0 || height < 0; width = this.hi[i].getWidth(this), height = this.hi[i].getHeight(this)) {}
            }
            this.loaded = true;
            if (this.registered) {
                this.showStatus(" iebb (C) 2000 The J Maker");
            }
            else {
                this.showStatus(" iebb (C) 2000 The J Maker - unregistered version");
            }
        }
        Thread.currentThread().setPriority(4);
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex2) {}
            final Date date = new Date();
            if (date.getMinutes() % 5 == 0 && date.getSeconds() < 2) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/"));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
        }
        this.t = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void waitForImage(final int n) {
        while (!this.tracker.checkID(n, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
}
