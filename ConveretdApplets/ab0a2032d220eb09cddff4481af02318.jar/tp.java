// 
// Decompiled by Procyon v0.5.30
// 

class tp extends Thread
{
    private final var n;
    
    tp(final var n) {
        this.n = n;
    }
    
    public void run() {
        while (!this.n.isShowing()) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        this.n.hEb.repaint();
        var.b(this.n).repaint();
    }
}
