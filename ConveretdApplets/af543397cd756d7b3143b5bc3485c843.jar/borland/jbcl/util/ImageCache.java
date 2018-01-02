// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

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
        this.limit = limit;
    }
    
    void put(final Object o, final Image image) {
        if (this.limit > 0 && this.list.size() >= this.limit) {
            this.map.remove(this.list.popBack());
        }
        this.map.put(o, image);
        this.list.pushFront(o);
    }
    
    Image get(final Object o) {
        final Image image = (Image)this.map.get(o);
        if (image != null) {
            this.list.remove(o);
            this.list.pushFront(o);
        }
        return image;
    }
}
