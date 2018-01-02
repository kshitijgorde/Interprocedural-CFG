import java.util.Iterator;
import a.a.h;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Thread
{
    private Vector if;
    private long for;
    private boolean a;
    h do;
    
    public c(final long for1) {
        this.if = new Vector();
        this.a = false;
        this.do = new h.j() {
            public String a() {
                return "Sleep Period";
            }
            
            public long int() {
                return c.this.for;
            }
            
            public void a(final long n) {
                c.access$1(c.this, n);
            }
        };
        this.for = for1;
    }
    
    public double a() {
        return this.for / 1000.0;
    }
    
    public void a(final long for1) {
        this.for = for1;
    }
    
    public void a(final a a) {
        if (!this.if.contains(a)) {
            this.if.addElement(a);
        }
        if (!this.a) {
            this.start();
        }
    }
    
    public void run() {
        this.a = true;
        while (true) {
            try {
                Thread.sleep(this.for);
            }
            catch (InterruptedException ex) {}
            final Iterator iterator = this.if.iterator();
            while (iterator.hasNext()) {
                iterator.next().try();
            }
        }
    }
    
    static /* synthetic */ void access$1(final c c, final long for1) {
        c.for = for1;
    }
    
    public interface a
    {
        void try();
    }
}
