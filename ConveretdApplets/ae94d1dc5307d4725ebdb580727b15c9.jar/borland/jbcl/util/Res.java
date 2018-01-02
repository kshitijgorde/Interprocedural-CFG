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
    
    static String getString(final int index) {
        return (String)Res.bundle.getObject(index);
    }
    
    static String format(final int index, final Object[] params) {
        for (int i = 0; i < params.length; ++i) {
            if (params[i] == null) {
                params[i] = "(null)";
            }
        }
        final String result = MessageFormat.format(getString(index), params);
        for (int j = 0; j < params.length; ++j) {
            if (params[j] == "(null)") {
                params[j] = null;
            }
        }
        return result;
    }
    
    public static String format(final int index, final Object param1) {
        return MessageFormat.format(getString(index), (param1 == null) ? "(null)" : param1);
    }
    
    public static String format(final int index, final Object param1, final Object param2) {
        return MessageFormat.format(getString(index), (param1 == null) ? "(null)" : param1, (param2 == null) ? "(null)" : param2);
    }
    
    public static String format(final int index, final Object param1, final Object param2, final Object param3) {
        return MessageFormat.format(getString(index), (param1 == null) ? "(null)" : param1, (param2 == null) ? "(null)" : param2, (param3 == null) ? "(null)" : param3);
    }
    
    static {
        bundle = (ArrayResourceBundle)ResourceBundle.getBundle("borland.jbcl.util.ResTable");
        resourceName = ResTable.resourceName;
    }
}
