// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.applications.hexplorer;

import javax.swing.ImageIcon;
import hypergraph.graphApi.AttributeManager;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.io.CSSColourParser;
import hypergraph.graphApi.GraphException;
import hypergraph.graphApi.Group;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.net.URL;

public class GraphXMLContentHandler extends hypergraph.graphApi.io.GraphXMLContentHandler
{
    private URL baseUrl;
    
    public GraphXMLContentHandler(final URL baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        super.startElement(s, s2, s3, attributes);
        if (s3.equals("line")) {
            this.startElementLine(attributes);
            return;
        }
        if (s3.equals("fill")) {
            this.startElementFill(attributes);
            return;
        }
        if (s3.equals("ref")) {
            this.startElementRef(attributes);
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        super.endElement(s, s2, s3);
        if (s3.equals("line")) {
            this.endElementLine();
            return;
        }
        if (s3.equals("fill")) {
            this.endElementFill();
        }
    }
    
    protected void startElementLine(final Attributes attributes) {
        Element group = null;
        Object stringToColor = null;
        String value = "";
        final float[] array = { 5.0f, 3.0f };
        final float[] array2 = { 1.0f, 4.0f };
        final float[] array3 = { 5.0f, 3.0f, 1.0f, 3.0f };
        final float[] array4 = new float[0];
        Object o = null;
        Object o2 = null;
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (attributes.getQName(i).equals("class")) {
                final String value2 = attributes.getValue(i);
                group = this.graph.getElement(value2);
                if (group == null) {
                    try {
                        group = this.graph.createGroup(value2);
                    }
                    catch (GraphException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else if (attributes.getQName(i).equals("colour") || attributes.getQName(i).equals("color")) {
                stringToColor = CSSColourParser.stringToColor(attributes.getValue(i));
            }
            else if (attributes.getQName(i).equals("linewidth")) {
                o2 = new Float(attributes.getValue(i));
            }
            else if (attributes.getQName(i).equals("linestyle")) {
                if (attributes.getValue(i).equalsIgnoreCase("dashed")) {
                    o = array;
                }
                if (attributes.getValue(i).equalsIgnoreCase("dotted")) {
                    o = array2;
                }
                if (attributes.getValue(i).equalsIgnoreCase("dash-dotted")) {
                    o = array3;
                }
                if (attributes.getValue(i).equalsIgnoreCase("none")) {
                    o = array4;
                }
            }
            else if (attributes.getQName(i).equals("tag")) {
                value = attributes.getValue(i);
            }
        }
        Element element = this.currentElement;
        if (element == null) {
            if (group == null) {
                element = this.graph;
            }
            else {
                element = group;
            }
        }
        final Object o3 = null;
        final AttributeManager attributeManager = this.graph.getAttributeManager();
        if (stringToColor != null) {
            if (element.getElementType() == 1 || value.equals("node")) {
                attributeManager.setAttribute("node.color", element, stringToColor);
            }
            if (o3 == null && (element.getElementType() == 2 || value.equals("edge"))) {
                attributeManager.setAttribute("edge.linecolor", element, stringToColor);
            }
        }
        if (o2 != null && (element.getElementType() == 2 || value.equals("edge"))) {
            attributeManager.setAttribute("edge.linewidth", element, o2);
        }
        if (o != null && (element.getElementType() == 2 || value.equals("edge"))) {
            attributeManager.setAttribute("edge.stroke", element, o);
        }
    }
    
    protected void endElementLine() {
    }
    
    protected void startElementFill(final Attributes attributes) {
        Element group = null;
        Object stringToColor = null;
        String value = "";
        Object o = null;
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (attributes.getQName(i).equals("class")) {
                final String value2 = attributes.getValue(i);
                group = this.graph.getElement(value2);
                if (group == null) {
                    try {
                        group = this.graph.createGroup(value2);
                    }
                    catch (GraphException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else if (attributes.getQName(i).equals("colour") || attributes.getQName(i).equals("color")) {
                stringToColor = CSSColourParser.stringToColor(attributes.getValue(i));
            }
            else if (attributes.getQName(i).equals("xlink:href")) {
                final String value3 = attributes.getValue(i);
                try {
                    o = new ImageIcon(new URL(this.baseUrl, value3));
                }
                catch (Exception ex2) {
                    System.out.println(ex2);
                }
            }
            else {
                if (attributes.getQName(i).equals("tag")) {
                    value = attributes.getValue(i);
                }
                Element element = this.currentElement;
                if (element == null) {
                    if (group == null) {
                        element = this.graph;
                    }
                    else {
                        element = group;
                    }
                }
                final AttributeManager attributeManager = this.graph.getAttributeManager();
                if (stringToColor != null && (element.getElementType() == 1 || value.equals("node"))) {
                    attributeManager.setAttribute("node.bkcolor", element, stringToColor);
                }
                if (o != null && (element.getElementType() == 1 || value.equals("node"))) {
                    attributeManager.setAttribute("node.icon", element, o);
                }
            }
        }
    }
    
    protected void endElementFill() {
    }
    
    protected void startElementRef(final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (attributes.getQName(i).equals("xlink:href")) {
                if (this.currentElement == null) {
                    return;
                }
                this.graph.getAttributeManager().setAttribute("xlink:href", this.currentElement, attributes.getValue(i));
            }
            if (attributes.getQName(i).equals("xlink:show")) {
                if (this.currentElement == null) {
                    return;
                }
                this.graph.getAttributeManager().setAttribute("xlink:show", this.currentElement, attributes.getValue(i));
            }
        }
    }
}
