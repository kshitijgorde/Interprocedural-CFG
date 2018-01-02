// 
// Decompiled by Procyon v0.5.30
// 

package nc.particle;

import java.awt.Rectangle;
import sexy.gui.SexyImage;

public class ParticleElementData
{
    public static final boolean DONT_MAKE_CLIP_RECTS = false;
    public static final boolean MAKE_CLIP_RECTS = true;
    public static final boolean DONT_COLORIZE = false;
    public static final boolean COLORIZE = true;
    SexyImage mImage;
    int mAnimType;
    int mNumFrames;
    int mImageWidth;
    float mFrameRate;
    float mRotationHertz;
    boolean mColorize;
    float mFadeStartTime;
    int mDrawMode;
    float mAccX;
    float mAccY;
    float mDrag;
    float mLifeTime;
    public Rectangle[] mFrameRects;
    
    public ParticleElementData(final SexyImage mImage, final int mAnimType, final int mNumFrames, final boolean b, final float mFrameRate, final float mRotationHertz, final boolean mColorize, final float mFadeStartTime, final int mDrawMode, final float mAccX, final float mAccY, final float mDrag, final float mLifeTime) {
        this.mImage = mImage;
        this.mAnimType = mAnimType;
        this.mNumFrames = mNumFrames;
        this.mImageWidth = this.mImage.GetWidth();
        this.mFrameRate = mFrameRate;
        if (mAnimType != 0) {
            this.mImageWidth /= this.mNumFrames;
            if (this.mFrameRate == 0.0f) {
                this.mFrameRate = this.mNumFrames / mLifeTime;
            }
            this.mFrameRects = new Rectangle[this.mNumFrames];
            final int mImageWidth = this.mImageWidth;
            final int getWidth = this.mImage.GetWidth();
            final int getHeight = this.mImage.GetHeight();
            if (b) {
                final int[] getBits = this.mImage.GetBits();
                final int n = getBits[0];
                for (int i = 0; i < this.mNumFrames; ++i) {
                    int min = mImageWidth;
                    int min2 = getHeight;
                    int max = 0;
                    int max2 = 0;
                    for (int j = 0; j < getHeight; ++j) {
                        final int n2 = getWidth * j + i * mImageWidth;
                        for (int k = 0; k < mImageWidth; ++k) {
                            if (getBits[n2 + k] != n) {
                                min2 = Math.min(min2, j);
                                max2 = Math.max(max2, j);
                                break;
                            }
                        }
                    }
                    for (int l = 0; l < mImageWidth; ++l) {
                        final int n3 = i * mImageWidth + l;
                        for (int n4 = min2; n4 < max2; ++n4) {
                            if (getBits[n3 + getWidth * n4] != n) {
                                min = Math.min(min, l);
                                max = Math.max(max, l);
                                break;
                            }
                        }
                    }
                    this.mFrameRects[i] = new Rectangle(mImageWidth * i + min, min2, max - min + 1, max2 - min2 + 1);
                }
            }
            else {
                for (int n5 = 0; n5 < this.mNumFrames; ++n5) {
                    this.mFrameRects[n5] = new Rectangle(mImageWidth * n5, 0, mImageWidth, getHeight);
                }
            }
        }
        this.mRotationHertz = mRotationHertz;
        this.mColorize = mColorize;
        this.mFadeStartTime = mFadeStartTime;
        this.mDrawMode = mDrawMode;
        this.mAccX = mAccX;
        this.mAccY = mAccY;
        this.mDrag = mDrag;
        this.mLifeTime = mLifeTime;
    }
}
