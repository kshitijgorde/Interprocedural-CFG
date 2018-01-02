// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.spi.LoggingEvent;
import java.io.Writer;
import org.apache.log4j.helpers.OptionConverter;
import java.io.File;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.CountingQuietWriter;
import java.io.IOException;

public class RollingFileAppender extends FileAppender
{
    protected long maxFileSize;
    protected int maxBackupIndex;
    
    public RollingFileAppender() {
        this.maxFileSize = 10485760L;
        this.maxBackupIndex = 1;
    }
    
    public RollingFileAppender(final Layout layout, final String s, final boolean b) throws IOException {
        super(layout, s, b);
        this.maxFileSize = 10485760L;
        this.maxBackupIndex = 1;
    }
    
    public RollingFileAppender(final Layout layout, final String s) throws IOException {
        super(layout, s);
        this.maxFileSize = 10485760L;
        this.maxBackupIndex = 1;
    }
    
    public int getMaxBackupIndex() {
        return this.maxBackupIndex;
    }
    
    public long getMaximumFileSize() {
        return this.maxFileSize;
    }
    
    public void rollOver() {
        LogLog.debug("rolling over count=" + ((CountingQuietWriter)super.qw).getCount());
        LogLog.debug("maxBackupIndex=" + this.maxBackupIndex);
        if (this.maxBackupIndex > 0) {
            final File file = new File(super.fileName + '.' + this.maxBackupIndex);
            if (file.exists()) {
                file.delete();
            }
            for (int i = this.maxBackupIndex - 1; i >= 1; --i) {
                final File file2 = new File(super.fileName + "." + i);
                if (file2.exists()) {
                    final File file3 = new File(super.fileName + '.' + (i + 1));
                    LogLog.debug("Renaming file " + file2 + " to " + file3);
                    file2.renameTo(file3);
                }
            }
            final File file4 = new File(super.fileName + "." + 1);
            this.closeFile();
            final File file5 = new File(super.fileName);
            LogLog.debug("Renaming file " + file5 + " to " + file4);
            file5.renameTo(file4);
        }
        try {
            this.setFile(super.fileName, false, super.bufferedIO, super.bufferSize);
        }
        catch (IOException ex) {
            LogLog.error("setFile(" + super.fileName + ", false) call failed.", ex);
        }
    }
    
    public synchronized void setFile(final String s, final boolean b, final boolean b2, final int n) throws IOException {
        super.setFile(s, b, super.bufferedIO, super.bufferSize);
        if (b) {
            ((CountingQuietWriter)super.qw).setCount(new File(s).length());
        }
    }
    
    public void setMaxBackupIndex(final int maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }
    
    public void setMaximumFileSize(final long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
    
    public void setMaxFileSize(final String s) {
        this.maxFileSize = OptionConverter.toFileSize(s, this.maxFileSize + 1L);
    }
    
    protected void setQWForFiles(final Writer writer) {
        super.qw = new CountingQuietWriter(writer, super.errorHandler);
    }
    
    protected void subAppend(final LoggingEvent loggingEvent) {
        super.subAppend(loggingEvent);
        if (super.fileName != null && ((CountingQuietWriter)super.qw).getCount() >= this.maxFileSize) {
            this.rollOver();
        }
    }
}
