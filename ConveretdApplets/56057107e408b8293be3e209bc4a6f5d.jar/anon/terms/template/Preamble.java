// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms.template;

import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.XMLParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import anon.infoservice.OperatorAddress;
import anon.infoservice.ServiceOperator;

public class Preamble
{
    public static String XML_ELEMENT_NAME;
    public static String XML_ELEMENT_LEADING_TEXT;
    public static String XML_ELEMENT_TRAILING_TEXT;
    private String leadingText;
    private ServiceOperator operator;
    private OperatorAddress operatorAddress;
    private String trailingText;
    
    public Preamble() {
        this.leadingText = null;
        this.operator = null;
        this.operatorAddress = null;
        this.trailingText = null;
    }
    
    public Preamble(final Node node) throws XMLParseException {
        this.leadingText = null;
        this.operator = null;
        this.operatorAddress = null;
        this.trailingText = null;
        Element documentElement;
        if (node.getNodeType() == 9) {
            documentElement = ((Document)node).getDocumentElement();
        }
        else {
            if (node.getNodeType() != 1) {
                throw new XMLParseException("Invalid node type");
            }
            documentElement = (Element)node;
        }
        final Element element = (Element)XMLUtil.getFirstChildByName(documentElement, Preamble.XML_ELEMENT_LEADING_TEXT);
        final Element element2 = (Element)XMLUtil.getFirstChildByName(documentElement, Preamble.XML_ELEMENT_TRAILING_TEXT);
        this.leadingText = ((element != null) ? XMLUtil.parseValue(element, (String)null) : null);
        this.trailingText = ((element2 != null) ? XMLUtil.parseValue(element2, (String)null) : null);
    }
    
    public String getLeadingText() {
        return this.leadingText;
    }
    
    public void setLeadingText(final String leadingText) {
        this.leadingText = leadingText;
    }
    
    public ServiceOperator getOperator() {
        return this.operator;
    }
    
    public void setOperator(final ServiceOperator operator) {
        this.operator = operator;
    }
    
    public OperatorAddress getOperatorAddress() {
        return this.operatorAddress;
    }
    
    public void setOperatorAddress(final OperatorAddress operatorAddress) {
        this.operatorAddress = operatorAddress;
    }
    
    public String getTrailingText() {
        return this.trailingText;
    }
    
    public void setTrailingText(final String trailingText) {
        this.trailingText = trailingText;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement(Preamble.XML_ELEMENT_NAME);
        final Element element2 = document.createElement(Preamble.XML_ELEMENT_LEADING_TEXT);
        final Element element3 = document.createElement(Preamble.XML_ELEMENT_TRAILING_TEXT);
        final Element element4 = (this.operator != null) ? this.operator.toXMLElement(document, this.operatorAddress, false) : document.createElement("Operator");
        XMLUtil.setValue(element2, (this.leadingText != null) ? this.leadingText : "");
        XMLUtil.setValue(element3, (this.trailingText != null) ? this.trailingText : "");
        element.appendChild(element2);
        element.appendChild(element4);
        element.appendChild(element3);
        return element;
    }
    
    static {
        Preamble.XML_ELEMENT_NAME = "Preamble";
        Preamble.XML_ELEMENT_LEADING_TEXT = "LeadingText";
        Preamble.XML_ELEMENT_TRAILING_TEXT = "TrailingText";
    }
}
