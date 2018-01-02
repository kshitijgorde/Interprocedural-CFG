// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xni.XMLLocator;

public class SimpleLocator implements XMLLocator
{
    String lsid;
    String esid;
    int line;
    int column;
    
    public SimpleLocator() {
    }
    
    public SimpleLocator(final String lsid, final String esid, final int line, final int column) {
        this.line = line;
        this.column = column;
        this.lsid = lsid;
        this.esid = esid;
    }
    
    public void setValues(final String lsid, final String esid, final int line, final int column) {
        this.line = line;
        this.column = column;
        this.lsid = lsid;
        this.esid = esid;
    }
    
    public int getLineNumber() {
        return this.line;
    }
    
    public int getColumnNumber() {
        return this.column;
    }
    
    public String getPublicId() {
        return null;
    }
    
    public String getExpandedSystemId() {
        return this.esid;
    }
    
    public String getLiteralSystemId() {
        return this.lsid;
    }
    
    public String getBaseSystemId() {
        return null;
    }
}
