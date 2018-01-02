// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.ByteArrayOutputStream;
import org.eclipse.swt.graphics.ImageLoader;
import java.io.OutputStream;
import org.eclipse.swt.graphics.RGB;
import java.io.IOException;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

public final class WinBMPFileFormat extends FileFormat
{
    static final int BMPFileHeaderSize = 14;
    static final int BMPHeaderFixedSize = 40;
    int importantColors;
    Point pelsPerMeter;
    
    public WinBMPFileFormat() {
        this.pelsPerMeter = new Point(0, 0);
    }
    
    int compress(final int n, final byte[] array, final int n2, final int n3, final byte[] array2, final boolean b) {
        if (n == 1) {
            return this.compressRLE8Data(array, n2, n3, array2, b);
        }
        if (n == 2) {
            return this.compressRLE4Data(array, n2, n3, array2, b);
        }
        SWT.error(40);
        return 0;
    }
    
    int compressRLE4Data(final byte[] array, final int n, final int n2, final byte[] array2, final boolean b) {
        int i = n;
        final int n3 = n + n2;
        int n4 = 0;
        int n5;
        int n9 = 0;
        for (n5 = 0; i < n3; i += n9, n5 += 2) {
            int n6 = n3 - i - 1;
            if (n6 > 127) {
                n6 = 127;
            }
            int n7;
            for (n7 = 0; n7 < n6 && array[i + n7] != array[i + n7 + 1]; ++n7) {}
            if (n7 < 127 && n7 == n6) {
                ++n7;
            }
            switch (n7) {
                case 0: {
                    break;
                }
                case 1: {
                    array2[n4] = 2;
                    ++n4;
                    array2[n4] = array[i];
                    ++n4;
                    ++i;
                    n5 += 2;
                    break;
                }
                default: {
                    array2[n4] = 0;
                    ++n4;
                    array2[n4] = (byte)(n7 + n7);
                    ++n4;
                    for (int j = n7; j > 0; --j) {
                        array2[n4] = array[i];
                        ++n4;
                        ++i;
                    }
                    n5 += 2 + n7;
                    if ((n7 & 0x1) != 0x0) {
                        array2[n4] = 0;
                        ++n4;
                        ++n5;
                        break;
                    }
                    break;
                }
            }
            int n8 = n3 - i;
            if (n8 > 0) {
                if (n8 > 127) {
                    n8 = 127;
                }
                byte b2;
                for (b2 = array[i], n9 = 1; n9 < n8 && array[i + n9] == b2; ++n9) {}
                array2[n4] = (byte)(n9 + n9);
                ++n4;
                array2[n4] = b2;
                ++n4;
            }
        }
        array2[n4] = 0;
        ++n4;
        if (b) {
            array2[n4] = 1;
            ++n4;
        }
        else {
            array2[n4] = 0;
            ++n4;
        }
        n5 += 2;
        return n5;
    }
    
    int compressRLE8Data(final byte[] array, final int n, final int n2, final byte[] array2, final boolean b) {
        int i = n;
        final int n3 = n + n2;
        int n4 = 0;
        int n5;
        int n9 = 0;
        for (n5 = 0; i < n3; i += n9, n5 += 2) {
            int n6 = n3 - i - 1;
            if (n6 > 254) {
                n6 = 254;
            }
            int n7;
            for (n7 = 0; n7 < n6 && array[i + n7] != array[i + n7 + 1]; ++n7) {}
            if (n7 == n6) {
                ++n7;
            }
            switch (n7) {
                case 0: {
                    break;
                }
                case 2: {
                    array2[n4] = 1;
                    ++n4;
                    array2[n4] = array[i];
                    ++n4;
                    ++i;
                    n5 += 2;
                }
                case 1: {
                    array2[n4] = 1;
                    ++n4;
                    array2[n4] = array[i];
                    ++n4;
                    ++i;
                    n5 += 2;
                    break;
                }
                default: {
                    array2[n4] = 0;
                    ++n4;
                    array2[n4] = (byte)n7;
                    ++n4;
                    for (int j = n7; j > 0; --j) {
                        array2[n4] = array[i];
                        ++n4;
                        ++i;
                    }
                    n5 += 2 + n7;
                    if ((n7 & 0x1) != 0x0) {
                        array2[n4] = 0;
                        ++n4;
                        ++n5;
                        break;
                    }
                    break;
                }
            }
            int n8 = n3 - i;
            if (n8 > 0) {
                if (n8 > 255) {
                    n8 = 255;
                }
                byte b2;
                for (b2 = array[i], n9 = 1; n9 < n8 && array[i + n9] == b2; ++n9) {}
                array2[n4] = (byte)n9;
                ++n4;
                array2[n4] = b2;
                ++n4;
            }
        }
        array2[n4] = 0;
        ++n4;
        if (b) {
            array2[n4] = 1;
            ++n4;
        }
        else {
            array2[n4] = 0;
            ++n4;
        }
        n5 += 2;
        return n5;
    }
    
