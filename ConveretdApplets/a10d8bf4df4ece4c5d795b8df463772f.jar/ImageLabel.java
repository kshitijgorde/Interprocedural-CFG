import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Component;
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
    
    static {
        ImageLabel.defaultImageString = "http://java.sun.com/lib/images/logo.java.color-transp.55x60.gif";
        ImageLabel.lastTrackerID = 0;
    }
    
    public ImageLabel() {
        this(Mine.hiddenimage);
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
    
    public void centerAt(final int n, final int n2) {
        this.debug("Placing center of " + this.imageString + " at (" + n + "," + n2 + ")");
        this.move(n - this.width / 2, n2 - this.height / 2);
    }
    
    protected void debug(final String s) {
        if (this.debug) {
            System.out.println(s);
        }
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
    
    public int getBorder() {
        return this.border;
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public static String getDefaultImageString() {
        return ImageLabel.defaultImageString;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    protected String getImageString() {
        return this.imageString;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    protected boolean hasExplicitSize() {
        return this.explicitSize;
    }
    
    public synchronized boolean inside(final int n, final int n2) {
        return n >= 0 && n <= this.width && n2 >= 0 && n2 <= this.height;
    }
    
    public boolean isDebugging() {
        return this.debug;
    }
    
    private static Image loadImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
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
    
    public Dimension minimumSize() {
        if (!this.doneLoading) {
            this.waitForImage(false);
        }
        return super.minimumSize();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image, this.border, this.border, this);
    }
    
    public Dimension preferredSize() {
        if (!this.doneLoading) {
            this.waitForImage(false);
        }
        return super.preferredSize();
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
    
    public void setBorder(final int border) {
        this.border = border;
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public static void setDefaultImageString(final String defaultImageString) {
        ImageLabel.defaultImageString = defaultImageString;
    }
    
    public void setIsDebugging(final boolean debug) {
        this.debug = debug;
    }
    
    protected void setNewImage(final int n) {
        if (n == 0) {
            this.image = Mine.a0image;
        }
        if (n == 1) {
            this.image = Mine.a1image;
        }
        if (n == 2) {
            this.image = Mine.a2image;
        }
        if (n == 3) {
            this.image = Mine.a3image;
        }
        if (n == 4) {
            this.image = Mine.a4image;
        }
        if (n == 5) {
            this.image = Mine.a5image;
        }
        if (n == 6) {
            this.image = Mine.a6image;
        }
        if (n == 7) {
            this.image = Mine.a7image;
        }
        if (n == 8) {
            this.image = Mine.a8image;
        }
        if (n == 9) {
            this.image = Mine.hiddenimage;
        }
        if (n == 10) {
            this.image = Mine.flagimage;
        }
        if (n == 11) {
            this.image = Mine.mineimage;
        }
        if (n == 12) {
            this.image = Mine.expmineimage;
        }
        this.repaint();
    }
    
    protected void setNewImage(final Image image) {
        this.image = image;
        this.repaint();
    }
    
    protected void setNewImage(final URL url, final String imageString) {
        this.imageString = imageString;
        final URL url2 = makeURL(url, imageString);
        System.out.println("The file is ");
        final Image loadImage = loadImage(url2);
        System.out.println(imageString);
        this.image = loadImage;
        this.tracker = new MediaTracker(this);
        this.currentTrackerID = ImageLabel.lastTrackerID++;
        this.tracker.addImage(this.image, this.currentTrackerID);
        this.waitForNewImage(true);
        this.repaint();
        System.out.println(imageString);
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
    
    public void waitForNewImage(final boolean b) {
        this.debug("[waitForImage] - Resizing and waiting for " + this.imageString);
        try {
            this.tracker.waitForID(this.currentTrackerID);
        }
        catch (InterruptedException ex2) {}
        catch (Exception ex) {
            System.out.println("Error loading " + this.imageString + ": " + ex.getMessage());
            ex.printStackTrace();
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
}
