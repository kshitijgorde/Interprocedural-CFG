import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class volatile
{
    private Hashtable uwa;
    private Hashtable kwa;
    
    private void ab() {
        this.uwa.put("Price", new int[] { 0 });
        this.uwa.put("BgPrice", new int[] { 0 });
        this.uwa.put("ExtraPrice", new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        this.uwa.put("EMA", new int[] { 0 });
        this.uwa.put("BOL", new int[] { 0 });
        this.uwa.put("SMA1", new int[] { 0 });
        this.uwa.put("SMA2", new int[] { 0 });
        this.uwa.put("SMA3", new int[] { 0 });
        this.uwa.put("VOLEMA", new int[] { 0 });
        this.uwa.put("ParabolicSAR", new int[] { 0 });
        this.uwa.put("Indicator", new int[] { 0, 0, 0 });
        this.uwa.put("OpenInterest", new int[] { 0 });
        this.uwa.put("TrendLine", new int[] { 0 });
    }
    
    public volatile() {
        this.uwa = new Hashtable();
        this.kwa = new Hashtable();
        this.ab();
    }
    
    public void _(final String s, final int[] array) {
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
        this.kwa.put(s, array);
    }
    
    public int[] _(final String s) {
        int[] array = this.kwa.get(s);
        if (array == null) {
            array = this.uwa.get(s);
        }
        return array;
    }
    
    public void a(final volatile volatile1) {
        final Enumeration<String> keys = volatile1.kwa.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this._(s, volatile1._(s));
        }
    }
}
