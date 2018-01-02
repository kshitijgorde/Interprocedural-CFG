import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Thread
{
    private EggApplet p;
    private String p;
    private DataInputStream p;
    private byte[] p;
    private int p;
    private int d;
    private int a;
    private int n;
    private int v;
    private boolean p;
    private boolean d;
    private boolean a;
    private boolean n;
    private n p;
    
    public final int p() {
        if (this.v == 0) {
            return 0;
        }
        return Math.min(100, this.p * 100 / this.v);
    }
    
    public final void a(final int a) {
        this.p = new byte[a];
        this.a = a;
    }
    
    public final void d(final int n) {
        this.p[this.p++] = (byte)(n >>> 24);
        this.p[this.p++] = (byte)(n << 8 >>> 24);
        this.p[this.p++] = (byte)(n << 16 >>> 24);
        this.p[this.p++] = (byte)(n << 24 >>> 24);
    }
    
    public final void p(final int n, final int n2) {
        if (n2 >= 1) {
            this.p[this.p++] = (byte)(n >>> 24);
        }
        if (n2 >= 2) {
            this.p[this.p++] = (byte)(n << 8 >>> 24);
        }
        if (n2 >= 3) {
            this.p[this.p++] = (byte)(n << 16 >>> 24);
        }
        if (n2 >= 4) {
            this.p[this.p++] = (byte)(n << 24 >>> 24);
        }
    }
    
    public final void a() {
        this.p = null;
    }
    
    public final void n() throws InterruptedException, IOException {
        while (this.p - this.d < 4) {
            Thread.sleep(100L);
        }
        final int int1 = this.p.readInt();
        this.d += 4;
        if (!this.p.p(int1)) {
            System.out.println("Versioning problem: ".concat(String.valueOf(String.valueOf(int1))));
        }
    }
    
    public final void v() {
        this.p = true;
    }
    
    public final void d() {
        this.d = true;
    }
    
    public final void n(final int n) throws InterruptedException, IOException {
        int i = 0;
        byte[] array = null;
        final o o = new o();
        if (this.n++ == 1) {
            this.v = n + this.d;
        }
        while (this.p - this.d < n) {
            Thread.sleep(100L);
        }
        while (i < n) {
            final int int1 = this.p.readInt();
            final int int2 = this.p.readInt();
            if (int2 == 1246774599) {
                array = new byte[int1 - 8];
                this.p.read(array);
                this.n = true;
            }
            else if (int2 == 1346914642) {
                o.p(this.p.readInt(), this.p.readInt());
            }
            else if (int2 == 1414091852) {
                this.p.skip(2L);
                final int n2 = (int1 - 10) / 2;
                String concat = "";
                for (int j = 0; j < n2; ++j) {
                    concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(this.p.readChar())));
                }
                o.p(concat);
            }
            else {
                this.p.skip(int1 - 8);
            }
            i += int1;
        }
        if (!this.n) {
            System.out.println("ERROR: image not found within the 'IMAG' tag");
            return;
        }
        o.p(array);
        this.p.p(o);
        this.p.p(true);
        this.d += n;
    }
    
    public final void p(final int n) throws InterruptedException, IOException {
        int i = 0;
        byte[] dy = null;
        String concat = "";
        while (this.p - this.d < n) {
            Thread.sleep(100L);
        }
        while (i < n) {
            final int int1 = this.p.readInt();
            final int int2 = this.p.readInt();
            if (int2 == 1415139397) {
                this.p.skip(2L);
                for (int n2 = (int1 - 10) / 2, j = 0; j < n2; ++j) {
                    concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(this.p.readChar())));
                }
            }
            else if (int2 == 1195984452) {
                dy = new byte[int1 - 8];
                this.p.read(dy);
                this.a = true;
            }
            else {
                this.p.skip(int1 - 8);
            }
            i += int1;
        }
        if (!this.a) {
            System.out.println("ERROR: icon not found within the 'ICON' tag");
            return;
        }
        if (concat.equals("")) {
            System.out.println("ERROR: icon not recognized within the 'ICON' tag");
            return;
        }
        if (concat.equals("Info")) {
            this.p.dg = dy;
        }
        else if (concat.equals("SplashScreen")) {
            this.p.df = dy;
        }
        else if (concat.equals("LogoActive")) {
            this.p.dh = dy;
        }
        else if (concat.equals("LogoPressed")) {
            this.p.dj = dy;
        }
        else if (concat.equals("HelpActive")) {
            this.p.dk = dy;
        }
        else if (concat.equals("HelpPressed")) {
            this.p.dm = dy;
        }
        else if (concat.equals("ZoomInActive")) {
            this.p.do = dy;
        }
        else if (concat.equals("ZoomInPressed")) {
            this.p.dq = dy;
        }
        else if (concat.equals("ZoomOutActive")) {
            this.p.dr = dy;
        }
        else if (concat.equals("ZoomOutPressed")) {
            this.p.ds = dy;
        }
        else if (concat.equals("PartnerLogo")) {
            this.p.dt = dy;
        }
        else if (concat.equals("PreviousImageActive")) {
            this.p.du = dy;
        }
        else if (concat.equals("PreviousImagePressed")) {
            this.p.dw = dy;
        }
        else if (concat.equals("NextImageActive")) {
            this.p.dx = dy;
        }
        else if (concat.equals("NextImagePressed")) {
            this.p.dy = dy;
        }
        this.d += n;
    }
    
    public final void p() throws InterruptedException, IOException {
        while (this.p - this.d < 12) {
            Thread.sleep(100L);
        }
        int n = 8;
        int int1 = this.p.readInt();
        if (int1 >>> 31 == 1) {
            this.p.skip(4L);
            int1 ^= Integer.MIN_VALUE;
            this.d += 4;
            n += 4;
        }
        final int int2 = this.p.readInt();
        this.d += 8;
        if (int2 == 1447383635) {
            this.n();
        }
        else if (int2 == 1414485330) {
            this.v();
        }
        else if (int2 == 1229799751) {
            this.n(int1 - 12);
        }
        else if (int2 == 1095910739) {
            this.d();
        }
        else if (int2 == 1229147982) {
            this.p(int1 - 12);
        }
        else {
            while (this.p + 1 - this.d < int1 - n) {
                Thread.sleep(100L);
            }
            this.p.skip(int1 - n);
            this.d += int1 - n;
        }
    }
    
    public final void run() {
        try {
            this.p = new n(this, this.p, this.p);
            while (this.p - this.d < 12) {
                Thread.sleep(100L);
            }
            this.p = new DataInputStream(new ByteArrayInputStream(this.p));
            if (this.p.readInt() != 1162299206) {
                System.out.println("File received malformed: tag 'EGGF' not found inside.");
                return;
            }
            this.d += 4;
            while (this.d < this.a) {
                this.p();
            }
            if (!this.p && !this.d) {
                System.out.println("File received malformed: tag 'TOUR' or 'ARES' not found inside.");
                return;
            }
        }
        catch (Exception ex) {
            System.out.println("Exception caught: ".concat(String.valueOf(String.valueOf(ex))));
        }
        if (this.n) {
            this.p.n();
        }
        else if (this.a) {
            this.p.d();
        }
    }
    
    public b(final EggApplet p2, final String p3) {
        this.p = 0;
        this.d = 0;
        this.a = 0;
        this.n = 1;
        this.v = 0;
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = false;
        this.p = p2;
        this.p = p3;
        this.start();
    }
}
