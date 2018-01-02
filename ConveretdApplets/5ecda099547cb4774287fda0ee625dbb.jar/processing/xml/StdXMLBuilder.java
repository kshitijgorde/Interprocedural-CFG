// 
// Decompiled by Procyon v0.5.30
// 

package processing.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

public class StdXMLBuilder
{
    private Stack<XMLElement> stack;
    private XMLElement root;
    private XMLElement parent;
    
    public StdXMLBuilder() {
        this.stack = null;
        this.root = null;
    }
    
    public StdXMLBuilder(final XMLElement parent) {
        this.parent = parent;
    }
    
    protected void finalize() throws Throwable {
        this.root = null;
        this.stack.clear();
        this.stack = null;
        super.finalize();
    }
    
    public void startBuilding(final String s, final int n) {
        this.stack = new Stack<XMLElement>();
        this.root = null;
    }
    
    public void newProcessingInstruction(final String s, final Reader reader) {
    }
    
    public void startElement(final String s, final String s2, final String s3, final String s4, final int n) {
        String string = s;
        if (s2 != null) {
            string = s2 + ':' + s;
        }
        if (this.stack.empty()) {
            this.parent.set(string, s3, s4, n);
            this.stack.push(this.parent);
            this.root = this.parent;
        }
        else {
            final XMLElement xmlElement = this.stack.peek();
            final XMLElement xmlElement2 = new XMLElement(string, s3, s4, n);
            xmlElement.addChild(xmlElement2);
            this.stack.push(xmlElement2);
        }
    }
    
    public void elementAttributesProcessed(final String s, final String s2, final String s3) {
    }
    
    public void endElement(final String s, final String s2, final String s3) {
        final XMLElement xmlElement = this.stack.pop();
        if (xmlElement.getChildCount() == 1) {
            final XMLElement childAtIndex = xmlElement.getChildAtIndex(0);
            if (childAtIndex.getLocalName() == null) {
                xmlElement.setContent(childAtIndex.getContent());
                xmlElement.removeChildAtIndex(0);
            }
        }
    }
    
    public void addAttribute(final String s, final String s2, final String s3, final String s4, final String s5) throws Exception {
        String string = s;
        if (s2 != null) {
            string = s2 + ':' + s;
        }
        final XMLElement xmlElement = this.stack.peek();
        if (xmlElement.hasAttribute(string)) {
            throw new XMLParseException(xmlElement.getSystemID(), xmlElement.getLineNr(), "Duplicate attribute: " + s);
        }
        if (s2 != null) {
            xmlElement.setAttribute(string, s3, s4);
        }
        else {
            xmlElement.setAttribute(string, s4);
        }
    }
    
    public void addPCData(final Reader reader, final String s, final int n) {
        int n2 = 2048;
        int n3 = 0;
        final StringBuffer sb = new StringBuffer(n2);
        final char[] array = new char[n2];
        while (true) {
            if (n3 >= n2) {
                n2 *= 2;
                sb.ensureCapacity(n2);
            }
            int read;
            try {
                read = reader.read(array);
            }
            catch (IOException ex) {
                break;
            }
            if (read < 0) {
                break;
            }
            sb.append(array, 0, read);
            n3 += read;
        }
        final XMLElement xmlElement = new XMLElement(null, null, s, n);
        xmlElement.setContent(sb.toString());
        if (!this.stack.empty()) {
            this.stack.peek().addChild(xmlElement);
        }
    }
    
    public Object getResult() {
        return this.root;
    }
}
