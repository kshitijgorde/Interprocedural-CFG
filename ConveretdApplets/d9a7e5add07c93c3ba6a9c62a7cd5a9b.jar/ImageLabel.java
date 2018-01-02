import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.net.URL;
import java.awt.Container;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageLabel extends Canvas
{
    private Image image;
    private static String defaultImageString;
    private String imageString;
    private boolean debug;
    private int border;
    private Color borderColor;
    private int width;
    private int height;
    private boolean explicitSize;
    private int explicitWidth;
    private int explicitHeight;
    private MediaTracker tracker;
    private static int lastTrackerID;
    private int currentTrackerID;
    private boolean doneLoading;
    private Container parentContainer;
    
    public ImageLabel() {
        this(ImageLabel.defaultImageString);
    }
    
    public ImageLabel(final String s) {
        this(makeURL(s));
    }
    
    public ImageLabel(final URL url) {
        this(loadImage(url));
        this.imageString = url.toExternalForm();
    }
    
    public ImageLabel(final URL url, final String imageString) {
        this(makeURL(url, imageString));
        this.imageString = imageString;
    }
    
    public ImageLabel(final Image image) {
        this.imageString = "<Existing Image>";
        this.debug = false;
        this.border = 0;
        this.borderColor = null;
        this.explicitSize = false;
        this.explicitWidth = 0;
        this.explicitHeight = 0;
        this.doneLoading = false;
        this.image = image;
        this.tracker = new MediaTracker(this);
        this.currentTrackerID = ImageLabel.lastTrackerID++;
        this.tracker.addImage(image, this.currentTrackerID);
    }
    
    public void waitForImage(final boolean b) {
        if (!this.doneLoading) {
            this.debug("[waitForImage] - Resizing and waiting for " + this.imageString);
            try {
                this.tracker.waitForID(this.currentTrackerID);
            }
            catch (InterruptedException ex2) {}
            catch (Exception ex) {
                System.out.println("Error loading " + this.imageString + ": " + ex.getMessage());
                ex.printStackTrace();
            }
            if (this.tracker.isErrorID(0)) {
                new Throwable("Error loading image " + this.imageString).printStackTrace();
            }
            this.doneLoading = true;
            if (this.explicitWidth != 0) {
                this.width = this.explicitWidth;
            }
            else {
                this.width = this.image.getWidth(this) + 2 * this.border;
            }
            if (this.explicitHeight != 0) {
                this.height = this.explicitHeight;
            }
            else {
                this.height = this.image.getHeight(this) + 2 * this.border;
            }
            this.resize(this.width, this.height);
            this.debug("[waitForImage] - " + this.imageString + " is " + this.width + "x" + this.height + ".");
            final Container parent = this.getParent();
            this.parentContainer = parent;
            if (parent != null && b) {
                this.setBackground(this.parentContainer.getBackground());
                this.parentContainer.layout();
            }
        }
    }
    
    public void centerAt(final int n, final int n2) {
        this.debug("Placing center of " + this.imageString + " at (" + n + "," + n2 + ")");
        this.move(n - this.width / 2, n2 - this.height / 2);
    }
    
    public synchronized boolean inside(final int n, final int n2) {
        return n >= 0 && n <= this.width && n2 >= 0 && n2 <= this.height;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.doneLoading) {
            this.waitForImage(true);
        }
        else {
            if (this.explicitSize) {
                graphics.drawImage(this.image, this.border, this.border, this.width - 2 * this.border, this.height - 2 * this.border, this);
            }
            else {
                graphics.drawImage(this.image, this.border, this.border, this);
            }
            this.drawRect(graphics, 0, 0, this.width - 1, this.height - 1, this.border, this.borderColor);
        }
    }
    
    public Dimension preferredSize() {
        if (!this.doneLoading) {
            this.waitForImage(false);
        }
        return super.preferredSize();
    }
    
    public Dimension minimumSize() {
        if (!this.doneLoading) {
            this.waitForImage(false);
        }
        return super.minimumSize();
    }
    
    public void resize(final int explicitWidth, final int explicitHeight) {
        if (!this.doneLoading) {
            this.explicitSize = true;
            if (explicitWidth > 0) {
                this.explicitWidth = explicitWidth;
            }
            if (explicitHeight > 0) {
                this.explicitHeight = explicitHeight;
            }
        }
        super.resize(explicitWidth, explicitHeight);
    }
    
    public void reshape(final int n, final int n2, final int explicitWidth, final int explicitHeight) {
        if (!this.doneLoading) {
            this.explicitSize = true;
            if (explicitWidth > 0) {
                this.explicitWidth = explicitWidth;
            }
            if (explicitHeight > 0) {
                this.explicitHeight = explicitHeight;
            }
        }
        super.reshape(n, n2, explicitWidth, explicitHeight);
    }
    
    protected void drawRect(final Graphics graphics, int n, int n2, int n3, int n4, final int n5, final Color color) {
        graphics.setColor(color);
        for (int i = 0; i < n5; ++i) {
            graphics.drawRect(n, n2, n3, n4);
            if (i < n5 - 1) {
                ++n;
                ++n2;
                n3 -= 2;
                n4 -= 2;
            }
        }
    }
    
    protected void debug(final String s) {
        if (this.debug) {
            System.out.println(s);
        }
    }
    
    private static URL makeURL(final String s) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL " + s + ": " + ex);
            ex.printStackTrace();
        }
        return url;
    }
    
    private static URL makeURL(final URL url, final String s) {
        URL url2 = null;
        try {
            url2 = new URL(url, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL " + url.toExternalForm() + ", " + s + ": " + ex);
            ex.printStackTrace();
        }
        return url2;
    }
    
    private static Image loadImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public int getBorder() {
        return this.border;
    }
    
    public void setBorder(final int border) {
        this.border = border;
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    protected boolean hasExplicitSize() {
        return this.explicitSize;
    }
    
    public static String getDefaultImageString() {
        return ImageLabel.defaultImageString;
    }
    
    public static void setDefaultImageString(final String defaultImageString) {
        ImageLabel.defaultImageString = defaultImageString;
    }
    
    protected String getImageString() {
        return this.imageString;
    }
    
    public boolean isDebugging() {
        return this.debug;
    }
    
    public void setIsDebugging(final boolean debug) {
        this.debug = debug;
    }
    
    static {
        ImageLabel.defaultImageString = "http://java.sun.com/lib/images/logo.java.color-transp.55x60.gif";
        ImageLabel.lastTrackerID = 0;
    }
}
