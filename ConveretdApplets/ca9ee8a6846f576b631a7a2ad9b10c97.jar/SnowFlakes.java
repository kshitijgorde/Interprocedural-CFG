import java.net.MalformedURLException;
import java.net.URL;
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
import java.awt.Point;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnowFlakes extends Applet implements Runnable
{
    int a317;
    int a138;
    boolean a167;
    boolean a318;
    Point[] a319;
    Image a320;
    Graphics a321;
    String a87;
    String target;
    boolean a32;
    int a7;
    public boolean a168;
    int width;
    int height;
    Thread a18;
    Image a19;
    Graphics a20;
    Image a35;
    Color a322;
    
    public void a323(final Point point, final int n) {
        final int x = point.x;
        final int y = point.y;
        if (n % 2 == 1) {
            this.a165(x + 7, y, 1, 16, 2);
            this.a165(x + 5, y + 1, 5, 1, 2);
            this.a165(x + 6, y + 2, 3, 1, 2);
            this.a165(x + 5, y + 14, 5, 1, 2);
            this.a165(x + 6, y + 13, 3, 1, 2);
            this.a165(x + 5, y + 7, 5, 2, 2);
            this.a165(x + 4, y + 6, 1, 1, 2);
            this.a165(x + 10, y + 6, 1, 1, 2);
            this.a165(x + 4, y + 9, 1, 1, 2);
            this.a165(x + 10, y + 9, 1, 1, 2);
            this.a165(x + 1, y + 10, 3, 3, 2);
            this.a165(x + 1, y + 3, 3, 3, 2);
            this.a165(x + 11, y + 3, 3, 3, 2);
            this.a165(x + 11, y + 10, 3, 3, 2);
            return;
        }
        this.a165(x + 4, y, 1, 12, 1);
        this.a165(x + 3, y + 1, 3, 1, 1);
        this.a165(x + 3, y + 10, 3, 1, 1);
        this.a165(x + 3, y + 5, 3, 1, 1);
        this.a165(x, y + 3, 2, 2, 1);
        this.a165(x, y + 7, 2, 2, 1);
        this.a165(x + 7, y + 3, 2, 2, 1);
        this.a165(x + 7, y + 7, 2, 2, 1);
        this.a165(x + 2, y + 4, 1, 1, 1);
        this.a165(x + 2, y + 7, 1, 1, 1);
        this.a165(x + 6, y + 4, 1, 1, 1);
        this.a165(x + 6, y + 7, 1, 1, 1);
    }
    
    public void init() {
        this.init("flaker");
    }
    
    public void init(final String s) {
        this.a317 = this.a47("flakeNumber", 10);
        this.a138 = this.a47("speed", 5);
        this.a167 = (this.getParameter("constant") != null);
        this.a318 = (this.getParameter("transparent") != null);
        this.a319 = new Point[this.a317];
        this.a322 = this.a50("color", Color.white);
        System.out.println(this.a322);
        for (int i = 0; i < this.a317; ++i) {
            this.a319[i] = new Point((int)(Math.random() * this.size().width), (int)(this.size().height * Math.random()));
        }
        this.a320 = this.createImage(this.size().width * 3, this.size().height);
        (this.a321 = this.a320.getGraphics()).setColor(this.a322);
        this.width = this.size().width;
        this.height = this.size().height;
        this.a19 = this.createImage(this.width, this.height);
        this.a20 = this.a19.getGraphics();
        this.a7 = this.a47("delay", 20);
        System.out.println(s);
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.a20.clipRect(0, 0, 1, 1);
            this.showStatus("Home page not specified");
        }
        final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase == null || lowerCase.equals("localhost") || lowerCase.equals("127.0.0.1") || lowerCase.equals("")) {
            this.a32 = false;
        }
        if (this.getParameter("key") != null) {
            final String a98 = this.a98(lowerCase);
            String s2;
            for (s2 = this.getParameter("key"); this.a32 && s2.indexOf(32) > 0; s2 = s2.substring(s2.indexOf(32) + 1, s2.length())) {
                this.a32 = !s2.substring(0, s2.indexOf(32)).equals(a98);
            }
            if (this.a32) {
                this.a32 = !s2.equals(a98);
            }
            if (this.a32 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && lowerCase.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a98(this.getParameter("host")))) {
                this.a32 = false;
            }
        }
        this.a87 = this.getParameter("link");
        this.target = this.getParameter("target");
        if (this.a32) {
            this.a87 = "http://www.javabase.fsnet.co.uk/";
        }
        if (this.getParameter("background") != null) {
            this.a154(this.getParameter("background"));
        }
        this.a168 = true;
    }
    
    int a47(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected String a66(final String s, final String s2) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    protected Color a50(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("") || parameter.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public void start() {
        if (this.a18 == null) {
            (this.a18 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a18 != null && this.a18.isAlive()) {
            this.a18.stop();
            this.a18 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.a168) {
            this.a148();
            graphics.drawImage(this.a19, 0, 0, this);
        }
    }
    
    protected String a98(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void run() {
        while (this.a18 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.repaint();
            try {
                Thread.sleep(Math.max(7L, this.a7 - System.currentTimeMillis() + currentTimeMillis));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void a148() {
        this.a321.drawImage(this.a35, 0, 0, this);
        for (int i = 0; i < this.a317; ++i) {
            this.a323(this.a319[i], i);
            if (this.a319[i].y < this.height) {
                final Point point = this.a319[i];
                point.y += this.a138 * (1 + i % 2);
            }
            else if (this.a167) {
                this.a319[i] = new Point((int)(Math.random() * this.size().width), -20);
            }
            if (Math.random() > 0.33 + i % 3 / 3.0f) {
                final Point point2 = this.a319[i];
                ++point2.x;
            }
            if (Math.random() < 0.33 + i % 3 / 3.0f) {
                final Point point3 = this.a319[i];
                --point3.x;
            }
            if (Math.random() > 0.8) {
                final Point point4 = this.a319[i];
                ++point4.y;
            }
            if (Math.random() > 0.8) {
                final Point point5 = this.a319[i];
                --point5.y;
            }
            if (this.a319[i].x < -20) {
                final Point point6 = this.a319[i];
                point6.x += this.width + 40;
            }
            if (this.a319[i].x > this.width) {
                final Point point7 = this.a319[i];
                point7.x -= this.width + 40;
            }
        }
        if (this.a35 != null) {
            this.a20.drawImage(this.a320, 0, 0, this);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a319[(int)(Math.random() * this.a317)] = new Point(n, n2);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean a154(final String s) {
        final Image image = this.getImage(this.getDocumentBase(), s);
        this.a35 = this.createImage(this.width, this.height);
        final Graphics graphics = this.a35.getGraphics();
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
        if (this.a318) {
            final Image a161 = this.a161(this.a35, this.a322.getRGB());
            final Image a162 = this.a161(a161, this.a322.getRGB());
            final MediaTracker mediaTracker2 = new MediaTracker(this);
            mediaTracker2.addImage(a161, 0);
            mediaTracker2.addImage(a162, 1);
            try {
                mediaTracker2.waitForAll();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
                this.stop();
            }
            this.a321.drawImage(a161, this.width, 0, this);
            this.a321.drawImage(a162, 2 * this.width, 0, this);
        }
        return true;
    }
    
    protected Image a161(final Image image, final int n) {
        final int[] array = new int[this.width * this.height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.width, this.height, array, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            this.stop();
        }
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                array[i + j * this.width] = this.a166(array[i + j * this.width], n);
            }
        }
        return this.createImage(new MemoryImageSource(this.width, this.height, ColorModel.getRGBdefault(), array, 0, this.width));
    }
    
    void a165(int n, int n2, int n3, int n4, final int n5) {
        if (n < 0) {
            n3 += n;
            n = 0;
        }
        if (n > this.width) {
            return;
        }
        if (n2 < 0) {
            n4 += n2;
            n2 = 0;
        }
        if (n2 > this.height) {
            return;
        }
        n3 = ((n + n3 > this.width) ? (this.width - n) : n3);
        n4 = ((n4 + n2 > this.height) ? (this.height - n2) : n4);
        if (!this.a318) {
            this.a321.fillRect(n, n2, n3, n4);
            return;
        }
        this.a321.copyArea(n + this.width * n5, n2, n3, n4, -this.width * n5, 0);
    }
    
    protected int a166(final int n, final int n2) {
        return 0xFF000000 | ((n & 0xFF0000) + (n2 & 0xFF0000) >> 1 & 0xFF0000) | ((n & 0xFF00) + (n2 & 0xFF00) >> 1 & 0xFF00) | ((n & 0xFF) + (n2 & 0xFF) >> 1 & 0xFF);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        URL url = null;
        try {
            url = new URL(this.a87);
        }
        catch (MalformedURLException ex) {
            try {
                url = new URL(this.getDocumentBase(), this.a87);
            }
            catch (MalformedURLException ex2) {}
        }
        if (url != null) {
            if (this.target != null && this.target != "") {
                this.getAppletContext().showDocument(url, this.target);
            }
            else {
                this.getAppletContext().showDocument(url);
            }
        }
        return true;
    }
    
    public SnowFlakes() {
        this.a32 = true;
        this.a168 = false;
    }
}
