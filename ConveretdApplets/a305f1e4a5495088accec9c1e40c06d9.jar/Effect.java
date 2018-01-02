import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class Effect
{
    Bitmap src;
    Bitmap map;
    Wipe wipe;
    int ticker;
    boolean complete;
    URL url;
    
    public Effect(final URL url, final Bitmap src, final Wipe wipe) {
        this.complete = false;
        this.url = url;
        this.src = src;
        this.wipe = wipe;
        this.start();
    }
    
    public URL getURL() {
        return this.url;
    }
    
    public void start() {
        this.ticker = 0;
        this.complete = false;
    }
    
    public boolean go(final Bitmap bitmap) {
        if (this.complete) {
            return this.complete;
        }
        this.wipe.go(bitmap, this.src, this.ticker++);
        if (this.ticker >= Wiper.wipeFactor) {
            this.complete = true;
        }
        return this.complete;
    }
    
    public boolean get(final int n, final int n2) {
        return this.wipe.get(n, n2) < this.ticker;
    }
}
