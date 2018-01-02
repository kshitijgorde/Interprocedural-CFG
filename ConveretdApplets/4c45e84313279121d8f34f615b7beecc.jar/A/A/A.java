// 
// Decompiled by Procyon v0.5.30
// 

package A.A;

public class A
{
    private String B;
    private B A;
    
    public B B() {
        return this.A;
    }
    
    public String A() {
        return this.B;
    }
    
    public A(final String b, final B a) {
        this.B = b;
        this.A = a;
    }
    
    public A(final B b) {
        this("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n", b);
    }
    
    public String toString() {
        return this.B + this.A.toString();
    }
}
