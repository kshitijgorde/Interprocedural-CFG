import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Shape;
import java.awt.RenderingHints;
import java.io.ByteArrayOutputStream;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aP extends Frame implements Runnable
{
    public boolean a;
    public boolean b;
    private int k;
    public int a;
    public int b;
    public int c;
    public static int d;
    public static int e;
    public boolean c;
    public int[] a;
    public int[] b;
    public int[] c;
    public int[] d;
    public Image a;
    private aE a;
    private au a;
    public Image b;
    private Image f;
    public Image c;
    public Image d;
    private int l;
    private int m;
    public Image e;
    private Image g;
    private Thread a;
    private boolean g;
    private int n;
    public boolean d;
    public float a;
    public float b;
    public int f;
    public int g;
    public double a;
    private Graphics a;
    public int h;
    public int i;
    private Image h;
    private Image i;
    private Graphics2D a;
    private Graphics b;
    private String a;
    private String b;
    private Font a;
    private int o;
    private boolean h;
    private float c;
    public boolean e;
    private boolean i;
    private int p;
    private int q;
    private int r;
    private boolean j;
    private int s;
    private boolean k;
    private boolean l;
    private int t;
    private int u;
    private int v;
    public int j;
    private boolean m;
    public double b;
    public boolean f;
    
    public aP(final au a, final aE a2) {
        this.a = true;
        this.b = true;
        this.k = 0;
        this.a = 0;
        this.b = 1;
        this.c = 0;
        this.c = false;
        this.b = new int[aP.d * aP.e];
        this.c = new int[aP.d * aP.e];
        this.d = new int[aP.d * aP.e];
        this.l = 0;
        this.m = 0;
        this.g = false;
        this.n = 0;
        this.d = false;
        this.a = 128.0f;
        this.b = 128.0f;
        this.f = 0;
        this.g = 0;
        this.a = 1.0;
        this.a = null;
        this.h = 20;
        this.i = this.h;
        this.h = null;
        this.i = null;
        this.a = null;
        this.b = null;
        this.a = "";
        this.b = "";
        this.a = new Font("Dialog", 0, 10);
        this.o = 0;
        this.h = false;
        this.c = -1.0f;
        this.e = false;
        this.i = false;
        this.p = 0;
        this.q = -1;
        this.r = -1;
        this.j = true;
        this.s = 255;
        this.k = false;
        this.l = false;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.j = 0;
        this.m = false;
        this.b = 0.0;
        this.f = false;
        this.a = a;
        this.a = a2;
        this.a = this.createImage(new MemoryImageSource(aP.d, aP.e, this.b, 0, 256));
        a.a.a(this.a, this.b, this.d, 0);
        this.a = a.a.a;
        this.a = a.a.a;
        this.q();
        try {
            (this.b = this.a("NESCafe.ddnb")).flush();
        }
        catch (Exception ex) {}
        try {
            (this.f = this.a("Wait.ddnb")).flush();
        }
        catch (Exception ex2) {}
        try {
            (this.g = this.a("Activate.ddnb")).flush();
        }
        catch (Exception ex3) {}
        try {
            (this.e = this.a("menu.ddnb")).flush();
        }
        catch (Exception ex4) {}
        try {
            (this.c = this.a("mushroom.ddnb")).flush();
        }
        catch (Exception ex5) {}
        try {
            (this.d = this.a("mario.ddnb")).flush();
        }
        catch (Exception ex6) {}
        System.gc();
        this.c();
        if ((aK.a() & 0x20) != 0x0) {
            final an an = new an(8, a);
            a2.a(true);
            a2.a(an);
        }
    }
    
    public final void a() {
        this.b = !this.b;
        this.c();
    }
    
    public final Image a(final String s) throws Exception {
        try {
            final Image b = this.b(s);
            ++this.j;
            final MediaTracker mediaTracker;
            (mediaTracker = new MediaTracker(this)).addImage(b, 0);
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorID(0)) {
                return null;
            }
            return b;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Image b(final String s) {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            final aj aj = new aj(this.getClass().getResourceAsStream(s));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[2048];
            int read;
            while ((read = aj.read(array, 0, 2048)) > -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            aj.close();
            return defaultToolkit.createImage(byteArray);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void b() {
        if (this.b == null) {
            return;
        }
        this.b.flush();
        this.a(this.b);
    }
    
    public final void c() {
        final int dispHeight = this.a.a.getDispHeight();
        final int dispWidth = this.a.a.getDispWidth();
        if (this.a.a.a >= 1.0) {
            this.a = this.a.a.a;
        }
        else {
            this.a = 1.0;
            if (dispWidth > 320 && dispHeight > 320 && this.b) {
                final double a = (dispWidth - 20) / 256.0;
                final double a2 = (dispHeight - 20) / 240.0;
                if (a > a2) {
                    this.a = a2;
                }
                else {
                    this.a = a;
                }
            }
        }
        if (this.a < 1.0) {
            this.a = 1.0;
        }
        if (this.a > 2.0) {
            this.a = 2.0;
        }
        if (dispHeight != this.q || dispWidth != this.r) {
            this.j = true;
        }
        this.q = dispHeight;
        this.r = dispWidth;
    }
    
    private final void a(final Image image) {
        if (this.m) {
            return;
        }
        this.m = true;
        this.b(image);
        this.m = false;
    }
    
    private final void b(final Image image) {
        try {
            if (image == null) {
                return;
            }
            if (this.a.e && this.a.b() && this.a.a.a) {
                this.a.a(true);
            }
            if (this.h == null || this.j) {
                if (this.r < 0 || this.q < 0) {
                    this.c();
                }
                this.h = this.a.a.createImage(this.r, this.q);
                if (this.h == null) {
                    return;
                }
                (this.a = (Graphics2D)this.h.getGraphics()).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                this.a.setClip(null);
                this.j = false;
            }
            if (this.i == null) {
                this.i = this.a.a.createImage(aP.d, aP.e);
                if (this.i == null) {
                    return;
                }
                this.b = this.i.getGraphics();
                if (this.b != null) {
                    this.b.setClip(null);
                }
            }
            if (!this.a.e && this.c == -1.0f) {
                final Graphics a;
                if ((a = this.a) == null) {
                    return;
                }
                a.setClip(null);
                a.setColor(Color.black);
                a.fillRect(0, 0, this.r, this.q);
            }
            else {
                if (image != null && !this.b.drawImage(image, 0, 0, Color.black, null)) {
                    return;
                }
                if (this.a.b() && this.a.a.a(this.b, this.a)) {
                    this.a.b();
                    this.a.a(false);
                }
                if (this.c > 0.0f && !this.a(this.b)) {
                    return;
                }
                if (this.a.e && !this.a.a() && !this.a.b() && this.c == -1.0f) {
                    this.b.setColor(new Color(120, 120, 120));
                    aK.a(this.b, "There is currently no game loaded", this.a, 100);
                }
                if (this.a.a.a != null) {
                    this.a.a.a.a(this.b);
                }
                this.a.setColor(Color.black);
                this.a.fillRect(0, 0, this.r, this.q);
                this.f = this.r / 2 - (int)(this.a * aP.d) / 2;
                this.g = this.q / 2 - (int)(this.a * aP.e) / 2;
                if (this.a == 1.0) {
                    if (!this.a.drawImage(this.i, this.f, this.g, Color.black, null)) {
                        return;
                    }
                }
                else if (!this.a.drawImage(this.i, this.f, this.g, (int)(aP.d * this.a), (int)(aP.e * this.a), Color.black, null)) {
                    return;
                }
                this.c(this.a);
                this.b(this.a);
                if (this.a.a.a != null) {
                    this.a.a.a.a(this.a, this.r, this.q);
                }
                final Graphics a2;
                if ((a2 = this.a) == null) {
                    return;
                }
                a2.setClip(0, 0, this.r, this.q);
                a2.drawImage(this.h, 0, 0, Color.black, null);
            }
        }
        catch (Exception ex) {}
        catch (Error error) {}
    }
    
    private void b(final Graphics graphics) {
        if (this.a.b()) {
            return;
        }
        if (this.a != null && this.a != "") {
            if (this.h) {
                graphics.setColor(new Color(255, 128, 128, 200));
            }
            else {
                graphics.setColor(new Color(128, 128, 255, 200));
            }
            graphics.fillRect(0, this.q - this.o, this.r - 1, 15);
            if (this.h) {
                graphics.setColor(new Color(255, 0, 0));
            }
            else {
                graphics.setColor(new Color(0, 0, 255));
            }
            graphics.drawRect(0, this.q - this.o, this.r - 1, 15);
            graphics.setFont(this.a);
            if (this.h) {
                graphics.setColor(new Color(128, 0, 0));
            }
            else {
                graphics.setColor(new Color(0, 0, 128));
            }
            graphics.drawString(this.a, 5, this.q - 4 + (16 - this.o));
            this.o += 2;
            if (this.o > 16) {
                this.o = 16;
            }
        }
        if (this.b != null && this.b != "") {
            graphics.setColor(new Color(128, 128, 255, 200));
            graphics.fillRect(0, 0, this.r - 1, 15);
            graphics.setColor(new Color(0, 0, 255));
            graphics.drawRect(0, 0, this.r - 1, 15);
            graphics.setFont(this.a);
            graphics.setColor(new Color(0, 0, 128));
            graphics.drawString(this.b, 5, 12);
        }
    }
    
    private void c(final Graphics graphics) {
        if (!this.a.a() || !this.a.a.a || this.a.a.b) {
            return;
        }
        if (this.a.a.m) {
            Color color = new Color(255, 100, 100, 200);
            ++this.p;
            if (this.p > 60) {
                final int n = (70 - this.p) * 15;
                color = new Color(255, 100 + n, 100 + n, 200);
                if (this.p > 70) {
                    this.p = 0;
                }
            }
            graphics.setColor(new Color(0, 0, 0, 200));
            graphics.fillOval(2, 2, 20, 20);
            graphics.setColor(color);
            graphics.fillOval(4, 4, 16, 16);
            aK.a(graphics, "Time Trial Mode (press T to submit screenshot)", 24, 17, color);
            return;
        }
        if (this.a.a.e) {
            Color color2 = new Color(255, 100, 100, 200);
            ++this.p;
            if (this.p > 60) {
                final int n2 = (70 - this.p) * 15;
                color2 = new Color(255, 100 + n2, 100 + n2, 200);
                if (this.p > 70) {
                    this.p = 0;
                }
            }
            graphics.setColor(Color.black);
            graphics.fillOval(2, 2, 20, 20);
            graphics.setColor(color2);
            graphics.fillOval(4, 4, 16, 16);
            aK.a(graphics, "Recording Movie (press F8 to end)", 24, 17, color2);
            return;
        }
        if (this.a.a.f) {
            Color color3 = new Color(100, 255, 100, 200);
            ++this.p;
            if (this.p > 60) {
                final int n3 = (70 - this.p) * 15;
                color3 = new Color(100 + n3, 255, 100 + n3, 200);
                if (this.p > 70) {
                    this.p = 0;
                }
            }
            graphics.setColor(Color.black);
            graphics.fillPolygon(new int[] { 2, 12, 2 }, new int[] { 2, 12, 22 }, 3);
            graphics.setColor(color3);
            graphics.fillPolygon(new int[] { 4, 10, 4 }, new int[] { 6, 12, 18 }, 3);
            aK.a(graphics, "Playing Movie", 14, 17, color3);
        }
    }
    
    private boolean a(final Graphics graphics) {
        if (this.a.a) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 256, 240);
            if (this.b != null && !graphics.drawImage(this.b, 0, -10, Color.black, this)) {
                return false;
            }
            this.b = this.c;
            final int n = (int)(this.b * 200);
            for (int i = 0; i < n / 16 + 1; ++i) {
                graphics.setColor(new Color(0, 0, Math.min(180 + i * 4, 255)));
                graphics.fillRect(30 + i * 16, 150, 14, 8);
                graphics.setColor(new Color(0, 0, 128));
                graphics.drawRect(30 + i * 16, 150, 14, 8);
            }
            graphics.setColor(Color.black);
            graphics.fillRect(30 + n, 150, 16, 12);
            graphics.setColor(new Color(200, 200, 200));
            graphics.drawRect(28, 148, 200, 12);
            final int n2 = 30 + n - 5;
            final int l = this.l;
            if (!graphics.drawImage(this.d, n2, 150, n2 + 16, 166, l * 16, 0, l * 16 + 16, 16, null, null)) {
                return false;
            }
        }
        else {
            final int n3 = (int)(this.c * 100.0f);
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, 256, 240);
            String string = n3 + "%";
            if (this.c == 1.0f) {
                string = "Download Complete";
            }
            aK.a(graphics, string, 30, 22, new Color(200, 200, 255), 1, new Color(0, 0, 255), this.a);
            int j = this.l;
            if (this.c == 1.0f) {
                j = 3;
            }
            if (!graphics.drawImage(this.d, 10, 10, 26, 26, j * 16, 0, j * 16 + 16, 16, null, null)) {
                return false;
            }
        }
        ++this.m;
        if (this.m > 8) {
            ++this.l;
            this.m = 0;
        }
        if (this.l > 2) {
            this.l = 0;
        }
        return true;
    }
    
    public final void a(final String a, final boolean h, final int o) {
        synchronized (this.a) {
            this.o = o;
            this.a = a;
            this.h = h;
        }
    }
    
    public final String a() {
        synchronized (this.a) {
            return this.a;
        }
    }
    
    public final void a(final String b) {
        synchronized (this.b) {
            this.b = b;
        }
    }
    
    public final void d() {
        final boolean hasFocus = this.a.a.hasFocus();
        if (!this.a.b() || hasFocus) {
            if (this.a.c()) {
                for (int i = 0; i < this.b.length; ++i) {
                    this.b[i] = -16777216;
                    this.c[i] = 0;
                }
                this.a.flush();
            }
            this.a(this.a);
            return;
        }
        if (this.i-- > 5) {
            this.g.flush();
            this.a(this.g);
            return;
        }
        this.b.flush();
        this.a(this.b);
        if (this.i < 0) {
            this.i = this.h;
        }
    }
    
    public final void a(final float c) {
        this.c = c;
        this.o();
    }
    
    private final void o() {
        try {
            if (!this.a.a()) {
                this.a(this.a);
                return;
            }
            if (!this.a.a.hasFocus && this.g != null) {
                this.a(this.g);
                return;
            }
            this.i();
            if (this.a.c && !this.a.a.c) {
                if (this.f == null) {
                    this.d();
                    return;
                }
                this.f.flush();
                this.a(this.f);
            }
            else {
                if (this.a.a.c() && this.a.a() && (this.a.a.b || !this.a.a.a || this.s < 255)) {
                    boolean b = false;
                    switch (this.a.a.a()) {
                        case 16: {
                            b = true;
                            break;
                        }
                    }
                    if (!b) {
                        if (!this.a.a.b && this.a.a.a) {
                            if (this.s < 255) {
                                this.s += 16;
                            }
                            if (this.s > 255) {
                                this.s = 255;
                            }
                        }
                        else if (this.s > 128) {
                            this.s -= 16;
                        }
                        else {
                            this.s = 128;
                        }
                        for (int i = 0; i < this.b.length; ++i) {
                            this.d[i] = this.b[i];
                        }
                        for (int j = 0; j < this.b.length; ++j) {
                            this.b[j] = (this.s << 24 | (this.b[j] & 0xFFFFFF));
                        }
                        this.a.flush();
                        this.a(this.a);
                        for (int k = 0; k < this.b.length; ++k) {
                            this.b[k] = this.d[k];
                        }
                        this.a.flush();
                        return;
                    }
                }
                if (!this.a.a()) {
                    this.d();
                    return;
                }
                if (this.a.a.d) {
                    final an an = new an(11, this.a);
                    this.a.a(true);
                    an.c = 13;
                    this.a.a(an);
                    this.a(this.a);
                    return;
                }
                this.a.flush();
                this.a(this.a);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final boolean b) {
        this.a(b, true);
    }
    
    public final void a(final boolean b, final boolean b2) {
        if (this.k) {
            return;
        }
        this.k = true;
        if (this.i) {
            this.p();
        }
        if (!this.g | b | this.a.b()) {
            this.o();
        }
        if (b2) {
            ++this.k;
        }
        this.k = false;
    }
    
    public final void a(final Graphics graphics) {
        if (this.a == null && graphics != null) {
            graphics.setClip(null);
            this.a = graphics.create();
            this.f = true;
        }
    }
    
    public final void e() {
        this.c = false;
        this.a.c("");
    }
    
    public final void f() {
        this.c = true;
        this.a.c("Calculating FPS...");
    }
    
    public final boolean a(final int c) {
        this.c = c;
        if (this.c == 0) {
            ++this.n;
            if (this.n <= this.b) {
                this.g = true;
            }
            else {
                this.n = (this.l ? 1 : 0);
                this.l = !this.l;
                this.g = false;
            }
        }
        if (this.a.a.a) {
            this.a = this.a.a.a;
            this.a.a.a = false;
        }
        return this.g;
    }
    
    public final void g() {
        this.e = true;
        try {
            this.a.join();
        }
        catch (Exception ex) {}
    }
    
    public final void run() {
        while (!this.e) {
            this.k = 0;
            final boolean b = this.a.a() && this.a.a.a && !this.a.a.b;
            try {
                final int n;
                Thread.sleep(n = (b ? 1000 : 100));
                if (!this.a.a()) {
                    this.a(true);
                }
            }
            catch (Exception ex) {}
            if (b) {
                this.t += this.k;
                ++this.u;
            }
            if (!this.a.a()) {
                this.b();
            }
            if (this.u != 0) {
                this.a = this.t / this.u;
            }
            final double n2 = 100.0 * this.a.a.a / 60.0;
            if (this.c || n2 < 100.0) {
                this.a.c("Running at " + this.a + " FPS, Emulation Rate is " + (int)n2 + "%");
            }
            if (this.a.a.g || this.a.a.t) {
                this.b = 0;
            }
            else {
                if (n2 != 100.0) {
                    continue;
                }
                if (this.a > 0 && this.a < 58) {
                    if (this.b >= 3) {
                        continue;
                    }
                    ++this.b;
                }
                else {
                    if (this.b <= 0) {
                        continue;
                    }
                    --this.b;
                }
            }
        }
    }
    
    public final void h() {
        this.i = true;
    }
    
    private void p() {
        this.i = false;
        try {
            byte[] array = null;
            if (this.a.a.f == 1) {
                array = J.a(this.a);
            }
            else if (this.a.a.f == 0) {
                array = J.a(this.c, this.a.a.a());
            }
            if (array == null) {
                this.a.a("SaveImage Buffer could not be created");
                throw new Exception("Failed");
            }
            if (!this.a.a.saveScreenShot(array)) {
                throw new Exception(this.a.a.lastBroadcastError);
            }
        }
        catch (Error error) {
            final an an = new an(12, this.a);
            this.a.a(true);
            an.c = 7;
            an.a("NESCafe is running on a web server that is", "incorrectly configured for PNG uploads.", "Please change image settings to GIF.");
            this.a.a(an);
            return;
        }
        catch (Exception ex) {
            this.a.a("TVController.saveScreenTakeShot() Failed");
            this.a.a(ex.toString());
            final an an2 = new an(12, this.a);
            this.a.a(true);
            an2.c = 14;
            an2.a("Screenshot Failed to be Saved", this.a.a.lastBroadcastError);
            this.a.a(an2);
            return;
        }
        final an an3 = new an(12, this.a);
        this.a.a(true);
        an3.c = 14;
        if (this.a.a.m) {
            an3.a("Timetrial Screenshot saved successfully");
        }
        else {
            an3.a("Your screenshot was saved successfully");
        }
        this.a.a(an3);
    }
    
    public final byte[] a(final boolean b) throws Exception {
        if (this.a == null) {
            this.a.a("TVController.getScreenThumbnailImage() failed");
            this.a.a("OffScreenImage object has not been created");
            return null;
        }
        if (b) {
            try {
                return J.a(this.c, this.a.a.a(), 2);
            }
            catch (Error error) {
                this.a.a.k = false;
                final an an = new an(12, this.a);
                this.a.a(true);
                an.c = 7;
                an.a("NESCafe is running on a web server that uses a", "custom error page for code 404, which means that", "the Time Shift Buffer must be disabled.");
                this.a.a(an);
                return null;
            }
            catch (Exception ex) {
                throw ex;
            }
        }
        if (this.a.a.i) {
            try {
                if (this.a.a.f == 1) {
                    return J.a(this.a.getScaledInstance(128, 120, 4));
                }
                return J.a(this.c, this.a.a.a(), 0);
            }
            catch (Error error2) {
                return null;
            }
        }
        try {
            if (this.a.a.f == 1) {
                return J.a(this.a.getScaledInstance(96, 90, 4));
            }
            return J.a(this.c, this.a.a.a(), 1);
        }
        catch (Error error3) {
            return null;
        }
    }
    
    public final void a(final int[] array) {
        if (this.c < 1 || this.c > 239) {
            return;
        }
        if (array.length < 256) {
            return;
        }
        final int n = this.a ? 8 : 0;
        final int n2 = 256 - n;
        final int n3 = this.c << 8;
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                this.c[n3 + i] = 64;
                this.c[n3 + 255 - i] = 64;
            }
        }
        for (int j = n; j < n2; ++j) {
            this.c[n3 + j] = array[j];
        }
        if (this.c == 239 && this.a.a.e && this.a.a.d == 1 && this.a.a.a != null) {
            ++this.v;
            if (60 / this.a.a.e <= this.v) {
                this.v = 0;
                if (this.a.a.a != null) {
                    try {
                        this.a.a.a.a(this.c);
                    }
                    catch (Exception ex) {}
                    catch (Error error) {
                        this.a.a.g = true;
                    }
                }
            }
        }
    }
    
    public final void i() {
        this.a = this.a.a.a;
        for (int i = 256; i < this.b.length; ++i) {
            this.b[i] = this.a[this.c[i]];
        }
    }
    
    private final void q() {
        (this.a = new Thread(this)).setPriority(1);
        this.a.start();
    }
    
    public final void j() {
        this.a += 5.0f;
        if (this.a > 255.0f) {
            this.a = 255.0f;
        }
        this.a.a.a(this.a, this.b, this.d, this.a.a.a.b);
    }
    
    public final void k() {
        this.a -= 5.0f;
        if (this.a < 0.0f) {
            this.a = 0.0f;
        }
        this.a.a.a(this.a, this.b, this.d, this.a.a.a.b);
    }
    
    public final void l() {
        this.b += 5.0f;
        if (this.b > 255.0f) {
            this.b = 255.0f;
        }
        this.a.a.a(this.a, this.b, this.d, this.a.a.a.b);
    }
    
    public final void m() {
        this.b -= 5.0f;
        if (this.b < 0.0f) {
            this.b = 0.0f;
        }
        this.a.a.a(this.a, this.b, this.d, this.a.a.a.b);
    }
    
    public final void n() {
        this.d = !this.d;
        this.a.a.a(this.a, this.b, this.d, this.a.a.a.b);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n & 0xE0) == 0x0;
    }
    
    static {
        aP.d = 256;
        aP.e = 240;
    }
}
