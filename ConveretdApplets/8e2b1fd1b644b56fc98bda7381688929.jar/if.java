import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class if
{
    private Hashtable jqa;
    private Hashtable kqa;
    private long lqa;
    private long mqa;
    private int nqa;
    private int Ga;
    private Hashtable oqa;
    
    if(final int n) {
        this.jqa = new Hashtable();
        this.kqa = new Hashtable();
        this.lqa = 0L;
        this.mqa = System.currentTimeMillis();
        this.nqa = 2;
        this.Ga = 10;
        this.oqa = new Hashtable();
        if (n > 0) {
            this.lqa = 60000L * n;
        }
    }
    
    private void I() {
        final Vector<String> vector = new Vector<String>();
        boolean b = false;
        final Enumeration<String> keys = this.jqa.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!this.oqa.containsKey(s)) {
                final interface interface1 = this.jqa.get(s);
                if (System.currentTimeMillis() - this.lqa <= interface1.a()) {
                    continue;
                }
                interface1.a(false);
                vector.addElement(s);
            }
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.jqa.remove(vector.elementAt(i));
            b = true;
        }
        final Enumeration<String> keys2 = this.kqa.keys();
        while (keys2.hasMoreElements()) {
            final String s2 = keys2.nextElement();
            if (!this.oqa.containsKey(s2)) {
                final interface interface2 = this.kqa.get(s2);
                if (System.currentTimeMillis() - this.lqa <= interface2.a()) {
                    continue;
                }
                interface2.a(true);
                vector.addElement(s2);
            }
        }
        for (int j = 0; j < vector.size(); ++j) {
            this.kqa.remove(vector.elementAt(j));
            b = true;
        }
        vector.removeAllElements();
        if (b) {
            System.gc();
        }
        this.mqa = System.currentTimeMillis();
    }
    
    synchronized void a(final int n) {
        this.nqa = Math.max(n, 0);
    }
    
    synchronized void _(final int n) {
        this.Ga = Math.max(n, 0);
    }
    
    synchronized int h() {
        return this.nqa;
    }
    
    synchronized int e() {
        return this.Ga;
    }
    
    public synchronized boolean a(final String s, final this this2, final boolean b, final String s2, final int n) {
        return this.a(s, b).a(this2, b, s2, n);
    }
    
    public synchronized int a(final String s, final boolean b) {
        return this.a(s, b).b(b);
    }
    
    public synchronized String b(final String s, final boolean b) {
        return this.a(s, b).a(b);
    }
    
    public synchronized String _(final String s, final boolean b) {
        return this.a(s, b).b(b);
    }
    
    public synchronized double[][] _(final String s, final boolean b) {
        final double[][] a = this.a(s, b).a(b, b ? this.Ga : this.nqa);
        if (this.lqa > 0L && System.currentTimeMillis() - this.lqa / 4L > this.mqa) {
            this.I();
        }
        return a;
    }
    
    private interface a(final String s, final boolean b) {
        interface interface1;
        if (b) {
            interface1 = this.kqa.get(s);
            if (interface1 == null) {
                interface1 = new interface(s);
                this.kqa.put(s, interface1);
            }
        }
        else {
            interface1 = this.jqa.get(s);
            if (interface1 == null) {
                interface1 = new interface(s);
                this.jqa.put(s, interface1);
            }
        }
        return interface1;
    }
    
    public synchronized void b(final Vector vector) {
        this.oqa.clear();
        for (int i = 0; i < vector.size(); ++i) {
            this.oqa.put(vector.elementAt(i), Boolean.TRUE);
        }
    }
}
