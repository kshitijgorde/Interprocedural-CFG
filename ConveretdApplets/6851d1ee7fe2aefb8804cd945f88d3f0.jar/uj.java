// 
// Decompiled by Procyon v0.5.30
// 

class uj extends Thread
{
    private final n ta;
    
    uj(final n ta) {
        this.ta = ta;
    }
    
    public void run() {
        synchronized (this.ta) {
            n._(this.ta)._();
            n._(this.ta).repaint();
            this.ta.b(true);
        }
    }
}
