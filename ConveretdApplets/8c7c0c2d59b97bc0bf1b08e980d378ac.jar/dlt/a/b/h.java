// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.b;

public class h extends g
{
    private h(final c c, final c c2) {
        super(c, c2);
    }
    
    public h(final c[] array) {
        this.a(array);
    }
    
    public void a(final c[] array) {
        final g g = new g(array[0], array[2]);
        final g g2 = new g(array[0], array[1]);
        g.for();
        g2.for();
        final g a = dlt.a.b.g.a(g, g2);
        a.for();
        this.a = a.do();
        this.do = a.a();
        this.if = a.int();
    }
}
