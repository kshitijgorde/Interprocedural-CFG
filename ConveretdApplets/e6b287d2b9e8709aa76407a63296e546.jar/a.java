// 
// Decompiled by Procyon v0.5.30
// 

class a extends Thread
{
    private final /* synthetic */ Client a;
    
    a(final Client a) {
        this.a = a;
    }
    
    public void run() {
        while (this.a.a() == 0) {
            try {
                Thread.sleep(25L);
            }
            catch (Exception ex) {}
        }
        System.out.println("Load complete.\n");
    }
}
