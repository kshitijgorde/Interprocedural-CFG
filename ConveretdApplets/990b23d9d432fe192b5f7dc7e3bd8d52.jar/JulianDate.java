import java.util.Date;
import java.util.GregorianCalendar;

// 
// Decompiled by Procyon v0.5.30
// 

public class JulianDate extends GregorianCalendar
{
    static final long EPOCH = 2440588L;
    static final long DAY = 86400000L;
    long julianOld;
    long julianToday;
    long now;
    
    public JulianDate(final int n, final int n2, final int n3) {
        super(n3, n - 1, n2 + 1);
        this.computeTime();
        this.now = super.time;
    }
    
    public int daysBetween(final long n, final long n2) {
        return (int)(n2 - n);
    }
    
    public int getJulianDay() {
        this.julianOld = 2440588L + this.now / 86400000L;
        return (int)this.julianOld;
    }
    
    public Date julianToDate(long n) {
        System.out.println("Making date with: " + n);
        if (n < 2440588L) {
            --n;
        }
        return new Date(this.julianToMillis(n));
    }
    
    private long julianToMillis(final long n) {
        return (n - 2440588L) * 86400000L;
    }
    
    public int todayToJulian() {
        this.julianToday = 2440588L + System.currentTimeMillis() / 86400000L;
        return (int)this.julianToday;
    }
}
