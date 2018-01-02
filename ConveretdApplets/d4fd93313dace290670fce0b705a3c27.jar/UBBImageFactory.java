import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.Component;
import java.net.URL;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBImageFactory implements ImageObserver
{
    String name;
    private URL loadBase;
    private Component parent;
    UBBErrorHandler error;
    private Hashtable managedImages;
    private Hashtable managedImageNames;
    int imageFullLoadStatus;
    private static final int UPDATES_DONE = 224;
    private static final int IMAGE_READY = 35;
    
    public Image createImage(final int n, final int n2) {
        return this.parent.createImage(n, n2);
    }
    
    public Image createImage(final MemoryImageSource memoryImageSource) {
        return this.parent.createImage(memoryImageSource);
    }
    
    public UBBImageFactory(final Component parent, final String s, final URL loadBase, final UBBErrorHandler error) {
        this.name = ((s != null) ? s : this.getClass().getName());
        this.parent = parent;
        this.loadBase = loadBase;
        this.error = error;
        this.managedImages = new Hashtable(23, 0.5f);
        this.managedImageNames = new Hashtable(23, 0.5f);
    }
    
    public synchronized UBBImageProxy loadImage(final String s) {
        return this.loadImage(s, true);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        final UBBImageProxy ubbImageProxy = this.managedImages.get(image);
        if (ubbImageProxy != null) {
            ubbImageProxy.updateStatus(n, n2, n3, n4, n5);
        }
        return (n & 0xE0) == 0x0;
    }
    
    public synchronized UBBImageProxy loadImage(final String s, boolean b) {
        String nextToken = null;
        String nextToken2 = null;
        UBBImageProxy ubbImageProxy = this.managedImageNames.get(s);
        if (ubbImageProxy != null) {
            return ubbImageProxy;
        }
        final String nextToken3 = new StringTokenizer(s, "+@#").nextToken();
        final int index = s.indexOf(43);
        if (index >= 0) {
            nextToken2 = new StringTokenizer(s.substring(index + 1), "@#").nextToken();
        }
        final int index2 = s.indexOf(35);
        if (index2 >= 0) {
            nextToken = new StringTokenizer(s.substring(index2 + 1), "@+").nextToken();
            b = true;
        }
        final int index3 = s.indexOf(64);
        if (index3 > 0) {
            final UBBImageProxy loadImage = this.loadImage(nextToken3, true);
            if (loadImage != null && loadImage.isReady()) {
                final String nextToken4 = new StringTokenizer(s.substring(index3 + 1), "#+").nextToken();
                final int[] array = { 0, 0, 0, 0 };
                final StringTokenizer stringTokenizer = new StringTokenizer(nextToken4, ",");
                int n = 0;
                do {
                    if (stringTokenizer.hasMoreTokens()) {
                        try {
                            array[n] = Integer.parseInt(stringTokenizer.nextToken().trim());
                        }
                        catch (Exception ex) {
                            this.error.notify(this.name, 0, "Image sub-area " + s, ex);
                        }
                    }
                } while (++n < 4);
                ubbImageProxy = new UBBImageProxy(this, loadImage.getImage(), s, new Rectangle(array[0], array[1], array[2], array[3]), this.error);
            }
        }
        else {
            try {
                final Image image = Toolkit.getDefaultToolkit().getImage((this.loadBase == null) ? new URL(nextToken3) : new URL(this.loadBase, nextToken3));
                ubbImageProxy = new UBBImageProxy(this, image, s, null, this.error);
                if (image == null) {
                    throw new Exception("Get image " + s);
                }
                ImageObserver imageObserver;
                if (b) {
                    this.imageFullLoadStatus = 0;
                    imageObserver = new ImageObserver() {
                        public boolean imageUpdate(final Image image, final int imageFullLoadStatus, final int n, final int n2, final int n3, final int n4) {
                            UBBImageFactory.this.imageFullLoadStatus = imageFullLoadStatus;
                            if ((imageFullLoadStatus & 0xC0) != 0x0) {
                                UBBImageFactory.this.error.notify(UBBImageFactory.this.name, 0, "Can't load image " + s, null);
                                return false;
                            }
                            return (imageFullLoadStatus & 0x20) == 0x0;
                        }
                    };
                }
                else {
                    imageObserver = this;
                }
                if (Toolkit.getDefaultToolkit().prepareImage(image, -1, -1, imageObserver)) {
                    ubbImageProxy.updateStatus(35, 0, 0, image.getWidth(null), image.getHeight(null));
                }
                else if (b) {
                    while ((this.imageFullLoadStatus & 0xE0) == 0x0) {
                        try {
                            Thread.sleep(200L);
                        }
                        catch (InterruptedException ex4) {}
                    }
                    if ((this.imageFullLoadStatus & 0xC0) == 0x0) {
                        ubbImageProxy.updateStatus(35, 0, 0, image.getWidth(null), image.getHeight(null));
                    }
                }
                this.managedImages.put(image, ubbImageProxy);
            }
            catch (Exception ex2) {
                this.error.notify(this.name, 0, "Can't load image", ex2);
                ubbImageProxy = null;
            }
        }
        if (ubbImageProxy != null) {
            this.managedImageNames.put(s, ubbImageProxy);
            if (index2 > 0) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, ",");
                final int[] array2 = { 0, 0 };
                int n2 = 0;
                do {
                    if (stringTokenizer2.hasMoreTokens()) {
                        try {
                            array2[n2] = Integer.parseInt(stringTokenizer2.nextToken().trim());
                        }
                        catch (Exception ex3) {
                            this.error.notify(this.name, 0, "Image scale " + s, ex3);
                        }
                    }
                } while (++n2 < 2);
                ubbImageProxy.scaleImage(array2[0], array2[1]);
            }
        }
        if (index >= 0) {
            ubbImageProxy.applyEffects(nextToken2, false);
        }
        return ubbImageProxy;
    }
    
    public UBBImageProxy manageImage(final String s, final Image image, final boolean b, final boolean b2) {
        Image image2 = image;
        UBBImageProxy ubbImageProxy = null;
        if (b2 && image != null) {
            Image image3 = this.parent.createImage(image.getWidth(null), image.getHeight(null));
            try {
                final Graphics graphics = image3.getGraphics();
                graphics.drawImage(image, 0, 0, null);
                graphics.dispose();
            }
            catch (Exception ex) {
                this.error.notify(this.name, 0, "manageImage copy " + s, ex);
                image3 = null;
            }
            image2 = image3;
        }
        if (image2 != null) {
            ubbImageProxy = new UBBImageProxy(this, image2, s, null, this.error);
            if (b && s != null) {
                this.managedImages.put(image2, ubbImageProxy);
                this.managedImageNames.put(s, ubbImageProxy);
            }
            ubbImageProxy.updateStatus(35, 0, 0, image2.getWidth(null), image2.getHeight(null));
        }
        return ubbImageProxy;
    }
    
    public void releaseManagedImages() {
        if (this.managedImages != null) {
            this.managedImages.clear();
        }
        if (this.managedImageNames != null) {
            this.managedImageNames.clear();
        }
    }
    
    public static final Color parseColor(final String s) {
        Color color;
        try {
            color = new Color(Integer.parseInt(s, 16));
        }
        catch (Exception ex) {
            final int[] array = { 0, 0, 0 };
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "-");
            int n = 0;
            do {
                if (stringTokenizer.hasMoreTokens()) {
                    try {
                        array[n] = Math.min(255, Integer.parseInt(stringTokenizer.nextToken().trim()));
                    }
                    catch (Exception ex2) {
                        System.out.println("<Bad Color> " + s);
                    }
                }
            } while (++n < 3);
            color = new Color(array[0], array[1], array[2]);
        }
        return color;
    }
}
