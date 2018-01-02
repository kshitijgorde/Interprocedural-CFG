import java.math.BigInteger;
import mindbright.security.KeyPair;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.util.Vector;
import java.net.InetAddress;

// 
// Decompiled by Procyon v0.5.30
// 

public class b5 extends ca
{
    public b2 lw;
    public InetAddress lv;
    public InetAddress lu;
    public InetAddress kx;
    public String lt;
    public int ls;
    public int lr;
    public Vector lq;
    public Vector lp;
    public String lo;
    public b7 fq;
    public bw km;
    public as gx;
    public b_ g1;
    public bo c;
    public Socket ln;
    public BufferedInputStream lm;
    public BufferedOutputStream ll;
    public boolean lk;
    public boolean fz;
    public boolean lj;
    public boolean li;
    public boolean lh;
    public int lg;
    public boolean lf;
    
    public b5(final as gx, final b_ g1) {
        this.lh = false;
        this.lf = true;
        this.g1 = g1;
        this.gx = gx;
        this.c = g1.f2();
        this.lt = null;
        this.li = false;
        try {
            this.kx = InetAddress.getByName("0.0.0.0");
        }
        catch (UnknownHostException ex) {
            if (this.c != null) {
                this.c.h9("FATAL: Could not create local InetAddress: " + ex.getMessage());
            }
        }
        this.g1();
    }
    
    public final void lq(final bw bw) {
        this.km = bw;
        if (this.fq != null) {
            this.fq.km = bw;
        }
    }
    
    public final bw lp() {
        return this.km;
    }
    
    public final InetAddress lo() {
        return this.lv;
    }
    
    public final InetAddress ln() {
        if (this.lu == null) {
            return this.lv;
        }
        return this.lu;
    }
    
    public final void lm(final InetAddress lu) {
        this.lu = lu;
    }
    
    public final InetAddress ll() {
        return this.kx;
    }
    
    public final void lk(final String s) throws UnknownHostException {
        this.kx = InetAddress.getByName(s);
    }
    
    public final String lj() {
        return this.lt;
    }
    
    public final void go(final int n, final String s, final int n2, final String s2) throws IOException {
        this.go(this.kx.getHostAddress(), n, s, n2, s2);
    }
    
    public final void go(final String s, final int n, final String s2, final int n2, final String s3) throws IOException {
        this.li(s, n);
        this.lq.addElement(new b1(s, n, s2, n2, s3));
        if (this.lj) {
            try {
                this.kv(s, n, s2, n2, s3);
            }
            catch (IOException ex) {
                this.li(s, n);
                throw ex;
            }
        }
    }
    
