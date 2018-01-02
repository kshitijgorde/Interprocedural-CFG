// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.ImageLoader;
import java.io.IOException;
import org.eclipse.swt.graphics.ImageLoaderEvent;
import java.io.InputStream;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;

public final class JPEGFileFormat extends FileFormat
{
    int restartInterval;
    JPEGFrameHeader frameHeader;
    int imageWidth;
    int imageHeight;
    int interleavedMcuCols;
    int interleavedMcuRows;
    int maxV;
    int maxH;
    boolean progressive;
    int samplePrecision;
    int nComponents;
    int[][] frameComponents;
    int[] componentIds;
    byte[][] imageComponents;
    int[] dataUnit;
    int[][][] dataUnits;
    int[] precedingDCs;
    JPEGScanHeader scanHeader;
    byte[] dataBuffer;
    int currentBitCount;
    int bufferCurrentPosition;
    int restartsToGo;
    int nextRestartNumber;
    JPEGHuffmanTable[] acHuffmanTables;
    JPEGHuffmanTable[] dcHuffmanTables;
    int[][] quantizationTables;
    int currentByte;
    int encoderQFactor;
    int eobrun;
    public static final int DCTSIZE = 8;
    public static final int DCTSIZESQR = 64;
    public static final int FIX_0_899976223 = 7373;
    public static final int FIX_1_961570560 = 16069;
    public static final int FIX_2_053119869 = 16819;
    public static final int FIX_0_298631336 = 2446;
    public static final int FIX_1_847759065 = 15137;
    public static final int FIX_1_175875602 = 9633;
    public static final int FIX_3_072711026 = 25172;
    public static final int FIX_0_765366865 = 6270;
    public static final int FIX_2_562915447 = 20995;
    public static final int FIX_0_541196100 = 4433;
    public static final int FIX_0_390180644 = 3196;
    public static final int FIX_1_501321110 = 12299;
    public static final int APP0 = 65504;
    public static final int APP15 = 65519;
    public static final int COM = 65534;
    public static final int DAC = 65484;
    public static final int DHP = 65502;
    public static final int DHT = 65476;
    public static final int DNL = 65500;
    public static final int DRI = 65501;
    public static final int DQT = 65499;
    public static final int EOI = 65497;
    public static final int EXP = 65503;
    public static final int JPG = 65480;
    public static final int JPG0 = 65520;
    public static final int JPG13 = 65533;
    public static final int RST0 = 65488;
    public static final int RST1 = 65489;
    public static final int RST2 = 65490;
    public static final int RST3 = 65491;
    public static final int RST4 = 65492;
    public static final int RST5 = 65493;
    public static final int RST6 = 65494;
    public static final int RST7 = 65495;
    public static final int SOF0 = 65472;
    public static final int SOF1 = 65473;
    public static final int SOF2 = 65474;
    public static final int SOF3 = 65475;
    public static final int SOF5 = 65477;
    public static final int SOF6 = 65478;
    public static final int SOF7 = 65479;
    public static final int SOF9 = 65481;
    public static final int SOF10 = 65482;
    public static final int SOF11 = 65483;
    public static final int SOF13 = 65485;
    public static final int SOF14 = 65486;
    public static final int SOF15 = 65487;
    public static final int SOI = 65496;
    public static final int SOS = 65498;
    public static final int TEM = 65281;
    public static final int TQI = 0;
    public static final int HI = 1;
    public static final int VI = 2;
    public static final int CW = 3;
    public static final int CH = 4;
    public static final int DC = 0;
    public static final int AC = 1;
    public static final int ID_Y = 0;
    public static final int ID_CB = 1;
    public static final int ID_CR = 2;
    public static final RGB[] RGB16;
    public static final int[] ExtendTest;
    public static final int[] ExtendOffset;
    public static final int[] ZigZag8x8;
    public static final int[] CrRTable;
    public static final int[] CbBTable;
    public static final int[] CrGTable;
    public static final int[] CbGTable;
    public static final int[] RYTable;
    public static final int[] GYTable;
    public static final int[] BYTable;
    public static final int[] RCbTable;
    public static final int[] GCbTable;
    public static final int[] BCbTable;
    public static final int[] RCrTable;
    public static final int[] GCrTable;
    public static final int[] BCrTable;
    public static final int[] NBitsTable;
    
    static {
        RGB16 = new RGB[] { new RGB(0, 0, 0), new RGB(128, 0, 0), new RGB(0, 128, 0), new RGB(128, 128, 0), new RGB(0, 0, 128), new RGB(128, 0, 128), new RGB(0, 128, 128), new RGB(192, 192, 192), new RGB(128, 128, 128), new RGB(255, 0, 0), new RGB(0, 255, 0), new RGB(255, 255, 0), new RGB(0, 0, 255), new RGB(255, 0, 255), new RGB(0, 255, 255), new RGB(255, 255, 255) };
        ExtendTest = new int[] { 0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144 };
        ExtendOffset = new int[] { 0, -1, -3, -7, -15, -31, -63, -127, -255, -511, -1023, -2047, -4095, -8191, -16383, -32767, -65535, -131071, -262143 };
        ZigZag8x8 = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
        final int[] ryTable = new int[256];
        final int[] gyTable = new int[256];
        final int[] byTable = new int[256];
        final int[] rCbTable = new int[256];
        final int[] gCbTable = new int[256];
        final int[] array = new int[256];
        final int[] gCrTable = new int[256];
        final int[] bCrTable = new int[256];
        for (int i = 0; i < 256; ++i) {
            ryTable[i] = i * 19595;
            gyTable[i] = i * 38470;
            byTable[i] = i * 7471 + 32768;
            rCbTable[i] = i * -11059;
            gCbTable[i] = i * -21709;
            array[i] = i * 32768 + 8388608;
            gCrTable[i] = i * -27439;
            bCrTable[i] = i * -5329;
        }
        RYTable = ryTable;
        GYTable = gyTable;
        BYTable = byTable;
        RCbTable = rCbTable;
        GCbTable = gCbTable;
        BCbTable = array;
        RCrTable = array;
        GCrTable = gCrTable;
        BCrTable = bCrTable;
        final int[] crRTable = new int[256];
        final int[] cbBTable = new int[256];
        final int[] crGTable = new int[256];
        final int[] cbGTable = new int[256];
        for (int j = 0; j < 256; ++j) {
            final int n = 2 * j - 255;
            crRTable[j] = 45941 * n + 32768 >> 16;
            cbBTable[j] = 58065 * n + 32768 >> 16;
            crGTable[j] = -23401 * n;
            cbGTable[j] = -11277 * n + 32768;
        }
        CrRTable = crRTable;
        CbBTable = cbBTable;
        CrGTable = crGTable;
        CbGTable = cbGTable;
        int n2 = 1;
        int n3 = 2;
        final int[] nBitsTable = new int[2048];
        nBitsTable[0] = 0;
        for (int k = 1; k < nBitsTable.length; ++k) {
            if (k >= n3) {
                ++n2;
                n3 *= 2;
            }
            nBitsTable[k] = n2;
        }
        NBitsTable = nBitsTable;
    }
    
    public JPEGFileFormat() {
        this.encoderQFactor = 75;
        this.eobrun = 0;
    }
    
