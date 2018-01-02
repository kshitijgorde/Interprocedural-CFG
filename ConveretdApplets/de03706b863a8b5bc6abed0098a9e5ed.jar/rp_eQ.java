// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_eQ extends Thread
{
    private rp_ax a;
    private int a;
    private boolean a;
    private Object a;
    
    public rp_eQ(final rp_ax a, final int a2, final Object a3) {
        this.a = false;
        this.a = null;
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.setName("EPTimer_" + a3 + "_" + a2);
    }
    
    public final void run() {
        try {
            Thread.sleep(this.a);
            try {
                this.a.a(this.a);
            }
            catch (Throwable t) {
                System.out.println("Error executing listener: " + this.a + " with data: " + this.a + " due to: " + t.getMessage());
                t.printStackTrace();
            }
        }
        catch (InterruptedException ex) {}
    }
}
