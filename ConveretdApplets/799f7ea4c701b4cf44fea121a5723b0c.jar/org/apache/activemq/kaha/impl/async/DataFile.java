// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import org.apache.activemq.util.IOHelper;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import org.apache.activemq.util.LinkedNode;

public class DataFile extends LinkedNode implements Comparable<DataFile>
{
    protected final File file;
    protected final Integer dataFileId;
    protected final int preferedSize;
    protected int length;
    protected int referenceCount;
    
    DataFile(final File file, final int number, final int preferedSize) {
        this.file = file;
        this.preferedSize = preferedSize;
        this.dataFileId = number;
        this.length = (int)(file.exists() ? file.length() : 0L);
    }
    
    File getFile() {
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
    
    public synchronized int increment() {
        return ++this.referenceCount;
    }
    
    public synchronized int decrement() {
        return --this.referenceCount;
    }
    
    public synchronized int getReferenceCount() {
        return this.referenceCount;
    }
    
    public synchronized boolean isUnused() {
        return this.referenceCount <= 0;
    }
    
    @Override
    public synchronized String toString() {
        final String result = this.file.getName() + " number = " + this.dataFileId + " , length = " + this.length + " refCount = " + this.referenceCount;
        return result;
    }
    
    public synchronized RandomAccessFile openRandomAccessFile(final boolean appender) throws IOException {
        final RandomAccessFile rc = new RandomAccessFile(this.file, "rw");
        if (appender && this.length < this.preferedSize) {
            try {
                rc.setLength(this.preferedSize);
            }
            catch (IOException ioe) {
                try {
                    rc.close();
                }
                catch (Exception ex) {}
                throw ioe;
            }
        }
        return rc;
    }
    
    public synchronized void closeRandomAccessFile(final RandomAccessFile file) throws IOException {
        if (this.length != file.length()) {
            file.setLength(this.getLength());
        }
        file.close();
    }
    
    public synchronized boolean delete() throws IOException {
        return this.file.delete();
    }
    
    public synchronized void move(final File targetDirectory) throws IOException {
        IOHelper.moveFile(this.file, targetDirectory);
    }
    
    @Override
    public int compareTo(final DataFile df) {
        return this.dataFileId - df.dataFileId;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean result = false;
        if (o instanceof DataFile) {
            result = (this.compareTo((DataFile)o) == 0);
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return this.dataFileId;
    }
}
