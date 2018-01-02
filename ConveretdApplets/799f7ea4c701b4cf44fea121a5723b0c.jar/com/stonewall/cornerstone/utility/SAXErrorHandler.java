// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

public class SAXErrorHandler implements ErrorHandler
{
    @Override
    public void warning(final SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.getMessage());
        System.out.println(" at line " + exception.getLineNumber() + ", column " + exception.getColumnNumber());
        System.out.println(" in entity " + exception.getSystemId());
    }
    
    @Override
    public void error(final SAXParseException exception) throws SAXException {
        System.out.println("Error: " + exception.getMessage());
        System.out.println(" at line " + exception.getLineNumber() + ", column " + exception.getColumnNumber());
        System.out.println(" in entity " + exception.getPublicId());
    }
    
    @Override
    public void fatalError(final SAXParseException exception) throws SAXException {
        System.out.println("Fatal Error: " + exception.getMessage());
        System.out.println(" at line " + exception.getLineNumber() + ", column " + exception.getColumnNumber());
        System.out.println(" in entity " + exception.getSystemId());
    }
}