    void compress(final ImageData imageData, final byte[] array, final byte[] array2, final byte[] array3) {
        final int width = imageData.width;
        final int height = imageData.height;
        final int n = this.maxV * this.maxH;
        this.imageComponents = new byte[this.nComponents][];
        for (int i = 0; i < this.nComponents; ++i) {
            final int[] array4 = this.frameComponents[this.componentIds[i]];
            this.imageComponents[i] = new byte[array4[3] * array4[4]];
        }
        final int[] array5 = this.frameComponents[this.componentIds[0]];
        for (int j = 0; j < height; ++j) {
            System.arraycopy(array, j * width, this.imageComponents[0], j * array5[3], width);
        }
        final int[] array6 = this.frameComponents[this.componentIds[1]];
        for (int k = 0; k < height / this.maxV; ++k) {
            final int n2 = k * array6[3];
            for (int l = 0; l < width / this.maxH; ++l) {
                int n3 = 0;
                for (int n4 = 0; n4 < this.maxV; ++n4) {
                    final int n5 = (k * this.maxV + n4) * width + l * this.maxH;
                    for (int n6 = 0; n6 < this.maxH; ++n6) {
                        n3 += (array2[n5 + n6] & 0xFF);
                    }
                }
                this.imageComponents[1][n2 + l] = (byte)(n3 / n);
            }
        }
        final int[] array7 = this.frameComponents[this.componentIds[2]];
        for (int n7 = 0; n7 < height / this.maxV; ++n7) {
            final int n8 = n7 * array7[3];
            for (int n9 = 0; n9 < width / this.maxH; ++n9) {
                int n10 = 0;
                for (int n11 = 0; n11 < this.maxV; ++n11) {
                    final int n12 = (n7 * this.maxV + n11) * width + n9 * this.maxH;
                    for (int n13 = 0; n13 < this.maxH; ++n13) {
                        n10 += (array3[n12 + n13] & 0xFF);
                    }
                }
                this.imageComponents[2][n8 + n9] = (byte)(n10 / n);
            }
        }
        for (int n14 = 0; n14 < this.nComponents; ++n14) {
            final byte[] array8 = this.imageComponents[n14];
            final int[] array9 = this.frameComponents[this.componentIds[n14]];
            final int n15 = array9[1];
            final int n16 = array9[2];
            final int n17 = array9[3];
            final int n18 = array9[4];
            final int n19 = width / (this.maxH / n15);
            final int n20 = height / (this.maxV / n16);
            if (n19 < n17) {
                final int n21 = n17 - n19;
                for (int n22 = 0; n22 < n20; ++n22) {
                    final int n23 = (n22 + 1) * n17 - n21;
                    final int n24 = array8[(n23 > 0) ? (n23 - 1) : 0] & 0xFF;
                    for (int n25 = 0; n25 < n21; ++n25) {
                        array8[n23 + n25] = (byte)n24;
                    }
                }
            }
            if (n20 < n18) {
                final int n26 = (n20 > 0) ? ((n20 - 1) * n17) : 1;
                for (int n27 = (n20 > 0) ? n20 : 1; n27 <= n18; ++n27) {
                    System.arraycopy(array8, n26, array8, (n27 - 1) * n17, n17);
                }
            }
        }
    }
    
    void convert4BitRGBToYCbCr(final ImageData imageData) {
        final RGB[] rgBs = imageData.getRGBs();
        final int length = rgBs.length;
        final byte[] array = new byte[length];
        final byte[] array2 = new byte[length];
        final byte[] array3 = new byte[length];
        final int width = imageData.width;
        final int height = imageData.height;
        for (int i = 0; i < length; ++i) {
            final RGB rgb = rgBs[i];
            final int red = rgb.red;
            final int green = rgb.green;
            final int blue = rgb.blue;
            final int n = JPEGFileFormat.RYTable[red] + JPEGFileFormat.GYTable[green] + JPEGFileFormat.BYTable[blue];
            array[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array4 = array;
                final int n2 = i;
                --array4[n2];
            }
            final int n3 = JPEGFileFormat.RCbTable[red] + JPEGFileFormat.GCbTable[green] + JPEGFileFormat.BCbTable[blue];
            array2[i] = (byte)(n3 >> 16);
            if (n3 < 0 && (n3 & 0xFFFF) != 0x0) {
                final byte[] array5 = array2;
                final int n4 = i;
                --array5[n4];
            }
            final int n5 = JPEGFileFormat.RCrTable[red] + JPEGFileFormat.GCrTable[green] + JPEGFileFormat.BCrTable[blue];
            array3[i] = (byte)(n5 >> 16);
            if (n5 < 0 && (n5 & 0xFFFF) != 0x0) {
                final byte[] array6 = array3;
                final int n6 = i;
                --array6[n6];
            }
        }
        final int n7 = width * height;
        final byte[] array7 = new byte[n7];
        final byte[] array8 = new byte[n7];
        final byte[] array9 = new byte[n7];
        final byte[] data = imageData.data;
        final int bytesPerLine = imageData.bytesPerLine;
        final int n8 = width >> 1;
        for (int j = 0; j < height; ++j) {
            for (int k = 0; k < n8; ++k) {
                final int n9 = j * bytesPerLine + k;
                final int n10 = j * width + k * 2;
                final int n11 = data[n9] & 0xFF;
                final int n12 = n11 >> 4;
                final int n13 = n11 & 0xF;
                array7[n10] = array[n12];
                array8[n10] = array2[n12];
                array9[n10] = array3[n12];
                array7[n10 + 1] = array[n13];
                array8[n10 + 1] = array2[n13];
                array9[n10 + 1] = array3[n13];
            }
        }
        this.compress(imageData, array7, array8, array9);
    }
    
    void convert8BitRGBToYCbCr(final ImageData imageData) {
        final RGB[] rgBs = imageData.getRGBs();
        final int length = rgBs.length;
        final byte[] array = new byte[length];
        final byte[] array2 = new byte[length];
        final byte[] array3 = new byte[length];
        final int width = imageData.width;
        final int height = imageData.height;
        for (int i = 0; i < length; ++i) {
            final RGB rgb = rgBs[i];
            final int red = rgb.red;
            final int green = rgb.green;
            final int blue = rgb.blue;
            final int n = JPEGFileFormat.RYTable[red] + JPEGFileFormat.GYTable[green] + JPEGFileFormat.BYTable[blue];
            array[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array4 = array;
                final int n2 = i;
                --array4[n2];
            }
            final int n3 = JPEGFileFormat.RCbTable[red] + JPEGFileFormat.GCbTable[green] + JPEGFileFormat.BCbTable[blue];
            array2[i] = (byte)(n3 >> 16);
            if (n3 < 0 && (n3 & 0xFFFF) != 0x0) {
                final byte[] array5 = array2;
                final int n4 = i;
                --array5[n4];
            }
            final int n5 = JPEGFileFormat.RCrTable[red] + JPEGFileFormat.GCrTable[green] + JPEGFileFormat.BCrTable[blue];
            array3[i] = (byte)(n5 >> 16);
            if (n5 < 0 && (n5 & 0xFFFF) != 0x0) {
                final byte[] array6 = array3;
                final int n6 = i;
                --array6[n6];
            }
        }
        final int width2 = imageData.width;
        final int n7 = height;
        final int n8 = width + 3 >> 2 << 2;
        final int n9 = width2 * n7;
        final byte[] array7 = new byte[n9];
        final byte[] array8 = new byte[n9];
        final byte[] array9 = new byte[n9];
        final byte[] data = imageData.data;
        for (int j = 0; j < height; ++j) {
            final int n10 = j * n8;
            final int n11 = j * width2;
            for (int k = 0; k < width; ++k) {
                final int n12 = data[n10 + k] & 0xFF;
                final int n13 = n11 + k;
                array7[n13] = array[n12];
                array8[n13] = array2[n12];
                array9[n13] = array3[n12];
            }
        }
        this.compress(imageData, array7, array8, array9);
    }
    
    byte[] convertCMYKToRGB() {
        return new byte[0];
    }
    
