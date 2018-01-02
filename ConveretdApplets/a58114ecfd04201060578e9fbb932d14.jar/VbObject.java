// 
// Decompiled by Procyon v0.5.30
// 

abstract class VbObject
{
    static final int ANIM_NONE = 0;
    static final int ANIM_SINUS = 1;
    static final int ANIM_SWOOSH = 2;
    static final int ANIM_WOBBLE = 3;
    private static final int VB_ZOFF = 520;
    private static final int VB_K = 512;
    protected int iNumBalls;
    protected VbBall[] Balls;
    protected int Animate;
    protected int value1;
    protected int value2;
    protected int value3;
    protected int value4;
    protected int value5;
    
    void SetSprite(final VbSprite sprite) {
        for (int i = 0; i < this.iNumBalls; ++i) {
            this.Balls[i].Sprite = sprite;
        }
    }
    
    void TransformAndAnimate(final VbMatrix vbMatrix) {
        final int n = 512;
        for (int i = 0; i < this.iNumBalls; ++i) {
            float z = 0.0f;
            switch (this.Animate) {
                case 1: {
                    this.Balls[i].z = this.value1 * SineTable.value[this.Balls[i].res1] + this.value1 * SineTable.value[this.Balls[i].res2];
                    final VbBall vbBall = this.Balls[i];
                    vbBall.res1 += this.value2;
                    final VbBall vbBall2 = this.Balls[i];
                    vbBall2.res2 += this.value3;
                    final VbBall vbBall3 = this.Balls[i];
                    vbBall3.res1 %= 4096;
                    final VbBall vbBall4 = this.Balls[i];
                    vbBall4.res2 %= 4096;
                    break;
                }
                case 2: {
                    final VbBall vbBall5 = this.Balls[i];
                    vbBall5.z += this.value1 * SineTable.value[this.Balls[i].res1];
                    final VbBall vbBall6 = this.Balls[i];
                    vbBall6.res1 += this.value2;
                    final VbBall vbBall7 = this.Balls[i];
                    vbBall7.res1 %= 4096;
                    break;
                }
                case 3: {
                    z = this.Balls[i].z;
                    final VbBall vbBall8 = this.Balls[i];
                    vbBall8.z += this.value1 * SineTable.value[this.Balls[i].res1] * (this.value3 * SineTable.value[this.Balls[i].res2]);
                    final VbBall vbBall9 = this.Balls[i];
                    vbBall9.res1 += this.value2;
                    final VbBall vbBall10 = this.Balls[i];
                    vbBall10.res1 %= 4096;
                    break;
                }
            }
            final float x = this.Balls[i].x;
            final float y = this.Balls[i].y;
            final float z2 = this.Balls[i].z;
            final float n2 = x * vbMatrix.El[0] + y * vbMatrix.El[1] + z2 * vbMatrix.El[2];
            final float n3 = x * vbMatrix.El[3] + y * vbMatrix.El[4] + z2 * vbMatrix.El[5];
            final float n4 = x * vbMatrix.El[6] + y * vbMatrix.El[7] + z2 * vbMatrix.El[8] + 520.0f;
            this.Balls[i].ScrX = (int)(n2 * n / n4);
            this.Balls[i].ScrY = (int)(n3 * n / n4);
            this.Balls[i].Xz = (int)n4;
            switch (this.Animate) {
                case 3: {
                    this.Balls[i].z = z;
                    break;
                }
            }
            this.Balls[i].TreeAdd(this.Balls[0]);
        }
    }
    
    void SetAnimation(final int animate, final int value1, final int value2, final int value3, final int value4, final int value5) {
        this.Animate = animate;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
    }
}
