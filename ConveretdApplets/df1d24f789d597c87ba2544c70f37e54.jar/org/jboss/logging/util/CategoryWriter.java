// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging.util;

import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.Priority;
import org.apache.log4j.Category;
import java.io.PrintWriter;

public class CategoryWriter extends PrintWriter
{
    private Category category;
    private Priority priority;
    private boolean inWrite;
    private boolean issuedWarning;
    
    public CategoryWriter(final Category category) {
        this(category, Priority.INFO);
    }
    
    public CategoryWriter(final Category category, final Priority priority) {
        super(new InternalCategoryWriter(category, priority), true);
    }
    
    static class InternalCategoryWriter extends Writer
    {
        private Category category;
        private Priority priority;
        private boolean closed;
        
        public InternalCategoryWriter(final Category category, final Priority priority) {
            this.lock = category;
            this.category = category;
            this.priority = priority;
        }
        
        public void write(final char[] cbuf, final int off, int len) throws IOException {
            if (this.closed) {
                throw new IOException("Called write on closed Writer");
            }
            while (len > 0 && (cbuf[len - 1] == '\n' || cbuf[len - 1] == '\r')) {
                --len;
            }
            if (len > 0) {
                this.category.log(this.priority, (Object)String.copyValueOf(cbuf, off, len));
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
