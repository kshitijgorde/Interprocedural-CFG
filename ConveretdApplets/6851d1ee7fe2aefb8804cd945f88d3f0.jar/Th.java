import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Th
{
    private Hashtable vja;
    private Hashtable wja;
    private long xja;
    private long yja;
    private int zja;
    private int La;
    private Hashtable Aja;
    
    Th(final int n) {
        this.vja = new Hashtable();
        this.wja = new Hashtable();
        this.xja = 0L;
        this.yja = System.currentTimeMillis();
        this.zja = 2;
        this.La = 10;
        this.Aja = new Hashtable();
        if (n > 0) {
            this.xja = 60000L * n;
        }
    }
    
    private void Y() {
        final Vector<String> vector = new Vector<String>();
        boolean b = false;
        final Enumeration<String> keys = this.vja.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!this.Aja.containsKey(s)) {
                final Yh yh = this.vja.get(s);
                if (System.currentTimeMillis() - this.xja <= yh.b()) {
                    continue;
                }
                yh.b(false);
                vector.addElement(s);
            }
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.vja.remove(vector.elementAt(i));
            b = true;
        }
        final Enumeration<String> keys2 = this.wja.keys();
        while (keys2.hasMoreElements()) {
            final String s2 = keys2.nextElement();
            if (!this.Aja.containsKey(s2)) {
                final Yh yh2 = this.wja.get(s2);
                if (System.currentTimeMillis() - this.xja <= yh2.b()) {
                    continue;
                }
                yh2.b(true);
                vector.addElement(s2);
            }
        }
        for (int j = 0; j < vector.size(); ++j) {
            this.wja.remove(vector.elementAt(j));
            b = true;
        }
        vector.removeAllElements();
        if (b) {
            System.gc();
        }
        this.yja = System.currentTimeMillis();
    }
    
    synchronized void b(final int n) {
        this.zja = Math.max(n, 0);
    }
    
    synchronized void a(final int n) {
        this.La = Math.max(n, 0);
    }
    
    synchronized int g() {
        return this.zja;
    }
    
    synchronized int b() {
        return this.La;
    }
    
    public synchronized boolean _(final String s, final q q, final boolean b, final String s2, final int n) {
        return this.b(s, b).a(q, b, s2, n);
    }
    
    public synchronized int _(final String s, final boolean b) {
        return this.b(s, b).b(b);
    }
    
    public synchronized String a(final String s, final boolean b) {
        return this.b(s, b).a(b);
    }
    
    public synchronized String b(final String s, final boolean b) {
        return this.b(s, b).b(b);
    }
    
    public synchronized Vh a(final String s, final boolean b) {
        final Vh _ = this.b(s, b)._(b, b ? this.La : this.zja);
        if (this.xja > 0L && System.currentTimeMillis() - this.xja / 4L > this.yja) {
            this.Y();
        }
        return _;
    }
    
    private Yh b(final String s, final boolean b) {
        Yh yh;
        if (b) {
            yh = this.wja.get(s);
            if (yh == null) {
                yh = new Yh(s);
                this.wja.put(s, yh);
            }
        }
        else {
            yh = this.vja.get(s);
            if (yh == null) {
                yh = new Yh(s);
                this.vja.put(s, yh);
            }
        }
        return yh;
    }
    
    public synchronized void a(final Vector vector) {
        this.Aja.clear();
        for (int i = 0; i < vector.size(); ++i) {
            this.Aja.put(vector.elementAt(i), Boolean.TRUE);
        }
    }
}
