import java.util.Vector;
import java.net.URLEncoder;
import java.util.Observer;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Observable
{
    public static final int nJb = 0;
    public static final int oJb = 1;
    public static final int pJb = 2;
    public static final int qJb = 3;
    public static final int rJb = 4;
    public static final int sJb = 5;
    public static final int tJb = 10;
    public static final int uJb = 15;
    public static final int vJb = 20;
    public static final int wJb = 30;
    public static final int xJb = 60;
    public static final int yJb = 120;
    public static final int zJb = 180;
    public static final int AJb = 240;
    public static final int BJb = 300;
    public static final int CJb = 600;
    public static final int DJb = 900;
    public static final int EJb = 1200;
    public static final int FJb = 1800;
    public static final int GJb = 3600;
    public static final int HJb = 100001;
    public static final int IJb = 100002;
    public static final int JJb = 100003;
    private int DHb;
    private int CHb;
    private String KJb;
    private String LJb;
    private String qGb;
    private String source;
    private String MJb;
    private _ NJb;
    private _ OJb;
    private String[] PJb;
    private String QJb;
    private String RJb;
    private double[] SJb;
    private double[] TJb;
    private double[] UJb;
    private double[] VJb;
    private double[] WJb;
    private double[] XJb;
    private double[] YJb;
    private double[] ZJb;
    private double[] bHb;
    private double[] cHb;
    private double[] dHb;
    private double[] eHb;
    private double[] fHb;
    private double[] gHb;
    private double[] _Kb;
    private double[] aKb;
    private double[] bKb;
    private double[] cKb;
    private double[] dKb;
    private double[] eKb;
    private double[] fKb;
    private double[] gKb;
    private double[] hKb;
    private double[] iKb;
    private double[] jKb;
    private double[] kKb;
    private double[] lKb;
    private double[] mKb;
    private double[] nKb;
    private double[] oKb;
    private double[] pKb;
    private double[] qKb;
    private double[] rKb;
    private double[] sKb;
    private double[] tKb;
    private String uKb;
    private boolean vKb;
    private boolean wKb;
    private boolean xKb;
    private boolean yKb;
    private int zKb;
    private boolean AKb;
    private boolean BKb;
    private Wo CKb;
    private g iGb;
    private long DKb;
    private long EKb;
    private boolean LHb;
    Xo FHb;
    Yo EHb;
    private Observer FKb;
    
    synchronized void L(final int n) {
        this.CKb.L(n);
    }
    
    synchronized void K(final int n) {
        this.CKb.K(n);
    }
    
    synchronized int G() {
        return this.CKb.G();
    }
    
    synchronized int E() {
        return this.CKb.E();
    }
    
    public a(final String source, final String mJb, final g iGb, final int n) {
        this.DHb = 2;
        this.CHb = -1;
        this.KJb = "";
        this.LJb = "";
        this.qGb = "YY-MM-DD";
        this.DKb = 0L;
        this.EKb = 0L;
        this.LHb = false;
        this.FHb = new Xo();
        this.EHb = new Yo();
        this.FKb = null;
        this.iGb = iGb;
        this.source = source;
        this.MJb = mJb;
        this.QJb = null;
        this.PJb = new String[0];
        (this.NJb = new _()).g(this.LHb);
        (this.OJb = new _()).g(false);
        this.CKb = new Wo(n);
        this.vKb = true;
        this.xKb = true;
        this.wKb = true;
        this.yKb = true;
        this.zKb = 100001;
        this.AKb = false;
        this.BKb = false;
        this.c(this.qGb);
    }
    
    public synchronized void g(final boolean lHb) {
        this.LHb = lHb;
        this.NJb.g(lHb);
    }
    
    synchronized void U() {
        this.vKb = true;
        this.wKb = true;
        this.xKb = true;
        this.yKb = true;
    }
    
    public synchronized boolean n() {
        return this.zKb >= 0 && this.zKb <= 3600;
    }
    
    public synchronized int I() {
        return this.DHb;
    }
    
    public synchronized void ea(final int cHb) {
        this.CHb = cHb;
    }
    
    public synchronized void c(final String s) {
        this.qGb = s.toUpperCase();
    }
    
    public synchronized void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }
    
    public synchronized void I() {
        this.NJb.deleteObservers();
        this.OJb.deleteObservers();
        this.FKb = null;
    }
    
    public synchronized void b(final Observer fKb) {
        this.NJb.addObserver(fKb);
        this.OJb.addObserver(fKb);
        this.FKb = fKb;
    }
    
    public synchronized boolean h() {
        return this.AKb;
    }
    
    public synchronized void M(final int zKb) {
        if (this.zKb != zKb) {
            final boolean n = this.n();
            this.zKb = zKb;
            if (n != this.n()) {
                this.vKb = true;
                this.wKb = true;
            }
            this.xKb = true;
            this.yKb = true;
        }
        this.notifyObservers();
    }
    
    public synchronized int T() {
        return this.zKb;
    }
    
    public synchronized void J() {
        this.T();
    }
    
    public synchronized void T() {
        final Zo zo = new Zo();
        zo.g(this.LHb);
        if (this.FKb != null) {
            zo.addObserver(this.FKb);
        }
        final String[][] _ = this.iGb._()._();
        final String[] array = _[0];
        final String[] array2 = _[1];
        zo.b(this.QJb, this.c());
        zo.b(this.RJb, this.d());
        for (int i = 0; i < array.length; ++i) {
            zo.b(array[i], array2[i]);
        }
        zo.x();
        final _[] array3 = new _[array.length];
        for (int j = 0; j < array.length; ++j) {
            array3[j] = zo.a(array[j]);
        }
        this.b(array, array2, array3);
        if (this.RJb != null && this.RJb.length() > 0) {
            _ a = zo.a(this.RJb);
            if (a == null) {
                a = new _();
            }
            this.a(a);
        }
        if (this.QJb != null && this.QJb.length() > 0) {
            _ a2 = zo.a(this.QJb);
            if (a2 == null) {
                a2 = new _();
            }
            this.b(a2);
        }
        zo.reset();
        zo.deleteObservers();
    }
    
    public synchronized void _(final String s, final _ _, final String s2, final _ _2) {
        if (this.RJb != null && this.RJb.length() > 0 && s2 != null && s2.length() > 0 && s2.equals(this.g(this.RJb)) && _2 != null) {
            this.a(_2);
        }
        if (this.QJb != null && this.QJb.length() > 0 && s != null && s.length() > 0 && s.equals(this.g(this.QJb)) && _ != null) {
            this.b(_);
        }
    }
    
    public synchronized void b(final String[] array, final String[] array2, final _[] array3) {
        for (int i = 0; i < array.length; ++i) {
            if (array3[i] != null && array2[i].equals(this.g(array[i]))) {
                this.CKb.a(this.h(array[i]), array3[i], this.n(), this.qGb, this.CHb);
            }
        }
    }
    
    public synchronized String c() {
        if (this.QJb == null || this.QJb.length() == 0) {
            return null;
        }
        return this.g(this.QJb);
    }
    
    public synchronized String d() {
        if (this.RJb == null || this.RJb.length() == 0) {
            return null;
        }
        return this.g(this.RJb);
    }
    
    public synchronized double[] h() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this._Kb;
    }
    
    public synchronized double[] c() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.nKb;
    }
    
    public synchronized double[] i() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this.aKb;
    }
    
    public synchronized double[] ha() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.oKb;
    }
    
    public synchronized double[] j() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this.bKb;
    }
    
    public synchronized double[] ia() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.pKb;
    }
    
    public synchronized double[] k() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this.cKb;
    }
    
    public synchronized double[] o() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.qKb;
    }
    
    public synchronized double[] l() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this.dKb;
    }
    
    public synchronized double[] p() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.rKb;
    }
    
    public synchronized double[] m() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this.eKb;
    }
    
    public synchronized double[] C() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.sKb;
    }
    
    public synchronized double[] n() {
        if (this.vKb) {
            this.K();
        }
        this.L();
        return this.fKb;
    }
    
    public synchronized double[] D() {
        if (this.wKb) {
            this.M();
        }
        this.N();
        return this.tKb;
    }
    
    public synchronized void a(final String qJb) {
        this.vKb = true;
        this.QJb = qJb;
        this.mc();
    }
    
    public synchronized void _(final String rJb) {
        this.wKb = true;
        this.RJb = rJb;
        this.mc();
    }
    
    public synchronized String i() {
        return this.QJb;
    }
    
    private String b(final String s, String encode) {
        final String s2 = "{SYMBOL}";
        final String s3 = "{symbol}";
        final String s4 = "{Symbol}";
        if (encode == null) {
            encode = "";
        }
        encode = URLEncoder.encode(encode);
        return i.b(i.b(i.b(s, s2, encode.toUpperCase()), s3, encode.toLowerCase()), s4, encode.toUpperCase());
    }
    
    private String h(final String s) {
        return s;
    }
    
    public synchronized String k() {
        return this.RJb;
    }
    
    public synchronized String getMessage() {
        return this.uKb;
    }
    
    private void nc() {
        if (this.SJb == null || this.WJb == null) {
            final double[] zJb = null;
            this.gHb = zJb;
            this.fHb = zJb;
            this.eHb = zJb;
            this.dHb = zJb;
            this.cHb = zJb;
            this.bHb = zJb;
            this.ZJb = zJb;
            final double[] sJb = null;
            this.YJb = sJb;
            this.XJb = sJb;
            this.WJb = sJb;
            this.VJb = sJb;
            this.UJb = sJb;
            this.TJb = sJb;
            this.SJb = sJb;
            return;
        }
        final int length = this.SJb.length;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (this.SJb[i] <= 0.0 || this.WJb[i] <= 0.0) {
                b = false;
                break;
            }
        }
        if (b) {
            this.ZJb = this.SJb;
            this.bHb = this.TJb;
            this.cHb = this.UJb;
            this.dHb = this.VJb;
            this.eHb = this.WJb;
            this.fHb = this.XJb;
            this.gHb = this.YJb;
            final double[] sJb2 = null;
            this.YJb = sJb2;
            this.XJb = sJb2;
            this.WJb = sJb2;
            this.VJb = sJb2;
            this.UJb = sJb2;
            this.TJb = sJb2;
            this.SJb = sJb2;
            return;
        }
        int n = 0;
        for (int j = 0; j < length; ++j) {
            if (this.SJb[j] > 0.0 && this.WJb[j] > 0.0) {
                ++n;
            }
        }
        if (length > 0 && n > 0) {
            this.ZJb = new double[n];
            this.bHb = new double[n];
            this.cHb = new double[n];
            this.dHb = new double[n];
            this.eHb = new double[n];
            this.fHb = new double[n];
            this.gHb = new double[n];
            int n2 = 0;
            for (int k = 0; k < length; ++k) {
                if (this.SJb[k] > 0.0 && this.WJb[k] > 0.0) {
                    this.ZJb[n2] = this.SJb[k];
                    this.bHb[n2] = this.TJb[k];
                    this.cHb[n2] = this.UJb[k];
                    this.dHb[n2] = this.VJb[k];
                    this.eHb[n2] = this.WJb[k];
                    this.fHb[n2] = this.XJb[k];
                    this.gHb[n2] = this.YJb[k];
                    ++n2;
                }
            }
        }
        else {
            final double[] zJb2 = null;
            this.gHb = zJb2;
            this.fHb = zJb2;
            this.eHb = zJb2;
            this.dHb = zJb2;
            this.cHb = zJb2;
            this.bHb = zJb2;
            this.ZJb = zJb2;
        }
        final double[] sJb3 = null;
        this.YJb = sJb3;
        this.XJb = sJb3;
        this.WJb = sJb3;
        this.VJb = sJb3;
        this.UJb = sJb3;
        this.TJb = sJb3;
        this.SJb = sJb3;
    }
    
    private void oc() {
        if (this.SJb == null || this.WJb == null) {
            final double[] gKb = null;
            this.mKb = gKb;
            this.lKb = gKb;
            this.kKb = gKb;
            this.jKb = gKb;
            this.iKb = gKb;
            this.hKb = gKb;
            this.gKb = gKb;
            final double[] sJb = null;
            this.YJb = sJb;
            this.XJb = sJb;
            this.WJb = sJb;
            this.VJb = sJb;
            this.UJb = sJb;
            this.TJb = sJb;
            this.SJb = sJb;
            return;
        }
        final int length = this.SJb.length;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (this.SJb[i] <= 0.0 || this.WJb[i] <= 0.0) {
                b = false;
                break;
            }
        }
        if (b) {
            this.gKb = this.SJb;
            this.hKb = this.TJb;
            this.iKb = this.UJb;
            this.jKb = this.VJb;
            this.kKb = this.WJb;
            this.lKb = this.XJb;
            this.mKb = this.YJb;
            final double[] sJb2 = null;
            this.YJb = sJb2;
            this.XJb = sJb2;
            this.WJb = sJb2;
            this.VJb = sJb2;
            this.UJb = sJb2;
            this.TJb = sJb2;
            this.SJb = sJb2;
            return;
        }
        int n = 0;
        for (int j = 0; j < length; ++j) {
            if (this.SJb[j] > 0.0 && this.WJb[j] > 0.0) {
                ++n;
            }
        }
        if (length > 0 && n > 0) {
            this.gKb = new double[n];
            this.hKb = new double[n];
            this.iKb = new double[n];
            this.jKb = new double[n];
            this.kKb = new double[n];
            this.lKb = new double[n];
            this.mKb = new double[n];
            int n2 = 0;
            for (int k = 0; k < length; ++k) {
                if (this.SJb[k] > 0.0 && this.WJb[k] > 0.0) {
                    this.gKb[n2] = this.SJb[k];
                    this.hKb[n2] = this.TJb[k];
                    this.iKb[n2] = this.UJb[k];
                    this.jKb[n2] = this.VJb[k];
                    this.kKb[n2] = this.WJb[k];
                    this.lKb[n2] = this.XJb[k];
                    this.mKb[n2] = this.YJb[k];
                    ++n2;
                }
            }
        }
        else {
            final double[] gKb2 = null;
            this.mKb = gKb2;
            this.lKb = gKb2;
            this.kKb = gKb2;
            this.jKb = gKb2;
            this.iKb = gKb2;
            this.hKb = gKb2;
            this.gKb = gKb2;
        }
        final double[] sJb3 = null;
        this.YJb = sJb3;
        this.XJb = sJb3;
        this.WJb = sJb3;
        this.VJb = sJb3;
        this.UJb = sJb3;
        this.TJb = sJb3;
        this.SJb = sJb3;
    }
    
    public synchronized double[][] b(final String s) {
        final double[][] b = this.CKb.b(this.h(s), this.n());
        double[] array = b[0];
        double[] array2 = b[4];
        if (array == null || array2 == null) {
            return null;
        }
        final int length = array.length;
        int n = 0;
        boolean b2 = true;
        for (int i = 0; i < length; ++i) {
            if (array[i] > 0.0 && array2[i] > 0.0) {
                ++n;
            }
            else {
                b2 = false;
            }
        }
        if (!b2) {
            if (length == 0 || n == 0) {
                return null;
            }
            array = new double[n];
            array2 = new double[n];
            int n2 = 0;
            for (int j = 0; j < length; ++j) {
                if (b[0][j] > 0.0 && b[0][j] > 0.0) {
                    array[n2] = b[0][j];
                    array2[n2] = b[4][j];
                    ++n2;
                }
            }
        }
        if (this.n()) {
            return this.a(array, array2);
        }
        return this.b(array, array2);
    }
    
    private void K() {
        this.ZJb = null;
        final double[] array = null;
        this.gHb = array;
        this.fHb = array;
        this.eHb = array;
        this.dHb = array;
        this.cHb = array;
        this.bHb = array;
        this.SJb = null;
        final double[] array2 = null;
        this.YJb = array2;
        this.XJb = array2;
        this.WJb = array2;
        this.VJb = array2;
        this.UJb = array2;
        this.TJb = array2;
        this._Kb = null;
        final double[] array3 = null;
        this.fKb = array3;
        this.eKb = array3;
        this.dKb = array3;
        this.cKb = array3;
        this.bKb = array3;
        this.aKb = array3;
        this.xKb = true;
        this.notifyObservers();
        this.DHb = 2;
        this.vKb = false;
        if (this.QJb == null || this.QJb.equals("")) {
            this.uKb = this.iGb.b()._("msgEnterSymbol");
            this.AKb = false;
            return;
        }
        boolean d = false;
        final String g = this.g(this.QJb);
        long dKb;
        if (this.n()) {
            dKb = 0L;
        }
        else {
            dKb = this.DKb;
        }
        this.NJb.a(dKb);
        if (g != null) {
            d = this.NJb.d(g);
        }
        if (d || this.LHb) {
            final boolean b = !this.CKb.a(this.h(this.QJb), this.NJb, this.n(), this.qGb, this.CHb);
            this.DHb = this.CKb.a(this.h(this.QJb), this.n());
            this.NJb.clear();
            final double[][] b2 = this.CKb.b(this.h(this.QJb), this.n());
            this.SJb = b2[0];
            this.TJb = b2[1];
            this.UJb = b2[2];
            this.VJb = b2[3];
            this.WJb = b2[4];
            this.XJb = b2[5];
            this.YJb = b2[6];
            this.nc();
            if (!b) {
                this.vKb = false;
                this.uKb = this.QJb + ": " + this.iGb.b()._("msgDataLoaded");
                this.AKb = true;
            }
            else {
                this.SJb = null;
                final double[] array4 = null;
                this.YJb = array4;
                this.XJb = array4;
                this.WJb = array4;
                this.VJb = array4;
                this.UJb = array4;
                this.TJb = array4;
                this.ZJb = null;
                final double[] array5 = null;
                this.gHb = array5;
                this.fHb = array5;
                this.eHb = array5;
                this.dHb = array5;
                this.cHb = array5;
                this.bHb = array5;
                if (d) {
                    this.uKb = this.QJb + ": " + this.iGb.b()._("msgInvalidData");
                }
                else {
                    this.uKb = this.QJb + ": " + this.iGb.b()._("msgNoDataAvailable");
                }
                this.AKb = false;
            }
        }
        else {
            this.SJb = null;
            final double[] array6 = null;
            this.YJb = array6;
            this.XJb = array6;
            this.WJb = array6;
            this.VJb = array6;
            this.UJb = array6;
            this.TJb = array6;
            this.ZJb = null;
            final double[] array7 = null;
            this.gHb = array7;
            this.fHb = array7;
            this.eHb = array7;
            this.dHb = array7;
            this.cHb = array7;
            this.bHb = array7;
            this.uKb = this.QJb + ": " + this.iGb.b()._("msgNoDataAvailable");
            this.AKb = false;
        }
    }
    
    private void b(final _ _) {
        this.ZJb = null;
        final double[] array = null;
        this.gHb = array;
        this.fHb = array;
        this.eHb = array;
        this.dHb = array;
        this.cHb = array;
        this.bHb = array;
        this.SJb = null;
        final double[] array2 = null;
        this.YJb = array2;
        this.XJb = array2;
        this.WJb = array2;
        this.VJb = array2;
        this.UJb = array2;
        this.TJb = array2;
        this._Kb = null;
        final double[] array3 = null;
        this.fKb = array3;
        this.eKb = array3;
        this.dKb = array3;
        this.cKb = array3;
        this.bKb = array3;
        this.aKb = array3;
        this.xKb = true;
        this.notifyObservers();
        this.DHb = 2;
        this.vKb = false;
        if (this.QJb == null || this.QJb.equals("")) {
            this.uKb = this.iGb.b()._("msgEnterSymbol");
            this.AKb = false;
            return;
        }
        boolean b = false;
        long dKb;
        if (this.n()) {
            dKb = 0L;
        }
        else {
            dKb = this.DKb;
        }
        this.NJb.a(dKb);
        if (_ != null) {
            b = this.NJb.b(_);
        }
        if (b || this.LHb) {
            final boolean b2 = !this.CKb.a(this.h(this.QJb), this.NJb, this.n(), this.qGb, this.CHb);
            this.DHb = this.CKb.a(this.h(this.QJb), this.n());
            this.NJb.clear();
            final double[][] b3 = this.CKb.b(this.h(this.QJb), this.n());
            this.SJb = b3[0];
            this.TJb = b3[1];
            this.UJb = b3[2];
            this.VJb = b3[3];
            this.WJb = b3[4];
            this.XJb = b3[5];
            this.YJb = b3[6];
            this.nc();
            if (!b2) {
                this.vKb = false;
                this.uKb = this.QJb + ": " + this.iGb.b()._("msgDataLoaded");
                this.AKb = true;
            }
            else {
                this.SJb = null;
                final double[] array4 = null;
                this.YJb = array4;
                this.XJb = array4;
                this.WJb = array4;
                this.VJb = array4;
                this.UJb = array4;
                this.TJb = array4;
                this.ZJb = null;
                final double[] array5 = null;
                this.gHb = array5;
                this.fHb = array5;
                this.eHb = array5;
                this.dHb = array5;
                this.cHb = array5;
                this.bHb = array5;
                if (b) {
                    this.uKb = this.QJb + ": " + this.iGb.b()._("msgInvalidData");
                }
                else {
                    this.uKb = this.QJb + ": " + this.iGb.b()._("msgNoDataAvailable");
                }
                this.AKb = false;
            }
        }
        else {
            this.SJb = null;
            final double[] array6 = null;
            this.YJb = array6;
            this.XJb = array6;
            this.WJb = array6;
            this.VJb = array6;
            this.UJb = array6;
            this.TJb = array6;
            this.ZJb = null;
            final double[] array7 = null;
            this.gHb = array7;
            this.fHb = array7;
            this.eHb = array7;
            this.dHb = array7;
            this.cHb = array7;
            this.bHb = array7;
            this.uKb = this.QJb + ": " + this.iGb.b()._("msgNoDataAvailable");
            this.AKb = false;
        }
    }
    
    private String g(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String s2;
        if (this.n()) {
            s2 = this.MJb;
        }
        else {
            s2 = this.source;
        }
        final String b = i.b(i.b(this.b(s2, s), "{FirstDataTimestamp}", this.CKb.b(this.h(s), this.n())), "{LastDataTimestamp}", this.CKb.a(this.h(s), this.n()));
        String s3;
        if (this.n()) {
            s3 = Integer.toString(this.E());
        }
        else {
            s3 = Integer.toString(this.G());
        }
        return i.b(b, "{Timeframe}", s3);
    }
    
    private void M() {
        this.SJb = null;
        final double[] array = null;
        this.YJb = array;
        this.XJb = array;
        this.WJb = array;
        this.VJb = array;
        this.UJb = array;
        this.TJb = array;
        this.gKb = null;
        final double[] array2 = null;
        this.mKb = array2;
        this.lKb = array2;
        this.kKb = array2;
        this.jKb = array2;
        this.iKb = array2;
        this.hKb = array2;
        this.nKb = null;
        final double[] array3 = null;
        this.tKb = array3;
        this.sKb = array3;
        this.rKb = array3;
        this.qKb = array3;
        this.pKb = array3;
        this.oKb = array3;
        this.yKb = true;
        this.notifyObservers();
        this.wKb = false;
        if (this.RJb == null || this.RJb.equals("")) {
            this.uKb = "";
            this.BKb = false;
            return;
        }
        boolean d = false;
        final String g = this.g(this.RJb);
        long dKb;
        if (this.n()) {
            dKb = 0L;
        }
        else {
            dKb = this.DKb;
        }
        this.NJb.a(dKb);
        if (g != null) {
            d = this.NJb.d(g);
        }
        if (d || this.LHb) {
            final boolean b = !this.CKb.a(this.h(this.RJb), this.NJb, this.n(), this.qGb, this.CHb);
            this.NJb.clear();
            final double[][] b2 = this.CKb.b(this.h(this.RJb), this.n());
            this.SJb = b2[0];
            this.TJb = b2[1];
            this.UJb = b2[2];
            this.VJb = b2[3];
            this.WJb = b2[4];
            this.XJb = b2[5];
            this.YJb = b2[6];
            this.oc();
            if (!b) {
                this.wKb = false;
                this.uKb = "";
                this.BKb = true;
            }
            else {
                this.SJb = null;
                final double[] array4 = null;
                this.YJb = array4;
                this.XJb = array4;
                this.WJb = array4;
                this.VJb = array4;
                this.UJb = array4;
                this.TJb = array4;
                this.gKb = null;
                final double[] array5 = null;
                this.mKb = array5;
                this.lKb = array5;
                this.kKb = array5;
                this.jKb = array5;
                this.iKb = array5;
                this.hKb = array5;
                this.uKb = "";
                this.BKb = false;
            }
        }
        else {
            this.SJb = null;
            final double[] array6 = null;
            this.YJb = array6;
            this.XJb = array6;
            this.WJb = array6;
            this.VJb = array6;
            this.UJb = array6;
            this.TJb = array6;
            this.gKb = null;
            final double[] array7 = null;
            this.mKb = array7;
            this.lKb = array7;
            this.kKb = array7;
            this.jKb = array7;
            this.iKb = array7;
            this.hKb = array7;
            this.uKb = "";
            this.BKb = false;
        }
    }
    
    private void a(final _ _) {
        this.SJb = null;
        final double[] array = null;
        this.YJb = array;
        this.XJb = array;
        this.WJb = array;
        this.VJb = array;
        this.UJb = array;
        this.TJb = array;
        this.gKb = null;
        final double[] array2 = null;
        this.mKb = array2;
        this.lKb = array2;
        this.kKb = array2;
        this.jKb = array2;
        this.iKb = array2;
        this.hKb = array2;
        this.nKb = null;
        final double[] array3 = null;
        this.tKb = array3;
        this.sKb = array3;
        this.rKb = array3;
        this.qKb = array3;
        this.pKb = array3;
        this.oKb = array3;
        this.yKb = true;
        this.notifyObservers();
        this.wKb = false;
        if (this.RJb == null || this.RJb.equals("")) {
            this.uKb = "";
            this.BKb = false;
            return;
        }
        boolean b = false;
        long dKb;
        if (this.n()) {
            dKb = 0L;
        }
        else {
            dKb = this.DKb;
        }
        this.NJb.a(dKb);
        if (_ != null) {
            b = this.NJb.b(_);
        }
        if (b || this.LHb) {
            final boolean b2 = !this.CKb.a(this.h(this.RJb), this.NJb, this.n(), this.qGb, this.CHb);
            this.NJb.clear();
            final double[][] b3 = this.CKb.b(this.h(this.RJb), this.n());
            this.SJb = b3[0];
            this.TJb = b3[1];
            this.UJb = b3[2];
            this.VJb = b3[3];
            this.WJb = b3[4];
            this.XJb = b3[5];
            this.YJb = b3[6];
            this.oc();
            if (!b2) {
                this.wKb = false;
                this.uKb = "";
                this.BKb = true;
            }
            else {
                this.SJb = null;
                final double[] array4 = null;
                this.YJb = array4;
                this.XJb = array4;
                this.WJb = array4;
                this.VJb = array4;
                this.UJb = array4;
                this.TJb = array4;
                this.gKb = null;
                final double[] array5 = null;
                this.mKb = array5;
                this.lKb = array5;
                this.kKb = array5;
                this.jKb = array5;
                this.iKb = array5;
                this.hKb = array5;
                this.uKb = "";
                this.BKb = false;
            }
        }
        else {
            this.SJb = null;
            final double[] array6 = null;
            this.YJb = array6;
            this.XJb = array6;
            this.WJb = array6;
            this.VJb = array6;
            this.UJb = array6;
            this.TJb = array6;
            this.gKb = null;
            final double[] array7 = null;
            this.mKb = array7;
            this.lKb = array7;
            this.kKb = array7;
            this.jKb = array7;
            this.iKb = array7;
            this.hKb = array7;
            this.uKb = "";
            this.BKb = false;
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
    
    private double[][] a(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        this.EHb.a(array);
        final int length = array.length;
        final double[] array3 = new double[length];
        final double[] array4 = new double[length];
        long n = -1L;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.EHb._(array[i]);
            long n3;
            if (this.zKb == 0) {
                n3 = i;
            }
            else {
                n3 = this.EHb.j() / this.zKb;
                if (n3 > 0L && this.EHb.j() % this.zKb == 0L) {
                    --n3;
                }
            }
            if (n3 != n) {
                ++n2;
            }
            array3[n2] = array[i];
            if (this.zKb != 0 && i != length - 1) {
                this.EHb.aa(this.zKb);
                array3[n2] = this.EHb.m();
            }
            array4[n2] = array2[i];
            n = n3;
        }
        return new double[][] { this.a(array3, n2 + 1), this.a(array4, n2 + 1) };
    }
    
    private double[][] b(final double[] array, final double[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        final int length = array.length;
        final double[] array3 = new double[length];
        final double[] array4 = new double[length];
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.FHb._(array[i]);
            int n3;
            if (this.zKb == 100002) {
                n3 = this.FHb.R();
            }
            else if (this.zKb == 100003) {
                n3 = this.FHb.m();
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
    
    private void pc() {
        this.EHb.a(this.ZJb);
        final int length = this.ZJb.length;
        this._Kb = new double[length];
        this.aKb = new double[length];
        this.bKb = new double[length];
        this.cKb = new double[length];
        this.dKb = new double[length];
        this.eKb = new double[length];
        this.fKb = new double[length];
        long n = -1L;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.EHb._(this.ZJb[i]);
            long n3;
            if (this.zKb == 0) {
                n3 = i;
            }
            else {
                n3 = this.EHb.j() / this.zKb;
                if (n3 > 0L && this.EHb.j() % this.zKb == 0L) {
                    --n3;
                }
            }
            if (n3 != n) {
                ++n2;
                this.aKb[n2] = this.bHb[i];
                this.cKb[n2] = Double.POSITIVE_INFINITY;
                this.bKb[n2] = Double.NEGATIVE_INFINITY;
            }
            this._Kb[n2] = this.ZJb[i];
            if (this.zKb != 0 && i != length - 1) {
                this.EHb.aa(this.zKb);
                this._Kb[n2] = this.EHb.m();
            }
            this.dKb[n2] = this.eHb[i];
            this.bKb[n2] = Math.max(this.bKb[n2], this.cHb[i]);
            this.cKb[n2] = Math.min(this.cKb[n2], this.dHb[i]);
            final double[] eKb = this.eKb;
            final int n4 = n2;
            eKb[n4] += this.fHb[i];
            this.fKb[n2] = this.gHb[i];
            n = n3;
        }
        this._Kb = this.a(this._Kb, n2 + 1);
        this.aKb = this.a(this.aKb, n2 + 1);
        this.bKb = this.a(this.bKb, n2 + 1);
        this.cKb = this.a(this.cKb, n2 + 1);
        this.dKb = this.a(this.dKb, n2 + 1);
        this.eKb = this.a(this.eKb, n2 + 1);
        this.fKb = this.a(this.fKb, n2 + 1);
    }
    
    private void qc() {
        final int length = this.ZJb.length;
        this._Kb = new double[length];
        this.aKb = new double[length];
        this.bKb = new double[length];
        this.cKb = new double[length];
        this.dKb = new double[length];
        this.eKb = new double[length];
        this.fKb = new double[length];
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.FHb._(this.ZJb[i]);
            int n3;
            if (this.zKb == 100002) {
                n3 = this.FHb.R();
            }
            else if (this.zKb == 100003) {
                n3 = this.FHb.m();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
                this.aKb[n2] = this.bHb[i];
                this.cKb[n2] = Double.POSITIVE_INFINITY;
                this.bKb[n2] = Double.NEGATIVE_INFINITY;
            }
            this._Kb[n2] = this.ZJb[i];
            this.dKb[n2] = this.eHb[i];
            this.bKb[n2] = Math.max(this.bKb[n2], this.cHb[i]);
            this.cKb[n2] = Math.min(this.cKb[n2], this.dHb[i]);
            final double[] eKb = this.eKb;
            final int n4 = n2;
            eKb[n4] += this.fHb[i];
            this.fKb[n2] = this.gHb[i];
            n = n3;
        }
        this._Kb = this.a(this._Kb, n2 + 1);
        this.aKb = this.a(this.aKb, n2 + 1);
        this.bKb = this.a(this.bKb, n2 + 1);
        this.cKb = this.a(this.cKb, n2 + 1);
        this.dKb = this.a(this.dKb, n2 + 1);
        this.eKb = this.a(this.eKb, n2 + 1);
        this.fKb = this.a(this.fKb, n2 + 1);
    }
    
    private void rc() {
        this.EHb.a(this.gKb);
        final int length = this.gKb.length;
        this.nKb = new double[length];
        this.oKb = new double[length];
        this.pKb = new double[length];
        this.qKb = new double[length];
        this.rKb = new double[length];
        this.sKb = new double[length];
        this.tKb = new double[length];
        long n = -1L;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.EHb._(this.gKb[i]);
            long n3;
            if (this.zKb == 0) {
                n3 = i;
            }
            else {
                n3 = this.EHb.j() / this.zKb;
                if (n3 > 0L && this.EHb.j() % this.zKb == 0L) {
                    --n3;
                }
            }
            if (n3 != n) {
                ++n2;
                this.oKb[n2] = this.hKb[i];
                this.qKb[n2] = Double.POSITIVE_INFINITY;
                this.pKb[n2] = Double.NEGATIVE_INFINITY;
            }
            this.nKb[n2] = this.gKb[i];
            if (this.zKb != 0 && i != length - 1) {
                this.EHb.aa(this.zKb);
                this.nKb[n2] = this.EHb.m();
            }
            this.rKb[n2] = this.kKb[i];
            this.pKb[n2] = Math.max(this.pKb[n2], this.iKb[i]);
            this.qKb[n2] = Math.min(this.qKb[n2], this.jKb[i]);
            final double[] sKb = this.sKb;
            final int n4 = n2;
            sKb[n4] += this.lKb[i];
            this.tKb[n2] = this.mKb[i];
            n = n3;
        }
        this.nKb = this.a(this.nKb, n2 + 1);
        this.oKb = this.a(this.oKb, n2 + 1);
        this.pKb = this.a(this.pKb, n2 + 1);
        this.qKb = this.a(this.qKb, n2 + 1);
        this.rKb = this.a(this.rKb, n2 + 1);
        this.sKb = this.a(this.sKb, n2 + 1);
        this.tKb = this.a(this.tKb, n2 + 1);
    }
    
    private void sc() {
        final int length = this.gKb.length;
        this.nKb = new double[length];
        this.oKb = new double[length];
        this.pKb = new double[length];
        this.qKb = new double[length];
        this.rKb = new double[length];
        this.sKb = new double[length];
        this.tKb = new double[length];
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < length; ++i) {
            this.FHb._(this.gKb[i]);
            int n3;
            if (this.zKb == 100002) {
                n3 = this.FHb.R();
            }
            else if (this.zKb == 100003) {
                n3 = this.FHb.m();
            }
            else {
                n3 = i;
            }
            if (n3 != n) {
                ++n2;
                this.oKb[n2] = this.hKb[i];
                this.qKb[n2] = Double.POSITIVE_INFINITY;
                this.pKb[n2] = Double.NEGATIVE_INFINITY;
            }
            this.nKb[n2] = this.gKb[i];
            this.rKb[n2] = this.kKb[i];
            this.pKb[n2] = Math.max(this.pKb[n2], this.iKb[i]);
            this.qKb[n2] = Math.min(this.qKb[n2], this.jKb[i]);
            final double[] sKb = this.sKb;
            final int n4 = n2;
            sKb[n4] += this.lKb[i];
            this.tKb[n2] = this.mKb[i];
            n = n3;
        }
        this.nKb = this.a(this.nKb, n2 + 1);
        this.oKb = this.a(this.oKb, n2 + 1);
        this.pKb = this.a(this.pKb, n2 + 1);
        this.qKb = this.a(this.qKb, n2 + 1);
        this.rKb = this.a(this.rKb, n2 + 1);
        this.sKb = this.a(this.sKb, n2 + 1);
        this.tKb = this.a(this.tKb, n2 + 1);
    }
    
    private void L() {
        if (this.ZJb == null) {
            this._Kb = null;
            this.aKb = null;
            this.bKb = null;
            this.cKb = null;
            this.dKb = null;
            this.eKb = null;
            this.fKb = null;
        }
        else if (this.xKb) {
            if (this.n()) {
                this.pc();
            }
            else {
                this.qc();
            }
            this.xKb = false;
        }
    }
    
    private void N() {
        if (this.gKb == null) {
            this.nKb = null;
            this.oKb = null;
            this.pKb = null;
            this.qKb = null;
            this.rKb = null;
            this.sKb = null;
            this.tKb = null;
        }
        else if (this.yKb) {
            if (this.n()) {
                this.rc();
            }
            else {
                this.sc();
            }
            this.yKb = false;
        }
    }
    
    public static int b(final String s) {
        if (s == null) {
            return 0;
        }
        int length;
        for (int n = length = s.length(), i = 0; i < n; ++i) {
            length ^= s.charAt(i) << i % 3 * 8;
        }
        return length;
    }
    
    public synchronized String[][] _() {
        final String[] array = new String[this.PJb.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.g(this.PJb[i]);
        }
        return new String[][] { this.PJb, array };
    }
    
    public synchronized String[] k() {
        return this.PJb;
    }
    
    public synchronized void b(final String[] pJb) {
        this.PJb = pJb;
        this.mc();
    }
    
    private void mc() {
        final Vector<String> vector = new Vector<String>();
        if (this.QJb != null) {
            vector.addElement(this.h(this.QJb));
        }
        if (this.RJb != null) {
            vector.addElement(this.h(this.RJb));
        }
        for (int i = 0; i < this.PJb.length; ++i) {
            vector.addElement(this.h(this.PJb[i]));
        }
        this.CKb.b(vector);
    }
    
    public synchronized void G(final String kJb) {
        this.KJb = kJb;
    }
    
    public synchronized void H(final String lJb) {
        this.LJb = lJb;
    }
    
    public synchronized String _(final String s, final String s2) {
        if (this.LJb == null || this.LJb.length() == 0) {
            return null;
        }
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0 || this.LJb == null || this.LJb.length() == 0) {
            return null;
        }
        final boolean b = this.OJb.b(this.LJb, s, s2);
        String text = null;
        if (b) {
            text = this.OJb.getText();
        }
        this.OJb.clear();
        return text;
    }
    
    public synchronized String[] b(final String text, final String s, final String s2) {
        if (text == null || text.length() == 0) {
            return null;
        }
        if (this.KJb == null || this.KJb.length() == 0) {
            return null;
        }
        this.OJb.setText(text);
        final boolean a = this.OJb.a(this.KJb, s, s2, "indicators");
        this.OJb.clear();
        String[] array = null;
        if (a) {
            array = new String[] { this.OJb.C(), this.OJb.D() };
        }
        return array;
    }
}
