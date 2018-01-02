// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class J2KViewerW3cXmlUtilities
{
    public static Document createDocument() throws J2KViewerXmlException {
        final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            document = instance.newDocumentBuilder().newDocument();
        }
        catch (ParserConfigurationException ex) {
            throw new J2KViewerXmlException("Unable to create a new XML Document", ex);
        }
        return document;
    }
    
    public static Element addChildElement(final Document document, final Element element, final String s, final String s2) {
        Node element2 = null;
        if (s != null && element != null) {
            element2 = document.createElement(s);
            element.appendChild(element2);
            if (s2 != null) {
                element2.appendChild(document.createTextNode(s2));
            }
        }
        return (Element)element2;
    }
    
    public static Element getChildElement(final Element element, final String s) {
        Element element2 = null;
        if (element != null) {
            final NodeList elementsByTagName = element.getElementsByTagName(s);
            if (elementsByTagName != null && elementsByTagName.getLength() > 0) {
                element2 = (Element)elementsByTagName.item(0);
            }
        }
        return element2;
    }
}
