// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform.dom;

import org.w3c.dom.Node;
import javax.xml.transform.Source;

public class DOMSource implements Source
{
    public static final String FEATURE = "http://javax.xml.transform.dom.DOMSource/feature";
    private Node node;
    String baseID;
    
    public DOMSource() {
    }
    
    public DOMSource(final Node n) {
        this.setNode(n);
    }
    
    public DOMSource(final Node node, final String systemID) {
        this.setNode(node);
        this.setSystemId(systemID);
    }
    
    public void setNode(final Node node) {
        this.node = node;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public void setSystemId(final String baseID) {
        this.baseID = baseID;
    }
    
    public String getSystemId() {
        return this.baseID;
    }
}
