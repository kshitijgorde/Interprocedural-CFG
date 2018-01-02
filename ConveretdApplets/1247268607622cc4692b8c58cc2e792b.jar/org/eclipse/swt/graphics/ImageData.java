// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;
import java.io.InputStream;
import org.eclipse.swt.internal.CloneableCompatibility;

public final class ImageData implements CloneableCompatibility
{
    public int width;
    public int height;
    public int depth;
    public int scanlinePad;
    public int bytesPerLine;
    public byte[] data;
    public PaletteData palette;
    public int transparentPixel;
    public byte[] maskData;
    public int maskPad;
    public byte[] alphaData;
    public int alpha;
    public int type;
    public int x;
    public int y;
    public int disposalMethod;
    public int delayTime;
    static final byte[][] ANY_TO_EIGHT;
    static final byte[] ONE_TO_ONE_MAPPING;
    static final int[][] DITHER_MATRIX;
    static final int BLIT_SRC = 1;
    static final int BLIT_ALPHA = 2;
    static final int BLIT_DITHER = 4;
    static final int ALPHA_OPAQUE = 255;
    static final int ALPHA_TRANSPARENT = 0;
    static final int ALPHA_CHANNEL_SEPARATE = -1;
    static final int ALPHA_CHANNEL_SOURCE = -2;
    static final int ALPHA_MASK_UNPACKED = -3;
    static final int ALPHA_MASK_PACKED = -4;
    static final int ALPHA_MASK_INDEX = -5;
    static final int ALPHA_MASK_RGB = -6;
    static final int LSB_FIRST = 0;
    static final int MSB_FIRST = 1;
    private static final int TYPE_GENERIC_8 = 0;
    private static final int TYPE_GENERIC_16_MSB = 1;
    private static final int TYPE_GENERIC_16_LSB = 2;
    private static final int TYPE_GENERIC_24 = 3;
    private static final int TYPE_GENERIC_32_MSB = 4;
    private static final int TYPE_GENERIC_32_LSB = 5;
    private static final int TYPE_INDEX_8 = 6;
    private static final int TYPE_INDEX_4 = 7;
    private static final int TYPE_INDEX_2 = 8;
    private static final int TYPE_INDEX_1_MSB = 9;
    private static final int TYPE_INDEX_1_LSB = 10;
    
    static {
        ANY_TO_EIGHT = new byte[9][];
        for (int i = 0; i < 9; ++i) {
            final byte[][] any_TO_EIGHT = ImageData.ANY_TO_EIGHT;
            final int n = i;
            final byte[] array = new byte[1 << i];
            any_TO_EIGHT[n] = array;
            final byte[] array2 = array;
            if (i != 0) {
                int n2 = 0;
                int n3 = 65536;
                while ((n3 >>= i) != 0) {
                    n2 |= n3;
                }
                int j = 0;
                int n4 = 0;
                while (j < 65536) {
                    array2[n4++] = (byte)(j >> 8);
                    j += n2;
                }
            }
        }
        ONE_TO_ONE_MAPPING = ImageData.ANY_TO_EIGHT[8];
        DITHER_MATRIX = new int[][] { { 16515072, 8126464, 14417920, 6029312, 15990784, 7602176, 13893632, 5505024 }, { 3932160, 12320768, 1835008, 10223616, 3407872, 11796480, 1310720, 9699328 }, { 13369344, 4980736, 15466496, 7077888, 12845056, 4456448, 14942208, 6553600 }, { 786432, 9175040, 2883584, 11272192, 262144, 8650752, 2359296, 10747904 }, { 15728640, 7340032, 13631488, 5242880, 16252928, 7864320, 14155776, 5767168 }, { 3145728, 11534336, 1048576, 9437184, 3670016, 12058624, 1572864, 9961472 }, { 12582912, 4194304, 14680064, 6291456, 13107200, 4718592, 15204352, 6815744 }, { 0, 8388608, 2097152, 10485760, 524288, 8912896, 2621440, 11010048 } };
    }
    
    public ImageData(final int n, final int n2, final int n3, final PaletteData paletteData) {
        this(n, n2, n3, paletteData, 4, null, 0, null, null, -1, -1, -1, 0, 0, 0, 0);
    }
    
    public ImageData(final int n, final int n2, final int n3, final PaletteData paletteData, final int n4, final byte[] array) {
        this(n, n2, n3, paletteData, n4, checkData(array), 0, null, null, -1, -1, -1, 0, 0, 0, 0);
    }
    
    public ImageData(final InputStream inputStream) {
        final ImageData[] load = ImageDataLoader.load(inputStream);
        if (load.length < 1) {
            SWT.error(40);
        }
        final ImageData imageData = load[0];
        this.setAllFields(imageData.width, imageData.height, imageData.depth, imageData.scanlinePad, imageData.bytesPerLine, imageData.data, imageData.palette, imageData.transparentPixel, imageData.maskData, imageData.maskPad, imageData.alphaData, imageData.alpha, imageData.type, imageData.x, imageData.y, imageData.disposalMethod, imageData.delayTime);
    }
    
    public ImageData(final String s) {
        final ImageData[] load = ImageDataLoader.load(s);
        if (load.length < 1) {
            SWT.error(40);
        }
        final ImageData imageData = load[0];
        this.setAllFields(imageData.width, imageData.height, imageData.depth, imageData.scanlinePad, imageData.bytesPerLine, imageData.data, imageData.palette, imageData.transparentPixel, imageData.maskData, imageData.maskPad, imageData.alphaData, imageData.alpha, imageData.type, imageData.x, imageData.y, imageData.disposalMethod, imageData.delayTime);
    }
    
    ImageData() {
    }
    
    ImageData(final int n, final int n2, final int n3, final PaletteData paletteData, final int n4, final byte[] array, final int n5, final byte[] array2, final byte[] array3, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        if (paletteData == null) {
            SWT.error(4);
        }
        if (n3 != 1 && n3 != 2 && n3 != 4 && n3 != 8 && n3 != 16 && n3 != 24 && n3 != 32) {
            SWT.error(5);
        }
        if (n <= 0 || n2 <= 0) {
            SWT.error(5);
        }
        if (n4 == 0) {
            SWT.error(7);
        }
        final int n13 = ((n * n3 + 7) / 8 + (n4 - 1)) / n4 * n4;
        final int n14 = (n8 == 5) ? (((n + 7) / 8 + 3) / 4 * 4) : n13;
        if (array != null && array.length < n14 * n2) {
            SWT.error(5);
        }
        this.setAllFields(n, n2, n3, n4, n13, (array != null) ? array : new byte[n13 * n2], paletteData, n7, array2, n5, array3, n6, n8, n9, n10, n11, n12);
    }
    
