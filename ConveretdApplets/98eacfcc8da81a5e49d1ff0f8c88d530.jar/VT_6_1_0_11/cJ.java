// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.StringTokenizer;
import java.io.IOException;
import com.hw.client.util.a;
import com.hw.client.util.d;
import java.io.InputStream;
import com.hw.client.util.e;

public final class cJ extends e implements du
{
    private int e;
    private boolean f;
    private int g;
    private boolean h;
    private int i;
    private cf j;
    public static cJ a;
    private bv k;
    private InputStream l;
    private cI m;
    private boolean n;
    private bR o;
    private InputStream p;
    private dv q;
    private dv r;
    private dc s;
    private long t;
    private long w;
    private long x;
    private int y;
    private int z;
    int b;
    private z A;
    long c;
    long d;
    private long B;
    
    public cJ(final String s) {
        this.f = false;
        this.g = 0;
        this.i = 0;
        this.n = false;
        this.x = 0L;
        this.b = 0;
        this.c = 0L;
        this.d = 0L;
        this.B = System.currentTimeMillis();
        (this.k = new bv(this, s)).a(new bN(this));
    }
    
    public final void b() {
        this.e = 0;
        this.k.b();
    }
    
    public final void c() {
        try {
            this.a();
        }
        catch (Exception ex) {}
        try {
            this.m();
        }
        catch (Exception ex2) {}
        try {
            if (this.k != null) {
                this.k.c();
            }
            this.k = null;
        }
        catch (Exception ex3) {}
    }
    
    public final void a(final cf j) {
        this.j = j;
    }
    
    public final void a(final int n) {
        if (this.e == 2) {
            this.k.a(n);
            return;
        }
        if (this.e == 1) {
            this.e = 4;
            this.k.b();
            this.k.a(false, true, '\0', '\0', 8000);
            this.k.a(n);
            this.k.a(dx.g());
            this.k.a(dx.f());
        }
    }
    
    public final void d() {
        if (this.e == 2) {
            this.k.a(dx.e());
            return;
        }
        if (this.e == 1) {
            this.e = 3;
            this.k.b();
            this.k.a(false, true, '\0', '\0', 8000);
            this.k.a(dx.e());
        }
    }
    
    public final void a(final String s, final String s2, final String s3) {
        if (this.e == 2) {
            this.k.a(s, s2, s3);
        }
    }
    
