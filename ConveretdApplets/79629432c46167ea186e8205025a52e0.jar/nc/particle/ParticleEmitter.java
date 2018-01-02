// 
// Decompiled by Procyon v0.5.30
// 

package nc.particle;

import java.util.Enumeration;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyColor;
import java.util.Vector;

public class ParticleEmitter
{
    public static final int skTouchHack = 1;
    public static final int DIRECTION_AHEAD = 0;
    public static final int DIRECTION_BEHIND = 1;
    public static final int DIRECTION_AWAY = 2;
    public static final int DIRECTION_ABSOLUTE = 3;
    ParticleEmitterData mEmitterData;
    ParticleElementData mElementData;
    Vector mFreeElements;
    Vector mUsedElements;
    float mX;
    float mY;
    float mVelX;
    float mVelY;
    float mAge;
    float mEmitRemainder;
    SexyColor mColor;
    float mElementTimeStepScale;
    
    public void Render(final SexyGraphics sexyGraphics) {
        sexyGraphics.SetDrawMode(this.mElementData.mDrawMode);
        sexyGraphics.SetColorizeImages(this.mElementData.mColorize);
        final Enumeration<ParticleElement> elements = this.mUsedElements.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().Render(sexyGraphics, this.mElementData, this.mColor);
        }
        sexyGraphics.SetColorizeImages(false);
        sexyGraphics.SetDrawMode(0);
    }
    
    public void Update(final float n) {
        this.mAge += n;
        if (this.mAge < this.mEmitterData.mLifeTime) {
            this.mVelX += n * this.mEmitterData.mAccX;
            this.mVelY += n * this.mEmitterData.mAccY;
            if (this.mEmitterData.mDrag > 0.0f && this.mVelX * this.mVelX + this.mVelY * this.mVelY > 0.0f) {
                this.mVelX -= n * this.mEmitterData.mDrag * this.mVelX;
                this.mVelY -= n * this.mEmitterData.mDrag * this.mVelY;
            }
            this.mX += n * this.mVelX;
            this.mY += n * this.mVelY;
            this.mEmitRemainder += n * this.mEmitterData.mEmitRate * this.mEmitterData.mEmitADSR.level(this.mAge, this.mEmitterData.mLifeTime);
            for (boolean b = false; !b && this.mEmitRemainder > 0.0f && this.mFreeElements.size() > 0; b = true) {
                if (this.mEmitRemainder >= 1.0f || this.mEmitRemainder > (float)Math.random()) {
                    --this.mEmitRemainder;
                    final ParticleElement particleElement = this.mFreeElements.lastElement();
                    this.mFreeElements.removeElementAt(this.mFreeElements.size() - 1);
                    this.mUsedElements.addElement(particleElement);
                    final float n2 = this.mEmitterData.mEmitSpeed + ((float)Math.random() - 0.5f) * this.mEmitterData.mEmitSpeedRange;
                    final float n3 = ((float)Math.random() - 0.5f) * this.mEmitterData.mEmitXRange;
                    final float n4 = ((float)Math.random() - 0.5f) * this.mEmitterData.mEmitYRange;
                    final float n5 = ((float)Math.random() - 0.5f) * this.mEmitterData.mEmitDirectionXRange;
                    final float n6 = ((float)Math.random() - 0.5f) * this.mEmitterData.mEmitDirectionYRange;
                    float n7 = 0.0f;
                    float n8 = 0.0f;
                    switch (this.mEmitterData.mEmitDirection) {
                        case 3: {
                            n7 = this.mEmitterData.mEmitDirectionX;
                            n8 = this.mEmitterData.mEmitDirectionY;
                            break;
                        }
                        case 0: {
                            n7 = this.mVelX;
                            n8 = this.mVelY;
                            break;
                        }
                        case 1: {
                            n7 = -this.mVelX;
                            n8 = -this.mVelY;
                            break;
                        }
                        case 2: {
                            n7 = n3;
                            n8 = n4;
                            break;
                        }
                    }
                    float n9 = n7 + n5;
                    float n10 = n8 + n6;
                    if (n9 != 0.0f || n10 != 0.0f) {
                        final float n11 = n2 / (float)Math.sqrt(n9 * n9 + n10 * n10);
                        n9 *= n11;
                        n10 *= n11;
                    }
                    particleElement.Setup(this.mElementData, this.mX + n3, this.mY + n4, n9, n10, this.mElementTimeStepScale);
                }
                if (this.mEmitRemainder < 1.0f) {}
            }
        }
        for (int i = this.mUsedElements.size() - 1; i >= 0; --i) {
            final ParticleElement particleElement2 = this.mUsedElements.elementAt(i);
            particleElement2.Update(this.mElementData, n);
            if (particleElement2.isDead()) {
                this.mUsedElements.removeElementAt(i);
                this.mFreeElements.addElement(particleElement2);
            }
        }
    }
    
    public ParticleEmitter(final ParticleEmitterData mEmitterData, final ParticleElementData mElementData, final SexyColor mColor, final float mx, final float my, final float mVelX, final float mVelY) {
        this.mEmitterData = mEmitterData;
        this.mElementData = mElementData;
        this.mColor = mColor;
        this.mFreeElements = new Vector(this.mEmitterData.mMaxElements);
        this.mUsedElements = new Vector(this.mEmitterData.mMaxElements);
        this.mX = mx;
        this.mY = my;
        this.mVelX = mVelX;
        this.mVelY = mVelY;
        this.mAge = 0.0f;
        this.mEmitRemainder = 0.0f;
        this.mElementData.mImageWidth = this.mElementData.mImage.GetWidth() / this.mElementData.mNumFrames;
        for (int i = 0; i < this.mFreeElements.capacity(); ++i) {
            this.mFreeElements.addElement(new ParticleElement(mElementData));
        }
        this.mElementTimeStepScale = 1.0f + this.mEmitterData.mElementTimeStepScaleRange * ((float)Math.random() - 0.5f);
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    public boolean isDead() {
        return this.mAge > this.mEmitterData.mLifeTime && this.mUsedElements.size() == 0;
    }
    
    public void MoveTo(final float mx, final float my) {
        this.mX = mx;
        this.mY = my;
    }
}
