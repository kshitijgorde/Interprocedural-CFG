import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

class kj extends Hashtable
{
    public Object put(final Object o, final Object o2) {
        if (o == null || o2 == null) {
            return null;
        }
        return super.put(o, o2);
    }
    
    public Object get(final Object o) {
        if (o == null) {
            return null;
        }
        return super.get(o);
    }
    
    public Object remove(final Object o) {
        if (o == null) {
            return null;
        }
        return super.remove(o);
    }
    
    public boolean containsKey(final Object o) {
        return o != null && super.containsKey(o);
    }
    
    public boolean containsValue(final Object o) {
        return o != null && super.containsValue(o);
    }
    
    public boolean contains(final Object o) {
        return this.containsValue(o);
    }
}
