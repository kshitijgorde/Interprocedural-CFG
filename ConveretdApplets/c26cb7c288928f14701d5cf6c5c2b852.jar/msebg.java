import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class msebg extends Applet implements Runnable
{
    Thread t;
    boolean suspended;
    String infile;
    Image photo;
    Image oimg;
    Graphics og;
    int width;
    int height;
    MediaTracker tracker;
    int nboxes;
    int id;
    int maxscale;
    int fontsize;
    boolean startAnimation;
    boolean resetScreen;
    boolean se;
    int[] XL;
    int[] XH;
    int[] YL;
    int[] YH;
    int[] xc;
    int[] yc;
    int mousex;
    int mousey;
    int stepx;
    int stepy;
    int diffx;
    int diffy;
    int panelR;
    int panelG;
    int panelB;
    float panelsize;
    int R;
    int G;
    int B;
    int style;
    int drawstyle;
    String[] url;
    String[] text;
    int delay;
    static int[] data;
    static int[] dataNew;
    boolean loaded;
    boolean copyright;
    
    public msebg() {
        this.t = null;
        this.suspended = false;
        this.nboxes = 1;
        this.id = 0;
        this.maxscale = 3;
        this.fontsize = 10;
        this.startAnimation = false;
        this.resetScreen = false;
        this.se = false;
        this.panelR = 100;
        this.panelG = 100;
        this.panelB = 0;
        this.panelsize = 0.6f;
        this.style = 0;
        this.drawstyle = 0;
        this.delay = 100;
        this.loaded = false;
        this.copyright = false;
    }
    
    public int blue(final int n, final int n2) {
        return msebg.data[n * this.width + n2] & 0xFF;
    }
    
    private void buildImage() {
        (this.photo = this.createImage(new MemoryImageSource(this.width, this.height, msebg.dataNew, 0, this.width))).flush();
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.photo, 0, 0, this.width, this.height, msebg.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public int green(final int n, final int n2) {
        return (msebg.data[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public void init() {
        super.init();
        this.repaint();
        this.infile = this.getParameter("file");
        this.delay = new Integer(this.getParameter("delay"));
        final int[] int1 = this.parseInt(this.getParameter("panel"));
        this.panelR = int1[0];
        this.panelG = int1[1];
        this.panelB = int1[2];
        this.panelsize = new Float(this.getParameter("panelsize"));
        this.style = new Integer(this.getParameter("style"));
        final int[] int2 = this.parseInt(this.getParameter("color"));
        this.R = int2[0];
        this.G = int2[1];
        this.B = int2[2];
        this.fontsize = new Integer(this.getParameter("fontsize"));
        this.maxscale = new Integer(this.getParameter("maxscale"));
        this.nboxes = new Integer(this.getParameter("nboxes"));
        this.xc = new int[this.nboxes];
        this.yc = new int[this.nboxes];
        this.XL = new int[this.nboxes];
        this.YL = new int[this.nboxes];
        this.XH = new int[this.nboxes];
        this.YH = new int[this.nboxes];
        this.text = new String[this.nboxes];
        this.url = new String[this.nboxes];
        for (int i = 0; i < this.nboxes; ++i) {
            final String[] parse = this.parse(this.getParameter("box" + i));
            final int[] int3 = this.parseInt(parse[0]);
            this.XL[i] = int3[0];
            this.XH[i] = int3[1];
            this.xc[i] = (this.XL[i] + this.XH[i]) / 2;
            this.YL[i] = int3[2];
            this.YH[i] = int3[3];
            this.yc[i] = (this.YL[i] + this.YH[i]) / 2;
            this.url[i] = parse[1];
            this.text[i] = parse[2];
        }
        this.tracker = new MediaTracker(this);
        this.photo = this.getImage(this.getDocumentBase(), this.infile);
        this.tracker.addImage(this.photo, 0);
        this.waitForImage();
        this.width = this.photo.getWidth(this);
        this.height = this.photo.getHeight(this);
        this.resize(this.width, this.height);
        msebg.data = new int[this.height * this.width];
        msebg.dataNew = new int[this.height * this.width];
        this.grabPixels();
        this.restoreData();
        this.buildImage();
        this.oimg = this.createImage(this.width, this.height);
        this.og = this.oimg.getGraphics();
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int mousex, final int mousey) {
        int n = 100000;
        this.mousex = mousex;
        this.mousey = mousey;
        for (int i = 0; i < this.nboxes; ++i) {
            final int n2 = this.xc[i] - this.mousex;
            final int n3 = this.yc[i] - this.mousey;
            final int n4 = n2 * n2 + n3 * n3;
            if (n4 < n) {
                n = n4;
                this.id = i;
            }
        }
        this.stepx = -1;
        this.stepy = -1;
        this.diffx = this.xc[this.id] - this.width / 2;
        this.diffy = this.yc[this.id] - this.height / 2;
        if (this.diffx < 0) {
            this.stepx = 1;
            this.diffx = -this.diffx;
        }
        if (this.diffy < 0) {
            this.stepy = 1;
            this.diffy = -this.diffy;
        }
        this.startAnimation = true;
        this.resetScreen = false;
        this.se = false;
        this.drawstyle = 0;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.drawstyle = 0;
        this.restoreData();
        this.buildImage();
        this.startAnimation = false;
        this.resetScreen = true;
        this.se = false;
        this.copyright = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mousex, final int mousey) {
        this.mousex = mousex;
        this.mousey = mousey;
        this.showStatus("(" + mousex + "," + mousey + ")  " + this.url[this.id]);
        if (!this.startAnimation) {
            boolean b = false;
            for (int i = 0; i < this.nboxes; ++i) {
                if (this.mousex >= this.XL[i] && this.mousex <= this.XH[i] && this.mousey >= this.YL[i] && this.mousey <= this.YH[i]) {
                    b = true;
                    this.id = i;
                    break;
                }
            }
            if (b) {
                this.drawstyle = this.style;
                this.se = true;
            }
            else {
                this.drawstyle = 0;
                this.restoreData();
                this.buildImage();
                this.resetScreen = true;
                this.se = false;
            }
        }
        this.copyright = true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.loaded) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.width - 1, this.height - 1);
            graphics.setColor(Color.blue);
            graphics.drawString("Loading...", 1, 40);
            this.loaded = true;
            return;
        }
        if (this.photo != null) {
            this.og.drawImage(this.photo, 0, 0, this);
            this.photo = null;
        }
        this.og.setFont(new Font("Helvetica", 1, this.fontsize));
        if (this.drawstyle == 3 || this.drawstyle == 4) {
            this.og.setColor(Color.white);
            this.og.drawRect(this.XL[this.id] - 1, this.YL[this.id], this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.drawRect(this.XL[this.id] + 1, this.YL[this.id], this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.drawRect(this.XL[this.id], this.YL[this.id] - 1, this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.drawRect(this.XL[this.id], this.YL[this.id] + 1, this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.setColor(new Color(this.R, this.G, this.B));
            this.og.drawRect(this.XL[this.id], this.YL[this.id] + 1, this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
        }
        if (this.drawstyle == 2 || this.drawstyle == 4) {
            final int stringWidth = this.og.getFontMetrics().stringWidth(this.text[this.id]);
            this.og.getFontMetrics().getHeight();
            final int n = (this.width - stringWidth) / 2;
            final int n2 = this.height / 2;
            this.og.setColor(Color.white);
            this.og.drawString(this.text[this.id], n - 1, n2);
            this.og.drawString(this.text[this.id], n + 1, n2);
            this.og.drawString(this.text[this.id], n, n2 - 1);
            this.og.drawString(this.text[this.id], n, n2 + 1);
            this.og.setColor(new Color(this.R, this.G, this.B));
            this.og.drawString(this.text[this.id], n, n2);
        }
        if (this.copyright) {
            this.og.setFont(new Font("Helvetica", 0, 10));
            final String s = "C. Liu";
            final int n3 = this.og.getFontMetrics().stringWidth(s) + 4;
            this.og.getFontMetrics().getHeight();
            this.og.setColor(Color.red);
            this.og.drawString(s, this.width - n3, this.height - 5);
            this.og.drawString(s, this.width - n3 + 2, this.height - 3);
            this.og.drawString(s, this.width - n3, this.height - 3);
            this.og.drawString(s, this.width - n3 + 2, this.height - 5);
            this.og.setColor(Color.yellow);
            this.og.drawString(s, this.width - n3 + 1, this.height - 4);
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
    
    private void program() {
        final float n = this.width * this.panelsize / (this.XH[this.id] - this.XL[this.id] + 1);
        final float n2 = this.height * this.panelsize / (this.YH[this.id] - this.YL[this.id] + 1);
        final int n3 = this.width / 2;
        final int n4 = this.height / 2;
        final int n5 = (int)(this.width * (1.0f - this.panelsize) / 2.0f);
        final int n6 = (int)(this.width * (1.0f + this.panelsize) / 2.0f);
        final int n7 = (int)(this.height * (1.0f - this.panelsize) / 2.0f);
        final int n8 = (int)(this.height * (1.0f + this.panelsize) / 2.0f);
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                final int n9 = (int)((j - n3) / n + this.xc[this.id]);
                final int n10 = (int)((i - n4) / n2 + this.yc[this.id]);
                if (n9 >= this.XL[this.id] && n9 <= this.XH[this.id] && n10 >= this.YL[this.id] && n10 <= this.YH[this.id] && j >= n5 && j <= n6 && i >= n7 && i <= n8) {
                    this.setRGB(i, j, (int)(this.panelR * 0.4f + this.red(n10, n9) * 0.6f), (int)(this.panelG * 0.4f + this.green(n10, n9) * 0.6f), (int)(this.panelB * 0.4f + this.blue(n10, n9) * 0.6f));
                }
                else {
                    final int n11 = (this.red(i, j) + this.green(i, j) + this.blue(i, j)) / 3;
                    this.setRGB(i, j, n11, n11, n11);
                }
            }
        }
        this.buildImage();
    }
    
    public int red(final int n, final int n2) {
        return (msebg.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    private void restoreData() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                msebg.dataNew[i * this.width + j] = msebg.data[i * this.width + j];
            }
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
        while (true) {
            if (this.startAnimation) {
                this.restoreData();
                this.buildImage();
                this.repaint();
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex2) {}
                for (int i = 1; i <= this.diffx; ++i) {
                    this.repaint();
                    try {
                        Thread.sleep(this.delay);
                    }
                    catch (InterruptedException ex3) {}
                    this.scroll(this.stepx * i, 0);
                }
                for (int j = 1; j <= this.diffy; ++j) {
                    this.repaint();
                    try {
                        Thread.sleep(this.delay);
                    }
                    catch (InterruptedException ex4) {}
                    this.scroll(this.stepx * this.diffx, this.stepy * j);
                }
                for (int k = 3; k < this.maxscale * 2 + 1; ++k) {
                    this.zoom(k);
                    this.repaint();
                    try {
                        Thread.sleep(this.delay);
                    }
                    catch (InterruptedException ex5) {}
                }
                this.startAnimation = false;
                try {
                    final URL url = new URL(this.getDocumentBase(), this.url[this.id]);
                    this.showStatus(this.url[this.id]);
                    this.getAppletContext().showDocument(url);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (this.resetScreen) {
                this.repaint();
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex6) {}
                this.resetScreen = false;
            }
            else {
                if (!this.se) {
                    continue;
                }
                this.program();
                this.repaint();
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex7) {}
            }
        }
    }
    
    private void scroll(final int n, final int n2) {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                msebg.dataNew[(i + n2 + this.height) % this.height * this.width + (j + n + this.width) % this.width] = msebg.data[i * this.width + j];
            }
        }
        this.buildImage();
    }
    
    public void setRGB(final int n, final int n2, final int n3, final int n4, final int n5) {
        msebg.dataNew[n * this.width + n2] = new Color(n3, n4, n5).getRGB();
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
        this.startAnimation = false;
        this.se = false;
        this.id = -1;
        this.drawstyle = 0;
        this.init();
        this.resetScreen = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void waitForImage() {
        while (!this.tracker.checkID(0, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
    
    private void zoom(final int n) {
        final int n2 = this.width / 2;
        final int n3 = this.height / 2;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                msebg.dataNew[i * this.width + j] = msebg.data[((i - n3) * 2 / n + this.yc[this.id] + this.height) % this.height * this.width + ((j - n2) * 2 / n + this.xc[this.id] + this.width) % this.width];
            }
        }
        this.buildImage();
    }
}
