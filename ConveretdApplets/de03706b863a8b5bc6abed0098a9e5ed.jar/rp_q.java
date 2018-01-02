import java.awt.image.SampleModel;
import java.awt.image.DataBufferInt;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.ComponentSampleModel;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_q implements rp_h
{
    private int a;
    private int b;
    private rp_eV a;
    
    public rp_q(final rp_eV a) {
        this.a = a;
    }
    
    public final void a(final rp_aV rp_aV, final int n, final int n2) {
        this.a.a(rp_aV, 0.0, 0 - n / 2, 0 - n2 / 2, n, n2);
    }
    
    public final void a_(final int a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public final void c(final int a, final int b) {
        this.a.a(this.a, this.b, a, b);
        this.a = a;
        this.b = b;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final double n7, final double n8) {
        this.a.a(n, n2, n3, n4, n5, n6, n7, 0.0);
    }
    
    public final void a(final int[] array, final int[] array2, final int n) {
        this.a.a(array, array2, n);
    }
    
    public final void b(final int[] array, final int[] array2, final int n) {
        this.a.b(array, array2, n);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final Color color) {
        this.a.a(array, array2, n, color);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final int n2, final Point point, final Point point2, final Color color, final Color color2) {
        this.a.a(array, array2, n, n2, point, point2, color, color2);
    }
    
    public rp_q() {
    }
    
    public static ColorModel a(final ColorModel colorModel, final boolean b) {
        if (colorModel.isAlphaPremultiplied() == b) {
            return colorModel;
        }
        return colorModel.coerceData(colorModel.createCompatibleWritableRaster(1, 1), b);
    }
    
    public static ColorModel a(WritableRaster writableRaster, final ColorModel colorModel, final boolean b) {
        if (!colorModel.hasAlpha()) {
            return colorModel;
        }
        if (colorModel.isAlphaPremultiplied() == b) {
            return colorModel;
        }
        int[] array = null;
        final int numBands = writableRaster.getNumBands();
        if (b) {
            if (a(writableRaster.getSampleModel())) {
                final ComponentSampleModel componentSampleModel = (ComponentSampleModel)(writableRaster = writableRaster).getSampleModel();
                final int width = writableRaster.getWidth();
                final int scanlineStride = componentSampleModel.getScanlineStride();
                final int pixelStride = componentSampleModel.getPixelStride();
                final int[] bandOffsets = componentSampleModel.getBandOffsets();
                final DataBufferByte dataBufferByte;
                final int n = (dataBufferByte = (DataBufferByte)writableRaster.getDataBuffer()).getOffset() + componentSampleModel.getOffset(writableRaster.getMinX() - writableRaster.getSampleModelTranslateX(), writableRaster.getMinY() - writableRaster.getSampleModelTranslateY());
                final int n2 = bandOffsets[bandOffsets.length - 1];
                final int n3 = bandOffsets.length - 1;
                final byte[] array2 = dataBufferByte.getBankData()[0];
                for (int i = 0; i < writableRaster.getHeight(); ++i) {
                    int j = 0;
                    while (j < (j = n + i * scanlineStride) + width * pixelStride) {
                        final int n4;
                        if ((n4 = (array2[j + n2] & 0xFF)) != 255) {
                            for (int k = 0; k < n3; ++k) {
                                final int n5 = j + bandOffsets[k];
                                array2[n5] = (byte)((array2[n5] & 0xFF) * n4 >> 8);
                            }
                        }
                        j += pixelStride;
                    }
                }
            }
            else if (a(writableRaster.getSampleModel(), true)) {
                final SinglePixelPackedSampleModel singlePixelPackedSampleModel = (SinglePixelPackedSampleModel)(writableRaster = writableRaster).getSampleModel();
                final int width2 = writableRaster.getWidth();
                final int scanlineStride2 = singlePixelPackedSampleModel.getScanlineStride();
                final DataBufferInt dataBufferInt;
                final int n6 = (dataBufferInt = (DataBufferInt)writableRaster.getDataBuffer()).getOffset() + singlePixelPackedSampleModel.getOffset(writableRaster.getMinX() - writableRaster.getSampleModelTranslateX(), writableRaster.getMinY() - writableRaster.getSampleModelTranslateY());
                final int[] array3 = dataBufferInt.getBankData()[0];
                for (int l = 0; l < writableRaster.getHeight(); ++l) {
                    int n7 = 0;
                    while (n7 < (n7 = n6 + l * scanlineStride2) + width2) {
                        final int n9;
                        final int n8;
                        if ((n8 = (n9 = array3[n7]) >>> 24) >= 0 && n8 < 255) {
                            array3[n7] = (n8 << 24 | ((n9 & 0xFF0000) * n8 >> 8 & 0xFF0000) | ((n9 & 0xFF00) * n8 >> 8 & 0xFF00) | ((n9 & 0xFF) * n8 >> 8 & 0xFF));
                        }
                        ++n7;
                    }
                }
            }
            else {
                final float n10 = 0.003921569f;
                final int minX;
                final int n11 = (minX = writableRaster.getMinX()) + writableRaster.getWidth();
                int minY;
                for (int n12 = (minY = writableRaster.getMinY()) + writableRaster.getHeight(), n13 = minY; n13 < n12; ++n13) {
                    for (int n14 = minX; n14 < n11; ++n14) {
                        final int n15;
                        if ((n15 = (array = writableRaster.getPixel(n14, n13, array))[numBands - 1]) >= 0 && n15 < 255) {
                            final float n16 = n15 * n10;
                            for (int n17 = 0; n17 < numBands - 1; ++n17) {
                                array[n17] = (int)(array[n17] * n16 + 0.5f);
                            }
                            writableRaster.setPixel(n14, n13, array);
                        }
                    }
                }
            }
        }
        else if (a(writableRaster.getSampleModel())) {
            final ComponentSampleModel componentSampleModel2 = (ComponentSampleModel)(writableRaster = writableRaster).getSampleModel();
            final int width3 = writableRaster.getWidth();
            final int scanlineStride3 = componentSampleModel2.getScanlineStride();
            final int pixelStride2 = componentSampleModel2.getPixelStride();
            final int[] bandOffsets2 = componentSampleModel2.getBandOffsets();
            final DataBufferByte dataBufferByte2;
            final int n18 = (dataBufferByte2 = (DataBufferByte)writableRaster.getDataBuffer()).getOffset() + componentSampleModel2.getOffset(writableRaster.getMinX() - writableRaster.getSampleModelTranslateX(), writableRaster.getMinY() - writableRaster.getSampleModelTranslateY());
            final int n19 = bandOffsets2[bandOffsets2.length - 1];
            final int n20 = bandOffsets2.length - 1;
            final byte[] array4 = dataBufferByte2.getBankData()[0];
            for (int n21 = 0; n21 < writableRaster.getHeight(); ++n21) {
                int n22 = 0;
                while (n22 < (n22 = n18 + n21 * scanlineStride3) + width3 * pixelStride2) {
                    final int n23;
                    if ((n23 = (array4[n22 + n19] & 0xFF)) == 0) {
                        for (int n24 = 0; n24 < n20; ++n24) {
                            array4[n22 + bandOffsets2[n24]] = -1;
                        }
                    }
                    else if (n23 < 255) {
                        final int n25 = 16711680 / n23;
                        for (int n26 = 0; n26 < n20; ++n26) {
                            final int n27 = n22 + bandOffsets2[n26];
                            array4[n27] = (byte)((array4[n27] & 0xFF) * n25 >>> 16);
                        }
                    }
                    n22 += pixelStride2;
                }
            }
        }
        else if (a(writableRaster.getSampleModel(), true)) {
            final SinglePixelPackedSampleModel singlePixelPackedSampleModel2 = (SinglePixelPackedSampleModel)(writableRaster = writableRaster).getSampleModel();
            final int width4 = writableRaster.getWidth();
            final int scanlineStride4 = singlePixelPackedSampleModel2.getScanlineStride();
            final DataBufferInt dataBufferInt2;
            final int n28 = (dataBufferInt2 = (DataBufferInt)writableRaster.getDataBuffer()).getOffset() + singlePixelPackedSampleModel2.getOffset(writableRaster.getMinX() - writableRaster.getSampleModelTranslateX(), writableRaster.getMinY() - writableRaster.getSampleModelTranslateY());
            final int[] array5 = dataBufferInt2.getBankData()[0];
            for (int n29 = 0; n29 < writableRaster.getHeight(); ++n29) {
                int n30 = 0;
                while (n30 < (n30 = n28 + n29 * scanlineStride4) + width4) {
                    final int n32;
                    final int n31;
                    if ((n31 = (n32 = array5[n30]) >>> 24) <= 0) {
                        array5[n30] = 16777215;
                    }
                    else if (n31 < 255) {
                        final int n33 = 16711680 / n31;
                        array5[n30] = (n31 << 24 | ((n32 >> 16 & 0xFF) * n33 & 0xFF0000) | ((n32 >> 8 & 0xFF) * n33 & 0xFF0000) >> 8 | ((n32 & 0xFF) * n33 >> 16 & 0xFF));
                    }
                    ++n30;
                }
            }
        }
        else {
            final int minX2;
            final int n34 = (minX2 = writableRaster.getMinX()) + writableRaster.getWidth();
            int minY2;
            for (int n35 = (minY2 = writableRaster.getMinY()) + writableRaster.getHeight(), n36 = minY2; n36 < n35; ++n36) {
                for (int n37 = minX2; n37 < n34; ++n37) {
                    final int n38;
                    if ((n38 = (array = writableRaster.getPixel(n37, n36, array))[numBands - 1]) > 0 && n38 < 255) {
                        final float n39 = 255.0f / n38;
                        for (int n40 = 0; n40 < numBands - 1; ++n40) {
                            array[n40] = (int)(array[n40] * n39 + 0.5f);
                        }
                        writableRaster.setPixel(n37, n36, array);
                    }
                }
            }
        }
        return a(colorModel, b);
    }
    
    public static boolean a(final SampleModel sampleModel, final boolean b) {
        final int[] bitMasks;
        return sampleModel instanceof SinglePixelPackedSampleModel && sampleModel.getDataType() == 3 && (bitMasks = ((SinglePixelPackedSampleModel)sampleModel).getBitMasks()).length != 3 && bitMasks.length == 4 && bitMasks[0] == 16711680 && bitMasks[1] == 65280 && bitMasks[2] == 255 && (bitMasks.length != 4 || bitMasks[3] == -16777216);
    }
    
    public static boolean a(final SampleModel sampleModel) {
        return sampleModel instanceof ComponentSampleModel && sampleModel.getDataType() == 0;
    }
}
