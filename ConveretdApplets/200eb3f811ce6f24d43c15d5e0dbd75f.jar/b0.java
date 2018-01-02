import java.text.DecimalFormatSymbols;

// 
// Decompiled by Procyon v0.5.30
// 

public class b0 extends by
{
    private b1 a;
    
    public b0() {
        super.a = 1;
    }
    
    public b0(final String s) {
        this.a = new b1(s);
    }
    
    public String a(final Object o, final az az, final int n) {
        try {
            if (o instanceof Double) {
                return this.a.a((double)o);
            }
            if (o instanceof Integer) {
                return this.a.a((double)o);
            }
            if (o instanceof Float) {
                return this.a.a((double)o);
            }
            if (o instanceof Long) {
                return this.a.a((double)o);
            }
            throw new dx("value " + o.toString() + " is of type " + o.getClass().getName() + " and has to be a number");
        }
        catch (Exception ex) {
            throw new dx("could not apply the format String to the value " + o.toString(), ex);
        }
    }
    
    public void a(final DecimalFormatSymbols decimalFormatSymbols) {
        this.a.a(decimalFormatSymbols);
    }
    
    public String a() {
        return this.a.a();
    }
}
