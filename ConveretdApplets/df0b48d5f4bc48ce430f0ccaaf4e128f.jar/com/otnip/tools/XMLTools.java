// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.util.ArrayList;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import java.io.OutputStream;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Element;

public class XMLTools
{
    public static Element getDocumentElement(final String xml) throws Exception {
        final ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        final DocumentBuilder db = dbf.newDocumentBuilder();
        final Document doc = db.parse(bais);
        return doc.getDocumentElement();
    }
    
    public static Element getDocumentElement(final URL url) throws Exception {
        final InputStream is = url.openStream();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int x = -1;
        while ((x = is.read()) != -1) {
            baos.write(x);
        }
        is.close();
        return getDocumentElement(baos.toString());
    }
    
    public static String toString(final Element element) throws Exception {
        final Source source = new DOMSource(element);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Result result = new StreamResult(baos);
        final Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);
        return baos.toString();
    }
    
    public static Element createElement(final Element parentElement, final String elementName, final Object textContent) {
        final Element newElement = parentElement.getOwnerDocument().createElement(elementName);
        parentElement.appendChild(newElement);
        if (textContent != null) {
            newElement.setTextContent(textContent.toString());
        }
        return newElement;
    }
    
    public static Element getChildElement(final Element parentElement, final String childName) {
        Element result = null;
        final NodeList list = parentElement.getElementsByTagName(childName);
        for (int i = 0; i < list.getLength(); ++i) {
            if (list.item(i).getParentNode() == parentElement) {
                result = (Element)list.item(i);
                break;
            }
        }
        return result;
    }
    
    public static Element[] getChildElements(final Element parentElement, final String childrenName) {
        final NodeList list = parentElement.getElementsByTagName(childrenName);
        final ArrayList<Element> children = new ArrayList<Element>();
        for (int i = 0; i < list.getLength(); ++i) {
            if (list.item(i).getParentNode() == parentElement) {
                children.add((Element)list.item(i));
            }
        }
        return children.toArray(new Element[children.size()]);
    }
    
    @Deprecated
    public static String getValue(final Element parentElement, final String elementName, final String defaultValue) {
        String result = defaultValue;
        final Element childElement = getChildElement(parentElement, elementName);
        if (childElement != null) {
            result = childElement.getTextContent();
        }
        return result;
    }
    
    public static String getString(final Element parentElement, final String elementName, final String defaultValue) {
        String result = defaultValue;
        final Element childElement = getChildElement(parentElement, elementName);
        if (childElement != null) {
            result = childElement.getTextContent();
        }
        return result;
    }
    
    public static int getInt(final Element parentElement, final String elementName, final int defaultValue) {
        int result = defaultValue;
        final Element childElement = getChildElement(parentElement, elementName);
        if (childElement != null) {
            result = Integer.parseInt(childElement.getTextContent());
        }
        return result;
    }
    
    public static long getLong(final Element parentElement, final String elementName, final long defaultValue) {
        long result = defaultValue;
        final Element childElement = getChildElement(parentElement, elementName);
        if (childElement != null) {
            result = Long.parseLong(childElement.getTextContent());
        }
        return result;
    }
    
    public static double getDouble(final Element parentElement, final String elementName, final double defaultValue) {
        double result = defaultValue;
        final Element childElement = getChildElement(parentElement, elementName);
        if (childElement != null) {
            result = Double.parseDouble(childElement.getTextContent());
        }
        return result;
    }
    
    public static boolean getBoolean(final Element parentElement, final String elementName, final boolean defaultValue) {
        boolean result = defaultValue;
        final Element childElement = getChildElement(parentElement, elementName);
        if (childElement != null) {
            result = Boolean.parseBoolean(childElement.getTextContent());
        }
        return result;
    }
    
    private XMLTools() {
        throw new UnsupportedOperationException("Utility Constuctor");
    }
}
