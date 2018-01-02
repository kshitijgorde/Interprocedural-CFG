// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.journal;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

public class ReadOnlyDataFile extends DataFile
{
    ReadOnlyDataFile(final File file, final int number, final int preferedSize) {
        super(file, number, preferedSize);
    }
    
    public RandomAccessFile openRandomAccessFile(final boolean appender) throws IOException {
        return new RandomAccessFile(this.file, "r");
    }
    
    public void closeRandomAccessFile(final RandomAccessFile file) throws IOException {
        file.close();
    }
    
    public synchronized boolean delete() throws IOException {
        throw new RuntimeException("Not valid on a read only file.");
    }
    
    public synchronized void move(final File targetDirectory) throws IOException {
        throw new RuntimeException("Not valid on a read only file.");
    }
}
