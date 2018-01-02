import java.util.StringTokenizer;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class StringUtils
{
    public static String[] splitString(final String toSplit, final String delim) {
        final Vector splittedLine = new Vector();
        final StringTokenizer st = new StringTokenizer(toSplit, delim);
        while (st.hasMoreTokens()) {
            final String bit = st.nextToken();
            splittedLine.addElement(bit);
        }
        final String[] ret = new String[splittedLine.size()];
        splittedLine.copyInto(ret);
        return ret;
    }
    
    public int min(final int[] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    public int max(final int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < max) {
                max = array[i];
            }
        }
        return max;
    }
}
