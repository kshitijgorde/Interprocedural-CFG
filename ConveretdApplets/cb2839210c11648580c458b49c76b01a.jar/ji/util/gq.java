// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

public class gq
{
    public static boolean a(final Object[] array, final Object[] array2) {
        boolean b = false;
        if (array == null && array2 == null) {
            b = true;
        }
        else if (array.length == array2.length) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null) {
                    b = (array2[i] == null);
                }
                else {
                    b = (array2[i] != null && array[i].equals(array2[i]));
                }
                if (!b) {
                    break;
                }
            }
        }
        return b;
    }
    
    public static final String a(final Object[] array) {
        if (array == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer("{");
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("}");
        return sb.toString();
    }
}
