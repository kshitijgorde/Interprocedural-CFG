// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.ComponentColorModel;
import java.awt.color.ColorSpace;
import java.awt.image.BandedSampleModel;
import java.awt.Rectangle;
import java.awt.image.RasterFormatException;
import java.awt.image.SampleModel;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.awt.Point;

public class U
{
    public static WritableRaster B(final int n, final int n2, final int n3, final int n4, final Point point) {
        if (n4 < 1) {
            throw new IllegalArgumentException(m.A("RasterFactory0"));
        }
        final int[] array = new int[n4];
        for (int i = 0; i < n4; ++i) {
            array[i] = n4 - 1 - i;
        }
        return A(n, n2, n3, n2 * n4, n4, array, point);
    }
    
    public static WritableRaster A(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array, final Point point) {
        if (array == null) {
            throw new IllegalArgumentException(m.A("RasterFactory4"));
        }
        final int length = array.length;
        int n6 = array[0];
        for (int i = 1; i < length; ++i) {
            if (array[i] > n6) {
                n6 = array[i];
            }
        }
        final long n7 = n6 + n4 * (n3 - 1) + n5 * (n2 - 1) + 1L;
        if (n7 > 2147483647L) {
            throw new IllegalArgumentException(m.A("RasterFactory16"));
        }
        final int n8 = (int)n7;
        DataBuffer dataBuffer = null;
        switch (n) {
            case 0: {
                dataBuffer = new DataBufferByte(n8);
                break;
            }
            case 1: {
                dataBuffer = new DataBufferUShort(n8);
                break;
            }
            case 2: {
                dataBuffer = new DataBufferShort(n8);
                break;
            }
            case 3: {
                dataBuffer = new DataBufferInt(n8);
                break;
            }
            case 4: {
                dataBuffer = new C(n8);
                break;
            }
            case 5: {
                dataBuffer = new q(n8);
                break;
            }
            default: {
                throw new IllegalArgumentException(m.A("RasterFactory3"));
            }
        }
        return A(dataBuffer, n2, n3, n4, n5, array, point);
    }
    
    public static WritableRaster A(final int n, final int n2, final int n3, final int n4, final Point point) {
        if (n4 < 1) {
            throw new IllegalArgumentException(m.A("RasterFactory0"));
        }
        final int[] array = new int[n4];
        final int[] array2 = new int[n4];
        for (int i = 0; i < n4; ++i) {
            array2[array[i] = i] = 0;
        }
        return A(n, n2, n3, n2, array, array2, point);
    }
    
    public static WritableRaster A(final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2, final Point point) {
        final int length = array2.length;
        if (array == null) {
            throw new IllegalArgumentException(m.A("RasterFactory1"));
        }
        if (array2 == null) {
            throw new IllegalArgumentException(m.A("RasterFactory4"));
        }
        if (array2.length != array.length) {
            throw new IllegalArgumentException(m.A("RasterFactory2"));
        }
        int n5 = array[0];
        int n6 = array2[0];
        for (int i = 1; i < length; ++i) {
            if (array[i] > n5) {
                n5 = array[i];
            }
            if (array2[i] > n6) {
                n6 = array2[i];
            }
        }
        final int n7 = n5 + 1;
        final long n8 = n6 + n4 * (n3 - 1) + (n2 - 1) + 1L;
        if (n8 > 2147483647L) {
            throw new IllegalArgumentException(m.A("RasterFactory16"));
        }
        final int n9 = (int)n8;
        DataBuffer dataBuffer = null;
        switch (n) {
            case 0: {
                dataBuffer = new DataBufferByte(n9, n7);
                break;
            }
            case 1: {
                dataBuffer = new DataBufferUShort(n9, n7);
                break;
            }
            case 2: {
                dataBuffer = new DataBufferShort(n9, n7);
                break;
            }
            case 3: {
                dataBuffer = new DataBufferInt(n9, n7);
                break;
            }
            case 4: {
                dataBuffer = new C(n9, n7);
                break;
            }
            case 5: {
                dataBuffer = new q(n9, n7);
                break;
            }
            default: {
                throw new IllegalArgumentException(m.A("RasterFactory3"));
            }
        }
        return A(dataBuffer, n2, n3, n4, array, array2, point);
    }
    
    public static WritableRaster A(final int n, final int n2, final int n3, final int[] array, final Point point) {
        return Raster.createPackedRaster(n, n2, n3, array, point);
    }
    
