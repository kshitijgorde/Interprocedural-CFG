import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class package
{
    private Hashtable tpa;
    
    public package() {
        this.tpa = new Hashtable();
    }
    
    public String[] c() {
        if (this.tpa.size() == 0) {
            return null;
        }
        final String[] array = new String[this.tpa.size()];
        final Enumeration<Object> keys = this.tpa.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement().toString();
        }
        return array;
    }
    
    public void b(final public public1) {
        this.tpa.put(public1.toString(), public1);
    }
    
    public public a(final String s) {
        if (s == null) {
            return null;
        }
        return this.tpa.get(s);
    }
    
    public void _(final public public1) {
        if (public1 != null) {
            this.tpa.remove(public1.toString());
        }
    }
}
