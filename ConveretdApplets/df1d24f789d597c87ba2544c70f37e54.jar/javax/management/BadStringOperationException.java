// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class BadStringOperationException extends Exception
{
    private static final long serialVersionUID = 7802201238441662100L;
    private String op;
    
    public BadStringOperationException(final String op) {
        super(op);
        this.op = op;
    }
    
    public String toString() {
        return "Bad string operation: " + this.op;
    }
}
