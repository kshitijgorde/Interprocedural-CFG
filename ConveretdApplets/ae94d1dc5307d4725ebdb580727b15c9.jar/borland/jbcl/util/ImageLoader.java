// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Component;

public class ImageLoader
{
    static ImageCache urlImageCache;
    static ImageCache blobImageCache;
    static ImageCache disabledImageCache;
    
    public static void setUrlImageCacheLimit(final int limit) {
        ImageLoader.urlImageCache.setLimit(limit);
    }
    
    public static int getUrlImageCacheLimit() {
        return ImageLoader.urlImageCache.getLimit();
    }
    
    public static void setBlobImageCacheLimit(final int limit) {
        ImageLoader.blobImageCache.setLimit(limit);
    }
    
    public static int getBlobImageCacheLimit() {
        return ImageLoader.blobImageCache.getLimit();
    }
    
    public static void setDisabledImageCacheLimit(final int limit) {
        ImageLoader.disabledImageCache.setLimit(limit);
    }
    
    public static int getDisabledImageCacheLimit() {
        return ImageLoader.disabledImageCache.getLimit();
    }
    
    public static Image load(final String path, final Component component) {
        try {
            return (component != null) ? component.getToolkit().getImage(path) : Toolkit.getDefaultToolkit().getImage(path);
        }
        catch (SecurityException x) {
            return null;
        }
    }
    
    public static Image load(final String path, final Component component, final boolean wait) {
        final Image image = load(path, component);
        if (image != null && wait) {
            waitForImage(component, image);
        }
        return image;
    }
    
    public static Image load(final URL url, final Component component) {
        Image image = ImageLoader.urlImageCache.get(url);
        if (image != null) {
            return image;
        }
        try {
            image = ((component != null) ? component.getToolkit().getImage(url) : Toolkit.getDefaultToolkit().getImage(url));
        }
        catch (SecurityException ex) {}
        if (image != null) {
            ImageLoader.urlImageCache.put(url, image, component);
        }
        return image;
    }
    
    public static Image load(final URL url, final Component component, final boolean wait) {
        final Image image = load(url, component);
        if (image != null && wait) {
            waitForImage(component, image);
        }
        return image;
    }
    
    public static Image loadFromPath(final SearchPath path, final String name, final Component component) {
        try {
            final Toolkit toolkit = (component == null) ? Toolkit.getDefaultToolkit() : component.getToolkit();
            return toolkit.getImage(path.getPath(name));
        }
        catch (SecurityException x) {
            return null;
        }
    }
    
    public static Image loadFromPath(final SearchPath path, final String name, final Component component, final boolean wait) {
        final Image image = loadFromPath(path, name, component);
        if (image != null && wait) {
            waitForImage(component, image);
        }
        return image;
    }
    
    public static Image loadFromResource(final String name, final Component component) {
        return loadFromResource(name, component, component.getClass());
    }
    
    public static Image loadFromResource(final String name, final Component component, final Class cl) {
        try {
            final URL url = cl.getResource(name);
            if (url == null) {
                return null;
            }
            Image image = ImageLoader.urlImageCache.get(url);
            if (image != null) {
                return image;
            }
            final Object content = url.getContent();
            if (content instanceof Image) {
                image = (Image)content;
            }
            else {
                if (!(content instanceof ImageProducer)) {
                    return null;
                }
                image = component.createImage((ImageProducer)content);
            }
            component.prepareImage(image, component);
            ImageLoader.urlImageCache.put(url, image, component);
            return image;
        }
        catch (IOException e) {
            System.err.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("loadFromResource IOException name=").concat(String.valueOf(name))).concat(String.valueOf(" component="))).concat(String.valueOf(component))).concat(String.valueOf(" cl="))).concat(String.valueOf(cl)));
        }
        catch (Exception e2) {
            System.err.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("loadFromResource Exception name=").concat(String.valueOf(name))).concat(String.valueOf(" component="))).concat(String.valueOf(component))).concat(String.valueOf(" cl="))).concat(String.valueOf(cl)));
        }
        return null;
    }
    
    public static Image loadFromBlob(Object object, final Component component) throws IOException {
        if (object == null) {
            return null;
        }
        if (object instanceof Variant) {
            final Variant value = (Variant)object;
            if (value.isNull()) {
                return null;
            }
            object = value.getBinaryStream();
        }
        Image image = ImageLoader.blobImageCache.get(object);
        if (image != null) {
            return image;
        }
        if (object instanceof Image) {
            image = (Image)object;
        }
        else if (object instanceof InputStream) {
            final InputStream stream = (InputStream)object;
            stream.reset();
            final byte[] buffer = byteArrayFromStream(stream);
            image = Toolkit.getDefaultToolkit().createImage(buffer);
        }
        if (image != null) {
            ImageLoader.blobImageCache.put(object, image, component);
        }
        return image;
    }
    
    public static Image loadFromBlob(final Object object, final Component component, final boolean wait) throws IOException {
        final Image image = loadFromBlob(object, component);
        if (image != null && wait) {
            waitForImage(component, image);
        }
        return image;
    }
    
    private static byte[] byteArrayFromStream(final InputStream s) throws IOException {
        final int len = s.available();
        final byte[] buf = new byte[len];
        int n;
        for (int count = 0; count < len; count += n) {
            n = s.read(buf, count, len - count);
            if (n < 0) {
                return null;
            }
        }
        return buf;
    }
    
    public static boolean waitForImage(final Component component, final Image image) {
        if (image == null) {
            return false;
        }
        if (image.getWidth(null) > 0) {
            return true;
        }
        final MediaTracker m = new MediaTracker(component);
        m.addImage(image, 1);
        try {
            m.waitForID(1);
        }
        catch (InterruptedException ex) {}
        return !m.isErrorID(1);
    }
    
    public static Image getDisabledImage(final Component component, final Image image) {
        Image disabledImage = ImageLoader.disabledImageCache.get(image);
        if (disabledImage != null) {
            return disabledImage;
        }
        final FilteredImageSource fis = new FilteredImageSource(image.getSource(), new DisabledImageFilter());
        disabledImage = ((component == null) ? Toolkit.getDefaultToolkit().createImage(fis) : component.createImage(fis));
        ImageLoader.disabledImageCache.put(image, disabledImage, component);
        return disabledImage;
    }
    
    static {
        ImageLoader.urlImageCache = new ImageCache(32);
        ImageLoader.blobImageCache = new ImageCache(16);
        ImageLoader.disabledImageCache = new ImageCache(48);
    }
}
