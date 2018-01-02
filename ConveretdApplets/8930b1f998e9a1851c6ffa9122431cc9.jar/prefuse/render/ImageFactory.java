// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import prefuse.data.Tuple;
import java.util.Iterator;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Toolkit;
import prefuse.util.io.IOLib;
import java.awt.Image;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.awt.MediaTracker;
import java.awt.Component;
import java.util.Map;

public class ImageFactory
{
    protected int m_imageCacheSize;
    protected int m_maxImageWidth;
    protected int m_maxImageHeight;
    protected boolean m_asynch;
    protected Map imageCache;
    protected Map loadMap;
    protected final Component component;
    protected final MediaTracker tracker;
    protected int nextTrackerID;
    
    public ImageFactory() {
        this(-1, -1);
    }
    
    public ImageFactory(final int n, final int n2) {
        this.m_imageCacheSize = 3000;
        this.m_maxImageWidth = 100;
        this.m_maxImageHeight = 100;
        this.m_asynch = true;
        this.imageCache = new LinkedHashMap((int)(this.m_imageCacheSize + 1.3333334f), 0.75f, true) {
            public boolean removeEldestEntry(final Map.Entry entry) {
                return this.size() > ImageFactory.this.m_imageCacheSize;
            }
        };
        this.loadMap = new HashMap(50);
        this.component = new Component() {};
        this.tracker = new MediaTracker(this.component);
        this.nextTrackerID = 0;
        this.setMaxImageDimensions(n, n2);
    }
    
    public void setMaxImageDimensions(final int maxImageWidth, final int maxImageHeight) {
        this.m_maxImageWidth = maxImageWidth;
        this.m_maxImageHeight = maxImageHeight;
    }
    
    public void setImageCacheSize(final int imageCacheSize) {
        this.m_imageCacheSize = imageCacheSize;
    }
    
    public boolean isInCache(final String s) {
        return this.imageCache.containsKey(s);
    }
    
    public Image getImage(final String s) {
        final Image image = this.imageCache.get(s);
        if (image == null && !this.loadMap.containsKey(s)) {
            final URL urlFromString = IOLib.urlFromString(s);
            if (urlFromString == null) {
                System.err.println("Null image: " + s);
                return null;
            }
            final Image image2 = Toolkit.getDefaultToolkit().createImage(urlFromString);
            if (!this.m_asynch) {
                this.waitForImage(image2);
                this.addImage(s, image2);
            }
            else {
                final int n = ++this.nextTrackerID;
                this.tracker.addImage(image2, n);
                this.loadMap.put(s, new LoadMapEntry(n, image2));
            }
        }
        else {
            if (image != null || !this.loadMap.containsKey(s)) {
                return image;
            }
            final LoadMapEntry loadMapEntry = this.loadMap.get(s);
            if (this.tracker.checkID(loadMapEntry.id, true)) {
                this.addImage(s, loadMapEntry.image);
                this.loadMap.remove(s);
                this.tracker.removeImage(loadMapEntry.image, loadMapEntry.id);
            }
        }
        return this.imageCache.get(s);
    }
    
    public Image addImage(final String s, Image scaledImage) {
        if (this.m_maxImageWidth > -1 || this.m_maxImageHeight > -1) {
            scaledImage = this.getScaledImage(scaledImage);
            scaledImage.getWidth(null);
        }
        this.imageCache.put(s, scaledImage);
        return scaledImage;
    }
    
    protected void waitForImage(final Image image) {
        final int n = ++this.nextTrackerID;
        this.tracker.addImage(image, n);
        try {
            this.tracker.waitForID(n, 0L);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.tracker.removeImage(image, n);
    }
    
    protected Image getScaledImage(final Image image) {
        final int n = image.getWidth(null) - this.m_maxImageWidth;
        final int n2 = image.getHeight(null) - this.m_maxImageHeight;
        if (n > n2 && n > 0 && this.m_maxImageWidth > -1) {
            final Image scaledInstance = image.getScaledInstance(this.m_maxImageWidth, -1, 4);
            image.flush();
            return scaledInstance;
        }
        if (n2 > 0 && this.m_maxImageHeight > -1) {
            final Image scaledInstance2 = image.getScaledInstance(-1, this.m_maxImageHeight, 4);
            image.flush();
            return scaledInstance2;
        }
        return image;
    }
    
    public void preloadImages(final Iterator iterator, final String s) {
        final boolean asynch = this.m_asynch;
        this.m_asynch = false;
        while (iterator.hasNext() && this.imageCache.size() <= this.m_imageCacheSize) {
            final String string = iterator.next().getString(s);
            if (string != null) {
                this.getImage(string);
            }
        }
        this.m_asynch = asynch;
    }
    
    private class LoadMapEntry
    {
        public int id;
        public Image image;
        
        public LoadMapEntry(final int id, final Image image) {
            this.id = id;
            this.image = image;
        }
    }
}
