// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.CDATASection;

public class CDATASectionImpl extends TextImpl implements CDATASection
{
    static final long serialVersionUID = 2372071297878177780L;
    
    public CDATASectionImpl(final CoreDocumentImpl ownerDoc, final String data) {
        super(ownerDoc, data);
    }
    
    public short getNodeType() {
        return 4;
    }
    
    public String getNodeName() {
        return "#cdata-section";
    }
}
