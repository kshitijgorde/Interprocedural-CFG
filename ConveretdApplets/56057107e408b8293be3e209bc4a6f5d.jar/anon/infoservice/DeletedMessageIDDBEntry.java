// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.XMLParseException;
import org.w3c.dom.Element;

public class DeletedMessageIDDBEntry extends AbstractMarkedMessageIDDBEntry
{
    public static final String XML_ELEMENT_NAME = "DeletedMessageIDEntry";
    public static final String XML_ELEMENT_CONTAINER_NAME = "DeletedMessageIDEntries";
    
    public DeletedMessageIDDBEntry(final MessageDBEntry messageDBEntry) {
        super(messageDBEntry);
    }
    
    public DeletedMessageIDDBEntry(final Element element) throws XMLParseException {
        super(element);
    }
    
    public String getXmlElementName() {
        return "DeletedMessageIDEntry";
    }
}
