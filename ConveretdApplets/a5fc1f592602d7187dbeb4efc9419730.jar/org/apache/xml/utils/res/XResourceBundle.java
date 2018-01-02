// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.ListResourceBundle;

public class XResourceBundle extends ListResourceBundle
{
    public static final String ERROR_RESOURCES = "org.apache.xalan.res.XSLTErrorResources";
    public static final String XSLT_RESOURCE = "org.apache.xml.utils.res.XResourceBundle";
    public static final String LANG_BUNDLE_NAME = "org.apache.xml.utils.res.XResources";
    public static final String MULT_ORDER = "multiplierOrder";
    public static final String MULT_PRECEDES = "precedes";
    public static final String MULT_FOLLOWS = "follows";
    public static final String LANG_ORIENTATION = "orientation";
    public static final String LANG_RIGHTTOLEFT = "rightToLeft";
    public static final String LANG_LEFTTORIGHT = "leftToRight";
    public static final String LANG_NUMBERING = "numbering";
    public static final String LANG_ADDITIVE = "additive";
    public static final String LANG_MULT_ADD = "multiplicative-additive";
    public static final String LANG_MULTIPLIER = "multiplier";
    public static final String LANG_MULTIPLIER_CHAR = "multiplierChar";
    public static final String LANG_NUMBERGROUPS = "numberGroups";
    public static final String LANG_NUM_TABLES = "tables";
    public static final String LANG_ALPHABET = "alphabet";
    public static final String LANG_TRAD_ALPHABET = "tradAlphabet";
    static final Object[][] contents;
    
    public static final XResourceBundle loadResourceBundle(final String className, final Locale locale) throws MissingResourceException {
        final String suffix = getResourceSuffix(locale);
        try {
            final String resourceName = className + suffix;
            return (XResourceBundle)ResourceBundle.getBundle(resourceName, locale);
        }
        catch (MissingResourceException e) {
            try {
                return (XResourceBundle)ResourceBundle.getBundle("org.apache.xml.utils.res.XResourceBundle", new Locale("en", "US"));
            }
            catch (MissingResourceException e2) {
                throw new MissingResourceException("Could not load any resource bundles.", className, "");
            }
        }
    }
    
    private static final String getResourceSuffix(final Locale locale) {
        final String lang = locale.getLanguage();
        final String country = locale.getCountry();
        final String variant = locale.getVariant();
        String suffix = "_" + locale.getLanguage();
        if (lang.equals("zh")) {
            suffix = suffix + "_" + country;
        }
        if (country.equals("JP")) {
            suffix = suffix + "_" + country + "_" + variant;
        }
        return suffix;
    }
    
    public Object[][] getContents() {
        return XResourceBundle.contents;
    }
    
    static {
        contents = new Object[][] { { "ui_language", "en" }, { "help_language", "en" }, { "language", "en" }, { "alphabet", { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' } }, { "tradAlphabet", { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' } }, { "orientation", "LeftToRight" }, { "numbering", "additive" } };
    }
}
