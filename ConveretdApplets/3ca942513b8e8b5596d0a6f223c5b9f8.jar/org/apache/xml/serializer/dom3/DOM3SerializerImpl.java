// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.xml.serializer.utils.WrappedRuntimeException;
import org.w3c.dom.Node;
import org.apache.xml.serializer.SerializationHandler;
import org.w3c.dom.ls.LSSerializerFilter;
import org.w3c.dom.DOMErrorHandler;
import org.apache.xml.serializer.DOM3Serializer;

public final class DOM3SerializerImpl implements DOM3Serializer
{
    private DOMErrorHandler fErrorHandler;
    private LSSerializerFilter fSerializerFilter;
    private char[] fNewLine;
    private SerializationHandler fSerializationHandler;
    
    public DOM3SerializerImpl(final SerializationHandler handler) {
        this.fSerializationHandler = handler;
    }
    
    public DOMErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }
    
    public LSSerializerFilter getNodeFilter() {
        return this.fSerializerFilter;
    }
    
    public char[] getNewLine() {
        return this.fNewLine;
    }
    
    public void serializeDOM3(final Node node) throws IOException {
        try {
            final DOM3TreeWalker walker = new DOM3TreeWalker(this.fSerializationHandler, this.fErrorHandler, this.fSerializerFilter, this.fNewLine);
            walker.traverse(node);
        }
        catch (SAXException se) {
            throw new WrappedRuntimeException(se);
        }
    }
    
    public void setErrorHandler(final DOMErrorHandler handler) {
        this.fErrorHandler = handler;
    }
    
    public void setNodeFilter(final LSSerializerFilter filter) {
        this.fSerializerFilter = filter;
    }
    
    public void setSerializationHandler(final SerializationHandler handler) {
        this.fSerializationHandler = handler;
    }
    
    public void setNewLine(final char[] newLine) {
        this.fNewLine = newLine;
    }
}
