import java.awt.image.PixelGrabber;
import java.awt.FontMetrics;
import java.awt.Font;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Frame;
import java.util.Random;
import java.awt.Image;
import java.util.Observer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class xatshowApplet extends Applet implements Observer
{
    static final int a = 4096;
    static final boolean[] b;
    static final int c = 0;
    static final int d = 1;
    static final int e = 2;
    static final int f = 3;
    static final int g = 0;
    static final int h = 9;
    static final int i = 12;
    static final int j = 13;
    static final int k = 4;
    static final int l = 6;
    static final int m = 5;
    static final int n = 2;
    static final int o = 14;
    static final int p = 3;
    static final int q = 7;
    static final int r = 8;
    static final int s = 15;
    static final int t = 15;
    static final int u = 62460;
    static final int v = 0;
    static final int w = 1;
    static final int x = 2;
    int y;
    int z;
    int A;
    int B;
    String C;
    int D;
    int E;
    String[] F;
    int[] G;
    int[] H;
    int[] I;
    boolean J;
    int K;
    int L;
    Image M;
    Image N;
    int[] O;
    int[] P;
    Image Q;
    b R;
    int S;
    int T;
    int U;
    long V;
    int W;
    int X;
    int Y;
    private Random Z;
    private Frame ba;
    static /* synthetic */ Class bb;
    
    public xatshowApplet() {
        this.J = true;
        this.K = 3;
        this.L = 0;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0L;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
    }
    
    public void start() {
        super.start();
        this.J = true;
        this.repaint();
    }
    
    public void stop() {
        super.stop();
        this.J = false;
    }
    
    public void init() {
        final int i = a.c;
        Container container = super.getParent();
        boolean b = false;
        Label_0037: {
            while (true) {
                Label_0018: {
                    if (i == 0) {
                        break Label_0018;
                    }
                    container = container.getParent();
                }
                if (container != null) {
                    b = (container instanceof Frame);
                    if (i != 0) {
                        break Label_0037;
                    }
                    if (!b) {
                        continue;
                    }
                }
                break;
            }
            final boolean b2 = container instanceof Frame;
        }
        if (b) {
            this.ba = (Frame)container;
        }
        this.Z = new Random();
        this.a();
        Label_0111: {
            if (this.E == 0) {
                this.U = 0;
                if (i == 0) {
                    break Label_0111;
                }
            }
            int u = 0;
        Label_0079:
            while (true) {
                xatshowApplet xatshowApplet = this;
                do {
                    u = (xatshowApplet.Z.nextInt() >>> 1) % this.L;
                    if (u == this.U) {
                        continue Label_0079;
                    }
                    xatshowApplet = this;
                } while (i != 0);
                break;
            }
            this.U = u;
        }
        Label_0206: {
            if (this.L != 0) {
                this.N = this.a(this.F[this.U]);
                if (i == 0) {
                    break Label_0206;
                }
            }
            this.K = 0;
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            this.N = this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), d("yJ\u001b`x\rC\fw=CM^{pLE\u001ba=YM^vt^R\u0012sd"), this.S, this.T);
        }
        this.P = this.a(this.N, this.S, this.T);
        this.M = this.createImage(this.S, this.T);
        final Graphics graphics = this.M.getGraphics();
        graphics.setColor(new Color(this.D));
        graphics.fillRect(0, 0, this.S, this.T);
        this.O = this.a(this.M, this.S, this.T);
        this.Q = this.M;
        this.repaint();
        this.validate();
        this.V = System.currentTimeMillis();
        this.a(this.U, this.O, this.P);
    }
    
    public void update(final Observable observable, final Object o) {
        final int c = a.c;
        if (!this.J) {
            return;
        }
        if (this.L == 0) {
            return;
        }
        Label_0111: {
            if (this.E == 1) {
            Block_4:
                while (true) {
                    final int i = (this.Z.nextInt() >>> 1) % this.L;
                    while (i != this.U) {
                        this.U = i;
                        if (c == 0) {
                            break Block_4;
                        }
                    }
                }
                if (c == 0) {
                    break Label_0111;
                }
            }
            ++this.U;
            if (this.U >= this.L) {
                this.U = 0;
                if (this.y != 0) {
                    this.G[this.U] = Integer.MAX_VALUE;
                }
            }
        }
        this.M = this.N;
        this.O = this.P;
        this.N = this.a(this.F[this.U]);
        this.P = this.a(this.N, this.S, this.T);
        final long n = System.currentTimeMillis() - this.V;
        if (n < this.G[this.U]) {
            System.gc();
            try {
                Thread.currentThread();
                Thread.sleep(this.G[this.U] - n);
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        this.V = System.currentTimeMillis();
        this.Y = this.U;
        this.a(this.U, this.O, this.P);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Q != null) {
            graphics.drawImage(this.Q, 0, 0, this);
        }
    }
    
    private void a(final int n, final int[] array, final int[] array2) {
        final int c = a.c;
        this.X = ((this.X | ~this.H[n]) & 0xF3FC);
        if (this.X == 62460) {
            this.X = (~this.H[n] & 0xF3FC);
        }
        this.W = 0;
        int n3;
        final int n2 = n3 = (this.Z.nextInt() >>> 1) % 16;
        int w = 0;
    Label_0124_Outer:
        while (true) {
            while (true) {
                Label_0139: {
                    if ((1 << n3 & (this.X | 0xFFFF0C03)) == 0x0) {
                        this.X |= 1 << n3;
                        this.W = 1 << n3;
                        if (c == 0) {
                            break Label_0139;
                        }
                    }
                    final int n4;
                    n3 = (n4 = (n3 + 1) % 16);
                    if (w != n2) {
                        if (n3 == n3) {
                            continue Label_0124_Outer;
                        }
                    }
                }
                w = this.W;
                if (c == 0) {
                    break;
                }
                continue;
            }
        }
        Label_0768: {
            switch (w) {
                case 0: {
                    this.R = new i(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 4: {
                    this.R = new d(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 8: {
                    this.R = new h(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 16: {
                    this.R = new k(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 32: {
                    this.R = new c(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 64: {
                    this.R = new m(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 128: {
                    this.R = new n(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 256: {
                    this.R = new o(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 512: {
                    this.R = new e(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 4096: {
                    this.R = new g(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 8192: {
                    this.R = new j(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 16384: {
                    this.R = new f(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    if (c != 0) {
                        break Label_0768;
                    }
                    break;
                }
                case 32768: {
                    this.R = new l(this.O, this.P, this.S, this.T, this.D, this.I[n]);
                    break;
                }
            }
        }
        this.R.addObserver(this);
        this.prepareImage(this.Q = this.createImage(this.R), this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int c = a.c;
        final String s = new String();
        Label_0268: {
            if (this.C != null) {
                if ((this.K == 2 || this.K == 3) && this.C.compareTo(d("CM\u0010w")) == 0) {
                    return true;
                }
                if (this.K == 3) {
                    final int index = this.C.indexOf(42, 0);
                    if (index != -1) {
                        String s2 = new String();
                        final char[] charArray = this.C.toCharArray();
                        int n3 = 0;
                        int n4;
                        while (true) {
                            while (true) {
                                Label_0134: {
                                    if (c == 0) {
                                        break Label_0134;
                                    }
                                    s2 += charArray[n3];
                                    ++n3;
                                }
                                if (n3 < index) {
                                    continue;
                                }
                                break;
                            }
                            s2 += String.valueOf(this.Y);
                            n4 = index + 1;
                            if (c != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            while (true) {
                                Label_0212: {
                                    if (c == 0) {
                                        break Label_0212;
                                    }
                                    s2 += charArray[n4];
                                    ++n4;
                                }
                                if (n4 < this.C.length()) {
                                    continue;
                                }
                                break;
                            }
                            final String s3 = s2;
                            if (c != 0) {
                                continue;
                            }
                            break;
                        }
                        if (c == 0) {
                            break Label_0268;
                        }
                    }
                    final String s3 = this.C;
                    if (c == 0) {
                        break Label_0268;
                    }
                }
                final String s3 = d("EV\nb'\u0002\r\tej\u0003Z\u001ff3NM\u0013=eLV\rzrZ\rAexO");
                if (c == 0) {
                    break Label_0268;
                }
            }
            final String s3 = d("EV\nb'\u0002\r\tej\u0003Z\u001ff3NM\u0013=eLV\rzrZ\rAexO");
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s3));
            }
            catch (Exception ex) {
                return true;
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        final int c = a.c;
        if (this.C != null) {
            if ((this.K == 2 || this.K == 3) && this.C.compareTo(d("CM\u0010w")) == 0) {
                return true;
            }
            if (this.K == 3) {
                final int index = this.C.indexOf(42, 0);
                Label_0239: {
                    if (index != -1) {
                        String s = new String();
                        final char[] charArray = this.C.toCharArray();
                        int n3 = 0;
                        int n4;
                        while (true) {
                            while (true) {
                                Label_0125: {
                                    if (c == 0) {
                                        break Label_0125;
                                    }
                                    s += charArray[n3];
                                    ++n3;
                                }
                                if (n3 < index) {
                                    continue;
                                }
                                break;
                            }
                            s += String.valueOf(this.Y);
                            n4 = index + 1;
                            if (c != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            while (true) {
                                Label_0203: {
                                    if (c == 0) {
                                        break Label_0203;
                                    }
                                    s += charArray[n4];
                                    ++n4;
                                }
                                if (n4 < this.C.length()) {
                                    continue;
                                }
                                break;
                            }
                            this.showStatus(s);
                            if (c != 0) {
                                continue;
                            }
                            break;
                        }
                        if (c == 0) {
                            break Label_0239;
                        }
                    }
                    this.showStatus(this.C);
                }
                this.ba.setCursor(12);
                if (c == 0) {
                    return true;
                }
            }
            this.showStatus(d("EV\nb'\u0002\r\tej\u0003Z\u001ff3NM\u0013=eLV\rzrZ\r"));
            this.ba.setCursor(12);
            if (c == 0) {
                return true;
            }
        }
        this.showStatus(d("EV\nb'\u0002\r\tej\u0003Z\u001ff3NM\u0013=eLV\rzrZ\r"));
        this.ba.setCursor(12);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.K == 3) {
            if (this.C == null) {
                return true;
            }
            this.showStatus("");
            this.ba.setCursor(0);
            if (a.c == 0) {
                return true;
            }
        }
        this.showStatus("");
        this.ba.setCursor(0);
        return true;
    }
    
    private void a() {
        final int c = a.c;
        Label_0040: {
            if (this.getParameter(d("_W\u0010}sNG")) == null || this.K != 3) {
                this.y = 0;
                if (c == 0) {
                    break Label_0040;
                }
            }
            this.y = 1;
        }
        final String parameter = this.getParameter(d("YK\u0013w"));
        Label_0073: {
            if (parameter == null) {
                this.z = 3000;
                if (c == 0) {
                    break Label_0073;
                }
            }
            this.z = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter(d("YP\u001f|nDV\u0017}s"));
        Label_0105: {
            if (parameter2 == null) {
                this.A = 62460;
                if (c == 0) {
                    break Label_0105;
                }
            }
            this.A = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter(d("YP\u001f|nDV\u0017}sYK\u0013w"));
        Label_0138: {
            if (parameter3 == null) {
                this.B = 2000;
                if (c == 0) {
                    break Label_0138;
                }
            }
            this.B = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter(d("NM\u0012}o"));
        Label_0169: {
            if (parameter4 == null) {
                this.D = 0;
                if (c == 0) {
                    break Label_0169;
                }
            }
            this.D = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter(d("BP\u001awo"));
        Label_0220: {
            if (parameter5 == null) {
                this.E = 0;
                if (c == 0) {
                    break Label_0220;
                }
            }
            this.E = Integer.parseInt(parameter5);
            if (this.E < 0 || this.E > 2) {
                this.E = 0;
            }
        }
        this.C = this.getParameter(d("AK\u0010y"));
        this.L = 0;
        while (this.getParameter("i" + this.L) != null) {
            ++this.L;
            if (this.L >= 4096) {
                break;
            }
        }
        this.F = new String[this.L];
        this.G = new int[this.L];
        this.H = new int[this.L];
        this.I = new int[this.L];
        int l = 0;
        while (true) {
        Label_0562:
            while (true) {
                Label_0554: {
                    if (c == 0) {
                        break Label_0554;
                    }
                    this.F[l] = this.getParameter("i" + l);
                    if (this.F[l] == null) {
                        this.L = l;
                        if (c == 0) {
                            break Label_0562;
                        }
                    }
                    final String parameter6 = this.getParameter("f" + l);
                    Label_0447: {
                        if (parameter6 == null) {
                            this.G[(l + 1) % this.L] = this.z;
                            if (c == 0) {
                                break Label_0447;
                            }
                        }
                        this.G[(l + 1) % this.L] = Integer.parseInt(parameter6);
                    }
                    final String parameter7 = this.getParameter("t" + l);
                    Label_0499: {
                        if (parameter7 == null) {
                            this.H[l] = this.A;
                            if (c == 0) {
                                break Label_0499;
                            }
                        }
                        this.H[l] = Integer.parseInt(parameter7);
                    }
                    final String parameter8 = this.getParameter("d" + l);
                    Label_0551: {
                        if (parameter8 == null) {
                            this.I[l] = this.B;
                            if (c == 0) {
                                break Label_0551;
                            }
                        }
                        this.I[l] = Integer.parseInt(parameter8);
                    }
                    ++l;
                }
                if (l < this.L) {
                    continue;
                }
                break;
            }
            if (c == 0) {
                return;
            }
            continue;
        }
    }
    
    private Image a(final String s) {
        final int c = a.c;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Image image;
        try {
            final InputStream resourceAsStream = ((xatshowApplet.bb == null) ? (xatshowApplet.bb = c(d("UC\nauBU?bmAG\n"))) : xatshowApplet.bb).getResourceAsStream(s);
            if (resourceAsStream == null) {
                return this.b(s);
            }
            ByteArrayOutputStream byteArrayOutputStream2 = null;
            while (true) {
                final int read;
                if ((read = resourceAsStream.read()) < 0) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    if (c == 0) {
                        break;
                    }
                }
                byteArrayOutputStream2.write(read);
            }
            final byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (byteArray == null) {
                throw new Exception(d("nC\u0010|rY\u0002\u001d`xLV\u001b2\u007fTV\u001b2|_P\u001fk"));
            }
            image = Toolkit.getDefaultToolkit().createImage(byteArray);
            if (image == null) {
                throw new Exception(d("nC\u0010|rY\u0002\u001d`xLV\u001b2t@C\u0019w"));
            }
        }
        catch (Exception ex) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            String s2 = ex.getMessage();
            if (s2 == null) {
                s2 = d("nC\u0010|rY\u0002\u001a}jCN\u0011sy\rK\u0013szH");
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), s2, this.S, this.T);
        }
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex2) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            String s3 = ex2.getMessage();
            if (s3 == null) {
                s3 = d("dO\u001fux\rD\u001f{qHF^vh_K\u0010u=IM\t|qBC\u001a");
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), s3, this.S, this.T);
        }
        final byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        final long n = (byteArray2[10] & 0xFF) * 16777216L + ((byteArray2[7] & 0xFF) + ((byteArray2[8] & 0xFF) << 8) + ((byteArray2[9] & 0xFF) << 16));
        final byte[] array = byteArray2;
        final int n2 = 7;
        final byte[] array2 = byteArray2;
        final int n3 = 8;
        final byte[] array3 = byteArray2;
        final int n4 = 9;
        final byte[] array4 = byteArray2;
        final int n5 = 10;
        final byte b = 32;
        array3[n4] = (array4[n5] = b);
        array[n2] = (array2[n3] = b);
        final Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(byteArray2);
        final long value = adler32.getValue();
        if (!this.prepareImage(image, this)) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), d("dO\u001fux\rD\u0011`pLV^{n\rK\u0010d|AK\u001a"), this.S, this.T);
        }
        if (this.S > 0 && this.T > 0 && (image.getWidth(this) != this.S || image.getHeight(this) != this.T)) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), d("dO\u001fux\rJ\u001fa=YJ\u001b2j_M\u0010u=IK\u0013ws^K\u0011|n"), this.S, this.T);
        }
        this.S = image.getWidth(this);
        this.T = image.getHeight(this);
        if (value != n) {
            this.K = 0;
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), d("dO\u001fux\rU\u001fa=CM\n2~_G\u001ffxI\u0002\u001ck=UC\nauBU"), this.S, this.T);
        }
        final int k = (byteArray2[6] & 0xFF) - 48;
        if (this.K < 0 || this.K > 3) {
            this.K = 0;
            if (c == 0) {
                return image;
            }
        }
        if (k < this.K) {
            this.K = k;
        }
        return image;
    }
    
    private Image b(final String s) {
        final Image image = this.getImage(this.getDocumentBase(), s);
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            String s2 = ex.getMessage();
            if (s2 == null) {
                s2 = d("dO\u001fux\rD\u001f{qHF^vh_K\u0010u=IM\t|qBC\u001a");
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), s2, this.S, this.T);
        }
        if (!this.prepareImage(image, this)) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), d("dO\u001fux\rL\u0011f=KM\u000b|y\u0003"), this.S, this.T);
        }
        if (this.S > 0 && this.T > 0 && (image.getWidth(this) != this.S || image.getHeight(this) != this.T)) {
            if (this.S == 0) {
                this.S = this.size().width;
            }
            if (this.T == 0) {
                this.T = this.size().height;
            }
            return this.a(d("UC\nauBU?bmAG\n2X_P\u0011`'"), d("dO\u001fux\rJ\u001fa=YJ\u001b2j_M\u0010u=IK\u0013ws^K\u0011|n"), this.S, this.T);
        }
        this.S = image.getWidth(this);
        this.T = image.getHeight(this);
        return image;
    }
    
    private Image a(final String s, final String s2, final int n, final int n2) {
        final Image image = this.createImage(n, n2);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n, n2);
        graphics.setFont(new Font(d("nM\u000b`tHP"), 0, 12));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.white);
        graphics.drawString(s, (n - fontMetrics.stringWidth(s)) / 2, 3 * n2 / 8);
        graphics.drawString(s2, (n - fontMetrics.stringWidth(s2)) / 2, 5 * n2 / 8);
        this.prepareImage(image, this);
        return image;
    }
    
    private int[] a(final Image image, final int n, final int n2) {
        final int[] array = new int[n * n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return null;
        }
        return array;
    }
    
    static /* synthetic */ Class c(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        b = new boolean[] { false, false, false, false, true, false, true, false, false, true, false, false, true, true, false, false };
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0094: {
                if (length > 1) {
                    break Label_0094;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '-';
                            break;
                        }
                        case 1: {
                            c2 = '\"';
                            break;
                        }
                        case 2: {
                            c2 = '~';
                            break;
                        }
                        case 3: {
                            c2 = '\u0012';
                            break;
                        }
                        default: {
                            c2 = '\u001d';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