    void convertImageToYCbCr(final ImageData imageData) {
        switch (imageData.depth) {
            case 4: {
                this.convert4BitRGBToYCbCr(imageData);
            }
            case 8: {
                this.convert8BitRGBToYCbCr(imageData);
            }
            case 16:
            case 24:
            case 32: {
                this.convertMultiRGBToYCbCr(imageData);
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    void convertMultiRGBToYCbCr(final ImageData imageData) {
        final int width = imageData.width;
        final int height = imageData.height;
        final int n = width * height;
        final byte[] array = new byte[n];
        final byte[] array2 = new byte[n];
        final byte[] array3 = new byte[n];
        final PaletteData palette = imageData.palette;
        final int[] array4 = new int[width];
        if (palette.isDirect) {
            final int redMask = palette.redMask;
            final int greenMask = palette.greenMask;
            final int blueMask = palette.blueMask;
            final int redShift = palette.redShift;
            final int greenShift = palette.greenShift;
            final int blueShift = palette.blueShift;
            for (int i = 0; i < height; ++i) {
                imageData.getPixels(0, i, width, array4, 0);
                final int n2 = i * width;
                for (int j = 0; j < width; ++j) {
                    final int n3 = array4[j];
                    final int n4 = n2 + j;
                    final int n5 = n3 & redMask;
                    final int n6 = (redShift < 0) ? (n5 >>> -redShift) : (n5 << redShift);
                    final int n7 = n3 & greenMask;
                    final int n8 = (greenShift < 0) ? (n7 >>> -greenShift) : (n7 << greenShift);
                    final int n9 = n3 & blueMask;
                    final int n10 = (blueShift < 0) ? (n9 >>> -blueShift) : (n9 << blueShift);
                    array[n4] = (byte)(JPEGFileFormat.RYTable[n6] + JPEGFileFormat.GYTable[n8] + JPEGFileFormat.BYTable[n10] >> 16);
                    array2[n4] = (byte)(JPEGFileFormat.RCbTable[n6] + JPEGFileFormat.GCbTable[n8] + JPEGFileFormat.BCbTable[n10] >> 16);
                    array3[n4] = (byte)(JPEGFileFormat.RCrTable[n6] + JPEGFileFormat.GCrTable[n8] + JPEGFileFormat.BCrTable[n10] >> 16);
                }
            }
        }
        else {
            for (int k = 0; k < height; ++k) {
                imageData.getPixels(0, k, width, array4, 0);
                final int n11 = k * width;
                for (int l = 0; l < width; ++l) {
                    final int n12 = array4[l];
                    final int n13 = n11 + l;
                    final RGB rgb = palette.getRGB(n12);
                    final int red = rgb.red;
                    final int green = rgb.green;
                    final int blue = rgb.blue;
                    array[n13] = (byte)(JPEGFileFormat.RYTable[red] + JPEGFileFormat.GYTable[green] + JPEGFileFormat.BYTable[blue] >> 16);
                    array2[n13] = (byte)(JPEGFileFormat.RCbTable[red] + JPEGFileFormat.GCbTable[green] + JPEGFileFormat.BCbTable[blue] >> 16);
                    array3[n13] = (byte)(JPEGFileFormat.RCrTable[red] + JPEGFileFormat.GCrTable[green] + JPEGFileFormat.BCrTable[blue] >> 16);
                }
            }
        }
        this.compress(imageData, array, array2, array3);
    }
    
    byte[] convertYToRGB() {
        final int n = this.frameComponents[this.componentIds[0]][3];
        final int n2 = ((this.imageWidth * 8 + 7) / 8 + 3) / 4 * 4;
        final byte[] array = new byte[n2 * this.imageHeight];
        final byte[] array2 = this.imageComponents[0];
        int n3 = 0;
        for (int i = 0; i < this.imageHeight; ++i) {
            int n4 = i * n;
            for (int j = 0; j < n2; ++j) {
                int n5 = array2[n4] & 0xFF;
                if (n5 < 0) {
                    n5 = 0;
                }
                else if (n5 > 255) {
                    n5 = 255;
                }
                if (j >= this.imageWidth) {
                    n5 = 0;
                }
                array[n3] = (byte)n5;
                ++n4;
                ++n3;
            }
        }
        return array;
    }
    
    byte[] convertYCbCrToRGB() {
        final byte[] array = new byte[this.imageWidth * this.imageHeight * this.nComponents];
        int n = 0;
        this.expandImageComponents();
        final byte[] array2 = this.imageComponents[0];
        final byte[] array3 = this.imageComponents[1];
        final byte[] array4 = this.imageComponents[2];
        final int n2 = this.frameComponents[this.componentIds[0]][3];
        for (int i = 0; i < this.imageHeight; ++i) {
            int n3 = i * n2;
            for (int j = 0; j < this.imageWidth; ++j) {
                final int n4 = array2[n3] & 0xFF;
                final int n5 = array3[n3] & 0xFF;
                final int n6 = array4[n3] & 0xFF;
                int n7 = n4 + JPEGFileFormat.CrRTable[n6];
                int n8 = n4 + (JPEGFileFormat.CbGTable[n5] + JPEGFileFormat.CrGTable[n6] >> 16);
                int n9 = n4 + JPEGFileFormat.CbBTable[n5];
                if (n7 < 0) {
                    n7 = 0;
                }
                else if (n7 > 255) {
                    n7 = 255;
                }
                if (n8 < 0) {
                    n8 = 0;
                }
                else if (n8 > 255) {
                    n8 = 255;
                }
                if (n9 < 0) {
                    n9 = 0;
                }
                else if (n9 > 255) {
                    n9 = 255;
                }
                array[n] = (byte)n9;
                array[n + 1] = (byte)n8;
                array[n + 2] = (byte)n7;
                n += 3;
                ++n3;
            }
        }
        return array;
    }
    
    void decodeACCoefficients(final int[] array, final int n) {
        final JPEGHuffmanTable jpegHuffmanTable = this.acHuffmanTables[this.scanHeader.componentParameters[this.componentIds[n]][1]];
        int i = 1;
        while (i < 64) {
            final int decodeUsingTable = this.decodeUsingTable(jpegHuffmanTable);
            final int n2 = decodeUsingTable >> 4;
            final int n3 = decodeUsingTable & 0xF;
            if (n3 == 0) {
                if (n2 != 15) {
                    break;
                }
                i += 16;
            }
            else {
                i += n2;
                array[JPEGFileFormat.ZigZag8x8[i]] = this.extendBy(this.receive(n3), n3);
                ++i;
            }
        }
    }
    
    void decodeACFirstCoefficients(final int[] array, final int n, final int n2, final int n3, final int n4) {
        if (this.eobrun > 0) {
            --this.eobrun;
            return;
        }
        final JPEGHuffmanTable jpegHuffmanTable = this.acHuffmanTables[this.scanHeader.componentParameters[this.componentIds[n]][1]];
        int i = n2;
        while (i <= n3) {
            final int decodeUsingTable = this.decodeUsingTable(jpegHuffmanTable);
            final int n5 = decodeUsingTable >> 4;
            final int n6 = decodeUsingTable & 0xF;
            if (n6 == 0) {
                if (n5 != 15) {
                    this.eobrun = (1 << n5) + this.receive(n5) - 1;
                    break;
                }
                i += 16;
            }
            else {
                i += n5;
                array[JPEGFileFormat.ZigZag8x8[i]] = this.extendBy(this.receive(n6), n6) << n4;
                ++i;
            }
        }
    }
    
    void decodeACRefineCoefficients(final int[] array, final int n, final int n2, final int n3, final int n4) {
        final JPEGHuffmanTable jpegHuffmanTable = this.acHuffmanTables[this.scanHeader.componentParameters[this.componentIds[n]][1]];
        int i = n2;
        while (i <= n3) {
            if (this.eobrun > 0) {
                while (i <= n3) {
                    final int n5 = JPEGFileFormat.ZigZag8x8[i];
                    if (array[n5] != 0) {
                        array[n5] = this.refineAC(array[n5], n4);
                    }
                    ++i;
                }
                --this.eobrun;
            }
            else {
                final int decodeUsingTable = this.decodeUsingTable(jpegHuffmanTable);
                final int n6 = decodeUsingTable >> 4;
                final int n7 = decodeUsingTable & 0xF;
                if (n7 == 0) {
                    if (n6 == 15) {
                        int j = 0;
                        while (j < 16) {
                            if (i > n3) {
                                break;
                            }
                            final int n8 = JPEGFileFormat.ZigZag8x8[i];
                            if (array[n8] != 0) {
                                array[n8] = this.refineAC(array[n8], n4);
                            }
                            else {
                                ++j;
                            }
                            ++i;
                        }
                    }
                    else {
                        this.eobrun = (1 << n6) + this.receive(n6);
                    }
                }
                else {
                    final int receive = this.receive(n7);
                    int n9;
                    int n10;
                    for (n9 = 0, n10 = JPEGFileFormat.ZigZag8x8[i]; (n9 < n6 || array[n10] != 0) && i <= n3; ++i, n10 = JPEGFileFormat.ZigZag8x8[i]) {
                        if (array[n10] != 0) {
                            array[n10] = this.refineAC(array[n10], n4);
                        }
                        else {
                            ++n9;
                        }
                    }
                    if (receive != 0) {
                        array[n10] = 1 << n4;
                    }
                    else {
                        array[n10] = -1 << n4;
                    }
                    ++i;
                }
            }
        }
    }
    
    int refineAC(int n, final int n2) {
        if (n > 0) {
            if (this.nextBit() != 0) {
                n += 1 << n2;
            }
        }
        else if (n < 0 && this.nextBit() != 0) {
            n += -1 << n2;
        }
        return n;
    }
    
    void decodeDCCoefficient(final int[] array, final int n, final boolean b, final int n2) {
        final JPEGHuffmanTable jpegHuffmanTable = this.dcHuffmanTables[this.scanHeader.componentParameters[this.componentIds[n]][0]];
        int n3;
        if (this.progressive && !b) {
            n3 = array[0] + (this.nextBit() << n2);
        }
        else {
            n3 = this.precedingDCs[n];
            final int decodeUsingTable = this.decodeUsingTable(jpegHuffmanTable);
            if (decodeUsingTable != 0) {
                n3 += this.extendBy(this.receive(decodeUsingTable), decodeUsingTable);
                this.precedingDCs[n] = n3;
            }
            if (this.progressive) {
                n3 <<= n2;
            }
        }
        array[0] = n3;
    }
    
    void dequantize(final int[] array, final int n) {
        final int[] array2 = this.quantizationTables[this.frameComponents[this.componentIds[n]][0]];
        for (int i = 0; i < array.length; ++i) {
            final int n2 = JPEGFileFormat.ZigZag8x8[i];
            array[n2] *= array2[i];
        }
    }
    
    byte[] decodeImageComponents() {
        if (this.nComponents == 3) {
            return this.convertYCbCrToRGB();
        }
        if (this.nComponents == 4) {
            return this.convertCMYKToRGB();
        }
        return this.convertYToRGB();
    }
    
    void decodeMCUAtXAndY(final int n, final int n2, final int n3, final boolean b, final int n4, final int n5, final int n6) {
        for (int i = 0; i < n3; ++i) {
            int n7;
            for (n7 = i; this.scanHeader.componentParameters[this.componentIds[n7]] == null; ++n7) {}
            final int[] array = this.frameComponents[this.componentIds[n7]];
            int n8 = array[1];
            int n9 = array[2];
            if (n3 == 1) {
                n8 = 1;
                n9 = 1;
            }
            final int n10 = array[3];
            for (int j = 0; j < n9; ++j) {
                for (int k = 0; k < n8; ++k) {
                    if (this.progressive) {
                        final int n11 = (n2 * n9 + j) * n10 + n * n8 + k;
                        this.dataUnit = this.dataUnits[n7][n11];
                        if (this.dataUnit == null) {
                            this.dataUnit = new int[64];
                            this.dataUnits[n7][n11] = this.dataUnit;
                        }
                    }
                    else {
                        for (int l = 0; l < this.dataUnit.length; ++l) {
                            this.dataUnit[l] = 0;
                        }
                    }
                    if (!this.progressive || this.scanHeader.isDCProgressiveScan()) {
                        this.decodeDCCoefficient(this.dataUnit, n7, b, n6);
                    }
                    if (!this.progressive) {
                        this.decodeACCoefficients(this.dataUnit, n7);
                    }
                    else {
                        if (this.scanHeader.isACProgressiveScan()) {
                            if (b) {
                                this.decodeACFirstCoefficients(this.dataUnit, n7, n4, n5, n6);
                            }
                            else {
                                this.decodeACRefineCoefficients(this.dataUnit, n7, n4, n5, n6);
                            }
                        }
                        if (this.loader.hasListeners()) {
                            System.arraycopy(this.dataUnit, 0, this.dataUnit = new int[64], 0, 64);
                        }
                    }
                    if (!this.progressive || (this.progressive && this.loader.hasListeners())) {
                        this.dequantize(this.dataUnit, n7);
                        this.inverseDCT(this.dataUnit);
                        this.storeData(this.dataUnit, n7, n, n2, n8, k, n9, j);
                    }
                }
            }
        }
    }
    
    void decodeScan() {
        if (this.progressive && !this.scanHeader.verifyProgressiveScan()) {
            SWT.error(40);
        }
        final int numberOfImageComponents = this.scanHeader.getNumberOfImageComponents();
        int interleavedMcuRows = this.interleavedMcuRows;
        int interleavedMcuCols = this.interleavedMcuCols;
        if (numberOfImageComponents == 1) {
            int n;
            for (n = 0; this.scanHeader.componentParameters[this.componentIds[n]] == null; ++n) {}
            final int[] array = this.frameComponents[this.componentIds[n]];
            final int n2 = array[1];
            final int n3 = array[2];
            final int n4 = 8 * this.maxH / n2;
            final int n5 = 8 * this.maxV / n3;
            interleavedMcuCols = (this.imageWidth + n4 - 1) / n4;
            interleavedMcuRows = (this.imageHeight + n5 - 1) / n5;
        }
        final boolean firstScan = this.scanHeader.isFirstScan();
        final int startOfSpectralSelection = this.scanHeader.getStartOfSpectralSelection();
        final int endOfSpectralSelection = this.scanHeader.getEndOfSpectralSelection();
        final int approxBitPositionLow = this.scanHeader.getApproxBitPositionLow();
        this.restartsToGo = this.restartInterval;
        this.nextRestartNumber = 0;
        for (int i = 0; i < interleavedMcuRows; ++i) {
            for (int j = 0; j < interleavedMcuCols; ++j) {
                if (this.restartInterval != 0) {
                    if (this.restartsToGo == 0) {
                        this.processRestartInterval();
                    }
                    --this.restartsToGo;
                }
                this.decodeMCUAtXAndY(j, i, numberOfImageComponents, firstScan, startOfSpectralSelection, endOfSpectralSelection, approxBitPositionLow);
            }
        }
    }
    
    int decodeUsingTable(final JPEGHuffmanTable jpegHuffmanTable) {
        int n = 0;
        final int[] dhMaxCodes = jpegHuffmanTable.getDhMaxCodes();
        final int[] dhMinCodes = jpegHuffmanTable.getDhMinCodes();
        final int[] dhValPtrs = jpegHuffmanTable.getDhValPtrs();
        final int[] dhValues = jpegHuffmanTable.getDhValues();
        int i;
        for (i = this.nextBit(); i > dhMaxCodes[n]; i = i * 2 + this.nextBit(), ++n) {}
        return dhValues[dhValPtrs[n] + i - dhMinCodes[n]];
    }
    
    void emit(final int n, final int n2) {
        if (n2 == 0) {
            SWT.error(40);
        }
        final int n3 = (n & (new int[] { 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131125 })[n2 - 1]) << 24 - n2 - this.currentBitCount;
        final byte[] array = { (byte)(n3 & 0xFF), (byte)(n3 >> 8 & 0xFF), (byte)(n3 >> 16 & 0xFF), (byte)(n3 >> 24 & 0xFF) };
        int n4 = n2 - (8 - this.currentBitCount);
        if (n4 < 0) {
            n4 = -n4;
        }
        if (n4 >> 3 > 0) {
            this.currentByte += array[2];
            this.emitByte((byte)this.currentByte);
            this.emitByte(array[1]);
            this.currentByte = array[0];
            this.currentBitCount += n2 - 16;
        }
        else {
            this.currentBitCount += n2;
            if (this.currentBitCount >= 8) {
                this.currentByte += array[2];
                this.emitByte((byte)this.currentByte);
                this.currentByte = array[1];
                this.currentBitCount -= 8;
            }
            else {
                this.currentByte += array[2];
            }
        }
    }
    
    void emitByte(final byte b) {
        if (this.bufferCurrentPosition >= 512) {
            this.resetOutputBuffer();
        }
        this.dataBuffer[this.bufferCurrentPosition] = b;
        ++this.bufferCurrentPosition;
        if (b == -1) {
            this.emitByte((byte)0);
        }
    }
    
    void encodeACCoefficients(final int[] array, final int n) {
        final JPEGHuffmanTable jpegHuffmanTable = this.acHuffmanTables[this.scanHeader.componentParameters[n][1]];
        final int[] ehCodes = jpegHuffmanTable.ehCodes;
        final byte[] ehCodeLengths = jpegHuffmanTable.ehCodeLengths;
        int i = 0;
        int j = 1;
        while (j < 64) {
            ++j;
            final int n2 = array[JPEGFileFormat.ZigZag8x8[j - 1]];
            if (n2 == 0) {
                if (j == 64) {
                    this.emit(ehCodes[0], ehCodeLengths[0] & 0xFF);
                }
                else {
                    ++i;
                }
            }
            else {
                while (i > 15) {
                    this.emit(ehCodes[240], ehCodeLengths[240] & 0xFF);
                    i -= 16;
                }
                if (n2 < 0) {
                    int n3 = n2;
                    if (n3 < 0) {
                        n3 = -n3;
                    }
                    final int n4 = JPEGFileFormat.NBitsTable[n3];
                    final int n5 = i * 16 + n4;
                    this.emit(ehCodes[n5], ehCodeLengths[n5] & 0xFF);
                    this.emit(16777215 - n3, n4);
                }
                else {
                    final int n6 = JPEGFileFormat.NBitsTable[n2];
                    final int n7 = i * 16 + n6;
                    this.emit(ehCodes[n7], ehCodeLengths[n7] & 0xFF);
                    this.emit(n2, n6);
                }
                i = 0;
            }
        }
    }
    
    void encodeDCCoefficients(final int[] array, final int n) {
        final JPEGHuffmanTable jpegHuffmanTable = this.dcHuffmanTables[this.scanHeader.componentParameters[n][0]];
        final int n2 = this.precedingDCs[n];
        final int n3 = array[0];
        final int n4 = n3 - n2;
        this.precedingDCs[n] = n3;
        if (n4 < 0) {
            final int n5 = 0 - n4;
            final int n6 = JPEGFileFormat.NBitsTable[n5];
            this.emit(jpegHuffmanTable.ehCodes[n6], jpegHuffmanTable.ehCodeLengths[n6]);
            this.emit(16777215 - n5, n6);
        }
        else {
            final int n7 = JPEGFileFormat.NBitsTable[n4];
            this.emit(jpegHuffmanTable.ehCodes[n7], jpegHuffmanTable.ehCodeLengths[n7]);
            if (n7 != 0) {
                this.emit(n4, n7);
            }
        }
    }
    
    void encodeMCUAtXAndY(final int n, final int n2) {
        final int numberOfImageComponents = this.scanHeader.getNumberOfImageComponents();
        this.dataUnit = new int[64];
        for (int i = 0; i < numberOfImageComponents; ++i) {
            final int[] array = this.frameComponents[this.componentIds[i]];
            final int n3 = array[1];
            for (int n4 = array[2], j = 0; j < n4; ++j) {
                for (int k = 0; k < n3; ++k) {
                    this.extractData(this.dataUnit, i, n, n2, k, j);
                    this.forwardDCT(this.dataUnit);
                    this.quantizeData(this.dataUnit, i);
                    this.encodeDCCoefficients(this.dataUnit, i);
                    this.encodeACCoefficients(this.dataUnit, i);
                }
            }
        }
    }
    
    void encodeScan() {
        for (int i = 0; i < this.interleavedMcuRows; ++i) {
            for (int j = 0; j < this.interleavedMcuCols; ++j) {
                this.encodeMCUAtXAndY(j, i);
            }
        }
        if (this.currentBitCount != 0) {
            this.emitByte((byte)this.currentByte);
        }
        this.resetOutputBuffer();
    }
    
    void expandImageComponents() {
        for (int i = 0; i < this.nComponents; ++i) {
            final int[] array = this.frameComponents[this.componentIds[i]];
            final int n = array[1];
            final int n2 = array[2];
            final int n3 = this.maxH / n;
            final int n4 = this.maxV / n2;
            if (n3 * n4 > 1) {
                final byte[] array2 = this.imageComponents[i];
                final int n5 = array[3];
                final int n6 = array[4];
                this.imageComponents[i] = new ImageData(n5, n6, 8, new PaletteData(JPEGFileFormat.RGB16), 4, array2).scaledTo(n5 * n3, n6 * n4).data;
            }
        }
    }
    
    int extendBy(final int n, final int n2) {
        if (n < JPEGFileFormat.ExtendTest[n2]) {
            return n + JPEGFileFormat.ExtendOffset[n2];
        }
        return n;
    }
    
    void extractData(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        final byte[] array2 = this.imageComponents[n];
        final int[] array3 = this.frameComponents[this.componentIds[n]];
        final int n6 = array3[1];
        final int n7 = array3[2];
        final int n8 = array3[3];
        int n9 = (n3 * n7 + n5) * n8 * 8 + (n2 * n6 + n4) * 8;
        int n10 = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                array[n10] = (array2[n9 + j] & 0xFF) - 128;
                ++n10;
            }
            n9 += n8;
        }
    }
    
    void forwardDCT(final int[] array) {
        for (int i = 0; i < 8; ++i) {
            final int n = i * 8;
            final int n2 = array[n] + array[n + 7];
            final int n3 = array[n] - array[n + 7];
            final int n4 = array[n + 1] + array[n + 6];
            final int n5 = array[n + 1] - array[n + 6];
            final int n6 = array[n + 2] + array[n + 5];
            final int n7 = array[n + 2] - array[n + 5];
            final int n8 = array[n + 3] + array[n + 4];
            final int n9 = array[n + 3] - array[n + 4];
            final int n10 = n2 + n8;
            final int n11 = n2 - n8;
            final int n12 = n4 + n6;
            final int n13 = n4 - n6;
            array[n] = (n10 + n12) * 4;
            array[n + 4] = (n10 - n12) * 4;
            final int n14 = (n13 + n11) * 4433;
            final int n15 = n14 + n11 * 6270 + 1024;
            array[n + 2] = n15 >> 11;
            if (n15 < 0 && (n15 & 0x7FF) != 0x0) {
                final int n16 = n + 2;
                --array[n16];
            }
            final int n17 = n14 + n13 * -15137 + 1024;
            array[n + 6] = n17 >> 11;
            if (n17 < 0 && (n17 & 0x7FF) != 0x0) {
                final int n18 = n + 6;
                --array[n18];
            }
            final int n19 = n9 + n3;
            final int n20 = n7 + n5;
            final int n21 = n9 + n5;
            final int n22 = n7 + n3;
            final int n23 = (n21 + n22) * 9633;
            final int n24 = n9 * 2446;
            final int n25 = n7 * 16819;
            final int n26 = n5 * 25172;
            final int n27 = n3 * 12299;
            final int n28 = n19 * -7373;
            final int n29 = n20 * -20995;
            final int n30 = n21 * -16069;
            final int n31 = n22 * -3196;
            final int n32 = n30 + n23;
            final int n33 = n31 + n23;
            final int n34 = n24 + n28 + n32 + 1024;
            array[n + 7] = n34 >> 11;
            if (n34 < 0 && (n34 & 0x7FF) != 0x0) {
                final int n35 = n + 7;
                --array[n35];
            }
            final int n36 = n25 + n29 + n33 + 1024;
            array[n + 5] = n36 >> 11;
            if (n36 < 0 && (n36 & 0x7FF) != 0x0) {
                final int n37 = n + 5;
                --array[n37];
            }
            final int n38 = n26 + n29 + n32 + 1024;
            array[n + 3] = n38 >> 11;
            if (n38 < 0 && (n38 & 0x7FF) != 0x0) {
                final int n39 = n + 3;
                --array[n39];
            }
            final int n40 = n27 + n28 + n33 + 1024;
            array[n + 1] = n40 >> 11;
            if (n40 < 0 && (n40 & 0x7FF) != 0x0) {
                final int n41 = n + 1;
                --array[n41];
            }
        }
        for (int j = 0; j < 8; ++j) {
            final int n42 = j;
            final int n43 = j + 8;
            final int n44 = j + 16;
            final int n45 = j + 24;
            final int n46 = j + 32;
            final int n47 = j + 40;
            final int n48 = j + 48;
            final int n49 = j + 56;
            final int n50 = array[n42] + array[n49];
            final int n51 = array[n42] - array[n49];
            final int n52 = array[n43] + array[n48];
            final int n53 = array[n43] - array[n48];
            final int n54 = array[n44] + array[n47];
            final int n55 = array[n44] - array[n47];
            final int n56 = array[n45] + array[n46];
            final int n57 = array[n45] - array[n46];
            final int n58 = n50 + n56;
            final int n59 = n50 - n56;
            final int n60 = n52 + n54;
            final int n61 = n52 - n54;
            final int n62 = n58 + n60 + 16;
            array[n42] = n62 >> 5;
            if (n62 < 0 && (n62 & 0x1F) != 0x0) {
                final int n63 = n42;
                --array[n63];
            }
            final int n64 = n58 - n60 + 16;
            array[n46] = n64 >> 5;
            if (n64 < 0 && (n64 & 0x1F) != 0x0) {
                final int n65 = n46;
                --array[n65];
            }
            final int n66 = (n61 + n59) * 4433;
            final int n67 = n66 + n59 * 6270 + 131072;
            array[n44] = n67 >> 18;
            if (n67 < 0 && (n67 & 0x3FFFF) != 0x0) {
                final int n68 = n44;
                --array[n68];
            }
            final int n69 = n66 + n61 * -15137 + 131072;
            array[n48] = n69 >> 18;
            if (n69 < 0 && (n69 & 0x3FFFF) != 0x0) {
                final int n70 = n48;
                --array[n70];
            }
            final int n71 = n57 + n51;
            final int n72 = n55 + n53;
            final int n73 = n57 + n53;
            final int n74 = n55 + n51;
            final int n75 = (n73 + n74) * 9633;
            final int n76 = n57 * 2446;
            final int n77 = n55 * 16819;
            final int n78 = n53 * 25172;
            final int n79 = n51 * 12299;
            final int n80 = n71 * -7373;
            final int n81 = n72 * -20995;
            final int n82 = n73 * -16069;
            final int n83 = n74 * -3196;
            final int n84 = n82 + n75;
            final int n85 = n83 + n75;
            final int n86 = n76 + n80 + n84 + 131072;
            array[n49] = n86 >> 18;
            if (n86 < 0 && (n86 & 0x3FFFF) != 0x0) {
                final int n87 = n49;
                --array[n87];
            }
            final int n88 = n77 + n81 + n85 + 131072;
            array[n47] = n88 >> 18;
            if (n88 < 0 && (n88 & 0x3FFFF) != 0x0) {
                final int n89 = n47;
                --array[n89];
            }
            final int n90 = n78 + n81 + n84 + 131072;
            array[n45] = n90 >> 18;
            if (n90 < 0 && (n90 & 0x3FFFF) != 0x0) {
                final int n91 = n45;
                --array[n91];
            }
            final int n92 = n79 + n80 + n85 + 131072;
            array[n43] = n92 >> 18;
            if (n92 < 0 && (n92 & 0x3FFFF) != 0x0) {
                final int n93 = n43;
                --array[n93];
            }
        }
    }
    
    void getAPP0() {
        if (!new JPEGAppn(this.inputStream).verify()) {
            SWT.error(40);
        }
    }
    
    void getCOM() {
        new JPEGComment(this.inputStream);
    }
    
    void getDAC() {
        new JPEGArithmeticConditioningTable(this.inputStream);
    }
    
    void getDHT() {
        final JPEGHuffmanTable jpegHuffmanTable = new JPEGHuffmanTable(this.inputStream);
        if (!jpegHuffmanTable.verify()) {
            SWT.error(40);
        }
        if (this.acHuffmanTables == null) {
            this.acHuffmanTables = new JPEGHuffmanTable[4];
        }
        if (this.dcHuffmanTables == null) {
            this.dcHuffmanTables = new JPEGHuffmanTable[4];
        }
        final JPEGHuffmanTable[] allTables = jpegHuffmanTable.getAllTables();
        for (int i = 0; i < allTables.length; ++i) {
            final JPEGHuffmanTable jpegHuffmanTable2 = allTables[i];
            if (jpegHuffmanTable2.getTableClass() == 0) {
                this.dcHuffmanTables[jpegHuffmanTable2.getTableIdentifier()] = jpegHuffmanTable2;
            }
            else {
                this.acHuffmanTables[jpegHuffmanTable2.getTableIdentifier()] = jpegHuffmanTable2;
            }
        }
    }
    
    void getDNL() {
        new JPEGRestartInterval(this.inputStream);
    }
    
    void getDQT() {
        final JPEGQuantizationTable jpegQuantizationTable = new JPEGQuantizationTable(this.inputStream);
        int[][] quantizationTables = this.quantizationTables;
        if (quantizationTables == null) {
            quantizationTables = new int[4][];
        }
        final int[] quantizationTablesKeys = jpegQuantizationTable.getQuantizationTablesKeys();
        final int[][] quantizationTablesValues = jpegQuantizationTable.getQuantizationTablesValues();
        for (int i = 0; i < quantizationTablesKeys.length; ++i) {
            quantizationTables[quantizationTablesKeys[i]] = quantizationTablesValues[i];
        }
        this.quantizationTables = quantizationTables;
    }
    
    void getDRI() {
        final JPEGRestartInterval jpegRestartInterval = new JPEGRestartInterval(this.inputStream);
        if (!jpegRestartInterval.verify()) {
            SWT.error(40);
        }
        this.restartInterval = jpegRestartInterval.getRestartInterval();
    }
    
    void inverseDCT(final int[] array) {
        for (int i = 0; i < 8; ++i) {
            final int n = i * 8;
            if (this.isZeroInRow(array, n)) {
                final int n2 = array[n] << 2;
                for (int j = n + 7; j >= n; --j) {
                    array[j] = n2;
                }
            }
            else {
                final int n3 = array[n + 2];
                final int n4 = array[n + 6];
                final int n5 = (n3 + n4) * 4433;
                final int n6 = n5 + n4 * -15137;
                final int n7 = n5 + n3 * 6270;
                final int n8 = array[n] + array[n + 4] << 13;
                final int n9 = array[n] - array[n + 4] << 13;
                final int n10 = n8 + n7;
                final int n11 = n8 - n7;
                final int n12 = n9 + n6;
                final int n13 = n9 - n6;
                final int n14 = array[n + 7];
                final int n15 = array[n + 5];
                final int n16 = array[n + 3];
                final int n17 = array[n + 1];
                final int n18 = n14 + n17;
                final int n19 = n15 + n16;
                final int n20 = n14 + n16;
                final int n21 = n15 + n17;
                final int n22 = (n20 + n21) * 9633;
                final int n23 = n14 * 2446;
                final int n24 = n15 * 16819;
                final int n25 = n16 * 25172;
                final int n26 = n17 * 12299;
                final int n27 = n18 * -7373;
                final int n28 = n19 * -20995;
                final int n29 = n20 * -16069;
                final int n30 = n21 * -3196;
                final int n31 = n29 + n22;
                final int n32 = n30 + n22;
                final int n33 = n23 + (n27 + n31);
                final int n34 = n24 + (n28 + n32);
                final int n35 = n25 + (n28 + n31);
                final int n36 = n26 + (n27 + n32);
                array[n] = n10 + n36 + 1024 >> 11;
                array[n + 7] = n10 - n36 + 1024 >> 11;
                array[n + 1] = n12 + n35 + 1024 >> 11;
                array[n + 6] = n12 - n35 + 1024 >> 11;
                array[n + 2] = n13 + n34 + 1024 >> 11;
                array[n + 5] = n13 - n34 + 1024 >> 11;
                array[n + 3] = n11 + n33 + 1024 >> 11;
                array[n + 4] = n11 - n33 + 1024 >> 11;
            }
        }
        for (int k = 0; k < 8; ++k) {
            final int n37 = k;
            final int n38 = k + 8;
            final int n39 = k + 16;
            final int n40 = k + 24;
            final int n41 = k + 32;
            final int n42 = k + 40;
            final int n43 = k + 48;
            final int n44 = k + 56;
            if (this.isZeroInColumn(array, k)) {
                final int n45 = array[n37] + 16 >> 5;
                array[n38] = (array[n37] = n45);
                array[n40] = (array[n39] = n45);
                array[n42] = (array[n41] = n45);
                array[n44] = (array[n43] = n45);
            }
            else {
                final int n46 = array[n37];
                final int n47 = array[n39];
                final int n48 = array[n43];
                final int n49 = array[n41];
                final int n50 = (n47 + n48) * 4433;
                final int n51 = n50 + n48 * -15137;
                final int n52 = n50 + n47 * 6270;
                final int n53 = n46 + n49 << 13;
                final int n54 = n46 - n49 << 13;
                final int n55 = n53 + n52;
                final int n56 = n53 - n52;
                final int n57 = n54 + n51;
                final int n58 = n54 - n51;
                final int n59 = array[n44];
                final int n60 = array[n42];
                final int n61 = array[n40];
                final int n62 = array[n38];
                final int n63 = n59 + n62;
                final int n64 = n60 + n61;
                final int n65 = n59 + n61;
                final int n66 = n60 + n62;
                final int n67 = (n65 + n66) * 9633;
                final int n68 = n59 * 2446;
                final int n69 = n60 * 16819;
                final int n70 = n61 * 25172;
                final int n71 = n62 * 12299;
                final int n72 = n63 * -7373;
                final int n73 = n64 * -20995;
                final int n74 = n65 * -16069;
                final int n75 = n66 * -3196;
                final int n76 = n74 + n67;
                final int n77 = n75 + n67;
                final int n78 = n68 + (n72 + n76);
                final int n79 = n69 + (n73 + n77);
                final int n80 = n70 + (n73 + n76);
                final int n81 = n71 + (n72 + n77);
                array[n37] = n55 + n81 + 131072 >> 18;
                array[n44] = n55 - n81 + 131072 >> 18;
                array[n38] = n57 + n80 + 131072 >> 18;
                array[n43] = n57 - n80 + 131072 >> 18;
                array[n39] = n58 + n79 + 131072 >> 18;
                array[n42] = n58 - n79 + 131072 >> 18;
                array[n40] = n56 + n78 + 131072 >> 18;
                array[n41] = n56 - n78 + 131072 >> 18;
            }
        }
    }
    
    boolean isFileFormat(final LEDataInputStream leDataInputStream) {
        try {
            final JPEGStartOfImage jpegStartOfImage = new JPEGStartOfImage(leDataInputStream);
            leDataInputStream.unread(jpegStartOfImage.reference);
            return jpegStartOfImage.verify();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    boolean isZeroInColumn(final int[] array, final int n) {
        return array[n + 8] == 0 && array[n + 16] == 0 && array[n + 24] == 0 && array[n + 32] == 0 && array[n + 40] == 0 && array[n + 48] == 0 && array[n + 56] == 0;
    }
    
    boolean isZeroInRow(final int[] array, final int n) {
        return array[n + 1] == 0 && array[n + 2] == 0 && array[n + 3] == 0 && array[n + 4] == 0 && array[n + 5] == 0 && array[n + 6] == 0 && array[n + 7] == 0;
    }
    
    ImageData[] loadFromByteStream() {
        if (System.getProperty("org.eclipse.swt.internal.image.JPEGFileFormat_3.2") == null) {
            return JPEGDecoder.loadFromByteStream(this.inputStream, this.loader);
        }
        if (!new JPEGStartOfImage(this.inputStream).verify()) {
            SWT.error(40);
        }
        this.restartInterval = 0;
        this.processTables();
        this.frameHeader = new JPEGFrameHeader(this.inputStream);
        if (!this.frameHeader.verify()) {
            SWT.error(40);
        }
        this.imageWidth = this.frameHeader.getSamplesPerLine();
        this.imageHeight = this.frameHeader.getNumberOfLines();
        this.maxH = this.frameHeader.getMaxHFactor();
        this.maxV = this.frameHeader.getMaxVFactor();
        final int n = this.maxH * 8;
        final int n2 = this.maxV * 8;
        this.interleavedMcuCols = (this.imageWidth + n - 1) / n;
        this.interleavedMcuRows = (this.imageHeight + n2 - 1) / n2;
        this.progressive = this.frameHeader.isProgressive();
        this.samplePrecision = this.frameHeader.getSamplePrecision();
        this.nComponents = this.frameHeader.getNumberOfImageComponents();
        this.frameComponents = this.frameHeader.componentParameters;
        this.componentIds = this.frameHeader.componentIdentifiers;
        this.imageComponents = new byte[this.nComponents][];
        if (this.progressive) {
            this.dataUnits = new int[this.nComponents][][];
        }
        else {
            this.dataUnit = new int[64];
        }
        for (int i = 0; i < this.nComponents; ++i) {
            final int[] array = this.frameComponents[this.componentIds[i]];
            final int n3 = array[3] * array[4];
            this.imageComponents[i] = new byte[n3];
            if (this.progressive) {
                this.dataUnits[i] = new int[n3][];
            }
        }
        this.processTables();
        this.scanHeader = new JPEGScanHeader(this.inputStream);
        if (!this.scanHeader.verify()) {
            SWT.error(40);
        }
        int n4 = 0;
        boolean b = false;
        while (!b) {
            this.resetInputBuffer();
            this.precedingDCs = new int[4];
            this.decodeScan();
            if (this.progressive && this.loader.hasListeners()) {
                this.loader.notifyListeners(new ImageLoaderEvent(this.loader, this.createImageData(), n4, false));
                ++n4;
            }
            final int n5 = 512 - this.bufferCurrentPosition - 1;
            if (n5 > 0) {
                final byte[] array2 = new byte[n5];
                System.arraycopy(this.dataBuffer, this.bufferCurrentPosition + 1, array2, 0, n5);
                try {
                    this.inputStream.unread(array2);
                }
                catch (IOException ex) {
                    SWT.error(39, ex);
                }
            }
            final JPEGSegment processTables = this.processTables();
            if (processTables == null || processTables.getSegmentMarker() == 65497) {
                b = true;
            }
            else {
                this.scanHeader = new JPEGScanHeader(this.inputStream);
                if (this.scanHeader.verify()) {
                    continue;
                }
                SWT.error(40);
            }
        }
        if (this.progressive) {
            for (int j = 0; j < this.interleavedMcuRows; ++j) {
                for (int k = 0; k < this.interleavedMcuCols; ++k) {
                    for (int l = 0; l < this.nComponents; ++l) {
                        final int[] array3 = this.frameComponents[this.componentIds[l]];
                        final int n6 = array3[1];
                        final int n7 = array3[2];
                        final int n8 = array3[3];
                        for (int n9 = 0; n9 < n7; ++n9) {
                            for (int n10 = 0; n10 < n6; ++n10) {
                                this.dequantize(this.dataUnit = this.dataUnits[l][(j * n7 + n9) * n8 + k * n6 + n10], l);
                                this.inverseDCT(this.dataUnit);
                                this.storeData(this.dataUnit, l, k, j, n6, n10, n7, n9);
                            }
                        }
                    }
                }
            }
            this.dataUnits = null;
        }
        final ImageData imageData = this.createImageData();
        if (this.progressive && this.loader.hasListeners()) {
            this.loader.notifyListeners(new ImageLoaderEvent(this.loader, imageData, n4, true));
        }
        return new ImageData[] { imageData };
    }
    
    ImageData createImageData() {
        return ImageData.internal_new(this.imageWidth, this.imageHeight, this.nComponents * this.samplePrecision, this.setUpPalette(), (this.nComponents == 1) ? 4 : 1, this.decodeImageComponents(), 0, null, null, -1, -1, 4, 0, 0, 0, 0);
    }
    
    int nextBit() {
        if (this.currentBitCount != 0) {
            --this.currentBitCount;
            this.currentByte *= 2;
            if (this.currentByte > 255) {
                this.currentByte -= 256;
                return 1;
            }
            return 0;
        }
        else {
            ++this.bufferCurrentPosition;
            if (this.bufferCurrentPosition >= 512) {
                this.resetInputBuffer();
                this.bufferCurrentPosition = 0;
            }
            this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
            this.currentBitCount = 8;
            byte b;
            if (this.bufferCurrentPosition == 511) {
                this.resetInputBuffer();
                this.currentBitCount = 8;
                b = this.dataBuffer[0];
            }
            else {
                b = this.dataBuffer[this.bufferCurrentPosition + 1];
            }
            if (this.currentByte == 255) {
                if (b == 0) {
                    ++this.bufferCurrentPosition;
                    --this.currentBitCount;
                    this.currentByte *= 2;
                    if (this.currentByte > 255) {
                        this.currentByte -= 256;
                        return 1;
                    }
                    return 0;
                }
                else {
                    if ((b & 0xFF) + 65280 == 65500) {
                        this.getDNL();
                        return 0;
                    }
                    SWT.error(40);
                    return 0;
                }
            }
            else {
                --this.currentBitCount;
                this.currentByte *= 2;
                if (this.currentByte > 255) {
                    this.currentByte -= 256;
                    return 1;
                }
                return 0;
            }
        }
    }
    
    void processRestartInterval() {
        do {
            ++this.bufferCurrentPosition;
            if (this.bufferCurrentPosition > 511) {
                this.resetInputBuffer();
                this.bufferCurrentPosition = 0;
            }
            this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
        } while (this.currentByte != 255);
        while (this.currentByte == 255) {
            ++this.bufferCurrentPosition;
            if (this.bufferCurrentPosition > 511) {
                this.resetInputBuffer();
                this.bufferCurrentPosition = 0;
            }
            this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
        }
        if (this.currentByte != (65488 + this.nextRestartNumber & 0xFF)) {
            SWT.error(40);
        }
        ++this.bufferCurrentPosition;
        if (this.bufferCurrentPosition > 511) {
            this.resetInputBuffer();
            this.bufferCurrentPosition = 0;
        }
        this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
        this.currentBitCount = 8;
        this.restartsToGo = this.restartInterval;
        this.nextRestartNumber = (this.nextRestartNumber + 1 & 0x7);
        this.precedingDCs = new int[4];
        this.eobrun = 0;
    }
    
    JPEGSegment processTables() {
        JPEGSegment seekUnspecifiedMarker = null;
    Label_0129:
        while (true) {
            seekUnspecifiedMarker = seekUnspecifiedMarker(this.inputStream);
            if (seekUnspecifiedMarker == null) {
                return null;
            }
            if (new JPEGFrameHeader(seekUnspecifiedMarker.reference).verify()) {
                return seekUnspecifiedMarker;
            }
            switch (seekUnspecifiedMarker.getSegmentMarker()) {
                case 65496: {
                    SWT.error(40);
                }
                case 65497:
                case 65498: {
                    break Label_0129;
                }
                case 65499: {
                    this.getDQT();
                    continue;
                }
                case 65476: {
                    this.getDHT();
                    continue;
                }
                case 65484: {
                    this.getDAC();
                    continue;
                }
                case 65501: {
                    this.getDRI();
                    continue;
                }
                case 65504: {
                    this.getAPP0();
                    continue;
                }
                case 65534: {
                    this.getCOM();
                    continue;
                }
                default: {
                    skipSegmentFrom(this.inputStream);
                    continue;
                }
            }
        }
        return seekUnspecifiedMarker;
    }
    
    void quantizeData(final int[] array, final int n) {
        final int[] array2 = this.quantizationTables[this.frameComponents[this.componentIds[n]][0]];
        for (int i = 0; i < array.length; ++i) {
            final int n2 = JPEGFileFormat.ZigZag8x8[i];
            final int n3 = array[n2];
            final int n4 = (n3 < 0) ? (0 - n3) : n3;
            final int n5 = array2[i];
            final int n6 = n4 + (n5 >> 1);
            if (n6 < n5) {
                array[n2] = 0;
            }
            else {
                final int n7 = n6 / n5;
                if (n3 >= 0) {
                    array[n2] = n7;
                }
                else {
                    array[n2] = 0 - n7;
                }
            }
        }
    }
    
    int receive(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 = n2 * 2 + this.nextBit();
        }
        return n2;
    }
    
    void resetInputBuffer() {
        if (this.dataBuffer == null) {
            this.dataBuffer = new byte[512];
        }
        try {
            this.inputStream.read(this.dataBuffer);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        this.currentBitCount = 0;
        this.bufferCurrentPosition = -1;
    }
    
    void resetOutputBuffer() {
        if (this.dataBuffer == null) {
            this.dataBuffer = new byte[512];
        }
        else {
            try {
                this.outputStream.write(this.dataBuffer, 0, this.bufferCurrentPosition);
            }
            catch (IOException ex) {
                SWT.error(39, ex);
            }
        }
        this.bufferCurrentPosition = 0;
    }
    
    static JPEGSegment seekUnspecifiedMarker(final LEDataInputStream leDataInputStream) {
        final byte[] array = new byte[2];
        try {
            while (leDataInputStream.read(array, 0, 1) == 1) {
                if (array[0] == -1) {
                    if (leDataInputStream.read(array, 1, 1) != 1) {
                        return null;
                    }
                    if (array[1] != -1 && array[1] != 0) {
                        leDataInputStream.unread(array);
                        return new JPEGSegment(array);
                    }
                    continue;
                }
            }
            return null;
        }
        catch (IOException ex) {
            SWT.error(39, ex);
            return null;
        }
    }
    
    PaletteData setUpPalette() {
        if (this.nComponents == 1) {
            final RGB[] array = new RGB[256];
            for (int i = 0; i < 256; ++i) {
                array[i] = new RGB(i, i, i);
            }
            return new PaletteData(array);
        }
        return new PaletteData(255, 65280, 16711680);
    }
    
    static void skipSegmentFrom(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] array = new byte[4];
            final JPEGSegment jpegSegment = new JPEGSegment(array);
            if (leDataInputStream.read(array) != array.length) {
                SWT.error(40);
            }
            if (array[0] != -1 || array[1] == 0 || array[1] == -1) {
                SWT.error(40);
            }
            leDataInputStream.skip(jpegSegment.getSegmentLength() - 2);
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
    }
    
    void storeData(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final byte[] array2 = this.imageComponents[n];
        final int n8 = this.frameComponents[this.componentIds[n]][3];
        int n9 = (n3 * n6 + n7) * n8 * 8 + (n2 * n4 + n5) * 8;
        int n10 = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                int n11 = array[n10] + 128;
                if (n11 < 0) {
                    n11 = 0;
                }
                else if (n11 > 255) {
                    n11 = 255;
                }
                array2[n9 + j] = (byte)n11;
                ++n10;
            }
            n9 += n8;
        }
    }
    
