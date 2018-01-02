// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.util.Vector;
import java.util.Hashtable;

public class var_hash extends Hashtable
{
    var_hash a;
    
    public var_hash() {
    }
    
    public var_hash(final var_hash a) {
        this.a = a;
    }
    
    public void a(final String s, final String s2) {
        super.put(s.toUpperCase(), s2);
    }
    
    public void a(final String s, final Vector vector) {
        super.put(s.toUpperCase(), vector);
    }
    
    public String a(final String s) {
        return super.get(s.toUpperCase());
    }
    
    public String b(final String s) {
        final String s2 = super.get(s.toUpperCase());
        if (s2 == null) {
            return "";
        }
        return s2;
    }
    
    public String a(final String s, final int n, final String s2) {
        return this.a(this.a(s, n), s2);
    }
    
    public fields a(final String s, final int n) {
        final Vector<fields> vector = super.get(s.toUpperCase());
        if (vector == null || n >= vector.size()) {
            return null;
        }
        try {
            return vector.elementAt(n);
        }
        catch (Exception ex) {
            abljem.b("var_hash.getfldset(" + s + "," + n + "): " + ex);
            return null;
        }
    }
    
    public String a(final fields fields, final String s) {
        if (fields == null) {
            return null;
        }
        try {
            return fields.a(Integer.parseInt(this.a.b(s)));
        }
        catch (Exception ex) {
            abljem.b("var_hash.getfld(<fieldset>," + s + "): " + ex);
            return null;
        }
    }
}
