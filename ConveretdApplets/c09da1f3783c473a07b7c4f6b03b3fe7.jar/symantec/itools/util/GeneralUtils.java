// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class GeneralUtils
{
    public static String frameTarget_self;
    public static String frameTarget_parent;
    public static String frameTarget_top;
    public static String frameTarget_blank;
    protected static ResourceBundle errors;
    
    public static boolean objectsEqual(final Object objectA, final Object objectB) {
        if (objectA == null) {
            return objectB == null;
        }
        return objectA.equals(objectB);
    }
    
    public static void checkValidPercent(final double percent) throws IllegalArgumentException {
        if (percent > 1.0 || percent < 0.0) {
            final Object[] args = { new Double(percent) };
            throw new IllegalArgumentException(MessageFormat.format(GeneralUtils.errors.getString("InvalidPercent2"), args));
        }
    }
    
    public static String removeCharAtIndex(final String string, final int index) {
        if (string == null || string == "") {
            return string;
        }
        final int length = string.length();
        if (index > length || index < 0) {
            return string;
        }
        final String left = (index > 0) ? string.substring(0, index) : "";
        final String right = (index + 1 < length) ? string.substring(index + 1) : "";
        return String.valueOf(left) + right;
    }
    
    static {
        GeneralUtils.frameTarget_self = "_self";
        GeneralUtils.frameTarget_parent = "_parent";
        GeneralUtils.frameTarget_top = "_top";
        GeneralUtils.frameTarget_blank = "_blank";
        GeneralUtils.errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle");
    }
}
