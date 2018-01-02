// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.XMLParseException;
import org.w3c.dom.Element;

public class ClickedMessageIDDBEntry extends AbstractMarkedMessageIDDBEntry
{
    public static final String XML_ELEMENT_NAME = "ClickedMessageIDDBEntry";
    public static final String XML_ELEMENT_CONTAINER_NAME = "ClickedMessageIDDBEntries";
    
    public ClickedMessageIDDBEntry(final MessageDBEntry messageDBEntry) {
        super(messageDBEntry);
    }
    
    public ClickedMessageIDDBEntry(final Element element) throws XMLParseException {
        super(element);
    }
    
    public String getXmlElementName() {
        return "ClickedMessageIDDBEntry";
    }
}
