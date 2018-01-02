import java.util.Date;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.io.EOFException;
import java.awt.image.ImageProducer;
import sun.awt.image.URLImageSource;
import java.io.ByteArrayInputStream;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.InputStream;
import java.awt.Dimension;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

class ipixc extends URLConnection implements Runnable
{
    static final float b = 0.017453292f;
    private int c;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    int[] g;
    Dimension h;
    float[] i;
    private ipixa j;
    private InputStream k;
    float l;
    float m;
    float n;
    float o;
    private int p;
    private float[] q;
    private float[] r;
    private Thread s;
    private int t;
    private int u;
    private int v;
    private int w;
    private static final String x = "application/jpeg";
    private InputStream y;
    private static int[] z;
    private static int[] A;
    private static int[][][] B;
    private static int[][][] C;
    private static int[][][] D;
    private static int[][] E;
    private static int[] F;
    private int[][] G;
    public static boolean a;
    
    private final void a(final byte[] array) {
        this.a(array, 0, array.length);
    }
    
    final void a() {
        if (this.s != null) {
            this.s.stop();
            this.s = null;
            super.url = null;
            this.j = null;
            this.k = null;
        }
    }
    
    final boolean b() {
        return this.s != null && this.s.isAlive();
    }
    
    private final void a(final byte[] array, final int n, final int n2) {
        final boolean a = ipixa.a;
        int n3 = n;
        while (true) {
            Label_1048: {
                if (!a) {
                    break Label_1048;
                }
                int n4 = ipixc.C[0][array[n3] & 0xFF][0] | ipixc.C[1][array[n3 + 1] & 0xFF][0] | ipixc.C[2][array[n3 + 2] & 0xFF][0] | ipixc.C[3][array[n3 + 3] & 0xFF][0] | ipixc.C[4][array[n3 + 4] & 0xFF][0] | ipixc.C[5][array[n3 + 5] & 0xFF][0] | ipixc.C[6][array[n3 + 6] & 0xFF][0] | ipixc.C[7][array[n3 + 7] & 0xFF][0];
                int n5 = ipixc.C[0][array[n3] & 0xFF][1] | ipixc.C[1][array[n3 + 1] & 0xFF][1] | ipixc.C[2][array[n3 + 2] & 0xFF][1] | ipixc.C[3][array[n3 + 3] & 0xFF][1] | ipixc.C[4][array[n3 + 4] & 0xFF][1] | ipixc.C[5][array[n3 + 5] & 0xFF][1] | ipixc.C[6][array[n3 + 6] & 0xFF][1] | ipixc.C[7][array[n3 + 7] & 0xFF][1];
                int i = 15;
            Block_2:
                while (true) {
                    final int n6 = (ipixc.D[0][n5 & 0xFF][0] | ipixc.D[1][n5 >>> 8 & 0xFF][0] | ipixc.D[2][n5 >>> 16 & 0xFF][0] | ipixc.D[3][n5 >>> 24 & 0xFF][0]) ^ this.G[i][0];
                    final int n7 = (ipixc.D[0][n5 & 0xFF][1] | ipixc.D[1][n5 >>> 8 & 0xFF][1] | ipixc.D[2][n5 >>> 16 & 0xFF][1] | ipixc.D[3][n5 >>> 24 & 0xFF][1]) ^ this.G[i][1];
                    final int n8 = n4 ^ (ipixc.E[7][n7 >>> 10 & 0x3F] | ipixc.E[6][n7 >>> 4 & 0x3F] | ipixc.E[5][(n7 << 2 & 0x3C) | (n6 >>> 30 & 0x3)] | ipixc.E[4][n6 >>> 24 & 0x3F] | ipixc.E[3][n6 >>> 18 & 0x3F] | ipixc.E[2][n6 >>> 12 & 0x3F] | ipixc.E[1][n6 >>> 6 & 0x3F] | ipixc.E[0][n6 & 0x3F]);
                    n4 = n5;
                    n5 = n8;
                    --i;
                    while (i < 0) {
                        i = (ipixc.B[0][n5 & 0xFF][0] | ipixc.B[1][n5 >>> 8 & 0xFF][0] | ipixc.B[2][n5 >>> 16 & 0xFF][0] | ipixc.B[3][n5 >>> 24 & 0xFF][0] | ipixc.B[4][n4 & 0xFF][0] | ipixc.B[5][n4 >>> 8 & 0xFF][0] | ipixc.B[6][n4 >>> 16 & 0xFF][0] | ipixc.B[7][n4 >>> 24 & 0xFF][0]);
                        final int n9 = ipixc.B[0][n5 & 0xFF][1] | ipixc.B[1][n5 >>> 8 & 0xFF][1] | ipixc.B[2][n5 >>> 16 & 0xFF][1] | ipixc.B[3][n5 >>> 24 & 0xFF][1] | ipixc.B[4][n4 & 0xFF][1] | ipixc.B[5][n4 >>> 8 & 0xFF][1] | ipixc.B[6][n4 >>> 16 & 0xFF][1] | ipixc.B[7][n4 >>> 24 & 0xFF][1];
                        array[n3] = (byte)(i & 0xFF);
                        array[n3 + 1] = (byte)(i >>> 8 & 0xFF);
                        array[n3 + 2] = (byte)(i >>> 16 & 0xFF);
                        array[n3 + 3] = (byte)(i >>> 24 & 0xFF);
                        array[n3 + 4] = (byte)(n9 & 0xFF);
                        array[n3 + 5] = (byte)(n9 >>> 8 & 0xFF);
                        array[n3 + 6] = (byte)(n9 >>> 16 & 0xFF);
                        array[n3 + 7] = (byte)(n9 >>> 24 & 0xFF);
                        if (!a) {
                            break Block_2;
                        }
                    }
                }
                n3 += 8;
            }
            if (n3 >= n + n2) {
                return;
            }
            continue;
        }
    }
    
