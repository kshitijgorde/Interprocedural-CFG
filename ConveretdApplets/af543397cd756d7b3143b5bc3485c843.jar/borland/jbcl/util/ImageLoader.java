// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.MediaTracker;
import sun.awt.image.ByteArrayImageSource;
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
    
    public static Image load(final String s, final Component component) {
        return (component != null) ? component.getToolkit().getImage(s) : Toolkit.getDefaultToolkit().getImage(s);
    }
    
    public static Image load(final String s, final Component component, final boolean b) {
        final Image load = load(s, component);
        if (b) {
            waitForImage(component, load);
        }
        return load;
    }
    
    public static Image load(final URL url, final Component component) {
        final Image value = ImageLoader.urlImageCache.get(url);
        if (value != null) {
            return value;
        }
        final Image image = (component != null) ? component.getToolkit().getImage(url) : Toolkit.getDefaultToolkit().getImage(url);
        ImageLoader.urlImageCache.put(url, image);
        return image;
    }
    
    public static Image load(final URL url, final Component component, final boolean b) {
        final Image load = load(url, component);
        if (b) {
            waitForImage(component, load);
        }
        return load;
    }
    
    public static Image loadFromPath(final SearchPath searchPath, final String s, final Component component) {
        return ((component == null) ? Toolkit.getDefaultToolkit() : component.getToolkit()).getImage(searchPath.getPath(s));
    }
    
    public static Image loadFromPath(final SearchPath searchPath, final String s, final Component component, final boolean b) {
        final Image loadFromPath = loadFromPath(searchPath, s, component);
        if (b) {
            waitForImage(component, loadFromPath);
        }
        return loadFromPath;
    }
    
    public static Image loadFromResource(final String s, final Component component) {
        return loadFromResource(s, component, component.getClass());
    }
    
    public static Image loadFromResource(final String s, final Component component, final Class clazz) {
        try {
            final URL resource = clazz.getResource(s);
            if (resource == null) {
                return null;
            }
            final Image value = ImageLoader.urlImageCache.get(resource);
            if (value != null) {
                return value;
            }
            final Object content = resource.getContent();
            Image image;
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
            ImageLoader.urlImageCache.put(resource, image);
            return image;
        }
        catch (IOException ex) {}
        catch (Exception ex2) {}
        return null;
    }
    
    public static Image loadFromBlob(Object binaryStream, final Component component) throws IOException {
        if (binaryStream == null) {
            return null;
        }
        if (binaryStream instanceof Variant) {
            final Variant variant = (Variant)binaryStream;
            if (variant.isNull()) {
                return null;
            }
            binaryStream = variant.getBinaryStream();
        }
        Image image = ImageLoader.blobImageCache.get(binaryStream);
        if (image != null) {
            return image;
        }
        if (binaryStream instanceof Image) {
            image = (Image)binaryStream;
        }
        else if (binaryStream instanceof InputStream) {
            final InputStream inputStream = (InputStream)binaryStream;
            inputStream.reset();
            image = component.createImage(new ByteArrayImageSource(byteArrayFromStream(inputStream)));
        }
        ImageLoader.blobImageCache.put(binaryStream, image);
        return image;
    }
    
    public static Image loadFromBlob(final Object o, final Component component, final boolean b) throws IOException {
        final Image loadFromBlob = loadFromBlob(o, component);
        if (b) {
            waitForImage(component, loadFromBlob);
        }
        return loadFromBlob;
    }
    
    private static byte[] byteArrayFromStream(final InputStream inputStream) throws IOException {
        final int available = inputStream.available();
        final byte[] array = new byte[available];
        int read;
        for (int i = 0; i < available; i += read) {
            read = inputStream.read(array, i, available - i);
            if (read < 0) {
                return null;
            }
        }
        return array;
    }
    
    public static boolean waitForImage(final Component component, final Image image) {
        if (image.getWidth(null) > 0) {
            return true;
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex) {}
        return !mediaTracker.isErrorID(1);
    }
    
    public static Image getDisabledImage(final Component component, final Image image) {
        final Image value = ImageLoader.disabledImageCache.get(image);
        if (value != null) {
            return value;
        }
        final FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), new DisabledImageFilter());
        final Image image2 = (component == null) ? Toolkit.getDefaultToolkit().createImage(filteredImageSource) : component.createImage(filteredImageSource);
        ImageLoader.disabledImageCache.put(image, image2);
        return image2;
    }
    
    static {
        ImageLoader.urlImageCache = new ImageCache(32);
        ImageLoader.blobImageCache = new ImageCache(16);
        ImageLoader.disabledImageCache = new ImageCache(48);
    }
}
