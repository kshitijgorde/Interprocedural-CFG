// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public class NFKeyValue
{
    public Object key;
    public Object value;
    
    public NFKeyValue() {
        this.key = null;
        this.value = null;
    }
    
    public NFKeyValue(final Object key, final Object value) {
        this.key = null;
        this.value = null;
        this.key = key;
        this.value = value;
    }
    
    public String toString() {
        return "(" + this.key + "," + this.value + ")";
    }
}
