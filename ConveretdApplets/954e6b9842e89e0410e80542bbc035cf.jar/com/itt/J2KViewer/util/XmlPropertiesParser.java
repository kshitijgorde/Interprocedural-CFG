// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.swing.tree.MutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import java.io.Reader;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import org.w3c.dom.Document;
import java.util.List;

public class XmlPropertiesParser
{
    private static Log log;
    private static final String ROOT_NAME = "XML Data";
    private List boxes;
    private List xmlTextList;
    private Document xmlDoc;
    private DefaultMutableTreeNode rootNode;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$XmlPropertiesParser;
    
    public XmlPropertiesParser() {
        this.boxes = null;
        this.xmlTextList = null;
        this.xmlDoc = null;
        this.rootNode = null;
        this.boxes = new ArrayList();
        this.xmlTextList = new ArrayList();
    }
    
    public void addBox(final String s, final int n) {
        this.buildXmlDocument(s, n);
        this.buildNodes(n);
        this.xmlTextList.add(s);
    }
    
    public DefaultMutableTreeNode getRootNode() {
        return this.rootNode;
    }
    
    public boolean isReady() {
        return this.rootNode != null && this.rootNode.getChildCount() > 0;
    }
    
    public List getXMLBoxes() {
        return this.boxes;
    }
    
    public List getXMLBoxStringsList() {
        return this.xmlTextList;
    }
    
    private void buildXmlDocument(String s, final int n) {
        this.xmlDoc = null;
        try {
            if (s != null) {
                if (s.indexOf("?>") > 0) {
                    s = s.substring(s.indexOf("?>") + 2);
                }
                if (s.indexOf("\">") > 0 && s.indexOf("<!DOCTYPE") > 0) {
                    s = s.substring(s.lastIndexOf("\">") + 2);
                }
                if (s.indexOf("'>") > 0 && s.indexOf("<!DOCTYPE") > 0) {
                    s = s.substring(s.lastIndexOf("'>") + 2);
                }
                final DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                try {
                    this.xmlDoc = documentBuilder.parse(new InputSource(new StringReader(s)));
                    this.boxes.add(this.xmlDoc);
                }
                catch (Exception ex2) {
                    s = this.fixXmlString(s);
                    this.xmlDoc = documentBuilder.parse(new InputSource(new StringReader(s)));
                    this.boxes.add(this.xmlDoc);
                }
            }
            else {
                XmlPropertiesParser.log.warn("XML String is null and could not be parsed.");
            }
        }
        catch (Exception ex) {
            XmlPropertiesParser.log.error("Error parsing XML", ex);
        }
    }
    
    private String fixXmlString(final String s) {
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\"') {
                b = !b;
                sb.append(char1);
            }
            else if (char1 == '&') {
                sb.append("&amp;");
            }
            else if (char1 == '<' && b) {
                sb.append("&lt;");
            }
            else if (char1 == '>' && b) {
                sb.append("&gt;");
            }
            else if (char1 > '\u007f') {
                sb.append("&#x" + Integer.toHexString(char1) + ";");
            }
            else if (char1 < ' ') {
                sb.append(" ");
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    private void buildNodes(final int n) {
        if (this.xmlDoc != null) {
            final Element documentElement = this.xmlDoc.getDocumentElement();
            if (this.rootNode == null) {
                this.rootNode = new DefaultMutableTreeNode("XML Data", true);
            }
            final DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(documentElement.getNodeName(), true);
            this.rootNode.add(defaultMutableTreeNode);
            this.doBuildNodes(documentElement, defaultMutableTreeNode);
        }
        else {
            XmlPropertiesParser.log.info("XML Document is null and could not be processed.");
        }
    }
    
    private String XMLDecode(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '&' && i + 3 < length) {
                if (s.charAt(i + 1) == 'l' && s.charAt(i + 2) == 't' && s.charAt(i + 3) == ';') {
                    sb.append('<');
                    i += 3;
                }
                else if (s.charAt(i + 1) == 'g' && s.charAt(i + 2) == 't' && s.charAt(i + 3) == ';') {
                    sb.append('>');
                    i += 3;
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    private DefaultMutableTreeNode doBuildNodes(final Node node, final DefaultMutableTreeNode defaultMutableTreeNode) {
        final NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            final Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                if (item.getChildNodes().getLength() > 1) {
                    defaultMutableTreeNode.add(this.doBuildNodes(item, new DefaultMutableTreeNode(item.getNodeName(), true)));
                }
                else if (item.getChildNodes().getLength() == 1) {
                    final DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(item.getNodeName(), true);
                    defaultMutableTreeNode2.add(new DefaultMutableTreeNode(this.XMLDecode(item.getFirstChild().getNodeValue()), false));
                    defaultMutableTreeNode.add(defaultMutableTreeNode2);
                }
            }
        }
        return defaultMutableTreeNode;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        XmlPropertiesParser.log = new Log((XmlPropertiesParser.class$com$itt$J2KViewer$util$XmlPropertiesParser == null) ? (XmlPropertiesParser.class$com$itt$J2KViewer$util$XmlPropertiesParser = class$("com.itt.J2KViewer.util.XmlPropertiesParser")) : XmlPropertiesParser.class$com$itt$J2KViewer$util$XmlPropertiesParser);
    }
}
