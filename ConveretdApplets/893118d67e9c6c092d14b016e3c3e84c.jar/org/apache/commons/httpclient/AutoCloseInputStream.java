// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

class AutoCloseInputStream extends FilterInputStream
{
    private ResponseConsumedWatcher watcher;
    private boolean selfClosed;
    private boolean streamOpen;
    
    public AutoCloseInputStream(final InputStream in, final ResponseConsumedWatcher watcher) {
        super(in);
        this.watcher = null;
        this.selfClosed = false;
        this.streamOpen = true;
        this.watcher = watcher;
    }
    
    public void close() throws IOException {
        if (!this.selfClosed) {
            this.selfClosed = true;
            this.notifyWatcher();
        }
    }
    
    public int read() throws IOException {
        int l = -1;
        if (this.isReadAllowed()) {
            l = super.read();
            this.checkClose(l);
        }
        return l;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        int l = -1;
        if (this.isReadAllowed()) {
            l = super.read(b, off, len);
            this.checkClose(l);
        }
        return l;
    }
    
    public int read(final byte[] b) throws IOException {
        int l = -1;
        if (this.isReadAllowed()) {
            l = super.read(b);
            this.checkClose(l);
        }
        return l;
    }
    
    private boolean isReadAllowed() throws IOException {
        if (!this.streamOpen && this.selfClosed) {
            throw new IOException("Attempted read on closed stream.");
        }
        return this.streamOpen;
    }
    
    private void checkClose(final int readResult) throws IOException {
        if (readResult == -1) {
            this.notifyWatcher();
        }
    }
    
    private void notifyWatcher() throws IOException {
        if (this.streamOpen) {
            super.close();
            this.streamOpen = false;
            if (this.watcher != null) {
                this.watcher.responseConsumed();
            }
        }
    }
}
