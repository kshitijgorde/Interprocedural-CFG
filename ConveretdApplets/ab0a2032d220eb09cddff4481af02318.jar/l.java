import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    private Hashtable KIb;
    
    public l() {
        this.KIb = new Hashtable();
    }
    
    public String[] m() {
        if (this.KIb.size() == 0) {
            return null;
        }
        final String[] array = new String[this.KIb.size()];
        final Enumeration<Object> keys = this.KIb.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement().toString();
        }
        return array;
    }
    
    public void b(final o o) {
        this.KIb.put(o.toString(), o);
    }
    
    public o _(final String s) {
        if (s == null) {
            return null;
        }
        return this.KIb.get(s);
    }
    
    public void _(final o o) {
        if (o != null) {
            this.KIb.remove(o.toString());
        }
    }
}
