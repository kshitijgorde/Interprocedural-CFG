import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class implements extends Observable implements Observer
{
    private Hashtable fqa;
    private Hashtable gqa;
    private Hashtable hqa;
    private boolean iqa;
    
    public implements() {
        this.fqa = new Hashtable();
        this.gqa = new Hashtable();
        this.hqa = new Hashtable();
        this.iqa = false;
    }
    
    public void a(final String s, final String s2) {
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0) {
            return;
        }
        this.hqa.put(s, s2);
    }
    
    public void _(final boolean iqa) {
        this.iqa = iqa;
    }
    
    public void C() {
        final Enumeration<String> keys = (Enumeration<String>)this.hqa.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final instanceof instanceof1 = new instanceof(this, this.hqa.get(s), this.iqa);
            this.gqa.put(s, instanceof1);
            this.fqa.put(instanceof1.a(), new Integer(0));
        }
        final Enumeration<String> keys2 = (Enumeration<String>)this.gqa.keys();
        while (keys2.hasMoreElements()) {
            ((instanceof)this.gqa.get(keys2.nextElement())).start();
        }
        final Enumeration<String> keys3 = (Enumeration<String>)this.gqa.keys();
        while (keys3.hasMoreElements()) {
            final instanceof instanceof2 = this.gqa.get(keys3.nextElement());
            try {
                instanceof2.join();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean k(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        final instanceof instanceof1 = this.gqa.get(s);
        return instanceof1 != null && instanceof1.f();
    }
    
    public this b(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final instanceof instanceof1 = this.gqa.get(s);
        if (instanceof1 != null) {
            return instanceof1.a();
        }
        return null;
    }
    
    public void reset() {
        this.hqa.clear();
        this.fqa.clear();
        final Enumeration<String> keys = this.gqa.keys();
        while (keys.hasMoreElements()) {
            ((instanceof)this.gqa.get(keys.nextElement())).a().clear();
        }
        this.gqa.clear();
    }
    
    public void notifyObservers(final Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }
    
    public void notifyObservers() {
        int n = 0;
        int n2 = 0;
        final Enumeration<Integer> elements = this.fqa.elements();
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
        if (observable instanceof this) {
            this.fqa.put(observable, o);
            this.notifyObservers();
        }
    }
}