    final Image b(final byte[] array) {
        return Toolkit.getDefaultToolkit().createImage(array);
    }
    
    final Image c(final byte[] array) {
        this.y = new ByteArrayInputStream(array);
        return Toolkit.getDefaultToolkit().createImage(new URLImageSource(this));
    }
    
    private final byte a(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    public final InputStream getInputStream() {
        return this.y;
    }
    
    final boolean c() {
        return this.p == 0;
    }
    
    private final void a(final InputStream inputStream, final byte[] array) throws IOException {
        int n = 0;
        while (true) {
            Label_0039: {
                if (!ipixa.a) {
                    break Label_0039;
                }
                final int a = this.a(inputStream, array, n, array.length - n);
                if (a < 0) {
                    throw new EOFException();
                }
                n += a;
            }
            if (n >= array.length) {
                return;
            }
            continue;
        }
    }
    
    private final int a(final InputStream inputStream, final byte[] array, final int n, final int n2) throws IOException {
        final int read = inputStream.read(array, n, n2);
        this.c += read;
        if (read != 0) {
            if (this.w + this.v + this.t != 0) {
                this.j.a(this.c / (this.w + this.v + this.t));
                if (!ipixa.a) {
                    return read;
                }
            }
            this.j.a(0.0f);
        }
        return read;
    }
    
    private final long a(final long n) {
        long n2 = 0L;
        int n3 = 0;
        long n5 = 0L;
    Label_0005:
        while (true) {
            final long n4 = n2 | (n >> ipixc.z[n3] & 0x1L) << n3;
            do {
                n2 = n5;
                if (++n3 < 56) {
                    continue Label_0005;
                }
                n5 = n2;
            } while (ipixa.a);
            break;
        }
        return n5;
    }
    
    final float[] d() {
        final float[] array = new float[3];
        System.arraycopy(this.r, 0, array, 0, array.length);
        return array;
    }
    
    private final boolean b(final InputStream inputStream) {
        try {
            if (!this.f(inputStream)) {
                return false;
            }
            this.a(inputStream, new byte[this.v]);
            return this.a(new Dimension(this.u & 0xFFFF, this.u >> 16 & 0xFFFF), this.t, inputStream);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static final int d(final byte[] array) {
        final boolean a = ipixa.a;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0073: {
                    if (!a) {
                        break Label_0073;
                    }
                    final byte b = array[n2];
                    final int n4;
                    int n3 = n4;
                    int n5 = 0;
                    do {
                        final boolean b2 = ((n & 0x1) ^ (n3 & 0x1)) != 0x0;
                        n >>>= 1;
                        if (b2) {
                            n ^= 0xA001;
                        }
                        n3 = (byte)(n3 >> 1 & 0x7F);
                    } while (++n5 < 9);
                    ++n2;
                }
                if (n2 < array.length) {
                    continue;
                }
                break;
            }
            final int n4 = n;
            if (!a) {
                return n4;
            }
            continue;
        }
    }
    
    final void e() {
        if (this.s != null) {
            this.s.start();
        }
    }
    
    public final String getContentType() {
        return "application/jpeg";
    }
    
    private final boolean a(final Image image, final Dimension h) throws InterruptedException {
        if (this.h != h) {
            this.h = h;
            this.g = new int[this.h.width * this.h.height];
            (this.i = new float[2])[0] = (this.i[1] = this.h.width / 2.0f - 0.5f);
        }
        return new PixelGrabber(image, 0, 0, this.h.width, this.h.height, this.g, 0, this.h.width).grabPixels();
    }
    
    private final float c(final InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(this.g(inputStream));
    }
    
    final boolean f() {
        return this.p == 2;
    }
    
    private final int d(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        if ((read | read2 | read3) < 0) {
            throw new EOFException();
        }
        return (read & 0xFF) | (read2 & 0xFF) << 8 | (read3 & 0xFF) << 16;
    }
    
    ipixc(final ipixa j, final URL url, final InputStream k) throws NullPointerException {
        super(url);
        this.c = 0;
        this.g = null;
        this.h = new Dimension(0, 0);
        this.i = new float[2];
        this.j = null;
        this.k = null;
        this.l = 1.0f;
        this.m = 180.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0;
        this.q = new float[3];
        this.r = new float[3];
        this.s = null;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.y = null;
        this.G = new int[16][2];
        if (j == null) {
            throw new NullPointerException();
        }
        this.j = j;
        this.k = k;
        if (this.k == null) {
            return;
        }
        this.s = new Thread(this);
    }
    
    private final long b(final long n) {
        long n2 = 0L;
        int n3 = 0;
        long n5 = 0L;
    Label_0005:
        while (true) {
            final long n4 = n2 | (n >> ipixc.A[n3] & 0x1L) << n3;
            do {
                n2 = n5;
                if (++n3 < 48) {
                    continue Label_0005;
                }
                n5 = n2;
            } while (ipixa.a);
            break;
        }
        return n5;
    }
    
    private final short e(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)(read + (read2 << 8));
    }
    
