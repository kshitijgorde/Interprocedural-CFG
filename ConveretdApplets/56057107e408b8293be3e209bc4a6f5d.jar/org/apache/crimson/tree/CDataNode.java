// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;
import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.CDATASection;

class CDataNode extends TextNode implements CDATASection
{
    public CDataNode() {
    }
    
    public CDataNode(final char[] buf, final int offset, final int len) {
        super(buf, offset, len);
    }
    
    public CDataNode(final String s) {
        super(s);
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        out.write("<![CDATA[");
        for (int i = 0; i < super.data.length; ++i) {
            final char c = super.data[i];
            if (c == ']' && i + 2 < super.data.length && super.data[i + 1] == ']' && super.data[i + 2] == '>') {
                out.write("]]]]><![CDATA[>");
            }
            else {
                out.write(c);
            }
        }
        out.write("]]>");
    }
    
    public short getNodeType() {
        return 4;
    }
    
    public Node cloneNode(final boolean deep) {
        final CDataNode retval = new CDataNode(super.data, 0, super.data.length);
        retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        return retval;
    }
    
    public String getNodeName() {
        return "#cdata-section";
    }
}
