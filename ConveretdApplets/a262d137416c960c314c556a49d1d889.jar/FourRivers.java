import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Font;
import java.applet.AudioClip;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FourRivers extends Applet implements Runnable, q
{
    static final int K2 = 1;
    static final int L2 = 2;
    static final int M2 = 4;
    Image N2;
    Graphics O2;
    Dimension size;
    FontMetrics P2;
    Thread Q2;
    Thread R2;
    Color S2;
    Color T2;
    Color U2;
    Color V2;
    Color W2;
    Color X2;
    Hashtable Y2;
    Vector Z2;
    boolean _3;
    int a3;
    int D2;
    int E2;
    int F2;
    int G2;
    int b3;
    long c3;
    Image d3;
    Image[] e3;
    AudioClip f3;
    AudioClip g3;
    AudioClip h3;
    AudioClip i3;
    AudioClip j3;
    r B2;
    s k3;
    t l3;
    u m3;
    v h2;
    abstract n3;
    break o3;
    break p3;
    private static String z = "\uf03c\uf034\uf022\uf022\uf030\uf036\uf034\uf037\uf038\uf03d\uf034";
    private static String A = "\uf03c\uf034\uf022\uf022\uf030\uf036\uf034\uf022\uf00e\uf034\uf03f\uf07f\uf025\uf029\uf025";
    private static String B = "\uf03c\uf030\uf025\uf032\uf039\uf07f\uf030\uf024";
    private static String C = "\uf03c\uf038\uf022\uf03c\uf030\uf025\uf032\uf039\uf07f\uf030\uf024";
    private static String D = "\uf022\uf03e\uf03d\uf027\uf034\uf035\uf07f\uf030\uf024";
    private static String E = "\uf022\uf034\uf03d\uf034\uf032\uf025\uf07f\uf030\uf024";
    private static String F = "\uf022\uf038\uf03d\uf034\uf03f\uf025\uf07f\uf030\uf024";
    private static String G = "\uf033\uf030\uf032\uf03a\uf012\uf03e\uf03d\uf03e\uf023";
    private static String H = "\uf037\uf03e\uf03f\uf025\uf012\uf03e\uf03d\uf03e\uf023";
    private static String I = "\uf010\uf023\uf038\uf030\uf03d";
    private static String J = "\uf030\uf033\uf03e\uf024\uf025\uf07f\uf032\uf03e\uf03c";
    private static String K = "\uf037\uf03e\uf024\uf023\uf023\uf038\uf027\uf034\uf023\uf022\uf00e\uf033\uf036\uf07f\uf036\uf038\uf037";
    private static String L = "\uf03c\uf030\uf039\uf03b\uf03e\uf03f\uf036\uf036\uf00e\uf025\uf038\uf03d\uf034\uf022\uf039\uf034\uf034\uf025\uf07f\uf03b\uf021\uf036";
    private static String M = "\uf025\uf038\uf03d\uf034\uf07f\uf036\uf038\uf037";
    private static String N = "\uf022\uf03e\uf024\uf03f\uf035\uf07f\uf036\uf038\uf037";
    private static String O = "\uf07e";
    private static String P = "\uf025\uf038\uf03c\uf034\uf071\uf06b\uf071";
    private static String Q = "\uf033\uf034\uf022\uf025\uf071\uf06b\uf071";
    private static String R = "\uf037\uf023\uf034\uf034\uf071\uf06b\uf071";
    private static String S = "\uf036\uf038\uf027\uf034\uf071\uf039\uf038\uf03f\uf025";
    private static String T = "\uf061";
    
    public void init() {
        String s = this.getParameter(FourRivers.z);
        if (s == null) {
            s = FourRivers.A;
        }
        this.h2 = new v(this.getCodeBase(), s);
        this.n3 = new abstract(this, this.h2);
        abstract.e2 = true;
        this.g3 = this.n3.a(FourRivers.B);
        this.f3 = this.n3.a(FourRivers.C);
        this.h3 = this.n3.a(FourRivers.D);
        this.i3 = this.n3.a(FourRivers.E);
        if (this.n3.n()) {
            abstract._(this.j3 = this.n3.a(FourRivers.F));
        }
        this.size = this.getSize();
        this.S2 = this.n3.b(FourRivers.G, Color.white);
        this.T2 = this.n3.b(FourRivers.H, Color.black);
        this.setBackground(this.S2);
        this.setFont(new Font(FourRivers.I, 1, 12));
    }
    
    private boolean j() {
        if (this.n3._() && !this.n3._(FourRivers.J)) {
            this.n3.a(true);
            return false;
        }
        if (!this.k()) {
            return false;
        }
        this.d3 = this.a(FourRivers.K);
        this.e3 = this.a(this.a(FourRivers.L));
        this.N2 = this.createImage(this.size.width, this.size.height);
        (this.O2 = this.N2.getGraphics()).setFont(new Font(FourRivers.I, 0, 12));
        this.B2 = new r(new Rectangle((this.size.width - this.F2 * this.D2) / 2, 35, this.F2 * this.D2, this.G2 * this.E2), this.D2, this.E2, this.F2, this.G2, this.a(FourRivers.M));
        this.l();
        for (int i = 0; i < this.F2 * this.G2; ++i) {
            this.B2.a(i % this.F2 + 1, i / this.F2 + 1, true);
        }
        this.b3 = this.B2.b();
        this.k3 = new s(new Rectangle(334, 382, 17, 17), this.a(FourRivers.N));
        this.m3 = new u(new Rectangle(377, 390, 50, 15));
        this.l3 = new t(this);
        this.setBackground(this.S2);
        this.enableEvents(16L);
        return !this.n3.d();
    }
    
    private boolean k() {
        (this.Y2 = new Hashtable()).put(FourRivers.G, this.S2);
        this.Y2.put(FourRivers.H, this.T2);
        return !this.n3.d();
    }
    
    private Image a(final String s) {
        return this.getImage(this.getClass().getResource(FourRivers.O + s));
    }
    
    private Image[] a(final Image image) {
        final ImageProducer source = image.getSource();
        final Image[] array = new Image[42];
        final MediaTracker mediaTracker = new MediaTracker(this);
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < 5) {
            for (int j = 0; j < 9; ++j, ++n) {
                if (n != 35 && n != 43) {
                    if (n != 44) {
                        array[n2] = this.createImage(new FilteredImageSource(source, new CropImageFilter(1 + j * this.D2, 1 + i * this.E2, this.D2 - 2, this.E2 - 2)));
                        mediaTracker.addImage(array[n2++], 0);
                    }
                }
            }
            ++i;
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        return array;
    }
    
    private boolean l() {
        this.o3 = null;
        this.Z2 = new Vector();
        return this.B2.g();
    }
    
    public void start() {
        this.a3 = 1;
    }
    
    public void stop() {
        if (this.Q2 != null && this.Q2.isAlive()) {
            this.Q2.stop();
        }
        this.Q2 = null;
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        if (currentThread == this.R2) {
            this.sleep(200L);
            if (this.l()) {
                abstract.a(this.h3);
            }
            final int[] array = new int[this.F2 * this.G2];
            for (int i = 0; i < array.length; ++i) {
                array[i] = i;
            }
            this.b(array);
            for (int j = 0, n = 0; j < array.length; ++j, ++n) {
                this.B2.a(array[j] % this.F2 + 1, array[j] / this.F2 + 1, true);
                if (n != 0 && n % 3 == 0) {
                    this.sleep(100L);
                    this.repaint();
                }
            }
            this.a3 = 1;
            this.b3 = this.B2.b();
            this.repaint();
            this.R2 = null;
        }
        while (currentThread == this.Q2) {
            this.sleep(80L);
            if (this.a3 == 4) {
                synchronized (this.Z2) {
                    for (int k = 0; k < this.Z2.size(); ++k) {
                        final case case2;
                        final case case1 = case2 = this.Z2.elementAt(k);
                        --case2.X1;
                        if (case1.X1 == 0) {
                            this.Z2.removeElement(case1);
                            case1.U1._2 = false;
                            case1.V1._2 = false;
                            --k;
                        }
                    }
                }
                // monitorexit(this.Z2)
                this.b3 = this.B2.b();
                this.repaint();
            }
        }
    }
    
    private void b(final int[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            final int _ = catch._(i);
            final int n = array[_];
            array[_] = array[i];
            array[i] = n;
        }
    }
    
    public void update(final Graphics graphics) {
        this.O2.drawImage(this.d3, 0, 0, this);
        this.k3.b(this.O2);
        switch (this.a3) {
            case 1:
            case 2: {
                this.B2.a(this.O2, this.e3);
                break;
            }
            case 4: {
                synchronized (this.Z2) {
                    for (int i = 0; i < this.Z2.size(); ++i) {
                        final case case1 = this.Z2.elementAt(i);
                        if (!case1.W1.S1) {
                            this.O2.setColor(this.U2);
                            for (int j = 0; j < case1.W1.P1.size(); ++j) {
                                final int[] array = case1.W1.P1.elementAt(j);
                                final int min = Math.min(array[0], array[2]);
                                final int max = Math.max(array[0], array[2]);
                                final int min2 = Math.min(array[1], array[3]);
                                final int max2 = Math.max(array[1], array[3]);
                                if (array[1] == array[3]) {
                                    this.O2.drawLine(min - 1, min2 - 1, max + 1, max2 - 1);
                                    this.O2.drawLine(min - 1, min2, max + 1, max2);
                                    this.O2.drawLine(min - 1, min2 + 1, max + 1, max2 + 1);
                                }
                                else {
                                    this.O2.drawLine(min - 1, min2 - 1, max - 1, max2 + 1);
                                    this.O2.drawLine(min, min2 - 1, max, max2 + 1);
                                    this.O2.drawLine(min + 1, min2 - 1, max + 1, max2 + 1);
                                }
                            }
                        }
                    }
                }
                // monitorexit(this.Z2)
                this.B2.a(this.O2, this.e3);
                if (this.o3 != null) {
                    this.O2.setColor(this.V2);
                    final Rectangle rectangle = new Rectangle(this.o3.a2);
                    abstract.b(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, this.O2);
                    abstract.b(rectangle.x + 1, rectangle.y + 1, rectangle.width - 3, rectangle.height - 3, this.O2);
                    break;
                }
                break;
            }
        }
        if (this.p3 != null) {
            this.O2.setColor(this.X2);
            final Rectangle a2 = this.p3.a2;
            abstract.b(a2.x, a2.y, a2.width - 1, a2.height - 1, this.O2);
            abstract.b(a2.x + 1, a2.y + 1, a2.width - 3, a2.height - 3, this.O2);
        }
        this.b();
        graphics.drawImage(this.N2, 0, 0, this);
        if (this.a3 == 4 && this.B2.i()) {
            this.a3 = 2;
            this.l3.stop();
            if (this.R2 == null) {
                (this.R2 = new Thread(this)).start();
            }
        }
    }
    
    private void b() {
        if (this.l3 != null) {
            this.O2.setColor(this.W2);
            this.O2.drawString(FourRivers.P + this.l3.k(), 450, 387);
            this.O2.drawString(FourRivers.Q + this.l3.a(this.c3), 450, 402);
            this.O2.drawString(FourRivers.R + this.b(this.b3), 380, 387);
            this.O2.drawString(FourRivers.S, 380, 402);
        }
    }
    
    private String b(final int n) {
        return (n < 10) ? (FourRivers.T + n) : String.valueOf(n);
    }
    
    private void _(final Graphics graphics) {
        this.n3.a(graphics);
        final String _ = this.h2._();
        graphics.setColor(this.T2);
        graphics.drawString(_, this.n3.b(_, true, graphics), this.n3.b(_, false, graphics));
        if (this.j()) {
            this._3 = true;
            this.n3.a(graphics);
            this.repaint();
        }
        else {
            this.n3.a(graphics);
            String s;
            if (this.n3.c()) {
                s = abstract.b2;
            }
            else {
                s = this.h2.a();
            }
            graphics.setColor(this.T2);
            graphics.drawString(s, this.n3.b(s, true, graphics), this.n3.b(s, false, graphics));
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this._3) {
            this._(graphics);
            return;
        }
        this.update(graphics);
        if (this.n3.d()) {
            this.showStatus(this.h2.b());
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
                break;
            }
            default: {
                mouseEvent.consume();
                break;
            }
        }
    }
    
    private void b(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.m3.contains(n, n2)) {
            final break b = this.B2.b();
            this.o3 = b;
            this.p3 = b;
            this.repaint();
            return;
        }
        if (this.k3.contains(n, n2)) {
            this.k3.f();
            this.repaint();
            return;
        }
        if (this.a3 == 1 && this.B2.contains(n, n2)) {
            if (this.Q2 == null) {
                (this.Q2 = new Thread(this)).start();
            }
            if (this.l3 != null) {
                final long a = this.l3.a();
                if (this.c3 == 0L || a < this.c3) {
                    this.c3 = a;
                }
                this.l3.stop();
                this.l3 = null;
            }
            (this.l3 = new t(this)).start();
            this.a3 = 4;
        }
        if (this.a3 == 4 && this.B2.contains(n, n2)) {
            final break a2 = this.B2.a(n, n2);
            if (a2 != null) {
                if (this.o3 == null) {
                    this.o3 = a2;
                    abstract.a(this.i3);
                }
                else {
                    Label_0411: {
                        if (this.o3.id == a2.id) {
                            final class a3;
                            if ((a3 = this.B2.a(this.o3, a2)) != null) {
                                final break o3 = this.o3;
                                final break v1 = a2;
                                final case case1 = new case(this);
                                case1.U1 = o3;
                                case1.V1 = v1;
                                case1.W1 = a3;
                                if (a3.S1) {
                                    case1.X1 = 1;
                                }
                                else {
                                    case1.X1 = 5;
                                }
                                this.B2._(2);
                                synchronized (this.Z2) {
                                    this.Z2.addElement(case1);
                                    if (!this.B2.i()) {
                                        abstract.a(this.g3);
                                    }
                                    // monitorexit(this.Z2)
                                    break Label_0411;
                                }
                            }
                            if (this.o3 != a2) {
                                abstract.a(this.f3);
                            }
                        }
                        else {
                            abstract.a(this.f3);
                        }
                    }
                    final break break1 = null;
                    this.p3 = break1;
                    this.o3 = break1;
                }
            }
        }
        this.repaint();
    }
    
    private void sleep(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void destroy() {
        abstract.a((Object)this.g3);
        abstract.a((Object)this.f3);
        abstract.a((Object)this.i3);
        abstract.a((Object)this.h3);
        abstract.a((Object)this.j3);
    }
    
    public FourRivers() {
        this.U2 = new Color(12644607);
        this.V2 = new Color(14033256);
        this.W2 = Color.white;
        this.X2 = this.V2;
        this.a3 = 1;
        this.D2 = 34;
        this.E2 = 44;
        this.F2 = 12;
        this.G2 = 7;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFF051);
        }
        return new String(array);
    }
    
    static {
        FourRivers.z = _(FourRivers.z);
        FourRivers.A = _(FourRivers.A);
        FourRivers.B = _(FourRivers.B);
        FourRivers.C = _(FourRivers.C);
        FourRivers.D = _(FourRivers.D);
        FourRivers.E = _(FourRivers.E);
        FourRivers.F = _(FourRivers.F);
        FourRivers.G = _(FourRivers.G);
        FourRivers.H = _(FourRivers.H);
        FourRivers.I = _(FourRivers.I);
        FourRivers.J = _(FourRivers.J);
        FourRivers.K = _(FourRivers.K);
        FourRivers.L = _(FourRivers.L);
        FourRivers.M = _(FourRivers.M);
        FourRivers.N = _(FourRivers.N);
        FourRivers.O = _(FourRivers.O);
        FourRivers.P = _(FourRivers.P);
        FourRivers.Q = _(FourRivers.Q);
        FourRivers.R = _(FourRivers.R);
        FourRivers.S = _(FourRivers.S);
        FourRivers.T = _(FourRivers.T);
    }
}
