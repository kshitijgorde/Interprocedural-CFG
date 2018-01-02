import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Component;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JPGanimator2 extends Applet implements Runnable
{
    Thread runner;
    Image offimage;
    Graphics off;
    Image[] billeder;
    int x;
    boolean doroolover;
    boolean start;
    boolean roolover;
    MediaTracker mt;
    int[] array;
    int max;
    int pause;
    byte type;
    String target;
    URL link;
    String files;
    String tekst;
    int huskeX;
    int f1;
    int f2;
    int f3;
    int f4;
    int f5;
    int f6;
    int f7;
    int f8;
    int f9;
    
    public JPGanimator2() {
        this.x = 0;
        this.doroolover = false;
        this.start = false;
        this.roolover = false;
        this.mt = new MediaTracker(this);
    }
    
    public void init() {
        if (this.getParameter("wait").equals("false")) {
            this.start = true;
        }
        this.target = this.getParameter("target");
        this.files = this.getParameter("filename");
        final String parameter = this.getParameter("link");
        if (parameter != null) {
            try {
                this.link = new URL(parameter);
            }
            catch (Exception ex) {}
        }
        this.max = 1 + Integer.parseInt(this.getParameter("pictures"));
        this.pause = Integer.parseInt(this.getParameter("pause"));
        final String parameter2 = this.getParameter("bgcolor");
        this.f1 = Integer.parseInt(parameter2.substring(0, 3));
        this.f2 = Integer.parseInt(parameter2.substring(4, 7));
        this.f3 = Integer.parseInt(parameter2.substring(8, 11));
        final String parameter3 = this.getParameter("textcolor");
        this.f4 = Integer.parseInt(parameter3.substring(0, 3));
        this.f5 = Integer.parseInt(parameter3.substring(4, 7));
        this.f6 = Integer.parseInt(parameter3.substring(8, 11));
        final String parameter4 = this.getParameter("roolovercolor");
        this.f7 = Integer.parseInt(parameter4.substring(0, 3));
        this.f8 = Integer.parseInt(parameter4.substring(4, 7));
        this.f9 = Integer.parseInt(parameter4.substring(8, 11));
        if (this.getParameter("roolover").equals("true")) {
            this.doroolover = true;
        }
        this.tekst = this.getParameter("loading-text");
        if (this.getParameter("type").equals("normal")) {
            this.type = 0;
        }
        else {
            this.type = 2;
        }
        if (this.getParameter("type").equals("random")) {
            this.type = 1;
        }
        if (!this.getParameter("programming").equals("www.stigc.dk")) {
            this.pause = 10000;
        }
        if (this.type == 2) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("type"), ",");
            final int countTokens = stringTokenizer.countTokens();
            this.array = new int[countTokens];
            for (int i = 0; i < countTokens; ++i) {
                this.array[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        this.billeder = new Image[this.max];
        String s = "";
        this.offimage = this.createImage(this.size().width, this.size().height);
        this.off = this.offimage.getGraphics();
        final String parameter5 = this.getParameter("file-extension");
        for (int j = 0; j < this.max; ++j) {
            if (j < 1000) {
                s = String.valueOf(j);
            }
            if (j < 100) {
                s = "0" + j;
            }
            if (j < 10) {
                s = "00" + j;
            }
            this.billeder[j] = this.getImage(this.getCodeBase(), String.valueOf(this.files) + s + parameter5);
            this.mt.addImage(this.billeder[j], j);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.link != null) {
            if (this.target == null) {
                this.getAppletContext().showDocument(this.link);
            }
            else {
                this.getAppletContext().showDocument(this.link, this.target);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.roolover = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return this.roolover = true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimage, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            if (this.start) {
                if (this.type == 0) {
                    this.off.drawImage(this.billeder[this.x], 0, 0, this);
                    ++this.x;
                    if (this.x == this.max) {
                        this.x = 0;
                    }
                }
                if (this.type == 1) {
                    this.off.drawImage(this.billeder[this.x], 0, 0, this);
                    while (this.x == this.huskeX) {
                        this.x = (int)(Math.random() * this.max);
                    }
                    this.huskeX = this.x;
                }
                if (this.type == 2) {
                    this.off.drawImage(this.billeder[this.array[this.x]], 0, 0, this);
                    ++this.x;
                    if (this.x == this.array.length) {
                        this.x = 0;
                    }
                }
            }
            this.off.setColor(new Color(this.f7, this.f8, this.f9));
            if (this.roolover && this.doroolover) {
                this.off.drawRect(1, 1, this.size().width - 3, this.size().height - 3);
            }
            if (!this.start) {
                int n = 0;
                for (int i = 0; i < this.max; ++i) {
                    if (this.mt.checkID(i, true)) {
                        ++n;
                    }
                }
                this.off.setColor(new Color(this.f1, this.f2, this.f3));
                this.off.fillRect(0, 0, this.size().width, this.size().height);
                this.off.setColor(new Color(this.f4, this.f5, this.f6));
                this.off.drawString(String.valueOf(this.tekst) + " (" + (int)(n / this.max * 100.0) + "%)", 10, 20);
            }
            if (!this.start && this.mt.checkAll(true)) {
                this.start = true;
            }
            this.repaint();
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        (this.runner = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
