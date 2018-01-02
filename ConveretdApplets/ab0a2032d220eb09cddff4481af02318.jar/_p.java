import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

class _p extends Thread
{
    private _ GHb;
    private String url;
    boolean HHb;
    private final Zo n;
    
    _p(final Zo n, final String url, final boolean b) {
        this.n = n;
        this.url = url;
        (this.GHb = new _()).g(b);
        this.GHb.addObserver(n);
        this.HHb = false;
    }
    
    public void run() {
        this.HHb = this.GHb.d(this.url);
    }
    
    public boolean U() {
        return this.HHb;
    }
    
    public _ a() {
        return this.GHb;
    }
}
