// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.awt.Rectangle;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.io.OutputStream;

public class G extends r
{
    private OutputStream H;
    private int G;
    private boolean F;
    private boolean C;
    private int E;
    private int I;
    private int D;
    
    public G(final OutputStream h, final M m) {
        super(h, m);
        this.D = 0;
        this.H = h;
        P p2;
        if (m == null) {
            p2 = new P();
        }
        else {
            p2 = (P)m;
        }
        this.G = p2.D();
        this.F = p2.F();
        if (this.F && !(h instanceof g)) {
            throw new IllegalArgumentException(m.A("BMPImageEncoder6"));
        }
        this.C = p2.E();
    }
    
    public void A(final RenderedImage renderedImage) throws IOException {
        final int minX = renderedImage.getMinX();
        final int minY = renderedImage.getMinY();
        this.E = renderedImage.getWidth();
        this.I = renderedImage.getHeight();
        int n = 24;
        int n2 = 0;
        int mapSize = 0;
        IndexColorModel indexColorModel = null;
        final SampleModel sampleModel = renderedImage.getSampleModel();
        final int numBands = sampleModel.getNumBands();
        final ColorModel colorModel = renderedImage.getColorModel();
        if (numBands != 1 && numBands != 3) {
            throw new IllegalArgumentException(m.A("BMPImageEncoder1"));
        }
        final int[] sampleSize = sampleModel.getSampleSize();
        if (sampleSize[0] > 8) {
            throw new RuntimeException(m.A("BMPImageEncoder2"));
        }
        for (int i = 1; i < sampleSize.length; ++i) {
            if (sampleSize[i] != sampleSize[0]) {
                throw new RuntimeException(m.A("BMPImageEncoder3"));
            }
        }
        final int transferType = sampleModel.getTransferType();
        if (transferType == 1 || transferType == 2 || transferType == 3 || transferType == 4 || transferType == 5) {
            throw new RuntimeException(m.A("BMPImageEncoder0"));
        }
        int n3 = this.E * numBands;
        int n4 = 0;
        byte[] array = null;
        byte[] array2 = null;
        byte[] array3 = null;
        byte[] array4 = null;
        if (colorModel instanceof IndexColorModel) {
            n2 = 1;
            indexColorModel = (IndexColorModel)colorModel;
            mapSize = indexColorModel.getMapSize();
            if (mapSize <= 2) {
                n = 1;
                n3 = (int)Math.ceil(this.E / 8.0);
            }
            else if (mapSize <= 16) {
                n = 4;
                n3 = (int)Math.ceil(this.E / 2.0);
            }
            else if (mapSize <= 256) {
                n = 8;
            }
            else {
                n = 24;
                n2 = 0;
                mapSize = 0;
                n3 = this.E * 3;
            }
            if (n2 == 1) {
                array = new byte[mapSize];
                array2 = new byte[mapSize];
                array3 = new byte[mapSize];
                array4 = new byte[mapSize];
                indexColorModel.getAlphas(array4);
                indexColorModel.getReds(array);
                indexColorModel.getGreens(array2);
                indexColorModel.getBlues(array3);
            }
        }
        else if (numBands == 1) {
            n2 = 1;
            mapSize = 256;
            n = sampleSize[0];
            n3 = (int)Math.ceil(this.E * n / 8.0);
            array = new byte[256];
            array2 = new byte[256];
            array3 = new byte[256];
            array4 = new byte[256];
            for (int j = 0; j < 256; ++j) {
                array[j] = (byte)j;
                array2[j] = (byte)j;
                array3[j] = (byte)j;
                array4[j] = (byte)j;
            }
        }
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        final int n9 = 0;
        final int n10 = 0;
        final int n11 = 0;
        final int n12 = mapSize;
        int n13 = 0;
        final int n14 = n3 % 4;
        if (n14 != 0) {
            n13 = 4 - n14;
        }
        switch (this.G) {
            case 0: {
                final int n15 = (n3 + n13) * this.I + (26 + mapSize * 3);
                throw new RuntimeException(m.A("BMPImageEncoder5"));
            }
            case 1: {
                if (this.F && n == 8) {
                    n4 = 1;
                }
                else if (this.F && n == 4) {
                    n4 = 2;
                }
                n6 = 54 + mapSize * 4;
                n8 = (n3 + n13) * this.I;
                n5 = n8 + n6;
                n7 = 40;
                break;
            }
            case 2: {
                throw new RuntimeException(m.A("BMPImageEncoder5"));
            }
        }
        this.A(n5, n6);
        this.B(n7, n);
        this.C(n4);
        this.C(n8);
        this.C(n9);
        this.C(n10);
        this.C(n11);
        this.C(n12);
        if (n2 == 1) {
            switch (this.G) {
                case 0: {
                    for (int k = 0; k < mapSize; ++k) {
                        this.H.write(array3[k]);
                        this.H.write(array2[k]);
                        this.H.write(array[k]);
                    }
                    break;
                }
                default: {
                    for (int l = 0; l < mapSize; ++l) {
                        this.H.write(array3[l]);
                        this.H.write(array2[l]);
                        this.H.write(array[l]);
                        this.H.write(array4[l]);
                    }
                    break;
                }
            }
        }
        final int n16 = this.E * numBands;
        final int[] array5 = new int[8 * n16];
        final byte[] array6 = new byte[n3];
        if (!this.C) {
            for (int n17 = minY + this.I - 1; n17 >= minY; n17 -= 8) {
                final int min = Math.min(8, n17 - minY + 1);
                renderedImage.getData(new Rectangle(minX, n17 - min + 1, this.E, min)).getPixels(minX, n17 - min + 1, this.E, min, array5);
                final int n18 = n16 * min - 1;
                for (int n19 = 0; n19 < min; ++n19) {
                    this.A(n18 - (n19 + 1) * n16 + 1, n16, n, array5, array6, n13, numBands, indexColorModel);
                }
            }
        }
        else {
            for (int n20 = minY + this.I, n21 = minY; n21 < n20; n21 += 8) {
                final int min2 = Math.min(8, n20 - n21);
                renderedImage.getData(new Rectangle(minX, n21, this.E, min2)).getPixels(minX, n21, this.E, min2, array5);
                final int n22 = 0;
                for (int n23 = 0; n23 < min2; ++n23) {
                    this.A(n22, n16, n, array5, array6, n13, numBands, indexColorModel);
                }
            }
        }
        if (this.F && (n == 4 || n == 8)) {
            this.H.write(0);
            this.H.write(1);
            this.A(2);
            final int d = this.D;
            this.C(this.D + n6, 2);
            this.C(d, 34);
        }
    }
    
