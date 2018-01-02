// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Vector;
import java.util.Locale;

public class LanguageMapper extends AbstractISOCodeMapper
{
    private static final String[] ms_languageCodes;
    private static final String MSG_CHOOSE_LANGUAGE;
    private Locale m_locale;
    static /* synthetic */ Class class$anon$util$LanguageMapper;
    
    public LanguageMapper() {
        this.createLocale();
    }
    
    public LanguageMapper(final int n) {
        super(n);
        this.createLocale();
    }
    
    public LanguageMapper(final String s, final int n) throws IllegalArgumentException {
        super(s, n);
        this.createLocale();
    }
    
    public LanguageMapper(final String s) throws IllegalArgumentException {
        super(s);
        this.createLocale();
    }
    
    public LanguageMapper(final String s, final Locale locale) throws IllegalArgumentException {
        super(s, locale);
        this.createLocale();
    }
    
    public LanguageMapper(final String s, final int n, final Locale locale) throws IllegalArgumentException {
        super(s, n, locale);
        this.createLocale();
    }
    
    public Locale getLocale() {
        return this.m_locale;
    }
    
    public Locale getLocale(final String s) {
        if (this.getISOCode().length() == 0) {
            return null;
        }
        return new Locale(this.getISOCode(), s);
    }
    
    public static Vector getLocalisedLanguages() {
        return getLocalisedLanguages(0, null);
    }
    
    public static Vector getLocalisedLanguages(final Locale locale) {
        return getLocalisedLanguages(0, locale);
    }
    
    public static Vector getLocalisedLanguages(final int n) {
        return getLocalisedLanguages(n, null);
    }
    
    public static Vector getLocalisedLanguages(final int n, final Locale locale) {
        final Vector<LanguageMapper> vector = new Vector<LanguageMapper>();
        for (int i = 0; i < LanguageMapper.ms_languageCodes.length; ++i) {
            vector.addElement(new LanguageMapper(LanguageMapper.ms_languageCodes[i], n, locale));
        }
        return Util.sortStrings(vector);
    }
    
    protected final String getChooseMessage() {
        return JAPMessages.getString(LanguageMapper.MSG_CHOOSE_LANGUAGE);
    }
    
    protected String getJRETransaltionOfISOCode(final String s, final Locale locale) {
        return new Locale(s, locale.getCountry()).getDisplayLanguage(locale);
    }
    
    private void createLocale() {
        if (this.getISOCode().length() > 0) {
            this.m_locale = new Locale(this.getISOCode(), "");
        }
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
        ms_languageCodes = new String[] { "AA", "AB", "AF", "AM", "AR", "AS", "AY", "AZ", "BA", "BE", "BG", "BH", "BI", "BN", "BO", "BR", "CA", "CO", "CS", "CY", "DA", "DE", "DZ", "EL", "EN", "EO", "ES", "ET", "EU", "FA", "FI", "FJ", "FO", "FR", "FY", "GA", "GD", "GL", "GN", "GU", "HA", "HI", "HR", "HU", "HY", "IA", "IE", "IK", "IN", "IS", "IT", "IW", "JA", "JI", "JW", "KA", "KK", "KL", "KM", "KN", "KO", "KS", "KU", "KY", "LA", "LN", "LO", "LT", "LV", "MG", "MI", "MK", "ML", "MN", "MO", "MR", "MS", "MT", "MY", "NA", "NE", "NL", "NO", "OC", "OM", "OR", "PA", "PL", "PS", "PT", "QU", "RM", "RN", "RO", "RU", "RW", "SA", "SD", "SG", "SH", "SI", "SK", "SL", "SM", "SN", "SO", "SQ", "SR", "SS", "ST", "SU", "SV", "SW", "TA", "TE", "TG", "TH", "TI", "TK", "TL", "TN", "TO", "TR", "TS", "TT", "TW", "UK", "UR", "UZ", "VI", "VO", "WO", "XH", "YO", "ZH", "ZU" };
        MSG_CHOOSE_LANGUAGE = ((LanguageMapper.class$anon$util$LanguageMapper == null) ? (LanguageMapper.class$anon$util$LanguageMapper = class$("anon.util.LanguageMapper")) : LanguageMapper.class$anon$util$LanguageMapper).getName() + "_ChooseLanguage";
    }
}
