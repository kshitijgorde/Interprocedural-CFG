// 
// Decompiled by Procyon v0.5.30
// 

class RefreshImages extends Thread
{
    long minutes;
    AniS mom;
    boolean alive;
    
    public RefreshImages(final int n, final AniS mom) {
        this.minutes = n;
        this.mom = mom;
        this.alive = true;
    }
    
    public void killit() {
        this.alive = false;
    }
    
    public void run() {
        try {
            while (this.alive) {
                Thread.sleep(this.minutes * 60L * 1000L);
                if (this.alive) {
                    this.mom.reloadImages();
                }
            }
        }
        catch (Exception ex) {}
    }
}
