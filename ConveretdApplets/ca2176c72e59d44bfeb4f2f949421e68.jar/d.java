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
    
    public void addValue(final double n, final double n2) {
        this.a.addElement(new Double(n));
        this.b.addElement(new Double(n2));
    }
    
    public double getMean() {
        final boolean h = b.h;
        double n = 0.0;
        double n2 = 0.0;
        final int size = this.a.size();
        if (h || size > 0) {
            int n3 = size;
            double n5 = 0.0;
            while (true) {
                while (true) {
                    Label_0090: {
                        if (!h) {
                            break Label_0090;
                        }
                        (double)this.b.elementAt(n3);
                        final double n4 = n5;
                        final double n6 = this.d / (n4 * n4);
                        n += this.a.elementAt(n3) * n6;
                        n2 += n6;
                        ++n3;
                    }
                    if (n3 < this.a.size()) {
                        continue;
                    }
                    break;
                }
                n5 = n / n2;
                if (h) {
                    continue;
                }
                break;
            }
            n = n5;
        }
        return n;
    }
    
    public double getSigma() {
        final boolean h = b.h;
        double n = 0.0;
        double n2 = 0.0;
        final int size = this.a.size();
        if (h || size > 0) {
            int n3 = size;
            double sqrt = 0.0;
            while (true) {
                while (true) {
                    Label_0065: {
                        if (!h) {
                            break Label_0065;
                        }
                        final double doubleValue = this.b.elementAt(n3);
                        final double n4 = n2 + this.d / (doubleValue * doubleValue);
                        n2 = sqrt;
                        ++n3;
                    }
                    if (n3 < this.a.size()) {
                        continue;
                    }
                    break;
                }
                sqrt = Math.sqrt(this.d / n2);
                if (h) {
                    continue;
                }
                break;
            }
            n = sqrt;
        }
        return n;
    }
    
    public int getCount() {
        return this.a.size();
    }
    
    public static String format(final double n) {
        return d.c.format(n);
    }
    
    static {
        d.c = NumberFormat.getInstance();
    }
}
