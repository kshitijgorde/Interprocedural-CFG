// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index;

import java.io.IOException;
import java.io.DataOutput;
import org.apache.activemq.kaha.impl.DataManager;
import java.io.RandomAccessFile;
import org.apache.activemq.util.DataByteArrayOutputStream;

class StoreIndexWriter
{
    protected final DataByteArrayOutputStream dataOut;
    protected final RandomAccessFile file;
    protected final String name;
    protected final DataManager redoLog;
    
    StoreIndexWriter(final RandomAccessFile file) {
        this(file, null, null);
    }
    
    public StoreIndexWriter(final RandomAccessFile file, final String indexName, final DataManager redoLog) {
        this.dataOut = new DataByteArrayOutputStream();
        this.file = file;
        this.name = indexName;
        this.redoLog = redoLog;
    }
    
    void storeItem(final IndexItem indexItem) throws IOException {
        if (this.redoLog != null) {
            final RedoStoreIndexItem redo = new RedoStoreIndexItem(this.name, indexItem.getOffset(), indexItem);
            this.redoLog.storeRedoItem(redo);
        }
        this.dataOut.reset();
        indexItem.write(this.dataOut);
        this.file.seek(indexItem.getOffset());
        this.file.write(this.dataOut.getData(), 0, 51);
    }
    
    void updateIndexes(final IndexItem indexItem) throws IOException {
        if (this.redoLog != null) {
            final RedoStoreIndexItem redo = new RedoStoreIndexItem(this.name, indexItem.getOffset(), indexItem);
            this.redoLog.storeRedoItem(redo);
        }
        this.dataOut.reset();
        indexItem.updateIndexes(this.dataOut);
        this.file.seek(indexItem.getOffset());
        this.file.write(this.dataOut.getData(), 0, 19);
    }
    
    public void redoStoreItem(final RedoStoreIndexItem redo) throws IOException {
        this.dataOut.reset();
        redo.getIndexItem().write(this.dataOut);
        this.file.seek(redo.getOffset());
        this.file.write(this.dataOut.getData(), 0, 51);
    }
}
