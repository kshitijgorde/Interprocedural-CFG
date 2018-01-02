// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xpointer;

import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;

class XPointerMessageFormatter implements MessageFormatter
{
    public static final String XPOINTER_DOMAIN = "http://www.w3.org/TR/XPTR";
    private Locale fLocale;
    private ResourceBundle fResourceBundle;
    
    XPointerMessageFormatter() {
        this.fLocale = null;
        this.fResourceBundle = null;
    }
    
    public String formatMessage(final Locale fLocale, final String s, final Object[] array) throws MissingResourceException {
        if (this.fResourceBundle == null || fLocale != this.fLocale) {
            if (fLocale != null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XPointerMessages", fLocale);
                this.fLocale = fLocale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XPointerMessages");
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
            throw new MissingResourceException(this.fResourceBundle.getString("BadMessageKey"), "org.apache.xerces.impl.msg.XPointerMessages", s);
        }
        return s2;
    }
}
