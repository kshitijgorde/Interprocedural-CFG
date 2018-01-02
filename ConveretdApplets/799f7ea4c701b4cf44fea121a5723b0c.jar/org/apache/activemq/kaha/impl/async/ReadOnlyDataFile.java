// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.async;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

public class ReadOnlyDataFile extends DataFile
{
    ReadOnlyDataFile(final File file, final int number, final int preferedSize) {
        super(file, number, preferedSize);
    }
    
    @Override
    public RandomAccessFile openRandomAccessFile(final boolean appender) throws IOException {
        final RandomAccessFile rc = new RandomAccessFile(this.file, "r");
        if (appender && this.length < this.preferedSize) {
            rc.setLength(this.preferedSize);
        }
        return rc;
    }
    
    @Override
    public void closeRandomAccessFile(final RandomAccessFile file) throws IOException {
        file.close();
    }
    
    @Override
    public synchronized boolean delete() throws IOException {
        throw new RuntimeException("Not valid on a read only file.");
    }
    
    @Override
    public synchronized void move(final File targetDirectory) throws IOException {
        throw new RuntimeException("Not valid on a read only file.");
    }
}
