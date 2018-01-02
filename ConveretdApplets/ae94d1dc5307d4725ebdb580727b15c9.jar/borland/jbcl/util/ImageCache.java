// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.awt.Component;
import java.awt.Image;
import COM.objectspace.jgl.SList;
import COM.objectspace.jgl.HashMap;

class ImageCache
{
    private HashMap map;
    private SList list;
    int limit;
    
    ImageCache() {
        this(10);
    }
    
    ImageCache(final int limit) {
        this.map = new HashMap();
        this.list = new SList();
        if (limit <= 0) {
            throw new IllegalArgumentException();
        }
        this.limit = limit;
    }
    
    void setLimit(final int newLimit) {
        if (newLimit <= 0) {
            throw new IllegalArgumentException();
        }
        while (this.list.size() > newLimit) {
            final Object lastKey = this.list.popBack();
            this.map.remove(lastKey);
        }
        this.limit = newLimit;
    }
    
    int getLimit() {
        return this.limit;
    }
    
    void put(final Object key, final Image image, final Component component) {
        if (this.limit > 0 && this.list.size() >= this.limit) {
            final Object lastKey = this.list.popBack();
            if (lastKey != null) {
                final Image last = (Image)this.map.get(lastKey);
                ImageLoader.waitForImage(component, last);
            }
            this.map.remove(lastKey);
        }
        this.map.put(key, image);
        this.list.pushFront(key);
    }
    
    Image get(final Object key) {
        final Image image = (Image)this.map.get(key);
        if (image != null) {
            this.list.remove(key);
            this.list.pushFront(key);
        }
        return image;
    }
}
