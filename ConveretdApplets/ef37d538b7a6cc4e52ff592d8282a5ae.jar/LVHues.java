import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Frame;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class LVHues extends Applet implements Runnable
{
    static final int PERIOD = 15000;
    static final int SLEEPTIME = 0;
    static final String CLASS_NAME = "LVHues";
    static final String VERSION = "2.2";
    static final String MESSAGE = "Loading...";
    static final Color BACKGROUNDCOLOR;
    static final Color FOREGROUNDCOLOR;
    static final Font FONT;
    boolean loadedImage;
    boolean useMask;
    int width;
    int height;
    int imageWidth;
    int imageHeight;
    int area;
    int period;
    int step;
    int sleepTime;
    int boost;
    int[] pixels;
    int[] imagePixels;
    int[] maskPixels;
    String imageName;
    String maskName;
    String message;
    String target;
    Font font;
    URL url;
    Color backgroundColor;
    Color foregroundColor;
    Image image;
    Image maskImage;
    Image displayImage;
    PixelGrabber pixelGrabber;
    MediaTracker mediaTracker;
    Thread LVHuesThread;
    PixelArray pixelArray;
    PixelArray[] bluePixelArray;
    
    public void init() {
        this.setBackground(this.backgroundColor = this.readColorParameter("BgColor", LVHues.BACKGROUNDCOLOR));
        this.getParent().setBackground(this.backgroundColor);
        this.getParent().repaint();
        this.width = this.size().width;
        this.height = this.size().height;
        this.foregroundColor = this.readColorParameter("FgColor", LVHues.FOREGROUNDCOLOR);
        final int intParameter = this.readIntParameter("Period", 15000);
        this.period = intParameter;
        if (intParameter <= 0) {
            this.period = 15000;
        }
        this.step = this.period / 6;
        if ((this.sleepTime = this.readIntParameter("SleepTime", 0)) < 0) {
            this.sleepTime = 0;
        }
        this.mediaTracker = new MediaTracker(this);
        if ((this.imageName = this.readStringParameter("Image", null)) != null) {
            this.image = this.getImage(this.getDocumentBase(), this.imageName);
            this.mediaTracker.addImage(this.image, 0);
            this.mediaTracker.checkID(0, true);
        }
        this.boost = this.readIntParameter("MoreSpeed", this.imageName.toLowerCase().endsWith("jpg") ? 3 : 0);
        this.boost = Math.max(Math.min(this.boost, 7), -7);
        if ((this.maskName = this.readStringParameter("Mask", null)) != null) {
            this.useMask = true;
            this.maskImage = this.getImage(this.getDocumentBase(), this.maskName);
            this.mediaTracker.addImage(this.maskImage, 1);
        }
        else {
            this.useMask = false;
        }
        this.message = this.readStringParameter("Message", "Loading...");
        this.font = this.readFontParameter("Font", LVHues.FONT);
        this.url = this.readURLParameter("Link", null);
        this.target = this.readStringParameter("Target", "_self");
        this.loadedImage = false;
    }
    
    public void start() {
        if (this.url != null && this.getParent() instanceof Frame) {
            ((Frame)this.getParent()).setCursor(12);
        }
        if (this.LVHuesThread == null) {
            this.LVHuesThread = new Thread(this);
        }
        this.LVHuesThread.start();
    }
    
    public void stop() {
        this.LVHuesThread.stop();
        this.LVHuesThread = null;
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        this.displayImage = this.createImage(this.width, this.height);
        final Graphics graphics = this.displayImage.getGraphics();
        graphics.setColor(this.foregroundColor);
        graphics.setFont(this.font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(this.message, (this.width - fontMetrics.stringWidth(this.message)) / 2, (this.height - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2 + fontMetrics.getAscent());
        this.hueShiftPaint(this.getGraphics());
        if (!this.loadedImage) {
            try {
                this.mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
            this.loadImage();
            this.loadedImage = true;
        }
        final Graphics graphics2 = this.getGraphics();
        while (Thread.currentThread() == this.LVHuesThread) {
            final int n = (int)(System.currentTimeMillis() % this.period);
            for (int i = 0; i < 256; ++i) {
                this.pixelArray = this.bluePixelArray[i];
                while (this.pixelArray != null) {
                    final int n2 = (n + this.pixelArray.offset) % this.period;
                    final int n3 = this.pixelArray.delta * (n2 % this.step) / this.step;
                    int n4 = 0;
                    int n5 = 0;
                    int n6 = 0;
                    switch (n2 / this.step) {
                        case 0: {
                            n4 = this.pixelArray.max;
                            n5 = this.pixelArray.min + n3;
                            n6 = this.pixelArray.min;
                            break;
                        }
                        case 1: {
                            n4 = this.pixelArray.max - n3;
                            n5 = this.pixelArray.max;
                            n6 = this.pixelArray.min;
                            break;
                        }
                        case 2: {
                            n4 = this.pixelArray.min;
                            n5 = this.pixelArray.max;
                            n6 = this.pixelArray.min + n3;
                            break;
                        }
                        case 3: {
                            n4 = this.pixelArray.min;
                            n5 = this.pixelArray.max - n3;
                            n6 = this.pixelArray.max;
                            break;
                        }
                        case 4: {
                            n4 = this.pixelArray.min + n3;
                            n5 = this.pixelArray.min;
                            n6 = this.pixelArray.max;
                            break;
                        }
                        default: {
                            n4 = this.pixelArray.max;
                            n5 = this.pixelArray.min;
                            n6 = this.pixelArray.max - n3;
                            break;
                        }
                    }
                    if (this.pixelArray.size > 0) {
                        if (this.useMask) {
                            for (int j = 0; j < this.pixelArray.size; ++j) {
                                final int n7 = this.maskPixels[this.pixelArray.pixels[j]];
                                final int n8 = this.imagePixels[this.pixelArray.pixels[j]];
                                this.pixels[this.pixelArray.pixels[j]] = (0xFF000000 | (n4 * n7 + (n8 >> 16 & 0xFF) * (255 - n7)) / 255 << 16 | (n5 * n7 + (n8 >> 8 & 0xFF) * (255 - n7)) / 255 << 8 | (n6 * n7 + (n8 & 0xFF) * (255 - n7)) / 255);
                            }
                        }
                        else {
                            final int n9 = 0xFF000000 | n4 << 16 | n5 << 8 | n6;
                            for (int k = 0; k < this.pixelArray.size; ++k) {
                                this.pixels[this.pixelArray.pixels[k]] = n9;
                            }
                        }
                    }
                    this.pixelArray = this.pixelArray.next;
                }
            }
            this.displayImage = this.createImage(new MemoryImageSource(this.imageWidth, this.imageHeight, this.pixels, 0, this.imageWidth));
            this.hueShiftPaint(graphics2);
            System.gc();
            try {
                if (this.sleepTime <= 0) {
                    continue;
                }
                Thread.currentThread();
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        this.hueShiftPaint(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.hueShiftPaint(graphics);
    }
    
    public void hueShiftPaint(final Graphics graphics) {
        if (this.LVHuesThread != null && this.displayImage != null) {
            graphics.drawImage(this.displayImage, 0, 0, null);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.url == null) {
            this.showStatus("LVHues 2.2 by Luciano Vernaschi");
        }
        else {
            this.showStatus(this.url.toExternalForm());
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.url != null) {
            this.getAppletContext().showDocument(this.url, this.target);
        }
        return true;
    }
    
    void storePixels(final int[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            final int n2 = array[i] >> 16 & 0xFF;
            final int n3 = array[i] >> 8 & 0xFF;
            final int n4 = array[i] & 0xFF;
            this.pixelArray = this.bluePixelArray[n4];
            while (this.pixelArray != null) {
                if (this.pixelArray.red == n2 && this.pixelArray.green == n3) {
                    this.pixelArray.add(i);
                    break;
                }
                this.pixelArray = this.pixelArray.next;
            }
            if (this.pixelArray == null) {
                (this.bluePixelArray[n4] = new PixelArray(n2, n3, n4, this.step, this.bluePixelArray[n4])).add(i);
            }
        }
    }
    
    void loadImage() {
        this.imageWidth = this.image.getWidth(null);
        this.imageHeight = this.image.getHeight(null);
        this.area = this.imageWidth * this.imageHeight;
        this.pixels = new int[this.area];
        this.imagePixels = new int[this.area];
        this.pixelGrabber = new PixelGrabber(this.image, 0, 0, this.imageWidth, this.imageHeight, this.imagePixels, 0, this.imageWidth);
        try {
            this.pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        if (this.boost > 0) {
            final int n = (int)Math.pow(2.0, this.boost) - 1;
            final int n2 = n << 16 | n << 8 | n;
            for (int i = 0; i < this.area; ++i) {
                final int[] imagePixels = this.imagePixels;
                final int n3 = i;
                imagePixels[n3] |= n2;
            }
        }
        else if (this.boost < 0) {
            final int n4 = 256 - (int)Math.pow(2.0, -this.boost);
            final int n5 = n4 << 16 | n4 << 8 | n4;
            for (int j = 0; j < this.area; ++j) {
                final int[] imagePixels2 = this.imagePixels;
                final int n6 = j;
                imagePixels2[n6] &= n5;
            }
        }
        this.bluePixelArray = new PixelArray[256];
        for (int k = 0; k < 256; ++k) {
            this.bluePixelArray[k] = new PixelArray(0, 0, k, this.step, null);
        }
        this.storePixels(this.imagePixels, this.area);
        if (this.useMask) {
            this.maskPixels = new int[this.area];
            this.pixelGrabber = new PixelGrabber(this.maskImage, 0, 0, this.imageWidth, this.imageHeight, this.maskPixels, 0, this.imageWidth);
            try {
                this.pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {}
            for (int l = 0; l < this.area; ++l) {
                final int[] maskPixels = this.maskPixels;
                final int n7 = l;
                maskPixels[n7] &= 0xFF;
            }
        }
    }
    
    int readIntParameter(final String s, int int1) {
        try {
            int1 = Integer.parseInt(this.getParameter(s));
        }
        catch (Exception ex) {}
        return int1;
    }
    
    String readStringParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    Color readColorParameter(final String s, Color color) {
        try {
            color = new Color(Integer.parseInt(this.getParameter(s), 16));
        }
        catch (Exception ex) {}
        return color;
    }
    
    Font readFontParameter(final String s, final Font font) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return font;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, " ,;+\t\n\r");
        Font font2;
        try {
            font2 = new Font(stringTokenizer.nextToken(), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return font;
        }
        return font2;
    }
    
    URL readURLParameter(final String s, final URL url) {
        URL url2 = null;
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return url;
        }
        try {
            url2 = new URL(parameter);
        }
        catch (MalformedURLException ex) {}
        if (url2 == null) {
            try {
                url2 = new URL(this.getDocumentBase(), parameter);
            }
            catch (MalformedURLException ex2) {}
        }
        return url2;
    }
    
    static {
        BACKGROUNDCOLOR = new Color(255, 255, 255);
        FOREGROUNDCOLOR = new Color(0, 0, 0);
        FONT = new Font("Helvetica", 0, 12);
    }
}
