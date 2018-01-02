// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.lang.reflect.Method;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import anon.crypto.SignatureCreator;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStream;
import java.io.IOException;
import org.xml.sax.InputSource;
import anon.crypto.XMLSignature;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import logging.LogHolder;
import logging.LogType;
import java.util.Vector;
import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLUtil
{
    public static final int STORAGE_MODE_NORMAL = 0;
    public static final int STORAGE_MODE_OPTIMIZED = 1;
    public static final int STORAGE_MODE_AGRESSIVE = 2;
    private static final String DEFAULT_FORMAT_SPACE = "    ";
    private static final String XML_STR_BOOLEAN_TRUE = "true";
    private static final String XML_STR_BOOLEAN_FALSE = "false";
    private static final String PACKAGE_TRANSFORMER = "javax.xml.transform.";
    private static final String HIERARCHY_REQUEST_ERR = "HIERARCHY_REQUEST_ERR: ";
    private static DocumentBuilderFactory ms_DocumentBuilderFactory;
    private static boolean m_bCheckedHumanReadableFormatting;
    private static boolean m_bNeedsHumanReadableFormatting;
    private static int ms_storageMode;
    public static final String[] SPECIAL_CHARS;
    public static final String[] ENTITIES;
    static /* synthetic */ Class class$java$io$Writer;
    static /* synthetic */ Class class$java$io$OutputStream;
    static /* synthetic */ Class class$org$w3c$dom$Node;
    static /* synthetic */ Class class$java$lang$String;
    
    public static int getStorageMode() {
        return XMLUtil.ms_storageMode;
    }
    
    public static void setStorageMode(final int ms_storageMode) {
        if (ms_storageMode == 0 || ms_storageMode == 1 || ms_storageMode == 2) {
            XMLUtil.ms_storageMode = ms_storageMode;
        }
    }
    
    public static void assertNotNull(final Node node) throws XMLParseException {
        if (node == null) {
            throw new XMLParseException("##__null__##");
        }
    }
    
    public static void assertNotNull(final Node node, final String s) throws XMLParseException {
        if (parseAttribute(node, s, null) == null) {
            throw new XMLParseException("##__null__##");
        }
    }
    
    public static Node assertNodeName(Node documentElement, final String s) throws XMLParseException {
        if (documentElement == null) {
            throw new XMLParseException("##__null__##", "Expected node '" + s + "' is NULL!");
        }
        documentElement = getDocumentElement(documentElement);
        if (!documentElement.getNodeName().equals(s)) {
            String nodeName;
            if (documentElement.getOwnerDocument().getDocumentElement() == documentElement || documentElement.getOwnerDocument() == documentElement) {
                nodeName = "##__root__##";
            }
            else {
                nodeName = documentElement.getNodeName();
            }
            throw new XMLParseException(nodeName, "Node '" + documentElement.getNodeName() + "' has not the expected name: '" + s + "'");
        }
        return documentElement;
    }
    
    public static Node getDocumentElement(Node documentElement) {
        if (documentElement instanceof Document) {
            documentElement = ((Document)documentElement).getDocumentElement();
        }
        return documentElement;
    }
    
    public static int parseValue(final Node node, final int n) {
        int int1 = n;
        final String value = parseValue(node, null);
        if (value != null) {
            try {
                int1 = Integer.parseInt(value);
            }
            catch (Exception ex) {}
        }
        return int1;
    }
    
    public static long parseValue(final Node node, final long n) {
        long long1 = n;
        final String value = parseValue(node, null);
        if (value != null) {
            try {
                long1 = Long.parseLong(value);
            }
            catch (Exception ex) {}
        }
        return long1;
    }
    
    public static double parseValue(final Node node, final double n) {
        double double1 = n;
        final String value = parseValue(node, null);
        if (value != null) {
            try {
                double1 = Util.parseDouble(value);
            }
            catch (Exception ex) {}
        }
        return double1;
    }
    
    public static String parseAttribute(Node documentElement, final String s, final String s2) {
        try {
            if (documentElement instanceof Document) {
                documentElement = ((Document)documentElement).getDocumentElement();
            }
            return ((Element)documentElement).getAttributeNode(s).getValue().trim();
        }
        catch (Exception ex) {
            return s2;
        }
    }
    
    public static boolean parseAttribute(final Node node, final String s, final boolean b) {
        boolean b2 = b;
        try {
            final String attribute = parseAttribute(node, s, null);
            if (attribute.equalsIgnoreCase("true")) {
                b2 = true;
            }
            else if (attribute.equalsIgnoreCase("false")) {
                b2 = false;
            }
        }
        catch (Exception ex) {}
        return b2;
    }
    
    public static int parseAttribute(final Node node, final String s, final int n) {
        int int1 = n;
        try {
            int1 = Integer.parseInt(parseAttribute(node, s, null));
        }
        catch (Exception ex) {}
        return int1;
    }
    
    public static double parseAttribute(final Node node, final String s, final double n) {
        double double1 = n;
        try {
            double1 = Util.parseDouble(parseAttribute(node, s, null));
        }
        catch (Exception ex) {}
        return double1;
    }
    
    public static long parseAttribute(final Node node, final String s, final long n) {
        long long1 = n;
        try {
            long1 = Long.parseLong(parseAttribute(node, s, null));
        }
        catch (Exception ex) {}
        return long1;
    }
    
    public static boolean parseValue(final Node node, final boolean b) {
        boolean b2 = b;
        try {
            final String value = parseValue(node, null);
            if (value == null) {
                return b2;
            }
            if (value.equalsIgnoreCase("true")) {
                b2 = true;
            }
            else if (value.equalsIgnoreCase("false")) {
                b2 = false;
            }
        }
        catch (Exception ex) {}
        return b2;
    }
    
    public static String parseValue(Node node, final String s) {
        String s2 = s;
        if (node != null) {
            try {
                if (node.getNodeType() == 1) {
                    node = node.getFirstChild();
                }
                if (node.getNodeType() == 3 || node.getNodeType() == 5) {
                    s2 = "";
                    while (node != null) {
                        if (node.getNodeType() != 5 && node.getNodeType() != 3) {
                            break;
                        }
                        if (node.getNodeType() == 5) {
                            s2 += node.getFirstChild().getNodeValue();
                        }
                        else {
                            s2 += node.getNodeValue();
                        }
                        node = node.getNextSibling();
                    }
                }
                else {
                    s2 = node.getNodeValue();
                }
            }
            catch (Exception ex) {
                return s;
            }
        }
        return s2;
    }
    
    public static String getXmlElementContainerName(final Class clazz) {
        return Util.getStaticFieldValue(clazz, "XML_ELEMENT_CONTAINER_NAME");
    }
    
    public static String getXmlElementName(final Class clazz) {
        return Util.getStaticFieldValue(clazz, "XML_ELEMENT_NAME");
    }
    
    public static Element[] readElementsByTagName(final File file, final String s) {
        final Vector vector = new Vector<Element>();
        if (file != null && s != null) {
            try {
                final NodeList elementsByTagName = readXMLDocument(file).getDocumentElement().getElementsByTagName(s);
                for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                    try {
                        vector.addElement((Element)elementsByTagName.item(i));
                    }
                    catch (Exception ex) {
                        LogHolder.log(2, LogType.MISC, ex);
                    }
                }
            }
            catch (Exception ex2) {
                LogHolder.log(2, LogType.MISC, ex2);
            }
        }
        final Element[] array = new Element[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    public static NodeList getElementsByTagName(final Node node, final String s) {
        if (node == null || !(node instanceof Element) || s == null || s.trim().length() == 0) {
            return null;
        }
        return ((Element)node).getElementsByTagName(s);
    }
    
    public static Node getFirstChildByName(final Node node, final String s) {
        try {
            for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                if (node2.getNodeName().equals(s)) {
                    return node2;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static Node getFirstChildByName(final Node node, final String s, final boolean b) {
        return getFirstChildByNameUsingDeepSearch(node, s);
    }
    
    public static Node getFirstChildByNameUsingDeepSearch(Node node, final String s) {
        Node firstChildByNameUsingDeepSearchInternal = null;
        try {
            for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
                firstChildByNameUsingDeepSearchInternal = getFirstChildByNameUsingDeepSearchInternal(node, s);
                if (firstChildByNameUsingDeepSearchInternal != null) {
                    break;
                }
            }
        }
        catch (Exception ex) {}
        return firstChildByNameUsingDeepSearchInternal;
    }
    
    public static Node getLastChildByName(final Node node, final String s) {
        try {
            for (Node node2 = node.getLastChild(); node2 != null; node2 = node2.getPreviousSibling()) {
                if (node2.getNodeName().equals(s)) {
                    return node2;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static Node getNextSiblingByName(final Node node, final String s) {
        try {
            if (node == null) {
                return null;
            }
            for (Node node2 = node.getNextSibling(); node2 != null; node2 = node2.getNextSibling()) {
                if (node2.getNodeName().equals(s)) {
                    return node2;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static void setValue(final Node node, final String s) {
        if (node == null || s == null) {
            return;
        }
        node.appendChild(node.getOwnerDocument().createTextNode(s));
    }
    
    public static void setValue(final Node node, final int n) {
        node.appendChild(node.getOwnerDocument().createTextNode(Integer.toString(n)));
    }
    
    public static void setValue(final Node node, final long n) {
        node.appendChild(node.getOwnerDocument().createTextNode(Long.toString(n)));
    }
    
    public static void setValue(final Node node, final double n) {
        node.appendChild(node.getOwnerDocument().createTextNode(Double.toString(n)));
    }
    
    public static void setValue(final Node node, final boolean b) {
        setValue(node, b ? "true" : "false");
    }
    
    public static void setAttribute(final Element element, final String s, final String s2) {
        if (s2 == null || s == null || element == null) {
            return;
        }
        element.setAttribute(s, s2);
    }
    
    public static void setAttribute(final Element element, final String s, final boolean b) {
        setAttribute(element, s, b ? "true" : "false");
    }
    
    public static void setAttribute(final Element element, final String s, final int n) {
        setAttribute(element, s, Integer.toString(n));
    }
    
    public static void setAttribute(final Element element, final String s, final double n) {
        setAttribute(element, s, Double.toString(n));
    }
    
    public static void setAttribute(final Element element, final String s, final long n) {
        setAttribute(element, s, Long.toString(n));
    }
    
    public static Document createDocument() {
        try {
            if (XMLUtil.ms_DocumentBuilderFactory == null) {
                XMLUtil.ms_DocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            }
            return XMLUtil.ms_DocumentBuilderFactory.newDocumentBuilder().newDocument();
        }
        catch (ParserConfigurationException ex) {
            return null;
        }
    }
    
    public static Element createChildElementWithValue(final Element element, final String s, final String s2) {
        final Element element2 = element.getOwnerDocument().createElement(s);
        setValue(element2, s2);
        element.appendChild(element2);
        return element2;
    }
    
    public static Element createChildElement(final Element element, final String s) {
        final Element element2 = element.getOwnerDocument().createElement(s);
        element.appendChild(element2);
        return element2;
    }
    
    public static Node importNode(final Document document, final Node node, boolean b) throws XMLParseException {
        if (document == null || node == null) {
            return null;
        }
        Node node2 = null;
        switch (node.getNodeType()) {
            case 1: {
                final Element element = document.createElement(node.getNodeName());
                final NamedNodeMap attributes = node.getAttributes();
                if (attributes != null) {
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        element.setAttributeNode((Attr)importNode(document, attributes.item(i), true));
                    }
                }
                node2 = element;
                break;
            }
            case 2: {
                node2 = document.createAttribute(node.getNodeName());
                node2.setNodeValue(node.getNodeValue());
                break;
            }
            case 3: {
                final Node parentNode = node.getParentNode();
                if (parentNode == null || parentNode.getNodeType() != 2) {
                    node2 = document.createTextNode(node.getNodeValue());
                    break;
                }
                break;
            }
            case 4: {
                node2 = document.createCDATASection(node.getNodeValue());
                break;
            }
            case 5: {
                node2 = document.createEntityReference(node.getNodeName());
                b = false;
                break;
            }
            case 6: {
                throw new XMLParseException(node.getNodeName(), "HIERARCHY_REQUEST_ERR: Entity");
            }
            case 7: {
                node2 = document.createProcessingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 8: {
                node2 = document.createComment(node.getNodeValue());
                break;
            }
            case 10: {
                throw new XMLParseException(node.getNodeName(), "HIERARCHY_REQUEST_ERR: DocumentType");
            }
            case 11: {
                node2 = document.createDocumentFragment();
                break;
            }
            case 12: {
                throw new XMLParseException(node.getNodeName(), "HIERARCHY_REQUEST_ERR: Notation");
            }
            default: {
                throw new XMLParseException(node.getNodeName(), "HIERARCHY_REQUEST_ERR: Document");
            }
        }
        if (b) {
            for (Node node3 = node.getFirstChild(); node3 != null; node3 = node3.getNextSibling()) {
                if (node2 != null) {
                    final Node importNode = importNode(document, node3, true);
                    if (importNode != null) {
                        node2.appendChild(importNode);
                    }
                }
            }
        }
        return node2;
    }
    
    public static byte[] toByteArray(final Node node) {
        byte[] canonical;
        try {
            canonical = XMLSignature.toCanonical(node, true);
        }
        catch (Exception ex) {
            return null;
        }
        return canonical;
    }
    
    public static String toString(final Node node) {
        String s;
        try {
            s = new String(toByteArray(node), "UTF8");
        }
        catch (Exception ex) {
            return null;
        }
        return s;
    }
    
    public static String quoteXML(final String s) {
        if (s.indexOf(38) >= 0 || s.indexOf(60) >= 0 || s.indexOf(62) >= 0) {
            final StringBuffer sb = new StringBuffer(s);
            for (int i = 0; i < sb.length(); ++i) {
                final char char1 = sb.charAt(i);
                if (char1 == '&') {
                    sb.insert(i, "amp;");
                    i += 4;
                }
                else if (char1 == '<') {
                    sb.setCharAt(i, '&');
                    sb.insert(i + 1, "lt;");
                    i += 3;
                }
                else if (char1 == '>') {
                    sb.setCharAt(i, '&');
                    sb.insert(i + 1, "gt;");
                    i += 3;
                }
            }
            return sb.toString();
        }
        return s;
    }
    
    public static void removeComments(final Node node) {
        if (node == null) {
            return;
        }
        if (node.getNodeType() != 8) {
            removeCommentsInternal(node, node);
        }
    }
    
    public static Document formatHumanReadable(final Document document) {
        formatHumanReadable(document.getDocumentElement(), 0);
        return document;
    }
    
    public static Element formatHumanReadable(final Element element) {
        formatHumanReadable(element, 0);
        return element;
    }
    
    public static Document readXMLDocument(final InputSource inputSource) throws IOException, XMLParseException {
        Document parse;
        try {
            if (XMLUtil.ms_DocumentBuilderFactory == null) {
                XMLUtil.ms_DocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            }
            parse = XMLUtil.ms_DocumentBuilderFactory.newDocumentBuilder().parse(inputSource);
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new XMLParseException("##__root__##", "Could not parse XML document: " + ex2.getMessage());
        }
        return parse;
    }
    
    public static Document readXMLDocument(final InputStream byteStream) throws IOException, XMLParseException {
        return readXMLDocument(new InputSource(byteStream));
    }
    
    public static Document readXMLDocument(final Reader characterStream) throws IOException, XMLParseException {
        return readXMLDocument(new InputSource(characterStream));
    }
    
    public static Document readXMLDocument(final File file) throws IOException, XMLParseException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        IOException ex = null;
        XMLParseException ex2 = null;
        Document xmlDocument = null;
        try {
            xmlDocument = readXMLDocument(fileInputStream);
        }
        catch (IOException ex3) {
            ex = ex3;
        }
        catch (XMLParseException ex4) {
            ex2 = ex4;
        }
        try {
            fileInputStream.close();
        }
        catch (IOException ex5) {}
        if (ex != null) {
            throw ex;
        }
        if (ex2 != null) {
            throw ex2;
        }
        return xmlDocument;
    }
    
    public static void write(final Document document, final OutputStream outputStream) throws IOException {
        formatHumanReadable(document);
        outputStream.write(toString(document).getBytes("UTF8"));
        outputStream.flush();
    }
    
    public static void write(final Document document, final Writer writer) throws IOException {
        formatHumanReadable(document);
        writer.write(toString(document));
        writer.flush();
    }
    
    public static void write(final Document document, final File file) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        write(document, fileOutputStream);
        fileOutputStream.close();
    }
    
    public static Document toXMLDocument(final String s) throws XMLParseException {
        if (s == null) {
            return toXMLDocument((byte[])null);
        }
        final InputSource inputSource = new InputSource(new StringReader(s));
        try {
            return readXMLDocument(inputSource);
        }
        catch (XMLParseException ex) {
            throw ex;
        }
        catch (IOException ex2) {
            throw new XMLParseException("##__root__##", "Could not parse XML document: " + ex2.getMessage());
        }
    }
    
    public static Document toXMLDocument(final char[] array) throws XMLParseException {
        return toXMLDocument(new String(array));
    }
    
    public static Document toXMLDocument(final byte[] array) throws XMLParseException {
        final InputSource inputSource = new InputSource(new ByteArrayInputStream(array));
        Document xmlDocument;
        try {
            xmlDocument = readXMLDocument(inputSource);
        }
        catch (XMLParseException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new XMLParseException("##__root__##", "Could not parse XML document: " + ex2.getMessage());
        }
        return xmlDocument;
    }
    
    public static Document toXMLDocument(final IXMLEncodable ixmlEncodable) {
        final Element xmlElement = toXMLElement(ixmlEncodable);
        if (xmlElement == null) {
            LogHolder.log(3, LogType.PAY, "XML element is null");
        }
        final Document ownerDocument = xmlElement.getOwnerDocument();
        if (ownerDocument == null) {
            LogHolder.log(3, LogType.PAY, "document is null");
        }
        ownerDocument.appendChild(xmlElement);
        return ownerDocument;
    }
    
    public static Document toSignedXMLDocument(final IXMLEncodable ixmlEncodable, final int n) {
        final Document xmlDocument = toXMLDocument(ixmlEncodable);
        SignatureCreator.getInstance().signXml(n, xmlDocument);
        return xmlDocument;
    }
    
    public static Element toXMLElement(final IXMLEncodable ixmlEncodable) {
        final Document document = createDocument();
        if (document == null) {
            return null;
        }
        return ixmlEncodable.toXmlElement(document);
    }
    
    public static final byte[] createDocumentStructure() {
        return toByteArrayOutputStream(createDocument()).toByteArray();
    }
    
    private static ByteArrayOutputStream toByteArrayOutputStream(final Node node) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        }
        catch (Throwable t) {
            return null;
        }
        try {
            if (Class.forName("com.sun.xml.tree.ParentNode").isInstance(node)) {
                Document ownerDocument;
                if (node instanceof Document) {
                    ownerDocument = (Document)node;
                }
                else {
                    ownerDocument = node.getOwnerDocument();
                }
                final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, "UTF8");
                node.getClass().getMethod("writeXml", Class.forName("com.sun.xml.tree.XmlWriteContext")).invoke(node, Class.forName("com.sun.xml.tree.XmlDocument").getMethod("createWriteContext", (XMLUtil.class$java$io$Writer == null) ? (XMLUtil.class$java$io$Writer = class$("java.io.Writer")) : XMLUtil.class$java$io$Writer, Integer.TYPE).invoke(ownerDocument, outputStreamWriter, new Integer(2)));
                outputStreamWriter.flush();
                return byteArrayOutputStream;
            }
        }
        catch (Throwable t2) {}
        try {
            final Class<?> forName = Class.forName("javax.xml.transform.TransformerFactory");
            final Object invoke = forName.getMethod("newTransformer", (Class[])null).invoke(forName.getMethod("newInstance", (Class[])null).invoke(forName, (Object[])null), (Object[])null);
            final Object instance = Class.forName("javax.xml.transform.stream.StreamResult").getConstructor((XMLUtil.class$java$io$OutputStream == null) ? (XMLUtil.class$java$io$OutputStream = class$("java.io.OutputStream")) : XMLUtil.class$java$io$OutputStream).newInstance(byteArrayOutputStream);
            final Object instance2 = Class.forName("javax.xml.transform.dom.DOMSource").getConstructor((XMLUtil.class$org$w3c$dom$Node == null) ? (XMLUtil.class$org$w3c$dom$Node = class$("org.w3c.dom.Node")) : XMLUtil.class$org$w3c$dom$Node).newInstance(node);
            final Class<?> forName2 = Class.forName("javax.xml.transform.Transformer");
            Method method = null;
            final Method[] methods = forName2.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals("transform")) {
                    method = methods[i];
                    if (method.getParameterTypes().length == 2) {
                        break;
                    }
                }
            }
            method.invoke(invoke, instance2, instance);
            return byteArrayOutputStream;
        }
        catch (Throwable t3) {
            return null;
        }
    }
    
    private static Node getFirstChildByNameUsingDeepSearchInternal(final Node node, final String s) {
        try {
            if (node.getNodeName().equals(s)) {
                return node;
            }
            if (!node.hasChildNodes()) {
                return null;
            }
            final NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); ++i) {
                final Node firstChildByNameUsingDeepSearchInternal = getFirstChildByNameUsingDeepSearchInternal(childNodes.item(i), s);
                if (firstChildByNameUsingDeepSearchInternal != null) {
                    return firstChildByNameUsingDeepSearchInternal;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private static int formatHumanReadable(final Node node, final int n) {
        int n2 = 0;
        if (!XMLUtil.m_bCheckedHumanReadableFormatting) {
            final Document document = createDocument();
            final Element element = document.createElement("test1");
            document.appendChild(element);
            element.appendChild(document.createElement("test2"));
            element.appendChild(document.createElement("test3"));
            final StringTokenizer stringTokenizer = new StringTokenizer(toString(element), "\n");
            int n3 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                ++n3;
                stringTokenizer.nextToken();
            }
            if (n3 == 4) {
                XMLUtil.m_bNeedsHumanReadableFormatting = false;
            }
            XMLUtil.m_bCheckedHumanReadableFormatting = true;
        }
        if (!XMLUtil.m_bNeedsHumanReadableFormatting) {
            return 0;
        }
        if (node.getNodeType() == 1 && parseAttribute(node, "xml:space", "").equals("preserve")) {
            return 0;
        }
        if (node.hasChildNodes()) {
            final NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i += formatHumanReadable(childNodes.item(i), n + 1), ++i) {}
        }
        if (node.getNodeType() == 3) {
            String nodeValue = node.getNodeValue();
            for (int j = 0; j < XMLUtil.SPECIAL_CHARS.length; ++j) {
                nodeValue = Util.replaceAll(nodeValue, XMLUtil.SPECIAL_CHARS[j], XMLUtil.ENTITIES[j], (String[])(XMLUtil.SPECIAL_CHARS[j].equals("&") ? XMLUtil.ENTITIES : null));
            }
            node.setNodeValue(nodeValue);
        }
        if (node.getNodeType() == 3 && (node.getNodeValue() == null || (node.getNodeValue().trim().length() == 0 && node.getNodeValue().indexOf(10) == -1))) {
            int n4;
            if (node.getNextSibling() == null && (node.getPreviousSibling() == null || node.getPreviousSibling().getNodeType() != 3 || node.getPreviousSibling().getNodeValue().indexOf(10) == -1)) {
                String string = new String();
                for (int k = 0; k < n - 1; ++k) {
                    string += "    ";
                }
                node.getParentNode().appendChild(node.getOwnerDocument().createTextNode(string));
                n4 = 0;
            }
            else {
                n4 = -1;
            }
            node.getParentNode().removeChild(node);
            return n4;
        }
        if (node.getOwnerDocument().getDocumentElement() != node && node.getNodeType() != 3) {
            node.getNextSibling();
            final StringBuffer sb = new StringBuffer();
            for (int l = 0; l < n; ++l) {
                sb.append("    ");
            }
            final String string2 = sb.toString();
            if (node == node.getParentNode().getFirstChild()) {
                node.getParentNode().insertBefore(node.getOwnerDocument().createTextNode("\n" + string2), node);
                ++n2;
            }
            final Node nextSibling = node.getNextSibling();
            if (nextSibling != null && nextSibling.getNodeType() != 3) {
                node.getParentNode().insertBefore(node.getOwnerDocument().createTextNode("\n" + string2), nextSibling);
                ++n2;
            }
            else if (nextSibling == null) {
                node.getParentNode().appendChild(node.getOwnerDocument().createTextNode("\n" + string2.substring(0, string2.length() - "    ".length())));
                ++n2;
            }
        }
        return n2;
    }
    
    private static int removeCommentsInternal(final Node node, final Node node2) {
        if (node.getNodeType() == 1 && parseAttribute(node, "xml:space", "").equals("preserve")) {
            return 0;
        }
        if (node.getNodeType() == 8) {
            node2.removeChild(node);
            return 1;
        }
        if (node.getNodeType() == 3 && node.getNodeValue().trim().length() == 0) {
            node2.removeChild(node);
            return 1;
        }
        if (node.hasChildNodes()) {
            final NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i -= removeCommentsInternal(childNodes.item(i), node), ++i) {}
        }
        return 0;
    }
    
    public static String stripNewlineFromHash(String substring) {
        final String substring2 = substring.substring(28);
        if (substring.length() == 29 && substring2.equals("\n")) {
            substring = substring.substring(0, 28);
        }
        return substring;
    }
    
    public static void printXmlEncodable(final IXMLEncodable ixmlEncodable) {
        System.out.println(toString(toXMLElement(ixmlEncodable)));
    }
    
    public static BigInteger parseValue(final Element element, final BigInteger bigInteger) {
        try {
            final String value = parseValue(element, (String)null);
            if (value == null) {
                return bigInteger;
            }
            return new BigInteger(value.trim());
        }
        catch (Exception ex) {
            return bigInteger;
        }
    }
    
    public static void setValue(final Element element, final BigInteger bigInteger) {
        try {
            setValue(element, bigInteger.toString());
        }
        catch (Exception ex) {}
    }
    
    public static Document createDocumentFromElement(final Element element) throws XMLParseException {
        final Document document = createDocument();
        document.appendChild(importNode(document, element, true));
        return document;
    }
    
    public static String filterXMLChars(final String s) {
        if (s == null) {
            return null;
        }
        return Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(s, "&", "&#38;"), "<", "&#60;"), ">", "&#62;"), "\"", "&#34;");
    }
    
    public static void filterXMLCharsForAnObject(final Object o) {
        if (o == null) {
            return;
        }
        final Class<?> class1 = o.getClass();
        final Method[] methods = class1.getMethods();
        for (int i = 0; i < methods.length; ++i) {
            if (methods[i].getParameterTypes().length == 1) {
                final int modifiers = methods[i].getModifiers();
                if (methods[i].getParameterTypes()[0].equals((XMLUtil.class$java$lang$String == null) ? (XMLUtil.class$java$lang$String = class$("java.lang.String")) : XMLUtil.class$java$lang$String) && methods[i].getName().startsWith("set") && Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                    final Method method = methods[i];
                    final String substring = method.getName().substring(3);
                    if (substring != null && !substring.equals("")) {
                        try {
                            final Method method2 = class1.getMethod("get" + substring, (Class[])null);
                            if (method2 != null && method2.getReturnType().equals((XMLUtil.class$java$lang$String == null) ? (XMLUtil.class$java$lang$String = class$("java.lang.String")) : XMLUtil.class$java$lang$String)) {
                                final String s = (String)method2.invoke(o, (Object[])null);
                                if (s != null) {
                                    method.invoke(o, filterXMLChars(s));
                                }
                            }
                        }
                        catch (SecurityException ex) {}
                        catch (NoSuchMethodException ex2) {}
                        catch (IllegalArgumentException ex3) {}
                        catch (IllegalAccessException ex4) {}
                        catch (InvocationTargetException ex5) {}
                    }
                }
            }
        }
    }
    
    public static String restoreFilteredXMLChars(final String s) {
        if (s == null) {
            return null;
        }
        return Util.replaceAll(Util.replaceAll(Util.replaceAll(Util.replaceAll(s, "&#38;", "&"), "&#60;", "<"), "&#62;", ">"), "&#34;", "\"");
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        XMLUtil.m_bCheckedHumanReadableFormatting = false;
        XMLUtil.m_bNeedsHumanReadableFormatting = true;
        XMLUtil.ms_storageMode = 0;
        SPECIAL_CHARS = new String[] { "&", "<", ">" };
        ENTITIES = new String[] { "&amp;", "&lt;", "&gt;" };
        if (XMLUtil.ms_DocumentBuilderFactory == null) {
            try {
                XMLUtil.ms_DocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            }
            catch (Exception ex) {}
        }
    }
}