    private void A(int n, final int n2, final int n3, final int[] array, final byte[] array2, final int n4, final int n5, final IndexColorModel indexColorModel) throws IOException {
        int n6 = 0;
        switch (n3) {
            case 1: {
                for (int i = 0; i < n2 / 8; ++i) {
                    array2[n6++] = (byte)(array[n++] << 7 | array[n++] << 6 | array[n++] << 5 | array[n++] << 4 | array[n++] << 3 | array[n++] << 2 | array[n++] << 1 | array[n++]);
                }
                if (n2 % 8 > 0) {
                    int n7 = 0;
                    for (int j = 0; j < n2 % 8; ++j) {
                        n7 |= array[n++] << 7 - j;
                    }
                    array2[n6++] = (byte)n7;
                }
                this.H.write(array2, 0, (n2 + 7) / 8);
                break;
            }
            case 4: {
                if (this.F) {
                    final byte[] array3 = new byte[n2];
                    for (int k = 0; k < n2; ++k) {
                        array3[k] = (byte)array[n++];
                    }
                    this.A(array3, n2);
                    break;
                }
                for (int l = 0; l < n2 / 2; ++l) {
                    array2[n6++] = (byte)(array[n++] << 4 | array[n++]);
                }
                if (n2 % 2 == 1) {
                    array2[n6++] = (byte)(array[n] << 4);
                }
                this.H.write(array2, 0, (n2 + 1) / 2);
                break;
            }
            case 8: {
                if (this.F) {
                    for (int n8 = 0; n8 < n2; ++n8) {
                        array2[n8] = (byte)array[n++];
                    }
                    this.B(array2, n2);
                    break;
                }
                for (int n9 = 0; n9 < n2; ++n9) {
                    array2[n9] = (byte)array[n++];
                }
                this.H.write(array2, 0, n2);
                break;
            }
            case 24: {
                if (n5 == 3) {
                    for (int n10 = 0; n10 < n2; n10 += 3) {
                        array2[n6++] = (byte)array[n + 2];
                        array2[n6++] = (byte)array[n + 1];
                        array2[n6++] = (byte)array[n];
                        n += 3;
                    }
                    this.H.write(array2, 0, n2);
                    break;
                }
                final int mapSize = indexColorModel.getMapSize();
                final byte[] array4 = new byte[mapSize];
                final byte[] array5 = new byte[mapSize];
                final byte[] array6 = new byte[mapSize];
                indexColorModel.getReds(array4);
                indexColorModel.getGreens(array5);
                indexColorModel.getBlues(array6);
                for (int n11 = 0; n11 < n2; ++n11) {
                    final int n12 = array[n];
                    array2[n6++] = array6[n12];
                    array2[n6++] = array5[n12];
                    array2[n6++] = array6[n12];
                    ++n;
                }
                this.H.write(array2, 0, n2 * 3);
                break;
            }
        }
        if (!this.F || (n3 != 8 && n3 != 4)) {
            for (int n13 = 0; n13 < n4; ++n13) {
                this.H.write(0);
            }
        }
    }
    
