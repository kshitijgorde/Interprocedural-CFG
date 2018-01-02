import java.awt.Cursor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ab extends Thread
{
    private au a;
    private aE a;
    private boolean i;
    public boolean a;
    public boolean b;
    public boolean c;
    public aC a;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    private int i;
    public float a;
    private boolean j;
    private boolean k;
    public boolean d;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    public boolean e;
    public boolean f;
    public boolean g;
    private int l;
    public ByteArrayOutputStream a;
    private ByteArrayInputStream a;
    private byte[] b;
    public boolean h;
    public int h;
    public h a;
    public byte[] a;
    public ay a;
    private R a;
    private final int[] a;
    private final int[] b;
    
    public ab(final au a, final aE a2) {
        this.i = true;
        this.a = false;
        this.b = false;
        this.c = false;
        this.a = 60;
        this.a = 116.0f;
        this.j = false;
        this.k = false;
        this.d = false;
        this.j = 0;
        this.k = 0;
        this.l = false;
        this.m = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.l = 0;
        this.a = null;
        this.a = null;
        this.b = null;
        this.h = false;
        this.h = 0;
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = new R();
        this.a = new int[] { 7, 6, 2, 8, 3, 3, 5, 5, 3, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 5, 5, 7, 7, 6, 6, 2, 8, 3, 3, 5, 5, 4, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 5, 5, 7, 7, 6, 6, 2, 8, 3, 3, 5, 5, 3, 2, 2, 2, 3, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 5, 5, 7, 7, 4, 6, 2, 8, 3, 3, 5, 5, 4, 2, 2, 2, 5, 4, 6, 6, 4, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 5, 5, 7, 7, 2, 6, 2, 6, 3, 3, 3, 3, 2, 2, 2, 2, 4, 4, 4, 4, 2, 6, 2, 6, 4, 4, 4, 4, 2, 5, 2, 5, 5, 5, 5, 5, 2, 6, 2, 6, 3, 3, 3, 3, 2, 2, 2, 2, 4, 4, 4, 4, 2, 5, 2, 5, 4, 4, 4, 4, 2, 4, 2, 4, 4, 4, 4, 4, 2, 6, 2, 8, 3, 3, 5, 5, 2, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 5, 5, 7, 7, 2, 6, 2, 8, 3, 3, 5, 5, 2, 2, 2, 2, 4, 4, 6, 6, 2, 5, 2, 8, 4, 4, 6, 6, 2, 4, 2, 7, 5, 5, 7, 7 };
        this.b = new int[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128 };
        this.a = a;
        this.a = a2;
    }
    
    public final void run() {
        while (!this.a.a.e) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        int n = 0;
        this.q();
        int n2 = 1;
        this.k = false;
        long n3 = System.currentTimeMillis() + 1000 / this.a * 5;
        int n4 = 1;
        while (!this.k) {
            if (n4 > 5) {
                n3 = System.currentTimeMillis() + 1000 / this.a * 5;
                n4 = 1;
            }
            this.o();
            if (this.a != null && !this.a.b() && this.a.a.k) {
                if (++n > 60 * this.a.a.j - 1) {
                    n = 0;
                }
                if (n == 0 && this.a != null) {
                    this.a.a(this.a.a(true, true, true));
                }
            }
            if (n4 == 5) {
                while (n3 > System.currentTimeMillis()) {}
            }
            if (n2 != 0 && ++n2 > 32) {
                this.a.b(false);
                n2 = 0;
            }
            ++n4;
            if (this.a.a.m && !this.a.b()) {
                final y a = this.a.a;
                ++a.k;
            }
        }
    }
    
    public final void a(final int n) {
        this.i -= n;
    }
    
    public final void a(final String a) throws aO {
        final aC a2;
        if ((a2 = new aC(this.a)).a(a)) {
            String string = "An error occurred when opening the ROM Image.";
            String string2 = "";
            String s = "";
            switch (a2.b) {
                case 24: {
                    string = "The following file could not be found:";
                    string2 = "'" + a + "'";
                    break;
                }
                case 20: {
                    string = "The selected ROM file could not be read.";
                    break;
                }
                case 16: {
                    string = "The selected game is not a valid iNES Rom.";
                    break;
                }
                case 18: {
                    string = "Mapper '" + this.a.a + "'" + " is not supported.";
                    break;
                }
                case 27: {
                    string = "Could not connect to the Webserver to open";
                    string2 = "game. Make sure the game is hosted on the";
                    s = "same server as this NESCafe Applet.";
                    break;
                }
                case 19: {
                    string = "The selected ROM file contains no Program ROM";
                    break;
                }
                case 21: {
                    string = "The selected ZIP file contains no Nintendo ROMs";
                    break;
                }
                case 22: {
                    string = "The selected ZIP file is corrupt.";
                    string2 = "Please attempt to download it again.";
                    break;
                }
                case 23: {
                    string = "The selected GZIP file is corrupt.";
                    string2 = "Please attempt to download it again.";
                    break;
                }
                case 25: {
                    string = "Can't load Encrypted ROMs into Standalone version.";
                    break;
                }
                case 26: {
                    string = "Encrypted ROM was not encoded for use on this site.";
                    break;
                }
            }
            throw new aO(string, string2, s);
        }
        if (this.a()) {
            this.a.c();
        }
        if (!this.a.a) {
            this.a.a();
        }
        this.a.a.a.a(0.0f);
        this.a = a2;
        this.d();
        ((ap)(this.a.a = a)).a(this.a, a);
        this.a.a = this.a.a;
        if (this.a.a == null) {
            this.a = null;
            throw new aO("The Hardware could not be located for the Cartridge.");
        }
        this.a.a.e = false;
        this.a.a.a(this.a.a);
        this.a.a.a(this.a.a);
        if (this.a.e != 0 && this.a.b != null) {
            final an an;
            (an = new an(17, this.a)).a(this.a.b);
            this.a.a(an);
        }
    }
    
    private void k() {
        this.h = false;
        this.a.a(this.h);
    }
    
    private void l() {
        this.e = false;
        this.m = false;
        this.f = false;
        this.g = false;
        this.l = 0;
        this.l = false;
        this.j = 0;
        this.k = 0;
        this.a = null;
        this.a = null;
        this.b = null;
    }
    
    public final void a(final byte[] array) {
        if (array == null) {
            this.f = false;
            this.a = null;
            this.a.a.j = 0;
            this.a.b.j = 0;
            this.a.a.b("Movie cancelled by user...");
            return;
        }
        this.a = new ByteArrayInputStream(array);
        this.l = true;
        this.a.a.b("Playing Movie...");
    }
    
    public final void a() {
        if (this.e) {
            return;
        }
        this.m = true;
    }
    
    private final void m() {
        if (this.m) {
            this.m = false;
            this.a = new ByteArrayOutputStream();
            if (this.a.a.d == 0) {
                this.b = this.a.a();
            }
            else if (this.a.a.d == 1 && this.a == null) {
                (this.a = new ay()).a();
                this.a.b(this.a.a.h ? 0 : 1);
                this.a.a((float)this.a.a.e);
                this.a.a(0);
                this.a.a = this.a.a.a();
            }
            this.e = true;
            this.f = false;
            this.l = 0;
        }
        if (!this.e) {
            return;
        }
        if (this.g) {
            if (this.a.a.d == 0) {
                try {
                    aK.b(this.a, this.j);
                    aK.b(this.a, this.k);
                    this.l += 4;
                    this.a.close();
                    final ByteArrayOutputStream byteArrayOutputStream;
                    (byteArrayOutputStream = new ByteArrayOutputStream()).write(this.b);
                    byteArrayOutputStream.write("MOV".getBytes());
                    aK.a(byteArrayOutputStream, this.a.size());
                    byteArrayOutputStream.write(this.a.toByteArray());
                    byteArrayOutputStream.close();
                    ((aE)(Object)(this.a = byteArrayOutputStream.toByteArray())).a(new an(2, this.a));
                    this.a.a(true);
                    this.a = null;
                }
                catch (Exception ex2) {
                    this.a.a("Movie could not be saved...", true);
                }
            }
            else if (this.a.a.d == 1) {
                try {
                    this.a.close();
                    if (!this.a.a.saveAnimGif(this.a.a())) {
                        throw new Exception(this.a.a.lastBroadcastError);
                    }
                    final an an = new an(12, this.a);
                    this.a.a(true);
                    an.c = 10;
                    if (!this.a.a.j) {
                        an.a("Movie has been saved to server");
                    }
                    else {
                        an.a("Movie has been saved to server", "[Server-based movie size restriction active]");
                    }
                    this.a.a(an);
                }
                catch (Exception ex) {
                    final an an2 = new an(12, this.a);
                    this.a.a(true);
                    an2.c = 14;
                    if (this.a != null && this.a.a != null) {
                        an2.a(this.a.a);
                    }
                    else {
                        an2.a("Movie could not be saved...", ex.getMessage());
                    }
                    this.a.a(an2);
                }
                this.a = null;
            }
            this.e = false;
            this.f = false;
            this.g = false;
            this.k = 0;
            this.j = 0;
            return;
        }
        if (this.a.a.d == 1) {
            if (this.a.a.size() > this.a.a.g) {
                this.g = true;
            }
            return;
        }
        if (this.a.a.d == 0) {
            final int j;
            if ((j = (this.a.a.a << 8 | this.a.b.a)) == this.j) {
                if (this.k != 65535) {
                    ++this.k;
                    return;
                }
            }
            try {
                aK.b(this.a, this.j);
                aK.b(this.a, this.k);
                this.l += 4;
            }
            catch (Exception ex3) {}
            if (this.l > this.a.a.g) {
                this.g = true;
            }
            this.k = 1;
            this.j = j;
        }
    }
    
    private final void n() {
        if (this.l) {
            this.f = true;
            this.l = false;
        }
        if (!this.f) {
            return;
        }
        if (this.k >= 1) {
            --this.k;
            return;
        }
        int a;
        try {
            a = aK.a(this.a);
            this.a.a.j = (a & 0xFF00) >> 8;
            this.a.b.j = (a & 0xFF);
            this.k = aK.a(this.a);
            --this.k;
        }
        catch (Exception ex) {
            a = -1;
        }
        if (a == -1) {
            this.f = false;
            this.a.a.j = 0;
            this.a.b.j = 0;
            this.a.a.b("Movie ended...");
        }
    }
    
    public final void b() {
        if (!this.e) {
            return;
        }
        this.g = true;
    }
    
    public final void c() {
        this.a = true;
        this.c = true;
        final Thread thread;
        (thread = new Thread(this)).setPriority(4);
        thread.start();
    }
    
    public final void d() {
        if (!this.c) {
            return;
        }
        this.j();
        this.c = false;
    }
    
    private final void b(final boolean b) {
        if (!this.i) {
            return;
        }
        this.a.a.a(b);
    }
    
    public final boolean a() {
        return this.a & this.c & !this.b;
    }
    
    public final void a(final boolean b) {
        this.b = b;
        if (this.a.a != null) {
            this.a.a.a(b);
        }
    }
    
    public final void a(final InputStream inputStream) throws IOException, aO {
        final int b = aK.b(inputStream);
        final int n;
        if ((n = (int)(this.a.a & -1L)) != b) {
            this.a.a("State File CRC   = " + aK.a(b, 8));
            this.a.a("Current File CRC = " + aK.a(n, 8));
            throw new aO("Saved-state belongs to a different Game");
        }
        this.b = aK.c(inputStream);
        this.c = aK.c(inputStream);
        this.d = aK.c(inputStream);
        this.e = aK.c(inputStream);
        this.f = aK.c(inputStream);
        this.g = aK.a(inputStream);
        this.i = aK.c(inputStream);
        this.d = (inputStream.read() == 255);
        this.l();
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        if (this.a != null) {
            outputStream.write(this.a);
            this.a = null;
            return;
        }
        outputStream.write("CPU".getBytes());
        aK.a(outputStream, 13);
        aK.a(outputStream, (int)(this.a.a & -1L));
        aK.c(outputStream, this.b);
        aK.c(outputStream, this.c);
        aK.c(outputStream, this.d);
        aK.c(outputStream, this.e);
        aK.c(outputStream, this.f);
        aK.b(outputStream, this.g);
        aK.c(outputStream, this.i = 0);
        outputStream.write(this.d ? 255 : 0);
    }
    
    private final void o() {
        if (this.a.b() || this.d) {
            this.b(true);
            return;
        }
        if (this.a.b) {
            this.a.d();
        }
        if (this.a.a) {
            this.a.c();
        }
        if (this.h) {
            this.k();
        }
        if (this.j) {
            this.p();
        }
        this.n();
        this.a.a.b();
        this.a.b.b();
        this.m();
        this.a.a.b();
        for (int i = 0; i < 240; ++i) {
            this.a(this.a);
            if (this.a.a.a(i) != 0) {
                this.f();
            }
            this.a.a.a();
        }
        for (int j = 240; j <= 261; ++j) {
            if (j == 240) {
                final ag a = this.a.a;
                a.c |= 0x80;
                this.a(this.a - 56.0f);
                if (this.a.a.a()) {
                    this.e();
                }
                this.a(56.0f);
                if (this.a.a.a(j) != 0) {
                    this.f();
                }
            }
            else if (j == 261) {
                final ag a2 = this.a.a;
                a2.c &= 0x7F;
                this.a(this.a - 56.0f);
                final ag a3 = this.a.a;
                a3.c &= 0xBF;
                this.a(56.0f);
                if (this.a.a.a(j) != 0) {
                    this.f();
                }
            }
            else {
                this.a(this.a);
                if (this.a.a.a(j) != 0) {
                    this.f();
                }
            }
        }
        if ((this.a.a & 0xC0) == 0x0) {
            this.f();
        }
        this.b(false);
        final int d;
        if ((d = this.a.a.d) > 0 && this.a.a.g && !this.a.a.h && this.a.hasFocus() && !this.b) {
            final int n = this.a.a.a.getBufferSize() - 3 * d;
            this.a.a(0L);
            while (this.a.a.a.available() < n && !this.a.b() && this.a.a.g && !this.a.a.h && this.a.hasFocus() && !this.b) {
                this.a.a();
            }
            if (this.a.a.g) {
                this.a.a.e();
            }
        }
        if (this.a.b()) {
            return;
        }
        this.r();
        this.s();
    }
    
    public final void e() {
        final ag a = this.a.a;
        a.c |= 0x80;
        this.g(this.g);
        this.f((this.e & 0xEF) | 0x20);
        this.g = this.b(65530);
        this.i -= 7;
    }
    
    public final void f() {
        if ((this.e & 0x4) == 0x0) {
            this.g(this.g);
            this.f((this.e & 0xEF) | 0x20);
            this.g = this.b(65534);
            this.e |= 0x4;
            this.i -= 7;
        }
    }
    
    public final void a(final float n) {
        this.i += (int)n;
        int n2 = 0;
        while (this.i > 0) {
            if (this.d) {
                --this.i;
            }
            else if ((n2 += this.a()) > 4) {
                this.a.a.b(n2 + n2 / 2);
                n2 = 0;
            }
            if (this.k) {
                return;
            }
        }
    }
    
    private final int a() {
        if (this.d) {
            return 0;
        }
        final int a;
        switch (a = this.a(this.g++)) {
            case 0: {
                this.g(this.g + 1);
                this.f(this.e | 0x10);
                this.g = this.b(65534);
                this.e |= 0x4;
                this.e |= 0x10;
                break;
            }
            case 169: {
                this.b(this.b = this.b());
                break;
            }
            case 165: {
                this.b(this.b = this.a(this.f()));
                break;
            }
            case 181: {
                this.b(this.b = this.a(this.g()));
                break;
            }
            case 173: {
                this.b(this.b = this.a(this.c()));
                break;
            }
            case 189: {
                this.b(this.b = this.a(this.e()));
                break;
            }
            case 185: {
                this.b(this.b = this.a(this.d()));
                break;
            }
            case 161: {
                this.b(this.b = this.a(this.i()));
                break;
            }
            case 177: {
                this.b(this.b = this.a(this.j()));
                break;
            }
            case 162: {
                this.b(this.c = this.b());
                break;
            }
            case 166: {
                this.b(this.c = this.a(this.f()));
                break;
            }
            case 182: {
                this.b(this.c = this.a(this.h()));
                break;
            }
            case 174: {
                this.b(this.c = this.a(this.c()));
                break;
            }
            case 190: {
                this.b(this.c = this.a(this.d()));
                break;
            }
            case 160: {
                this.b(this.d = this.b());
                break;
            }
            case 164: {
                this.b(this.d = this.a(this.f()));
                break;
            }
            case 180: {
                this.b(this.d = this.a(this.g()));
                break;
            }
            case 172: {
                this.b(this.d = this.a(this.c()));
                break;
            }
            case 188: {
                this.b(this.d = this.a(this.e()));
                break;
            }
            case 133: {
                this.a(this.f(), this.b);
                break;
            }
            case 149: {
                this.a(this.g(), this.b);
                break;
            }
            case 141: {
                this.a(this.c(), this.b);
                break;
            }
            case 157: {
                this.a(this.e(), this.b);
                break;
            }
            case 153: {
                this.a(this.d(), this.b);
                break;
            }
            case 129: {
                this.a(this.i(), this.b);
                break;
            }
            case 145: {
                this.a(this.j(), this.b);
                break;
            }
            case 134: {
                this.a(this.f(), this.c);
                break;
            }
            case 150: {
                this.a(this.h(), this.c);
                break;
            }
            case 142: {
                this.a(this.c(), this.c);
                break;
            }
            case 132: {
                this.a(this.f(), this.d);
                break;
            }
            case 148: {
                this.a(this.g(), this.d);
                break;
            }
            case 140: {
                this.a(this.c(), this.d);
                break;
            }
            case 170: {
                this.b(this.c = this.b);
                break;
            }
            case 168: {
                this.b(this.d = this.b);
                break;
            }
            case 186: {
                this.b(this.c = (this.f & 0xFF));
                break;
            }
            case 138: {
                this.b(this.b = this.c);
                break;
            }
            case 154: {
                this.f = (this.c & 0xFF);
                break;
            }
            case 152: {
                this.b(this.b = this.d);
                break;
            }
            case 9: {
                this.b(this.b |= this.b());
                break;
            }
            case 5: {
                this.b(this.b |= this.a(this.f()));
                break;
            }
            case 21: {
                this.b(this.b |= this.a(this.g()));
                break;
            }
            case 13: {
                this.b(this.b |= this.a(this.c()));
                break;
            }
            case 29: {
                this.b(this.b |= this.a(this.e()));
                break;
            }
            case 25: {
                this.b(this.b |= this.a(this.d()));
                break;
            }
            case 1: {
                this.b(this.b |= this.a(this.i()));
                break;
            }
            case 17: {
                this.b(this.b |= this.a(this.j()));
                break;
            }
            case 41: {
                this.b(this.b &= this.b());
                break;
            }
            case 37: {
                this.b(this.b &= this.a(this.f()));
                break;
            }
            case 53: {
                this.b(this.b &= this.a(this.g()));
                break;
            }
            case 45: {
                this.b(this.b &= this.a(this.c()));
                break;
            }
            case 61: {
                this.b(this.b &= this.a(this.e()));
                break;
            }
            case 57: {
                this.b(this.b &= this.a(this.d()));
                break;
            }
            case 33: {
                this.b(this.b &= this.a(this.i()));
                break;
            }
            case 49: {
                this.b(this.b &= this.a(this.j()));
                break;
            }
            case 73: {
                this.b(this.b ^= this.b());
                break;
            }
            case 69: {
                this.b(this.b ^= this.a(this.f()));
                break;
            }
            case 85: {
                this.b(this.b ^= this.a(this.g()));
                break;
            }
            case 77: {
                this.b(this.b ^= this.a(this.c()));
                break;
            }
            case 93: {
                this.b(this.b ^= this.a(this.e()));
                break;
            }
            case 89: {
                this.b(this.b ^= this.a(this.d()));
                break;
            }
            case 65: {
                this.b(this.b ^= this.a(this.i()));
                break;
            }
            case 81: {
                this.b(this.b ^= this.a(this.j()));
                break;
            }
            case 36: {
                this.e(this.a(this.f()));
                break;
            }
            case 44: {
                this.e(this.a(this.c()));
                break;
            }
            case 10: {
                this.b = this.c(this.b);
                break;
            }
            case 6: {
                final int f = this.f();
                final int a2 = this.a(f);
                this.a(f, a2);
                this.a(f, this.c(a2));
                break;
            }
            case 22: {
                final int g = this.g();
                final int a3 = this.a(g);
                this.a(g, a3);
                this.a(g, this.c(a3));
                break;
            }
            case 14: {
                final int c = this.c();
                final int a4 = this.a(c);
                this.a(c, a4);
                this.a(c, this.c(a4));
                break;
            }
            case 30: {
                final int e = this.e();
                final int a5 = this.a(e);
                this.a(e, a5);
                this.a(e, this.c(a5));
                break;
            }
            case 74: {
                this.b = this.d(this.b);
                break;
            }
            case 70: {
                final int f2 = this.f();
                final int a6 = this.a(f2);
                this.a(f2, a6);
                this.a(f2, this.d(a6));
                break;
            }
            case 86: {
                final int g2 = this.g();
                final int a7 = this.a(g2);
                this.a(g2, a7);
                this.a(g2, this.d(a7));
                break;
            }
            case 78: {
                final int c2 = this.c();
                final int a8 = this.a(c2);
                this.a(c2, a8);
                this.a(c2, this.d(a8));
                break;
            }
            case 94: {
                final int e2 = this.e();
                final int a9 = this.a(e2);
                this.a(e2, a9);
                this.a(e2, this.d(a9));
                break;
            }
            case 42: {
                this.b = this.e(this.b);
                break;
            }
            case 38: {
                final int f3 = this.f();
                final int a10 = this.a(f3);
                this.a(f3, a10);
                this.a(f3, this.e(a10));
                break;
            }
            case 54: {
                final int g3 = this.g();
                final int a11 = this.a(g3);
                this.a(g3, a11);
                this.a(g3, this.e(a11));
                break;
            }
            case 46: {
                final int c3 = this.c();
                final int a12 = this.a(c3);
                this.a(c3, a12);
                this.a(c3, this.e(a12));
                break;
            }
            case 62: {
                final int e3 = this.e();
                final int a13 = this.a(e3);
                this.a(e3, a13);
                this.a(e3, this.e(a13));
                break;
            }
            case 106: {
                this.b = this.f(this.b);
                break;
            }
            case 102: {
                final int f4 = this.f();
                final int a14 = this.a(f4);
                this.a(f4, a14);
                this.a(f4, this.f(a14));
                break;
            }
            case 118: {
                final int g4 = this.g();
                final int a15 = this.a(g4);
                this.a(g4, a15);
                this.a(g4, this.f(a15));
                break;
            }
            case 110: {
                final int c4 = this.c();
                final int a16 = this.a(c4);
                this.a(c4, a16);
                this.a(c4, this.f(a16));
                break;
            }
            case 126: {
                final int e4 = this.e();
                final int a17 = this.a(e4);
                this.a(e4, a17);
                this.a(e4, this.f(a17));
                break;
            }
            case 144: {
                this.a(1, false);
                break;
            }
            case 176: {
                this.a(1, true);
                break;
            }
            case 208: {
                this.a(2, false);
                break;
            }
            case 240: {
                this.a(2, true);
                break;
            }
            case 16: {
                this.a(128, false);
                break;
            }
            case 48: {
                this.a(128, true);
                break;
            }
            case 80: {
                this.a(64, false);
                break;
            }
            case 112: {
                this.a(64, true);
                break;
            }
            case 76: {
                this.g = this.c();
                break;
            }
            case 108: {
                final int c5;
                if (((c5 = this.c()) & 0xFF) == 0xFF) {
                    this.g = (this.a(c5 & 0xFF00) << 8 | this.a(c5));
                    break;
                }
                this.g = this.b(c5);
                break;
            }
            case 32: {
                this.g(this.g + 1);
                this.g = this.c();
                break;
            }
            case 96: {
                this.g = this.l() + 1;
                break;
            }
            case 64: {
                this.e = this.k();
                this.g = this.l();
                break;
            }
            case 72: {
                this.f(this.b);
                break;
            }
            case 8: {
                this.f(this.e | 0x10 | 0x20);
                break;
            }
            case 104: {
                this.b(this.b = this.k());
                break;
            }
            case 40: {
                this.e = this.k();
                break;
            }
            case 24: {
                this.e &= 0xFE;
                break;
            }
            case 216: {
                this.e &= 0xF7;
                break;
            }
            case 88: {
                this.e &= 0xFB;
                break;
            }
            case 184: {
                this.e &= 0xBF;
                break;
            }
            case 56: {
                this.e |= 0x1;
                break;
            }
            case 248: {
                this.e |= 0x8;
                break;
            }
            case 120: {
                this.e |= 0x4;
                break;
            }
            case 230: {
                final int f5 = this.f();
                final int a18 = this.a(f5);
                this.a(f5, a18);
                this.a(f5, this.g(a18));
                break;
            }
            case 246: {
                final int g5 = this.g();
                this.a(g5, this.a(g5));
                this.a(g5, this.g(this.a(g5)));
                break;
            }
            case 238: {
                final int c6 = this.c();
                this.a(c6, this.a(c6));
                this.a(c6, this.g(this.a(c6)));
                break;
            }
            case 254: {
                final int e5 = this.e();
                this.a(e5, this.a(e5));
                this.a(e5, this.g(this.a(e5)));
                break;
            }
            case 232: {
                ++this.c;
                this.b(this.c &= 0xFF);
                break;
            }
            case 200: {
                ++this.d;
                this.b(this.d &= 0xFF);
                break;
            }
            case 198: {
                final int f6 = this.f();
                this.a(f6, this.a(f6));
                this.a(f6, this.h(this.a(f6)));
                break;
            }
            case 214: {
                final int g6 = this.g();
                this.a(g6, this.a(g6));
                this.a(g6, this.h(this.a(g6)));
                break;
            }
            case 206: {
                final int c7 = this.c();
                this.a(c7, this.a(c7));
                this.a(c7, this.h(this.a(c7)));
                break;
            }
            case 222: {
                final int e6 = this.e();
                this.a(e6, this.a(e6));
                this.a(e6, this.h(this.a(e6)));
                break;
            }
            case 202: {
                --this.c;
                this.b(this.c &= 0xFF);
                break;
            }
            case 136: {
                --this.d;
                this.b(this.d &= 0xFF);
                break;
            }
            case 105: {
                this.c(this.b());
                break;
            }
            case 101: {
                this.c(this.a(this.f()));
                break;
            }
            case 117: {
                this.c(this.a(this.g()));
                break;
            }
            case 109: {
                this.c(this.a(this.c()));
                break;
            }
            case 125: {
                this.c(this.a(this.e()));
                break;
            }
            case 121: {
                this.c(this.a(this.d()));
                break;
            }
            case 97: {
                this.c(this.a(this.i()));
                break;
            }
            case 113: {
                this.c(this.a(this.j()));
                break;
            }
            case 233:
            case 235: {
                this.d(this.b());
                break;
            }
            case 229: {
                this.d(this.a(this.f()));
                break;
            }
            case 245: {
                this.d(this.a(this.g()));
                break;
            }
            case 237: {
                this.d(this.a(this.c()));
                break;
            }
            case 253: {
                this.d(this.a(this.e()));
                break;
            }
            case 249: {
                this.d(this.a(this.d()));
                break;
            }
            case 225: {
                this.d(this.a(this.i()));
                break;
            }
            case 241: {
                this.d(this.a(this.j()));
                break;
            }
            case 201: {
                this.c(this.b, this.b());
                break;
            }
            case 197: {
                this.c(this.b, this.a(this.f()));
                break;
            }
            case 213: {
                this.c(this.b, this.a(this.g()));
                break;
            }
            case 205: {
                this.c(this.b, this.a(this.c()));
                break;
            }
            case 221: {
                this.c(this.b, this.a(this.e()));
                break;
            }
            case 217: {
                this.c(this.b, this.a(this.d()));
                break;
            }
            case 193: {
                this.c(this.b, this.a(this.i()));
                break;
            }
            case 209: {
                this.c(this.b, this.a(this.j()));
                break;
            }
            case 224: {
                this.c(this.c, this.b());
                break;
            }
            case 228: {
                this.c(this.c, this.a(this.f()));
                break;
            }
            case 236: {
                this.c(this.c, this.a(this.c()));
                break;
            }
            case 192: {
                this.c(this.d, this.b());
                break;
            }
            case 196: {
                this.c(this.d, this.a(this.f()));
                break;
            }
            case 204: {
                this.c(this.d, this.a(this.c()));
                break;
            }
            case 26:
            case 58:
            case 90:
            case 122:
            case 218:
            case 234:
            case 250: {
                break;
            }
            default: {
                this.d = true;
                --this.g;
                break;
            }
        }
        this.i -= this.a[a];
        return this.a[a];
    }
    
    private final void p() {
        this.j = false;
        this.a.a.a.d();
        this.a.a.b();
        this.a.a.a(this.a, this.a.a);
        this.a.a.a(this.a.a);
        this.l();
        this.q();
    }
    
    public final void g() {
        final long a = this.a.a;
        boolean b = false;
        if (a == 609847185L) {
            b = true;
        }
        if (b) {
            this.a.a.g = true;
            this.a.a.setCursor(new Cursor(13));
        }
    }
    
    public final void h() {
        if (this.a.a == null) {
            this.a = 116.0f;
            return;
        }
        final long a = this.a.a;
        switch (this.a.a.a()) {
            case 1: {
                if (a == 1657174404L) {
                    this.a = 170.0f;
                    return;
                }
            }
            case 4: {
                if (a == 2695935810L) {
                    this.a = 144.0f;
                    return;
                }
                if (a == 3453875657L) {
                    this.a = 120.0f;
                    return;
                }
                if (a == 395971927L) {
                    this.a = 136.0f;
                    return;
                }
                if (a == 36014521L) {
                    this.a = 136.0f;
                    return;
                }
            }
            case 7: {
                if (a == 664211676L) {
                    this.a = 111.0f;
                    return;
                }
                break;
            }
        }
        this.a = 116.0f;
    }
    
    public final void i() {
        this.j = true;
    }
    
    private final void q() {
        this.h();
        this.g();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 255;
        this.d = false;
        this.g = this.b(65532);
        this.a.a("Reset Occurred");
        this.a = new h(this.a);
        this.i -= 7;
    }
    
    public final void j() {
        this.k = true;
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
    }
    
    private final void r() {
        while (!this.a) {
            if (this.a.b()) {
                return;
            }
            this.b(true);
            if (this.k) {
                return;
            }
            if (this.a.a) {
                this.a.c();
            }
            if (this.a.b) {
                this.a.d();
            }
            try {
                Thread.sleep(13L);
            }
            catch (Exception ex) {}
        }
    }
    
    private final void s() {
        while (this.b) {
            this.b(true);
            if (this.k) {
                return;
            }
            if (this.a.b) {
                this.a.d();
            }
            if (this.a.a) {
                this.a.c();
            }
            try {
                Thread.sleep(13L);
            }
            catch (Exception ex) {}
        }
    }
    
    private final int a(final int n) {
        return this.a.a.a(n);
    }
    
    private final int b(final int n) {
        return this.a.a.b(n);
    }
    
    private final void a(final int n, final int n2) {
        this.a.a.b(n, n2);
    }
    
    private final int b() {
        return this.a(this.g++);
    }
    
    private final int c() {
        final int n = this.b(this.g) & 0xFFFF;
        this.g += 2;
        return n;
    }
    
    private final int d() {
        final int c;
        final int n = (c = this.c()) + this.d & 0xFFFF;
        this.b(c, n);
        return n;
    }
    
    private final int e() {
        final int c;
        final int n = (c = this.c()) + this.c & 0xFFFF;
        this.b(c, n);
        return n;
    }
    
    private final int f() {
        return this.a(this.g++) & 0xFFFF;
    }
    
    private final int g() {
        return this.a(this.g++) + this.c & 0xFF;
    }
    
    private final int h() {
        return this.a(this.g++) + this.d & 0xFF;
    }
    
    private final int i() {
        final int n = this.a(this.g++) + this.c & 0xFF;
        return (this.a(n + 1 & 0xFF) << 8) + this.a(n);
    }
    
    private final int j() {
        final int a = this.a(this.g++);
        final int n2;
        final int n = (n2 = (this.a(a + 1 & 0xFF) << 8) + this.a(a)) + this.d & 0xFFFF;
        this.b(n2, n);
        return n;
    }
    
    private final void b(final int n, final int n2) {
        if (((n2 ^ n) & 0x100) != 0x0) {
            --this.i;
        }
    }
    
    private final void b(final int n) {
        this.e &= 0x7D;
        this.e |= this.b[n];
    }
    
    private final int c(int n) {
        this.e &= 0x7C;
        this.e |= n >> 7;
        n = ((n <<= 1) & 0xFF);
        this.e |= this.b[n];
        return n;
    }
    
    private final int d(int n) {
        this.e &= 0x7C;
        this.e |= (n & 0x1);
        n >>= 1;
        this.e |= this.b[n];
        return n;
    }
    
    private final int e(int n) {
        n = ((n <<= 1) | (this.e & 0x1));
        this.e &= 0x7C;
        this.e |= n >> 8;
        n &= 0xFF;
        this.e |= this.b[n];
        return n;
    }
    
    private final int f(int n) {
        final int n2 = this.e & 0x1;
        this.e &= 0x7C;
        this.e |= (n & 0x1);
        n = ((n >>= 1) | n2 << 7);
        this.e |= this.b[n];
        return n;
    }
    
    private final int g(int n) {
        n = (++n & 0xFF);
        this.b(n);
        return n;
    }
    
    private final int h(int n) {
        n = (--n & 0xFF);
        this.b(n);
        return n;
    }
    
    private final void c(final int n) {
        final int n2 = this.b + n + (this.e & 0x1);
        this.e &= 0x3C;
        this.e |= ((((this.b ^ n) & 0x80) == 0x0 && ((this.b ^ n2) & 0x80) != 0x0) ? 64 : 0);
        this.e |= ((n2 > 255) ? 1 : 0);
        this.b = (n2 & 0xFF);
        this.e |= this.b[this.b];
    }
    
    private final void d(final int n) {
        final int n2 = this.b - n - (~this.e & 0x1);
        this.e &= 0x3C;
        this.e |= ((((this.b ^ n) & 0x80) != 0x0 && ((this.b ^ n2) & 0x80) != 0x0) ? 64 : 0);
        this.e |= ((n2 >= 0) ? 1 : 0);
        this.b = (n2 & 0xFF);
        this.e |= this.b[this.b];
    }
    
    private final void c(final int n, final int n2) {
        final int n3 = n - n2;
        this.e &= 0x7C;
        this.e |= ((n3 >= 0) ? 1 : 0);
        this.e |= this.b[n3 & 0xFF];
    }
    
    private final void e(final int n) {
        this.e &= 0x3D;
        this.e |= (n & 0xC0);
        this.e |= (((this.b & n) != 0x0) ? 0 : 2);
    }
    
    private final void a(final int n, final boolean b) {
        final byte b2 = (byte)this.a(this.g++);
        if ((this.e & n) != 0x0 == b) {
            this.b(this.g + b2, this.g);
            this.g += b2;
            --this.i;
        }
    }
    
    private final void f(final int n) {
        this.a(this.f + 256, n);
        --this.f;
        this.f &= 0xFF;
    }
    
    private final int k() {
        ++this.f;
        this.f &= 0xFF;
        return this.a(this.f + 256);
    }
    
    private final void g(final int n) {
        this.f(n >> 8 & 0xFF);
        this.f(n & 0xFF);
    }
    
    private final int l() {
        return this.k() + this.k() * 256;
    }
}
