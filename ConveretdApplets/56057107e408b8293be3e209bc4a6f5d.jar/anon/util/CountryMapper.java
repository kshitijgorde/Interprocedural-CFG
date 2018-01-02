// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Vector;
import java.util.Locale;

public class CountryMapper extends AbstractISOCodeMapper
{
    private static final String[] DEFAULT_COUNTRIES;
    private static final String MSG_CHOOSE_COUNTRY;
    private static final String[] COUNTRIES;
    static /* synthetic */ Class class$anon$util$CountryMapper;
    static /* synthetic */ Class class$java$util$Locale;
    
    public CountryMapper() {
    }
    
    public CountryMapper(final int n) {
        super(n);
    }
    
    public CountryMapper(final String s, final int n) throws IllegalArgumentException {
        super(s, n);
    }
    
    public CountryMapper(final String s) throws IllegalArgumentException {
        super(s);
    }
    
    public CountryMapper(final String s, final Locale locale) throws IllegalArgumentException {
        super(s, locale);
    }
    
    public CountryMapper(final String s, final int n, final Locale locale) throws IllegalArgumentException {
        super(s, n, locale);
    }
    
    public static Vector getLocalisedCountries() {
        return getLocalisedCountries(0, null);
    }
    
    public static Vector getLocalisedCountries(final Locale locale) {
        return getLocalisedCountries(0, locale);
    }
    
    public static Vector getLocalisedCountries(final int n) {
        return getLocalisedCountries(n, null);
    }
    
    public static Vector getLocalisedCountries(final int n, final Locale locale) {
        final Vector<CountryMapper> vector = new Vector<CountryMapper>();
        for (int i = 0; i < CountryMapper.COUNTRIES.length; ++i) {
            vector.addElement(new CountryMapper(CountryMapper.COUNTRIES[i], n, locale));
        }
        return Util.sortStrings(vector);
    }
    
    protected final String getChooseMessage() {
        return JAPMessages.getString(CountryMapper.MSG_CHOOSE_COUNTRY);
    }
    
    protected String getJRETransaltionOfISOCode(final String s, final Locale locale) {
        return new Locale(locale.getLanguage(), s).getDisplayCountry(locale);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        DEFAULT_COUNTRIES = new String[] { "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BM", "BN", "BO", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CS", "CU", "CV", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "GA", "GB", "FM", "FO", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "GD", "GE", "GF", "GH", "GI", "GL", "GM", "GN", "HM", "HN", "HR", "HT", "HU", "IQ", "IR", "IS", "IT", "LR", "LS", "LT", "LU", "LV", "HK", "ID", "IE", "IL", "IN", "IO", "KE", "KG", "KH", "KI", "JM", "JO", "JP", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LY", "MA", "MC", "MD", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "PA", "PE", "PF", "PG", "PH", "OM", "PK", "PL", "PM", "PN", "RU", "RW", "SV", "PR", "PS", "PT", "QA", "RE", "PW", "PY", "SY", "SZ", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "RO", "SR", "ST", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TR", "TT", "TV", "TW", "TZ", "UG", "UA", "UY", "UZ", "UM", "US", "VI", "VN", "ZM", "YT", "VU", "VA", "VC", "VE", "VG", "WF", "WS", "ZA", "YE", "ZW" };
        MSG_CHOOSE_COUNTRY = ((CountryMapper.class$anon$util$CountryMapper == null) ? (CountryMapper.class$anon$util$CountryMapper = class$("anon.util.CountryMapper")) : CountryMapper.class$anon$util$CountryMapper).getName() + "_ChooseCountry";
        String[] countries = null;
        try {
            countries = (String[])((CountryMapper.class$java$util$Locale == null) ? (CountryMapper.class$java$util$Locale = class$("java.util.Locale")) : CountryMapper.class$java$util$Locale).getMethod("getISOCountries", (Class[])null).invoke((CountryMapper.class$java$util$Locale == null) ? (CountryMapper.class$java$util$Locale = class$("java.util.Locale")) : CountryMapper.class$java$util$Locale, (Object[])null);
        }
        catch (Exception ex) {}
        if (countries == null || countries.length < CountryMapper.DEFAULT_COUNTRIES.length) {
            COUNTRIES = CountryMapper.DEFAULT_COUNTRIES;
        }
        else {
            COUNTRIES = countries;
        }
    }
}
