// 
// Decompiled by Procyon v0.5.30
// 

class tunnel_writer extends Thread
{
    tunnel t;
    
    tunnel_writer(final tunnel t) {
        this.t = t;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            if (this.t.outbuf.size() > 0) {
                this.t.post();
            }
        }
    }
}
