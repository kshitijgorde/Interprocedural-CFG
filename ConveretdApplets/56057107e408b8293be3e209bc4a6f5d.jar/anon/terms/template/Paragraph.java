// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms.template;

import java.util.StringTokenizer;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.NodeList;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.XMLParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.util.Vector;
import anon.util.IXMLEncodable;
import anon.terms.TCComponent;

public class Paragraph extends TCComponent implements IXMLEncodable
{
    public static String XML_ELEMENT_CONTAINER_NAME;
    public static String XML_ELEMENT_NAME;
    private Vector elementNodes;
    private boolean hasElementNodes;
    
    public Paragraph() {
        this.elementNodes = null;
        this.hasElementNodes = false;
        super.content = new Vector();
        this.elementNodes = new Vector(3);
        this.hasElementNodes = false;
    }
    
    public Paragraph(final double id) {
        this();
        this.setId(id);
    }
    
    public Paragraph(final Node node) throws XMLParseException {
        this();
        Element documentElement;
        if (node.getNodeType() == 9) {
            documentElement = ((Document)node).getDocumentElement();
        }
        else {
            if (node.getNodeType() != 1) {
                throw new XMLParseException("Invalid node type.");
            }
            documentElement = (Element)node;
        }
        if (!documentElement.getTagName().equals(Paragraph.XML_ELEMENT_NAME)) {
            throw new XMLParseException("Invalid Tag name: " + documentElement.getTagName());
        }
        super.id = XMLUtil.parseAttribute(documentElement, "id", -1.0);
        if (super.id < 0.0) {
            throw new XMLParseException("Attribute id missing: " + XMLUtil.toString(node));
        }
        this.setContent(documentElement.getChildNodes());
    }
    
    public void replaceElementNodes(final NodeList list) {
        for (int i = 0; i < list.getLength(); ++i) {
            final Element element = (Element)list.item(i);
            for (int j = 0; j < this.elementNodes.size(); ++j) {
                final Element element2 = this.elementNodes.elementAt(j);
                if (element2.getTagName().equals(element.getTagName())) {
                    this.elementNodes.removeElementAt(j);
                    this.elementNodes.insertElementAt(element, j);
                    final int index = this.contentNodes().indexOf(element2);
                    this.contentNodes().removeElementAt(index);
                    this.contentNodes().insertElementAt(element, index);
                }
                else {
                    final NodeList elementsByTagName = element2.getElementsByTagName(element.getTagName());
                    for (int k = 0; k < elementsByTagName.getLength(); ++k) {
                        final Element element3 = (Element)elementsByTagName.item(k);
                        try {
                            element3.getParentNode().replaceChild(XMLUtil.importNode(element3.getParentNode().getOwnerDocument(), element, true), element3);
                        }
                        catch (XMLParseException ex) {
                            LogHolder.log(0, LogType.MISC, ex);
                        }
                    }
                }
            }
        }
    }
    
    public boolean hasElementNodes() {
        return this.hasElementNodes;
    }
    
    public void setContent(final Object o) {
        NodeList nodeList = null;
        Label_0178: {
            if (o != null) {
                if (!(o instanceof NodeList)) {
                    if (o instanceof Node[]) {
                        nodeList = toNodeList((Node[])o);
                        break Label_0178;
                    }
                    final StringBuffer sb = new StringBuffer();
                    sb.append("<?xml version=\"1.0\"?><temp>");
                    sb.append(o);
                    sb.append("</temp>");
                    try {
                        final Document xmlDocument = XMLUtil.readXMLDocument(new StringReader(sb.toString()));
                        nodeList = ((xmlDocument.getDocumentElement() != null) ? xmlDocument.getDocumentElement().getChildNodes() : null);
                        break Label_0178;
                    }
                    catch (IOException ex) {
                        LogHolder.log(7, LogType.MISC, "Cannot set content, reason: " + ex.getMessage());
                        return;
                    }
                    catch (XMLParseException ex2) {
                        LogHolder.log(7, LogType.MISC, "Cannot set content, reason: " + ex2.getMessage());
                        return;
                    }
                }
                nodeList = (NodeList)o;
            }
        }
        this.elementNodes.removeAllElements();
        this.contentNodes().removeAllElements();
        this.hasElementNodes = false;
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); ++i) {
                final Node cloneNode = nodeList.item(i).cloneNode(true);
                if (cloneNode.getNodeType() == 1) {
                    this.elementNodes.addElement(cloneNode);
                    this.hasElementNodes = true;
                }
                this.contentNodes().addElement(cloneNode);
            }
        }
    }
    
    public Object getContent() {
        final Node[] array = new Node[this.contentNodes().size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (Node)this.contentNodes().elementAt(i);
        }
        return new NodeList() {
            public int getLength() {
                return array.length;
            }
            
            public Node item(final int n) {
                return array[n];
            }
        };
    }
    
    public void setContentBold() {
        final NodeList list = (NodeList)this.getContent();
        final Document document = XMLUtil.createDocument();
        this.elementNodes.removeAllElements();
        this.contentNodes().removeAllElements();
        for (int i = 0; i < list.getLength(); ++i) {
            Node node;
            if (list.item(i).getNodeType() == 1 && ((Element)list.item(i)).getTagName().toLowerCase().equals("b")) {
                node = list.item(i);
            }
            else if (list.item(i).getNodeType() != 3 || (list.item(i).getNodeValue() != null && !list.item(i).getNodeValue().trim().equals(""))) {
                node = document.createElement("b");
                try {
                    node.appendChild(XMLUtil.importNode(document, list.item(i), true));
                }
                catch (XMLParseException ex) {
                    LogHolder.log(1, LogType.MISC, ex);
                }
            }
            else {
                node = null;
            }
            if (node != null) {
                this.contentNodes().addElement(node);
                this.elementNodes.addElement(node);
            }
        }
        this.hasElementNodes = (this.elementNodes.size() > 0);
    }
    
    private Vector contentNodes() {
        return (Vector)super.content;
    }
    
    public boolean hasContent() {
        return super.hasContent() && this.contentNodes().size() > 0;
    }
    
    public Element toXmlElement(final Document document) {
        return this.toXmlElement(document, false);
    }
    
    public Element toXmlElement(final Document document, final boolean b) {
        if (super.id < 0.0 || (this.contentNodes().size() == 0 && !b)) {
            return null;
        }
        final Element element = document.createElement(Paragraph.XML_ELEMENT_NAME);
        element.setAttribute("id", "" + super.id);
        for (int i = 0; i < this.contentNodes().size(); ++i) {
            try {
                element.appendChild(XMLUtil.importNode(document, (Node)this.contentNodes().elementAt(i), true));
            }
            catch (XMLParseException ex) {
                LogHolder.log(0, LogType.MISC, ex);
            }
        }
        return element;
    }
    
    public Object clone() {
        final Paragraph paragraph = new Paragraph();
        paragraph.setId(super.id);
        paragraph.setContent(this.getContent());
        return paragraph;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.contentNodes().size(); ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(XMLUtil.toString(this.contentNodes().elementAt(i)), "\n");
            while (stringTokenizer.hasMoreTokens()) {
                sb.append(stringTokenizer.nextToken().trim());
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }
    
    public static NodeList toNodeList(final Node[] array) {
        return new NodeList() {
            public int getLength() {
                return array.length;
            }
            
            public Node item(final int n) {
                return array[n];
            }
        };
    }
    
    static {
        Paragraph.XML_ELEMENT_CONTAINER_NAME = "Section";
        Paragraph.XML_ELEMENT_NAME = "Paragraph";
    }
}
