// 
// Decompiled by Procyon v0.5.30
// 

package speedometer.A;

import javax.swing.SwingUtilities;

public abstract class G
{
    private Object B;
    private _A A;
    
    protected synchronized Object F() {
        return this.B;
    }
    
    private synchronized void A(final Object b) {
        this.B = b;
    }
    
    public abstract Object A();
    
    public void B() {
    }
    
    public void C() {
        final Thread b = this.A.B();
        if (b != null) {
            b.interrupt();
        }
        this.A.A();
    }
    
    public Object E() {
        while (true) {
            final Thread b = this.A.B();
            if (b == null) {
                break;
            }
            try {
                b.join();
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return this.F();
    }
    
    public G() {
        this.A = new _A(new Thread(new Runnable() {
            private final /* synthetic */ Runnable val$doFinished = new Runnable() {
                public void run() {
                    G.this.B();
                }
            };
            
            public void run() {
                try {
                    G.this.A(G.this.A());
                }
                finally {
                    G.this.A.A();
                }
                G.this.A.A();
                SwingUtilities.invokeLater(this.val$doFinished);
            }
        }));
    }
    
    public void D() {
        final Thread b = this.A.B();
        if (b != null) {
            b.start();
        }
    }
    
    private static class _A
    {
        private Thread A;
        
        _A(final Thread a) {
            this.A = a;
        }
        
        synchronized Thread B() {
            return this.A;
        }
        
        synchronized void A() {
            this.A = null;
        }
    }
}
