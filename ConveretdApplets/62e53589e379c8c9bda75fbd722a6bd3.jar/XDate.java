import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class XDate
{
    private final int BASE_YEAR = 1900;
    private final int BASE_DAY = 1;
    private final int[] nDAYSXMONTH;
    protected int m_nYear;
    protected int m_nDate;
    protected int m_nMonth;
    
    public XDate() {
        this.nDAYSXMONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        final Date date = new Date();
        this.m_nYear = date.getYear();
        this.m_nMonth = date.getMonth();
        this.m_nDate = date.getDate();
    }
    
    public XDate(final int nYear, final int nMonth, final int nDate) {
        this.nDAYSXMONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.m_nYear = nYear;
        this.m_nMonth = nMonth;
        this.m_nDate = nDate;
        this.adjustDate();
    }
    
    public XDate(final XDate xDate) {
        this.nDAYSXMONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.m_nYear = xDate.m_nYear;
        this.m_nMonth = xDate.m_nMonth;
        this.m_nDate = xDate.m_nDate;
    }
    
    public int getDate() {
        return this.m_nDate;
    }
    
    public int getMonth() {
        return this.m_nMonth;
    }
    
    public int getYear() {
        return this.m_nYear;
    }
    
    public void setDate(final int nDate) {
        this.m_nDate = nDate;
        this.adjustDate();
    }
    
    public void setMonth(final int nMonth) {
        this.m_nMonth = nMonth;
        this.adjustDate();
    }
    
    public void setYear(final int nYear) {
        this.m_nYear = nYear;
        this.adjustDate();
    }
    
    public long getPastDays() {
        long n = 0L;
        if (this.m_nYear > 1) {
            n = (this.m_nYear - 1) * 365 + (this.m_nYear - 1) / 4 + ((this.m_nYear == 100 && this.m_nMonth > 1) ? 1 : 0);
        }
        for (int i = 0; i < this.m_nMonth; ++i) {
            n += this.nDAYSXMONTH[i];
        }
        final int nYear = this.m_nYear;
        return n + (this.m_nDate + ((nYear % 4 == 0 && (nYear % 100 != 0 || nYear % 400 == 0 || false) && this.m_nMonth > 1) ? 1 : 0));
    }
    
    public static boolean isLeapYear(final int n) {
        return n % 4 == 0 && (n % 100 != 0 || n % 400 == 0);
    }
    
    public int getDayOfWeek() {
        int n = (int)(this.getPastDays() % 7L) + 1;
        if (n == 7) {
            n = 0;
        }
        return n;
    }
    
    private void adjustDate() {
        final int n = (this.m_nYear % 4 == 0 && this.m_nMonth == 1) ? 1 : 0;
        if (this.m_nMonth > 11) {
            this.m_nMonth -= 12;
            ++this.m_nYear;
            this.adjustDate();
        }
        else if (this.m_nMonth < 0) {
            if (this.m_nYear == 0) {
                throw new IllegalArgumentException();
            }
            --this.m_nYear;
            this.m_nMonth = 11 + this.m_nMonth + 1;
            this.adjustDate();
        }
        if (this.m_nDate > this.nDAYSXMONTH[this.m_nMonth] + n) {
            this.m_nDate -= this.nDAYSXMONTH[this.m_nMonth] + n;
            ++this.m_nMonth;
            this.adjustDate();
            return;
        }
        if (this.m_nDate < 0) {
            --this.m_nMonth;
            this.m_nDate = this.nDAYSXMONTH[this.m_nMonth] + this.m_nDate + n + 1;
            this.adjustDate();
        }
    }
}
