// 
// Decompiled by Procyon v0.5.30
// 

class Pj extends Thread
{
    private final lj oa;
    
    Pj(final lj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (lj.a(this.oa)) {
            n.m(lj.a(this.oa));
        }
    }
}
