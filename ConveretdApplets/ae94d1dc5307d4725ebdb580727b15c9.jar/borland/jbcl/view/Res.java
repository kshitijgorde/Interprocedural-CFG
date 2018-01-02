// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.util.ResourceBundle;
import java.text.MessageFormat;
import borland.jbcl.util.ArrayResourceBundle;

class Res
{
    private static final String nullString = "(null)";
    static final ArrayResourceBundle bundle;
    static final String resourceName;
    static final int FileDialogNoFrame = 0;
    static final int BadVAlignment = 1;
    static final int BadHAlignment = 2;
    static final int FileNotFound = 3;
    static final int SelectImageFile = 4;
    static final int ColumnName = 5;
    static final int PropertyOutOfRange = 6;
    static final int NullRectangles = 7;
    static final int IllegalSubfocus = 8;
    static final int CantResetImageStream = 9;
    static final int EditorCannotPost = 10;
    static final int Ellipsis = 11;
    
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
        bundle = (ArrayResourceBundle)ResourceBundle.getBundle("borland.jbcl.view.ResTable");
        resourceName = ResTable.resourceName;
    }
}