    public static WritableRaster A(final int n, final int n2, final int n3, final int n4, final int n5, final Point point) {
        if (n5 <= 0) {
            throw new IllegalArgumentException(m.A("RasterFactory15"));
        }
        return Raster.createPackedRaster(n, n2, n3, n4, n5, point);
    }
    
    public static WritableRaster A(final DataBuffer dataBuffer, final int n, final int n2, final int n3, final int n4, final int[] array, Point point) {
        if (array == null) {
            throw new IllegalArgumentException(m.A("RasterFactory4"));
        }
        if (point == null) {
            point = new Point(0, 0);
        }
        final int dataType = dataBuffer.getDataType();
        switch (dataType) {
            case 0:
            case 1: {
                return Raster.createWritableRaster(new PixelInterleavedSampleModel(dataType, n, n2, n4, n3, array), dataBuffer, point);
            }
            case 2:
            case 3:
            case 4:
            case 5: {
                int min = array[0];
                int max = array[0];
                for (int i = 1; i < array.length; ++i) {
                    min = Math.min(min, array[i]);
                    max = Math.max(max, array[i]);
                }
                final int n5 = max - min;
                if (n5 > n3) {
                    throw new IllegalArgumentException(m.A("RasterFactory5"));
                }
                if (n4 * n > n3) {
                    throw new IllegalArgumentException(m.A("RasterFactory6"));
                }
                if (n4 < n5) {
                    throw new IllegalArgumentException(m.A("RasterFactory7"));
                }
                return Raster.createWritableRaster(new T(dataType, n, n2, n4, n3, array), dataBuffer, point);
            }
            default: {
                throw new IllegalArgumentException(m.A("RasterFactory3"));
            }
        }
    }
    
