// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.layout;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;

public class PatternParserEx extends PatternParser
{
    public PatternParserEx(final String pattern) {
        super(pattern);
    }
    
    protected void finalizeConverter(final char c) {
        PatternConverter pc = null;
        switch (c) {
            case 'z': {
                pc = new ThreadNDCConverter(this.formattingInfo);
                this.currentLiteral.setLength(0);
                break;
            }
            case 'Z': {
                final String key = this.extractOption();
                pc = new ThreadMDCConverter(this.formattingInfo, key);
                this.currentLiteral.setLength(0);
                break;
            }
            default: {
                super.finalizeConverter(c);
                return;
            }
        }
        this.addConverter(pc);
    }
}
