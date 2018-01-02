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

public class FourRivers extends Applet implements Runnable, d
{
    static final int sKc = 1;
    static final int tKc = 2;
    static final int uKc = 4;
    Image vKc;
    Graphics wKc;
    Dimension size;
    FontMetrics xKc;
    Thread yKc;
    Thread zKc;
    Color AKc;
    Color BKc;
    Color CKc;
    Color DKc;
    Color EKc;
    Color FKc;
    Hashtable GKc;
    Vector HKc;
    boolean IKc;
    int JKc;
    int lKc;
    int mKc;
    int nKc;
    int oKc;
    int KKc;
    long LKc;
    Image MKc;
    Image[] NKc;
    AudioClip OKc;
    AudioClip PKc;
    AudioClip QKc;
    AudioClip RKc;
    AudioClip SKc;
    e jKc;
    f TKc;
    g UKc;
    h VKc;
    i QJc;
    j WKc;
    k XKc;
    k YKc;
    private static String ta = "\u0d86\u0d8e\u0d98\u0d98\u0d8a\u0d8c\u0d8e\u0d8d\u0d82\u0d87\u0d8e";
    private static String ua = "\u0d86\u0d8e\u0d98\u0d98\u0d8a\u0d8c\u0d8e\u0d98\u0db4\u0d8e\u0d85\u0dc5\u0d9f\u0d93\u0d9f";
    private static String va = "\u0d86\u0d8a\u0d9f\u0d88\u0d83\u0dc5\u0d8a\u0d9e";
    private static String wa = "\u0d86\u0d82\u0d98\u0d86\u0d8a\u0d9f\u0d88\u0d83\u0dc5\u0d8a\u0d9e";
    private static String xa = "\u0d98\u0d84\u0d87\u0d9d\u0d8e\u0d8f\u0dc5\u0d8a\u0d9e";
    private static String ya = "\u0d98\u0d8e\u0d87\u0d8e\u0d88\u0d9f\u0dc5\u0d8a\u0d9e";
    private static String za = "\u0d98\u0d82\u0d87\u0d8e\u0d85\u0d9f\u0dc5\u0d8a\u0d9e";
    private static String Aa = "\u0d89\u0d8a\u0d88\u0d80\u0da8\u0d84\u0d87\u0d84\u0d99";
    private static String Ba = "\u0d8d\u0d84\u0d85\u0d9f\u0da8\u0d84\u0d87\u0d84\u0d99";
    private static String Ca = "\u0daa\u0d99\u0d82\u0d8a\u0d87";
    private static String Da = "\u0d8d\u0d84\u0d9e\u0d99\u0d99\u0d82\u0d9d\u0d8e\u0d99\u0d98\u0db4\u0d89\u0d8c\u0dc5\u0d8c\u0d82\u0d8d";
    private static String Ea = "\u0d86\u0d8a\u0d83\u0d81\u0d84\u0d85\u0d8c\u0d8c\u0db4\u0d9f\u0d82\u0d87\u0d8e\u0d98\u0d83\u0d8e\u0d8e\u0d9f\u0dc5\u0d81\u0d9b\u0d8c";
    private static String Fa = "\u0d9f\u0d82\u0d87\u0d8e\u0dc5\u0d8c\u0d82\u0d8d";
    private static String Ga = "\u0d98\u0d84\u0d9e\u0d85\u0d8f\u0dc5\u0d8c\u0d82\u0d8d";
    private static String Ha = "\u0d9f\u0d82\u0d86\u0d8e\u0dcb\u0dd1\u0dcb";
    private static String Ia = "\u0d89\u0d8e\u0d98\u0d9f\u0dcb\u0dd1\u0dcb";
    private static String Ja = "\u0d8d\u0d99\u0d8e\u0d8e\u0dcb\u0dd1\u0dcb";
    private static String Ka = "\u0d8c\u0d82\u0d9d\u0d8e\u0dcb\u0d83\u0d82\u0d85\u0d9f";
    private static String La = "\u0ddb";
    
