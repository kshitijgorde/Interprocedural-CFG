// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class InvalidApplicationException extends Exception
{
    private static final long serialVersionUID = -3048022274675537269L;
    private Object val;
    
    public InvalidApplicationException(final Object val) {
        this.val = null;
        this.val = val;
    }
    
    public String toString() {
        return "Invalid Application: " + this.val.toString();
    }
}
