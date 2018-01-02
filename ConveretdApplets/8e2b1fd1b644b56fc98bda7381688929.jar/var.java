import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class var
{
    private Hashtable vwa;
    private Hashtable kwa;
    
    private void bb() {
        this.vwa.put("Grid", new Color[] { Color.darkGray });
        this.vwa.put("ChartFg", new Color[] { Color.black });
        this.vwa.put("Price", new Color[] { Color.yellow });
        this.vwa.put("BullishCandle", new Color[] { Color.green.darker() });
        this.vwa.put("BearishCandle", new Color[] { Color.red });
        this.vwa.put("BgPrice", new Color[] { Color.gray });
        this.vwa.put("ExtraPrice", new Color[] { Color.red, Color.green.darker(), Color.blue, Color.magenta, Color.cyan.darker(), Color.orange, Color.gray, Color.pink, Color.yellow, Color.red.darker() });
        this.vwa.put("EMA", new Color[] { Color.cyan });
        this.vwa.put("BOL", new Color[] { Color.lightGray });
        this.vwa.put("SMA1", new Color[] { Color.red });
        this.vwa.put("SMA2", new Color[] { Color.magenta });
        this.vwa.put("SMA3", new Color[] { Color.blue });
        this.vwa.put("VOLEMA", new Color[] { Color.orange });
        this.vwa.put("ParabolicSAR", new Color[] { Color.cyan.darker().darker() });
        this.vwa.put("Indicator", new Color[] { Color.red, Color.cyan, Color.green });
        this.vwa.put("OpenInterest", new Color[] { Color.blue });
        this.vwa.put("Volume", new Color[] { Color.green });
        this.vwa.put("TrendLine", new Color[] { Color.red });
        this.vwa.put("BuySignal", new Color[] { Color.green.darker() });
        this.vwa.put("SellSignal", new Color[] { Color.red });
    }
    
    public var() {
        this.vwa = new Hashtable();
        this.kwa = new Hashtable();
        this.bb();
    }
    
    public void b(final String s, final Color[] array) {
        this.kwa.put(s, array);
    }
    
    public void a(final String s, final Color[] array) {
        this.kwa.put(s, array);
    }
    
    public Color[] b(final String s) {
        Color[] array = this.kwa.get(s);
        if (array == null) {
            array = this.vwa.get(s);
        }
        return array;
    }
    
    public void b(final var var) {
        final Enumeration<String> keys = var.kwa.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this.b(s, var.b(s));
        }
    }
}
