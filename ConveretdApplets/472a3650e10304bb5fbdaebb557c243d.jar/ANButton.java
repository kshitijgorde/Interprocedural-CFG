import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.MalformedURLException;
import java.awt.Component;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ANButton extends Applet implements Runnable
{
    private MediaTracker tracker;
    private Image buf;
    private Image bg;
    private Image[] img;
    private int X;
    private int Y;
    private Graphics offscreen;
    private Dimension d;
    private boolean onButt;
    private boolean pressedButt;
    private boolean three_state;
    private boolean userPause;
    private int onIs;
    private int animImg;
    private int maxImg;
    private int pause;
    private URL clickDest;
    private String dest;
    private String destDefault;
    private Thread engine;
    
    public void init() {
        this.d = this.size();
        this.buf = this.createImage(this.d.width, this.d.height);
        this.offscreen = this.buf.getGraphics();
        int n = 0;
        boolean b = false;
        this.tracker = new MediaTracker(this);
        while (!b && n < 10) {
            final String parameter = this.getParameter("image" + n);
            if (parameter == null) {
                if (n > 0) {
                    b = true;
                }
                else {
                    this.three_state = false;
                    this.onIs = 3;
                }
            }
            else {
                if (n == 0) {
                    this.three_state = true;
                }
                this.showStatus("Loading image " + parameter + ".");
                this.img[n] = this.getImage(this.getCodeBase(), parameter);
                this.tracker.addImage(this.img[n], 0);
                try {
                    this.tracker.waitForAll();
                }
                catch (InterruptedException ex) {
                    System.out.println("Error waiting for image" + n + " to load");
                }
            }
            ++n;
        }
        this.maxImg = n - 2;
        if (this.maxImg < 3) {
            System.out.println("Need at least images 1 to 3: Check Applet Tag.");
            for (int i = this.maxImg + 1; i < 4; ++i) {
                this.img[i] = this.img[1];
            }
            this.maxImg = 3;
        }
        final String parameter2 = this.getParameter("x");
        this.X = ((parameter2 != null) ? Integer.parseInt(parameter2) : 0);
        final String parameter3 = this.getParameter("y");
        this.Y = ((parameter3 != null) ? Integer.parseInt(parameter3) : 0);
        final String parameter4 = this.getParameter("pause");
        this.pause = ((parameter4 != null) ? Integer.parseInt(parameter4) : 200);
        final String parameter5 = this.getParameter("dest");
        this.dest = ((parameter5 != null) ? parameter5 : this.destDefault);
        try {
            this.clickDest = new URL(this.dest);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Malformed URL: Check Applet Tag.");
        }
    }
    
    public void start() {
        if (this.engine == null && !this.userPause) {
            (this.engine = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.engine != null && this.engine.isAlive()) {
            this.engine.stop();
        }
        this.engine = null;
    }
    
    public void destroy() {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.engine != null && this.engine.isAlive()) {
            this.userPause = true;
            this.engine.suspend();
            this.stopPlaying();
        }
        this.pressedButt = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.pressedButt && this.onButt) {
            this.pressedButt = false;
            this.repaint();
            this.getAppletContext().showDocument(this.clickDest);
        }
        else {
            this.pressedButt = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.onButt = true;
        this.userPause = false;
        if (this.engine != null && this.engine.isAlive()) {
            this.engine.resume();
            this.startPlaying();
        }
        else {
            (this.engine = new Thread(this)).start();
            this.startPlaying();
        }
        this.repaint();
        this.showStatus(this.dest);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.onButt = false;
        this.userPause = true;
        if (this.engine != null && this.engine.isAlive()) {
            this.engine.suspend();
            this.stopPlaying();
        }
        this.repaint();
        this.showStatus("");
        return true;
    }
    
    void startPlaying() {
    }
    
    void stopPlaying() {
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        currentThread.setPriority(1);
        while (this.engine == currentThread) {
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex) {}
            ++this.animImg;
            if (this.animImg > this.maxImg) {
                this.animImg = 3;
            }
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        if (!this.onButt) {
            if (this.three_state) {
                this.onIs = 0;
            }
            else {
                this.onIs = 3;
            }
        }
        else if (this.onButt && !this.pressedButt) {
            this.onIs = 3;
        }
        else {
            this.onIs = 2;
        }
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
        int n = this.onIs;
        if (this.onIs == 3) {
            n = this.animImg;
        }
        graphics.drawImage(this.img[1], 0, 0, null);
        graphics.drawImage(this.img[n], this.X, this.Y, null);
    }
    
    public ANButton() {
        this.img = new Image[10];
        this.onButt = false;
        this.pressedButt = false;
        this.three_state = true;
        this.userPause = true;
        this.animImg = 3;
        this.maxImg = 3;
        this.pause = 200;
        this.destDefault = "http://www.math.uni-hamburg.de/math/ign/";
    }
}
