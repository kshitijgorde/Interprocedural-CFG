// 
// Decompiled by Procyon v0.5.30
// 

class ob extends Thread
{
    private final esChat a;
    
    ob(final esChat a) {
        this.a = a;
    }
    
    public void run() {
        try {
            while (true) {
                this.a.a((String)null, 0);
                try {
                    Thread.sleep(20L);
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
    }
}
