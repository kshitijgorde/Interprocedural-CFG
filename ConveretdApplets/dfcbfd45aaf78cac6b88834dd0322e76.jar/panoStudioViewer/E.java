// 
// Decompiled by Procyon v0.5.30
// 

package panoStudioViewer;

import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.DirectColorModel;
import java.awt.Image;
import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

public class E implements ImageProducer
{
    int[] f;
    int V;
    int Q;
    ImageProducer W;
    int[] J;
    int[] S;
    int[] U;
    private static final int d = 14;
    private static final int X = 40;
    private int Z;
    private int b;
    private int P;
    private int L;
    private int _;
    private int A;
    private int O;
    private int N;
    private int T;
    private int c;
    private int C;
    private int H;
    private int G;
    private short F;
    private short B;
    public static final int a = 0;
    public static final int E = 1;
    public static final int I = 2;
    public static final int D = 3;
    private byte[] K;
    private byte[] Y;
    private byte[] M;
    ColorModel R;
    ImageConsumer e;
    
    E(final Image image) throws InterruptedException, OutOfMemoryError {
        this.W = null;
        this.H = 0;
        this.G = 0;
        this.R = new DirectColorModel(32, 16711680, 65280, 255, 0);
        this.V = image.getWidth(null);
        this.Q = image.getHeight(null);
        for (int i = 0; i < 5; ++i) {
            System.currentTimeMillis();
            try {
                this.f = new int[this.V * this.Q];
                new PixelGrabber((this.V != image.getWidth(null)) ? image.getScaledInstance(this.V, this.Q, 2) : image, 0, 0, this.V, this.Q, this.f, 0, this.V).grabPixels();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                if (i == 0) {
                    System.err.println("Not enough memory for full-size image. Trying smaller image...");
                }
                this.f = null;
                System.gc();
                if (i == 4) {
                    throw new OutOfMemoryError();
                }
            }
            if (this.f != null) {
                break;
            }
            this.V *= (int)0.7;
            this.Q *= (int)0.7;
            if (this.V == 0) {
                this.V = 1;
            }
            if (this.Q == 0) {
                this.Q = 1;
            }
        }
    }
    
    E(final int v, final int q) {
        this.W = null;
        this.H = 0;
        this.G = 0;
        this.R = new DirectColorModel(32, 16711680, 65280, 255, 0);
        this.f = new int[v * q];
        this.V = v;
        this.Q = q;
        this.W = new MemoryImageSource(v, q, this.f, 0, v);
        ((MemoryImageSource)this.W).setAnimated(true);
    }
    
    E(final int n, final int n2, final boolean b) {
        this.W = null;
        this.H = 0;
        this.G = 0;
        this.R = new DirectColorModel(32, 16711680, 65280, 255, 0);
        if (!b) {
            this.f = new int[n * n2];
            this.V = n;
            this.Q = n2;
            this.W = new MemoryImageSource(n, n2, new DirectColorModel(32, 16711680, 65280, 255, 0), this.f, 0, n);
            ((MemoryImageSource)this.W).setAnimated(true);
        }
        else {
            this.f = new int[n * n2];
            this.V = n;
            this.Q = n2;
            this.W = this;
        }
    }
    
    public void A(final E e, final int n, final int n2, int n3) {
        n3 &= 0xFF;
        final int n4 = (e.Q < this.Q - n2) ? (e.Q + n2) : this.Q;
        final int n5 = (e.V < this.V - n) ? (e.V + n) : this.V;
        for (int i = n2; i < n4; ++i) {
            final int n6 = i - n2;
            for (int j = n; j < n5; ++j) {
                final int n7 = e.f[n6 * e.V + (j - n)];
                final int n8 = (n7 >> 24 & 0xFF) * n3 / 255;
                if (n8 == 255) {
                    this.f[i * this.V + j] = n7;
                }
                else if (n8 != 0) {
                    final int n9 = this.f[i * this.V + j];
                    final int n10 = n9 >> 24 & 0xFF;
                    this.f[i * this.V + j] = (((n7 >> 16 & 0xFF) * n8 + (n9 >> 16 & 0xFF) * (255 - n8)) / 255 << 16 | ((n7 >> 8 & 0xFF) * n8 + (n9 >> 8 & 0xFF) * (255 - n8)) / 255 << 8 | ((n7 & 0xFF) * n8 + (n9 & 0xFF) * (255 - n8)) / 255 | ((n10 > n8) ? n10 : n8) << 24);
                }
            }
        }
    }
    
