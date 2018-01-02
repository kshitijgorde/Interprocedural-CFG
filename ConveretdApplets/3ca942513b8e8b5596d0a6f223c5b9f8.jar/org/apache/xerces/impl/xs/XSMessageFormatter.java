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
    
    public String formatMessage(final Locale fLocale, final String s, final Object[] array) throws MissingResourceException {
        if (this.fResourceBundle == null || fLocale != this.fLocale) {
            if (fLocale != null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSchemaMessages", fLocale);
                this.fLocale = fLocale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSchemaMessages");
            }
        }
        String s2 = this.fResourceBundle.getString(s);
        if (array != null) {
            try {
                s2 = MessageFormat.format(s2, array);
            }
            catch (Exception ex) {
                s2 = this.fResourceBundle.getString("FormatFailed") + " " + this.fResourceBundle.getString(s);
            }
        }
        if (s2 == null) {
            throw new MissingResourceException(this.fResourceBundle.getString("BadMessageKey"), "org.apache.xerces.impl.msg.SchemaMessages", s);
        }
        return s2;
    }
}
