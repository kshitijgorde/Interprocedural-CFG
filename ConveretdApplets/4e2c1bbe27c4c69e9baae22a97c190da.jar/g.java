import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Frame implements Runnable, ImageObserver, b
{
    public c a;
    public int b;
    public boolean c;
    public Thread d;
    public Image e;
    public volatile Image f;
    public Graphics g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public volatile int m;
    public volatile int n;
    public volatile int[] o;
    public volatile int[] p;
    public volatile Image[] q;
    public volatile boolean[] r;
    public boolean s;
    public h t;
    public final int u = 0;
    public final int v = 16384;
    public final int w = 32768;
    public final int x = 49152;
    public final int y = 6;
    public int[][] z;
    public int[][] aa;
    public boolean[][] ab;
    public boolean[][] ac;
    public int ad;
    public int ae;
    public final int af = 128;
    
    public g(final c a, final String s, final int h, final int i) {
        super(s);
        this.b = 0;
        this.c = true;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.s = true;
        this.t = null;
        this.a = a;
        this.h = h;
        this.i = i;
        this.t = new h(this, zkmToString(".Ju'C"));
    }
    
    private Image a(final byte[] array, final int n, final int n2, final MediaTracker mediaTracker) {
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        Image image = this.getToolkit().createImage(array, n, n2);
        if (image == null) {
            System.out.println(zkmToString("=Qc-^XGt!C\u001cJ\u007f%\f\u001eQp/IX\u0000") + this.a.a2.al);
        }
        try {
            mediaTracker2.addImage(image, 0);
            mediaTracker2.waitForAll();
        }
        catch (InterruptedException ex) {
            image = null;
        }
        return image;
    }
    
    public void run() {
        System.gc();
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            this.s = true;
            this.l = this.a.ab;
            this.r = new boolean[this.l];
            this.q = new Image[this.l];
            this.o = new int[this.l];
            this.p = new int[this.l];
            final boolean b = false;
            this.n = (b ? 1 : 0);
            this.m = (b ? 1 : 0);
            this.j = this.a.z;
            this.k = this.a.aa;
            while (true) {
                if (!this.r[this.m] && this.a.a2 != null && this.a.a2.as) {
                    final int al = this.a.a2.al;
                    if (this.a.a2.aj[al].d == 1) {
                        final int m = this.m;
                        int n = 0;
                        int n2 = 0;
                        int n3 = 0;
                        int n4 = 0;
                        int n5 = 0;
                        int n6 = 0;
                        int n7 = 0;
                        final byte[] a = this.a.a2.aj[al].a;
                        final int b2 = this.a.a2.aj[al].b;
                        this.o[m] = this.a.a2.aj[al].c;
                        final int n8 = this.a.a2.aj[al].e & 0xC000;
                        if (this.a.bc) {
                            this.a.a2.af = 0;
                        }
                        if (this.a.a2.af <= this.b && (this.c || n8 == 0)) {
                            this.b -= this.a.a2.af;
                            if (this.m <= 1 || this.m == this.n) {}
                            if (n8 != 0) {
                                n4 = (this.ad * this.ae + 7) / 8;
                                if (n8 == 32768) {
                                    n = n4;
                                }
                                else if (n8 == 16384) {
                                    n = b2;
                                }
                                else if ((a[n7 + 1] & 0x80) != 0x0) {
                                    n6 = (a[n7 + 1] & 0x7F);
                                    final int n9 = (a[n7 + 2] & 0xFF) | (a[n7 + 3] & 0xFF) << 8;
                                    n2 = (a[n7] & 0xFF);
                                    n = 4 + n9;
                                    n7 += 4;
                                }
                                else {
                                    n3 = ((a[n7] & 0xFF) | (a[n7 + 1] & 0xFF) << 8);
                                    n = 4 + n4 + (n3 + 7) / 8 + ((a[n7 + 2] & 0xFF) | (a[n7 + 3] & 0xFF) << 8);
                                    n7 += 4;
                                }
                            }
                            else {
                                this.c = true;
                            }
                            if (b2 > n) {
                                if (n8 == 0 || !this.a.a2.x) {
                                    this.e = this.a(this.a.a2.aj[al].a, n, b2 - n, mediaTracker);
                                }
                                else {
                                    int n10 = n;
                                    int i = 0;
                                    final int n11 = (this.a.a2.aj[al].a[n10 + 1] << 8 & 0xFF00) | (this.a.a2.aj[al].a[n10] & 0xFF);
                                    n10 += 2;
                                    final int n12 = (n11 > 4000) ? 4000 : n11;
                                    this.e = this.a.al.createImage(n12, (n11 + n12 - 1) / n12 * 8);
                                    int n13 = 0;
                                    final Graphics graphics = this.e.getGraphics();
                                    while (i < n11) {
                                        final int n14 = (this.a.a2.aj[al].a[n10 + 1] & 0xFF) << 8 | (this.a.a2.aj[al].a[n10] & 0xFF);
                                        n10 += 2;
                                        if (n14 > 0) {
                                            final Image a2 = this.a(this.a.a2.aj[al].a, n10, n14, mediaTracker);
                                            graphics.drawImage(a2, 0, n13, null);
                                            i += a2.getWidth(null);
                                            n13 += 8;
                                            n10 += n14;
                                            a2.flush();
                                        }
                                    }
                                    graphics.dispose();
                                }
                            }
                            if (this.s) {
                                if (this.h == 0) {
                                    this.h = this.e.getWidth(this);
                                }
                                if (this.i == 0) {
                                    this.i = this.e.getHeight(this);
                                }
                                if (!this.a.ak) {
                                    if (!this.a.ad) {
                                        final c a3 = this.a;
                                        a3.ae += (this.a.x - this.h) / 2;
                                        final c a4 = this.a;
                                        a4.af += (this.a.y - this.i) / 2;
                                        this.a.ad = true;
                                    }
                                    this.j = this.h;
                                    this.k = this.i;
                                }
                                else {
                                    this.j = this.a.x;
                                    this.k = this.a.y;
                                }
                                if (this.j != this.a.z || this.k != this.a.aa) {
                                    this.a.aa = this.k;
                                    this.a.z = this.j;
                                    this.a.al.action(new Event(this.a.al, 101, null), null);
                                }
                                this.ad = this.h / 8;
                                this.ae = this.i / 8;
                                this.ab = new boolean[this.ad][this.ae];
                                this.ac = new boolean[this.ad][this.ae];
                                this.z = new int[this.ad][this.ae];
                                this.aa = new int[this.ad][this.ae];
                                this.s = false;
                                this.f = this.a.al.createImage(this.h, this.i);
                                this.g = this.f.getGraphics();
                            }
                            if (this.q[m] == null) {
                                this.q[m] = this.a.al.createImage(this.h, this.i);
                            }
                            final Graphics graphics2 = this.q[m].getGraphics();
                            if (n8 == 0) {
                                graphics2.drawImage(this.e, 0, 0, this.h, this.i, this);
                            }
                            else {
                                graphics2.drawImage(this.f, 0, 0, this);
                                if (n2 > 0) {
                                    final int n15 = 1 << n6 - 1;
                                    final int[] array = new int[(n2 + 1) * n6];
                                    final int[] array2 = new int[(n2 + 1) * n6];
                                    int n16 = -1;
                                    int n17 = -1;
                                    int n18 = -1;
                                    int n19 = 0;
                                    int n20 = 0;
                                    int n21 = 0;
                                    int n22 = 0;
                                    int n23 = n7;
                                    for (int j = 0; j < n2; ++j, ++n20) {
                                        if (n22 % 8 == 0) {
                                            n21 = a[n23++];
                                        }
                                        if ((n21 & 0x1) == 0x1) {
                                            int n24 = n21 >> 1;
                                            if (++n22 % 8 == 0) {
                                                n24 = a[n23++];
                                            }
                                            if ((n24 & 0x1) == 0x1) {
                                                n17 = j;
                                            }
                                            else {
                                                n18 = j;
                                            }
                                            n21 = n24 >> 1;
                                            ++n22;
                                            for (int k = 2 * n6 - 1; k > 0; --k) {
                                                if (n22 % 8 == 0) {
                                                    n21 = a[n23++];
                                                }
                                                n19 <<= 1;
                                                if ((n21 & 0x1) == 0x1) {
                                                    n19 |= 0x1;
                                                }
                                                n21 >>= 1;
                                                ++n22;
                                            }
                                            if (n19 > j) {
                                                ++n20;
                                                if (n17 == j) {
                                                    n18 = n19;
                                                }
                                                else {
                                                    n17 = n19;
                                                }
                                            }
                                        }
                                        else {
                                            ++n22;
                                            n21 >>= 1;
                                            int n25 = 0;
                                            for (int l = n6; l > 0; --l) {
                                                if (n22 % 8 == 0) {
                                                    n21 = a[n23++];
                                                }
                                                n25 <<= 1;
                                                if ((n21 & 0x1) == 0x1) {
                                                    n25 |= 0x1;
                                                }
                                                n21 >>= 1;
                                                ++n22;
                                            }
                                            int n26 = 0;
                                            for (int n27 = n6; n27 > 0; --n27) {
                                                if (n22 % 8 == 0) {
                                                    n21 = a[n23++];
                                                }
                                                n26 <<= 1;
                                                if ((n21 & 0x1) == 0x1) {
                                                    n26 |= 0x1;
                                                }
                                                n21 >>= 1;
                                                ++n22;
                                            }
                                            int n28 = j;
                                            if (n19 > 0 && j >= n19) {
                                                ++n28;
                                            }
                                            array[n28] = n25 - n15;
                                            array2[n28] = n26 - n15;
                                            if (array[n28] == 0 && array2[n28] == 0) {
                                                n16 = n28;
                                            }
                                        }
                                    }
                                    for (int n29 = 0; n29 < this.ae; ++n29) {
                                        for (int n30 = 0; n30 < this.ad; ++n30) {
                                            this.ab[n30][n29] = (this.ac[n30][n29] = false);
                                            int n31;
                                            for (n31 = 0; n31 < n20 - 1; ++n31) {
                                                if (n22 % 8 == 0) {
                                                    n21 = a[n23++];
                                                }
                                                final int n32 = n21 & 0x1;
                                                n21 >>= 1;
                                                ++n22;
                                                if (n32 == 0) {
                                                    break;
                                                }
                                            }
                                            if (n31 == n17) {
                                                ++n3;
                                                this.ab[n30][n29] = true;
                                            }
                                            else if (n31 == n18) {
                                                int n33 = 0;
                                                for (int n34 = n6; n34 > 0; --n34) {
                                                    if (n22 % 8 == 0) {
                                                        n21 = a[n23++];
                                                    }
                                                    n33 <<= 1;
                                                    if ((n21 & 0x1) == 0x1) {
                                                        n33 |= 0x1;
                                                    }
                                                    n21 >>= 1;
                                                    ++n22;
                                                }
                                                int n35 = 0;
                                                for (int n36 = n6; n36 > 0; --n36) {
                                                    if (n22 % 8 == 0) {
                                                        n21 = a[n23++];
                                                    }
                                                    n35 <<= 1;
                                                    if ((n21 & 0x1) == 0x1) {
                                                        n35 |= 0x1;
                                                    }
                                                    n21 >>= 1;
                                                    ++n22;
                                                }
                                                this.ac[n30][n29] = true;
                                                this.z[n30][n29] = n33 - n15;
                                                this.aa[n30][n29] = n35 - n15;
                                                ++n5;
                                            }
                                            else if (n31 != n16) {
                                                this.ac[n30][n29] = true;
                                                this.z[n30][n29] = array[n31];
                                                this.aa[n30][n29] = array2[n31];
                                                ++n5;
                                            }
                                        }
                                    }
                                }
                                else {
                                    int n37 = n7;
                                    int n39;
                                    int n38 = n39 = n7 + n4;
                                    if (n8 == 49152) {
                                        n39 += (n3 + 7) / 8;
                                    }
                                    int n41;
                                    int n40 = n41 = 0;
                                    int n42 = 0;
                                    int n43 = 0;
                                    int n44 = 0;
                                    int n45 = 0;
                                    int n46 = 0;
                                    int n47 = 0;
                                    while (n46 < this.ae) {
                                        for (int n48 = 0; n48 < this.ad; ++n48) {
                                            this.ab[n48][n46] = (this.ac[n48][n46] = false);
                                            if (n47 % 8 == 0) {
                                                n42 = a[n37++];
                                            }
                                            if ((n42 & 0x1) == 0x1) {
                                                switch (n8) {
                                                    case 32768: {
                                                        this.ab[n48][n46] = true;
                                                        break;
                                                    }
                                                    case 16384: {
                                                        this.ac[n48][n46] = true;
                                                        break;
                                                    }
                                                    case 49152: {
                                                        if (n41 % 8 == 0) {
                                                            n43 = a[n38++];
                                                        }
                                                        if ((n43 & 0x1) == 0x1) {
                                                            this.ac[n48][n46] = true;
                                                        }
                                                        else {
                                                            this.ab[n48][n46] = true;
                                                        }
                                                        n43 = (byte)(n43 >> 1);
                                                        break;
                                                    }
                                                }
                                                if (this.ac[n48][n46]) {
                                                    int n49 = 0;
                                                    for (int n50 = 6; n50 > 0; n50 = (byte)(n50 - 1)) {
                                                        if (n45 == 0) {
                                                            n44 = a[n39++];
                                                            n45 = 8;
                                                        }
                                                        n49 <<= 1;
                                                        if ((n44 & 0x1) == 0x1) {
                                                            n49 |= 0x1;
                                                        }
                                                        n44 = (byte)(n44 >> 1);
                                                        n45 = (byte)(n45 - 1);
                                                    }
                                                    int n51 = 0;
                                                    for (int n52 = 6; n52 > 0; n52 = (byte)(n52 - 1)) {
                                                        if (n45 == 0) {
                                                            n44 = a[n39++];
                                                            n45 = 8;
                                                        }
                                                        n51 <<= 1;
                                                        if ((n44 & 0x1) == 0x1) {
                                                            n51 |= 0x1;
                                                        }
                                                        n44 = (byte)(n44 >> 1);
                                                        n45 = (byte)(n45 - 1);
                                                    }
                                                    this.z[n48][n46] = n49 - 32;
                                                    this.aa[n48][n46] = n51 - 32;
                                                    ++n40;
                                                }
                                                ++n41;
                                            }
                                            n42 = (byte)(n42 >> 1);
                                            ++n47;
                                        }
                                        ++n46;
                                    }
                                }
                                int n54;
                                int n53 = n54 = 0;
                                int n56;
                                int n55 = n56 = 0;
                                for (int n57 = 0; n57 < this.ae; ++n57) {
                                    for (int n58 = 0; n58 < this.ad; ++n58) {
                                        if (this.ac[n58][n57]) {
                                            final int n59 = n58 * 8;
                                            final int n60 = n57 * 8;
                                            final int n61 = n59 + this.z[n58][n57];
                                            final int n62 = n60 + this.aa[n58][n57];
                                            final int n63 = (n58 > 0 && this.ab[n58 - 1][n57]) ? 1 : 0;
                                            final int n64 = (n58 < this.ad - 1 && this.ab[n58 + 1][n57]) ? 1 : 0;
                                            graphics2.drawImage(this.f, n59 - n63, n60, n59 + 8 + n64, n60 + 8, n61 - n63, n62, n61 + 8 + n64, n62 + 8, null);
                                        }
                                        if (this.ab[n58][n57]) {
                                            if (n56-- == 0) {
                                                n53 = n58;
                                                n54 = n57;
                                            }
                                            if (n58 == this.ad - 1 || (n55 - n56) % 500 == 0) {
                                                n56 *= -1;
                                            }
                                        }
                                        else if (n56 < 0) {
                                            n56 *= -1;
                                        }
                                        if (n56 > 0) {
                                            final int n65 = n53 * 8;
                                            final int n66 = n54 * 8;
                                            int n67;
                                            int n68;
                                            if (!this.a.a2.x) {
                                                n67 = n55 * 8;
                                                n68 = 0;
                                            }
                                            else {
                                                n67 = n55 % 500 * 8;
                                                n68 = n55 / 500 * 8;
                                            }
                                            final int n69 = (n53 > 0 && this.ac[n53 - 1][n54]) ? 1 : 0;
                                            final int n70 = (n53 + n56 < this.ad - 1 && this.ac[n53 + n56][n54]) ? 1 : 0;
                                            graphics2.drawImage(this.e, n65 + n69, n66, n65 + 8 * n56 - n70, n66 + 8, n67 + n69, n68, n67 + 8 * n56 - n70, n68 + 8, null);
                                            n55 += n56;
                                            n56 = 0;
                                        }
                                    }
                                }
                            }
                            this.g.drawImage(this.q[m], 0, 0, this);
                            graphics2.dispose();
                            this.e.flush();
                            ++this.m;
                            this.m %= this.l;
                            this.r[m] = true;
                        }
                        else {
                            this.b += 1000;
                            this.c = false;
                        }
                        final f a5 = this.a.a2;
                        ++a5.an;
                        final f a6 = this.a.a2;
                        ++a6.al;
                        final f a7 = this.a.a2;
                        a7.al %= this.a.a2.ai;
                        this.a.a2.aj[al].d = 0;
                    }
                }
                else if (this.a.a2 != null && this.a.a2.af > 0) {
                    final f a8 = this.a.a2;
                    a8.af -= 10;
                }
                Thread.currentThread();
                Thread.sleep(10L);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a() {
        if (this.d != null && !this.d.isAlive()) {
            this.d = null;
        }
        if (this.d == null) {
            (this.d = new Thread(this, zkmToString(".`B\u0006I\u001bLu'^"))).start();
        }
        this.t.a();
    }
    
    public void b() {
        this.t.b();
        if (this.d != null) {
            this.d.stop();
            this.d = null;
        }
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'x';
                    break;
                }
                case 1: {
                    c2 = '#';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = 'B';
                    break;
                }
                default: {
                    c2 = ',';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