    public boolean A(final byte[] array) {
        try {
            return this.B(array);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static int A(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | (array[n + 3] & 0xFF) << 24;
    }
    
    public static short B(final byte[] array, final int n) {
        return (short)((array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8);
    }
    
    public boolean B(final byte[] array) {
        int a = 0;
        final byte[] array2 = new byte[54];
        for (int i = 0; i < 54; ++i) {
            array2[i] = array[a++];
        }
        if (array2[0] != 66 || array2[1] != 77) {
            return false;
        }
        this.Z = A(array2, 2);
        this.b = A(array2, 10);
        if (A(array2, 14) != 40) {
            return false;
        }
        this.P = A(array2, 14);
        this.L = A(array2, 18);
        this._ = A(array2, 22);
        this.F = B(array2, 26);
        this.B = B(array2, 28);
        this.A = A(array2, 30);
        this.O = A(array2, 34);
        this.N = A(array2, 38);
        this.T = A(array2, 42);
        this.c = A(array2, 46);
        this.C = A(array2, 50);
        if (this.B != 8) {
            return false;
        }
        if (this._ == 0 || this.L == 0 || this.F != 1) {
            return false;
        }
        if (this.A != 0 && this.A != 2 && this.A != 1) {
            return false;
        }
        if (this.L < this.V || ((this._ < 0) ? (-this._) : this._) < this.Q) {
            return false;
        }
        final int c = (this.b - this.P - 14) / 4;
        if (this.c == 0 && this.B <= 8) {
            this.c = c;
        }
        final int n = (this.L * this.B - 1) / 32 * 4 + 4;
        if (c > 0) {
            a += c * 4;
        }
        this.K = new byte[n];
        this.Y = new byte[n];
        this.M = new byte[4];
        if (this.A != 0) {
            for (int j = 0; j < this.Q; ++j) {
                for (int k = 0; k < this.V; ++k) {
                    this.f[j * this.V + k] &= 0xFFFFFF;
                }
            }
        }
        final double n2 = this.L / this.V;
        final double n3 = this._ / this.Q;
        final int[] array3 = new int[this.L];
        for (int l = this._ - 1; l >= 0; --l) {
            try {
                for (int n4 = 0; n4 < this.L; ++n4) {
                    array3[n4] = 0;
                }
                a = this.A(array, a, 0, n, this.L, this.B, this.A, array3, this);
                final int n5 = (int)(l / n3);
                if (n5 < this.Q) {
                    for (int n6 = 0; n6 < this.V; ++n6) {
                        this.f[n5 * this.V + n6] = ((array3[(int)(n6 * n2)] & 0xFF000000) | (this.f[n5 * this.V + n6] & 0xFFFFFF));
                    }
                }
                if (a == -1) {
                    return true;
                }
            }
            catch (Exception ex) {
                return true;
            }
        }
        return true;
    }
    
    private int A(final byte[] array, int n, final int n2) {
        final byte[] array2 = new byte[4];
        for (int i = 0; i < n2; ++i) {
            n += 4;
        }
        return n;
    }
    
    private int A(final byte[] array, int n, int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final E e) {
        switch (n5) {
            case 16:
            case 24:
            case 32: {
                return -1;
            }
            case 1:
            case 4:
            case 8: {
                if (n6 == 0) {
                    try {
                        for (int i = 0; i < this.K.length; ++i) {
                            this.K[i] = array[n++];
                        }
                    }
                    catch (Exception ex) {}
                    int j = 0;
                    while (j < n4) {
                        for (int n7 = 1; n7 <= 8 / n5 && j < n4; ++n7, ++j) {
                            array2[j + n2] = (((this.K[j] & (1 << n5) - 1 << 8 - n7 * n5) >> 8 - n7 * n5 & 0xFF) << 24 | (array2[j + n2] & 0xFFFFFF));
                        }
                    }
                    break;
                }
                int n8 = 0;
                while (e.Q - 1 >= 0 && n8 <= n4) {
                    this.Y[0] = array[n++];
                    this.Y[1] = array[n++];
                    if (this.Y[0] != 0) {
                        int n9 = 0;
                        while (n9 < (this.Y[0] & 0xFF) && n8 < n4) {
                            for (int n10 = 1; n10 <= 8 / n5 && n8 < n4 && n9 < (this.Y[0] & 0xFF); ++n10, ++n8, ++n9) {
                                array2[n8 + n2] = (((this.Y[1] & (1 << n5) - 1 << 8 - n10 * n5) >> 8 - n10 * n5 & 0xFF) << 24 | (array2[n8 + n2] & 0xFFFFFF));
                            }
                        }
                    }
                    if (this.Y[0] == 0 && (this.Y[1] & 0xFF) > 2) {
                        int n11 = this.Y[1] & 0xFF;
                        for (int k = 0; k < n11; k += 8 / n5) {
                            this.M[0] = array[n++];
                            for (int n12 = 1; n12 <= 8 / n5 && n8 < n4; ++n12, ++n8) {
                                array2[n8 + n2] = (((this.M[0] & (1 << n5) - 1 << 8 - n12 * n5) >> 8 - n12 * n5 & 0xFF) << 24 | (array2[n8 + n2] & 0xFFFFFF));
                            }
                        }
                        if (n11 % 2 != 0 && n5 == 4) {
                            ++n11;
                        }
                        if (n11 / (8 / n5) % 2 != 0) {
                            this.M[0] = array[n++];
                        }
                    }
                    if (this.Y[0] == 0 && this.Y[1] == 0) {
                        break;
                    }
                    if (this.Y[0] == 0 && this.Y[1] == 1) {
                        break;
                    }
                    if (this.Y[0] != 0 || this.Y[1] != 2) {
                        continue;
                    }
                    this.Y[0] = array[n++];
                    this.Y[1] = array[n++];
                    n8 += (this.Y[0] & 0xFF);
                    n2 -= n4 * (this.Y[1] & 0xFF);
                }
                break;
            }
        }
        return n;
    }
    
    public void addConsumer(final ImageConsumer e) {
        (this.e = e).setDimensions(this.V, this.Q);
        this.e.setHints(30);
        this.e.setColorModel(this.R);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return imageConsumer == this.e;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.e) {
            this.e = null;
        }
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.e && this.e != null) {
            this.e.setPixels(0, 0, this.V, this.Q, this.R, this.f, 0, this.V);
            this.e.imageComplete(2);
        }
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public void A() {
        if (this.e != null) {}
        this.requestTopDownLeftRightResend(this.e);
    }
}
