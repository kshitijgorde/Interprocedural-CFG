// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.io.IOException;
import java.io.Writer;

public class XmlWriteContext
{
    private Writer writer;
    private int indentLevel;
    private boolean prettyOutput;
    
    public XmlWriteContext(final Writer out) {
        this.writer = out;
    }
    
    public XmlWriteContext(final Writer out, final int level) {
        this.writer = out;
        this.prettyOutput = true;
        this.indentLevel = level;
    }
    
    public Writer getWriter() {
        return this.writer;
    }
    
    public boolean isEntityDeclared(final String name) {
        return "amp".equals(name) || "lt".equals(name) || "gt".equals(name) || "quot".equals(name) || "apos".equals(name);
    }
    
    public int getIndentLevel() {
        return this.indentLevel;
    }
    
    public void setIndentLevel(final int level) {
        this.indentLevel = level;
    }
    
    public void printIndent() throws IOException {
        int temp = this.indentLevel;
        if (!this.prettyOutput) {
            return;
        }
        this.writer.write(XmlDocument.eol);
        while (temp-- > 0) {
            this.writer.write(32);
        }
    }
    
    public boolean isPrettyOutput() {
        return this.prettyOutput;
    }
}
