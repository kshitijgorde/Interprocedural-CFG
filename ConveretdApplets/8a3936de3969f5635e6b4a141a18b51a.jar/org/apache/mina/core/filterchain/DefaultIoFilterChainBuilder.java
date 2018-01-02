// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.filterchain;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class DefaultIoFilterChainBuilder implements IoFilterChainBuilder
{
    private final List<IoFilterChain.Entry> entries;
    
    public DefaultIoFilterChainBuilder() {
        this.entries = new CopyOnWriteArrayList<IoFilterChain.Entry>();
    }
    
    public final synchronized void addLast(final String s, final IoFilter ioFilter) {
        final int size = this.entries.size();
        final EntryImpl entryImpl = new EntryImpl(s, ioFilter);
        final int n = size;
        final String name = entryImpl.getName();
        final Iterator<IoFilterChain.Entry> iterator = this.entries.iterator();
        while (true) {
            while (iterator.hasNext()) {
                final IoFilterChain.Entry entry;
                if ((entry = iterator.next()).getName().equals(name)) {
                    final IoFilterChain.Entry entry2 = entry;
                    if (entry2 != null) {
                        throw new IllegalArgumentException("Other filter is using the same name: " + entryImpl.getName());
                    }
                    this.entries.add(n, entryImpl);
                    return;
                }
            }
            final IoFilterChain.Entry entry2 = null;
            continue;
        }
    }
    
    public final synchronized void clear() {
        this.entries.clear();
    }
    
    @Override
    public final void buildFilterChain(final IoFilterChain ioFilterChain) throws Exception {
        for (final IoFilterChain.Entry entry : this.entries) {
            ioFilterChain.addLast(entry.getName(), entry.getFilter());
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("{ ");
        int n = 1;
        for (final IoFilterChain.Entry entry : this.entries) {
            if (n == 0) {
                sb.append(", ");
            }
            else {
                n = 0;
            }
            sb.append('(');
            sb.append(entry.getName());
            sb.append(':');
            sb.append(entry.getFilter());
            sb.append(')');
        }
        if (n != 0) {
            sb.append("empty");
        }
        sb.append(" }");
        return sb.toString();
    }
    
    static {
        LoggerFactory.getLogger$4ecaad6a();
    }
    
    final class EntryImpl implements IoFilterChain.Entry
    {
        private final String name;
        private volatile IoFilter filter;
        
        private EntryImpl(final DefaultIoFilterChainBuilder defaultIoFilterChainBuilder, final String name, final IoFilter filter, final byte b) {
            if (name == null) {
                throw new IllegalArgumentException("name");
            }
            if (filter == null) {
                throw new IllegalArgumentException("filter");
            }
            this.name = name;
            this.filter = filter;
        }
        
        @Override
        public final String getName() {
            return this.name;
        }
        
        @Override
        public final IoFilter getFilter() {
            return this.filter;
        }
        
        @Override
        public final IoFilter.NextFilter getNextFilter() {
            throw new IllegalStateException();
        }
        
        @Override
        public final String toString() {
            return "(" + this.name + ':' + this.filter + ')';
        }
    }
}
