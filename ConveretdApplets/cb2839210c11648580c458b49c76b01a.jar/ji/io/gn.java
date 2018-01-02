// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

public class gn extends Exception
{
    public String toString() {
        if (this.getMessage() != null) {
            return "LZW Exception: ".concat(String.valueOf(String.valueOf(this.getMessage())));
        }
        return "LZW Exception";
    }
    
    public gn(final String s) {
        super(s);
    }
}
