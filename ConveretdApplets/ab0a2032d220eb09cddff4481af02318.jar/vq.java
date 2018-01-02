// 
// Decompiled by Procyon v0.5.30
// 

class vq extends Thread
{
    private final np va;
    
    vq(final np va) {
        this.va = va;
    }
    
    public void run() {
        synchronized (np._(this.va)) {
            var.a(np._(this.va)).doSaveConfigAction();
        }
    }
}
