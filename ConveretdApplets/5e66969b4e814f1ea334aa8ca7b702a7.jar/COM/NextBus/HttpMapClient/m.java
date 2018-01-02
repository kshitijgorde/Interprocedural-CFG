// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.text.NumberFormat;

public final class m
{
    private double a;
    private double b;
    private double c;
    private double d;
    private int e;
    private int f;
    
    public m(final double a, final double b, final double c, final double d, final int e, final int f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public final String toString() {
        final NumberFormat b = b();
        return "lon=" + b.format(this.a) + "&lat=" + b.format(this.b) + "&wid=" + b.format(this.c) + "&ht=" + b.format(this.d) + "&iwd=" + this.e + "&iht=" + this.f;
    }
    
    public final String a() {
        final NumberFormat b = b();
        return ("lo" + b.format(this.a) + "la" + b.format(this.b) + "wi" + b.format(this.c) + "he" + b.format(this.d) + "ix" + this.e + "iy" + this.f).replace('-', 'm').replace('.', 'p').replace(',', 'p') + ".png";
    }
    
    public final int hashCode() {
        return this.toString().hashCode();
    }
    
    public final boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof m)) {
            return false;
        }
        final m m = (m)o;
        return this.a == m.a && this.b == m.b && this.c == m.c && this.d == m.d && this.e == m.e && this.f == m.f;
    }
    
    private static NumberFormat b() {
        final NumberFormat instance;
        (instance = NumberFormat.getInstance()).setGroupingUsed(false);
        instance.setMaximumFractionDigits(5);
        instance.setMinimumFractionDigits(5);
        instance.setMaximumIntegerDigits(3);
        instance.setMinimumIntegerDigits(1);
        return instance;
    }
}
