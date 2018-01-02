// 
// Decompiled by Procyon v0.5.30
// 

package A.A;

public class D
{
    private String A;
    private String B;
    
    public String A() {
        return this.B;
    }
    
    public int C() {
        try {
            return Integer.parseInt(this.B);
        }
        catch (NumberFormatException ex) {
            throw new NumberFormatException("Attribute '" + this.A + "' has value '" + this.B + "' which is not an integer");
        }
    }
    
    public String B() {
        return this.A;
    }
    
    public D(final String a, final String b) {
        this.A = "";
        this.B = "";
        this.A = a;
        this.B = b;
    }
}
