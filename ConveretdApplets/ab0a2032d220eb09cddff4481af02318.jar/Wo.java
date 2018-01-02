import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wo
{
    private Hashtable ZHb;
    private Hashtable _Ib;
    private long aIb;
    private long bIb;
    private int cIb;
    private int KDb;
    private boolean dIb;
    private Hashtable eIb;
    
    Wo(final int n) {
        this.ZHb = new Hashtable();
        this._Ib = new Hashtable();
        this.aIb = 0L;
        this.bIb = System.currentTimeMillis();
        this.cIb = 2;
        this.KDb = 10;
        this.dIb = true;
        this.eIb = new Hashtable();
        if (n > 0) {
            this.aIb = 60000L * n;
        }
    }
    
    private void y() {
        final Vector<String> vector = new Vector<String>();
        boolean b = false;
        final Enumeration<String> keys = this.ZHb.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!this.eIb.containsKey(s)) {
                final bp bp = this.ZHb.get(s);
                if (System.currentTimeMillis() - this.aIb <= bp.a()) {
                    continue;
                }
                bp.a(false);
                vector.addElement(s);
            }
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.ZHb.remove(vector.elementAt(i));
            b = true;
        }
        final Enumeration<String> keys2 = this._Ib.keys();
        while (keys2.hasMoreElements()) {
            final String s2 = keys2.nextElement();
            if (!this.eIb.containsKey(s2)) {
                final bp bp2 = this._Ib.get(s2);
                if (System.currentTimeMillis() - this.aIb <= bp2.a()) {
                    continue;
                }
                bp2.a(true);
                vector.addElement(s2);
            }
        }
        for (int j = 0; j < vector.size(); ++j) {
            this._Ib.remove(vector.elementAt(j));
            b = true;
        }
        vector.removeAllElements();
        if (b) {
            System.gc();
        }
        this.bIb = System.currentTimeMillis();
    }
    
    synchronized void L(final int n) {
        this.cIb = Math.max(n, 0);
    }
    
    synchronized void K(final int n) {
        this.KDb = Math.max(n, 0);
    }
    
    synchronized int G() {
        return this.cIb;
    }
    
    synchronized int E() {
        return this.KDb;
    }
    
    public synchronized boolean a(final String s, final _ _, final boolean b, final String s2, final int n) {
        return this.b(s, b).a(_, b, s2, n);
    }
    
    public synchronized int a(final String s, final boolean b) {
        return this.b(s, b)._(b);
    }
    
    public synchronized String a(final String s, final boolean b) {
        return this.b(s, b)._(b);
    }
    
    public synchronized String b(final String s, final boolean b) {
        return this.b(s, b).a(b);
    }
    
    public synchronized double[][] b(final String s, final boolean b) {
        final double[][] a = this.b(s, b).a(b, b ? this.KDb : this.cIb);
        if (this.aIb > 0L && System.currentTimeMillis() - this.aIb / 4L > this.bIb) {
            this.y();
        }
        return a;
    }
    
    private bp b(final String s, final boolean b) {
        bp bp;
        if (b) {
            bp = this._Ib.get(s);
            if (bp == null) {
                bp = new bp(s);
                this._Ib.put(s, bp);
            }
        }
        else {
            bp = this.ZHb.get(s);
            if (bp == null) {
                bp = new bp(s);
                this.ZHb.put(s, bp);
            }
        }
        return bp;
    }
    
    public synchronized void b(final Vector vector) {
        this.eIb.clear();
        for (int i = 0; i < vector.size(); ++i) {
            this.eIb.put(vector.elementAt(i), Boolean.TRUE);
        }
    }
}
