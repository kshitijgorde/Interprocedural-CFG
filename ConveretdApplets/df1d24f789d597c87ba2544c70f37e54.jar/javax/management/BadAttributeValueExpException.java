// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class BadAttributeValueExpException extends Exception
{
    private static final long serialVersionUID = -3105272988410493376L;
    private Object val;
    
    public BadAttributeValueExpException(final Object val) {
        this.val = null;
        this.val = val;
    }
    
    public String toString() {
        return "Bad attribute value expression: " + this.val;
    }
}
