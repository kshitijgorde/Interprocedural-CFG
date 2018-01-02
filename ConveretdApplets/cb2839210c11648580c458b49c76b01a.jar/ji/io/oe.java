// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

public class oe extends Exception
{
    public String toString() {
        if (this.getMessage() != null) {
            return "Cancel Exception: ".concat(String.valueOf(String.valueOf(this.getMessage())));
        }
        return "Cancel Exception";
    }
    
    public oe(final String s) {
        super(s);
    }
}
