// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.ComponentSampleModel;
import java.awt.image.SampleModel;
import java.awt.Point;
import java.awt.image.Raster;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.color.ColorSpace;
import java.awt.image.ComponentColorModel;

public class j extends ComponentColorModel
{
    protected ColorSpace colorSpace;
    protected int colorSpaceType;
    protected int numColorComponents;
    protected int numComponents;
    protected int transparency;
    protected boolean A;
    protected boolean isAlphaPremultiplied;
    
    private static int[] A(final int n, final ColorSpace colorSpace, final boolean b) {
        final int n2 = (n == 4) ? 32 : 64;
        int numComponents = colorSpace.getNumComponents();
        if (b) {
            ++numComponents;
        }
        final int[] array = new int[numComponents];
        for (int i = 0; i < numComponents; ++i) {
            array[i] = n2;
        }
        return array;
    }
    
    public j(final ColorSpace colorSpace, final boolean a, final boolean isAlphaPremultiplied, final int transparency, final int n) {
        super(colorSpace, A(n, colorSpace, a), a, isAlphaPremultiplied, transparency, n);
        if (n != 4 && n != 5) {
            throw new IllegalArgumentException(m.A("FloatDoubleColorModel0"));
        }
        this.colorSpace = colorSpace;
        this.colorSpaceType = colorSpace.getType();
        final int numComponents = colorSpace.getNumComponents();
        this.numColorComponents = numComponents;
        this.numComponents = numComponents;
        if (a) {
            ++this.numComponents;
        }
        this.transparency = transparency;
        this.A = a;
        this.isAlphaPremultiplied = isAlphaPremultiplied;
    }
    
    public int getRed(final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel1"));
    }
    
    public int getGreen(final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel2"));
    }
    
    public int getBlue(final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel3"));
    }
    
    public int getAlpha(final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel4"));
    }
    
    public int getRGB(final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel5"));
    }
    
    private final int A(final float n) {
        return (n >= 0.0f) ? ((n > 255.0f) ? 255 : ((int)n)) : 0;
    }
    
    private final int A(final double n) {
        return (n >= 0.0) ? ((n > 255.0) ? 255 : ((int)n)) : 0;
    }
    
    private int A(final Object o, int n) {
        final boolean b = this.A && this.isAlphaPremultiplied;
        final int colorSpaceType = this.colorSpaceType;
        boolean cs_sRGB = this.colorSpace.isCS_sRGB();
        if (colorSpaceType == 6) {
            n = 0;
            cs_sRGB = true;
        }
        if (cs_sRGB) {
            if (this.transferType == 4) {
                final float[] array = (float[])o;
                final float n2 = array[n] * 255.0f;
                if (!b) {
                    return this.A(n2);
                }
                final float n3 = array[this.numColorComponents];
                if (n3 == 0.0) {
                    return 0;
                }
                return this.A(n2 / n3);
            }
            else {
                final double[] array2 = (double[])o;
                final double n4 = array2[n] * 255.0;
                if (!b) {
                    return this.A(n4);
                }
                final double n5 = array2[this.numColorComponents];
                if (n5 == 0.0) {
                    return 0;
                }
                return this.A(n4 / n5);
            }
        }
        else {
            if (this.transferType == 4) {
                final float[] array3 = (float[])o;
                float[] array5;
                if (b) {
                    final float n6 = array3[this.numColorComponents];
                    if (n6 == 0.0) {
                        return 0;
                    }
                    final float[] array4 = new float[this.numColorComponents];
                    for (int i = 0; i < this.numColorComponents; ++i) {
                        array4[i] = array3[i] / n6;
                    }
                    array5 = this.colorSpace.toRGB(array4);
                }
                else {
                    array5 = this.colorSpace.toRGB(array3);
                }
                return (int)(array5[n] * 255.0f);
            }
            final double[] array6 = (double[])o;
            final float[] array7 = new float[this.numColorComponents];
            float[] array8;
            if (b) {
                final double n7 = array6[this.numColorComponents];
                if (n7 == 0.0) {
                    return 0;
                }
                for (int j = 0; j < this.numColorComponents; ++j) {
                    array7[j] = (float)(array6[j] / n7);
                }
                array8 = this.colorSpace.toRGB(array7);
            }
            else {
                for (int k = 0; k < this.numColorComponents; ++k) {
                    array7[k] = (float)array6[k];
                }
                array8 = this.colorSpace.toRGB(array7);
            }
            return (int)(array8[n] * 255.0f);
        }
    }
    
