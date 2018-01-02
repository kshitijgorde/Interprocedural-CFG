// 
// Decompiled by Procyon v0.5.30
// 

package anon.util.captcha;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Color;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Image;

public class BinaryImageExtractor
{
    public static Image binaryToImage(final byte[] array) {
        Image image;
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            final int int1 = dataInputStream.readInt();
            final int int2 = dataInputStream.readInt();
            if (int1 < 0 || int2 < 0 || array.length != 8 + (int2 * int1 + 7) / 8) {
                throw new Exception("BinaryImageExtractor: binaryToImage: The binary image has an invalid size.");
            }
            final int[] array2 = new int[int1 * int2];
            int n = byteArrayInputStream.read();
            for (int i = 0; i < int1 * int2; ++i) {
                if ((n & 0x80) == 0x80) {
                    array2[i] = Color.darkGray.getRGB();
                }
                else {
                    array2[i] = Color.white.getRGB();
                }
                n <<= 1;
                if (i % 8 == 7) {
                    n = byteArrayInputStream.read();
                }
            }
            image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(int1, int2, array2, 0, int1));
        }
        catch (Exception ex) {
            image = null;
        }
        return image;
    }
}
