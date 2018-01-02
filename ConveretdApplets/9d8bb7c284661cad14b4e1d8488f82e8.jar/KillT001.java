// 
// Decompiled by Procyon v0.5.30
// 

class KillT001 extends Thread
{
    SetTogether \u00c8;
    final int \u0123 = 5000;
    
    public KillT001(final SetTogether \u00e8) {
        super("finishThread");
        this.\u00c8 = \u00e8;
    }
    
    public void run() {
        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException ex) {}
        this.\u00c8.destroy();
    }
}
