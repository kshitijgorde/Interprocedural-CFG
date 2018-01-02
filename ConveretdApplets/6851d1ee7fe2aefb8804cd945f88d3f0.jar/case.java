import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class case
{
    private Hashtable Vra;
    
    public case() {
        this.Vra = new Hashtable();
    }
    
    public void b(final String s, final String s2) {
        this.Vra.put(s, s2);
    }
    
    public String a(final String s) {
        return this.Vra.get(s);
    }
    
    public int b(final String s) {
        if (this.Vra.size() == 0) {
            return -1;
        }
        final String s2 = this.Vra.get(s);
        if (s2 == null) {
            return -1;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s2);
        }
        catch (NumberFormatException ex) {
            int1 = -1;
        }
        return int1;
    }
    
    public int k(final String s) {
        int n = 0;
        if (this.Vra.size() == 0) {
            return -1;
        }
        final String s2 = this.Vra.get(s);
        if (s2 == null) {
            return -1;
        }
        if (s2.equalsIgnoreCase("PLAIN")) {
            n |= 0x0;
        }
        if (s2.equalsIgnoreCase("BOLD")) {
            n |= 0x1;
        }
        if (s2.equalsIgnoreCase("ITALIC")) {
            n |= 0x2;
        }
        if (s2.equalsIgnoreCase("BOLDITALIC")) {
            n |= 0x3;
        }
        return n;
    }
    
    public void _(final case case1) {
        final Enumeration<String> keys = case1.Vra.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this.b(s, case1.a(s));
        }
    }
}
