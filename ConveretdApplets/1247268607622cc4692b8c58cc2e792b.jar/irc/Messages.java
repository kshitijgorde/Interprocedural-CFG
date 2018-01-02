// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages
{
    private static final String BUNDLE_NAME = "irc.messages";
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
        RESOURCE_BUNDLE = ResourceBundle.getBundle("irc.messages");
    }
}
