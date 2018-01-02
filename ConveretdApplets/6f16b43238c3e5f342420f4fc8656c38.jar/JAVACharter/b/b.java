// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class b
{
    private Hashtable do;
    private String a;
    private Vector if;
    
    public b() {
        this.if = new Vector();
        this.do = new Hashtable();
    }
    
    public b(final int n, final float n2) {
        this.if = new Vector();
        this.do = new Hashtable(n, n2);
    }
    
    public void a(final String s, final int n, final Object o) {
        this.do.get(s).if(n, o);
    }
    
    public void a(final String s, final Object o, final int n) {
        this.do.get(s).a(o, 0, n);
    }
    
    public void if(final String s, final Object o, final int n) {
        this.do.get(s).a(o, n);
    }
    
    public void if(final String s, final int n, final Object o) {
        this.do.get(s).a(n, o);
    }
    
    public void if() {
        final Enumeration<String> keys = this.do.keys();
        while (keys.hasMoreElements()) {
            ((e)this.do.get(keys.nextElement())).if();
        }
    }
    
    public void do() {
        final Enumeration<String> keys = this.do.keys();
        while (keys.hasMoreElements()) {
            ((e)this.do.get(keys.nextElement())).do();
        }
    }
    
    public e a(final String s) {
        return this.do.get(s);
    }
    
    public e for() {
        return this.do.get(this.a);
    }
    
    public void if(final String a) {
        this.a = a;
    }
    
    public void a(final String s, final e e) {
        if (!this.do.containsKey(s)) {
            this.do.put(s, e);
        }
        if (!this.if.contains(s)) {
            this.if.addElement(s);
        }
    }
    
    public void a(final int n, final int n2) {
        final Enumeration<String> keys = this.do.keys();
        while (keys.hasMoreElements()) {
            ((e)this.do.get(keys.nextElement())).a(n, n2);
        }
    }
    
    public Enumeration a() {
        return this.do.keys();
    }
}
