import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Zo extends Observable implements Observer
{
    private Hashtable IHb;
    private Hashtable JHb;
    private Hashtable KHb;
    private boolean LHb;
    
    public Zo() {
        this.IHb = new Hashtable();
        this.JHb = new Hashtable();
        this.KHb = new Hashtable();
        this.LHb = false;
    }
    
    public void b(final String s, final String s2) {
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0) {
            return;
        }
        this.KHb.put(s, s2);
    }
    
    public void g(final boolean lHb) {
        this.LHb = lHb;
    }
    
    public void x() {
        final Enumeration<String> keys = (Enumeration<String>)this.KHb.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final _p p = new _p(this, this.KHb.get(s), this.LHb);
            this.JHb.put(s, p);
            this.IHb.put(p.a(), new Integer(0));
        }
        final Enumeration<String> keys2 = (Enumeration<String>)this.JHb.keys();
        while (keys2.hasMoreElements()) {
            ((_p)this.JHb.get(keys2.nextElement())).start();
        }
        final Enumeration<String> keys3 = (Enumeration<String>)this.JHb.keys();
        while (keys3.hasMoreElements()) {
            final _p p2 = this.JHb.get(keys3.nextElement());
            try {
                p2.join();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean m(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        final _p p = this.JHb.get(s);
        return p != null && p.U();
    }
    
    public _ a(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final _p p = this.JHb.get(s);
        if (p != null) {
            return p.a();
        }
        return null;
    }
    
    public void reset() {
        this.KHb.clear();
        this.IHb.clear();
        final Enumeration<String> keys = this.JHb.keys();
        while (keys.hasMoreElements()) {
            ((_p)this.JHb.get(keys.nextElement())).a().clear();
        }
        this.JHb.clear();
    }
    
    public void notifyObservers(final Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }
    
    public void notifyObservers() {
        int n = 0;
        int n2 = 0;
        final Enumeration<Integer> elements = this.IHb.elements();
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
        if (observable instanceof _) {
            this.IHb.put(observable, o);
            this.notifyObservers();
        }
    }
}