    public final void a(final InputStream inputStream, final dv dv) {
        final int d = dv.d();
        final int c = dv.c();
        if (d == 1 || d == 2) {
            if (c == 12) {
                try {
                    new x(inputStream);
                }
                catch (y y) {
                    com.hw.client.util.a.b("DoorAudioManager2.setPlayInputStream: could not read wav header", y);
                }
                catch (IOException ex) {
                    com.hw.client.util.a.b("DoorAudioManager2.setPlayInputStream: could not read wav header", ex);
                }
            }
            this.l = inputStream;
            this.q = dv;
            this.y = 20 * this.q.g() / 1000;
        }
        else if (d == 62) {
            if (c == 25 || c == 21) {
                this.m = new cI(inputStream);
                this.n = false;
                this.l = null;
                this.q = dv;
                if (dv.g() <= 12000) {
                    this.y = 160;
                }
                else if (dv.g() <= 24000) {
                    this.y = 320;
                }
                else {
                    this.y = 640;
                }
            }
            else {
                if (c == 1) {
                    throw new IllegalArgumentException("Speex Raw format not supported");
                }
                throw new IllegalArgumentException("Format not supported: encoding=" + d + ", fileformat=" + c);
            }
        }
        else if (d == 42) {
            this.l = inputStream;
            this.q = dv;
            this.y = 160;
        }
        else {
            if (d != 41) {
                throw new IllegalArgumentException("The given audioFormat specifies an unknown encoding: " + d + " (" + bT.v[d] + ")");
            }
            this.l = new bp(inputStream, 2048, true);
            this.q = dv;
            this.y = 160;
        }
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("DoorAudioManager2.setPlayInputStream: audioformat sampleRate=" + dv.g() + ", blockSize=" + dv.l() + ", sampleSize=" + dv.k());
        }
        if (bj.c()) {
            this.z = 300 * this.q.g() / 1000;
            return;
        }
        this.z = 200 * this.q.g() / 1000;
    }
    
    public final void a(final long n) {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("startPlay samples=" + n);
        }
        if (this.e == 1) {
            this.b(n);
            this.g = 1;
            this.a(this.f = false, true, this.q);
            if (this.j != null) {
                this.j.g();
            }
            this.s = new dc(this);
            return;
        }
        com.hw.client.util.a.d("DoorAudioManager2.startPlay could not start play, invalid state=" + this.e);
    }
    
    private void a(final boolean b, final boolean b2, final dv dv) {
        try {
            this.h = false;
            if (cJ.a != null) {
                com.hw.client.util.a.d("DoorAudioManager2.startAudio calling pause on active AudioManager");
                try {
                    final cJ a;
                    if ((a = cJ.a).e == 2 && !a.f) {
                        if (a.g == 1) {
                            cJ.a.e();
                        }
                        else if (a.g == 2) {
                            cJ.a.i();
                        }
                    }
                }
                catch (Exception ex) {
                    com.hw.client.util.a.a("Exception caught when trying to pause activeAudioManager", ex);
                }
            }
        }
        catch (Exception ex2) {
            com.hw.client.util.a.b("Exception while trying to pause other audio manager", ex2);
            throw new IllegalStateException("Exception while trying to pause other audio manager");
        }
        cJ.a = this;
        int d = 1;
        String e = null;
        int g = 8000;
        if (dv != null) {
            d = dv.d();
            e = dv.e();
            g = dv.g();
        }
        if (e == null) {
            e = "";
        }
        char c = '\0';
        char c2 = '\0';
        if (d == 1 || d == 2) {
            c = '\0';
        }
        else if (d == 42 || d == 41) {
            c = '\u0001';
        }
        else if (d == 62) {
            c = '\u0002';
            final StringTokenizer stringTokenizer = new StringTokenizer(e, " ,;\t");
            int int1 = 3;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken;
                if ((nextToken = stringTokenizer.nextToken()).startsWith("quality")) {
                    final StringTokenizer stringTokenizer2;
                    (stringTokenizer2 = new StringTokenizer(nextToken, "= \t")).nextToken();
                    int1 = Integer.parseInt(stringTokenizer2.nextToken());
                }
            }
            c2 = (char)int1;
        }
        final char c3 = c;
        final char c4 = c2;
        final int n = g;
        final char c5 = c4;
        final char c6 = c3;
        if (this.e == 1) {
            final C a2 = C.a();
            this.b = 0;
            this.k.b();
            this.k.a(dx.b(a2.k()));
            this.k.a(b, false, c6, c5, n);
            this.k.a(a2.c(), a2.d(), a2.e());
            if (b2) {
                this.k.a(dx.a());
            }
            if (a2.h()) {
                a2.b(false);
                this.k.a(a2.b().getValue());
            }
            this.k.a(dx.f());
            this.k.a(dx.g());
            this.e = 2;
        }
    }
    
    private void b(final long n) {
        int n2 = 0;
        final int d = this.q.d();
        final int c = this.q.c();
        Label_0200: {
            Label_0126: {
                if (d == 62) {
                    if (c != 21) {
                        if (c != 25) {
                            break Label_0126;
                        }
                    }
                    try {
                        while (n2 < n && this.m.d()) {
                            if (!this.n) {
                                this.n = true;
                                this.m.a();
                            }
                            this.m.c();
                            n2 += this.y;
                        }
                        this.m.b();
                        break Label_0200;
                    }
                    catch (IOException ex) {
                        com.hw.client.util.a.b("DoorAudioManager2.skipSamplesInPlayStream: ioexc while skipping packets in OggSpeex stream:", ex);
                        throw new IllegalStateException("IOExc was thrown in DoorAudioManager2.skipSamplesInPlayStream");
                    }
                }
            }
            if (d != 42) {
                if (d != 41) {
                    break Label_0200;
                }
            }
            try {
                while (n2 < n && this.l.available() >= 33) {
                    this.l.skip(33L);
                    n2 += this.y;
                }
            }
            catch (IOException ex2) {
                com.hw.client.util.a.b("DoorAudioManager2.skipSamplesInPlayStream: ioexc while skipping packets in GSM stream:", ex2);
                throw new IllegalStateException("IOExc was thrown in DoorAudioManager2.skipSamplesInPlayStream");
            }
        }
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("DoorAudioManager2.skipSamplesInPlayStream: samples=" + n + ", skipped=" + n2);
        }
        this.w = n2;
        this.x = n2;
        this.d = this.w;
    }
    
    public final void e() {
        if (this.e == 2 && this.g == 1 && !this.f) {
            this.f = true;
            this.m();
            if (this.j != null) {
                this.j.d(this.w);
            }
        }
    }
    
    public final void f() {
        if (this.e == 1 && this.g == 1 && this.f) {
            this.a(this.f = false, true, this.q);
            if (this.j != null) {
                this.j.h();
            }
        }
    }
    
    public final void g() {
        if (this.e == 2 && this.g == 1) {
            this.s.a();
            this.m();
        }
        else if (this.e == 1 && this.g == 1 && this.f) {
            this.s.a();
            this.f = false;
            this.g = 0;
            this.w = this.x - this.b;
        }
        if (this.j != null) {
            this.j.e(this.w);
            if (this.h) {
                this.j.f();
            }
        }
    }
    
    public final InputStream h() {
        return this.p;
    }
    
    public final void a(final dv r) {
        com.hw.client.util.a.c("DoorAudioManager2.startRecord: audioformat sampleRate=" + r.g() + ", blockSize=" + r.l() + ", sampleSize=" + r.k());
        if (this.e == 1 && !this.f) {
            this.r = r;
            this.g = 2;
            final long n = 0L;
            this.c = n;
            this.t = n;
            try {
                this.o = new bR(131072);
                this.p = this.o;
                if (this.r.d() != 42) {
                    if (this.r.d() == 41) {
                        this.p = new ao(this.o);
                        final byte[] array = new byte[60];
                        new dF(-1, this.r.g()).b(array, 0);
                        ((ch)this.p).a(array, true);
                    }
                    else if (this.r.d() == 62) {
                        int n2 = 0;
                        if (this.r.g() > 12000) {
                            n2 = 1;
                        }
                        if (this.r.g() > 24000) {
                            n2 = 2;
                        }
                        this.A = new z(n2, this.r.g(), this.r.f(), 1, false, 10);
                        this.o();
                    }
                }
                this.a(true, false, this.r);
            }
            catch (IOException ex) {
                com.hw.client.util.a.b("DoorAudioManager2.startRecord: IOExc", ex);
                throw new IllegalStateException("DoorAudioManager2.startRecord: should not catch an ioexception here");
            }
            if (this.j != null) {
                this.j.e();
            }
        }
    }
    
    public final void i() {
        if (this.e == 2 && this.g == 2 && !this.f) {
            this.f = true;
            this.m();
            if (this.j != null) {
                this.j.f(this.t);
            }
        }
    }
    
    public final void j() {
        if (this.e == 1 && this.g == 2 && this.f) {
            this.a(true, this.f = false, this.r);
        }
        if (this.j != null) {
            this.j.i();
        }
    }
    
    public final void k() {
        if (this.e == 2 && this.g == 2) {
            this.m();
            this.n();
        }
        else if (this.e == 1 && this.g == 2 && this.f) {
            this.f = false;
            this.g = 0;
            this.n();
        }
        if (this.j != null) {
            this.j.c(this.t);
        }
    }
    
    private void m() {
        if (cJ.a == this) {
            cJ.a = null;
        }
        if (this.e == 2) {
            this.k.a(dx.b());
            this.b = 0;
            this.e = 1;
            this.k.c();
        }
    }
    
    private void n() {
        try {
            if (this.r.d() == 62) {
                this.A.a();
                this.o();
            }
            this.o.a();
        }
        catch (IOException ex) {
            com.hw.client.util.a.b("should not catch an ioexception here", ex);
            throw new IllegalStateException("should not catch an ioexception here");
        }
    }
    
    private void o() {
        ck a;
        while ((a = this.A.a(false)) != null) {
            this.o.a(a.f());
            if (a.b()) {
                this.A.b();
                return;
            }
        }
        this.A.b();
    }
    
    private static void b(final byte[] array) {
        for (int i = 0; i < array.length; i += 2) {
            final byte b = array[i];
            array[i] = array[i + 1];
            array[i + 1] = b;
        }
    }
    
    public final void a(final byte[] array) {
        if (this.e == 2) {
            this.k.a(dx.a(array, 0, (short)array.length));
        }
    }
    
    public final int l() {
        return this.e;
    }
    
    public final void finalize() {
        this.c();
        super.finalize();
    }
    
    static int a(final cJ cj) {
        return cj.e;
    }
    
    static bv b(final cJ cj) {
        return cj.k;
    }
    
    static int a(final cJ cj, final int e) {
        return cj.e = e;
    }
    
    static cf c(final cJ cj) {
        return cj.j;
    }
    
    static int d(final cJ cj) {
        return cj.y;
    }
    
    static int e(final cJ cj) {
        return cj.z;
    }
    
    static boolean a(final cJ cj, final boolean b) {
        return cj.h = true;
    }
    
    static int f(final cJ cj) {
        return cj.g;
    }
    
    static boolean g(final cJ cj) {
        return cj.f;
    }
    
    static long a(final cJ cj, final long n) {
        return cj.x += n;
    }
    
    static dv h(final cJ cj) {
        return cj.q;
    }
    
    static void a(final cJ cj, final byte[] array) {
        b(array);
    }
    
    static InputStream i(final cJ cj) {
        return cj.l;
    }
    
    static boolean j(final cJ cj) {
        return cj.n;
    }
    
    static boolean b(final cJ cj, final boolean b) {
        return cj.n = true;
    }
    
    static cI k(final cJ cj) {
        return cj.m;
    }
    
    static int l(final cJ cj) {
        return cj.i++;
    }
    
    static int m(final cJ cj) {
        return cj.i;
    }
    
    static void b(cJ cj, int n) {
        final cJ cj2 = cj;
        n = n;
        cj = cj2;
        com.hw.client.util.a.d("AudioProxy: received TRANSITION_CB_UNAVAILABLE: state=" + cj.e);
        if (cj.e == 0) {
            if (cj.j != null) {
                cj.j.j();
            }
        }
        else {
            if (cj.g == 2) {
                cj.k();
            }
            else if (cj.g == 1) {
                cj.g();
            }
            cj.j.a(1, "from agent: CB_UNAVAILABLE, errType=" + n);
        }
        cj.e = 1;
        cj.f = false;
        cj.g = 0;
        cj.k.c();
    }
    
    static long b(final cJ cj, final long n) {
        return cj.t += n;
    }
    
    static dv n(final cJ cj) {
        return cj.r;
    }
    
    static void b(cJ cj, byte[] array) {
        final cJ cj2 = cj;
        array = array;
        cj = cj2;
        final int d;
        if ((d = cj2.r.d()) == 1) {
            cj.o.a(array);
            return;
        }
        if (d == 2) {
            b(array);
            cj.o.a(array);
            return;
        }
        if (d == 62) {
            cj.A.a(array);
            cj.o();
            return;
        }
        if (d == 42 || d == 41) {
            cj.o.a(array);
            return;
        }
        throw new IllegalStateException("DoorAudioManager2.appendAudio - Unsupported encoding=" + d);
    }
    
    static long o(final cJ cj) {
        return cj.t;
    }
    
    static long c(final cJ cj, final long w) {
        return cj.w = w;
    }
    
    static long p(final cJ cj) {
        return cj.x;
    }
    
    static long q(final cJ cj) {
        return cj.w;
    }
    
    static long r(final cJ cj) {
        return cj.B;
    }
    
    static long d(final cJ cj, final long b) {
        return cj.B = b;
    }
    
    static int c(final cJ cj, final int n) {
        int n2;
        if ((n2 = (n + 40) * 10 / 3) > 100) {
            n2 = 100;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        return n2;
    }
}
