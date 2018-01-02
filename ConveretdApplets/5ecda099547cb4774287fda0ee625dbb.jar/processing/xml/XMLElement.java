// 
// Decompiled by Procyon v0.5.30
// 

package processing.xml;

import java.util.Hashtable;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import java.util.Enumeration;
import java.io.StringReader;
import java.io.Reader;
import processing.core.PApplet;
import java.util.Vector;
import java.io.Serializable;

public class XMLElement implements Serializable
{
    public static final int NO_LINE = -1;
    private XMLElement parent;
    private Vector<XMLAttribute> attributes;
    private Vector<XMLElement> children;
    private String name;
    private String fullName;
    private String namespace;
    private String content;
    private String systemID;
    private int lineNr;
    
    public XMLElement() {
        this(null, null, null, -1);
    }
    
    protected void set(final String name, final String namespace, final String systemID, final int lineNr) {
        this.fullName = name;
        if (namespace == null) {
            this.name = name;
        }
        else {
            final int index = name.indexOf(58);
            if (index >= 0) {
                this.name = name.substring(index + 1);
            }
            else {
                this.name = name;
            }
        }
        this.namespace = namespace;
        this.lineNr = lineNr;
        this.systemID = systemID;
    }
    
    public XMLElement(final String name, final String namespace, final String systemID, final int lineNr) {
        this.attributes = new Vector<XMLAttribute>();
        this.children = new Vector<XMLElement>(8);
        this.fullName = name;
        if (namespace == null) {
            this.name = name;
        }
        else {
            final int index = name.indexOf(58);
            if (index >= 0) {
                this.name = name.substring(index + 1);
            }
            else {
                this.name = name;
            }
        }
        this.namespace = namespace;
        this.content = null;
        this.lineNr = lineNr;
        this.systemID = systemID;
        this.parent = null;
    }
    
    public XMLElement(final PApplet pApplet, final String s) {
        this();
        this.parseFromReader(pApplet.createReader(s));
    }
    
    public XMLElement(final Reader reader) {
        this();
        this.parseFromReader(reader);
    }
    
    public XMLElement(final String s) {
        this();
        this.parseFromReader(new StringReader(s));
    }
    
    protected void parseFromReader(final Reader reader) {
        try {
            final StdXMLParser stdXMLParser = new StdXMLParser();
            stdXMLParser.setBuilder(new StdXMLBuilder(this));
            stdXMLParser.setValidator(new XMLValidator());
            stdXMLParser.setReader(new StdXMLReader(reader));
            stdXMLParser.parse();
        }
        catch (XMLException ex) {
            ex.printStackTrace();
        }
    }
    
    public XMLElement createPCDataElement() {
        return new XMLElement();
    }
    
    public XMLElement createElement(final String s, final String s2) {
        return new XMLElement(s, s2, null, -1);
    }
    
    public XMLElement createElement(final String s, final String s2, final String s3, final int n) {
        return new XMLElement(s, s2, s3, n);
    }
    
    protected void finalize() throws Throwable {
        this.attributes.clear();
        this.attributes = null;
        this.children = null;
        this.fullName = null;
        this.name = null;
        this.namespace = null;
        this.content = null;
        this.systemID = null;
        this.parent = null;
        super.finalize();
    }
    
    public XMLElement getParent() {
        return this.parent;
    }
    
    public String getName() {
        return this.fullName;
    }
    
    public String getLocalName() {
        return this.name;
    }
    
    public String getNamespace() {
        return this.namespace;
    }
    
    public void setName(final String s) {
        this.name = s;
        this.fullName = s;
        this.namespace = null;
    }
    
    public void setName(final String s, final String namespace) {
        final int index = s.indexOf(58);
        if (namespace == null || index < 0) {
            this.name = s;
        }
        else {
            this.name = s.substring(index + 1);
        }
        this.fullName = s;
        this.namespace = namespace;
    }
    
    public void addChild(final XMLElement xmlElement) {
        if (xmlElement == null) {
            throw new IllegalArgumentException("child must not be null");
        }
        if (xmlElement.getLocalName() == null && !this.children.isEmpty()) {
            final XMLElement xmlElement2 = this.children.lastElement();
            if (xmlElement2.getLocalName() == null) {
                xmlElement2.setContent(xmlElement2.getContent() + xmlElement.getContent());
                return;
            }
        }
        xmlElement.parent = this;
        this.children.addElement(xmlElement);
    }
    
