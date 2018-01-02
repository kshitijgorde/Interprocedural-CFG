// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.image.PixelGrabber;
import java.awt.Image;
import sexy.res.ResLoader;

public class SexyImage
{
    public int mWidth;
    public int mHeight;
    public int[] mBits;
    public int[] mColorTable;
    public int[] mNativeAlphaColorTable;
    public byte[] mColorIndices;
    public int[] mNativeAlphaData;
    public byte[] mRLAlphaData;
    public boolean mBitsChanged;
    public boolean mForcedMode;
    public boolean mHasTrans;
    public boolean mHasAlpha;
    int[] mPFPointIndices;
    double[][] mPFActiveEdgeList;
    public ResLoader mResLoader;
    public Image mAWTColorImage;
    public PixelGrabber mAWTColorImageGrabber;
    public Image mAWTAlphaImage;
    public PixelGrabber mAWTAlphaImageGrabber;
    public boolean mHasFailed;
    
    public void CommitBits() {
        if (this.mBitsChanged) {
            if (!this.mForcedMode) {
                this.mHasTrans = false;
                this.mHasAlpha = false;
                if (this.mColorTable != null) {
                    int n = 0;
                    do {
                        final int n2 = this.mColorTable[n] >> 24 & 0xFF;
                        if (n2 == 0) {
                            this.mHasTrans = true;
                        }
                        else {
                            if (n2 == 255) {
                                continue;
                            }
                            this.mHasAlpha = true;
                        }
                    } while (++n < 256);
                }
                else {
                    for (int i = 0; i < this.mWidth * this.mHeight; ++i) {
                        final int n3 = this.mBits[i] >> 24 & 0xFF;
                        if (n3 == 0) {
                            this.mHasTrans = true;
                        }
                        else if (n3 != 255) {
                            this.mHasAlpha = true;
                        }
                    }
                }
            }
            this.mBitsChanged = false;
        }
    }
    
    public int[] GetBits() {
        if (this.mBits == null && this.mColorTable != null) {
            this.mBits = new int[this.mColorIndices.length];
            for (int i = 0; i < this.mColorIndices.length; ++i) {
                this.mBits[i] = this.mColorTable[this.mColorIndices[i] & 0xFF];
            }
            this.mColorIndices = null;
            this.mColorTable = null;
            this.mNativeAlphaColorTable = null;
        }
        return this.mBits;
    }
    
    public void Create(final ResLoader mResLoader, final Image mawtColorImage, final Image mawtAlphaImage) {
        this.mResLoader = mResLoader;
        this.mAWTColorImage = mawtColorImage;
        this.mAWTAlphaImage = mawtAlphaImage;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mBits = null;
    }
    
    public void Create(final int mWidth, final int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mBits = new int[this.mWidth * this.mHeight];
        this.mHasTrans = true;
        this.mHasAlpha = false;
        this.mBitsChanged = false;
    }
    
    public SexyImage(final int[] array, final int mWidth, final int mHeight) {
        this.mBits = array.clone();
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mBitsChanged = true;
        this.CommitBits();
    }
    
    public SexyImage() {
    }
    
    public void SetImageMode(final boolean mHasTrans, final boolean mHasAlpha) {
        this.mForcedMode = true;
        this.mHasTrans = mHasTrans;
        this.mHasAlpha = mHasAlpha;
    }
    
    public int GetHeight() {
        this.WaitForImage();
        return this.mHeight;
    }
    
    public int[] GetNativeAlphaData() {
        if (this.mNativeAlphaData == null) {
            final int[] getBits = this.GetBits();
            this.mNativeAlphaData = new int[this.mWidth * this.mHeight];
            for (int i = 0; i < this.mWidth * this.mHeight; ++i) {
                final int n = getBits[i];
                final int n2 = n >> 24 & 0xFF;
                this.mNativeAlphaData[i] = ((n & 0xFF000000) | ((n & 0xFF) * n2 >> 8 & 0xFF) | ((n & 0xFF00) * n2 >> 8 & 0xFF00) | ((n & 0xFF0000) * n2 >> 8 & 0xFF0000));
            }
        }
        return this.mNativeAlphaData;
    }
    
    public void WaitForImage() {
        while (!this.IsReady() && !this.HasFailed() && !this.mResLoader.mShutdown) {
            try {
                this.mResLoader.wait();
            }
            catch (Exception ex) {}
        }
    }
    
