// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform.dom;

import org.w3c.dom.Node;
import javax.xml.transform.Result;

public class DOMResult implements Result
{
    public static final String FEATURE = "http://javax.xml.transform.dom.DOMResult/feature";
    private Node node;
    private Node nextSibling;
    private String systemId;
    
    public DOMResult() {
    }
    
    public DOMResult(final Node node) {
        this.setNode(node);
        this.setNextSibling(null);
    }
    
    public DOMResult(final Node node, final Node nextSibling) {
        this.setNode(node);
        this.setNextSibling(nextSibling);
    }
    
    public DOMResult(final Node node, final String systemId) {
        this.setNode(node);
        this.setNextSibling(null);
        this.setSystemId(systemId);
    }
    
    public DOMResult(final Node node, final Node nextSibling, final String systemId) {
        this.setNode(node);
        this.setNextSibling(nextSibling);
        this.setSystemId(systemId);
    }
    
    public void setNode(final Node node) {
        if (this.nextSibling != null) {
            if (node == null) {
                throw new IllegalStateException("nextSibling cannot be contained in a null node.");
            }
            if ((node.compareDocumentPosition(this.nextSibling) & 0x10) == 0x0) {
                throw new IllegalStateException("The nextSibling is not contained by the node.");
            }
        }
        this.node = node;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public void setNextSibling(final Node nextSibling) {
        this.nextSibling = nextSibling;
        if (nextSibling != null) {
            if (this.node == null) {
                throw new IllegalStateException("nextSibling cannot be contained in a null node.");
            }
            if ((this.node.compareDocumentPosition(nextSibling) & 0x10) == 0x0) {
                throw new IllegalArgumentException("The nextSibling is not contained by the node.");
            }
        }
    }
    
    public Node getNextSibling() {
        return this.nextSibling;
    }
    
    public void setSystemId(final String systemId) {
        this.systemId = systemId;
    }
    
    public String getSystemId() {
        return this.systemId;
    }
}
