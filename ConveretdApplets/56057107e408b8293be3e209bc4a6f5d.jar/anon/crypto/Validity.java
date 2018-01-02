// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Date;
import java.util.Calendar;

public class Validity
{
    private Calendar m_validFrom;
    private Calendar m_validTo;
    private static final int TEMPORARY_VALIDITY_IN_MINUTES = 10;
    
    public Validity(final Calendar calendar, final int n) {
        this(calendar, createValidTo(calendar, n));
    }
    
    public Validity(final Calendar calendar, final Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("Calendars for validity must not be null!");
        }
        this.m_validFrom = (Calendar)calendar.clone();
        if (calendar2.before(calendar)) {
            this.m_validTo = this.m_validFrom;
        }
        else {
            this.m_validTo = (Calendar)calendar2.clone();
        }
    }
    
    public Date getValidFrom() {
        return this.m_validFrom.getTime();
    }
    
    public Date getValidTo() {
        return this.m_validTo.getTime();
    }
    
    public boolean isValid(final Date date) {
        return !date.before(this.getValidFrom()) && !date.after(this.getValidTo());
    }
    
    private static Calendar createValidTo(final Calendar calendar, final int n) {
        if (calendar == null) {
            return null;
        }
        final Calendar calendar2 = (Calendar)calendar.clone();
        if (n < 0) {
            calendar2.add(12, 10);
        }
        else {
            calendar2.add(1, n);
        }
        return calendar2;
    }
}
