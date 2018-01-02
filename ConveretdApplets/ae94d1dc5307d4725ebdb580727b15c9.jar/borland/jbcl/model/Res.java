// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.ResourceBundle;
import java.text.MessageFormat;
import borland.jbcl.util.ArrayResourceBundle;

class Res
{
    private static final String nullString = "(null)";
    static final ArrayResourceBundle bundle;
    static final String resourceName;
    static final int True = 0;
    static final int False = 1;
    static final int DeleteFrom = 2;
    static final int InsertInto = 3;
    static final int Values = 4;
    static final int _Partial = 5;
    static final int _Next = 6;
    static final int _Prior = 7;
    static final int _CaseInsensitive = 8;
    static final int _Closest = 9;
    static final int _First = 10;
    static final int _Last = 11;
    static final int _Fast = 12;
    static final int Where = 13;
    static final int Unknown = 14;
    static final int Select = 15;
    static final int As = 16;
    static final int MustBeVariant = 17;
    static final int ParseError = 18;
    static final int ParseNotSupported = 19;
    static final int EmptyContainer = 20;
    static final int CantAdd = 21;
    static final int TreeCircularity = 22;
    static final int TreeNode = 23;
    static final int TreeNode1 = 24;
    static final int TreeNode2 = 25;
    static final int TreeNode3 = 26;
    static final int TreeNode4 = 27;
    static final int NoVariableSize = 28;
    static final int NoVariableRows = 29;
    static final int NoVariableColumns = 30;
    static final int TrueFalsePattern = 31;
    static final int ShortPrecisionBad = 32;
    static final int BytePrecisionBad = 33;
    
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
        bundle = (ArrayResourceBundle)ResourceBundle.getBundle("borland.jbcl.model.ResTable");
        resourceName = ResTable.resourceName;
    }
}
