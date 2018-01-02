// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.util.StringTokenizer;

public class DoubleArrayParser implements DataParser
{
    public Class getType() {
        return double[].class;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof double[])) {
            throw new IllegalArgumentException("This class can only format Objects of type double[].");
        }
        final double[] array = (double[])o;
        final StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public boolean canParse(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\"[](){}, ");
            while (stringTokenizer.hasMoreTokens()) {
                Double.parseDouble(stringTokenizer.nextToken());
            }
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\"[](){}, ");
            final double[] array = new double[stringTokenizer.countTokens()];
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array[n] = Double.parseDouble(stringTokenizer.nextToken());
                ++n;
            }
            return array;
        }
        catch (NumberFormatException ex) {
            throw new DataParseException(ex);
        }
    }
}
