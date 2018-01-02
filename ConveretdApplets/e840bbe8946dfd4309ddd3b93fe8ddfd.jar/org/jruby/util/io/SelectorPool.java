// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import org.jruby.Ruby;
import java.nio.channels.spi.SelectorProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.channels.Selector;
import java.util.List;

public class SelectorPool
{
    private final List<Selector> pool;
    
    public SelectorPool() {
        this.pool = new ArrayList<Selector>();
    }
    
    public synchronized Selector get() throws IOException {
        return this.retrieveFromPool();
    }
    
    public synchronized void put(final Selector selector) {
        this.returnToPool(selector);
    }
    
    public synchronized void cleanup() {
        while (!this.pool.isEmpty()) {
            final Selector selector = this.pool.remove(this.pool.size() - 1);
            try {
                selector.close();
            }
            catch (IOException ex) {}
        }
    }
    
    private Selector retrieveFromPool() throws IOException {
        if (!this.pool.isEmpty()) {
            return this.pool.remove(this.pool.size() - 1);
        }
        return SelectorFactory.openWithRetryFrom(null, SelectorProvider.provider());
    }
    
    private void returnToPool(final Selector selector) {
        this.pool.add(selector);
    }
}
