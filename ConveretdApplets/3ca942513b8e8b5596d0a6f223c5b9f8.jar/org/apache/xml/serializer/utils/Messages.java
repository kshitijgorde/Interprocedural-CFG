// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.text.MessageFormat;
import java.util.ListResourceBundle;
import java.util.Locale;

public final class Messages
{
    private final Locale m_locale;
    private ListResourceBundle m_resourceBundle;
    private String m_resourceBundleName;
    
    Messages(final String resourceBundle) {
        this.m_locale = Locale.getDefault();
        this.m_resourceBundleName = resourceBundle;
    }
    
    private Locale getLocale() {
        return this.m_locale;
    }
    
    private ListResourceBundle getResourceBundle() {
        return this.m_resourceBundle;
    }
    
    public final String createMessage(final String msgKey, final Object[] args) {
        if (this.m_resourceBundle == null) {
            this.m_resourceBundle = this.loadResourceBundle(this.m_resourceBundleName);
        }
        if (this.m_resourceBundle != null) {
            return this.createMsg(this.m_resourceBundle, msgKey, args);
        }
        return "Could not load the resource bundles: " + this.m_resourceBundleName;
    }
    
    private final String createMsg(final ListResourceBundle fResourceBundle, String msgKey, final Object[] args) {
        String fmsg = null;
        boolean throwex = false;
        String msg = null;
        if (msgKey != null) {
            msg = fResourceBundle.getString(msgKey);
        }
        else {
            msgKey = "";
        }
        if (msg == null) {
            throwex = true;
            try {
                msg = MessageFormat.format("BAD_MSGKEY", msgKey, this.m_resourceBundleName);
            }
            catch (Exception e) {
                msg = "The message key '" + msgKey + "' is not in the message class '" + this.m_resourceBundleName + "'";
            }
        }
        else if (args != null) {
            try {
                for (int n = args.length, i = 0; i < n; ++i) {
                    if (null == args[i]) {
                        args[i] = "";
                    }
                }
                fmsg = MessageFormat.format(msg, args);
            }
            catch (Exception e) {
                throwex = true;
                try {
                    fmsg = MessageFormat.format("BAD_MSGFORMAT", msgKey, this.m_resourceBundleName);
                    fmsg = fmsg + " " + msg;
                }
                catch (Exception formatfailed) {
                    fmsg = "The format of message '" + msgKey + "' in message class '" + this.m_resourceBundleName + "' failed.";
                }
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
    
    private ListResourceBundle loadResourceBundle(final String resourceBundle) throws MissingResourceException {
        this.m_resourceBundleName = resourceBundle;
        final Locale locale = this.getLocale();
        ListResourceBundle lrb;
        try {
            final ResourceBundle rb = ResourceBundle.getBundle(this.m_resourceBundleName, locale);
            lrb = (ListResourceBundle)rb;
        }
        catch (MissingResourceException e) {
            try {
                lrb = (ListResourceBundle)ResourceBundle.getBundle(this.m_resourceBundleName, new Locale("en", "US"));
            }
            catch (MissingResourceException e2) {
                throw new MissingResourceException("Could not load any resource bundles." + this.m_resourceBundleName, this.m_resourceBundleName, "");
            }
        }
        return this.m_resourceBundle = lrb;
    }
    
    private static String getResourceSuffix(final Locale locale) {
        String suffix = "_" + locale.getLanguage();
        final String country = locale.getCountry();
        if (country.equals("TW")) {
            suffix = suffix + "_" + country;
        }
        return suffix;
    }
}
