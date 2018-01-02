import java.util.regex.Pattern;

// 
// Decompiled by Procyon v0.5.30
// 

public class ab implements j
{
    Pattern a;
    
    public ab(final String s) {
        this.a = Pattern.compile(s);
    }
    
    public boolean a(final String s) {
        return this.a.matcher(s).find();
    }
    
    public String toString() {
        return this.a.pattern();
    }
}