    private final boolean a(final Dimension dimension, final int n, final InputStream inputStream) {
        final boolean a = ipixa.a;
        try {
            final byte[] array = new byte[n];
            this.c(-4427970394437748728L);
            int n2 = 0;
            int n3 = 0;
            while (true) {
                while (true) {
                    Label_0350: {
                        if (!a) {
                            break Label_0350;
                        }
                        Math.min(Math.max(1024, inputStream.available()), array.length - n3);
                        final int n5;
                        final int n4 = n5;
                    Label_0049:
                        while (true) {
                            int i = n4;
                            int n6;
                            int n7;
                            int n8 = 0;
                            int n9;
                            int n10;
                            int n11;
                            byte[] array2;
                            int n12;
                            byte[] array3 = null;
                            boolean b;
                            Label_0064_Outer:Label_0088_Outer:Label_0219_Outer:Label_0274_Outer:
                            while (i != 0) {
                                n6 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0090: {
                                            if (!a) {
                                                break Label_0090;
                                            }
                                            n7 = n6 + this.a(inputStream, array, n3 + n6, array.length - n3 - n6);
                                            n6 = n8;
                                        }
                                        if (n6 < n4) {
                                            continue Label_0088_Outer;
                                        }
                                        break;
                                    }
                                    n9 = (n3 & 0xFFFFFFF8);
                                    n10 = (n3 + n6 & 0xFFFFFFF8);
                                    this.a(array, n9, n10 - n9);
                                    n3 += n6;
                                    n8 = n2;
                                    if (a) {
                                        continue Label_0219_Outer;
                                    }
                                    break;
                                }
                                Label_0195: {
                                    if (n8 == 0) {
                                        n11 = n9;
                                        while (true) {
                                            Label_0186: {
                                                if (!a) {
                                                    break Label_0186;
                                                }
                                                if (array[n11] == -1 && array[n11 + 1] == -38) {
                                                    n2 = n11;
                                                    if (!a) {
                                                        break Label_0195;
                                                    }
                                                }
                                                ++n11;
                                            }
                                            if (n11 < n10 - 1) {
                                                continue Label_0219_Outer;
                                            }
                                            break;
                                        }
                                    }
                                }
                                if (n2 == 0) {
                                    break Label_0350;
                                }
                                array2 = null;
                                n12 = n10 - 2;
                                if (a) {
                                    continue Label_0049;
                                }
                                while (true) {
                                Label_0301:
                                    while (true) {
                                        Label_0287: {
                                            if (!a) {
                                                break Label_0287;
                                            }
                                            if (array[n12] != -1 || (array[n12 + 1] != -38 && array[n12 + 1] != -39)) {
                                                --n12;
                                                break Label_0287;
                                            }
                                            array2 = new byte[n12 + 2];
                                            System.arraycopy(array, 0, array2, 0, array2.length);
                                            array3[n12 + 1] = -39;
                                            break Label_0301;
                                        }
                                        if (n12 > Math.max(n9, n2 + 2)) {
                                            continue Label_0274_Outer;
                                        }
                                        break;
                                    }
                                    array3 = array2;
                                    if (a) {
                                        continue;
                                    }
                                    break;
                                }
                                if (array3 == null) {
                                    break Label_0350;
                                }
                                b = ((i = (this.a(this.j.a(array2), dimension) ? 1 : 0)) != 0);
                                if (a) {
                                    continue Label_0064_Outer;
                                }
                                if (!b) {
                                    return false;
                                }
                                this.j.h = this;
                                this.j.b();
                                break Label_0350;
                            }
                            return false;
                        }
                    }
                    if (n3 < array.length) {
                        continue;
                    }
                    break;
                }
                final int n5 = 1;
                if (!a) {
                    return n5 != 0;
                }
                continue;
            }
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    final void a(final float[] array, final boolean b) {
        final int n = 0;
        array[n] += this.i[0];
        final int n2 = 1;
        array[n2] += this.i[1];
        if (this.c() && b) {
            final int n3 = 1;
            array[n3] += this.h.height / 2;
        }
    }
    
