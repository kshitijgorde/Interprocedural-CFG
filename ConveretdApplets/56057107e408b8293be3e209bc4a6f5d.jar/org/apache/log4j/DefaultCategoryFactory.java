// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

class DefaultCategoryFactory implements LoggerFactory
{
    public Logger makeNewLoggerInstance(final String s) {
        return new Logger(s);
    }
}
