// 
// Decompiled by Procyon v0.5.30
// 

class Qj extends Thread
{
    private final lj oa;
    
    Qj(final lj oa) {
        this.oa = oa;
    }
    
    public void run() {
        synchronized (lj.a(this.oa)) {
            n.b(lj.a(this.oa));
        }
    }
}