    public int getRed(final Object o) {
        return this.A(o, 0);
    }
    
    public int getGreen(final Object o) {
        return this.A(o, 1);
    }
    
    public int getBlue(final Object o) {
        return this.A(o, 2);
    }
    
    public int getAlpha(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException(m.A("Generic0"));
        }
        if (!this.A) {
            return 255;
        }
        if (this.transferType == 4) {
            return (int)(((float[])o)[this.numColorComponents] * 255.0f);
        }
        return (int)(((double[])o)[this.numColorComponents] * 255.0);
    }
    
    public int getRGB(final Object o) {
        final boolean b = this.A && this.isAlphaPremultiplied;
        int n = 255;
        int n7;
        int n8;
        int n9;
        if (this.colorSpace.isCS_sRGB()) {
            if (this.transferType == 4) {
                final float[] array = (float[])o;
                final float n2 = array[0];
                final float n3 = array[1];
                final float n4 = array[2];
                float n5 = 255.0f;
                if (b) {
                    final float n6 = array[3];
                    n5 /= n6;
                    n = this.A(255.0f * n6);
                }
                n7 = this.A(n2 * n5);
                n8 = this.A(n3 * n5);
                n9 = this.A(n4 * n5);
            }
            else {
                final double[] array2 = (double[])o;
                final double n10 = array2[0];
                final double n11 = array2[1];
                final double n12 = array2[2];
                double n13 = 255.0;
                if (b) {
                    final double n14 = array2[3];
                    n13 /= n14;
                    n = this.A(255.0 * n14);
                }
                n7 = this.A(n10 * n13);
                n8 = this.A(n11 * n13);
                n9 = this.A(n12 * n13);
            }
        }
        else if (this.colorSpaceType == 6) {
            if (this.transferType == 4) {
                final float[] array3 = (float[])o;
                final float n15 = array3[0];
                if (b) {
                    final float n16 = array3[1];
                    n8 = (n7 = (n9 = this.A(n15 * 255.0f / n16)));
                    n = this.A(255.0f * n16);
                }
                else {
                    n8 = (n7 = (n9 = this.A(n15 * 255.0f)));
                }
            }
            else {
                final double[] array4 = (double[])o;
                final double n17 = array4[0];
                if (b) {
                    final double n18 = array4[1];
                    n8 = (n7 = (n9 = this.A(n17 * 255.0 / n18)));
                    n = this.A(255.0 * n18);
                }
                else {
                    n8 = (n7 = (n9 = this.A(n17 * 255.0)));
                }
            }
        }
        else {
            float[] array6;
            if (this.transferType == 4) {
                final float[] array5 = (float[])o;
                if (b) {
                    final float n19 = array5[this.numColorComponents];
                    final float n20 = 1.0f / n19;
                    array6 = new float[this.numColorComponents];
                    for (int i = 0; i < this.numColorComponents; ++i) {
                        array6[i] = array5[i] * n20;
                    }
                    n = this.A(255.0f * n19);
                }
                else {
                    array6 = array5;
                }
            }
            else {
                final double[] array7 = (double[])o;
                array6 = new float[this.numColorComponents];
                if (b) {
                    final double n21 = array7[this.numColorComponents];
                    final double n22 = 1.0 / n21;
                    for (int j = 0; j < this.numColorComponents; ++j) {
                        array6[j] = (float)(array7[j] * n22);
                    }
                    n = this.A(255.0 * n21);
                }
                else {
                    for (int k = 0; k < this.numColorComponents; ++k) {
                        array6[k] = (float)array7[k];
                    }
                }
            }
            final float[] rgb = this.colorSpace.toRGB(array6);
            n7 = this.A(rgb[0] * 255.0f);
            n8 = this.A(rgb[1] * 255.0f);
            n9 = this.A(rgb[2] * 255.0f);
        }
        return n << 24 | n7 << 16 | n8 << 8 | n9;
    }
    
    public Object getDataElements(final int n, final Object o) {
        if (this.transferType == 4) {
            float[] array;
            if (o == null) {
                array = new float[this.numComponents];
            }
            else {
                if (!(o instanceof float[])) {
                    throw new ClassCastException(m.A("FloatDoubleColorModel7"));
                }
                array = (float[])o;
                if (array.length < this.numComponents) {
                    throw new ArrayIndexOutOfBoundsException(m.A("FloatDoubleColorModel8"));
                }
            }
            final float n2 = 0.003921569f;
            if (this.colorSpace.isCS_sRGB()) {
                final int n3 = n >> 24 & 0xFF;
                final int n4 = n >> 16 & 0xFF;
                final int n5 = n >> 8 & 0xFF;
                final int n6 = n & 0xFF;
                float n7 = n2;
                if (this.isAlphaPremultiplied) {
                    n7 *= n3;
                }
                array[0] = n4 * n7;
                array[1] = n5 * n7;
                array[2] = n6 * n7;
                if (this.A) {
                    array[3] = n3 * n2;
                }
            }
            else if (this.colorSpaceType == 6) {
                array[0] = (n >> 16 & 0xFF) * (0.299f * n2) + (n >> 8 & 0xFF) * (0.587f * n2) + (n & 0xFF) * (0.114f * n2);
                if (this.A) {
                    array[1] = (n >> 24 & 0xFF) * n2;
                }
            }
            else {
                final float[] fromRGB = this.colorSpace.fromRGB(new float[] { (n >> 16 & 0xFF) * n2, (n >> 8 & 0xFF) * n2, (n & 0xFF) * n2 });
                for (int i = 0; i < this.numColorComponents; ++i) {
                    array[i] = fromRGB[i];
                }
                if (this.A) {
                    array[this.numColorComponents] = (n >> 24 & 0xFF) * n2;
                }
            }
            return array;
        }
        double[] array2;
        if (o == null) {
            array2 = new double[this.numComponents];
        }
        else {
            if (!(o instanceof double[])) {
                throw new ClassCastException(m.A("FloatDoubleColorModel7"));
            }
            array2 = (double[])o;
            if (array2.length < this.numComponents) {
                throw new ArrayIndexOutOfBoundsException(m.A("FloatDoubleColorModel8"));
            }
        }
        final double n8 = 0.00392156862745098;
        if (this.colorSpace.isCS_sRGB()) {
            final int n9 = n >> 24 & 0xFF;
            final int n10 = n >> 16 & 0xFF;
            final int n11 = n >> 8 & 0xFF;
            final int n12 = n & 0xFF;
            double n13 = n8;
            if (this.isAlphaPremultiplied) {
                n13 *= n9;
            }
            array2[0] = n10 * n13;
            array2[1] = n11 * n13;
            array2[2] = n12 * n13;
            if (this.A) {
                array2[3] = n9 * n8;
            }
        }
        else if (this.colorSpaceType == 6) {
            array2[0] = (n >> 16 & 0xFF) * (0.299 * n8) + (n >> 8 & 0xFF) * (0.587 * n8) + (n & 0xFF) * (0.114 * n8);
            if (this.A) {
                array2[1] = (n >> 24 & 0xFF) * n8;
            }
        }
        else {
            final float n14 = 0.003921569f;
            final float[] fromRGB2 = this.colorSpace.fromRGB(new float[] { (n >> 16 & 0xFF) * n14, (n >> 8 & 0xFF) * n14, (n & 0xFF) * n14 });
            for (int j = 0; j < this.numColorComponents; ++j) {
                array2[j] = fromRGB2[j];
            }
            if (this.A) {
                array2[this.numColorComponents] = (n >> 24 & 0xFF) * n8;
            }
        }
        return array2;
    }
    
    public int[] getComponents(final int n, final int[] array, final int n2) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel9"));
    }
    
    public int[] getComponents(final Object o, final int[] array, final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel9"));
    }
    
    public int getDataElement(final int[] array, final int n) {
        throw new IllegalArgumentException(m.A("FloatDoubleColorModel9"));
    }
    
    public Object getDataElements(final int[] array, final int n, final Object o) {
        if (array.length - n < this.numComponents) {
            throw new IllegalArgumentException(this.numComponents + " " + m.A("FloatDoubleColorModel10"));
        }
        if (this.transferType == 4) {
            float[] array2;
            if (o == null) {
                array2 = new float[array.length];
            }
            else {
                array2 = (float[])o;
            }
            for (int i = 0; i < this.numComponents; ++i) {
                array2[i] = array[n + i];
            }
            return array2;
        }
        double[] array3;
        if (o == null) {
            array3 = new double[array.length];
        }
        else {
            array3 = (double[])o;
        }
        for (int j = 0; j < this.numComponents; ++j) {
            array3[j] = array[n + j];
        }
        return array3;
    }
    
    public ColorModel coerceData(final WritableRaster writableRaster, final boolean b) {
        if (!this.A || this.isAlphaPremultiplied == b) {
            return this;
        }
        final int width = writableRaster.getWidth();
        final int height = writableRaster.getHeight();
        final int n = writableRaster.getNumBands() - 1;
        final int minX = writableRaster.getMinX();
        int minY = writableRaster.getMinY();
        if (writableRaster.getTransferType() != this.transferType) {
            throw new IllegalArgumentException(m.A("FloatDoubleColorModel6"));
        }
        if (b) {
            switch (this.transferType) {
                case 4: {
                    Object o = null;
                    for (int i = 0; i < height; ++i, ++minY) {
                        for (int n2 = minX, j = 0; j < width; ++j, ++n2) {
                            o = writableRaster.getDataElements(n2, minY, o);
                            final float n3 = o[n];
                            if (n3 != 0.0f) {
                                for (int k = 0; k < n; ++k) {
                                    final Object o2 = o;
                                    final int n4 = k;
                                    o2[n4] *= n3;
                                }
                                writableRaster.setDataElements(n2, minY, o);
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    Object o3 = null;
                    for (int l = 0; l < height; ++l, ++minY) {
                        for (int n5 = minX, n6 = 0; n6 < width; ++n6, ++n5) {
                            o3 = writableRaster.getDataElements(n5, minY, o3);
                            final double n7 = o3[n];
                            if (n7 != 0.0) {
                                for (int n8 = 0; n8 < n; ++n8) {
                                    final Object o4 = o3;
                                    final int n9 = n8;
                                    o4[n9] *= n7;
                                }
                                writableRaster.setDataElements(n5, minY, o3);
                            }
                        }
                    }
                    break;
                }
                default: {
                    throw new RuntimeException(m.A("FloatDoubleColorModel0"));
                }
            }
            if (b) {}
        }
        else {
            switch (this.transferType) {
                case 4: {
                    for (int n10 = 0; n10 < height; ++n10, ++minY) {
                        for (int n11 = minX, n12 = 0; n12 < width; ++n12, ++n11) {
                            final float[] array = (float[])writableRaster.getDataElements(n11, minY, null);
                            final float n13 = array[n];
                            if (n13 != 0.0f) {
                                final float n14 = 1.0f / n13;
                                for (int n15 = 0; n15 < n; ++n15) {
                                    final float[] array2 = array;
                                    final int n16 = n15;
                                    array2[n16] *= n14;
                                }
                            }
                            writableRaster.setDataElements(n11, minY, array);
                        }
                    }
                    break;
                }
                case 5: {
                    for (int n17 = 0; n17 < height; ++n17, ++minY) {
                        for (int n18 = minX, n19 = 0; n19 < width; ++n19, ++n18) {
                            final double[] array3 = (double[])writableRaster.getDataElements(n18, minY, null);
                            final double n20 = array3[n];
                            if (n20 != 0.0) {
                                final double n21 = 1.0 / n20;
                                for (int n22 = 0; n22 < n; ++n22) {
                                    final double[] array4 = array3;
                                    final int n23 = n22;
                                    array4[n23] *= n21;
                                }
                            }
                            writableRaster.setDataElements(n18, minY, array3);
                        }
                    }
                    break;
                }
                default: {
                    throw new RuntimeException(m.A("FloatDoubleColorModel0"));
                }
            }
        }
        return new j(this.colorSpace, this.A, b, this.transparency, this.transferType);
    }
    
    public boolean isCompatibleRaster(final Raster raster) {
        return this.isCompatibleSampleModel(raster.getSampleModel());
    }
    
    public WritableRaster createCompatibleWritableRaster(final int n, final int n2) {
        return U.A(this.createCompatibleSampleModel(n, n2), new Point(0, 0));
    }
    
    public SampleModel createCompatibleSampleModel(final int n, final int n2) {
        final int[] array = new int[this.numComponents];
        for (int i = 0; i < this.numComponents; ++i) {
            array[i] = i;
        }
        return new T(this.transferType, n, n2, this.numComponents, n * this.numComponents, array);
    }
    
    public boolean isCompatibleSampleModel(final SampleModel sampleModel) {
        return sampleModel instanceof ComponentSampleModel && sampleModel.getNumBands() == this.getNumComponents() && sampleModel.getDataType() == this.transferType;
    }
    
    public String toString() {
        return "FloatDoubleColorModel: " + super.toString();
    }
}
