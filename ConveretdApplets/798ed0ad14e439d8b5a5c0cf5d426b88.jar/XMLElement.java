import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class XMLElement extends XMLNode
{
    public Hashtable attributes;
    public XMLNode[] children;
    Vector addElement;
    
    public XMLElement() {
    }
    
    public XMLElement(final String element_name, final int n) {
        super.element_name = element_name;
        this.children = new XMLNode[n];
    }
    
    final void I(final XMLNode xmlNode) {
        if (this.addElement == null) {
            this.addElement = new Vector();
        }
        this.addElement.addElement(xmlNode);
    }
    
    protected final void I(final StringBuffer sb) {
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                this.children[i].I(sb);
            }
        }
    }
    
    public final void toString(final StringBuffer sb) {
        if (super.element_name.length() > 0) {
            sb.append("<");
            sb.append(super.element_name);
        }
        final int length = this.children.length;
        if (this.attributes != null) {
            final Enumeration<String> elements = this.attributes.elements();
            final Enumeration<Object> keys = this.attributes.keys();
            while (elements.hasMoreElements()) {
                sb.append(" ");
                sb.append(keys.nextElement());
                sb.append("=\"");
                XMLNode.appendText(sb, elements.nextElement());
                sb.append("\"");
            }
        }
        if (length == 0) {
            if (super.element_name.length() > 0) {
                sb.append(" />");
            }
        }
        else {
            if (super.element_name.length() > 0) {
                sb.append(">");
            }
            for (int i = 0; i < length; ++i) {
                this.children[i].toString(sb);
            }
            if (super.element_name.length() > 0) {
                sb.append("</" + super.element_name + ">");
            }
        }
    }
    
    final void I() {
        if (this.addElement != null) {
            this.children = new XMLNode[this.addElement.size()];
            final Enumeration<XMLNode> elements = this.addElement.elements();
            int n = 0;
            while (elements.hasMoreElements()) {
                (this.children[n] = elements.nextElement()).I();
                ++n;
            }
        }
        else {
            this.children = new XMLNode[0];
        }
        this.addElement = null;
    }
    
    public final void remove(final int n, final int n2) {
        final XMLNode[] children = new XMLNode[this.children.length - n2];
        System.arraycopy(this.children, 0, children, 0, n);
        System.arraycopy(this.children, n + n2, children, n, this.children.length - n - n2);
        this.children = children;
    }
}
