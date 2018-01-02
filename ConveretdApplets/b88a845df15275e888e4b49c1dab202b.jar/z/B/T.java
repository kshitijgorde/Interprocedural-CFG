// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.awt.image.ComponentSampleModel;

public class T extends ComponentSampleModel
{
    public T(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) {
        super(n, n2, n3, n4, n5, array);
    }
    
    public T(final int n, final int n2, final int n3, final int n4, final int n5, final int[] array, final int[] array2) {
        super(n, n2, n3, n4, n5, array, array2);
    }
    
    private long A() {
        int max = this.bandOffsets[0];
        for (int i = 1; i < this.bandOffsets.length; ++i) {
            max = Math.max(max, this.bandOffsets[i]);
        }
        long n = 0L;
        if (max >= 0) {
            n += max + 1;
        }
        if (this.pixelStride > 0) {
            n += this.pixelStride * (this.width - 1);
        }
        if (this.scanlineStride > 0) {
            n += this.scanlineStride * (this.height - 1);
        }
        return n;
    }
    
    private int[] A(final int[] array, final int n) {
        final int[] array2 = new int[array.length];
        final int[] array3 = new int[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = i;
        }
        for (int j = 0; j < array3.length; ++j) {
            int n2 = j;
            for (int k = j + 1; k < array3.length; ++k) {
                if (array[array2[n2]] > array[array2[k]]) {
                    n2 = k;
                }
            }
            array3[array2[n2]] = j * n;
            array2[n2] = array2[j];
        }
        return array3;
    }
    
    public SampleModel createCompatibleSampleModel(final int n, final int n2) {
        int min = this.bandOffsets[0];
        int max = this.bandOffsets[0];
        for (int i = 1; i < this.bandOffsets.length; ++i) {
            min = Math.min(min, this.bandOffsets[i]);
            max = Math.max(max, this.bandOffsets[i]);
        }
        final int n3 = max - min;
        final int length = this.bandOffsets.length;
        int abs = Math.abs(this.pixelStride);
        int abs2 = Math.abs(this.scanlineStride);
        final int abs3 = Math.abs(n3);
        int[] array;
        if (abs > abs2) {
            if (abs > abs3) {
                if (abs2 > abs3) {
                    array = new int[this.bandOffsets.length];
                    for (int j = 0; j < length; ++j) {
                        array[j] = this.bandOffsets[j] - min;
                    }
                    abs2 = abs3 + 1;
                    abs = abs2 * n2;
                }
                else {
                    array = this.A(this.bandOffsets, abs2 * n2);
                    abs = length * abs2 * n2;
                }
            }
            else {
                abs = abs2 * n2;
                array = this.A(this.bandOffsets, abs * n);
            }
        }
        else if (abs > abs3) {
            array = new int[this.bandOffsets.length];
            for (int k = 0; k < length; ++k) {
                array[k] = this.bandOffsets[k] - min;
            }
            abs = abs3 + 1;
            abs2 = abs * n;
        }
        else if (abs2 > abs3) {
            array = this.A(this.bandOffsets, abs * n);
            abs2 = length * abs * n;
        }
        else {
            abs2 = abs * n;
            array = this.A(this.bandOffsets, abs2 * n2);
        }
        int n4 = 0;
        if (this.scanlineStride < 0) {
            n4 += abs2 * n2;
            abs2 *= -1;
        }
        if (this.pixelStride < 0) {
            n4 += abs * n;
            abs *= -1;
        }
        for (int l = 0; l < length; ++l) {
            final int[] array2 = array;
            final int n5 = l;
            array2[n5] += n4;
        }
        return new T(this.dataType, n, n2, abs, abs2, this.bankIndices, array);
    }
    
