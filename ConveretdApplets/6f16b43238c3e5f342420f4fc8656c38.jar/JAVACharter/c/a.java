// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.util.Enumeration;
import java.util.Vector;
import JAVACharter.Charter;
import java.util.Hashtable;

public class a
{
    public Hashtable if;
    private Charter a;
    
    public a(final Charter a) {
        this.if = new Hashtable();
        this.a = a;
    }
    
    public i a(final String s, final int n) {
        Vector<Object> vector = this.if.get(s);
        if (vector == null) {
            this.if.put(s, new Vector());
            vector = this.if.get(s);
        }
        final i i = new i(n);
        vector.addElement(i);
        return i;
    }
    
    public Vector if(final String s) {
        return this.if.get(s);
    }
    
    public void a(final String s) {
        final Vector vector = this.if.get(s);
        if (vector != null) {
            vector.removeAllElements();
        }
    }
    
    public void if(final String s, final int n) {
        final Vector vector = this.if.get(s);
        if (vector != null) {
            vector.removeElementAt(n);
        }
    }
    
    public Enumeration a() {
        return this.if.keys();
    }
}
