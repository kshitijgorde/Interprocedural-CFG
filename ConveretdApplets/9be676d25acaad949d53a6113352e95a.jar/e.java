import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class e extends Thread
{
    protected Vector do;
    protected Vector for;
    protected int if;
    static String a;
    
    public e() {
        this.do = new Vector();
        this.for = new Vector();
        this.start();
    }
    
    public void a(final int if1) {
        this.if = if1;
    }
    
    public void a(final String a) {
        e.a = a;
    }
    
    public void if(final String s) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("SYMBOL", s);
        synchronized (this.for) {
            this.for.addElement(hashtable);
        }
        // monitorexit(this.for)
    }
    
    public void a(final a a) {
        this.do.addElement(a);
    }
    
    private void if(final Hashtable hashtable) {
        for (int i = 0; i < this.do.size(); ++i) {
            ((a)this.do.elementAt(i)).a(hashtable);
        }
    }
    
    public abstract void run();
    
    public void a(final Hashtable hashtable) {
        for (int i = 0; i < this.for.size(); ++i) {
            final Hashtable<String, Object> hashtable2 = this.for.elementAt(i);
            if (hashtable.get("SYMBOL").equals(hashtable2.get("SYMBOL"))) {
                final Enumeration<K> keys = (Enumeration<K>)hashtable.keys();
                while (keys.hasMoreElements()) {
                    final String s = (String)keys.nextElement();
                    hashtable2.put(s, hashtable.get(s));
                }
                this.if(hashtable2);
                return;
            }
        }
    }
}
