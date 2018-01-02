// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import java.text.ParseException;
import javax.swing.text.DefaultFormatter;

public final class HexNumberFormatter extends DefaultFormatter
{
    private final int width;
    
    public HexNumberFormatter(final int width) {
        if (width > 16) {
            throw new IllegalArgumentException("HexFormat width > 16");
        }
        this.width = width;
    }
    
    public Object stringToValue(final String string) throws ParseException {
        try {
            if (string.length() > this.width) {
                throw new ParseException("Max " + this.width + " digits allowed.", 0);
            }
            final Object value = this.getFormattedTextField().getValue();
            if (value instanceof Integer) {
                return Integer.valueOf(string, 16);
            }
            if (value instanceof Long) {
                return Long.valueOf(string, 16);
            }
            throw new IllegalArgumentException("HexNumberFormatter only works with Integer and Long");
        }
        catch (NumberFormatException nfe) {
            throw new ParseException(string, 0);
        }
    }
    
    public String valueToString(final Object value) throws ParseException {
        final long asLong = ((Number)value).longValue();
        final String hex = Long.toHexString(asLong);
        final int lz = this.width - hex.length();
        return (lz <= 0) ? hex : ("0000000000000000".substring(0, lz) + hex);
    }
}
