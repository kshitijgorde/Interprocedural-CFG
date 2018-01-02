// 
// Decompiled by Procyon v0.5.30
// 

public class b2 extends b0
{
    private String a;
    
    public b2(final String s, final String s2) {
        super(s);
        this.a(s2);
    }
    
    public String a(final Object o, final az az, final int n) {
        return super.a(new Double(this.a(o)), az, n);
    }
    
    private final double a(final Object o) {
        double n;
        if (o instanceof Integer) {
            n = (double)o;
        }
        else {
            if (!(o instanceof Double)) {
                throw new dx("Incoming value is of type " + o.getClass().getName() + " - must be either Double or Integer");
            }
            n = (double)o;
        }
        if (o == null) {
            return 0.0;
        }
        if (this.a == null || this.a.length() == 0) {
            return n;
        }
        double doubleValue;
        try {
            doubleValue = new Integer(this.a.substring(1));
        }
        catch (NumberFormatException ex) {
            throw new dx("Could not create an Integer from the value " + this.a.substring(1), ex);
        }
        if (this.a.startsWith("D") || this.a.startsWith("d")) {
            doubleValue *= -1.0;
        }
        return n * Math.pow(10.0, doubleValue);
    }
    
    public final void a(final String s) {
        if (s != null) {
            this.a = s.trim();
        }
    }
}
