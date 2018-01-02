// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLParseException;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.xerces.xni.parser.XMLErrorHandler;

public class DefaultErrorHandler implements XMLErrorHandler
{
    protected PrintWriter fOut;
    
    public DefaultErrorHandler() {
        this(new PrintWriter(System.err));
    }
    
    public DefaultErrorHandler(final PrintWriter out) {
        this.fOut = out;
    }
    
    public void warning(final String domain, final String key, final XMLParseException ex) throws XNIException {
        this.printError("Warning", ex);
    }
    
    public void error(final String domain, final String key, final XMLParseException ex) throws XNIException {
        this.printError("Error", ex);
    }
    
    public void fatalError(final String domain, final String key, final XMLParseException ex) throws XNIException {
        this.printError("Fatal Error", ex);
        throw ex;
    }
    
    private void printError(final String type, final XMLParseException ex) {
        this.fOut.print("[");
        this.fOut.print(type);
        this.fOut.print("] ");
        String systemId = ex.getExpandedSystemId();
        if (systemId != null) {
            final int index = systemId.lastIndexOf(47);
            if (index != -1) {
                systemId = systemId.substring(index + 1);
            }
            this.fOut.print(systemId);
        }
        this.fOut.print(':');
        this.fOut.print(ex.getLineNumber());
        this.fOut.print(':');
        this.fOut.print(ex.getColumnNumber());
        this.fOut.print(": ");
        this.fOut.print(ex.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }
}
