// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.applet.Applet;

public class 0 extends Applet
{
    11 a;
    String b;
    String c;
    String d;
    
    public void init() {
        this.d = this.getParameter("host");
        if (this.d == null) {
            this.d = this.getCodeBase().getHost();
        }
        this.b = this.getParameter("id");
        this.c = this.getParameter("location");
    }
    
    public void start() {
        if (this.b != null && this.c != null) {
            this.a = new 11(this.d, this.b, this.c);
        }
    }
    
    public void stop() {
        if (this.a != null) {
            this.a.va();
        }
    }
}
