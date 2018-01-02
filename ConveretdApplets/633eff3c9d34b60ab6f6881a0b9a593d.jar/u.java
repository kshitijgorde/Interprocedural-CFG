// 
// Decompiled by Procyon v0.5.30
// 

class u implements e
{
    private final IpixViewer a;
    
    u(final IpixViewer a) {
        this.a = a;
    }
    
    public void a(final int n, final float n2) {
        if (n == 2 || n == 4 || n == -1) {
            this.a.j = null;
            synchronized (this.a.i) {
                this.a.i.notify();
            }
        }
    }
}
