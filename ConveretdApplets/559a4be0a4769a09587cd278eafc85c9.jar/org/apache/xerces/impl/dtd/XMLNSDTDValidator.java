// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dtd;

import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.QName;

public class XMLNSDTDValidator extends XMLDTDValidator
{
    private QName fAttributeQName;
    
    public XMLNSDTDValidator() {
        this.fAttributeQName = new QName();
    }
    
    protected final void startNamespaceScope(final QName element, final XMLAttributes attributes, final Augmentations augs) throws XNIException {
        super.fNamespaceSupport.pushContext();
        if (element.prefix == XMLSymbols.PREFIX_XMLNS) {
            super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { element.rawname }, (short)2);
        }
        final int length = attributes.getLength();
        for (int i = 0; i < length; ++i) {
            final String localpart = attributes.getLocalName(i);
            String prefix = attributes.getPrefix(i);
            if (prefix == XMLSymbols.PREFIX_XMLNS || (prefix == XMLSymbols.EMPTY_STRING && localpart == XMLSymbols.PREFIX_XMLNS)) {
                final String uri = super.fSymbolTable.addSymbol(attributes.getValue(i));
                if (prefix == XMLSymbols.PREFIX_XMLNS && localpart == XMLSymbols.PREFIX_XMLNS) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { attributes.getQName(i) }, (short)2);
                }
                if (uri == NamespaceContext.XMLNS_URI) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { attributes.getQName(i) }, (short)2);
                }
                if (localpart == XMLSymbols.PREFIX_XML) {
                    if (uri != NamespaceContext.XML_URI) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { attributes.getQName(i) }, (short)2);
                    }
                }
                else if (uri == NamespaceContext.XML_URI) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { attributes.getQName(i) }, (short)2);
                }
                prefix = ((localpart != XMLSymbols.PREFIX_XMLNS) ? localpart : XMLSymbols.EMPTY_STRING);
                if (uri == XMLSymbols.EMPTY_STRING && localpart != XMLSymbols.PREFIX_XMLNS) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "EmptyPrefixedAttName", new Object[] { attributes.getQName(i) }, (short)2);
                }
                else {
                    super.fNamespaceSupport.declarePrefix(prefix, (uri.length() != 0) ? uri : null);
                    if (super.fDocumentHandler != null) {
                        super.fDocumentHandler.startPrefixMapping(prefix, uri, augs);
                    }
                }
            }
        }
        final String prefix2 = (element.prefix != null) ? element.prefix : XMLSymbols.EMPTY_STRING;
        element.uri = super.fNamespaceSupport.getURI(prefix2);
        if (element.prefix == null && element.uri != null) {
            element.prefix = XMLSymbols.EMPTY_STRING;
        }
        if (element.prefix != null && element.uri == null) {
            super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { element.prefix, element.rawname }, (short)2);
        }
        for (int j = 0; j < length; ++j) {
            attributes.getName(j, this.fAttributeQName);
            final String aprefix = (this.fAttributeQName.prefix != null) ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            final String arawname = this.fAttributeQName.rawname;
            if (arawname == XMLSymbols.PREFIX_XMLNS) {
                this.fAttributeQName.uri = super.fNamespaceSupport.getURI(XMLSymbols.PREFIX_XMLNS);
                attributes.setName(j, this.fAttributeQName);
            }
            else if (aprefix != XMLSymbols.EMPTY_STRING) {
                this.fAttributeQName.uri = super.fNamespaceSupport.getURI(aprefix);
                if (this.fAttributeQName.uri == null) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { aprefix, arawname }, (short)2);
                }
                attributes.setName(j, this.fAttributeQName);
            }
        }
        for (int attrCount = attributes.getLength(), k = 0; k < attrCount - 1; ++k) {
            final String alocalpart = attributes.getLocalName(k);
            final String auri = attributes.getURI(k);
            for (int l = k + 1; l < attrCount; ++l) {
                final String blocalpart = attributes.getLocalName(l);
                final String buri = attributes.getURI(l);
                if (alocalpart == blocalpart && auri == buri) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { element.rawname, alocalpart, auri }, (short)2);
                }
            }
        }
    }
    
    protected void endNamespaceScope(final QName element, final Augmentations augs, final boolean isEmpty) throws XNIException {
        final String eprefix = (element.prefix != null) ? element.prefix : XMLSymbols.EMPTY_STRING;
        element.uri = super.fNamespaceSupport.getURI(eprefix);
        if (element.uri != null) {
            element.prefix = eprefix;
        }
        if (super.fDocumentHandler != null && !isEmpty) {
            super.fDocumentHandler.endElement(element, augs);
        }
        if (super.fDocumentHandler != null) {
            final int count = super.fNamespaceSupport.getDeclaredPrefixCount();
            for (int i = count - 1; i >= 0; --i) {
                final String prefix = super.fNamespaceSupport.getDeclaredPrefixAt(i);
                super.fDocumentHandler.endPrefixMapping(prefix, augs);
            }
        }
        super.fNamespaceSupport.popContext();
    }
}
