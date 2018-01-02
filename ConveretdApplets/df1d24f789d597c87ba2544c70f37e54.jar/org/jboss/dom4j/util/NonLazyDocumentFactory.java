// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.util;

import org.jboss.dom4j.Element;
import org.jboss.dom4j.QName;
import org.jboss.dom4j.DocumentFactory;

public class NonLazyDocumentFactory extends DocumentFactory
{
    protected static transient NonLazyDocumentFactory singleton;
    
    public static DocumentFactory getInstance() {
        return NonLazyDocumentFactory.singleton;
    }
    
    public Element createElement(final QName qname) {
        return new NonLazyElement(qname);
    }
    
    static {
        NonLazyDocumentFactory.singleton = new NonLazyDocumentFactory();
    }
}
