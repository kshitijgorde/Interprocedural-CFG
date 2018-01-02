// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.xpath;

import javax.xml.namespace.QName;

public class XPathConstants
{
    public static final QName NUMBER;
    public static final QName STRING;
    public static final QName BOOLEAN;
    public static final QName NODESET;
    public static final QName NODE;
    public static final String DOM_OBJECT_MODEL = "http://java.sun.com/jaxp/xpath/dom";
    
    static {
        NUMBER = new QName("Double");
        STRING = new QName("String");
        BOOLEAN = new QName("Boolean");
        NODESET = new QName("NodeList");
        NODE = new QName("Node");
    }
}
