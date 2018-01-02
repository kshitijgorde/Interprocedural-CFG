import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public String if;
    public String do;
    public Image a;
    
    public d() {
        this.if = "";
        this.do = "";
    }
    
    public d(final d d) {
        this.if = "";
        this.do = "";
        if (d != null) {
            if (d.if != null) {
                this.if = new String(d.if);
            }
            if (d.do != null) {
                this.do = new String(d.do);
            }
        }
    }
}
