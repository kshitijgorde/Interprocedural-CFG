// 
// Decompiled by Procyon v0.5.30
// 

final class ak
{
    private boolean a;
    
    ak(final boolean a) {
        this.a = a;
    }
    
    synchronized void a() throws InterruptedException {
        while (true) {
            ak ak;
            if (this.a) {
                ak = this;
                if (!c.l) {
                    break;
                }
            }
            else {
                ak = this;
            }
            ak.wait();
        }
        this.a = false;
    }
    
    synchronized void b() {
        if (!this.a) {
            this.a = true;
        }
        this.notifyAll();
    }
    
    synchronized boolean c() {
        final boolean a = this.a;
        this.a = false;
        return a;
    }
}
