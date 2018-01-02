import java.text.NumberFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class aq extends c
{
    static NumberFormat a;
    static String[] b;
    float c;
    
    c a(final aa aa) {
        return aa.d;
    }
    
    public aq() {
        super.a = 3;
        this.c = 0.0f;
    }
    
    public aq(final float c) {
        super.a = 3;
        this.c = c;
    }
    
    String r() {
        return "number";
    }
    
    c h(final int n) {
        return this;
    }
    
    float af() {
        return this.c;
    }
    
    boolean ag() {
        return this.c != 0.0f;
    }
    
    public String toString() {
        if (this.c < 500.0f && this.c >= 0.0f) {
            final int n = (int)this.c;
            if (n - this.c == 0.0f) {
                if (aq.b[n] == null) {
                    aq.b[n] = aq.a.format(this.c);
                }
                return aq.b[n];
            }
        }
        if (this.c == Float.POSITIVE_INFINITY) {
            return "Infinity";
        }
        if (Float.isNaN(this.c)) {
            return "NaN";
        }
        return aq.a.format(this.c);
    }
    
    static {
        aq.a = NumberFormat.getInstance();
        aq.b = new String[500];
        aq.a.setGroupingUsed(false);
    }
}
