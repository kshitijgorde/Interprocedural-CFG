// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import sexy.gui.SexyColor;
import java.awt.Rectangle;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyImage;
import sexy.gui.Widget;

public class FloatingHead extends Widget
{
    static final int SEQUENCE_IDLE_1 = 0;
    static final int SEQUENCE_IDLE_2 = 1;
    static final int SEQUENCE_IDLE_3 = 2;
    static final int SEQUENCE_IDLE_4 = 3;
    static final int SEQUENCE_IDLE_5 = 4;
    static final int SEQUENCE_NUM_IDLES = 5;
    static final int SEQUENCE_GET_READY = 5;
    static final int SEQUENCE_LEVEL_UP = 6;
    static final int SEQUENCE_WELL_DONE = 7;
    static final int SEQUENCE_OUT_OF_TIME = 8;
    static final int SEQUENCE_NONE = 9;
    static final int SEQUENCE_NUM_SEQUENCES = 9;
    static final int[] skSequenceVoices;
    static final int FACE_MOUTH_A = 0;
    static final int FACE_MOUTH_I = 1;
    static final int FACE_MOUTH_BLA = 2;
    static final int FACE_MOUTH_O = 3;
    static final int FACE_MOUTH_SMILE = 4;
    static final int FACE_EYES_LOOK = 5;
    static final int FACE_EYES_BLINK = 6;
    static final int FACE_END_SEQUENCE = 7;
    static final int FACE_NUM_FRAMES = 7;
    static final int FACE = 0;
    static final int FRAMETIME = 1;
    static final int[][][] skSequences;
    int mX;
    int mY;
    int mSequence;
    int mFrame;
    int mFrameTime;
    int mFromFace;
    int mFace;
    int mFaceBlend;
    int mIdleTimer;
    int mHoverHeight;
    double mCycle1Time;
    double mCycle2Time;
    RocketManiaApplet mApplet;
    
    private void ResetIdleTimer() {
        if (this.mApplet.mBoard != null) {
            this.mIdleTimer = 100 + this.mApplet.mBoard.mRand.Next() % 250;
            return;
        }
        this.mIdleTimer = 200;
    }
    
