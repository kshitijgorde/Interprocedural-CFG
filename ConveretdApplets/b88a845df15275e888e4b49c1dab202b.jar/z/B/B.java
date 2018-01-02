// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.Raster;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.awt.Rectangle;
import java.awt.image.ComponentSampleModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.util.zip.Deflater;
import java.io.RandomAccessFile;
import java.io.File;
import java.util.SortedSet;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.util.TreeSet;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.util.Iterator;
import java.awt.image.RenderedImage;
import java.io.OutputStream;

public class B extends r
{
    private static final int W = -1;
    private static final int h = 0;
    private static final int N = 1;
    private static final int T = 2;
    private static final int R = 3;
    private static final int K = 4;
    private static final int Y = 5;
    private static final int P = 6;
    private static final int _ = 7;
    private static final int Q = 8;
    private static final int f = 1;
    private static final int S = 2;
    private static final int c = 3;
    private static final int O = 4;
    private static final int M = 7;
    private static final int L = 32773;
    private static final int V = 32946;
    private static final int g = 347;
    private static final int a = 530;
    private static final int i = 531;
    private static final int b = 532;
    private static final int U = 0;
    private static final int Z = 1;
    private static final int X = 2;
    private static final int e = 8;
    private static final int[] d;
    
    private static final char[] A(final int[] array) {
        final int length = array.length;
        final char[] array2 = new char[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = (char)(array[i] & 0xFFFF);
        }
        return array2;
    }
    
    public B(final OutputStream outputStream, final M m) {
        super(outputStream, m);
        if (this.B == null) {
            this.B = new c();
        }
    }
    
    public void A(final RenderedImage renderedImage) throws IOException {
        this.C();
        final c c = (c)this.B;
        final Iterator r = c.R();
        if (r != null) {
            int a = 8;
            RenderedImage renderedImage2 = renderedImage;
            c c2 = c;
            boolean hasNext;
            do {
                hasNext = r.hasNext();
                a = this.A(renderedImage2, c2, a, !hasNext);
                if (hasNext) {
                    final RenderedImage next = r.next();
                    if (next instanceof RenderedImage) {
                        renderedImage2 = next;
                        c2 = c;
                    }
                    else {
                        if (!(next instanceof Object[])) {
                            continue;
                        }
                        final Object[] array = (Object)next;
                        renderedImage2 = (RenderedImage)array[0];
                        c2 = (c)array[1];
                    }
                }
            } while (hasNext);
        }
        else {
            this.A(renderedImage, c, 8, true);
        }
    }
    
