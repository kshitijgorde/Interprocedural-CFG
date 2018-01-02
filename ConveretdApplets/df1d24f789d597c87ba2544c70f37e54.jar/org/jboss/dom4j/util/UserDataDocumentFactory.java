// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.util;

import org.jboss.dom4j.Attribute;
import org.jboss.dom4j.Element;
import org.jboss.dom4j.QName;
import org.jboss.dom4j.DocumentFactory;

public class UserDataDocumentFactory extends DocumentFactory
{
    protected static transient UserDataDocumentFactory singleton;
    
    public static DocumentFactory getInstance() {
        return UserDataDocumentFactory.singleton;
    }
    
    public Element createElement(final QName qname) {
        return new UserDataElement(qname);
    }
    
    public Attribute createAttribute(final Element owner, final QName qname, final String value) {
        return new UserDataAttribute(qname, value);
    }
    
    static {
        UserDataDocumentFactory.singleton = new UserDataDocumentFactory();
    }
}
