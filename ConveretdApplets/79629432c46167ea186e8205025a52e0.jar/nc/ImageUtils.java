// 
// Decompiled by Procyon v0.5.30
// 

package nc;

import sexy.gui.SexyImage;

public class ImageUtils
{
    public static SexyImage CopyAndRotate90(final SexyImage sexyImage) {
        final SexyImage sexyImage2 = new SexyImage();
        sexyImage2.Create(sexyImage.GetHeight(), sexyImage.GetWidth());
        for (int i = 0; i < sexyImage.GetWidth(); ++i) {
            for (int j = 0; j < sexyImage.GetHeight(); ++j) {
                sexyImage2.mBits[sexyImage2.GetWidth() * i + sexyImage.GetWidth() - j - 1] = sexyImage.mBits[sexyImage.GetWidth() * j + i];
            }
        }
        sexyImage2.BitsChanged();
        return sexyImage2;
    }
}
