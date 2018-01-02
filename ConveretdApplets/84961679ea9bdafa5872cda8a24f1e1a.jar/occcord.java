import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class occcord
{
    static int[] getIntValues(final String s, final String s2, final int n) {
        int n2 = 0;
        final int[] array = new int[n];
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            s.trim();
            if (stringTokenizer.countTokens() > n) {
                array[0] = -1;
                return array;
            }
            while (stringTokenizer.hasMoreTokens()) {
                array[n2] = Integer.parseInt(stringTokenizer.nextToken().trim());
                ++n2;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array;
    }
}
