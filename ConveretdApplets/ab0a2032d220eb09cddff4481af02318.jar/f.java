import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    private Hashtable aJb;
    private Hashtable QIb;
    
    private void G() {
        this.aJb.put("Price", new int[] { 0 });
        this.aJb.put("BgPrice", new int[] { 0 });
        this.aJb.put("ExtraPrice", new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        this.aJb.put("EMA", new int[] { 0 });
        this.aJb.put("BOL", new int[] { 0 });
        this.aJb.put("SMA1", new int[] { 0 });
        this.aJb.put("SMA2", new int[] { 0 });
        this.aJb.put("SMA3", new int[] { 0 });
        this.aJb.put("VOLEMA", new int[] { 0 });
        this.aJb.put("ParabolicSAR", new int[] { 0 });
        this.aJb.put("Indicator", new int[] { 0, 0, 0 });
        this.aJb.put("OpenInterest", new int[] { 0 });
        this.aJb.put("TrendLine", new int[] { 0 });
    }
    
    public f() {
        this.aJb = new Hashtable();
        this.QIb = new Hashtable();
        this.G();
    }
    
    public void b(final String s, final int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 0) {
                array[i] = 0;
            }
            if (array[i] > 5) {
                array[i] = 5;
            }
        }
        this.QIb.put(s, array);
    }
    
    public int[] b(final String s) {
        int[] array = this.QIb.get(s);
        if (array == null) {
            array = this.aJb.get(s);
        }
        return array;
    }
    
    public void b(final f f) {
        final Enumeration<String> keys = f.QIb.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this.b(s, f.b(s));
        }
    }
}
