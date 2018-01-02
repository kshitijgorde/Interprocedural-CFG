// 
// Decompiled by Procyon v0.5.30
// 

package nc;

public class ADSR
{
    public static final int skTouchHack = 1;
    float mAttack;
    float mDecay;
    float mSustain;
    float mRelease;
    float mDelay;
    
    public ADSR(final float mAttack, final float mDecay, final float mSustain, final float mRelease, final float mDelay) {
        this.mAttack = mAttack;
        this.mDecay = mDecay;
        this.mSustain = mSustain;
        this.mRelease = mRelease;
        this.mDelay = mDelay;
        Assert.assert(mAttack >= 0.0f);
        Assert.assert(mDecay >= 0.0f);
        Assert.assert(mSustain >= 0.0f);
        Assert.assert(mSustain <= 1.0f);
        Assert.assert(mRelease >= 0.0f);
        Assert.assert(mDelay >= 0.0f);
    }
    
    public ADSR(final float n, final float n2, final float n3, final float n4) {
        this(n, n2, n3, n4, 0.0f);
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    public float level(final float n, final float n2) {
        Assert.assert(n2 - this.mDelay - this.mAttack - this.mDecay >= 0.0f);
        if (n <= this.mDelay || n >= n2) {
            return 0.0f;
        }
        final float n3 = n - this.mDelay;
        if (n3 <= this.mAttack) {
            return n3 / this.mAttack;
        }
        final float n4 = n3 - this.mAttack;
        if (n4 <= this.mDecay) {
            return 1.0f - (1.0f - this.mSustain) * n4 / this.mDecay;
        }
        final float n5 = n4 - this.mDecay;
        final float n6 = n2 - this.mDelay - this.mAttack - this.mDecay;
        if (n5 <= n6) {
            return this.mSustain;
        }
        return this.mSustain * (1.0f - (n5 - n6) / this.mRelease);
    }
}
