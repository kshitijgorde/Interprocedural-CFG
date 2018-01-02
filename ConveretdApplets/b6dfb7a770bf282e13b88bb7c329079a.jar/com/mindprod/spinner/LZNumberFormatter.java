// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import com.mindprod.common11.ST;
import java.text.ParseException;
import javax.swing.text.DefaultFormatter;

public final class LZNumberFormatter extends DefaultFormatter
{
    final int width;
    
    public LZNumberFormatter(final int width) {
        this.width = width;
    }
    
    public Object stringToValue(final String string) throws ParseException {
        try {
            final Object value = this.getFormattedTextField().getValue();
            if (value instanceof Integer) {
                return Integer.valueOf(string);
            }
            if (value instanceof Long) {
                return Long.valueOf(string);
            }
            throw new IllegalArgumentException("LZNumberFormatter only works with Integer and Long");
        }
        catch (NumberFormatException nfe) {
            throw new ParseException(string, 0);
        }
    }
    
    public String valueToString(final Object value) throws ParseException {
        if (value instanceof Integer) {
            return ST.toLZ((int)value, this.width);
        }
        return ST.toLZ(((Number)value).longValue(), this.width);
    }
}
