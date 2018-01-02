// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.spi;

import org.apache.log4j.Logger;

public interface LoggerFactory
{
    Logger makeNewLoggerInstance(final String p0);
}
