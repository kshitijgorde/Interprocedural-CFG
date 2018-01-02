// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.CDATASection;

public class CDATASectionImpl extends TextImpl implements CDATASection
{
    public CDATASectionImpl(final DocumentImpl doc, final String data) {
        super(doc, data);
    }
    
    public CDATASectionImpl(final DocumentImpl doc, final char[] ch, final int start, final int length) {
        super(doc, ch, start, length);
    }
    
    public String getLocalName() {
        return "#cdata-section";
    }
    
    public String getNodeName() {
        return "#cdata-section";
    }
    
    public short getNodeType() {
        return 4;
    }
}
