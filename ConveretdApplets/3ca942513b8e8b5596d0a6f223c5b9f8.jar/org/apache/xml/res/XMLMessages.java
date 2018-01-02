// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.text.MessageFormat;
import java.util.ListResourceBundle;
import java.util.Locale;

public class XMLMessages
{
    protected Locale fLocale;
    private static ListResourceBundle XMLBundle;
    private static final String XML_ERROR_RESOURCES = "org.apache.xml.res.XMLErrorResources";
    protected static final String BAD_CODE = "BAD_CODE";
    protected static final String FORMAT_FAILED = "FORMAT_FAILED";
    
    public XMLMessages() {
        this.fLocale = Locale.getDefault();
    }
    
    public void setLocale(final Locale locale) {
        this.fLocale = locale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public static final String createXMLMessage(final String msgKey, final Object[] args) {
        if (XMLMessages.XMLBundle == null) {
            XMLMessages.XMLBundle = loadResourceBundle("org.apache.xml.res.XMLErrorResources");
        }
        if (XMLMessages.XMLBundle != null) {
            return createMsg(XMLMessages.XMLBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    public static final String createMsg(final ListResourceBundle fResourceBundle, final String msgKey, final Object[] args) {
        String fmsg = null;
        boolean throwex = false;
        String msg = null;
        if (msgKey != null) {
            msg = fResourceBundle.getString(msgKey);
        }
        if (msg == null) {
            msg = fResourceBundle.getString("BAD_CODE");
            throwex = true;
        }
        if (args != null) {
            try {
                for (int n = args.length, i = 0; i < n; ++i) {
                    if (null == args[i]) {
                        args[i] = "";
                    }
                }
                fmsg = MessageFormat.format(msg, args);
            }
            catch (Exception e) {
                fmsg = fResourceBundle.getString("FORMAT_FAILED");
                fmsg = fmsg + " " + msg;
            }
        }
        else {
            fmsg = msg;
        }
        if (throwex) {
            throw new RuntimeException(fmsg);
        }
        return fmsg;
    }
    
    public static ListResourceBundle loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        try {
            return (ListResourceBundle)ResourceBundle.getBundle(className, locale);
        }
        catch (MissingResourceException e) {
            try {
                return (ListResourceBundle)ResourceBundle.getBundle(className, new Locale("en", "US"));
            }
            catch (MissingResourceException e2) {
                throw new MissingResourceException("Could not load any resource bundles." + className, className, "");
            }
        }
    }
    
    protected static String getResourceSuffix(final Locale locale) {
        String suffix = "_" + locale.getLanguage();
        final String country = locale.getCountry();
        if (country.equals("TW")) {
            suffix = suffix + "_" + country;
        }
        return suffix;
    }
    
    static {
        XMLMessages.XMLBundle = null;
    }
}