    public final void li(final String s, final int n) {
        if (n == -1) {
            if (this.lj) {
                this.fq.lu();
            }
            this.lq = new Vector();
        }
        else {
            int i = 0;
            while (i < this.lq.size()) {
                final b1 b1 = this.lq.elementAt(i);
                if (b1.fe == n && b1.lc.equals(s)) {
                    this.lq.removeElementAt(i);
                    if (this.lj) {
                        this.fq.lv(b1.lc, b1.fe);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    public final void gn(final int n, final String s, final int n2, final String s2) {
        this.lh(n);
        this.lp.addElement(new b0(n, s, n2, s2));
    }
    
    public final void lh(final int n) {
        if (n == -1) {
            this.lp = new Vector();
        }
        else {
            for (int i = 0; i < this.lp.size(); ++i) {
                if (((b0)this.lp.elementAt(i)).ff == n) {
                    this.lp.removeElementAt(i);
                    break;
                }
            }
        }
    }
    
    public final void lh(final String s) {
        for (int i = 0; i < this.lp.size(); ++i) {
            if (((b0)this.lp.elementAt(i)).lb.equals(s)) {
                this.lp.removeElementAt(i);
                --i;
            }
        }
    }
    
    public final void g1() {
        this.lq = new Vector();
        this.lp = new Vector();
    }
    
    public final void lg(final long n) {
        new Thread(new b3(this, n)).start();
    }
    
    public final void lf() {
        if (this.fq != null) {
            this.fq.ls("exit");
        }
        else if (this.c != null) {
            this.c.i8(this, false);
        }
    }
    
    public final void le(final long n) {
        try {
            this.fq.le(n);
        }
        catch (InterruptedException ex) {
            if (this.c != null) {
                this.c.h9("Error when shutting down SSHClient: " + ex.getMessage());
            }
            this.fq.l3();
        }
        try {
            if (this.ln != null) {
                this.ln.close();
            }
        }
        catch (IOException ex2) {}
    }
    
    public void jm(final String lo, final boolean b, final long n) throws IOException {
        this.lo = lo;
        this.ld(false);
        if (b) {
            this.lg(n);
        }
        else {
            this.le(n);
        }
    }
    
    public final void ld(final boolean b) throws IOException {
        try {
            Thread.currentThread();
            if (this.c != null) {
                this.c.jh(this);
            }
            final String gb = this.g1.gb();
            if (this.c != null) {
                this.c.jg(this);
            }
            this.ln = this.g1.f9();
            if (this.ln == null) {
                this.lv = InetAddress.getByName(gb);
                if (this.g1.f4()) {
                    int i = 1023;
                    while (i > 512) {
                        try {
                            this.ln = new Socket(this.lv, this.g1.ga(), this.kx, i);
                            break;
                        }
                        catch (IOException ex) {
                            if (ex.getMessage().toLowerCase().indexOf("use") == -1) {
                                throw ex;
                            }
                            --i;
                        }
                    }
                    if (i == 512) {
                        throw new IOException("No available privileged ports");
                    }
                }
                else {
                    this.ln = new Socket(this.lv, this.g1.ga());
                }
            }
            else {
                this.lv = this.ln.getInetAddress();
                if (this.c != null) {
                    this.c.jf("Connecting through proxy at " + this.lv.getHostAddress() + ":" + this.ln.getPort());
                }
            }
            this.lm = new BufferedInputStream(this.ln.getInputStream(), 8192);
            this.ll = new BufferedOutputStream(this.ln.getOutputStream());
            this.lb();
            this.fz = true;
            if (this.c != null) {
                this.c.fb(this);
            }
            final String gm = this.gx.gm(this.g1);
            this.la();
            this.k_();
            super.g6 = this.gx.gi(this.g1);
            if (!super.mj(super.g6)) {
                throw new IOException("Sorry, server does not support the '" + ca.mm[this.gx.gi(this.g1)][1] + "' cipher.");
            }
            super.ma();
            this.k9();
            super.l9();
            this.k8(super.g6);
            this.k7(gm);
            this.fq = new b7(this, this.lm, this.ll, super.f7, super.mc, this.km, b);
            this.k0();
            if (this.km != null) {
                this.km.eq(this.fq, super.f7);
            }
            this.lj = true;
            if (this.c != null) {
                this.c.i9(this);
            }
            this.kq(this.g1.f6());
            this.fq.ei();
        }
        catch (IOException ex2) {
            if (this.ln != null) {
                this.ln.close();
            }
            this.lc(false);
            if (this.fq != null) {
                this.fq.lu();
            }
            this.fq = null;
            throw ex2;
        }
    }
    
    public final void lc(final boolean lk) {
        if (!this.fz) {
            return;
        }
        this.fz = false;
        this.lj = false;
        this.lk = lk;
        this.lt = null;
        this.kq(0);
        if (this.c != null) {
            this.c.i8(this, lk);
        }
    }
    
    public final void lb() throws IOException {
        final byte[] array = new byte[256];
        this.lt = new String(array, 0, this.lm.read(array));
        try {
            final int index = this.lt.indexOf(45);
            final int index2 = this.lt.indexOf(46);
            this.ls = Integer.parseInt(this.lt.substring(index + 1, index2));
            final int n = index2;
            final int index3 = this.lt.indexOf(45, n);
            if (index3 == -1) {
                this.lr = Integer.parseInt(this.lt.substring(n + 1));
            }
            else {
                this.lr = Integer.parseInt(this.lt.substring(n + 1, index3));
            }
        }
        catch (Throwable t) {
            throw new IOException("Server version string invalid: " + this.lt);
        }
        if (this.ls > 1) {
            throw new IOException("MindTerm do not support SSHv2 yet, enable SSHv1 compatibility in server");
        }
        if (this.ls < 1 || this.lr < 5) {
            throw new IOException("Server's protocol version (" + this.ls + "-" + this.lr + ") is too old, please upgrade");
        }
        this.lt = this.lt.trim();
        this.ll.write((String.valueOf(ca.ms(true)) + "\n").getBytes());
        this.ll.flush();
    }
    
    public final void la() throws IOException {
        final ax ax = new ax(2, null);
        ax.h_(this.lm);
        ax.read(super.mb = new byte[8], 0, 8);
        ax.readInt();
        super.ma = new KeyPair(new ce(ax.jx(), ax.jx()), null);
        ax.readInt();
        super.l9 = new KeyPair(new ce(ax.jx(), ax.jx()), null);
        if (Math.abs(super.ma.fs().m4() - super.l9.fs().m4()) < 24) {
            throw new IOException("Invalid server keys, difference in sizes must be at least 24 bits");
        }
        if (!this.gx.gf(super.l9.fs())) {
            throw new IOException("Verification of known hosts failed");
        }
        super.l8 = ax.readInt();
        super.l7 = ax.readInt();
        super.l6 = ax.readInt();
        if ((super.l6 & 0x10000) != 0x0) {
            super.l6 = ((super.l6 & 0xFFFF) | 0x100);
        }
    }
    
    public final void k9() {
        final cd mf = ca.mf();
        mf.nextBytes(super.mf = new byte[32]);
        mf.my();
    }
    
    public final void k8(final int n) throws IOException {
        final byte[] array = new byte[super.mf.length + 1];
        array[0] = 0;
        System.arraycopy(super.mf, 0, array, 1, super.mf.length);
        for (int i = 0; i < super.me.length; ++i) {
            final byte[] array2 = array;
            final int n2 = i + 1;
            array2[n2] ^= super.me[i];
        }
        final BigInteger bigInteger = new BigInteger(array);
        BigInteger bigInteger2;
        if (super.ma.fs().m4() < super.l9.fs().m4()) {
            bigInteger2 = new cg(super.l9).m8(cg.m5(new cg(super.ma).m8(cg.m5(bigInteger, super.ma.fs().m4(), ca.mf())), super.l9.fs().m4(), ca.mf()));
        }
        else {
            bigInteger2 = new cg(super.ma).m8(cg.m5(new cg(super.l9).m8(cg.m5(bigInteger, super.l9.fs().m4(), ca.mf())), super.ma.fs().m4(), ca.mf()));
        }
        final av av = new av(3, null);
        av.writeByte((byte)n);
        av.write(super.mb, 0, super.mb.length);
        av.ju(bigInteger2);
        av.writeInt(super.l8);
        av.hz(this.ll);
        if (!this.kr()) {
            throw new IOException("Error while sending session key!");
        }
    }
    
    public final void k7(final String s) throws IOException {
        this.li = false;
        final av av = new av(4, super.f7);
        av.jt(s);
        av.hz(this.ll);
        if (this.kr()) {
            if (this.c != null) {
                this.c.jf("Authenticated directly by server, no other authentication required");
            }
            return;
        }
        final int[] gj = this.gx.gj(this.g1);
        int i = 0;
        while (i < gj.length) {
            try {
                if (!super.mi(gj[i])) {
                    throw new b4("Server does not support '" + ca.ml[gj[i]] + "'");
                }
                switch (gj[i]) {
                    case 2: {
                        this.k3(false, s);
                        break;
                    }
                    case 3: {
                        this.k6(s);
                        break;
                    }
                    case 4: {
                        this.k3(true, s);
                        break;
                    }
                    case 5: {
                        this.k4(s);
                        break;
                    }
                    case 1: {
                        this.k5(s);
                        break;
                    }
                    case 8: {
                        this.k2(s);
                        this.li = true;
                        break;
                    }
                    default: {
                        throw new IOException("We do not support selected authentication type " + ca.ml[gj[i]]);
                    }
                }
            }
            catch (b4 b4) {
                if (i == gj.length - 1) {
                    throw b4;
                }
                if (this.c != null) {
                    this.c.jf("Authenticating with " + ca.ml[gj[i]] + " failed, " + b4.getMessage());
                }
                ++i;
            }
        }
    }
    
    public final void k6(final String s) throws IOException {
        final String gl = this.gx.gl(this.g1);
        final av av = new av(9, super.f7);
        av.jt(gl);
        av.hz(this.ll);
        if (!this.kr()) {
            throw new b4("Permission denied");
        }
    }
    
    public final void k5(final String s) throws IOException {
        final av av = new av(5, super.f7);
        av.jt(s);
        av.hz(this.ll);
        if (!this.kr()) {
            throw new b4("Permission denied");
        }
    }
    
    public final void k4(final String s) throws IOException {
        new av(39, super.f7).hz(this.ll);
        final ax ax = new ax(-1, super.mc);
        ax.h_(this.lm);
        if (ax.h_ == 15) {
            throw new b4("TIS authentication server not reachable or user unknown");
        }
        if (ax.h_ != 40) {
            throw new IOException("Protocol error, expected TIS challenge but got " + ax.h_);
        }
        final String gk = this.gx.gk(this.g1, ax.jw());
        final av av = new av(41, super.f7);
        av.jt(gk);
        av.hz(this.ll);
        if (!this.kr()) {
            throw new b4("Permission denied");
        }
    }
    
    public final void k3(final boolean b, final String s) throws IOException {
        final an gh = this.gx.gh(this.g1);
        final ce fs = gh.fs();
        av av;
        if (b) {
            av = new av(35, super.f7);
            av.jt(s);
            av.writeInt(fs.m4());
            av.ju(fs.m3());
            av.ju(fs.m2());
        }
        else {
            av = new av(6, super.f7);
            av.ju(fs.m2());
        }
        av.hz(this.ll);
        final ax ax = new ax(-1, super.mc);
        ax.h_(this.lm);
        if (ax.h_ == 15) {
            throw new b4("Server refused our key" + (b ? " or rhosts" : ""));
        }
        if (ax.h_ != 7) {
            throw new IOException("Protocol error, expected RSA-challenge but got " + ax.h_);
        }
        final BigInteger jx = ax.jx();
        cf cf = gh.fr("");
        if (cf == null) {
            cf = gh.fr(this.gx.gg(this.g1));
        }
        else if (this.c != null) {
            this.c.jf("Authenticated with password-less rsa-key '" + gh.ft() + "'");
        }
        if (cf == null) {
            throw new b4("Invalid password for key-file '" + gh.ft() + "'");
        }
        this.k1(cf, jx);
    }
    
    public final void k2(final String s) throws IOException {
        final String gk = this.gx.gk(this.g1, String.valueOf(s) + "'s SDI token passcode: ");
        final av av = new av(16, super.f7);
        av.jt(gk);
        av.hz(this.ll);
        final ax ax = new ax(-1, super.mc);
        ax.h_(this.lm);
        Label_0462: {
            switch (ax.h_) {
                case 14: {
                    this.c.jf("SDI authentication accepted.");
                    break;
                }
                case 15: {
                    throw new b4("SDI authentication failed.");
                }
                case 66: {
                    final String jb = this.c.jb("Next token required: ");
                    final av av2 = new av(67, super.f7);
                    av2.jt(jb);
                    av2.hz(this.ll);
                    if (!this.kr()) {
                        throw new b4("Permission denied");
                    }
                    break;
                }
                case 68: {
                    if (!this.c.jd("New PIN required, do you want to continue?", false)) {
                        throw new b4("New PIN not wanted");
                    }
                    final String jw = ax.jw();
                    final String jw2 = ax.jw();
                    switch (ax.readInt()) {
                        case 1:
                        case 2: {
                            String jb2;
                            do {
                                jb2 = this.c.jb("Please enter new PIN containing " + jw2 + " " + jw);
                            } while (!jb2.equals(this.c.jb("Please enter new PIN again")));
                            final av av3 = new av(71, super.f7);
                            av3.jt(jb2);
                            av3.hz(this.ll);
                            final ax ax2 = new ax(-1, super.mc);
                            ax2.h_(this.lm);
                            if (ax2.h_ != 69) {
                                throw new b4("PIN rejected by server");
                            }
                            throw new b4("New PIN accepted, Wait for the code on your token to change");
                        }
                        default: {
                            throw new b4("Invalid response from server");
                        }
                        case 0: {
                            break Label_0462;
                        }
                    }
                    break;
                }
                default: {
                    throw new b4("Permission denied");
                }
            }
        }
    }
    
    public final void k1(final cf cf, BigInteger bigInteger) throws IOException {
        bigInteger = new cg(new KeyPair(null, cf)).m7(bigInteger);
        bigInteger = cg.m6(bigInteger);
        final byte[] byteArray = bigInteger.toByteArray();
        byte[] nd;
        try {
            final ch ne = ch.ne("MD5");
            if (byteArray[0] == 0) {
                ne.c3(byteArray, 1, 32);
            }
            else {
                ne.c3(byteArray, 0, 32);
            }
            ne.c3(super.me);
            nd = ne.nd();
        }
        catch (Exception ex) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
        final av av = new av(8, super.f7);
        av.write(nd, 0, nd.length);
        av.hz(this.ll);
        if (!this.kr()) {
            throw new b4("Permission denied");
        }
    }
    
    public final void k0() throws IOException {
        if (this.g1.f3()) {
            this.kw();
        }
        final int f7 = this.g1.f7();
        if (f7 > 0) {
            this.ky(f7);
        }
        if (this.g1.f5()) {
            this.kx();
        }
        if (this.lf) {
            this.kz();
        }
        if (this.lo != null) {
            this.kt(this.lo);
        }
        else {
            this.ks();
        }
    }
    
    public final void k_() {
        ar.f_(this);
    }
    
    public final void kz() throws IOException {
        for (int i = 0; i < this.lq.size(); ++i) {
            final b1 b1 = this.lq.elementAt(i);
            this.kv(b1.lc, b1.fe, b1.fg, b1.ff, b1.lb);
        }
        for (int j = 0; j < this.lp.size(); ++j) {
            final b0 b2 = this.lp.elementAt(j);
            this.ku(b2.ff, b2.lc, b2.fe, b2.lb);
        }
    }
    
    public final void ky(final int n) throws IOException {
        final av av = new av(38, super.f7);
        av.writeInt(n);
        av.hz(this.ll);
        if (!this.kr() && this.c != null) {
            this.c.jf("Error requesting max packet size: " + n);
        }
    }
    
    public final void kx() throws IOException {
        final av av = new av(34, super.f7);
        av.jt("MIT-MAGIC-COOKIE-1");
        av.jt("112233445566778899aabbccddeeff00");
        av.writeInt(0);
        av.hz(this.ll);
        if (!this.kr() && this.c != null) {
            this.c.jf("Error requesting X11 forward");
        }
    }
    
    public final void kw() throws IOException {
        final av av = new av(10, super.f7);
        h ev = null;
        if (this.km != null) {
            ev = this.km.ev();
        }
        if (ev != null) {
            av.jt(ev.b3());
            av.writeInt(ev.b2());
            av.writeInt(ev.b1());
            av.writeInt(ev.b0());
            av.writeInt(ev.b_());
        }
        else {
            av.jt("");
            av.writeInt(0);
            av.writeInt(0);
            av.writeInt(0);
            av.writeInt(0);
        }
        av.writeByte(0);
        av.hz(this.ll);
        if (!this.kr() && this.c != null) {
            this.c.jf("Error requesting PTY");
        }
    }
    
    public final void kv(final String s, final int n, final String s2, final int n2, final String s3) throws IOException {
        this.fq.lw(s, n, s2, n2, s3);
    }
    
    public final void ku(final int n, final String s, final int n2, final String s2) throws IOException {
        try {
            ar.f1(s2).fx(n, s, n2, this.fq);
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            throw new IOException("Plugins not available");
        }
        final av av = new av(28, super.f7);
        av.writeInt(n);
        av.jt(s);
        av.writeInt(n2);
        av.hz(this.ll);
        if (!this.kr() && this.c != null) {
            this.c.jf("Error requesting remote port forward: " + s2 + "/" + n + ":" + s + ":" + n2);
        }
    }
    
    public final void kt(final String s) throws IOException {
        final av av = new av(13, super.f7);
        av.jt(s);
        av.hz(this.ll);
    }
    
    public final void ks() throws IOException {
        new av(12, super.f7).hz(this.ll);
    }
    
    public final boolean kr() throws IOException {
        final ax ax = new ax(-1, super.mc);
        ax.h_(this.lm);
        boolean b;
        if (ax.h_ == 14) {
            b = true;
        }
        else if (ax.h_ == 15) {
            b = false;
        }
        else {
            if (ax.h_ == 1) {
                throw new IOException("Server disconnected: " + ax.jw());
            }
            throw new IOException("Protocol error: got " + ax.h_ + " when expecting success/failure");
        }
        return b;
    }
    
    public final void kq(final int n) {
        if (n == 0) {
            if (this.lw != null && this.lw.isAlive()) {
                this.lw.stop();
            }
            this.lw = null;
        }
        else if (this.lw != null) {
            this.lw.km(n);
        }
        else {
            (this.lw = new b2(this, n)).start();
        }
    }
    
    public final boolean kp() {
        return this.lj;
    }
    
    public final boolean e5() {
        return this.fz;
    }
    
    public final void ko(final char c) throws IOException {
        this.kn(String.valueOf(c));
    }
    
    public final void kn(final String s) throws IOException {
        this.kn(s.getBytes(), 0, s.length());
    }
    
    public final void kn(final byte[] array) throws IOException {
        this.kn(array, 0, array.length);
    }
    
    public final void kn(final byte[] array, final int n, final int n2) throws IOException {
        if (this.lj && this.fq != null) {
            final av av = new av(16, super.f7);
            av.writeInt(n2);
            av.write(array, n, n2);
            this.fq.ee(av);
        }
    }
    
    public final void al(final int n, final int n2, final int n3, final int n4) {
        if (this.lj && this.fq != null) {
            try {
                final av av = new av(11, super.f7);
                av.writeInt(n);
                av.writeInt(n2);
                av.writeInt(n3);
                av.writeInt(n4);
                this.fq.ee(av);
            }
            catch (Exception ex) {
                if (this.c != null) {
                    this.c.h9("Error when sending sigWinch: " + ex.toString());
                }
            }
        }
    }
}
