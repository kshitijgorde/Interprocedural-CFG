import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRfade2Free extends Applet implements Runnable, MouseListener
{
    boolean started;
    boolean okToAnimate;
    boolean thrRun;
    boolean loadall;
    boolean finishedload;
    boolean mousein;
    boolean veryslow;
    int arraySize;
    int framesPerSecond;
    int timeFading;
    int currentImage;
    int nextImage;
    int numOfImages;
    int numOfFrame;
    int frameTime;
    int imageTime;
    int Width;
    int Height;
    Image[] image;
    Image[] frame;
    Graphics pad;
    Thread thread;
    String[] imageNames;
    String hyperlink;
    
    public void init() {
        this.setBackground(this.parmgetcolor("startupcolor", "255,255,255"));
    }
    
    public void startUp(final Graphics graphics) {
        this.started = true;
        final String parmgetstr = this.parmgetstr("loadingmessage", " ");
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        graphics.setColor(this.parmgetcolor("textcolor", "0,0,0"));
        graphics.drawString(parmgetstr, (this.size().width - fontMetrics.stringWidth(parmgetstr)) / 2, (this.size().height + fontMetrics.getHeight()) / 2);
        this.framesPerSecond = 24;
        this.timeFading = 500;
        this.numOfFrame = this.framesPerSecond * this.timeFading / 1000;
        this.frameTime = 1000 / this.framesPerSecond;
        this.getImages(graphics);
        this.Width = this.size().width;
        this.Height = this.size().height;
        this.thread = new Thread(this);
        this.thrRun = true;
        this.thread.start();
        this.addMouseListener(this);
    }
    
    public void start() {
        this.veryslow = false;
    }
    
    public void run() {
        while (this.thrRun) {
            if (!this.veryslow) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (!this.finishedload) {
                    this.image[this.nextImage] = this.downloadImage(this.imageNames[this.nextImage]);
                }
                this.frame = new Image[this.numOfFrame + 1];
                for (int i = 1; i <= this.numOfFrame; ++i) {
                    if (this.thrRun) {
                        this.frame[i] = this.createImage(this.Width, this.Height);
                        (this.pad = this.frame[i].getGraphics()).drawImage(this.image[this.nextImage], 0, 0, this.Width, this.Height, this);
                        this.pad.drawImage(this.createImage(new FilteredImageSource(this.image[this.currentImage].getSource(), new FadeFilter(255 - i * 255 / this.numOfFrame))), 0, 0, this.Width, this.Height, this);
                    }
                }
                long n = new Integer(this.imageTime) - System.currentTimeMillis() + currentTimeMillis;
                if (n < 0L) {
                    n = 0L;
                }
                for (int n2 = 0; n2 < n / 1000L; ++n2) {
                    this.pause(1000L);
                    this.repaint();
                }
                this.pause(n - n / 1000L * 1000L);
                this.okToAnimate = true;
                this.repaint();
                try {
                    this.thread.suspend();
                }
                catch (SecurityException ex) {}
            }
            else {
                this.pause(1000L);
            }
        }
    }
    
    public void stop() {
        this.veryslow = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.started) {
            this.startUp(graphics);
        }
        if (this.okToAnimate) {
            this.animate(graphics);
        }
    }
    
    public void animate(final Graphics graphics) {
        this.okToAnimate = false;
        for (int i = 1; i <= this.numOfFrame; ++i) {
            graphics.drawImage(this.frame[i], 0, 0, this);
            this.pause(this.frameTime);
        }
        ++this.currentImage;
        ++this.nextImage;
        if (this.currentImage > this.numOfImages) {
            this.currentImage = 1;
        }
        if (this.nextImage > this.numOfImages) {
            this.nextImage = 1;
            this.finishedload = true;
        }
        this.pointerUpdate();
        try {
            this.thread.resume();
        }
        catch (SecurityException ex) {}
    }
    
    public void update(final Graphics graphics) {
        final Image image = this.createImage(this.size().width, this.size().height);
        image.getGraphics().drawImage(this.image[this.currentImage], 0, 0, this.size().width, this.size().height, this);
        graphics.drawImage(image, 0, 0, this);
        this.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mousein = true;
        this.pointerUpdate();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mousein = false;
        this.pointerUpdate();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
            if (this.hyperlink.substring(0, 2).equals("./")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.hyperlink.substring(2)));
                    return;
                }
                catch (MalformedURLException ex) {
                    return;
                }
            }
            try {
                if (this.hyperlink != "noparam") {
                    this.getAppletContext().showDocument(new URL(this.hyperlink));
                }
            }
            catch (MalformedURLException ex2) {}
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void pointerUpdate() {
        if (this.mousein && this.hyperlink != "noparam") {
            this.setCursor(new Cursor(12));
            this.showStatus(this.hyperlink);
            return;
        }
        this.setCursor(new Cursor(0));
    }
    
    public void getImages(final Graphics graphics) {
        this.parmList();
        this.numOfImages = this.arraySize;
        this.image = new Image[this.numOfImages + 1];
        this.loadall = false;
        if (this.loadall) {
            for (int i = 1; i <= this.arraySize; ++i) {
                this.image[i] = this.downloadImage(this.imageNames[i]);
            }
            this.finishedload = true;
        }
        else {
            this.image[1] = this.downloadImage(this.imageNames[1]);
        }
        graphics.drawImage(this.image[1], 0, 0, this.size().width, this.size().height, this);
        System.gc();
    }
    
    public void parmList() {
        boolean b = true;
        this.arraySize = 0;
        int n = 1;
        while (b) {
            if (this.parmgetstr("image" + Integer.toString(n), "noparam") != "noparam") {
                ++this.arraySize;
            }
            else {
                b = false;
            }
            ++n;
        }
        this.imageNames = new String[this.arraySize + 1];
        for (int i = 1; i <= this.arraySize; ++i) {
            this.imageNames[i] = this.parmgetstr("image" + Integer.toString(i), "noparam");
        }
        this.hyperlink = "http://www.net800.co.uk/netstart/sirius";
        if (this.hyperlink.length() != 39) {
            System.exit(0);
        }
        this.imageTime = this.parmgetint("time", 0, 0, 300000);
    }
    
    public int parmgetint(final String s, final int n, final int n2, final int n3) {
        final String parameter = this.getParameter(s);
        int int1;
        if (parameter == null) {
            int1 = n;
        }
        else {
            int1 = Integer.parseInt(parameter);
        }
        if (int1 > n3) {
            int1 = n3;
        }
        if (int1 < n2) {
            int1 = n2;
        }
        return int1;
    }
    
    public String parmgetstr(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String s3;
        if (parameter == null) {
            s3 = s2;
        }
        else {
            s3 = parameter;
        }
        return s3;
    }
    
    public void pause(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public Color parmgetcolor(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.parmgetstr(s, s2), ",");
        return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
    }
    
    public Image downloadImage(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            this.showStatus("Loading Image...");
            mediaTracker.waitForID(0);
            this.showStatus("");
            if (this.mousein && this.hyperlink != "noparam") {
                this.showStatus(this.hyperlink);
            }
        }
        catch (InterruptedException ex) {
            return this.createImage(this.size().width, this.size().height);
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        return this.createImage(new MemoryImageSource(width, height, array, 0, width));
    }
    
    public SIRfade2Free() {
        this.started = false;
        this.okToAnimate = false;
        this.loadall = false;
        this.finishedload = false;
        this.veryslow = false;
        this.currentImage = 1;
        this.nextImage = 2;
    }
}