    public void init() {
        String s = this.getParameter(FourRivers.ta);
        if (s == null) {
            s = FourRivers.ua;
        }
        this.QJc = new i(this.getCodeBase(), s);
        this.WKc = new j(this, this.QJc);
        j.NJc = true;
        this.PKc = this.WKc.b(FourRivers.va);
        this.OKc = this.WKc.b(FourRivers.wa);
        this.QKc = this.WKc.b(FourRivers.xa);
        this.RKc = this.WKc.b(FourRivers.ya);
        if (this.WKc.m()) {
            j.b(this.SKc = this.WKc.b(FourRivers.za));
        }
        this.size = this.getSize();
        this.AKc = this.WKc._(FourRivers.Aa, Color.white);
        this.BKc = this.WKc._(FourRivers.Ba, Color.black);
        this.setBackground(this.AKc);
        this.setFont(new Font(FourRivers.Ca, 1, 12));
    }
    
    private boolean i() {
        if (this.WKc._() && !this.WKc.b(j.MJc)) {
            this.WKc._(true);
            return false;
        }
        if (!this.j()) {
            return false;
        }
        this.MKc = this.getImage(FourRivers.Da);
        this.NKc = this.b(this.getImage(FourRivers.Ea));
        this.vKc = this.createImage(this.size.width, this.size.height);
        (this.wKc = this.vKc.getGraphics()).setFont(new Font(FourRivers.Ca, 0, 12));
        this.jKc = new e(new Rectangle((this.size.width - this.nKc * this.lKc) / 2, 35, this.nKc * this.lKc, this.oKc * this.mKc), this.lKc, this.mKc, this.nKc, this.oKc, this.getImage(FourRivers.Fa));
        this.k();
        for (int i = 0; i < this.nKc * this.oKc; ++i) {
            this.jKc.b(i % this.nKc + 1, i / this.nKc + 1, true);
        }
        this.KKc = this.jKc._();
        this.TKc = new f(new Rectangle(334, 382, 17, 17), this.getImage(FourRivers.Ga));
        this.VKc = new h(new Rectangle(377, 390, 50, 15));
        this.UKc = new g(this);
        this.setBackground(this.AKc);
        this.enableEvents(16L);
        return !this.WKc.c();
    }
    
    private boolean j() {
        (this.GKc = new Hashtable()).put(FourRivers.Aa, this.AKc);
        this.GKc.put(FourRivers.Ba, this.BKc);
        return !this.WKc.c();
    }
    
