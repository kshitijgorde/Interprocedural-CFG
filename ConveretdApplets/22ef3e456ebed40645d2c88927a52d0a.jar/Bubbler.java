import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bubbler extends Applet implements Runnable
{
    int a149;
    int a150;
    int a151;
    int a152;
    int a153;
    boolean[] a154;
    boolean a165;
    Rectangle[] a155;
    Image a28;
    Image a156;
    Graphics a157;
    boolean a35;
    int a40;
    public boolean started;
    int width;
    int height;
    Thread a46;
    Image a47;
    Graphics a48;
    Image a59;
    
    Rectangle a159(final int n, final int n2) {
        final int n3 = (int)(this.a151 + Math.random() * (this.a152 - this.a151));
        return new Rectangle(n, n2, n3, n3);
    }
    
    public void a160(final Rectangle rectangle, final int n) {
        final int n2 = rectangle.width / 2;
        final int n3 = rectangle.height / 2;
        for (int i = 0; i < rectangle.height; ++i) {
            final int n4 = (int)(2.0 * Math.sqrt(n2 * n2 - (i - n3) * (i - n3)));
            this.a22(new Rectangle(rectangle.x + (rectangle.width - n4) / 2, rectangle.y + i, n4, 1), n);
        }
        if (n == 1) {
            this.a160(new Rectangle(rectangle.x + rectangle.width / 4, rectangle.y + rectangle.height / 4, rectangle.width / 4, rectangle.height / 4), 2);
        }
    }
    
    public void init() {
        this.init("Bubbler");
    }
    
    public void init(final String s) {
        this.a149 = this.a77("bubbleNumber", 10);
        this.a151 = this.a77("minBubbleSize", 10);
        this.a152 = this.a77("maxBubbleSize", 20);
        this.a153 = this.a77("growthRate", 10);
        this.a150 = this.a77("speed", 5);
        this.a154 = new boolean[this.a149];
        this.a165 = (this.getParameter("constant") != null);
        this.a155 = new Rectangle[this.a149];
        for (int i = 0; i < this.a149; ++i) {
            this.a154[i] = (Math.random() > 0.7);
            final int n = (int)(this.a151 + Math.random() * (this.a152 - this.a151));
            this.a155[i] = new Rectangle((int)(Math.random() * this.size().width), (int)(this.size().height * Math.random()), n, n);
        }
        this.a156 = this.createImage(this.size().width * 4, this.size().height);
        this.a157 = this.a156.getGraphics();
        this.width = this.size().width;
        this.height = this.size().height;
        this.a47 = this.createImage(this.width, this.height);
        this.a48 = this.a47.getGraphics();
        this.a40 = this.a77("delay", 20);
        System.out.println(s);
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.a48.clipRect(0, 0, 1, 1);
            this.showStatus("Home page not specified");
        }
        final String host = this.getDocumentBase().getHost();
        if (host == null || host.equals("localhost") || host.equals("127.0.0.1") || host.equals("")) {
            this.a35 = false;
        }
        if (this.getParameter("key") != null) {
            final String a84 = this.a84(host);
            String s2;
            for (s2 = this.getParameter("key"); this.a35 && s2.indexOf(32) > 0; s2 = s2.substring(s2.indexOf(32) + 1, s2.length())) {
                this.a35 = !s2.substring(0, s2.indexOf(32)).equals(a84);
            }
            if (this.a35) {
                this.a35 = !s2.equals(a84);
            }
            if (this.a35 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && host.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a84(this.getParameter("host")))) {
                this.a35 = false;
            }
        }
        if (this.getParameter("background") != null) {
            this.backgroundImage(this.getParameter("background"));
        }
        this.started = true;
    }
    
    int a77(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected String a79(final String s, final String s2) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    protected Color a81(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("") || parameter.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public void start() {
        if (this.a46 == null) {
            (this.a46 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a46 != null && this.a46.isAlive()) {
            this.a46.stop();
            this.a46 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
    }
    
    protected void a83() {
        Thread.yield();
        this.getGraphics().drawImage(this.a47, 0, 0, this);
        Thread.yield();
    }
    
    protected String a84(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void run() {
        while (this.a46 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.render();
            try {
                Thread.sleep(Math.max(7L, this.a40 - System.currentTimeMillis() + currentTimeMillis));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void render() {
        this.a157.drawImage(this.a59, 0, 0, this);
        for (int i = 0; i < this.a149; ++i) {
            this.a160(this.a155[i], 1);
            if (this.a154[i]) {
                if (this.a155[i].y + 2 * this.a155[i].height > -this.a150) {
                    final Rectangle rectangle = this.a155[i];
                    rectangle.y -= this.a150 * this.a155[i].width / 30;
                }
                else if (this.a165) {
                    this.a155[i] = this.a159((int)(Math.random() * this.size().width), (int)(Math.random() * this.size().height));
                }
                if (Math.random() * 100.0 < this.a153) {
                    final Rectangle rectangle2 = this.a155[i];
                    ++rectangle2.width;
                    final Rectangle rectangle3 = this.a155[i];
                    ++rectangle3.height;
                }
            }
            else {
                this.a154[i] = (Math.random() > 0.95);
            }
        }
        if (this.a59 != null) {
            this.a48.drawImage(this.a156, 0, 0, this);
        }
        this.a83();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a155[(int)(Math.random() * this.a149)] = this.a159(n, n2);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean backgroundImage(final String s) {
        final Image image = this.getImage(this.getDocumentBase(), s);
        this.a59 = this.createImage(this.width, this.height);
        final Graphics graphics = this.a59.getGraphics();
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {}
        if (this.getParameter("tile") != null) {
            for (int i = 0; i < this.width; i += image.getWidth(this)) {
                for (int j = 0; j < this.height; j += image.getHeight(this)) {
                    graphics.drawImage(image, i, j, this);
                }
            }
        }
        else {
            graphics.drawImage(image, 0, 0, this.width, this.height, this);
        }
        final Color a81 = this.a81("bubbleColor", Color.black);
        final Image a82 = this.a10(a81.getRGB());
        final Image a83 = this.a10(this.a24(Color.white.getRGB(), a81.getRGB()));
        final Image a84 = this.a10(a81.getRGB());
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        mediaTracker2.addImage(a82, 0);
        mediaTracker2.addImage(a83, 1);
        mediaTracker2.addImage(a84, 2);
        try {
            mediaTracker2.waitForAll();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            this.stop();
        }
        this.a157.drawImage(a82, this.width, 0, this);
        this.a157.drawImage(a83, 2 * this.width, 0, this);
        this.a157.drawImage(a84, 3 * this.width, 0, this);
        return true;
    }
    
    protected Image a10(final int n) {
        final int[] array = new int[this.width * this.height];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.a59, 0, 0, this.width, this.height, array, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            this.stop();
        }
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                array[i + j * this.width] = this.a24(array[i + j * this.width], n);
            }
        }
        return this.createImage(new MemoryImageSource(this.width, this.height, ColorModel.getRGBdefault(), array, 0, this.width));
    }
    
    void a22(Rectangle intersection, final int n) {
        intersection = intersection.intersection(new Rectangle(0, 0, this.width, this.height));
        this.a157.copyArea(intersection.x + this.width * n, intersection.y, intersection.width, intersection.height, -this.width * n, 0);
    }
    
    protected int a24(final int n, final int n2) {
        return 0xFF000000 | ((n & 0xFF0000) + (n2 & 0xFF0000) >> 1 & 0xFF0000) | ((n & 0xFF00) + (n2 & 0xFF00) >> 1 & 0xFF00) | ((n & 0xFF) + (n2 & 0xFF) >> 1 & 0xFF);
    }
    
    public Bubbler() {
        this.a35 = true;
        this.a40 = 20;
        this.started = false;
    }
}
