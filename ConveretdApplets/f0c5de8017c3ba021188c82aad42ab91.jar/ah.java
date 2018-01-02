import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ah extends Hashtable implements v
{
    private String a;
    
    public ah(final String a) {
        this.a = a;
    }
    
    public String a(final String s, final v v) {
        return this.get(s);
    }
    
    public boolean m(final String s) {
        return super.containsKey(s);
    }
    
    public Enumeration b() {
        return this.keys();
    }
    
    public String a() {
        return this.a;
    }
}
