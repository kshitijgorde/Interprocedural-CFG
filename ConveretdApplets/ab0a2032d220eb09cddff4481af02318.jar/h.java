import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class h
{
    private Hashtable QIb;
    
    public h() {
        this.QIb = new Hashtable();
    }
    
    public void _(final String s, final String s2) {
        this.QIb.put(s, s2);
    }
    
    public String a(final String s) {
        return this.QIb.get(s);
    }
    
    public int _(final String s) {
        if (this.QIb.size() == 0) {
            return -1;
        }
        final String s2 = this.QIb.get(s);
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
    
    public int a(final String s) {
        int n = 0;
        if (this.QIb.size() == 0) {
            return -1;
        }
        final String s2 = this.QIb.get(s);
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
    
    public void a(final h h) {
        final Enumeration<String> keys = h.QIb.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this._(s, h.a(s));
        }
    }
}