    void unloadIntoByteStream(final ImageLoader imageLoader) {
        final ImageData imageData = imageLoader.data[0];
        if (!new JPEGStartOfImage().writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        if (!new JPEGAppn(new byte[] { -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }).writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        this.quantizationTables = new int[4][];
        final JPEGQuantizationTable defaultChrominanceTable = JPEGQuantizationTable.defaultChrominanceTable();
        defaultChrominanceTable.scaleBy(this.encoderQFactor);
        final int[] quantizationTablesKeys = defaultChrominanceTable.getQuantizationTablesKeys();
        final int[][] quantizationTablesValues = defaultChrominanceTable.getQuantizationTablesValues();
        for (int i = 0; i < quantizationTablesKeys.length; ++i) {
            this.quantizationTables[quantizationTablesKeys[i]] = quantizationTablesValues[i];
        }
        final JPEGQuantizationTable defaultLuminanceTable = JPEGQuantizationTable.defaultLuminanceTable();
        defaultLuminanceTable.scaleBy(this.encoderQFactor);
        final int[] quantizationTablesKeys2 = defaultLuminanceTable.getQuantizationTablesKeys();
        final int[][] quantizationTablesValues2 = defaultLuminanceTable.getQuantizationTablesValues();
        for (int j = 0; j < quantizationTablesKeys2.length; ++j) {
            this.quantizationTables[quantizationTablesKeys2[j]] = quantizationTablesValues2[j];
        }
        if (!defaultLuminanceTable.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        if (!defaultChrominanceTable.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        int segmentLength;
        int[][] array;
        int[][] componentParameters;
        int segmentLength2;
        int samplePrecision;
        if (imageData.depth == 1) {
            segmentLength = 11;
            array = new int[][] { { 1, 1, 1, 0, 0 } };
            componentParameters = new int[][] { new int[2] };
            segmentLength2 = 8;
            this.nComponents = 1;
            samplePrecision = 1;
        }
        else {
            segmentLength = 17;
            array = new int[][] { { 0, 2, 2, 0, 0 }, { 1, 1, 1, 0, 0 }, { 1, 1, 1, 0, 0 } };
            componentParameters = new int[][] { new int[2], { 1, 1 }, { 1, 1 } };
            segmentLength2 = 12;
            this.nComponents = 3;
            samplePrecision = 8;
        }
        this.imageWidth = imageData.width;
        this.imageHeight = imageData.height;
        (this.frameHeader = new JPEGFrameHeader(new byte[19])).setSegmentMarker(65472);
        this.frameHeader.setSegmentLength(segmentLength);
        this.frameHeader.setSamplePrecision(samplePrecision);
        this.frameHeader.setSamplesPerLine(this.imageWidth);
        this.frameHeader.setNumberOfLines(this.imageHeight);
        this.frameHeader.setNumberOfImageComponents(this.nComponents);
        this.frameHeader.componentParameters = array;
        this.frameHeader.componentIdentifiers = new int[] { 0, 1, 2 };
        this.frameHeader.initializeContents();
        if (!this.frameHeader.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        this.frameComponents = array;
        this.componentIds = this.frameHeader.componentIdentifiers;
        this.maxH = this.frameHeader.getMaxHFactor();
        this.maxV = this.frameHeader.getMaxVFactor();
        final int n = this.maxH * 8;
        final int n2 = this.maxV * 8;
        this.interleavedMcuCols = (this.imageWidth + n - 1) / n;
        this.interleavedMcuRows = (this.imageHeight + n2 - 1) / n2;
        this.acHuffmanTables = new JPEGHuffmanTable[4];
        this.dcHuffmanTables = new JPEGHuffmanTable[4];
        final JPEGHuffmanTable[] array2 = { JPEGHuffmanTable.getDefaultDCLuminanceTable(), JPEGHuffmanTable.getDefaultDCChrominanceTable(), JPEGHuffmanTable.getDefaultACLuminanceTable(), JPEGHuffmanTable.getDefaultACChrominanceTable() };
        for (int k = 0; k < array2.length; ++k) {
            final JPEGHuffmanTable jpegHuffmanTable = array2[k];
            if (!jpegHuffmanTable.writeToStream(this.outputStream)) {
                SWT.error(39);
            }
            final JPEGHuffmanTable[] allTables = jpegHuffmanTable.getAllTables();
            for (int l = 0; l < allTables.length; ++l) {
                final JPEGHuffmanTable jpegHuffmanTable2 = allTables[l];
                if (jpegHuffmanTable2.getTableClass() == 0) {
                    this.dcHuffmanTables[jpegHuffmanTable2.getTableIdentifier()] = jpegHuffmanTable2;
                }
                else {
                    this.acHuffmanTables[jpegHuffmanTable2.getTableIdentifier()] = jpegHuffmanTable2;
                }
            }
        }
        this.precedingDCs = new int[4];
        (this.scanHeader = new JPEGScanHeader(new byte[14])).setSegmentMarker(65498);
        this.scanHeader.setSegmentLength(segmentLength2);
        this.scanHeader.setNumberOfImageComponents(this.nComponents);
        this.scanHeader.setStartOfSpectralSelection(0);
        this.scanHeader.setEndOfSpectralSelection(63);
        this.scanHeader.componentParameters = componentParameters;
        this.scanHeader.initializeContents();
        if (!this.scanHeader.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        this.convertImageToYCbCr(imageData);
        this.resetOutputBuffer();
        this.currentByte = 0;
        this.currentBitCount = 0;
        this.encodeScan();
        if (!new JPEGEndOfImage().writeToStream(this.outputStream)) {
            SWT.error(39);
        }
    }
}
