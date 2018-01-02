// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.io.IOException;
import java.net.BindException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import org.jruby.Ruby;

public class SelectorFactory
{
    private static final int RETRY_MAX = 20;
    
    public static Selector openWithRetryFrom(final Ruby runtime, final SelectorProvider provider) throws IOException {
        int retryCount = 0;
        try {
            return provider.openSelector();
        }
        catch (IOException e) {
            if (e.getMessage() != null && e.getMessage().contains("Unable to establish loopback connection") && e.getCause() instanceof BindException && retryCount < 20) {
                ++retryCount;
                if (runtime != null) {
                    runtime.getWarnings().warn("try number " + retryCount + " to get a selector");
                }
                return provider.openSelector();
            }
            throw e;
        }
    }
}
