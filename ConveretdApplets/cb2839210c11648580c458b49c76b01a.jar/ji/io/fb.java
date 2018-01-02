// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

public class fb extends Exception
{
    public String toString() {
        if (this.getMessage() != null) {
            return "ModuleException: ".concat(String.valueOf(String.valueOf(this.getMessage())));
        }
        return "ModuleException";
    }
    
    public fb(final String s) {
        super(s);
    }
}
