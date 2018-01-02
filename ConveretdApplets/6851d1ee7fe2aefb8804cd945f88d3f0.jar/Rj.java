// 
// Decompiled by Procyon v0.5.30
// 

class Rj extends Thread
{
    private final lj oa;
    
    Rj(final lj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (lj.a(this.oa)) {
            lj.a(this.oa).b(false);
            n.a(lj.a(this.oa));
            lj.a(this.oa).b(true);
        }
    }
}