    void convertPixelsToBGR(final ImageData imageData, final byte[] array) {
        final byte[] data = imageData.data;
        final PaletteData palette = imageData.palette;
        for (int i = 0; i < imageData.height; ++i) {
            int n = 0;
            int n2 = i;
            final int n3 = imageData.depth / 8;
            int n4 = i * imageData.bytesPerLine;
            for (int j = 0; j < imageData.width; ++j) {
                int n5 = 0;
                switch (imageData.depth) {
                    case 32: {
                        n5 = ((data[n4] & 0xFF) << 24 | (data[n4 + 1] & 0xFF) << 16 | (data[n4 + 2] & 0xFF) << 8 | (data[n4 + 3] & 0xFF));
                        break;
                    }
                    case 24: {
                        n5 = ((data[n4] & 0xFF) << 16 | (data[n4 + 1] & 0xFF) << 8 | (data[n4 + 2] & 0xFF));
                        break;
                    }
                    case 16: {
                        n5 = ((data[n4 + 1] & 0xFF) << 8 | (data[n4] & 0xFF));
                        break;
                    }
                    default: {
                        SWT.error(38);
                        break;
                    }
                }
                if (imageData.depth == 16) {
                    final int n6 = n5 & palette.redMask;
                    final int n7 = (palette.redShift < 0) ? (n6 >>> -palette.redShift) : (n6 << palette.redShift);
                    final int n8 = n5 & palette.greenMask;
                    final int n9 = ((palette.greenShift < 0) ? (n8 >>> -palette.greenShift) : (n8 << palette.greenShift)) & 0xF8;
                    final int n10 = n5 & palette.blueMask;
                    final int n11 = n7 << 7 | n9 << 2 | ((palette.blueShift < 0) ? (n10 >>> -palette.blueShift) : (n10 << palette.blueShift)) >> 3;
                    array[n4] = (byte)(n11 & 0xFF);
                    array[n4 + 1] = (byte)(n11 >> 8 & 0xFF);
                }
                else {
                    final int n12 = n5 & palette.blueMask;
                    array[n4] = (byte)((palette.blueShift < 0) ? (n12 >>> -palette.blueShift) : (n12 << palette.blueShift));
                    final int n13 = n5 & palette.greenMask;
                    array[n4 + 1] = (byte)((palette.greenShift < 0) ? (n13 >>> -palette.greenShift) : (n13 << palette.greenShift));
                    final int n14 = n5 & palette.redMask;
                    array[n4 + 2] = (byte)((palette.redShift < 0) ? (n14 >>> -palette.redShift) : (n14 << palette.redShift));
                    if (n3 == 4) {
                        array[n4 + 3] = 0;
                    }
                }
                if (++n >= imageData.width) {
                    n4 = ++n2 * imageData.bytesPerLine;
                    n = 0;
                }
                else {
                    n4 += n3;
                }
            }
        }
    }
    
    void decompressData(final byte[] array, final byte[] array2, final int n, final int n2) {
        if (n2 == 1) {
            if (this.decompressRLE8Data(array, array.length, n, array2, array2.length) <= 0) {
                SWT.error(40);
            }
            return;
        }
        if (n2 == 2) {
            if (this.decompressRLE4Data(array, array.length, n, array2, array2.length) <= 0) {
                SWT.error(40);
            }
            return;
        }
        SWT.error(40);
    }
    
