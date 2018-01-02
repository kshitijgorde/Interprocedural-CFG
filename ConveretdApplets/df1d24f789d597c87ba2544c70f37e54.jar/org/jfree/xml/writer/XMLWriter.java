// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import java.util.Properties;
import java.io.IOException;
import java.io.Writer;

public class XMLWriter extends XMLWriterSupport
{
    private Writer writer;
    
    public XMLWriter(final Writer writer) {
        this(writer, "    ");
    }
    
    public XMLWriter(final Writer writer, final String s) {
        this.writer = writer;
    }
    
    public void writeXmlDeclaration() throws IOException {
        this.writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        this.writer.write(XMLWriterSupport.getLineSeparator());
    }
    
    public void writeTag(final String s, final boolean b) throws IOException {
        if (b) {
            this.writeTag(this.writer, s, new AttributeList(), b);
        }
        else {
            this.writeTag(this.writer, s);
        }
    }
    
    public void writeCloseTag(final String s) throws IOException {
        this.writeCloseTag(this.writer, s);
    }
    
    public void writeTag(final String s, final String s2, final String s3, final boolean b) throws IOException {
        this.writeTag(this.writer, s, s2, s3, b);
    }
    
    public void writeTag(final String s, final AttributeList list, final boolean b) throws IOException {
        this.writeTag(this.writer, s, list, b);
    }
    
    public void writeTag(final String s, final Properties properties, final boolean b) throws IOException {
        this.writeTag(this.writer, s, properties, b);
    }
    
    public void writeText(final String s) throws IOException {
        this.writer.write(s);
    }
    
    public void close() throws IOException {
        this.writer.close();
    }
}
