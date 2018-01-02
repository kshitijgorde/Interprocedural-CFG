// 
// Decompiled by Procyon v0.5.30
// 

package y;

final class v extends em
{
    af[] a;
    es[] a;
    
    v(final dm dm) {
        super(dm.a(1716521484), dm);
        this.a = new af[2];
        this.a = new es[2];
        for (int i = 0; i < 2; ++i) {
            this.a[i] = new es();
            super.a.a(this.a[i], 10, 2, 0, 1, 1, i, 0);
            (this.a[i] = new af(8, 100, 2, -1, null, true, false, true)).a(0, dm.a(1716521485));
            this.a[i].a(1, dm.a(1716521483));
            this.a[i].a(1, 0.75);
            super.a.a(this.a[i], 1, 1, i, 1, true);
        }
        this.pack();
    }
}
