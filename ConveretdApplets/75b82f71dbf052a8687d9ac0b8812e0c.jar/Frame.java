import java.util.Hashtable;
import java.awt.Dimension;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Frame
{
    protected Vector pixmaps;
    protected Dimension size;
    protected float[] center;
    protected Hashtable properties;
    
    Frame() {
        this.pixmaps = new Vector();
        this.properties = new Hashtable();
    }
    
    Vector getPixmaps() {
        return this.pixmaps;
    }
    
    Dimension getSize() {
        return this.size;
    }
    
    float[] getCenter() {
        return this.center;
    }
    
    void setCenter(final float[] inCenter) {
        this.center = inCenter;
    }
    
    void setProperty(final String key, final Object datum) {
        if (key != null && datum != null) {
            this.properties.put(key, datum);
        }
    }
    
    Object getProperty(final String key) {
        return this.properties.get(key);
    }
}
