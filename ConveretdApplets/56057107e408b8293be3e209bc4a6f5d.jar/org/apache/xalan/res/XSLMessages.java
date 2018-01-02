// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.res;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;
import java.text.MessageFormat;
import org.apache.xml.utils.res.XResourceBundleBase;
import java.util.Locale;

public class XSLMessages
{
    private Locale fLocale;
    private static XResourceBundleBase XSLTBundle;
    private static XResourceBundleBase XPATHBundle;
    private static final String XSLT_ERROR_RESOURCES = "org.apache.xalan.res.XSLTErrorResources";
    private static final String XPATH_ERROR_RESOURCES = "org.apache.xpath.res.XPATHErrorResources";
    private static String BAD_CODE;
    private static String FORMAT_FAILED;
    
    static {
        XSLMessages.XSLTBundle = null;
        XSLMessages.XPATHBundle = null;
        XSLMessages.BAD_CODE = "BAD_CODE";
        XSLMessages.FORMAT_FAILED = "FORMAT_FAILED";
    }
    
    public XSLMessages() {
        this.fLocale = Locale.getDefault();
    }
    
    public static final String createMessage(final int errorCode, final Object[] args) {
        if (XSLMessages.XSLTBundle == null) {
            XSLMessages.XSLTBundle = (XResourceBundleBase)loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
        }
        final XResourceBundleBase fResourceBundle = XSLMessages.XSLTBundle;
        if (fResourceBundle != null) {
            final String msgKey = fResourceBundle.getMessageKey(errorCode);
            return createMsg(fResourceBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    public String createMessage(final String bundleName, final int errorCode, final Object[] args) throws Exception {
        boolean throwex = false;
        String fmsg = null;
        XResourceBundleBase aResourceBundle = null;
        aResourceBundle = (XResourceBundleBase)loadResourceBundle(bundleName);
        final String msgKey = aResourceBundle.getMessageKey(errorCode);
        String msg = null;
        if (msgKey != null) {
            msg = aResourceBundle.getString(msgKey);
        }
        if (msg == null) {
            msg = aResourceBundle.getString(XSLMessages.BAD_CODE);
            throwex = true;
        }
        if (args != null) {
            try {
                for (int n = args.length, i = 0; i < n; ++i) {
                    if (args[i] == null) {
                        args[i] = "";
                    }
                }
                fmsg = MessageFormat.format(msg, args);
            }
            catch (Exception ex) {
                fmsg = aResourceBundle.getString(XSLMessages.FORMAT_FAILED);
                fmsg = String.valueOf(fmsg) + " " + msg;
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
    
    public static final String createMsg(final XResourceBundleBase fResourceBundle, final String msgKey, final Object[] args) {
        String fmsg = null;
        boolean throwex = false;
        String msg = null;
        if (msgKey != null) {
            msg = fResourceBundle.getString(msgKey);
        }
        if (msg == null) {
            msg = fResourceBundle.getString(XSLMessages.BAD_CODE);
            throwex = true;
        }
        if (args != null) {
            try {
                for (int n = args.length, i = 0; i < n; ++i) {
                    if (args[i] == null) {
                        args[i] = "";
                    }
                }
                fmsg = MessageFormat.format(msg, args);
            }
            catch (Exception ex) {
                fmsg = fResourceBundle.getString(XSLMessages.FORMAT_FAILED);
                fmsg = String.valueOf(fmsg) + " " + msg;
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
    
    public static final String createWarning(final int errorCode, final Object[] args) {
        if (XSLMessages.XSLTBundle == null) {
            XSLMessages.XSLTBundle = (XResourceBundleBase)loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
        }
        final XResourceBundleBase fResourceBundle = XSLMessages.XSLTBundle;
        if (fResourceBundle != null) {
            final String msgKey = fResourceBundle.getWarningKey(errorCode);
            return createMsg(fResourceBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    public static final String createXPATHMessage(final int errorCode, final Object[] args) {
        if (XSLMessages.XPATHBundle == null) {
            XSLMessages.XPATHBundle = (XResourceBundleBase)loadResourceBundle("org.apache.xpath.res.XPATHErrorResources");
        }
        final XResourceBundleBase fResourceBundle = XSLMessages.XPATHBundle;
        if (fResourceBundle != null) {
            final String msgKey = fResourceBundle.getMessageKey(errorCode);
            return createXPATHMsg(fResourceBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    public static final String createXPATHMsg(final XResourceBundleBase fResourceBundle, final String msgKey, final Object[] args) {
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
                    if (args[i] == null) {
                        args[i] = "";
                    }
                }
                fmsg = MessageFormat.format(msg, args);
            }
            catch (Exception ex) {
                fmsg = fResourceBundle.getString("FORMAT_FAILED");
                fmsg = String.valueOf(fmsg) + " " + msg;
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
    
    public static final String createXPATHWarning(final int errorCode, final Object[] args) {
        if (XSLMessages.XPATHBundle == null) {
            XSLMessages.XPATHBundle = (XResourceBundleBase)loadResourceBundle("org.apache.xpath.res.XPATHErrorResources");
        }
        final XResourceBundleBase fResourceBundle = XSLMessages.XPATHBundle;
        if (fResourceBundle != null) {
            final String msgKey = fResourceBundle.getWarningKey(errorCode);
            return createXPATHMsg(fResourceBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    private static final String getResourceSuffix(final Locale locale) {
        String suffix = "_" + locale.getLanguage();
        final String country = locale.getCountry();
        if (country.equals("TW")) {
            suffix = String.valueOf(suffix) + "_" + country;
        }
        return suffix;
    }
    
    public static final ListResourceBundle loadResourceBundle(final String className) throws MissingResourceException {
        final Locale locale = Locale.getDefault();
        try {
            return (ListResourceBundle)ResourceBundle.getBundle(className, locale);
        }
        catch (MissingResourceException ex) {
            try {
                return (ListResourceBundle)ResourceBundle.getBundle("org.apache.xalan.res.XSLTErrorResources", new Locale("en", "US"));
            }
            catch (MissingResourceException ex2) {
                throw new MissingResourceException("Could not load any resource bundles." + className, className, "");
            }
        }
    }
    
    public void setLocale(final Locale locale) {
        this.fLocale = locale;
    }
}