    public static WritableRaster A(final DataBuffer dataBuffer, final int n, final int n2, final int n3, final int[] array, final int[] array2, Point point) {
        if (point == null) {
            point = new Point(0, 0);
        }
        final int dataType = dataBuffer.getDataType();
        if (array == null) {
            throw new IllegalArgumentException(m.A("RasterFactory1"));
        }
        if (array2 == null) {
            throw new IllegalArgumentException(m.A("RasterFactory4"));
        }
        if (array2.length != array.length) {
            throw new IllegalArgumentException(m.A("RasterFactory2"));
        }
        final T t = new T(dataType, n, n2, 1, n3, array, array2);
        switch (dataType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return Raster.createWritableRaster(t, dataBuffer, point);
            }
            default: {
                throw new IllegalArgumentException(m.A("RasterFactory3"));
            }
        }
    }
    
    public static WritableRaster A(final DataBuffer dataBuffer, final int n, final int n2, final int n3, final int[] array, final Point point) {
        return Raster.createPackedRaster(dataBuffer, n, n2, n3, array, point);
    }
    
    public static WritableRaster A(final DataBuffer dataBuffer, final int n, final int n2, final int n3, final Point point) {
        return Raster.createPackedRaster(dataBuffer, n, n2, n3, point);
    }
    
    public static Raster B(final SampleModel sampleModel, final DataBuffer dataBuffer, final Point point) {
        return Raster.createRaster(sampleModel, dataBuffer, point);
    }
    
    public static WritableRaster A(final SampleModel sampleModel, Point point) {
        if (point == null) {
            point = new Point(0, 0);
        }
        return A(sampleModel, sampleModel.createDataBuffer(), point);
    }
    
    public static WritableRaster A(final SampleModel sampleModel, final DataBuffer dataBuffer, final Point point) {
        return Raster.createWritableRaster(sampleModel, dataBuffer, point);
    }
    
    public static WritableRaster A(final WritableRaster writableRaster, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array) {
        if (n < writableRaster.getMinX()) {
            throw new RasterFormatException(m.A("RasterFactory9"));
        }
        if (n2 < writableRaster.getMinY()) {
            throw new RasterFormatException(m.A("RasterFactory10"));
        }
        if (n + n3 > writableRaster.getWidth() + writableRaster.getMinX()) {
            throw new RasterFormatException(m.A("RasterFactory11"));
        }
        if (n2 + n4 > writableRaster.getHeight() + writableRaster.getMinY()) {
            throw new RasterFormatException(m.A("RasterFactory12"));
        }
        final SampleModel sampleModel = writableRaster.getSampleModel();
        final DataBuffer dataBuffer = writableRaster.getDataBuffer();
        final int sampleModelTranslateX = writableRaster.getSampleModelTranslateX();
        final int sampleModelTranslateY = writableRaster.getSampleModelTranslateY();
        SampleModel subsetSampleModel;
        if (array != null) {
            subsetSampleModel = sampleModel.createCompatibleSampleModel(sampleModel.getWidth(), sampleModel.getHeight()).createSubsetSampleModel(array);
        }
        else {
            subsetSampleModel = sampleModel;
        }
        return new H(subsetSampleModel, dataBuffer, new Rectangle(n5, n6, n3, n4), new Point(sampleModelTranslateX + (n5 - n), sampleModelTranslateY + (n6 - n2)), writableRaster);
    }
    
    public static SampleModel A(final int n, final int n2, final int n3, final int n4, int[] array, int[] array2) {
        if (n4 < 1) {
            throw new IllegalArgumentException(m.A("RasterFactory0"));
        }
        if (array == null) {
            array = new int[n4];
            for (int i = 0; i < n4; ++i) {
                array[i] = i;
            }
        }
        if (array2 == null) {
            array2 = new int[n4];
            for (int j = 0; j < n4; ++j) {
                array2[j] = 0;
            }
        }
        if (array2.length != array.length) {
            throw new IllegalArgumentException(m.A("RasterFactory2"));
        }
        return new T(n, n2, n3, 1, n2, array, array2);
    }
    
    public static SampleModel A(final int n, final int n2, final int n3, final int n4) {
        return A(n, n2, n3, n4, null, null);
    }
    
    public static SampleModel A(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) {
        if (array == null) {
            throw new IllegalArgumentException(m.A("RasterFactory4"));
        }
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; ++i) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);
        }
        final int n6 = max - min;
        if (n6 > n5) {
            throw new IllegalArgumentException(m.A("RasterFactory5"));
        }
        if (n4 * n2 > n5) {
            throw new IllegalArgumentException(m.A("RasterFactory6"));
        }
        if (n4 < n6) {
            throw new IllegalArgumentException(m.A("RasterFactory7"));
        }
        switch (n) {
            case 0:
            case 1: {
                return new PixelInterleavedSampleModel(n, n2, n3, n4, n5, array);
            }
            case 2:
            case 3:
            case 4:
            case 5: {
                return new T(n, n2, n3, n4, n5, array);
            }
            default: {
                throw new IllegalArgumentException(m.A("RasterFactory3"));
            }
        }
    }
    
    public static SampleModel B(final int n, final int n2, final int n3, final int n4) {
        if (n4 < 1) {
            throw new IllegalArgumentException(m.A("RasterFactory0"));
        }
        final int[] array = new int[n4];
        for (int i = 0; i < n4; ++i) {
            array[i] = n4 - 1 - i;
        }
        return A(n, n2, n3, n4, n4 * n2, array);
    }
    
    public static SampleModel A(final SampleModel sampleModel, final int n, final int n2, final int n3, final int n4) {
        if (sampleModel instanceof BandedSampleModel) {
            return A(n, n2, n3, n4);
        }
        return B(n, n2, n3, n4);
    }
    
    public static ComponentColorModel A(final int n, final ColorSpace colorSpace, final boolean b, boolean b2, int n2) {
        if (colorSpace == null) {
            throw new IllegalArgumentException(m.A("Generic0"));
        }
        if (n2 != 1 && n2 != 2 && n2 != 3) {
            throw new IllegalArgumentException(m.A("RasterFactory13"));
        }
        if (b && n2 == 1) {
            throw new IllegalArgumentException(m.A("RasterFactory14"));
        }
        if (!b) {
            b2 = false;
            n2 = 1;
        }
        int numComponents = colorSpace.getNumComponents();
        if (b) {
            ++numComponents;
        }
        final int dataTypeSize = DataBuffer.getDataTypeSize(n);
        final int[] array = new int[numComponents];
        for (int i = 0; i < numComponents; ++i) {
            array[i] = dataTypeSize;
        }
        switch (n) {
            case 0: {
                return new ComponentColorModel(colorSpace, array, b, b2, n2, n);
            }
            case 1: {
                return new ComponentColorModel(colorSpace, array, b, b2, n2, n);
            }
            case 3: {
                return new ComponentColorModel(colorSpace, array, b, b2, n2, n);
            }
            case 4: {
                return new j(colorSpace, b, b2, n2, n);
            }
            case 5: {
                return new j(colorSpace, b, b2, n2, n);
            }
            default: {
                throw new IllegalArgumentException(m.A("RasterFactory8"));
            }
        }
    }
}
