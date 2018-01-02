// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime;

import org.apache.xalan.xsltc.DOM;
import org.xml.sax.AttributeList;

public final class Attributes implements AttributeList
{
    private int _element;
    private DOM _document;
    
    public Attributes(final DOM document, final int element) {
        this._element = element;
        this._document = document;
    }
    
    public int getLength() {
        return 0;
    }
    
    public String getName(final int i) {
        return null;
    }
    
    public String getType(final int i) {
        return null;
    }
    
    public String getType(final String name) {
        return null;
    }
    
    public String getValue(final int i) {
        return null;
    }
    
    public String getValue(final String name) {
        return null;
    }
}
