import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class memorycheck extends Applet implements Runnable
{
    int delay;
    Thread thread;
    int w;
    int h;
    Image bgi;
    Graphics bgg;
    long step;
    Color bgcolor;
    Color textcolor;
    int rows;
    int cols;
    String prefix;
    String postfix;
    MediaTracker mt;
    boolean loaded;
    boolean errored;
    boolean finished;
    FontMetrics fm;
    String message;
    Image[] ims;
    int xstep;
    int ystep;
    int xoff;
    int yoff;
    long stime;
    boolean[] show;
    int[] indexes;
    int firstinx;
    int secondinx;
    int errors;
    int clicks;
    long dt;
    private boolean lock;
    
    public void init() {
        this.lock = this.unlock();
        this.w = this.size().width;
        this.h = this.size().height;
        this.bgi = this.createImage(this.w, this.h);
        this.bgg = this.bgi.getGraphics();
        this.fm = this.bgg.getFontMetrics();
        this.bgcolor = new Color(this.parsenum(this.getParameter("BGCOLOR"), 16, 0));
        this.textcolor = new Color(this.parsenum(this.getParameter("TEXTCOLOR"), 16, 16776960));
        this.rows = this.parsenum(this.getParameter("ROWS"), 10, 0);
        this.cols = this.parsenum(this.getParameter("COLS"), 10, 0);
        this.prefix = this.getParameter("PREFIX");
        this.postfix = this.getParameter("POSTFIX");
        final int n = this.rows * this.cols / 2;
        this.indexes = new int[n * 2];
        this.show = new boolean[n * 2];
        for (int i = 0; i < n; ++i) {
            this.indexes[i] = 0;
        }
        int n2 = 0;
        for (int j = 0; j < n; ++j) {
            int n3;
            do {
                n3 = (int)(Math.random() * n * 2.0);
            } while (this.indexes[n3] != 0);
            this.indexes[n3] = j + 1;
            int n4;
            do {
                n4 = (int)(Math.random() * n * 2.0);
            } while (this.indexes[n4] != 0);
            this.indexes[n4] = j + 1;
            this.show[n2++] = false;
            this.show[n2++] = false;
        }
        this.mt = new MediaTracker(this);
        this.ims = new Image[n + 1];
        for (int k = 0; k < n + 1; ++k) {
            this.ims[k] = this.getImage(this.getCodeBase(), this.prefix + k + this.postfix);
            this.mt.addImage(this.ims[k], k);
        }
        this.mt.checkAll(true);
        this.errors = 0;
        this.clicks = 0;
    }
    
    void preparepaint() {
        if (!this.loaded) {
            if (this.mt.checkAll(true)) {
                this.loaded = true;
                final int width = this.ims[0].getWidth(this);
                final int height = this.ims[0].getHeight(this);
                this.xoff = this.w / this.cols - width >> 1;
                this.xstep = this.w / this.cols;
                final int n = this.fm.getHeight() * 2 + 10;
                this.yoff = (this.h - n) / this.rows - height >> 1;
                this.ystep = (this.h - n) / this.rows;
                this.stime = System.currentTimeMillis();
                this.message = "";
            }
            else {
                this.message = "Loading images...";
            }
        }
        if (this.mt.isErrorAny()) {
            this.message = "Error loading images!";
            this.errored = true;
        }
        if (this.errored || !this.loaded) {
            this.bgg.setColor(this.bgcolor);
            this.bgg.fillRect(0, 0, this.w, this.h);
            this.bgg.setColor(this.textcolor);
            this.bgg.drawString(this.message, this.w - this.fm.stringWidth(this.message) >> 1, this.h - this.fm.getHeight() >> 1);
            return;
        }
        this.bgg.setColor(this.bgcolor);
        this.bgg.fillRect(0, 0, this.w, this.h);
        for (int i = 0; i < this.cols; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                if (this.show[j * this.cols + i]) {
                    this.bgg.drawImage(this.ims[this.indexes[j * this.cols + i]], i * this.xstep + this.xoff, j * this.ystep + this.yoff, this);
                }
                else {
                    this.bgg.drawImage(this.ims[0], i * this.xstep + this.xoff, j * this.ystep + this.yoff, this);
                }
            }
        }
        this.bgg.setColor(this.textcolor);
        if (!this.finished) {
            this.dt = (System.currentTimeMillis() - this.stime) / 1000L;
        }
        final int n2 = (int)(this.dt / 60L);
        final int n3 = (int)(this.dt % 60L);
        String s = "";
        String s2 = "";
        if (n2 < 10) {
            s = " ";
        }
        final String string = s + n2;
        if (n3 < 10) {
            s2 = "0";
        }
        this.message = "Time:" + string + ":" + (s2 + n3) + " Clicks:" + this.clicks + " Errors:" + this.errors;
        this.bgg.drawString(this.message, this.w - this.fm.stringWidth(this.message) >> 1, this.h - (this.fm.getHeight() << 1));
        this.message = "";
        if (this.finished) {
            this.message = "Click to Start";
        }
        if (!this.lock) {
            this.message = "Click to get your FREE copy";
        }
        this.bgg.drawString(this.message, this.w - this.fm.stringWidth(this.message) >> 1, this.h - this.fm.getHeight());
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.bgi, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (Thread.currentThread() == this.thread) {
            ++this.step;
            this.preparepaint();
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    int parsenum(final String s, final int n, final int n2) {
        if (s == null) {
            return n2;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s, n);
        }
        catch (NumberFormatException ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 > this.h - (this.fm.getHeight() << 1) && !this.lock) {
            try {
                this.getAppletContext().showDocument(new URL("http://come.to/vmax"), "_blank");
            }
            catch (MalformedURLException ex) {}
            return true;
        }
        if (this.finished) {
            this.finished = false;
            this.init();
        }
        final int n3 = n / this.xstep;
        final int n4 = n2 / this.ystep;
        if (n3 >= this.cols || n3 < 0) {
            return true;
        }
        if (n4 >= this.rows || n4 < 0) {
            return true;
        }
        if (this.show[n4 * this.cols + n3]) {
            return true;
        }
        ++this.clicks;
        if (this.firstinx == -1) {
            this.show[n4 * this.cols + n3] = true;
            this.firstinx = n4 * this.cols + n3;
            return true;
        }
        if (this.secondinx == -1) {
            this.show[n4 * this.cols + n3] = true;
            this.secondinx = n4 * this.cols + n3;
            boolean finished = true;
            for (int i = 0; i < this.rows * this.cols; ++i) {
                finished = (finished && this.show[i]);
            }
            this.finished = finished;
            if (this.finished) {
                this.gameover();
            }
            if (this.indexes[this.firstinx] != this.indexes[this.secondinx]) {
                this.errors += 2;
            }
            return true;
        }
        if (this.indexes[this.firstinx] != this.indexes[this.secondinx]) {
            this.show[this.firstinx] = false;
            this.show[this.secondinx] = false;
        }
        this.show[n4 * this.cols + n3] = true;
        this.firstinx = n4 * this.cols + n3;
        this.secondinx = -1;
        return true;
    }
    
    private final boolean unlock() {
        final String parameter = this.getParameter("AUTHOR");
        if (parameter == null) {
            return false;
        }
        final String lowerCase = parameter.toLowerCase();
        if (lowerCase.indexOf("virtual_max") == -1 && lowerCase.indexOf("kollegov") == -1) {
            return false;
        }
        if (this.getDocumentBase().getProtocol().equalsIgnoreCase("file")) {
            return true;
        }
        final String host = this.getDocumentBase().getHost();
        return this.getParameter("KEY") != null && this.getParameter("KEY").trim().equalsIgnoreCase(this.encrypt(host, this.getClass().getName()));
    }
    
    private String encrypt(String s, String trim) {
        trim = trim.toLowerCase().trim();
        s = s.toLowerCase().trim();
        s += trim;
        final int length = s.length();
        if (length < 40) {
            for (int i = 0; i < 40 - length; ++i) {
                s += i % 10;
            }
        }
        if (length > 40) {
            s = s.substring(0, 40);
        }
        final byte[] array = new byte[40];
        s.getBytes(0, 40, array, 0);
        for (int j = 0; j < 20; ++j) {
            final byte[] array2 = array;
            final int n = j;
            array2[n] += array[39 - j];
        }
        s = "";
        for (int k = 0; k < 10; ++k) {
            final byte[] array3 = array;
            final int n2 = k;
            array3[n2] ^= (byte)((byte)((array[10 + k] << 8) + array[19 - k]) >> (array[k] & 0x3));
            s += Integer.toString(array[k], 9);
        }
        return s;
    }
    
    public void gameover() {
        final String parameter = this.getParameter("URL");
        if (parameter != null) {
            String parameter2 = this.getParameter("TARGET");
            if (parameter2 == null) {
                parameter2 = "_self";
            }
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), parameter), parameter2);
            }
            catch (MalformedURLException ex) {
                System.out.println("Exception:" + ex);
            }
        }
    }
    
    public memorycheck() {
        this.delay = 300;
        this.loaded = false;
        this.errored = false;
        this.finished = true;
        this.message = "Starting...";
        this.firstinx = -1;
        this.secondinx = -1;
    }
}
