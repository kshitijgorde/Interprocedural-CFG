// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.util;

public final class GeneralUtils
{
    public static String frameTarget_self;
    public static String frameTarget_parent;
    public static String frameTarget_top;
    public static String frameTarget_blank;
    
    public static boolean objectsEqual(final Object objectA, final Object objectB) {
        if (objectA == null) {
            return objectB == null;
        }
        return objectA.equals(objectB);
    }
    
    public static void checkValidPercent(final double percent) throws IllegalArgumentException {
        if (percent > 1.0 || percent < 0.0) {
            throw new IllegalArgumentException(String.valueOf(percent) + " is not a valid percentage value. It should be <= 1 && >= 0");
        }
    }
    
    static {
        GeneralUtils.frameTarget_self = "_self";
        GeneralUtils.frameTarget_parent = "_parent";
        GeneralUtils.frameTarget_top = "_top";
        GeneralUtils.frameTarget_blank = "_blank";
    }
}
