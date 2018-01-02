// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.util.StringTokenizer;

public class LongArrayParser implements DataParser
{
    public Class getType() {
        return long[].class;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof long[])) {
            throw new IllegalArgumentException("This class can only format Objects of type long[].");
        }
        final long[] array = (long[])o;
        final StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]).append('L');
        }
        sb.append(']');
        return sb.toString();
    }
    
    public boolean canParse(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\"[](){}, ");
            while (stringTokenizer.hasMoreTokens()) {
                LongParser.parseLong(stringTokenizer.nextToken());
            }
            return true;
        }
        catch (DataParseException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\"[](){}, ");
            final long[] array = new long[stringTokenizer.countTokens()];
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array[n] = LongParser.parseLong(stringTokenizer.nextToken());
                ++n;
            }
            return array;
        }
        catch (NumberFormatException ex) {
            throw new DataParseException(ex);
        }
    }
}
