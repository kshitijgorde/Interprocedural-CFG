// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.net.uploader;

import java.util.Iterator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.apache.commons.httpclient.methods.multipart.FilePart;

public class FilePartMonitor extends FilePart implements FileUploader
{
    private static final int BUF_SIZE = 4096;
    private List<FileUploadListener> listeners;
    private FileInputStream input;
    private long bytesSent;
    private File file;
    
    public FilePartMonitor(final File file, final String filename) throws FileNotFoundException {
        super("file", filename, file);
        this.file = file;
        this.input = new FileInputStream(file);
        this.listeners = new LinkedList<FileUploadListener>();
    }
    
    public void addUploaderListener(final FileUploadListener listener) {
        this.listeners.add(listener);
    }
    
    protected void sendData(final OutputStream output) {
        this.bytesSent = 0L;
        UploadState finalState = UploadState.FAILED;
        try {
            final byte[] buffer = new byte[4096];
            int bytesRead = 0;
            this.notifyListeners(UploadState.UPLOADING);
            while ((bytesRead = this.input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
                this.bytesSent += bytesRead;
                this.notifyListeners(UploadState.UPLOADING);
            }
            finalState = UploadState.COMPLETE;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (this.input != null) {
                try {
                    this.input.close();
                }
                catch (Exception ex) {}
            }
            this.notifyListeners(finalState);
        }
    }
    
    private void notifyListeners(final UploadState state) {
        for (final FileUploadListener l : this.listeners) {
            l.updateStatus(this, state);
        }
    }
    
    public long getBytesSent() {
        return this.bytesSent;
    }
    
    public File getFile() {
        return this.file;
    }
}
