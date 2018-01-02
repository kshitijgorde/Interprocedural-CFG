// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import org.apache.xerces.utils.XMLMessageProvider;

public class DatatypeMessageProvider implements XMLMessageProvider
{
    public static final String DATATYPE_DOMAIN = "http://www.w3.org/TR/xml-schema-2";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    public static final int MSG_BAD_MAJORCODE = 0;
    public static final int MSG_FORMAT_FAILURE = 1;
    public static final int NotBoolean = 2;
    public static final int NotDecimal = 3;
    public static final int FacetsInconsistent = 4;
    public static final int IllegalFacetValue = 5;
    public static final int IllegalDecimalFacet = 6;
    public static final int UnknownFacet = 7;
    public static final int InvalidEnumValue = 8;
    public static final int OutOfBounds = 9;
    public static final int NotAnEnumValue = 10;
    public static final int NotInteger = 11;
    public static final int IllegalIntegerFacet = 12;
    public static final int NotReal = 13;
    public static final int IllegalRealFacet = 14;
    public static final int MSG_MAX_CODE = 15;
    public static final int MSG_NONE = 0;
    public static final String[] fgMessageKeys;
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public String createMessage(final Locale locale, int n, final int n2, final Object[] array) {
        boolean b = false;
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.DatatypeMessages", locale);
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.DatatypeMessages");
            }
        }
        if (n < 0 || n >= DatatypeMessageProvider.fgMessageKeys.length) {
            n = 0;
            b = true;
        }
        final String s = DatatypeMessageProvider.fgMessageKeys[n];
        String s2 = this.fResourceBundle.getString(s);
        if (array != null) {
            try {
                s2 = MessageFormat.format(s2, array);
            }
            catch (Exception ex) {
                s2 = String.valueOf(this.fResourceBundle.getString(DatatypeMessageProvider.fgMessageKeys[1])) + " " + this.fResourceBundle.getString(s);
            }
        }
        if (b) {
            throw new RuntimeException(s2);
        }
        return s2;
    }
    
    static {
        fgMessageKeys = new String[] { "BadMajorCode", "FormatFailed", "NotBoolean", "NotDecimal", "FacetsInconsistent", "IllegalFacetValue", "IllegalDecimalFacet", "UnknownFacet", "InvalidEnumValue", "OutOfBounds", "NotAnEnumValue", "NotInteger", "IllegalIntegerFacet", "NotReal", "IllegalRealFacet" };
    }
}
