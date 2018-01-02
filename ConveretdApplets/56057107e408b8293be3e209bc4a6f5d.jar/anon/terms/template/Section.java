// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms.template;

import org.w3c.dom.NodeList;
import anon.terms.TCComponent;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.XMLParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import anon.util.IXMLEncodable;
import anon.terms.TCComposite;

public class Section extends TCComposite implements IXMLEncodable
{
    public static String XML_ELEMENT_CONTAINER_NAME;
    public static String XML_ELEMENT_NAME;
    public static String XML_ATTR_NAME;
    
    public Section() {
    }
    
    public Section(final double n, final Object o) {
        super(n, o);
    }
    
    public Section(final Node node) throws XMLParseException {
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
        if (!documentElement.getTagName().equals(Section.XML_ELEMENT_NAME)) {
            throw new XMLParseException("Invalid Tag name: " + documentElement.getTagName());
        }
        super.id = XMLUtil.parseAttribute(documentElement, "id", -1.0);
        if (super.id < 0.0) {
            throw new XMLParseException("Attribute id of " + XMLUtil.parseAttribute(documentElement, Section.XML_ATTR_NAME, "") + " missing");
        }
        Element element = (Element)XMLUtil.getFirstChildByName(documentElement, Paragraph.XML_ELEMENT_NAME);
        this.setContent(XMLUtil.parseAttribute(documentElement, Section.XML_ATTR_NAME, null));
        while (element != null) {
            this.addTCComponent(new Paragraph(element));
            element = (Element)XMLUtil.getNextSiblingByName(element, Paragraph.XML_ELEMENT_NAME);
        }
    }
    
    public void replaceElementNodes(final NodeList list) {
        final TCComponent[] tcComponents = this.getTCComponents();
        for (int i = 0; i < tcComponents.length; ++i) {
            final Paragraph paragraph = (Paragraph)tcComponents[i];
            if (paragraph.hasElementNodes()) {
                paragraph.replaceElementNodes(list);
            }
        }
    }
    
    public Element toXmlElement(final Document document) {
        return this.toXmlElement(document, false);
    }
    
    public Element toXmlElement(final Document document, final boolean b) {
        if (this.getId() < 0.0 || (!this.hasContent() && !b)) {
            return null;
        }
        final Element element = document.createElement(Section.XML_ELEMENT_NAME);
        if (this.getContent() != null) {
            element.setAttribute(Section.XML_ATTR_NAME, this.getContent().toString());
        }
        element.setAttribute("id", "" + this.getId());
        final TCComponent[] tcComponents = this.getTCComponents();
        for (int i = 0; i < tcComponents.length; ++i) {
            final Element xmlElement = ((Paragraph)tcComponents[i]).toXmlElement(document, b);
            if (xmlElement != null) {
                element.appendChild(xmlElement);
            }
        }
        return element;
    }
    
    static {
        Section.XML_ELEMENT_CONTAINER_NAME = "Sections";
        Section.XML_ELEMENT_NAME = "Section";
        Section.XML_ATTR_NAME = "name";
    }
}
