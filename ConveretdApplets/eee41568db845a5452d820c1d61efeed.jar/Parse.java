// 
// Decompiled by Procyon v0.5.30
// 

class Parse
{
    public static double[] NumericArgs(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        int i = 0;
        int n = 1;
        while (i >= 0) {
            i = s.indexOf(44, i);
            if (i >= 0) {
                ++n;
                ++i;
            }
        }
        final double[] array = new double[n];
        int n2 = s.indexOf("(") + 1;
        if (n2 <= 0) {
            throw new ParsingException("Open paren missing");
        }
        int index = 0;
        for (int j = 0; j < n; ++j) {
            String s2;
            if (j < n - 1) {
                index = s.indexOf(",", n2);
                s2 = s.substring(n2, index);
            }
            else {
                s2 = s.substring(n2, s.length() - 1);
            }
            array[j] = Double.valueOf(s2);
            n2 = index + 1;
        }
        return array;
    }
}