    public void Update() {
        this.mCycle1Time += 0.006599999852478504;
        if (this.mCycle1Time > 6.283185307179586) {
            this.mCycle1Time -= 6.283185307179586;
        }
        this.mCycle2Time += 0.019999999552965164;
        if (this.mCycle2Time > 6.283185307179586) {
            this.mCycle2Time -= 6.283185307179586;
        }
        final int mHoverHeight = this.mHoverHeight;
        this.mHoverHeight = (int)(4.0 * Math.sin(this.mCycle1Time) + 2.0 * Math.sin(this.mCycle2Time));
        if (this.mHoverHeight != mHoverHeight) {
            final SexyImage sexyImage = this.mApplet.mRes.mImages[12];
            this.Resize(this.mX, this.mY + this.mHoverHeight, sexyImage.GetWidth(), sexyImage.GetHeight());
        }
        if (this.mSequence != 9) {
            ++this.mFrameTime;
            final int mFrameTime = this.mFrameTime - FloatingHead.skSequences[this.mSequence][this.mFrame][1];
            if (mFrameTime > 0) {
                ++this.mFrame;
                this.SetNewFace(FloatingHead.skSequences[this.mSequence][this.mFrame][0]);
                this.mFrameTime = mFrameTime;
                if (this.mFace == 7) {
                    this.mSequence = 9;
                    this.ResetIdleTimer();
                }
            }
        }
        else {
            --this.mIdleTimer;
            if (this.mIdleTimer == 0) {
                this.ResetIdleTimer();
                this.PlaySequence(this.mApplet.mBoard.mRand.Next() % 5);
            }
        }
        if (this.mFaceBlend < 255) {
            this.mFaceBlend = Math.min(this.mFaceBlend + 64, 255);
            this.MarkDirty();
            if (this.mApplet.mBoard != null) {
                this.mApplet.mBoard.MarkDirty();
            }
        }
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[12], 0, 0);
        if (this.mFace != 7 || this.mFaceBlend < 255) {
            if (this.mFaceBlend < 255) {
                sexyGraphics.SetColorizeImages(true);
            }
            final SexyImage sexyImage = this.mApplet.mRes.mImages[13];
            final int n = sexyImage.GetHeight() / 7;
            if (this.mFromFace != 7 && this.mFaceBlend < 255) {
                final Rectangle rectangle = new Rectangle(0, this.mFromFace * n, sexyImage.GetWidth(), n);
                sexyGraphics.SetColor(new SexyColor(255, 255, 255, 255 - this.mFaceBlend));
                sexyGraphics.DrawImage(sexyImage, 1, -1, rectangle);
            }
            if (this.mFace != 7) {
                final Rectangle rectangle2 = new Rectangle(0, this.mFace * n, sexyImage.GetWidth(), n);
                sexyGraphics.SetColor(new SexyColor(255, 255, 255, this.mFaceBlend));
                sexyGraphics.DrawImage(sexyImage, 1, -1, rectangle2);
            }
            sexyGraphics.SetColorizeImages(false);
        }
    }
    
    public FloatingHead(final RocketManiaApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mApplet = mApplet;
        this.mSequence = 9;
        this.mFromFace = 7;
        this.mFace = 7;
        this.mFaceBlend = 255;
        this.ResetIdleTimer();
        this.mHoverHeight = 0;
        this.mCycle1Time = 0.0;
        this.mCycle2Time = 0.0;
        this.mX = 5;
        this.mY = 195;
        final SexyImage sexyImage = this.mApplet.mRes.mImages[12];
        this.Resize(this.mX, this.mY, sexyImage.GetWidth(), sexyImage.GetHeight());
    }
    
    private void SetNewFace(final int mFace) {
        if (this.mFace != mFace) {
            this.mFromFace = this.mFace;
            this.mFace = mFace;
            this.mFaceBlend = 0;
        }
    }
    
    static {
        skSequenceVoices = new int[] { 0, 0, 0, 0, 0, 11, 21, 22, 23 };
        skSequences = new int[][][] { { { 6, 10 }, { 4, 30 }, { 6, 10 }, { 4, 100 }, { 7 } }, { { 6, 10 }, { 4, 30 }, { 7 } }, { { 5, 100 }, { 4, 30 }, { 7 } }, { { 6, 8 }, { 5, 20 }, { 6, 8 }, { 4, 100 }, { 7 } }, { { 2, 30 }, { 4, 30 }, { 6, 10 }, { 7 } }, { { 2, 5 }, { 0, 4 }, { 2, 3 }, { 3, 3 }, { 2, 2 }, { 0, 6 }, { 4, 5 }, { 1, 7 }, { 7 } }, { { 2, 4 }, { 3, 4 }, { 4, 3 }, { 1, 15 }, { 4, 3 }, { 0, 4 }, { 2, 21 }, { 7 } }, { { 3, 5 }, { 0, 6 }, { 2, 14 }, { 3, 9 }, { 2, 26 }, { 4, 40 }, { 6, 10 }, { 4, 50 }, { 7 } }, { { 0, 7 }, { 3, 9 }, { 2, 3 }, { 3, 5 }, { 2, 3 }, { 4, 4 }, { 2, 4 }, { 1, 16 }, { 4, 10 }, { 7 } } };
    }
    
    void PlaySequence(final int mSequence) {
        if (this.mSequence == 9 || this.mSequence < 5) {
            this.mSequence = mSequence;
            this.mFrame = 0;
            this.mFrameTime = 0;
            this.SetNewFace(FloatingHead.skSequences[this.mSequence][this.mFrame][0]);
            if (FloatingHead.skSequenceVoices[this.mSequence] != 0) {
                this.mApplet.PlaySound(FloatingHead.skSequenceVoices[this.mSequence]);
            }
        }
    }
}
