// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xdom;

import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import java.io.FileInputStream;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Transformer;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.w3c.dom.Document;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.File;
import java.net.URL;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Node;
import java.util.Map;
import java.util.HashMap;
import org.w3c.dom.Element;

public class XDOM
{
    protected static XPathFinder finder;
    protected Element root;
    protected HashMap xp;
    
    static {
        XDOM.finder = new XPathFinder();
    }
    
    public XDOM() {
        this.root = null;
        this.xp = new HashMap();
    }
    
    public XDOM(final Element node) throws Exception {
        this();
        this.setRoot(node);
    }
    
    public XDOM(final String name) throws Exception {
        this(getDocument(name).getDocumentElement());
    }
    
    public XDOM(final String name, final Map pathAndValues) throws Exception {
        this(getDocument(name, pathAndValues).getDocumentElement());
    }
    
    public XDOM(final Node node) throws Exception {
        this((Element)node);
    }
    
    public XDOM(final DOMResult domResult) throws Exception {
        this(domResult.getNode());
    }
    
    public XDOM(final URL url) throws Exception {
        this(getDocument(url).getDocumentElement());
    }
    
    public XDOM(final File f) throws Exception {
        this(getDocument(f).getDocumentElement());
    }
    
    public XDOM(final InputStream istr) throws Exception {
        this(getDocument(istr).getDocumentElement());
    }
    
    public XDOM(final byte[] text) throws Exception {
        this(new ByteArrayInputStream(text));
    }
    
    public Element getRoot() {
        return this.root;
    }
    
    public Document getDocument() throws Exception {
        return (this.root == null) ? getParser().newDocument() : this.root.getOwnerDocument();
    }
    
    public void setRoot(final Element root) {
        this.root = root;
    }
    
    public void setRoot(final String name) throws Exception {
        this.setRoot(getDocument(name).getDocumentElement());
    }
    
    public Iterator iterator() {
        return new NodeIterator(this.root);
    }
    
