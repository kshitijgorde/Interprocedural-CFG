import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class y implements Runnable
{
    public static final int int = 1;
    public static final int a = 2;
    private l for;
    private Vector if;
    private boolean do;
    
    public void a() {
        if (!this.do) {
            this.for = null;
            this.if.removeAllElements();
            this.if = null;
        }
    }
    
    y(final l for1) {
        this.do = false;
        this.for = for1;
        this.if = new Vector();
    }
    
    public void if(final aa aa) {
        synchronized (this) {
            this.if.addElement(aa);
            if (!this.do) {
                this.do = true;
                new Thread(this).start();
            }
        }
    }
    
    public void run() {
        while (!this.for.h) {
            synchronized (this) {
                if (this.if.size() == 0) {
                    this.do = false;
                    if (this.for.h) {
                        this.a();
                    }
                    // monitorexit(this)
                    return;
                }
            }
            int i = -1;
            int n = -1;
            final int size = this.if.size();
            while (i == -1) {
                for (int j = 0; j < size; ++j) {
                    final aa aa = this.if.elementAt(j);
                    if (aa.d != 0 && n == -1) {
                        n = j;
                    }
                    if (aa.if == aa.d && i == -1) {
                        i = j;
                    }
                }
                if (i == -1 && n != -1) {
                    i = n;
                }
                if (i == -1) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (Exception ex) {}
                }
            }
            final aa aa2 = this.if.elementAt(i);
            this.if.removeElementAt(i);
            if (!aa2.try && aa2.a != 0) {
                this.a(aa2);
            }
        }
        this.do = false;
        this.a();
    }
    
    private void a(final aa aa) {
        aa.try = true;
        if (aa.a == 1) {
            try {
                final ab ab = new ab();
                if (ab.a(aa, this.for)) {
                    ab.p();
                }
                aa.h = 0;
                aa.char = false;
                aa.c = false;
            }
            catch (Exception ex) {}
        }
        if (aa.a == 2) {
            new p().a(aa, this.for);
        }
    }
}
