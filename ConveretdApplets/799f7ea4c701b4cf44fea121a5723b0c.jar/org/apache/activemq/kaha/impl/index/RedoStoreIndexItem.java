// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import java.io.DataOutput;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.DataInput;
import java.io.ObjectInput;
import org.apache.activemq.kaha.Marshaller;
import java.io.Externalizable;

public class RedoStoreIndexItem implements Externalizable
{
    public static final Marshaller MARSHALLER;
    private static final long serialVersionUID = -4865508871719676655L;
    private String indexName;
    private IndexItem indexItem;
    private long offset;
    
    public RedoStoreIndexItem() {
    }
    
    public RedoStoreIndexItem(final String indexName, final long offset, final IndexItem item) {
        this.indexName = indexName;
        this.offset = offset;
        this.indexItem = item;
    }
    
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.readExternal((DataInput)in);
    }
    
    public void readExternal(final DataInput in) throws IOException {
        this.offset = in.readLong();
        (this.indexItem = new IndexItem()).read(in);
    }
    
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        this.writeExternal((DataOutput)out);
    }
    
    public void writeExternal(final DataOutput out) throws IOException {
        out.writeLong(this.offset);
        this.indexItem.write(out);
    }
    
    public String getIndexName() {
        return this.indexName;
    }
    
    public void setIndexName(final String indexName) {
        this.indexName = indexName;
    }
    
    public IndexItem getIndexItem() {
        return this.indexItem;
    }
    
    public void setIndexItem(final IndexItem item) {
        this.indexItem = item;
    }
    
    public long getOffset() {
        return this.offset;
    }
    
    public void setOffset(final long offset) {
        this.offset = offset;
    }
    
    static {
        MARSHALLER = new Marshaller() {
            @Override
            public Object readPayload(final DataInput in) throws IOException {
                final RedoStoreIndexItem item = new RedoStoreIndexItem();
                item.readExternal(in);
                return item;
            }
            
            @Override
            public void writePayload(final Object object, final DataOutput out) throws IOException {
                final RedoStoreIndexItem item = (RedoStoreIndexItem)object;
                item.writeExternal(out);
            }
        };
    }
}
