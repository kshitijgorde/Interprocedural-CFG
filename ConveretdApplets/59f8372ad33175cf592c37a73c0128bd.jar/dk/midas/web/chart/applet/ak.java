// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class ak
{
    public static final String do = "Integer";
    public static final String try = "Float";
    String for;
    String new;
    float int;
    float if;
    float a;
    
    protected ak(final String for1, final String new1) {
        this.for = for1;
        this.new = new1;
    }
    
    public ak(final String s, final String s2, final float int1, final float if1, final float a) {
        this(s, s2);
        this.int = int1;
        this.if = if1;
        this.a = a;
    }
    
    protected boolean a(final Integer n) {
        return n != null && this.int <= n && n <= this.if;
    }
    
    protected boolean a(final Float n) {
        return n != null && this.int <= n && n <= this.if;
    }
}