    void setAllFields(final int width, final int height, final int depth, final int scanlinePad, final int bytesPerLine, final byte[] data, final PaletteData palette, final int transparentPixel, final byte[] maskData, final int maskPad, final byte[] alphaData, final int alpha, final int type, final int x, final int y, final int disposalMethod, final int delayTime) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.scanlinePad = scanlinePad;
        this.bytesPerLine = bytesPerLine;
        this.data = data;
        this.palette = palette;
        this.transparentPixel = transparentPixel;
        this.maskData = maskData;
        this.maskPad = maskPad;
        this.alphaData = alphaData;
        this.alpha = alpha;
        this.type = type;
        this.x = x;
        this.y = y;
        this.disposalMethod = disposalMethod;
        this.delayTime = delayTime;
    }
    
    public static ImageData internal_new(final int n, final int n2, final int n3, final PaletteData paletteData, final int n4, final byte[] array, final int n5, final byte[] array2, final byte[] array3, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        return new ImageData(n, n2, n3, paletteData, n4, array, n5, array2, array3, n6, n7, n8, n9, n10, n11, n12);
    }
    
    ImageData colorMaskImage(final int n) {
        final ImageData imageData = new ImageData(this.width, this.height, 1, bwPalette(), 2, null, 0, null, null, -1, -1, -1, 0, 0, 0, 0);
        final int[] array = new int[this.width];
        for (int i = 0; i < this.height; ++i) {
            this.getPixels(0, i, this.width, array, 0);
            for (int j = 0; j < this.width; ++j) {
                if (n != -1 && array[j] == n) {
                    array[j] = 0;
                }
                else {
                    array[j] = 1;
                }
            }
            imageData.setPixels(0, i, this.width, array, 0);
        }
        return imageData;
    }
    
    static byte[] checkData(final byte[] array) {
        if (array == null) {
            SWT.error(4);
        }
        return array;
    }
    
    public Object clone() {
        final byte[] array = new byte[this.data.length];
        System.arraycopy(this.data, 0, array, 0, this.data.length);
        byte[] array2 = null;
        if (this.maskData != null) {
            array2 = new byte[this.maskData.length];
            System.arraycopy(this.maskData, 0, array2, 0, this.maskData.length);
        }
        byte[] array3 = null;
        if (this.alphaData != null) {
            array3 = new byte[this.alphaData.length];
            System.arraycopy(this.alphaData, 0, array3, 0, this.alphaData.length);
        }
        return new ImageData(this.width, this.height, this.depth, this.palette, this.scanlinePad, array, this.maskPad, array2, array3, this.alpha, this.transparentPixel, this.type, this.x, this.y, this.disposalMethod, this.delayTime);
    }
    
    public int getAlpha(final int n, final int n2) {
        if (n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (this.alphaData == null) {
            return 255;
        }
        return this.alphaData[n2 * this.width + n] & 0xFF;
    }
    
    public void getAlphas(final int n, final int n2, final int n3, final byte[] array, final int n4) {
        if (array == null) {
            SWT.error(4);
        }
        if (n3 < 0 || n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (n3 == 0) {
            return;
        }
        if (this.alphaData == null) {
            for (int n5 = n4 + n3, i = n4; i < n5; ++i) {
                array[i] = -1;
            }
            return;
        }
        System.arraycopy(this.alphaData, n2 * this.width + n, array, n4, n3);
    }
    
    public int getPixel(final int n, final int n2) {
        if (n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        switch (this.depth) {
            case 32: {
                final int n3 = n2 * this.bytesPerLine + n * 4;
                return ((this.data[n3] & 0xFF) << 24) + ((this.data[n3 + 1] & 0xFF) << 16) + ((this.data[n3 + 2] & 0xFF) << 8) + (this.data[n3 + 3] & 0xFF);
            }
            case 24: {
                final int n4 = n2 * this.bytesPerLine + n * 3;
                return ((this.data[n4] & 0xFF) << 16) + ((this.data[n4 + 1] & 0xFF) << 8) + (this.data[n4 + 2] & 0xFF);
            }
            case 16: {
                final int n5 = n2 * this.bytesPerLine + n * 2;
                return ((this.data[n5 + 1] & 0xFF) << 8) + (this.data[n5] & 0xFF);
            }
            case 8: {
                return this.data[n2 * this.bytesPerLine + n] & 0xFF;
            }
            case 4: {
                final int n6 = this.data[n2 * this.bytesPerLine + (n >> 1)] & 0xFF;
                if ((n & 0x1) == 0x0) {
                    return n6 >> 4;
                }
                return n6 & 0xF;
            }
            case 2: {
                final int n7 = this.data[n2 * this.bytesPerLine + (n >> 2)] & 0xFF;
                final int n8 = 3 - n % 4;
                return (n7 & 3 << n8 * 2) >> n8 * 2;
            }
            case 1: {
                if ((this.data[n2 * this.bytesPerLine + (n >> 3)] & 0xFF & 1 << 7 - (n & 0x7)) == 0x0) {
                    return 0;
                }
                return 1;
            }
            default: {
                SWT.error(38);
                return 0;
            }
        }
    }
    
    public void getPixels(final int n, int n2, final int n3, final byte[] array, final int n4) {
        if (array == null) {
            SWT.error(4);
        }
        if (n3 < 0 || n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (n3 == 0) {
            return;
        }
        int i = n3;
        int n5 = n4;
        int n6 = n;
        switch (this.depth) {
            case 8: {
                int n7 = n2 * this.bytesPerLine + n;
                for (int j = 0; j < n3; ++j) {
                    array[n5] = this.data[n7];
                    ++n5;
                    if (++n6 >= this.width) {
                        n7 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        ++n7;
                    }
                }
            }
            case 4: {
                int n8 = n2 * this.bytesPerLine + (n >> 1);
                if ((n & 0x1) == 0x1) {
                    array[n5] = (byte)(this.data[n8] & 0xFF & 0xF);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n8 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        ++n8;
                    }
                }
                while (i > 1) {
                    final int n9 = this.data[n8] & 0xFF;
                    array[n5] = (byte)(n9 >> 4);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n8 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        array[n5] = (byte)(n9 & 0xF);
                        ++n5;
                        --i;
                        if (++n6 >= this.width) {
                            n8 = ++n2 * this.bytesPerLine;
                            n6 = 0;
                        }
                        else {
                            ++n8;
                        }
                    }
                }
                if (i > 0) {
                    array[n5] = (byte)((this.data[n8] & 0xFF) >> 4);
                }
            }
            case 2: {
                int n10 = n2 * this.bytesPerLine + (n >> 2);
                int n11 = this.data[n10] & 0xFF;
                while (i > 0) {
                    final int n12 = 3 - n6 % 4;
                    array[n5] = (byte)((n11 & 3 << n12 * 2) >> n12 * 2);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n10 = ++n2 * this.bytesPerLine;
                        if (i > 0) {
                            n11 = (this.data[n10] & 0xFF);
                        }
                        n6 = 0;
                    }
                    else {
                        if (n12 != 0) {
                            continue;
                        }
                        ++n10;
                        n11 = (this.data[n10] & 0xFF);
                    }
                }
            }
            case 1: {
                int n13 = n2 * this.bytesPerLine + (n >> 3);
                int n14 = this.data[n13] & 0xFF;
                while (i > 0) {
                    final int n15 = 1 << 7 - (n6 & 0x7);
                    if ((n14 & n15) == 0x0) {
                        array[n5] = 0;
                    }
                    else {
                        array[n5] = 1;
                    }
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n13 = ++n2 * this.bytesPerLine;
                        if (i > 0) {
                            n14 = (this.data[n13] & 0xFF);
                        }
                        n6 = 0;
                    }
                    else {
                        if (n15 != 1) {
                            continue;
                        }
                        ++n13;
                        if (i <= 0) {
                            continue;
                        }
                        n14 = (this.data[n13] & 0xFF);
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public void getPixels(final int n, int n2, final int n3, final int[] array, final int n4) {
        if (array == null) {
            SWT.error(4);
        }
        if (n3 < 0 || n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (n3 == 0) {
            return;
        }
        int i = n3;
        int n5 = n4;
        int n6 = n;
        switch (this.depth) {
            case 32: {
                int n7 = n2 * this.bytesPerLine + n * 4;
                int n8 = n4;
                for (int j = 0; j < n3; ++j) {
                    array[n8] = ((this.data[n7] & 0xFF) << 24 | (this.data[n7 + 1] & 0xFF) << 16 | (this.data[n7 + 2] & 0xFF) << 8 | (this.data[n7 + 3] & 0xFF));
                    ++n8;
                    if (++n6 >= this.width) {
                        n7 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        n7 += 4;
                    }
                }
            }
            case 24: {
                int n9 = n2 * this.bytesPerLine + n * 3;
                for (int k = 0; k < n3; ++k) {
                    array[n5] = ((this.data[n9] & 0xFF) << 16 | (this.data[n9 + 1] & 0xFF) << 8 | (this.data[n9 + 2] & 0xFF));
                    ++n5;
                    if (++n6 >= this.width) {
                        n9 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        n9 += 3;
                    }
                }
            }
            case 16: {
                int n10 = n2 * this.bytesPerLine + n * 2;
                for (int l = 0; l < n3; ++l) {
                    array[n5] = ((this.data[n10 + 1] & 0xFF) << 8) + (this.data[n10] & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n10 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        n10 += 2;
                    }
                }
            }
            case 8: {
                int n11 = n2 * this.bytesPerLine + n;
                for (int n12 = 0; n12 < n3; ++n12) {
                    array[n5] = (this.data[n11] & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n11 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        ++n11;
                    }
                }
            }
            case 4: {
                int n13 = n2 * this.bytesPerLine + (n >> 1);
                if ((n & 0x1) == 0x1) {
                    array[n5] = (this.data[n13] & 0xFF & 0xF);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n13 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        ++n13;
                    }
                }
                while (i > 1) {
                    final int n14 = this.data[n13] & 0xFF;
                    array[n5] = n14 >> 4;
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n13 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        array[n5] = (n14 & 0xF);
                        ++n5;
                        --i;
                        if (++n6 >= this.width) {
                            n13 = ++n2 * this.bytesPerLine;
                            n6 = 0;
                        }
                        else {
                            ++n13;
                        }
                    }
                }
                if (i > 0) {
                    array[n5] = (this.data[n13] & 0xFF) >> 4;
                }
            }
            case 2: {
                int n15 = n2 * this.bytesPerLine + (n >> 2);
                int n16 = this.data[n15] & 0xFF;
                while (i > 0) {
                    final int n17 = 3 - n6 % 4;
                    array[n5] = (byte)((n16 & 3 << n17 * 2) >> n17 * 2);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n15 = ++n2 * this.bytesPerLine;
                        if (i > 0) {
                            n16 = (this.data[n15] & 0xFF);
                        }
                        n6 = 0;
                    }
                    else {
                        if (n17 != 0) {
                            continue;
                        }
                        ++n15;
                        n16 = (this.data[n15] & 0xFF);
                    }
                }
            }
            case 1: {
                int n18 = n2 * this.bytesPerLine + (n >> 3);
                int n19 = this.data[n18] & 0xFF;
                while (i > 0) {
                    final int n20 = 1 << 7 - (n6 & 0x7);
                    if ((n19 & n20) == 0x0) {
                        array[n5] = 0;
                    }
                    else {
                        array[n5] = 1;
                    }
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n18 = ++n2 * this.bytesPerLine;
                        if (i > 0) {
                            n19 = (this.data[n18] & 0xFF);
                        }
                        n6 = 0;
                    }
                    else {
                        if (n20 != 1) {
                            continue;
                        }
                        ++n18;
                        if (i <= 0) {
                            continue;
                        }
                        n19 = (this.data[n18] & 0xFF);
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public RGB[] getRGBs() {
        return this.palette.getRGBs();
    }
    
    public ImageData getTransparencyMask() {
        if (this.getTransparencyType() == 2) {
            return new ImageData(this.width, this.height, 1, bwPalette(), this.maskPad, this.maskData);
        }
        return this.colorMaskImage(this.transparentPixel);
    }
    
    public int getTransparencyType() {
        if (this.maskData != null) {
            return 2;
        }
        if (this.transparentPixel != -1) {
            return 4;
        }
        if (this.alphaData != null) {
            return 1;
        }
        return 0;
    }
    
    int getByteOrder() {
        return (this.depth != 16) ? 1 : 0;
    }
    
    public ImageData scaledTo(int n, int n2) {
        final boolean b = n < 0;
        if (b) {
            n = -n;
        }
        final boolean b2 = n2 < 0;
        if (b2) {
            n2 = -n2;
        }
        final ImageData imageData = new ImageData(n, n2, this.depth, this.palette, this.scanlinePad, null, 0, null, null, -1, this.transparentPixel, this.type, this.x, this.y, this.disposalMethod, this.delayTime);
        if (this.palette.isDirect) {
            blit(1, this.data, this.depth, this.bytesPerLine, this.getByteOrder(), 0, 0, this.width, this.height, 0, 0, 0, 255, null, 0, 0, 0, imageData.data, imageData.depth, imageData.bytesPerLine, imageData.getByteOrder(), 0, 0, imageData.width, imageData.height, 0, 0, 0, b, b2);
        }
        else {
            blit(1, this.data, this.depth, this.bytesPerLine, this.getByteOrder(), 0, 0, this.width, this.height, null, null, null, 255, null, 0, 0, 0, imageData.data, imageData.depth, imageData.bytesPerLine, imageData.getByteOrder(), 0, 0, imageData.width, imageData.height, null, null, null, b, b2);
        }
        if (this.maskData != null) {
            imageData.maskPad = this.maskPad;
            final int n3 = ((imageData.width + 7) / 8 + (imageData.maskPad - 1)) / imageData.maskPad * imageData.maskPad;
            imageData.maskData = new byte[n3 * imageData.height];
            blit(1, this.maskData, 1, ((this.width + 7) / 8 + (this.maskPad - 1)) / this.maskPad * this.maskPad, 1, 0, 0, this.width, this.height, null, null, null, 255, null, 0, 0, 0, imageData.maskData, 1, n3, 1, 0, 0, imageData.width, imageData.height, null, null, null, b, b2);
        }
        else if (this.alpha != -1) {
            imageData.alpha = this.alpha;
        }
        else if (this.alphaData != null) {
            imageData.alphaData = new byte[imageData.width * imageData.height];
            blit(1, this.alphaData, 8, this.width, 1, 0, 0, this.width, this.height, null, null, null, 255, null, 0, 0, 0, imageData.alphaData, 8, imageData.width, 1, 0, 0, imageData.width, imageData.height, null, null, null, b, b2);
        }
        return imageData;
    }
    
    public void setAlpha(final int n, final int n2, final int n3) {
        if (n >= this.width || n2 >= this.height || n < 0 || n2 < 0 || n3 < 0 || n3 > 255) {
            SWT.error(5);
        }
        if (this.alphaData == null) {
            this.alphaData = new byte[this.width * this.height];
        }
        this.alphaData[n2 * this.width + n] = (byte)n3;
    }
    
    public void setAlphas(final int n, final int n2, final int n3, final byte[] array, final int n4) {
        if (array == null) {
            SWT.error(4);
        }
        if (n3 < 0 || n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (n3 == 0) {
            return;
        }
        if (this.alphaData == null) {
            this.alphaData = new byte[this.width * this.height];
        }
        System.arraycopy(array, n4, this.alphaData, n2 * this.width + n, n3);
    }
    
    public void setPixel(final int n, final int n2, final int n3) {
        if (n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        switch (this.depth) {
            case 32: {
                final int n4 = n2 * this.bytesPerLine + n * 4;
                this.data[n4] = (byte)(n3 >> 24 & 0xFF);
                this.data[n4 + 1] = (byte)(n3 >> 16 & 0xFF);
                this.data[n4 + 2] = (byte)(n3 >> 8 & 0xFF);
                this.data[n4 + 3] = (byte)(n3 & 0xFF);
            }
            case 24: {
                final int n5 = n2 * this.bytesPerLine + n * 3;
                this.data[n5] = (byte)(n3 >> 16 & 0xFF);
                this.data[n5 + 1] = (byte)(n3 >> 8 & 0xFF);
                this.data[n5 + 2] = (byte)(n3 & 0xFF);
            }
            case 16: {
                final int n6 = n2 * this.bytesPerLine + n * 2;
                this.data[n6 + 1] = (byte)(n3 >> 8 & 0xFF);
                this.data[n6] = (byte)(n3 & 0xFF);
            }
            case 8: {
                this.data[n2 * this.bytesPerLine + n] = (byte)(n3 & 0xFF);
            }
            case 4: {
                final int n7 = n2 * this.bytesPerLine + (n >> 1);
                if ((n & 0x1) == 0x0) {
                    this.data[n7] = (byte)((this.data[n7] & 0xF) | (n3 & 0xF) << 4);
                }
                else {
                    this.data[n7] = (byte)((this.data[n7] & 0xF0) | (n3 & 0xF));
                }
            }
            case 2: {
                final int n8 = n2 * this.bytesPerLine + (n >> 2);
                final byte b = this.data[n8];
                final int n9 = 3 - n % 4;
                this.data[n8] = (byte)((this.data[n8] & (0xFF ^ 3 << n9 * 2)) | n3 << n9 * 2);
            }
            case 1: {
                final int n10 = n2 * this.bytesPerLine + (n >> 3);
                final byte b2 = this.data[n10];
                final int n11 = 1 << 7 - (n & 0x7);
                if ((n3 & 0x1) == 0x1) {
                    this.data[n10] = (byte)(b2 | n11);
                }
                else {
                    this.data[n10] = (byte)(b2 & ~n11);
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public void setPixels(final int n, int n2, final int n3, final byte[] array, final int n4) {
        if (array == null) {
            SWT.error(4);
        }
        if (n3 < 0 || n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (n3 == 0) {
            return;
        }
        int i = n3;
        int n5 = n4;
        int n6 = n;
        switch (this.depth) {
            case 8: {
                int n7 = n2 * this.bytesPerLine + n;
                for (int j = 0; j < n3; ++j) {
                    this.data[n7] = (byte)(array[n5] & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n7 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        ++n7;
                    }
                }
            }
            case 4: {
                int n8 = n2 * this.bytesPerLine + (n >> 1);
                int n9 = ((n & 0x1) == 0x0) ? 1 : 0;
                while (i > 0) {
                    final byte b = (byte)(array[n5] & 0xF);
                    if (n9 != 0) {
                        this.data[n8] = (byte)((this.data[n8] & 0xF) | b << 4);
                    }
                    else {
                        this.data[n8] = (byte)((this.data[n8] & 0xF0) | b);
                    }
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n8 = ++n2 * this.bytesPerLine;
                        n9 = 1;
                        n6 = 0;
                    }
                    else {
                        if (n9 == 0) {
                            ++n8;
                        }
                        n9 = ((n9 == 0) ? 1 : 0);
                    }
                }
            }
            case 2: {
                final byte[] array2 = { -4, -13, -49, 63 };
                int n10 = n2 * this.bytesPerLine + (n >> 2);
                int n11 = 3 - n % 4;
                while (i > 0) {
                    this.data[n10] = (byte)((this.data[n10] & array2[n11]) | (array[n5] & 0x3) << n11 * 2);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n10 = ++n2 * this.bytesPerLine;
                        n11 = 0;
                        n6 = 0;
                    }
                    else if (n11 == 0) {
                        ++n10;
                        n11 = 3;
                    }
                    else {
                        --n11;
                    }
                }
            }
            case 1: {
                int n12 = n2 * this.bytesPerLine + (n >> 3);
                while (i > 0) {
                    final int n13 = 1 << 7 - (n6 & 0x7);
                    if ((array[n5] & 0x1) == 0x1) {
                        this.data[n12] = (byte)((this.data[n12] & 0xFF) | n13);
                    }
                    else {
                        this.data[n12] = (byte)(this.data[n12] & 0xFF & ~n13);
                    }
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n12 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        if (n13 != 1) {
                            continue;
                        }
                        ++n12;
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public void setPixels(final int n, int n2, final int n3, final int[] array, final int n4) {
        if (array == null) {
            SWT.error(4);
        }
        if (n3 < 0 || n >= this.width || n2 >= this.height || n < 0 || n2 < 0) {
            SWT.error(5);
        }
        if (n3 == 0) {
            return;
        }
        int i = n3;
        int n5 = n4;
        int n6 = n;
        switch (this.depth) {
            case 32: {
                int n7 = n2 * this.bytesPerLine + n * 4;
                for (int j = 0; j < n3; ++j) {
                    final int n8 = array[n5];
                    this.data[n7] = (byte)(n8 >> 24 & 0xFF);
                    this.data[n7 + 1] = (byte)(n8 >> 16 & 0xFF);
                    this.data[n7 + 2] = (byte)(n8 >> 8 & 0xFF);
                    this.data[n7 + 3] = (byte)(n8 & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n7 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        n7 += 4;
                    }
                }
            }
            case 24: {
                int n9 = n2 * this.bytesPerLine + n * 3;
                for (int k = 0; k < n3; ++k) {
                    final int n10 = array[n5];
                    this.data[n9] = (byte)(n10 >> 16 & 0xFF);
                    this.data[n9 + 1] = (byte)(n10 >> 8 & 0xFF);
                    this.data[n9 + 2] = (byte)(n10 & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n9 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        n9 += 3;
                    }
                }
            }
            case 16: {
                int n11 = n2 * this.bytesPerLine + n * 2;
                for (int l = 0; l < n3; ++l) {
                    final int n12 = array[n5];
                    this.data[n11] = (byte)(n12 & 0xFF);
                    this.data[n11 + 1] = (byte)(n12 >> 8 & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n11 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        n11 += 2;
                    }
                }
            }
            case 8: {
                int n13 = n2 * this.bytesPerLine + n;
                for (int n14 = 0; n14 < n3; ++n14) {
                    this.data[n13] = (byte)(array[n5] & 0xFF);
                    ++n5;
                    if (++n6 >= this.width) {
                        n13 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        ++n13;
                    }
                }
            }
            case 4: {
                int n15 = n2 * this.bytesPerLine + (n >> 1);
                int n16 = ((n & 0x1) == 0x0) ? 1 : 0;
                while (i > 0) {
                    final int n17 = array[n5] & 0xF;
                    if (n16 != 0) {
                        this.data[n15] = (byte)((this.data[n15] & 0xF) | n17 << 4);
                    }
                    else {
                        this.data[n15] = (byte)((this.data[n15] & 0xF0) | n17);
                    }
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n15 = ++n2 * this.bytesPerLine;
                        n16 = 1;
                        n6 = 0;
                    }
                    else {
                        if (n16 == 0) {
                            ++n15;
                        }
                        n16 = ((n16 == 0) ? 1 : 0);
                    }
                }
            }
            case 2: {
                final byte[] array2 = { -4, -13, -49, 63 };
                int n18 = n2 * this.bytesPerLine + (n >> 2);
                int n19 = 3 - n % 4;
                while (i > 0) {
                    this.data[n18] = (byte)((this.data[n18] & array2[n19]) | (array[n5] & 0x3) << n19 * 2);
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n18 = ++n2 * this.bytesPerLine;
                        n19 = 3;
                        n6 = 0;
                    }
                    else if (n19 == 0) {
                        ++n18;
                        n19 = 3;
                    }
                    else {
                        --n19;
                    }
                }
            }
            case 1: {
                int n20 = n2 * this.bytesPerLine + (n >> 3);
                while (i > 0) {
                    final int n21 = 1 << 7 - (n6 & 0x7);
                    if ((array[n5] & 0x1) == 0x1) {
                        this.data[n20] = (byte)((this.data[n20] & 0xFF) | n21);
                    }
                    else {
                        this.data[n20] = (byte)(this.data[n20] & 0xFF & ~n21);
                    }
                    ++n5;
                    --i;
                    if (++n6 >= this.width) {
                        n20 = ++n2 * this.bytesPerLine;
                        n6 = 0;
                    }
                    else {
                        if (n21 != 1) {
                            continue;
                        }
                        ++n20;
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    static PaletteData bwPalette() {
        return new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) });
    }
    
    static int getMSBOffset(final int n) {
        for (int i = 31; i >= 0; --i) {
            if ((n >> i & 0x1) != 0x0) {
                return i + 1;
            }
        }
        return 0;
    }
    
    static int closestMatch(final int n, final byte b, final byte b2, final byte b3, final int n2, final int n3, final int n4, final byte[] array, final byte[] array2, final byte[] array3) {
        if (n > 8) {
            return (b << 24 >>> 32 - getMSBOffset(n2) & n2) | (b2 << 24 >>> 32 - getMSBOffset(n3) & n3) | (b3 << 24 >>> 32 - getMSBOffset(n4) & n4);
        }
        int n5 = Integer.MAX_VALUE;
        int n6 = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            final int n7 = (array[i] & 0xFF) - (b & 0xFF);
            final int n8 = (array2[i] & 0xFF) - (b2 & 0xFF);
            final int n9 = (array3[i] & 0xFF) - (b3 & 0xFF);
            final int n10 = n7 * n7 + n8 * n8 + n9 * n9;
            if (n10 < n5) {
                n6 = i;
                if (n10 == 0) {
                    break;
                }
                n5 = n10;
            }
        }
        return n6;
    }
    
    static final ImageData convertMask(final ImageData imageData) {
        if (imageData.depth == 1) {
            return imageData;
        }
        final PaletteData paletteData = new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) });
        final ImageData imageData2 = new ImageData(imageData.width, imageData.height, 1, paletteData);
        int i = 0;
        final RGB[] rgBs = imageData.getRGBs();
        if (rgBs != null) {
            while (i < rgBs.length) {
                if (rgBs[i].equals(paletteData.colors[0])) {
                    break;
                }
                ++i;
            }
        }
        final int[] array = new int[imageData.width];
        for (int j = 0; j < imageData.height; ++j) {
            imageData.getPixels(0, j, imageData.width, array, 0);
            for (int k = 0; k < array.length; ++k) {
                if (array[k] == i) {
                    array[k] = 0;
                }
                else {
                    array[k] = 1;
                }
            }
            imageData2.setPixels(0, j, imageData.width, array, 0);
        }
        return imageData2;
    }
    
    static final byte[] convertPad(final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n4 == n5) {
            return array;
        }
        final int n6 = (n * n3 + 7) / 8;
        final int n7 = (n6 + (n4 - 1)) / n4 * n4;
        final int n8 = (n6 + (n5 - 1)) / n5 * n5;
        final byte[] array2 = new byte[n2 * n8];
        int n9 = 0;
        int n10 = 0;
        for (int i = 0; i < n2; ++i) {
            System.arraycopy(array, n9, array2, n10, n6);
            n9 += n7;
            n10 += n8;
        }
        return array2;
    }
    
    static void blit(final int n, final byte[] array, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, int n12, final byte[] array2, int n13, final int n14, final int n15, final byte[] array3, final int n16, final int n17, final int n18, final int n19, final int n20, final int n21, final int n22, final int n23, final int n24, final int n25, final boolean b, final boolean b2) {
        if (n21 <= 0 || n22 <= 0 || n12 == 0) {
            return;
        }
        final int n26 = 0;
        final int n27 = 0;
        final int n28 = n21 - 1;
        final int n29 = (n28 != 0) ? ((int)(((n7 << 16) - 1L) / n28)) : 0;
        final int n30 = n22 - 1;
        final int n31 = (n30 != 0) ? ((int)(((n8 << 16) - 1L) / n30)) : 0;
        int n32 = 0;
        int n33 = 0;
        switch (n2) {
            case 8: {
                n32 = 1;
                n33 = 0;
                break;
            }
            case 16: {
                n32 = 2;
                n33 = ((n4 == 1) ? 1 : 2);
                break;
            }
            case 24: {
                n32 = 3;
                n33 = 3;
                break;
            }
            case 32: {
                n32 = 4;
                n33 = ((n4 == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int n34 = n6 * n3 + n5 * n32;
        int n35 = 0;
        int n36 = 0;
        switch (n16) {
            case 8: {
                n35 = 1;
                n36 = 0;
                break;
            }
            case 16: {
                n35 = 2;
                n36 = ((n18 == 1) ? 1 : 2);
                break;
            }
            case 24: {
                n35 = 3;
                n36 = 3;
                break;
            }
            case 32: {
                n35 = 4;
                n36 = ((n18 == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int n37 = (b2 ? (n20 + n30) : n20) * n17 + (b ? (n19 + n28) : n19) * n35;
        final int n38 = b ? (-n35) : n35;
        final int n39 = b2 ? (-n17) : n17;
        int n40 = 0;
        if ((n & 0x2) != 0x0) {
            switch (n12) {
                case -3:
                case -1: {
                    if (array2 == null) {
                        n12 = 65536;
                    }
                    n40 = n15 * n13 + n14;
                    break;
                }
                case -4: {
                    if (array2 == null) {
                        n12 = 65536;
                    }
                    n13 <<= 3;
                    n40 = n15 * n13 + n14;
                    break;
                }
                case -5: {
                    return;
                }
                case -6: {
                    if (array2 == null) {
                        n12 = 65536;
                    }
                    n40 = 0;
                    break;
                }
                default: {
                    n12 = (n12 << 16) / 255;
                }
                case -2: {
                    n40 = 0;
                    break;
                }
            }
        }
        else {
            n12 = 65536;
            n40 = 0;
        }
        int n41 = n37;
        int n42 = n34;
        if (n12 == 65536 && n33 == n36 && n9 == n23 && n10 == n24 && n11 == n25 && n26 == n27) {
            switch (n32) {
                case 1: {
                    for (int i = n22, n43 = n31; i > 0; --i, n34 = (n42 = n34 + (n43 >>> 16) * n3), n43 = (n43 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
                        for (int j = n21, n44 = n29; j > 0; --j, n41 += n38, n44 = (n44 & 0xFFFF) + n29) {
                            array3[n41] = array[n42];
                            n42 += n44 >>> 16;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int k = n22, n45 = n31; k > 0; --k, n34 = (n42 = n34 + (n45 >>> 16) * n3), n45 = (n45 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
                        for (int l = n21, n46 = n29; l > 0; --l, n41 += n38, n46 = (n46 & 0xFFFF) + n29) {
                            array3[n41] = array[n42];
                            array3[n41 + 1] = array[n42 + 1];
                            n42 += (n46 >>> 16) * 2;
                        }
                    }
                    break;
                }
                case 3: {
                    for (int n47 = n22, n48 = n31; n47 > 0; --n47, n34 = (n42 = n34 + (n48 >>> 16) * n3), n48 = (n48 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
                        for (int n49 = n21, n50 = n29; n49 > 0; --n49, n41 += n38, n50 = (n50 & 0xFFFF) + n29) {
                            array3[n41] = array[n42];
                            array3[n41 + 1] = array[n42 + 1];
                            array3[n41 + 2] = array[n42 + 2];
                            n42 += (n50 >>> 16) * 3;
                        }
                    }
                    break;
                }
                case 4: {
                    for (int n51 = n22, n52 = n31; n51 > 0; --n51, n34 = (n42 = n34 + (n52 >>> 16) * n3), n52 = (n52 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
                        for (int n53 = n21, n54 = n29; n53 > 0; --n53, n41 += n38, n54 = (n54 & 0xFFFF) + n29) {
                            array3[n41] = array[n42];
                            array3[n41 + 1] = array[n42 + 1];
                            array3[n41 + 2] = array[n42 + 2];
                            array3[n41 + 3] = array[n42 + 3];
                            n42 += (n54 >>> 16) * 4;
                        }
                    }
                    break;
                }
            }
            return;
        }
        if (n12 == 65536 && n33 == 4 && n36 == 4 && n9 == 65280 && n10 == 16711680 && n11 == -16777216 && n23 == 16711680 && n24 == 65280 && n25 == 255) {
            for (int n55 = n22, n56 = n31; n55 > 0; --n55, n34 = (n42 = n34 + (n56 >>> 16) * n3), n56 = (n56 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
                for (int n57 = n21, n58 = n29; n57 > 0; --n57, n41 += n38, n58 = (n58 & 0xFFFF) + n29) {
                    array3[n41] = array[n42 + 3];
                    array3[n41 + 1] = array[n42 + 2];
                    array3[n41 + 2] = array[n42 + 1];
                    array3[n41 + 3] = array[n42];
                    n42 += (n58 >>> 16) * 4;
                }
            }
            return;
        }
        if (n12 == 65536 && n33 == 3 && n36 == 4 && n9 == 255 && n10 == 65280 && n11 == 16711680 && n23 == 16711680 && n24 == 65280 && n25 == 255) {
            for (int n59 = n22, n60 = n31; n59 > 0; --n59, n34 = (n42 = n34 + (n60 >>> 16) * n3), n60 = (n60 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
                for (int n61 = n21, n62 = n29; n61 > 0; --n61, n41 += n38, n62 = (n62 & 0xFFFF) + n29) {
                    array3[n41] = 0;
                    array3[n41 + 1] = array[n42 + 2];
                    array3[n41 + 2] = array[n42 + 1];
                    array3[n41 + 3] = array[n42];
                    n42 += (n62 >>> 16) * 3;
                }
            }
            return;
        }
        final int channelShift = getChannelShift(n9);
        final byte[] array4 = ImageData.ANY_TO_EIGHT[getChannelWidth(n9, channelShift)];
        final int channelShift2 = getChannelShift(n10);
        final byte[] array5 = ImageData.ANY_TO_EIGHT[getChannelWidth(n10, channelShift2)];
        final int channelShift3 = getChannelShift(n11);
        final byte[] array6 = ImageData.ANY_TO_EIGHT[getChannelWidth(n11, channelShift3)];
        final int channelShift4 = getChannelShift(n26);
        final byte[] array7 = ImageData.ANY_TO_EIGHT[getChannelWidth(n26, channelShift4)];
        final int channelShift5 = getChannelShift(n23);
        final int channelWidth = getChannelWidth(n23, channelShift5);
        final byte[] array8 = ImageData.ANY_TO_EIGHT[channelWidth];
        final int n63 = 8 - channelWidth;
        final int channelShift6 = getChannelShift(n24);
        final int channelWidth2 = getChannelWidth(n24, channelShift6);
        final byte[] array9 = ImageData.ANY_TO_EIGHT[channelWidth2];
        final int n64 = 8 - channelWidth2;
        final int channelShift7 = getChannelShift(n25);
        final int channelWidth3 = getChannelWidth(n25, channelShift7);
        final byte[] array10 = ImageData.ANY_TO_EIGHT[channelWidth3];
        final int n65 = 8 - channelWidth3;
        final int channelShift8 = getChannelShift(n27);
        final int channelWidth4 = getChannelWidth(n27, channelShift8);
        final byte[] array11 = ImageData.ANY_TO_EIGHT[channelWidth4];
        final int n66 = 8 - channelWidth4;
        int n67 = n40;
        int n68 = n12;
        int n69 = 0;
        int n70 = 0;
        int n71 = 0;
        int n72 = 0;
        int n73 = 0;
        int n74 = 0;
        int n75 = 0;
        int n76 = 0;
        for (int n77 = n22, n78 = n31; n77 > 0; --n77, n34 = (n42 = n34 + (n78 >>> 16) * n3), n40 = (n67 = n40 + (n78 >>> 16) * n13), n78 = (n78 & 0xFFFF) + n31, n37 = (n41 = n37 + n39)) {
            for (int n79 = n21, n80 = n29; n79 > 0; --n79, n41 += n38, n80 = (n80 & 0xFFFF) + n29) {
                switch (n33) {
                    case 0: {
                        final int n81 = array[n42] & 0xFF;
                        n42 += n80 >>> 16;
                        n69 = (array4[(n81 & n9) >>> channelShift] & 0xFF);
                        n70 = (array5[(n81 & n10) >>> channelShift2] & 0xFF);
                        n71 = (array6[(n81 & n11) >>> channelShift3] & 0xFF);
                        n72 = (array7[(n81 & n26) >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 1: {
                        final int n82 = (array[n42] & 0xFF) << 8 | (array[n42 + 1] & 0xFF);
                        n42 += (n80 >>> 16) * 2;
                        n69 = (array4[(n82 & n9) >>> channelShift] & 0xFF);
                        n70 = (array5[(n82 & n10) >>> channelShift2] & 0xFF);
                        n71 = (array6[(n82 & n11) >>> channelShift3] & 0xFF);
                        n72 = (array7[(n82 & n26) >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 2: {
                        final int n83 = (array[n42 + 1] & 0xFF) << 8 | (array[n42] & 0xFF);
                        n42 += (n80 >>> 16) * 2;
                        n69 = (array4[(n83 & n9) >>> channelShift] & 0xFF);
                        n70 = (array5[(n83 & n10) >>> channelShift2] & 0xFF);
                        n71 = (array6[(n83 & n11) >>> channelShift3] & 0xFF);
                        n72 = (array7[(n83 & n26) >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 3: {
                        final int n84 = ((array[n42] & 0xFF) << 8 | (array[n42 + 1] & 0xFF)) << 8 | (array[n42 + 2] & 0xFF);
                        n42 += (n80 >>> 16) * 3;
                        n69 = (array4[(n84 & n9) >>> channelShift] & 0xFF);
                        n70 = (array5[(n84 & n10) >>> channelShift2] & 0xFF);
                        n71 = (array6[(n84 & n11) >>> channelShift3] & 0xFF);
                        n72 = (array7[(n84 & n26) >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 4: {
                        final int n85 = (((array[n42] & 0xFF) << 8 | (array[n42 + 1] & 0xFF)) << 8 | (array[n42 + 2] & 0xFF)) << 8 | (array[n42 + 3] & 0xFF);
                        n42 += (n80 >>> 16) * 4;
                        n69 = (array4[(n85 & n9) >>> channelShift] & 0xFF);
                        n70 = (array5[(n85 & n10) >>> channelShift2] & 0xFF);
                        n71 = (array6[(n85 & n11) >>> channelShift3] & 0xFF);
                        n72 = (array7[(n85 & n26) >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 5: {
                        final int n86 = (((array[n42 + 3] & 0xFF) << 8 | (array[n42 + 2] & 0xFF)) << 8 | (array[n42 + 1] & 0xFF)) << 8 | (array[n42] & 0xFF);
                        n42 += (n80 >>> 16) * 4;
                        n69 = (array4[(n86 & n9) >>> channelShift] & 0xFF);
                        n70 = (array5[(n86 & n10) >>> channelShift2] & 0xFF);
                        n71 = (array6[(n86 & n11) >>> channelShift3] & 0xFF);
                        n72 = (array7[(n86 & n26) >>> channelShift4] & 0xFF);
                        break;
                    }
                }
                switch (n12) {
                    case -1: {
                        n68 = ((array2[n67] & 0xFF) << 16) / 255;
                        n67 += n80 >> 16;
                        break;
                    }
                    case -2: {
                        n68 = (n72 << 16) / 255;
                        break;
                    }
                    case -3: {
                        n68 = ((array2[n67] != 0) ? 65536 : 0);
                        n67 += n80 >> 16;
                        break;
                    }
                    case -4: {
                        n68 = (array2[n67 >> 3] << (n67 & 0x7) + 9 & 0x10000);
                        n67 += n80 >> 16;
                        break;
                    }
                    case -6: {
                        n68 = 65536;
                        for (int n87 = 0; n87 < array2.length; n87 += 3) {
                            if (n69 == array2[n87] && n70 == array2[n87 + 1] && n71 == array2[n87 + 2]) {
                                n68 = 0;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (n68 != 65536) {
                    if (n68 == 0) {
                        continue;
                    }
                    switch (n36) {
                        case 0: {
                            final int n88 = array3[n41] & 0xFF;
                            n73 = (array8[(n88 & n23) >>> channelShift5] & 0xFF);
                            n74 = (array9[(n88 & n24) >>> channelShift6] & 0xFF);
                            n75 = (array10[(n88 & n25) >>> channelShift7] & 0xFF);
                            n76 = (array11[(n88 & n27) >>> channelShift8] & 0xFF);
                            break;
                        }
                        case 1: {
                            final int n89 = (array3[n41] & 0xFF) << 8 | (array3[n41 + 1] & 0xFF);
                            n73 = (array8[(n89 & n23) >>> channelShift5] & 0xFF);
                            n74 = (array9[(n89 & n24) >>> channelShift6] & 0xFF);
                            n75 = (array10[(n89 & n25) >>> channelShift7] & 0xFF);
                            n76 = (array11[(n89 & n27) >>> channelShift8] & 0xFF);
                            break;
                        }
                        case 2: {
                            final int n90 = (array3[n41 + 1] & 0xFF) << 8 | (array3[n41] & 0xFF);
                            n73 = (array8[(n90 & n23) >>> channelShift5] & 0xFF);
                            n74 = (array9[(n90 & n24) >>> channelShift6] & 0xFF);
                            n75 = (array10[(n90 & n25) >>> channelShift7] & 0xFF);
                            n76 = (array11[(n90 & n27) >>> channelShift8] & 0xFF);
                            break;
                        }
                        case 3: {
                            final int n91 = ((array3[n41] & 0xFF) << 8 | (array3[n41 + 1] & 0xFF)) << 8 | (array3[n41 + 2] & 0xFF);
                            n73 = (array8[(n91 & n23) >>> channelShift5] & 0xFF);
                            n74 = (array9[(n91 & n24) >>> channelShift6] & 0xFF);
                            n75 = (array10[(n91 & n25) >>> channelShift7] & 0xFF);
                            n76 = (array11[(n91 & n27) >>> channelShift8] & 0xFF);
                            break;
                        }
                        case 4: {
                            final int n92 = (((array3[n41] & 0xFF) << 8 | (array3[n41 + 1] & 0xFF)) << 8 | (array3[n41 + 2] & 0xFF)) << 8 | (array3[n41 + 3] & 0xFF);
                            n73 = (array8[(n92 & n23) >>> channelShift5] & 0xFF);
                            n74 = (array9[(n92 & n24) >>> channelShift6] & 0xFF);
                            n75 = (array10[(n92 & n25) >>> channelShift7] & 0xFF);
                            n76 = (array11[(n92 & n27) >>> channelShift8] & 0xFF);
                            break;
                        }
                        case 5: {
                            final int n93 = (((array3[n41 + 3] & 0xFF) << 8 | (array3[n41 + 2] & 0xFF)) << 8 | (array3[n41 + 1] & 0xFF)) << 8 | (array3[n41] & 0xFF);
                            n73 = (array8[(n93 & n23) >>> channelShift5] & 0xFF);
                            n74 = (array9[(n93 & n24) >>> channelShift6] & 0xFF);
                            n75 = (array10[(n93 & n25) >>> channelShift7] & 0xFF);
                            n76 = (array11[(n93 & n27) >>> channelShift8] & 0xFF);
                            break;
                        }
                    }
                    n72 = n76 + ((n72 - n76) * n68 >> 16);
                    n69 = n73 + ((n69 - n73) * n68 >> 16);
                    n70 = n74 + ((n70 - n74) * n68 >> 16);
                    n71 = n75 + ((n71 - n75) * n68 >> 16);
                }
                final int n94 = n69 >>> n63 << channelShift5 | n70 >>> n64 << channelShift6 | n71 >>> n65 << channelShift7 | n72 >>> n66 << channelShift8;
                switch (n36) {
                    case 0: {
                        array3[n41] = (byte)n94;
                        break;
                    }
                    case 1: {
                        array3[n41] = (byte)(n94 >>> 8);
                        array3[n41 + 1] = (byte)(n94 & 0xFF);
                        break;
                    }
                    case 2: {
                        array3[n41] = (byte)(n94 & 0xFF);
                        array3[n41 + 1] = (byte)(n94 >>> 8);
                        break;
                    }
                    case 3: {
                        array3[n41] = (byte)(n94 >>> 16);
                        array3[n41 + 1] = (byte)(n94 >>> 8);
                        array3[n41 + 2] = (byte)(n94 & 0xFF);
                        break;
                    }
                    case 4: {
                        array3[n41] = (byte)(n94 >>> 24);
                        array3[n41 + 1] = (byte)(n94 >>> 16);
                        array3[n41 + 2] = (byte)(n94 >>> 8);
                        array3[n41 + 3] = (byte)(n94 & 0xFF);
                        break;
                    }
                    case 5: {
                        array3[n41] = (byte)(n94 & 0xFF);
                        array3[n41 + 1] = (byte)(n94 >>> 8);
                        array3[n41 + 2] = (byte)(n94 >>> 16);
                        array3[n41 + 3] = (byte)(n94 >>> 24);
                        break;
                    }
                }
            }
        }
    }
    
    static void blit(final int n, final byte[] array, final int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final byte[] array2, final byte[] array3, final byte[] array4, int n9, final byte[] array5, int n10, final int n11, final int n12, final byte[] array6, final int n13, int n14, final int n15, final int n16, final int n17, final int n18, final int n19, final byte[] array7, final byte[] array8, final byte[] array9, final boolean b, final boolean b2) {
        if (n18 <= 0 || n19 <= 0 || n9 == 0) {
            return;
        }
        final int n20 = n18 - 1;
        final int n21 = (n20 != 0) ? ((int)(((n7 << 16) - 1L) / n20)) : 0;
        final int n22 = n19 - 1;
        final int n23 = (n22 != 0) ? ((int)(((n8 << 16) - 1L) / n22)) : 0;
        int n24 = 0;
        switch (n2) {
            case 8: {
                n24 = 6;
                break;
            }
            case 4: {
                n3 <<= 1;
                n24 = 7;
                break;
            }
            case 2: {
                n3 <<= 2;
                n24 = 8;
                break;
            }
            case 1: {
                n3 <<= 3;
                n24 = ((n4 == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int n25 = n6 * n3 + n5;
        int n26 = 0;
        switch (n13) {
            case 8: {
                n26 = 6;
                break;
            }
            case 4: {
                n14 <<= 1;
                n26 = 7;
                break;
            }
            case 2: {
                n14 <<= 2;
                n26 = 8;
                break;
            }
            case 1: {
                n14 <<= 3;
                n26 = ((n15 == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int n27 = (b2 ? (n17 + n22) : n17) * n14 + (b ? (n16 + n20) : n16);
        final int n28 = b ? -1 : 1;
        final int n29 = b2 ? (-n14) : n14;
        int n30 = 0;
        if ((n & 0x2) != 0x0) {
            switch (n9) {
                case -3:
                case -1: {
                    if (array5 == null) {
                        n9 = 65536;
                    }
                    n30 = n12 * n10 + n11;
                    break;
                }
                case -4: {
                    if (array5 == null) {
                        n9 = 65536;
                    }
                    n10 <<= 3;
                    n30 = n12 * n10 + n11;
                    break;
                }
                case -6:
                case -5: {
                    if (array5 == null) {
                        n9 = 65536;
                    }
                    n30 = 0;
                    break;
                }
                default: {
                    n9 = (n9 << 16) / 255;
                }
                case -2: {
                    n30 = 0;
                    break;
                }
            }
        }
        else {
            n9 = 65536;
            n30 = 0;
        }
        final boolean b3 = (n & 0x4) != 0x0;
        int n31 = n27;
        int n32 = n25;
        int n33 = n30;
        int length = 1 << n13;
        if (array7 != null && array7.length < length) {
            length = array7.length;
        }
        byte[] array10 = null;
        boolean b4 = true;
        Label_0709: {
            switch (n9) {
                case 65536: {
                    if (n24 == n26 && array2 == array7 && array3 == array8 && array4 == array9) {
                        array10 = ImageData.ONE_TO_ONE_MAPPING;
                        break;
                    }
                    if (array2 != null && array7 != null) {
                        break Label_0709;
                    }
                    if (n2 <= n13) {
                        array10 = ImageData.ONE_TO_ONE_MAPPING;
                        break;
                    }
                    array10 = new byte[1 << n2];
                    final int n34 = 255 << n13 >>> 8;
                    for (int i = 0; i < array10.length; ++i) {
                        array10[i] = (byte)(i & n34);
                    }
                    break;
                }
                case -6:
                case -5:
                case -4:
                case -3: {
                    int length2 = 1 << n2;
                    array10 = new byte[length2];
                    if (array2 != null && array2.length < length2) {
                        length2 = array2.length;
                    }
                    for (int j = 0; j < length2; ++j) {
                        final int n35 = array2[j] & 0xFF;
                        final int n36 = array3[j] & 0xFF;
                        final int n37 = array4[j] & 0xFF;
                        int n38 = 0;
                        int n39 = Integer.MAX_VALUE;
                        for (int k = 0; k < length; ++k) {
                            final int n40 = (array7[k] & 0xFF) - n35;
                            final int n41 = (array8[k] & 0xFF) - n36;
                            final int n42 = (array9[k] & 0xFF) - n37;
                            final int n43 = n40 * n40 + n41 * n41 + n42 * n42;
                            if (n43 < n39) {
                                n38 = k;
                                if (n43 == 0) {
                                    break;
                                }
                                n39 = n43;
                            }
                        }
                        array10[j] = (byte)n38;
                        if (n39 != 0) {
                            b4 = false;
                        }
                    }
                    break;
                }
            }
        }
        if (array10 != null && (b4 || !b3)) {
            if (n24 == n26 && n9 == 65536) {
                switch (n24) {
                    case 6: {
                        for (int l = n19, n44 = n23; l > 0; --l, n25 = (n32 = n25 + (n44 >>> 16) * n3), n44 = (n44 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
                            for (int n45 = n18, n46 = n21; n45 > 0; --n45, n31 += n28, n46 = (n46 & 0xFFFF) + n21) {
                                array6[n31] = array10[array[n32] & 0xFF];
                                n32 += n46 >>> 16;
                            }
                        }
                        break;
                    }
                    case 7: {
                        for (int n47 = n19, n48 = n23; n47 > 0; --n47, n25 = (n32 = n25 + (n48 >>> 16) * n3), n48 = (n48 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
                            for (int n49 = n18, n50 = n21; n49 > 0; --n49, n31 += n28, n50 = (n50 & 0xFFFF) + n21) {
                                int n51;
                                if ((n32 & 0x1) != 0x0) {
                                    n51 = array10[array[n32 >> 1] & 0xF];
                                }
                                else {
                                    n51 = (array[n32 >> 1] >>> 4 & 0xF);
                                }
                                n32 += n50 >>> 16;
                                if ((n31 & 0x1) != 0x0) {
                                    array6[n31 >> 1] = (byte)((array6[n31 >> 1] & 0xF0) | n51);
                                }
                                else {
                                    array6[n31 >> 1] = (byte)((array6[n31 >> 1] & 0xF) | n51 << 4);
                                }
                            }
                        }
                        break;
                    }
                    case 8: {
                        for (int n52 = n19, n53 = n23; n52 > 0; --n52, n25 = (n32 = n25 + (n53 >>> 16) * n3), n53 = (n53 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
                            for (int n54 = n18, n55 = n21; n54 > 0; --n54, n31 += n28, n55 = (n55 & 0xFFFF) + n21) {
                                final byte b5 = array10[array[n32 >> 2] >>> 6 - (n32 & 0x3) * 2 & 0x3];
                                n32 += n55 >>> 16;
                                final int n56 = 6 - (n31 & 0x3) * 2;
                                array6[n31 >> 2] = (byte)((array6[n31 >> 2] & ~(3 << n56)) | b5 << n56);
                            }
                        }
                        break;
                    }
                    case 9: {
                        for (int n57 = n19, n58 = n23; n57 > 0; --n57, n25 = (n32 = n25 + (n58 >>> 16) * n3), n58 = (n58 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
                            for (int n59 = n18, n60 = n21; n59 > 0; --n59, n31 += n28, n60 = (n60 & 0xFFFF) + n21) {
                                final byte b6 = array10[array[n32 >> 3] >>> 7 - (n32 & 0x7) & 0x1];
                                n32 += n60 >>> 16;
                                final int n61 = 7 - (n31 & 0x7);
                                array6[n31 >> 3] = (byte)((array6[n31 >> 3] & ~(1 << n61)) | b6 << n61);
                            }
                        }
                        break;
                    }
                    case 10: {
                        for (int n62 = n19, n63 = n23; n62 > 0; --n62, n25 = (n32 = n25 + (n63 >>> 16) * n3), n63 = (n63 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
                            for (int n64 = n18, n65 = n21; n64 > 0; --n64, n31 += n28, n65 = (n65 & 0xFFFF) + n21) {
                                final byte b7 = array10[array[n32 >> 3] >>> (n32 & 0x7) & 0x1];
                                n32 += n65 >>> 16;
                                final int n66 = n31 & 0x7;
                                array6[n31 >> 3] = (byte)((array6[n31 >> 3] & ~(1 << n66)) | b7 << n66);
                            }
                        }
                        break;
                    }
                }
            }
            else {
                for (int n67 = n19, n68 = n23; n67 > 0; --n67, n25 = (n32 = n25 + (n68 >>> 16) * n3), n68 = (n68 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
                    for (int n69 = n18, n70 = n21; n69 > 0; --n69, n31 += n28, n70 = (n70 & 0xFFFF) + n21) {
                        int n71 = 0;
                        switch (n24) {
                            case 6: {
                                n71 = (array[n32] & 0xFF);
                                n32 += n70 >>> 16;
                                break;
                            }
                            case 7: {
                                if ((n32 & 0x1) != 0x0) {
                                    n71 = (array[n32 >> 1] & 0xF);
                                }
                                else {
                                    n71 = (array[n32 >> 1] >>> 4 & 0xF);
                                }
                                n32 += n70 >>> 16;
                                break;
                            }
                            case 8: {
                                n71 = (array[n32 >> 2] >>> 6 - (n32 & 0x3) * 2 & 0x3);
                                n32 += n70 >>> 16;
                                break;
                            }
                            case 9: {
                                n71 = (array[n32 >> 3] >>> 7 - (n32 & 0x7) & 0x1);
                                n32 += n70 >>> 16;
                                break;
                            }
                            case 10: {
                                n71 = (array[n32 >> 3] >>> (n32 & 0x7) & 0x1);
                                n32 += n70 >>> 16;
                                break;
                            }
                            default: {
                                return;
                            }
                        }
                        switch (n9) {
                            case -3: {
                                final byte b8 = array5[n33];
                                n33 += n70 >> 16;
                                if (b8 == 0) {
                                    continue;
                                }
                                break;
                            }
                            case -4: {
                                final int n72 = array5[n33 >> 3] & 1 << (n33 & 0x7);
                                n33 += n70 >> 16;
                                if (n72 == 0) {
                                    continue;
                                }
                                break;
                            }
                            case -5: {
                                final int n73 = 0;
                                while (n73 < array5.length && n71 != (array5[n73] & 0xFF)) {}
                                if (n73 < array5.length) {
                                    continue;
                                }
                                break;
                            }
                            case -6: {
                                byte b9;
                                byte b10;
                                byte b11;
                                int n74;
                                for (b9 = array2[n71], b10 = array3[n71], b11 = array4[n71], n74 = 0; n74 < array5.length && (b9 != array5[n74] || b10 != array5[n74 + 1] || b11 != array5[n74 + 2]); n74 += 3) {}
                                if (n74 < array5.length) {
                                    continue;
                                }
                                break;
                            }
                        }
                        final int n75 = array10[n71] & 0xFF;
                        switch (n26) {
                            case 6: {
                                array6[n31] = (byte)n75;
                                break;
                            }
                            case 7: {
                                if ((n31 & 0x1) != 0x0) {
                                    array6[n31 >> 1] = (byte)((array6[n31 >> 1] & 0xF0) | n75);
                                    break;
                                }
                                array6[n31 >> 1] = (byte)((array6[n31 >> 1] & 0xF) | n75 << 4);
                                break;
                            }
                            case 8: {
                                final int n76 = 6 - (n31 & 0x3) * 2;
                                array6[n31 >> 2] = (byte)((array6[n31 >> 2] & ~(3 << n76)) | n75 << n76);
                                break;
                            }
                            case 9: {
                                final int n77 = 7 - (n31 & 0x7);
                                array6[n31 >> 3] = (byte)((array6[n31 >> 3] & ~(1 << n77)) | n75 << n77);
                                break;
                            }
                            case 10: {
                                final int n78 = n31 & 0x7;
                                array6[n31 >> 3] = (byte)((array6[n31 >> 3] & ~(1 << n78)) | n75 << n78);
                                break;
                            }
                        }
                    }
                }
            }
            return;
        }
        int n79 = n9;
        int n80 = 0;
        int n81 = 0;
        int n82 = 0;
        int n83 = -1;
        int n84 = -1;
        int n85 = -1;
        int[] array11;
        int[] array12;
        int[] array13;
        if (b3) {
            array11 = new int[n18 + 2];
            array12 = new int[n18 + 2];
            array13 = new int[n18 + 2];
        }
        else {
            array11 = null;
            array12 = null;
            array13 = null;
        }
        for (int n86 = n19, n87 = n23; n86 > 0; --n86, n25 = (n32 = n25 + (n87 >>> 16) * n3), n30 = (n33 = n30 + (n87 >>> 16) * n10), n87 = (n87 & 0xFFFF) + n23, n27 = (n31 = n27 + n29)) {
            int n88 = 0;
            int n89 = 0;
            int n90 = 0;
            for (int n91 = n18, n92 = n21; n91 > 0; --n91, n31 += n28, n92 = (n92 & 0xFFFF) + n21) {
                switch (n24) {
                    case 6: {
                        n80 = (array[n32] & 0xFF);
                        n32 += n92 >>> 16;
                        break;
                    }
                    case 7: {
                        if ((n32 & 0x1) != 0x0) {
                            n80 = (array[n32 >> 1] & 0xF);
                        }
                        else {
                            n80 = (array[n32 >> 1] >>> 4 & 0xF);
                        }
                        n32 += n92 >>> 16;
                        break;
                    }
                    case 8: {
                        n80 = (array[n32 >> 2] >>> 6 - (n32 & 0x3) * 2 & 0x3);
                        n32 += n92 >>> 16;
                        break;
                    }
                    case 9: {
                        n80 = (array[n32 >> 3] >>> 7 - (n32 & 0x7) & 0x1);
                        n32 += n92 >>> 16;
                        break;
                    }
                    case 10: {
                        n80 = (array[n32 >> 3] >>> (n32 & 0x7) & 0x1);
                        n32 += n92 >>> 16;
                        break;
                    }
                }
                int n93 = array2[n80] & 0xFF;
                int n94 = array3[n80] & 0xFF;
                int n95 = array4[n80] & 0xFF;
                switch (n9) {
                    case -1: {
                        n79 = ((array5[n33] & 0xFF) << 16) / 255;
                        n33 += n92 >> 16;
                        break;
                    }
                    case -3: {
                        n79 = ((array5[n33] != 0) ? 65536 : 0);
                        n33 += n92 >> 16;
                        break;
                    }
                    case -4: {
                        n79 = (array5[n33 >> 3] << (n33 & 0x7) + 9 & 0x10000);
                        n33 += n92 >> 16;
                        break;
                    }
                    case -5: {
                        final int n96 = 0;
                        while (n96 < array5.length && n80 != (array5[n96] & 0xFF)) {}
                        if (n96 < array5.length) {
                            continue;
                        }
                        break;
                    }
                    case -6: {
                        int n97;
                        for (n97 = 0; n97 < array5.length && (n93 != (array5[n97] & 0xFF) || n94 != (array5[n97 + 1] & 0xFF) || n95 != (array5[n97 + 2] & 0xFF)); n97 += 3) {}
                        if (n97 < array5.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (n79 != 65536) {
                    if (n79 == 0) {
                        continue;
                    }
                    switch (n26) {
                        case 6: {
                            n81 = (array6[n31] & 0xFF);
                            break;
                        }
                        case 7: {
                            if ((n31 & 0x1) != 0x0) {
                                n81 = (array6[n31 >> 1] & 0xF);
                                break;
                            }
                            n81 = (array6[n31 >> 1] >>> 4 & 0xF);
                            break;
                        }
                        case 8: {
                            n81 = (array6[n31 >> 2] >>> 6 - (n31 & 0x3) * 2 & 0x3);
                            break;
                        }
                        case 9: {
                            n81 = (array6[n31 >> 3] >>> 7 - (n31 & 0x7) & 0x1);
                            break;
                        }
                        case 10: {
                            n81 = (array6[n31 >> 3] >>> (n31 & 0x7) & 0x1);
                            break;
                        }
                    }
                    final int n98 = array7[n81] & 0xFF;
                    final int n99 = array8[n81] & 0xFF;
                    final int n100 = array9[n81] & 0xFF;
                    n93 = n98 + ((n93 - n98) * n79 >> 16);
                    n94 = n99 + ((n94 - n99) * n79 >> 16);
                    n95 = n100 + ((n95 - n100) * n79 >> 16);
                }
                if (b3) {
                    n93 += array11[n91] >> 4;
                    if (n93 < 0) {
                        n93 = 0;
                    }
                    else if (n93 > 255) {
                        n93 = 255;
                    }
                    n94 += array12[n91] >> 4;
                    if (n94 < 0) {
                        n94 = 0;
                    }
                    else if (n94 > 255) {
                        n94 = 255;
                    }
                    n95 += array13[n91] >> 4;
                    if (n95 < 0) {
                        n95 = 0;
                    }
                    else if (n95 > 255) {
                        n95 = 255;
                    }
                    array11[n91] = n88;
                    array12[n91] = n89;
                    array13[n91] = n90;
                }
                if (n93 != n83 || n94 != n84 || n95 != n85) {
                    int n101 = 0;
                    int n102 = Integer.MAX_VALUE;
                    while (n101 < length) {
                        final int n103 = (array7[n101] & 0xFF) - n93;
                        final int n104 = (array8[n101] & 0xFF) - n94;
                        final int n105 = (array9[n101] & 0xFF) - n95;
                        final int n106 = n103 * n103 + n104 * n104 + n105 * n105;
                        if (n106 < n102) {
                            n82 = n101;
                            if (n106 == 0) {
                                break;
                            }
                            n102 = n106;
                        }
                        ++n101;
                    }
                    n83 = n93;
                    n84 = n94;
                    n85 = n95;
                }
                if (b3) {
                    final int n107 = n91 - 1;
                    final int n108 = n91 + 1;
                    final int[] array14 = array11;
                    final int n109 = n108;
                    final int n110;
                    array14[n109] += (n110 = (n88 = n93 - (array7[n82] & 0xFF)) + n88 + n88);
                    final int[] array15 = array11;
                    final int n111 = n91;
                    final int n112;
                    array15[n111] += (n112 = n110 + (n88 + n88));
                    final int[] array16 = array11;
                    final int n113 = n107;
                    array16[n113] += n112 + n88 + n88;
                    final int[] array17 = array12;
                    final int n114 = n108;
                    final int n115;
                    array17[n114] += (n115 = (n89 = n94 - (array8[n82] & 0xFF)) + n89 + n89);
                    final int[] array18 = array12;
                    final int n116 = n91;
                    final int n117;
                    array18[n116] += (n117 = n115 + (n89 + n89));
                    final int[] array19 = array12;
                    final int n118 = n107;
                    array19[n118] += n117 + n89 + n89;
                    final int[] array20 = array13;
                    final int n119 = n108;
                    final int n120;
                    array20[n119] += (n120 = (n90 = n95 - (array9[n82] & 0xFF)) + n90 + n90);
                    final int[] array21 = array13;
                    final int n121 = n91;
                    final int n122;
                    array21[n121] += (n122 = n120 + (n90 + n90));
                    final int[] array22 = array13;
                    final int n123 = n107;
                    array22[n123] += n122 + n90 + n90;
                }
                switch (n26) {
                    case 6: {
                        array6[n31] = (byte)n82;
                        break;
                    }
                    case 7: {
                        if ((n31 & 0x1) != 0x0) {
                            array6[n31 >> 1] = (byte)((array6[n31 >> 1] & 0xF0) | n82);
                            break;
                        }
                        array6[n31 >> 1] = (byte)((array6[n31 >> 1] & 0xF) | n82 << 4);
                        break;
                    }
                    case 8: {
                        final int n124 = 6 - (n31 & 0x3) * 2;
                        array6[n31 >> 2] = (byte)((array6[n31 >> 2] & ~(3 << n124)) | n82 << n124);
                        break;
                    }
                    case 9: {
                        final int n125 = 7 - (n31 & 0x7);
                        array6[n31 >> 3] = (byte)((array6[n31 >> 3] & ~(1 << n125)) | n82 << n125);
                        break;
                    }
                    case 10: {
                        final int n126 = n31 & 0x7;
                        array6[n31 >> 3] = (byte)((array6[n31 >> 3] & ~(1 << n126)) | n82 << n126);
                        break;
                    }
                }
            }
        }
    }
    
    static void blit(final int n, final byte[] array, final int n2, int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final byte[] array2, final byte[] array3, final byte[] array4, int n9, final byte[] array5, int n10, final int n11, final int n12, final byte[] array6, final int n13, final int n14, final int n15, final int n16, final int n17, final int n18, final int n19, final int n20, final int n21, final int n22, final boolean b, final boolean b2) {
        if (n18 <= 0 || n19 <= 0 || n9 == 0) {
            return;
        }
        if (n5 == 0 && n6 == 0 && n16 == 0 && n17 == 0 && n18 == n7 && n19 == n8) {
            if (n13 == 24 && n2 == 8 && (n & 0x2) == 0x0 && n20 == 16711680 && n21 == 65280 && n22 == 255) {
                for (int i = 0, n23 = 0, n24 = 0, n25 = n3 - n7, n26 = n14 - n18 * 3; i < n19; ++i, n23 += n25, n24 += n26) {
                    for (int j = 0; j < n18; ++j) {
                        final int n27 = array[n23++] & 0xFF;
                        array6[n24++] = array2[n27];
                        array6[n24++] = array3[n27];
                        array6[n24++] = array4[n27];
                    }
                }
                return;
            }
            if (n13 == 32 && n15 == 1 && n2 == 8 && (n & 0x2) == 0x0 && n20 == 16711680 && n21 == 65280 && n22 == 255) {
                for (int k = 0, n28 = 0, n29 = 0, n30 = n3 - n7, n31 = n14 - n18 * 4; k < n19; ++k, n28 += n30, n29 += n31) {
                    for (int l = 0; l < n18; ++l) {
                        final int n32 = array[n28++] & 0xFF;
                        ++n29;
                        array6[n29++] = array2[n32];
                        array6[n29++] = array3[n32];
                        array6[n29++] = array4[n32];
                    }
                }
                return;
            }
        }
        final int n33 = n18 - 1;
        final int n34 = (n33 != 0) ? ((int)(((n7 << 16) - 1L) / n33)) : 0;
        final int n35 = n19 - 1;
        final int n36 = (n35 != 0) ? ((int)(((n8 << 16) - 1L) / n35)) : 0;
        int n37 = 0;
        switch (n2) {
            case 8: {
                n37 = 6;
                break;
            }
            case 4: {
                n3 <<= 1;
                n37 = 7;
                break;
            }
            case 2: {
                n3 <<= 2;
                n37 = 8;
                break;
            }
            case 1: {
                n3 <<= 3;
                n37 = ((n4 == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int n38 = n6 * n3 + n5;
        int n39 = 0;
        int n40 = 0;
        switch (n13) {
            case 8: {
                n39 = 1;
                n40 = 0;
                break;
            }
            case 16: {
                n39 = 2;
                n40 = ((n15 == 1) ? 1 : 2);
                break;
            }
            case 24: {
                n39 = 3;
                n40 = 3;
                break;
            }
            case 32: {
                n39 = 4;
                n40 = ((n15 == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int n41 = (b2 ? (n17 + n35) : n17) * n14 + (b ? (n16 + n33) : n16) * n39;
        final int n42 = b ? (-n39) : n39;
        final int n43 = b2 ? (-n14) : n14;
        int n44 = 0;
        if ((n & 0x2) != 0x0) {
            switch (n9) {
                case -3:
                case -1: {
                    if (array5 == null) {
                        n9 = 65536;
                    }
                    n44 = n12 * n10 + n11;
                    break;
                }
                case -4: {
                    if (array5 == null) {
                        n9 = 65536;
                    }
                    n10 <<= 3;
                    n44 = n12 * n10 + n11;
                    break;
                }
                case -6:
                case -5: {
                    if (array5 == null) {
                        n9 = 65536;
                    }
                    n44 = 0;
                    break;
                }
                default: {
                    n9 = (n9 << 16) / 255;
                }
                case -2: {
                    n44 = 0;
                    break;
                }
            }
        }
        else {
            n9 = 65536;
            n44 = 0;
        }
        final int channelShift = getChannelShift(n20);
        final int channelWidth = getChannelWidth(n20, channelShift);
        final byte[] array7 = ImageData.ANY_TO_EIGHT[channelWidth];
        final int n45 = 8 - channelWidth;
        final int channelShift2 = getChannelShift(n21);
        final int channelWidth2 = getChannelWidth(n21, channelShift2);
        final byte[] array8 = ImageData.ANY_TO_EIGHT[channelWidth2];
        final int n46 = 8 - channelWidth2;
        final int channelShift3 = getChannelShift(n22);
        final int channelWidth3 = getChannelWidth(n22, channelShift3);
        final byte[] array9 = ImageData.ANY_TO_EIGHT[channelWidth3];
        final int n47 = 8 - channelWidth3;
        final int channelShift4 = getChannelShift(0);
        final int channelWidth4 = getChannelWidth(0, channelShift4);
        final byte[] array10 = ImageData.ANY_TO_EIGHT[channelWidth4];
        final int n48 = 8 - channelWidth4;
        int n49 = n41;
        int n50 = n38;
        int n51 = n44;
        int n52 = n9;
        int n53 = 0;
        int n54 = 0;
        int n55 = 0;
        int n56 = 0;
        int n57 = 0;
        int n58 = 0;
        for (int n59 = n19, n60 = n36; n59 > 0; --n59, n38 = (n50 = n38 + (n60 >>> 16) * n3), n44 = (n51 = n44 + (n60 >>> 16) * n10), n60 = (n60 & 0xFFFF) + n36, n41 = (n49 = n41 + n43)) {
            for (int n61 = n18, n62 = n34; n61 > 0; --n61, n49 += n42, n62 = (n62 & 0xFFFF) + n34) {
                switch (n37) {
                    case 6: {
                        n54 = (array[n50] & 0xFF);
                        n50 += n62 >>> 16;
                        break;
                    }
                    case 7: {
                        if ((n50 & 0x1) != 0x0) {
                            n54 = (array[n50 >> 1] & 0xF);
                        }
                        else {
                            n54 = (array[n50 >> 1] >>> 4 & 0xF);
                        }
                        n50 += n62 >>> 16;
                        break;
                    }
                    case 8: {
                        n54 = (array[n50 >> 2] >>> 6 - (n50 & 0x3) * 2 & 0x3);
                        n50 += n62 >>> 16;
                        break;
                    }
                    case 9: {
                        n54 = (array[n50 >> 3] >>> 7 - (n50 & 0x7) & 0x1);
                        n50 += n62 >>> 16;
                        break;
                    }
                    case 10: {
                        n54 = (array[n50 >> 3] >>> (n50 & 0x7) & 0x1);
                        n50 += n62 >>> 16;
                        break;
                    }
                }
                int n63 = array2[n54] & 0xFF;
                int n64 = array3[n54] & 0xFF;
                int n65 = array4[n54] & 0xFF;
                switch (n9) {
                    case -1: {
                        n52 = ((array5[n51] & 0xFF) << 16) / 255;
                        n51 += n62 >> 16;
                        break;
                    }
                    case -3: {
                        n52 = ((array5[n51] != 0) ? 65536 : 0);
                        n51 += n62 >> 16;
                        break;
                    }
                    case -4: {
                        n52 = (array5[n51 >> 3] << (n51 & 0x7) + 9 & 0x10000);
                        n51 += n62 >> 16;
                        break;
                    }
                    case -5: {
                        final int n66 = 0;
                        while (n66 < array5.length && n54 != (array5[n66] & 0xFF)) {}
                        if (n66 < array5.length) {
                            continue;
                        }
                        break;
                    }
                    case -6: {
                        int n67;
                        for (n67 = 0; n67 < array5.length && (n63 != (array5[n67] & 0xFF) || n64 != (array5[n67 + 1] & 0xFF) || n65 != (array5[n67 + 2] & 0xFF)); n67 += 3) {}
                        if (n67 < array5.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (n52 != 65536) {
                    if (n52 == 0) {
                        continue;
                    }
                    switch (n40) {
                        case 0: {
                            final int n68 = array6[n49] & 0xFF;
                            n55 = (array7[(n68 & n20) >>> channelShift] & 0xFF);
                            n56 = (array8[(n68 & n21) >>> channelShift2] & 0xFF);
                            n57 = (array9[(n68 & n22) >>> channelShift3] & 0xFF);
                            n58 = (array10[0 >>> channelShift4] & 0xFF);
                            break;
                        }
                        case 1: {
                            final int n69 = (array6[n49] & 0xFF) << 8 | (array6[n49 + 1] & 0xFF);
                            n55 = (array7[(n69 & n20) >>> channelShift] & 0xFF);
                            n56 = (array8[(n69 & n21) >>> channelShift2] & 0xFF);
                            n57 = (array9[(n69 & n22) >>> channelShift3] & 0xFF);
                            n58 = (array10[0 >>> channelShift4] & 0xFF);
                            break;
                        }
                        case 2: {
                            final int n70 = (array6[n49 + 1] & 0xFF) << 8 | (array6[n49] & 0xFF);
                            n55 = (array7[(n70 & n20) >>> channelShift] & 0xFF);
                            n56 = (array8[(n70 & n21) >>> channelShift2] & 0xFF);
                            n57 = (array9[(n70 & n22) >>> channelShift3] & 0xFF);
                            n58 = (array10[0 >>> channelShift4] & 0xFF);
                            break;
                        }
                        case 3: {
                            final int n71 = ((array6[n49] & 0xFF) << 8 | (array6[n49 + 1] & 0xFF)) << 8 | (array6[n49 + 2] & 0xFF);
                            n55 = (array7[(n71 & n20) >>> channelShift] & 0xFF);
                            n56 = (array8[(n71 & n21) >>> channelShift2] & 0xFF);
                            n57 = (array9[(n71 & n22) >>> channelShift3] & 0xFF);
                            n58 = (array10[0 >>> channelShift4] & 0xFF);
                            break;
                        }
                        case 4: {
                            final int n72 = (((array6[n49] & 0xFF) << 8 | (array6[n49 + 1] & 0xFF)) << 8 | (array6[n49 + 2] & 0xFF)) << 8 | (array6[n49 + 3] & 0xFF);
                            n55 = (array7[(n72 & n20) >>> channelShift] & 0xFF);
                            n56 = (array8[(n72 & n21) >>> channelShift2] & 0xFF);
                            n57 = (array9[(n72 & n22) >>> channelShift3] & 0xFF);
                            n58 = (array10[0 >>> channelShift4] & 0xFF);
                            break;
                        }
                        case 5: {
                            final int n73 = (((array6[n49 + 3] & 0xFF) << 8 | (array6[n49 + 2] & 0xFF)) << 8 | (array6[n49 + 1] & 0xFF)) << 8 | (array6[n49] & 0xFF);
                            n55 = (array7[(n73 & n20) >>> channelShift] & 0xFF);
                            n56 = (array8[(n73 & n21) >>> channelShift2] & 0xFF);
                            n57 = (array9[(n73 & n22) >>> channelShift3] & 0xFF);
                            n58 = (array10[0 >>> channelShift4] & 0xFF);
                            break;
                        }
                    }
                    n53 = n58 + ((n53 - n58) * n52 >> 16);
                    n63 = n55 + ((n63 - n55) * n52 >> 16);
                    n64 = n56 + ((n64 - n56) * n52 >> 16);
                    n65 = n57 + ((n65 - n57) * n52 >> 16);
                }
                final int n74 = n63 >>> n45 << channelShift | n64 >>> n46 << channelShift2 | n65 >>> n47 << channelShift3 | n53 >>> n48 << channelShift4;
                switch (n40) {
                    case 0: {
                        array6[n49] = (byte)n74;
                        break;
                    }
                    case 1: {
                        array6[n49] = (byte)(n74 >>> 8);
                        array6[n49 + 1] = (byte)(n74 & 0xFF);
                        break;
                    }
                    case 2: {
                        array6[n49] = (byte)(n74 & 0xFF);
                        array6[n49 + 1] = (byte)(n74 >>> 8);
                        break;
                    }
                    case 3: {
                        array6[n49] = (byte)(n74 >>> 16);
                        array6[n49 + 1] = (byte)(n74 >>> 8);
                        array6[n49 + 2] = (byte)(n74 & 0xFF);
                        break;
                    }
                    case 4: {
                        array6[n49] = (byte)(n74 >>> 24);
                        array6[n49 + 1] = (byte)(n74 >>> 16);
                        array6[n49 + 2] = (byte)(n74 >>> 8);
                        array6[n49 + 3] = (byte)(n74 & 0xFF);
                        break;
                    }
                    case 5: {
                        array6[n49] = (byte)(n74 & 0xFF);
                        array6[n49 + 1] = (byte)(n74 >>> 8);
                        array6[n49 + 2] = (byte)(n74 >>> 16);
                        array6[n49 + 3] = (byte)(n74 >>> 24);
                        break;
                    }
                }
            }
        }
    }
    
    static void blit(final int n, final byte[] array, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, int n12, final byte[] array2, int n13, final int n14, final int n15, final byte[] array3, final int n16, int n17, final int n18, final int n19, final int n20, final int n21, final int n22, final byte[] array4, final byte[] array5, final byte[] array6, final boolean b, final boolean b2) {
        if (n21 <= 0 || n22 <= 0 || n12 == 0) {
            return;
        }
        final int n23 = n21 - 1;
        final int n24 = (n23 != 0) ? ((int)(((n7 << 16) - 1L) / n23)) : 0;
        final int n25 = n22 - 1;
        final int n26 = (n25 != 0) ? ((int)(((n8 << 16) - 1L) / n25)) : 0;
        int n27 = 0;
        int n28 = 0;
        switch (n2) {
            case 8: {
                n27 = 1;
                n28 = 0;
                break;
            }
            case 16: {
                n27 = 2;
                n28 = ((n4 == 1) ? 1 : 2);
                break;
            }
            case 24: {
                n27 = 3;
                n28 = 3;
                break;
            }
            case 32: {
                n27 = 4;
                n28 = ((n4 == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int n29 = n6 * n3 + n5 * n27;
        int n30 = 0;
        switch (n16) {
            case 8: {
                n30 = 6;
                break;
            }
            case 4: {
                n17 <<= 1;
                n30 = 7;
                break;
            }
            case 2: {
                n17 <<= 2;
                n30 = 8;
                break;
            }
            case 1: {
                n17 <<= 3;
                n30 = ((n18 == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int n31 = (b2 ? (n20 + n25) : n20) * n17 + (b ? (n19 + n23) : n19);
        final int n32 = b ? -1 : 1;
        final int n33 = b2 ? (-n17) : n17;
        int n34 = 0;
        if ((n & 0x2) != 0x0) {
            switch (n12) {
                case -3:
                case -1: {
                    if (array2 == null) {
                        n12 = 65536;
                    }
                    n34 = n15 * n13 + n14;
                    break;
                }
                case -4: {
                    if (array2 == null) {
                        n12 = 65536;
                    }
                    n13 <<= 3;
                    n34 = n15 * n13 + n14;
                    break;
                }
                case -5: {
                    return;
                }
                case -6: {
                    if (array2 == null) {
                        n12 = 65536;
                    }
                    n34 = 0;
                    break;
                }
                default: {
                    n12 = (n12 << 16) / 255;
                }
                case -2: {
                    n34 = 0;
                    break;
                }
            }
        }
        else {
            n12 = 65536;
            n34 = 0;
        }
        final boolean b3 = (n & 0x4) != 0x0;
        final int channelShift = getChannelShift(n9);
        final byte[] array7 = ImageData.ANY_TO_EIGHT[getChannelWidth(n9, channelShift)];
        final int channelShift2 = getChannelShift(n10);
        final byte[] array8 = ImageData.ANY_TO_EIGHT[getChannelWidth(n10, channelShift2)];
        final int channelShift3 = getChannelShift(n11);
        final byte[] array9 = ImageData.ANY_TO_EIGHT[getChannelWidth(n11, channelShift3)];
        final int channelShift4 = getChannelShift(0);
        final byte[] array10 = ImageData.ANY_TO_EIGHT[getChannelWidth(0, channelShift4)];
        int n35 = n31;
        int n36 = n29;
        int n37 = n34;
        int n38 = n12;
        int n39 = 0;
        int n40 = 0;
        int n41 = 0;
        int n42 = 0;
        int n43 = 0;
        int n44 = 0;
        byte b4 = -1;
        byte b5 = -1;
        byte b6 = -1;
        int length = 1 << n16;
        if (array4 != null && array4.length < length) {
            length = array4.length;
        }
        int[] array11;
        int[] array12;
        int[] array13;
        if (b3) {
            array11 = new int[n21 + 2];
            array12 = new int[n21 + 2];
            array13 = new int[n21 + 2];
        }
        else {
            array11 = null;
            array12 = null;
            array13 = null;
        }
        for (int i = n22, n45 = n26; i > 0; --i, n29 = (n36 = n29 + (n45 >>> 16) * n3), n34 = (n37 = n34 + (n45 >>> 16) * n13), n45 = (n45 & 0xFFFF) + n26, n31 = (n35 = n31 + n33)) {
            int n46 = 0;
            int n47 = 0;
            int n48 = 0;
            for (int j = n21, n49 = n24; j > 0; --j, n35 += n32, n49 = (n49 & 0xFFFF) + n24) {
                switch (n28) {
                    case 0: {
                        final int n50 = array[n36] & 0xFF;
                        n36 += n49 >>> 16;
                        n39 = (array7[(n50 & n9) >>> channelShift] & 0xFF);
                        n40 = (array8[(n50 & n10) >>> channelShift2] & 0xFF);
                        n41 = (array9[(n50 & n11) >>> channelShift3] & 0xFF);
                        n42 = (array10[0 >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 1: {
                        final int n51 = (array[n36] & 0xFF) << 8 | (array[n36 + 1] & 0xFF);
                        n36 += (n49 >>> 16) * 2;
                        n39 = (array7[(n51 & n9) >>> channelShift] & 0xFF);
                        n40 = (array8[(n51 & n10) >>> channelShift2] & 0xFF);
                        n41 = (array9[(n51 & n11) >>> channelShift3] & 0xFF);
                        n42 = (array10[0 >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 2: {
                        final int n52 = (array[n36 + 1] & 0xFF) << 8 | (array[n36] & 0xFF);
                        n36 += (n49 >>> 16) * 2;
                        n39 = (array7[(n52 & n9) >>> channelShift] & 0xFF);
                        n40 = (array8[(n52 & n10) >>> channelShift2] & 0xFF);
                        n41 = (array9[(n52 & n11) >>> channelShift3] & 0xFF);
                        n42 = (array10[0 >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 3: {
                        final int n53 = ((array[n36] & 0xFF) << 8 | (array[n36 + 1] & 0xFF)) << 8 | (array[n36 + 2] & 0xFF);
                        n36 += (n49 >>> 16) * 3;
                        n39 = (array7[(n53 & n9) >>> channelShift] & 0xFF);
                        n40 = (array8[(n53 & n10) >>> channelShift2] & 0xFF);
                        n41 = (array9[(n53 & n11) >>> channelShift3] & 0xFF);
                        n42 = (array10[0 >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 4: {
                        final int n54 = (((array[n36] & 0xFF) << 8 | (array[n36 + 1] & 0xFF)) << 8 | (array[n36 + 2] & 0xFF)) << 8 | (array[n36 + 3] & 0xFF);
                        n36 += (n49 >>> 16) * 4;
                        n39 = (array7[(n54 & n9) >>> channelShift] & 0xFF);
                        n40 = (array8[(n54 & n10) >>> channelShift2] & 0xFF);
                        n41 = (array9[(n54 & n11) >>> channelShift3] & 0xFF);
                        n42 = (array10[0 >>> channelShift4] & 0xFF);
                        break;
                    }
                    case 5: {
                        final int n55 = (((array[n36 + 3] & 0xFF) << 8 | (array[n36 + 2] & 0xFF)) << 8 | (array[n36 + 1] & 0xFF)) << 8 | (array[n36] & 0xFF);
                        n36 += (n49 >>> 16) * 4;
                        n39 = (array7[(n55 & n9) >>> channelShift] & 0xFF);
                        n40 = (array8[(n55 & n10) >>> channelShift2] & 0xFF);
                        n41 = (array9[(n55 & n11) >>> channelShift3] & 0xFF);
                        n42 = (array10[0 >>> channelShift4] & 0xFF);
                        break;
                    }
                }
                switch (n12) {
                    case -1: {
                        n38 = ((array2[n37] & 0xFF) << 16) / 255;
                        n37 += n49 >> 16;
                        break;
                    }
                    case -2: {
                        n38 = (n42 << 16) / 255;
                        break;
                    }
                    case -3: {
                        n38 = ((array2[n37] != 0) ? 65536 : 0);
                        n37 += n49 >> 16;
                        break;
                    }
                    case -4: {
                        n38 = (array2[n37 >> 3] << (n37 & 0x7) + 9 & 0x10000);
                        n37 += n49 >> 16;
                        break;
                    }
                    case -6: {
                        n38 = 65536;
                        for (int k = 0; k < array2.length; k += 3) {
                            if (n39 == array2[k] && n40 == array2[k + 1] && n41 == array2[k + 2]) {
                                n38 = 0;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (n38 != 65536) {
                    if (n38 == 0) {
                        continue;
                    }
                    switch (n30) {
                        case 6: {
                            n43 = (array3[n35] & 0xFF);
                            break;
                        }
                        case 7: {
                            if ((n35 & 0x1) != 0x0) {
                                n43 = (array3[n35 >> 1] & 0xF);
                                break;
                            }
                            n43 = (array3[n35 >> 1] >>> 4 & 0xF);
                            break;
                        }
                        case 8: {
                            n43 = (array3[n35 >> 2] >>> 6 - (n35 & 0x3) * 2 & 0x3);
                            break;
                        }
                        case 9: {
                            n43 = (array3[n35 >> 3] >>> 7 - (n35 & 0x7) & 0x1);
                            break;
                        }
                        case 10: {
                            n43 = (array3[n35 >> 3] >>> (n35 & 0x7) & 0x1);
                            break;
                        }
                    }
                    final int n56 = array4[n43] & 0xFF;
                    final int n57 = array5[n43] & 0xFF;
                    final int n58 = array6[n43] & 0xFF;
                    n39 = n56 + ((n39 - n56) * n38 >> 16);
                    n40 = n57 + ((n40 - n57) * n38 >> 16);
                    n41 = n58 + ((n41 - n58) * n38 >> 16);
                }
                if (b3) {
                    n39 += array11[j] >> 4;
                    if (n39 < 0) {
                        n39 = 0;
                    }
                    else if (n39 > 255) {
                        n39 = 255;
                    }
                    n40 += array12[j] >> 4;
                    if (n40 < 0) {
                        n40 = 0;
                    }
                    else if (n40 > 255) {
                        n40 = 255;
                    }
                    n41 += array13[j] >> 4;
                    if (n41 < 0) {
                        n41 = 0;
                    }
                    else if (n41 > 255) {
                        n41 = 255;
                    }
                    array11[j] = n46;
                    array12[j] = n47;
                    array13[j] = n48;
                }
                if (n39 != b4 || n40 != b5 || n41 != b6) {
                    int l = 0;
                    int n59 = Integer.MAX_VALUE;
                    while (l < length) {
                        final int n60 = (array4[l] & 0xFF) - n39;
                        final int n61 = (array5[l] & 0xFF) - n40;
                        final int n62 = (array6[l] & 0xFF) - n41;
                        final int n63 = n60 * n60 + n61 * n61 + n62 * n62;
                        if (n63 < n59) {
                            n44 = l;
                            if (n63 == 0) {
                                break;
                            }
                            n59 = n63;
                        }
                        ++l;
                    }
                    b4 = (byte)n39;
                    b5 = (byte)n40;
                    b6 = (byte)n41;
                }
                if (b3) {
                    final int n64 = j - 1;
                    final int n65 = j + 1;
                    final int[] array14 = array11;
                    final int n66 = n65;
                    final int n67;
                    array14[n66] += (n67 = (n46 = n39 - (array4[n44] & 0xFF)) + n46 + n46);
                    final int[] array15 = array11;
                    final int n68 = j;
                    final int n69;
                    array15[n68] += (n69 = n67 + (n46 + n46));
                    final int[] array16 = array11;
                    final int n70 = n64;
                    array16[n70] += n69 + n46 + n46;
                    final int[] array17 = array12;
                    final int n71 = n65;
                    final int n72;
                    array17[n71] += (n72 = (n47 = n40 - (array5[n44] & 0xFF)) + n47 + n47);
                    final int[] array18 = array12;
                    final int n73 = j;
                    final int n74;
                    array18[n73] += (n74 = n72 + (n47 + n47));
                    final int[] array19 = array12;
                    final int n75 = n64;
                    array19[n75] += n74 + n47 + n47;
                    final int[] array20 = array13;
                    final int n76 = n65;
                    final int n77;
                    array20[n76] += (n77 = (n48 = n41 - (array6[n44] & 0xFF)) + n48 + n48);
                    final int[] array21 = array13;
                    final int n78 = j;
                    final int n79;
                    array21[n78] += (n79 = n77 + (n48 + n48));
                    final int[] array22 = array13;
                    final int n80 = n64;
                    array22[n80] += n79 + n48 + n48;
                }
                switch (n30) {
                    case 6: {
                        array3[n35] = (byte)n44;
                        break;
                    }
                    case 7: {
                        if ((n35 & 0x1) != 0x0) {
                            array3[n35 >> 1] = (byte)((array3[n35 >> 1] & 0xF0) | n44);
                            break;
                        }
                        array3[n35 >> 1] = (byte)((array3[n35 >> 1] & 0xF) | n44 << 4);
                        break;
                    }
                    case 8: {
                        final int n81 = 6 - (n35 & 0x3) * 2;
                        array3[n35 >> 2] = (byte)((array3[n35 >> 2] & ~(3 << n81)) | n44 << n81);
                        break;
                    }
                    case 9: {
                        final int n82 = 7 - (n35 & 0x7);
                        array3[n35 >> 3] = (byte)((array3[n35 >> 3] & ~(1 << n82)) | n44 << n82);
                        break;
                    }
                    case 10: {
                        final int n83 = n35 & 0x7;
                        array3[n35 >> 3] = (byte)((array3[n35 >> 3] & ~(1 << n83)) | n44 << n83);
                        break;
                    }
                }
            }
        }
    }
    
    static int getChannelShift(int n) {
        if (n == 0) {
            return 0;
        }
        int n2;
        for (n2 = 0; (n & 0x1) == 0x0 && n2 < 32; n >>>= 1, ++n2) {}
        return n2;
    }
    
    static int getChannelWidth(int n, final int n2) {
        if (n == 0) {
            return 0;
        }
        int n3;
        for (n >>>= n2, n3 = n2; (n & 0x1) != 0x0 && n3 < 32; n >>>= 1, ++n3) {}
        return n3 - n2;
    }
    
    static byte getChannelField(final int n, final int n2) {
        final int channelShift = getChannelShift(n2);
        return ImageData.ANY_TO_EIGHT[getChannelWidth(n2, channelShift)][(n & n2) >>> channelShift];
    }
    
    static ImageData createGradientBand(final int n, final int n2, final boolean b, final RGB rgb, final RGB rgb2, final int n3, final int n4, final int n5) {
        PaletteData paletteData;
        int n6;
        int n7;
        int n8;
        byte[] array;
        if (n3 != 0 && n4 != 0 && n5 != 0) {
            paletteData = new PaletteData(65280, 16711680, -16777216);
            n6 = 32;
            if (n3 >= 8 && n4 >= 8 && n5 >= 8) {
                int n9;
                if (b) {
                    n7 = 1;
                    n8 = n2;
                    n9 = ((n8 > 1) ? (n8 - 1) : 1);
                }
                else {
                    n7 = n;
                    n8 = 1;
                    n9 = ((n7 > 1) ? (n7 - 1) : 1);
                }
                final int n10 = n7 * 4;
                array = new byte[n8 * n10];
                buildPreciseGradientChannel(rgb.blue, rgb2.blue, n9, n7, n8, b, array, 0, n10);
                buildPreciseGradientChannel(rgb.green, rgb2.green, n9, n7, n8, b, array, 1, n10);
                buildPreciseGradientChannel(rgb.red, rgb2.red, n9, n7, n8, b, array, 2, n10);
            }
            else {
                int n11;
                if (b) {
                    n7 = ((n < 8) ? n : 8);
                    n8 = n2;
                    n11 = ((n8 > 1) ? (n8 - 1) : 1);
                }
                else {
                    n7 = n;
                    n8 = ((n2 < 8) ? n2 : 8);
                    n11 = ((n7 > 1) ? (n7 - 1) : 1);
                }
                final int n12 = n7 * 4;
                array = new byte[n8 * n12];
                buildDitheredGradientChannel(rgb.blue, rgb2.blue, n11, n7, n8, b, array, 0, n12, n5);
                buildDitheredGradientChannel(rgb.green, rgb2.green, n11, n7, n8, b, array, 1, n12, n4);
                buildDitheredGradientChannel(rgb.red, rgb2.red, n11, n7, n8, b, array, 2, n12, n3);
            }
        }
        else {
            paletteData = new PaletteData(new RGB[] { rgb, rgb2 });
            n6 = 8;
            int n13;
            if (b) {
                n7 = ((n < 8) ? n : 8);
                n8 = n2;
                n13 = ((n8 > 1) ? (17039360 / (n8 - 1) + 1) : 1);
            }
            else {
                n7 = n;
                n8 = ((n2 < 8) ? n2 : 8);
                n13 = ((n7 > 1) ? (17039360 / (n7 - 1) + 1) : 1);
            }
            final int n14 = n7 + 3 & 0xFFFFFFFC;
            array = new byte[n8 * n14];
            if (b) {
                for (int i = 0, n15 = 0, n16 = 0; i < n8; ++i, n15 += n13, n16 += n14) {
                    for (int j = 0; j < n7; ++j) {
                        array[n16 + j] = (byte)((n15 + ImageData.DITHER_MATRIX[i & 0x7][j] >= 16777216) ? 1 : 0);
                    }
                }
            }
            else {
                for (int k = 0, n17 = 0; k < n7; ++k, n17 += n13) {
                    for (int l = 0, n18 = k; l < n8; ++l, n18 += n14) {
                        array[n18] = (byte)((n17 + ImageData.DITHER_MATRIX[l][k & 0x7] >= 16777216) ? 1 : 0);
                    }
                }
            }
        }
        return new ImageData(n7, n8, n6, paletteData, 4, array);
    }
    
    static final void buildPreciseGradientChannel(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final byte[] array, int n6, final int n7) {
        int n8 = n << 16;
        final int n9 = ((n2 << 16) - n8) / n3 + 1;
        if (b) {
            for (int i = 0; i < n5; ++i, n6 += n7) {
                array[n6] = (byte)(n8 >>> 16);
                n8 += n9;
            }
        }
        else {
            for (int j = 0; j < n4; ++j, n6 += 4) {
                array[n6] = (byte)(n8 >>> 16);
                n8 += n9;
            }
        }
    }
    
    static final void buildDitheredGradientChannel(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final byte[] array, int n6, final int n7, final int n8) {
        final int n9 = 65280 >>> n8;
        int n10 = n << 16;
        final int n11 = ((n2 << 16) - n10) / n3 + 1;
        if (b) {
            for (int i = 0; i < n5; ++i, n6 += n7) {
                for (int j = 0, n12 = n6; j < n4; ++j, n12 += 4) {
                    final int n13 = n10 + (ImageData.DITHER_MATRIX[i & 0x7][j] >>> n8);
                    if (n13 > 16777215) {
                        array[n12] = -1;
                    }
                    else {
                        array[n12] = (byte)(n13 >>> 16 & n9);
                    }
                }
                n10 += n11;
            }
        }
        else {
            for (int k = 0; k < n4; ++k, n6 += 4) {
                for (int l = 0, n14 = n6; l < n5; ++l, n14 += n7) {
                    final int n15 = n10 + (ImageData.DITHER_MATRIX[l][k & 0x7] >>> n8);
                    if (n15 > 16777215) {
                        array[n14] = -1;
                    }
                    else {
                        array[n14] = (byte)(n15 >>> 16 & n9);
                    }
                }
                n10 += n11;
            }
        }
    }
    
    static void fillGradientRectangle(final GC gc, final Device device, final int n, final int n2, final int n3, final int n4, final boolean b, final RGB rgb, final RGB rgb2, final int n5, final int n6, final int n7) {
        final ImageData gradientBand = createGradientBand(n3, n4, b, rgb, rgb2, n5, n6, n7);
        final Image image = new Image(device, gradientBand);
        if (gradientBand.width == 1 || gradientBand.height == 1) {
            gc.drawImage(image, 0, 0, gradientBand.width, gradientBand.height, n, n2, n3, n4);
        }
        else if (b) {
            for (int i = 0; i < n3; i += gradientBand.width) {
                int width = n3 - i;
                if (width > gradientBand.width) {
                    width = gradientBand.width;
                }
                gc.drawImage(image, 0, 0, width, gradientBand.height, i + n, n2, width, gradientBand.height);
            }
        }
        else {
            for (int j = 0; j < n4; j += gradientBand.height) {
                int height = n4 - j;
                if (height > gradientBand.height) {
                    height = gradientBand.height;
                }
                gc.drawImage(image, 0, 0, gradientBand.width, height, n, j + n2, gradientBand.width, height);
            }
        }
        image.dispose();
    }
}
