// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.util;

import java.io.IOException;
import java.io.Writer;
import org.jboss.logging.LoggerPlugin;
import java.io.PrintWriter;

public class LoggerPluginWriter extends PrintWriter
{
    public LoggerPluginWriter(final LoggerPlugin logger) {
        super(new PluginWriter(logger), true);
    }
    
    static class PluginWriter extends Writer
    {
        private LoggerPlugin logger;
        private boolean closed;
        
        public PluginWriter(final LoggerPlugin logger) {
            this.lock = logger;
            this.logger = logger;
        }
        
        public void write(final char[] cbuf, final int off, int len) throws IOException {
            if (this.closed) {
                throw new IOException("Called write on closed Writer");
            }
            while (len > 0 && (cbuf[len - 1] == '\n' || cbuf[len - 1] == '\r')) {
                --len;
            }
            if (len > 0) {
                this.logger.info(String.copyValueOf(cbuf, off, len));
            }
        }
        
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("Called flush on closed Writer");
            }
        }
        
        public void close() {
            this.closed = true;
        }
    }
}
