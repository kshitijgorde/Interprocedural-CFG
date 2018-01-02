// 
// Decompiled by Procyon v0.5.30
// 

class vea extends Thread
{
    private final super da;
    
    vea(final super da) {
        this.da = da;
    }
    
    public void run() {
        while (!this.da.isShowing()) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
        super._(this.da).repaint();
        super._(this.da).repaint();
    }
}
