// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.util.ResourceBundle;
import java.text.MessageFormat;

class Res
{
    private static final String nullString = "(null)";
    static final ArrayResourceBundle bundle;
    static final String resourceName;
    static final int InvalidArg = 0;
    static final int UnexpectedType = 1;
    static final int InvalidVariantType = 2;
    static final int InvalidVariantName = 3;
    
    static String getString(final int n) {
        return (String)Res.bundle.getObject(n);
    }
    
    static String format(final int n, final Object[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                array[i] = "(null)";
            }
        }
        final String format = MessageFormat.format(getString(n), array);
        for (int j = 0; j < array.length; ++j) {
            if (array[j] == "(null)") {
                array[j] = null;
            }
        }
        return format;
    }
    
    public static String format(final int n, final Object o) {
        return MessageFormat.format(getString(n), (o == null) ? "(null)" : o);
    }
    
    public static String format(final int n, final Object o, final Object o2) {
        return MessageFormat.format(getString(n), (o == null) ? "(null)" : o, (o2 == null) ? "(null)" : o2);
    }
    
    public static String format(final int n, final Object o, final Object o2, final Object o3) {
        return MessageFormat.format(getString(n), (o == null) ? "(null)" : o, (o2 == null) ? "(null)" : o2, (o3 == null) ? "(null)" : o3);
    }
    
    static {
        bundle = (ArrayResourceBundle)ResourceBundle.getBundle("borland.jbcl.util.ResTable");
        resourceName = ResTable.resourceName;
    }
}
