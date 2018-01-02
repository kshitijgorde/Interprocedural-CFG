// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;
import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.ProcessingInstruction;

final class PINode extends NodeBase implements ProcessingInstruction
{
    private String target;
    private char[] data;
    
    public PINode() {
    }
    
    public PINode(final String target, final String text) {
        this.data = text.toCharArray();
        this.target = target;
    }
    
    PINode(final String target, final char[] buf, final int offset, final int len) {
        System.arraycopy(buf, offset, this.data = new char[len], 0, len);
        this.target = target;
    }
    
    public short getNodeType() {
        return 7;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(final String target) {
        this.target = target;
    }
    
    public String getData() {
        return new String(this.data);
    }
    
    public void setData(final String data) {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        this.data = data.toCharArray();
    }
    
    public String getNodeValue() {
        return this.getData();
    }
    
    public void setNodeValue(final String data) {
        this.setData(data);
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        out.write("<?");
        out.write(this.target);
        if (this.data != null) {
            out.write(32);
            out.write(this.data);
        }
        out.write("?>");
    }
    
    public Node cloneNode(final boolean deep) {
        final PINode retval = new PINode(this.target, this.data, 0, this.data.length);
        retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        return retval;
    }
    
    public String getNodeName() {
        return this.target;
    }
}
