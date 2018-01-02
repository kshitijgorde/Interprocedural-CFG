import java.awt.Event;
import java.util.Observable;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.util.Observer;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollingUpdate extends Canvas implements Runnable, Observer
{
    protected int imageWidth;
    protected int imageHeight;
    private Rectangle displayArea;
    private Thread animation;
    private int frameDelay;
    private boolean pause;
    private boolean clearScreen;
    private boolean imageUpdated;
    private FontMetrics fm;
    private NetworkTextFile textUpdate;
    private Font displayFont;
    private Color textColor;
    private boolean notifyOnRead;
    private boolean textFileChecked;
    private long textFileCheckedIndicatorTime;
    String[] textToScroll;
    public static final int LEFT = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    private int justify;
    private Image backgroundImage;
    private Image textBackground;
    private Image textImage;
    private Image compositImage;
    
    public synchronized void stop() {
        this.textUpdate.stopRefresh();
    }
    
    public ScrollingUpdate(final URL url, final String s, final int n, final int n2, final boolean notifyOnRead, final String s2, final int n3, final int n4, final Color textColor, final int n5, final int frameDelay, final String s3, final Rectangle displayArea) throws MalformedURLException {
        this.clearScreen = true;
        this.textColor = textColor;
        this.displayFont = new Font(s2, n4, n3);
        this.justify = ((n5 < 0 || n5 > 3) ? 1 : n5);
        this.frameDelay = frameDelay;
        this.displayArea = displayArea;
        this.notifyOnRead = notifyOnRead;
        this.backgroundImage = Toolkit.getDefaultToolkit().getImage(new URL(url, s3));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.backgroundImage, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.imageWidth = this.backgroundImage.getWidth(this);
        this.imageHeight = this.backgroundImage.getHeight(this);
        this.reshape(0, 0, this.imageWidth, this.imageHeight);
        this.fm = Toolkit.getDefaultToolkit().getFontMetrics(this.displayFont);
        (this.textUpdate = new NetworkTextFile(url, s, n, this.fm, this.displayArea.width, n2, this.notifyOnRead)).addObserver(this);
        this.repaint();
    }
    
    public void setFrameRate(final int frameDelay) {
        this.frameDelay = frameDelay;
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.textFileChecked) {
            if (this.textFileCheckedIndicatorTime == 0L) {
                this.textFileCheckedIndicatorTime = System.currentTimeMillis() + 500L;
            }
            else if (System.currentTimeMillis() > this.textFileCheckedIndicatorTime) {
                this.textFileCheckedIndicatorTime = 0L;
                this.textFileChecked = false;
                this.clearScreen = true;
            }
            else {
                graphics.setColor(Color.black);
                graphics.drawRect(1, 1, 4, 4);
                graphics.setColor(Color.red);
                graphics.fillRect(2, 2, 3, 3);
            }
        }
        if (!this.imageUpdated || this.clearScreen) {
            if (this.animation != null) {
                this.compositImage.getGraphics().drawImage(this.backgroundImage, 0, 0, null);
                this.compositImage.getGraphics().drawImage(this.textImage, this.displayArea.x, this.displayArea.y, null);
                graphics.drawImage(this.compositImage, 0, 0, null);
            }
            else {
                graphics.drawImage(this.backgroundImage, 0, 0, null);
            }
            this.clearScreen = false;
            return;
        }
        graphics.drawImage(this.textImage, this.displayArea.x, this.displayArea.y, null);
        this.imageUpdated = false;
    }
    
    public void addNotify() {
        super.addNotify();
        this.textBackground = this.createImage(new FilteredImageSource(this.backgroundImage.getSource(), new CropImageFilter(this.displayArea.x, this.displayArea.y, this.displayArea.width, this.displayArea.height)));
        this.textImage = this.createImage(this.displayArea.width, this.displayArea.height);
        this.compositImage = this.createImage(this.imageWidth, this.imageHeight);
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        if (((String)o).equals("C")) {
            if (this.animation != null) {
                this.animation.stop();
                this.animation = null;
            }
            this.textToScroll = this.textUpdate.getText();
            if (this.textToScroll != null) {
                (this.animation = new Thread(this)).start();
            }
        }
        if (this.notifyOnRead) {
            this.textFileChecked = true;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void start() {
        this.textUpdate.startRefresh();
    }
    
    public String getClassInfo() {
        return "ScrollingUpdate v1.1 copyright (C) 1997 by Modern Minds, Inc.";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.pause = !this.pause;
        return true;
    }
    
    public void run() {
        final int descent = this.fm.getDescent();
        final int n = this.fm.getAscent() + this.fm.getLeading();
        final int n2 = descent + n;
        final int n3 = this.displayArea.height / n2 + 3;
        int n4 = 0;
        int n5 = 0;
        int n6 = n2 * (n3 - 1) - descent;
        int n7 = (this.textToScroll.length - n4 < n3) ? (this.textToScroll.length - n4) : n3;
        while (true) {
            if (!this.pause) {
                try {
                    Thread.sleep(this.frameDelay);
                }
                catch (InterruptedException ex) {}
                if (n6 < -descent) {
                    if (++n4 == this.textToScroll.length) {
                        n4 = 0;
                        n6 = n2 * (n3 - 1) - descent;
                    }
                    else {
                        n6 = n;
                    }
                    n7 = ((this.textToScroll.length - n4 < n3) ? (this.textToScroll.length - n4) : n3);
                }
                synchronized (this) {
                    final Graphics graphics = this.textImage.getGraphics();
                    if (this.textBackground != null) {
                        graphics.drawImage(this.textBackground, 0, 0, null);
                    }
                    graphics.setColor(this.textColor);
                    graphics.setFont(this.displayFont);
                    int n8 = n6--;
                    for (int i = n4; i < n4 + n7; ++i) {
                        if (this.justify != 1) {
                            n5 = this.displayArea.width - this.fm.stringWidth(this.textToScroll[i]);
                            if (this.justify == 2) {
                                n5 /= 2;
                            }
                        }
                        graphics.drawString(this.textToScroll[i], n5, n8);
                        n8 += n2;
                    }
                    this.imageUpdated = true;
                    this.paint(this.getGraphics());
                }
            }
            else {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
}
