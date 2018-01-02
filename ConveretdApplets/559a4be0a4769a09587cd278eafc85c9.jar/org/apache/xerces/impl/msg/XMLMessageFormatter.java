// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.msg;

import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;

public class XMLMessageFormatter implements MessageFormatter
{
    public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
    public static final String XMLNS_DOMAIN = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    
    public XMLMessageFormatter() {
        this.fLocale = null;
        this.fResourceBundle = null;
    }
    
    public String formatMessage(final Locale locale, final String key, final Object[] arguments) throws MissingResourceException {
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLMessages", locale);
                this.fLocale = locale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLMessages");
            }
        }
        String msg;
        try {
            msg = this.fResourceBundle.getString(key);
            if (arguments != null) {
                try {
                    msg = MessageFormat.format(msg, arguments);
                }
                catch (Exception e) {
                    msg = this.fResourceBundle.getString("FormatFailed");
                    msg = msg + " " + this.fResourceBundle.getString(key);
                }
            }
        }
        catch (MissingResourceException e2) {
            msg = this.fResourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(key, msg, key);
        }
        if (msg == null) {
            msg = key;
            if (arguments.length > 0) {
                final StringBuffer str = new StringBuffer(msg);
                str.append('?');
                for (int i = 0; i < arguments.length; ++i) {
                    if (i > 0) {
                        str.append('&');
                    }
                    str.append(String.valueOf(arguments[i]));
                }
            }
        }
        return msg;
    }
}
