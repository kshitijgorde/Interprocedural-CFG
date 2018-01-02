import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class BSPLeaf extends BSPNode
{
    Vector slist;
    
    void fix_bounds(final int n, final int n2) {
        super.xl = Math.min(super.xl, n);
        super.yl = Math.min(super.yl, n2);
        super.xu = Math.max(super.xu, n);
        super.yu = Math.max(super.yu, n2);
    }
    
    BSPLeaf(final Vector slist) {
        this.slist = slist;
        final int n = 1000000;
        super.yl = n;
        super.xl = n;
        final int n2 = -1000000;
        super.yu = n2;
        super.xu = n2;
        super.isleaf = true;
        for (int i = 0; i != slist.size(); ++i) {
            final Seg seg = this.slist.elementAt(i);
            this.fix_bounds(seg.x, seg.y);
            this.fix_bounds(seg.x + seg.dx, seg.y + seg.dy);
        }
    }
}
