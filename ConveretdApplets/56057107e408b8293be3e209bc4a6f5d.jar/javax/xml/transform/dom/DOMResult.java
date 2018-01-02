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
    private String systemId;
    
    public DOMResult() {
    }
    
    public DOMResult(final Node node) {
        this.setNode(node);
    }
    
    public DOMResult(final Node node, final String systemID) {
        this.setNode(node);
        this.setSystemId(systemID);
    }
    
    public void setNode(final Node node) {
        this.node = node;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public void setSystemId(final String systemId) {
        this.systemId = systemId;
    }
    
    public String getSystemId() {
        return this.systemId;
    }
}
