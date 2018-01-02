import java.awt.Container;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageCanvas extends Canvas
{
    protected Image itsImage;
    protected boolean imageValid;
    protected static String rootPath;
    protected static Applet appletEnv;
    protected static boolean getImagesAsResources;
    protected static final int INVALID_IMAGE_SIZE = 20;
    protected static final boolean AS_RESOURCE = true;
    
    public ImageCanvas() {
        this.itsImage = null;
        this.imageValid = true;
        this.itsImage = null;
    }
    
    public ImageCanvas(final String imagePath) {
        this.itsImage = null;
        this.imageValid = true;
        this.itsImage = this.getImage(imagePath);
    }
    
    public ImageCanvas(final Image img) {
        this.itsImage = null;
        this.imageValid = true;
        this.itsImage = img;
    }
    
    protected Image getImage(String imagePath) {
        final Toolkit tk = Toolkit.getDefaultToolkit();
        if (ImageCanvas.getImagesAsResources) {
            final String resPath = this.extendFilePath(imagePath, true);
            final InputStream imgStream = this.getClass().getResourceAsStream(resPath);
            if (imgStream != null) {
                try {
                    final byte[] imgBytes = new byte[imgStream.available()];
                    imgStream.read(imgBytes);
                    return tk.createImage(imgBytes);
                }
                catch (IOException ex) {}
            }
        }
        imagePath = this.extendFilePath(imagePath);
        if (ImageCanvas.appletEnv != null) {
            return ImageCanvas.appletEnv.getImage(ImageCanvas.appletEnv.getCodeBase(), imagePath);
        }
        return tk.getImage(imagePath);
    }
    
    public static void setRootPath(final String newRootPath) {
        ImageCanvas.rootPath = newRootPath;
    }
    
    public static void useImageResources(final boolean state) {
        ImageCanvas.getImagesAsResources = state;
    }
    
    public static void setAppletEnvironment(final Applet anApplet) {
        ImageCanvas.appletEnv = anApplet;
    }
    
    public String extendFilePath(final String filePath) {
        return this.extendFilePath(filePath, false);
    }
    
    public String extendFilePath(final String filePath, final boolean asResource) {
        if (filePath == null || !filePath.startsWith("$/")) {
            return filePath;
        }
        if (ImageCanvas.rootPath != null) {
            return String.valueOf(ImageCanvas.rootPath) + filePath.substring(2);
        }
        if (ImageCanvas.appletEnv == null && !asResource) {
            String newPath = System.getProperty("user.dir");
            if (!newPath.endsWith(System.getProperty("file.separator"))) {
                newPath = String.valueOf(newPath) + System.getProperty("file.separator");
            }
            newPath = String.valueOf(newPath) + filePath.substring(2);
            return newPath;
        }
        return filePath.substring(2);
    }
    
    public Dimension minimumSize() {
        return this.preferredSizeAux(super.minimumSize());
    }
    
    public Dimension preferredSize() {
        return this.preferredSizeAux(super.preferredSize());
    }
    
    protected Dimension preferredSizeAux(final Dimension trialSize) {
        if ((trialSize.width == 0 || trialSize.height == 0) && this.itsImage != null) {
            for (int j = 0; j < 20 && this.imageValid; ++j) {
                if (this.prepareImage(this.itsImage, this)) {
                    final int width = this.itsImage.getWidth(this);
                    final int height = this.itsImage.getHeight(this);
                    if (width >= 0 && height >= 0) {
                        trialSize.width = width;
                        trialSize.height = height;
                        return trialSize;
                    }
                }
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            if (!this.imageValid) {
                final int n = 20;
                trialSize.height = n;
                trialSize.width = n;
            }
            else {
                this.invalidate();
            }
        }
        else if ((trialSize.width == 0 || trialSize.height == 0) && this.itsImage == null) {
            final int n2 = 20;
            trialSize.height = n2;
            trialSize.width = n2;
        }
        return trialSize;
    }
    
    public void paint(final Graphics g) {
        if (!this.isValid()) {
            Container lastParent;
            for (Container parent = lastParent = this.getParent(); parent != null; parent = parent.getParent()) {
                parent.invalidate();
                lastParent = parent;
            }
            if (lastParent != null) {
                lastParent.validate();
            }
        }
        if (this.itsImage != null && this.imageValid) {
            g.drawImage(this.itsImage, 0, 0, this.getBackground(), this);
        }
        else {
            g.setColor(Color.red);
            g.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
            g.drawString("??", 2, g.getFontMetrics().getAscent() + 2);
        }
    }
    
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        final boolean result = super.imageUpdate(img, infoflags, x, y, width, height);
        if ((infoflags & 0xC0) != 0x0) {
            this.imageValid = false;
        }
        return result;
    }
    
    static {
        ImageCanvas.rootPath = null;
        ImageCanvas.appletEnv = null;
        ImageCanvas.getImagesAsResources = true;
    }
}
