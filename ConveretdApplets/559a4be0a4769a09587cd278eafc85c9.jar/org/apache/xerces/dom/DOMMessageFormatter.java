// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DOMMessageFormatter
{
    public static final String DOM_DOMAIN = "http://www.w3.org/dom/DOMTR";
    public static final String SERIALIZER_DOMAIN = "http://apache.org/xml/serializer";
    
    public static String formatMessage(final String domain, final String key, final Object[] arguments) throws MissingResourceException {
        ResourceBundle resourceBundle = null;
        if (domain.equals("http://www.w3.org/dom/DOMTR")) {
            resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.DOMMessages");
        }
        else {
            if (!domain.equals("http://apache.org/xml/serializer")) {
                throw new MissingResourceException("Unknown domain" + domain, null, key);
            }
            resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSerializerMessages");
        }
        if (resourceBundle == null) {
            throw new MissingResourceException("Property file not found!", "org.apache.xerces.impl.msg.DOMMessages", key);
        }
        String msg = key + ": " + resourceBundle.getString(key);
        if (arguments != null) {
            try {
                msg = MessageFormat.format(msg, arguments);
            }
            catch (Exception e) {
                msg = resourceBundle.getString("FormatFailed");
                msg = msg + " " + resourceBundle.getString(key);
            }
        }
        if (msg == null) {
            msg = resourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(msg, "org.apache.xerces.impl.msg.DOMMessages", key);
        }
        return msg;
    }
}
