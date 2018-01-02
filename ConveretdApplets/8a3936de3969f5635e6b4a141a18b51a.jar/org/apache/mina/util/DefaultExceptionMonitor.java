// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.util;

import org.slf4j.LoggerFactory;
import com.masystem.beergame.debug.Log;
import org.slf4j.Logger;

public class DefaultExceptionMonitor extends ExceptionMonitor
{
    private static final Logger LOGGER;
    
    @Override
    public final void exceptionCaught(final Throwable t) {
        if (t instanceof Error) {
            throw (Error)t;
        }
        final Logger logger = DefaultExceptionMonitor.LOGGER;
        Log.warn("Unexpected exception.", t);
    }
    
    static {
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
    }
}
