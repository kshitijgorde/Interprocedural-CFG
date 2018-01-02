// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;
import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.Comment;

class CommentNode extends DataNode implements Comment
{
    public CommentNode() {
    }
    
    public CommentNode(final String data) {
        super(data);
    }
    
    CommentNode(final char[] buf, final int offset, final int len) {
        super(buf, offset, len);
    }
    
    public short getNodeType() {
        return 8;
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        out.write("<!--");
        if (super.data != null) {
            boolean sawDash = false;
            for (int length = super.data.length, i = 0; i < length; ++i) {
                if (super.data[i] == '-') {
                    if (!sawDash) {
                        sawDash = true;
                        out.write(45);
                        continue;
                    }
                    out.write(32);
                }
                sawDash = false;
                out.write(super.data[i]);
            }
            if (super.data[super.data.length - 1] == '-') {
                out.write(32);
            }
        }
        out.write("-->");
    }
    
    public Node cloneNode(final boolean deep) {
        final CommentNode retval = new CommentNode(super.data, 0, super.data.length);
        retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        return retval;
    }
    
    public String getNodeName() {
        return "#comment";
    }
}
