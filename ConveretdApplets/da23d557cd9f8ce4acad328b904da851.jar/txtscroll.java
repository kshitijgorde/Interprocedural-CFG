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

public class txtscroll extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    final String d = "\tmqI <=c\\e\u000e|cL*h^hP&+t!\r2?j/D+.d";
    Color e;
    boolean f;
    int g;
    long h;
    int i;
    Font j;
    int k;
    private Graphics l;
    int m;
    float n;
    float o;
    private Image p;
    Image q;
    int r;
    boolean s;
    String[] t;
    URL u;
    int v;
    int w;
    String x;
    String y;
    int z;
    boolean A;
    int B;
    private Graphics C;
    private Image D;
    boolean E;
    private Image F;
    int G;
    int H;
    int I;
    int J;
    int K;
    boolean L;
    String M;
    int N;
    int O;
    int P;
    Color Q;
    int R;
    int S;
    int T;
    int U;
    int V;
    int W;
    int[] X;
    int[] Y;
    String Z;
    Color ba;
    int bb;
    int bc;
    boolean bd;
    boolean be;
    Toolkit bf;
    Thread bg;
    int bh;
    int bi;
    int bj;
    int bk;
    int[] bl;
    int bm;
    Lware bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    Font[] bt;
    
    private final void a() {
        while (true) {
            this.showStatus(c("\fro\u00021hodH*>x!R2?3`K#1idD(f~nHe+odA,<n!I,&x!L+hUUh\ti"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.F, this);
        for (int i = 0; i < 3; ++i) {
            this.notifyAll();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        return this.E;
    }
    
    public void destroy() {
        if (this.F != null) {
            this.F.flush();
        }
        this.F = null;
        if (this.D != null) {
            this.D.flush();
        }
        this.D = null;
        if (this.C != null) {
            this.C.dispose();
        }
        this.C = null;
        System.gc();
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
                this.showStatus(c("\u0001p`B h") + s + c("hsnQe.rtK!i"));
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
                            this.M = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.M = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.t = new String[n3 - 1];
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
                                this.t[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.t[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.t = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.j);
        if (this.a == 0) {
            this.bp = this.bq;
        }
        else {
            this.r += this.bh;
            this.bp = this.bq - (int)Math.abs(this.a * Math.sin(this.r / 90.0 * 3.141592653589793));
        }
        if (this.S != 0) {
            for (int i = 0; i < this.bj; ++i) {
                final int n = this.X[this.R + i];
                graphics.copyArea(i, n, 1, this.U, 0, this.J - n);
            }
            if (this.be) {
                graphics.setColor(this.Q);
                graphics.drawString(this.M, this.bo + 1, this.J + this.N + 1);
            }
            graphics.setColor(this.ba);
            graphics.drawString(this.M, this.bo, this.J + this.N);
            for (int j = 0; j < this.bj; ++j) {
                graphics.copyArea(j, this.J, 1, this.V, 0, this.Y[this.R + j]);
            }
            this.R -= this.W;
            if (this.R < 0) {
                this.R += 360;
            }
        }
        else {
            if (this.be) {
                graphics.setColor(this.Q);
                graphics.drawString(this.M, this.bo + 1, this.bp + 1);
            }
            graphics.setColor(this.ba);
            graphics.drawString(this.M, this.bo, this.bp);
        }
        this.bo -= this.bi;
        if (this.bo < -this.O) {
            this.bo = this.bj;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.F) {
            if (n == 16) {
                this.E = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.Z = this.getParameter(c(";i`Q0;prB"));
        final Dimension size = this.size();
        this.K = size.width;
        this.J = size.height;
        this.bm = size.width;
        this.m = size.height;
        final String parameter = this.getParameter(c("+odA,<n"));
        if (parameter != null) {
            if (!parameter.startsWith(c("\tmqI <=c\\e\u000e|cL*h^hP&+t!\r2?j/D+.d"))) {
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
            s = c(".tm@");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c(".tm@")) || s2.length() == 0 || s2.equalsIgnoreCase(c("$rbD) rrQ")) || s2.equals(c("y/6\u000buf-/\u0014"))) {
            this.L = true;
        }
        else {
            if (s2.startsWith(c("?jv\u000b"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c(":xfF*,x"));
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
                        if (s5.startsWith(c("?jv\u000b"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.L = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c(":xfI,&v"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.u = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.u = null;
            }
        }
        if (this.getParameter(c(":xfK ?{sD(-")).equalsIgnoreCase(c("\u0011XR"))) {
            this.A = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("'kdW,%z"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.F = this.a(parameter4);
            if (this.F != null) {
                String parameter5 = this.getParameter(c("'kdW,%zY"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.G = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("'kdW,%zX"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.H = Integer.valueOf(parameter6);
            }
        }
        final String parameter7 = this.getParameter(c("*|bN,%|f@"));
        if (parameter7 == null || parameter7.equalsIgnoreCase("NO")) {
            this.f = false;
        }
        else {
            this.f = true;
            this.q = this.a(parameter7);
            if (this.q == null) {
                this.f = false;
            }
        }
        this.e = new Color(Integer.valueOf(this.getParameter(c("*|bN\u0017"))), Integer.valueOf(this.getParameter(c("*|bN\u0002"))), Integer.valueOf(this.getParameter(c("*|bN\u0007"))));
        this.x = this.getParameter(c("%xlA $|x"));
        this.y = this.getParameter(c("8ohJ7!ix"));
        this.v = Integer.valueOf(this.x);
        this.I = Integer.valueOf(this.y);
        this.y = null;
        this.y = this.getParameter(c("\u0005tov\u001c\u0006^"));
        this.w = Integer.valueOf(this.y);
        if (this.v < 0) {
            this.v = 0;
        }
        if (this.I > 10) {
            this.I = 10;
        }
        else if (this.I < 1) {
            this.I = 1;
        }
        this.d();
        if (this.S != 0) {
            this.D = this.createImage(this.bm, this.m + this.U);
        }
        else {
            this.D = this.createImage(this.bm, this.m);
        }
        this.C = this.D.getGraphics();
        if (!this.L) {
            (this.bn = new Lware(this.getAppletContext(), new Label(c("\u001cxyQ\u0016+onI)h|qU)-i!G<h[`G,'=BL0+~h\u0005tq$9\n|q3")))).setTitle(c("\u001cxyQ\u0016+onI)h\\qU)-i!G<h[`G,'=BL0+~h"));
            this.bn.hide();
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.L) {
            this.bn.show();
            this.bn.toFront();
            this.bn.requestFocus();
        }
        else if (this.u != null) {
            if (this.A) {
                this.getAppletContext().showDocument(this.u, this.getParameter(c(":xfC7)pdK$%x")));
            }
            else {
                this.getAppletContext().showDocument(this.u);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.Z);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.D, 0, 0, this);
    }
    
    public synchronized void c() {
        if (this.c) {
            this.notifyAll();
            while (!this.E) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.E = false;
        }
        this.C.drawImage(this.F, this.G, this.H, this);
    }
    
    public void run() {
        this.bf = this.getToolkit();
        this.bg.setPriority(this.I);
        this.showStatus("");
        System.gc();
        this.h = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.F != null && !this.c) {
            this.c = this.b();
        }
        if (this.u != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bg != null) {
            if (++this.g == this.v) {
                System.gc();
                this.g = 0;
            }
            if (!this.f) {
                this.C.setColor(this.e);
                this.C.fillRect(0, 0, this.bm, this.m);
            }
            else {
                this.C.drawImage(this.q, 0, 0, this);
            }
            this.b(this.C);
            if (this.F != null) {
                this.c();
            }
            graphics.drawImage(this.D, 0, 0, this);
            this.e();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void d() {
        this.bd = false;
        final String parameter = this.getParameter(c("<xyQ6+onI)"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("<xyQ11md"));
            if (s == null) {
                s = c(" rsL?'suD)");
            }
            if (s.equals(c(" rsL?'suD)"))) {
                this.P = 0;
            }
            else if (s.equals(c(">xsQ,+|m"))) {
                this.P = 1;
            }
            else if (s.equals(c("2rnH,&z"))) {
                this.P = 2;
            }
            else if (s.equals(c("!sw_*'phK\""))) {
                this.P = 3;
            }
            if (this.P == 0) {
                this.a(parameter, 0);
                if (this.M != null) {
                    this.bd = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.t != null) {
                    this.bd = true;
                }
            }
        }
        if (this.bd) {
            String parameter2 = this.getParameter(c("<xyQ68xdA"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bi = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("<xyQ#'su"));
            if (s2 == null) {
                s2 = c("\tohD)");
            }
            int n = 0;
            if (this.getParameter(c("<xyQ''qe")).equalsIgnoreCase(c("\u0011XR"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("<xyQ,<|mL&"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("\u0011XR"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("<xyQ6!gd"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.j = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("<xyQ6 |eJ2")).equalsIgnoreCase(c("\u0011XR"))) {
                this.be = true;
            }
            else {
                this.be = false;
            }
            this.ba = new Color(Integer.valueOf(this.getParameter(c("\u001cxyQ\u0006'qS"))), Integer.valueOf(this.getParameter(c("\u001cxyQ\u0006'qF"))), Integer.valueOf(this.getParameter(c("\u001cxyQ\u0006'qC"))));
            this.Q = new Color(Integer.valueOf(this.getParameter(c("\u001cxyQ\u0016\u000brmw"))), Integer.valueOf(this.getParameter(c("\u001cxyQ\u0016\u000brmb"))), Integer.valueOf(this.getParameter(c("\u001cxyQ\u0016\u000brmg"))));
            this.bj = this.size().width;
            this.bk = this.size().height;
            if (this.P == 0) {
                String parameter5 = this.getParameter(c("<xyQ*.{r@1"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bq = Integer.valueOf(parameter5);
                if (this.bq < 0) {
                    this.bq = 0;
                }
                String parameter6 = this.getParameter(c("\u001cxyQ\u000f=pqd(8"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("\u001cxyQ\u000f=pqv5,"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bh = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("\u001cxyQ\u0016!sdd(8"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.S = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("\u001cxyQ\u0016!sdv5,"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.W = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("\u001cxyQ\u0016!sdd+/qd"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.T = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.j);
                this.O = fontMetrics.stringWidth(this.M);
                this.N = fontMetrics.getHeight();
                this.i = fontMetrics.getMaxDescent();
                this.bo = this.bj;
                if (this.bq < this.N - this.i) {
                    this.bq = this.N - this.i;
                }
                else if (this.bq > this.bk - this.i) {
                    this.bq = this.bk - this.i;
                }
                if (this.S != 0) {
                    this.X = new int[this.bj + 360];
                    this.Y = new int[this.bj + 360];
                    for (int i = 0; i < this.bj + 360; ++i) {
                        this.X[i] = (int)(this.S * Math.sin(this.T * i * 0.017453292519943295) - this.N - this.i + this.bq);
                        this.Y[i] = this.X[i] - this.J;
                    }
                    this.R = 360;
                    this.U = this.N + this.i + 1;
                    this.V = this.U - 1;
                }
            }
            else {
                if (this.P == 1) {
                    String parameter11 = this.getParameter(c("<xyQ3;m`F "));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.j);
                    this.k = fontMetrics2.getHeight() + intValue;
                    this.bl = new int[this.t.length];
                    for (int j = 0; j < this.t.length; ++j) {
                        this.bl[j] = (this.bj - fontMetrics2.stringWidth(this.t[j])) / 2;
                    }
                    this.z = -this.k;
                    return;
                }
                if (this.P >= 2) {
                    String parameter12 = this.getParameter(c("<xyQ(!sgJ+<"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bc = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("<xyQ()egJ+<"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bb = Integer.valueOf(parameter13);
                    this.B = this.bb - this.bc;
                    this.j = null;
                    this.bt = new Font[this.B];
                    int bc = this.bc;
                    for (int k = 0; k < this.B; ++k) {
                        this.bt[k] = new Font(s2, n, bc++);
                    }
                    this.o = this.bj / 2.0f;
                    this.n = this.bk / 2.0f;
                    if (this.P == 3) {
                        this.bs = this.B - 1;
                        return;
                    }
                    this.bs = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.P) {
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
        if (this.bg == null) {
            (this.bg = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bg != null && this.bg.isAlive()) {
            this.bg.stop();
        }
        this.bg = null;
    }
    
    public txtscroll() {
        this.s = false;
        this.E = false;
        this.c = false;
        this.L = false;
        this.A = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.j);
        this.z += this.bi;
        if (this.z > this.bk + this.t.length * this.k) {
            this.z = -this.k;
        }
        if (this.be) {
            for (int i = 0; i < this.t.length; ++i) {
                final String s = this.t[i];
                final int n = this.bl[i];
                final int n2 = this.bk - this.z + i * this.k;
                graphics.setColor(this.Q);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.ba);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.ba);
        for (int j = 0; j < this.t.length; ++j) {
            graphics.drawString(this.t[j], this.bl[j], this.bk - this.z + j * this.k);
        }
    }
    
    public synchronized void e() {
        Thread.yield();
        this.bf.sync();
        final long n = 10L - (System.currentTimeMillis() - this.h);
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
        this.h = System.currentTimeMillis();
        try {
            Thread.sleep(this.w);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.t[this.br];
        graphics.setFont(this.bt[this.bs]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bt[this.bs]);
        final int n = (int)(this.o - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.n + fontMetrics.getHeight() / 4.0f);
        if (this.be) {
            graphics.setColor(this.Q);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.ba);
        graphics.drawString(s, n, n2);
        if (this.P == 3) {
            this.bs -= this.bi;
            if (this.bs <= 1) {
                this.bs = this.B - 1;
                ++this.br;
                if (this.br >= this.t.length) {
                    this.br = 0;
                }
            }
        }
        else {
            this.bs += this.bi;
            if (this.bs >= this.B) {
                this.bs = 0;
                ++this.br;
                if (this.br >= this.t.length) {
                    this.br = 0;
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
                char c = 'H';
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
                                c = '\u001d';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\u0001';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '%';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'E';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
