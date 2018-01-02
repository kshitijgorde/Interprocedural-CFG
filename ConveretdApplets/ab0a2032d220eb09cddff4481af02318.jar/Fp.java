import java.awt.Color;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fp
{
    private Vector dEb;
    private Hashtable eEb;
    
    public Fp() {
        this.dEb = new Vector();
        this.eEb = new Hashtable();
    }
    
    public synchronized void b(final String s, final Color color, final int n) {
        if (s == null || color == null) {
            throw new IllegalArgumentException("invalid argument(s) passed");
        }
        if (!this.eEb.containsKey(s)) {
            this.dEb.addElement(s);
        }
        this.eEb.put(s, new Hp(this, color, n));
    }
    
    public Hp _(final String s) {
        return this.eEb.get(s);
    }
    
    public synchronized String[] _() {
        final String[] array = new String[this.dEb.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (String)this.dEb.elementAt(i);
        }
        return array;
    }
}
