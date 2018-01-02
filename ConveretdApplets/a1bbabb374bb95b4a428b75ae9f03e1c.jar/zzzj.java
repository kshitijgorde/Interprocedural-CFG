import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class zzzj
{
    private float Lt;
    private Vector lT;
    
    public zzzj() {
        this.Lt = 0.0f;
        this.lT = new Vector();
    }
    
    private synchronized void lt() {
        this.Lt = 0.0f;
        final Enumeration<zzzq> elements = this.lT.elements();
        while (elements.hasMoreElements()) {
            this.Lt += elements.nextElement().bdh;
        }
    }
    
    void b(final zzzq zzzq) {
        synchronized (this) {
            if (this.lT.contains(zzzq)) {
                this.lt();
            }
        }
    }
    
    public synchronized Enumeration zzze() {
        return ((Vector)this.lT.clone()).elements();
    }
    
    public synchronized zzzq zzzd(final int n) {
        return this.lT.elementAt(n);
    }
    
    public synchronized float zzzg(final float n) {
        if (this.Lt > 0.0f) {
            return n / this.Lt;
        }
        return 0.0f;
    }
    
    public zzzq zzzf() {
        final zzzq zzzq = new zzzq(this);
        synchronized (this) {
            this.lT.addElement(zzzq);
            this.lt();
        }
        return zzzq;
    }
}