    public byte[] GetRLAlphaData() {
        if (this.mRLAlphaData == null) {
            this.mRLAlphaData = new byte[this.mWidth * this.mHeight];
            if (this.mColorTable != null) {
                final byte[] mrlAlphaData = this.mRLAlphaData;
                final byte[] mColorIndices = this.mColorIndices;
                final int[] mColorTable = this.mColorTable;
                int n = 0;
                int n2 = 0;
                for (int i = 0; i < this.mHeight; ++i) {
                    int j = 1;
                    int n3 = 1;
                    final int n4 = mColorIndices[n2++] >> 24 & 0xFF;
                    int n5 = (n4 == 0) ? 0 : ((n4 == 255) ? 1 : 2);
                    while (j < this.mWidth) {
                        ++j;
                        final int n6 = mColorTable[mColorIndices[n2++] & 0xFF] >> 24 & 0xFF;
                        final int n7 = (n6 == 0) ? 0 : ((n6 == 255) ? 1 : 2);
                        if (n7 != n5 || j == this.mWidth) {
                            if (n7 == n5) {
                                ++n3;
                            }
                            for (int k = n3; k > 0; --k) {
                                if (k >= 255) {
                                    mrlAlphaData[n++] = -1;
                                }
                                else {
                                    mrlAlphaData[n++] = (byte)k;
                                }
                            }
                            if (j == this.mWidth && n7 != n5) {
                                mrlAlphaData[n++] = 1;
                            }
                            n5 = n7;
                            n3 = 1;
                        }
                        else {
                            ++n3;
                        }
                    }
                }
            }
            else {
                final byte[] mrlAlphaData2 = this.mRLAlphaData;
                final int[] getBits = this.GetBits();
                int n8 = 0;
                int n9 = 0;
                for (int l = 0; l < this.mHeight; ++l) {
                    int n10 = 1;
                    int n11 = 1;
                    final int n12 = getBits[n9++] >> 24 & 0xFF;
                    int n13 = (n12 == 0) ? 0 : ((n12 == 255) ? 1 : 2);
                    while (n10 < this.mWidth) {
                        ++n10;
                        final int n14 = getBits[n9++] >> 24 & 0xFF;
                        final int n15 = (n14 == 0) ? 0 : ((n14 == 255) ? 1 : 2);
                        if (n15 != n13 || n10 == this.mWidth) {
                            if (n15 == n13) {
                                ++n11;
                            }
                            for (int n16 = n11; n16 > 0; --n16) {
                                if (n16 >= 255) {
                                    mrlAlphaData2[n8++] = -1;
                                }
                                else {
                                    mrlAlphaData2[n8++] = (byte)n16;
                                }
                            }
                            if (n10 == this.mWidth && n15 != n13) {
                                mrlAlphaData2[n8++] = 1;
                            }
                            n13 = n15;
                            n11 = 1;
                        }
                        else {
                            ++n11;
                        }
                    }
                }
            }
        }
        return this.mRLAlphaData;
    }
    
    public boolean HasFailed() {
        return this.mHasFailed;
    }
    
    public void BitsChanged() {
        this.mBitsChanged = true;
        this.mRLAlphaData = null;
        this.mNativeAlphaData = null;
        this.mColorIndices = null;
        this.mColorTable = null;
        this.mNativeAlphaColorTable = null;
    }
    
    public int GetWidth() {
        this.WaitForImage();
        return this.mWidth;
    }
    
    public int[] GetNativeAlphaColorTable() {
        if (this.mNativeAlphaColorTable == null) {
            this.mNativeAlphaColorTable = new int[256];
            int n = 0;
            do {
                final int n2 = this.mColorTable[n];
                final int n3 = n2 >> 24 & 0xFF;
                this.mNativeAlphaColorTable[n] = ((n2 & 0xFF000000) | ((n2 & 0xFF) * n3 >> 8 & 0xFF) | ((n2 & 0xFF00) * n3 >> 8 & 0xFF00) | ((n2 & 0xFF0000) * n3 >> 8 & 0xFF0000));
            } while (++n < 256);
        }
        return this.mNativeAlphaColorTable;
    }
    
    public boolean Palletize() {
        if (this.mColorTable != null) {
            return true;
        }
        this.GetBits();
        if (this.mBits == null) {
            return false;
        }
        this.mColorIndices = new byte[this.mWidth * this.mHeight];
        this.mColorTable = new int[256];
        if (!Quantize.Quantize8Bit(this.mBits, this.mWidth, this.mHeight, this.mColorIndices, this.mColorTable)) {
            this.mColorIndices = null;
            this.mColorTable = null;
            return false;
        }
        this.mBits = null;
        return true;
    }
    
    public boolean IsReady() {
        return this.mBits != null || this.mColorTable != null;
    }
}
