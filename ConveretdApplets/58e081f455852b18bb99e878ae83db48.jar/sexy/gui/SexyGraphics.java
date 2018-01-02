// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class SexyGraphics
{
    public static final int DRAWMODE_NORMAL = 0;
    public static final int DRAWMODE_ADDITIVE = 1;
    static int[] ADDITIVE_CLIPPED_TABLE;
    public SexyImage mImage;
    Rectangle mClipRect;
    boolean mColorizeImages;
    SexyColor mColor;
    SexyFont mFont;
    int mTransX;
    int mTransY;
    int mDrawMode;
    static final int MAX_TEMP_SPANS = 8192;
    Point[] mPFPoints;
    int[] mPFPointIndices;
    double[][] mPFActiveEdgeList;
    int mPFNumActiveEdges;
    
    public void ClipRect(final int n, final int n2, final int n3, final int n4) {
        this.mClipRect = new Rectangle(this.mTransX + n, this.mTransY + n2, n3, n4).intersection(this.mClipRect);
    }
    
    public void DrawLine(final int n, final int n2, final int n3, final int n4) {
        double n5 = n + this.mTransX;
        double n6 = n2 + this.mTransY;
        double n7 = n3 + this.mTransX;
        double n8 = n4 + this.mTransY;
        if (n5 < n7) {
            if (n5 < this.mClipRect.x) {
                if (n7 < this.mClipRect.x) {
                    return;
                }
                n6 += (this.mClipRect.x - n5) * ((n8 - n6) / (n7 - n5));
                n5 = 0.0;
            }
            if (n7 >= this.mClipRect.x + this.mClipRect.width) {
                if (n5 >= this.mClipRect.x + this.mClipRect.width) {
                    return;
                }
                n8 += (this.mClipRect.x + this.mClipRect.width - 1 - n7) * ((n8 - n6) / (n7 - n5));
                n7 = this.mClipRect.x + this.mClipRect.width - 1;
            }
        }
        else {
            if (n7 < this.mClipRect.x) {
                if (n5 < this.mClipRect.x) {
                    return;
                }
                n6 += (this.mClipRect.x - n7) * ((n8 - n6) / (n5 - n7));
                n7 = 0.0;
            }
            if (n5 >= this.mClipRect.x + this.mClipRect.width) {
                if (n7 >= this.mClipRect.x + this.mClipRect.width) {
                    return;
                }
                n6 += (n5 - (this.mClipRect.x + this.mClipRect.width - 1)) * ((n8 - n6) / (n5 - n7));
                n5 = this.mClipRect.x + this.mClipRect.width - 1;
            }
        }
        if (n6 < n8) {
            if (n6 < this.mClipRect.y) {
                if (n8 < this.mClipRect.y) {
                    return;
                }
                n5 += (this.mClipRect.y - n6) * ((n7 - n5) / (n8 - n6));
                n6 = 0.0;
            }
            if (n8 >= this.mClipRect.y + this.mClipRect.height) {
                if (n6 >= this.mClipRect.y + this.mClipRect.height) {
                    return;
                }
                n7 += (this.mClipRect.y + this.mClipRect.height - 1 - n8) * ((n7 - n5) / (n8 - n6));
                n8 = this.mClipRect.y + this.mClipRect.height - 1;
            }
        }
        else {
            if (n8 < this.mClipRect.y) {
                if (n6 < this.mClipRect.y) {
                    return;
                }
                n5 += (this.mClipRect.y - n8) * ((n7 - n5) / (n6 - n8));
                if (n5 < this.mClipRect.x || n5 > this.mClipRect.x + this.mClipRect.height - 1) {
                    return;
                }
                n8 = 0.0;
            }
            if (n6 >= this.mClipRect.y + this.mClipRect.height) {
                if (n8 >= this.mClipRect.y + this.mClipRect.height) {
                    return;
                }
                n5 += (n6 - (this.mClipRect.y + this.mClipRect.height - 1)) * ((n7 - n5) / (n6 - n8));
                if (n7 < this.mClipRect.x || n7 > this.mClipRect.x + this.mClipRect.height - 1) {
                    return;
                }
                n6 = this.mClipRect.y + this.mClipRect.height - 1;
            }
        }
        final int n9 = this.mColor.mAlpha << 24 | this.mColor.mRed * this.mColor.mAlpha >> 8 << 16 | this.mColor.mGreen * this.mColor.mAlpha >> 8 << 8 | this.mColor.mBlue * this.mColor.mAlpha >> 8;
        final int n10 = 256 - this.mColor.mAlpha;
        final int[] getBits = this.mImage.GetBits();
        final int getWidth = this.mImage.GetWidth();
        double n11 = n8 - n6;
        double n12 = n7 - n5;
        int n13 = 1;
        int n14 = getWidth;
        if (Math.abs(n11) < Math.abs(n12)) {
            if (n12 < 0.0) {
                n12 = -n12;
                n11 = -n11;
                n6 = n8;
                final double n15 = n7;
                n7 = n5;
                n5 = n15;
            }
            if (n11 < 0.0) {
                n11 = -n11;
                n13 = -1;
                n14 = -n14;
            }
            int n16 = (int)n6 * getWidth + (int)n5;
            final int n17 = getBits[n16];
            getBits[n16] = n9 + ((n17 & 0xFF0000) * n10 >> 8 & 0xFF0000) + ((n17 & 0xFF00) * n10 >> 8 & 0xFF00) + ((n17 & 0xFF) * n10 >> 8 & 0xFF);
            ++n16;
            int n18 = (int)n6;
            int n19 = (int)n5 + 1;
            final int n20 = (int)(2.0 * n11 - n12);
            final int n21 = (int)(2.0 * (n11 - n12));
            final int n22 = (int)(2.0 * n11);
            int n23 = (int)(n20 + n22 * (n5 - (int)n5));
            while (n19 <= n7) {
                if (n23 > 0) {
                    n23 += n21;
                    n18 += n13;
                    n16 += n14;
                }
                else {
                    n23 += n22;
                }
                final int n24 = getBits[n16];
                getBits[n16] = n9 + ((n24 & 0xFF0000) * n10 >> 8 & 0xFF0000) + ((n24 & 0xFF00) * n10 >> 8 & 0xFF00) + ((n24 & 0xFF) * n10 >> 8 & 0xFF);
                ++n16;
                ++n19;
            }
        }
        else {
            if (n11 < 0.0) {
                n12 = -n12;
                n11 = -n11;
                final double n25 = n8;
                n8 = n6;
                n6 = n25;
                n5 = n7;
            }
            if (n12 < 0.0) {
                n12 = -n12;
                n13 = -1;
            }
            final int n26 = (int)n6 * getWidth + (int)n5;
            final int n27 = getBits[n26];
            getBits[n26] = n9 + ((n27 & 0xFF0000) * n10 >> 8 & 0xFF0000) + ((n27 & 0xFF00) * n10 >> 8 & 0xFF00) + ((n27 & 0xFF) * n10 >> 8 & 0xFF);
            int n28 = n26 + getWidth;
            int n29 = (int)n5;
            int n30 = (int)n6 + 1;
            final int n31 = (int)(2.0 * n12 - n11);
            final int n32 = (int)(2.0 * (n12 - n11));
            final int n33 = (int)(2.0 * n12);
            int n34 = (int)(n31 + n33 * (n5 - (int)n5));
            while (n30 <= n8) {
                if (n34 > 0) {
                    n34 += n32;
                    n29 += n13;
                    n28 += n13;
                }
                else {
                    n34 += n33;
                }
                final int n35 = getBits[n28];
                getBits[n28] = n9 + ((n35 & 0xFF0000) * n10 >> 8 & 0xFF0000) + ((n35 & 0xFF00) * n10 >> 8 & 0xFF00) + ((n35 & 0xFF) * n10 >> 8 & 0xFF);
                n28 += getWidth;
                ++n30;
            }
        }
        this.mImage.BitsChanged();
    }
    
    public void DrawImagePalletized(final SexyImage sexyImage, int n, int n2, final Rectangle rectangle) {
        n += this.mTransX;
        n2 += this.mTransY;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.mClipRect);
        final int n3 = rectangle.x + intersection.x - n;
        final int n4 = rectangle.y + intersection.y - n2;
        sexyImage.CommitBits();
        final int[] getBits = this.mImage.GetBits();
        final int[] mColorTable = sexyImage.mColorTable;
        final byte[] mColorIndices = sexyImage.mColorIndices;
        final int[] getNativeAlphaColorTable = sexyImage.GetNativeAlphaColorTable();
        if (!this.mColorizeImages || (this.mColor.mRed == 255 && this.mColor.mGreen == 255 && this.mColor.mBlue == 255 && this.mColor.mAlpha == 255)) {
            if (!sexyImage.mHasAlpha && !sexyImage.mHasTrans) {
                for (int i = 0; i < intersection.height; ++i) {
                    int n5 = n3 + (n4 + i) * sexyImage.mWidth;
                    int n6 = intersection.x + (intersection.y + i) * this.mImage.mWidth;
                    for (int j = 0; j < intersection.width; ++j) {
                        getBits[n6++] = mColorTable[mColorIndices[n5++] & 0xFF];
                    }
                }
            }
            else if (this.mImage.mHasAlpha || this.mImage.mHasTrans) {
                for (int k = 0; k < intersection.height; ++k) {
                    int n7 = n3 + (n4 + k) * sexyImage.mWidth;
                    int n8 = intersection.x + (intersection.y + k) * this.mImage.mWidth;
                    for (int l = 0; l < intersection.width; ++l) {
                        final int n9 = getBits[n8];
                        final int n10 = mColorTable[mColorIndices[n7++] & 0xFF];
                        final int n11 = n10 >> 24 & 0xFF;
                        if (n11 != 0) {
                            final int n12 = n9 >> 24 & 0xFF;
                            final int n13 = n12 + (255 - n12) * n11 / 255;
                            final int n14 = 255 * n11 / n13;
                            final int n15 = 256 - n14;
                            getBits[n8++] = (n13 << 24 | (((n9 & 0xFF) * n15 >> 8) + ((n10 & 0xFF) * n14 >> 8) & 0xFF) | (((n9 & 0xFF00) * n15 >> 8) + ((n10 & 0xFF00) * n14 >> 8) & 0xFF00) | (((n9 & 0xFF0000) * n15 >> 8) + ((n10 & 0xFF0000) * n14 >> 8) & 0xFF0000));
                        }
                        else {
                            ++n8;
                        }
                    }
                }
            }
            else {
                final byte[] getRLAlphaData = sexyImage.GetRLAlphaData();
                for (int n16 = 0; n16 < intersection.height; ++n16) {
                    int n17 = n3 + (n4 + n16) * sexyImage.mWidth;
                    int n18 = intersection.x + (intersection.y + n16) * this.mImage.mWidth;
                    int n20;
                    for (int width = intersection.width; width > 0; width -= n20) {
                        final int n19 = getNativeAlphaColorTable[mColorIndices[n17] & 0xFF];
                        n20 = (getRLAlphaData[n17] & 0xFF);
                        if (n20 > width) {
                            n20 = width;
                        }
                        final int n21 = 256 - (n19 >> 24 & 0xFF);
                        if (n21 == 1) {
                            for (int n22 = 0; n22 < n20; ++n22) {
                                getBits[n18++] = mColorTable[mColorIndices[n17++] & 0xFF];
                            }
                        }
                        else if (n21 == 256) {
                            n18 += n20;
                            n17 += n20;
                        }
                        else {
                            for (int n23 = 0; n23 < n20; ++n23) {
                                final int n24 = getNativeAlphaColorTable[mColorIndices[n17++] & 0xFF];
                                final int n25 = 256 - (n24 >> 24 & 0xFF);
                                final int n26 = getBits[n18];
                                getBits[n18++] = (0xFF000000 | n24 + (((n26 & 0xFF) * n25 >> 8 & 0xFF) | ((n26 & 0xFF00) * n25 >> 8 & 0xFF00) | ((n26 & 0xFF0000) * n25 >> 8 & 0xFF0000)));
                            }
                        }
                    }
                }
            }
        }
        else {
            final int mAlpha = this.mColor.mAlpha;
            final int mRed = this.mColor.mRed;
            final int mGreen = this.mColor.mGreen;
            final int mBlue = this.mColor.mBlue;
            if (this.mImage.mHasAlpha || this.mImage.mHasTrans) {
                for (int n27 = 0; n27 < intersection.height; ++n27) {
                    int n28 = n3 + (n4 + n27) * sexyImage.mWidth;
                    int n29 = intersection.x + (intersection.y + n27) * this.mImage.mWidth;
                    for (int n30 = 0; n30 < intersection.width; ++n30) {
                        final int n31 = getBits[n29];
                        final int n32 = mColorTable[mColorIndices[n28++] & 0xFF];
                        final int n33 = (n32 >> 24 & 0xFF) * mAlpha / 255 & 0xFF;
                        if (n33 != 0) {
                            final int n34 = n31 >> 24 & 0xFF;
                            final int n35 = n34 + (255 - n34) * n33 / 255;
                            final int n36 = 255 * n33 / n35;
                            final int n37 = 256 - n36;
                            getBits[n29++] = (n35 << 24 | (((n31 & 0xFF) * n37 >> 8) + ((n32 & 0xFF) * n36 * mBlue >> 16) & 0xFF) | (((n31 & 0xFF00) * n37 >> 8) + ((n32 & 0xFF00) * n36 * mGreen >> 16) & 0xFF00) | (((n31 & 0xFF0000) * n37 >> 8) + (((n32 & 0xFF0000) * n36 >> 8 & 0xFF0000) * mRed >> 8) & 0xFF0000));
                        }
                        else {
                            ++n29;
                        }
                    }
                }
            }
            else {
                for (int n38 = 0; n38 < intersection.height; ++n38) {
                    int n39 = n3 + (n4 + n38) * sexyImage.mWidth;
                    int n40 = intersection.x + (intersection.y + n38) * this.mImage.mWidth;
                    for (int n41 = 0; n41 < intersection.width; ++n41) {
                        final int n42 = getBits[n40];
                        final int n43 = mColorTable[mColorIndices[n39++] & 0xFF];
                        final int n44 = (n43 >> 24 & 0xFF) * mAlpha / 255 & 0xFF;
                        final int n45 = 256 - n44;
                        getBits[n40++] = (0xFF000000 | (((n42 & 0xFF) * n45 >> 8) + ((n43 & 0xFF) * n44 * mBlue >> 16) & 0xFF) | (((n42 & 0xFF00) * n45 >> 8) + ((n43 & 0xFF00) * n44 * mGreen >> 16) & 0xFF00) | (((n42 & 0xFF0000) * n45 >> 8) + (((n43 & 0xFF0000) * n44 >> 8 & 0xFF0000) * mRed >> 8) & 0xFF0000));
                    }
                }
            }
        }
        this.mImage.BitsChanged();
    }
    
    public SexyFont GetFont() {
        return this.mFont;
    }
    
    public int GetDrawMode() {
        return this.mDrawMode;
    }
    
    void PFInsert(final int n, final int n2) {
        final int n3 = (n < this.mPFPoints.length - 1) ? (n + 1) : 0;
        Point point;
        Point point2;
        if (this.mPFPoints[n].y < this.mPFPoints[n3].y) {
            point = this.mPFPoints[n];
            point2 = this.mPFPoints[n3];
        }
        else {
            point = this.mPFPoints[n3];
            point2 = this.mPFPoints[n];
        }
        this.mPFActiveEdgeList[this.mPFNumActiveEdges][0] = (this.mPFActiveEdgeList[this.mPFNumActiveEdges][1] = (point2.x - point.x) / (point2.y - point.y)) * (n2 + 0.5 - point.y - this.mTransY) + point.x + this.mTransX;
        this.mPFActiveEdgeList[this.mPFNumActiveEdges][2] = n;
        ++this.mPFNumActiveEdges;
    }
    
    public void DrawString(final String s, final int n, final int n2) {
        final boolean mColorizeImages = this.mColorizeImages;
        this.mColorizeImages = true;
        int n3 = n;
        final int n4 = n2 - this.mFont.GetAscent();
        for (int i = 0; i < s.length(); ++i) {
            final char c = (char)(s.charAt(i) & '\u00ff');
            final int n5 = this.mFont.mCharStarts[c];
            final int n6 = this.mFont.mCharWidths[c];
            if (n6 > 0 && n5 >= 0) {
                this.DrawImage(this.mFont.mImage, n3, n4, new Rectangle(n5, 0, n6, this.mFont.mHeight));
            }
            if (n6 > 0) {
                n3 += n6;
            }
        }
        this.mColorizeImages = mColorizeImages;
    }
    
    public void SetColorizeImages(final boolean mColorizeImages) {
        this.mColorizeImages = mColorizeImages;
    }
    
    public void SetClipRect(final Rectangle mClipRect) {
        this.mClipRect = mClipRect;
    }
    
    void PFDelete(final int n) {
        int i;
        for (i = 0; i < this.mPFNumActiveEdges && this.mPFActiveEdgeList[i][2] != n; ++i) {}
        if (i >= this.mPFNumActiveEdges) {
            return;
        }
        final double[] array = this.mPFActiveEdgeList[i];
        while (i < this.mPFNumActiveEdges - 1) {
            this.mPFActiveEdgeList[i] = this.mPFActiveEdgeList[i + 1];
            ++i;
        }
        this.mPFActiveEdgeList[this.mPFNumActiveEdges - 1] = array;
        --this.mPFNumActiveEdges;
    }
    
    public void SetColor(final Color color) {
        this.mColor = new SexyColor(color.getRed(), color.getGreen(), color.getBlue(), 255);
    }
    
    public void SetColor(final SexyColor mColor) {
        this.mColor = mColor;
    }
    
    public SexyColor GetColor() {
        return this.mColor;
    }
    
    public boolean GetColorizeImages() {
        return this.mColorizeImages;
    }
    
    public void DrawImage(final SexyImage sexyImage, int n, int n2, final Rectangle rectangle) {
        if (this.mDrawMode == 1) {
            if (sexyImage.mColorTable != null) {
                this.AdditiveDrawImagePalletized(sexyImage, n, n2, rectangle);
                return;
            }
            this.AdditiveDrawImage(sexyImage, n, n2, rectangle);
        }
        else {
            if (sexyImage.mColorTable != null) {
                this.DrawImagePalletized(sexyImage, n, n2, rectangle);
                return;
            }
            n += this.mTransX;
            n2 += this.mTransY;
            final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.mClipRect);
            final int n3 = rectangle.x + intersection.x - n;
            final int n4 = rectangle.y + intersection.y - n2;
            sexyImage.CommitBits();
            final int[] getBits = this.mImage.GetBits();
            final int[] getBits2 = sexyImage.GetBits();
            final int[] getNativeAlphaData = sexyImage.GetNativeAlphaData();
            if (!this.mColorizeImages || (this.mColor.mRed == 255 && this.mColor.mGreen == 255 && this.mColor.mBlue == 255 && this.mColor.mAlpha == 255)) {
                if (!sexyImage.mHasAlpha && !sexyImage.mHasTrans) {
                    for (int i = 0; i < intersection.height; ++i) {
                        int n5 = n3 + (n4 + i) * sexyImage.mWidth;
                        int n6 = intersection.x + (intersection.y + i) * this.mImage.mWidth;
                        for (int j = 0; j < intersection.width; ++j) {
                            getBits[n6++] = getBits2[n5++];
                        }
                    }
                }
                else if (this.mImage.mHasAlpha || this.mImage.mHasTrans) {
                    for (int k = 0; k < intersection.height; ++k) {
                        int n7 = n3 + (n4 + k) * sexyImage.mWidth;
                        int n8 = intersection.x + (intersection.y + k) * this.mImage.mWidth;
                        for (int l = 0; l < intersection.width; ++l) {
                            final int n9 = getBits[n8];
                            final int n10 = getBits2[n7++];
                            final int n11 = n10 >> 24 & 0xFF;
                            if (n11 != 0) {
                                final int n12 = n9 >> 24 & 0xFF;
                                final int n13 = n12 + (255 - n12) * n11 / 255;
                                final int n14 = 255 * n11 / n13;
                                final int n15 = 256 - n14;
                                getBits[n8++] = (n13 << 24 | (((n9 & 0xFF) * n15 >> 8) + ((n10 & 0xFF) * n14 >> 8) & 0xFF) | (((n9 & 0xFF00) * n15 >> 8) + ((n10 & 0xFF00) * n14 >> 8) & 0xFF00) | (((n9 & 0xFF0000) * n15 >> 8) + ((n10 & 0xFF0000) * n14 >> 8) & 0xFF0000));
                            }
                            else {
                                ++n8;
                            }
                        }
                    }
                }
                else {
                    final byte[] getRLAlphaData = sexyImage.GetRLAlphaData();
                    for (int n16 = 0; n16 < intersection.height; ++n16) {
                        int n17 = n3 + (n4 + n16) * sexyImage.mWidth;
                        int n18 = intersection.x + (intersection.y + n16) * this.mImage.mWidth;
                        int n20;
                        for (int width = intersection.width; width > 0; width -= n20) {
                            final int n19 = getNativeAlphaData[n17];
                            n20 = (getRLAlphaData[n17] & 0xFF);
                            if (n20 > width) {
                                n20 = width;
                            }
                            final int n21 = 256 - (n19 >> 24 & 0xFF);
                            if (n21 == 1) {
                                for (int n22 = 0; n22 < n20; ++n22) {
                                    getBits[n18++] = getBits2[n17++];
                                }
                            }
                            else if (n21 == 256) {
                                n18 += n20;
                                n17 += n20;
                            }
                            else {
                                for (int n23 = 0; n23 < n20; ++n23) {
                                    final int n24 = getNativeAlphaData[n17++];
                                    final int n25 = 256 - (n24 >> 24 & 0xFF);
                                    final int n26 = getBits[n18];
                                    getBits[n18++] = (0xFF000000 | n24 + (((n26 & 0xFF) * n25 >> 8 & 0xFF) | ((n26 & 0xFF00) * n25 >> 8 & 0xFF00) | ((n26 & 0xFF0000) * n25 >> 8 & 0xFF0000)));
                                }
                            }
                        }
                    }
                }
            }
            else {
                final int mAlpha = this.mColor.mAlpha;
                final int mRed = this.mColor.mRed;
                final int mGreen = this.mColor.mGreen;
                final int mBlue = this.mColor.mBlue;
                if (this.mImage.mHasAlpha || this.mImage.mHasTrans) {
                    for (int n27 = 0; n27 < intersection.height; ++n27) {
                        int n28 = n3 + (n4 + n27) * sexyImage.mWidth;
                        int n29 = intersection.x + (intersection.y + n27) * this.mImage.mWidth;
                        for (int n30 = 0; n30 < intersection.width; ++n30) {
                            final int n31 = getBits[n29];
                            final int n32 = getBits2[n28++];
                            final int n33 = (n32 >> 24 & 0xFF) * mAlpha / 255 & 0xFF;
                            if (n33 != 0) {
                                final int n34 = n31 >> 24 & 0xFF;
                                final int n35 = n34 + (255 - n34) * n33 / 255;
                                final int n36 = 255 * n33 / n35;
                                final int n37 = 256 - n36;
                                getBits[n29++] = (n35 << 24 | (((n31 & 0xFF) * n37 >> 8) + ((n32 & 0xFF) * n36 * mBlue >> 16) & 0xFF) | (((n31 & 0xFF00) * n37 >> 8) + ((n32 & 0xFF00) * n36 * mGreen >> 16) & 0xFF00) | (((n31 & 0xFF0000) * n37 >> 8) + (((n32 & 0xFF0000) * n36 >> 8 & 0xFF0000) * mRed >> 8) & 0xFF0000));
                            }
                            else {
                                ++n29;
                            }
                        }
                    }
                }
                else {
                    for (int n38 = 0; n38 < intersection.height; ++n38) {
                        int n39 = n3 + (n4 + n38) * sexyImage.mWidth;
                        int n40 = intersection.x + (intersection.y + n38) * this.mImage.mWidth;
                        for (int n41 = 0; n41 < intersection.width; ++n41) {
                            final int n42 = getBits[n40];
                            final int n43 = getBits2[n39++];
                            final int n44 = (n43 >> 24 & 0xFF) * mAlpha / 255 & 0xFF;
                            final int n45 = 256 - n44;
                            getBits[n40++] = (0xFF000000 | (((n42 & 0xFF) * n45 >> 8) + ((n43 & 0xFF) * n44 * mBlue >> 16) & 0xFF) | (((n42 & 0xFF00) * n45 >> 8) + ((n43 & 0xFF00) * n44 * mGreen >> 16) & 0xFF00) | (((n42 & 0xFF0000) * n45 >> 8) + (((n43 & 0xFF0000) * n44 >> 8 & 0xFF0000) * mRed >> 8) & 0xFF0000));
                        }
                    }
                }
            }
            this.mImage.BitsChanged();
        }
    }
    
    public void DrawImage(final SexyImage sexyImage, final int n, final int n2) {
        this.DrawImage(sexyImage, n, n2, new Rectangle(0, 0, sexyImage.mWidth, sexyImage.mHeight));
    }
    
    public void DrawImage(final SexyImage sexyImage, final Rectangle rectangle, final Rectangle rectangle2) {
        final int n = rectangle.x + this.mTransX;
        final int n2 = rectangle.y + this.mTransY;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.mClipRect);
        if (rectangle2.width == rectangle.width) {
            final int n3 = rectangle2.x + intersection.x - n;
            final int[] getNativeAlphaData = sexyImage.GetNativeAlphaData();
            final int[] getBits = this.mImage.GetBits();
            final byte[] getRLAlphaData = sexyImage.GetRLAlphaData();
            final double n4 = rectangle2.height / rectangle.height;
            double n5 = rectangle2.y + (intersection.y - n2) * n4;
            for (int i = 0; i < intersection.height; ++i) {
                int n6 = n3 + (int)n5 * sexyImage.mWidth;
                int n7 = intersection.x + (intersection.y + i) * this.mImage.mWidth;
                int n9;
                for (int j = intersection.width; j > 0; j -= n9) {
                    final int n8 = getNativeAlphaData[n6];
                    n9 = (getRLAlphaData[n6] & 0xFF);
                    if (n9 > j) {
                        n9 = j;
                    }
                    final int n10 = 256 - (n8 >> 24 & 0xFF);
                    if (n10 == 1) {
                        for (int k = 0; k < n9; ++k) {
                            getBits[n7++] = getNativeAlphaData[n6++];
                        }
                    }
                    else if (n10 == 256) {
                        n7 += n9;
                        n6 += n9;
                    }
                    else {
                        for (int l = 0; l < n9; ++l) {
                            final int n11 = getNativeAlphaData[n6++];
                            final int n12 = 256 - (n11 >> 24 & 0xFF);
                            final int n13 = getBits[n7];
                            getBits[n7++] = (0xFF000000 | n11 + (((n13 & 0xFF) * n12 >> 8 & 0xFF) | ((n13 & 0xFF00) * n12 >> 8 & 0xFF00) | ((n13 & 0xFF0000) * n12 >> 8 & 0xFF0000)));
                        }
                    }
                }
                n5 += n4;
            }
        }
        else {
            final double n14 = rectangle2.width / rectangle.width;
            final double n15 = rectangle2.height / rectangle.height;
            final double n16 = rectangle2.x + (intersection.x - n) * n14;
            double n17 = rectangle2.y + (intersection.y - n2) * n15;
            final int[] getBits2 = this.mImage.GetBits();
            final int[] getBits3 = sexyImage.GetBits();
            sexyImage.GetNativeAlphaData();
            for (int n18 = 0; n18 < intersection.height; ++n18) {
                double n19 = n16 + (int)n17 * sexyImage.mWidth;
                int n20 = intersection.x + (intersection.y + n18) * this.mImage.mWidth;
                for (int n21 = 0; n21 < intersection.width; ++n21) {
                    final int n22 = getBits2[n20];
                    final int n23 = getBits3[(int)n19];
                    final int n24 = n23 >> 24 & 0xFF;
                    if (n24 != 0) {
                        final int n25 = n22 >> 24 & 0xFF;
                        final int n26 = n25 + (255 - n25) * n24 / 255;
                        final int n27 = 255 * n24 / n26;
                        final int n28 = 256 - n27;
                        getBits2[n20++] = (n26 << 24 | (((n22 & 0xFF) * n28 >> 8) + ((n23 & 0xFF) * n27 >> 8) & 0xFF) | (((n22 & 0xFF00) * n28 >> 8) + ((n23 & 0xFF00) * n27 >> 8) & 0xFF00) | (((n22 & 0xFF0000) * n28 >> 8) + ((n23 & 0xFF0000) * n27 >> 8) & 0xFF0000));
                    }
                    else {
                        ++n20;
                    }
                    n19 += n14;
                }
                n17 += n15;
            }
        }
        this.mImage.BitsChanged();
    }
    
    public void SetFont(final SexyFont mFont) {
        this.mFont = mFont;
    }
    
    public void AdditiveDrawImagePalletized(final SexyImage sexyImage, int n, int n2, final Rectangle rectangle) {
        n += this.mTransX;
        n2 += this.mTransY;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.mClipRect);
        final int n3 = rectangle.x + intersection.x - n;
        final int n4 = rectangle.y + intersection.y - n2;
        sexyImage.CommitBits();
        final int[] getAdditiveClippedTable = this.GetAdditiveClippedTable();
        final int[] getBits = this.mImage.GetBits();
        final int[] mColorTable = sexyImage.mColorTable;
        final byte[] mColorIndices = sexyImage.mColorIndices;
        if (!this.mColorizeImages || (this.mColor.mRed == 255 && this.mColor.mGreen == 255 && this.mColor.mBlue == 255 && this.mColor.mAlpha == 255)) {
            for (int i = 0; i < intersection.height; ++i) {
                int n5 = n3 + (n4 + i) * sexyImage.mWidth;
                int n6 = intersection.x + (intersection.y + i) * this.mImage.mWidth;
                for (int j = 0; j < intersection.width; ++j) {
                    final int n7 = getBits[n6];
                    final int n8 = mColorTable[mColorIndices[n5++] & 0xFF];
                    this.mImage.mBits[n6++] = ((n7 & 0xFF000000) | getAdditiveClippedTable[(n7 & 0xFF) + (n8 & 0xFF)] | getAdditiveClippedTable[(n7 & 0xFF00) + (n8 & 0xFF00) >> 8] << 8 | getAdditiveClippedTable[(n7 & 0xFF0000) + (n8 & 0xFF0000) >> 16] << 16);
                }
            }
        }
        else {
            final int mRed = this.mColor.mRed;
            final int mGreen = this.mColor.mGreen;
            final int mBlue = this.mColor.mBlue;
            for (int k = 0; k < intersection.height; ++k) {
                int n9 = n3 + (n4 + k) * sexyImage.mWidth;
                int n10 = intersection.x + (intersection.y + k) * this.mImage.mWidth;
                for (int l = 0; l < intersection.width; ++l) {
                    final int n11 = getBits[n10];
                    final int n12 = mColorTable[mColorIndices[n9++] & 0xFF];
                    this.mImage.mBits[n10++] = ((n11 & 0xFF000000) | getAdditiveClippedTable[(n11 & 0xFF) + ((n12 & 0xFF) * mRed >> 8)] | getAdditiveClippedTable[((n11 & 0xFF00) >> 8) + ((n12 & 0xFF00) * mGreen >> 16)] << 8 | getAdditiveClippedTable[((n11 & 0xFF0000) >> 16) + ((n12 & 0xFF0000) * mBlue >> 24 & 0xFF)] << 16);
                }
            }
        }
        this.mImage.BitsChanged();
    }
    
    public void SetDrawMode(final int mDrawMode) {
        this.mDrawMode = mDrawMode;
    }
    
    void QuickSortEdges(final double[][] array, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        if (n2 > n) {
            final double[] array2 = array[(n + n2) / 2];
            while (i <= n3) {
                while (i < n2) {
                    if (array[i][0] >= array2[0]) {
                        break;
                    }
                    ++i;
                }
                while (n3 > n && array[n3][0] > array2[0]) {
                    --n3;
                }
                if (i <= n3) {
                    final double[] array3 = array[i];
                    array[i] = array[n3];
                    array[n3] = array3;
                    ++i;
                    --n3;
                }
            }
            if (n < n3) {
                this.QuickSortEdges(array, n, n3);
            }
            if (i < n2) {
                this.QuickSortEdges(array, i, n2);
            }
        }
    }
    
    public void FillRect(final int n, final int n2, final int n3, final int n4) {
        final Rectangle intersection = new Rectangle(this.mTransX + n, this.mTransY + n2, n3, n4).intersection(this.mClipRect);
        final int toInt = this.mColor.ToInt();
        if (this.mDrawMode == 0) {
            int n5 = toInt >> 24 & 0xFF;
            if (n5 == 255) {
                for (int i = 0; i < intersection.height; ++i) {
                    int n6 = intersection.x + (intersection.y + i) * this.mImage.mWidth;
                    for (int j = 0; j < intersection.width; ++j) {
                        this.mImage.mBits[n6++] = toInt;
                    }
                }
            }
            else if (n5 != 0) {
                for (int k = 0; k < intersection.height; ++k) {
                    int n7 = intersection.x + (intersection.y + k) * this.mImage.mWidth;
                    for (int l = 0; l < intersection.width; ++l) {
                        final int n8 = this.mImage.mBits[n7];
                        final int n9 = n8 >> 24 & 0xFF;
                        final int n10 = n9 + (255 - n9) * n5 / 255;
                        n5 = 255 * n5 / n10;
                        final int n11 = 256 - n5;
                        this.mImage.mBits[n7++] = (n10 << 24 | (((n8 & 0xFF) * n11 >> 8) + ((toInt & 0xFF) * n5 >> 8) & 0xFF) | (((n8 & 0xFF00) * n11 >> 8) + ((toInt & 0xFF00) * n5 >> 8) & 0xFF00) | (((n8 & 0xFF0000) * n11 >> 8) + ((toInt & 0xFF0000) * n5 >> 8) & 0xFF0000));
                    }
                }
            }
        }
        else {
            final int[] getAdditiveClippedTable = this.GetAdditiveClippedTable();
            for (int n12 = 0; n12 < intersection.height; ++n12) {
                int n13 = intersection.x + (intersection.y + n12) * this.mImage.mWidth;
                for (int n14 = 0; n14 < intersection.width; ++n14) {
                    final int n15 = this.mImage.mBits[n13];
                    this.mImage.mBits[n13++] = ((n15 & 0xFF000000) | getAdditiveClippedTable[(n15 & 0xFF) + (toInt & 0xFF)] | getAdditiveClippedTable[(n15 & 0xFF00) + (toInt & 0xFF00) >> 8] << 8 | getAdditiveClippedTable[(n15 & 0xFF0000) + (toInt & 0xFF0000) >> 16] << 16);
                }
            }
        }
        this.mImage.BitsChanged();
    }
    
    public SexyGraphics(final SexyImage mImage) {
        this.mColor = new SexyColor(0, 0, 0);
        this.mImage = mImage;
        this.mClipRect = new Rectangle(0, 0, this.mImage.mWidth, this.mImage.mHeight);
    }
    
    public SexyGraphics(final SexyGraphics sexyGraphics) {
        this.mColor = new SexyColor(0, 0, 0);
        this.mImage = sexyGraphics.mImage;
        this.mClipRect = sexyGraphics.mClipRect;
        this.mColorizeImages = sexyGraphics.mColorizeImages;
        this.mColor = sexyGraphics.mColor;
        this.mFont = sexyGraphics.mFont;
        this.mTransX = sexyGraphics.mTransX;
        this.mTransY = sexyGraphics.mTransY;
        this.mDrawMode = sexyGraphics.mDrawMode;
    }
    
    public SexyGraphics create() {
        return new SexyGraphics(this);
    }
    
    public void Translate(final int n, final int n2) {
        this.mTransX += n;
        this.mTransY += n2;
    }
    
    public void DrawImageRotated(final SexyImage sexyImage, int n, int n2, final double n3) {
        n += this.mTransX;
        n2 += this.mTransY;
        final double abs = Math.abs(Math.cos(n3));
        final double abs2 = Math.abs(Math.sin(n3));
        final int n4 = (int)((sexyImage.mWidth * abs + sexyImage.mHeight * abs2 - sexyImage.mWidth + 1.0) / 2.0);
        final int n5 = (int)((sexyImage.mWidth * abs2 + sexyImage.mHeight * abs - sexyImage.mHeight + 1.0) / 2.0);
        final int n6 = sexyImage.GetWidth() / 2;
        final int n7 = sexyImage.GetHeight() / 2;
        final Rectangle intersection = new Rectangle(n - n4, n2 - n5, sexyImage.GetWidth() + n4 * 2, sexyImage.GetHeight() + n5 * 2).intersection(this.mClipRect);
        final Rectangle rectangle = new Rectangle(intersection.x - n, intersection.y - n2, intersection.width, intersection.height);
        final double cos = Math.cos(n3);
        final double sin = Math.sin(n3);
        final int n8 = (int)(cos * 65536.0);
        final int n9 = (int)(sin * 65536.0);
        final int mWidth = sexyImage.mWidth;
        final int mHeight = sexyImage.mHeight;
        final int[] getBits = this.mImage.GetBits();
        final int[] getBits2 = sexyImage.GetBits();
        final int mWidth2 = this.mImage.mWidth;
        int n10 = intersection.y * mWidth2 + intersection.x;
        if (!this.mColorizeImages || (this.mColor.mRed == 255 && this.mColor.mGreen == 255 && this.mColor.mBlue == 255 && this.mColor.mAlpha == 255)) {
            for (int i = 0; i < rectangle.height; ++i) {
                final int n11 = rectangle.x - n6;
                final int n12 = rectangle.x + i - n7;
                final int n13 = (int)((cos * n11 - sin * n12 + n6 + 0.5) * 65536.0);
                final int n14 = (int)((cos * n12 + sin * n11 + n7 + 0.5) * 65536.0);
                final int n15 = n10;
                int n16 = 0;
                int n17 = rectangle.width - 1;
                if (n8 < -1) {
                    final int n18 = ((mWidth << 16) - 163839 - n13) / n8;
                    if (n18 >= n16) {
                        n16 = n18;
                    }
                    final int n19 = (32767 - n13) / n8;
                    if (n19 < n17) {
                        n17 = n19;
                    }
                }
                else if (n8 >= 1) {
                    final int n20 = (65536 - n13) / n8;
                    if (n20 > n16) {
                        n16 = n20;
                    }
                    final int n21 = ((mWidth << 16) - 98303 - n13) / n8;
                    if (n21 < n17) {
                        n17 = n21;
                    }
                }
                else if (n13 < 0 || n13 >= mWidth - 1 << 16) {
                    n17 = n16 - 1;
                }
                if (n9 <= -1) {
                    final int n22 = ((mHeight << 16) - 163839 - n14) / n9;
                    if (n22 > n16) {
                        n16 = n22;
                    }
                    final int n23 = (0 - n14) / n9;
                    if (n23 < n17) {
                        n17 = n23;
                    }
                }
                else if (n9 >= 1) {
                    final int n24 = (65536 - n14) / n9;
                    if (n24 > n16) {
                        n16 = n24;
                    }
                    final int n25 = ((mHeight << 16) - 98303 - n14) / n9;
                    if (n25 < n17) {
                        n17 = n25;
                    }
                }
                else if (n14 < 0 || n14 >= mHeight - 1 << 16) {
                    n17 = n16 - 1;
                }
                int n26 = n13 + n16 * n8;
                int n27 = n14 + n16 * n9;
                int n28 = n15 + n16;
                final int n29 = mWidth;
                for (int j = n16; j <= n17; ++j) {
                    final int n30 = n26 >> 16;
                    final int n31 = n27 >> 16;
                    final int n32 = getBits[n28];
                    final int n33 = n31 * n29 + n30;
                    final int n34 = n26 & 0xFFFF;
                    final int n35 = n27 & 0xFFFF;
                    final int n36 = getBits2[n33];
                    final int n37 = getBits2[n33 + 1];
                    final int n38 = getBits2[n33 + sexyImage.mWidth];
                    final int n39 = getBits2[n33 + 1 + sexyImage.mWidth];
                    final int n40 = (n36 >> 24 & 0xFF) * ((65536 - n34) * (65536 - n35) >> 16) >> 16 & 0xFF;
                    final int n41 = (n37 >> 24 & 0xFF) * (n34 * (65536 - n35) >> 16) >> 16 & 0xFF;
                    final int n42 = (n38 >> 24 & 0xFF) * ((65536 - n34) * n35 >> 16) >> 16 & 0xFF;
                    final int n43 = (n39 >> 24 & 0xFF) * (n34 * n35 >> 16) >> 16 & 0xFF;
                    final int n44 = 256 - (n40 + n41 + n42 + n43);
                    getBits[n28++] = (((n32 & 0xFF) * n44 >> 8 & 0xFF) + ((n36 & 0xFF) * n40 + (n37 & 0xFF) * n41 + (n38 & 0xFF) * n42 + (n39 & 0xFF) * n43 >> 8 & 0xFF) | ((n32 & 0xFF00) * n44 >> 8 & 0xFF00) + ((n36 & 0xFF00) * n40 + (n37 & 0xFF00) * n41 + (n38 & 0xFF00) * n42 + (n39 & 0xFF00) * n43 >> 8 & 0xFF00) | ((n32 & 0xFF0000) * n44 >> 8 & 0xFF0000) + ((n36 & 0xFF0000) * n40 + (n37 & 0xFF0000) * n41 + (n38 & 0xFF0000) * n42 + (n39 & 0xFF0000) * n43 >> 8 & 0xFF0000));
                    n26 += n8;
                    n27 += n9;
                }
                n10 += mWidth2;
            }
        }
        this.mImage.BitsChanged();
    }
    
    public void AdditiveDrawImage(final SexyImage sexyImage, int n, int n2, final Rectangle rectangle) {
        n += this.mTransX;
        n2 += this.mTransY;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.mClipRect);
        final int n3 = rectangle.x + intersection.x - n;
        final int n4 = rectangle.y + intersection.y - n2;
        sexyImage.CommitBits();
        final int[] getAdditiveClippedTable = this.GetAdditiveClippedTable();
        final int[] getBits = this.mImage.GetBits();
        final int[] getBits2 = sexyImage.GetBits();
        if (!this.mColorizeImages || (this.mColor.mRed == 255 && this.mColor.mGreen == 255 && this.mColor.mBlue == 255 && this.mColor.mAlpha == 255)) {
            for (int i = 0; i < intersection.height; ++i) {
                int n5 = n3 + (n4 + i) * sexyImage.mWidth;
                int n6 = intersection.x + (intersection.y + i) * this.mImage.mWidth;
                for (int j = 0; j < intersection.width; ++j) {
                    final int n7 = getBits[n6];
                    final int n8 = getBits2[n5++];
                    this.mImage.mBits[n6++] = ((n7 & 0xFF000000) | getAdditiveClippedTable[(n7 & 0xFF) + (n8 & 0xFF)] | getAdditiveClippedTable[(n7 & 0xFF00) + (n8 & 0xFF00) >> 8] << 8 | getAdditiveClippedTable[(n7 & 0xFF0000) + (n8 & 0xFF0000) >> 16] << 16);
                }
            }
        }
        else {
            final int mRed = this.mColor.mRed;
            final int mGreen = this.mColor.mGreen;
            final int mBlue = this.mColor.mBlue;
            for (int k = 0; k < intersection.height; ++k) {
                int n9 = n3 + (n4 + k) * sexyImage.mWidth;
                int n10 = intersection.x + (intersection.y + k) * this.mImage.mWidth;
                for (int l = 0; l < intersection.width; ++l) {
                    final int n11 = getBits[n10];
                    final int n12 = getBits2[n9++];
                    this.mImage.mBits[n10++] = ((n11 & 0xFF000000) | getAdditiveClippedTable[(n11 & 0xFF) + ((n12 & 0xFF) * mRed >> 8)] | getAdditiveClippedTable[((n11 & 0xFF00) >> 8) + ((n12 & 0xFF00) * mGreen >> 16)] << 8 | getAdditiveClippedTable[((n11 & 0xFF0000) >> 16) + ((n12 & 0xFF0000) * mBlue >> 24 & 0xFF)] << 16);
                }
            }
        }
        this.mImage.BitsChanged();
    }
    
    public void PolyFill(final Point[] mpfPoints) {
        final int x = this.mClipRect.x;
        final int n = this.mClipRect.x + this.mClipRect.width - 1;
        final int y = this.mClipRect.y;
        final int n2 = this.mClipRect.y + this.mClipRect.height - 1;
        if (mpfPoints.length == 0) {
            return;
        }
        this.mPFPoints = mpfPoints;
        if (this.mImage.mPFPointIndices == null || this.mImage.mPFPointIndices.length < this.mPFPoints.length) {
            this.mImage.mPFPointIndices = new int[this.mPFPoints.length];
        }
        this.mPFPointIndices = this.mImage.mPFPointIndices;
        for (int i = 0; i < this.mPFPoints.length; ++i) {
            this.mPFPointIndices[i] = i;
        }
        if (this.mImage.mPFActiveEdgeList == null || this.mImage.mPFActiveEdgeList.length < this.mPFPoints.length) {
            this.mImage.mPFActiveEdgeList = new double[this.mPFPoints.length][3];
        }
        this.mPFActiveEdgeList = this.mImage.mPFActiveEdgeList;
        this.QuickSortPoints(0, this.mPFPoints.length - 1);
        this.mPFNumActiveEdges = 0;
        int n3 = 0;
        final int n4 = (int)Math.max(y, Math.ceil(this.mPFPoints[this.mPFPointIndices[0]].y - 0.5 + this.mTransY));
        for (int n5 = (int)Math.min(n2, Math.floor(this.mPFPoints[this.mPFPointIndices[this.mPFPoints.length - 1]].y - 0.5 + this.mTransY)), j = n4; j <= n5; ++j) {
            while (n3 < this.mPFPoints.length && this.mPFPoints[this.mPFPointIndices[n3]].y + this.mTransY <= j + 0.5) {
                final int n6 = this.mPFPointIndices[n3];
                final int n7 = (n6 > 0) ? (n6 - 1) : (this.mPFPoints.length - 1);
                if (this.mPFPoints[n7].y + this.mTransY <= j - 0.5) {
                    this.PFDelete(n7);
                }
                else if (this.mPFPoints[n7].y + this.mTransY > j + 0.5) {
                    this.PFInsert(n7, j);
                }
                final int n8 = (n6 < this.mPFPoints.length - 1) ? (n6 + 1) : 0;
                if (this.mPFPoints[n8].y + this.mTransY <= j - 0.5) {
                    this.PFDelete(n6);
                }
                else if (this.mPFPoints[n8].y + this.mTransY > j + 0.5) {
                    this.PFInsert(n6, j);
                }
                ++n3;
            }
            this.QuickSortEdges(this.mPFActiveEdgeList, 0, this.mPFNumActiveEdges - 1);
            for (int k = 0; k < this.mPFNumActiveEdges; k += 2) {
                int n9 = (int)(this.mPFActiveEdgeList[k][0] - 0.5 + 0.9999);
                if (n9 < x) {
                    n9 = x;
                }
                int n10 = (int)(this.mPFActiveEdgeList[k + 1][0] - 0.5);
                if (n10 > n) {
                    n10 = n;
                }
                if (n9 <= n10) {
                    this.FillRect(n9 - this.mTransX, j - this.mTransY, n10 - n9 + 1, 1);
                }
                final double[] array = this.mPFActiveEdgeList[k];
                final int n11 = 0;
                array[n11] += this.mPFActiveEdgeList[k][1];
                final double[] array2 = this.mPFActiveEdgeList[k + 1];
                final int n12 = 0;
                array2[n12] += this.mPFActiveEdgeList[k + 1][1];
            }
        }
        this.mImage.BitsChanged();
    }
    
    void QuickSortPoints(final int n, final int n2) {
        int i = n;
        int n3 = n2;
        if (n2 > n) {
            final Point point = this.mPFPoints[this.mPFPointIndices[(n + n2) / 2]];
            while (i <= n3) {
                while (i < n2) {
                    if (this.mPFPoints[this.mPFPointIndices[i]].y >= point.y) {
                        break;
                    }
                    ++i;
                }
                while (n3 > n && this.mPFPoints[this.mPFPointIndices[n3]].y > point.y) {
                    --n3;
                }
                if (i <= n3) {
                    final int n4 = this.mPFPointIndices[i];
                    this.mPFPointIndices[i] = this.mPFPointIndices[n3];
                    this.mPFPointIndices[n3] = n4;
                    ++i;
                    --n3;
                }
            }
            if (n < n3) {
                this.QuickSortPoints(n, n3);
            }
            if (i < n2) {
                this.QuickSortPoints(i, n2);
            }
        }
    }
    
    public Rectangle GetClipRect() {
        return this.mClipRect;
    }
    
    public int[] GetAdditiveClippedTable() {
        if (SexyGraphics.ADDITIVE_CLIPPED_TABLE == null) {
            SexyGraphics.ADDITIVE_CLIPPED_TABLE = new int[512];
            int n = 0;
            do {
                SexyGraphics.ADDITIVE_CLIPPED_TABLE[n] = n;
            } while (++n < 256);
            int n2 = 256;
            do {
                SexyGraphics.ADDITIVE_CLIPPED_TABLE[n2] = 255;
            } while (++n2 < 512);
        }
        return SexyGraphics.ADDITIVE_CLIPPED_TABLE;
    }
    
    public void DrawRect(final int n, final int n2, final int n3, final int n4) {
        this.FillRect(n, n2, n3 + 1, 1);
        this.FillRect(n, n2 + n4, n3 + 1, 1);
        this.FillRect(n, n2 + 1, 1, n4 - 1);
        this.FillRect(n + n3, n2 + 1, 1, n4 - 1);
    }
}
