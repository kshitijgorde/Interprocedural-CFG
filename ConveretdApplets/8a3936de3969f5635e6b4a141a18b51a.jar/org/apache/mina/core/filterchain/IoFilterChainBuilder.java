// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.filterchain;

public interface IoFilterChainBuilder
{
    void buildFilterChain(final IoFilterChain p0) throws Exception;
    
    default static {
        new IoFilterChainBuilder() {
            @Override
            public final void buildFilterChain(final IoFilterChain ioFilterChain) throws Exception {
            }
            
            @Override
            public final String toString() {
                return "NOOP";
            }
        };
    }
}
