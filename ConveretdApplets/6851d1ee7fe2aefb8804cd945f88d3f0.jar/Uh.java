import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Uh extends Observable implements Observer
{
    private Hashtable rja;
    private Hashtable sja;
    private Hashtable tja;
    private boolean uja;
    
    public Uh() {
        this.rja = new Hashtable();
        this.sja = new Hashtable();
        this.tja = new Hashtable();
        this.uja = false;
    }
    
    public void a(final String s, final String s2) {
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0) {
            return;
        }
        this.tja.put(s, s2);
    }
    
    public void setUseCache(final boolean uja) {
        this.uja = uja;
    }
    
    public void f() {
        final Enumeration<String> keys = (Enumeration<String>)this.tja.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final Xh xh = new Xh(this, this.tja.get(s), this.uja);
            this.sja.put(s, xh);
            this.rja.put(xh.b(), new Integer(0));
        }
        final Enumeration<String> keys2 = (Enumeration<String>)this.sja.keys();
        while (keys2.hasMoreElements()) {
            ((Xh)this.sja.get(keys2.nextElement())).start();
        }
        final Enumeration<String> keys3 = (Enumeration<String>)this.sja.keys();
        while (keys3.hasMoreElements()) {
            final Xh xh2 = this.sja.get(keys3.nextElement());
            try {
                xh2.join();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean c(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        final Xh xh = this.sja.get(s);
        return xh != null && xh.c();
    }
    
    public q b(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final Xh xh = this.sja.get(s);
        if (xh != null) {
            return xh.b();
        }
        return null;
    }
    
    public void reset() {
        this.tja.clear();
        this.rja.clear();
        final Enumeration<String> keys = this.sja.keys();
        while (keys.hasMoreElements()) {
            ((Xh)this.sja.get(keys.nextElement())).b().clear();
        }
        this.sja.clear();
    }
    
    public void notifyObservers(final Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }
    
    public void notifyObservers() {
        int n = 0;
        int n2 = 0;
        final Enumeration<Integer> elements = this.rja.elements();
        while (elements.hasMoreElements()) {
            n2 += elements.nextElement();
            ++n;
        }
        int n3 = 0;
        if (n > 0) {
            n3 = n2 / n;
        }
        this.setChanged();
        super.notifyObservers(new Integer(n3));
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable instanceof q) {
            this.rja.put(observable, o);
            this.notifyObservers();
        }
    }
}
