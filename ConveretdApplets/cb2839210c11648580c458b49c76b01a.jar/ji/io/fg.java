// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

public class fg extends Exception
{
    private boolean a;
    
    public String toString() {
        if (this.getMessage() != null) {
            return "Format Exception: ".concat(String.valueOf(String.valueOf(this.getMessage())));
        }
        return "Format Exception";
    }
    
    public fg(final String s) {
        super(s);
        this.a = true;
    }
    
    public fg(final String s, final boolean a) {
        super(s);
        this.a = true;
        this.a = a;
    }
    
    public final boolean a() {
        return this.a;
    }
}
