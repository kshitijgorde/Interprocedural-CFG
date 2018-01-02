import java.util.Vector;
import java.net.URLEncoder;
import java.util.Observer;
import java.util.Hashtable;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Observable
{
    public static final int Mua = 0;
    public static final int Nua = 1;
    public static final int Oua = 2;
    public static final int Pua = 3;
    public static final int Qua = 4;
    public static final int Rua = 5;
    public static final int Sua = 10;
    public static final int Tua = 15;
    public static final int Uua = 20;
    public static final int Vua = 30;
    public static final int Wua = 60;
    public static final int Xua = 120;
    public static final int Yua = 180;
    public static final int Zua = 240;
    public static final int _va = 300;
    public static final int ava = 600;
    public static final int bva = 900;
    public static final int cva = 1200;
    public static final int dva = 1800;
    public static final int eva = 3600;
    public static final int fva = 100001;
    public static final int gva = 100002;
    public static final int hva = 100003;
    private int Zpa;
    private int Ypa;
    private String iva;
    private String jva;
    private String l;
    private String source;
    private String kva;
    private this lva;
    private this mva;
    private String[] nva;
    private String ova;
    private String pva;
    private double[] qva;
    private double[] rva;
    private double[] sva;
    private double[] tva;
    private double[] uva;
    private double[] vva;
    private double[] wva;
    private double[] xva;
    private double[] yva;
    private double[] kpa;
    private double[] lpa;
    private double[] mpa;
    private double[] npa;
    private double[] opa;
    private double[] ppa;
    private double[] info;
    private double[] zva;
    private double[] Ava;
    private double[] Bva;
    private double[] Cva;
    private double[] Dva;
    private double[] Eva;
    private double[] Fva;
    private double[] Gva;
    private double[] Hva;
    private double[] Iva;
    private double[] Jva;
    private double[] Kva;
    private double[] Lva;
    private double[] Mva;
    private double[] Nva;
    private double[] Ova;
    private double[] Pva;
    private double[] Qva;
    private double[] Rva;
    private double[] Sva;
    private double[] Tva;
    private double[] Uva;
    private double[] Vva;
    private double[] Wva;
    private String Xva;
    private boolean Yva;
    private boolean Zva;
    private boolean _wa;
    private boolean awa;
    private int bwa;
    private boolean cwa;
    private boolean dwa;
    private boolean ewa;
    private final Hashtable fwa;
    private if gwa;
    private while e;
    private boolean iqa;
    private final String hwa;
    throw aqa;
    else _qa;
    private Observer iwa;
    
    public synchronized void a(final int n) {
        this.gwa.a(n);
    }
    
    public synchronized void _(final int n) {
        this.gwa._(n);
    }
    
    public synchronized int h() {
        return this.gwa.h();
    }
    
    public synchronized int e() {
        return this.gwa.e();
    }
    
    public b(final String source, final String kva, final while e, final int n, final String s) {
        this.Zpa = 2;
        this.Ypa = -1;
        this.iva = "";
        this.jva = "";
        this.l = "YY-MM-DD";
        this.fwa = new Hashtable(500);
        this.iqa = false;
        this.aqa = new throw();
        this._qa = new else();
        this.iwa = null;
        this.e = e;
        this.source = source;
        this.kva = kva;
        this.hwa = ("".equals(s) ? null : s);
        this.ova = null;
        this.nva = new String[0];
        (this.lva = new this())._(this.iqa);
        (this.mva = new this())._(false);
        this.gwa = new if(n);
        this.Yva = true;
        this._wa = true;
        this.Zva = true;
        this.awa = true;
        this.bwa = 100001;
        this.cwa = false;
        this.dwa = false;
        this.ewa = false;
        this.x(this.l);
    }
    
    public synchronized void _(final boolean iqa) {
        this.iqa = iqa;
        this.lva._(iqa);
    }
    
    public synchronized void i() {
        this.Yva = true;
        this.Zva = true;
        this._wa = true;
        this.awa = true;
    }
    
    public synchronized boolean a() {
        return this.bwa >= 0 && this.bwa <= 3600;
    }
    
    public synchronized int c() {
        return this.Zpa;
    }
    
    public synchronized void Y(final int ypa) {
        this.Ypa = ypa;
    }
    
    public synchronized void x(final String s) {
        this.l = s.toUpperCase();
    }
    
    public synchronized void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }
    
    public synchronized void ha() {
        this.lva.deleteObservers();
        this.mva.deleteObservers();
        this.iwa = null;
    }
    
    public synchronized void b(final Observer iwa) {
        this.lva.addObserver(iwa);
        this.mva.addObserver(iwa);
        this.iwa = iwa;
    }
    
    public synchronized boolean m() {
        return this.dwa;
    }
    
    public synchronized void b(final int bwa) {
        if (this.bwa != bwa) {
            final boolean a = this.a();
            this.bwa = bwa;
            if (a != this.a()) {
                this.Yva = true;
                this.Zva = true;
            }
            this._wa = true;
            this.awa = true;
            if (this.cwa) {
                this.Yva = true;
                this.Zva = true;
                this._wa = true;
                this.awa = true;
            }
        }
        this.notifyObservers();
    }
    
    public synchronized int ia() {
        return this.bwa;
    }
    
    public synchronized void ia() {
        this.h();
    }
    
    public synchronized void h() {
        final implements implements1 = new implements();
        implements1._(this.iqa);
        if (this.iwa != null) {
            implements1.addObserver(this.iwa);
        }
        final String[][] a = this.e._().a();
        final String[] array = a[0];
        final String[] array2 = a[1];
        implements1.a(this.ova, this.f());
        implements1.a(this.pva, this.g());
        for (int i = 0; i < array.length; ++i) {
            implements1.a(array[i], array2[i]);
        }
        implements1.C();
        final this[] array3 = new this[array.length];
        for (int j = 0; j < array.length; ++j) {
            array3[j] = implements1.b(array[j]);
        }
        this.b(array, array2, array3);
        if (this.pva != null && this.pva.length() > 0) {
            this b = implements1.b(this.pva);
            if (b == null) {
                b = new this();
            }
            this.a(b);
        }
        if (this.ova != null && this.ova.length() > 0) {
            this b2 = implements1.b(this.ova);
            if (b2 == null) {
                b2 = new this();
            }
            this.b(b2);
        }
        implements1.reset();
        implements1.deleteObservers();
    }
    
    public synchronized void b(final String s, final this this2, final String s2, final this this3) {
        if (this.pva != null && this.pva.length() > 0 && s2 != null && s2.length() > 0 && s2.equals(this.e(this.pva)) && this3 != null) {
            this.a(this3);
        }
        if (this.ova != null && this.ova.length() > 0 && s != null && s.length() > 0 && s.equals(this.e(this.ova)) && this2 != null) {
            this.b(this2);
        }
    }
    
    public synchronized void b(final String[] array, final String[] array2, final this[] array3) {
        for (int i = 0; i < array.length; ++i) {
            if (array3[i] != null && array2[i].equals(this.e(array[i]))) {
                this.gwa.a(this.f(array[i]), array3[i], this.a(), this.l, this.Ypa);
            }
        }
    }
    
    public synchronized String f() {
        if (this.ova == null || this.ova.length() == 0) {
            return null;
        }
        return this.e(this.ova);
    }
    
    public synchronized String g() {
        if (this.pva == null || this.pva.length() == 0) {
            return null;
        }
        return this.e(this.pva);
    }
    
    public synchronized double[] a() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.zva;
    }
    
    public synchronized double[] k() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Pva;
    }
    
    public synchronized double[] b() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Ava;
    }
    
    public synchronized double[] l() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Qva;
    }
    
    public synchronized double[] _() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Bva;
    }
    
    public synchronized double[] m() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Rva;
    }
    
    public synchronized double[] g() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Cva;
    }
    
    public synchronized double[] n() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Sva;
    }
    
    public synchronized double[] h() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Dva;
    }
    
    public synchronized double[] c() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Tva;
    }
    
    public synchronized double[] i() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Eva;
    }
    
    public synchronized double[] ba() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Uva;
    }
    
    public synchronized double[] j() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Fva;
    }
    
    public synchronized double[] f() {
        if (this.Yva) {
            this.o();
        }
        this.p();
        return this.Gva;
    }
    
    public synchronized double[] ca() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Vva;
    }
    
    public synchronized double[] da() {
        if (this.Zva) {
            this.q();
        }
        this.r();
        return this.Wva;
    }
    
    public synchronized void _(final String ova) {
        this.Yva = true;
        this.ova = ova;
        this.s();
    }
    
    public synchronized void b(final String pva) {
        this.Zva = true;
        this.pva = pva;
        this.s();
    }
    
    public synchronized String _() {
        return this.ova;
    }
    
    private String _(final String s, String encode) {
        final String s2 = "{SYMBOL}";
        final String s3 = "{symbol}";
        final String s4 = "{Symbol}";
        if (encode == null) {
            encode = "";
        }
        encode = URLEncoder.encode(encode);
        return a.b(a.b(a.b(s, s2, encode.toUpperCase()), s3, encode.toLowerCase()), s4, encode);
    }
    
    private String f(final String s) {
        return this.b(s) + (this.cwa ? ("{P:" + this.bwa + "}") : "");
    }
    
    public synchronized String n() {
        return this.pva;
    }
    
    public synchronized String getMessage() {
        return this.Xva;
    }
    
    private void Ua() {
        if (this.qva == null || this.uva == null) {
            final double[] array = null;
            this.info = array;
            this.ppa = array;
            this.opa = array;
            this.npa = array;
            this.mpa = array;
            this.lpa = array;
            this.kpa = array;
            this.yva = array;
            final double[] array2 = null;
            this.xva = array2;
            this.wva = array2;
            this.vva = array2;
            this.uva = array2;
            this.tva = array2;
            this.sva = array2;
            this.rva = array2;
            this.qva = array2;
            return;
        }
        final int length = this.qva.length;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (this.qva[i] <= 0.0 || this.uva[i] <= 0.0) {
                b = false;
                break;
            }
        }
        if (b) {
            this.yva = this.qva;
            this.kpa = this.rva;
            this.lpa = this.sva;
            this.mpa = this.tva;
            this.npa = this.uva;
            this.opa = this.vva;
            this.ppa = this.wva;
            this.info = this.xva;
            final double[] array3 = null;
            this.xva = array3;
            this.wva = array3;
            this.vva = array3;
            this.uva = array3;
            this.tva = array3;
            this.sva = array3;
            this.rva = array3;
            this.qva = array3;
            return;
        }
        int n = 0;
        for (int j = 0; j < length; ++j) {
            if (this.qva[j] > 0.0 && this.uva[j] > 0.0) {
                ++n;
            }
        }
        if (length > 0 && n > 0) {
            this.yva = new double[n];
            this.kpa = new double[n];
            this.lpa = new double[n];
            this.mpa = new double[n];
            this.npa = new double[n];
            this.opa = new double[n];
            this.ppa = new double[n];
            this.info = new double[n];
            int n2 = 0;
            for (int k = 0; k < length; ++k) {
                if (this.qva[k] > 0.0 && this.uva[k] > 0.0) {
                    this.yva[n2] = this.qva[k];
                    this.kpa[n2] = this.rva[k];
                    this.lpa[n2] = this.sva[k];
                    this.mpa[n2] = this.tva[k];
                    this.npa[n2] = this.uva[k];
                    this.opa[n2] = this.vva[k];
                    this.ppa[n2] = this.wva[k];
                    this.info[n2] = this.xva[k];
                    ++n2;
                }
            }
        }
        else {
            final double[] array4 = null;
            this.info = array4;
            this.ppa = array4;
            this.opa = array4;
            this.npa = array4;
            this.mpa = array4;
            this.lpa = array4;
            this.kpa = array4;
            this.yva = array4;
        }
        final double[] array5 = null;
        this.xva = array5;
        this.wva = array5;
        this.vva = array5;
        this.uva = array5;
        this.tva = array5;
        this.sva = array5;
        this.rva = array5;
        this.qva = array5;
    }
    
    private void Va() {
        if (this.qva == null || this.uva == null) {
            final double[] array = null;
            this.Ova = array;
            this.Nva = array;
            this.Mva = array;
            this.Lva = array;
            this.Kva = array;
            this.Jva = array;
            this.Iva = array;
            this.Hva = array;
            final double[] array2 = null;
            this.xva = array2;
            this.wva = array2;
            this.vva = array2;
            this.uva = array2;
            this.tva = array2;
            this.sva = array2;
            this.rva = array2;
            this.qva = array2;
            return;
        }
        final int length = this.qva.length;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (this.qva[i] <= 0.0 || this.uva[i] <= 0.0) {
                b = false;
                break;
            }
        }
        if (b) {
            this.Hva = this.qva;
            this.Iva = this.rva;
            this.Jva = this.sva;
            this.Kva = this.tva;
            this.Lva = this.uva;
            this.Mva = this.vva;
            this.Nva = this.wva;
            this.Ova = this.xva;
            final double[] array3 = null;
            this.xva = array3;
            this.wva = array3;
            this.vva = array3;
            this.uva = array3;
            this.tva = array3;
            this.sva = array3;
            this.rva = array3;
            this.qva = array3;
            return;
        }
        int n = 0;
        for (int j = 0; j < length; ++j) {
            if (this.qva[j] > 0.0 && this.uva[j] > 0.0) {
                ++n;
            }
        }
        if (length > 0 && n > 0) {
            this.Hva = new double[n];
            this.Iva = new double[n];
            this.Jva = new double[n];
            this.Kva = new double[n];
            this.Lva = new double[n];
            this.Mva = new double[n];
            this.Nva = new double[n];
            this.Ova = new double[n];
            int n2 = 0;
            for (int k = 0; k < length; ++k) {
                if (this.qva[k] > 0.0 && this.uva[k] > 0.0) {
                    this.Hva[n2] = this.qva[k];
                    this.Iva[n2] = this.rva[k];
                    this.Jva[n2] = this.sva[k];
                    this.Kva[n2] = this.tva[k];
                    this.Lva[n2] = this.uva[k];
                    this.Mva[n2] = this.vva[k];
                    this.Nva[n2] = this.wva[k];
                    this.Ova[n2] = this.xva[k];
                    ++n2;
                }
            }
        }
        else {
            final double[] array4 = null;
            this.Ova = array4;
            this.Nva = array4;
            this.Mva = array4;
            this.Lva = array4;
            this.Kva = array4;
            this.Jva = array4;
            this.Iva = array4;
            this.Hva = array4;
        }
        final double[] array5 = null;
        this.xva = array5;
        this.wva = array5;
        this.vva = array5;
        this.uva = array5;
        this.tva = array5;
        this.sva = array5;
        this.rva = array5;
        this.qva = array5;
    }
    
    public synchronized double[][] a(final String s) {
        final double[][] _ = this.gwa._(this.f(s), this.a());
        double[] array = _[0];
        double[] array2 = _[4];
        if (array == null || array2 == null) {
            return null;
        }
        final int length = array.length;
        int n = 0;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (array[i] > 0.0 && array2[i] > 0.0) {
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
            array = new double[n];
            array2 = new double[n];
            int n2 = 0;
            for (int j = 0; j < length; ++j) {
                if (_[0][j] > 0.0 && _[0][j] > 0.0) {
                    array[n2] = _[0][j];
                    array2[n2] = _[4][j];
                    ++n2;
                }
            }
        }
        if (this.a()) {
            return this.b(array, array2);
        }
        return this._(array, array2);
    }
    
    private void o() {
        this.yva = null;
        final double[] kpa = null;
        this.info = kpa;
        this.ppa = kpa;
        this.opa = kpa;
        this.npa = kpa;
        this.mpa = kpa;
        this.lpa = kpa;
        this.kpa = kpa;
        this.qva = null;
        final double[] rva = null;
        this.xva = rva;
        this.wva = rva;
        this.vva = rva;
        this.uva = rva;
        this.tva = rva;
        this.sva = rva;
        this.rva = rva;
        this.zva = null;
        final double[] ava = null;
        this.Gva = ava;
        this.Fva = ava;
        this.Eva = ava;
        this.Dva = ava;
        this.Cva = ava;
        this.Bva = ava;
        this.Ava = ava;
        this._wa = true;
        this.notifyObservers();
        this.Zpa = 2;
        this.Yva = false;
        if (this.ova == null || this.ova.equals("")) {
            this.Xva = this.e.a().a("msgEnterSymbol");
            this.dwa = false;
            return;
        }
        boolean n = false;
        final String e = this.e(this.ova);
        this.lva._(0L);
        if (e != null) {
            n = this.lva.n(e);
        }
        if (n || this.iqa) {
            final boolean b = !this.gwa.a(this.f(this.ova), this.lva, this.a(), this.l, this.Ypa);
            this.Zpa = this.gwa.a(this.f(this.ova), this.a());
            this.lva.clear();
            final double[][] _ = this.gwa._(this.f(this.ova), this.a());
            this.qva = _[0];
            this.rva = _[1];
            this.sva = _[2];
            this.tva = _[3];
            this.uva = _[4];
            this.vva = _[5];
            this.wva = _[6];
            this.xva = _[7];
            this.Ua();
            if (!b) {
                this.Yva = false;
                this.Xva = this.ova + ": " + this.e.a().a("msgDataLoaded");
                this.dwa = true;
            }
            else {
                this.qva = null;
                final double[] rva2 = null;
                this.xva = rva2;
                this.wva = rva2;
                this.vva = rva2;
                this.uva = rva2;
                this.tva = rva2;
                this.sva = rva2;
                this.rva = rva2;
                this.yva = null;
                final double[] kpa2 = null;
                this.info = kpa2;
                this.ppa = kpa2;
                this.opa = kpa2;
                this.npa = kpa2;
                this.mpa = kpa2;
                this.lpa = kpa2;
                this.kpa = kpa2;
                if (n) {
                    this.Xva = this.ova + ": " + this.e.a().a("msgInvalidData");
                }
                else {
                    this.Xva = this.ova + ": " + this.e.a().a("msgNoDataAvailable");
                }
                this.dwa = false;
            }
        }
        else {
            this.qva = null;
            final double[] rva3 = null;
            this.xva = rva3;
            this.wva = rva3;
            this.vva = rva3;
            this.uva = rva3;
            this.tva = rva3;
            this.sva = rva3;
            this.rva = rva3;
            this.yva = null;
            final double[] kpa3 = null;
            this.info = kpa3;
            this.ppa = kpa3;
            this.opa = kpa3;
            this.npa = kpa3;
            this.mpa = kpa3;
            this.lpa = kpa3;
            this.kpa = kpa3;
            this.Xva = this.ova + ": " + this.e.a().a("msgNoDataAvailable");
            this.dwa = false;
        }
    }
    
    private void b(final this this2) {
        this.yva = null;
        final double[] kpa = null;
        this.info = kpa;
        this.ppa = kpa;
        this.opa = kpa;
        this.npa = kpa;
        this.mpa = kpa;
        this.lpa = kpa;
        this.kpa = kpa;
        this.qva = null;
        final double[] rva = null;
        this.xva = rva;
        this.wva = rva;
        this.vva = rva;
        this.uva = rva;
        this.tva = rva;
        this.sva = rva;
        this.rva = rva;
        this.zva = null;
        final double[] ava = null;
        this.Gva = ava;
        this.Fva = ava;
        this.Eva = ava;
        this.Dva = ava;
        this.Cva = ava;
        this.Bva = ava;
        this.Ava = ava;
        this._wa = true;
        this.notifyObservers();
        this.Zpa = 2;
        this.Yva = false;
        if (this.ova == null || this.ova.equals("")) {
            this.Xva = this.e.a().a("msgEnterSymbol");
            this.dwa = false;
            return;
        }
        boolean _ = false;
        this.lva._(0L);
        if (this2 != null) {
            _ = this.lva._(this2);
        }
        if (_ || this.iqa) {
            final boolean b = !this.gwa.a(this.f(this.ova), this.lva, this.a(), this.l, this.Ypa);
            this.Zpa = this.gwa.a(this.f(this.ova), this.a());
            this.lva.clear();
            final double[][] _2 = this.gwa._(this.f(this.ova), this.a());
            this.qva = _2[0];
            this.rva = _2[1];
            this.sva = _2[2];
            this.tva = _2[3];
            this.uva = _2[4];
            this.vva = _2[5];
            this.wva = _2[6];
            this.xva = _2[7];
            this.Ua();
            if (!b) {
                this.Yva = false;
                this.Xva = this.ova + ": " + this.e.a().a("msgDataLoaded");
                this.dwa = true;
            }
            else {
                this.qva = null;
                final double[] rva2 = null;
                this.xva = rva2;
                this.wva = rva2;
                this.vva = rva2;
                this.uva = rva2;
                this.tva = rva2;
                this.sva = rva2;
                this.rva = rva2;
                this.yva = null;
                final double[] kpa2 = null;
                this.info = kpa2;
                this.ppa = kpa2;
                this.opa = kpa2;
                this.npa = kpa2;
                this.mpa = kpa2;
                this.lpa = kpa2;
                this.kpa = kpa2;
                if (_) {
                    this.Xva = this.ova + ": " + this.e.a().a("msgInvalidData");
                }
                else {
                    this.Xva = this.ova + ": " + this.e.a().a("msgNoDataAvailable");
                }
                this.dwa = false;
            }
        }
        else {
            this.qva = null;
            final double[] rva3 = null;
            this.xva = rva3;
            this.wva = rva3;
            this.vva = rva3;
            this.uva = rva3;
            this.tva = rva3;
            this.sva = rva3;
            this.rva = rva3;
            this.yva = null;
            final double[] kpa3 = null;
            this.info = kpa3;
            this.ppa = kpa3;
            this.opa = kpa3;
            this.npa = kpa3;
            this.mpa = kpa3;
            this.lpa = kpa3;
            this.kpa = kpa3;
            this.Xva = this.ova + ": " + this.e.a().a("msgNoDataAvailable");
            this.dwa = false;
        }
    }
    
    private String e(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String s2;
        if (this.a()) {
            s2 = this.kva;
        }
        else {
            s2 = this.source;
        }
        final String b = a.b(a.b(a.b(a.b(a.b(this._(s2, this.b(s)), "{UserID}", this.hwa), "{DataType}", this.a() ? "INTRA" : "EOD"), "{Periodicity}", c.j(this.bwa)), "{FirstDataTimestamp}", this.gwa._(this.f(s), this.a())), "{LastDataTimestamp}", this.gwa.b(this.f(s), this.a()));
        String s3;
        if (this.a()) {
            s3 = Integer.toString(this.e());
        }
        else {
            s3 = Integer.toString(this.h());
        }
        return a.b(b, "{Timeframe}", s3);
    }
    
    private void q() {
        this.qva = null;
        final double[] rva = null;
        this.xva = rva;
        this.wva = rva;
        this.vva = rva;
        this.uva = rva;
        this.tva = rva;
        this.sva = rva;
        this.rva = rva;
        this.Hva = null;
        final double[] iva = null;
        this.Ova = iva;
        this.Nva = iva;
        this.Mva = iva;
        this.Lva = iva;
        this.Kva = iva;
        this.Jva = iva;
        this.Iva = iva;
        this.Pva = null;
        final double[] qva = null;
        this.Wva = qva;
        this.Vva = qva;
        this.Uva = qva;
        this.Tva = qva;
        this.Sva = qva;
        this.Rva = qva;
        this.Qva = qva;
        this.awa = true;
        this.notifyObservers();
        this.Zva = false;
        if (this.pva == null || this.pva.equals("")) {
            this.Xva = "";
            this.ewa = false;
            return;
        }
        boolean n = false;
        final String e = this.e(this.pva);
        this.lva._(0L);
        if (e != null) {
            n = this.lva.n(e);
        }
        if (n || this.iqa) {
            final boolean b = !this.gwa.a(this.f(this.pva), this.lva, this.a(), this.l, this.Ypa);
            this.lva.clear();
            final double[][] _ = this.gwa._(this.f(this.pva), this.a());
            this.qva = _[0];
            this.rva = _[1];
            this.sva = _[2];
            this.tva = _[3];
            this.uva = _[4];
            this.vva = _[5];
            this.wva = _[6];
            this.xva = _[7];
            this.Va();
            if (!b) {
                this.Zva = false;
                this.Xva = "";
                this.ewa = true;
            }
            else {
                this.qva = null;
                final double[] rva2 = null;
                this.xva = rva2;
                this.wva = rva2;
                this.vva = rva2;
                this.uva = rva2;
                this.tva = rva2;
                this.sva = rva2;
                this.rva = rva2;
                this.Hva = null;
                final double[] iva2 = null;
                this.Ova = iva2;
                this.Nva = iva2;
                this.Mva = iva2;
                this.Lva = iva2;
                this.Kva = iva2;
                this.Jva = iva2;
                this.Iva = iva2;
                this.Xva = "";
                this.ewa = false;
            }
        }
        else {
            this.qva = null;
            final double[] rva3 = null;
            this.xva = rva3;
            this.wva = rva3;
            this.vva = rva3;
            this.uva = rva3;
            this.tva = rva3;
            this.sva = rva3;
            this.rva = rva3;
            this.Hva = null;
            final double[] iva3 = null;
            this.Ova = iva3;
            this.Nva = iva3;
            this.Mva = iva3;
            this.Lva = iva3;
            this.Kva = iva3;
            this.Jva = iva3;
            this.Iva = iva3;
            this.Xva = "";
            this.ewa = false;
        }
    }
    
    private void a(final this this2) {
        this.qva = null;
        final double[] rva = null;
        this.xva = rva;
        this.wva = rva;
        this.vva = rva;
        this.uva = rva;
        this.tva = rva;
        this.sva = rva;
        this.rva = rva;
        this.Hva = null;
        final double[] iva = null;
        this.Ova = iva;
        this.Nva = iva;
        this.Mva = iva;
        this.Lva = iva;
        this.Kva = iva;
        this.Jva = iva;
        this.Iva = iva;
        this.Pva = null;
        final double[] qva = null;
        this.Wva = qva;
        this.Vva = qva;
        this.Uva = qva;
        this.Tva = qva;
        this.Sva = qva;
        this.Rva = qva;
        this.Qva = qva;
        this.awa = true;
        this.notifyObservers();
        this.Zva = false;
        if (this.pva == null || this.pva.equals("")) {
            this.Xva = "";
            this.ewa = false;
            return;
        }
        boolean _ = false;
        this.lva._(0L);
        if (this2 != null) {
            _ = this.lva._(this2);
        }
        if (_ || this.iqa) {
            final boolean b = !this.gwa.a(this.f(this.pva), this.lva, this.a(), this.l, this.Ypa);
            this.lva.clear();
            final double[][] _2 = this.gwa._(this.f(this.pva), this.a());
            this.qva = _2[0];
            this.rva = _2[1];
            this.sva = _2[2];
            this.tva = _2[3];
            this.uva = _2[4];
            this.vva = _2[5];
            this.wva = _2[6];
            this.xva = _2[7];
            this.Va();
            if (!b) {
                this.Zva = false;
                this.Xva = "";
                this.ewa = true;
            }
            else {
                this.qva = null;
                final double[] rva2 = null;
                this.xva = rva2;
                this.wva = rva2;
                this.vva = rva2;
                this.uva = rva2;
                this.tva = rva2;
                this.sva = rva2;
                this.rva = rva2;
                this.Hva = null;
                final double[] iva2 = null;
                this.Ova = iva2;
                this.Nva = iva2;
                this.Mva = iva2;
                this.Lva = iva2;
                this.Kva = iva2;
                this.Jva = iva2;
                this.Iva = iva2;
                this.Xva = "";
                this.ewa = false;
            }
        }
        else {
            this.qva = null;
            final double[] rva3 = null;
            this.xva = rva3;
            this.wva = rva3;
            this.vva = rva3;
            this.uva = rva3;
            this.tva = rva3;
            this.sva = rva3;
            this.rva = rva3;
            this.Hva = null;
            final double[] iva3 = null;
            this.Ova = iva3;
            this.Nva = iva3;
            this.Mva = iva3;
            this.Lva = iva3;
            this.Kva = iva3;
            this.Jva = iva3;
            this.Iva = iva3;
            this.Xva = "";
            this.ewa = false;
        }
    }
    
    private double[] a(final double[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final double[] array2 = new double[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    private double[][] b(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        this._qa.a(array);
        final int length = array.length;
        final double[] array3 = new double[length];
        final double[] array4 = new double[length];
        if (this.cwa) {
            System.arraycopy(array, 0, array3, 0, length);
            System.arraycopy(array2, 0, array4, 0, length);
            return new double[][] { array3, array4 };
        }
        long n = -1L;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this._qa._(array[i]);
            long n3;
            if (this.bwa == 0) {
                n3 = i;
            }
            else {
                n3 = this._qa.m() / this.bwa;
                if (n3 > 0L && this._qa.m() % this.bwa == 0L) {
                    --n3;
                }
            }
            if (n3 != n) {
                ++n2;
            }
            array3[n2] = array[i];
            array4[n2] = array2[i];
            n = n3;
        }
        if (this.bwa != 0) {
            for (int j = 0; j < n2; ++j) {
                this._qa._(array3[j]);
                this._qa.G(this.bwa);
                array3[j] = this._qa.l();
            }
        }
        return new double[][] { this.a(array3, n2 + 1), this.a(array4, n2 + 1) };
    }
    
    private double[][] _(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        final int length = array.length;
        final double[] array3 = new double[length];
        final double[] array4 = new double[length];
        if (this.cwa) {
            System.arraycopy(array, 0, array3, 0, length);
            System.arraycopy(array2, 0, array4, 0, length);
            return new double[][] { array3, array4 };
        }
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.aqa._(array[i]);
            int n3;
            if (this.bwa == 100002) {
                n3 = this.aqa.o();
            }
            else if (this.bwa == 100003) {
                n3 = this.aqa.T();
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
        return new double[][] { this.a(array3, n2 + 1), this.a(array4, n2 + 1) };
    }
    
    private void Wa() {
        this._qa.a(this.yva);
        final int length = this.yva.length;
        this.zva = new double[length];
        this.Ava = new double[length];
        this.Bva = new double[length];
        this.Cva = new double[length];
        this.Dva = new double[length];
        this.Eva = new double[length];
        this.Fva = new double[length];
        this.Gva = new double[length];
        if (this.cwa) {
            System.arraycopy(this.yva, 0, this.zva, 0, length);
            System.arraycopy(this.kpa, 0, this.Ava, 0, length);
            System.arraycopy(this.lpa, 0, this.Bva, 0, length);
            System.arraycopy(this.mpa, 0, this.Cva, 0, length);
            System.arraycopy(this.npa, 0, this.Dva, 0, length);
            System.arraycopy(this.opa, 0, this.Eva, 0, length);
            System.arraycopy(this.ppa, 0, this.Fva, 0, length);
            System.arraycopy(this.info, 0, this.Gva, 0, length);
            return;
        }
        long n = -1L;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this._qa._(this.yva[i]);
            long n3;
            if (this.bwa == 0) {
                n3 = i;
            }
            else {
                n3 = this._qa.m() / this.bwa;
                if (n3 > 0L && this._qa.m() % this.bwa == 0L) {
                    --n3;
                }
            }
            if (n3 != n) {
                ++n2;
                this.Ava[n2] = this.kpa[i];
                this.Cva[n2] = Double.POSITIVE_INFINITY;
                this.Bva[n2] = Double.NEGATIVE_INFINITY;
            }
            this.zva[n2] = this.yva[i];
            this.Dva[n2] = this.npa[i];
            this.Bva[n2] = Math.max(this.Bva[n2], this.lpa[i]);
            this.Cva[n2] = Math.min(this.Cva[n2], this.mpa[i]);
            final double[] eva = this.Eva;
            final int n4 = n2;
            eva[n4] += this.opa[i];
            this.Fva[n2] = this.ppa[i];
            this.Gva[n2] = this.info[i];
            n = n3;
        }
        if (this.bwa != 0) {
            for (int j = 0; j < n2; ++j) {
                this._qa._(this.zva[j]);
                this._qa.G(this.bwa);
                this.zva[j] = this._qa.l();
            }
        }
        this.zva = this.a(this.zva, n2 + 1);
        this.Ava = this.a(this.Ava, n2 + 1);
        this.Bva = this.a(this.Bva, n2 + 1);
        this.Cva = this.a(this.Cva, n2 + 1);
        this.Dva = this.a(this.Dva, n2 + 1);
        this.Eva = this.a(this.Eva, n2 + 1);
        this.Fva = this.a(this.Fva, n2 + 1);
        this.Gva = this.a(this.Gva, n2 + 1);
    }
    
    private void Xa() {
        final int length = this.yva.length;
        this.zva = new double[length];
        this.Ava = new double[length];
        this.Bva = new double[length];
        this.Cva = new double[length];
        this.Dva = new double[length];
        this.Eva = new double[length];
        this.Fva = new double[length];
        this.Gva = new double[length];
        if (this.cwa) {
            System.arraycopy(this.yva, 0, this.zva, 0, length);
            System.arraycopy(this.kpa, 0, this.Ava, 0, length);
            System.arraycopy(this.lpa, 0, this.Bva, 0, length);
            System.arraycopy(this.mpa, 0, this.Cva, 0, length);
            System.arraycopy(this.npa, 0, this.Dva, 0, length);
            System.arraycopy(this.opa, 0, this.Eva, 0, length);
            System.arraycopy(this.ppa, 0, this.Fva, 0, length);
            System.arraycopy(this.info, 0, this.Gva, 0, length);
            return;
        }
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.aqa._(this.yva[i]);
            int n3;
            if (this.bwa == 100002) {
                n3 = this.aqa.o();
            }
            else if (this.bwa == 100003) {
                n3 = this.aqa.T();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
                this.Ava[n2] = this.kpa[i];
                this.Cva[n2] = Double.POSITIVE_INFINITY;
                this.Bva[n2] = Double.NEGATIVE_INFINITY;
            }
            this.zva[n2] = this.yva[i];
            this.Dva[n2] = this.npa[i];
            this.Bva[n2] = Math.max(this.Bva[n2], this.lpa[i]);
            this.Cva[n2] = Math.min(this.Cva[n2], this.mpa[i]);
            final double[] eva = this.Eva;
            final int n4 = n2;
            eva[n4] += this.opa[i];
            this.Fva[n2] = this.ppa[i];
            this.Gva[n2] = this.info[i];
            n = n3;
        }
        this.zva = this.a(this.zva, n2 + 1);
        this.Ava = this.a(this.Ava, n2 + 1);
        this.Bva = this.a(this.Bva, n2 + 1);
        this.Cva = this.a(this.Cva, n2 + 1);
        this.Dva = this.a(this.Dva, n2 + 1);
        this.Eva = this.a(this.Eva, n2 + 1);
        this.Fva = this.a(this.Fva, n2 + 1);
        this.Gva = this.a(this.Gva, n2 + 1);
    }
    
    private void Ya() {
        this._qa.a(this.Hva);
        final int length = this.Hva.length;
        this.Pva = new double[length];
        this.Qva = new double[length];
        this.Rva = new double[length];
        this.Sva = new double[length];
        this.Tva = new double[length];
        this.Uva = new double[length];
        this.Vva = new double[length];
        this.Wva = new double[length];
        if (this.cwa) {
            System.arraycopy(this.Hva, 0, this.Pva, 0, length);
            System.arraycopy(this.Iva, 0, this.Qva, 0, length);
            System.arraycopy(this.Jva, 0, this.Rva, 0, length);
            System.arraycopy(this.Kva, 0, this.Sva, 0, length);
            System.arraycopy(this.Lva, 0, this.Tva, 0, length);
            System.arraycopy(this.Mva, 0, this.Uva, 0, length);
            System.arraycopy(this.Nva, 0, this.Vva, 0, length);
            System.arraycopy(this.Ova, 0, this.Wva, 0, length);
            return;
        }
        long n = -1L;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this._qa._(this.Hva[i]);
            long n3;
            if (this.bwa == 0) {
                n3 = i;
            }
            else {
                n3 = this._qa.m() / this.bwa;
                if (n3 > 0L && this._qa.m() % this.bwa == 0L) {
                    --n3;
                }
            }
            if (n3 != n) {
                ++n2;
                this.Qva[n2] = this.Iva[i];
                this.Sva[n2] = Double.POSITIVE_INFINITY;
                this.Rva[n2] = Double.NEGATIVE_INFINITY;
            }
            this.Pva[n2] = this.Hva[i];
            this.Tva[n2] = this.Lva[i];
            this.Rva[n2] = Math.max(this.Rva[n2], this.Jva[i]);
            this.Sva[n2] = Math.min(this.Sva[n2], this.Kva[i]);
            final double[] uva = this.Uva;
            final int n4 = n2;
            uva[n4] += this.Mva[i];
            this.Vva[n2] = this.Nva[i];
            this.Wva[n2] = this.Ova[i];
            n = n3;
        }
        if (this.bwa != 0) {
            for (int j = 0; j < n2; ++j) {
                this._qa._(this.Pva[j]);
                this._qa.G(this.bwa);
                this.Pva[j] = this._qa.l();
            }
        }
        this.Pva = this.a(this.Pva, n2 + 1);
        this.Qva = this.a(this.Qva, n2 + 1);
        this.Rva = this.a(this.Rva, n2 + 1);
        this.Sva = this.a(this.Sva, n2 + 1);
        this.Tva = this.a(this.Tva, n2 + 1);
        this.Uva = this.a(this.Uva, n2 + 1);
        this.Vva = this.a(this.Vva, n2 + 1);
        this.Wva = this.a(this.Wva, n2 + 1);
    }
    
    private void Za() {
        final int length = this.Hva.length;
        this.Pva = new double[length];
        this.Qva = new double[length];
        this.Rva = new double[length];
        this.Sva = new double[length];
        this.Tva = new double[length];
        this.Uva = new double[length];
        this.Vva = new double[length];
        this.Wva = new double[length];
        if (this.cwa) {
            System.arraycopy(this.Hva, 0, this.Pva, 0, length);
            System.arraycopy(this.Iva, 0, this.Qva, 0, length);
            System.arraycopy(this.Jva, 0, this.Rva, 0, length);
            System.arraycopy(this.Kva, 0, this.Sva, 0, length);
            System.arraycopy(this.Lva, 0, this.Tva, 0, length);
            System.arraycopy(this.Mva, 0, this.Uva, 0, length);
            System.arraycopy(this.Nva, 0, this.Vva, 0, length);
            System.arraycopy(this.Ova, 0, this.Wva, 0, length);
            return;
        }
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.aqa._(this.Hva[i]);
            int n3;
            if (this.bwa == 100002) {
                n3 = this.aqa.o();
            }
            else if (this.bwa == 100003) {
                n3 = this.aqa.T();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
                this.Qva[n2] = this.Iva[i];
                this.Sva[n2] = Double.POSITIVE_INFINITY;
                this.Rva[n2] = Double.NEGATIVE_INFINITY;
            }
            this.Pva[n2] = this.Hva[i];
            this.Tva[n2] = this.Lva[i];
            this.Rva[n2] = Math.max(this.Rva[n2], this.Jva[i]);
            this.Sva[n2] = Math.min(this.Sva[n2], this.Kva[i]);
            final double[] uva = this.Uva;
            final int n4 = n2;
            uva[n4] += this.Mva[i];
            this.Vva[n2] = this.Nva[i];
            this.Wva[n2] = this.Ova[i];
            n = n3;
        }
        this.Pva = this.a(this.Pva, n2 + 1);
        this.Qva = this.a(this.Qva, n2 + 1);
        this.Rva = this.a(this.Rva, n2 + 1);
        this.Sva = this.a(this.Sva, n2 + 1);
        this.Tva = this.a(this.Tva, n2 + 1);
        this.Uva = this.a(this.Uva, n2 + 1);
        this.Vva = this.a(this.Vva, n2 + 1);
        this.Wva = this.a(this.Wva, n2 + 1);
    }
    
    private void p() {
        if (this.yva == null) {
            this.zva = null;
            this.Ava = null;
            this.Bva = null;
            this.Cva = null;
            this.Dva = null;
            this.Eva = null;
            this.Fva = null;
            this.Gva = null;
        }
        else if (this._wa) {
            if (this.a()) {
                this.Wa();
            }
            else {
                this.Xa();
            }
            this._wa = false;
        }
    }
    
    private void r() {
        if (this.Hva == null) {
            this.Pva = null;
            this.Qva = null;
            this.Rva = null;
            this.Sva = null;
            this.Tva = null;
            this.Uva = null;
            this.Vva = null;
            this.Wva = null;
        }
        else if (this.awa) {
            if (this.a()) {
                this.Ya();
            }
            else {
                this.Za();
            }
            this.awa = false;
        }
    }
    
    public synchronized String[][] a() {
        final String[] array = new String[this.nva.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.e(this.nva[i]);
        }
        return new String[][] { this.nva, array };
    }
    
    public synchronized String[] m() {
        return this.nva;
    }
    
    public synchronized void a(final String[] nva) {
        this.nva = nva;
        this.s();
    }
    
    private void s() {
        final Vector<String> vector = new Vector<String>();
        if (this.ova != null) {
            vector.addElement(this.f(this.ova));
        }
        if (this.pva != null) {
            vector.addElement(this.f(this.pva));
        }
        for (int i = 0; i < this.nva.length; ++i) {
            vector.addElement(this.f(this.nva[i]));
        }
        this.gwa.b(vector);
    }
    
    public synchronized void D(final String iva) {
        this.iva = iva;
    }
    
    public synchronized void E(final String jva) {
        this.jva = jva;
    }
    
    public synchronized String H() {
        if (this.jva == null || this.jva.length() == 0) {
            return null;
        }
        final boolean d = this.mva.d(this.jva);
        String text = null;
        if (d) {
            text = this.mva.getText();
        }
        this.mva.clear();
        return text;
    }
    
    public synchronized void F(final String text) {
        if (text == null || text.length() == 0) {
            return;
        }
        if (this.iva == null || this.iva.length() == 0) {
            return;
        }
        this.mva.setText(text);
        this.mva._(this.iva, "indicators");
        this.mva.clear();
    }
    
    public String I() {
        return this.hwa;
    }
    
    public synchronized boolean j() {
        return this.cwa;
    }
    
    public synchronized void U(final boolean cwa) {
        this.cwa = cwa;
    }
    
    public synchronized void b(final import import1) {
        if (import1.eqa) {
            this.fwa.put(import1.name, import1.dqa);
        }
    }
    
    public synchronized String b(final String s) {
        if (s == null) {
            return null;
        }
        final String s2 = this.fwa.get(s);
        return (s2 == null) ? s : s2;
    }
}
