import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    private Hashtable fsa;
    private Hashtable Vra;
    
    private void Jb() {
        this.fsa.put("Grid", new Color[] { Color.darkGray });
        this.fsa.put("ChartFg", new Color[] { Color.black });
        this.fsa.put("Price", new Color[] { Color.yellow });
        this.fsa.put("BullishCandle", new Color[] { Color.green.darker() });
        this.fsa.put("BearishCandle", new Color[] { Color.red });
        this.fsa.put("BgPrice", new Color[] { Color.gray });
        this.fsa.put("ExtraPrice", new Color[] { Color.red, Color.green.darker(), Color.blue, Color.magenta, Color.cyan.darker(), Color.orange, Color.gray, Color.pink, Color.yellow, Color.red.darker() });
        this.fsa.put("EMA1", new Color[] { Color.cyan });
        this.fsa.put("EMA2", new Color[] { Color.magenta.darker() });
        this.fsa.put("EMA3", new Color[] { Color.blue.brighter() });
        this.fsa.put("BOL", new Color[] { Color.lightGray });
        this.fsa.put("SMA1", new Color[] { Color.red });
        this.fsa.put("SMA2", new Color[] { Color.magenta });
        this.fsa.put("SMA3", new Color[] { Color.blue });
        this.fsa.put("DMA", new Color[] { Color.pink });
        this.fsa.put("VOLEMA", new Color[] { Color.orange });
        this.fsa.put("ParabolicSAR", new Color[] { Color.cyan.darker().darker() });
        this.fsa.put("PivotPoints", new Color[] { Color.blue });
        this.fsa.put("S1", new Color[] { Color.red });
        this.fsa.put("R1", new Color[] { Color.green.darker() });
        this.fsa.put("S2", new Color[] { Color.red.darker() });
        this.fsa.put("R2", new Color[] { Color.green.darker().darker() });
        this.fsa.put("Indicator", new Color[] { Color.red, Color.cyan, Color.green });
        this.fsa.put("OpenInterest", new Color[] { Color.blue });
        this.fsa.put("Volume", new Color[] { Color.green });
        this.fsa.put("TrendLine", new Color[] { Color.red });
        this.fsa.put("BuySignal", new Color[] { Color.green.darker() });
        this.fsa.put("SellSignal", new Color[] { Color.red });
    }
    
    public v() {
        this.fsa = new Hashtable();
        this.Vra = new Hashtable();
        this.Jb();
    }
    
    public void b(final String s, final Color[] array) {
        this.Vra.put(s, array);
    }
    
    public void a(final String s, final Color[] array) {
        this.Vra.put(s, array);
    }
    
    public Color[] a(final String s) {
        Color[] array = this.Vra.get(s);
        if (array == null) {
            array = this.fsa.get(s);
        }
        return array;
    }
    
    public void a(final v v) {
        final Enumeration<String> keys = v.Vra.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this.b(s, v.a(s));
        }
    }
}
