// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.util.lang;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
    private static final String BUNDLE_NAME = "com.eaio.util.lang.messages";
    private static final ResourceBundle RESOURCE_BUNDLE;
    
    public static String getString(final String s) {
        try {
            return Messages.RESOURCE_BUNDLE.getString(s);
        }
        catch (MissingResourceException ex) {
            return '!' + s + '!';
        }
    }
    
    static {
        RESOURCE_BUNDLE = ResourceBundle.getBundle("com.eaio.util.lang.messages");
    }
}
