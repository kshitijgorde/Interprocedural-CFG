// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.journal;

import org.apache.kahadb.util.IOHelper;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.kahadb.util.SequenceSet;
import java.io.File;
import org.apache.kahadb.util.LinkedNode;

public class DataFile extends LinkedNode<DataFile> implements Comparable<DataFile>
{
    protected final File file;
    protected final Integer dataFileId;
    protected int length;
    protected final SequenceSet corruptedBlocks;
    
    DataFile(final File file, final int number, final int preferedSize) {
        this.corruptedBlocks = new SequenceSet();
        this.file = file;
        this.dataFileId = number;
        this.length = (int)(file.exists() ? file.length() : 0L);
    }
    
    public File getFile() {
        return this.file;
    }
    
    public Integer getDataFileId() {
        return this.dataFileId;
    }
    
    public synchronized int getLength() {
        return this.length;
    }
    
    public void setLength(final int length) {
        this.length = length;
    }
    
    public synchronized void incrementLength(final int size) {
        this.length += size;
    }
    
    public synchronized String toString() {
        return this.file.getName() + " number = " + this.dataFileId + " , length = " + this.length;
    }
    
    public synchronized RandomAccessFile openRandomAccessFile() throws IOException {
        return new RandomAccessFile(this.file, "rw");
    }
    
    public synchronized void closeRandomAccessFile(final RandomAccessFile file) throws IOException {
        file.close();
    }
    
    public synchronized boolean delete() throws IOException {
        return this.file.delete();
    }
    
    public synchronized void move(final File targetDirectory) throws IOException {
        IOHelper.moveFile(this.file, targetDirectory);
    }
    
    public SequenceSet getCorruptedBlocks() {
        return this.corruptedBlocks;
    }
    
    public int compareTo(final DataFile df) {
        return this.dataFileId - df.dataFileId;
    }
    
    public boolean equals(final Object o) {
        boolean result = false;
        if (o instanceof DataFile) {
            result = (this.compareTo((DataFile)o) == 0);
        }
        return result;
    }
    
    public int hashCode() {
        return this.dataFileId;
    }
}
