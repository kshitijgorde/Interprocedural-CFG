import java.util.GregorianCalendar;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Yh
{
    private String id;
    private long fja;
    private Zh gja;
    private Vector ib;
    private int hja;
    private int ija;
    private f jja;
    private r kja;
    
    public Yh(final String id) {
        this.fja = System.currentTimeMillis();
        this.gja = new Zh();
        this.ib = null;
        this.hja = -1;
        this.ija = 2;
        this.jja = new f();
        this.kja = new r();
        if (id == null || id.length() == 0) {
            throw new IllegalArgumentException("id cannot empty");
        }
        this.id = id;
    }
    
    public synchronized int b(final boolean b) {
        return this.ija;
    }
    
    public synchronized long b() {
        return this.fja;
    }
    
    public synchronized boolean b(final boolean b) {
        if (this.ib == null || this.ib.size() == 0) {
            return false;
        }
        this.ib.removeAllElements();
        return true;
    }
    
    private long _() {
        if (this.ib != null && this.ib.size() > 0) {
            return this.ib.elementAt(0).la;
        }
        return 0L;
    }
    
    private long k() {
        if (this.ib != null && this.ib.size() > 0) {
            return this.ib.elementAt(this.ib.size() - 1).la;
        }
        return 0L;
    }
    
    public synchronized String a(final boolean b) {
        if (this.ib != null && this.ib.size() > 0) {
            this.gja.b(this.ib.elementAt(this.ib.size() - 1).la);
            return this.gja.toString();
        }
        return "";
    }
    
    public synchronized String b(final boolean b) {
        if (this.ib != null && this.ib.size() > 0) {
            this.gja.b(this.ib.elementAt(0).la);
            return this.gja.toString();
        }
        return "";
    }
    
    private void w(final String s) {
        if (this.hja >= 0) {
            this.ija = this.hja;
            return;
        }
        if (s == null) {
            return;
        }
        final int index = s.indexOf(46);
        if (index != -1) {
            this.ija = Math.max(this.ija, s.length() - index - 1);
        }
    }
    
    private boolean n() {
        return this.ib != null && this.ib.size() > 0;
    }
    
    private boolean a(final int n, final q q, final boolean b) {
        this.fja = System.currentTimeMillis();
        if (q.l() <= n) {
            return this.n();
        }
        boolean b2 = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        ai ai = null;
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        int n11 = -1;
        int n12 = -1;
        int n13 = -1;
        int n14 = -1;
        int n15 = -1;
        int n16 = -1;
        int n17 = -1;
        final u u = new u(",");
        final String _ = q._(n);
        if (_ == null) {
            return this.n();
        }
        if (!this.d(_)) {
            return this.n();
        }
        u.m(_.toUpperCase());
        for (int i = 0; i < u.a(); ++i) {
            if (u.b(i).equals("<TIME>") || u.b(i).equals("TIME")) {
                n8 = i;
            }
            else if (u.b(i).startsWith("<DT") || u.b(i).equals("<DATE>") || u.b(i).equals("DATE")) {
                n9 = i;
            }
            else if (u.b(i).equals("<OPEN>") || u.b(i).equals("OPEN")) {
                n10 = i;
            }
            else if (u.b(i).equals("<HIGH>") || u.b(i).equals("HIGH")) {
                n11 = i;
            }
            else if (u.b(i).equals("<LOW>") || u.b(i).equals("LOW")) {
                n12 = i;
            }
            else if (u.b(i).equals("<CLOSE>") || u.b(i).equals("CLOSE")) {
                n13 = i;
            }
            else if (u.b(i).equals("<VOL>") || u.b(i).equals("<VOLUME>") || u.b(i).equals("VOL") || u.b(i).equals("VOLUME")) {
                n14 = i;
            }
            else if (u.b(i).equals("<OI>") || u.b(i).equals("<OPENINT>") || u.b(i).equals("OI") || u.b(i).equals("OPENINT")) {
                n15 = i;
            }
            else if (u.b(i).equals("<INFO>") || u.b(i).equals("INFO")) {
                n16 = i;
            }
            else if (u.b(i).equals("<ANNOTATION>") || u.b(i).equals("ANNOTATION")) {
                n17 = i;
            }
        }
        if (n9 != -1) {
            String s = u.b(n9);
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
                ai = new ai(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return this.n();
            }
        }
        Label_0858: {
            if (n8 != -1) {
                final u u2 = new u(",");
                String _2 = null;
                int j = q.l() - 1;
                while (true) {
                    while (j >= 1) {
                        _2 = q._(j);
                        --j;
                        if (_2 != null && _2.length() != 0) {
                            if (_2 == null || _2.length() == 0) {
                                return this.n();
                            }
                            u2.m(_2);
                            final String b3 = u2.b(n8);
                            if (b3 == null || b3.indexOf(":") == -1) {
                                n2 = 0;
                                n3 = 1;
                                n4 = 2;
                                n5 = 3;
                                n6 = 4;
                                n7 = 5;
                                break Label_0858;
                            }
                            n2 = 0;
                            n3 = 1;
                            n4 = 3;
                            n5 = 4;
                            n6 = 6;
                            n7 = 7;
                            break Label_0858;
                        }
                    }
                    continue;
                }
            }
        }
        int n18 = 1;
        if (this.ib == null) {
            if (b) {
                this.ib = new Vector(q.l() + 100);
            }
            else {
                this.ib = new Vector(q.l() + 1);
            }
        }
        this.gja.b(0, 0, 0, 0, 0, 0, 0);
        try {
            for (int k = n; k < q.l(); ++k) {
                boolean b4 = false;
                final String _3 = q._(k);
                if (_3 != null) {
                    if (_3.length() != 0) {
                        u.m(_3);
                        if (u.a() >= 2) {
                            if (k != n || !this.d(u.K())) {
                                int n19 = 0;
                                int n20 = 0;
                                final _i l = new _i();
                                if (b && n8 != -1) {
                                    int day;
                                    int year;
                                    int month = year = (day = 0);
                                    final String b5 = u.b(n8);
                                    final int int1 = Integer.parseInt(b5.substring(n2, n3 + 1));
                                    final int int2 = Integer.parseInt(b5.substring(n4, n5 + 1));
                                    final int int3 = Integer.parseInt(b5.substring(n6, n7 + 1));
                                    int int4 = -1;
                                    if (b5.length() > n7 + 1) {
                                        int4 = Integer.parseInt(b5.substring(n7 + 2));
                                    }
                                    if (int4 > 999) {
                                        int4 = 999;
                                    }
                                    n20 = int1 + int2 + int3 + ((int4 < 0) ? 0 : int4);
                                    if (n9 != -1) {
                                        ai.v(u.b(n9));
                                        year = ai.getYear();
                                        final int n21 = n19 + year;
                                        month = ai.getMonth();
                                        final int n22 = n21 + month;
                                        day = ai.getDay();
                                        n19 = n22 + day;
                                    }
                                    if (int4 < 0) {
                                        int4 = 0;
                                    }
                                    this.gja.b(year, month, day, int1, int2, int3, int4);
                                    l.la = this.gja.a();
                                    b4 = true;
                                }
                                else if (!b && n9 != -1) {
                                    ai.v(u.b(n9));
                                    final int year2 = ai.getYear();
                                    final int n23 = n19 + year2;
                                    final int month2 = ai.getMonth();
                                    final int n24 = n23 + month2;
                                    final int day2 = ai.getDay();
                                    n19 = n24 + day2;
                                    this.gja.set(year2, month2, day2);
                                    l.la = this.gja.a();
                                    b4 = true;
                                }
                                if (b4) {
                                    if (n18 != 0) {
                                        this.ija = 0;
                                        if (n19 == 0 && n20 == 0) {
                                            this.ib.removeAllElements();
                                            continue;
                                        }
                                    }
                                    String b6 = u.b(n10);
                                    if (b6 == null || b6.length() == 0) {
                                        b6 = "0";
                                    }
                                    l.L = Float.valueOf(b6);
                                    if (n18 != 0) {
                                        this.w(b6);
                                    }
                                    String b7 = u.b(n12);
                                    if (b7 == null || b7.length() == 0) {
                                        b7 = "0";
                                    }
                                    l.N = Float.valueOf(b7);
                                    if (n18 != 0) {
                                        this.w(b7);
                                    }
                                    String b8 = u.b(n11);
                                    if (b8 == null || b8.length() == 0) {
                                        b8 = "0";
                                    }
                                    l.M = Float.valueOf(b8);
                                    if (n18 != 0) {
                                        this.w(b8);
                                    }
                                    String b9 = u.b(n13);
                                    if (b9 == null || b9.length() == 0) {
                                        b9 = "0";
                                    }
                                    l.O = Float.valueOf(b9);
                                    if (n18 != 0) {
                                        this.w(b9);
                                    }
                                    String b10 = u.b(n14);
                                    if (b10 == null || b10.length() == 0) {
                                        b10 = "0";
                                    }
                                    l.P = Double.valueOf(b10);
                                    String b11 = u.b(n15);
                                    if (b11 == null || b11.length() == 0) {
                                        b11 = "0";
                                    }
                                    l.Q = Float.valueOf(b11);
                                    String b12 = u.b(n16);
                                    if (b12 == null || b12.length() == 0) {
                                        b12 = "0";
                                    }
                                    l.info = Float.valueOf(b12);
                                    String b13 = u.b(n17);
                                    if (b13 != null && b13.length() == 0) {
                                        b13 = null;
                                    }
                                    l.ma = b13;
                                    if (l.O < Float.MIN_VALUE) {
                                        if (l.L > 0.0f) {
                                            l.O = l.L;
                                        }
                                        else if (l.M > 0.0f) {
                                            l.O = l.M;
                                        }
                                        else if (l.N > 0.0f) {
                                            l.O = l.N;
                                        }
                                    }
                                    if (l.L < Float.MIN_VALUE) {
                                        if (l.O > 0.0f) {
                                            l.L = l.O;
                                        }
                                        else if (l.M > 0.0f) {
                                            l.L = l.M;
                                        }
                                        else if (l.N > 0.0f) {
                                            l.L = l.N;
                                        }
                                    }
                                    if (l.N < Float.MIN_VALUE) {
                                        l.N = Math.min(l.L, l.O);
                                    }
                                    if (l.M < Float.MIN_VALUE) {
                                        l.M = Math.max(l.L, l.O);
                                    }
                                    if (l.N > l.M) {
                                        final float n25 = l.N;
                                        l.N = l.M;
                                        l.M = n25;
                                    }
                                    if (l.O > 0.0f) {
                                        n18 = 0;
                                        this.a(l);
                                    }
                                    else if (l.la > 0L) {
                                        this.b(l);
                                    }
                                    else if (l.O < 0.0f) {
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
    
    private void b(final _i i) {
        final int a = this.a(i.la);
        if (a >= 0) {
            this.ib.removeElementAt(a);
        }
    }
    
    private void a(final _i i) {
        final int a = this.a(i.la);
        if (a >= 0) {
            this.ib.setElementAt(i, a);
        }
        else {
            this.ib.insertElementAt(i, -a - 1);
        }
    }
    
    private int a(final long n) {
        int i = 0;
        int n2 = this.ib.size() - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final long la = this.ib.elementAt(n3).la;
            if (la < n) {
                i = n3 + 1;
            }
            else {
                if (la <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
    
    private boolean d(String upperCase) {
        if (upperCase == null) {
            return false;
        }
        if (upperCase.startsWith("<") && upperCase.endsWith(">")) {
            return true;
        }
        upperCase = upperCase.toUpperCase();
        return upperCase.indexOf(44) != -1 && upperCase.indexOf("CLOSE") != -1 && (upperCase.indexOf("DATE") != -1 || upperCase.indexOf("TIME") != -1 || upperCase.indexOf("<DT") != -1);
    }
    
    private boolean e(final String s) {
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
    
    public synchronized boolean a(final q q, final boolean b, final String s, final int hja) {
        if (q == null) {
            return this.n();
        }
        if (q.l() == 0) {
            return this.n();
        }
        this.hja = hja;
        int n;
        for (n = 0; n < q.l() && this.e(q._(n)); ++n) {}
        if (n >= q.l()) {
            return this.n();
        }
        return this.d(q._(n)) && this.a(n, q, b);
    }
    
    public synchronized Vh _(final boolean b, final int n) {
        this.fja = System.currentTimeMillis();
        if (this.ib == null || this.ib.size() < 1) {
            return new Vh(null, null, null, null, null, null, null, null, null);
        }
        int n2 = 0;
        if (n > 0) {
            if (b) {
                final int size = this.ib.size();
                final Vector vector = new Vector<Long>(30);
                long n3 = -1L;
                for (int i = 0; i < size; ++i) {
                    final long n4 = this.ib.elementAt(i).la / 1000000000L * 1000000000L;
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
                    this.gja.b(this.k());
                    this.gja.set(this.gja.getYear(), this.gja.getMonth(), this.gja.getDay());
                    n5 = this.gja.a();
                }
                n2 = this.a(n5);
            }
            else {
                this.gja.b(this.k());
                final GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(this.gja.getYear(), this.gja.getMonth() - 1, this.gja.getDay());
                if (b) {
                    gregorianCalendar.add(5, -n);
                }
                else {
                    gregorianCalendar.add(1, -n);
                }
                this.gja.b(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5), 0, 0, 0, 0);
                n2 = this.a(this.gja.a());
            }
            if (n2 < 0) {
                n2 = -n2 - 1;
            }
            if (n2 >= this.ib.size()) {
                n2 = this.ib.size() - 1;
            }
        }
        final int n6 = this.ib.size() - n2;
        final double[] array = new double[n6];
        final double[] array2 = new double[n6];
        final double[] array3 = new double[n6];
        final double[] array4 = new double[n6];
        final double[] array5 = new double[n6];
        final double[] array6 = new double[n6];
        final double[] array7 = new double[n6];
        final double[] array8 = new double[n6];
        final String[] array9 = new String[n6];
        int n7 = 0;
        for (int size2 = this.ib.size(), j = n2; j < size2; ++j) {
            final _i k = this.ib.elementAt(j);
            this.gja.b(k.la);
            if (b) {
                this.jja.b(this.gja.getYear(), this.gja.getMonth(), this.gja.getDay(), this.gja.c(), this.gja.e(), this.gja.f(), this.gja.d());
                array[n7] = this.jja._();
            }
            else {
                this.kja.set(this.gja.getYear(), this.gja.getMonth(), this.gja.getDay());
                array[n7] = this.kja._();
            }
            array2[n7] = k.L;
            array3[n7] = k.M;
            array4[n7] = k.N;
            array5[n7] = k.O;
            array6[n7] = k.P;
            array7[n7] = k.Q;
            array8[n7] = k.info;
            array9[n7] = k.ma;
            ++n7;
        }
        return new Vh(array, array2, array3, array4, array5, array6, array7, array8, array9);
    }
}
