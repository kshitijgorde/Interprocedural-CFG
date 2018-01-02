// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class DatatypeMessageFormatter
{
    private static final String BASE_NAME = "org.apache.xerces.impl.msg.DatatypeMessages";
    
    public static String formatMessage(final Locale locale, final String s, final Object[] array) throws MissingResourceException {
        ResourceBundle resourceBundle;
        if (locale != null) {
            resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.DatatypeMessages", locale);
        }
        else {
            resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.DatatypeMessages");
        }
        String s2;
        try {
            s2 = resourceBundle.getString(s);
            if (array != null) {
                try {
                    s2 = MessageFormat.format(s2, array);
                }
                catch (Exception ex) {
                    s2 = resourceBundle.getString("FormatFailed") + " " + resourceBundle.getString(s);
                }
            }
        }
        catch (MissingResourceException ex2) {
            throw new MissingResourceException(s, resourceBundle.getString("BadMessageKey"), s);
        }
        if (s2 == null) {
            s2 = s;
            if (array.length > 0) {
                final StringBuffer sb = new StringBuffer(s2);
                sb.append('?');
                for (int i = 0; i < array.length; ++i) {
                    if (i > 0) {
                        sb.append('&');
                    }
                    sb.append(String.valueOf(array[i]));
                }
            }
        }
        return s2;
    }
}
