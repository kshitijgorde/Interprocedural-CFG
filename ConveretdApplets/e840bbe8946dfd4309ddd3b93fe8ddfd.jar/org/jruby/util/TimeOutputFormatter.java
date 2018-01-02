// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeOutputFormatter
{
    private final String formatter;
    private final int totalPadding;
    private static final String formatPattern = "%([\\^0_-]+)?(\\d+)?.+";
    private static final Pattern regexp;
    
    public TimeOutputFormatter(final String formatter, final int totalPadding) {
        this.formatter = formatter;
        this.totalPadding = totalPadding;
    }
    
    public static TimeOutputFormatter getFormatter(final String pattern) {
        final Matcher matcher = TimeOutputFormatter.regexp.matcher(pattern);
        if (matcher.matches() && (matcher.group(1) != null || matcher.group(2) != null)) {
            final String formatter = matcher.group(1);
            final int totalPadding = (matcher.group(2) != null) ? Integer.valueOf(matcher.group(2)) : 0;
            return new TimeOutputFormatter(formatter, totalPadding);
        }
        return null;
    }
    
    public String getFormatter() {
        return ((this.formatter != null) ? this.formatter : "") + ((this.totalPadding > 0) ? this.totalPadding : "");
    }
    
    public String format(String sequence) {
        char paddedWith = ' ';
        if (this.formatter != null) {
            for (int i = 0; i < this.formatter.length(); ++i) {
                switch (this.formatter.charAt(i)) {
                    case '^': {
                        sequence = sequence.toUpperCase();
                        break;
                    }
                    case '_': {
                        paddedWith = ' ';
                        break;
                    }
                    case '0': {
                        paddedWith = '0';
                        break;
                    }
                    case '-': {
                        sequence = sequence.replaceAll("^[0]", "");
                        break;
                    }
                }
            }
        }
        if (this.totalPadding > 0) {
            sequence = this.padding(sequence, paddedWith);
        }
        return sequence;
    }
    
    private String padding(final String sequence, final char padder) {
        if (this.formatter != null && this.formatter.contains("-")) {
            return sequence;
        }
        if (sequence != null && sequence.length() < this.totalPadding) {
            final StringBuilder seqBuf = new StringBuilder(this.totalPadding);
            for (int i = sequence.length(); i < this.totalPadding; ++i) {
                seqBuf.append(padder);
            }
            seqBuf.append(sequence);
            return seqBuf.toString();
        }
        return sequence;
    }
    
    static {
        regexp = Pattern.compile("%([\\^0_-]+)?(\\d+)?.+");
    }
}
