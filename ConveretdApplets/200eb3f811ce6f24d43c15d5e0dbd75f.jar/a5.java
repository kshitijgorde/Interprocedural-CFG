// 
// Decompiled by Procyon v0.5.30
// 

public class a5 extends a6
{
    private String a;
    
    public a5(final String s) {
        super(s);
        this.a = null;
    }
    
    public a5(final a5 a5) {
        super(a5);
        this.a = null;
        if (a5.a != null) {
            this.a = new String(a5.a);
        }
    }
    
    public final String a() {
        if (super.e == null || this.a == null) {
            if (super.b == null) {
                return null;
            }
            this.a = super.b;
            if (this.a.startsWith("/")) {
                this.a = this.a.substring(1);
            }
            if (this.a.endsWith(".csv")) {
                this.a = this.a.substring(0, this.a.length() - 4);
            }
        }
        return this.a;
    }
    
    public String toString() {
        if (super.e == null) {
            this.a = null;
        }
        return super.toString();
    }
}
