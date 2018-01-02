// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.helpers.QuietWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.log4j.helpers.LogLog;
import java.io.IOException;

public class FileAppender extends WriterAppender
{
    protected boolean fileAppend;
    protected String fileName;
    protected boolean bufferedIO;
    protected int bufferSize;
    
    public FileAppender() {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
    }
    
    public FileAppender(final Layout layout, final String s, final boolean b, final boolean b2, final int n) throws IOException {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
        super.layout = layout;
        this.setFile(s, b, b2, n);
    }
    
    public FileAppender(final Layout layout, final String s, final boolean b) throws IOException {
        this.fileAppend = true;
        this.fileName = null;
        this.bufferedIO = false;
        this.bufferSize = 8192;
        super.layout = layout;
        this.setFile(s, b, false, this.bufferSize);
    }
    
    public FileAppender(final Layout layout, final String s) throws IOException {
        this(layout, s, true);
    }
    
    public void setFile(final String s) {
        this.fileName = s.trim();
    }
    
    public boolean getAppend() {
        return this.fileAppend;
    }
    
    public String getFile() {
        return this.fileName;
    }
    
    public void activateOptions() {
        if (this.fileName != null) {
            try {
                this.setFile(this.fileName, this.fileAppend, this.bufferedIO, this.bufferSize);
            }
            catch (IOException ex) {
                super.errorHandler.error("setFile(" + this.fileName + "," + this.fileAppend + ") call failed.", ex, 4);
            }
        }
        else {
            LogLog.warn("File option not set for appender [" + super.name + "].");
            LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
        }
    }
    
    protected void closeFile() {
        if (super.qw != null) {
            try {
                super.qw.close();
            }
            catch (IOException ex) {
                LogLog.error("Could not close " + super.qw, ex);
            }
        }
    }
    
    public boolean getBufferedIO() {
        return this.bufferedIO;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public void setAppend(final boolean fileAppend) {
        this.fileAppend = fileAppend;
    }
    
    public void setBufferedIO(final boolean bufferedIO) {
        this.bufferedIO = bufferedIO;
        if (bufferedIO) {
            super.immediateFlush = false;
        }
    }
    
    public void setBufferSize(final int bufferSize) {
        this.bufferSize = bufferSize;
    }
    
    public synchronized void setFile(final String fileName, final boolean fileAppend, final boolean bufferedIO, final int bufferSize) throws IOException {
        LogLog.debug("setFile called: " + fileName + ", " + fileAppend);
        if (bufferedIO) {
            this.setImmediateFlush(false);
        }
        this.reset();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName, fileAppend);
        }
        catch (FileNotFoundException ex) {
            final String parent = new File(fileName).getParent();
            if (parent == null) {
                throw ex;
            }
            final File file = new File(parent);
            if (file.exists() || !file.mkdirs()) {
                throw ex;
            }
            fileOutputStream = new FileOutputStream(fileName, fileAppend);
        }
        Writer writer = this.createWriter(fileOutputStream);
        if (bufferedIO) {
            writer = new BufferedWriter(writer, bufferSize);
        }
        this.setQWForFiles(writer);
        this.fileName = fileName;
        this.fileAppend = fileAppend;
        this.bufferedIO = bufferedIO;
        this.bufferSize = bufferSize;
        this.writeHeader();
        LogLog.debug("setFile ended");
    }
    
    protected void setQWForFiles(final Writer writer) {
        super.qw = new QuietWriter(writer, super.errorHandler);
    }
    
    protected void reset() {
        this.closeFile();
        this.fileName = null;
        super.reset();
    }
}
