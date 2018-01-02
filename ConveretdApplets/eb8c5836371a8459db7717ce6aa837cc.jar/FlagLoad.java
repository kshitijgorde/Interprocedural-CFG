import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Label;
import java.awt.image.PixelGrabber;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Font;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FlagLoad extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    final String d = "[x\u0012P\u000bn(\u0000EN\\i\u0000U\u0001:K\u000bI\ryaB\u0014\u0019m\u007fL]\u0000|";
    int e;
    int f;
    int g;
    int[][] h;
    int i;
    int[] j;
    int k;
    long l;
    int m;
    int[] n;
    Font o;
    int p;
    int q;
    float r;
    float s;
    private Image t;
    private Image u;
    int v;
    boolean w;
    String[] x;
    URL y;
    int z;
    int A;
    String B;
    String C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    int O;
    boolean P;
    int Q;
    int R;
    private Graphics S;
    private Image T;
    boolean U;
    private Image V;
    int W;
    int X;
    int Y;
    int Z;
    anfy ba;
    MemoryImageSource bb;
    int bc;
    int bd;
    boolean be;
    int bf;
    int bg;
    String bh;
    int bi;
    int bj;
    int bk;
    Color bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    int[] bt;
    int[] bu;
    int bv;
    int bw;
    int[] bx;
    int by;
    int bz;
    String bA;
    Color bB;
    int bC;
    int bD;
    boolean bE;
    boolean bF;
    Toolkit bG;
    Thread bH;
    int bI;
    int bJ;
    int bK;
    int bL;
    int bM;
    int[] bN;
    int bO;
    Lware bP;
    int bQ;
    int bR;
    int bS;
    int bT;
    int bU;
    int bV;
    int bW;
    int bX;
    int bY;
    int bZ;
    int ca;
    int cb;
    Font[] cc;
    
    private final void a() {
        while (true) {
            this.showStatus(c("^g\f\u001b\u001a:z\u0007Q\u0001lmBK\u0019m&\u0003R\bcb\u0003J\u000f4k\rQNyz\u0007X\u0007n{BP\u0007tmBU\u0000:@6q\";"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.V, this);
        if (this.w) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.U;
        }
        return false;
    }
    
    public void destroy() {
        if (this.V != null) {
            this.V.flush();
        }
        this.V = null;
        if (this.T != null) {
            this.T.flush();
        }
        this.T = null;
        if (this.S != null) {
            this.S.dispose();
        }
        this.S = null;
        System.gc();
    }
    
    public final void c() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        for (int i = 0; i < this.bR; ++i) {
            final int n2 = this.bm + this.k * i;
            final int n3 = this.bv * i;
            final int n4 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n5 = this.k * j;
                this.bT = n3 + bx[n2 + n5 & 0xFF];
                this.bZ = this.bv * j + bx[n4 + n5 + j & 0xFF];
                n[this.bZ * bo + this.bT] = array[j];
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void d() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        for (int i = 0; i < this.bR; ++i) {
            final int n2 = this.bm + this.k * i;
            final int n3 = this.bv * i;
            final int n4 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n5 = this.k * j;
                this.bT = n3 + bx[n2 + n5 & 0xFF];
                this.bZ = this.bv * j + bx[n4 + n5 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int r = this.R;
                final int[] array3 = n;
                final int n6 = this.R - bo;
                final int[] array4 = n;
                final int n7 = this.R - 1;
                final int[] array5 = n;
                final int n8 = this.R - bo - 1;
                final int n9 = array[j];
                array4[n7] = (array5[n8] = n9);
                array2[r] = (array3[n6] = n9);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void e() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        final int n2 = bo - 1;
        final int n3 = bo + 1;
        for (int i = 0; i < this.bR; ++i) {
            final int n4 = this.bm + this.k * i;
            final int n5 = this.bv * i;
            final int n6 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n7 = this.k * j;
                this.bT = n5 + bx[n4 + n7 & 0xFF];
                this.bZ = this.bv * j + bx[n6 + n7 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int r = this.R;
                final int[] array3 = n;
                final int n8 = this.R - n2;
                final int[] array4 = n;
                final int n9 = this.R - bo;
                final int[] array5 = n;
                final int n10 = this.R - n3;
                final int[] array6 = n;
                final int n11 = this.R - 1;
                final int[] array7 = n;
                final int n12 = this.R + 1;
                final int[] array8 = n;
                final int n13 = this.R + n2;
                final int[] array9 = n;
                final int n14 = this.R + bo;
                final int[] array10 = n;
                final int n15 = this.R + n3;
                final int n16 = array[j];
                array10[n15] = n16;
                array8[n13] = (array9[n14] = n16);
                array6[n11] = (array7[n12] = n16);
                array4[n9] = (array5[n10] = n16);
                array2[r] = (array3[n8] = n16);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void f() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        final int n2 = bo - 1;
        final int n3 = bo + 1;
        for (int i = 0; i < this.bR; ++i) {
            final int n4 = this.bm + this.k * i;
            final int n5 = this.bv * i;
            final int n6 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n7 = this.k * j;
                this.bT = n5 + bx[n4 + n7 & 0xFF];
                this.bZ = this.bv * j + bx[n6 + n7 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int n8 = this.R - n2;
                final int[] array3 = n;
                final int n9 = this.R - bo;
                final int[] array4 = n;
                final int n10 = this.R - n3;
                final int[] array5 = n;
                final int n11 = this.R - 1;
                final int[] array6 = n;
                final int n12 = this.R + 1;
                final int[] array7 = n;
                final int n13 = this.R + n2;
                final int[] array8 = n;
                final int n14 = this.R + this.bO;
                final int[] array9 = n;
                final int n15 = this.R + n3;
                final int n16 = array[j];
                array8[n14] = (array9[n15] = n16);
                array6[n12] = (array7[n13] = n16);
                array4[n10] = (array5[n11] = n16);
                array2[n8] = (array3[n9] = n16);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void g() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        final int n2 = bo - 1;
        final int n3 = bo + 1;
        for (int i = 0; i < this.bR; ++i) {
            final int n4 = this.bm + this.k * i;
            final int n5 = this.bv * i;
            final int n6 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n7 = this.k * j;
                this.bT = n5 + bx[n4 + n7 & 0xFF];
                this.bZ = this.bv * j + bx[n6 + n7 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int r = this.R;
                final int[] array3 = n;
                final int n8 = this.R - n2;
                final int[] array4 = n;
                final int n9 = this.R - this.bO + 1;
                final int[] array5 = n;
                final int n10 = this.R + n2;
                final int[] array6 = n;
                final int n11 = this.R + n3;
                final int n12 = array[j];
                array6[n11] = n12;
                array4[n9] = (array5[n10] = n12);
                array2[r] = (array3[n8] = n12);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void h() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        final int n2 = bo - 1;
        final int n3 = bo + 1;
        for (int i = 0; i < this.bR; ++i) {
            final int n4 = this.bm + this.k * i;
            final int n5 = this.bv * i;
            final int n6 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n7 = this.k * j;
                this.bT = n5 + bx[n4 + n7 & 0xFF];
                this.bZ = this.bv * j + bx[n6 + n7 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int n8 = this.R - n2;
                final int[] array3 = n;
                final int n9 = this.R - n3;
                final int[] array4 = n;
                final int n10 = this.R + n2;
                final int[] array5 = n;
                final int n11 = this.R + n3;
                final int n12 = array[j];
                array4[n10] = (array5[n11] = n12);
                array2[n8] = (array3[n9] = n12);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void i() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        for (int i = 0; i < this.bR; ++i) {
            final int n2 = this.bm + this.k * i;
            final int n3 = this.bv * i;
            final int n4 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n5 = this.k * j;
                this.bT = n3 + bx[n2 + n5 & 0xFF];
                this.bZ = this.bv * j + bx[n4 + n5 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int r = this.R;
                final int[] array3 = n;
                final int n6 = this.R - bo;
                final int[] array4 = n;
                final int n7 = this.R - 1;
                final int[] array5 = n;
                final int n8 = this.R + 1;
                final int[] array6 = n;
                final int n9 = this.R + bo;
                final int n10 = array[j];
                array6[n9] = n10;
                array4[n7] = (array5[n8] = n10);
                array2[r] = (array3[n6] = n10);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void j() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        for (int i = 0; i < this.bR; ++i) {
            final int n2 = this.bm + this.k * i;
            final int n3 = this.bv * i;
            final int n4 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n5 = this.k * j;
                this.bT = n3 + bx[n2 + n5 & 0xFF];
                this.bZ = this.bv * j + bx[n4 + n5 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int n6 = this.R - bo;
                final int[] array3 = n;
                final int n7 = this.R - 1;
                final int[] array4 = n;
                final int n8 = this.R + 1;
                final int[] array5 = n;
                final int n9 = this.R + bo;
                final int n10 = array[j];
                array4[n8] = (array5[n9] = n10);
                array2[n6] = (array3[n7] = n10);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    public final void k() {
        final int[] bx = this.bx;
        final int[] n = this.n;
        final int bo = this.bO;
        for (int i = 0; i < this.bR; ++i) {
            final int n2 = this.bm + this.k * i;
            final int n3 = this.bv * i;
            final int n4 = this.bm + (i << 2);
            final int[] array = this.h[i];
            for (int j = this.bX; j > 0; --j) {
                final int n5 = this.k * j;
                this.bT = n3 + bx[n2 + n5 & 0xFF];
                this.bZ = this.bv * j + bx[n4 + n5 + j & 0xFF];
                this.R = this.bZ * bo + this.bT;
                final int[] array2 = n;
                final int r = this.R;
                final int[] array3 = n;
                final int n6 = this.R - 1;
                final int[] array4 = n;
                final int n7 = this.R + 1;
                final int[] array5 = n;
                final int n8 = this.R + bo;
                final int n9 = array[j];
                array4[n7] = (array5[n8] = n9);
                array2[r] = (array3[n6] = n9);
            }
        }
        this.bm += this.bw;
        this.bm &= 0xFF;
    }
    
    Image a(final String s) {
        try {
            return this.b(s);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.b(s);
        }
    }
    
    synchronized Image b(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Label_0172: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0172;
                    }
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    final byte[] array = new byte[512];
                    boolean b = false;
                    byte[] byteArray;
                    try {
                        while (!b) {
                            final int read = resourceAsStream.read(array, 0, 512);
                            if (read != -1) {
                                byteArrayOutputStream.write(array, 0, read);
                                byteArrayOutputStream.flush();
                            }
                            else {
                                b = true;
                            }
                        }
                        byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        resourceAsStream.close();
                    }
                    catch (IOException ex2) {
                        byteArray = null;
                    }
                    System.gc();
                    if (byteArray != null) {
                        image = this.getToolkit().createImage(byteArray);
                        this.prepareImage(image, this);
                    }
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            catch (SecurityException ex3) {}
        }
        if (image == null) {
            int i = 0;
            while (i < 5) {
                try {
                    if (i % 2 == 0) {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    }
                    else {
                        image = this.getImage(url);
                    }
                    ++i;
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    this.notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try {
                        mediaTracker.addImage(image, 0);
                        mediaTracker.waitForID(0);
                    }
                    catch (InterruptedException ex4) {
                        image = null;
                    }
                    if (mediaTracker.isErrorID(0)) {
                        image = null;
                    }
                    else {
                        i = 6;
                    }
                }
                catch (NullPointerException ex5) {
                    System.gc();
                }
            }
        }
        if (image == null) {
            for (int j = 0; j < 25; ++j) {
                this.showStatus(c("Se\u0003[\u000b:") + s + c(":f\rHN|g\u0017R\n;"));
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex6) {}
            }
        }
        else {
            while (image.getWidth(this) < 0) {
                this.notifyAll();
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex7) {}
            }
        }
        return image;
    }
    
    public FlagLoad() {
        this.w = false;
        this.bf = 1;
        this.U = false;
        this.c = false;
        this.be = false;
        this.P = false;
    }
    
    public void a(final String s, final int n) {
        try {
            this.b(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.b(s, n);
        }
    }
    
    public void b(final String s, final int n) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            try {
                final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                if (dataInputStream == null) {
                    return;
                }
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                final byte[] array = new byte[512];
                int n2 = 0;
                boolean b = false;
                try {
                    while (!b) {
                        final int read = dataInputStream.read(array, 0, 512);
                        if (read == -1) {
                            b = true;
                        }
                        else {
                            byteArrayOutputStream.write(array, 0, read);
                            byteArrayOutputStream.flush();
                            n2 += read;
                        }
                    }
                    final byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    dataInputStream.close();
                    System.gc();
                    if (n == 0) {
                        for (int i = 0; i < n2; ++i) {
                            final byte b2 = byteArray[i];
                            if (b2 == 13 || b2 == 10) {
                                byteArray[i] = 32;
                            }
                        }
                        try {
                            this.bh = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bh = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.x = new String[n3 - 1];
                    final int[] array2 = new int[n3 + 1];
                    final int[] array3 = new int[n3 + 1];
                    array2[0] = 0;
                    int n4 = 0;
                    int n5 = 0;
                    for (int k = 0; k < n2; ++k) {
                        final byte b3 = byteArray[k];
                        if (b3 == 10) {
                            array2[n4 + 1] = k + 1;
                            if (n5 == 13) {
                                array3[n4] = k - array2[n4] - 1;
                            }
                            else {
                                array3[n4] = k - array2[n4];
                            }
                            ++n4;
                        }
                        n5 = b3;
                    }
                    array3[n4] = n2 - array2[n4 + 1] - 1;
                    try {
                        for (int l = 0; l < n3 - 1; ++l) {
                            try {
                                this.x[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.x[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.x = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.o);
        if (this.a == 0) {
            this.bV = this.bY;
        }
        else {
            this.v += this.bJ;
            this.bV = this.bY - (int)Math.abs(this.a * Math.sin(this.v / 90.0 * 3.141592653589793));
        }
        if (this.bo != 0) {
            for (int i = 0; i < this.bL; ++i) {
                final int n = this.bt[this.bn + i];
                graphics.copyArea(i, n, 1, this.bq, 0, this.bc - n);
            }
            if (this.bF) {
                graphics.setColor(this.bl);
                graphics.drawString(this.bh, this.bS + 1, this.bc + this.bi + 1);
            }
            graphics.setColor(this.bB);
            graphics.drawString(this.bh, this.bS, this.bc + this.bi);
            for (int j = 0; j < this.bL; ++j) {
                graphics.copyArea(j, this.bc, 1, this.br, 0, this.bu[this.bn + j]);
            }
            this.bn -= this.bs;
            if (this.bn < 0) {
                this.bn += 360;
            }
        }
        else {
            if (this.bF) {
                graphics.setColor(this.bl);
                graphics.drawString(this.bh, this.bS + 1, this.bV + 1);
            }
            graphics.setColor(this.bB);
            graphics.drawString(this.bh, this.bS, this.bV);
        }
        this.bS -= this.bK;
        if (this.bS < -this.bj) {
            this.bS = this.bL;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.V) {
            if (n == 16) {
                this.U = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bG = this.getToolkit();
        this.bA = this.getParameter(c("i|\u0003H\u001bie\u0011["));
        final String parameter = this.getParameter(c("yz\u0007X\u0007n{"));
        if (parameter != null) {
            if (!parameter.startsWith(c("[x\u0012P\u000bn(\u0000EN\\i\u0000U\u0001:K\u000bI\ryaB\u0014\u0019m\u007fL]\u0000|"))) {
                this.a();
            }
        }
        else {
            this.a();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = c("|a\u000eY");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("|a\u000eY")) || s2.length() == 0 || s2.equalsIgnoreCase(c("vg\u0001]\u0002rg\u0011H")) || s2.equals(c("+:U\u0012^48L\r"))) {
            this.be = true;
        }
        else {
            if (s2.startsWith(c("m\u007f\u0015\u0012"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("hm\u0005_\u0001~m"));
            if (parameter2 != null && !parameter2.equals("NO") && parameter2.length() > 10) {
                int n = 1;
                try {
                    for (int i = 0; i < parameter2.length(); ++i) {
                        if (parameter2.charAt(i) == '+') {
                            ++n;
                        }
                    }
                }
                catch (StringIndexOutOfBoundsException ex3) {}
                final int[] array = new int[n];
                if (n == 1) {
                    array[0] = parameter2.length();
                }
                else {
                    int n2 = 0;
                    try {
                        for (int j = 0; j < parameter2.length(); ++j) {
                            if (parameter2.charAt(j) == '+') {
                                array[n2] = j;
                                ++n2;
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex4) {}
                    array[n2] = parameter2.length();
                }
                final String[] array2 = new String[n];
                int n3 = 0;
                for (int k = 0; k < n; ++k) {
                    try {
                        array2[k] = parameter2.substring(n3, array[k]);
                    }
                    catch (StringIndexOutOfBoundsException ex5) {}
                    n3 = array[k] + 1;
                }
                for (int l = 0; l < n; ++l) {
                    final int n4 = array2[l].length() - 8;
                    final byte[] array3 = new byte[n4];
                    final byte[] array4 = new byte[8];
                    array2[l].getBytes(0, n4, array3, 0);
                    array2[l].getBytes(n4, n4 + 8, array4, 0);
                    int n5 = n4 % 7;
                    final int n6 = n4 % 3;
                    for (int n7 = 0; n7 < n4; ++n7) {
                        final byte b = array3[n7];
                        if (b >= 48 && b <= 57) {
                            array3[n7] = this.a(b, n5, 48, 57);
                        }
                        else if (b >= 65 && b <= 90) {
                            array3[n7] = this.a(b, n5, 65, 90);
                        }
                        else if (b >= 97 && b <= 122) {
                            array3[n7] = this.a(b, n5, 97, 122);
                        }
                        else if (b == 45) {
                            array3[n7] = 46;
                        }
                        else if (b == 46) {
                            array3[n7] = 45;
                        }
                        if ((n5 += n6) > 7) {
                            n5 = 1;
                        }
                    }
                    byte b2 = 0;
                    byte b3 = 0;
                    for (int n8 = 0; n8 < 4; ++n8) {
                        final byte[] array5 = array4;
                        final int n9 = n8;
                        array5[n9] -= 52;
                    }
                    for (int n10 = 4; n10 < 8; ++n10) {
                        final byte[] array6 = array4;
                        final int n11 = n10;
                        array6[n11] -= 55;
                    }
                    for (int n12 = 0; n12 < n4; n12 += 2) {
                        b2 += array3[n12];
                    }
                    for (int n13 = 1; n13 < n4; n13 += 2) {
                        b3 += array3[n13];
                    }
                    String s3 = String.valueOf(b2);
                    String s4 = String.valueOf(b3);
                    while (s3.length() < 4) {
                        s3 = "0" + s3;
                    }
                    while (s4.length() < 4) {
                        s4 = "0" + s4;
                    }
                    final byte[] array7 = new byte[8];
                    s3.getBytes(0, 4, array7, 0);
                    s4.getBytes(0, 4, array7, 4);
                    if (new String(array7, 0).equals(new String(array4, 0))) {
                        final String s5 = new String(array3, 0);
                        String substring;
                        if (s5.startsWith(c("m\u007f\u0015\u0012"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.be = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("hm\u0005P\u0007tc"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.y = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.y = null;
            }
        }
        if (this.getParameter(c("hm\u0005R\u000bmn\u0010]\u0003\u007f")).equalsIgnoreCase(c("CM1"))) {
            this.P = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("u~\u0007N\u0007wo"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.V = this.a(parameter4);
            if (this.V != null) {
                String parameter5 = this.getParameter(c("u~\u0007N\u0007wo:"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.W = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("u~\u0007N\u0007wo;"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.X = Integer.valueOf(parameter6);
            }
        }
        this.K = this.getParameter(c("Wa\fo7TK"));
        if (this.K == null) {
            this.K = "10";
        }
        this.A = Integer.valueOf(this.K);
        this.B = this.getParameter(c("hm\u0011"));
        if (this.B == null) {
            this.B = "1";
        }
        this.F = this.getParameter(c("xi\u0001W<"));
        if (this.F == null) {
            this.F = "0";
        }
        this.G = this.getParameter(c("xi\u0001W)"));
        if (this.G == null) {
            this.G = "0";
        }
        this.H = this.getParameter(c("xi\u0001W,"));
        if (this.H == null) {
            this.H = "60";
        }
        this.I = this.getParameter(c("ji\u0016H\u000bhf"));
        if (this.I == null) {
            this.I = "8";
        }
        this.J = this.getParameter(c("ix\u0007Y\n"));
        if (this.J == null) {
            this.J = "3";
        }
        this.M = this.getParameter(c("y}\u0010J\u000b"));
        if (this.M == null) {
            this.M = "3";
        }
        this.N = this.getParameter(c("ma\fX"));
        if (this.N == null) {
            this.N = "8";
        }
        this.C = this.getParameter(c("ja\u001aX\u000bt{\u000bH\u0017"));
        if (this.C == null) {
            this.C = "3";
        }
        this.bv = Integer.valueOf(this.C);
        this.D = this.getParameter(c("wm\u000fX\u000bvi\u001b"));
        this.E = this.getParameter(c("jz\u000bS\u001cs|\u001b"));
        this.z = Integer.valueOf(this.D);
        this.Z = Integer.valueOf(this.E);
        if (this.z < 0) {
            this.z = 0;
        }
        if (this.Z > 10) {
            this.Z = 10;
        }
        else if (this.Z < 1) {
            this.Z = 1;
        }
        this.bf = Integer.valueOf(this.B);
        if (this.bf > 8) {
            this.bf = 8;
        }
        else if (this.bf < 1) {
            this.bf = 1;
        }
        this.g = Integer.valueOf(this.F);
        this.f = Integer.valueOf(this.G);
        this.e = Integer.valueOf(this.H);
        this.Y = Integer.valueOf(this.I);
        this.bw = Integer.valueOf(this.J);
        this.k = Integer.valueOf(this.M) + 122;
        this.bg = Integer.valueOf(this.N);
        this.C = this.getParameter(c("|d\u0003[\u0002ui\u0006"));
        this.u = this.a(this.C);
        if (this.u == null) {
            this.showStatus(c("_z\u0010S\u001c:d\r]\nsf\u0005\u001c\u0007wi\u0005YO"));
        }
        final int width = this.u.getWidth(this);
        final int height = this.u.getHeight(this);
        final int[] array8 = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.u, 0, 0, width, height, array8, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        final Dimension size = this.size();
        this.bO = size.width / this.bf;
        this.q = size.height / this.bf;
        this.bI = this.bO * this.q;
        final int n14 = this.bO - this.bg * 2 - 3;
        final int n15 = this.q - this.bg * 2 - 2;
        this.bR = n14 / this.bv;
        this.bW = n15 / this.bv;
        this.bX = n15 / this.bv - 2;
        this.bd = this.bO * this.bf;
        this.bc = this.q * this.bf;
        this.h = new int[this.bR + 1][this.bW + 1];
        float n16 = 0.0f;
        float n17 = 0.0f;
        final float n18 = (width - 1) / this.bR;
        final float n19 = (height - 1) / this.bW;
        for (int n20 = 0; n20 < this.bW; ++n20) {
            final int n21 = (int)n17 * width;
            for (int n22 = 0; n22 < this.bR; ++n22) {
                n16 += n18;
                this.h[n22][n20] = array8[(int)n16 + n21];
            }
            n17 += n19;
            n16 = 0.0f;
        }
        this.j = new int[this.bI];
        this.bQ = 0;
        while (this.bQ < this.bI) {
            this.j[this.bQ] = (this.g << 16 | this.f << 8 | this.e);
            ++this.bQ;
        }
        String parameter7 = this.getParameter(c("xi\u0001W\u0007wi\u0005Y"));
        if (parameter7 == null) {
            parameter7 = "NO";
        }
        if (!parameter7.equalsIgnoreCase("NO")) {
            final Image a = this.a(parameter7);
            if (a != null && a.getWidth(this) == this.bO && a.getHeight(this) == this.q) {
                final PixelGrabber pixelGrabber2 = new PixelGrabber(a, 0, 0, this.bO, this.q, this.j, 0, this.bO);
                try {
                    pixelGrabber2.grabPixels();
                }
                catch (InterruptedException ex8) {}
            }
        }
        this.by = 256;
        this.bz = this.by - 1;
        this.bx = new int[this.by];
        final double n23 = 3.141592653589793 / this.bz;
        final int n24 = this.bg + 2;
        for (int n25 = 0; n25 < this.by; ++n25) {
            this.bx[n25] = (int)(Math.sin(n25 * 4 * n23) * this.bg + n24);
        }
        this.n = new int[this.bO * this.q];
        try {
            this.l();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.l();
        }
        this.p();
        this.T = this.createImage(this.bd, this.bc + this.bq);
        this.S = this.T.getGraphics();
        if (!this.be) {
            (this.bP = new Lware(this.getAppletContext(), new Label(c("[f\u0004E(vi\u0005\u001c\u000fjx\u000eY\u001a:j\u001b\u001c({j\u000bSNYa\u0017_\rs(S\u0005W,'[\u0004@")))).setTitle(c("[f\u0004E(vi\u0005\u001c/jx\u000eY\u001a:j\u001b\u001c({j\u000bSNYa\u0017_\rs"));
            this.bP.hide();
        }
    }
    
    void l() {
        this.bb = new MemoryImageSource(this.bO, this.q, new DirectColorModel(24, 16711680, 65280, 255), this.n, 0, this.bO);
        String s;
        try {
            s = System.getProperty(c("pi\u0014]@lm\u0010O\u0007uf"));
        }
        catch (SecurityException ex) {
            s = c("of\t");
        }
        if (!s.startsWith(c("+&R"))) {
            try {
                this.bb.setAnimated(true);
                this.bb.setFullBufferUpdates(true);
                this.t = this.createImage(this.bb);
                this.bb.newPixels();
                this.w = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.w = false;
            }
        }
        if (!this.w) {
            this.bb = null;
            this.ba = new anfy(this.bO, this.q, new DirectColorModel(24, 16711680, 65280, 255), this.n, 0, this.bO);
            this.t = this.createImage(this.ba);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.be) {
            this.bP.show();
            this.bP.toFront();
            this.bP.requestFocus();
        }
        else if (this.y != null) {
            if (this.P) {
                this.getAppletContext().showDocument(this.y, this.getParameter(c("hm\u0005Z\u001c{e\u0007R\u000fwm")));
            }
            else {
                this.getAppletContext().showDocument(this.y);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bA);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.t != null) {
            if (this.bf == 1) {
                this.S.drawImage(this.t, 0, 0, this);
            }
            else {
                this.n();
                this.S.drawImage(this.t, 0, 0, this.bd, this.bc, this);
            }
            if (this.V != null) {
                this.m();
            }
            if (this.bE) {
                this.b(this.S);
            }
            graphics.drawImage(this.T, 0, 0, this);
        }
    }
    
    public synchronized void m() {
        if (this.c) {
            this.notifyAll();
            while (!this.U) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.U = false;
        }
        this.S.drawImage(this.V, this.W, this.X, this);
    }
    
    public synchronized void n() {
        int checkImage = 0;
        this.prepareImage(this.t, this.bd, this.bc, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.t, this.bd, this.bc, this);
        }
    }
    
    public final void o() {
        try {
            if (this.w) {
                this.bb.newPixels();
                return;
            }
            this.ba.startProduction(this.ba.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bH.setPriority(this.Z);
        this.showStatus("");
        System.gc();
        this.l = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.V != null && !this.c) {
            this.c = this.b();
        }
        if (this.y != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bH != null) {
            try {
                System.arraycopy(this.j, 0, this.n, 0, this.bI);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                this.stop();
            }
            catch (ArrayStoreException ex2) {
                this.stop();
            }
            switch (this.Y) {
                case 1: {
                    this.c();
                    break;
                }
                case 2: {
                    this.d();
                    break;
                }
                case 3: {
                    this.i();
                    break;
                }
                case 4: {
                    this.j();
                    break;
                }
                case 5: {
                    this.k();
                    break;
                }
                case 6: {
                    this.e();
                    break;
                }
                case 7: {
                    this.g();
                    break;
                }
                case 8: {
                    this.h();
                    break;
                }
            }
            if (++this.i == this.z) {
                System.gc();
                this.i = 0;
            }
            try {
                this.o();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bf == 1) {
                this.S.drawImage(this.t, 0, 0, this);
            }
            else {
                this.n();
                this.S.drawImage(this.t, 0, 0, this.bd, this.bc, this);
            }
            if (this.V != null) {
                this.m();
            }
            if (this.bE) {
                this.b(this.S);
            }
            graphics.drawImage(this.T, 0, 0, this);
            this.q();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void p() {
        this.bE = false;
        final String parameter = this.getParameter(c("nm\u001aH\u001dyz\rP\u0002"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("nm\u001aH\u001acx\u0007"));
            if (s == null) {
                s = c("rg\u0010U\u0014uf\u0016]\u0002");
            }
            if (s.equals(c("rg\u0010U\u0014uf\u0016]\u0002"))) {
                this.bk = 0;
            }
            else if (s.equals(c("lm\u0010H\u0007yi\u000e"))) {
                this.bk = 1;
            }
            else if (s.equals(c("`g\rQ\u0007to"))) {
                this.bk = 2;
            }
            else if (s.equals(c("sf\u0014F\u0001ue\u000bR\t"))) {
                this.bk = 3;
            }
            if (this.bk == 0) {
                this.a(parameter, 0);
                if (this.bh != null) {
                    this.bE = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.x != null) {
                    this.bE = true;
                }
            }
        }
        if (this.bE) {
            String parameter2 = this.getParameter(c("nm\u001aH\u001djm\u0007X"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bK = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("nm\u001aH\buf\u0016"));
            if (s2 == null) {
                s2 = c("[z\u000b]\u0002");
            }
            int n = 0;
            if (this.getParameter(c("nm\u001aH\fud\u0006")).equalsIgnoreCase(c("CM1"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("nm\u001aH\u0007ni\u000eU\r"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("CM1"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("nm\u001aH\u001dsr\u0007"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.o = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("nm\u001aH\u001dri\u0006S\u0019")).equalsIgnoreCase(c("CM1"))) {
                this.bF = true;
            }
            else {
                this.bF = false;
            }
            this.bB = new Color(Integer.valueOf(this.getParameter(c("Nm\u001aH-ud0"))), Integer.valueOf(this.getParameter(c("Nm\u001aH-ud%"))), Integer.valueOf(this.getParameter(c("Nm\u001aH-ud "))));
            this.bl = new Color(Integer.valueOf(this.getParameter(c("Nm\u001aH=Yg\u000en"))), Integer.valueOf(this.getParameter(c("Nm\u001aH=Yg\u000e{"))), Integer.valueOf(this.getParameter(c("Nm\u001aH=Yg\u000e~"))));
            this.bL = this.size().width;
            this.bM = this.size().height;
            if (this.bk == 0) {
                String parameter5 = this.getParameter(c("nm\u001aH\u0001|n\u0011Y\u001a"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bY = Integer.valueOf(parameter5);
                if (this.bY < 0) {
                    this.bY = 0;
                }
                String parameter6 = this.getParameter(c("Nm\u001aH$oe\u0012}\u0003j"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("Nm\u001aH$oe\u0012o\u001e~"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bJ = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("Nm\u001aH=sf\u0007}\u0003j"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bo = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("Nm\u001aH=sf\u0007o\u001e~"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bs = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("Nm\u001aH=sf\u0007}\u0000}d\u0007"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bp = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.o);
                this.bj = fontMetrics.stringWidth(this.bh);
                this.bi = fontMetrics.getHeight();
                this.m = fontMetrics.getMaxDescent();
                this.bS = this.bL;
                if (this.bY < this.bi - this.m) {
                    this.bY = this.bi - this.m;
                }
                else if (this.bY > this.bM - this.m) {
                    this.bY = this.bM - this.m;
                }
                if (this.bo != 0) {
                    this.bt = new int[this.bL + 360];
                    this.bu = new int[this.bL + 360];
                    for (int i = 0; i < this.bL + 360; ++i) {
                        this.bt[i] = (int)(this.bo * Math.sin(this.bp * i * 3.141592653589793 / 180.0)) - this.bi - this.m + this.bY;
                        this.bu[i] = this.bt[i] - this.bc;
                    }
                    this.bn = 360;
                    this.bq = this.bi + this.m + 1;
                    this.br = this.bq - 1;
                }
            }
            else {
                if (this.bk == 1) {
                    String parameter11 = this.getParameter(c("nm\u001aH\u0018ix\u0003_\u000b"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.o);
                    this.p = fontMetrics2.getHeight() + intValue;
                    this.bN = new int[this.x.length];
                    for (int j = 0; j < this.x.length; ++j) {
                        this.bN[j] = (this.bL - fontMetrics2.stringWidth(this.x[j])) / 2;
                    }
                    this.O = -this.p;
                    return;
                }
                if (this.bk >= 2) {
                    String parameter12 = this.getParameter(c("nm\u001aH\u0003sf\u0004S\u0000n"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bD = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("nm\u001aH\u0003{p\u0004S\u0000n"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bC = Integer.valueOf(parameter13);
                    this.Q = this.bC - this.bD;
                    this.o = null;
                    this.cc = new Font[this.Q];
                    int bd = this.bD;
                    for (int k = 0; k < this.Q; ++k) {
                        this.cc[k] = new Font(s2, n, bd++);
                    }
                    this.s = this.bL / 2.0f;
                    this.r = this.bM / 2.0f;
                    if (this.bk == 3) {
                        this.cb = this.Q - 1;
                        return;
                    }
                    this.cb = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bk) {
            case 0: {
                this.a(graphics);
            }
            case 1: {
                this.c(graphics);
            }
            default: {
                this.d(graphics);
            }
        }
    }
    
    public void start() {
        if (this.bH == null) {
            (this.bH = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bH != null && this.bH.isAlive()) {
            this.bH.stop();
        }
        this.bH = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.o);
        this.O += this.bK;
        if (this.O > this.bM + this.x.length * this.p) {
            this.O = -this.p;
        }
        if (this.bF) {
            for (int i = 0; i < this.x.length; ++i) {
                final String s = this.x[i];
                final int n = this.bN[i];
                final int n2 = this.bM - this.O + i * this.p;
                graphics.setColor(this.bl);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bB);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bB);
        for (int j = 0; j < this.x.length; ++j) {
            graphics.drawString(this.x[j], this.bN[j], this.bM - this.O + j * this.p);
        }
    }
    
    public synchronized void q() {
        Thread.yield();
        this.bG.sync();
        final long n = 10L - (System.currentTimeMillis() - this.l);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.l = System.currentTimeMillis();
        try {
            Thread.sleep(this.A);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.x[this.ca];
        graphics.setFont(this.cc[this.cb]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cc[this.cb]);
        final int n = (int)(this.s - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.r + fontMetrics.getHeight() / 4.0f);
        if (this.bF) {
            graphics.setColor(this.bl);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bB);
        graphics.drawString(s, n, n2);
        if (this.bk == 3) {
            this.cb -= this.bK;
            if (this.cb <= 1) {
                this.cb = this.Q - 1;
                ++this.ca;
                if (this.ca >= this.x.length) {
                    this.ca = 0;
                }
            }
        }
        else {
            this.cb += this.bK;
            if (this.cb >= this.Q) {
                this.cb = 0;
                ++this.ca;
                if (this.ca >= this.x.length) {
                    this.ca = 0;
                }
            }
        }
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = '\u001a';
                char[] array2;
                char[] array = array2 = charArray;
                int n4;
                int n3 = n4 = n;
                while (true) {
                    array[n3] = (char)(c ^ array2[n4]);
                    Label_0047: {
                        Label_0039: {
                            Label_0029: {
                            Label_0021:
                                while (true) {
                                    ++n;
                                    ++n2;
                                    if (length == n) {
                                        break Label_0010;
                                    }
                                    switch (n2) {
                                        case 5: {
                                            continue Label_0010;
                                        }
                                        case 1: {
                                            break Label_0021;
                                        }
                                        case 2: {
                                            break Label_0029;
                                        }
                                        case 3: {
                                            break Label_0039;
                                        }
                                        case 4: {
                                            break Label_0047;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                }
                                c = '\b';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = 'b';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '<';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'n';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
