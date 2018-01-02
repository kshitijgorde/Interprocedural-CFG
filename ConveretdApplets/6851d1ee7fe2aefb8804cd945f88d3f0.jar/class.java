import java.util.Vector;
import java.net.URLEncoder;
import java.util.Observer;
import java.util.Hashtable;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class class extends Observable
{
    public static final int qqa = 0;
    public static final int rqa = 1;
    public static final int sqa = 2;
    public static final int tqa = 3;
    public static final int uqa = 4;
    public static final int vqa = 5;
    public static final int wqa = 10;
    public static final int xqa = 15;
    public static final int yqa = 20;
    public static final int zqa = 30;
    public static final int Aqa = 60;
    public static final int Bqa = 120;
    public static final int Cqa = 180;
    public static final int Dqa = 240;
    public static final int Eqa = 300;
    public static final int Fqa = 600;
    public static final int Gqa = 900;
    public static final int Hqa = 1200;
    public static final int Iqa = 1800;
    public static final int Jqa = 3600;
    public static final int Kqa = 100001;
    public static final int Lqa = 100002;
    public static final int Mqa = 100003;
    private int ija;
    private int hja;
    private String Nqa;
    private String Oqa;
    private String foa;
    private String source;
    private String Pqa;
    private q Qqa;
    private q Rqa;
    private String[] Sqa;
    private String Tqa;
    private String Uqa;
    private double[] Vqa;
    private double[] Wqa;
    private double[] Xqa;
    private double[] Yqa;
    private double[] Zqa;
    private double[] _ra;
    private double[] ara;
    private double[] bra;
    private String[] cra;
    private double[] dra;
    private double[] L;
    private double[] M;
    private double[] N;
    private double[] O;
    private double[] P;
    private double[] Q;
    private double[] info;
    private String[] era;
    private double[] fra;
    private double[] gra;
    private double[] hra;
    private double[] ira;
    private double[] jra;
    private double[] kra;
    private double[] lra;
    private double[] mra;
    private String[] nra;
    private double[] ora;
    private double[] pra;
    private double[] qra;
    private double[] rra;
    private double[] sra;
    private double[] tra;
    private double[] ura;
    private double[] vra;
    private String[] wra;
    private double[] xra;
    private double[] yra;
    private double[] zra;
    private double[] Ara;
    private double[] Bra;
    private double[] Cra;
    private double[] Dra;
    private double[] Era;
    private String[] Fra;
    private String Gra;
    private boolean Hra;
    private boolean Ira;
    private boolean Jra;
    private boolean Kra;
    private int Lra;
    private boolean Mra;
    private boolean Nra;
    private boolean Ora;
    private final Hashtable Pra;
    private Th Qra;
    private break kb;
    private boolean uja;
    private final String Rra;
    private int Sra;
    r kja;
    f jja;
    private Observer Tra;
    
    public synchronized void _a(final int sra) {
        this.Sra = sra;
    }
    
    public synchronized void b(final int n) {
        this.Qra.b(n);
    }
    
    public synchronized void a(final int n) {
        this.Qra.a(n);
    }
    
    public synchronized int g() {
        return this.Qra.g();
    }
    
    public synchronized int b() {
        return this.Qra.b();
    }
    
    public class(final String source, final String pqa, final break kb, final int n, final String s) {
        this.ija = 2;
        this.hja = -1;
        this.Nqa = "";
        this.Oqa = "";
        this.foa = "YY-MM-DD";
        this.Pra = new Hashtable(500);
        this.uja = false;
        this.Sra = -1;
        this.kja = new r();
        this.jja = new f();
        this.Tra = null;
        this.kb = kb;
        this.source = source;
        this.Pqa = pqa;
        this.Rra = ("".equals(s) ? null : s);
        this.Tqa = null;
        this.Sqa = new String[0];
        (this.Qqa = new q()).setUseCache(this.uja);
        (this.Rqa = new q()).setUseCache(false);
        this.Qra = new Th(n);
        this.Hra = true;
        this.Jra = true;
        this.Ira = true;
        this.Kra = true;
        this.Lra = 100001;
        this.Mra = false;
        this.Nra = false;
        this.Ora = false;
        this.x(this.foa);
    }
    
    public synchronized void setUseCache(final boolean b) {
        this.uja = b;
        this.Qqa.setUseCache(b);
    }
    
    public synchronized void i() {
        this.Hra = true;
        this.Ira = true;
        this.Jra = true;
        this.Kra = true;
    }
    
    public synchronized boolean b() {
        return this.Lra >= 0 && this.Lra <= 3600;
    }
    
    public synchronized int O() {
        return this.ija;
    }
    
    public synchronized void aa(final int hja) {
        this.hja = hja;
    }
    
    public synchronized void x(final String s) {
        this.foa = s.toUpperCase();
    }
    
    public synchronized void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }
    
    public synchronized void R() {
        this.Qqa.deleteObservers();
        this.Rqa.deleteObservers();
        this.Tra = null;
    }
    
    public synchronized void b(final Observer tra) {
        this.Qqa.addObserver(tra);
        this.Rqa.addObserver(tra);
        this.Tra = tra;
    }
    
    public synchronized boolean R() {
        return this.Nra;
    }
    
    public synchronized void _(final int lra) {
        if (this.Lra != lra) {
            final boolean b = this.b();
            this.Lra = lra;
            if (b != this.b()) {
                this.Hra = true;
                this.Ira = true;
            }
            this.Jra = true;
            this.Kra = true;
            if (this.Mra) {
                this.Hra = true;
                this.Ira = true;
                this.Jra = true;
                this.Kra = true;
            }
        }
        this.notifyObservers();
    }
    
    public synchronized int y() {
        return this.Lra;
    }
    
    public synchronized void S() {
        this.h();
    }
    
    public synchronized void h() {
        final Uh uh = new Uh();
        uh.setUseCache(this.uja);
        if (this.Tra != null) {
            uh.addObserver(this.Tra);
        }
        final String[][] b = this.kb._().b();
        final String[] array = b[0];
        final String[] array2 = b[1];
        uh.a(this.Tqa, this.h());
        uh.a(this.Uqa, this.i());
        for (int i = 0; i < array.length; ++i) {
            uh.a(array[i], array2[i]);
        }
        uh.f();
        final q[] array3 = new q[array.length];
        for (int j = 0; j < array.length; ++j) {
            array3[j] = uh.b(array[j]);
        }
        this.b(array, array2, array3);
        if (this.Uqa != null && this.Uqa.length() > 0) {
            q b2 = uh.b(this.Uqa);
            if (b2 == null) {
                b2 = new q();
            }
            this._(b2);
        }
        if (this.Tqa != null && this.Tqa.length() > 0) {
            q b3 = uh.b(this.Tqa);
            if (b3 == null) {
                b3 = new q();
            }
            this.a(b3);
        }
        uh.reset();
        uh.deleteObservers();
    }
    
    public synchronized void _(final String s, final q q, final String s2, final q q2) {
        if (this.Uqa != null && this.Uqa.length() > 0 && s2 != null && s2.length() > 0 && s2.equals(this.f(this.Uqa)) && q2 != null) {
            this._(q2);
        }
        if (this.Tqa != null && this.Tqa.length() > 0 && s != null && s.length() > 0 && s.equals(this.f(this.Tqa)) && q != null) {
            this.a(q);
        }
    }
    
    public synchronized void b(final String[] array, final String[] array2, final q[] array3) {
        for (int i = 0; i < array.length; ++i) {
            if (array3[i] != null && array2[i].equals(this.f(array[i]))) {
                this.Qra._(this.g(array[i]), array3[i], this.b(), this.foa, this.hja);
            }
        }
    }
    
    public synchronized String h() {
        if (this.Tqa == null || this.Tqa.length() == 0) {
            return null;
        }
        return this.f(this.Tqa);
    }
    
    public synchronized String i() {
        if (this.Uqa == null || this.Uqa.length() == 0) {
            return null;
        }
        return this.f(this.Uqa);
    }
    
    public synchronized double[] Y() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.fra;
    }
    
    public synchronized double[] Z() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.xra;
    }
    
    public synchronized double[] g() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.gra;
    }
    
    public synchronized double[] _a() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.yra;
    }
    
    public synchronized double[] a() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.hra;
    }
    
    public synchronized double[] aa() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.zra;
    }
    
    public synchronized double[] b() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.ira;
    }
    
    public synchronized double[] ba() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.Ara;
    }
    
    public synchronized double[] _() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.jra;
    }
    
    public synchronized double[] ca() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.Bra;
    }
    
    public synchronized double[] f() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.kra;
    }
    
    public synchronized double[] ga() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.Cra;
    }
    
    public synchronized double[] h() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.lra;
    }
    
    public synchronized double[] i() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.mra;
    }
    
    public synchronized String[] d() {
        if (this.Hra) {
            this.T();
        }
        this.wb();
        return this.nra;
    }
    
    public synchronized double[] ha() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.Dra;
    }
    
    public synchronized double[] ia() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.Era;
    }
    
    public synchronized String[] f() {
        if (this.Ira) {
            this.xb();
        }
        this.yb();
        return this.Fra;
    }
    
    public synchronized void a(final String tqa) {
        this.Hra = true;
        this.Tqa = tqa;
        this.zb();
    }
    
    public synchronized void _(final String uqa) {
        this.Ira = true;
        this.Uqa = uqa;
        this.zb();
    }
    
    public synchronized String _() {
        return this.Tqa;
    }
    
    private String _(final String s, String encode) {
        final String s2 = "{SYMBOL}";
        final String s3 = "{symbol}";
        final String s4 = "{Symbol}";
        if (encode == null) {
            encode = "";
        }
        encode = URLEncoder.encode(encode);
        return catch.a(catch.a(catch.a(s, s2, encode.toUpperCase()), s3, encode.toLowerCase()), s4, encode);
    }
    
    private String g(final String s) {
        return this._(s) + (this.Mra ? ("{P:" + this.Lra + "}") : "");
    }
    
    public synchronized String g() {
        return this.Uqa;
    }
    
    public synchronized String getMessage() {
        return this.Gra;
    }
    
    private void Ab() {
        if (this.Vqa == null || this.Zqa == null) {
            final double[] array = null;
            this.info = array;
            this.Q = array;
            this.P = array;
            this.O = array;
            this.N = array;
            this.M = array;
            this.L = array;
            this.dra = array;
            this.era = null;
            final double[] array2 = null;
            this.bra = array2;
            this.ara = array2;
            this._ra = array2;
            this.Zqa = array2;
            this.Yqa = array2;
            this.Xqa = array2;
            this.Wqa = array2;
            this.Vqa = array2;
            this.cra = null;
            return;
        }
        final int length = this.Vqa.length;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (this.Vqa[i] <= 0.0 || this.Zqa[i] <= 0.0) {
                b = false;
                break;
            }
        }
        if (b) {
            this.dra = this.Vqa;
            this.L = this.Wqa;
            this.M = this.Xqa;
            this.N = this.Yqa;
            this.O = this.Zqa;
            this.P = this._ra;
            this.Q = this.ara;
            this.info = this.bra;
            this.era = this.cra;
            final double[] array3 = null;
            this.bra = array3;
            this.ara = array3;
            this._ra = array3;
            this.Zqa = array3;
            this.Yqa = array3;
            this.Xqa = array3;
            this.Wqa = array3;
            this.Vqa = array3;
            this.cra = null;
            return;
        }
        int n = 0;
        for (int j = 0; j < length; ++j) {
            if (this.Vqa[j] > 0.0 && this.Zqa[j] > 0.0) {
                ++n;
            }
        }
        if (length > 0 && n > 0) {
            this.dra = new double[n];
            this.L = new double[n];
            this.M = new double[n];
            this.N = new double[n];
            this.O = new double[n];
            this.P = new double[n];
            this.Q = new double[n];
            this.info = new double[n];
            this.era = new String[n];
            int n2 = 0;
            for (int k = 0; k < length; ++k) {
                if (this.Vqa[k] > 0.0 && this.Zqa[k] > 0.0) {
                    this.dra[n2] = this.Vqa[k];
                    this.L[n2] = this.Wqa[k];
                    this.M[n2] = this.Xqa[k];
                    this.N[n2] = this.Yqa[k];
                    this.O[n2] = this.Zqa[k];
                    this.P[n2] = this._ra[k];
                    this.Q[n2] = this.ara[k];
                    this.info[n2] = this.bra[k];
                    this.era[n2] = this.cra[k];
                    ++n2;
                }
            }
        }
        else {
            final double[] array4 = null;
            this.info = array4;
            this.Q = array4;
            this.P = array4;
            this.O = array4;
            this.N = array4;
            this.M = array4;
            this.L = array4;
            this.dra = array4;
            this.era = null;
        }
        final double[] array5 = null;
        this.bra = array5;
        this.ara = array5;
        this._ra = array5;
        this.Zqa = array5;
        this.Yqa = array5;
        this.Xqa = array5;
        this.Wqa = array5;
        this.Vqa = array5;
        this.cra = null;
    }
    
    private void Bb() {
        if (this.Vqa == null || this.Zqa == null) {
            final double[] array = null;
            this.vra = array;
            this.ura = array;
            this.tra = array;
            this.sra = array;
            this.rra = array;
            this.qra = array;
            this.pra = array;
            this.ora = array;
            this.wra = null;
            final double[] array2 = null;
            this.bra = array2;
            this.ara = array2;
            this._ra = array2;
            this.Zqa = array2;
            this.Yqa = array2;
            this.Xqa = array2;
            this.Wqa = array2;
            this.Vqa = array2;
            this.cra = null;
            return;
        }
        final int length = this.Vqa.length;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (this.Vqa[i] <= 0.0 || this.Zqa[i] <= 0.0) {
                b = false;
                break;
            }
        }
        if (b) {
            this.ora = this.Vqa;
            this.pra = this.Wqa;
            this.qra = this.Xqa;
            this.rra = this.Yqa;
            this.sra = this.Zqa;
            this.tra = this._ra;
            this.ura = this.ara;
            this.vra = this.bra;
            this.wra = this.cra;
            final double[] array3 = null;
            this.bra = array3;
            this.ara = array3;
            this._ra = array3;
            this.Zqa = array3;
            this.Yqa = array3;
            this.Xqa = array3;
            this.Wqa = array3;
            this.Vqa = array3;
            this.cra = null;
            return;
        }
        int n = 0;
        for (int j = 0; j < length; ++j) {
            if (this.Vqa[j] > 0.0 && this.Zqa[j] > 0.0) {
                ++n;
            }
        }
        if (length > 0 && n > 0) {
            this.ora = new double[n];
            this.pra = new double[n];
            this.qra = new double[n];
            this.rra = new double[n];
            this.sra = new double[n];
            this.tra = new double[n];
            this.ura = new double[n];
            this.vra = new double[n];
            this.wra = new String[n];
            int n2 = 0;
            for (int k = 0; k < length; ++k) {
                if (this.Vqa[k] > 0.0 && this.Zqa[k] > 0.0) {
                    this.ora[n2] = this.Vqa[k];
                    this.pra[n2] = this.Wqa[k];
                    this.qra[n2] = this.Xqa[k];
                    this.rra[n2] = this.Yqa[k];
                    this.sra[n2] = this.Zqa[k];
                    this.tra[n2] = this._ra[k];
                    this.ura[n2] = this.ara[k];
                    this.vra[n2] = this.bra[k];
                    this.wra[n2] = this.cra[k];
                    ++n2;
                }
            }
        }
        else {
            final double[] array4 = null;
            this.vra = array4;
            this.ura = array4;
            this.tra = array4;
            this.sra = array4;
            this.rra = array4;
            this.qra = array4;
            this.pra = array4;
            this.ora = array4;
            this.wra = null;
        }
        final double[] array5 = null;
        this.bra = array5;
        this.ara = array5;
        this._ra = array5;
        this.Zqa = array5;
        this.Yqa = array5;
        this.Xqa = array5;
        this.Wqa = array5;
        this.Vqa = array5;
        this.cra = null;
    }
    
    public synchronized double[][] _(final String s) {
        final Vh a = this.Qra.a(this.g(s), this.b());
        double[] qja = a.qja;
        double[] o = a.O;
        if (qja == null || o == null) {
            return null;
        }
        final int length = qja.length;
        int n = 0;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (qja[i] > 0.0 && o[i] > 0.0) {
                ++n;
            }
            else {
                b = false;
            }
        }
        if (!b) {
            if (length == 0 || n == 0) {
                return null;
            }
            qja = new double[n];
            o = new double[n];
            int n2 = 0;
            for (int j = 0; j < length; ++j) {
                if (a.qja[j] > 0.0 && a.O[j] > 0.0) {
                    qja[n2] = a.qja[j];
                    o[n2] = a.O[j];
                    ++n2;
                }
            }
        }
        if (this.b()) {
            return this.a(qja, o);
        }
        return this.b(qja, o);
    }
    
    private void T() {
        this.dra = null;
        final double[] l = null;
        this.info = l;
        this.Q = l;
        this.P = l;
        this.O = l;
        this.N = l;
        this.M = l;
        this.L = l;
        this.era = null;
        this.Vqa = null;
        final double[] wqa = null;
        this.bra = wqa;
        this.ara = wqa;
        this._ra = wqa;
        this.Zqa = wqa;
        this.Yqa = wqa;
        this.Xqa = wqa;
        this.Wqa = wqa;
        this.cra = null;
        this.fra = null;
        final double[] gra = null;
        this.mra = gra;
        this.lra = gra;
        this.kra = gra;
        this.jra = gra;
        this.ira = gra;
        this.hra = gra;
        this.gra = gra;
        this.nra = null;
        this.Jra = true;
        this.notifyObservers();
        this.ija = 2;
        this.Hra = false;
        if (this.Tqa == null || this.Tqa.equals("")) {
            this.Gra = this.kb.a().b("msgEnterSymbol");
            this.Nra = false;
            return;
        }
        boolean f = false;
        final String f2 = this.f(this.Tqa);
        this.Qqa._(0L);
        if (f2 != null) {
            f = this.Qqa.f(f2);
        }
        if (f || this.uja) {
            final boolean b = !this.Qra._(this.g(this.Tqa), this.Qqa, this.b(), this.foa, this.hja);
            this.ija = this.Qra._(this.g(this.Tqa), this.b());
            this.Qqa.clear();
            final Vh a = this.Qra.a(this.g(this.Tqa), this.b());
            this.Vqa = a.qja;
            this.Wqa = a.L;
            this.Xqa = a.M;
            this.Yqa = a.N;
            this.Zqa = a.O;
            this._ra = a.P;
            this.ara = a.Q;
            this.bra = a.info;
            this.cra = a.ma;
            this.Ab();
            if (!b) {
                this.Hra = false;
                this.Gra = this.Tqa + ": " + this.kb.a().b("msgDataLoaded");
                this.Nra = true;
            }
            else {
                this.Vqa = null;
                final double[] wqa2 = null;
                this.bra = wqa2;
                this.ara = wqa2;
                this._ra = wqa2;
                this.Zqa = wqa2;
                this.Yqa = wqa2;
                this.Xqa = wqa2;
                this.Wqa = wqa2;
                this.cra = null;
                this.dra = null;
                final double[] i = null;
                this.info = i;
                this.Q = i;
                this.P = i;
                this.O = i;
                this.N = i;
                this.M = i;
                this.L = i;
                this.era = null;
                if (f) {
                    this.Gra = this.Tqa + ": " + this.kb.a().b("msgInvalidData");
                }
                else {
                    this.Gra = this.Tqa + ": " + this.kb.a().b("msgNoDataAvailable");
                }
                this.Nra = false;
            }
        }
        else {
            this.Vqa = null;
            final double[] wqa3 = null;
            this.bra = wqa3;
            this.ara = wqa3;
            this._ra = wqa3;
            this.Zqa = wqa3;
            this.Yqa = wqa3;
            this.Xqa = wqa3;
            this.Wqa = wqa3;
            this.cra = null;
            this.dra = null;
            final double[] j = null;
            this.info = j;
            this.Q = j;
            this.P = j;
            this.O = j;
            this.N = j;
            this.M = j;
            this.L = j;
            this.era = null;
            this.Gra = this.Tqa + ": " + this.kb.a().b("msgNoDataAvailable");
            this.Nra = false;
        }
    }
    
    private void a(final q q) {
        this.dra = null;
        final double[] l = null;
        this.info = l;
        this.Q = l;
        this.P = l;
        this.O = l;
        this.N = l;
        this.M = l;
        this.L = l;
        this.era = null;
        this.Vqa = null;
        final double[] wqa = null;
        this.bra = wqa;
        this.ara = wqa;
        this._ra = wqa;
        this.Zqa = wqa;
        this.Yqa = wqa;
        this.Xqa = wqa;
        this.Wqa = wqa;
        this.cra = null;
        this.fra = null;
        final double[] gra = null;
        this.mra = gra;
        this.lra = gra;
        this.kra = gra;
        this.jra = gra;
        this.ira = gra;
        this.hra = gra;
        this.gra = gra;
        this.nra = null;
        this.Jra = true;
        this.notifyObservers();
        this.ija = 2;
        this.Hra = false;
        if (this.Tqa == null || this.Tqa.equals("")) {
            this.Gra = this.kb.a().b("msgEnterSymbol");
            this.Nra = false;
            return;
        }
        boolean b = false;
        this.Qqa._(0L);
        if (q != null) {
            b = this.Qqa.b(q);
        }
        if (b || this.uja) {
            final boolean b2 = !this.Qra._(this.g(this.Tqa), this.Qqa, this.b(), this.foa, this.hja);
            this.ija = this.Qra._(this.g(this.Tqa), this.b());
            this.Qqa.clear();
            final Vh a = this.Qra.a(this.g(this.Tqa), this.b());
            this.Vqa = a.qja;
            this.Wqa = a.L;
            this.Xqa = a.M;
            this.Yqa = a.N;
            this.Zqa = a.O;
            this._ra = a.P;
            this.ara = a.Q;
            this.bra = a.info;
            this.cra = a.ma;
            this.Ab();
            if (!b2) {
                this.Hra = false;
                this.Gra = this.Tqa + ": " + this.kb.a().b("msgDataLoaded");
                this.Nra = true;
            }
            else {
                this.Vqa = null;
                final double[] wqa2 = null;
                this.bra = wqa2;
                this.ara = wqa2;
                this._ra = wqa2;
                this.Zqa = wqa2;
                this.Yqa = wqa2;
                this.Xqa = wqa2;
                this.Wqa = wqa2;
                this.cra = null;
                this.dra = null;
                final double[] i = null;
                this.info = i;
                this.Q = i;
                this.P = i;
                this.O = i;
                this.N = i;
                this.M = i;
                this.L = i;
                this.era = null;
                if (b) {
                    this.Gra = this.Tqa + ": " + this.kb.a().b("msgInvalidData");
                }
                else {
                    this.Gra = this.Tqa + ": " + this.kb.a().b("msgNoDataAvailable");
                }
                this.Nra = false;
            }
        }
        else {
            this.Vqa = null;
            final double[] wqa3 = null;
            this.bra = wqa3;
            this.ara = wqa3;
            this._ra = wqa3;
            this.Zqa = wqa3;
            this.Yqa = wqa3;
            this.Xqa = wqa3;
            this.Wqa = wqa3;
            this.cra = null;
            this.dra = null;
            final double[] j = null;
            this.info = j;
            this.Q = j;
            this.P = j;
            this.O = j;
            this.N = j;
            this.M = j;
            this.L = j;
            this.era = null;
            this.Gra = this.Tqa + ": " + this.kb.a().b("msgNoDataAvailable");
            this.Nra = false;
        }
    }
    
    private String f(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String s2;
        if (this.b()) {
            s2 = this.Pqa;
        }
        else {
            s2 = this.source;
        }
        final String a = catch.a(catch.a(catch.a(catch.a(catch.a(this._(s2, this._(s)), "{UserID}", this.Rra), "{DataType}", this.b() ? "INTRA" : "EOD"), "{Periodicity}", const.d(this.Lra)), "{FirstDataTimestamp}", this.Qra.b(this.g(s), this.b())), "{LastDataTimestamp}", this.Qra.a(this.g(s), this.b()));
        String s3;
        if (this.b()) {
            s3 = Integer.toString(this.b());
        }
        else {
            s3 = Integer.toString(this.g());
        }
        return catch.a(a, "{Timeframe}", s3);
    }
    
    private void xb() {
        this.Vqa = null;
        final double[] wqa = null;
        this.bra = wqa;
        this.ara = wqa;
        this._ra = wqa;
        this.Zqa = wqa;
        this.Yqa = wqa;
        this.Xqa = wqa;
        this.Wqa = wqa;
        this.cra = null;
        this.ora = null;
        final double[] pra = null;
        this.vra = pra;
        this.ura = pra;
        this.tra = pra;
        this.sra = pra;
        this.rra = pra;
        this.qra = pra;
        this.pra = pra;
        this.wra = null;
        this.xra = null;
        final double[] yra = null;
        this.Era = yra;
        this.Dra = yra;
        this.Cra = yra;
        this.Bra = yra;
        this.Ara = yra;
        this.zra = yra;
        this.yra = yra;
        this.Fra = null;
        this.Kra = true;
        this.notifyObservers();
        this.Ira = false;
        if (this.Uqa == null || this.Uqa.equals("")) {
            this.Gra = "";
            this.Ora = false;
            return;
        }
        boolean f = false;
        final String f2 = this.f(this.Uqa);
        this.Qqa._(0L);
        if (f2 != null) {
            f = this.Qqa.f(f2);
        }
        if (f || this.uja) {
            final boolean b = !this.Qra._(this.g(this.Uqa), this.Qqa, this.b(), this.foa, this.hja);
            this.Qqa.clear();
            final Vh a = this.Qra.a(this.g(this.Uqa), this.b());
            this.Vqa = a.qja;
            this.Wqa = a.L;
            this.Xqa = a.M;
            this.Yqa = a.N;
            this.Zqa = a.O;
            this._ra = a.P;
            this.ara = a.Q;
            this.bra = a.info;
            this.cra = a.ma;
            this.Bb();
            if (!b) {
                this.Ira = false;
                this.Gra = "";
                this.Ora = true;
            }
            else {
                this.Vqa = null;
                final double[] wqa2 = null;
                this.bra = wqa2;
                this.ara = wqa2;
                this._ra = wqa2;
                this.Zqa = wqa2;
                this.Yqa = wqa2;
                this.Xqa = wqa2;
                this.Wqa = wqa2;
                this.cra = null;
                this.ora = null;
                final double[] pra2 = null;
                this.vra = pra2;
                this.ura = pra2;
                this.tra = pra2;
                this.sra = pra2;
                this.rra = pra2;
                this.qra = pra2;
                this.pra = pra2;
                this.wra = null;
                this.Gra = "";
                this.Ora = false;
            }
        }
        else {
            this.Vqa = null;
            final double[] wqa3 = null;
            this.bra = wqa3;
            this.ara = wqa3;
            this._ra = wqa3;
            this.Zqa = wqa3;
            this.Yqa = wqa3;
            this.Xqa = wqa3;
            this.Wqa = wqa3;
            this.cra = null;
            this.ora = null;
            final double[] pra3 = null;
            this.vra = pra3;
            this.ura = pra3;
            this.tra = pra3;
            this.sra = pra3;
            this.rra = pra3;
            this.qra = pra3;
            this.pra = pra3;
            this.wra = null;
            this.Gra = "";
            this.Ora = false;
        }
    }
    
    private void _(final q q) {
        this.Vqa = null;
        final double[] wqa = null;
        this.bra = wqa;
        this.ara = wqa;
        this._ra = wqa;
        this.Zqa = wqa;
        this.Yqa = wqa;
        this.Xqa = wqa;
        this.Wqa = wqa;
        this.cra = null;
        this.ora = null;
        final double[] pra = null;
        this.vra = pra;
        this.ura = pra;
        this.tra = pra;
        this.sra = pra;
        this.rra = pra;
        this.qra = pra;
        this.pra = pra;
        this.wra = null;
        this.xra = null;
        final double[] yra = null;
        this.Era = yra;
        this.Dra = yra;
        this.Cra = yra;
        this.Bra = yra;
        this.Ara = yra;
        this.zra = yra;
        this.yra = yra;
        this.Fra = null;
        this.Kra = true;
        this.notifyObservers();
        this.Ira = false;
        if (this.Uqa == null || this.Uqa.equals("")) {
            this.Gra = "";
            this.Ora = false;
            return;
        }
        boolean b = false;
        this.Qqa._(0L);
        if (q != null) {
            b = this.Qqa.b(q);
        }
        if (b || this.uja) {
            final boolean b2 = !this.Qra._(this.g(this.Uqa), this.Qqa, this.b(), this.foa, this.hja);
            this.Qqa.clear();
            final Vh a = this.Qra.a(this.g(this.Uqa), this.b());
            this.Vqa = a.qja;
            this.Wqa = a.L;
            this.Xqa = a.M;
            this.Yqa = a.N;
            this.Zqa = a.O;
            this._ra = a.P;
            this.ara = a.Q;
            this.bra = a.info;
            this.cra = a.ma;
            this.Bb();
            if (!b2) {
                this.Ira = false;
                this.Gra = "";
                this.Ora = true;
            }
            else {
                this.Vqa = null;
                final double[] wqa2 = null;
                this.bra = wqa2;
                this.ara = wqa2;
                this._ra = wqa2;
                this.Zqa = wqa2;
                this.Yqa = wqa2;
                this.Xqa = wqa2;
                this.Wqa = wqa2;
                this.cra = null;
                this.ora = null;
                final double[] pra2 = null;
                this.vra = pra2;
                this.ura = pra2;
                this.tra = pra2;
                this.sra = pra2;
                this.rra = pra2;
                this.qra = pra2;
                this.pra = pra2;
                this.wra = null;
                this.Gra = "";
                this.Ora = false;
            }
        }
        else {
            this.Vqa = null;
            final double[] wqa3 = null;
            this.bra = wqa3;
            this.ara = wqa3;
            this._ra = wqa3;
            this.Zqa = wqa3;
            this.Yqa = wqa3;
            this.Xqa = wqa3;
            this.Wqa = wqa3;
            this.cra = null;
            this.ora = null;
            final double[] pra3 = null;
            this.vra = pra3;
            this.ura = pra3;
            this.tra = pra3;
            this.sra = pra3;
            this.rra = pra3;
            this.qra = pra3;
            this.pra = pra3;
            this.wra = null;
            this.Gra = "";
            this.Ora = false;
        }
    }
    
    private double[] b(final double[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final double[] array2 = new double[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    private String[] _(final String[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final String[] array2 = new String[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    private double[][] a(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        this.jja.b(array);
        final int length = array.length;
        final double[] array3 = new double[length];
        final double[] array4 = new double[length];
        if (this.Mra) {
            System.arraycopy(array, 0, array3, 0, length);
            System.arraycopy(array2, 0, array4, 0, length);
            return new double[][] { array3, array4 };
        }
        long n = -1L;
        int n2 = -1;
        int n3 = -1;
        int n4 = 1;
        for (int i = 0; i < length; ++i) {
            this.jja._(array[i]);
            long n5;
            if (this.Lra == 0) {
                n5 = i;
            }
            else {
                n5 = this.jja.l() / this.Lra;
                if (n5 > 0L && this.jja.l() % this.Lra == 0L) {
                    --n5;
                }
            }
            if (this.Lra >= 60) {
                final int n6 = (int)array[i];
                if (n6 != n3) {
                    n4 = 1;
                }
                n3 = n6;
                if (n4 != 0) {
                    if (this.jja.q() == this.Sra) {
                        if (this.jja.l() % this.Lra == 0L) {
                            ++n5;
                        }
                    }
                    else {
                        n4 = 0;
                    }
                }
            }
            if (n5 != n) {
                ++n2;
            }
            array3[n2] = array[i];
            array4[n2] = array2[i];
            n = n5;
        }
        if (this.Lra != 0) {
            for (int j = 0; j < n2; ++j) {
                this.jja._(array3[j]);
                this.jja.u(this.Lra);
                array3[j] = this.jja._();
            }
        }
        return new double[][] { this.b(array3, n2 + 1), this.b(array4, n2 + 1) };
    }
    
    private double[][] b(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        final int length = array.length;
        final double[] array3 = new double[length];
        final double[] array4 = new double[length];
        if (this.Mra) {
            System.arraycopy(array, 0, array3, 0, length);
            System.arraycopy(array2, 0, array4, 0, length);
            return new double[][] { array3, array4 };
        }
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.kja._(array[i]);
            int n3;
            if (this.Lra == 100002) {
                n3 = this.kja.z();
            }
            else if (this.Lra == 100003) {
                n3 = this.kja.p();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
            }
            array3[n2] = array[i];
            array4[n2] = array2[i];
            n = n3;
        }
        return new double[][] { this.b(array3, n2 + 1), this.b(array4, n2 + 1) };
    }
    
    private void Cb() {
        this.jja.b(this.dra);
        final int length = this.dra.length;
        this.fra = new double[length];
        this.gra = new double[length];
        this.hra = new double[length];
        this.ira = new double[length];
        this.jra = new double[length];
        this.kra = new double[length];
        this.lra = new double[length];
        this.mra = new double[length];
        this.nra = new String[length];
        if (this.Mra) {
            System.arraycopy(this.dra, 0, this.fra, 0, length);
            System.arraycopy(this.L, 0, this.gra, 0, length);
            System.arraycopy(this.M, 0, this.hra, 0, length);
            System.arraycopy(this.N, 0, this.ira, 0, length);
            System.arraycopy(this.O, 0, this.jra, 0, length);
            System.arraycopy(this.P, 0, this.kra, 0, length);
            System.arraycopy(this.Q, 0, this.lra, 0, length);
            System.arraycopy(this.info, 0, this.mra, 0, length);
            System.arraycopy(this.era, 0, this.nra, 0, length);
            return;
        }
        long n = -1L;
        int n2 = -1;
        int n3 = -1;
        int n4 = 1;
        for (int i = 0; i < length; ++i) {
            this.jja._(this.dra[i]);
            long n5;
            if (this.Lra == 0) {
                n5 = i;
            }
            else {
                n5 = this.jja.l() / this.Lra;
                if (n5 > 0L && this.jja.l() % this.Lra == 0L) {
                    --n5;
                }
            }
            if (this.Lra >= 60) {
                final int n6 = (int)this.dra[i];
                if (n6 != n3) {
                    n4 = 1;
                }
                n3 = n6;
                if (n4 != 0) {
                    if (this.jja.q() == this.Sra) {
                        if (this.jja.l() % this.Lra == 0L) {
                            ++n5;
                        }
                    }
                    else {
                        n4 = 0;
                    }
                }
            }
            if (n5 != n) {
                ++n2;
                this.gra[n2] = this.L[i];
                this.ira[n2] = Double.POSITIVE_INFINITY;
                this.hra[n2] = Double.NEGATIVE_INFINITY;
            }
            this.fra[n2] = this.dra[i];
            this.jra[n2] = this.O[i];
            this.hra[n2] = Math.max(this.hra[n2], this.M[i]);
            this.ira[n2] = Math.min(this.ira[n2], this.N[i]);
            final double[] kra = this.kra;
            final int n7 = n2;
            kra[n7] += this.P[i];
            this.lra[n2] = this.Q[i];
            this.mra[n2] = this.info[i];
            if (this.Lra == 0) {
                this.nra[n2] = this.era[i];
            }
            else if (this.era[i] != null && "P".equals(new this(this.era[i]).vla)) {
                this.nra[n2] = this.era[i];
            }
            n = n5;
        }
        if (this.Lra != 0) {
            for (int j = 0; j < n2; ++j) {
                this.jja._(this.fra[j]);
                this.jja.u(this.Lra);
                this.fra[j] = this.jja._();
            }
        }
        this.fra = this.b(this.fra, n2 + 1);
        this.gra = this.b(this.gra, n2 + 1);
        this.hra = this.b(this.hra, n2 + 1);
        this.ira = this.b(this.ira, n2 + 1);
        this.jra = this.b(this.jra, n2 + 1);
        this.kra = this.b(this.kra, n2 + 1);
        this.lra = this.b(this.lra, n2 + 1);
        this.mra = this.b(this.mra, n2 + 1);
        this.nra = this._(this.nra, n2 + 1);
    }
    
    private void Db() {
        final int length = this.dra.length;
        this.fra = new double[length];
        this.gra = new double[length];
        this.hra = new double[length];
        this.ira = new double[length];
        this.jra = new double[length];
        this.kra = new double[length];
        this.lra = new double[length];
        this.mra = new double[length];
        this.nra = new String[length];
        if (this.Mra) {
            System.arraycopy(this.dra, 0, this.fra, 0, length);
            System.arraycopy(this.L, 0, this.gra, 0, length);
            System.arraycopy(this.M, 0, this.hra, 0, length);
            System.arraycopy(this.N, 0, this.ira, 0, length);
            System.arraycopy(this.O, 0, this.jra, 0, length);
            System.arraycopy(this.P, 0, this.kra, 0, length);
            System.arraycopy(this.Q, 0, this.lra, 0, length);
            System.arraycopy(this.info, 0, this.mra, 0, length);
            System.arraycopy(this.era, 0, this.nra, 0, length);
            return;
        }
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.kja._(this.dra[i]);
            int n3;
            if (this.Lra == 100002) {
                n3 = this.kja.z();
            }
            else if (this.Lra == 100003) {
                n3 = this.kja.p();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
                this.gra[n2] = this.L[i];
                this.ira[n2] = Double.POSITIVE_INFINITY;
                this.hra[n2] = Double.NEGATIVE_INFINITY;
            }
            this.fra[n2] = this.dra[i];
            this.jra[n2] = this.O[i];
            this.hra[n2] = Math.max(this.hra[n2], this.M[i]);
            this.ira[n2] = Math.min(this.ira[n2], this.N[i]);
            final double[] kra = this.kra;
            final int n4 = n2;
            kra[n4] += this.P[i];
            this.lra[n2] = this.Q[i];
            this.mra[n2] = this.info[i];
            if (this.Lra == 100001) {
                this.nra[n2] = this.era[i];
            }
            else if (this.era[i] != null && "P".equals(new this(this.era[i]).vla)) {
                this.nra[n2] = this.era[i];
            }
            n = n3;
        }
        this.fra = this.b(this.fra, n2 + 1);
        this.gra = this.b(this.gra, n2 + 1);
        this.hra = this.b(this.hra, n2 + 1);
        this.ira = this.b(this.ira, n2 + 1);
        this.jra = this.b(this.jra, n2 + 1);
        this.kra = this.b(this.kra, n2 + 1);
        this.lra = this.b(this.lra, n2 + 1);
        this.mra = this.b(this.mra, n2 + 1);
        this.nra = this._(this.nra, n2 + 1);
    }
    
    private void Eb() {
        this.jja.b(this.ora);
        final int length = this.ora.length;
        this.xra = new double[length];
        this.yra = new double[length];
        this.zra = new double[length];
        this.Ara = new double[length];
        this.Bra = new double[length];
        this.Cra = new double[length];
        this.Dra = new double[length];
        this.Era = new double[length];
        this.Fra = new String[length];
        if (this.Mra) {
            System.arraycopy(this.ora, 0, this.xra, 0, length);
            System.arraycopy(this.pra, 0, this.yra, 0, length);
            System.arraycopy(this.qra, 0, this.zra, 0, length);
            System.arraycopy(this.rra, 0, this.Ara, 0, length);
            System.arraycopy(this.sra, 0, this.Bra, 0, length);
            System.arraycopy(this.tra, 0, this.Cra, 0, length);
            System.arraycopy(this.ura, 0, this.Dra, 0, length);
            System.arraycopy(this.vra, 0, this.Era, 0, length);
            System.arraycopy(this.wra, 0, this.Fra, 0, length);
            return;
        }
        long n = -1L;
        int n2 = -1;
        int n3 = -1;
        int n4 = 1;
        for (int i = 0; i < length; ++i) {
            this.jja._(this.ora[i]);
            long n5;
            if (this.Lra == 0) {
                n5 = i;
            }
            else {
                n5 = this.jja.l() / this.Lra;
                if (n5 > 0L && this.jja.l() % this.Lra == 0L) {
                    --n5;
                }
            }
            if (this.Lra >= 60) {
                final int n6 = (int)this.ora[i];
                if (n6 != n3) {
                    n4 = 1;
                }
                n3 = n6;
                if (n4 != 0) {
                    if (this.jja.q() == this.Sra) {
                        if (this.jja.l() % this.Lra == 0L) {
                            ++n5;
                        }
                    }
                    else {
                        n4 = 0;
                    }
                }
            }
            if (n5 != n) {
                ++n2;
                this.yra[n2] = this.pra[i];
                this.Ara[n2] = Double.POSITIVE_INFINITY;
                this.zra[n2] = Double.NEGATIVE_INFINITY;
            }
            this.xra[n2] = this.ora[i];
            this.Bra[n2] = this.sra[i];
            this.zra[n2] = Math.max(this.zra[n2], this.qra[i]);
            this.Ara[n2] = Math.min(this.Ara[n2], this.rra[i]);
            final double[] cra = this.Cra;
            final int n7 = n2;
            cra[n7] += this.tra[i];
            this.Dra[n2] = this.ura[i];
            this.Era[n2] = this.vra[i];
            if (this.Lra == 0) {
                this.Fra[n2] = this.wra[i];
            }
            else if (this.wra[i] != null && "P".equals(new this(this.wra[i]).vla)) {
                this.Fra[n2] = this.wra[i];
            }
            n = n5;
        }
        if (this.Lra != 0) {
            for (int j = 0; j < n2; ++j) {
                this.jja._(this.xra[j]);
                this.jja.u(this.Lra);
                this.xra[j] = this.jja._();
            }
        }
        this.xra = this.b(this.xra, n2 + 1);
        this.yra = this.b(this.yra, n2 + 1);
        this.zra = this.b(this.zra, n2 + 1);
        this.Ara = this.b(this.Ara, n2 + 1);
        this.Bra = this.b(this.Bra, n2 + 1);
        this.Cra = this.b(this.Cra, n2 + 1);
        this.Dra = this.b(this.Dra, n2 + 1);
        this.Era = this.b(this.Era, n2 + 1);
        this.Fra = this._(this.Fra, n2 + 1);
    }
    
    private void Fb() {
        final int length = this.ora.length;
        this.xra = new double[length];
        this.yra = new double[length];
        this.zra = new double[length];
        this.Ara = new double[length];
        this.Bra = new double[length];
        this.Cra = new double[length];
        this.Dra = new double[length];
        this.Era = new double[length];
        this.Fra = new String[length];
        if (this.Mra) {
            System.arraycopy(this.ora, 0, this.xra, 0, length);
            System.arraycopy(this.pra, 0, this.yra, 0, length);
            System.arraycopy(this.qra, 0, this.zra, 0, length);
            System.arraycopy(this.rra, 0, this.Ara, 0, length);
            System.arraycopy(this.sra, 0, this.Bra, 0, length);
            System.arraycopy(this.tra, 0, this.Cra, 0, length);
            System.arraycopy(this.ura, 0, this.Dra, 0, length);
            System.arraycopy(this.vra, 0, this.Era, 0, length);
            System.arraycopy(this.wra, 0, this.Fra, 0, length);
            return;
        }
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.kja._(this.ora[i]);
            int n3;
            if (this.Lra == 100002) {
                n3 = this.kja.z();
            }
            else if (this.Lra == 100003) {
                n3 = this.kja.p();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
                this.yra[n2] = this.pra[i];
                this.Ara[n2] = Double.POSITIVE_INFINITY;
                this.zra[n2] = Double.NEGATIVE_INFINITY;
            }
            this.xra[n2] = this.ora[i];
            this.Bra[n2] = this.sra[i];
            this.zra[n2] = Math.max(this.zra[n2], this.qra[i]);
            this.Ara[n2] = Math.min(this.Ara[n2], this.rra[i]);
            final double[] cra = this.Cra;
            final int n4 = n2;
            cra[n4] += this.tra[i];
            this.Dra[n2] = this.ura[i];
            this.Era[n2] = this.vra[i];
            if (this.Lra == 100001) {
                this.Fra[n2] = this.wra[i];
            }
            else if (this.wra[i] != null && "P".equals(new this(this.wra[i]).vla)) {
                this.Fra[n2] = this.wra[i];
            }
            n = n3;
        }
        this.xra = this.b(this.xra, n2 + 1);
        this.yra = this.b(this.yra, n2 + 1);
        this.zra = this.b(this.zra, n2 + 1);
        this.Ara = this.b(this.Ara, n2 + 1);
        this.Bra = this.b(this.Bra, n2 + 1);
        this.Cra = this.b(this.Cra, n2 + 1);
        this.Dra = this.b(this.Dra, n2 + 1);
        this.Era = this.b(this.Era, n2 + 1);
        this.Fra = this._(this.Fra, n2 + 1);
    }
    
    private void wb() {
        if (this.dra == null) {
            this.fra = null;
            this.gra = null;
            this.hra = null;
            this.ira = null;
            this.jra = null;
            this.kra = null;
            this.lra = null;
            this.mra = null;
            this.nra = null;
        }
        else if (this.Jra) {
            if (this.b()) {
                this.Cb();
            }
            else {
                this.Db();
            }
            this.Jra = false;
        }
    }
    
    private void yb() {
        if (this.ora == null) {
            this.xra = null;
            this.yra = null;
            this.zra = null;
            this.Ara = null;
            this.Bra = null;
            this.Cra = null;
            this.Dra = null;
            this.Era = null;
            this.Fra = null;
        }
        else if (this.Kra) {
            if (this.b()) {
                this.Eb();
            }
            else {
                this.Fb();
            }
            this.Kra = false;
        }
    }
    
    public synchronized String[][] b() {
        final String[] array = new String[this.Sqa.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.f(this.Sqa[i]);
        }
        return new String[][] { this.Sqa, array };
    }
    
    public synchronized String[] c() {
        return this.Sqa;
    }
    
    public synchronized void a(final String[] sqa) {
        this.Sqa = sqa;
        this.zb();
    }
    
    private void zb() {
        final Vector<String> vector = new Vector<String>();
        if (this.Tqa != null) {
            vector.addElement(this.g(this.Tqa));
        }
        if (this.Uqa != null) {
            vector.addElement(this.g(this.Uqa));
        }
        for (int i = 0; i < this.Sqa.length; ++i) {
            vector.addElement(this.g(this.Sqa[i]));
        }
        this.Qra.a(vector);
    }
    
    public synchronized void H(final String nqa) {
        this.Nqa = nqa;
    }
    
    public synchronized void I(final String oqa) {
        this.Oqa = oqa;
    }
    
    public synchronized String N() {
        if (this.Oqa == null || this.Oqa.length() == 0) {
            return null;
        }
        final boolean h = this.Rqa.h(this.Oqa);
        String text = null;
        if (h) {
            text = this.Rqa.getText();
        }
        this.Rqa.clear();
        return text;
    }
    
    public synchronized void J(final String text) {
        if (text == null || text.length() == 0) {
            return;
        }
        if (this.Nqa == null || this.Nqa.length() == 0) {
            return;
        }
        this.Rqa.setText(text);
        this.Rqa._(this.Nqa, "indicators");
        this.Rqa.clear();
    }
    
    public String O() {
        return this.Rra;
    }
    
    public synchronized boolean h() {
        return this.Mra;
    }
    
    public synchronized void O(final boolean mra) {
        this.Mra = mra;
    }
    
    public synchronized void a(final Wh wh) {
        if (wh.pja && (!this.Pra.containsKey(wh.name) || !wh.name.equals(wh.nja))) {
            this.Pra.put(wh.name, wh.nja);
        }
    }
    
    public synchronized void b(final Wh wh) {
        if (wh.pja) {
            this.Pra.put(wh.name, wh.nja);
        }
    }
    
    public synchronized String _(final String s) {
        if (s == null) {
            return null;
        }
        final String s2 = this.Pra.get(s);
        return (s2 == null) ? s : s2;
    }
    
    public synchronized void Gb() {
        this.Pra.clear();
    }
}
