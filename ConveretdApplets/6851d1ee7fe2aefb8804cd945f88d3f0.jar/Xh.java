import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

class Xh extends Thread
{
    private q lja;
    private String url;
    boolean mja;
    private final Uh ta;
    
    Xh(final Uh ta, final String url, final boolean useCache) {
        this.ta = ta;
        this.url = url;
        (this.lja = new q()).setUseCache(useCache);
        this.lja.addObserver(ta);
        this.mja = false;
    }
    
    public void run() {
        this.mja = this.lja.f(this.url);
    }
    
    public boolean c() {
        return this.mja;
    }
    
    public q b() {
        return this.lja;
    }
}
