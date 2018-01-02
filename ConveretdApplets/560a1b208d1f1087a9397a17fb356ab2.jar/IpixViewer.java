import java.awt.Color;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IpixViewer extends Applet implements Runnable
{
    protected static final int a = -1;
    protected static final int b = -2;
    protected static final int c = 0;
    protected static final int d = 1;
    protected static final int e = 2;
    protected static final String f = "Copyright © 1999 IPIX - v2.15";
    protected static final String g = "Loading IPIX";
    protected static final String h = "Failed to load IPIX.";
    private Thread i;
    protected k j;
    h k;
    protected g l;
    protected o m;
    protected int n;
    protected String o;
    protected static Image p;
    protected static int[] q;
    protected float r;
    private Image s;
    
    public boolean keyDown(final Event event, final int n) {
        if (this.m != null) {
            this.m.a(n);
        }
        return true;
    }
    
    public void stop() {
        if (this.k != null) {
            this.k.b();
        }
        if (this.i != null) {
            this.i.stop();
            this.i = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.m.b(n, n2);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.m.b(n, n2);
        }
        return true;
    }
    
    public synchronized void setViewpoint(final float n, final float n2, final float n3, final float n4) {
        if (this.k == null || this.l == null || this.j == null) {
            return;
        }
        this.a(new q(n, n2, n4).a());
        this.d();
    }
    
    public void repaint() {
        this.showStatus(this.c());
        super.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final int a = q.a;
        final int width = this.size().width;
        final int height = this.size().height;
        final float min = Math.min(Math.min(width / 160.0f, height / 120.0f), 1.0f);
        final int n = (int)(IpixViewer.p.getWidth(this) * min);
        final int n2 = (int)(IpixViewer.p.getHeight(this) * min);
        Label_0204: {
            if (this.l != null) {
                graphics.drawImage(this.s, 0, 0, null);
                if (a == 0) {
                    break Label_0204;
                }
            }
            int n3 = graphics.getFontMetrics().stringWidth("Copyright © 1999 IPIX - v2.15");
            final int descent = graphics.getFontMetrics().getDescent();
            Label_0179: {
                if (n3 > width) {
                    final String substring = "Copyright © 1999 IPIX - v2.15".substring(10);
                    n3 = graphics.getFontMetrics().stringWidth(substring);
                    graphics.drawString(substring, (width - n3) / 2, height - descent - 5);
                    if (a == 0) {
                        break Label_0179;
                    }
                }
                graphics.drawString("Copyright © 1999 IPIX - v2.15", (width - n3) / 2, height - descent - 5);
            }
            graphics.drawImage(IpixViewer.p, (width - n) / 2, (height - n2) / 2, n, n2, this);
        }
        final int height2 = graphics.getFontMetrics().getHeight();
        final String c = this.c();
        graphics.drawString(c, (width - graphics.getFontMetrics().stringWidth((this.n == 0) ? "Loading IPIX" : c)) / 2, ((n2 - height) / 2 + 2 * height2 + 5 > 0) ? (5 + height2) : ((height + n2) / 2 + height2));
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.m.a(n, n2);
        }
        return true;
    }
    
    q a() {
        final q q2;
        final q q = q2 = new q(this.j.b());
        q2.e /= 0.64f * this.r / this.k.k;
        return q;
    }
    
    void b() {
        this.a(this.k.f());
    }
    
    void a(final q q) {
        final int a = q.a;
        if (q.c > 10.0f || q.c < -10.0f) {
            q.c = 0.0f;
        }
        if (q.d > 5.0f || q.d < -5.0f) {
            q.d = 0.0f;
        }
        Label_0112: {
            if (this.k.d() || this.k.c()) {
                q.c = n.a(q.c, -3.1415927f, 3.1415927f);
                if (a == 0) {
                    break Label_0112;
                }
            }
            q.c = n.b(q.c, -1.5707964f, 1.5707964f);
        }
        q.d = n.b(q.d, -1.5707964f, 1.5707964f);
        if (this.k.l != 0.0f) {
            q.e = Math.max(q.e, this.k.l);
        }
        if (this.k.m != 0.0f) {
            q.e = Math.min(q.e, this.k.m);
        }
        final float n = 0.64f * this.r / this.k.k;
        final float n2 = 2.8f;
        final float n3 = this.r / (3.115f * this.k.k);
        Label_0267: {
            if (n3 < n2) {
                q.e = n.b(q.e * n, n3, n2);
                if (a == 0) {
                    break Label_0267;
                }
            }
            q.e = this.k.f().e * n;
        }
        this.j.a(q);
    }
    
    protected String c() {
        switch (this.n) {
            case 0: {
                return this.o;
            }
            case -2:
            case -1: {
                return "Failed to load IPIX.";
            }
            default: {
                return "";
            }
        }
    }
    
    public IpixViewer() {
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = 0;
        this.o = "Loading IPIX";
        this.r = 800.0f;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (this.m != null) {
            this.m.b(n);
        }
        return true;
    }
    
    void d() {
        if (this.n != 2) {
            return;
        }
        if (!this.j.g()) {
            return;
        }
        this.l.e.flush();
        this.s.getGraphics().drawImage(this.l.e, 0, 0, null);
        super.repaint();
    }
    
    static {
        final int a = q.a;
        IpixViewer.p = null;
        IpixViewer.q = null;
        final int[] array = { 130146840, -1581128767, 1961017826, 331475315, -519855647, -1592876573, -1593392671, -1049444091, -1039416862, -1593122367, 1923220898, 146924045, -513351167, -1042151231, 228712897, 266430114, 29483528, -1576279536, -1050172927, -1025439295, 163701010, -1050172927, -1593728758, -1056529951, -1056464630, -1049059067, -502611704, -1056267501, -1391221998, -1559649259, -1056792095, -1056128762, -1576418845, 334405905, -485301087, 113369879, -1562308895, 94505665, -485232879, -485235707, -1578952478, 262267139, -1576287773, 333646017, -485367020, -469523997, -1592270399, -1592729342, -1576222237, 333646307, 300094948, 48480535, -509541695, 266404513, 230810387, -485891311, -485038143, 379707653, -1055989233, -1042082845, 144827153, -484973119, 379707654, -1581183519, -1055931933, 334434787, 417601505, 146907404, -509538367, -485232959, 300095462, 367108361, -1046410271, 281142035, -473849401, -1592532202, -401350133, -509539935, -518995485, 333651427, 365028098, -468590067, -519446000, -1042082845, 501421249, -486301212, 264364449, 60885729, 149033153, -485235939, -485244445, -1593531932, 228712964, -1056186106, -492707569, -1042082845, 501420705, -473889087, -468917599, 96537569, -1593589279, 48341262, -1042082845, 501420772, 146924554, -1579022687, -519315039, 27386305, 65107427, -1593400863, 199475460, -509512476, 132301064, -1578957407, -512432701, -473890367, -490626367, -469646879, 98673012, -406780255, -442432319, -503061889, 283541265, -1041989869, 12706418 };
        final int[] array2 = { -16777216, -10066330, -6710887, -1 };
        IpixViewer.q = new int[4768];
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_1040: {
                if (a == 0) {
                    break Label_1040;
                }
                int i = 24;
                byte b;
                int n3;
                byte b2;
                int n4;
                byte b3;
                Label_1004_Outer:Label_1014_Outer:
                do {
                    b = (byte)(array[n] >> i & 0xFF);
                    n3 = (~b & 0x80);
                    b2 = (byte)((n3 == 0) ? (b & 0x1F) : (b & 0x7F));
                    n4 = array2[(n3 == 0) ? (b >> 5 & 0x3) : 0];
                    b3 = 0;
                    while (true) {
                        while (true) {
                            Label_1017: {
                                if (a == 0) {
                                    break Label_1017;
                                }
                                IpixViewer.q[n2++] = n4;
                                ++b3;
                            }
                            if (b3 < b2) {
                                continue Label_1014_Outer;
                            }
                            break;
                        }
                        i -= 8;
                        if (a == 0) {
                            continue Label_1004_Outer;
                        }
                        continue;
                    }
                } while (i >= 0);
                ++n;
            }
            if (n >= array.length) {
                IpixViewer.p = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(149, 32, IpixViewer.q, 0, 149));
                return;
            }
            continue;
        }
    }
    
    public void start() {
        if (this.i == null) {
            (this.i = new Thread(this)).start();
        }
    }
    
    public synchronized void setURL(final String s) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            System.out.println("Loading: " + url);
            if (this.k != null) {
                if (this.k.h != null && url.sameFile(this.k.h)) {
                    return;
                }
                this.k.b();
            }
            this.k = new h(url);
            this.n = 0;
            if (this.l == null) {
                this.getGraphics().clearRect(0, 0, this.size().width, this.size().height);
            }
            this.repaint();
        }
        catch (Exception ex) {
            this.n = -1;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.m.c(n, n2);
        }
        return true;
    }
    
    public void run() {
        final int a = q.a;
        try {
            this.setURL(this.getParameter("URL"));
            while (true) {
                Label_0294: {
                    switch (this.n) {
                        case 4: {
                            this.m.tick();
                            if (a != 0) {
                                break Label_0294;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.k.j != 1) {
                                this.n = ((this.k.j >= 0) ? 1 : -1);
                                if (a == 0) {
                                    continue;
                                }
                            }
                            Label_0152: {
                                if (this.o.length() > "Loading IPIX".length() + 10) {
                                    this.o = "Loading IPIX";
                                    if (a == 0) {
                                        break Label_0152;
                                    }
                                }
                                this.o += '.';
                            }
                            this.repaint();
                            Thread.sleep(1000L);
                            if (a != 0) {
                                break Label_0294;
                            }
                            continue;
                        }
                        case 3: {
                            synchronized (this) {
                                this.l = new g(this.size());
                                this.s = this.createImage(this.size().width, this.size().height);
                                this.j = new l(this.k, this.l);
                                this.k.b[0] = -16777216;
                                this.n = 2;
                                this.showStatus("");
                                this.b();
                                this.d();
                            }
                            this.m = new o(this);
                            Thread.sleep(0L);
                            if (a != 0) {
                                break Label_0294;
                            }
                            continue;
                        }
                        case 1: {
                            this.k = null;
                            this.l = null;
                            this.s = null;
                            this.getGraphics().clearRect(0, 0, this.size().width, this.size().height);
                            this.repaint();
                            this.n = -2;
                        }
                        case 0: {
                            Thread.sleep(200L);
                            continue;
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    public void init() {
        this.r = (float)Math.sqrt(this.size().width * this.size().width + this.size().height * this.size().height);
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.repaint();
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.m.b(n, n2);
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.m != null) {
            this.m.b(n, n2);
        }
        return true;
    }
}
