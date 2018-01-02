// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

public interface IXMLEncodable
{
    public static final String XML_ATTR_VERSION = "version";
    public static final String XML_ATTR_ID = "id";
    public static final String XML_ATTR_LANGUAGE = "lang";
    public static final String FIELD_XML_ELEMENT_NAME = "XML_ELEMENT_NAME";
    public static final String FIELD_XML_ELEMENT_CONTAINER_NAME = "XML_ELEMENT_CONTAINER_NAME";
    
    Element toXmlElement(final Document p0);
}
