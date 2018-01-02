import java.awt.Color;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ij
{
    private Vector Ha;
    private Hashtable Ia;
    
    public Ij() {
        this.Ha = new Vector();
        this.Ia = new Hashtable();
    }
    
    public synchronized void b(final String s, final Color color, final int n) {
        if (s == null || color == null) {
            throw new IllegalArgumentException("invalid argument(s) passed");
        }
        if (!this.Ia.containsKey(s)) {
            this.Ha.addElement(s);
        }
        this.Ia.put(s, new Kj(color, n));
    }
    
    public Kj _(final String s) {
        return this.Ia.get(s);
    }
    
    public synchronized String[] b() {
        final String[] array = new String[this.Ha.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (String)this.Ha.elementAt(i);
        }
        return array;
    }
}
