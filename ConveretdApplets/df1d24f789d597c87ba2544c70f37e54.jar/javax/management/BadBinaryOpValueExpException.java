// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class BadBinaryOpValueExpException extends Exception
{
    private static final long serialVersionUID = 5068475589449021227L;
    private ValueExp exp;
    
    public BadBinaryOpValueExpException(final ValueExp exp) {
        this.exp = null;
        this.exp = exp;
    }
    
    public ValueExp getExp() {
        return this.exp;
    }
    
    public String toString() {
        return "Bad binary operation value expression: " + this.exp.toString();
    }
}