    private int A(final RenderedImage renderedImage, final c c, final int n, final boolean b) throws IOException {
        final int n2 = c.N();
        final boolean o = c.O();
        final int minX = renderedImage.getMinX();
        final int minY = renderedImage.getMinY();
        final int width = renderedImage.getWidth();
        final int height = renderedImage.getHeight();
        final SampleModel sampleModel = renderedImage.getSampleModel();
        final int[] sampleSize = sampleModel.getSampleSize();
        for (int i = 1; i < sampleSize.length; ++i) {
            if (sampleSize[i] != sampleSize[0]) {
                throw new Error(m.A("TIFFImageEncoder0"));
            }
        }
        final int numBands = sampleModel.getNumBands();
        if ((sampleSize[0] == 1 || sampleSize[0] == 4) && numBands != 1) {
            throw new Error(m.A("TIFFImageEncoder1"));
        }
        final int dataType = sampleModel.getDataType();
        switch (dataType) {
            case 0: {
                if (sampleSize[0] != 1 && sampleSize[0] != 4 && sampleSize[0] != 8) {
                    throw new Error(m.A("TIFFImageEncoder2"));
                }
                break;
            }
            case 1:
            case 2: {
                if (sampleSize[0] != 16) {
                    throw new Error(m.A("TIFFImageEncoder3"));
                }
                break;
            }
            case 3:
            case 4: {
                if (sampleSize[0] != 32) {
                    throw new Error(m.A("TIFFImageEncoder4"));
                }
                break;
            }
            default: {
                throw new Error(m.A("TIFFImageEncoder5"));
            }
        }
        final boolean b2 = dataType == 2 || dataType == 1;
        final ColorModel colorModel = renderedImage.getColorModel();
        if (colorModel != null && colorModel instanceof IndexColorModel && dataType != 0) {
            throw new Error(m.A("TIFFImageEncoder6"));
        }
        int n3 = 0;
        int[] array = null;
        int n4 = -1;
        int n5 = 0;
        int n6 = 0;
        if (colorModel instanceof IndexColorModel) {
            final IndexColorModel indexColorModel = (IndexColorModel)colorModel;
            final int mapSize = indexColorModel.getMapSize();
            if (sampleSize[0] == 1 && numBands == 1) {
                if (mapSize != 2) {
                    throw new IllegalArgumentException(m.A("TIFFImageEncoder7"));
                }
                final byte[] array2 = new byte[mapSize];
                indexColorModel.getReds(array2);
                final byte[] array3 = new byte[mapSize];
                indexColorModel.getGreens(array3);
                final byte[] array4 = new byte[mapSize];
                indexColorModel.getBlues(array4);
                if ((array2[0] & 0xFF) == 0x0 && (array2[1] & 0xFF) == 0xFF && (array3[0] & 0xFF) == 0x0 && (array3[1] & 0xFF) == 0xFF && (array4[0] & 0xFF) == 0x0 && (array4[1] & 0xFF) == 0xFF) {
                    n4 = 1;
                }
                else if ((array2[0] & 0xFF) == 0xFF && (array2[1] & 0xFF) == 0x0 && (array3[0] & 0xFF) == 0xFF && (array3[1] & 0xFF) == 0x0 && (array4[0] & 0xFF) == 0xFF && (array4[1] & 0xFF) == 0x0) {
                    n4 = 0;
                }
                else {
                    n4 = 3;
                }
            }
            else if (numBands == 1) {
                n4 = 3;
            }
        }
        else if (colorModel == null) {
            if (sampleSize[0] == 1 && numBands == 1) {
                n4 = 1;
            }
            else {
                n4 = 8;
                if (numBands > 1) {
                    n5 = numBands - 1;
                }
            }
        }
        else {
            final ColorSpace colorSpace = colorModel.getColorSpace();
            switch (colorSpace.getType()) {
                case 9: {
                    n4 = 5;
                    break;
                }
                case 6: {
                    n4 = 2;
                    break;
                }
                case 1: {
                    n4 = 7;
                    break;
                }
                case 5: {
                    if (n2 == 7 && c.Q()) {
                        n4 = 6;
                        break;
                    }
                    n4 = 4;
                    break;
                }
                case 3: {
                    n4 = 6;
                    break;
                }
                default: {
                    n4 = 8;
                    break;
                }
            }
            if (n4 == 8) {
                n5 = numBands - 1;
            }
            else if (numBands > 1) {
                n5 = numBands - colorSpace.getNumComponents();
            }
            if (n5 == 1 && colorModel.hasAlpha()) {
                n6 = (colorModel.isAlphaPremultiplied() ? 1 : 2);
            }
        }
        if (n4 == -1) {
            throw new Error(m.A("TIFFImageEncoder8"));
        }
        if (n2 == 7) {
            if (n4 == 3) {
                throw new Error(m.A("TIFFImageEncoder11"));
            }
            if (sampleSize[0] != 8 || (n4 != 2 && n4 != 4 && n4 != 6)) {
                throw new Error(m.A("TIFFImageEncoder9"));
            }
        }
        int n7 = 0;
        switch (n4) {
            case 0: {
                n7 = 0;
                break;
            }
            case 1: {
                n7 = 1;
                break;
            }
            case 2:
            case 8: {
                n7 = 1;
                break;
            }
            case 3: {
                n7 = 3;
                final IndexColorModel indexColorModel2 = (IndexColorModel)colorModel;
                final int mapSize2 = indexColorModel2.getMapSize();
                final byte[] array5 = new byte[mapSize2];
                indexColorModel2.getReds(array5);
                final byte[] array6 = new byte[mapSize2];
                indexColorModel2.getGreens(array6);
                final byte[] array7 = new byte[mapSize2];
                indexColorModel2.getBlues(array7);
                int n8 = 0;
                int n9 = mapSize2;
                int n10 = 2 * mapSize2;
                array = new int[mapSize2 * 3];
                for (int j = 0; j < mapSize2; ++j) {
                    array[n8++] = (array5[j] << 8 & 0xFFFF);
                    array[n9++] = (array6[j] << 8 & 0xFFFF);
                    array[n10++] = (array7[j] << 8 & 0xFFFF);
                }
                n3 = mapSize2 * 3;
                break;
            }
            case 4: {
                n7 = 2;
                break;
            }
            case 5: {
                n7 = 5;
                break;
            }
            case 6: {
                n7 = 6;
                break;
            }
            case 7: {
                n7 = 8;
                break;
            }
            default: {
                throw new Error(m.A("TIFFImageEncoder8"));
            }
        }
        int n11;
        int n12;
        if (o) {
            n11 = ((c.P() > 0) ? c.P() : renderedImage.getTileWidth());
            n12 = ((c.X() > 0) ? c.X() : renderedImage.getTileHeight());
        }
        else {
            n11 = width;
            n12 = ((c.X() > 0) ? c.X() : 8);
        }
        R m = null;
        if (n2 == 7) {
            m = c.M();
            int e = m.E(0);
            int g = m.G(0);
            for (int k = 1; k < numBands; ++k) {
                final int e2 = m.E(k);
                if (e2 > e) {
                    e = e2;
                }
                final int g2 = m.G(k);
                if (g2 > g) {
                    g = g2;
                }
            }
            final int n13 = 8 * g;
            n12 = (int)(n12 / n13 + 0.5f) * n13;
            if (n12 < n13) {
                n12 = n13;
            }
            if (o) {
                final int n14 = 8 * e;
                n11 = (int)(n11 / n14 + 0.5f) * n14;
                if (n11 < n14) {
                    n11 = n14;
                }
            }
        }
        int n15;
        if (o) {
            n15 = (width + n11 - 1) / n11 * ((height + n12 - 1) / n12);
        }
        else {
            n15 = (int)Math.ceil(height / n12);
        }
        final long[] array8 = new long[n15];
        final long n16 = (long)Math.ceil(sampleSize[0] / 8.0 * n11 * numBands);
        final long n17 = n16 * n12;
        for (int l = 0; l < n15; ++l) {
            array8[l] = n17;
        }
        if (!o) {
            array8[n15 - 1] = (height - n12 * (n15 - 1)) * n16;
        }
        final long n18 = n17 * (n15 - 1) + array8[n15 - 1];
        final long[] array9 = new long[n15];
        final TreeSet<p> set = new TreeSet<p>();
        set.add(new p(256, 4, 1, new long[] { width }));
        set.add(new p(257, 4, 1, new long[] { height }));
        set.add(new p(258, 3, numBands, A(sampleSize)));
        set.add(new p(259, 3, 1, new char[] { (char)n2 }));
        set.add(new p(262, 3, 1, new char[] { (char)n7 }));
        if (!o) {
            set.add(new p(273, 4, n15, array9));
        }
        set.add(new p(277, 3, 1, new char[] { (char)numBands }));
        if (!o) {
            set.add(new p(278, 4, 1, new long[] { n12 }));
            set.add(new p(279, 4, n15, array8));
        }
        if (array != null) {
            set.add(new p(320, 3, n3, A(array)));
        }
        if (o) {
            set.add(new p(322, 4, 1, new long[] { n11 }));
            set.add(new p(323, 4, 1, new long[] { n12 }));
            set.add(new p(324, 4, n15, array9));
            set.add(new p(325, 4, n15, array8));
        }
        if (n5 > 0) {
            final int[] array10 = new int[n5];
            for (int n19 = 0; n19 < n5; ++n19) {
                array10[n19] = n6;
            }
            set.add(new p(338, 3, n5, A(array10)));
        }
        if (dataType != 0) {
            final int[] array11 = new int[numBands];
            if (dataType == 4) {
                array11[0] = 3;
            }
            else if (dataType == 1) {
                array11[0] = 1;
            }
            else {
                array11[0] = 2;
            }
            for (int n20 = 1; n20 < numBands; ++n20) {
                array11[n20] = array11[0];
            }
            set.add(new p(339, 3, numBands, A(array11)));
        }
        final boolean s = c.S();
        final boolean u = c.U();
        final boolean v = c.V();
        i i2 = null;
        if ((n4 == 1 || n4 == 0) && (n2 == 2 || n2 == 3 || n2 == 4)) {
            i2 = new i(s);
            set.add(new p(266, 3, 1, new char[] { s ? 2 : 1 }));
            if (n2 == 3) {
                long n21 = 0L;
                if (u) {
                    n21 |= 0x1L;
                }
                if (v) {
                    n21 |= 0x4L;
                }
                set.add(new p(292, 4, 1, new long[] { n21 }));
            }
            else if (n2 == 4) {
                set.add(new p(293, 4, 1, new long[] { 0L }));
            }
        }
        JPEGEncodeParam jpegEncodeParam = null;
        JPEGImageEncoder jpegEncoder = null;
        int n22 = 0;
        if (n2 == 7) {
            n22 = 0;
            switch (n4) {
                case 2:
                case 3: {
                    n22 = 1;
                    break;
                }
                case 4: {
                    n22 = 2;
                    break;
                }
                case 6: {
                    n22 = 3;
                    break;
                }
            }
            final Raster tile = renderedImage.getTile(0, 0);
            jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(tile, n22);
            k.A(m, jpegEncodeParam, numBands);
            if (m.L()) {
                jpegEncodeParam.setImageInfoValid(false);
                jpegEncodeParam.setTableInfoValid(true);
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                JPEGCodec.createJPEGEncoder((OutputStream)byteArrayOutputStream, jpegEncodeParam).encode(tile);
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                set.add(new p(347, 7, byteArray.length, byteArray));
                jpegEncoder = null;
            }
        }
        if (n4 == 6) {
            int e3 = 1;
            int g3 = 1;
            if (n2 == 7) {
                e3 = m.E(0);
                g3 = m.G(0);
                for (int n23 = 1; n23 < numBands; ++n23) {
                    final int e4 = m.E(n23);
                    if (e4 > e3) {
                        e3 = e4;
                    }
                    final int g4 = m.G(n23);
                    if (g4 > g3) {
                        g3 = g4;
                    }
                }
            }
            set.add(new p(530, 3, 2, new char[] { (char)e3, (char)g3 }));
            set.add(new p(531, 3, 1, new char[] { (n2 == 7) ? '\u0001' : '\u0002' }));
            long[][] array12;
            if (n2 == 7) {
                array12 = new long[][] { { 0L, 1L }, { 255L, 1L }, { 128L, 1L }, { 255L, 1L }, { 128L, 1L }, { 255L, 1L } };
            }
            else {
                array12 = new long[][] { { 15L, 1L }, { 235L, 1L }, { 128L, 1L }, { 240L, 1L }, { 128L, 1L }, { 240L, 1L } };
            }
            set.add(new p(532, 5, 6, array12));
        }
        final p[] w = c.W();
        if (w != null) {
            final ArrayList list = new ArrayList<Integer>(set.size());
            final Iterator<Object> iterator = set.iterator();
            while (iterator.hasNext()) {
                list.add(new Integer(iterator.next().K()));
            }
            for (final p p4 : w) {
                final Integer n25 = new Integer(p4.K());
                if (!list.contains(n25)) {
                    set.add(p4);
                    list.add(n25);
                }
            }
        }
        final int a = this.A(set);
        array9[0] = n + a;
        OutputStream a2 = null;
        byte[] array13 = null;
        File tempFile = null;
        int n26 = 0;
        int n27 = 0;
        Deflater deflater = null;
        boolean b3 = false;
        if (n2 == 1) {
            int n28 = 0;
            if (sampleSize[0] == 16 && array9[0] % 2L != 0L) {
                n28 = 1;
                final long[] array14 = array9;
                final int n29 = 0;
                ++array14[n29];
            }
            else if (sampleSize[0] == 32 && array9[0] % 4L != 0L) {
                n28 = (int)(4L - array9[0] % 4L);
                final long[] array15 = array9;
                final int n30 = 0;
                array15[n30] += n28;
            }
            for (int n31 = 1; n31 < n15; ++n31) {
                array9[n31] = array9[n31 - 1] + array8[n31 - 1];
            }
            if (!b) {
                n26 = (int)(array9[0] + n18);
                if (n26 % 2 != 0) {
                    ++n26;
                    n27 = 1;
                }
            }
            this.A(n, set, n26);
            if (n28 != 0) {
                for (int n32 = 0; n32 < n28; ++n32) {
                    this.A.write(0);
                }
            }
        }
        else {
            if (this.A instanceof g) {
                ((g)this.A).A(array9[0]);
            }
            else {
                a2 = this.A;
                try {
                    tempFile = File.createTempFile("jai-SOS-", ".tmp");
                    tempFile.deleteOnExit();
                    this.A = new g(new RandomAccessFile(tempFile, "rw"));
                }
                catch (Exception ex) {
                    tempFile = null;
                    this.A = new ByteArrayOutputStream((int)n18);
                }
            }
            int n33 = 0;
            switch (n2) {
                case 2: {
                    n33 = (int)Math.ceil(((n11 + 1) / 2 * 9 + 2) / 8.0);
                    break;
                }
                case 3:
                case 4: {
                    n33 = n12 * ((int)Math.ceil(((n11 + 1) / 2 * 9 + 2) / 8.0) + 2) + 12;
                    break;
                }
                case 32773: {
                    n33 = (int)(n17 + (n16 + 127L) / 128L * n12);
                    break;
                }
                case 7: {
                    n33 = 0;
                    if (n4 == 6 && colorModel != null && colorModel.getColorSpace().getType() == 5) {
                        b3 = true;
                        break;
                    }
                    break;
                }
                case 32946: {
                    n33 = (int)n17;
                    deflater = new Deflater(c.T());
                    break;
                }
                default: {
                    n33 = 0;
                    break;
                }
            }
            if (n33 != 0) {
                array13 = new byte[n33];
            }
        }
        int[] pixels = null;
        float[] pixels2 = null;
        final boolean b4 = (sampleSize[0] == 1 && sampleModel instanceof MultiPixelPackedSampleModel && dataType == 0) || (sampleSize[0] == 8 && sampleModel instanceof ComponentSampleModel);
        byte[] array16 = null;
        if (n2 != 7) {
            if (dataType == 0) {
                array16 = new byte[n12 * n11 * numBands];
            }
            else if (b2) {
                array16 = new byte[2 * n12 * n11 * numBands];
            }
            else if (dataType == 3 || dataType == 4) {
                array16 = new byte[4 * n12 * n11 * numBands];
            }
        }
        final int n34 = minY + height;
        final int n35 = minX + width;
        int n36 = 0;
        for (int n37 = minY; n37 < n34; n37 += n12) {
            final int n38 = o ? n12 : Math.min(n12, n34 - n37);
            final int n39 = n38 * n11 * numBands;
            for (int n40 = minX; n40 < n35; n40 += n11) {
                final Raster data = renderedImage.getData(new Rectangle(n40, n37, n11, n38));
                int n41 = 0;
                if (n2 != 7) {
                    if (b4) {
                        if (sampleSize[0] == 8) {
                            final ComponentSampleModel componentSampleModel = (ComponentSampleModel)data.getSampleModel();
                            final int[] bankIndices = componentSampleModel.getBankIndices();
                            final int[] bandOffsets = componentSampleModel.getBandOffsets();
                            final int pixelStride = componentSampleModel.getPixelStride();
                            final int scanlineStride = componentSampleModel.getScanlineStride();
                            if (pixelStride != numBands || scanlineStride != n16) {
                                n41 = 0;
                            }
                            else {
                                n41 = 1;
                                for (int n42 = 0; n41 != 0 && n42 < numBands; ++n42) {
                                    if (bankIndices[n42] != 0 || bandOffsets[n42] != n42) {
                                        n41 = 0;
                                    }
                                }
                            }
                        }
                        else {
                            final MultiPixelPackedSampleModel multiPixelPackedSampleModel = (MultiPixelPackedSampleModel)data.getSampleModel();
                            if (multiPixelPackedSampleModel.getNumBands() == 1 && multiPixelPackedSampleModel.getDataBitOffset() == 0 && multiPixelPackedSampleModel.getPixelBitStride() == 1) {
                                n41 = 1;
                            }
                        }
                    }
                    if (n41 == 0) {
                        if (dataType == 4) {
                            pixels2 = data.getPixels(n40, n37, n11, n38, pixels2);
                        }
                        else {
                            pixels = data.getPixels(n40, n37, n11, n38, pixels);
                        }
                    }
                }
                int n43 = 0;
                switch (sampleSize[0]) {
                    case 1: {
                        if (n41 != 0) {
                            final byte[] data2 = ((DataBufferByte)data.getDataBuffer()).getData();
                            final MultiPixelPackedSampleModel multiPixelPackedSampleModel2 = (MultiPixelPackedSampleModel)data.getSampleModel();
                            final int scanlineStride2 = multiPixelPackedSampleModel2.getScanlineStride();
                            int offset = multiPixelPackedSampleModel2.getOffset(n40 - data.getSampleModelTranslateX(), n37 - data.getSampleModelTranslateY());
                            if (scanlineStride2 == (int)n16) {
                                System.arraycopy(data2, offset, array16, 0, (int)n16 * n38);
                            }
                            else {
                                int n44 = 0;
                                for (int n45 = 0; n45 < n38; ++n45) {
                                    System.arraycopy(data2, offset, array16, n44, (int)n16);
                                    offset += scanlineStride2;
                                    n44 += (int)n16;
                                }
                            }
                        }
                        else {
                            int n46 = 0;
                            for (int n47 = 0; n47 < n38; ++n47) {
                                for (int n48 = 0; n48 < n11 / 8; ++n48) {
                                    array16[n43++] = (byte)(pixels[n46++] << 7 | pixels[n46++] << 6 | pixels[n46++] << 5 | pixels[n46++] << 4 | pixels[n46++] << 3 | pixels[n46++] << 2 | pixels[n46++] << 1 | pixels[n46++]);
                                }
                                if (n11 % 8 > 0) {
                                    int n49 = 0;
                                    for (int n50 = 0; n50 < n11 % 8; ++n50) {
                                        n49 |= pixels[n46++] << 7 - n50;
                                    }
                                    array16[n43++] = (byte)n49;
                                }
                            }
                        }
                        if (n2 == 1) {
                            this.A.write(array16, 0, n38 * ((n11 + 7) / 8));
                            break;
                        }
                        if (n2 == 2) {
                            final int n51 = (n11 + 7) / 8;
                            int n52 = 0;
                            int n53 = 0;
                            for (int n54 = 0; n54 < n38; ++n54) {
                                final int a3 = i2.A(array16, n52, 0, n11, array13);
                                this.A.write(array13, 0, a3);
                                n52 += n51;
                                n53 += a3;
                            }
                            array8[n36++] = n53;
                            break;
                        }
                        if (n2 == 3) {
                            final int a4 = i2.A(!u, v, array16, (n11 + 7) / 8, 0, n11, n38, array13);
                            array8[n36++] = a4;
                            this.A.write(array13, 0, a4);
                            break;
                        }
                        if (n2 == 4) {
                            final int a5 = i2.A(array16, (n11 + 7) / 8, 0, n11, n38, array13);
                            array8[n36++] = a5;
                            this.A.write(array13, 0, a5);
                            break;
                        }
                        if (n2 == 32773) {
                            final int a6 = A(array16, n38, (int)n16, array13);
                            array8[n36++] = a6;
                            this.A.write(array13, 0, a6);
                            break;
                        }
                        if (n2 == 32946) {
                            final int a7 = A(deflater, array16, array13);
                            array8[n36++] = a7;
                            this.A.write(array13, 0, a7);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        int n55 = 0;
                        for (int n56 = 0; n56 < n38; ++n56) {
                            for (int n57 = 0; n57 < n11 / 2; ++n57) {
                                array16[n43++] = (byte)(pixels[n55++] << 4 | pixels[n55++]);
                            }
                            if (n11 % 2 == 1) {
                                array16[n43++] = (byte)(pixels[n55++] << 4);
                            }
                        }
                        if (n2 == 1) {
                            this.A.write(array16, 0, n38 * ((n11 + 1) / 2));
                            break;
                        }
                        if (n2 == 32773) {
                            final int a8 = A(array16, n38, (int)n16, array13);
                            array8[n36++] = a8;
                            this.A.write(array13, 0, a8);
                            break;
                        }
                        if (n2 == 32946) {
                            final int a9 = A(deflater, array16, array13);
                            array8[n36++] = a9;
                            this.A.write(array13, 0, a9);
                            break;
                        }
                        break;
                    }
                    case 8: {
                        if (n2 != 7) {
                            if (n41 != 0) {
                                final byte[] data3 = ((DataBufferByte)data.getDataBuffer()).getData();
                                final ComponentSampleModel componentSampleModel2 = (ComponentSampleModel)data.getSampleModel();
                                int offset2 = componentSampleModel2.getOffset(n40 - data.getSampleModelTranslateX(), n37 - data.getSampleModelTranslateY());
                                final int scanlineStride3 = componentSampleModel2.getScanlineStride();
                                if (scanlineStride3 == (int)n16) {
                                    System.arraycopy(data3, offset2, array16, 0, (int)n16 * n38);
                                }
                                else {
                                    int n58 = 0;
                                    for (int n59 = 0; n59 < n38; ++n59) {
                                        System.arraycopy(data3, offset2, array16, n58, (int)n16);
                                        offset2 += scanlineStride3;
                                        n58 += (int)n16;
                                    }
                                }
                            }
                            else {
                                for (int n60 = 0; n60 < n39; ++n60) {
                                    array16[n60] = (byte)pixels[n60];
                                }
                            }
                        }
                        if (n2 == 1) {
                            this.A.write(array16, 0, n39);
                            break;
                        }
                        if (n2 == 32773) {
                            final int a10 = A(array16, n38, (int)n16, array13);
                            array8[n36++] = a10;
                            this.A.write(array13, 0, a10);
                            break;
                        }
                        if (n2 == 7) {
                            final long a11 = this.A(this.A);
                            if (jpegEncoder == null || jpegEncodeParam.getWidth() != data.getWidth() || jpegEncodeParam.getHeight() != data.getHeight()) {
                                jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(data, n22);
                                k.A(m, jpegEncodeParam, numBands);
                                jpegEncoder = JPEGCodec.createJPEGEncoder(this.A, jpegEncodeParam);
                            }
                            if (b3) {
                                WritableRaster writableRaster;
                                if (data instanceof WritableRaster) {
                                    writableRaster = (WritableRaster)data;
                                }
                                else {
                                    writableRaster = data.createCompatibleWritableRaster();
                                    writableRaster.setRect(data);
                                }
                                if (writableRaster.getMinX() != 0 || writableRaster.getMinY() != 0) {
                                    writableRaster = writableRaster.createWritableTranslatedChild(0, 0);
                                }
                                jpegEncoder.encode(new BufferedImage(colorModel, writableRaster, false, null));
                            }
                            else {
                                jpegEncoder.encode(data.createTranslatedChild(0, 0));
                            }
                            array8[n36++] = (int)(this.A(this.A) - a11);
                            break;
                        }
                        if (n2 == 32946) {
                            final int a12 = A(deflater, array16, array13);
                            array8[n36++] = a12;
                            this.A.write(array13, 0, a12);
                            break;
                        }
                        break;
                    }
                    case 16: {
                        int n61 = 0;
                        for (int n62 = 0; n62 < n39; ++n62) {
                            final short n63 = (short)pixels[n62];
                            array16[n61++] = (byte)((n63 & 0xFF00) >> 8);
                            array16[n61++] = (byte)(n63 & 0xFF);
                        }
                        if (n2 == 1) {
                            this.A.write(array16, 0, n39 * 2);
                            break;
                        }
                        if (n2 == 32773) {
                            final int a13 = A(array16, n38, (int)n16, array13);
                            array8[n36++] = a13;
                            this.A.write(array13, 0, a13);
                            break;
                        }
                        if (n2 == 32946) {
                            final int a14 = A(deflater, array16, array13);
                            array8[n36++] = a14;
                            this.A.write(array13, 0, a14);
                            break;
                        }
                        break;
                    }
                    case 32: {
                        if (dataType == 3) {
                            int n64 = 0;
                            for (final int n66 : pixels) {
                                array16[n64++] = (byte)((n66 & 0xFF000000) >> 24);
                                array16[n64++] = (byte)((n66 & 0xFF0000) >> 16);
                                array16[n64++] = (byte)((n66 & 0xFF00) >> 8);
                                array16[n64++] = (byte)(n66 & 0xFF);
                            }
                        }
                        else {
                            int n67 = 0;
                            for (int n68 = 0; n68 < n39; ++n68) {
                                final int floatToIntBits = Float.floatToIntBits(pixels2[n68]);
                                array16[n67++] = (byte)((floatToIntBits & 0xFF000000) >> 24);
                                array16[n67++] = (byte)((floatToIntBits & 0xFF0000) >> 16);
                                array16[n67++] = (byte)((floatToIntBits & 0xFF00) >> 8);
                                array16[n67++] = (byte)(floatToIntBits & 0xFF);
                            }
                        }
                        if (n2 == 1) {
                            this.A.write(array16, 0, n39 * 4);
                            break;
                        }
                        if (n2 == 32773) {
                            final int a15 = A(array16, n38, (int)n16, array13);
                            array8[n36++] = a15;
                            this.A.write(array13, 0, a15);
                            break;
                        }
                        if (n2 == 32946) {
                            final int a16 = A(deflater, array16, array13);
                            array8[n36++] = a16;
                            this.A.write(array13, 0, a16);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        if (n2 == 1) {
            if (n27 != 0) {
                this.A.write(0);
            }
        }
        else {
            int n69 = 0;
            for (int n70 = 1; n70 < n15; ++n70) {
                final int n71 = (int)array8[n70 - 1];
                n69 += n71;
                array9[n70] = array9[n70 - 1] + n71;
            }
            final int n72 = n69 + (int)array8[n15 - 1];
            n26 = (b ? 0 : (n + a + n72));
            if (n26 % 2 != 0) {
                ++n26;
                n27 = 1;
            }
            if (a2 == null) {
                if (n27 != 0) {
                    this.A.write(0);
                }
                final g g5 = (g)this.A;
                final long a17 = g5.A();
                g5.A(n);
                this.A(n, set, n26);
                g5.A(a17);
            }
            else if (tempFile != null) {
                this.A.close();
                final FileInputStream fileInputStream = new FileInputStream(tempFile);
                this.A = a2;
                this.A(n, set, n26);
                final byte[] array17 = new byte[8192];
                int read;
                for (int n73 = 0; n73 < n72; n73 += read) {
                    read = fileInputStream.read(array17);
                    if (read == -1) {
                        break;
                    }
                    this.A.write(array17, 0, read);
                }
                fileInputStream.close();
                tempFile.delete();
                if (n27 != 0) {
                    this.A.write(0);
                }
            }
            else {
                if (!(this.A instanceof ByteArrayOutputStream)) {
                    throw new IllegalStateException();
                }
                final ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream)this.A;
                this.A = a2;
                this.A(n, set, n26);
                byteArrayOutputStream2.writeTo(this.A);
                if (n27 != 0) {
                    this.A.write(0);
                }
            }
        }
        return n26;
    }
    
    private int A(final SortedSet set) {
        int n = 2 + set.size() * 12 + 4;
        final Iterator<p> iterator = set.iterator();
        while (iterator.hasNext()) {
            final int a = A(iterator.next());
            if (a > 4) {
                n += a;
            }
        }
        return n;
    }
    
    private void C() throws IOException {
        this.A.write(77);
        this.A.write(77);
        this.A.write(0);
        this.A.write(42);
        this.A(8L);
    }
    
    private void A(final int n, final SortedSet set, final int n2) throws IOException {
        final int size = set.size();
        long n3 = n + 12 * size + 4 + 2;
        final ArrayList<p> list = new ArrayList<p>();
        this.E(size);
        for (final p p3 : set) {
            this.E(p3.K());
            final int h = p3.H();
            this.E(h);
            final int j = p3.J();
            final int a = A(p3);
            this.A((h == 2) ? ((long)a) : ((long)j));
            if (a > 4) {
                this.A(n3);
                n3 += a;
                list.add(p3);
            }
            else {
                this.B(p3);
            }
        }
        this.A(n2);
        for (int i = 0; i < list.size(); ++i) {
            this.C(list.get(i));
        }
    }
    
    private static final int A(final p p) {
        final int h = p.H();
        final int j = p.J();
        int n = 0;
        if (h == 2) {
            for (int i = 0; i < j; ++i) {
                final byte[] bytes = p.C(i).getBytes();
                n += bytes.length;
                if (bytes[bytes.length - 1] != 0) {
                    ++n;
                }
            }
        }
        else {
            n = j * B.d[h];
        }
        return n;
    }
    
    private void B(final p p) throws IOException {
        final int h = p.H();
        final int j = p.J();
        switch (h) {
            case 1:
            case 6:
            case 7: {
                final byte[] a = p.A();
                for (int i = 0; i < j; ++i) {
                    this.A.write(a[i]);
                }
                for (int k = 0; k < 4 - j; ++k) {
                    this.A.write(0);
                }
                break;
            }
            case 3: {
                final char[] l = p.L();
                for (int n = 0; n < j; ++n) {
                    this.E(l[n]);
                }
                for (int n2 = 0; n2 < 2 - j; ++n2) {
                    this.E(0);
                }
                break;
            }
            case 8: {
                final short[] b = p.B();
                for (int n3 = 0; n3 < j; ++n3) {
                    this.E(b[n3]);
                }
                for (int n4 = 0; n4 < 2 - j; ++n4) {
                    this.E(0);
                }
                break;
            }
            case 4: {
                this.A(p.D(0));
                break;
            }
            case 9: {
                this.A(p.E(0));
                break;
            }
            case 11: {
                this.A(Float.floatToIntBits(p.B(0)));
                break;
            }
            case 2: {
                int n5 = 0;
                for (int n6 = 0; n6 < j; ++n6) {
                    final byte[] bytes = p.C(n6).getBytes();
                    this.A.write(bytes);
                    n5 += bytes.length;
                    if (bytes[bytes.length - 1] != 0) {
                        this.A.write(0);
                        ++n5;
                    }
                }
                for (int n7 = 0; n7 < 4 - n5; ++n7) {
                    this.A.write(0);
                }
                break;
            }
            default: {
                throw new Error(m.A("TIFFImageEncoder10"));
            }
        }
    }
    
    private void C(final p p) throws IOException {
        final int h = p.H();
        final int j = p.J();
        switch (h) {
            case 1:
            case 6:
            case 7: {
                final byte[] a = p.A();
                for (int i = 0; i < j; ++i) {
                    this.A.write(a[i]);
                }
                break;
            }
            case 3: {
                final char[] l = p.L();
                for (int k = 0; k < j; ++k) {
                    this.E(l[k]);
                }
                break;
            }
            case 8: {
                final short[] b = p.B();
                for (int n = 0; n < j; ++n) {
                    this.E(b[n]);
                }
                break;
            }
            case 4: {
                final long[] f = p.F();
                for (int n2 = 0; n2 < j; ++n2) {
                    this.A(f[n2]);
                }
                break;
            }
            case 9: {
                final int[] e = p.E();
                for (int n3 = 0; n3 < j; ++n3) {
                    this.A(e[n3]);
                }
                break;
            }
            case 11: {
                final float[] c = p.C();
                for (int n4 = 0; n4 < j; ++n4) {
                    this.A(Float.floatToIntBits(c[n4]));
                }
                break;
            }
            case 12: {
                final double[] g = p.G();
                for (int n5 = 0; n5 < j; ++n5) {
                    final long doubleToLongBits = Double.doubleToLongBits(g[n5]);
                    this.A((int)(doubleToLongBits >> 32));
                    this.A((int)(doubleToLongBits & -1L));
                }
                break;
            }
            case 5: {
                final long[][] d = p.D();
                for (int n6 = 0; n6 < j; ++n6) {
                    this.A(d[n6][0]);
                    this.A(d[n6][1]);
                }
                break;
            }
            case 10: {
                final int[][] m = p.I();
                for (int n7 = 0; n7 < j; ++n7) {
                    this.A(m[n7][0]);
                    this.A(m[n7][1]);
                }
                break;
            }
            case 2: {
                for (int n8 = 0; n8 < j; ++n8) {
                    final byte[] bytes = p.C(n8).getBytes();
                    this.A.write(bytes);
                    if (bytes[bytes.length - 1] != 0) {
                        this.A.write(0);
                    }
                }
                break;
            }
            default: {
                throw new Error(m.A("TIFFImageEncoder10"));
            }
        }
    }
    
    private void E(final int n) throws IOException {
        this.A.write((n & 0xFF00) >>> 8);
        this.A.write(n & 0xFF);
    }
    
    private void A(final long n) throws IOException {
        this.A.write((int)((n & 0xFFFFFFFFFF000000L) >>> 24));
        this.A.write((int)((n & 0xFF0000L) >>> 16));
        this.A.write((int)((n & 0xFF00L) >>> 8));
        this.A.write((int)n & 0xFF);
    }
    
    private long A(final OutputStream outputStream) throws IOException {
        if (outputStream instanceof ByteArrayOutputStream) {
            return ((ByteArrayOutputStream)outputStream).size();
        }
        if (outputStream instanceof g) {
            return ((g)outputStream).A();
        }
        throw new IllegalStateException();
    }
    
    private static int A(final byte[] array, final int n, final int n2, final byte[] array2) {
        int n3 = 0;
        int a = 0;
        for (int i = 0; i < n; ++i) {
            a = A(array, n3, n2, array2, a);
            n3 += n2;
        }
        return a;
    }
    
    private static int A(final byte[] array, int i, final int n, final byte[] array2, int n2) {
        final int n3 = i + n - 1;
        final int n4 = n3 - 1;
        while (i <= n3) {
            int n5 = 1;
            final byte b = array[i];
            while (n5 < 127 && i < n3 && array[i] == array[i + 1]) {
                ++n5;
                ++i;
            }
            if (n5 > 1) {
                ++i;
                array2[n2++] = (byte)(-(n5 - 1));
                array2[n2++] = b;
            }
            int n6 = 0;
            final int n7 = n2;
            while (n6 < 128 && ((i < n3 && array[i] != array[i + 1]) || (i < n4 && array[i] != array[i + 2]))) {
                ++n6;
                array2[++n2] = array[i++];
            }
            if (n6 > 0) {
                array2[n7] = (byte)(n6 - 1);
                ++n2;
            }
            if (i == n3) {
                if (n6 > 0 && n6 < 128) {
                    final int n8 = n7;
                    ++array2[n8];
                    array2[n2++] = array[i++];
                }
                else {
                    array2[n2++] = 0;
                    array2[n2++] = array[i++];
                }
            }
        }
        return n2;
    }
    
    private static int A(final Deflater deflater, final byte[] input, final byte[] array) {
        deflater.setInput(input);
        deflater.finish();
        final int deflate = deflater.deflate(array);
        deflater.reset();
        return deflate;
    }
    
    static {
        d = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
    }
}
