// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.util;

import org.apache.log4j.Priority;
import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.io.PrintWriter;

public class LoggerWriter extends PrintWriter
{
    private Logger logger;
    private Level level;
    private boolean inWrite;
    private boolean issuedWarning;
    
    public LoggerWriter(final Logger logger) {
        this(logger, Level.INFO);
    }
    
    public LoggerWriter(final Logger logger, final Level level) {
        super(new InternalLoggerWriter(logger, level), true);
    }
    
    static class InternalLoggerWriter extends Writer
    {
        private Logger logger;
        private Level level;
        private boolean closed;
        
        public InternalLoggerWriter(final Logger logger, final Level level) {
            this.lock = logger;
            this.logger = logger;
            this.level = level;
        }
        
        public void write(final char[] cbuf, final int off, int len) throws IOException {
            if (this.closed) {
                throw new IOException("Called write on closed Writer");
            }
            while (len > 0 && (cbuf[len - 1] == '\n' || cbuf[len - 1] == '\r')) {
                --len;
            }
            if (len > 0) {
                this.logger.log((Priority)this.level, (Object)String.copyValueOf(cbuf, off, len));
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
