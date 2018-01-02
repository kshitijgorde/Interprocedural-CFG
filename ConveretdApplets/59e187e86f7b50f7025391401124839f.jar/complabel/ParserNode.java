// 
// Decompiled by Procyon v0.5.30
// 

package complabel;

import java.util.Enumeration;
import java.util.Vector;

public class ParserNode
{
    public ParserElement element;
    public Vector children;
    public ParserNode parent;
    
    public ParserNode() {
        this.element = new ParserElement();
    }
    
    public ParserNode(final ParserNode parent) {
        this.element = new ParserElement();
        this.parent = parent;
        if (parent != null) {
            this.element = new ParserElement(parent.element);
        }
    }
    
    public void addChild(final ParserNode parserNode) {
        (parserNode.parent = this).add(parserNode);
    }
    
    public void add(final ParserNode parserNode) {
        if (this.children == null) {
            this.children = new Vector();
        }
        this.children.addElement(parserNode);
    }
    
    public void dump(final int n) {
        String s = "" + n;
        for (int i = 0; i < n; ++i) {
            s += "  ";
        }
        System.out.println(s + " element: " + ((this.element != null) ? this.element.toString() : "(null)"));
        if (this.children != null) {
            final Enumeration<ParserNode> elements = (Enumeration<ParserNode>)this.children.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().dump(n + 1);
            }
        }
    }
}
