// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class ImplementationMessages implements XMLMessageProvider
{
    public static final String XERCES_IMPLEMENTATION_DOMAIN = "http://www.apache.org/xml/xerces.html";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    public static final int BAD_MAJORCODE = 0;
    public static final int ENC4 = 1;
    public static final int ENC5 = 2;
    public static final int ENC6 = 3;
    public static final int ENC7 = 4;
    public static final int IO0 = 5;
    public static final int VAL_BST = 6;
    public static final int VAL_CMSI = 7;
    public static final int VAL_CST = 8;
    public static final int VAL_LST = 9;
    public static final int VAL_NIICM = 10;
    public static final int VAL_NPCD = 11;
    public static final int VAL_UST = 12;
    public static final int VAL_WCGHI = 13;
    public static final int INT_DCN = 14;
    public static final int INT_PCN = 15;
    public static final int FATAL_ERROR = 16;
    public static final int FORMAT_FAILED = 17;
    private static final String[] fgMessageKeys;
    
    public ImplementationMessages() {
        this.fLocale = null;
        this.fResourceBundle = null;
    }
    
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
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.ImplementationMessages", locale);
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.msg.ImplementationMessages");
            }
        }
        if (n < 0 || n >= ImplementationMessages.fgMessageKeys.length - 1) {
            n = 0;
            b = true;
        }
        final String s = ImplementationMessages.fgMessageKeys[n];
        String s2 = this.fResourceBundle.getString(s);
        if (array != null) {
            try {
                s2 = MessageFormat.format(s2, array);
            }
            catch (Exception ex) {
                s2 = this.fResourceBundle.getString(ImplementationMessages.fgMessageKeys[17]) + " " + this.fResourceBundle.getString(s);
            }
        }
        if (b) {
            throw new RuntimeException(s2);
        }
        return s2;
    }
    
    static {
        fgMessageKeys = new String[] { "BadMajorCode", "ENC4", "ENC5", "ENC6", "ENC7", "FileNotFound", "VAL_BST", "VAL_CMSI", "VAL_CST", "VAL_LST", "VAL_NIICM", "VAL_NPCD", "VAL_UST", "VAL_WCGHI", "INT_DCN", "INT_PCN", "FatalError", "FormatFailed", null };
    }
}
