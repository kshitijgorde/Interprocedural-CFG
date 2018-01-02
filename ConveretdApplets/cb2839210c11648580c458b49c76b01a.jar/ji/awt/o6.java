// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

public class o6
{
    private double a;
    private double b;
    
    public o6() {
        this.a = 0.0;
        this.b = 0.0;
    }
    
    public o6(final double a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiDoubleDimension [width=").append(this.a).append(",height=").append(this.b).append("]")));
    }
    
    public double a() {
        return this.b;
    }
    
    public double b() {
        return this.a;
    }
}