    private Image getImage(final String s) {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.getImage(this.getCodeBase(), s));
        this.WKc._(vector, 0);
        return vector.elementAt(0);
    }
    
    private Image[] b(final Image image) {
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
                        array[n2] = this.createImage(new FilteredImageSource(source, new CropImageFilter(1 + j * this.lKc, 1 + i * this.mKc, this.lKc - 2, this.mKc - 2)));
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
    
    private boolean k() {
        this.XKc = null;
        this.HKc = new Vector();
        return this.jKc.f();
    }
    
    public void start() {
        this.JKc = 1;
    }
    
    public void stop() {
        if (this.yKc != null && this.yKc.isAlive()) {
            this.yKc.stop();
        }
        this.yKc = null;
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        if (currentThread == this.zKc) {
            this.sleep(200L);
            if (this.k()) {
                j._(this.QKc);
            }
            final int[] array = new int[this.nKc * this.oKc];
            for (int i = 0; i < array.length; ++i) {
                array[i] = i;
            }
            this.b(array);
            for (int j = 0, n = 0; j < array.length; ++j, ++n) {
                this.jKc.b(array[j] % this.nKc + 1, array[j] / this.nKc + 1, true);
                if (n != 0 && n % 3 == 0) {
                    this.sleep(100L);
                    this.repaint();
                }
            }
            this.JKc = 1;
            this.KKc = this.jKc._();
            this.repaint();
            this.zKc = null;
        }
        while (currentThread == this.yKc) {
            this.sleep(80L);
            if (this.JKc == 4) {
                synchronized (this.HKc) {
                    for (int k = 0; k < this.HKc.size(); ++k) {
                        final l m;
                        final l l = m = this.HKc.elementAt(k);
                        --m.FJc;
                        if (l.FJc == 0) {
                            this.HKc.removeElement(l);
                            l.CJc.IJc = false;
                            l.DJc.IJc = false;
                            --k;
                        }
                    }
                }
                // monitorexit(this.HKc)
                this.KKc = this.jKc._();
                this.repaint();
            }
        }
    }
    
    private void b(final int[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            final int _ = m._(i);
            final int n = array[_];
            array[_] = array[i];
            array[i] = n;
        }
    }
    
    public void update(final Graphics graphics) {
        this.wKc.drawImage(this.MKc, 0, 0, this);
        this.TKc._(this.wKc);
        switch (this.JKc) {
            case 1:
            case 2: {
                this.jKc.a(this.wKc, this.NKc);
                break;
            }
            case 4: {
                synchronized (this.HKc) {
                    for (int i = 0; i < this.HKc.size(); ++i) {
                        final l l = this.HKc.elementAt(i);
                        if (!l.EJc.AJc) {
                            this.wKc.setColor(this.CKc);
                            for (int j = 0; j < l.EJc.xJc.size(); ++j) {
                                final int[] array = l.EJc.xJc.elementAt(j);
                                final int min = Math.min(array[0], array[2]);
                                final int max = Math.max(array[0], array[2]);
                                final int min2 = Math.min(array[1], array[3]);
                                final int max2 = Math.max(array[1], array[3]);
                                if (array[1] == array[3]) {
                                    this.wKc.drawLine(min - 1, min2 - 1, max + 1, max2 - 1);
                                    this.wKc.drawLine(min - 1, min2, max + 1, max2);
                                    this.wKc.drawLine(min - 1, min2 + 1, max + 1, max2 + 1);
                                }
                                else {
                                    this.wKc.drawLine(min - 1, min2 - 1, max - 1, max2 + 1);
                                    this.wKc.drawLine(min, min2 - 1, max, max2 + 1);
                                    this.wKc.drawLine(min + 1, min2 - 1, max + 1, max2 + 1);
                                }
                            }
                        }
                    }
                }
                // monitorexit(this.HKc)
                this.jKc.a(this.wKc, this.NKc);
                if (this.XKc != null) {
                    this.wKc.setColor(this.DKc);
                    final Rectangle rectangle = new Rectangle(this.XKc.JJc);
                    j.b(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, this.wKc);
                    j.b(rectangle.x + 1, rectangle.y + 1, rectangle.width - 3, rectangle.height - 3, this.wKc);
                    break;
                }
                break;
            }
        }
        if (this.YKc != null) {
            this.wKc.setColor(this.FKc);
            final Rectangle jJc = this.YKc.JJc;
            j.b(jJc.x, jJc.y, jJc.width - 1, jJc.height - 1, this.wKc);
            j.b(jJc.x + 1, jJc.y + 1, jJc.width - 3, jJc.height - 3, this.wKc);
        }
        this.a();
        graphics.drawImage(this.vKc, 0, 0, this);
        if (this.JKc == 4 && this.jKc.h()) {
            this.JKc = 2;
            this.UKc.stop();
            if (this.zKc == null) {
                (this.zKc = new Thread(this)).start();
            }
        }
    }
    
    private void a() {
        if (this.UKc != null) {
            this.wKc.setColor(this.EKc);
            this.wKc.drawString(FourRivers.Ha + this.UKc.e(), 450, 387);
            this.wKc.drawString(FourRivers.Ia + this.UKc._(this.LKc), 450, 402);
            this.wKc.drawString(FourRivers.Ja + this.a(this.KKc), 380, 387);
            this.wKc.drawString(FourRivers.Ka, 380, 402);
        }
    }
    
    private String a(final int n) {
        return (n < 10) ? (FourRivers.La + n) : String.valueOf(n);
    }
    
    private void a(final Graphics graphics) {
        this.WKc.b(graphics);
        final String b = this.QJc.b();
        graphics.setColor(this.BKc);
        graphics.drawString(b, this.WKc.b(b, true, graphics), this.WKc.b(b, false, graphics));
        if (this.i()) {
            this.IKc = true;
            this.WKc.b(graphics);
            this.repaint();
        }
        else {
            this.WKc.b(graphics);
            String s;
            if (this.WKc.n()) {
                s = j.KJc;
            }
            else {
                s = this.QJc._();
            }
            graphics.setColor(this.BKc);
            graphics.drawString(s, this.WKc.b(s, true, graphics), this.WKc.b(s, false, graphics));
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.IKc) {
            this.a(graphics);
            return;
        }
        this.update(graphics);
        if (this.WKc.c()) {
            this.showStatus(this.QJc.a());
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
                break;
            }
            default: {
                mouseEvent.consume();
                break;
            }
        }
    }
    
    private void _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.VKc.contains(n, n2)) {
            final k a = this.jKc.a();
            this.XKc = a;
            this.YKc = a;
            this.repaint();
            return;
        }
        if (this.TKc.contains(n, n2)) {
            this.TKc.e();
            this.repaint();
            return;
        }
        if (this.JKc == 1 && this.jKc.contains(n, n2)) {
            if (this.yKc == null) {
                (this.yKc = new Thread(this)).start();
            }
            if (this.UKc != null) {
                final long a2 = this.UKc.a();
                if (this.LKc == 0L || a2 < this.LKc) {
                    this.LKc = a2;
                }
                this.UKc.stop();
                this.UKc = null;
            }
            (this.UKc = new g(this)).start();
            this.JKc = 4;
        }
        if (this.JKc == 4 && this.jKc.contains(n, n2)) {
            final k b = this.jKc.b(n, n2);
            if (b != null) {
                if (this.XKc == null) {
                    this.XKc = b;
                    j._(this.RKc);
                }
                else {
                    Label_0411: {
                        if (this.XKc.id == b.id) {
                            final n _;
                            if ((_ = this.jKc._(this.XKc, b)) != null) {
                                final k xKc = this.XKc;
                                final k dJc = b;
                                final l l = new l(this);
                                l.CJc = xKc;
                                l.DJc = dJc;
                                l.EJc = _;
                                if (_.AJc) {
                                    l.FJc = 1;
                                }
                                else {
                                    l.FJc = 5;
                                }
                                this.jKc.b(2);
                                synchronized (this.HKc) {
                                    this.HKc.addElement(l);
                                    if (!this.jKc.h()) {
                                        j._(this.PKc);
                                    }
                                    // monitorexit(this.HKc)
                                    break Label_0411;
                                }
                            }
                            if (this.XKc != b) {
                                j._(this.OKc);
                            }
                        }
                        else {
                            j._(this.OKc);
                        }
                    }
                    final k k = null;
                    this.YKc = k;
                    this.XKc = k;
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
        j.a((Object)this.PKc);
        j.a((Object)this.OKc);
        j.a((Object)this.RKc);
        j.a((Object)this.QKc);
        j.a((Object)this.SKc);
    }
    
    public FourRivers() {
        this.CKc = new Color(12644607);
        this.DKc = new Color(14033256);
        this.EKc = Color.white;
        this.FKc = this.DKc;
        this.JKc = 1;
        this.lKc = 34;
        this.mKc = 44;
        this.nKc = 12;
        this.oKc = 7;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u0deb');
        }
        return new String(array);
    }
    
    static {
        FourRivers.ta = a(FourRivers.ta);
        FourRivers.ua = a(FourRivers.ua);
        FourRivers.va = a(FourRivers.va);
        FourRivers.wa = a(FourRivers.wa);
        FourRivers.xa = a(FourRivers.xa);
        FourRivers.ya = a(FourRivers.ya);
        FourRivers.za = a(FourRivers.za);
        FourRivers.Aa = a(FourRivers.Aa);
        FourRivers.Ba = a(FourRivers.Ba);
        FourRivers.Ca = a(FourRivers.Ca);
        FourRivers.Da = a(FourRivers.Da);
        FourRivers.Ea = a(FourRivers.Ea);
        FourRivers.Fa = a(FourRivers.Fa);
        FourRivers.Ga = a(FourRivers.Ga);
        FourRivers.Ha = a(FourRivers.Ha);
        FourRivers.Ia = a(FourRivers.Ia);
        FourRivers.Ja = a(FourRivers.Ja);
        FourRivers.Ka = a(FourRivers.Ka);
        FourRivers.La = a(FourRivers.La);
    }
}
