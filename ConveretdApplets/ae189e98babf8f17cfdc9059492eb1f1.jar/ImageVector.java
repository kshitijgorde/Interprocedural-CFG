import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.IndexColorModel;
import java.net.URL;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageVector implements ImageObserver
{
    private static final int ERR_WIDTH = 50;
    private static final int ERR_HEIGHT = 50;
    private static Toolkit tk;
    private static Image errImg;
    private URL[] dataURL;
    private int[] dataWidth;
    private int[] dataHeight;
    private Image[] dataImage;
    private int size;
    private int index;
    
    protected synchronized void addElement(final URL url, final String s, final int n, final int n2) {
        if (this.size == this.dataURL.length) {
            this.changeCapacity(2 * this.size);
        }
        this.dataWidth[this.size] = n;
        this.dataHeight[this.size] = n2;
        URL url2;
        Image image;
        try {
            url2 = new URL(url, s);
            image = ImageVector.tk.getImage(new URL(url, s));
            ImageVector.tk.prepareImage(image, n, n2, null);
        }
        catch (Exception ex) {
            url2 = null;
            image = null;
            if (this.dataWidth[this.size] < 0) {
                this.dataWidth[this.size] = 50;
            }
            if (this.dataHeight[this.size] < 0) {
                this.dataHeight[this.size] = 50;
            }
        }
        this.dataURL[this.size] = url2;
        this.dataImage[this.size] = image;
        ++this.size;
    }
    
    protected void trimToSize() {
        if (this.size < this.dataURL.length) {
            this.changeCapacity(this.size);
        }
    }
    
    protected void reset() {
        this.index = 0;
    }
    
    protected boolean hasMoreElements() {
        return this.index < this.size;
    }
    
    protected synchronized HtmlImage nextElement() {
        if (this.dataImage[this.index] != null) {
            final Image image = this.dataImage[this.index];
            int width;
            int height;
            if (!this.imageSizeAvailable(image, this.dataWidth[this.index], this.dataHeight[this.index])) {
                this.dataURL[this.index] = null;
                width = 50;
                height = 50;
            }
            else {
                width = image.getWidth(null);
                height = image.getHeight(null);
            }
            this.dataImage[this.index] = null;
            if (this.dataWidth[this.index] < 0) {
                this.dataWidth[this.index] = width;
            }
            if (this.dataHeight[this.index] < 0) {
                this.dataHeight[this.index] = height;
            }
        }
        Image image2;
        if (this.dataURL[this.index] == null) {
            image2 = ImageVector.errImg;
        }
        else {
            image2 = ImageVector.tk.getImage(this.dataURL[this.index]);
        }
        ImageVector.tk.prepareImage(image2, this.dataWidth[this.index], this.dataHeight[this.index], null);
        final HtmlImage htmlImage = new HtmlImage(-1, -1, this.dataWidth[this.index], this.dataHeight[this.index], image2);
        ++this.index;
        return htmlImage;
    }
    
    private String statusString(final int n) {
        String s = "";
        if ((n & 0x80) != 0x0) {
            s = String.valueOf(s) + "ABORT ";
        }
        if ((n & 0x20) != 0x0) {
            s = String.valueOf(s) + "ALLBITS ";
        }
        if ((n & 0x40) != 0x0) {
            s = String.valueOf(s) + "ERROR ";
        }
        if ((n & 0x10) != 0x0) {
            s = String.valueOf(s) + "FRAMEBITS ";
        }
        if ((n & 0x2) != 0x0) {
            s = String.valueOf(s) + "HEIGHT ";
        }
        if ((n & 0x4) != 0x0) {
            s = String.valueOf(s) + "PROPERTIES ";
        }
        if ((n & 0x8) != 0x0) {
            s = String.valueOf(s) + "SOMEBITS ";
        }
        if ((n & 0x1) != 0x0) {
            s = String.valueOf(s) + "WIDTH ";
        }
        return String.valueOf(s) + n;
    }
    
    public synchronized boolean imageUpdate(final Image image, int checkImage, final int n, final int n2, final int n3, final int n4) {
        if ((checkImage & 0xC0) != 0x0) {
            this.notify();
            return true;
        }
        checkImage = ImageVector.tk.checkImage(image, -1, -1, null);
        if ((checkImage & 0x3) == 0x3) {
            this.notify();
            return true;
        }
        return false;
    }
    
    private synchronized boolean imageSizeAvailable(final Image image, final int n, final int n2) {
        while (true) {
            try {
                while (true) {
                    final int checkImage = ImageVector.tk.checkImage(image, n, n2, this);
                    if ((checkImage & 0xC0) != 0x0) {
                        return false;
                    }
                    if ((checkImage & 0x3) == 0x3) {
                        return true;
                    }
                    this.wait();
                }
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
    
    private void changeCapacity(final int n) {
        final int[] dataWidth = new int[n];
        System.arraycopy(this.dataWidth, 0, dataWidth, 0, this.size);
        this.dataWidth = dataWidth;
        final int[] dataHeight = new int[n];
        System.arraycopy(this.dataHeight, 0, dataHeight, 0, this.size);
        this.dataHeight = dataHeight;
        final URL[] dataURL = new URL[n];
        System.arraycopy(this.dataURL, 0, dataURL, 0, this.size);
        this.dataURL = dataURL;
        final Image[] dataImage = new Image[n];
        System.arraycopy(this.dataImage, 0, dataImage, 0, this.size);
        this.dataImage = dataImage;
    }
    
    ImageVector() {
        this.dataURL = new URL[100];
        this.dataWidth = new int[100];
        this.dataHeight = new int[100];
        this.dataImage = new Image[100];
    }
    
    static {
        ImageVector.tk = Toolkit.getDefaultToolkit();
        final IndexColorModel indexColorModel = new IndexColorModel(1, 1, new byte[] { -1 }, new byte[] { 0 }, new byte[] { 0 });
        final byte[] array = new byte[2500];
        for (int i = 0; i < 2500; ++i) {
            array[i] = 0;
        }
        ImageVector.errImg = ImageVector.tk.createImage(new MemoryImageSource(50, 50, indexColorModel, array, 0, 50));
        ImageVector.tk.prepareImage(ImageVector.errImg, -1, -1, null);
    }
}
