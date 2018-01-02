import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class ADPCMDecoder
{
    static final int[] stepSizeTable;
    static final int[] indices2bit;
    static final int[] indices3bit;
    static final int[] indices4bit;
    static final int[] indices5bit;
    DataInputStream in;
    int bitsPerSample;
    int currentByte;
    int bitPosition;
    int[] indexTable;
    int predicted;
    int index;
    
    ADPCMDecoder(final byte[] array, final int n) {
        this(new ByteArrayInputStream(array), n);
    }
    
    ADPCMDecoder(final InputStream inputStream, final int bitsPerSample) {
        this.in = new DataInputStream(inputStream);
        switch (this.bitsPerSample = bitsPerSample) {
            case 2: {
                this.indexTable = ADPCMDecoder.indices2bit;
                break;
            }
            case 3: {
                this.indexTable = ADPCMDecoder.indices3bit;
                break;
            }
            case 4: {
                this.indexTable = ADPCMDecoder.indices4bit;
                break;
            }
            case 5: {
                this.indexTable = ADPCMDecoder.indices5bit;
                break;
            }
        }
    }
    
    int[] decode(final int n) {
        final int n2 = 1 << this.bitsPerSample - 1;
        final int n3 = n2 - 1;
        final int n4 = n2 >> 1;
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            final int nextBits = this.nextBits();
            int n5 = ADPCMDecoder.stepSizeTable[this.index];
            int n6 = 0;
            for (int j = n4; j > 0; j >>= 1) {
                if ((nextBits & j) != 0x0) {
                    n6 += n5;
                }
                n5 >>= 1;
            }
            final int n7 = n6 + n5;
            this.predicted += (((nextBits & n2) != 0x0) ? (-n7) : n7);
            if (this.predicted > 32767) {
                this.predicted = 32767;
            }
            if (this.predicted < -32768) {
                this.predicted = -32768;
            }
            array[i] = this.predicted;
            this.index += this.indexTable[nextBits & n3];
            if (this.index < 0) {
                this.index = 0;
            }
            if (this.index > 88) {
                this.index = 88;
            }
        }
        return array;
    }
    
    int nextBits() {
        int n = 0;
        int bitsPerSample = this.bitsPerSample;
        do {
            final int n2 = bitsPerSample - this.bitPosition;
            n += ((n2 < 0) ? (this.currentByte >> -n2) : (this.currentByte << n2));
            if (n2 <= 0) {
                this.bitPosition -= bitsPerSample;
                this.currentByte &= 255 >> 8 - this.bitPosition;
                return n;
            }
            bitsPerSample -= this.bitPosition;
            try {
                this.currentByte = this.in.readUnsignedByte();
            }
            catch (IOException ex) {
                this.currentByte = -1;
            }
            this.bitPosition = 8;
        } while (this.currentByte >= 0);
        this.bitPosition = 0;
        return n;
    }
    
    static {
        stepSizeTable = new int[] { 7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143, 157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767 };
        indices2bit = new int[] { -1, 2 };
        indices3bit = new int[] { -1, -1, 2, 4 };
        indices4bit = new int[] { -1, -1, -1, -1, 2, 4, 6, 8 };
        indices5bit = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, 4, 6, 8, 10, 13, 16 };
    }
}
