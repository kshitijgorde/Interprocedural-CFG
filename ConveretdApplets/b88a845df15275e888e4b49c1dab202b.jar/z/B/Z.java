// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferByte;
import java.awt.Point;
import java.awt.image.Raster;
import java.awt.image.DirectColorModel;
import java.awt.color.ColorSpace;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;

class Z extends a
{
    private BufferedInputStream O;
    private long v;
    private long P;
    private long N;
    private long M;
    private byte[] t;
    private int s;
    private int S;
    private boolean T;
    private int Q;
    private int l;
    private int n;
    private int U;
    private int u;
    private static final int X = 0;
    private static final int f = 1;
    private static final int a = 2;
    private static final int j = 3;
    private static final int W = 4;
    private static final int e = 5;
    private static final int _ = 6;
    private static final int k = 7;
    private static final int Z = 8;
    private static final int g = 9;
    private static final int V = 10;
    private static final int d = 11;
    private static final int Y = 12;
    private static final int m = 13;
    private static final int r = 14;
    private static final int w = 15;
    private static final int q = 0;
    private static final int o = 1;
    private static final int i = 2;
    private static final int h = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int p = 3;
    private WritableRaster R;
    
    public Z(final InputStream inputStream) {
        this.R = null;
        if (inputStream instanceof BufferedInputStream) {
            this.O = (BufferedInputStream)inputStream;
        }
        else {
            this.O = new BufferedInputStream(inputStream);
        }
        try {
            if (this.B(this.O) != 66 || this.B(this.O) != 77) {
                throw new RuntimeException(z.B.m.A("BMPImageDecoder0"));
            }
            this.v = this.F(this.O);
            this.E(this.O);
            this.E(this.O);
            this.P = this.F(this.O);
            final long f = this.F(this.O);
            if (f == 12L) {
                this.B = this.E(this.O);
                this.L = this.E(this.O);
            }
            else {
                this.B = this.G(this.O);
                this.L = this.G(this.O);
            }
            final int e = this.E(this.O);
            this.Q = this.E(this.O);
            this.G.put("color_planes", new Integer(e));
            this.G.put("bits_per_pixel", new Integer(this.Q));
            this.S = 3;
            if (f == 12L) {
                this.G.put("bmp_version", "BMP v. 2.x");
                if (this.Q == 1) {
                    this.s = 0;
                }
                else if (this.Q == 4) {
                    this.s = 1;
                }
                else if (this.Q == 8) {
                    this.s = 2;
                }
                else if (this.Q == 24) {
                    this.s = 3;
                }
                final int n = (int)((this.P - 14L - f) / 3L) * 3;
                this.t = new byte[n];
                this.O.read(this.t, 0, n);
                this.G.put("palette", this.t);
            }
            else {
                this.N = this.F(this.O);
                this.M = this.F(this.O);
                final long n2 = this.G(this.O);
                final long n3 = this.G(this.O);
                final long f2 = this.F(this.O);
                final long f3 = this.F(this.O);
                switch ((int)this.N) {
                    case 0: {
                        this.G.put("compression", "BI_RGB");
                        break;
                    }
                    case 1: {
                        this.G.put("compression", "BI_RLE8");
                        break;
                    }
                    case 2: {
                        this.G.put("compression", "BI_RLE4");
                        break;
                    }
                    case 3: {
                        this.G.put("compression", "BI_BITFIELDS");
                        break;
                    }
                }
                this.G.put("x_pixels_per_meter", new Long(n2));
                this.G.put("y_pixels_per_meter", new Long(n3));
                this.G.put("colors_used", new Long(f2));
                this.G.put("colors_important", new Long(f3));
                if (f == 40L) {
                    switch ((int)this.N) {
                        case 0:
                        case 1:
                        case 2: {
                            final int n4 = (int)((this.P - 14L - f) / 4L) * 4;
                            this.t = new byte[n4];
                            this.O.read(this.t, 0, n4);
                            this.G.put("palette", this.t);
                            if (this.Q == 1) {
                                this.s = 4;
                            }
                            else if (this.Q == 4) {
                                this.s = 5;
                            }
                            else if (this.Q == 8) {
                                this.s = 6;
                            }
                            else if (this.Q == 24) {
                                this.s = 7;
                            }
                            else if (this.Q == 16) {
                                this.s = 8;
                                this.l = 31744;
                                this.n = 992;
                                this.U = 31;
                                this.G.put("red_mask", new Integer(this.l));
                                this.G.put("green_mask", new Integer(this.n));
                                this.G.put("blue_mask", new Integer(this.U));
                            }
                            else if (this.Q == 32) {
                                this.s = 9;
                                this.l = 16711680;
                                this.n = 65280;
                                this.U = 255;
                                this.G.put("red_mask", new Integer(this.l));
                                this.G.put("green_mask", new Integer(this.n));
                                this.G.put("blue_mask", new Integer(this.U));
                            }
                            this.G.put("bmp_version", "BMP v. 3.x");
                            break;
                        }
                        case 3: {
                            if (this.Q == 16) {
                                this.s = 8;
                            }
                            else if (this.Q == 32) {
                                this.s = 9;
                            }
                            this.l = (int)this.F(this.O);
                            this.n = (int)this.F(this.O);
                            this.U = (int)this.F(this.O);
                            this.G.put("red_mask", new Integer(this.l));
                            this.G.put("green_mask", new Integer(this.n));
                            this.G.put("blue_mask", new Integer(this.U));
                            if (f2 != 0L) {
                                final int n5 = (int)f2 * 4;
                                this.t = new byte[n5];
                                this.O.read(this.t, 0, n5);
                                this.G.put("palette", this.t);
                            }
                            this.G.put("bmp_version", "BMP v. 3.x NT");
                            break;
                        }
                        default: {
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder1"));
                        }
                    }
                }
                else {
                    if (f != 108L) {
                        this.G.put("bmp_version", "BMP v. 5.x");
                        throw new RuntimeException(z.B.m.A("BMPImageDecoder4"));
                    }
                    this.G.put("bmp_version", "BMP v. 4.x");
                    this.l = (int)this.F(this.O);
                    this.n = (int)this.F(this.O);
                    this.U = (int)this.F(this.O);
                    this.u = (int)this.F(this.O);
                    final long f4 = this.F(this.O);
                    final int g = this.G(this.O);
                    final int g2 = this.G(this.O);
                    final int g3 = this.G(this.O);
                    final int g4 = this.G(this.O);
                    final int g5 = this.G(this.O);
                    final int g6 = this.G(this.O);
                    final int g7 = this.G(this.O);
                    final int g8 = this.G(this.O);
                    final int g9 = this.G(this.O);
                    final long f5 = this.F(this.O);
                    final long f6 = this.F(this.O);
                    final long f7 = this.F(this.O);
                    final int n6 = (int)((this.P - 14L - f) / 4L) * 4;
                    this.t = new byte[n6];
                    this.O.read(this.t, 0, n6);
                    if (this.t != null || this.t.length != 0) {
                        this.G.put("palette", this.t);
                    }
                    switch ((int)f4) {
                        case 0: {
                            this.G.put("color_space", "LCS_CALIBRATED_RGB");
                            this.G.put("redX", new Integer(g));
                            this.G.put("redY", new Integer(g2));
                            this.G.put("redZ", new Integer(g3));
                            this.G.put("greenX", new Integer(g4));
                            this.G.put("greenY", new Integer(g5));
                            this.G.put("greenZ", new Integer(g6));
                            this.G.put("blueX", new Integer(g7));
                            this.G.put("blueY", new Integer(g8));
                            this.G.put("blueZ", new Integer(g9));
                            this.G.put("gamma_red", new Long(f5));
                            this.G.put("gamma_green", new Long(f6));
                            this.G.put("gamma_blue", new Long(f7));
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder2"));
                        }
                        case 1: {
                            this.G.put("color_space", "LCS_sRGB");
                            break;
                        }
                        case 2: {
                            this.G.put("color_space", "LCS_CMYK");
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder2"));
                        }
                    }
                    if (this.Q == 1) {
                        this.s = 10;
                    }
                    else if (this.Q == 4) {
                        this.s = 11;
                    }
                    else if (this.Q == 8) {
                        this.s = 12;
                    }
                    else if (this.Q == 16) {
                        this.s = 13;
                        if ((int)this.N == 0) {
                            this.l = 31744;
                            this.n = 992;
                            this.U = 31;
                        }
                    }
                    else if (this.Q == 24) {
                        this.s = 14;
                    }
                    else if (this.Q == 32) {
                        this.s = 15;
                        if ((int)this.N == 0) {
                            this.l = 16711680;
                            this.n = 65280;
                            this.U = 255;
                        }
                    }
                    this.G.put("red_mask", new Integer(this.l));
                    this.G.put("green_mask", new Integer(this.n));
                    this.G.put("blue_mask", new Integer(this.U));
                    this.G.put("alpha_mask", new Integer(this.u));
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder5"));
        }
        if (this.L > 0) {
            this.T = true;
        }
        else {
            this.T = false;
            this.L = Math.abs(this.L);
        }
        this.J = this.B;
        this.F = this.L;
        if (this.Q == 1 || this.Q == 4 || this.Q == 8) {
            this.S = 1;
            if (this.Q == 8) {
                this.I = z.B.U.B(0, this.B, this.L, this.S);
            }
            else {
                this.I = new MultiPixelPackedSampleModel(0, this.B, this.L, this.Q);
            }
            int n7;
            byte[] array;
            byte[] array2;
            byte[] array3;
            if (this.s == 0 || this.s == 1 || this.s == 2) {
                n7 = this.t.length / 3;
                if (n7 > 256) {
                    n7 = 256;
                }
                array = new byte[n7];
                array2 = new byte[n7];
                array3 = new byte[n7];
                for (int i = 0; i < n7; ++i) {
                    final int n8 = 3 * i;
                    array3[i] = this.t[n8];
                    array2[i] = this.t[n8 + 1];
                    array[i] = this.t[n8 + 2];
                }
            }
            else {
                n7 = this.t.length / 4;
                if (n7 > 256) {
                    n7 = 256;
                }
                array = new byte[n7];
                array2 = new byte[n7];
                array3 = new byte[n7];
                for (int j = 0; j < n7; ++j) {
                    final int n9 = 4 * j;
                    array3[j] = this.t[n9];
                    array2[j] = this.t[n9 + 1];
                    array[j] = this.t[n9 + 2];
                }
            }
            this.H = new IndexColorModel(this.Q, n7, array, array2, array3);
        }
        else if (this.Q == 16) {
            this.S = 3;
            this.I = new SinglePixelPackedSampleModel(1, this.B, this.L, new int[] { this.l, this.n, this.U });
            this.H = new DirectColorModel(ColorSpace.getInstance(1000), 16, this.l, this.n, this.U, 0, false, 1);
        }
        else if (this.Q == 32) {
            this.S = ((this.u == 0) ? 3 : 4);
            int[] array5;
            if (this.S == 3) {
                final int[] array4 = array5 = new int[3];
                array4[0] = this.l;
                array4[1] = this.n;
                array4[2] = this.U;
            }
            else {
                final int[] array6 = array5 = new int[4];
                array6[0] = this.l;
                array6[1] = this.n;
                array6[2] = this.U;
                array6[3] = this.u;
            }
            this.I = new SinglePixelPackedSampleModel(3, this.B, this.L, array5);
            this.H = new DirectColorModel(ColorSpace.getInstance(1000), 32, this.l, this.n, this.U, this.u, false, 3);
        }
        else {
            this.S = 3;
            this.I = z.B.U.B(0, this.B, this.L, this.S);
            this.H = z.B.J.A(this.I);
        }
    }
    
    private void C(final byte[] array, final int n) {
        int n2 = 0;
        final int n3 = (int)Math.ceil(this.B / 8.0);
        final int n4 = n3 % 4;
        if (n4 != 0) {
            n2 = 4 - n4;
        }
        final int n5 = (n3 + n2) * this.L;
        final byte[] array2 = new byte[n5];
        try {
            for (int i = 0; i < n5; i += this.O.read(array2, i, n5 - i)) {}
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
        if (this.T) {
            for (int j = 0; j < this.L; ++j) {
                System.arraycopy(array2, n5 - (j + 1) * (n3 + n2), array, j * n3, n3);
            }
        }
        else {
            for (int k = 0; k < this.L; ++k) {
                System.arraycopy(array2, k * (n3 + n2), array, k * n3, n3);
            }
        }
    }
    
    private void B(final byte[] array, final int n) {
        int n2 = 0;
        final int n3 = (int)Math.ceil(this.B / 2.0);
        final int n4 = n3 % 4;
        if (n4 != 0) {
            n2 = 4 - n4;
        }
        final int n5 = (n3 + n2) * this.L;
        final byte[] array2 = new byte[n5];
        try {
            for (int i = 0; i < n5; i += this.O.read(array2, i, n5 - i)) {}
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
        if (this.T) {
            for (int j = 0; j < this.L; ++j) {
                System.arraycopy(array2, n5 - (j + 1) * (n3 + n2), array, j * n3, n3);
            }
        }
        else {
            for (int k = 0; k < this.L; ++k) {
                System.arraycopy(array2, k * (n3 + n2), array, k * n3, n3);
            }
        }
    }
    
    private void A(final byte[] array, final int n) {
        int n2 = 0;
        final int n3 = this.B * 8;
        if (n3 % 32 != 0) {
            n2 = (int)Math.ceil(((n3 / 32 + 1) * 32 - n3) / 8.0);
        }
        final int n4 = (this.B + n2) * this.L;
        final byte[] array2 = new byte[n4];
        try {
            for (int i = 0; i < n4; i += this.O.read(array2, i, n4 - i)) {}
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
        if (this.T) {
            for (int j = 0; j < this.L; ++j) {
                System.arraycopy(array2, n4 - (j + 1) * (this.B + n2), array, j * this.B, this.B);
            }
        }
        else {
            for (int k = 0; k < this.L; ++k) {
                System.arraycopy(array2, k * (this.B + n2), array, k * this.B, this.B);
            }
        }
    }
    
    private void B(final byte[] array) {
        int n = 0;
        final int n2 = this.B * 24;
        if (n2 % 32 != 0) {
            n = (int)Math.ceil(((n2 / 32 + 1) * 32 - n2) / 8.0);
        }
        int n3 = (int)this.M;
        if (n3 == 0) {
            n3 = (int)(this.v - this.P);
        }
        final byte[] array2 = new byte[n3];
        try {
            for (int i = 0; i < n3; i += this.O.read(array2, i, n3 - i)) {}
        }
        catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        int n4 = 0;
        if (this.T) {
            final int n5 = this.B * this.L * 3 - 1;
            int n6 = -n;
            for (int j = 0; j < this.L; ++j) {
                int n7 = n5 - (j + 1) * this.B * 3 + 1;
                n6 += n;
                for (int k = 0; k < this.B; ++k) {
                    array[n7++] = array2[n6++];
                    array[n7++] = array2[n6++];
                    array[n7++] = array2[n6++];
                }
            }
        }
        else {
            int n8 = -n;
            for (int l = 0; l < this.L; ++l) {
                n8 += n;
                for (int n9 = 0; n9 < this.B; ++n9) {
                    array[n4++] = array2[n8++];
                    array[n4++] = array2[n8++];
                    array[n4++] = array2[n8++];
                }
            }
        }
    }
    
    private void A(final short[] array) {
        int n = 0;
        final int n2 = this.B * 16;
        if (n2 % 32 != 0) {
            n = (int)Math.ceil(((n2 / 32 + 1) * 32 - n2) / 8.0);
        }
        if ((int)this.M == 0) {
            final int n3 = (int)(this.v - this.P);
        }
        int n4 = 0;
        try {
            if (this.T) {
                final int n5 = this.B * this.L - 1;
                for (int i = 0; i < this.L; ++i) {
                    int n6 = n5 - (i + 1) * this.B + 1;
                    for (int j = 0; j < this.B; ++j) {
                        array[n6++] = (short)(this.E(this.O) & 0xFFFF);
                    }
                    for (int k = 0; k < n; ++k) {
                        this.O.read();
                    }
                }
            }
            else {
                for (int l = 0; l < this.L; ++l) {
                    for (int n7 = 0; n7 < this.B; ++n7) {
                        array[n4++] = (short)(this.E(this.O) & 0xFFFF);
                    }
                    for (int n8 = 0; n8 < n; ++n8) {
                        this.O.read();
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
    }
    
    private void A(final int[] array) {
        if ((int)this.M == 0) {
            final int n = (int)(this.v - this.P);
        }
        int n2 = 0;
        try {
            if (this.T) {
                final int n3 = this.B * this.L - 1;
                for (int i = 0; i < this.L; ++i) {
                    int n4 = n3 - (i + 1) * this.B + 1;
                    for (int j = 0; j < this.B; ++j) {
                        array[n4++] = (int)this.F(this.O);
                    }
                }
            }
            else {
                for (int k = 0; k < this.L; ++k) {
                    for (int l = 0; l < this.B; ++l) {
                        array[n2++] = (int)this.F(this.O);
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
    }
    
    private void A(byte[] array) {
        int n = (int)this.M;
        if (n == 0) {
            n = (int)(this.v - this.P);
        }
        int n2 = 0;
        final int n3 = this.B % 4;
        if (n3 != 0) {
            n2 = 4 - n3;
        }
        final byte[] array2 = new byte[n];
        try {
            for (int i = 0; i < n; i += this.O.read(array2, i, n - i)) {}
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
        final byte[] a = this.A(n, n2, array2);
        final int n4 = this.B * this.L;
        if (this.T) {
            final int b = this.B;
            for (int j = 0; j < this.L; ++j) {
                System.arraycopy(a, n4 - (j + 1) * b, array, j * b, b);
            }
        }
        else {
            array = a;
        }
    }
    
    private byte[] A(final int n, final int n2, final byte[] array) {
        final byte[] array2 = new byte[this.B * this.L];
        int i = 0;
        int n3 = 0;
        boolean b = false;
        while (i != n) {
            final int n4 = array[i++] & 0xFF;
            if (n4 == 0) {
                switch (array[i++] & 0xFF) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        b = true;
                        break;
                    }
                    case 2: {
                        n3 += (array[i++] & 0xFF) + (array[i] & 0xFF) * this.B;
                        break;
                    }
                    default: {
                        final int n5 = array[i - 1] & 0xFF;
                        for (int j = 0; j < n5; ++j) {
                            array2[n3++] = (byte)(array[i++] & 0xFF);
                        }
                        if (!this.E(n5)) {
                            ++i;
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                for (int k = 0; k < n4; ++k) {
                    array2[n3++] = (byte)(array[i] & 0xFF);
                }
                ++i;
            }
            if (b) {
                break;
            }
        }
        return array2;
    }
    
    private int[] F() {
        int n = (int)this.M;
        if (n == 0) {
            n = (int)(this.v - this.P);
        }
        int n2 = 0;
        final int n3 = this.B % 4;
        if (n3 != 0) {
            n2 = 4 - n3;
        }
        final int[] array = new int[n];
        try {
            for (int i = 0; i < n; ++i) {
                array[i] = this.O.read();
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(z.B.m.A("BMPImageDecoder6"));
        }
        int[] a = this.A(n, n2, array);
        if (this.T) {
            final int[] array2 = a;
            a = new int[this.B * this.L];
            int j = 0;
            for (int k = this.L - 1; k >= 0; --k) {
                for (int n4 = k * this.B; j != j + this.B; a[j++] = array2[n4++]) {}
            }
        }
        return a;
    }
    
    private int[] A(final int n, final int n2, final int[] array) {
        final int[] array2 = new int[this.B * this.L];
        int i = 0;
        int n3 = 0;
        boolean b = false;
        while (i != n) {
            final int n4 = array[i++];
            if (n4 == 0) {
                switch (array[i++]) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        b = true;
                        break;
                    }
                    case 2: {
                        n3 += array[i++] + array[i] * this.B;
                        break;
                    }
                    default: {
                        final int n5 = array[i - 1];
                        for (int j = 0; j < n5; ++j) {
                            array2[n3++] = (this.E(j) ? ((array[i] & 0xF0) >> 4) : (array[i++] & 0xF));
                        }
                        if (!this.E(n5)) {
                            ++i;
                        }
                        if (!this.E((int)Math.ceil(n5 / 2))) {
                            ++i;
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                final int[] array3 = { (array[i] & 0xF0) >> 4, array[i] & 0xF };
                for (int k = 0; k < n4; ++k) {
                    array2[n3++] = array3[k % 2];
                }
                ++i;
            }
            if (b) {
                break;
            }
        }
        return array2;
    }
    
    private boolean E(final int n) {
        return n % 2 == 0;
    }
    
    private int B(final InputStream inputStream) throws IOException {
        return inputStream.read() & 0xFF;
    }
    
    private int C(final InputStream inputStream) throws IOException {
        return (this.B(inputStream) << 8 | this.B(inputStream)) & 0xFFFF;
    }
    
    private int E(final InputStream inputStream) throws IOException {
        return this.C(inputStream);
    }
    
    private long A(final InputStream inputStream) throws IOException {
        return (this.B(inputStream) << 24 | this.B(inputStream) << 16 | this.B(inputStream) << 8 | this.B(inputStream)) & -1L;
    }
    
    private int D(final InputStream inputStream) throws IOException {
        return this.B(inputStream) << 24 | this.B(inputStream) << 16 | this.B(inputStream) << 8 | this.B(inputStream);
    }
    
    private long F(final InputStream inputStream) throws IOException {
        return this.A(inputStream);
    }
    
    private int G(final InputStream inputStream) throws IOException {
        return this.D(inputStream);
    }
    
    private synchronized Raster A(final int n, final int n2) {
        if (this.R != null) {
            return this.R;
        }
        final WritableRaster a = z.B.U.A(this.I, new Point(this.B(n), this.C(n2)));
        byte[] array = null;
        short[] array2 = null;
        int[] array3 = null;
        if (this.I.getDataType() == 0) {
            array = ((DataBufferByte)a.getDataBuffer()).getData();
        }
        else if (this.I.getDataType() == 1) {
            array2 = ((DataBufferUShort)a.getDataBuffer()).getData();
        }
        else if (this.I.getDataType() == 3) {
            array3 = ((DataBufferInt)a.getDataBuffer()).getData();
        }
        Label_0529: {
            switch (this.s) {
                case 0: {
                    this.C(array, 3);
                    break;
                }
                case 1: {
                    this.B(array, 3);
                    break;
                }
                case 2: {
                    this.A(array, 3);
                    break;
                }
                case 3: {
                    this.B(array);
                    break;
                }
                case 4: {
                    this.C(array, 4);
                    break;
                }
                case 5: {
                    switch ((int)this.N) {
                        case 0: {
                            this.B(array, 4);
                            break;
                        }
                        case 2: {
                            a.setPixels(0, 0, this.B, this.L, this.F());
                            break;
                        }
                        default: {
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder3"));
                        }
                    }
                    break;
                }
                case 6: {
                    switch ((int)this.N) {
                        case 0: {
                            this.A(array, 4);
                            break Label_0529;
                        }
                        case 1: {
                            this.A(array);
                            break Label_0529;
                        }
                        default: {
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder3"));
                        }
                    }
                    break;
                }
                case 7: {
                    this.B(array);
                    break;
                }
                case 8: {
                    this.A(array2);
                    break;
                }
                case 9: {
                    this.A(array3);
                    break;
                }
                case 10: {
                    this.C(array, 4);
                    break;
                }
                case 11: {
                    switch ((int)this.N) {
                        case 0: {
                            this.B(array, 4);
                            break Label_0529;
                        }
                        case 2: {
                            a.setPixels(0, 0, this.B, this.L, this.F());
                            break Label_0529;
                        }
                        default: {
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder3"));
                        }
                    }
                    break;
                }
                case 12: {
                    switch ((int)this.N) {
                        case 0: {
                            this.A(array, 4);
                            break Label_0529;
                        }
                        case 1: {
                            this.A(array);
                            break Label_0529;
                        }
                        default: {
                            throw new RuntimeException(z.B.m.A("BMPImageDecoder3"));
                        }
                    }
                    break;
                }
                case 13: {
                    this.A(array2);
                    break;
                }
                case 14: {
                    this.B(array);
                    break;
                }
                case 15: {
                    this.A(array3);
                    break;
                }
            }
        }
        return this.R = a;
    }
    
    public synchronized Raster getTile(final int n, final int n2) {
        if (n != 0 || n2 != 0) {
            throw new IllegalArgumentException(z.B.m.A("BMPImageDecoder7"));
        }
        return this.A(n, n2);
    }
}
