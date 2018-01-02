// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class LayeredSync implements Sync
{
    protected final Sync outer_;
    protected final Sync inner_;
    
    public LayeredSync(final Sync outer_, final Sync inner_) {
        this.outer_ = outer_;
        this.inner_ = inner_;
    }
    
    public void acquire() throws InterruptedException {
        this.outer_.acquire();
        try {
            this.inner_.acquire();
        }
        catch (InterruptedException ex) {
            this.outer_.release();
            throw ex;
        }
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        final long n2 = (n <= 0L) ? 0L : System.currentTimeMillis();
        long n3 = n;
        if (this.outer_.attempt(n3)) {
            try {
                if (n > 0L) {
                    n3 = n - (System.currentTimeMillis() - n2);
                }
                if (this.inner_.attempt(n3)) {
                    return true;
                }
                this.outer_.release();
                return false;
            }
            catch (InterruptedException ex) {
                this.outer_.release();
                throw ex;
            }
        }
        return false;
    }
    
    public void release() {
        this.inner_.release();
        this.outer_.release();
    }
}