    public void insertChild(final XMLElement xmlElement, final int n) {
        if (xmlElement == null) {
            throw new IllegalArgumentException("child must not be null");
        }
        if (xmlElement.getLocalName() == null && !this.children.isEmpty()) {
            final XMLElement xmlElement2 = this.children.lastElement();
            if (xmlElement2.getLocalName() == null) {
                xmlElement2.setContent(xmlElement2.getContent() + xmlElement.getContent());
                return;
            }
        }
        xmlElement.parent = this;
        this.children.insertElementAt(xmlElement, n);
    }
    
    public void removeChild(final XMLElement xmlElement) {
        if (xmlElement == null) {
            throw new IllegalArgumentException("child must not be null");
        }
        this.children.removeElement(xmlElement);
    }
    
    public void removeChildAtIndex(final int n) {
        this.children.removeElementAt(n);
    }
    
    public Enumeration<XMLElement> enumerateChildren() {
        return this.children.elements();
    }
    
    public boolean isLeaf() {
        return this.children.isEmpty();
    }
    
    public boolean hasChildren() {
        return !this.children.isEmpty();
    }
    
    public int getChildCount() {
        return this.children.size();
    }
    
    public String[] listChildren() {
        final int childCount = this.getChildCount();
        final String[] array = new String[childCount];
        for (int i = 0; i < childCount; ++i) {
            array[i] = this.getChild(i).getName();
        }
        return array;
    }
    
    public XMLElement[] getChildren() {
        final XMLElement[] array = new XMLElement[this.getChildCount()];
        this.children.copyInto(array);
        return array;
    }
    
    public XMLElement getChild(final int n) {
        return this.children.elementAt(n);
    }
    