    private void B(final byte[] array, final int n) throws IOException {
        int n2 = 1;
        int n3 = -1;
        int i = -1;
        byte b = array[++i];
        final byte[] array2 = new byte[256];
        while (i < n - 1) {
            final byte b2 = array[++i];
            if (b2 == b) {
                if (n3 >= 3) {
                    this.H.write(0);
                    this.H.write(n3);
                    this.A(2);
                    for (int j = 0; j < n3; ++j) {
                        this.H.write(array2[j]);
                        this.A(1);
                    }
                    if (!this.B(n3)) {
                        this.H.write(0);
                        this.A(1);
                    }
                }
                else if (n3 > -1) {
                    for (int k = 0; k < n3; ++k) {
                        this.H.write(1);
                        this.H.write(array2[k]);
                        this.A(2);
                    }
                }
                n3 = -1;
                if (++n2 == 256) {
                    this.H.write(n2 - 1);
                    this.H.write(b);
                    this.A(2);
                    n2 = 1;
                }
            }
            else {
                if (n2 > 1) {
                    this.H.write(n2);
                    this.H.write(b);
                    this.A(2);
                }
                else if (n3 < 0) {
                    array2[++n3] = b;
                    array2[++n3] = b2;
                }
                else if (n3 < 254) {
                    array2[++n3] = b2;
                }
                else {
                    this.H.write(0);
                    this.H.write(n3 + 1);
                    this.A(2);
                    for (int l = 0; l <= n3; ++l) {
                        this.H.write(array2[l]);
                        this.A(1);
                    }
                    this.H.write(0);
                    this.A(1);
                    n3 = -1;
                }
                b = b2;
                n2 = 1;
            }
            if (i == n - 1) {
                if (n3 == -1) {
                    this.H.write(n2);
                    this.H.write(b);
                    this.A(2);
                    n2 = 1;
                }
                else if (n3 >= 2) {
                    this.H.write(0);
                    this.H.write(n3 + 1);
                    this.A(2);
                    for (int n4 = 0; n4 <= n3; ++n4) {
                        this.H.write(array2[n4]);
                        this.A(1);
                    }
                    if (!this.B(n3 + 1)) {
                        this.H.write(0);
                        this.A(1);
                    }
                }
                else if (n3 > -1) {
                    for (int n5 = 0; n5 <= n3; ++n5) {
                        this.H.write(1);
                        this.H.write(array2[n5]);
                        this.A(2);
                    }
                }
                this.H.write(0);
                this.H.write(0);
                this.A(2);
            }
        }
    }
    
