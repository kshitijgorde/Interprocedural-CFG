// 
// Decompiled by Procyon v0.5.30
// 

package nc.particle;

import java.awt.Rectangle;
import sexy.gui.SexyColor;
import sexy.gui.SexyGraphics;

public class ParticleElement
{
    public static final int skTouchHack = 1;
    public static final int ANIMTYPE_NONE = 0;
    public static final int ANIMTYPE_ONCE = 1;
    public static final int ANIMTYPE_LOOP = 2;
    public static final int ANIMTYPE_BOUNCE = 3;
    float mX;
    float mY;
    float mVelX;
    float mVelY;
    float mAge;
    float mTimeStepScale;
    boolean mIsDead;
    
    public void Render(final SexyGraphics sexyGraphics, final ParticleElementData particleElementData, final SexyColor sexyColor) {
        final int n = (int)this.mX - particleElementData.mImageWidth / 2;
        final int n2 = (int)this.mY - particleElementData.mImage.GetHeight() / 2;
        if (particleElementData.mColorize) {
            if (this.mAge < particleElementData.mFadeStartTime) {
                sexyGraphics.SetColor(sexyColor);
            }
            else {
                sexyGraphics.SetColor(new SexyColor(sexyColor.mRed, sexyColor.mGreen, sexyColor.mBlue, 255 - Math.min((int)(255.0f * (this.mAge - particleElementData.mFadeStartTime) / (particleElementData.mLifeTime - particleElementData.mFadeStartTime)), 255)));
            }
        }
        if (particleElementData.mAnimType != 0) {
            int n3 = (int)(particleElementData.mFrameRate * this.mAge);
            switch (particleElementData.mAnimType) {
                case 1: {
                    if (n3 >= particleElementData.mNumFrames) {
                        n3 = particleElementData.mNumFrames - 1;
                        break;
                    }
                    break;
                }
                case 2: {
                    n3 %= particleElementData.mNumFrames;
                    break;
                }
                case 3: {
                    final int n4 = 2 * (particleElementData.mNumFrames - 1);
                    n3 %= n4;
                    if (n3 > n4 / 2) {
                        n3 = n4 - n3;
                        break;
                    }
                    break;
                }
            }
            final Rectangle rectangle = particleElementData.mFrameRects[n3];
            sexyGraphics.DrawImage(particleElementData.mImage, n + (rectangle.x - n3 * particleElementData.mImageWidth), n2 + rectangle.y, rectangle);
            return;
        }
        if (particleElementData.mRotationHertz != 0.0f) {
            sexyGraphics.DrawImageRotated(particleElementData.mImage, n, n2, 6.283185307179586 * particleElementData.mRotationHertz * this.mAge);
            return;
        }
        sexyGraphics.DrawImage(particleElementData.mImage, n, n2);
    }
    
    public void Update(final ParticleElementData particleElementData, final float n) {
        this.mAge += this.mTimeStepScale * n;
        if (this.mAge < particleElementData.mLifeTime) {
            this.mVelX += n * particleElementData.mAccX;
            this.mVelY += n * particleElementData.mAccY;
            if (particleElementData.mDrag > 0.0f) {
                final float n2 = 1.0f - n * particleElementData.mDrag;
                this.mVelX *= n2;
                this.mVelY *= n2;
            }
            this.mX += n * this.mVelX;
            this.mY += n * this.mVelY;
            return;
        }
        this.mIsDead = true;
    }
    
    public ParticleElement(final ParticleElementData particleElementData) {
        this.mX = 0.0f;
        this.mY = 0.0f;
        this.mVelX = 0.0f;
        this.mVelY = 0.0f;
        this.mAge = 0.0f;
        this.mIsDead = false;
        this.mTimeStepScale = 1.0f;
    }
    
    public void Setup(final ParticleElementData particleElementData, final float mx, final float my, final float mVelX, final float mVelY, final float mTimeStepScale) {
        this.mX = mx;
        this.mY = my;
        this.mVelX = mVelX;
        this.mVelY = mVelY;
        this.mAge = 0.0f;
        this.mIsDead = false;
        this.mTimeStepScale = mTimeStepScale;
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    public boolean isDead() {
        return this.mIsDead;
    }
}
