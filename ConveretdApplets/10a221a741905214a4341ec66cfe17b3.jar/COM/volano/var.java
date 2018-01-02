// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;

public class var extends Vector
{
    private int a;
    private int b;
    
    public var(final int a) {
        super(a + 1);
        this.a = a;
        this.addElement("");
    }
    
    public String a() {
        if (this.b < this.size() - 1) {
            return this.elementAt(++this.b);
        }
        return this.elementAt(this.b);
    }
    
    public String b() {
        if (this.b > 0) {
            final int b = this.b - 1;
            this.b = b;
            return (String)this.elementAt(b);
        }
        return this.elementAt(this.b);
    }
    
    public void a(final String s) {
        if (this.size() == this.a) {
            this.removeElementAt(this.a - 1);
        }
        this.insertElementAt(s, 1);
    }
    
    public void c() {
        this.b = 0;
    }
}
