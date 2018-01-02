// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.layout;

import org.apache.log4j.helpers.PatternParser;

public class PatternLayout extends org.apache.log4j.PatternLayout
{
    protected PatternParser createPatternParser(final String pattern) {
        return new PatternParserEx(pattern);
    }
}
