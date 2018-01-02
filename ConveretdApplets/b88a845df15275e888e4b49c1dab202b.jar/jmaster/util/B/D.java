// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.B;

import java.util.ArrayList;
import jmaster.util.log.B;
import java.util.List;
import jmaster.util.log.A;

public class D
{
    private A B;
    protected String A;
    protected List C;
    
    public D() {
        this.B = jmaster.util.log.B.getInstance().getLog(this);
        this.C = new ArrayList();
    }
    
    public String B() {
        return this.A;
    }
    
    public void A(final String a) {
        this.A = a;
    }
    
    public Thread A(final jmaster.util.B.B b) {
        if (this.B.B()) {
            this.B.D("Creating thread: " + b.D());
        }
        final Thread thread = new Thread(b.E());
        thread.setName(this.A + b.D());
        if (b.A()) {
            thread.setDaemon(b.A());
        }
        if (b.C()) {
            thread.start();
        }
        b.A(thread);
        this.C.add(b);
        return thread;
    }
    
    public jmaster.util.B.B B(final String s) {
        jmaster.util.B.B b = null;
        for (int n = 0; b == null && n < this.C.size(); ++n) {
            final jmaster.util.B.B b2 = this.C.get(n);
            if (s.equals(b2.D())) {
                b = b2;
            }
        }
        return b;
    }
    
    public void C(final String s) {
        final jmaster.util.B.B b = this.B(s);
        if (b == null) {
            throw new NullPointerException("Runnable not found: " + s);
        }
        if (b.B() != null) {
            b.B().interrupt();
        }
    }
    
    public synchronized void A() {
        for (int i = 0; i < this.C.size(); ++i) {
            final jmaster.util.B.B b = this.C.get(i);
            try {
                if (b.B() != null && b.B().isAlive()) {
                    b.B().interrupt();
                }
            }
            catch (Exception ex) {
                this.B.E(ex, ex);
            }
        }
        this.C.clear();
        this.C = null;
    }
}
