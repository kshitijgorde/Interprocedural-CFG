import java.text.NumberFormat;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class d
{
    Vector a;
    Vector b;
    public static NumberFormat c;
    double d;
    
    public d() {
        this.d = 0.04000000000000001;
        this.a = new Vector();
        this.b = new Vector();
        d.c.setMaximumFractionDigits(3);
        d.c.setMinimumFractionDigits(3);
        d.c.setMinimumIntegerDigits(1);
    }
    
    public void a(final double n, final double n2) {
        this.a.addElement(new Double(n));
        this.b.addElement(new Double(n2));
    }
    
    public double a() {
        double n = 0.0;
        double n2 = 0.0;
        if (this.a.size() > 0) {
            for (int i = 0; i < this.a.size(); ++i) {
                final double doubleValue = this.b.elementAt(i);
                final double n3 = this.d / (doubleValue * doubleValue);
                n += this.a.elementAt(i) * n3;
                n2 += n3;
            }
            n /= n2;
        }
        return n;
    }
    
    public double b() {
        double sqrt = 0.0;
        double n = 0.0;
        if (this.a.size() > 0) {
            for (int i = 0; i < this.a.size(); ++i) {
                final double doubleValue = this.b.elementAt(i);
                n += this.d / (doubleValue * doubleValue);
            }
            sqrt = Math.sqrt(this.d / n);
        }
        return sqrt;
    }
    
    public int c() {
        return this.a.size();
    }
    
    public static String a(final double n) {
        return d.c.format(n);
    }
    
    static {
        d.c = NumberFormat.getInstance();
    }
}