    int decompressRLE4Data(final byte[] array, final int n, final int n2, final byte[] array2, final int n3) {
        int i = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        while (i < n) {
            final int n7 = array[i] & 0xFF;
            ++i;
            if (n7 == 0) {
                final int n8 = array[i] & 0xFF;
                ++i;
                switch (n8) {
                    case 0: {
                        ++n6;
                        n5 = 0;
                        n4 = n6 * n2;
                        if (n4 > n3) {
                            return -1;
                        }
                        continue;
                    }
                    case 1: {
                        return 1;
                    }
                    case 2: {
                        n5 += (array[i] & 0xFF);
                        ++i;
                        n6 += (array[i] & 0xFF);
                        ++i;
                        n4 = n6 * n2 + n5 / 2;
                        if (n4 > n3) {
                            return -1;
                        }
                        continue;
                    }
                    default: {
                        if ((n8 & 0x1) != 0x0) {
                            return -1;
                        }
                        n5 += n8;
                        final int n9 = n8 / 2;
                        if (n9 > n - i) {
                            return -1;
                        }
                        if (n9 > n3 - n4) {
                            return -1;
                        }
                        for (int j = 0; j < n9; ++j) {
                            array2[n4] = array[i];
                            ++n4;
                            ++i;
                        }
                        if ((i & 0x1) != 0x0) {
                            ++i;
                            continue;
                        }
                        continue;
                    }
                }
            }
            else {
                if ((n7 & 0x1) != 0x0) {
                    return -1;
                }
                n5 += n7;
                final int n10 = n7 / 2;
                final byte b = array[i];
                ++i;
                if (n10 > n3 - n4) {
                    return -1;
                }
                for (int k = 0; k < n10; ++k) {
                    array2[n4] = b;
                    ++n4;
                }
            }
        }
        return 1;
    }
    
    int decompressRLE8Data(final byte[] array, final int n, final int n2, final byte[] array2, final int n3) {
        int i = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        while (i < n) {
            final int n7 = array[i] & 0xFF;
            ++i;
            if (n7 == 0) {
                final int n8 = array[i] & 0xFF;
                ++i;
                switch (n8) {
                    case 0: {
                        ++n6;
                        n5 = 0;
                        n4 = n6 * n2;
                        if (n4 > n3) {
                            return -1;
                        }
                        continue;
                    }
                    case 1: {
                        return 1;
                    }
                    case 2: {
                        n5 += (array[i] & 0xFF);
                        ++i;
                        n6 += (array[i] & 0xFF);
                        ++i;
                        n4 = n6 * n2 + n5;
                        if (n4 > n3) {
                            return -1;
                        }
                        continue;
                    }
                    default: {
                        if (n8 > n - i) {
                            return -1;
                        }
                        if (n8 > n3 - n4) {
                            return -1;
                        }
                        for (int j = 0; j < n8; ++j) {
                            array2[n4] = array[i];
                            ++n4;
                            ++i;
                        }
                        if ((i & 0x1) != 0x0) {
                            ++i;
                        }
                        n5 += n8;
                        continue;
                    }
                }
            }
            else {
                final byte b = array[i];
                ++i;
                if (n7 > n3 - n4) {
                    return -1;
                }
                for (int k = 0; k < n7; ++k) {
                    array2[n4] = b;
                    ++n4;
                }
                n5 += n7;
            }
        }
        return 1;
    }
    
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[18];
            leDataInputStream.read(array);
            leDataInputStream.unread(array);
            final int n = (array[14] & 0xFF) | (array[15] & 0xFF) << 8 | (array[16] & 0xFF) << 16 | (array[17] & 0xFF) << 24;
            return array[0] == 66 && array[1] == 77 && n >= 40;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    boolean isPaletteBMP(final PaletteData paletteData, final int n) {
        switch (n) {
            case 32: {
                return paletteData.redMask == 65280 && paletteData.greenMask == 16711680 && paletteData.blueMask == -16777216;
            }
            case 24: {
                return paletteData.redMask == 255 && paletteData.greenMask == 65280 && paletteData.blueMask == 16711680;
            }
            case 16: {
                return paletteData.redMask == 31744 && paletteData.greenMask == 992 && paletteData.blueMask == 31;
            }
            default: {
                return true;
            }
        }
    }
    
