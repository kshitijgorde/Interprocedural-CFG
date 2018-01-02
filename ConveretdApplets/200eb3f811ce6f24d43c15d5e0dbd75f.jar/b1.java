import java.text.DecimalFormat;
import java.util.Hashtable;
import java.text.DecimalFormatSymbols;

// 
// Decompiled by Procyon v0.5.30
// 

public class b1
{
    private String a;
    private DecimalFormatSymbols b;
    private Hashtable c;
    
    public b1(final String a) {
        this.c = new Hashtable();
        this.a = a;
    }
    
    public final String a(final double n) {
        return this.b().format(n);
    }
    
    public final void a(final DecimalFormatSymbols decimalFormatSymbols) {
        this.b = decimalFormatSymbols;
        this.b().setDecimalFormatSymbols(decimalFormatSymbols);
    }
    
    public final String a() {
        return this.b().toPattern();
    }
    
    private DecimalFormat b() {
        DecimalFormat decimalFormat = this.c.get(Thread.currentThread());
        if (decimalFormat == null) {
            if (this.b != null) {
                decimalFormat = new DecimalFormat(this.a, this.b);
            }
            else {
                decimalFormat = new DecimalFormat(this.a);
            }
            this.c.put(Thread.currentThread(), decimalFormat);
        }
        return decimalFormat;
    }
}