    public XMLElement getChild(final String s) {
        if (s.indexOf(47) != -1) {
            return this.getChildRecursive(PApplet.split(s, '/'), 0);
        }
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            final XMLElement child = this.getChild(i);
            final String name = child.getName();
            if (name != null && name.equals(s)) {
                return child;
            }
        }
        return null;
    }
    
    protected XMLElement getChildRecursive(final String[] array, final int n) {
        if (!Character.isDigit(array[n].charAt(0))) {
            final int childCount = this.getChildCount();
            int i = 0;
            while (i < childCount) {
                final XMLElement child = this.getChild(i);
                final String name = child.getName();
                if (name != null && name.equals(array[n])) {
                    if (n == array.length - 1) {
                        return child;
                    }
                    return child.getChildRecursive(array, n + 1);
                }
                else {
                    ++i;
                }
            }
            return null;
        }
        final XMLElement child2 = this.getChild(Integer.parseInt(array[n]));
        if (n == array.length - 1) {
            return child2;
        }
        return child2.getChildRecursive(array, n + 1);
    }
    
    public XMLElement getChildAtIndex(final int n) throws ArrayIndexOutOfBoundsException {
        return this.children.elementAt(n);
    }
    
    public XMLElement[] getChildren(final String s) {
        if (s.indexOf(47) != -1) {
            return this.getChildrenRecursive(PApplet.split(s, '/'), 0);
        }
        if (Character.isDigit(s.charAt(0))) {
            return new XMLElement[] { this.getChild(Integer.parseInt(s)) };
        }
        final int childCount = this.getChildCount();
        final XMLElement[] array = new XMLElement[childCount];
        int n = 0;
        for (int i = 0; i < childCount; ++i) {
            final XMLElement child = this.getChild(i);
            final String name = child.getName();
            if (name != null && name.equals(s)) {
                array[n++] = child;
            }
        }
        return (XMLElement[])PApplet.subset(array, 0, n);
    }
    
    protected XMLElement[] getChildrenRecursive(final String[] array, final int n) {
        if (n == array.length - 1) {
            return this.getChildren(array[n]);
        }
        final XMLElement[] children = this.getChildren(array[n]);
        XMLElement[] array2 = new XMLElement[0];
        for (int i = 0; i < children.length; ++i) {
            array2 = (XMLElement[])PApplet.concat(array2, children[i].getChildrenRecursive(array, n + 1));
        }
        return array2;
    }
    
    private XMLAttribute findAttribute(final String s) {
        final Enumeration<XMLAttribute> elements = this.attributes.elements();
        while (elements.hasMoreElements()) {
            final XMLAttribute xmlAttribute = elements.nextElement();
            if (xmlAttribute.getFullName().equals(s)) {
                return xmlAttribute;
            }
        }
        return null;
    }
    
    private XMLAttribute findAttribute(final String s, final String s2) {
        final Enumeration<XMLAttribute> elements = this.attributes.elements();
        while (elements.hasMoreElements()) {
            final XMLAttribute xmlAttribute = elements.nextElement();
            final boolean equals = xmlAttribute.getName().equals(s);
            boolean b;
            if (s2 == null) {
                b = (equals & xmlAttribute.getNamespace() == null);
            }
            else {
                b = (equals & s2.equals(xmlAttribute.getNamespace()));
            }
            if (b) {
                return xmlAttribute;
            }
        }
        return null;
    }
    
    public int getAttributeCount() {
        return this.attributes.size();
    }
    
    public String getAttribute(final String s) {
        return this.getAttribute(s, null);
    }
    
    public String getAttribute(final String s, final String s2) {
        final XMLAttribute attribute = this.findAttribute(s);
        if (attribute == null) {
            return s2;
        }
        return attribute.getValue();
    }
    
    public String getAttribute(final String s, final String s2, final String s3) {
        final XMLAttribute attribute = this.findAttribute(s, s2);
        if (attribute == null) {
            return s3;
        }
        return attribute.getValue();
    }
    
    public String getStringAttribute(final String s) {
        return this.getAttribute(s);
    }
    
    public String getStringAttribute(final String s, final String s2) {
        return this.getAttribute(s, s2);
    }
    
    public String getStringAttribute(final String s, final String s2, final String s3) {
        return this.getAttribute(s, s2, s3);
    }
    
    public int getIntAttribute(final String s) {
        return this.getIntAttribute(s, 0);
    }
    
    public int getIntAttribute(final String s, final int n) {
        return Integer.parseInt(this.getAttribute(s, Integer.toString(n)));
    }
    
    public int getIntAttribute(final String s, final String s2, final int n) {
        return Integer.parseInt(this.getAttribute(s, s2, Integer.toString(n)));
    }
    
    public float getFloatAttribute(final String s) {
        return this.getFloatAttribute(s, 0.0f);
    }
    
    public float getFloatAttribute(final String s, final float n) {
        return Float.parseFloat(this.getAttribute(s, Float.toString(n)));
    }
    
    public float getFloatAttribute(final String s, final String s2, final float n) {
        return Float.parseFloat(this.getAttribute(s, s2, Float.toString(n)));
    }
    
    public double getDoubleAttribute(final String s) {
        return this.getDoubleAttribute(s, 0.0);
    }
    
    public double getDoubleAttribute(final String s, final double n) {
        return Double.parseDouble(this.getAttribute(s, Double.toString(n)));
    }
    
    public double getDoubleAttribute(final String s, final String s2, final double n) {
        return Double.parseDouble(this.getAttribute(s, s2, Double.toString(n)));
    }
    
    public String getAttributeType(final String s) {
        final XMLAttribute attribute = this.findAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.getType();
    }
    
    public String getAttributeNamespace(final String s) {
        final XMLAttribute attribute = this.findAttribute(s);
        if (attribute == null) {
            return null;
        }
        return attribute.getNamespace();
    }
    
    public String getAttributeType(final String s, final String s2) {
        final XMLAttribute attribute = this.findAttribute(s, s2);
        if (attribute == null) {
            return null;
        }
        return attribute.getType();
    }
    
    public void setAttribute(final String s, final String value) {
        final XMLAttribute attribute = this.findAttribute(s);
        if (attribute == null) {
            this.attributes.addElement(new XMLAttribute(s, s, null, value, "CDATA"));
        }
        else {
            attribute.setValue(value);
        }
    }
    
    public void setAttribute(final String s, final String s2, final String value) {
        final String substring = s.substring(s.indexOf(58) + 1);
        final XMLAttribute attribute = this.findAttribute(substring, s2);
        if (attribute == null) {
            this.attributes.addElement(new XMLAttribute(s, substring, s2, value, "CDATA"));
        }
        else {
            attribute.setValue(value);
        }
    }
    
    public void removeAttribute(final String s) {
        for (int i = 0; i < this.attributes.size(); ++i) {
            if (this.attributes.elementAt(i).getFullName().equals(s)) {
                this.attributes.removeElementAt(i);
                return;
            }
        }
    }
    
    public void removeAttribute(final String s, final String s2) {
        for (int i = 0; i < this.attributes.size(); ++i) {
            final XMLAttribute xmlAttribute = this.attributes.elementAt(i);
            final boolean equals = xmlAttribute.getName().equals(s);
            boolean b;
            if (s2 == null) {
                b = (equals & xmlAttribute.getNamespace() == null);
            }
            else {
                b = (equals & xmlAttribute.getNamespace().equals(s2));
            }
            if (b) {
                this.attributes.removeElementAt(i);
                return;
            }
        }
    }
    
    public Enumeration<String> enumerateAttributeNames() {
        final Vector<String> vector = new Vector<String>();
        final Enumeration<XMLAttribute> elements = this.attributes.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement().getFullName());
        }
        return vector.elements();
    }
    
    public boolean hasAttribute(final String s) {
        return this.findAttribute(s) != null;
    }
    
    public boolean hasAttribute(final String s, final String s2) {
        return this.findAttribute(s, s2) != null;
    }
    
    public Properties getAttributes() {
        final Properties properties = new Properties();
        final Enumeration<XMLAttribute> elements = this.attributes.elements();
        while (elements.hasMoreElements()) {
            final XMLAttribute xmlAttribute = elements.nextElement();
            ((Hashtable<String, String>)properties).put(xmlAttribute.getFullName(), xmlAttribute.getValue());
        }
        return properties;
    }
    
    public Properties getAttributesInNamespace(final String s) {
        final Properties properties = new Properties();
        final Enumeration<XMLAttribute> elements = this.attributes.elements();
        while (elements.hasMoreElements()) {
            final XMLAttribute xmlAttribute = elements.nextElement();
            if (s == null) {
                if (xmlAttribute.getNamespace() != null) {
                    continue;
                }
                ((Hashtable<String, String>)properties).put(xmlAttribute.getName(), xmlAttribute.getValue());
            }
            else {
                if (!s.equals(xmlAttribute.getNamespace())) {
                    continue;
                }
                ((Hashtable<String, String>)properties).put(xmlAttribute.getName(), xmlAttribute.getValue());
            }
        }
        return properties;
    }
    
    public String getSystemID() {
        return this.systemID;
    }
    
    public int getLineNr() {
        return this.lineNr;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public boolean equals(final Object o) {
        try {
            return this.equalsXMLElement((XMLElement)o);
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public boolean equalsXMLElement(final XMLElement xmlElement) {
        if (!this.name.equals(xmlElement.getLocalName())) {
            return false;
        }
        if (this.attributes.size() != xmlElement.getAttributeCount()) {
            return false;
        }
        final Enumeration<XMLAttribute> elements = this.attributes.elements();
        while (elements.hasMoreElements()) {
            final XMLAttribute xmlAttribute = elements.nextElement();
            if (!xmlElement.hasAttribute(xmlAttribute.getName(), xmlAttribute.getNamespace())) {
                return false;
            }
            if (!xmlAttribute.getValue().equals(xmlElement.getAttribute(xmlAttribute.getName(), xmlAttribute.getNamespace(), null))) {
                return false;
            }
            if (!xmlAttribute.getType().equals(xmlElement.getAttributeType(xmlAttribute.getName(), xmlAttribute.getNamespace()))) {
                return false;
            }
        }
        if (this.children.size() != xmlElement.getChildCount()) {
            return false;
        }
        for (int i = 0; i < this.children.size(); ++i) {
            if (!this.getChildAtIndex(i).equalsXMLElement(xmlElement.getChildAtIndex(i))) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        return this.toString(true);
    }
    
    public String toString(final boolean b) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(byteArrayOutputStream));
        try {
            if (b) {
                xmlWriter.write(this, true, 2, true);
            }
            else {
                xmlWriter.write(this, false, 0, true);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }
}