    public SampleModel createSubsetSampleModel(final int[] array) {
        final int[] array2 = new int[array.length];
        final int[] array3 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i];
            array2[i] = this.bankIndices[n];
            array3[i] = this.bandOffsets[n];
        }
        return new T(this.dataType, this.width, this.height, this.pixelStride, this.scanlineStride, array2, array3);
    }
    
    public DataBuffer createDataBuffer() {
        final int n = (int)this.A();
        DataBuffer dataBuffer = null;
        switch (this.dataType) {
            case 0: {
                dataBuffer = new DataBufferByte(n, this.numBanks);
                break;
            }
            case 1: {
                dataBuffer = new DataBufferUShort(n, this.numBanks);
                break;
            }
            case 3: {
                dataBuffer = new DataBufferInt(n, this.numBanks);
                break;
            }
            case 2: {
                dataBuffer = new DataBufferShort(n, this.numBanks);
                break;
            }
            case 4: {
                dataBuffer = new C(n, this.numBanks);
                break;
            }
            case 5: {
                dataBuffer = new q(n, this.numBanks);
                break;
            }
            default: {
                throw new RuntimeException(m.A("RasterFactory3"));
            }
        }
        return dataBuffer;
    }
    
    public Object getDataElements(final int n, final int n2, final Object o, final DataBuffer dataBuffer) {
        final int transferType = this.getTransferType();
        final int numDataElements = this.getNumDataElements();
        final int n3 = n2 * this.scanlineStride + n * this.pixelStride;
        byte[] array2 = null;
        switch (transferType) {
            case 0: {
                byte[] array;
                if (o == null) {
                    array = new byte[numDataElements];
                }
                else {
                    array = (byte[])o;
                }
                for (int i = 0; i < numDataElements; ++i) {
                    array[i] = (byte)dataBuffer.getElem(this.bankIndices[i], n3 + this.bandOffsets[i]);
                }
                array2 = array;
                break;
            }
            case 1: {
                short[] array3;
                if (o == null) {
                    array3 = new short[numDataElements];
                }
                else {
                    array3 = (short[])o;
                }
                for (int j = 0; j < numDataElements; ++j) {
                    array3[j] = (short)dataBuffer.getElem(this.bankIndices[j], n3 + this.bandOffsets[j]);
                }
                array2 = (byte[])array3;
                break;
            }
            case 3: {
                int[] array4;
                if (o == null) {
                    array4 = new int[numDataElements];
                }
                else {
                    array4 = (int[])o;
                }
                for (int k = 0; k < numDataElements; ++k) {
                    array4[k] = dataBuffer.getElem(this.bankIndices[k], n3 + this.bandOffsets[k]);
                }
                array2 = (byte[])array4;
                break;
            }
            case 2: {
                short[] array5;
                if (o == null) {
                    array5 = new short[numDataElements];
                }
                else {
                    array5 = (short[])o;
                }
                for (int l = 0; l < numDataElements; ++l) {
                    array5[l] = (short)dataBuffer.getElem(this.bankIndices[l], n3 + this.bandOffsets[l]);
                }
                array2 = (byte[])array5;
                break;
            }
            case 4: {
                float[] array6;
                if (o == null) {
                    array6 = new float[numDataElements];
                }
                else {
                    array6 = (float[])o;
                }
                for (int n4 = 0; n4 < numDataElements; ++n4) {
                    array6[n4] = dataBuffer.getElemFloat(this.bankIndices[n4], n3 + this.bandOffsets[n4]);
                }
                array2 = (byte[])array6;
                break;
            }
            case 5: {
                double[] array7;
                if (o == null) {
                    array7 = new double[numDataElements];
                }
                else {
                    array7 = (double[])o;
                }
                for (int n5 = 0; n5 < numDataElements; ++n5) {
                    array7[n5] = dataBuffer.getElemDouble(this.bankIndices[n5], n3 + this.bandOffsets[n5]);
                }
                array2 = (byte[])array7;
                break;
            }
            default: {
                throw new RuntimeException(m.A("RasterFactory3"));
            }
        }
        return array2;
    }
    
    public Object getDataElements(final int n, final int n2, final int n3, final int n4, final Object o, final DataBuffer dataBuffer) {
        final int transferType = this.getTransferType();
        final int numDataElements = this.getNumDataElements();
        int n5 = 0;
        Object o2 = null;
        byte[] array3 = null;
        switch (transferType) {
            case 0: {
                byte[] array;
                if (o == null) {
                    array = new byte[numDataElements * n3 * n4];
                }
                else {
                    array = (byte[])o;
                }
                for (int i = n2; i < n2 + n4; ++i) {
                    for (int j = n; j < n + n3; ++j) {
                        o2 = this.getDataElements(j, i, o2, dataBuffer);
                        final byte[] array2 = (byte[])o2;
                        for (int k = 0; k < numDataElements; ++k) {
                            array[n5++] = array2[k];
                        }
                    }
                }
                array3 = array;
                break;
            }
            case 1: {
                short[] array4;
                if (o == null) {
                    array4 = new short[numDataElements * n3 * n4];
                }
                else {
                    array4 = (short[])o;
                }
                for (int l = n2; l < n2 + n4; ++l) {
                    for (int n6 = n; n6 < n + n3; ++n6) {
                        o2 = this.getDataElements(n6, l, o2, dataBuffer);
                        final short[] array5 = (short[])o2;
                        for (int n7 = 0; n7 < numDataElements; ++n7) {
                            array4[n5++] = array5[n7];
                        }
                    }
                }
                array3 = (byte[])array4;
                break;
            }
            case 3: {
                int[] array6;
                if (o == null) {
                    array6 = new int[numDataElements * n3 * n4];
                }
                else {
                    array6 = (int[])o;
                }
                for (int n8 = n2; n8 < n2 + n4; ++n8) {
                    for (int n9 = n; n9 < n + n3; ++n9) {
                        o2 = this.getDataElements(n9, n8, o2, dataBuffer);
                        final int[] array7 = (int[])o2;
                        for (int n10 = 0; n10 < numDataElements; ++n10) {
                            array6[n5++] = array7[n10];
                        }
                    }
                }
                array3 = (byte[])array6;
                break;
            }
            case 2: {
                short[] array8;
                if (o == null) {
                    array8 = new short[numDataElements * n3 * n4];
                }
                else {
                    array8 = (short[])o;
                }
                for (int n11 = n2; n11 < n2 + n4; ++n11) {
                    for (int n12 = n; n12 < n + n3; ++n12) {
                        o2 = this.getDataElements(n12, n11, o2, dataBuffer);
                        final short[] array9 = (short[])o2;
                        for (int n13 = 0; n13 < numDataElements; ++n13) {
                            array8[n5++] = array9[n13];
                        }
                    }
                }
                array3 = (byte[])array8;
                break;
            }
            case 4: {
                float[] array10;
                if (o == null) {
                    array10 = new float[numDataElements * n3 * n4];
                }
                else {
                    array10 = (float[])o;
                }
                for (int n14 = n2; n14 < n2 + n4; ++n14) {
                    for (int n15 = n; n15 < n + n3; ++n15) {
                        o2 = this.getDataElements(n15, n14, o2, dataBuffer);
                        final float[] array11 = (float[])o2;
                        for (int n16 = 0; n16 < numDataElements; ++n16) {
                            array10[n5++] = array11[n16];
                        }
                    }
                }
                array3 = (byte[])array10;
                break;
            }
            case 5: {
                double[] array12;
                if (o == null) {
                    array12 = new double[numDataElements * n3 * n4];
                }
                else {
                    array12 = (double[])o;
                }
                for (int n17 = n2; n17 < n2 + n4; ++n17) {
                    for (int n18 = n; n18 < n + n3; ++n18) {
                        o2 = this.getDataElements(n18, n17, o2, dataBuffer);
                        final double[] array13 = (double[])o2;
                        for (int n19 = 0; n19 < numDataElements; ++n19) {
                            array12[n5++] = array13[n19];
                        }
                    }
                }
                array3 = (byte[])array12;
                break;
            }
            default: {
                throw new RuntimeException(m.A("RasterFactory3"));
            }
        }
        return array3;
    }
    
    public void setDataElements(final int n, final int n2, final Object o, final DataBuffer dataBuffer) {
        final int transferType = this.getTransferType();
        final int numDataElements = this.getNumDataElements();
        final int n3 = n2 * this.scanlineStride + n * this.pixelStride;
        switch (transferType) {
            case 0: {
                final byte[] array = (byte[])o;
                for (int i = 0; i < numDataElements; ++i) {
                    dataBuffer.setElem(this.bankIndices[i], n3 + this.bandOffsets[i], array[i] & 0xFF);
                }
                break;
            }
            case 1: {
                final short[] array2 = (short[])o;
                for (int j = 0; j < numDataElements; ++j) {
                    dataBuffer.setElem(this.bankIndices[j], n3 + this.bandOffsets[j], array2[j] & 0xFFFF);
                }
                break;
            }
            case 3: {
                final int[] array3 = (int[])o;
                for (int k = 0; k < numDataElements; ++k) {
                    dataBuffer.setElem(this.bankIndices[k], n3 + this.bandOffsets[k], array3[k]);
                }
                break;
            }
            case 2: {
                final short[] array4 = (short[])o;
                for (int l = 0; l < numDataElements; ++l) {
                    dataBuffer.setElem(this.bankIndices[l], n3 + this.bandOffsets[l], array4[l]);
                }
                break;
            }
            case 4: {
                final float[] array5 = (float[])o;
                for (int n4 = 0; n4 < numDataElements; ++n4) {
                    dataBuffer.setElemFloat(this.bankIndices[n4], n3 + this.bandOffsets[n4], array5[n4]);
                }
                break;
            }
            case 5: {
                final double[] array6 = (double[])o;
                for (int n5 = 0; n5 < numDataElements; ++n5) {
                    dataBuffer.setElemDouble(this.bankIndices[n5], n3 + this.bandOffsets[n5], array6[n5]);
                }
                break;
            }
            default: {
                throw new RuntimeException(m.A("RasterFactory3"));
            }
        }
    }
    
    public void setDataElements(final int n, final int n2, final int n3, final int n4, final Object o, final DataBuffer dataBuffer) {
        int n5 = 0;
        final int transferType = this.getTransferType();
        final int numDataElements = this.getNumDataElements();
        switch (transferType) {
            case 0: {
                final byte[] array = (byte[])o;
                final byte[] array2 = new byte[numDataElements];
                for (int i = n2; i < n2 + n4; ++i) {
                    for (int j = n; j < n + n3; ++j) {
                        for (int k = 0; k < numDataElements; ++k) {
                            array2[k] = array[n5++];
                        }
                        this.setDataElements(j, i, array2, dataBuffer);
                    }
                }
                break;
            }
            case 1: {
                final short[] array3 = (short[])o;
                final short[] array4 = new short[numDataElements];
                for (int l = n2; l < n2 + n4; ++l) {
                    for (int n6 = n; n6 < n + n3; ++n6) {
                        for (int n7 = 0; n7 < numDataElements; ++n7) {
                            array4[n7] = array3[n5++];
                        }
                        this.setDataElements(n6, l, array4, dataBuffer);
                    }
                }
                break;
            }
            case 3: {
                final int[] array5 = (int[])o;
                final int[] array6 = new int[numDataElements];
                for (int n8 = n2; n8 < n2 + n4; ++n8) {
                    for (int n9 = n; n9 < n + n3; ++n9) {
                        for (int n10 = 0; n10 < numDataElements; ++n10) {
                            array6[n10] = array5[n5++];
                        }
                        this.setDataElements(n9, n8, array6, dataBuffer);
                    }
                }
                break;
            }
            case 2: {
                final short[] array7 = (short[])o;
                final short[] array8 = new short[numDataElements];
                for (int n11 = n2; n11 < n2 + n4; ++n11) {
                    for (int n12 = n; n12 < n + n3; ++n12) {
                        for (int n13 = 0; n13 < numDataElements; ++n13) {
                            array8[n13] = array7[n5++];
                        }
                        this.setDataElements(n12, n11, array8, dataBuffer);
                    }
                }
                break;
            }
            case 4: {
                final float[] array9 = (float[])o;
                final float[] array10 = new float[numDataElements];
                for (int n14 = n2; n14 < n2 + n4; ++n14) {
                    for (int n15 = n; n15 < n + n3; ++n15) {
                        for (int n16 = 0; n16 < numDataElements; ++n16) {
                            array10[n16] = array9[n5++];
                        }
                        this.setDataElements(n15, n14, array10, dataBuffer);
                    }
                }
                break;
            }
            case 5: {
                final double[] array11 = (double[])o;
                final double[] array12 = new double[numDataElements];
                for (int n17 = n2; n17 < n2 + n4; ++n17) {
                    for (int n18 = n; n18 < n + n3; ++n18) {
                        for (int n19 = 0; n19 < numDataElements; ++n19) {
                            array12[n19] = array11[n5++];
                        }
                        this.setDataElements(n18, n17, array12, dataBuffer);
                    }
                }
                break;
            }
            default: {
                throw new RuntimeException(m.A("RasterFactory3"));
            }
        }
    }
    
    public void setSample(final int n, final int n2, final int n3, final float n4, final DataBuffer dataBuffer) {
        dataBuffer.setElemFloat(this.bankIndices[n3], n2 * this.scanlineStride + n * this.pixelStride + this.bandOffsets[n3], n4);
    }
    
    public float getSampleFloat(final int n, final int n2, final int n3, final DataBuffer dataBuffer) {
        return dataBuffer.getElemFloat(this.bankIndices[n3], n2 * this.scanlineStride + n * this.pixelStride + this.bandOffsets[n3]);
    }
    
    public void setSample(final int n, final int n2, final int n3, final double n4, final DataBuffer dataBuffer) {
        dataBuffer.setElemDouble(this.bankIndices[n3], n2 * this.scanlineStride + n * this.pixelStride + this.bandOffsets[n3], n4);
    }
    
    public double getSampleDouble(final int n, final int n2, final int n3, final DataBuffer dataBuffer) {
        return dataBuffer.getElemDouble(this.bankIndices[n3], n2 * this.scanlineStride + n * this.pixelStride + this.bandOffsets[n3]);
    }
    
    public double[] getPixels(final int n, final int n2, final int n3, final int n4, final double[] array, final DataBuffer dataBuffer) {
        int n5 = 0;
        double[] array2;
        if (array != null) {
            array2 = array;
        }
        else {
            array2 = new double[this.numBands * n3 * n4];
        }
        for (int i = n2; i < n4 + n2; ++i) {
            for (int j = n; j < n3 + n; ++j) {
                for (int k = 0; k < this.numBands; ++k) {
                    array2[n5++] = this.getSampleDouble(j, i, k, dataBuffer);
                }
            }
        }
        return array2;
    }
    
    public String toString() {
        String s = "ComponentSampleModelJAI:   dataType=" + this.getDataType() + "  numBands=" + this.getNumBands() + "  width=" + this.getWidth() + "  height=" + this.getHeight() + "  bandOffsets=[ ";
        for (int i = 0; i < this.numBands; ++i) {
            s = s + this.getBandOffsets()[i] + " ";
        }
        return s + "]";
    }
}
