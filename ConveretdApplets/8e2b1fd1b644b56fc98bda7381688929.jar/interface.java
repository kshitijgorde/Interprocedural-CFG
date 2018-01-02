import java.util.GregorianCalendar;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class interface
{
    private String id;
    private long Wpa;
    private native Xpa;
    private Vector z;
    private int Ypa;
    private int Zpa;
    private else _qa;
    private throw aqa;
    
    public interface(final String id) {
        this.Wpa = System.currentTimeMillis();
        this.Xpa = new native();
        this.z = null;
        this.Ypa = -1;
        this.Zpa = 2;
        this._qa = new else();
        this.aqa = new throw();
        if (id == null || id.length() == 0) {
            throw new IllegalArgumentException("id cannot empty");
        }
        this.id = id;
    }
    
    public synchronized int b(final boolean b) {
        return this.Zpa;
    }
    
    public synchronized long a() {
        return this.Wpa;
    }
    
    public synchronized boolean a(final boolean b) {
        if (this.z == null || this.z.size() == 0) {
            return false;
        }
        this.z.removeAllElements();
        return true;
    }
    
    private long b() {
        if (this.z != null && this.z.size() > 0) {
            return this.z.elementAt(0).Lpa;
        }
        return 0L;
    }
    
    private long l() {
        if (this.z != null && this.z.size() > 0) {
            return this.z.elementAt(this.z.size() - 1).Lpa;
        }
        return 0L;
    }
    
    public synchronized String a(final boolean b) {
        if (this.z != null && this.z.size() > 0) {
            this.Xpa.b(this.z.elementAt(this.z.size() - 1).Lpa);
            return this.Xpa.toString();
        }
        return "";
    }
    
    public synchronized String b(final boolean b) {
        if (this.z != null && this.z.size() > 0) {
            this.Xpa.b(this.z.elementAt(0).Lpa);
            return this.Xpa.toString();
        }
        return "";
    }
    
    private void w(final String s) {
        if (this.Ypa >= 0) {
            this.Zpa = this.Ypa;
            return;
        }
        if (s == null) {
            return;
        }
        final int index = s.indexOf(46);
        if (index != -1) {
            this.Zpa = Math.max(this.Zpa, s.length() - index - 1);
        }
    }
    
    private boolean e() {
        return this.z != null && this.z.size() > 0;
    }
    
    private boolean _(final int n, final this this2, final boolean b) {
        this.Wpa = System.currentTimeMillis();
        if (this2.K() <= n) {
            return this.e();
        }
        boolean b2 = false;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        null null = null;
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        int n11 = -1;
        int n12 = -1;
        int n13 = -1;
        int n14 = -1;
        int n15 = -1;
        int n16 = -1;
        final try try1 = new try(",");
        final String b3 = this2.b(n);
        if (b3 == null) {
            return this.e();
        }
        if (!this.l(b3)) {
            return this.e();
        }
        try1.l(b3.toUpperCase());
        for (int i = 0; i < try1.g(); ++i) {
            if (try1.a(i).equals("<TIME>") || try1.a(i).equals("TIME")) {
                n8 = i;
            }
            else if (try1.a(i).startsWith("<DT") || try1.a(i).equals("<DATE>") || try1.a(i).equals("DATE")) {
                n9 = i;
            }
            else if (try1.a(i).equals("<OPEN>") || try1.a(i).equals("OPEN")) {
                n10 = i;
            }
            else if (try1.a(i).equals("<HIGH>") || try1.a(i).equals("HIGH")) {
                n11 = i;
            }
            else if (try1.a(i).equals("<LOW>") || try1.a(i).equals("LOW")) {
                n12 = i;
            }
            else if (try1.a(i).equals("<CLOSE>") || try1.a(i).equals("CLOSE")) {
                n13 = i;
            }
            else if (try1.a(i).equals("<VOL>") || try1.a(i).equals("<VOLUME>") || try1.a(i).equals("VOL") || try1.a(i).equals("VOLUME")) {
                n14 = i;
            }
            else if (try1.a(i).equals("<OI>") || try1.a(i).equals("<OPENINT>") || try1.a(i).equals("OI") || try1.a(i).equals("OPENINT")) {
                n15 = i;
            }
            else if (try1.a(i).equals("<INFO>") || try1.a(i).equals("INFO")) {
                n16 = i;
            }
        }
        if (n9 != -1) {
            String s = try1.a(n9);
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
                null = new null(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return this.e();
            }
        }
        Label_0818: {
            if (n8 != -1) {
                final try try2 = new try(",");
                String b4 = null;
                int j = this2.K() - 1;
                while (true) {
                    while (j >= 1) {
                        b4 = this2.b(j);
                        --j;
                        if (b4 != null && b4.length() != 0) {
                            if (b4 == null || b4.length() == 0) {
                                return this.e();
                            }
                            try2.l(b4);
                            final String a = try2.a(n8);
                            if (a == null || a.indexOf(":") == -1) {
                                n2 = 0;
                                n3 = 1;
                                n4 = 2;
                                n5 = 3;
                                n6 = 4;
                                n7 = 5;
                                break Label_0818;
                            }
                            n2 = 0;
                            n3 = 1;
                            n4 = 3;
                            n5 = 4;
                            n6 = 6;
                            n7 = 7;
                            break Label_0818;
                        }
                    }
                    continue;
                }
            }
        }
        int n17 = 1;
        if (this.z == null) {
            if (b) {
                this.z = new Vector(this2.K() + 100);
            }
            else {
                this.z = new Vector(this2.K() + 1);
            }
        }
        this.Xpa.b(0, 0, 0, 0, 0, 0, 0);
        try {
            for (int k = n; k < this2.K(); ++k) {
                boolean b5 = false;
                final String b6 = this2.b(k);
                if (b6 != null) {
                    if (b6.length() != 0) {
                        try1.l(b6);
                        if (try1.g() >= 2) {
                            if (k != n || !this.l(try1.E())) {
                                int n18 = 0;
                                int n19 = 0;
                                final new new1 = new new();
                                if (b && n8 != -1) {
                                    int day;
                                    int year;
                                    int month = year = (day = 0);
                                    final String a2 = try1.a(n8);
                                    final int int1 = Integer.parseInt(a2.substring(n2, n3 + 1));
                                    final int int2 = Integer.parseInt(a2.substring(n4, n5 + 1));
                                    final int int3 = Integer.parseInt(a2.substring(n6, n7 + 1));
                                    int int4 = -1;
                                    if (a2.length() > n7 + 1) {
                                        int4 = Integer.parseInt(a2.substring(n7 + 2));
                                    }
                                    if (int4 > 999) {
                                        int4 = 999;
                                    }
                                    n19 = int1 + int2 + int3 + ((int4 < 0) ? 0 : int4);
                                    if (n9 != -1) {
                                        null.v(try1.a(n9));
                                        year = null.getYear();
                                        final int n20 = n18 + year;
                                        month = null.getMonth();
                                        final int n21 = n20 + month;
                                        day = null.getDay();
                                        n18 = n21 + day;
                                    }
                                    if (int4 < 0) {
                                        int4 = 0;
                                    }
                                    this.Xpa.b(year, month, day, int1, int2, int3, int4);
                                    new1.Lpa = this.Xpa._();
                                    b5 = true;
                                }
                                else if (!b && n9 != -1) {
                                    null.v(try1.a(n9));
                                    final int year2 = null.getYear();
                                    final int n22 = n18 + year2;
                                    final int month2 = null.getMonth();
                                    final int n23 = n22 + month2;
                                    final int day2 = null.getDay();
                                    n18 = n23 + day2;
                                    this.Xpa.set(year2, month2, day2);
                                    new1.Lpa = this.Xpa._();
                                    b5 = true;
                                }
                                if (b5) {
                                    if (n17 != 0) {
                                        this.Zpa = 0;
                                        if (n18 == 0 && n19 == 0) {
                                            this.z.removeAllElements();
                                            continue;
                                        }
                                    }
                                    String a3 = try1.a(n10);
                                    if (a3 == null || a3.length() == 0) {
                                        a3 = "0";
                                    }
                                    new1.kpa = Float.valueOf(a3);
                                    if (n17 != 0) {
                                        this.w(a3);
                                    }
                                    String a4 = try1.a(n12);
                                    if (a4 == null || a4.length() == 0) {
                                        a4 = "0";
                                    }
                                    new1.mpa = Float.valueOf(a4);
                                    if (n17 != 0) {
                                        this.w(a4);
                                    }
                                    String a5 = try1.a(n11);
                                    if (a5 == null || a5.length() == 0) {
                                        a5 = "0";
                                    }
                                    new1.lpa = Float.valueOf(a5);
                                    if (n17 != 0) {
                                        this.w(a5);
                                    }
                                    String a6 = try1.a(n13);
                                    if (a6 == null || a6.length() == 0) {
                                        a6 = "0";
                                    }
                                    new1.npa = Float.valueOf(a6);
                                    if (n17 != 0) {
                                        this.w(a6);
                                    }
                                    String a7 = try1.a(n14);
                                    if (a7 == null || a7.length() == 0) {
                                        a7 = "0";
                                    }
                                    new1.opa = Double.valueOf(a7);
                                    String a8 = try1.a(n15);
                                    if (a8 == null || a8.length() == 0) {
                                        a8 = "0";
                                    }
                                    new1.ppa = Float.valueOf(a8);
                                    String a9 = try1.a(n16);
                                    if (a9 == null || a9.length() == 0) {
                                        a9 = "0";
                                    }
                                    new1.info = Float.valueOf(a9);
                                    if (new1.npa < Float.MIN_VALUE) {
                                        if (new1.kpa > 0.0f) {
                                            new1.npa = new1.kpa;
                                        }
                                        else if (new1.lpa > 0.0f) {
                                            new1.npa = new1.lpa;
                                        }
                                        else if (new1.mpa > 0.0f) {
                                            new1.npa = new1.mpa;
                                        }
                                    }
                                    if (new1.kpa < Float.MIN_VALUE) {
                                        if (new1.npa > 0.0f) {
                                            new1.kpa = new1.npa;
                                        }
                                        else if (new1.lpa > 0.0f) {
                                            new1.kpa = new1.lpa;
                                        }
                                        else if (new1.mpa > 0.0f) {
                                            new1.kpa = new1.mpa;
                                        }
                                    }
                                    if (new1.mpa < Float.MIN_VALUE) {
                                        new1.mpa = Math.min(new1.kpa, new1.npa);
                                    }
                                    if (new1.lpa < Float.MIN_VALUE) {
                                        new1.lpa = Math.max(new1.kpa, new1.npa);
                                    }
                                    if (new1.mpa > new1.lpa) {
                                        final float mpa = new1.mpa;
                                        new1.mpa = new1.lpa;
                                        new1.lpa = mpa;
                                    }
                                    if (new1.npa > 0.0f) {
                                        n17 = 0;
                                        this.b(new1);
                                    }
                                    else if (new1.Lpa > 0L) {
                                        this._(new1);
                                    }
                                    else if (new1.npa < 0.0f) {
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
    
    private void _(final new new1) {
        final int b = this.b(new1.Lpa);
        if (b >= 0) {
            this.z.removeElementAt(b);
        }
    }
    
    private void b(final new new1) {
        final int b = this.b(new1.Lpa);
        if (b >= 0) {
            this.z.setElementAt(new1, b);
        }
        else {
            this.z.insertElementAt(new1, -b - 1);
        }
    }
    
    private int b(final long n) {
        int i = 0;
        int n2 = this.z.size() - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final long lpa = this.z.elementAt(n3).Lpa;
            if (lpa < n) {
                i = n3 + 1;
            }
            else {
                if (lpa <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
    
    private boolean l(String upperCase) {
        if (upperCase == null) {
            return false;
        }
        if (upperCase.startsWith("<") && upperCase.endsWith(">")) {
            return true;
        }
        upperCase = upperCase.toUpperCase();
        return upperCase.indexOf(44) != -1 && upperCase.indexOf("CLOSE") != -1 && (upperCase.indexOf("DATE") != -1 || upperCase.indexOf("TIME") != -1 || upperCase.indexOf("<DT") != -1);
    }
    
    private boolean m(final String s) {
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
    
    public synchronized boolean a(final this this2, final boolean b, final String s, final int ypa) {
        if (this2 == null) {
            return this.e();
        }
        if (this2.K() == 0) {
            return this.e();
        }
        this.Ypa = ypa;
        int n;
        for (n = 0; n < this2.K() && this.m(this2.b(n)); ++n) {}
        if (n >= this2.K()) {
            return this.e();
        }
        return this.l(this2.b(n)) && this._(n, this2, b);
    }
    
    public synchronized double[][] a(final boolean b, final int n) {
        this.Wpa = System.currentTimeMillis();
        if (this.z == null || this.z.size() < 1) {
            return new double[][] { null, null, null, null, null, null, null, null };
        }
        int n2 = 0;
        if (n > 0) {
            if (b) {
                final int size = this.z.size();
                final Vector vector = new Vector<Long>(30);
                long n3 = -1L;
                for (int i = 0; i < size; ++i) {
                    final long n4 = this.z.elementAt(i).Lpa / 1000000000L * 1000000000L;
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
                    this.Xpa.b(this.l());
                    this.Xpa.set(this.Xpa.getYear(), this.Xpa.getMonth(), this.Xpa.getDay());
                    n5 = this.Xpa._();
                }
                n2 = this.b(n5);
            }
            else {
                this.Xpa.b(this.l());
                final GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(this.Xpa.getYear(), this.Xpa.getMonth() - 1, this.Xpa.getDay());
                if (b) {
                    gregorianCalendar.add(5, -n);
                }
                else {
                    gregorianCalendar.add(1, -n);
                }
                this.Xpa.b(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5), 0, 0, 0, 0);
                n2 = this.b(this.Xpa._());
            }
            if (n2 < 0) {
                n2 = -n2 - 1;
            }
            if (n2 >= this.z.size()) {
                n2 = this.z.size() - 1;
            }
        }
        final int n6 = this.z.size() - n2;
        final double[] array = new double[n6];
        final double[] array2 = new double[n6];
        final double[] array3 = new double[n6];
        final double[] array4 = new double[n6];
        final double[] array5 = new double[n6];
        final double[] array6 = new double[n6];
        final double[] array7 = new double[n6];
        final double[] array8 = new double[n6];
        int n7 = 0;
        for (int size2 = this.z.size(), j = n2; j < size2; ++j) {
            final new new1 = this.z.elementAt(j);
            this.Xpa.b(new1.Lpa);
            if (b) {
                this._qa.b(this.Xpa.getYear(), this.Xpa.getMonth(), this.Xpa.getDay(), this.Xpa.M(), this.Xpa.O(), this.Xpa.P(), this.Xpa.N());
                array[n7] = this._qa.l();
            }
            else {
                this.aqa.set(this.Xpa.getYear(), this.Xpa.getMonth(), this.Xpa.getDay());
                array[n7] = this.aqa.l();
            }
            array2[n7] = new1.kpa;
            array3[n7] = new1.lpa;
            array4[n7] = new1.mpa;
            array5[n7] = new1.npa;
            array6[n7] = new1.opa;
            array7[n7] = new1.ppa;
            array8[n7] = new1.info;
            ++n7;
        }
        return new double[][] { array, array2, array3, array4, array5, array6, array7, array8 };
    }
}
