// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLCloseAck implements IXMLEncodable
{
    public Element toXmlElement(final Document document) {
        return document.createElement("CloseAck");
    }
}
