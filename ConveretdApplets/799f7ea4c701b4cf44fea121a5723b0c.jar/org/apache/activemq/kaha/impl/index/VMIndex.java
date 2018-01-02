// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import org.slf4j.LoggerFactory;
import org.apache.activemq.kaha.Marshaller;
import java.io.IOException;
import java.util.HashMap;
import org.apache.activemq.kaha.StoreEntry;
import java.util.Map;
import org.slf4j.Logger;
import org.apache.activemq.kaha.IndexMBean;

public class VMIndex implements Index, IndexMBean
{
    private static final Logger LOG;
    private IndexManager indexManager;
    private Map<Object, StoreEntry> map;
    
    public VMIndex(final IndexManager manager) {
        this.map = new HashMap<Object, StoreEntry>();
        this.indexManager = manager;
    }
    
    @Override
    public void clear() {
        this.map.clear();
    }
    
    @Override
    public boolean containsKey(final Object key) {
        return this.map.containsKey(key);
    }
    
    @Override
    public StoreEntry remove(final Object key) {
        StoreEntry result = this.map.remove(key);
        if (result != null) {
            try {
                result = this.indexManager.refreshIndex((IndexItem)result);
            }
            catch (IOException e) {
                VMIndex.LOG.error("Failed to refresh entry", e);
                throw new RuntimeException("Failed to refresh entry");
            }
        }
        return result;
    }
    
    @Override
    public void store(final Object key, final StoreEntry entry) {
        this.map.put(key, entry);
    }
    
    @Override
    public StoreEntry get(final Object key) {
        StoreEntry result = this.map.get(key);
        if (result != null) {
            try {
                result = this.indexManager.refreshIndex((IndexItem)result);
            }
            catch (IOException e) {
                VMIndex.LOG.error("Failed to refresh entry", e);
                throw new RuntimeException("Failed to refresh entry");
            }
        }
        return result;
    }
    
    @Override
    public boolean isTransient() {
        return true;
    }
    
    @Override
    public void load() {
    }
    
    @Override
    public void unload() {
        this.map.clear();
    }
    
    @Override
    public void delete() throws IOException {
        this.unload();
    }
    
    @Override
    public void setKeyMarshaller(final Marshaller marshaller) {
    }
    
    @Override
    public int getSize() {
        return this.map.size();
    }
    
    static {
        LOG = LoggerFactory.getLogger(VMIndex.class);
    }
}