    byte[] loadData(final byte[] array) {
        final int n = (array[4] & 0xFF) | (array[5] & 0xFF) << 8 | (array[6] & 0xFF) << 16 | (array[7] & 0xFF) << 24;
        final int n2 = (array[8] & 0xFF) | (array[9] & 0xFF) << 8 | (array[10] & 0xFF) << 16 | (array[11] & 0xFF) << 24;
        final int n3 = ((n * ((array[14] & 0xFF) | (array[15] & 0xFF) << 8) + 7) / 8 + 3) / 4 * 4;
        final byte[] loadData = this.loadData(array, n3);
        this.flipScanLines(loadData, n3, n2);
        return loadData;
    }
    
    byte[] loadData(final byte[] array, final int n) {
        int n2 = (array[8] & 0xFF) | (array[9] & 0xFF) << 8 | (array[10] & 0xFF) << 16 | (array[11] & 0xFF) << 24;
        if (n2 < 0) {
            n2 = -n2;
        }
        final int n3 = n2 * n;
        final byte[] array2 = new byte[n3];
        final int n4 = (array[16] & 0xFF) | (array[17] & 0xFF) << 8 | (array[18] & 0xFF) << 16 | (array[19] & 0xFF) << 24;
        if (n4 != 0) {
            if (n4 != 3) {
                final int n5 = (array[20] & 0xFF) | (array[21] & 0xFF) << 8 | (array[22] & 0xFF) << 16 | (array[23] & 0xFF) << 24;
                final byte[] array3 = new byte[n5];
                try {
                    if (this.inputStream.read(array3) != n5) {
                        SWT.error(40);
                    }
                }
                catch (IOException ex) {
                    SWT.error(39, ex);
                }
                this.decompressData(array3, array2, n, n4);
                return array2;
            }
        }
        try {
            if (this.inputStream.read(array2) != n3) {
                SWT.error(40);
            }
        }
        catch (IOException ex2) {
            SWT.error(39, ex2);
        }
        return array2;
    }
    
