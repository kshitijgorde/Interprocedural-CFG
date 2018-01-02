import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class bi
{
    private Hashtable U;
    
    public bi() {
        this.U = new Hashtable();
    }
    
    public String[] n() {
        if (this.U.size() == 0) {
            return null;
        }
        final String[] array = new String[this.U.size()];
        final Enumeration<Object> keys = this.U.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement().toString();
        }
        return array;
    }
    
    public void _(final implements implements1) {
        this.U.put(implements1.toString(), implements1);
    }
    
    public implements _(final String s) {
        if (s == null) {
            return null;
        }
        return this.U.get(s);
    }
    
    public void a(final implements implements1) {
        if (implements1 != null) {
            this.U.remove(implements1.toString());
        }
    }
}
