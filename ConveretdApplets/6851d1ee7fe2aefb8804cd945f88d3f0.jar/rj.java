// 
// Decompiled by Procyon v0.5.30
// 

class rj extends Thread
{
    private final n ta;
    
    rj(final n ta) {
        this.ta = ta;
    }
    
    public void run() {
        while (!this.ta.isShowing()) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        n.b(this.ta).repaint();
        n._(this.ta).repaint();
    }
}