    private void A(final byte[] array, final int n) throws IOException {
        int n2 = 2;
        int n3 = -1;
        int i = -1;
        final byte[] array2 = new byte[256];
        byte b = array[++i];
        byte b2 = array[++i];
        while (i < n - 2) {
            final byte b3 = array[++i];
            final byte b4 = array[++i];
            if (b3 == b) {
                if (n3 >= 4) {
                    this.H.write(0);
                    this.H.write(n3 - 1);
                    this.A(2);
                    for (int j = 0; j < n3 - 2; j += 2) {
                        this.H.write((byte)(array2[j] << 4 | array2[j + 1]));
                        this.A(1);
                    }
                    if (!this.B(n3 - 1)) {
                        this.H.write(array2[n3 - 2] << 4 | 0x0);
                        this.A(1);
                    }
                    if (!this.B((int)Math.ceil((n3 - 1) / 2))) {
                        this.H.write(0);
                        this.A(1);
                    }
                }
                else if (n3 > -1) {
                    this.H.write(2);
                    this.H.write(array2[0] << 4 | array2[1]);
                    this.A(2);
                }
                n3 = -1;
                if (b4 == b2) {
                    n2 += 2;
                    if (n2 == 256) {
                        this.H.write(n2 - 1);
                        this.H.write(b << 4 | b2);
                        this.A(2);
                        n2 = 2;
                        if (i < n - 1) {
                            b = b2;
                            b2 = array[++i];
                        }
                        else {
                            this.H.write(1);
                            this.H.write(b2 << 4 | 0x0);
                            this.A(2);
                            n2 = -1;
                        }
                    }
                }
                else {
                    ++n2;
                    final int n4 = b << 4 | b2;
                    this.H.write(n2);
                    this.H.write(n4);
                    this.A(2);
                    n2 = 2;
                    b = b4;
                    if (i < n - 1) {
                        b2 = array[++i];
                    }
                    else {
                        this.H.write(1);
                        this.H.write(b4 << 4 | 0x0);
                        this.A(2);
                        n2 = -1;
                    }
                }
            }
            else {
                if (n2 > 2) {
                    final int n5 = b << 4 | b2;
                    this.H.write(n2);
                    this.H.write(n5);
                    this.A(2);
                }
                else if (n3 < 0) {
                    array2[++n3] = b;
                    array2[++n3] = b2;
                    array2[++n3] = b3;
                    array2[++n3] = b4;
                }
                else if (n3 < 253) {
                    array2[++n3] = b3;
                    array2[++n3] = b4;
                }
                else {
                    this.H.write(0);
                    this.H.write(n3 + 1);
                    this.A(2);
                    for (int k = 0; k < n3; k += 2) {
                        this.H.write((byte)(array2[k] << 4 | array2[k + 1]));
                        this.A(1);
                    }
                    this.H.write(0);
                    this.A(1);
                    n3 = -1;
                }
                b = b3;
                b2 = b4;
                n2 = 2;
            }
            if (i >= n - 2) {
                if (n3 == -1 && n2 >= 2) {
                    if (i == n - 2) {
                        if (array[++i] == b) {
                            ++n2;
                            final int n6 = b << 4 | b2;
                            this.H.write(n2);
                            this.H.write(n6);
                            this.A(2);
                        }
                        else {
                            final int n7 = b << 4 | b2;
                            this.H.write(n2);
                            this.H.write(n7);
                            this.H.write(1);
                            this.H.write(array[i] << 4 | 0x0);
                            final int n8 = array[i] << 4 | 0x0;
                            this.A(4);
                        }
                    }
                    else {
                        this.H.write(n2);
                        this.H.write(b << 4 | b2);
                        this.A(2);
                    }
                }
                else if (n3 > -1) {
                    if (i == n - 2) {
                        array2[++n3] = array[++i];
                    }
                    if (n3 >= 2) {
                        this.H.write(0);
                        this.H.write(n3 + 1);
                        this.A(2);
                        for (int l = 0; l < n3; l += 2) {
                            this.H.write((byte)(array2[l] << 4 | array2[l + 1]));
                            this.A(1);
                        }
                        if (!this.B(n3 + 1)) {
                            this.H.write(array2[n3] << 4 | 0x0);
                            this.A(1);
                        }
                        if (!this.B((int)Math.ceil((n3 + 1) / 2))) {
                            this.H.write(0);
                            this.A(1);
                        }
                    }
                    else {
                        switch (n3) {
                            case 0: {
                                this.H.write(1);
                                this.H.write(array2[0] << 4 | 0x0);
                                this.A(2);
                                break;
                            }
                            case 1: {
                                this.H.write(2);
                                this.H.write(array2[0] << 4 | array2[1]);
                                this.A(2);
                                break;
                            }
                        }
                    }
                }
                this.H.write(0);
                this.H.write(0);
                this.A(2);
            }
        }
    }
    
    private synchronized void A(final int n) {
        this.D += n;
    }
    
    private boolean B(final int n) {
        return n % 2 == 0;
    }
    
    private void A(final int n, final int n2) throws IOException {
        this.H.write(66);
        this.H.write(77);
        this.C(n);
        this.H.write(0);
        this.H.write(0);
        this.H.write(0);
        this.H.write(0);
        this.C(n2);
    }
    
    private void B(final int n, final int n2) throws IOException {
        this.C(n);
        this.C(this.E);
        this.C(this.I);
        this.D(1);
        this.D(n2);
    }
    
    public void D(final int n) throws IOException {
        this.H.write(n & 0xFF);
        this.H.write((n & 0xFF00) >> 8);
    }
    
    public void C(final int n) throws IOException {
        this.H.write(n & 0xFF);
        this.H.write((n & 0xFF00) >> 8);
        this.H.write((n & 0xFF0000) >> 16);
        this.H.write((n & 0xFF000000) >> 24);
    }
    
    private void C(final int n, final int n2) throws IOException {
        ((g)this.H).A(n2);
        this.C(n);
    }
}
