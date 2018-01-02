// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.util;

public final class b
{
    public static String a(double[] array, String s) {
        final double[] array2 = array;
        s = s;
        array = array2;
        if (array2 == null) {
            return "";
        }
        if (s == null) {
            s = new String("");
        }
        final int length = array.length;
        if (length <= 0) {
            return "";
        }
        final int n = length + 0;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    static {
        new Float(0.0);
        new Double(0.0);
        final byte[] array = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    }
}
