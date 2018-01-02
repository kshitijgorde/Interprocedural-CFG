// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import javax.xml.transform.SourceLocator;

public class NodeLocator implements SourceLocator
{
    protected String m_publicId;
    protected String m_systemId;
    protected int m_lineNumber;
    protected int m_columnNumber;
    
    public NodeLocator(final String publicId, final String systemId, final int lineNumber, final int columnNumber) {
        this.m_publicId = publicId;
        this.m_systemId = systemId;
        this.m_lineNumber = lineNumber;
        this.m_columnNumber = columnNumber;
    }
    
    public String getPublicId() {
        return this.m_publicId;
    }
    
    public String getSystemId() {
        return this.m_systemId;
    }
    
    public int getLineNumber() {
        return this.m_lineNumber;
    }
    
    public int getColumnNumber() {
        return this.m_columnNumber;
    }
    
    public String toString() {
        return "file '" + this.m_systemId + "', line #" + this.m_lineNumber + ", column #" + this.m_columnNumber;
    }
}
