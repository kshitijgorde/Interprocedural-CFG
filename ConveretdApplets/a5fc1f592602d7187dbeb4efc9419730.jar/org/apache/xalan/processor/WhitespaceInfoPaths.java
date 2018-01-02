// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.Stylesheet;
import java.util.Vector;
import org.apache.xalan.templates.WhiteSpaceInfo;

public class WhitespaceInfoPaths extends WhiteSpaceInfo
{
    private Vector m_elements;
    
    public void setElements(final Vector elems) {
        this.m_elements = elems;
    }
    
    Vector getElements() {
        return this.m_elements;
    }
    
    public void clearElements() {
        this.m_elements = null;
    }
    
    public WhitespaceInfoPaths(final Stylesheet thisSheet) {
        super(thisSheet);
        this.setStylesheet(thisSheet);
    }
}
