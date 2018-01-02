// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
import com.hw.client.util.a;
import java.io.InputStream;

public class R implements cf
{
    protected du a;
    protected int b;
    protected boolean c;
    protected dv d;
    protected dv e;
    protected long f;
    private long i;
    private long j;
    private InputStream k;
    private boolean l;
    protected boolean g;
    protected double h;
    
    public R(final du a) {
        this.b = 3;
        this.j = -1L;
        this.g = false;
        this.a = a;
    }
    
    public final void a(final long i) {
        if (this.b != 6) {
            com.hw.client.util.a.d("AudioController.playProgress: Not in PLAYING_STATE, samples=" + i);
            return;
        }
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("AudioController.playProgress, samples=" + i);
        }
        this.i = i;
        this.u();
    }
    
    private void u() {
        if (this.d.i() > 0) {
            this.b(c(1.0 * (0L + this.i) / this.d.i()));
        }
    }
    
    public final dv a() {
        return this.d;
    }
    
    public final dv b() {
        return this.e;
    }
    
    public void b(final long n) {
        this.f = n;
        if (this.b != 4) {
            if (com.hw.client.util.a.c()) {
                com.hw.client.util.a.a("AudioController.recordProgress, samples=" + n);
            }
            return;
        }
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("AudioController.recordProgress, samples=" + n);
        }
        if (this.j > 0L) {
            this.v();
            this.f = n;
            if (n >= this.j) {
                this.l = true;
                SwingUtilities.invokeLater(new L(this));
            }
        }
    }
    
    private void v() {
        if (this.j > 0L) {
            this.a(c(1.0 * this.f / this.j));
        }
    }
    
    public void a(final int n) {
    }
    
    public void b(final int n) {
    }
    
    public void a(final List list) {
    }
    
    public void f() {
    }
    
    public final void g() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.playStarted");
        }
        this.c(6);
        this.g = false;
    }
    
    public final void d(final long i) {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.playPaused");
        }
        this.i = i;
        this.u();
        this.c(7);
    }
    
    public final void h() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.playResumed");
        }
        this.c(6);
    }
    
    public final void e(final long i) {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.playStopped, samples=" + i);
        }
        this.i = i;
        try {
            this.k.reset();
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("AudioController.playStopped: ioexc:", ex);
        }
        this.c(3);
    }
    
    public void e() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.recordStarted");
        }
        this.c(4);
    }
    
    public final void f(final long f) {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.recordPaused samples=" + f);
        }
        this.v();
        this.f = f;
        this.c(5);
    }
    
    public final void i() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.recordResumed");
        }
        this.c(4);
    }
    
    public void c(final long f) {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("AudioController.recordStopped samples=" + f);
        }
        this.f = f;
        this.c(3);
        this.a(0.0);
        if (this.l) {
            this.o();
        }
    }
    
    public void a(final int n, final String s) {
    }
    
    public final void j() {
    }
    
    public final void k() {
        this.c(3);
    }
    
    public void c() {
    }
    
    public void d() {
    }
    
    public void l() {
    }
    
    public void m() {
    }
    
    public void n() {
    }
    
    public void o() {
    }
    
    public void a(final double n) {
    }
    
    public void b(final double n) {
    }
    
    public final void g(final long j) {
        this.j = j;
    }
    
    public final long p() {
        return this.j;
    }
    
    public final int q() {
        return this.b;
    }
    
    private void c(final int b) {
        switch (this.b = b) {
            case 3: {
                this.c();
            }
            case 4: {
                this.l();
            }
            case 5: {
                this.m();
            }
            case 6: {
                this.d();
            }
            case 7: {
                this.n();
                break;
            }
        }
    }
    
    protected final boolean r() {
        return this.c;
    }
    
    public final void s() {
        switch (this.b) {
            case 6:
            case 7: {
                this.a.g();
            }
            case 4:
            case 5: {
                this.a.k();
                break;
            }
        }
    }
    
    protected final void t() {
        switch (this.b) {
            case 6: {
                this.a.e();
            }
            case 4: {
                this.a.i();
            }
            case 5: {
                this.a.j();
            }
            case 7: {
                if (!this.g) {
                    this.a.f();
                    return;
                }
                this.s();
                this.a(this.k, this.d, this.h);
                break;
            }
        }
    }
    
    protected final void a(final InputStream k, final dv d, final double n) {
        long n2;
        if ((n2 = (long)(n * d.i())) < 0L) {
            n2 = 0L;
        }
        final long n3 = n2;
        if (this.b != 3 && this.b != 7) {
            this.s();
        }
        (this.k = k).mark(900000000);
        this.d = d;
        this.a.a(k, d);
        this.a.a(n3);
    }
    
    protected final InputStream a(final dv e) {
        if (this.b == 5) {
            this.t();
            return this.a.h();
        }
        this.l = false;
        this.e = e;
        this.a.a(e);
        final InputStream h = this.a.h();
        this.c = (h != null);
        return h;
    }
    
    private static double c(final double n) {
        return Math.max(Math.min(n, 1.0), 0.0);
    }
    
    public R() {
    }
    
    public static du a(final Applet applet) {
        String s;
        try {
            if (a.b()) {
                a.c("getCodeBase()=" + applet.getCodeBase());
            }
            if (a.b()) {
                a.c("getDocumentBase()=" + applet.getDocumentBase());
            }
            final String parameter;
            if ((parameter = applet.getParameter("door_repository")) == null) {
                s = new URL(applet.getCodeBase(), ca.a(applet)).toString() + "/doors/";
            }
            else {
                s = new URL(applet.getCodeBase(), parameter).toExternalForm();
            }
        }
        catch (MalformedURLException ex) {
            throw new IllegalArgumentException("could not build AudioManager - problems in params");
        }
        final String s2 = s;
        a.c("AudioManagerFactory.createAudioManager: doorRepository=" + s2);
        return new cJ(s2);
    }
}
