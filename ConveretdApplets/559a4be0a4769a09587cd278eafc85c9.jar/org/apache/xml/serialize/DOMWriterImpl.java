// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.apache.xerces.util.XMLSymbols;
import org.w3c.dom.ls.DOMWriterFilter;
import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;
import org.apache.xerces.dom3.DOMError;
import org.apache.xerces.dom.DOMErrorImpl;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.io.OutputStream;
import org.apache.xerces.dom3.DOMErrorHandler;
import org.w3c.dom.DOMException;
import java.util.Hashtable;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.NamespaceSupport;
import org.w3c.dom.ls.DOMWriter;

public class DOMWriterImpl implements DOMWriter
{
    private String fEncoding;
    private XMLSerializer serializer;
    
    public DOMWriterImpl(final boolean namespaces) {
        this.serializer = new XMLSerializer();
        this.serializer.fNamespaces = namespaces;
        this.serializer.fNSBinder = new NamespaceSupport();
        this.serializer.fLocalNSBinder = new NamespaceSupport();
        this.serializer.fSymbolTable = new SymbolTable();
        (this.serializer.fFeatures = new Hashtable()).put("normalize-characters", new Boolean(false));
        this.serializer.fFeatures.put("split-cdata-sections", new Boolean(true));
        this.serializer.fFeatures.put("validate", new Boolean(false));
        this.serializer.fFeatures.put("entities", new Boolean(false));
        this.serializer.fFeatures.put("whitespace-in-element-content", new Boolean(true));
        this.serializer.fFeatures.put("discard-default-content", new Boolean(true));
        this.serializer.fFeatures.put("canonical-form", new Boolean(false));
        this.serializer.fFeatures.put("format-pretty-print", new Boolean(false));
    }
    
    public void setFeature(final String name, final boolean state) throws DOMException {
        if (name == null || !this.serializer.fFeatures.containsKey(name)) {
            throw new DOMException((short)8, "Feature " + name + " not found");
        }
        if (this.canSetFeature(name, state)) {
            this.serializer.fFeatures.put(name, new Boolean(state));
            return;
        }
        throw new DOMException((short)9, "Feature " + name + " cannot be set as " + state);
    }
    
    public boolean canSetFeature(final String name, final boolean state) {
        return (!name.equals("normalize-characters") || !state) && (!name.equals("validate") || !state) && (!name.equals("whitespace-in-element-content") || state) && (!name.equals("canonical-form") || !state) && (!name.equals("format-pretty-print") || !state);
    }
    
    public boolean getFeature(final String name) throws DOMException {
        final Boolean state = this.serializer.fFeatures.get(name);
        if (state == null) {
            throw new DOMException((short)8, "Feature " + name + " not found");
        }
        return state;
    }
    
    public String getEncoding() {
        return this.fEncoding;
    }
    
    public void setEncoding(final String encoding) {
        this.serializer._format.setEncoding(encoding);
        this.fEncoding = this.serializer._format.getEncoding();
    }
    
    public DOMErrorHandler getErrorHandler() {
        return this.serializer.fDOMErrorHandler;
    }
    
    public void setErrorHandler(final DOMErrorHandler errorHandler) {
        this.serializer.fDOMErrorHandler = errorHandler;
    }
    
    public boolean writeNode(final OutputStream destination, final Node wnode) {
        this.checkAllFeatures();
        try {
            this.reset();
            this.serializer.setOutputByteStream(destination);
            if (wnode == null) {
                return false;
            }
            if (wnode.getNodeType() == 9) {
                this.serializer.serialize((Document)wnode);
            }
            else if (wnode.getNodeType() == 11) {
                this.serializer.serialize((DocumentFragment)wnode);
            }
            else {
                if (wnode.getNodeType() != 1) {
                    return false;
                }
                this.serializer.serialize((Element)wnode);
            }
        }
        catch (Exception e) {
            if (this.serializer.fDOMErrorHandler != null) {
                final DOMErrorImpl error = new DOMErrorImpl();
                error.fException = e;
                error.fMessage = e.getMessage();
                error.fSeverity = 1;
                this.serializer.fDOMErrorHandler.handleError(error);
            }
        }
        return true;
    }
    
    public String writeToString(final Node wnode) throws DOMException {
        this.checkAllFeatures();
        final StringWriter destination = new StringWriter();
        try {
            this.reset();
            this.serializer.setOutputCharStream(destination);
            if (wnode == null) {
                return null;
            }
            if (wnode.getNodeType() == 9) {
                this.serializer.serialize((Document)wnode);
            }
            else if (wnode.getNodeType() == 11) {
                this.serializer.serialize((DocumentFragment)wnode);
            }
            else {
                if (wnode.getNodeType() != 1) {
                    return null;
                }
                this.serializer.serialize((Element)wnode);
            }
        }
        catch (IOException ioe) {
            throw new DOMException((short)2, "The resulting string is too long to fit in a DOMString: " + ioe.getMessage());
        }
        return destination.toString();
    }
    
    public void setNewLine(final String newLine) {
        this.serializer._format.setLineSeparator(newLine);
    }
    
    public String getNewLine() {
        return this.serializer._format.getLineSeparator();
    }
    
    public DOMWriterFilter getFilter() {
        return null;
    }
    
    public void setFilter(final DOMWriterFilter filter) {
        this.serializer.fDOMFilter = filter;
    }
    
    private boolean reset() {
        this.serializer.reset();
        this.serializer.fNSBinder.reset();
        this.serializer.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
        this.serializer.fNamespaceCounter = 1;
        return true;
    }
    
    private void checkAllFeatures() {
        if (this.getFeature("whitespace-in-element-content")) {
            this.serializer._format.setPreserveSpace(true);
        }
        else {
            this.serializer._format.setPreserveSpace(false);
        }
    }
}
