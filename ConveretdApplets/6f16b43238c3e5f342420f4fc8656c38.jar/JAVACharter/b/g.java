// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.b;

import java.util.Enumeration;
import java.util.Hashtable;

public class g extends e
{
    private Hashtable try;
    
    public g() {
        this.try = new Hashtable();
    }
    
    public void if(final int n, final Object o) {
        final Hashtable hashtable = (Hashtable)o;
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            this.try.put(nextElement, hashtable.get(nextElement));
        }
    }
    
    public void a(final Object o, final int n, final int n2) {
        final Hashtable hashtable = (Hashtable)o;
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            this.try.put(nextElement, hashtable.get(nextElement));
        }
    }
    
    public void a(final Object o, final int n) {
        final Hashtable hashtable = (Hashtable)o;
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            this.try.put(nextElement, hashtable.get(nextElement));
        }
    }
    
    public int a() {
        return this.try.size();
    }
    
    public int for() {
        return this.try.size();
    }
    
    public void a(final int n, final Object o) {
        final Hashtable hashtable = (Hashtable)o;
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            this.try.put(nextElement, hashtable.get(nextElement));
        }
    }
    
    public Hashtable byte() {
        return this.try;
    }
    
    public void if() {
    }
    
    public void do() {
    }
}
