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

public class mselight extends Applet implements Runnable
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
    int idold;
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
    private int lightR;
    private int lightG;
    private int lightB;
    private int radius;
    private int mradius;
    private int lightX;
    private int lightY;
    private int lightZ;
    private float angle;
    boolean loaded;
    boolean copyright;
    
    public mselight() {
        this.t = null;
        this.suspended = false;
        this.nboxes = 1;
        this.id = 0;
        this.idold = 0;
        this.maxscale = 3;
        this.fontsize = 10;
        this.startAnimation = false;
        this.resetScreen = false;
        this.se = false;
        this.style = 0;
        this.drawstyle = 0;
        this.delay = 100;
        this.lightR = 150;
        this.lightG = 220;
        this.lightB = 150;
        this.radius = 20;
        this.mradius = 20;
        this.lightX = 0;
        this.lightY = 0;
        this.lightZ = 0;
        this.angle = 0.0f;
        this.loaded = false;
        this.copyright = false;
    }
    
    public int blue(final int n, final int n2) {
        return mselight.data[n * this.width + n2] & 0xFF;
    }
    
    private void buildImage() {
        this.photo = this.createImage(new MemoryImageSource(this.width, this.height, mselight.dataNew, 0, this.width));
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.photo, 0, 0, this.width, this.height, mselight.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public int green(final int n, final int n2) {
        return (mselight.data[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public void init() {
        super.init();
        this.repaint();
        this.infile = this.getParameter("file");
        this.delay = new Integer(this.getParameter("delay"));
        this.radius = new Integer(this.getParameter("radius"));
        this.mradius = new Integer(this.getParameter("mradius"));
        final int[] int1 = this.parseInt(this.getParameter("lightcolor"));
        this.lightR = int1[0];
        this.lightG = int1[1];
        this.lightB = int1[2];
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
        mselight.data = new int[this.height * this.width];
        mselight.dataNew = new int[this.height * this.width];
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
        this.angle = 0.0f;
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
        this.og.setColor(new Color(this.R, this.G, this.B));
        if (this.drawstyle == 3 || this.drawstyle == 4) {
            this.og.setColor(Color.white);
            this.og.drawRect(this.XL[this.id] - 1, this.YL[this.id], this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.drawRect(this.XL[this.id] + 1, this.YL[this.id], this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.drawRect(this.XL[this.id], this.YL[this.id] - 1, this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.drawRect(this.XL[this.id], this.YL[this.id] + 1, this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
            this.og.setColor(new Color(this.R, this.G, this.B));
            this.og.drawRect(this.XL[this.id], this.YL[this.id], this.XH[this.id] - this.XL[this.id], this.YH[this.id] - this.YL[this.id]);
        }
        if (this.drawstyle == 2 || this.drawstyle == 4) {
            final int stringWidth = this.og.getFontMetrics().stringWidth(this.text[this.id]);
            final int height = this.og.getFontMetrics().getHeight();
            final int descent = this.og.getFontMetrics().getDescent();
            final int n = this.XH[this.id] - this.XL[this.id] - stringWidth;
            int n2;
            if (n > 0) {
                n2 = this.XL[this.id] + n / 2;
            }
            else {
                n2 = this.XL[this.id] - (stringWidth - this.XH[this.id] + this.XL[this.id]) / 2;
            }
            final int n3 = this.YH[this.id] - this.YL[this.id] - height;
            int n4;
            if (n3 > 0) {
                n4 = this.YH[this.id] - n3 / 2 - descent;
            }
            else {
                n4 = this.YH[this.id] + (height - this.YH[this.id] + this.YL[this.id]) / 2 - descent;
            }
            this.og.setColor(Color.white);
            this.og.drawString(this.text[this.id], n2 - 1, n4);
            this.og.drawString(this.text[this.id], n2 + 1, n4);
            this.og.drawString(this.text[this.id], n2, n4 - 1);
            this.og.drawString(this.text[this.id], n2, n4 + 1);
            this.og.setColor(new Color(this.R, this.G, this.B));
            this.og.drawString(this.text[this.id], n2, n4);
        }
        if (this.copyright) {
            this.og.setFont(new Font("Helvetica", 0, 10));
            final String s = "C. Liu";
            final int n5 = this.og.getFontMetrics().stringWidth(s) + 4;
            this.og.getFontMetrics().getHeight();
            this.og.setColor(Color.red);
            this.og.drawString(s, this.width - n5, this.height - 5);
            this.og.drawString(s, this.width - n5 + 2, this.height - 3);
            this.og.drawString(s, this.width - n5, this.height - 3);
            this.og.drawString(s, this.width - n5 + 2, this.height - 5);
            this.og.setColor(Color.yellow);
            this.og.drawString(s, this.width - n5 + 1, this.height - 4);
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
        final int width = this.width;
        final int height = this.height;
        final int[][] array = new int[height][width];
        final int[][] array2 = new int[height][width];
        final int[][] array3 = new int[height][width];
        if (this.id != this.idold) {
            int n = this.xc[this.idold] - this.radius - this.mradius;
            if (n < 0) {
                n = 0;
            }
            int width2 = this.xc[this.idold] + this.radius + this.mradius;
            if (width2 >= this.width) {
                width2 = this.width;
            }
            int n2 = this.yc[this.idold] - this.radius - this.mradius;
            if (n2 < 0) {
                n2 = 0;
            }
            int height2 = this.yc[this.idold] + this.radius + this.mradius;
            if (height2 >= this.height) {
                height2 = this.height;
            }
            for (int i = n2; i < height2; ++i) {
                for (int j = n; j < width2; ++j) {
                    mselight.dataNew[i * this.width + j] = mselight.data[i * this.width + j];
                }
            }
            this.idold = this.id;
        }
        int n3 = this.xc[this.id] - this.radius - this.mradius;
        if (n3 < 0) {
            n3 = 0;
        }
        int width3 = this.xc[this.id] + this.radius + this.mradius;
        if (width3 >= this.width) {
            width3 = this.width;
        }
        int n4 = this.yc[this.id] - this.radius - this.mradius;
        if (n4 < 0) {
            n4 = 0;
        }
        int height3 = this.yc[this.id] + this.radius + this.mradius;
        if (height3 >= this.height) {
            height3 = this.height;
        }
        this.lightX = (int)(this.mradius * Math.cos(this.angle * 3.14159f / 180.0f) + this.xc[this.id]);
        this.lightY = (int)(this.mradius * Math.sin(this.angle * 3.14159f / 180.0f) + this.yc[this.id]);
        for (int k = n4; k < height3; ++k) {
            for (int l = n3; l < width3; ++l) {
                final int n5 = this.lightX - l;
                final int n6 = this.lightY - k;
                final float n7 = (float)Math.sqrt(n5 * n5 + n6 * n6);
                if (n7 < this.radius) {
                    final float n8 = (float)Math.cos(n7 * 3.14159f / this.radius / 2.0f);
                    final float n9 = n8 * n8;
                    array[k][l] = this.red(k, l) + (int)(this.lightR * n9);
                    array2[k][l] = this.green(k, l) + (int)(this.lightG * n9);
                    array3[k][l] = this.blue(k, l) + (int)(this.lightB * n9);
                    if (array[k][l] > 255) {
                        array[k][l] = 255;
                    }
                    if (array2[k][l] > 255) {
                        array2[k][l] = 255;
                    }
                    if (array3[k][l] > 255) {
                        array3[k][l] = 255;
                    }
                }
                else {
                    array[k][l] = this.red(k, l);
                    array2[k][l] = this.green(k, l);
                    array3[k][l] = this.blue(k, l);
                }
                this.setRGB(k, l, array[k][l], array2[k][l], array3[k][l]);
            }
        }
        this.angle += 10.0f;
        if (this.angle > 360.0f) {
            this.angle = 0.0f;
        }
        this.buildImage();
    }
    
    public int red(final int n, final int n2) {
        return (mselight.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    private void restoreData() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                mselight.dataNew[i * this.width + j] = mselight.data[i * this.width + j];
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
                mselight.dataNew[(i + n2 + this.height) % this.height * this.width + (j + n + this.width) % this.width] = mselight.data[i * this.width + j];
            }
        }
        this.buildImage();
    }
    
    public void setRGB(final int n, final int n2, final int n3, final int n4, final int n5) {
        mselight.dataNew[n * this.width + n2] = new Color(n3, n4, n5).getRGB();
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
        this.drawstyle = 0;
        this.angle = 0.0f;
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
                mselight.dataNew[i * this.width + j] = mselight.data[((i - n3) * 2 / n + this.yc[this.id] + this.height) % this.height * this.width + ((j - n2) * 2 / n + this.xc[this.id] + this.width) % this.width];
            }
        }
        this.buildImage();
    }
}