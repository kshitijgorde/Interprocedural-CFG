import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class throw
{
    private SimpleDateFormat Hwa;
    private GregorianCalendar calendar;
    private static boolean Iwa;
    private static boolean Jwa;
    private static final int Kwa = 1968;
    private static final int Lwa = 1;
    private static final int Mwa = 1;
    private int zpa;
    private int ypa;
    private int xpa;
    private int[] Nwa;
    private int[] Owa;
    
    public throw(final int n, final int n2, final int n3) {
        this.Hwa = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.Nwa = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.Owa = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        throw.Iwa = false;
        this.set(n, n2, n3);
    }
    
    public throw(final double n) {
        this.Hwa = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.Nwa = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.Owa = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        throw.Iwa = false;
        this._(n);
    }
    
    public throw() {
        this.Hwa = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.Nwa = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.Owa = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        throw.Iwa = false;
        this.set(1968, 1, 1);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n >= 1900 && n <= 2100 && n2 >= 1 && n2 <= 12 && n3 >= 1 && n3 <= this.e(n2, n) && (!throw.Jwa || this.a(n, n2, n3) < 5);
    }
    
    public int a(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 1968; i < n; ++i) {
            n4 += h(i);
        }
        for (int j = 1; j < n2; ++j) {
            n4 += this.e(j, n);
        }
        return (n4 + (n3 - 1)) % 7;
    }
    
    public int e(final int n, final int n2) {
        if (isLeapYear(n2)) {
            return this.Owa[n];
        }
        return this.Nwa[n];
    }
    
    public static int h(final int n) {
        if (isLeapYear(n)) {
            return 366;
        }
        return 365;
    }
    
    public static boolean isLeapYear(final int n) {
        return n % 400 == 0 || (n % 4 == 0 && n % 100 != 0);
    }
    
    public void set(final int zpa, final int ypa, final int xpa) {
        if (this.a(zpa, ypa, xpa)) {
            this.zpa = zpa;
            this.ypa = ypa;
            this.xpa = xpa;
        }
        else {
            this.zpa = 1968;
            this.ypa = 1;
            this.xpa = 1;
        }
    }
    
    public void V(final boolean jwa) {
        throw.Jwa = jwa;
        if (!this.a(this.zpa, this.ypa, this.xpa)) {
            this.set(this.zpa, this.ypa, this.xpa);
        }
    }
    
    public void x(final String s) {
        synchronized (this.Hwa) {
            this.Hwa.applyPattern(s);
        }
    }
    
    public double l() {
        return this.b(this.zpa, this.ypa, this.xpa);
    }
    
    public double b(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 1968; i < n; ++i) {
            n4 += h(i);
        }
        for (int j = 1; j < n2; ++j) {
            n4 += this.e(j, n);
        }
        int n5 = n4 + (n3 - 1);
        if (throw.Jwa) {
            n5 -= n5 / 7 * 2;
            final int a = this.a(n, n2, n3);
            if (a >= 5) {
                n5 -= a - 4;
            }
        }
        return Math.round(n5);
    }
    
    public int _(final double n, final double n2) {
        return Math.round(Math.round(n) - Math.round(n2));
    }
    
    public void _(final double n) {
        final int n2 = 0;
        int n3 = 0;
        int n4 = (int)Math.round(n);
        if (throw.Jwa) {
            n4 += n4 / 5 * 2;
        }
        int n5;
        for (n5 = 1968; n3 + h(n5) <= n4; n3 += h(n5), ++n5) {}
        int n6;
        for (n6 = 1; n3 + this.e(n6, n5) <= n4; n3 += this.e(n6, n5), ++n6) {}
        this.set(n5, n6, n2 + (n4 - n3 + 1));
    }
    
    public String toString() {
        return this.b(this.zpa, this.ypa, this.xpa);
    }
    
    public String b(final int n, final int n2, final int n3) {
        if (this.a(n, n2, n3)) {
            this.calendar.set(1, n);
            this.calendar.set(2, n2 - 1);
            this.calendar.set(5, n3);
        }
        else {
            this.calendar.set(1, 1968);
            this.calendar.set(2, 0);
            this.calendar.set(5, 1);
        }
        final String format;
        synchronized (this.Hwa) {
            format = this.Hwa.format(this.calendar.getTime());
        }
        return format;
    }
    
    public String g(final double n) {
        final int n2 = 0;
        int n3 = 0;
        int n4 = (int)Math.round(n);
        if (throw.Jwa) {
            n4 += n4 / 5 * 2;
        }
        int n5;
        for (n5 = 1968; n3 + h(n5) <= n4; n3 += h(n5), ++n5) {}
        int n6;
        for (n6 = 1; n3 + this.e(n6, n5) <= n4; n3 += this.e(n6, n5), ++n6) {}
        return this.b(n5, n6, n2 + (n4 - n3 + 1));
    }
    
    public void Q() {
        if (this.ypa != 1 || !this.S()) {
            ++this.zpa;
            this.ypa = 1;
            this.xpa = 1;
            if (throw.Jwa) {
                while (this.a(this.zpa, this.ypa, this.xpa) >= 5) {
                    ++this.xpa;
                }
            }
        }
    }
    
    public void R() {
        if (!this.S()) {
            if (this.ypa == 12) {
                ++this.zpa;
                this.ypa = 1;
            }
            else {
                ++this.ypa;
            }
            this.xpa = 1;
            if (throw.Jwa) {
                while (this.a(this.zpa, this.ypa, this.xpa) >= 5) {
                    ++this.xpa;
                }
            }
        }
    }
    
    private boolean S() {
        if (!throw.Jwa) {
            return this.xpa == 1;
        }
        final int a = this.a(this.zpa, this.ypa, this.xpa);
        int n;
        if (a >= 5) {
            n = a - 4;
        }
        else {
            n = 0;
        }
        return this.xpa - n <= 1;
    }
    
    public void w(final int n) {
        final int n2 = n / 12;
        final int n3 = n % 12;
        this.zpa += n2;
        this.ypa += n3;
        if (n >= 0) {
            if (this.ypa > 12) {
                ++this.zpa;
                this.ypa -= 12;
            }
        }
        else {
            ++this.xpa;
            if (this.ypa < 1) {
                --this.zpa;
                this.ypa += 12;
            }
        }
        if (this.zpa < 1968 && this.ypa < 1 && this.xpa < 1) {
            this.zpa = 1968;
            this.ypa = 1;
            this.xpa = 1;
        }
        else {
            if (this.xpa > this.e(this.ypa, this.zpa)) {
                this.xpa -= this.e(this.ypa, this.zpa);
                if (this.ypa < 12) {
                    ++this.ypa;
                }
                else {
                    ++this.zpa;
                    this.ypa = 1;
                }
            }
            if (throw.Jwa) {
                while (this.a(this.zpa, this.ypa, this.xpa) >= 5) {
                    ++this.xpa;
                }
            }
        }
    }
    
    public int T() {
        return this.zpa * 12 + this.ypa;
    }
    
    public int S() {
        return this.zpa;
    }
    
    public boolean T() {
        final int e = this.e(this.ypa, this.zpa);
        return this.xpa == e || this.xpa + Math.max(0, this.a(this.zpa, this.ypa, this.xpa) - 4) == e;
    }
    
    public boolean U() {
        return this.a(this.zpa, this.ypa, this.xpa) >= 4;
    }
    
    public int o() {
        return throw.Jwa ? ((int)this.l() / 5) : ((int)this.l() / 7);
    }
    
    public int getDay() {
        return this.xpa;
    }
    
    public int getMonth() {
        return this.ypa;
    }
    
    public int getYear() {
        return this.zpa;
    }
    
    public static void W(final boolean b) {
        if (throw.Iwa) {
            throw.Jwa = !b;
        }
    }
    
    static {
        throw.Iwa = true;
        throw.Jwa = true;
    }
}
