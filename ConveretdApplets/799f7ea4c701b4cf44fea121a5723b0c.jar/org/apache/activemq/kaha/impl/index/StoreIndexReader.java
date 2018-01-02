// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import java.io.IOException;
import java.io.DataInput;
import org.apache.activemq.util.DataByteArrayInputStream;
import java.io.RandomAccessFile;

class StoreIndexReader
{
    protected RandomAccessFile file;
    protected DataByteArrayInputStream dataIn;
    protected byte[] buffer;
    
    StoreIndexReader(final RandomAccessFile file) {
        this.buffer = new byte[51];
        this.file = file;
        this.dataIn = new DataByteArrayInputStream();
    }
    
    protected IndexItem readItem(final long offset) throws IOException {
        this.file.seek(offset);
        this.file.readFully(this.buffer);
        this.dataIn.restart(this.buffer);
        final IndexItem result = new IndexItem();
        result.setOffset(offset);
        result.read(this.dataIn);
        return result;
    }
    
    void updateIndexes(final IndexItem indexItem) throws IOException {
        if (indexItem != null) {
            this.file.seek(indexItem.getOffset());
            this.file.readFully(this.buffer, 0, 19);
            this.dataIn.restart(this.buffer);
            indexItem.readIndexes(this.dataIn);
        }
    }
}
