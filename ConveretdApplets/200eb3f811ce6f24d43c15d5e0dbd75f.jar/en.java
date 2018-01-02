import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class en extends Hashtable implements u
{
    private String a;
    
    public en(final String a) {
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    public eo a(final String s) {
        return super.get(s);
    }
    
    public eo a(final String s, final eo eo) {
        return super.put(s, eo);
    }
    
    public static final String b(final String s) {
        final int index = s.indexOf(46);
        if (index >= 0 && index < s.length()) {
            return s.substring(index + 1);
        }
        return s;
    }
    
    public String a(final String s, final v v) {
        final eo a = this.a(b(s));
        if (a != null) {
            return a.a();
        }
        return null;
    }
    
    public boolean m(final String s) {
        return this.containsKey(b(s));
    }
    
    public Enumeration b() {
        return this.keys();
    }
    
    public Object a(final String s, final String s2, final u u) {
        final eo a = this.a(b(s));
        if (a != null) {
            return a.a(s2, u);
        }
        return null;
    }
    
    public String n(final String s) {
        final eo a = this.a(b(s));
        if (a != null) {
            return a.d;
        }
        return null;
    }
}
