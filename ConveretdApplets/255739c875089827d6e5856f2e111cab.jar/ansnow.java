import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.util.Random;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ansnow extends Applet implements Runnable, ImageObserver
{
    boolean a;
    boolean b;
    private int c;
    Frame d;
    boolean e;
    final String f = "pBdE7E\u0012vPrwSv@=\u0011q}\\1R[4\u0001%FE:H<WK";
    Color g;
    boolean h;
    int[] i;
    float[] j;
    int k;
    long l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    Font u;
    int v;
    int w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    int[] E;
    float[] F;
    int G;
    float H;
    float I;
    float J;
    private Graphics K;
    int L;
    int M;
    int N;
    float O;
    float P;
    private Image Q;
    Image R;
    int S;
    int T;
    boolean U;
    String[] V;
    URL W;
    int X;
    int Y;
    String Z;
    String ba;
    int bb;
    boolean bc;
    int[] bd;
    float[] be;
    int bf;
    private Graphics bg;
    private Image bh;
    boolean bi;
    float bj;
    private Image bk;
    boolean bl;
    int bm;
    int bn;
    int bo;
    Random bp;
    int bq;
    int br;
    boolean bs;
    String bt;
    int bu;
    int bv;
    int bw;
    Color bx;
    int by;
    int bz;
    int bA;
    int bB;
    int bC;
    int bD;
    int[] bE;
    int[] bF;
    int[] bG;
    float[] bH;
    float bI;
    String bJ;
    int bK;
    long bL;
    Color bM;
    int bN;
    int bO;
    boolean bP;
    boolean bQ;
    Toolkit bR;
    Thread bS;
    int bT;
    int bU;
    int bV;
    int bW;
    int[] bX;
    int bY;
    int bZ;
    int ca;
    int cb;
    int cc;
    Lware cd;
    int ce;
    int cf;
    int cg;
    int ch;
    int ci;
    Font[] cj;
    
    public ansnow() {
        this.U = false;
        this.bi = false;
        this.e = false;
        this.bs = false;
        this.bc = false;
        this.v = 12;
        this.w = 8;
        this.x = 10;
        this.b = false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("u]z\u000e&\u0011@qD=GW4^%F\u001cuG4HFqH?\u001fQ{DrR@qM;EA4E;_W4@<\u0011z@d\u001e\u0010"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bk, this);
        for (int i = 0; i < 3; ++i) {
            this.notifyAll();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        return this.bi;
    }
    
    public void destroy() {
        if (this.bk != null) {
            this.bk.flush();
        }
        this.bk = null;
        if (this.bh != null) {
            this.bh.flush();
        }
        this.bh = null;
        if (this.bg != null) {
            this.bg.dispose();
        }
        this.bg = null;
        System.gc();
    }
    
    public void c() {
        final Graphics bg = this.bg;
        final Random bp = this.bp;
        bg.setColor(new Color(230, 230, 230));
        if (this.n > 0) {
            final int[] i = this.i;
            final float[] j = this.j;
            for (int k = 0; k < this.n; ++k) {
                final int n = i[k];
                final int n2 = (int)j[k];
                bg.drawLine(n, n2, n, n2);
                final int[] array = i;
                final int n3 = k;
                array[n3] += (int)(bp.nextInt() % 2 + this.H * 0.7);
                final float[] array2 = j;
                final int n4 = k;
                array2[n4] += ((bp.nextInt() % 4 + 2) / 8 + 1) * this.bI;
                if (i[k] >= this.bZ) {
                    i[k] = 0;
                }
                if (i[k] < 0) {
                    i[k] = this.bZ - 1;
                }
                final int n5 = (int)j[k];
                if (n5 >= this.M || n5 < 0) {
                    final int n6 = bp.nextInt() % this.bZ;
                    if (n6 < 0) {
                        i[k] = -n6;
                    }
                    else {
                        i[k] = n6;
                    }
                    j[k] = 0.0f;
                }
            }
        }
        bg.setColor(new Color(245, 245, 245));
        if (this.o > 0) {
            final int[] bg2 = this.bG;
            final float[] bh = this.bH;
            this.r = this.y;
            for (int l = 0; l < this.o; ++l) {
                ++this.B;
                if (this.B > this.v) {
                    this.B = 0;
                    ++this.r;
                    if (this.r > 2) {
                        this.r = 0;
                    }
                }
                final int n7 = bg2[l];
                final int n8 = (int)bh[l];
                if (l % 2 == 0) {
                    switch (this.r) {
                        case 0: {
                            bg.drawLine(n7, n8, n7, n8);
                            break;
                        }
                        case 1: {
                            bg.drawLine(n7, n8, n7, n8 + 1);
                            break;
                        }
                        case 2: {
                            bg.drawLine(n7, n8, n7 + 1, n8);
                            break;
                        }
                    }
                }
                else {
                    switch (this.r) {
                        case 2: {
                            bg.drawLine(n7, n8, n7, n8);
                            break;
                        }
                        case 0: {
                            bg.drawLine(n7, n8, n7, n8 + 1);
                            break;
                        }
                        case 1: {
                            bg.drawLine(n7, n8, n7 + 1, n8);
                            break;
                        }
                    }
                }
                final int[] array3 = bg2;
                final int n9 = l;
                array3[n9] += (int)(bp.nextInt() % 2 + this.H * 0.8);
                final float[] array4 = bh;
                final int n10 = l;
                array4[n10] += ((bp.nextInt() % 6 + 5) / 5 + 1) * this.bI;
                if (bg2[l] >= this.bZ) {
                    bg2[l] = 0;
                }
                if (bg2[l] < 0) {
                    bg2[l] = this.bZ - 1;
                }
                final int n11 = (int)bh[l];
                if (n11 >= this.M || n11 < 0) {
                    final int n12 = bp.nextInt() % this.bZ;
                    if (n12 < 0) {
                        bg2[l] = -n12;
                    }
                    else {
                        bg2[l] = n12;
                    }
                    bh[l] = 0.0f;
                }
            }
            ++this.y;
            if (this.y > 2) {
                this.y = 0;
            }
        }
        bg.setColor(new Color(254, 254, 254));
        if (this.p > 0) {
            final int[] e = this.E;
            final float[] f = this.F;
            this.s = this.z;
            for (int n13 = 0; n13 < this.p; ++n13) {
                ++this.C;
                if (this.C > this.w) {
                    this.C = 0;
                    ++this.s;
                    if (this.s > 4) {
                        this.s = 0;
                    }
                }
                final int n14 = e[n13];
                final int n15 = (int)f[n13];
                if (n13 % 2 == 0) {
                    switch (this.s) {
                        case 0: {
                            bg.drawLine(n14 - 1, n15, n14, n15);
                            break;
                        }
                        case 1: {
                            bg.drawLine(n14 - 1, n15 - 1, n14, n15);
                            break;
                        }
                        case 2: {
                            bg.drawLine(n14 - 1, n15 - 1, n14 + 1, n15);
                            break;
                        }
                        case 3: {
                            bg.drawLine(n14 - 1, n15, n14 + 1, n15);
                            break;
                        }
                        case 4: {
                            bg.drawLine(n14 - 1, n15, n14, n15 + 1);
                            break;
                        }
                    }
                }
                else {
                    switch (this.s) {
                        case 2: {
                            bg.drawLine(n14 - 1, n15, n14, n15);
                            break;
                        }
                        case 1: {
                            bg.drawLine(n14 - 1, n15 - 1, n14, n15);
                            break;
                        }
                        case 0: {
                            bg.drawLine(n14 - 1, n15 - 1, n14 + 1, n15);
                            break;
                        }
                        case 4: {
                            bg.drawLine(n14 - 1, n15, n14 + 1, n15);
                            break;
                        }
                        case 3: {
                            bg.drawLine(n14 - 1, n15, n14, n15 + 1);
                            break;
                        }
                    }
                }
                final int[] array5 = e;
                final int n16 = n13;
                array5[n16] += (int)(bp.nextInt() % 2 + this.H * 1.1);
                final float[] array6 = f;
                final int n17 = n13;
                array6[n17] += ((bp.nextInt() % 8 + 6) / 5 + 2) * this.bI;
                if (e[n13] >= this.bZ) {
                    e[n13] = 0;
                }
                if (e[n13] < 0) {
                    e[n13] = this.bZ - 1;
                }
                final int n18 = (int)f[n13];
                if (n18 >= this.M || n18 < 0) {
                    final int n19 = bp.nextInt() % this.bZ;
                    if (n19 < 0) {
                        e[n13] = -n19;
                    }
                    else {
                        e[n13] = n19;
                    }
                    f[n13] = 0.0f;
                }
            }
            ++this.z;
            if (this.z > 4) {
                this.z = 0;
            }
        }
        bg.setColor(new Color(255, 255, 255));
        if (this.q > 0) {
            final int[] bd = this.bd;
            final float[] be = this.be;
            this.t = this.A;
            for (int n20 = 0; n20 < this.q; ++n20) {
                ++this.D;
                if (this.D > this.x) {
                    this.D = 0;
                    ++this.t;
                    if (this.t > 8) {
                        this.t = 0;
                    }
                }
                final int n21 = bd[n20];
                final int n22 = (int)be[n20];
                if (n20 % 2 == 0) {
                    switch (this.t) {
                        case 0: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22 + 1);
                            break;
                        }
                        case 1: {
                            bg.drawLine(n21 - 2, n22 - 1, n21 + 2, n22);
                            break;
                        }
                        case 2: {
                            bg.drawLine(n21 - 1, n22 - 1, n21 + 2, n22);
                            break;
                        }
                        case 3: {
                            bg.drawLine(n21 - 1, n22, n21 + 1, n22 - 1);
                            break;
                        }
                        case 4: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22 - 1);
                            break;
                        }
                        case 5: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22);
                            break;
                        }
                        case 6: {
                            bg.drawLine(n21 - 1, n22 + 1, n21 + 2, n22);
                            break;
                        }
                        case 7: {
                            bg.drawLine(n21 - 2, n22 + 1, n21 + 2, n22 + 1);
                            break;
                        }
                        case 8: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22 + 1);
                            break;
                        }
                    }
                }
                else {
                    switch (this.t) {
                        case 4: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22 + 1);
                            break;
                        }
                        case 3: {
                            bg.drawLine(n21 - 2, n22 - 1, n21 + 2, n22);
                            break;
                        }
                        case 2: {
                            bg.drawLine(n21 - 1, n22 - 1, n21 + 2, n22);
                            break;
                        }
                        case 1: {
                            bg.drawLine(n21 - 1, n22, n21 + 1, n22 - 1);
                            break;
                        }
                        case 0: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22 - 1);
                            break;
                        }
                        case 8: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22);
                            break;
                        }
                        case 7: {
                            bg.drawLine(n21 - 1, n22 + 1, n21 + 2, n22);
                            break;
                        }
                        case 6: {
                            bg.drawLine(n21 - 2, n22 + 1, n21 + 2, n22 + 1);
                            break;
                        }
                        case 5: {
                            bg.drawLine(n21 - 2, n22, n21 + 1, n22 + 1);
                            break;
                        }
                    }
                }
                final int[] array7 = bd;
                final int n23 = n20;
                array7[n23] += (int)(bp.nextInt() % 3 + this.H * 1.5);
                final float[] array8 = be;
                final int n24 = n20;
                array8[n24] += ((bp.nextInt() % 8 + 6) / 5 + 3) * this.bI;
                if (bd[n20] >= this.bZ) {
                    bd[n20] = 0;
                }
                if (bd[n20] < 0) {
                    bd[n20] = this.bZ - 1;
                }
                final int n25 = (int)be[n20];
                if (n25 >= this.M || n25 < 0) {
                    final int n26 = bp.nextInt() % this.bZ;
                    if (n26 < 0) {
                        bd[n20] = -n26;
                    }
                    else {
                        bd[n20] = n26;
                    }
                    be[n20] = 0.0f;
                }
            }
            ++this.A;
            if (this.A > 8) {
                this.A = 0;
            }
        }
        if (this.cb > 0) {
            if (!this.b) {
                final int n27 = bp.nextInt() % this.cc;
                if (this.cb == 3) {
                    switch (n27) {
                        case -3: {
                            this.I = -3.0f;
                            break;
                        }
                        case -2: {
                            this.I = -2.0f;
                            break;
                        }
                        case -1: {
                            this.I = -1.0f;
                            break;
                        }
                        case 0: {
                            this.I = 0.0f;
                            break;
                        }
                        case 1: {
                            this.I = 1.0f;
                            break;
                        }
                        case 2: {
                            this.I = 2.0f;
                            break;
                        }
                        case 3: {
                            this.I = 3.0f;
                            break;
                        }
                    }
                }
                else if (this.cb == 2) {
                    switch (n27) {
                        case -2: {
                            this.I = -2.0f;
                            break;
                        }
                        case -1: {
                            this.I = -1.0f;
                            break;
                        }
                        case 0: {
                            this.I = 0.0f;
                            break;
                        }
                        case 1: {
                            this.I = 1.0f;
                            break;
                        }
                        case 2: {
                            this.I = 2.0f;
                            break;
                        }
                    }
                }
                else if (this.cb == 1) {
                    switch (n27) {
                        case -1: {
                            this.I = -1.0f;
                            break;
                        }
                        case 0: {
                            this.I = 0.0f;
                            break;
                        }
                        case 1: {
                            this.I = 1.0f;
                            break;
                        }
                    }
                }
                if (this.bj == this.I) {
                    this.b = false;
                    return;
                }
                if (this.I > this.bj) {
                    this.a = true;
                }
                else {
                    this.a = false;
                }
                this.bj = this.I;
                this.J = (this.I - this.H) / 80.0f;
                this.b = true;
            }
            else {
                this.H += this.J;
                if (this.a) {
                    if (this.H >= this.I) {
                        this.b = false;
                    }
                }
                else if (this.H <= this.I) {
                    this.b = false;
                }
            }
        }
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
                this.showStatus(c("x_uN7\u0011") + s + c("\u0011\\{]rW]aG6\u0010"));
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
                            this.bt = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bt = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.V = new String[n3 - 1];
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
                                this.V[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.V[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.V = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.u);
        if (this.c == 0) {
            this.cf = this.cg;
        }
        else {
            this.T += this.bT;
            this.cf = this.cg - (int)Math.abs(this.c * Math.sin(this.T / 90.0 * 3.141592653589793));
        }
        if (this.bz != 0) {
            for (int i = 0; i < this.bV; ++i) {
                final int n = this.bE[this.by + i];
                graphics.copyArea(i, n, 1, this.bB, 0, this.bq - n);
            }
            if (this.bQ) {
                graphics.setColor(this.bx);
                graphics.drawString(this.bt, this.ce + 1, this.bq + this.bu + 1);
            }
            graphics.setColor(this.bM);
            graphics.drawString(this.bt, this.ce, this.bq + this.bu);
            for (int j = 0; j < this.bV; ++j) {
                graphics.copyArea(j, this.bq, 1, this.bC, 0, this.bF[this.by + j]);
            }
            this.by -= this.bD;
            if (this.by < 0) {
                this.by += 360;
            }
        }
        else {
            if (this.bQ) {
                graphics.setColor(this.bx);
                graphics.drawString(this.bt, this.ce + 1, this.cf + 1);
            }
            graphics.setColor(this.bM);
            graphics.drawString(this.bt, this.ce, this.cf);
        }
        this.ce -= this.bU;
        if (this.ce < -this.bv) {
            this.ce = this.bV;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bk) {
            if (n == 16) {
                this.bi = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bJ = this.getParameter(c("BFu]'B_gN"));
        this.bZ = this.size().width;
        this.M = this.size().height;
        this.ca = this.bZ / 2;
        this.N = this.M / 2;
        final Dimension size = this.size();
        this.br = size.width;
        this.bq = size.height;
        this.bY = size.width;
        this.L = size.height;
        final String parameter = this.getParameter(c("R@qM;EA"));
        if (parameter != null) {
            if (!parameter.startsWith(c("pBdE7E\u0012vPrwSv@=\u0011q}\\1R[4\u0001%FE:H<WK"))) {
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
            s = c("W[xL");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("W[xL")) || s2.length() == 0 || s2.equalsIgnoreCase(c("]]wH>Y]g]")) || s2.equals(c("\u0000\u0000#\u0007b\u001f\u0002:\u0018"))) {
            this.bs = true;
        }
        else {
            if (s2.startsWith(c("FEc\u0007"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("CWsJ=UW"));
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
                        if (s5.startsWith(c("FEc\u0007"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bs = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("CWsE;_Y"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.W = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.W = null;
            }
        }
        if (this.getParameter(c("CWsG7FTfH?T")).equalsIgnoreCase(c("hwG"))) {
            this.bc = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.d = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("^Dq[;\\U"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bk = this.a(parameter4);
            if (this.bk != null) {
                String parameter5 = this.getParameter(c("^Dq[;\\UL"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bm = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("^Dq[;\\UM"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bn = Integer.valueOf(parameter6);
            }
        }
        final String parameter7 = this.getParameter(c("F[zM?PJ"));
        if (parameter7 == null) {
            this.cb = 2;
        }
        else {
            this.cb = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter(c("F[zM$P@}H&X]z"));
        if (parameter8 == null) {
            this.cc = 0;
        }
        else {
            this.cc = 1000 - Integer.parseInt(parameter8) * 100 + 4;
        }
        if (this.cc == 1004) {
            this.cb = 0;
        }
        final String parameter9 = this.getParameter(c("SSwB;\\SsL"));
        if (parameter9 == null || parameter9.equalsIgnoreCase("NO")) {
            this.h = false;
        }
        else {
            this.h = true;
            this.R = this.a(parameter9);
            if (this.R == null) {
                this.h = false;
            }
        }
        if (this.getParameter(c("^Dq[&TJ`")).equalsIgnoreCase(c("hwG"))) {
            this.bl = true;
        }
        else {
            this.bl = false;
        }
        this.g = new Color(Integer.parseInt(this.getParameter(c("SUwF>^@")), 16));
        this.bI = Float.valueOf(this.getParameter(c("BBqL6"))) / 10.0f;
        this.bp = new Random();
        this.n = Integer.valueOf(this.getParameter(c("W^uB7B\u0003")));
        this.o = Integer.valueOf(this.getParameter(c("W^uB7B\u0000")));
        this.p = Integer.valueOf(this.getParameter(c("W^uB7B\u0001")));
        this.q = Integer.valueOf(this.getParameter(c("W^uB7B\u0006")));
        if (this.n > 0) {
            this.i = new int[this.n];
            this.j = new float[this.n];
            for (int n14 = 0; n14 < this.n; ++n14) {
                this.i[n14] = this.bp.nextInt() % this.ca + this.ca;
                this.j[n14] = this.bp.nextInt() % this.N + this.N;
            }
        }
        if (this.o > 0) {
            this.bG = new int[this.o];
            this.bH = new float[this.o];
            for (int n15 = 0; n15 < this.o; ++n15) {
                this.bG[n15] = this.bp.nextInt() % this.ca + this.ca;
                this.bH[n15] = this.bp.nextInt() % this.N + this.N;
            }
        }
        if (this.p > 0) {
            this.E = new int[this.p];
            this.F = new float[this.p];
            for (int n16 = 0; n16 < this.p; ++n16) {
                this.E[n16] = this.bp.nextInt() % this.ca + this.ca;
                this.F[n16] = this.bp.nextInt() % this.N + this.N;
            }
        }
        if (this.q > 0) {
            this.bd = new int[this.q];
            this.be = new float[this.q];
            for (int n17 = 0; n17 < this.q; ++n17) {
                this.bd[n17] = this.bp.nextInt() % this.ca + this.ca;
                this.be[n17] = this.bp.nextInt() % this.N + this.N;
            }
        }
        this.Z = this.getParameter(c("\\WyM7]Sm"));
        this.ba = this.getParameter(c("A@}F XFm"));
        this.X = Integer.valueOf(this.Z);
        this.bo = Integer.valueOf(this.ba);
        this.ba = null;
        this.ba = this.getParameter(c("|[zz\u000b\u007fq"));
        this.Y = Integer.valueOf(this.ba);
        if (this.X < 0) {
            this.X = 0;
        }
        if (this.bo > 10) {
            this.bo = 10;
        }
        else if (this.bo < 1) {
            this.bo = 1;
        }
        this.e();
        if (this.bz != 0) {
            this.bh = this.createImage(this.bZ, this.M + this.bB);
        }
        else {
            this.bh = this.createImage(this.bZ, this.M);
        }
        this.bg = this.bh.getGraphics();
        if (!this.bs) {
            (this.cd = new Lware(this.getAppletContext(), new Label(c("b\\{^rPBdE7E\u0012vPrwSv@=\u0011q}\\1R[4\u0018k\b\u000b:")))).setTitle(c("b\\{^rpBdE7E\u0012vPrwSv@=\u0011q}\\1R["));
            this.cd.hide();
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bs) {
            this.cd.show();
            this.cd.toFront();
            this.cd.requestFocus();
        }
        else if (this.W != null) {
            if (this.bc) {
                this.getAppletContext().showDocument(this.W, this.getParameter(c("CWsO P_qG3\\W")));
            }
            else {
                this.getAppletContext().showDocument(this.W);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bJ);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.bh, 0, 0, this);
    }
    
    public synchronized void d() {
        if (this.e) {
            this.notifyAll();
            while (!this.bi) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bi = false;
        }
        this.bg.drawImage(this.bk, this.bm, this.bn, this);
    }
    
    public void run() {
        this.bR = this.getToolkit();
        this.bS.setPriority(this.bo);
        this.showStatus("");
        System.gc();
        this.l = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bk != null && !this.e) {
            this.e = this.b();
        }
        if (this.W != null) {
            this.d.setCursor(12);
        }
        else {
            this.d.setCursor(0);
        }
        while (this.bS != null) {
            if (++this.k == this.X) {
                System.gc();
                this.k = 0;
            }
            if (!this.h) {
                this.bg.setColor(this.g);
                this.bg.fillRect(0, 0, this.bZ, this.M);
            }
            else {
                this.bg.drawImage(this.R, 0, 0, this);
            }
            if (this.bP && this.bl) {
                this.b(this.bg);
            }
            this.c();
            if (this.bk != null) {
                this.d();
            }
            if (this.bP && !this.bl) {
                this.b(this.bg);
            }
            graphics.drawImage(this.bh, 0, 0, this);
            this.f();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void e() {
        this.bP = false;
        final String parameter = this.getParameter(c("EWl]!R@{E>"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("EWl]&HBq"));
            if (s == null) {
                s = c("Y]f@(^\\`H>");
            }
            if (s.equals(c("Y]f@(^\\`H>"))) {
                this.bw = 0;
            }
            else if (s.equals(c("GWf];RSx"))) {
                this.bw = 1;
            }
            else if (s.equals(c("K]{D;_U"))) {
                this.bw = 2;
            }
            else if (s.equals(c("X\\bS=^_}G5"))) {
                this.bw = 3;
            }
            if (this.bw == 0) {
                this.a(parameter, 0);
                if (this.bt != null) {
                    this.bP = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.V != null) {
                    this.bP = true;
                }
            }
        }
        if (this.bP) {
            String parameter2 = this.getParameter(c("EWl]!AWqM"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bU = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("EWl]4^\\`"));
            if (s2 == null) {
                s2 = c("p@}H>");
            }
            int n = 0;
            if (this.getParameter(c("EWl]0^^p")).equalsIgnoreCase(c("hwG"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("EWl];ESx@1"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("hwG"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("EWl]!XHq"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.u = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("EWl]!YSpF%")).equalsIgnoreCase(c("hwG"))) {
                this.bQ = true;
            }
            else {
                this.bQ = false;
            }
            this.bM = new Color(Integer.valueOf(this.getParameter(c("eWl]\u0011^^F"))), Integer.valueOf(this.getParameter(c("eWl]\u0011^^S"))), Integer.valueOf(this.getParameter(c("eWl]\u0011^^V"))));
            this.bx = new Color(Integer.valueOf(this.getParameter(c("eWl]\u0001r]x{"))), Integer.valueOf(this.getParameter(c("eWl]\u0001r]xn"))), Integer.valueOf(this.getParameter(c("eWl]\u0001r]xk"))));
            this.bV = this.size().width;
            this.bW = this.size().height;
            if (this.bw == 0) {
                String parameter5 = this.getParameter(c("EWl]=WTgL&"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cg = Integer.valueOf(parameter5);
                if (this.cg < 0) {
                    this.cg = 0;
                }
                String parameter6 = this.getParameter(c("eWl]\u0018D_dh?A"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.c = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("eWl]\u0018D_dz\"U"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bT = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("eWl]\u0001X\\qh?A"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bz = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("eWl]\u0001X\\qz\"U"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bD = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("eWl]\u0001X\\qh<V^q"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bA = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.u);
                this.bv = fontMetrics.stringWidth(this.bt);
                this.bu = fontMetrics.getHeight();
                this.m = fontMetrics.getMaxDescent();
                this.ce = this.bV;
                if (this.cg < this.bu - this.m) {
                    this.cg = this.bu - this.m;
                }
                else if (this.cg > this.bW - this.m) {
                    this.cg = this.bW - this.m;
                }
                if (this.bz != 0) {
                    this.bE = new int[this.bV + 360];
                    this.bF = new int[this.bV + 360];
                    for (int i = 0; i < this.bV + 360; ++i) {
                        this.bE[i] = (int)(this.bz * Math.sin(this.bA * i * 3.141592653589793 / 180.0)) - this.bu - this.m + this.cg;
                        this.bF[i] = this.bE[i] - this.bq;
                    }
                    this.by = 360;
                    this.bB = this.bu + this.m + 1;
                    this.bC = this.bB - 1;
                }
            }
            else {
                if (this.bw == 1) {
                    String parameter11 = this.getParameter(c("EWl]$BBuJ7"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.u);
                    this.G = fontMetrics2.getHeight() + intValue;
                    this.bX = new int[this.V.length];
                    for (int j = 0; j < this.V.length; ++j) {
                        this.bX[j] = (this.bV - fontMetrics2.stringWidth(this.V[j])) / 2;
                    }
                    this.bb = -this.G;
                    return;
                }
                if (this.bw >= 2) {
                    String parameter12 = this.getParameter(c("EWl]?X\\rF<E"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bO = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("EWl]?PJrF<E"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bN = Integer.valueOf(parameter13);
                    this.bf = this.bN - this.bO;
                    this.u = null;
                    this.cj = new Font[this.bf];
                    int bo = this.bO;
                    for (int k = 0; k < this.bf; ++k) {
                        this.cj[k] = new Font(s2, n, bo++);
                    }
                    this.P = this.bV / 2.0f;
                    this.O = this.bW / 2.0f;
                    if (this.bw == 3) {
                        this.ci = this.bf - 1;
                        return;
                    }
                    this.ci = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bw) {
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
        if (this.bS == null) {
            (this.bS = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bS != null && this.bS.isAlive()) {
            this.bS.stop();
        }
        this.bS = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.u);
        this.bb += this.bU;
        if (this.bb > this.bW + this.V.length * this.G) {
            this.bb = -this.G;
        }
        if (this.bQ) {
            for (int i = 0; i < this.V.length; ++i) {
                final String s = this.V[i];
                final int n = this.bX[i];
                final int n2 = this.bW - this.bb + i * this.G;
                graphics.setColor(this.bx);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bM);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bM);
        for (int j = 0; j < this.V.length; ++j) {
            graphics.drawString(this.V[j], this.bX[j], this.bW - this.bb + j * this.G);
        }
    }
    
    public synchronized void f() {
        Thread.yield();
        this.bR.sync();
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
            Thread.sleep(this.Y);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.V[this.ch];
        graphics.setFont(this.cj[this.ci]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cj[this.ci]);
        final int n = (int)(this.P - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.O + fontMetrics.getHeight() / 4.0f);
        if (this.bQ) {
            graphics.setColor(this.bx);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bM);
        graphics.drawString(s, n, n2);
        if (this.bw == 3) {
            this.ci -= this.bU;
            if (this.ci <= 1) {
                this.ci = this.bf - 1;
                ++this.ch;
                if (this.ch >= this.V.length) {
                    this.ch = 0;
                }
            }
        }
        else {
            this.ci += this.bU;
            if (this.ci >= this.bf) {
                this.ci = 0;
                ++this.ch;
                if (this.ch >= this.V.length) {
                    this.ch = 0;
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
                char c = '1';
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
                                c = '2';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\u0014';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = ')';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'R';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
