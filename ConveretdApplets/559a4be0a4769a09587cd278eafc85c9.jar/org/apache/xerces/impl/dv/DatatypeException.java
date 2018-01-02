// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatatypeException extends Exception
{
    protected String key;
    protected Object[] args;
    
    public DatatypeException(final String key, final Object[] args) {
        super(key);
        this.key = key;
        this.args = args;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public Object[] getArgs() {
        return this.args;
    }
    
    public String getMessage() {
        ResourceBundle resourceBundle = null;
        resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.XMLSchemaMessages");
        if (resourceBundle == null) {
            throw new MissingResourceException("Property file not found!", "org.apache.xerces.impl.msg.XMLSchemaMessages", this.key);
        }
        String msg = resourceBundle.getString(this.key);
        if (msg == null) {
            msg = resourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(msg, "org.apache.xerces.impl.msg.XMLSchemaMessages", this.key);
        }
        if (this.args != null) {
            try {
                msg = MessageFormat.format(msg, this.args);
            }
            catch (Exception e) {
                msg = resourceBundle.getString("FormatFailed");
                msg = msg + " " + resourceBundle.getString(this.key);
            }
        }
        return msg;
    }
}