    @Override
    public boolean equals(final Object obj) {
        boolean result = true;
        try {
            final XDOM docB = (XDOM)obj;
            final Iterator itrA = this.iterator();
            final Iterator itrB = docB.iterator();
            while (itrA.hasNext()) {
                final Node a = itrA.next();
                final Node b = itrB.next();
                if (!nodeEquals(a, b)) {
                    result = false;
                    break;
                }
            }
            if (itrB.hasNext()) {
                result = false;
            }
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public Element appendElement(final String name) throws Exception {
        return appendElement(this.root, name);
    }
    
    public Element appendElement(final String name, final String value) throws Exception {
        return appendElement(this.root, name, value);
    }
    
    public void append(final Node n) throws Exception {
        appendAt(this.root, n);
    }
    
    public Element smartAdd(final String path) throws Exception {
        return this.smartAdd(path, null);
    }
    
    public Element smartAdd(final String path, final String value) throws Exception {
        return smartAdd(this.root, path, value);
    }
    
    public Node findNode(final String xpath) {
        return findNode(this.root, xpath);
    }
    
    public Collection findNodes(final String xpath) {
        return findNodes(this.root, xpath);
    }
    
    public Collection findNodeValues(final String xpath) {
        return findNodeValues(this.root, xpath);
    }
    
    public Element findElement(final String xpath) {
        return findElement(this.root, xpath);
    }
    
    public String findValue(final String xpath) {
        return findValue(this.root, xpath);
    }
    
    public String findCdataValue(final String xpath) {
        return findCdataValue(this.root, xpath);
    }
    
    public String findValue(final String xpath, final String def) {
        final String result = this.findValue(xpath);
        return (result == null) ? def : result;
    }
    
    public int findIntValue(final String xpath) {
        return findIntValue(this.root, xpath);
    }
    
    public int getNodeCount(final String xpath) {
        return getNodeCount(this.root, xpath);
    }
    
    public void setValue(final String xpath, final String value) {
        setValue(this.root, xpath, value);
    }
    
    public void setCdataValue(final String xpath, final String value) {
        setCdataValue(this.root, xpath, value);
    }
    
    public XDOM transform(final URL xslt) throws Exception {
        return this.transform(getTransformer(xslt));
    }
    
    public XDOM transform(final File xslt) throws Exception {
        return this.transform(getTransformer(xslt));
    }
    
    public XDOM transform(final InputStream xslt) throws Exception {
        return this.transform(getTransformer(xslt));
    }
    
    public void transform(final File xslt, final OutputStream ostr) throws Exception {
        this.transform(getTransformer(xslt), ostr);
    }
    
    public void transform(final URL xslt, final OutputStream ostr) throws Exception {
        this.transform(getTransformer(xslt), ostr);
    }
    
    public void transform(final InputStream istr, final OutputStream ostr) throws Exception {
        this.transform(getTransformer(istr), ostr);
    }
    
    public void transform(final Transformer t, final OutputStream ostr) throws Exception {
        setOutputProperties(t, this.xp);
        t.transform(new DOMSource(this.root), new StreamResult(ostr));
    }
    
    public String applyStylesheet(final URL xsl) throws Exception {
        return this.applyStylesheet(getTransformer(xsl));
    }
    
    public String applyStylesheet(final File xsl) throws Exception {
        return this.applyStylesheet(getTransformer(xsl));
    }
    
    public String applyStylesheet(final InputStream xsl) throws Exception {
        return this.applyStylesheet(getTransformer(xsl));
    }
    
    public void write(final OutputStream ostr) throws Exception {
        final Transformer t = getTransformer();
        setOutputProperties(t, this.xp);
        t.transform(new DOMSource(this.root), new StreamResult(ostr));
    }
    
    @Override
    public String toString() {
        final ByteArrayOutputStream ostr = new ByteArrayOutputStream();
        try {
            this.write(ostr);
        }
        catch (Exception e) {
            final PrintWriter writer = new PrintWriter(ostr);
            writer.println("<exception>");
            writer.println("<![CDATA[");
            writer.println(e);
            e.printStackTrace(writer);
            writer.println("]]>");
            writer.println("</exception>");
            writer.flush();
        }
        return ostr.toString();
    }
    
    public void setOutputProperty(final Object key, final Object value) {
        this.xp.put(key, value);
    }
    
    XDOM transform(final Transformer t) throws Exception {
        setOutputProperties(t, this.xp);
        final Document result = getParser().newDocument();
        final Document doc = getDocument(this.root);
        final DOMSource domSource = new DOMSource(doc);
        t.transform(domSource, new DOMResult(result));
        return new XDOM(result.getDocumentElement());
    }
    
    String applyStylesheet(final Transformer t) throws Exception {
        setOutputProperties(t, this.xp);
        final Document doc = getDocument(this.root);
        final ByteArrayOutputStream ostr = new ByteArrayOutputStream();
        final DOMSource domSource = new DOMSource(doc);
        t.transform(domSource, new StreamResult(ostr));
        return ostr.toString();
    }
    
    public static Node findNode(final Node n, final String xpath) {
        Node result = null;
        try {
            result = XDOM.finder.selectSingleNode(n, xpath);
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static Collection findNodes(final Node n, final String xpath) {
        final LinkedList result = new LinkedList();
        try {
            final NodeList list = XDOM.finder.selectNodeList(n, xpath);
            for (int i = 0; i < list.getLength(); ++i) {
                result.add(list.item(i));
            }
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static Collection findNodeValues(final Node node, final String xpath) {
        final LinkedList result = new LinkedList();
        try {
            final NodeList list = XDOM.finder.selectNodeList(node, xpath);
            for (int i = 0; i < list.getLength(); ++i) {
                final Node n = list.item(i);
                final String s = getValue(n);
                result.add(s);
            }
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static int getNodeCount(final Node n, final String xpath) {
        int result = 0;
        try {
            final NodeList list = XDOM.finder.selectNodeList(n, xpath);
            result = list.getLength();
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static Element findElement(final Node n, final String xpath) {
        Element result = null;
        try {
            result = (Element)findNode(n, xpath);
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static String findValue(final Node node, final String xpath) {
        String result = null;
        try {
            result = getValue(findNode(node, xpath));
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static String findCdataValue(final Node node, final String xpath) {
        String result = null;
        try {
            result = getCdataValue(findNode(node, xpath));
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static void setValue(final Node node, final String xpath, final String value) {
        try {
            final Node n = findNode(node, xpath);
            switch (n.getNodeType()) {
                case 2:
                case 3:
                case 4: {
                    n.setNodeValue(value);
                    break;
                }
                default: {
                    Node textNode = getTextChild(n);
                    if (textNode == null) {
                        final Document d = n.getOwnerDocument();
                        textNode = d.createTextNode(value);
                        n.appendChild(textNode);
                        break;
                    }
                    textNode.setNodeValue(value);
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static void setCdataValue(final Node node, final String xpath, final String value) {
        try {
            final Node n = findNode(node, xpath);
            switch (n.getNodeType()) {
                case 2:
                case 3:
                case 4: {
                    n.setNodeValue(value);
                    break;
                }
                default: {
                    Node cdata = getCdataChild(n);
                    if (cdata == null) {
                        final Document d = n.getOwnerDocument();
                        cdata = d.createCDATASection(value);
                        n.appendChild(cdata);
                        break;
                    }
                    cdata.setNodeValue(value);
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static String getValue(final Node node) {
        String result = null;
        switch (node.getNodeType()) {
            case 2:
            case 3:
            case 4: {
                result = node.getNodeValue();
                break;
            }
            default: {
                final Node n = getTextChild(node);
                if (n != null) {
                    result = n.getNodeValue();
                    break;
                }
                break;
            }
        }
        return result;
    }
    
    public static String getCdataValue(final Node node) {
        String result = null;
        switch (node.getNodeType()) {
            case 2:
            case 3:
            case 4: {
                result = node.getNodeValue();
                break;
            }
            default: {
                final Node n = getCdataChild(node);
                if (n != null) {
                    result = n.getNodeValue();
                    break;
                }
                break;
            }
        }
        return result;
    }
    
    public static Node getTextChild(final Node node) {
        Node result = null;
        final NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            final Node n = list.item(i);
            if (n.getNodeType() == 3) {
                result = n;
                break;
            }
        }
        return result;
    }
    
    public static Node getCdataChild(final Node node) {
        Node result = null;
        final NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            final Node n = list.item(i);
            if (n.getNodeType() == 4) {
                result = n;
                break;
            }
        }
        return result;
    }
    
    public static int findIntValue(final Node n, final String xpath) {
        int result = 0;
        try {
            result = Integer.parseInt(findValue(n, xpath));
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static DocumentBuilder getParser() throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        return factory.newDocumentBuilder();
    }
    
    public static Transformer getTransformer(final URL url) throws Exception {
        final URLConnection con = url.openConnection();
        return getTransformer(con.getInputStream());
    }
    
    public static Transformer getTransformer(final File xslt) throws Exception {
        return getTransformer(new FileInputStream(xslt));
    }
    
    public static Transformer getTransformer(final InputStream istr) throws Exception {
        final TransformerFactory factory = TransformerFactory.newInstance();
        return factory.newTransformer(new StreamSource(istr));
    }
    
    public static Transformer getTransformer(final Document doc) throws Exception {
        final TransformerFactory factory = TransformerFactory.newInstance();
        return factory.newTransformer(new DOMSource(doc));
    }
    
    public static void transform(final URL doc, final URL xslt, final OutputStream ostr) throws Exception {
        final URLConnection con = doc.openConnection();
        final InputStream istr = con.getInputStream();
        transform(istr, getTransformer(xslt), ostr, null);
    }
    
    public static void transform(final URL doc, final File xslt, final OutputStream ostr) throws Exception {
        final URLConnection con = doc.openConnection();
        final InputStream istr = con.getInputStream();
        transform(istr, getTransformer(xslt), ostr, null);
    }
    
    public static void transform(final File doc, final File xslt, final OutputStream ostr) throws Exception {
        final InputStream istr = new FileInputStream(doc);
        transform(istr, getTransformer(xslt), ostr, null);
    }
    
    public static void transform(final URL doc, final URL xslt, final OutputStream ostr, final Map params) throws Exception {
        final URLConnection con = doc.openConnection();
        final InputStream istr = con.getInputStream();
        transform(istr, getTransformer(xslt), ostr, params);
    }
    
    public static void transform(final File doc, final File xslt, final OutputStream ostr, final Map params) throws Exception {
        final InputStream istr = new FileInputStream(doc);
        transform(istr, getTransformer(xslt), ostr, params);
    }
    
    public static void transform(final URL doc, final File xslt, final File output, final Map params) throws Exception {
        final URLConnection con = doc.openConnection();
        final InputStream istr = con.getInputStream();
        final OutputStream ostr = new FileOutputStream(output);
        transform(istr, getTransformer(xslt), ostr, params);
        ostr.close();
    }
    
    public static void transform(final InputStream doc, final InputStream xslt, final OutputStream output) throws Exception {
        transform(doc, getTransformer(xslt), output, null);
    }
    
    public static void transform(final InputStream istr, final Transformer t, final OutputStream ostr, final Map params) throws Exception {
        setOutputProperties(t, params);
        final BufferedInputStream bIn = new BufferedInputStream(istr);
        stripLeadingTrash(bIn);
        t.transform(new StreamSource(bIn), new StreamResult(ostr));
    }
    
    public static Node transform(final InputStream istr, final Transformer t, final Map params) throws Exception {
        setOutputProperties(t, params);
        final BufferedInputStream bIn = new BufferedInputStream(istr);
        stripLeadingTrash(bIn);
        final Document result = getParser().newDocument();
        final DOMResult domResult = new DOMResult(result);
        t.transform(new StreamSource(bIn), domResult);
        return domResult.getNode();
    }
    
    public static Transformer getTransformer() throws Exception {
        final TransformerFactory factory = TransformerFactory.newInstance();
        return factory.newTransformer();
    }
    
    public static Document getDocument(final URL url) throws Exception {
        final URLConnection con = url.openConnection();
        final InputStream istr = con.getInputStream();
        final Document d = getDocument(istr);
        istr.close();
        return d;
    }
    
    public static Document getDocument(final InputStream istr) throws Exception {
        final BufferedInputStream bIn = new BufferedInputStream(istr);
        stripLeadingTrash(bIn);
        return getParser().parse(bIn);
    }
    
    public static Document getDocument(final File f) throws Exception {
        return getParser().parse(new FileInputStream(f));
    }
    
    public static Document getDocument(final Node n) throws Exception {
        final Document d = getParser().newDocument();
        d.appendChild(d.importNode(n, true));
        return d;
    }
    
    public static Document getDocument(final String name) throws Exception {
        final Document d = getParser().newDocument();
        d.appendChild(d.createElement(name));
        return d;
    }
    
    public static Document getDocument(final String name, final Map pathAndValues) throws Exception {
        final Document d = getDocument(name);
        final Element root = d.getDocumentElement();
        for (final String path : pathAndValues.keySet()) {
            final String value = pathAndValues.get(path);
            smartAdd(root, path, value);
        }
        return d;
    }
    
    public static void appendAt(final Element point, Node node) throws Exception {
        final Document pDoc = point.getOwnerDocument();
        final Document nDoc = node.getOwnerDocument();
        if (pDoc != nDoc) {
            node = pDoc.importNode(node, true);
        }
        point.appendChild(node);
    }
    
    public static void setOutputProperties(final Transformer t, final Map m) {
        try {
            setDefaultOutputProperties(t);
            final Iterator itr = m.keySet().iterator();
            while (itr.hasNext()) {
                final String key = itr.next().toString();
                t.setOutputProperty(key, m.get(key));
            }
        }
        catch (Exception ex) {}
    }
    
    public static boolean nodeEquals(final Node a, final Node b) {
        final boolean nameMatch = cmp(a.getNodeName(), b.getNodeName());
        final boolean valueMatch = cmp(getValue(a), getValue(b));
        return nameMatch && valueMatch;
    }
    
    public static void setDefaultOutputProperties(final Transformer t) {
        t.setOutputProperty("method", "xml");
        t.setOutputProperty("omit-xml-declaration", "no");
        t.setOutputProperty("encoding", "ISO-8859-1");
        t.setOutputProperty("indent", "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
    }
    
    public static String encodeEntityReferences(final String s, final boolean limited) {
        if (s == null) {
            return s;
        }
        final int len = s.length();
        final StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; ++i) {
            final char c = s.charAt(i);
            switch (c) {
                case '<': {
                    sb.append("&lt;");
                    break;
                }
                case '>': {
                    sb.append("&gt;");
                    break;
                }
                case '&': {
                    sb.append(limited ? "&" : "&amp;");
                    break;
                }
                case '\'': {
                    sb.append(limited ? "'" : "&apos;");
                    break;
                }
                case '\"': {
                    sb.append(limited ? "\"" : "&quot;");
                    break;
                }
                default: {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static String decodeEntityReferences(final String s) {
        String result = s.replaceAll("&lt;", "<");
        result = result.replaceAll("&gt;", ">");
        result = result.replaceAll("&amp;", "&");
        result = result.replaceAll("&pos;", "'");
        result = result.replaceAll("&quot;", "\"");
        return result;
    }
    
    public static Element appendElement(final Node n, final String name) throws Exception {
        final Document d = n.getOwnerDocument();
        final Element element = d.createElement(name);
        n.appendChild(element);
        return element;
    }
    
    public static Element appendElement(final Node n, final String name, final String value) throws Exception {
        final Document d = n.getOwnerDocument();
        final Element element = appendElement(n, name);
        if (value == null) {
            return element;
        }
        final Node tn = d.createTextNode(value);
        element.appendChild(tn);
        return element;
    }
    
    public static Element smartAdd(final Element root, final String path, final String value) throws Exception {
        Element node = root;
        final Document d = node.getOwnerDocument();
        final StringTokenizer tk = new StringTokenizer(path, "/");
        while (tk.hasMoreTokens()) {
            final String name = tk.nextToken();
            Element child = findChildElement(node, name);
            if (child == null || !tk.hasMoreTokens()) {
                child = appendElement(node, name);
            }
            node = child;
        }
        if (value != null) {
            final Node textNode = d.createTextNode(value);
            node.appendChild(textNode);
        }
        return node;
    }
    
    protected static Element findChildElement(final Node node, final String name) {
        return (Element)findChild(node, name);
    }
    
    protected static Node findChild(final Node node, final String name) {
        final NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            final Node n = list.item(i);
            if (name.equals(n.getNodeName())) {
                return n;
            }
        }
        return null;
    }
    
    protected static void stripLeadingTrash(final BufferedInputStream istr) throws IOException {
        int c;
        do {
            istr.mark(1);
            c = istr.read();
        } while (c != 60 && c != -1);
        istr.reset();
    }
    
    protected static boolean cmp(final String s1, final String s2) {
        boolean result = true;
        try {
            final String stringA = (s1 == null) ? s2 : s1;
            final String stringB = (stringA == s1) ? s2 : s1;
            result = stringA.equals(stringB);
        }
        catch (Exception ex) {}
        return result;
    }
}
