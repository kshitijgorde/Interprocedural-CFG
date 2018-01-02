import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.Dimension;
import java.awt.Container;
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
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Font;
import java.awt.image.IndexColorModel;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class wormhole extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    int d;
    final String e = "#[)61\u0016\u000b;#t$J;3;Bh0/7\u0001Byr#\u0015\\w;:\u0004";
    int f;
    int g;
    int h;
    byte[] i;
    byte[] j;
    int k;
    byte[] l;
    IndexColorModel m;
    float[] n;
    long o;
    int p;
    Font q;
    int r;
    int s;
    byte[] t;
    int u;
    float v;
    float w;
    int x;
    private Image y;
    int z;
    boolean A;
    String[] B;
    URL C;
    int D;
    int E;
    int F;
    int G;
    int H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P;
    String Q;
    String R;
    int S;
    boolean T;
    int U;
    private Graphics V;
    private Image W;
    boolean X;
    private Image Y;
    int Z;
    int ba;
    int bb;
    anfy bc;
    MemoryImageSource bd;
    int be;
    int bf;
    byte[] bg;
    boolean bh;
    String bi;
    int bj;
    int bk;
    int bl;
    Color bm;
    int bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    int[] bt;
    int[] bu;
    float[] bv;
    float[] bw;
    float[] bx;
    String by;
    Color bz;
    int bA;
    int bB;
    boolean bC;
    boolean bD;
    Toolkit bE;
    Thread bF;
    int bG;
    int bH;
    int bI;
    int bJ;
    int bK;
    int[] bL;
    int bM;
    Lware bN;
    int bO;
    int bP;
    int bQ;
    int bR;
    int bS;
    int bT;
    int bU;
    int bV;
    int bW;
    Font[] bX;
    
    private final void a() {
        while (true) {
            this.showStatus(c("&D7} BY<7;\u0014Ny-#\u0015\u0005842\u001bA8,5LH67t\u0001Y<>=\u0016Xy6=\fNy3:Bc\r\u0017\u0018C"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.Y, this);
        if (this.A) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.X;
        }
        return false;
    }
    
    public void destroy() {
        if (this.Y != null) {
            this.Y.flush();
        }
        this.Y = null;
        if (this.W != null) {
            this.W.flush();
        }
        this.W = null;
        if (this.V != null) {
            this.V.dispose();
        }
        this.V = null;
        System.gc();
    }
    
    public final void c() {
        byte b = 3;
        int n = 1;
        int i = 5;
        final int d = this.D;
        final int bm = this.bM;
        final int u = this.u;
        final int d2 = this.d;
        final byte[] j = this.j;
        while (i < d) {
            int k = 0;
            final float n2 = this.n[this.bO + (d - i) & 0x1FF];
            final float n3 = this.bx[this.bR + (d - i) & 0x1FF];
            while (k < 360) {
                final int n4 = (int)(n2 + i * this.bv[k]);
                final int n5 = (int)(n3 + i * this.bw[k]);
                if (n4 < bm && n4 >= 0 && n5 < u && n5 >= 0) {
                    j[n5 * bm + n4] = b;
                }
                k += d2;
            }
            if (b < 15) {
                ++b;
            }
            i += n;
            if (i % 3 == 0) {
                ++n;
            }
        }
        this.bO = (this.bQ + this.bO & 0xFF);
        this.bR = (this.bU + this.bR & 0xFF);
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
                this.showStatus(c("+F8=1B") + s + c("BE6.t\u0004D,40C"));
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
                            this.bi = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bi = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.B = new String[n3 - 1];
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
                                this.B[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.B[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.B = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.q);
        if (this.a == 0) {
            this.bS = this.bT;
        }
        else {
            this.z += this.bH;
            this.bS = this.bT - (int)Math.abs(this.a * Math.sin(this.z / 90.0 * 3.141592653589793));
        }
        if (this.bo != 0) {
            for (int i = 0; i < this.bJ; ++i) {
                final int n = this.bt[this.bn + i];
                graphics.copyArea(i, n, 1, this.bq, 0, this.be - n);
            }
            if (this.bD) {
                graphics.setColor(this.bm);
                graphics.drawString(this.bi, this.bP + 1, this.be + this.bj + 1);
            }
            graphics.setColor(this.bz);
            graphics.drawString(this.bi, this.bP, this.be + this.bj);
            for (int j = 0; j < this.bJ; ++j) {
                graphics.copyArea(j, this.be, 1, this.br, 0, this.bu[this.bn + j]);
            }
            this.bn -= this.bs;
            if (this.bn < 0) {
                this.bn += 360;
            }
        }
        else {
            if (this.bD) {
                graphics.setColor(this.bm);
                graphics.drawString(this.bi, this.bP + 1, this.bS + 1);
            }
            graphics.setColor(this.bz);
            graphics.drawString(this.bi, this.bP, this.bS);
        }
        this.bP -= this.bI;
        if (this.bP < -this.bk) {
            this.bP = this.bJ;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.Y) {
            if (n == 16) {
                this.X = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bE = this.getToolkit();
        this.by = this.getParameter(c("\u0011_8.!\u0011F*="));
        final String parameter = this.getParameter(c("\u0001Y<>=\u0016X"));
        if (parameter != null) {
            if (!parameter.startsWith(c("#[)61\u0016\u000b;#t$J;3;Bh0/7\u0001Byr#\u0015\\w;:\u0004"))) {
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
            s = c("\u0004B5?");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\u0004B5?")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\u000eD:;8\nD*.")) || s2.equals(c("S\u0019ntdL\u001bwk"))) {
            this.bh = true;
        }
        else {
            if (s2.startsWith(c("\u0015\\.t"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\u0010N>9;\u0006N"));
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
                        if (s5.startsWith(c("\u0015\\.t"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bh = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("\u0010N>6=\f@"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.C = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.C = null;
            }
        }
        if (this.getParameter(c("\u0010N>41\u0015M+;9\u0007")).equalsIgnoreCase(c(";n\n"))) {
            this.T = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("\r]<(=\u000fL"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.Y = this.a(parameter4);
            if (this.Y != null) {
                String parameter5 = this.getParameter(c("\r]<(=\u000fL\u0001"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.Z = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("\r]<(=\u000fL\u0000"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.ba = Integer.valueOf(parameter6);
            }
        }
        this.O = this.getParameter(c("/B7\t\r,h"));
        if (this.O == null) {
            this.O = "10";
        }
        this.H = Integer.valueOf(this.O);
        this.I = this.getParameter(c("\u0006D->1\f"));
        if (this.I == null) {
            this.I = "10";
        }
        this.J = this.getParameter(c("\u0000J:1\u0006"));
        if (this.J == null) {
            this.J = "0";
        }
        this.K = this.getParameter(c("\u0000J:1\u0013"));
        if (this.K == null) {
            this.K = "0";
        }
        this.L = this.getParameter(c("\u0000J:1\u0016"));
        if (this.L == null) {
            this.L = "60";
        }
        this.M = this.getParameter(c("\u001aX)?1\u0006"));
        if (this.M == null) {
            this.M = "3";
        }
        this.N = this.getParameter(c("\u001bX)?1\u0006"));
        if (this.N == null) {
            this.N = "4";
        }
        this.P = this.getParameter(c("\fN>; \u000b]<"));
        this.Q = this.getParameter(c("\u000fN4>1\u000eJ "));
        this.R = this.getParameter(c("\u0012Y05&\u000b_ "));
        this.E = Integer.valueOf(this.Q);
        this.bb = Integer.valueOf(this.R);
        if (this.E < 0) {
            this.E = 0;
        }
        if (this.bb > 10) {
            this.bb = 10;
        }
        else if (this.bb < 1) {
            this.bb = 1;
        }
        int intValue = Integer.valueOf(this.I);
        this.h = Integer.valueOf(this.J);
        this.g = Integer.valueOf(this.K);
        this.f = Integer.valueOf(this.L);
        this.bQ = Integer.valueOf(this.M);
        this.bU = Integer.valueOf(this.N);
        if (this.bQ > 20) {
            this.bQ = 20;
        }
        else if (this.bQ < 0) {
            this.bQ = 0;
        }
        if (this.bU > 20) {
            this.bU = 20;
        }
        else if (this.bU < 0) {
            this.bU = 0;
        }
        if (this.h > 255) {
            this.h = 255;
        }
        else if (this.h < 0) {
            this.h = 0;
        }
        if (this.g > 255) {
            this.g = 255;
        }
        else if (this.g < 0) {
            this.g = 0;
        }
        if (this.f > 255) {
            this.f = 255;
        }
        else if (this.f < 0) {
            this.f = 0;
        }
        if (intValue < 1) {
            intValue = 1;
        }
        if (intValue > 23) {
            intValue = 23;
        }
        if (intValue < 7) {
            this.d = intValue;
        }
        else {
            switch (intValue) {
                case 7: {
                    this.d = 8;
                    break;
                }
                case 8: {
                    this.d = 9;
                    break;
                }
                case 9: {
                    this.d = 10;
                    break;
                }
                case 10: {
                    this.d = 12;
                    break;
                }
                case 11: {
                    this.d = 15;
                    break;
                }
                case 12: {
                    this.d = 18;
                    break;
                }
                case 13: {
                    this.d = 20;
                    break;
                }
                case 14: {
                    this.d = 24;
                    break;
                }
                case 15: {
                    this.d = 30;
                    break;
                }
                case 16: {
                    this.d = 36;
                    break;
                }
                case 17: {
                    this.d = 40;
                    break;
                }
                case 18: {
                    this.d = 45;
                    break;
                }
                case 19: {
                    this.d = 60;
                    break;
                }
                case 20: {
                    this.d = 72;
                    break;
                }
                case 21: {
                    this.d = 90;
                    break;
                }
                case 22: {
                    this.d = 120;
                    break;
                }
                case 23: {
                    this.d = 180;
                    break;
                }
            }
        }
        final Dimension size = this.size();
        this.bM = size.width;
        this.u = size.height;
        this.G = this.bM / 2;
        this.F = this.u / 2;
        this.bO = this.bM / 10;
        this.bR = this.u / 2;
        this.bG = this.bM * this.u;
        if (this.bM > this.u) {
            this.D = this.bM;
        }
        else {
            this.D = this.u;
        }
        this.bg = new byte[16];
        this.t = new byte[16];
        this.i = new byte[16];
        this.bg[0] = (byte)this.h;
        this.t[0] = (byte)this.g;
        this.i[0] = (byte)this.f;
        if (this.P.equalsIgnoreCase(c(";n\n"))) {
            for (int n14 = 1; n14 < 16; ++n14) {
                this.bg[n14] = (byte)(n14 * 16);
                this.t[n14] = (byte)(n14 * 16);
                this.i[n14] = (byte)(n14 * 16);
            }
        }
        else {
            for (int n15 = 15; n15 > 0; --n15) {
                this.bg[n15] = (byte)((16 - n15) * 16);
                this.t[n15] = (byte)((16 - n15) * 16);
                this.i[n15] = (byte)((16 - n15) * 16);
            }
        }
        this.m = new IndexColorModel(4, 16, this.bg, this.t, this.i);
        this.j = new byte[this.bG];
        this.l = new byte[this.bG];
        this.bO = 0;
        while (this.bO < this.bG) {
            this.l[this.bO] = 0;
            ++this.bO;
        }
        this.bv = new float[360];
        this.bw = new float[360];
        this.bx = new float[512];
        this.n = new float[512];
        for (int n16 = 0; n16 < 512; ++n16) {
            this.n[n16] = (float)(this.G + Math.cos(3.141592653589793 * n16 / 128.0) * (this.bM / 4.2));
            this.bx[n16] = (float)(this.F + Math.sin(3.141592653589793 * n16 / 128.0) * (this.u / 3.5));
        }
        for (int n17 = 90; n17 < 450; ++n17) {
            this.bv[n17 - 90] = (float)Math.sin(6.283185307179586 * n17 / 360.0);
        }
        for (int n18 = 0; n18 < 360; ++n18) {
            this.bw[n18] = (float)Math.sin(6.283185307179586 * n18 / 360.0);
        }
        try {
            this.d();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.d();
        }
        this.bf = this.bM;
        this.be = this.u;
        this.g();
        this.W = this.createImage(this.bM, this.u + this.bq);
        this.V = this.W.getGraphics();
        if (!this.bh) {
            (this.bN = new Lware(this.getAppletContext(), new Label(c("5D+7<\rG<z5\u0012[5? BI z\u0012\u0003I05t!B,97\u000b\u000bhcmT\u0004`bz")))).setTitle(c("5D+7<\rG<z\u0015\u0012[5? BI z\u0012\u0003I05t!B,97\u000b"));
            this.bN.hide();
        }
    }
    
    void d() {
        this.bd = new MemoryImageSource(this.bM, this.u, this.m, this.j, 0, this.bM);
        String s;
        try {
            s = System.getProperty(c("\bJ/;z\u0014N+)=\rE"));
        }
        catch (SecurityException ex) {
            s = c("\u0017E2");
        }
        if (!s.startsWith(c("S\u0005i"))) {
            try {
                this.bd.setAnimated(true);
                this.bd.setFullBufferUpdates(true);
                this.y = this.createImage(this.bd);
                this.bd.newPixels();
                this.A = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.A = false;
            }
        }
        if (!this.A) {
            this.bd = null;
            this.bc = new anfy(this.bM, this.u, this.m, this.j, 0, this.bM);
            this.y = this.createImage(this.bc);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bh) {
            this.bN.show();
            this.bN.toFront();
            this.bN.requestFocus();
        }
        else if (this.C != null) {
            if (this.T) {
                this.getAppletContext().showDocument(this.C, this.getParameter(c("\u0010N><&\u0003F<45\u000fN")));
            }
            else {
                this.getAppletContext().showDocument(this.C);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.by);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.y != null) {
            this.V.drawImage(this.y, 0, 0, this);
            if (this.Y != null) {
                this.e();
            }
            if (this.bC) {
                this.b(this.V);
            }
            graphics.drawImage(this.W, 0, 0, this);
        }
    }
    
    public synchronized void e() {
        if (this.c) {
            this.notifyAll();
            while (!this.X) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.X = false;
        }
        this.V.drawImage(this.Y, this.Z, this.ba, this);
    }
    
    public final void f() {
        try {
            if (this.A) {
                this.bd.newPixels();
                return;
            }
            this.bc.startProduction(this.bc.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bF.setPriority(this.bb);
        this.showStatus("");
        System.gc();
        this.o = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.Y != null && !this.c) {
            this.c = this.b();
        }
        if (this.C != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bF != null) {
            try {
                System.arraycopy(this.l, 0, this.j, 0, this.bG);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                this.stop();
            }
            catch (ArrayStoreException ex2) {
                this.stop();
            }
            this.c();
            if (++this.k == this.E) {
                System.gc();
                this.k = 0;
            }
            try {
                this.f();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.V.drawImage(this.y, 0, 0, this);
            if (this.Y != null) {
                this.e();
            }
            if (this.bC) {
                this.b(this.V);
            }
            graphics.drawImage(this.W, 0, 0, this);
            this.h();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void g() {
        this.bC = false;
        final String parameter = this.getParameter(c("\u0016N!.'\u0001Y668"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("\u0016N!. \u001b[<"));
            if (s == null) {
                s = c("\nD+3.\rE-;8");
            }
            if (s.equals(c("\nD+3.\rE-;8"))) {
                this.bl = 0;
            }
            else if (s.equals(c("\u0014N+.=\u0001J5"))) {
                this.bl = 1;
            }
            else if (s.equals(c("\u0018D67=\fL"))) {
                this.bl = 2;
            }
            else if (s.equals(c("\u000bE/ ;\rF043"))) {
                this.bl = 3;
            }
            if (this.bl == 0) {
                this.a(parameter, 0);
                if (this.bi != null) {
                    this.bC = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.B != null) {
                    this.bC = true;
                }
            }
        }
        if (this.bC) {
            String parameter2 = this.getParameter(c("\u0016N!.'\u0012N<>"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bI = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\u0016N!.2\rE-"));
            if (s2 == null) {
                s2 = c("#Y0;8");
            }
            int n = 0;
            if (this.getParameter(c("\u0016N!.6\rG=")).equalsIgnoreCase(c(";n\n"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("\u0016N!.=\u0016J537"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c(";n\n"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("\u0016N!.'\u000bQ<"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.q = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("\u0016N!.'\nJ=5#")).equalsIgnoreCase(c(";n\n"))) {
                this.bD = true;
            }
            else {
                this.bD = false;
            }
            this.bz = new Color(Integer.valueOf(this.getParameter(c("6N!.\u0017\rG\u000b"))), Integer.valueOf(this.getParameter(c("6N!.\u0017\rG\u001e"))), Integer.valueOf(this.getParameter(c("6N!.\u0017\rG\u001b"))));
            this.bm = new Color(Integer.valueOf(this.getParameter(c("6N!.\u0007!D5\b"))), Integer.valueOf(this.getParameter(c("6N!.\u0007!D5\u001d"))), Integer.valueOf(this.getParameter(c("6N!.\u0007!D5\u0018"))));
            this.bJ = this.size().width;
            this.bK = this.size().height;
            if (this.bl == 0) {
                String parameter5 = this.getParameter(c("\u0016N!.;\u0004M*? "));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bT = Integer.valueOf(parameter5);
                if (this.bT < 0) {
                    this.bT = 0;
                }
                String parameter6 = this.getParameter(c("6N!.\u001e\u0017F)\u001b9\u0012"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("6N!.\u001e\u0017F)\t$\u0006"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bH = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("6N!.\u0007\u000bE<\u001b9\u0012"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bo = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("6N!.\u0007\u000bE<\t$\u0006"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bs = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("6N!.\u0007\u000bE<\u001b:\u0005G<"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bp = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.q);
                this.bk = fontMetrics.stringWidth(this.bi);
                this.bj = fontMetrics.getHeight();
                this.p = fontMetrics.getMaxDescent();
                this.bP = this.bJ;
                if (this.bT < this.bj - this.p) {
                    this.bT = this.bj - this.p;
                }
                else if (this.bT > this.bK - this.p) {
                    this.bT = this.bK - this.p;
                }
                if (this.bo != 0) {
                    this.bt = new int[this.bJ + 360];
                    this.bu = new int[this.bJ + 360];
                    for (int i = 0; i < this.bJ + 360; ++i) {
                        this.bt[i] = (int)(this.bo * Math.sin(this.bp * i * 3.141592653589793 / 180.0)) - this.bj - this.p + this.bT;
                        this.bu[i] = this.bt[i] - this.be;
                    }
                    this.bn = 360;
                    this.bq = this.bj + this.p + 1;
                    this.br = this.bq - 1;
                }
            }
            else {
                if (this.bl == 1) {
                    String parameter11 = this.getParameter(c("\u0016N!.\"\u0011[891"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.q);
                    this.r = fontMetrics2.getHeight() + intValue;
                    this.bL = new int[this.B.length];
                    this.x = 0;
                    while (this.x < this.B.length) {
                        this.bL[this.x] = (this.bJ - fontMetrics2.stringWidth(this.B[this.x])) / 2;
                        ++this.x;
                    }
                    this.S = -this.r;
                    return;
                }
                if (this.bl >= 2) {
                    String parameter12 = this.getParameter(c("\u0016N!.9\u000bE?5:\u0016"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bB = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("\u0016N!.9\u0003S?5:\u0016"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bA = Integer.valueOf(parameter13);
                    this.U = this.bA - this.bB;
                    this.q = null;
                    this.bX = new Font[this.U];
                    int bb = this.bB;
                    this.x = 0;
                    while (this.x < this.U) {
                        this.bX[this.x] = new Font(s2, n, bb++);
                        ++this.x;
                    }
                    this.w = this.bJ / 2.0f;
                    this.v = this.bK / 2.0f;
                    if (this.bl == 3) {
                        this.bW = this.U - 1;
                        return;
                    }
                    this.bW = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bl) {
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
        if (this.bF == null) {
            (this.bF = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bF != null && this.bF.isAlive()) {
            this.bF.stop();
        }
        this.bF = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.q);
        this.S += this.bI;
        if (this.S > this.bK + this.B.length * this.r) {
            this.S = -this.r;
        }
        if (this.bD) {
            for (int i = 0; i < this.B.length; ++i) {
                final String s = this.B[i];
                final int n = this.bL[i];
                final int n2 = this.bK - this.S + i * this.r;
                graphics.setColor(this.bm);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bz);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bz);
        for (int j = 0; j < this.B.length; ++j) {
            graphics.drawString(this.B[j], this.bL[j], this.bK - this.S + j * this.r);
        }
    }
    
    public synchronized void h() {
        Thread.yield();
        this.bE.sync();
        final long n = 10L - (System.currentTimeMillis() - this.o);
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
        this.o = System.currentTimeMillis();
        try {
            Thread.sleep(this.H);
        }
        catch (InterruptedException ex3) {}
    }
    
    public wormhole() {
        this.A = false;
        this.bO = 30;
        this.bR = 90;
        this.d = 8;
        this.bQ = 2;
        this.bU = 3;
        this.X = false;
        this.c = false;
        this.bh = false;
        this.T = false;
    }
    
    public void d(final Graphics graphics) {
        final String s = this.B[this.bV];
        graphics.setFont(this.bX[this.bW]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bX[this.bW]);
        final int n = (int)(this.w - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.v + fontMetrics.getHeight() / 4.0f);
        if (this.bD) {
            graphics.setColor(this.bm);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bz);
        graphics.drawString(s, n, n2);
        if (this.bl == 3) {
            this.bW -= this.bI;
            if (this.bW <= 1) {
                this.bW = this.U - 1;
                ++this.bV;
                if (this.bV >= this.B.length) {
                    this.bV = 0;
                }
            }
        }
        else {
            this.bW += this.bI;
            if (this.bW >= this.U) {
                this.bW = 0;
                ++this.bV;
                if (this.bV >= this.B.length) {
                    this.bV = 0;
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
                char c = 'b';
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
                                c = '+';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = 'Y';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'Z';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'T';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
