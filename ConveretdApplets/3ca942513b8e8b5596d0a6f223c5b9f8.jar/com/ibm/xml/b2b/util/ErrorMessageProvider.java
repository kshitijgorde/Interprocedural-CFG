// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.ibm.xml.b2b.util.msg.ErrorMessageBundle;
import java.util.Locale;

public class ErrorMessageProvider implements MessageProvider
{
    private String fClassName;
    private Locale fLocale;
    private ErrorMessageBundle fMessageBundle;
    
    public ErrorMessageProvider(final String fClassName) {
        this.fClassName = fClassName;
    }
    
    public String createMessage(final Locale locale, final int n, final Object[] array) {
        boolean b = false;
        String s;
        try {
            if (this.fMessageBundle == null || locale != this.fLocale) {
                if (locale != null) {
                    this.fMessageBundle = (ErrorMessageBundle)ResourceBundle.getBundle(this.fClassName, locale);
                }
                if (this.fMessageBundle == null) {
                    this.fMessageBundle = (ErrorMessageBundle)ResourceBundle.getBundle(this.fClassName);
                }
            }
            s = this.fMessageBundle.getString(n);
        }
        catch (MissingResourceException ex) {
            s = "ResourceBundle.getBundle(" + this.fClassName + ")";
            b = true;
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            s = this.fMessageBundle.getString("BadMessageKey");
            b = true;
        }
        String s2 = s;
        if (array != null) {
            try {
                s2 = MessageFormat.format(s, array);
            }
            catch (Exception ex3) {
                s2 = this.fMessageBundle.getString("FormatFailed") + " " + s;
            }
        }
        if (b) {
            throw new RuntimeException(s2);
        }
        return s2;
    }
}
