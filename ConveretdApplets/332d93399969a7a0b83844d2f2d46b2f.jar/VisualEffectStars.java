import java.awt.Dimension;
import java.net.URL;
import java.awt.Event;
import java.awt.Font;
import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VisualEffectStars extends Applet implements Runnable
{
    Thread t;
    String infile;
    String moonfile;
    Image photo;
    Image oimg;
    Image moon;
    Graphics og;
    int width;
    int height;
    int height2;
    MediaTracker tracker;
    int delay;
    boolean loaded;
    boolean showCopyright;
    String url;
    public int nstars;
    private int w;
    private int h;
    private VisualEffectStarsObj[] sobj;
    private Color bg;
    int i;
    int ee;
    int bb;
    int ll;
    boolean sleep;
    int maxspeed;
    int maxsize;
    int dir;
    int moonx;
    int moony;
    int moonw;
    int moonh;
    int mx;
    int my;
    boolean registered;
    int nBlock;
    int loading;
    
    public VisualEffectStars() {
        this.t = null;
        this.photo = null;
        this.moon = null;
        this.tracker = null;
        this.delay = 100;
        this.loaded = false;
        this.showCopyright = false;
        this.url = null;
        this.nstars = 50;
        this.bg = Color.black;
        this.ee = 0;
        this.bb = 0;
        this.ll = 0;
        this.maxspeed = 5;
        this.maxsize = 2;
        this.dir = 1;
        this.moonx = 0;
        this.moony = 0;
        this.moonw = -1;
        this.moonh = -1;
        this.mx = 0;
        this.my = 0;
        this.registered = false;
        this.nBlock = 0;
        this.loading = 1;
    }
    
    public void init() {
        super.init();
        this.width = this.size().width;
        this.height = this.size().height;
        this.height2 = this.height / 2;
        final String parameter = this.getParameter("regcode");
        parameter.trim();
        parameter.toUpperCase();
        final char c = (char)(parameter.charAt(parameter.length() - 1) - '0');
        if (c + '\u0004' < parameter.length()) {
            final int intValue = new Integer(parameter.substring(c, c + '\u0003'));
            int intValue2 = new Integer(parameter.substring(parameter.length() - 4, parameter.length() - 1));
            final String upperCase = this.getDocumentBase().getHost().toUpperCase();
            char c2 = '\0';
            int n = 0;
            if (upperCase.length() > 0) {
                for (int i = upperCase.length() - 1; i >= 0; --i) {
                    if (upperCase.charAt(i) == '.') {
                        if (++n > 1) {
                            i = -1;
                        }
                    }
                    else if (n < 2 && upperCase.charAt(i) >= 'A' && upperCase.charAt(i) <= 'Z') {
                        c2 += (char)(upperCase.charAt(i) - 'A' + '\n');
                    }
                    else if (n < 2 && upperCase.charAt(i) >= '0' && upperCase.charAt(i) <= '9') {
                        c2 += (char)(upperCase.charAt(i) - '0');
                    }
                }
            }
            else {
                intValue2 = 0;
            }
            if (intValue == '\u01bc' && intValue2 == intValue * c2 % '\u03e8') {
                this.registered = true;
            }
        }
        this.infile = this.getParameter("image");
        this.moonfile = this.getParameter("moon");
        final int[] int1 = this.parseInt(this.getParameter("bg"), " ");
        this.bg = new Color(int1[0], int1[1], int1[2]);
        final int[] int2 = this.parseInt(this.getParameter("moonloc"), " ");
        this.moonx = int2[0];
        this.moony = int2[1];
        this.mx = this.moonx;
        this.my = this.moony;
        this.loading = Integer.parseInt(this.getParameter("loading"));
        this.delay = new Integer(this.getParameter("delay"));
        this.url = this.getParameter("url").trim();
        this.nstars = Integer.parseInt(this.getParameter("nstars"));
        this.maxsize = Integer.parseInt(this.getParameter("maxsize"));
        this.maxspeed = Integer.parseInt(this.getParameter("maxspeed"));
        if (this.maxspeed < 0) {
            this.dir = -1;
            this.maxspeed = -this.maxspeed;
        }
        else {
            this.dir = 1;
        }
        this.w = this.width - 1;
        this.h = this.height - 1;
        this.sobj = new VisualEffectStarsObj[this.nstars];
        this.i = 0;
        while (this.i < this.nstars) {
            this.sobj[this.i] = new VisualEffectStarsObj(this.w, this.h * 3 / 4, this.maxspeed, this.dir, this.maxsize);
            ++this.i;
        }
    }
    
    int[] parseInt(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
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
        this.photo = null;
        this.oimg = null;
        System.gc();
    }
    
    public void destroy() {
        if (this.oimg != null) {
            this.oimg.flush();
        }
        this.oimg = null;
        if (this.og != null) {
            this.og.dispose();
        }
        this.og = null;
        System.gc();
    }
    
    public void run() {
        if (!this.loaded) {
            final Graphics graphics = this.getGraphics();
            if (this.loading == 1 && !this.infile.toUpperCase().startsWith("NONE")) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                graphics.drawString("Loading \"" + this.infile + "\"...", 4, 29);
            }
            if (!this.infile.toUpperCase().startsWith("NONE")) {
                this.tracker = new MediaTracker(this);
                this.photo = this.getImage(this.getDocumentBase(), this.infile);
                this.tracker.addImage(this.photo, 0);
                this.waitForImage();
                this.width = this.photo.getWidth(this);
                this.height = this.photo.getHeight(this);
            }
            if (this.loading == 1 && !this.moonfile.toUpperCase().startsWith("NONE")) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.setColor(Color.black);
                graphics.drawString("Loading \"" + this.moonfile + "\"...", 4, 29);
            }
            if (!this.moonfile.toUpperCase().startsWith("NONE")) {
                if (this.tracker == null) {
                    this.tracker = new MediaTracker(this);
                }
                this.moon = this.getImage(this.getDocumentBase(), this.moonfile);
                this.tracker.addImage(this.moon, 0);
                this.waitForImage();
                this.moonw = this.moon.getWidth(this);
                this.moonh = this.moon.getHeight(this);
            }
            this.resize(this.width, this.height);
            this.oimg = this.createImage(this.width, this.height);
            this.og = this.oimg.getGraphics();
            this.loaded = true;
        }
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        int n = 0;
        while (Thread.currentThread() == this.t) {
            if (n++ > 1000) {
                n = 0;
                System.gc();
            }
            this.paint(graphics2);
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            if (this.moon != null && new Date().getSeconds() % 10 == 0) {
                this.mx = (this.mx + (int)(Math.random() + 0.5)) % this.width;
                this.my = (this.my + (int)(Math.random() + 0.5)) % this.height2;
            }
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 10 == 0 && date.getSeconds() < 20) {
                    this.nBlock = 20 - date.getSeconds();
                }
                else {
                    this.nBlock = 0;
                }
            }
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.loaded) {
            return;
        }
        if (this.nBlock > 0) {
            this.og.setColor(Color.black);
            this.og.fillRect(0, 0, this.width, this.height);
            this.og.setColor(Color.white);
            final String[] array = { "\"VisualEffectStars\"", "by", "www.thejmaker.com", "Unregistered timeout: " + this.nBlock };
            int n = this.height / 2 - 30;
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.og.setFont(new Font("Helvetica", 1, 14));
                }
                else {
                    this.og.setFont(new Font("Helvetica", 1, 11));
                }
                this.og.drawString(array[i], (this.width - this.og.getFontMetrics().stringWidth(array[i])) / 2, n);
                if (i == 0) {
                    n += 16;
                }
                else if (i == 2) {
                    this.og.setColor(Color.yellow);
                    n += 20;
                }
                else {
                    n += 13;
                }
            }
            graphics.drawImage(this.oimg, 0, 0, this);
            return;
        }
        this.og.setColor(this.bg);
        this.og.fillRect(0, 0, this.width, this.height);
        this.i = 0;
        while (this.i < this.nstars) {
            this.og.setColor(this.sobj[this.i].getNewColor());
            this.og.fillOval(this.sobj[this.i].x, this.sobj[this.i].y, this.sobj[this.i].size, this.sobj[this.i].size);
            this.sobj[this.i].move();
            ++this.i;
        }
        if (this.moon != null) {
            this.og.drawImage(this.moon, this.mx - this.moonw / 2, this.my - this.moonh / 2, this);
        }
        if (this.photo != null) {
            this.og.drawImage(this.photo, 0, 0, this);
        }
        if (!this.registered && this.showCopyright) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String s = "VisualEffectStars (C) thejmaker.com 2003";
            final int n2 = this.width - this.og.getFontMetrics().stringWidth(s) - 3;
            final int height = this.og.getFontMetrics().getHeight();
            this.og.setColor(Color.black);
            this.og.drawString(s, n2, height - 1);
            this.og.drawString(s, n2, height + 1);
            this.og.drawString(s, n2 - 1, height);
            this.og.drawString(s, n2 + 1, height);
            this.og.setColor(Color.white);
            this.og.drawString(s, n2, height);
        }
        graphics.drawImage(this.oimg, 0, 0, this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.nBlock > 0) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/pricing.html"), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }
        if (!this.url.toUpperCase().startsWith("NONE")) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.url));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mx = this.moonx;
        this.my = this.moony;
        this.showCopyright = false;
        if (this.registered) {
            this.showStatus("");
        }
        else {
            this.showStatus("VisualEffectStars (C) thejmaker.com 2003");
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mx, final int my) {
        this.showCopyright = true;
        if (!this.url.toUpperCase().startsWith("NONE")) {
            this.showStatus(this.url);
        }
        else {
            this.showStatus("");
        }
        this.mx = mx;
        this.my = my;
        return true;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    private void waitForImage() {
        while (!this.tracker.checkID(0, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
}
