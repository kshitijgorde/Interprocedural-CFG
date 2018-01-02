// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.image.PixelGrabber;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.io.ObjectInputStream;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.Serializable;

public class ImageIcon implements Icon, Serializable
{
    transient Image image;
    transient int loadStatus;
    ImageObserver imageObserver;
    String description;
    protected static final Component component;
    protected static final MediaTracker tracker;
    int width;
    int height;
    
    static {
        component = new Component() {};
        tracker = new MediaTracker(ImageIcon.component);
    }
    
    public ImageIcon() {
        this.loadStatus = 0;
        this.description = null;
        this.width = -1;
        this.height = -1;
    }
    
    public ImageIcon(final Image image) {
        this.loadStatus = 0;
        this.description = null;
        this.width = -1;
        this.height = -1;
        this.image = image;
        final Object property = image.getProperty("comment", this.imageObserver);
        if (property instanceof String) {
            this.description = (String)property;
        }
        this.loadImage(image);
    }
    
    public ImageIcon(final Image image, final String description) {
        this(image);
        this.description = description;
    }
    
    public ImageIcon(final String s) {
        this(s, s);
    }
    
    public ImageIcon(final String s, final String description) {
        this.loadStatus = 0;
        this.description = null;
        this.width = -1;
        this.height = -1;
        this.image = Toolkit.getDefaultToolkit().getImage(s);
        this.description = description;
        this.loadImage(this.image);
    }
    
    public ImageIcon(final URL url) {
        this(url, url.toExternalForm());
    }
    
    public ImageIcon(final URL url, final String description) {
        this.loadStatus = 0;
        this.description = null;
        this.width = -1;
        this.height = -1;
        this.image = Toolkit.getDefaultToolkit().getImage(url);
        this.description = description;
        this.loadImage(this.image);
    }
    
    public ImageIcon(final byte[] array) {
        this.loadStatus = 0;
        this.description = null;
        this.width = -1;
        this.height = -1;
        this.image = Toolkit.getDefaultToolkit().createImage(array);
        if (this.image == null) {
            return;
        }
        final Object property = this.image.getProperty("comment", this.imageObserver);
        if (property instanceof String) {
            this.description = (String)property;
        }
        this.loadImage(this.image);
    }
    
    public ImageIcon(final byte[] array, final String description) {
        this.loadStatus = 0;
        this.description = null;
        this.width = -1;
        this.height = -1;
        this.image = Toolkit.getDefaultToolkit().createImage(array);
        if (this.image == null) {
            return;
        }
        this.description = description;
        this.loadImage(this.image);
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getIconHeight() {
        return this.height;
    }
    
    public int getIconWidth() {
        return this.width;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public int getImageLoadStatus() {
        return this.loadStatus;
    }
    
    public ImageObserver getImageObserver() {
        return this.imageObserver;
    }
    
    protected void loadImage(final Image image) {
        synchronized (ImageIcon.tracker) {
            ImageIcon.tracker.addImage(image, 0);
            try {
                ImageIcon.tracker.waitForID(0, 5000L);
            }
            catch (InterruptedException ex) {
                System.out.println("INTERRUPTED while loading Image");
            }
            this.loadStatus = ImageIcon.tracker.statusID(0, false);
            ImageIcon.tracker.removeImage(image, 0);
            this.width = image.getWidth(this.imageObserver);
            this.height = image.getHeight(this.imageObserver);
        }
        // monitorexit(ImageIcon.tracker)
    }
    
    public synchronized void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        if (this.imageObserver == null) {
            graphics.drawImage(this.image, n, n2, component);
        }
        else {
            graphics.drawImage(this.image, n, n2, this.imageObserver);
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        final int int1 = objectInputStream.readInt();
        final int int2 = objectInputStream.readInt();
        final int[] array = (int[])objectInputStream.readObject();
        if (array != null) {
            this.image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(int1, int2, ColorModel.getRGBdefault(), array, 0, int1));
        }
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setImage(final Image image) {
        this.loadImage(this.image = image);
    }
    
    public void setImageObserver(final ImageObserver imageObserver) {
        this.imageObserver = imageObserver;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final int iconWidth = this.getIconWidth();
        final int iconHeight = this.getIconHeight();
        final int[] array = (int[])((this.image != null) ? new int[iconWidth * iconHeight] : null);
        if (this.image != null) {
            try {
                final PixelGrabber pixelGrabber = new PixelGrabber(this.image, 0, 0, iconWidth, iconHeight, array, 0, iconWidth);
                pixelGrabber.grabPixels();
                if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
                    throw new IOException("failed to load image contents");
                }
            }
            catch (InterruptedException ex) {
                throw new IOException("image load interrupted");
            }
        }
        objectOutputStream.writeInt(iconWidth);
        objectOutputStream.writeInt(iconHeight);
        objectOutputStream.writeObject(array);
    }
}
