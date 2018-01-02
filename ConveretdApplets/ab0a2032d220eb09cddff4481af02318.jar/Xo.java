import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class Xo
{
    private SimpleDateFormat QHb;
    private GregorianCalendar calendar;
    private Date RHb;
    private boolean SHb;
    private static final int THb = 1968;
    private static final int UHb = 1;
    private static final int VHb = 1;
    private static final int WHb = 0;
    private int PGb;
    private int OGb;
    private int NGb;
    private int[] XHb;
    private int[] YHb;
    
    public Xo(final int n, final int n2, final int n3) {
        this.QHb = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.RHb = new Date();
        this.SHb = true;
        this.XHb = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.YHb = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.set(n, n2, n3);
    }
    
    public Xo(final double n) {
        this.QHb = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.RHb = new Date();
        this.SHb = true;
        this.XHb = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.YHb = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this._(n);
    }
    
    public Xo() {
        this.QHb = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.RHb = new Date();
        this.SHb = true;
        this.XHb = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.YHb = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.set(1968, 1, 1);
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return n >= 1900 && n <= 2100 && n2 >= 1 && n2 <= 12 && n3 >= 1 && n3 <= this.g(n2, n) && (!this.SHb || this.b(n, n2, n3) < 5);
    }
    
    public int b(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 1968; i < n; ++i) {
            n4 += k(i);
        }
        for (int j = 1; j < n2; ++j) {
            n4 += this.g(j, n);
        }
        return (n4 + (n3 - 1)) % 7;
    }
    
    public int g(final int n, final int n2) {
        if (isLeapYear(n2)) {
            return this.YHb[n];
        }
        return this.XHb[n];
    }
    
    public static int k(final int n) {
        if (isLeapYear(n)) {
            return 366;
        }
        return 365;
    }
    
    public static boolean isLeapYear(final int n) {
        return n % 400 == 0 || (n % 4 == 0 && n % 100 != 0);
    }
    
    public void set(final int pGb, final int oGb, final int nGb) {
        if (this.b(pGb, oGb, nGb)) {
            this.PGb = pGb;
            this.OGb = oGb;
            this.NGb = nGb;
        }
        else {
            this.PGb = 1968;
            this.OGb = 1;
            this.NGb = 1;
        }
    }
    
    public void E(final boolean sHb) {
        this.SHb = sHb;
        if (!this.b(this.PGb, this.OGb, this.NGb)) {
            this.set(this.PGb, this.OGb, this.NGb);
        }
    }
    
    public void c(final String s) {
        synchronized (this.QHb) {
            this.QHb.applyPattern(s);
        }
    }
    
    public double m() {
        return this.a(this.PGb, this.OGb, this.NGb);
    }
    
    public double a(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 1968; i < n; ++i) {
            n4 += k(i);
        }
        for (int j = 1; j < n2; ++j) {
            n4 += this.g(j, n);
        }
        int n5 = n4 + (n3 - 1);
        if (this.SHb) {
            n5 -= n5 / 7 * 2;
            final int b = this.b(n, n2, n3);
            if (b >= 5) {
                n5 -= b - 4;
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
        if (this.SHb) {
            n4 += n4 / 5 * 2;
        }
        int n5;
        for (n5 = 1968; n3 + k(n5) <= n4; n3 += k(n5), ++n5) {}
        int n6;
        for (n6 = 1; n3 + this.g(n6, n5) <= n4; n3 += this.g(n6, n5), ++n6) {}
        this.set(n5, n6, n2 + (n4 - n3 + 1));
    }
    
    public String toString() {
        return this.a(this.PGb, this.OGb, this.NGb);
    }
    
    public String a(final int n, final int n2, final int n3) {
        if (this.b(n, n2, n3)) {
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
        synchronized (this.QHb) {
            format = this.QHb.format(this.calendar.getTime());
        }
        return format;
    }
    
    public String b(final double n) {
        final int n2 = 0;
        int n3 = 0;
        int n4 = (int)Math.round(n);
        if (this.SHb) {
            n4 += n4 / 5 * 2;
        }
        int n5;
        for (n5 = 1968; n3 + k(n5) <= n4; n3 += k(n5), ++n5) {}
        int n6;
        for (n6 = 1; n3 + this.g(n6, n5) <= n4; n3 += this.g(n6, n5), ++n6) {}
        return this.a(n5, n6, n2 + (n4 - n3 + 1));
    }
    
    public void e() {
        if (this.OGb != 1 || !this.V()) {
            ++this.PGb;
            this.OGb = 1;
            this.NGb = 1;
            if (this.SHb) {
                while (this.b(this.PGb, this.OGb, this.NGb) >= 5) {
                    ++this.NGb;
                }
            }
        }
    }
    
    public void f() {
        if (!this.V()) {
            if (this.OGb == 12) {
                ++this.PGb;
                this.OGb = 1;
            }
            else {
                ++this.OGb;
            }
            this.NGb = 1;
            if (this.SHb) {
                while (this.b(this.PGb, this.OGb, this.NGb) >= 5) {
                    ++this.NGb;
                }
            }
        }
    }
    
    private boolean V() {
        if (!this.SHb) {
            return this.NGb == 1;
        }
        final int b = this.b(this.PGb, this.OGb, this.NGb);
        int n;
        if (b >= 5) {
            n = b - 4;
        }
        else {
            n = 0;
        }
        return this.NGb - n <= 1;
    }
    
    public int m() {
        return this.PGb * 12 + this.OGb;
    }
    
    public int l() {
        return this.PGb;
    }
    
    public boolean W() {
        final int g = this.g(this.OGb, this.PGb);
        return this.NGb == g || this.NGb + Math.max(0, this.b(this.PGb, this.OGb, this.NGb) - 4) == g;
    }
    
    public boolean X() {
        return this.b(this.PGb, this.OGb, this.NGb) >= 4;
    }
    
    public int R() {
        return this.SHb ? ((int)this.m() / 5) : ((int)this.m() / 7);
    }
    
    public int getDay() {
        return this.NGb;
    }
    
    public int getMonth() {
        return this.OGb;
    }
    
    public int getYear() {
        return this.PGb;
    }
}
