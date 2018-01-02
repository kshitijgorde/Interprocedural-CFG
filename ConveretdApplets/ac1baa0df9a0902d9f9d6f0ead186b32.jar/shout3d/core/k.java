// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

class k extends Thread
{
    Shout3DViewer a;
    Object b;
    c c;
    d d;
    
    public void a(final Shout3DViewer a, final c c, final d d) {
        this.a = a;
        this.c = c;
        this.d = d;
        if (a != null && c.e() != null) {
            super.start();
        }
    }
    
    void a(final Object o) {
        if (o == null) {
            this.d.a(this.c, false);
            this.c.a(null);
            return;
        }
        this.d.a(this.c, true);
        this.c.a(o);
        this.d.a(this.c.e(), o);
    }
    
    public void run() {
        try {
            this.c.d();
            this.a(this.c.a());
        }
        catch (Exception ex) {
            System.out.println("Resource loading ERROR: " + ex);
            ex.printStackTrace();
        }
    }
}