    int[] loadFileHeader() {
        final int[] array = new int[5];
        try {
            array[0] = this.inputStream.readShort();
            array[1] = this.inputStream.readInt();
            array[2] = this.inputStream.readShort();
            array[3] = this.inputStream.readShort();
            array[4] = this.inputStream.readInt();
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        if (array[0] != 19778) {
            SWT.error(40);
        }
        return array;
    }
    
    ImageData[] loadFromByteStream() {
        final int[] loadFileHeader = this.loadFileHeader();
        final byte[] array = new byte[40];
        try {
            this.inputStream.read(array);
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
        final int n = (array[4] & 0xFF) | (array[5] & 0xFF) << 8 | (array[6] & 0xFF) << 16 | (array[7] & 0xFF) << 24;
        int n2 = (array[8] & 0xFF) | (array[9] & 0xFF) << 8 | (array[10] & 0xFF) << 16 | (array[11] & 0xFF) << 24;
        if (n2 < 0) {
            n2 = -n2;
        }
        final int n3 = (array[14] & 0xFF) | (array[15] & 0xFF) << 8;
        this.compression = ((array[16] & 0xFF) | (array[17] & 0xFF) << 8 | (array[18] & 0xFF) << 16 | (array[19] & 0xFF) << 24);
        final PaletteData loadPalette = this.loadPalette(array);
        if (this.inputStream.getPosition() < loadFileHeader[4]) {
            try {
                this.inputStream.skip(loadFileHeader[4] - this.inputStream.getPosition());
            }
            catch (IOException ex2) {
                SWT.error(39, ex2);
            }
        }
        final byte[] loadData = this.loadData(array);
        this.importantColors = ((array[36] & 0xFF) | (array[37] & 0xFF) << 8 | (array[38] & 0xFF) << 16 | (array[39] & 0xFF) << 24);
        this.pelsPerMeter = new Point((array[24] & 0xFF) | (array[25] & 0xFF) << 8 | (array[26] & 0xFF) << 16 | (array[27] & 0xFF) << 24, (array[28] & 0xFF) | (array[29] & 0xFF) << 8 | (array[30] & 0xFF) << 16 | (array[31] & 0xFF) << 24);
        return new ImageData[] { ImageData.internal_new(n, n2, n3, loadPalette, 4, loadData, 0, null, null, -1, -1, (this.compression == 1 || this.compression == 2) ? 1 : 0, 0, 0, 0, 0) };
    }
    
    PaletteData loadPalette(final byte[] array) {
        final int n = (array[14] & 0xFF) | (array[15] & 0xFF) << 8;
        if (n <= 8) {
            int n2 = (array[32] & 0xFF) | (array[33] & 0xFF) << 8 | (array[34] & 0xFF) << 16 | (array[35] & 0xFF) << 24;
            if (n2 == 0) {
                n2 = 1 << n;
            }
            else if (n2 > 256) {
                n2 = 256;
            }
            final byte[] array2 = new byte[n2 * 4];
            try {
                if (this.inputStream.read(array2) != array2.length) {
                    SWT.error(40);
                }
            }
            catch (IOException ex) {
                SWT.error(39, ex);
            }
            return this.paletteFromBytes(array2, n2);
        }
        if (n == 16) {
            if (this.compression == 3) {
                try {
                    return new PaletteData(this.inputStream.readInt(), this.inputStream.readInt(), this.inputStream.readInt());
                }
                catch (IOException ex2) {
                    SWT.error(39, ex2);
                }
            }
            return new PaletteData(31744, 992, 31);
        }
        if (n == 24) {
            return new PaletteData(255, 65280, 16711680);
        }
        if (this.compression == 3) {
            try {
                return new PaletteData(this.inputStream.readInt(), this.inputStream.readInt(), this.inputStream.readInt());
            }
            catch (IOException ex3) {
                SWT.error(39, ex3);
            }
        }
        return new PaletteData(65280, 16711680, -16777216);
    }
    
    PaletteData paletteFromBytes(final byte[] array, final int n) {
        int n2 = 0;
        final RGB[] array2 = new RGB[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = new RGB(array[n2 + 2] & 0xFF, array[n2 + 1] & 0xFF, array[n2] & 0xFF);
            n2 += 4;
        }
        return new PaletteData(array2);
    }
    
    static byte[] paletteToBytes(final PaletteData paletteData) {
        final int n = (paletteData.colors == null) ? 0 : ((paletteData.colors.length < 256) ? paletteData.colors.length : 256);
        final byte[] array = new byte[n * 4];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final RGB rgb = paletteData.colors[i];
            array[n2] = (byte)rgb.blue;
            array[n2 + 1] = (byte)rgb.green;
            array[n2 + 2] = (byte)rgb.red;
            n2 += 4;
        }
        return array;
    }
    
    int unloadData(final ImageData imageData, byte[] data, final OutputStream outputStream, final int n) {
        int n2 = 0;
        try {
            if (n == 0) {
                return this.unloadDataNoCompression(imageData, data, outputStream);
            }
            final int n3 = (imageData.width * imageData.depth + 7) / 8;
            final int n4 = (n3 + 3) / 4 * 4;
            final int bytesPerLine = imageData.bytesPerLine;
            final byte[] array = new byte[n4 * 2];
            int n5 = bytesPerLine * (imageData.height - 1);
            if (data == null) {
                data = imageData.data;
            }
            n2 = 0;
            final byte[] array2 = new byte[32768];
            int n6 = 0;
            for (int i = imageData.height - 1; i >= 0; --i) {
                final int compress = this.compress(n, data, n5, n3, array, i == 0);
                if (n6 + compress > array2.length) {
                    outputStream.write(array2, 0, n6);
                    n6 = 0;
                }
                System.arraycopy(array, 0, array2, n6, compress);
                n6 += compress;
                n2 += compress;
                n5 -= bytesPerLine;
            }
            if (n6 > 0) {
                outputStream.write(array2, 0, n6);
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        return n2;
    }
    
    int unloadDataNoCompression(final ImageData imageData, byte[] data, final OutputStream outputStream) {
        int n = 0;
        try {
            final int n2 = (imageData.width * imageData.depth + 7) / 8;
            n = (n2 + 3) / 4 * 4;
            final int n3 = 32678 / n;
            final byte[] array = new byte[n3 * n];
            if (data == null) {
                data = imageData.data;
            }
            final int bytesPerLine = imageData.bytesPerLine;
            int n4 = bytesPerLine * (imageData.height - 1);
            if (imageData.depth == 16) {
                for (int i = 0; i < imageData.height; i += n3) {
                    int n5 = imageData.height - i;
                    if (n3 < n5) {
                        n5 = n3;
                    }
                    int n6 = 0;
                    for (int j = 0; j < n5; ++j) {
                        for (int k = 0; k < n2; k += 2) {
                            array[n6 + k + 1] = data[n4 + k + 1];
                            array[n6 + k] = data[n4 + k];
                        }
                        n6 += n;
                        n4 -= bytesPerLine;
                    }
                    outputStream.write(array, 0, n6);
                }
            }
            else {
                for (int l = 0; l < imageData.height; l += n3) {
                    final int n7 = imageData.height - l;
                    final int n8 = (n7 < n3) ? n7 : n3;
                    int n9 = 0;
                    for (int n10 = 0; n10 < n8; ++n10) {
                        System.arraycopy(data, n4, array, n9, n2);
                        n9 += n;
                        n4 -= bytesPerLine;
                    }
                    outputStream.write(array, 0, n9);
                }
            }
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        return n * imageData.height;
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        final ImageData imageData = imageLoader.data[0];
        if (imageData.depth != 1 && imageData.depth != 4 && imageData.depth != 8 && imageData.depth != 16 && imageData.depth != 24 && imageData.depth != 32) {
            SWT.error(38);
        }
        final int compression = this.compression;
        if (compression != 0 && (compression != 1 || imageData.depth != 8) && (compression != 2 || imageData.depth != 4)) {
            SWT.error(40);
        }
        final PaletteData palette = imageData.palette;
        int length;
        byte[] paletteToBytes;
        if (imageData.depth == 16 || imageData.depth == 24 || imageData.depth == 32) {
            if (!palette.isDirect) {
                SWT.error(40);
            }
            length = 0;
            paletteToBytes = null;
        }
        else {
            if (palette.isDirect) {
                SWT.error(40);
            }
            length = palette.colors.length;
            paletteToBytes = paletteToBytes(palette);
        }
        final int[] array = { 19778, 0, 0, 0, 54 };
        if (paletteToBytes != null) {
            final int[] array2 = array;
            final int n = 4;
            array2[n] += paletteToBytes.length;
        }
        byte[] array3 = null;
        if (palette.isDirect && !this.isPaletteBMP(palette, imageData.depth)) {
            array3 = new byte[imageData.data.length];
            this.convertPixelsToBGR(imageData, array3);
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.unloadData(imageData, array3, byteArrayOutputStream, compression);
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        array[1] = array[4] + byteArray.length;
        try {
            this.outputStream.writeShort(array[0]);
            this.outputStream.writeInt(array[1]);
            this.outputStream.writeShort(array[2]);
            this.outputStream.writeShort(array[3]);
            this.outputStream.writeInt(array[4]);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        try {
            this.outputStream.writeInt(40);
            this.outputStream.writeInt(imageData.width);
            this.outputStream.writeInt(imageData.height);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort((short)imageData.depth);
            this.outputStream.writeInt(compression);
            this.outputStream.writeInt(byteArray.length);
            this.outputStream.writeInt(this.pelsPerMeter.x);
            this.outputStream.writeInt(this.pelsPerMeter.y);
            this.outputStream.writeInt(length);
            this.outputStream.writeInt(this.importantColors);
        }
        catch (IOException ex2) {
            SWT.error(39, ex2);
        }
        if (length > 0) {
            try {
                this.outputStream.write(paletteToBytes);
            }
            catch (IOException ex3) {
                SWT.error(39, ex3);
            }
        }
        try {
            this.outputStream.write(byteArray);
        }
        catch (IOException ex4) {
            SWT.error(39, ex4);
        }
    }
    
    void flipScanLines(final byte[] array, final int n, final int n2) {
        int n3 = 0;
        int n4 = (n2 - 1) * n;
        for (int i = 0; i < n2 / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                final byte b = array[j + n3];
                array[j + n3] = array[j + n4];
                array[j + n4] = b;
            }
            n3 += n;
            n4 -= n;
        }
    }
}
