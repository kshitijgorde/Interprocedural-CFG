// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternConverter
{
    public PatternConverter next;
    int min;
    int max;
    boolean leftAlign;
    static String[] SPACES;
    
    protected PatternConverter() {
        this.min = -1;
        this.max = Integer.MAX_VALUE;
        this.leftAlign = false;
    }
    
    protected PatternConverter(final FormattingInfo formattingInfo) {
        this.min = -1;
        this.max = Integer.MAX_VALUE;
        this.leftAlign = false;
        this.min = formattingInfo.min;
        this.max = formattingInfo.max;
        this.leftAlign = formattingInfo.leftAlign;
    }
    
    protected abstract String convert(final LoggingEvent p0);
    
    public void format(final StringBuffer sb, final LoggingEvent loggingEvent) {
        final String convert = this.convert(loggingEvent);
        if (convert == null) {
            if (0 < this.min) {
                this.spacePad(sb, this.min);
            }
            return;
        }
        final int length = convert.length();
        if (length > this.max) {
            sb.append(convert.substring(length - this.max));
        }
        else if (length < this.min) {
            if (this.leftAlign) {
                sb.append(convert);
                this.spacePad(sb, this.min - length);
            }
            else {
                this.spacePad(sb, this.min - length);
                sb.append(convert);
            }
        }
        else {
            sb.append(convert);
        }
    }
    
    public void spacePad(final StringBuffer sb, int i) {
        while (i >= 32) {
            sb.append(PatternConverter.SPACES[5]);
            i -= 32;
        }
        for (int j = 4; j >= 0; --j) {
            if ((i & 1 << j) != 0x0) {
                sb.append(PatternConverter.SPACES[j]);
            }
        }
    }
    
    static {
        PatternConverter.SPACES = new String[] { " ", "  ", "    ", "        ", "                ", "                                " };
    }
}
