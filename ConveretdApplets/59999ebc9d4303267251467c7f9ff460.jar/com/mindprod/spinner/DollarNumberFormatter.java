// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import java.text.ParseException;
import java.text.DecimalFormat;
import javax.swing.text.DefaultFormatter;

public final class DollarNumberFormatter extends DefaultFormatter
{
    private static final DecimalFormat DOLLARFORMAT;
    
    public Object stringToValue(final String string) throws ParseException {
        try {
            return Math.rint(Double.parseDouble(this.strip(string)) * 100.0) / 100.0;
        }
        catch (NumberFormatException e) {
            throw new ParseException(string, 0);
        }
    }
    
    public String valueToString(final Object value) throws ParseException {
        return DollarNumberFormatter.DOLLARFORMAT.format(value);
    }
    
    private String strip(final String dirty) {
        final StringBuilder sb = new StringBuilder(dirty.length());
        for (int i = 0; i < dirty.length(); ++i) {
            final char c = dirty.charAt(i);
            switch (c) {
                case '-':
                case '.':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    static {
        DOLLARFORMAT = new DecimalFormat("'$'###,###,###,###,##0.00");
    }
}
