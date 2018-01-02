import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class abstract
{
    private Hashtable esa;
    private Hashtable Vra;
    
    private void Ib() {
        this.esa.put("Price", new int[] { 0 });
        this.esa.put("BgPrice", new int[] { 0 });
        this.esa.put("ExtraPrice", new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        this.esa.put("EMA1", new int[] { 0 });
        this.esa.put("EMA2", new int[] { 0 });
        this.esa.put("EMA3", new int[] { 0 });
        this.esa.put("BOL", new int[] { 0 });
        this.esa.put("SMA1", new int[] { 0 });
        this.esa.put("SMA2", new int[] { 0 });
        this.esa.put("SMA3", new int[] { 0 });
        this.esa.put("DMA", new int[] { 0 });
        this.esa.put("VOLEMA", new int[] { 0 });
        this.esa.put("ParabolicSAR", new int[] { 0 });
        this.esa.put("PivotPoints", new int[] { 2 });
        this.esa.put("S1", new int[] { 0 });
        this.esa.put("R1", new int[] { 0 });
        this.esa.put("S2", new int[] { 0 });
        this.esa.put("R2", new int[] { 0 });
        this.esa.put("Indicator", new int[] { 0, 0, 0 });
        this.esa.put("OpenInterest", new int[] { 0 });
        this.esa.put("TrendLine", new int[] { 0 });
    }
    
    public abstract() {
        this.esa = new Hashtable();
        this.Vra = new Hashtable();
        this.Ib();
    }
    
    public void a(final String s, final int[] array) {
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
        this.Vra.put(s, array);
    }
    
    public int[] _(final String s) {
        int[] array = this.Vra.get(s);
        if (array == null) {
            array = this.esa.get(s);
        }
        return array;
    }
    
    public void _(final abstract abstract1) {
        final Enumeration<String> keys = abstract1.Vra.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this.a(s, abstract1._(s));
        }
    }
}
