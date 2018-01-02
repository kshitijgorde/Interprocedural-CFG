// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.jdom.EntityRef;
import org.jdom.ProcessingInstruction;
import com.sun.java.util.collections.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.DocType;
import org.jdom.Comment;
import org.jdom.Text;
import org.jdom.CDATA;
import org.jdom.Attribute;
import org.jdom.Namespace;

public class DefaultJDOMFactory implements JDOMFactory
{
    private static final String CVS_ID = "@(#) $RCSfile: DefaultJDOMFactory.java,v $ $Revision: 1.6 $ $Date: 2002/03/12 07:57:06 $ $Name: jdom_1_0_b8 $";
    
    public Attribute attribute(final String name, final String value, final Namespace namespace) {
        return new Attribute(name, value, namespace);
    }
    
    public Attribute attribute(final String name, final String value, final int type, final Namespace namespace) {
        return new Attribute(name, value, type, namespace);
    }
    
    public Attribute attribute(final String name, final String value) {
        return new Attribute(name, value);
    }
    
    public Attribute attribute(final String name, final String value, final int type) {
        return new Attribute(name, value, type);
    }
    
    public CDATA cdata(final String text) {
        return new CDATA(text);
    }
    
    public Text text(final String text) {
        return new Text(text);
    }
    
    public Comment comment(final String text) {
        return new Comment(text);
    }
    
    public DocType docType(final String elementName, final String publicID, final String systemID) {
        return new DocType(elementName, publicID, systemID);
    }
    
    public DocType docType(final String elementName, final String systemID) {
        return new DocType(elementName, systemID);
    }
    
    public DocType docType(final String elementName) {
        return new DocType(elementName);
    }
    
    public Document document(final Element rootElement, final DocType docType) {
        return new Document(rootElement, docType);
    }
    
    public Document document(final Element rootElement) {
        return new Document(rootElement);
    }
    
    public Element element(final String name, final Namespace namespace) {
        return new Element(name, namespace);
    }
    
    public Element element(final String name) {
        return new Element(name);
    }
    
    public Element element(final String name, final String uri) {
        return new Element(name, uri);
    }
    
    public Element element(final String name, final String prefix, final String uri) {
        return new Element(name, prefix, uri);
    }
    
    public ProcessingInstruction processingInstruction(final String target, final Map data) {
        return new ProcessingInstruction(target, data);
    }
    
    public ProcessingInstruction processingInstruction(final String target, final String data) {
        return new ProcessingInstruction(target, data);
    }
    
    public EntityRef entityRef(final String name) {
        return new EntityRef(name);
    }
    
    public EntityRef entityRef(final String name, final String publicID, final String systemID) {
        return new EntityRef(name, publicID, systemID);
    }
}
