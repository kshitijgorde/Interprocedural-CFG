// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

class qz
{
    protected w e;
    static boolean a;
    
    public qz(final w e) {
        this.e = e;
        this.e.p.setAnimated(true);
        this.e.p.setFullBufferUpdates(true);
    }
    
    public void flush() {
        this.e.p.newPixels(0, 0, this.e.q[2], this.e.q[1]);
    }
}
