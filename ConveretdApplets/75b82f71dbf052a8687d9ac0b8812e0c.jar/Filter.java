import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Filter
{
    protected Frame src;
    protected Frame dst;
    protected Hashtable properties;
    
    Filter() {
        this.properties = new Hashtable();
    }
    
    void setInput(final Frame src) {
        this.src = src;
    }
    
    void setOutput(final Frame dst) {
        this.dst = dst;
    }
    
    void setProperty(final String key, final Object datum) {
        this.properties.put(key, datum);
    }
    
    Object getProperty(final String key) {
        return this.properties.get(key);
    }
    
    abstract void render();
}
