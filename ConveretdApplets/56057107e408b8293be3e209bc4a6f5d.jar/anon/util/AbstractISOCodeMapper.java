// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Locale;

public abstract class AbstractISOCodeMapper
{
    private final int MAX_LENGTH;
    private boolean m_bUseDefaultLocale;
    private String m_iso2;
    private Locale m_locale;
    
    public AbstractISOCodeMapper() {
        this(null, 0);
    }
    
    public AbstractISOCodeMapper(final int n) {
        this(null, n);
    }
    
    public AbstractISOCodeMapper(final String s, final int n) throws IllegalArgumentException {
        this(s, n, null);
    }
    
    public AbstractISOCodeMapper(final String s) throws IllegalArgumentException {
        this(s, 0, null);
    }
    
    public AbstractISOCodeMapper(final String s, final Locale locale) throws IllegalArgumentException {
        this(s, 0, locale);
    }
    
    public AbstractISOCodeMapper(String s, final int max_LENGTH, final Locale locale) throws IllegalArgumentException {
        this.MAX_LENGTH = max_LENGTH;
        if (s == null || s.trim().length() == 0) {
            s = "";
        }
        if (s.length() > 0 && s.length() != 2) {
            throw new IllegalArgumentException("Mapped ISO code must have a length of two characters!");
        }
        this.m_iso2 = s.trim().toUpperCase();
        if (locale == null) {
            this.m_bUseDefaultLocale = true;
            this.m_locale = JAPMessages.getLocale();
        }
        else {
            this.m_bUseDefaultLocale = false;
            this.m_locale = locale;
        }
    }
    
    public final String getISOCode() {
        return this.m_iso2.toLowerCase();
    }
    
    public final boolean equals(final Object o) {
        return o != null && o instanceof AbstractISOCodeMapper && this.getISOCode().equals(((AbstractISOCodeMapper)o).getISOCode());
    }
    
    public final int hashCode() {
        return this.getISOCode().hashCode();
    }
    
    protected abstract String getChooseMessage();
    
    protected abstract String getJRETransaltionOfISOCode(final String p0, final Locale p1);
    
    public final String toString() {
        String s;
        if (this.m_iso2.length() == 0) {
            s = this.getChooseMessage();
        }
        else {
            Locale locale;
            if (this.m_bUseDefaultLocale) {
                locale = JAPMessages.getLocale();
            }
            else {
                locale = this.m_locale;
            }
            s = this.getJRETransaltionOfISOCode(this.m_iso2, locale);
            if (s == null || s.trim().length() == 0 || s.equals(this.m_iso2) || (s.equals(this.getJRETransaltionOfISOCode("AA", locale)) && s.equals(this.getJRETransaltionOfISOCode("ZZ", locale)))) {
                final String string = this.getClass().getName() + "_" + this.m_iso2;
                s = JAPMessages.getString(string);
                if (s.equals(string)) {
                    s = this.m_iso2;
                }
            }
        }
        if (this.MAX_LENGTH > 0 && s.length() > this.MAX_LENGTH) {
            s = s.substring(0, this.MAX_LENGTH);
        }
        if (s != null && s.length() > 1) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
        }
        return s;
    }
}
