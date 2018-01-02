// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.dom;

import org.apache.xerces.dom.AttrNSImpl;
import org.w3c.dom.Attr;
import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.Text;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.apache.xerces.dom.CoreDocumentImpl;

public class DocumentImpl extends CoreDocumentImpl
{
    protected DOMNodePool fNodePool;
    
    public Element createElementNS(final String namespaceURI, final String qualifiedName, final String localpart, final int lineNum, final int columnNum) throws DOMException {
        if (this.fNodePool != null) {
            final ElementNSImpl element = this.fNodePool.getElementNode();
            element.setValues(this, namespaceURI, qualifiedName, localpart, lineNum, columnNum);
            return element;
        }
        return new ElementNSImpl(this, namespaceURI, qualifiedName, localpart, lineNum, columnNum);
    }
    
    public Text createTextNode(final String data) {
        if (this.fNodePool != null) {
            final TextImpl text = this.fNodePool.getTextNode();
            text.setValues(this, data);
            return text;
        }
        return new TextImpl(this, data);
    }
    
    public Attr createAttributeNS(final String namespaceURI, final String qualifiedName, final String localName) throws DOMException {
        if (this.fNodePool != null) {
            final AttrNSImpl attr = this.fNodePool.getAttrNode();
            attr.setValues(this, namespaceURI, qualifiedName, localName);
            return attr;
        }
        return new AttrNSImpl(this, namespaceURI, qualifiedName, localName);
    }
}
