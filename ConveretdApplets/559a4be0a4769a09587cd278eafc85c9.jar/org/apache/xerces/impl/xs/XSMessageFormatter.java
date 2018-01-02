// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;

public class XSMessageFormatter implements MessageFormatter
{
    public static final String SCHEMA_DOMAIN = "http://www.w3.org/TR/xml-schema-1";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    
    public XSMessageFormatter() {
        this.fLocale = null;
        this.fResourceBundle = null;
    }
    
    public String formatMessage(final Locale locale, final String key, final Object[] arguments) throws MissingResourceException {
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSchemaMessages", locale);
                this.fLocale = locale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSchemaMessages");
            }
        }
        String msg = this.fResourceBundle.getString(key);
        if (arguments != null) {
            try {
                msg = MessageFormat.format(msg, arguments);
            }
            catch (Exception e) {
                msg = this.fResourceBundle.getString("FormatFailed");
                msg = msg + " " + this.fResourceBundle.getString(key);
            }
        }
        if (msg == null) {
            msg = this.fResourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(msg, "org.apache.xerces.impl.msg.SchemaMessages", key);
        }
        return msg;
    }
}
