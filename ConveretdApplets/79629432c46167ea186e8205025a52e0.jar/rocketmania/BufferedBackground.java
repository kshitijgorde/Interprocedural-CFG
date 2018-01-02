// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.awt.Color;
import sexy.gui.SexyColor;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyImage;

public class BufferedBackground
{
    private int mLinesPerFrame;
    private SexyImage mWorkingImage;
    private SexyImage mCompleteImage;
    private int mBrightness;
    private int mCurrentLine;
    private boolean mDone;
    private boolean mOutOfMemory;
    private double mStageDuration;
    
    void Render(final RocketManiaApplet rocketManiaApplet, final SexyGraphics sexyGraphics, final double n) {
        if (n <= this.mStageDuration) {
            this.Update(rocketManiaApplet, n);
        }
        if (!this.mOutOfMemory) {
            try {
                sexyGraphics.DrawImage(this.mCompleteImage, 480 - this.mCompleteImage.mWidth, 0);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.FreeUpMemory();
            }
        }
        if (this.mOutOfMemory) {
            this.Gradient(rocketManiaApplet, sexyGraphics, n);
        }
    }
    
    void SetStageDuration(final RocketManiaApplet rocketManiaApplet, final double n) {
        this.mStageDuration = n / 2.0;
        if (!this.mOutOfMemory) {
            try {
                this.mLinesPerFrame = 255 * this.mCompleteImage.GetHeight() / (50 * (int)this.mStageDuration) + 2;
                new SexyGraphics(this.mCompleteImage).DrawImage(rocketManiaApplet.mRes.mImages[0], 0, 0);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.FreeUpMemory();
            }
        }
    }
    
    private void Update(final RocketManiaApplet rocketManiaApplet, final double n) {
        if (!this.mOutOfMemory) {
            if (!this.mDone) {
                try {
                    final SexyGraphics sexyGraphics = new SexyGraphics(this.mWorkingImage);
                    final int mCurrentLine = this.mCurrentLine + this.mLinesPerFrame;
                    sexyGraphics.ClipRect(0, this.mCurrentLine, this.mWorkingImage.GetWidth(), (mCurrentLine > this.mWorkingImage.GetHeight()) ? (this.mWorkingImage.GetHeight() - this.mCurrentLine) : this.mLinesPerFrame);
                    sexyGraphics.DrawImage(rocketManiaApplet.mRes.mImages[0], 0, 0);
                    sexyGraphics.SetColorizeImages(true);
                    sexyGraphics.SetColor(new SexyColor(255, 255, 255, this.mBrightness));
                    sexyGraphics.DrawImage(rocketManiaApplet.mRes.mImages[1], 0, 0);
                    this.mCurrentLine = mCurrentLine;
                    if (this.mCurrentLine >= this.mWorkingImage.GetHeight()) {
                        this.mDone = true;
                        this.mCurrentLine = 0;
                        final SexyImage mWorkingImage = this.mWorkingImage;
                        this.mWorkingImage = this.mCompleteImage;
                        (this.mCompleteImage = mWorkingImage).SetImageMode(false, false);
                    }
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.FreeUpMemory();
                }
            }
            if (this.mDone) {
                final int n2 = (int)(255.0 * (1.0 - n / this.mStageDuration));
                final int n3 = (n2 > 255) ? 255 : n2;
                final int mBrightness = (n3 < 0) ? 0 : n3;
                if (mBrightness != this.mBrightness) {
                    this.mBrightness = mBrightness;
                    this.mDone = false;
                }
            }
        }
    }
    
    void Gradient(final RocketManiaApplet rocketManiaApplet, final SexyGraphics sexyGraphics, final double n) {
        final SexyImage sexyImage = rocketManiaApplet.mRes.mImages[0];
        final float max = Math.max(0.0f, Math.min((float)(1.0 - n / this.mStageDuration), 1.0f));
        final float n2 = 1.0f - max;
        final float[] array = new float[3];
        final float[] array2 = { 0.0f, 0.1254902f, 0.3764706f };
        final float[] array3 = { 0.42352942f, 0.3764706f, 0.42352942f };
        final float[] array4 = { 1.0f, 0.5019608f, 0.2509804f };
        final float[] array5 = new float[3];
        final float[] array6 = new float[3];
        final float[] array7 = new float[3];
        final float n3 = sexyImage.GetHeight();
        for (int i = sexyImage.GetHeight() - 1; i >= 0; --i) {
            final float n4 = i / n3;
            final float n5 = 1.0f - n4;
            int n6 = 0;
            do {
                array5[n6] = n5 * array[n6] + n4 * array2[n6];
                array6[n6] = n5 * array3[n6] + n4 * array4[n6];
                array7[n6] = n2 * array5[n6] + max * array6[n6];
            } while (++n6 < 3);
            sexyGraphics.SetColor(new Color(array7[0], array7[1], array7[2]));
            sexyGraphics.FillRect(480 - sexyImage.GetWidth(), i, sexyImage.GetWidth(), 1);
        }
    }
    
    BufferedBackground(final RocketManiaApplet rocketManiaApplet) {
        this.mOutOfMemory = false;
        try {
            final SexyImage sexyImage = rocketManiaApplet.mRes.mImages[0];
            (this.mWorkingImage = new SexyImage()).Create(sexyImage.GetWidth(), sexyImage.GetHeight());
            (this.mCompleteImage = new SexyImage()).Create(sexyImage.GetWidth(), sexyImage.GetHeight());
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.FreeUpMemory();
        }
        this.mBrightness = 0;
        this.mCurrentLine = 0;
        this.mDone = true;
        this.mStageDuration = 100.0;
        this.mLinesPerFrame = 32;
    }
    
    void FreeUpMemory() {
        this.mWorkingImage = null;
        this.mCompleteImage = null;
        this.mOutOfMemory = true;
    }
}
