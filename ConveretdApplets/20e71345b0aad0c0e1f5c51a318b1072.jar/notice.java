// 
// Decompiled by Procyon v0.5.30
// 

class notice implements Runnable
{
    ImageZoom2 IZ2;
    
    public notice(final ImageZoom2 IZ) {
        this.IZ2 = IZ;
    }
    
    public void run() {
        while (true) {
            this.IZ2.wakeUp();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
