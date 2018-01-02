import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import java.io.DataInputStream;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wiper extends Applet implements Runnable
{
    String[][] pinfo;
    String ainfo;
    Thread thread;
    int secondsBetweenSleeps;
    int secondsBetweenWipes;
    int secondsBetweenFrames;
    Vector effectvect;
    Hashtable bitmaphash;
    Hashtable wipehash;
    LineReader linereader;
    int width;
    int height;
    Bitmap bitmap;
    public static int wipeBits;
    public static int wipeFactor;
    Image backdrop;
    Effect lastEffect;
    Effect effect;
    URL url;
    boolean mouseInside;
    int mouseX;
    int mouseY;
    int index;
    
    public String[][] getParameterInfo() {
        return this.pinfo;
    }
    
    public String getAppletInfo() {
        return this.ainfo;
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    String getParameterSafely(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    int getIntParamSafely(final String s, final String s2) {
        return Integer.valueOf(this.getParameterSafely(s, s2));
    }
    
    double getDoubleParamSafely(final String s, final String s2) {
        return Double.valueOf(this.getParameterSafely(s, s2));
    }
    
    public void init() {
        final Rectangle bounds = this.bounds();
        this.width = bounds.width;
        this.height = bounds.height;
        this.bitmap = new Bitmap(this.width, this.height);
        this.backdrop = this.createImage(this.bitmap.prepare());
        this.effectvect = new Vector();
        this.bitmaphash = new Hashtable();
        this.wipehash = new Hashtable();
        this.secondsBetweenSleeps = (int)(1000.0 * this.getDoubleParamSafely("secondsBetweenSleeps", "0.100"));
        this.secondsBetweenWipes = (int)(1000.0 * this.getDoubleParamSafely("secondsBetweenWipes", "3.000"));
        this.secondsBetweenFrames = (int)(1000.0 * this.getDoubleParamSafely("secondsBetweenFrames", "0.100"));
        Wiper.wipeBits = this.getIntParamSafely("wipeBits", "5");
        Wiper.wipeFactor = 1 << Wiper.wipeBits;
        Wiper.wipeBits = 8 - Wiper.wipeBits;
        final String parameterSafely = this.getParameterSafely("src", "play.txt");
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), parameterSafely).openStream());
        }
        catch (Exception ex) {}
        this.linereader = new LineReader(dataInputStream, this);
    }
    
    Bitmap loadBitmap(final String s) {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (Exception ex) {}
        if (this.bitmaphash.containsKey(url)) {
            return (Bitmap)this.bitmaphash.get(url);
        }
        final Bitmap bitmap = new Bitmap(this.getImage(url), this.width, this.height);
        this.bitmaphash.put(url, bitmap);
        return bitmap;
    }
    
    Wipe loadWipe(final Bitmap bitmap) {
        if (this.wipehash.containsKey(bitmap)) {
            return this.wipehash.get(bitmap);
        }
        final Wipe wipe = new Wipe(bitmap);
        this.wipehash.put(bitmap, wipe);
        return wipe;
    }
    
    public void addLine(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String nextToken = stringTokenizer.nextToken();
        String s2 = stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreElements()) {
            s2 = stringTokenizer.nextToken();
        }
        URL documentBase = this.getDocumentBase();
        try {
            documentBase = new URL(this.getDocumentBase(), nextToken);
        }
        catch (Exception ex) {}
        this.effectvect.addElement(new Effect(documentBase, this.loadBitmap(s2), this.loadWipe(this.loadBitmap(s2))));
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this.backdrop) {
            graphics.drawImage(this.backdrop, 0, 0, this);
        }
        // monitorexit(this.backdrop)
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void mouseIt(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.effect == null) {
            return;
        }
        Effect effect = this.lastEffect;
        if (this.effect.get(mouseX, mouseY)) {
            effect = this.effect;
        }
        if (effect == null) {
            return;
        }
        final URL url = effect.getURL();
        if (url == this.url) {
            return;
        }
        this.url = url;
        this.showStatus(this.url.toString());
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.mouseIt(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.mouseIt(n, n2);
        if (this.url != null) {
            this.getAppletContext().showDocument(this.url);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseInside = true;
        this.mouseIt(n, n2);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseInside = false;
        return true;
    }
    
    public void run() {
        final Waiter waiter = new Waiter(this.secondsBetweenWipes);
        final Waiter waiter2 = new Waiter(this.secondsBetweenFrames);
        while (this.effectvect.isEmpty()) {
            try {
                Thread.sleep(this.secondsBetweenSleeps);
            }
            catch (Exception ex) {}
        }
        while (true) {
            if (this.index >= this.effectvect.size()) {
                this.index = 0;
            }
            this.lastEffect = this.effect;
            waiter.go();
            (this.effect = this.effectvect.elementAt(this.index++)).start();
            boolean go;
            do {
                go = this.effect.go(this.bitmap);
                if (this.mouseInside) {
                    this.mouseIt(this.mouseX, this.mouseY);
                }
                waiter2.go();
                this.backdrop = this.createImage(this.bitmap.prepare());
                this.update(this.getGraphics());
            } while (!go);
            waiter.go();
        }
    }
    
    public Wiper() {
        this.pinfo = new String[][] { { "src", "URL", "location of play list" }, { "secondsBetweenSleeps", "double", "seconds between sleeps" }, { "secondsBetweenWipes", "double", "seconds between wipe effects" }, { "secondsBetweenFrames", "double", "seconds between display frames" }, { "wipeBits", "int", "frame count equal to two to this power" } };
        this.ainfo = "Wiper v1.0 (c) 1996 Bryan McNett - distributed under the terms of the GNU General Public License";
        this.mouseInside = false;
    }
    
    static {
        Wiper.wipeBits = 5;
        Wiper.wipeFactor = 32;
    }
}
