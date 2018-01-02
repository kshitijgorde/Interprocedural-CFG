import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.MalformedURLException;
import java.awt.Component;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnimNav extends Applet
{
    MediaTracker tracker;
    Dimension d;
    Graphics offscreen;
    Image buf;
    Image bg;
    Image[] img;
    NavAutoRun autoRun;
    int spriteX;
    int spriteY;
    int imageCount;
    int oldx;
    int downx;
    public int frame;
    public int URLcount;
    boolean bgP;
    boolean autorunP;
    URL[] URLdest;
    String[] URLdescription;
    String[] targetWindow;
    int[] translate;
    
    public void init() {
        this.d = this.size();
        this.buf = this.createImage(this.d.width, this.d.height);
        this.offscreen = this.buf.getGraphics();
        this.tracker = new MediaTracker(this);
        final String parameter = this.getParameter("spritex");
        if (parameter == null) {
            this.spriteX = 0;
        }
        else {
            this.spriteX = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("spritey");
        if (parameter2 == null) {
            this.spriteY = 0;
        }
        else {
            this.spriteY = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("sleeptime");
        if (parameter3 != null) {
            this.autorunP = true;
            this.autoRun = new NavAutoRun(this, Integer.parseInt(parameter3));
        }
        final String parameter4 = this.getParameter("urlcount");
        if (parameter4 == null) {
            System.out.println("Error Loading: urlcount, Not Optional");
        }
        else {
            this.URLcount = Integer.parseInt(parameter4);
            this.URLdescription = new String[this.URLcount];
            this.targetWindow = new String[this.URLcount];
            this.URLdest = new URL[this.URLcount];
        }
        for (int i = 0; i < this.URLcount; ++i) {
            this.URLdescription[i] = this.getParameter("dest" + i);
            try {
                if (this.URLdescription[i] != null) {
                    this.URLdest[i] = new URL(this.URLdescription[i]);
                }
            }
            catch (MalformedURLException ex) {
                System.out.println("Malformed URL: Check Applet Tag.");
            }
        }
        for (int j = 0; j < this.URLcount; ++j) {
            final String parameter5 = this.getParameter("desc" + j);
            if (parameter5 != null) {
                this.URLdescription[j] = parameter5;
            }
        }
        for (int k = 0; k < this.URLcount; ++k) {
            final String parameter6 = this.getParameter("target" + k);
            if (parameter6 == null) {
                this.targetWindow[k] = " _parent";
            }
            else {
                this.targetWindow[k] = parameter6;
            }
        }
        final String parameter7 = this.getParameter("imagecount");
        if (parameter7 == null) {
            System.out.println("Error getting Parameter: imagecount, Not Optional.");
        }
        else {
            this.imageCount = Integer.parseInt(parameter7);
            this.img = new Image[this.imageCount];
        }
        final String parameter8 = this.getParameter("translation");
        if (parameter8 == null) {
            System.out.println("Error getting Parameter: translation, Not Optional.");
        }
        else {
            this.translate = new int[this.imageCount];
            int n3;
            for (int n = 0, n2 = 0; n2 < parameter8.length() && n < this.imageCount; n2 = n3 + 1) {
                n3 = parameter8.indexOf(124, n2);
                if (n3 == -1) {
                    n3 = parameter8.length();
                }
                if (n2 != n3) {
                    this.translate[n] = Integer.parseInt(parameter8.substring(n2, n3));
                    ++n;
                }
            }
        }
        final String parameter9 = this.getParameter("imagetype");
        String s;
        if (parameter9 == null) {
            s = "jpg";
        }
        else {
            s = parameter9;
        }
        final String parameter10 = this.getParameter("background");
        if (parameter10 == null) {
            this.bgP = false;
            this.spriteX = 0;
            this.spriteY = 0;
        }
        else {
            this.showStatus("Loading Background Image");
            this.bg = this.getImage(this.getCodeBase(), parameter10);
            this.tracker.addImage(this.bg, 0);
            try {
                this.tracker.waitForAll();
            }
            catch (InterruptedException ex2) {
                System.out.println("Error waiting for Background image to load.");
            }
            this.showStatus("");
        }
        final String parameter11 = this.getParameter("prefix");
        if (parameter11 == null) {
            System.out.println("Error Loading image: " + parameter11 + ", Not Optional");
            return;
        }
        for (int l = 0; l < this.imageCount; ++l) {
            this.showStatus("AnimNav Loading Image :" + (l + 1) + " of " + this.imageCount);
            this.img[l] = this.getImage(this.getCodeBase(), parameter11 + l + "." + s);
            this.tracker.addImage(this.img[l], 1);
            try {
                this.tracker.waitForAll();
            }
            catch (InterruptedException ex3) {
                System.out.println("Error waiting for image" + l + " to load");
            }
            this.showStatus("");
        }
    }
    
    public void start() {
        if (this.autorunP) {
            this.autoRun.start();
        }
        final boolean frame = false;
        this.oldx = (frame ? 1 : 0);
        this.downx = (frame ? 1 : 0);
        this.frame = (frame ? 1 : 0);
        this.repaint();
    }
    
    public void stop() {
        if (this.autorunP) {
            this.autoRun.stop();
        }
    }
    
    public void destroy() {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.oldx = n;
        this.downx = n;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.downx < n + 2 && this.downx > n - 2) {
            this.getAppletContext().showDocument(this.URLdest[this.translate[this.frame]], this.targetWindow[this.translate[this.frame]]);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.autorunP) {
            this.autoRun.stop();
        }
        this.showStatus(this.URLdescription[this.translate[this.frame]]);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.autorunP) {
            this.autoRun.start();
        }
        this.showStatus("");
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n >= this.oldx + 20) {
            this.oldx = n;
            --this.frame;
            if (this.frame < 0) {
                this.frame = this.imageCount - 1;
            }
        }
        else if (n <= this.oldx - 20) {
            this.oldx = n;
            ++this.frame;
            if (this.frame >= this.imageCount) {
                this.frame = 0;
            }
        }
        this.showStatus(this.URLdescription[this.translate[this.frame]]);
        this.repaint();
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen != null) {
            this.paintApplet(this.offscreen);
            graphics.drawImage(this.buf, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        if (this.bgP) {
            graphics.drawImage(this.bg, 0, 0, null);
        }
        graphics.drawImage(this.img[this.frame], this.spriteX, this.spriteY, null);
    }
    
    public String getAppletInfo() {
        return "AnimNav.class By Elijah Meeker 1/5/96";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "[spriteX]", "int", "X offset of images on background [0]" }, { "[spriteY]", "int", "Y offset of images on background[0]" }, { "[sleeptime]", "int", "pause between images in AutoRun[AutoRun off]" }, { "imageCount", "int", "Number of animation images" }, { "translation", "parsed string", "which URLs go with which images,form:0|0|1|1" }, { "URLcount", "int", "number of URLs" }, { "dest+int", "url", "URL to navigate to(int min=0, max=URLcount-1" }, { "[desc+int]", "string", "URL description[string of dest+int]" }, { "[target+int]", "string", "target Window for URL[_parent]" }, { "[imagetype]", "string", "image type suffix W/O '.'[jpg]" }, { "[background]", "string", "Background image(offset from CodeBase)[no background, spriteX=spriteY=0]" }, { "prefix", "string", "Prefix to sprites(offset from CodeBase)" } };
    }
    
    public AnimNav() {
        this.bgP = true;
        this.autorunP = false;
    }
}
