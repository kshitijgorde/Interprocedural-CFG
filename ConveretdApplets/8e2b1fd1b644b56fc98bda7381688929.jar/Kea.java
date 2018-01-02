import java.awt.Color;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Kea
{
    private Vector Ca;
    private Hashtable Da;
    
    public Kea() {
        this.Ca = new Vector();
        this.Da = new Hashtable();
    }
    
    public synchronized void b(final String s, final Color color, final int n) {
        if (s == null || color == null) {
            throw new IllegalArgumentException("invalid argument(s) passed");
        }
        if (!this.Da.containsKey(s)) {
            this.Ca.addElement(s);
        }
        this.Da.put(s, new Mea(color, n));
    }
    
    public Mea _(final String s) {
        return this.Da.get(s);
    }
    
    public synchronized String[] a() {
        final String[] array = new String[this.Ca.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (String)this.Ca.elementAt(i);
        }
        return array;
    }
}
