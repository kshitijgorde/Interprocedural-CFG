import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    public Font for;
    public String int;
    public String if;
    public int do;
    public int a;
    
    public g() {
        this.int = "DEFAULT";
        this.if = "SansSerif";
        this.do = 9;
        this.a = 0;
    }
    
    public g(final g g) {
        this.int = "DEFAULT";
        this.if = "SansSerif";
        this.do = 9;
        this.a = 0;
        if (g != null) {
            if (g.int != null) {
                this.int = new String(g.int);
            }
            if (g.if != null) {
                this.if = new String(g.if);
            }
            this.do = g.do;
            this.a = g.a;
        }
    }
}
