// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

public class lines
{
    private String a;
    private int b;
    
    public lines(final String a) {
        this.a = a;
        if (this.a == null) {
            this.a = "";
        }
        this.b = 0;
    }
    
    public String a() {
        if (this.b >= this.a.length()) {
            return null;
        }
        int n = this.a.indexOf("\r\n", this.b);
        int n2 = 2;
        if (n == -1) {
            n2 = 1;
            n = this.a.indexOf("\n", this.b);
        }
        if (n == -1) {
            n = this.a.indexOf("\r", this.b);
        }
        String s;
        if (n != -1) {
            s = this.a.substring(this.b, n);
            this.b = n + n2;
        }
        else {
            s = this.a.substring(this.b);
            this.b = this.a.length();
        }
        return s;
    }
    
    public void b() {
        this.b = 0;
    }
}
