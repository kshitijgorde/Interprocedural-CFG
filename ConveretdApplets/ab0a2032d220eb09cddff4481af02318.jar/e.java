import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class e
{
    private Hashtable bJb;
    private Hashtable QIb;
    
    private void H() {
        this.bJb.put("Grid", new Color[] { Color.darkGray });
        this.bJb.put("ChartFg", new Color[] { Color.black });
        this.bJb.put("Price", new Color[] { Color.yellow });
        this.bJb.put("BgPrice", new Color[] { Color.gray });
        this.bJb.put("ExtraPrice", new Color[] { Color.red, Color.green.darker(), Color.blue, Color.magenta, Color.cyan.darker(), Color.orange, Color.gray, Color.pink, Color.yellow, Color.red.darker() });
        this.bJb.put("EMA", new Color[] { Color.cyan });
        this.bJb.put("BOL", new Color[] { Color.lightGray });
        this.bJb.put("SMA1", new Color[] { Color.red });
        this.bJb.put("SMA2", new Color[] { Color.magenta });
        this.bJb.put("SMA3", new Color[] { Color.blue });
        this.bJb.put("VOLEMA", new Color[] { Color.orange });
        this.bJb.put("ParabolicSAR", new Color[] { Color.cyan.darker().darker() });
        this.bJb.put("Indicator", new Color[] { Color.red, Color.cyan, Color.green });
        this.bJb.put("OpenInterest", new Color[] { Color.blue });
        this.bJb.put("Volume", new Color[] { Color.green });
        this.bJb.put("TrendLine", new Color[] { Color.red });
        this.bJb.put("BuySignal", new Color[] { Color.green.darker() });
        this.bJb.put("SellSignal", new Color[] { Color.red });
    }
    
    public e() {
        this.bJb = new Hashtable();
        this.QIb = new Hashtable();
        this.H();
    }
    
    public void _(final String s, final Color[] array) {
        this.QIb.put(s, array);
    }
    
    public void b(final String s, final Color[] array) {
        this.QIb.put(s, array);
    }
    
    public Color[] _(final String s) {
        Color[] array = this.QIb.get(s);
        if (array == null) {
            array = this.bJb.get(s);
        }
        return array;
    }
    
    public void _(final e e) {
        final Enumeration<String> keys = e.QIb.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this._(s, e._(s));
        }
    }
}
