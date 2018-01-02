// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

import java.util.Enumeration;
import java.util.Hashtable;

public class d
{
    private Hashtable if;
    private int a;
    
    public d(final int a) {
        this.if = new Hashtable();
        this.a = a;
    }
    
    public String a() {
        final Enumeration<String> keys = this.if.keys();
        if (keys.hasMoreElements()) {
            return keys.nextElement();
        }
        return "";
    }
    
    public d(final int n, final float n2, final int a) {
        this.a = a;
        this.if = new Hashtable(n, n2);
    }
    
    public int if() {
        return this.a;
    }
    
    public b a(final String s) {
        if (!this.if.containsKey(s)) {
            this.if.put(s, new b());
        }
        return this.if.get(s);
    }
    
    public void if(final String s) {
        this.if.remove(s);
    }
    
    public b for(final String s) {
        return this.if.get(s);
    }
    
    public boolean do(final String s) {
        return this.if.containsKey(s);
    }
}
