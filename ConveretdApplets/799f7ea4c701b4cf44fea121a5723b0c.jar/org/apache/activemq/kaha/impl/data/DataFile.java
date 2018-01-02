// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.data;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.File;

class DataFile
{
    private final File file;
    private final Integer number;
    private int referenceCount;
    private RandomAccessFile randomAcessFile;
    private Object writerData;
    private long length;
    private boolean dirty;
    
    DataFile(final File file, final int number) {
        this.file = file;
        this.number = number;
        this.length = (file.exists() ? file.length() : 0L);
    }
    
    Integer getNumber() {
        return this.number;
    }
    
    synchronized RandomAccessFile getRandomAccessFile() throws FileNotFoundException {
        if (this.randomAcessFile == null) {
            this.randomAcessFile = new RandomAccessFile(this.file, "rw");
        }
        return this.randomAcessFile;
    }
    
    synchronized long getLength() {
        return this.length;
    }
    
    synchronized void incrementLength(final int size) {
        this.length += size;
    }
    
    synchronized void purge() throws IOException {
        if (this.randomAcessFile != null) {
            this.randomAcessFile.close();
            this.randomAcessFile = null;
        }
    }
    
    synchronized boolean delete() throws IOException {
        this.purge();
        return this.file.delete();
    }
    
    synchronized void close() throws IOException {
        if (this.randomAcessFile != null) {
            this.randomAcessFile.close();
        }
    }
    
    synchronized int increment() {
        return ++this.referenceCount;
    }
    
    synchronized int decrement() {
        return --this.referenceCount;
    }
    
    synchronized boolean isUnused() {
        return this.referenceCount <= 0;
    }
    
    @Override
    public String toString() {
        final String result = this.file.getName() + " number = " + this.number + " , length = " + this.length + " refCount = " + this.referenceCount;
        return result;
    }
    
    public synchronized Object getWriterData() {
        return this.writerData;
    }
    
    public synchronized void setWriterData(final Object writerData) {
        this.writerData = writerData;
        this.dirty = true;
    }
    
    public synchronized boolean isDirty() {
        return this.dirty;
    }
    
    public synchronized void setDirty(final boolean value) {
        this.dirty = value;
    }
}