    static {
        final boolean a = ipixa.a;
        ipixc.z = new int[56];
        ipixc.A = new int[48];
        ipixc.B = new int[8][256][2];
        ipixc.C = new int[8][256][2];
        ipixc.D = new int[4][256][2];
        ipixc.E = new int[8][64];
        ipixc.F = new int[16];
        final int[] array = new int[64];
        int[] array2 = { 959523105, 420546817, 993209123, 454232835, 1026895141, 487918853, 1060581159, 521604871, 942680096, 403703808, 976366114, 437389826, 1010052132, 471075844, 1043738150, 504761862 };
        int n = 0;
        int[] array4;
        while (true) {
            while (true) {
                Label_0222: {
                    if (!a) {
                        break Label_0222;
                    }
                    final int[] array3 = array;
                    array3[n] = (array2[n / 4] >>> 8 * (3 - n % 4) & 0xFF);
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
            array2 = new int[48];
            int[] array3;
            array4 = (array3 = new int[8]);
            array4[0] = 1040222308;
            array4[1] = 105027816;
            array4[2] = 243575148;
            array4[3] = 382122480;
            array4[4] = 520669812;
            array4[5] = 659217144;
            array4[6] = 797764476;
            array4[7] = 936311776;
            if (a) {
                continue;
            }
            break;
        }
        int[] array5 = array4;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0324: {
                    if (!a) {
                        break Label_0324;
                    }
                    array2[n2] = (array5[n2 / 6] >>> 5 * (5 - n2 % 6) & 0x1F);
                    ++n2;
                }
                if (n2 < array2.length) {
                    continue;
                }
                break;
            }
            array5 = new int[] { 942680096, 403703808, 959523105, 420546817, 976366114, 437389826, 993209123, 1043738150, 504761862, 1026895141, 487918853, 1010052132, 471075844, 454232835 };
            n2 = 0;
            if (a) {
                if (a) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            if (n2 >= ipixc.z.length) {
                array5 = new int[] { 219154967, 262683, 235213833, 370281219, 419892998, 437455873, 674438692, 775298343, 841752623, 724575799, 557067561, 824384543 };
                n2 = 0;
                if (!a) {
                    break;
                }
            }
            else {
                ipixc.z[n2] = (array5[n2 / 4] >>> 8 * (3 - n2 % 4) & 0xFF);
            }
            ++n2;
        }
        int[] array6;
        while (true) {
            while (true) {
                Label_0597: {
                    if (!a) {
                        break Label_0597;
                    }
                    final int[] a2 = ipixc.A;
                    a2[n2] = (array5[n2 / 4] >>> 8 * (3 - n2 % 4) & 0xFF);
                    ++n2;
                }
                if (n2 < ipixc.A.length) {
                    continue;
                }
                break;
            }
            array5 = new int[32];
            int[] a2;
            array6 = (a2 = new int[8]);
            array6[0] = 252056340;
            array6[1] = 470489872;
            array6[2] = 923161;
            array6[3] = 68230665;
            array6[4] = 17241869;
            array6[5] = 521798152;
            array6[6] = 302783749;
            array6[7] = 352977688;
            if (a) {
                continue;
            }
            break;
        }
        int[] array7 = array6;
        int n3 = 0;
        int[] array9;
        while (true) {
            while (true) {
                Label_0705: {
                    if (!a) {
                        break Label_0705;
                    }
                    final int[] array8 = array5;
                    array8[n3] = (array7[n3 / 4] >>> 8 * (3 - n3 % 4) & 0xFF);
                    ++n3;
                }
                if (n3 < array5.length) {
                    continue;
                }
                break;
            }
            array7 = new int[64];
            int[] array8;
            array9 = (array8 = new int[16]);
            array9[0] = 654782223;
            array9[1] = 924270367;
            array9[2] = 637939214;
            array9[3] = 907427358;
            array9[4] = 621096205;
            array9[5] = 890584349;
            array9[6] = 604253196;
            array9[7] = 873741340;
            array9[8] = 587410187;
            array9[9] = 856898331;
            array9[10] = 570567178;
            array9[11] = 840055322;
            array9[12] = 553724169;
            array9[13] = 823212313;
            array9[14] = 536881160;
            array9[15] = 806369304;
            if (a) {
                continue;
            }
            break;
        }
        final int[] array10 = array9;
        int n4 = 0;
        while (true) {
            Label_0870: {
                if (!a) {
                    break Label_0870;
                }
                array7[n4] = (array10[n4 / 4] >>> 8 * (3 - n4 % 4) & 0xFF);
                ++n4;
            }
            if (n4 < array7.length) {
                continue;
            }
            break;
        }
        final int[][][] array11 = new int[8][16][4];
        final int[] array12 = { -274256819, 64875217, 1088047383, -49040286, 1091420594, -42418417, 510013003, -928021583, -660714968, 1946887357, -414638156, -1966030114, 518201316, 1200996984, -1953450543, 554073223, 643856886, -281655478, -628317495, 1131026708, -80305649, 575634579, 1694226492, -1617828534, -1275480437, -664741609, 758611070, 344797096, -2074449711, 514004388, -1324514334, 1920522877, 957448250, -1406904596, -171968608, 1531538335, -1479385655, 1619460405, -938372874, -1225458084, 1657287571, -1051525802, -1675377558, 937754889, -847907714, -1158893877, 1993235085, -331638800, 1555816021, -1765724128, 961896719, -1598249245, -1874065504, 1505401854, -1549648557, 89604133, 86304108, 1006535561, 1390939029, 1847744566, 2056231703, -2061932446, 259319336, -640795189 };
        int n5 = 0;
        int n6 = 0;
        int n8 = 0;
        while (true) {
            Label_1416: {
                if (!a) {
                    break Label_1416;
                }
                int n7 = n8;
                int i = 0;
                int n9 = 0;
                int n10;
                int n11;
                Label_1366_Outer:Label_1388_Outer:
                do {
                    n10 = array12[n5];
                    n11 = 7;
                    while (true) {
                        while (true) {
                            Label_1391: {
                                if (!a) {
                                    break Label_1391;
                                }
                                array11[n11][n6][n7] = (n10 & 0xF);
                                --n11;
                                n10 = i >>> n9;
                            }
                            if (n11 >= 0) {
                                continue Label_1388_Outer;
                            }
                            break;
                        }
                        ++n7;
                        ++n5;
                        i = n7;
                        n9 = 4;
                        if (!a) {
                            continue Label_1366_Outer;
                        }
                        continue;
                    }
                } while (i < n9);
                ++n6;
            }
            if (n6 < 16) {
                continue;
            }
            n8 = 1521117865;
            if (a) {
                continue;
            }
            break;
        }
        int n12 = n8;
        int n13 = ipixc.F.length - 1;
    Label_1478_Outer:
        while (true) {
            while (true) {
                Label_1465: {
                    if (!a) {
                        break Label_1465;
                    }
                    ipixc.F[n13] = (n12 & 0x3);
                    --n13;
                    final int n14;
                    n12 = n14;
                }
                if (n13 >= 0) {
                    continue;
                }
                break;
            }
            final int n14 = 0;
            if (!a) {
                int n15 = n14;
                int n19 = 0;
            Label_1478:
                while (true) {
                    while (true) {
                        int n16 = 0;
                    Label_1481:
                        while (true) {
                            ipixc.E[n15][n16] = array11[n15][n16 >> 1 & 0xF][(n16 >> 4 & 0x2) | (n16 & 0x1)] << (n15 << 2);
                            int n17 = 0;
                            do {
                                int n18 = n19;
                                int n21 = 0;
                                int n22 = 0;
                            Block_23:
                                while (true) {
                                    n17 |= (ipixc.E[n15][n16] >> array5[n18] & 0x1) << n18;
                                    ++n18;
                                    int j = 0;
                                    int n20 = 0;
                                    while (j >= n20) {
                                        ipixc.E[n15][n16] = n17;
                                        j = ++n16;
                                        n20 = 64;
                                        if (!a) {
                                            if (j < n20) {
                                                continue Label_1481;
                                            }
                                            n21 = ++n15;
                                            n22 = 8;
                                            if (!a) {
                                                break Block_23;
                                            }
                                            continue Label_1478_Outer;
                                        }
                                    }
                                }
                                if (n21 < n22) {
                                    continue Label_1478;
                                }
                                n19 = 0;
                            } while (a);
                            break Label_1478;
                        }
                    }
                    break;
                }
                int n23 = n19;
                int n27 = 0;
            Label_1621:
                while (true) {
                    while (true) {
                        int n24 = 0;
                    Label_1624:
                        while (true) {
                            ipixc.D[n23][n24][0] = 0;
                            ipixc.D[n23][n24][1] = 0;
                            final int n25 = n24 << (n23 << 3);
                            do {
                                int n26 = n27;
                                int n31 = 0;
                                int n32 = 0;
                            Block_29:
                                while (true) {
                                    final int n28 = n25 >> array2[n26] & 0x1;
                                    final int[] array13 = ipixc.D[n23][n24];
                                    final int n29 = n26 >> 5;
                                    array13[n29] |= n28 << (n26 & 0x1F);
                                    ++n26;
                                    int k = 0;
                                    int n30 = 0;
                                    while (k >= n30) {
                                        k = ++n24;
                                        n30 = 256;
                                        if (!a) {
                                            if (k < n30) {
                                                continue Label_1624;
                                            }
                                            n31 = ++n23;
                                            n32 = 4;
                                            if (!a) {
                                                break Block_29;
                                            }
                                            continue Label_1478_Outer;
                                        }
                                    }
                                }
                                if (n31 < n32) {
                                    continue Label_1621;
                                }
                                n27 = 0;
                            } while (a);
                            break Label_1621;
                        }
                    }
                    break;
                }
                int n33 = n27;
            Block_35:
                while (true) {
                    int l = 0;
                    int n34 = 0;
                    do {
                        int n35 = 0;
                    Label_1747:
                        while (true) {
                            while (true) {
                                ipixc.C[n33][n35][0] = 0;
                                ipixc.C[n33][n35][1] = 0;
                                ipixc.B[n33][n35][0] = 0;
                                ipixc.B[n33][n35][1] = 0;
                                final int[] array14 = new int[2];
                                array14[n33 >> 2] = n35 << ((n33 & 0x3) << 3);
                                int n36 = 0;
                                while (true) {
                                    final int n37 = array14[array[n36] >> 5] >> (array[n36] & 0x1F) & 0x1;
                                    final int[] array15 = ipixc.C[n33][n35];
                                    final int n38 = n36 >> 5;
                                    array15[n38] |= n37 << (n36 & 0x1F);
                                    final int n39 = array14[array7[n36] >> 5] >> (array7[n36] & 0x1F) & 0x1;
                                    final int[] array16 = ipixc.B[n33][n35];
                                    final int n40 = n36 >> 5;
                                    array16[n40] |= n39 << (n36 & 0x1F);
                                    ++n36;
                                    int n41 = 0;
                                    int n42 = 0;
                                    while (n41 >= n42) {
                                        n41 = ++n35;
                                        n42 = 256;
                                        if (!a) {
                                            if (n41 < n42) {
                                                continue Label_1747;
                                            }
                                            l = ++n33;
                                            n34 = 8;
                                            if (!a) {
                                                continue Block_35;
                                            }
                                            continue Label_1478_Outer;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    } while (l < n34);
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    private final long a(final int n, long n2) {
        final boolean a = ipixa.a;
        int n3 = 0;
        while (true) {
            Label_0037: {
                if (!a) {
                    break Label_0037;
                }
                final long n4;
                n2 = ((n2 & 0x7FFFFFF7FFFFFFL) << 1 | n4 >> 27);
                ++n3;
            }
            if (n3 < n) {
                continue;
            }
            final long n4 = n2;
            if (!a) {
                return n4;
            }
            continue;
        }
    }
    
    private final boolean f(final InputStream inputStream) {
        final boolean a = ipixa.a;
        try {
            if (this.e(inputStream) != 25203) {
                return false;
            }
            this.w = this.e(inputStream);
            final int g = this.g(inputStream);
            final byte[] array = new byte[this.w - 8];
            this.a(inputStream, array);
            if (d(array) != g) {
                return false;
            }
            try {
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
                if (this.a(byteArrayInputStream) != 6) {
                    return false;
                }
                byteArrayInputStream.skip(1L);
                this.m = this.e(byteArrayInputStream);
                byteArrayInputStream.skip(2L);
                final byte a2 = this.a(byteArrayInputStream);
                if (a2 != -112 && (a2 > -117 || a2 < -125)) {
                    return false;
                }
                byteArrayInputStream.skip(1L);
                this.l = this.g(byteArrayInputStream);
                this.v = this.g(byteArrayInputStream) * 4;
                this.v += this.g(byteArrayInputStream);
                this.v += this.g(byteArrayInputStream);
                this.t = this.g(byteArrayInputStream);
                if (this.t == 0) {
                    return false;
                }
                byteArrayInputStream.skip(4L);
                final boolean b = this.a(byteArrayInputStream) != 0;
                final boolean b2 = this.a(byteArrayInputStream) != 0;
                Label_0315: {
                    if (b) {
                        this.p = 0;
                        if (!a) {
                            break Label_0315;
                        }
                    }
                    if (b2) {
                        this.p = 2;
                        if (!a) {
                            break Label_0315;
                        }
                    }
                    this.p = 1;
                }
                byteArrayInputStream.skip(334L);
                this.v += this.g(byteArrayInputStream);
                this.v += this.g(byteArrayInputStream);
                this.v += this.g(byteArrayInputStream);
                this.r[0] = this.c(byteArrayInputStream) * 0.017453292f;
                this.r[1] = this.c(byteArrayInputStream) * -0.017453292f;
                this.r[2] = this.c(byteArrayInputStream) * -0.017453292f;
                this.q[0] = this.c(byteArrayInputStream) * 0.017453292f;
                this.q[1] = this.c(byteArrayInputStream) * -0.017453292f;
                byteArrayInputStream.skip(4L);
                this.q[2] = this.c(byteArrayInputStream);
                this.v += this.g(byteArrayInputStream);
                this.v += this.g(byteArrayInputStream);
                byteArrayInputStream.skip(1L);
                final long n = this.d(byteArrayInputStream);
                if (n != 0L && new Date(new Date(97, 0, 1).getTime() + n * 24L * 60L * 60L * 1000L).before(new Date())) {
                    return false;
                }
                this.o = this.c(byteArrayInputStream);
                this.n = this.c(byteArrayInputStream);
                byteArrayInputStream.skip(8L);
                this.u = this.g(byteArrayInputStream);
                if (this.u == 0) {
                    return false;
                }
                byteArrayInputStream.skip(8L);
                final long n2 = this.d(byteArrayInputStream);
                if (n2 != 0L && new Date(new Date(97, 0, 1).getTime() + n2 * 24L * 60L * 60L * 1000L).after(new Date())) {
                    return false;
                }
            }
            catch (EOFException ex) {}
            return true;
        }
        catch (IOException ex2) {
            return false;
        }
    }
    
    public final void run() {
        try {
            if (!this.b(this.k)) {
                this.j.g();
                super.url = null;
            }
        }
        catch (NullPointerException ex) {}
        finally {
            this.s = null;
            this.j = null;
            this.k = null;
        }
    }
    
    final float[] g() {
        final float[] array = new float[3];
        System.arraycopy(this.q, 0, array, 0, array.length);
        return array;
    }
    
    private final int g(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        final int read4 = inputStream.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return read + (read2 << 8) + (read3 << 16) + (read4 << 24);
    }
    
    private final void c(final long n) {
        long n2 = this.a(n);
        int n3 = 0;
        do {
            n2 = this.a(ipixc.F[n3], n2);
            final long b = this.b(n2);
            this.G[n3][0] = (int)(b & -1L);
            this.G[n3][1] = (int)(b >>> 32);
        } while (++n3 < 16);
    }
    
    public final void connect() {
    }
}
