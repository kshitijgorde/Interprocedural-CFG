import java.util.GregorianCalendar;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class bp
{
    private String id;
    public static final int qHb = 2;
    public static final int rHb = 3;
    public static final int sHb = 4;
    public static final int tHb = 6;
    public static final int uHb = 7;
    public static final int vHb = 3;
    public static final int wHb = 2;
    public static final int xHb = 4;
    public static final int yHb = 5;
    public static final int zHb = 3;
    private long AHb;
    private cp BHb;
    private Vector e;
    private int CHb;
    private int DHb;
    private Yo EHb;
    private Xo FHb;
    
    public bp(final String id) {
        this.AHb = System.currentTimeMillis();
        this.BHb = new cp();
        this.e = null;
        this.CHb = -1;
        this.DHb = 2;
        this.EHb = new Yo();
        this.FHb = new Xo();
        if (id == null || id.length() == 0) {
            throw new IllegalArgumentException("id cannot empty");
        }
        this.id = id;
    }
    
    public synchronized int _(final boolean b) {
        return this.DHb;
    }
    
    public synchronized long a() {
        return this.AHb;
    }
    
    public synchronized boolean a(final boolean b) {
        if (this.e == null || this.e.size() == 0) {
            return false;
        }
        this.e.removeAllElements();
        return true;
    }
    
    private long b() {
        if (this.e != null && this.e.size() > 0) {
            return this.e.elementAt(0).aHb;
        }
        return 0L;
    }
    
    private long i() {
        if (this.e != null && this.e.size() > 0) {
            return this.e.elementAt(this.e.size() - 1).aHb;
        }
        return 0L;
    }
    
    public synchronized String _(final boolean b) {
        if (this.e != null && this.e.size() > 0) {
            this.BHb._(this.e.elementAt(this.e.size() - 1).aHb);
            return this.BHb.toString();
        }
        return "";
    }
    
    public synchronized String a(final boolean b) {
        if (this.e != null && this.e.size() > 0) {
            this.BHb._(this.e.elementAt(0).aHb);
            return this.BHb.toString();
        }
        return "";
    }
    
    private void A(final String s) {
        if (this.CHb >= 0) {
            this.DHb = this.CHb;
            return;
        }
        if (s == null) {
            return;
        }
        final int index = s.indexOf(46);
        if (index != -1) {
            this.DHb = Math.max(this.DHb, s.length() - index - 1);
        }
    }
    
    private boolean k() {
        return this.e != null && this.e.size() > 0;
    }
    
    private boolean a(final int n, final _ _, final boolean b) {
        this.AHb = System.currentTimeMillis();
        if (_.L() <= n) {
            return this.k();
        }
        boolean b2 = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        ep ep = null;
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        int n11 = -1;
        int n12 = -1;
        int n13 = -1;
        int n14 = -1;
        int n15 = -1;
        final d d = new d(",");
        final String l = _.l(n);
        if (l == null) {
            return this.k();
        }
        if (!this.n(l)) {
            return this.k();
        }
        d.e(l.toUpperCase());
        for (int i = 0; i < d.z(); ++i) {
            if (d._(i).equals("<TIME>") || d._(i).equals("TIME")) {
                n8 = i;
            }
            else if (d._(i).startsWith("<DT") || d._(i).equals("<DATE>") || d._(i).equals("DATE")) {
                n9 = i;
            }
            else if (d._(i).equals("<OPEN>") || d._(i).equals("OPEN")) {
                n10 = i;
            }
            else if (d._(i).equals("<HIGH>") || d._(i).equals("HIGH")) {
                n11 = i;
            }
            else if (d._(i).equals("<LOW>") || d._(i).equals("LOW")) {
                n12 = i;
            }
            else if (d._(i).equals("<CLOSE>") || d._(i).equals("CLOSE")) {
                n13 = i;
            }
            else if (d._(i).equals("<VOL>") || d._(i).equals("<VOLUME>") || d._(i).equals("VOL") || d._(i).equals("VOLUME")) {
                n14 = i;
            }
            else if (d._(i).equals("<OI>") || d._(i).equals("<OPENINT>") || d._(i).equals("OI") || d._(i).equals("OPENINT")) {
                n15 = i;
            }
        }
        if (n9 != -1) {
            String s = d._(n9);
            if (s.startsWith("<DT")) {
                s = s.substring(3);
            }
            else if (s.startsWith("<")) {
                s = s.substring(1);
            }
            if (s.endsWith(">")) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.equals("DATE")) {
                s = "YYYYMMDD";
            }
            try {
                ep = new ep(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return this.k();
            }
        }
        Label_0778: {
            if (n8 != -1) {
                final d d2 = new d(",");
                String j = null;
                int k = _.L() - 1;
                while (true) {
                    while (k >= 1) {
                        j = _.l(k);
                        --k;
                        if (j != null && j.length() != 0) {
                            if (j == null || j.length() == 0) {
                                return this.k();
                            }
                            d2.e(j);
                            final String _2 = d2._(n8);
                            if (_2 == null || _2.indexOf(":") == -1) {
                                n2 = 0;
                                n3 = 1;
                                n4 = 2;
                                n5 = 3;
                                n6 = 4;
                                n7 = 5;
                                break Label_0778;
                            }
                            n2 = 0;
                            n3 = 1;
                            n4 = 3;
                            n5 = 4;
                            n6 = 6;
                            n7 = 7;
                            break Label_0778;
                        }
                    }
                    continue;
                }
            }
        }
        int n16 = 1;
        if (this.e == null) {
            if (b) {
                this.e = new Vector(_.L() + 100);
            }
            else {
                this.e = new Vector(_.L() + 1);
            }
        }
        try {
            for (int n17 = n; n17 < _.L(); ++n17) {
                boolean b3 = false;
                final String m = _.l(n17);
                if (m != null) {
                    if (m.length() != 0) {
                        d.e(m);
                        if (d.z() >= 2) {
                            if (n17 != n || !this.n(d.f())) {
                                int n18 = 0;
                                int n19 = 0;
                                final dp dp = new dp();
                                if (b && n8 != -1) {
                                    final String _3 = d._(n8);
                                    final int int1 = Integer.parseInt(_3.substring(n2, n3 + 1));
                                    final int int2 = Integer.parseInt(_3.substring(n4, n5 + 1));
                                    final int int3 = Integer.parseInt(_3.substring(n6, n7 + 1));
                                    int int4 = 0;
                                    if (_3.length() > n7 + 1) {
                                        int4 = Integer.parseInt(_3.substring(n7 + 2));
                                    }
                                    if (int4 < 0 || int4 > 999) {
                                        int4 = 0;
                                    }
                                    n19 = int1 + int2 + int3 + int4;
                                    if (n9 != -1) {
                                        ep.z(d._(n9));
                                        final int year = ep.getYear();
                                        final int n20 = n18 + year;
                                        final int month = ep.getMonth();
                                        final int n21 = n20 + month;
                                        final int day = ep.getDay();
                                        n18 = n21 + day;
                                        this.BHb._(year, month, day, int1, int2, int3, int4);
                                    }
                                    else {
                                        this.BHb._(0, 0, 0, int1, int2, int3, int4);
                                    }
                                    dp.aHb = this.BHb._();
                                    b3 = true;
                                }
                                else if (!b && n9 != -1) {
                                    ep.z(d._(n9));
                                    final int year2 = ep.getYear();
                                    final int n22 = n18 + year2;
                                    final int month2 = ep.getMonth();
                                    final int n23 = n22 + month2;
                                    final int day2 = ep.getDay();
                                    n18 = n23 + day2;
                                    this.BHb.set(year2, month2, day2);
                                    dp.aHb = this.BHb._();
                                    b3 = true;
                                }
                                if (b3) {
                                    if (n16 != 0) {
                                        this.DHb = 0;
                                        if (n18 == 0 && n19 == 0) {
                                            this.e.removeAllElements();
                                            continue;
                                        }
                                    }
                                    String _4 = d._(n10);
                                    if (_4 == null || _4.length() == 0) {
                                        _4 = "0";
                                    }
                                    dp.bHb = Float.valueOf(_4);
                                    if (n16 != 0) {
                                        this.A(_4);
                                    }
                                    String _5 = d._(n12);
                                    if (_5 == null || _5.length() == 0) {
                                        _5 = "0";
                                    }
                                    dp.dHb = Float.valueOf(_5);
                                    if (n16 != 0) {
                                        this.A(_5);
                                    }
                                    String _6 = d._(n11);
                                    if (_6 == null || _6.length() == 0) {
                                        _6 = "0";
                                    }
                                    dp.cHb = Float.valueOf(_6);
                                    if (n16 != 0) {
                                        this.A(_6);
                                    }
                                    String _7 = d._(n13);
                                    if (_7 == null || _7.length() == 0) {
                                        _7 = "0";
                                    }
                                    dp.eHb = Float.valueOf(_7);
                                    if (n16 != 0) {
                                        this.A(_7);
                                    }
                                    String _8 = d._(n14);
                                    if (_8 == null || _8.length() == 0) {
                                        _8 = "0";
                                    }
                                    dp.fHb = Double.valueOf(_8);
                                    String _9 = d._(n15);
                                    if (_9 == null || _9.length() == 0) {
                                        _9 = "0";
                                    }
                                    dp.gHb = Float.valueOf(_9);
                                    if (dp.eHb < Float.MIN_VALUE) {
                                        if (dp.bHb > 0.0f) {
                                            dp.eHb = dp.bHb;
                                        }
                                        else if (dp.cHb > 0.0f) {
                                            dp.eHb = dp.cHb;
                                        }
                                        else if (dp.dHb > 0.0f) {
                                            dp.eHb = dp.dHb;
                                        }
                                    }
                                    if (dp.bHb < Float.MIN_VALUE) {
                                        if (dp.eHb > 0.0f) {
                                            dp.bHb = dp.eHb;
                                        }
                                        else if (dp.cHb > 0.0f) {
                                            dp.bHb = dp.cHb;
                                        }
                                        else if (dp.dHb > 0.0f) {
                                            dp.bHb = dp.dHb;
                                        }
                                    }
                                    if (dp.dHb < Float.MIN_VALUE) {
                                        dp.dHb = Math.min(dp.bHb, dp.eHb);
                                    }
                                    if (dp.cHb < Float.MIN_VALUE) {
                                        dp.cHb = Math.max(dp.bHb, dp.eHb);
                                    }
                                    if (dp.dHb > dp.cHb) {
                                        final float dHb = dp.dHb;
                                        dp.dHb = dp.cHb;
                                        dp.cHb = dHb;
                                    }
                                    if (dp.eHb > 0.0f) {
                                        n16 = 0;
                                        this.b(dp);
                                    }
                                    else if (dp.aHb > 0L) {
                                        this._(dp);
                                    }
                                    else if (dp.eHb < 0.0f) {
                                        b2 = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            b2 = true;
        }
        return !b2;
    }
    
    private void _(final dp dp) {
        final int _ = this._(dp.aHb);
        if (_ >= 0) {
            this.e.removeElementAt(_);
        }
    }
    
    private void b(final dp dp) {
        final int _ = this._(dp.aHb);
        if (_ >= 0) {
            this.e.setElementAt(dp, _);
        }
        else {
            this.e.insertElementAt(dp, -_ - 1);
        }
    }
    
    private int _(final long n) {
        int i = 0;
        int n2 = this.e.size() - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final long aHb = this.e.elementAt(n3).aHb;
            if (aHb < n) {
                i = n3 + 1;
            }
            else {
                if (aHb <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
    
    private boolean n(String upperCase) {
        if (upperCase == null) {
            return false;
        }
        if (upperCase.startsWith("<") && upperCase.endsWith(">")) {
            return true;
        }
        upperCase = upperCase.toUpperCase();
        return upperCase.indexOf(44) != -1 && upperCase.indexOf("CLOSE") != -1 && (upperCase.indexOf("DATE") != -1 || upperCase.indexOf("TIME") != -1 || upperCase.indexOf("<DT") != -1);
    }
    
    private boolean c(final String s) {
        if (s == null) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }
        boolean b = true;
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isSpaceChar(s.charAt(i))) {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public synchronized boolean a(final _ _, final boolean b, final String s, final int cHb) {
        if (_ == null) {
            return this.k();
        }
        if (_.L() == 0) {
            return this.k();
        }
        this.CHb = cHb;
        int n;
        for (n = 0; n < _.L() && this.c(_.l(n)); ++n) {}
        if (n >= _.L()) {
            return this.k();
        }
        return this.n(_.l(n)) && this.a(n, _, b);
    }
    
    public synchronized double[][] a(final boolean b, final int n) {
        this.AHb = System.currentTimeMillis();
        if (this.e == null || this.e.size() < 1) {
            return new double[][] { null, null, null, null, null, null, null };
        }
        int n2 = 0;
        if (n > 0) {
            if (b) {
                final int size = this.e.size();
                final Vector vector = new Vector<Long>(30);
                long n3 = -1L;
                for (int i = 0; i < size; ++i) {
                    final long n4 = this.e.elementAt(i).aHb / 1000000000L * 1000000000L;
                    if (n4 != n3) {
                        vector.addElement(new Long(n4));
                        n3 = n4;
                    }
                }
                long n5;
                if (vector.size() > 0) {
                    n5 = vector.elementAt(Math.max(0, vector.size() - n));
                }
                else {
                    this.BHb._(this.i());
                    this.BHb.set(this.BHb.getYear(), this.BHb.getMonth(), this.BHb.getDay());
                    n5 = this.BHb._();
                }
                n2 = this._(n5);
            }
            else {
                this.BHb._(this.i());
                final GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(this.BHb.getYear(), this.BHb.getMonth() - 1, this.BHb.getDay());
                if (b) {
                    gregorianCalendar.add(5, -n);
                }
                else {
                    gregorianCalendar.add(1, -n);
                }
                this.BHb._(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5), 0, 0, 0, 0);
                n2 = this._(this.BHb._());
            }
            if (n2 < 0) {
                n2 = -n2 - 1;
            }
            if (n2 >= this.e.size()) {
                n2 = this.e.size() - 1;
            }
        }
        final int n6 = this.e.size() - n2;
        final double[] array = new double[n6];
        final double[] array2 = new double[n6];
        final double[] array3 = new double[n6];
        final double[] array4 = new double[n6];
        final double[] array5 = new double[n6];
        final double[] array6 = new double[n6];
        final double[] array7 = new double[n6];
        int n7 = 0;
        for (int size2 = this.e.size(), j = n2; j < size2; ++j) {
            final dp dp = this.e.elementAt(j);
            this.BHb._(dp.aHb);
            if (b) {
                this.EHb._(this.BHb.getYear(), this.BHb.getMonth(), this.BHb.getDay(), this.BHb.M(), this.BHb.O(), this.BHb.P(), this.BHb.N());
                array[n7] = this.EHb.m();
            }
            else {
                this.FHb.set(this.BHb.getYear(), this.BHb.getMonth(), this.BHb.getDay());
                array[n7] = this.FHb.m();
            }
            array2[n7] = dp.bHb;
            array3[n7] = dp.cHb;
            array4[n7] = dp.dHb;
            array5[n7] = dp.eHb;
            array6[n7] = dp.fHb;
            array7[n7] = dp.gHb;
            ++n7;
        }
        return new double[][] { array, array2, array3, array4, array5, array6, array7 };
    }
}
