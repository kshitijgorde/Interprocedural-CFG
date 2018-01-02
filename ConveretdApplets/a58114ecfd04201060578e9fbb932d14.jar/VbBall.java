import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class VbBall
{
    float x;
    float y;
    float z;
    int Xz;
    int ScrX;
    int ScrY;
    int frame;
    int res1;
    int res2;
    VbSprite Sprite;
    private VbBall pLeft;
    private VbBall pRight;
    
    VbBall(final float x, final float y, final float z, final int frame, final int res1, final int res2) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.frame = frame;
        this.res1 = res1;
        this.res2 = res2;
    }
    
    void TreeAdd(final VbBall vbBall) {
        this.pLeft = null;
        this.pRight = null;
        if (this == vbBall) {
            return;
        }
        VbBall vbBall2 = vbBall;
        while (vbBall2 != null) {
            if (this.Xz <= vbBall2.Xz) {
                if (vbBall2.pLeft == null) {
                    vbBall2.pLeft = this;
                    return;
                }
                vbBall2 = vbBall2.pLeft;
            }
            else {
                if (vbBall2.pRight == null) {
                    vbBall2.pRight = this;
                    return;
                }
                vbBall2 = vbBall2.pRight;
            }
        }
    }
    
    void TreeWalkAndDraw(final Graphics graphics) {
        if (this.pRight != null) {
            this.pRight.TreeWalkAndDraw(graphics);
        }
        this.Sprite.Draw(graphics, this.ScrX, this.ScrY, this.frame);
        if (this.pLeft != null) {
            this.pLeft.TreeWalkAndDraw(graphics);
        }
    }
}
