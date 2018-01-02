// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.QName;
import java.io.IOException;
import org.apache.xerces.xni.XMLLocator;

public abstract class XMLEntityScanner implements XMLLocator
{
    public abstract String getBaseSystemId();
    
    public abstract void setEncoding(final String p0) throws IOException;
    
    public abstract boolean isExternal();
    
    public abstract int peekChar() throws IOException;
    
    public abstract int scanChar() throws IOException;
    
    public abstract String scanNmtoken() throws IOException;
    
    public abstract String scanName() throws IOException;
    
    public abstract boolean scanQName(final QName p0) throws IOException;
    
    public abstract int scanContent(final XMLString p0) throws IOException;
    
    public abstract int scanLiteral(final int p0, final XMLString p1) throws IOException;
    
    public abstract boolean scanData(final String p0, final XMLStringBuffer p1) throws IOException;
    
    public abstract boolean skipChar(final int p0) throws IOException;
    
    public abstract boolean skipSpaces() throws IOException;
    
    public abstract boolean skipString(final String p0) throws IOException;
    
    public abstract int getColumnNumber();
    
    public abstract int getLineNumber();
    
    public abstract String getLiteralSystemId();
    
    public abstract String getExpandedSystemId();
    
    public abstract String getPublicId();
}
