// 
// Decompiled by Procyon v0.5.30
// 

package com.is_teledata.livehtml;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Graphics;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;
import java.applet.Applet;

public class LHTML extends Applet implements b, c, d, e
{
    public StringBuffer a;
    public Hashtable b;
    public Vector c;
    public Vector d;
    public Vector e;
    public as f;
    public ax g;
    public j h;
    public bc i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    public static String DELIMITER;
    public static boolean printed_version;
    private static int n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;
    public static Object monitor;
    
    public LHTML() {
        this.a = new StringBuffer();
        this.b = new Hashtable();
        this.c = new Vector();
        this.d = new Vector();
        this.e = new Vector();
        this.f = null;
        this.j = true;
        this.k = false;
        this.l = false;
        this.m = -1;
        this.o = System.currentTimeMillis();
        this.p = 0L;
        this.q = 20000L;
        this.r = 10000L;
        this.s = 10000L;
        this.t = 0L;
    }
    
    public void init() {
        synchronized (LHTML.monitor) {
            if (!LHTML.printed_version) {
                System.out.println(es.d + A_B_C_D("!*mmt@'") + new Date(System.currentTimeMillis()) + A_B_C_D("*"));
                LHTML.printed_version = true;
            }
            if (!es.c.equalsIgnoreCase(A_B_C_D("+")) && this.getDocumentBase().getHost().indexOf(es.c) < 0) {
                if (d.a.f()) {
                    d.a.c(A_B_C_D("MiugMRLL)az,ie~os~xx5tzf9nssp>{qNALN\u000b"));
                }
                return;
            }
            this.setName(A_B_C_D("JS-Xbjddj~l,\".Cggu[hzz7") + LHTML.n++);
            this.d();
            final t a = s.a(this, A_B_C_D("MJWOI"), new en[] { r.a() }, new String[] { A_B_C_D("MJWOI[FIGPDMTLHDV"), A_B_C_D("NFF]HKIBBOVNFBL") });
            if (a.b(A_B_C_D("MJWOI[HXYMEPJ`")) != null) {
                y.c(a.g(A_B_C_D("MJWOI[HXYMEPJ`")));
            }
            if (a.b(A_B_C_D("MJWOI[JRXE`C")) != null) {
                this.m = a.d(A_B_C_D("MJWOI[JRXE`C"));
            }
            if (a.i(A_B_C_D("MJWOI[CO\\IIHJS^KDC\\]["))) {
                System.out.println(A_B_C_D(""));
                d.a.g(A_B_C_D("MJWOI)Vc|{dec.h}1v|avx{s}"));
                try {
                    this.f = as.b(a);
                }
                catch (Exception ex) {
                    if (d.a.f()) {
                        d.a.c(A_B_C_D("MJWOI&pjb~dkaeuoey~~5tx\u007fu}\u007f<lukx\u0001") + ex + A_B_C_D(";\f") + ex.getMessage());
                    }
                    ex.printStackTrace();
                    return;
                }
                this.l = true;
                this.k = true;
            }
            else {
                try {
                    this.f = as.b(a);
                    this.g = ax.a(this.f);
                }
                catch (Exception ex2) {
                    System.out.println(A_B_C_D(""));
                    if (d.a.f()) {
                        d.a.c(A_B_C_D("MJWOI&pjb~dkaeuoey~~5tx\u007fu}\u007f<lukx\u0001") + ex2 + A_B_C_D(";\f") + ex2.getMessage());
                    }
                    ex2.printStackTrace();
                    return;
                }
                System.out.println(A_B_C_D("MJWOI&fighpiyik0hyg|5") + this.g.ae());
                this.a(this.g.l(), 67108864);
                this.g.a(this);
                this.e();
                this.setBackground(a.l(A_B_C_D("CABIDXJSGN")));
                if (this.g != null) {
                    this.k = true;
                }
            }
            final Integer d = a.d(A_B_C_D("MJWOI[]IFLDOTOGKT[N[[BTFOYW"));
            if (d != null) {
                this.q = d;
            }
            final Integer d2 = a.d(A_B_C_D("MJWOI[]IFLDOTZHCV_HH"));
            if (d2 != null) {
                this.r = d2;
            }
            if (d.a.j()) {
                d.a.h(A_B_C_D("djhapmia)tfgoel0dfthr6tb~ld<") + this.q + A_B_C_D("ns:$\u007fklfbm+cczl~gq\u007f4~g7e~n;pt>") + this.r + A_B_C_D("ns"));
            }
            try {
                final Long e = a.e(A_B_C_D("SEOMFBZTBEPEZZ"));
                if (e != null) {
                    this.t = e;
                    if (this.t > 0L) {
                        this.t += System.currentTimeMillis();
                        if (d.a.i()) {
                            d.a.g(A_B_C_D("ut|$qk'vnffki.hb1") + (long)e + A_B_C_D("!mr$fr'") + new Date(this.t));
                        }
                    }
                    else if (d.a.i()) {
                        d.a.g(A_B_C_D("Seomfb'o|*oc\u0080mmdvv4"));
                    }
                }
            }
            catch (Exception ex3) {
                System.out.println(A_B_C_D("hewPllbC{|fz7.") + ex3.getMessage());
            }
            if (this.g != null) {
                this.g.at().a(this, System.currentTimeMillis() + this.q);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void produce() {
        if (d.a.j()) {
            d.a.h(A_B_C_D("djhapmia)tfgoel0dfthr/7|zko<WO-VdOP\u0004NW\u0007") + (System.currentTimeMillis() - this.o) + A_B_C_D("ns#cdk"));
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (System.currentTimeMillis() - this.o > this.r) {
            d.a.c(A_B_C_D("liopnlb(sghjfi"));
            this.stop();
        }
        else if (this.p != 0L && currentTimeMillis - this.p > this.s) {
            d.a.c(A_B_C_D("BuwlNb'cqzdzjj/nff3sef{sm:ti=okwMN\u0003RRLIOGO\u0005\f`F\\\\EY]U\u0015RZAW"));
            this.stop();
        }
        else if (this.t > 0L && currentTimeMillis > this.t) {
            this.getNewXid(this.g);
        }
        if (this.j && this.g != null) {
            this.g.at().a(this, currentTimeMillis + this.q);
        }
    }
    
    public void getNewXid(final az az) {
        System.out.println(A_B_C_D("BUWL\\MC(nr{c\u007fik/"));
        this.a(A_B_C_D("BUWL\\MC(nr{c\u007fik"), 134217728);
        this.p = System.currentTimeMillis();
    }
    
    public ax getPushSession() {
        return this.g;
    }
    
    public void addMessage(final String s) {
        this.a.append(s);
    }
    
    private void a(final String s) {
        this.a(s, 1048576);
    }
    
    private void a(final String s, final int n) {
        this.a(s, n + A_B_C_D(""));
    }
    
    private void a(final String s, final String s2) {
        this.addMessage(LHTML.DELIMITER + LHTML.DELIMITER + s2 + LHTML.DELIMITER + LHTML.DELIMITER + 1 + LHTML.DELIMITER + s);
    }
    
    private void a(final ce ce) {
        int n = 0;
        switch (ce.a()) {
            case 5: {
                n = 688128;
                break;
            }
            case 7:
            case 8: {
                n = 753664;
                break;
            }
            case 6: {
                n = 720896;
                break;
            }
            case 1: {
                n = 557056;
                break;
            }
            case 4: {
                n = 655360;
                break;
            }
            case 3: {
                n = 622592;
                break;
            }
            case 2: {
                n = 589824;
                break;
            }
        }
        if (n == 0 && d.a.f()) {
            d.a.c(A_B_C_D("doxpal\"t)pdfi.~kdc|}{9fdznpi=uq@mjwoi\fHDM{_KYY^cVCBSTQ\u001f\u001f"));
        }
        else {
            this.a(ce.b() + A_B_C_D(";\"") + ce.c(), n);
        }
    }
    
    public String isReady() {
        return this.k ? A_B_C_D("utxg") : A_B_C_D("gaoqb");
    }
    
    public void JS2JCall(final String s) {
        if (d.a.k()) {
            d.a.i(A_B_C_D("MJWOI&uclmd~jj/FD=`AT07") + s);
        }
        if (this.j && !this.l) {
            int a = 0;
            if (s != null && s.length() > 0) {
                a = this.c().a((e)new LHTML.dg(this, s));
            }
            if (a != 0 && d.a.f()) {
                d.a.c(A_B_C_D("Doxpal\"t)blfibl0[C@_rg7") + s + A_B_C_D("!dhafqvc)gm,}\u0080blfsxb5gcwm};") + j.a(a));
            }
        }
    }
    
    public String JS2JRequest() {
        if (d.a.k()) {
            d.a.i(A_B_C_D("MJWOI&uclmd~jj/FD=Awfatem5Z{qr"));
        }
        this.o = System.currentTimeMillis();
        if (!this.j) {
            return A_B_C_D("");
        }
        synchronized (this.a) {
            if (this.a.length() == 0) {
                return A_B_C_D("");
            }
            final String string = this.a.toString();
            this.a = new StringBuffer();
            return string;
        }
    }
    
    public void JS2JMessage(final String s) {
        if (d.a.i()) {
            d.a.g(A_B_C_D("MJWOI&uclmd~jj/FD=`AT07") + s);
        }
        if (!this.j) {
            return;
        }
        if (s.length() > 0) {
            if (s.equals(A_B_C_D("GNXQM"))) {
                synchronized (this.a) {
                    this.a = new StringBuffer();
                    this.a(A_B_C_D("Gnxqmmia)D9B`Al}dqvw"));
                    return;
                }
            }
            if (s.equals(A_B_C_D("TVDXB"))) {
                if (this.g != null) {
                    final ce c = this.g.c();
                    this.a(c.b() + A_B_C_D(";\"") + c.c());
                }
                else {
                    this.e.addElement(A_B_C_D("TVDXB"));
                }
            }
            else if (s.equals(A_B_C_D("TVDXB[OO\\^"))) {
                if (this.g != null) {
                    final Vector e = this.g.e();
                    for (int i = 0; i < e.size(); ++i) {
                        this.a(e.elementAt(i));
                    }
                }
                else {
                    this.e.addElement(A_B_C_D("TVDXB[OO\\^"));
                }
            }
            else if (s.equals(A_B_C_D("KS1JHKL"))) {
                this.a(A_B_C_D("pk"), 2097152);
            }
            else if (s.equals(A_B_C_D("QIME"))) {
                final int a = this.c().a((e)new LHTML.da(this));
                if (a != 0 && d.a.g()) {
                    d.a.d(A_B_C_D("Doxpal\"t)kyonzl0ay}u5xtuzmj\u007f=sy@") + j.a(a));
                }
                else {
                    this.e.addElement(A_B_C_D("QIME"));
                }
            }
            else if (s.equals(A_B_C_D("QOME"))) {
                final int a2 = this.c().a((e)new LHTML.db(this));
                if (a2 != 0 && d.a.g()) {
                    d.a.d(A_B_C_D("Doxpal\"t)kyonzl0a\u007f}u5xtuzmj\u007f=sy@") + j.a(a2));
                }
                else {
                    this.e.addElement(A_B_C_D("QOME"));
                }
            }
            else if (s.startsWith(A_B_C_D("KXHR?"))) {
                this.a(this.c(s.substring(5)), 16777216);
            }
        }
    }
    
    public cb getScheduler() {
        if (this.g != null && this.g.d() != 7) {
            return this.g.at();
        }
        return null;
    }
    
    public void stop() {
        this.j = false;
        if (this.b != null) {
            final Enumeration<dc> elements = this.b.elements();
            while (elements.hasMoreElements()) {
                final dc dc = elements.nextElement();
                dc.a(true);
                dc.c();
            }
        }
        if (this.h != null) {
            this.h.a();
        }
        if (this.g != null) {
            this.g.b(this);
            this.g.a(false);
            if (this.b != null) {
                final Enumeration<dc> elements2 = this.b.elements();
                while (elements2.hasMoreElements()) {
                    this.g.b(elements2.nextElement());
                }
            }
            this.g = null;
        }
        if (this.b != null) {
            this.b.clear();
        }
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
    }
    
    private void b(final String s) {
        int n = 0;
        int n2 = 0;
        LHTML.DELIMITER = s.substring(0, 1);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, LHTML.DELIMITER, false);
        final Vector vector = new Vector<dc>();
        synchronized (this.c) {
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final x x = new x(stringTokenizer.nextToken());
                if (this.m >= 0) {
                    x.a(this.f.g(A_B_C_D("OAPG\\NSTYWH_F")), this.m + A_B_C_D(""));
                }
                final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                final String[] array = new String[int1];
                for (int i = 0; i < int1; ++i) {
                    array[i] = stringTokenizer.nextToken();
                }
                final dc a = dc.a(nextToken, x.toString(), array, this);
                final dc dc = this.b.put(nextToken, a);
                ++n;
                if (dc != null) {
                    if (this.g != null) {
                        this.g.b(dc);
                    }
                    else {
                        this.d.addElement(dc);
                    }
                    ++n2;
                }
                vector.addElement(a);
            }
            if (vector.size() > 0) {
                final c1[] array2 = new c1[vector.size()];
                vector.copyInto(array2);
                if (this.g != null) {
                    this.g.a(array2);
                }
                else {
                    this.c.addElement(array2);
                    if (d.a.f()) {
                        d.a.c(A_B_C_D("Tvnrbb'") + array2.length + A_B_C_D("!sxbxguoy~dec&~'1txqvafs9w}<sist\u000eSHQXMJJ"));
                    }
                }
            }
        }
        if (d.a.i()) {
            d.a.g(A_B_C_D("MJWOI&uclmd~jj/") + n + A_B_C_D("!amh%xdkh\u0080pp-") + n2 + A_B_C_D("!sxbxguoy~dec\u007f"));
        }
    }
    
    private void a() {
        this.f().a(new x(A_B_C_D("0sj{b+wogo6")), (bf)new LHTML.ei(this), this.f.a(A_B_C_D("VTL]UXDBBR")), this.f.d(A_B_C_D("SETWBWSY]AHODY[")), null);
    }
    
    private void b() {
        System.currentTimeMillis();
        this.f().a(new x(A_B_C_D("0sj{b+wigo6E[!PK.bha}4_YPW]PZT$/\u0011")), (bf)new LHTML.ej(this), this.f.a(A_B_C_D("VTL]UXDBBR")), this.f.d(A_B_C_D("SETWBWSY]AHODY[")), null);
    }
    
    private String c(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, A_B_C_D("/"));
        if (stringTokenizer.countTokens() < 3) {
            return A_B_C_D("qaqqb[dv{gy");
        }
        int int1;
        int int2;
        int int3;
        int int4;
        try {
            int1 = Integer.parseInt(stringTokenizer.nextToken());
            int2 = Integer.parseInt(stringTokenizer.nextToken());
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), A_B_C_D("`"));
            int3 = Integer.parseInt(stringTokenizer2.nextToken());
            if (stringTokenizer2.countTokens() > 0) {
                int4 = Integer.parseInt(stringTokenizer2.nextToken());
            }
            else {
                int4 = 0;
            }
        }
        catch (NumberFormatException ex) {
            return A_B_C_D("qaqqb[dv{gy");
        }
        final StringTokenizer stringTokenizer3 = new StringTokenizer(System.getProperty(A_B_C_D("kauc+tdv|aff")), A_B_C_D("/"));
        if (stringTokenizer3.countTokens() < 3) {
            return A_B_C_D("qaqqb[dv{gy");
        }
        try {
            final int int5 = Integer.parseInt(stringTokenizer3.nextToken());
            final int int6 = Integer.parseInt(stringTokenizer3.nextToken());
            final StringTokenizer stringTokenizer4 = new StringTokenizer(stringTokenizer3.nextToken(), A_B_C_D("`"));
            final int int7 = Integer.parseInt(stringTokenizer4.nextToken());
            int int8 = 0;
            if (stringTokenizer4.countTokens() > 0) {
                int8 = Integer.parseInt(stringTokenizer4.nextToken());
            }
            if (int5 > int1) {
                return A_B_C_D("pk");
            }
            if (int5 != int1) {
                return A_B_C_D("oow]lo");
            }
            if (int6 > int2) {
                return A_B_C_D("pk");
            }
            if (int6 != int2) {
                return A_B_C_D("oow]lo");
            }
            if (int7 > int3) {
                return A_B_C_D("pk");
            }
            if (int7 == int3 && int8 >= int4) {
                return A_B_C_D("pk");
            }
            return A_B_C_D("oow]lo");
        }
        catch (NumberFormatException ex2) {
            return A_B_C_D("qaqqb[dv{gy");
        }
    }
    
    private j c() {
        if (this.h == null && this.j) {
            this.h = new j(5, 10, A_B_C_D("MJWOI"));
        }
        return this.h;
    }
    
    private void d() {
        this.h = this.c();
        for (int i = 0; i < 10; ++i) {
            this.c().a((e)new LHTML.k(this));
        }
    }
    
    private void e() {
        synchronized (this.c) {
            if (this.e.size() > 0) {
                for (int i = 0; i < this.e.size(); ++i) {
                    this.JS2JMessage((String)this.e.elementAt(i));
                }
            }
            if (this.c.size() > 0) {
                for (int j = 0; j < this.c.size(); ++j) {
                    this.g.a((c1[])this.c.elementAt(j));
                }
            }
            if (this.d.size() > 0) {
                for (int k = 0; k < this.d.size(); ++k) {
                    this.g.b((c1)this.d.elementAt(k));
                }
            }
        }
    }
    
    private bc f() {
        if (this.g != null) {
            return this.g.ao();
        }
        if (this.i == null) {
            final Integer d = this.f.d(A_B_C_D("NA[]DASY[M\\_J_[Q]U]UA^"));
            int intValue = 4000;
            if (d != null) {
                intValue = d;
            }
            final Integer d2 = this.f.d(A_B_C_D("NA[]KAVTNNV\\D_[QCUDGRGCIU]U]IV"));
            int intValue2 = 64000;
            if (d2 != null) {
                intValue2 = d2;
            }
            this.i = new dw(this.f.b(A_B_C_D("QTNXLGJLXFDYY"), 0), this.f.b(A_B_C_D("DJNQQ[KO\\^"), 0), this.f.a(A_B_C_D("DRNRQ[KO\\^"), 0), new Hashtable(), this.c(), this.f.b(A_B_C_D("WJNQQ[KO\\^"), 0), this.f.f(A_B_C_D("SETWBWSY]AHODY[QWQRH\\H")), intValue, intValue2, this.f.h(A_B_C_D("BUWL\\WSOLCTU]M]O^C")));
        }
        return this.i;
    }
    
    public void callback(final ax ax, final ce ce) {
        this.a(ce);
    }
    
    static {
        LHTML.DELIMITER = A_B_C_D("\u007f");
        LHTML.printed_version = false;
        LHTML.n = 0;
        LHTML.monitor = new Object();
    }
    
    public static String A_B_C_D(final String s) {
        final char[] array = new char[s.length()];
        s.getChars(0, s.length(), array, 0);
        char c = '\0';
        for (int i = 0; i < array.length; ++i) {
            final char[] array2 = array;
            final int n = i;
            final char c2 = (char)(array[i] - '\u0001');
            final char c3 = c;
            ++c;
            array2[n] = (char)(c2 ^ c3);
        }
        return new String(array);
    }
}
