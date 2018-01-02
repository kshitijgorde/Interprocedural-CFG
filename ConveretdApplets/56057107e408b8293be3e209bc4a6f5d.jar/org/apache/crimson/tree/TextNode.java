// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.Text;

class TextNode extends DataNode implements Text
{
    public TextNode() {
    }
    
    public TextNode(final char[] buf, final int offset, final int len) {
        super(buf, offset, len);
    }
    
    public TextNode(final String s) {
        super(s);
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        int start = 0;
        int last = 0;
        if (super.data == null) {
            System.err.println("Null text data??");
            return;
        }
        while (last < super.data.length) {
            final char c = super.data[last];
            if (c == '<') {
                out.write(super.data, start, last - start);
                start = last + 1;
                out.write("&lt;");
            }
            else if (c == '>') {
                out.write(super.data, start, last - start);
                start = last + 1;
                out.write("&gt;");
            }
            else if (c == '&') {
                out.write(super.data, start, last - start);
                start = last + 1;
                out.write("&amp;");
            }
            ++last;
        }
        out.write(super.data, start, last - start);
    }
    
    public void joinNextText() {
        final Node next = this.getNextSibling();
        if (next == null || next.getNodeType() != 3) {
            return;
        }
        this.getParentNode().removeChild(next);
        final char[] nextText = ((TextNode)next).getText();
        final char[] tmp = new char[super.data.length + nextText.length];
        System.arraycopy(super.data, 0, tmp, 0, super.data.length);
        System.arraycopy(nextText, 0, tmp, super.data.length, nextText.length);
        super.data = tmp;
    }
    
    public short getNodeType() {
        return 3;
    }
    
    public Text splitText(final int offset) throws DOMException {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        TextNode retval;
        try {
            retval = new TextNode(super.data, offset, super.data.length - offset);
        }
        catch (ArrayIndexOutOfBoundsException ae) {
            throw new DomEx((short)1);
        }
        catch (NegativeArraySizeException nae) {
            throw new DomEx((short)1);
        }
        this.getParentNode().insertBefore(retval, this.getNextSibling());
        final char[] delta = new char[offset];
        System.arraycopy(super.data, 0, delta, 0, offset);
        super.data = delta;
        return retval;
    }
    
    public Node cloneNode(final boolean deep) {
        final TextNode retval = new TextNode(super.data, 0, super.data.length);
        retval.setOwnerDocument((XmlDocument)this.getOwnerDocument());
        return retval;
    }
    
    public String getNodeName() {
        return "#text";
    }
}
