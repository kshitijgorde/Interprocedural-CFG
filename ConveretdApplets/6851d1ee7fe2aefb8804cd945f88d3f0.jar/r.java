import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    private SimpleDateFormat usa;
    private GregorianCalendar calendar;
    private static boolean vsa;
    private static boolean wsa;
    private static final int xsa = 1968;
    private static final int ysa = 1;
    private static final int zsa = 1;
    private int _a;
    private int Z;
    private int Y;
    private int[] Asa;
    private int[] Bsa;
    
    public r(final int n, final int n2, final int n3) {
        this.usa = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.Asa = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.Bsa = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        r.vsa = false;
        this.set(n, n2, n3);
    }
    
    public r(final double n) {
        this.usa = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.Asa = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.Bsa = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        r.vsa = false;
        this._(n);
    }
    
    public r() {
        this.usa = new SimpleDateFormat("dd-MMM-yyyy");
        this.calendar = new GregorianCalendar();
        this.Asa = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.Bsa = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        r.vsa = false;
        this.set(1968, 1, 1);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n >= 1900 && n <= 2100 && n2 >= 1 && n2 <= 12 && n3 >= 1 && n3 <= this.c(n2, n) && (!r.wsa || this._(n, n2, n3) < 5);
    }
    
    public int _(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 1968; i < n; ++i) {
            n4 += c(i);
        }
        for (int j = 1; j < n2; ++j) {
            n4 += this.c(j, n);
        }
        return (n4 + (n3 - 1)) % 7;
    }
    
    public int c(final int n, final int n2) {
        if (isLeapYear(n2)) {
            return this.Bsa[n];
        }
        return this.Asa[n];
    }
    
    public static int c(final int n) {
        if (isLeapYear(n)) {
            return 366;
        }
        return 365;
    }
    
    public static boolean isLeapYear(final int n) {
        return n % 400 == 0 || (n % 4 == 0 && n % 100 != 0);
    }
    
    public void set(final int a, final int z, final int y) {
        if (this.a(a, z, y)) {
            this._a = a;
            this.Z = z;
            this.Y = y;
        }
        else {
            this._a = 1968;
            this.Z = 1;
            this.Y = 1;
        }
    }
    
    public void P(final boolean wsa) {
        r.wsa = wsa;
        if (!this.a(this._a, this.Z, this.Y)) {
            this.set(this._a, this.Z, this.Y);
        }
    }
    
    public void x(final String s) {
        synchronized (this.usa) {
            this.usa.applyPattern(s);
        }
    }
    
    public double _() {
        return this.a(this._a, this.Z, this.Y);
    }
    
    public double a(final int n, final int n2, final int n3) {
        int n4 = 0;
        for (int i = 1968; i < n; ++i) {
            n4 += c(i);
        }
        for (int j = 1; j < n2; ++j) {
            n4 += this.c(j, n);
        }
        int n5 = n4 + (n3 - 1);
        if (r.wsa) {
            n5 -= n5 / 7 * 2;
            final int _ = this._(n, n2, n3);
            if (_ >= 5) {
                n5 -= _ - 4;
            }
        }
        return n5;
    }
    
    public int a(final double n, final double n2) {
        return Math.round(Math.round(n) - Math.round(n2));
    }
    
    public void _(final double n) {
        final int n2 = 0;
        int n3 = 0;
        int n4 = (int)Math.round(n);
        if (r.wsa) {
            n4 += n4 / 5 * 2;
        }
        int n5;
        for (n5 = 1968; n3 + c(n5) <= n4; n3 += c(n5), ++n5) {}
        int n6;
        for (n6 = 1; n3 + this.c(n6, n5) <= n4; n3 += this.c(n6, n5), ++n6) {}
        this.set(n5, n6, n2 + (n4 - n3 + 1));
    }
    
    public String toString() {
        return this._(this._a, this.Z, this.Y);
    }
    
    public String _(final int n, final int n2, final int n3) {
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
        synchronized (this.usa) {
            format = this.usa.format(this.calendar.getTime());
        }
        return format;
    }
    
    public String f(final double n) {
        final int n2 = 0;
        int n3 = 0;
        int n4 = (int)Math.round(n);
        if (r.wsa) {
            n4 += n4 / 5 * 2;
        }
        int n5;
        for (n5 = 1968; n3 + c(n5) <= n4; n3 += c(n5), ++n5) {}
        int n6;
        for (n6 = 1; n3 + this.c(n6, n5) <= n4; n3 += this.c(n6, n5), ++n6) {}
        return this._(n5, n6, n2 + (n4 - n3 + 1));
    }
    
    public void ha() {
        if (this.Z != 1 || !this.Z()) {
            ++this._a;
            this.Z = 1;
            this.Y = 1;
            if (r.wsa) {
                while (this._(this._a, this.Z, this.Y) >= 5) {
                    ++this.Y;
                }
            }
        }
    }
    
    public void ia() {
        if (!this.Z()) {
            if (this.Z == 12) {
                ++this._a;
                this.Z = 1;
            }
            else {
                ++this.Z;
            }
            this.Y = 1;
            if (r.wsa) {
                while (this._(this._a, this.Z, this.Y) >= 5) {
                    ++this.Y;
                }
            }
        }
    }
    
    private boolean Z() {
        if (!r.wsa) {
            return this.Y == 1;
        }
        final int _ = this._(this._a, this.Z, this.Y);
        int n;
        if (_ >= 5) {
            n = _ - 4;
        }
        else {
            n = 0;
        }
        return this.Y - n <= 1;
    }
    
    public void Q(final int n) {
        final int n2 = n / 12;
        final int n3 = n % 12;
        this._a += n2;
        this.Z += n3;
        if (n >= 0) {
            if (this.Z > 12) {
                ++this._a;
                this.Z -= 12;
            }
        }
        else {
            ++this.Y;
            if (this.Z < 1) {
                --this._a;
                this.Z += 12;
            }
        }
        if (this._a < 1968 && this.Z < 1 && this.Y < 1) {
            this._a = 1968;
            this.Z = 1;
            this.Y = 1;
        }
        else {
            if (this.Y > this.c(this.Z, this._a)) {
                this.Y -= this.c(this.Z, this._a);
                if (this.Z < 12) {
                    ++this.Z;
                }
                else {
                    ++this._a;
                    this.Z = 1;
                }
            }
            if (r.wsa) {
                while (this._(this._a, this.Z, this.Y) >= 5) {
                    ++this.Y;
                }
            }
        }
    }
    
    public int p() {
        return this._a * 12 + this.Z;
    }
    
    public int o() {
        return this._a;
    }
    
    public boolean e() {
        final int c = this.c(this.Z, this._a);
        return this.Y == c || this.Y + Math.max(0, this._(this._a, this.Z, this.Y) - 4) == c;
    }
    
    public boolean f() {
        return this._(this._a, this.Z, this.Y) >= 4;
    }
    
    public int z() {
        return r.wsa ? ((int)this._() / 5) : ((int)this._() / 7);
    }
    
    public int getDay() {
        return this.Y;
    }
    
    public int getMonth() {
        return this.Z;
    }
    
    public int getYear() {
        return this._a;
    }
    
    public static void Q(final boolean b) {
        if (r.vsa) {
            r.wsa = !b;
        }
    }
    
    static {
        r.vsa = true;
        r.wsa = true;
    }
}
