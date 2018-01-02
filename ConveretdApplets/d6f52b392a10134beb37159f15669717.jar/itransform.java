import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class itransform
{
    static final int mask41 = 986895;
    static final int mask42 = 15790320;
    
    static BufferedImage halve(final BufferedImage bufferedImage) {
        final BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2, 2);
        for (int i = 0; i < bufferedImage2.getWidth(); ++i) {
            for (int j = 0; j < bufferedImage2.getHeight(); ++j) {
                bufferedImage2.setRGB(i, j, ave4(bufferedImage.getRGB(i + i, j + j), bufferedImage.getRGB(i + i + 1, j + j), bufferedImage.getRGB(i + i, j + j + 1), bufferedImage.getRGB(i + i + 1, j + j + 1)));
            }
        }
        return bufferedImage2;
    }
    
    static BufferedImage dblim(final BufferedImage bufferedImage) {
        final BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth() * 2, bufferedImage.getHeight() * 2, 1);
        for (int i = 0; i < bufferedImage.getWidth(); ++i) {
            for (int j = 0; j < bufferedImage.getHeight(); ++j) {
                final int rgb = bufferedImage.getRGB(i, j);
                final int n = (i == 0) ? rgb : bufferedImage.getRGB(i - 1, j);
                final int n2 = (j == 0) ? rgb : bufferedImage.getRGB(i, j - 1);
                final int n3 = (i == bufferedImage.getWidth() - 1) ? rgb : bufferedImage.getRGB(i + 1, j);
                final int n4 = (j == bufferedImage.getHeight() - 1) ? rgb : bufferedImage.getRGB(i, j + 1);
                bufferedImage2.setRGB(i + i, j + j, ave4(rgb, rgb, n, n2));
                bufferedImage2.setRGB(i + i + 1, j + j, ave4(rgb, rgb, n3, n2));
                bufferedImage2.setRGB(i + i, j + j + 1, ave4(rgb, rgb, n, n4));
                bufferedImage2.setRGB(i + i + 1, j + j + 1, ave4(rgb, rgb, n3, n4));
            }
        }
        return bufferedImage2;
    }
    
    static BufferedImage custom(BufferedImage bufferedImage, final int i, final int n) {
        if (i > bufferedImage.getWidth()) {
            while (i >= bufferedImage.getWidth() << 1) {
                bufferedImage = dblim(bufferedImage);
            }
        }
        else {
            while (i <= bufferedImage.getWidth() >> 1) {
                bufferedImage = halve(bufferedImage);
            }
        }
        final BufferedImage bufferedImage2 = new BufferedImage(i, bufferedImage.getHeight(), 2);
        for (int j = 0; j < bufferedImage.getHeight(); ++j) {
            for (int k = 0; k < i; ++k) {
                final float n2 = k / (i - 1) * (bufferedImage.getWidth() - 1);
                final int n3 = (int)n2;
                int n4 = (int)n2 + 1;
                final float n5 = 1.0f - n2 + n3;
                if (n5 == 1.0f) {
                    n4 = n3;
                }
                bufferedImage2.setRGB(k, j, avelin(bufferedImage.getRGB(n3, j), bufferedImage.getRGB(n4, j), n5));
            }
        }
        final BufferedImage bufferedImage3 = new BufferedImage(i, n, 2);
        for (int l = 0; l < i; ++l) {
            for (int n6 = 0; n6 < n; ++n6) {
                final float n7 = n6 / (n - 1) * (bufferedImage2.getHeight() - 1);
                final int n8 = (int)n7;
                int n9 = (int)n7 + 1;
                final float n10 = 1.0f - n7 + n8;
                if (n10 == 1.0f) {
                    n9 = n8;
                }
                bufferedImage3.setRGB(l, n6, avelin(bufferedImage2.getRGB(l, n8), bufferedImage2.getRGB(l, n9), n10));
            }
        }
        return bufferedImage3;
    }
    
    static int avelin(final int n, final int n2, final float n3) {
        final int n4 = (int)(n3 * 256.0 + 0.5);
        final int n5 = 256 - n4;
        return ((n & 0xFF) * n4 + (n2 & 0xFF) * n5 >> 8) + ((n >> 8 & 0xFF) * n4 + (n2 >> 8 & 0xFF) * n5 >> 8 << 8) + ((n >> 16 & 0xFF) * n4 + (n2 >> 16 & 0xFF) * n5 >> 8 << 16) - 16777216;
    }
    
    static int ave4(final int n, final int n2, final int n3, final int n4) {
        return ((n & 0xF0F0F) + (n2 & 0xF0F0F) + (n3 & 0xF0F0F) + (n4 & 0xF0F0F) >> 2 & 0xF0F0F) + ((n & 0xF0F0F0) + (n2 & 0xF0F0F0) + (n3 & 0xF0F0F0) + (n4 & 0xF0F0F0) >> 2) - 16777216;
    }
}
