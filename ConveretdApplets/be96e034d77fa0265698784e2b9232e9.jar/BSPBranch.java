// 
// Decompiled by Procyon v0.5.30
// 

class BSPBranch extends BSPNode
{
    BSPNode lbranch;
    BSPNode rbranch;
    int x;
    int y;
    int dx;
    int dy;
    
    BSPBranch(final int x, final int y, final int dx, final int dy, final BSPNode lbranch, final BSPNode rbranch) {
        this.lbranch = lbranch;
        this.rbranch = rbranch;
        super.isleaf = false;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        super.xl = Math.min(lbranch.xl, rbranch.xl);
        super.xu = Math.max(lbranch.xu, rbranch.xu);
        super.yl = Math.min(lbranch.yl, rbranch.yl);
        super.yu = Math.max(lbranch.yu, rbranch.yu);
    }
}
