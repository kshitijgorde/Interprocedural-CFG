// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.dom3.DOMLocator;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.dom.DOMLocatorImpl;
import org.apache.xerces.dom3.DOMError;
import org.apache.xerces.xni.parser.XMLParseException;
import java.io.OutputStream;
import org.apache.xerces.dom.DOMErrorImpl;
import org.w3c.dom.Node;
import java.io.PrintWriter;
import org.apache.xerces.dom3.DOMErrorHandler;
import org.apache.xerces.xni.parser.XMLErrorHandler;

public class DOMErrorHandlerWrapper implements XMLErrorHandler, DOMErrorHandler
{
    protected DOMErrorHandler fDomErrorHandler;
    boolean eStatus;
    protected PrintWriter fOut;
    public Node fCurrentNode;
    protected final DOMErrorImpl fDOMError;
    
    public DOMErrorHandlerWrapper() {
        this.eStatus = true;
        this.fDOMError = new DOMErrorImpl();
        this.fOut = new PrintWriter(System.err);
    }
    
    public DOMErrorHandlerWrapper(final DOMErrorHandler domErrorHandler) {
        this.eStatus = true;
        this.fDOMError = new DOMErrorImpl();
        this.fDomErrorHandler = domErrorHandler;
    }
    
    public void setErrorHandler(final DOMErrorHandler errorHandler) {
        this.fDomErrorHandler = errorHandler;
    }
    
    public DOMErrorHandler getErrorHandler() {
        return this.fDomErrorHandler;
    }
    
    public void warning(final String domain, final String key, final XMLParseException exception) throws XNIException {
        this.fDOMError.fSeverity = 0;
        this.fDOMError.fException = exception;
        this.fDOMError.fMessage = exception.getMessage();
        final DOMLocatorImpl locator = this.fDOMError.fLocator;
        if (locator != null) {
            locator.fColumnNumber = exception.getColumnNumber();
            locator.fLineNumber = exception.getLineNumber();
            locator.fUri = exception.getExpandedSystemId();
            locator.fErrorNode = this.fCurrentNode;
        }
        this.fDomErrorHandler.handleError(this.fDOMError);
    }
    
    public void error(final String domain, final String key, final XMLParseException exception) throws XNIException {
        this.fDOMError.fSeverity = 1;
        this.fDOMError.fException = exception;
        this.fDOMError.fMessage = exception.getMessage();
        final DOMLocatorImpl locator = this.fDOMError.fLocator;
        if (locator != null) {
            locator.fColumnNumber = exception.getColumnNumber();
            locator.fLineNumber = exception.getLineNumber();
            locator.fUri = exception.getExpandedSystemId();
            locator.fErrorNode = this.fCurrentNode;
        }
        this.fDomErrorHandler.handleError(this.fDOMError);
    }
    
    public void fatalError(final String domain, final String key, final XMLParseException exception) throws XNIException {
        this.fDOMError.fSeverity = 2;
        this.fDOMError.fException = exception;
        this.fDOMError.fMessage = exception.getMessage();
        final DOMLocatorImpl locator = this.fDOMError.fLocator;
        if (locator != null) {
            locator.fColumnNumber = exception.getColumnNumber();
            locator.fLineNumber = exception.getLineNumber();
            locator.fUri = exception.getExpandedSystemId();
            locator.fErrorNode = this.fCurrentNode;
        }
        this.fDomErrorHandler.handleError(this.fDOMError);
    }
    
    public boolean handleError(final DOMError error) {
        this.printError(error);
        return this.eStatus;
    }
    
    private void printError(final DOMError error) {
        final int severity = error.getSeverity();
        this.fOut.print("[");
        if (severity == 0) {
            this.fOut.print("Warning");
        }
        else if (severity == 1) {
            this.fOut.print("Error");
        }
        else {
            this.fOut.print("FatalError");
            this.eStatus = false;
        }
        this.fOut.print("] ");
        final DOMLocator locator = error.getLocation();
        if (locator != null) {
            this.fOut.print(locator.getLineNumber());
            this.fOut.print(":");
            this.fOut.print(locator.getColumnNumber());
            this.fOut.print(":");
            this.fOut.print(locator.getOffset());
            final Node node = locator.getErrorNode();
            if (node != null) {
                this.fOut.print("[");
                this.fOut.print(node.getNodeName());
                this.fOut.print("]");
            }
            String systemId = locator.getUri();
            if (systemId != null) {
                final int index = systemId.lastIndexOf(47);
                if (index != -1) {
                    systemId = systemId.substring(index + 1);
                }
                this.fOut.print(": ");
                this.fOut.print(systemId);
            }
        }
        this.fOut.print(":");
        this.fOut.print(error.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }
}
