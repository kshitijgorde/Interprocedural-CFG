// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.XMLAttributesImpl;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;

public class XMLNSDocumentScannerImpl extends XMLDocumentScannerImpl
{
    protected boolean fBindNamespaces;
    protected boolean fPerformValidation;
    protected String[] fUri;
    protected String[] fLocalpart;
    protected int fLength;
    private XMLDTDValidatorFilter fDTDValidator;
    
    public XMLNSDocumentScannerImpl() {
        this.fUri = new String[4];
        this.fLocalpart = new String[4];
        this.fLength = 0;
    }
    
    public void setDTDValidator(final XMLDTDValidatorFilter dtd) {
        this.fDTDValidator = dtd;
    }
    
    protected boolean scanStartElement() throws IOException, XNIException {
        String rawname = null;
        super.fEntityScanner.scanQName(super.fElementQName);
        if (this.fBindNamespaces) {
            super.fNamespaceContext.pushContext();
            rawname = super.fElementQName.rawname;
            if (super.fScannerState == 6 && this.fPerformValidation) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", new Object[] { rawname }, (short)1);
                if (super.fDoctypeName == null || !super.fDoctypeName.equals(rawname)) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[] { super.fDoctypeName, rawname }, (short)1);
                }
            }
        }
        super.fCurrentElement = super.fElementStack.pushElement(super.fElementQName);
        boolean empty = false;
        super.fAttributes.removeAllAttributes();
        while (true) {
            final boolean sawSpace = super.fEntityScanner.skipSpaces();
            final int c = super.fEntityScanner.peekChar();
            if (c == 62) {
                super.fEntityScanner.scanChar();
                break;
            }
            if (c == 47) {
                super.fEntityScanner.scanChar();
                if (!super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("ElementUnterminated", new Object[] { rawname });
                }
                empty = true;
                break;
            }
            if (!XMLChar.isNameStart(c) || !sawSpace) {
                this.reportFatalError("ElementUnterminated", new Object[] { rawname });
            }
            this.scanAttribute(super.fAttributes);
        }
        if (this.fBindNamespaces) {
            if (super.fElementQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { super.fElementQName.rawname }, (short)2);
            }
            final String prefix = (super.fElementQName.prefix != null) ? super.fElementQName.prefix : XMLSymbols.EMPTY_STRING;
            super.fElementQName.uri = super.fNamespaceContext.getURI(prefix);
            super.fCurrentElement.uri = super.fElementQName.uri;
            if (super.fElementQName.prefix == null && super.fElementQName.uri != null) {
                super.fElementQName.prefix = XMLSymbols.EMPTY_STRING;
            }
            if (super.fElementQName.prefix != null && super.fElementQName.uri == null) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { super.fElementQName.prefix, super.fElementQName.rawname }, (short)2);
            }
            final int length = super.fAttributes.getLength();
            this.fLength = 0;
            for (int i = 0; i < length; ++i) {
                super.fAttributes.getName(i, super.fAttributeQName);
                final String aprefix = (super.fAttributeQName.prefix != null) ? super.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
                final String uri = super.fNamespaceContext.getURI(aprefix);
                if (super.fAttributeQName.uri != null && super.fAttributeQName.uri == uri) {
                    this.checkDuplicates(super.fAttributeQName, super.fAttributes);
                }
                else if (aprefix != XMLSymbols.EMPTY_STRING) {
                    if ((super.fAttributeQName.uri = uri) == null) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { aprefix, super.fAttributeQName.rawname }, (short)2);
                    }
                    super.fAttributes.setURI(i, uri);
                    this.checkDuplicates(super.fAttributeQName, super.fAttributes);
                }
            }
        }
        if (super.fDocumentHandler != null) {
            if (empty) {
                --super.fMarkupDepth;
                if (super.fMarkupDepth < super.fEntityStack[super.fEntityDepth - 1]) {
                    this.reportFatalError("ElementEntityMismatch", new Object[] { super.fCurrentElement.rawname });
                }
                super.fDocumentHandler.emptyElement(super.fElementQName, super.fAttributes, null);
                if (this.fBindNamespaces) {
                    final int count = super.fNamespaceContext.getDeclaredPrefixCount();
                    for (int j = count - 1; j >= 0; --j) {
                        final String prefix2 = super.fNamespaceContext.getDeclaredPrefixAt(j);
                        super.fDocumentHandler.endPrefixMapping(prefix2, null);
                    }
                    super.fNamespaceContext.popContext();
                }
                super.fElementStack.popElement(super.fElementQName);
            }
            else {
                super.fDocumentHandler.startElement(super.fElementQName, super.fAttributes, null);
            }
        }
        return empty;
    }
    
    private final void checkDuplicates(final QName qname, final XMLAttributesImpl attributes) {
        for (int i = 0; i < this.fLength; ++i) {
            if (qname.uri == this.fUri[i] && this.fLocalpart[i].equals(qname.localpart)) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { super.fElementQName.rawname, qname.uri, qname.uri }, (short)2);
            }
        }
        final int index = this.fLength;
        if (this.fLength++ == this.fUri.length) {
            final String[] uris = new String[this.fUri.length + 4];
            final String[] lps = new String[this.fUri.length + 4];
            System.arraycopy(this.fUri, 0, uris, 0, this.fUri.length);
            System.arraycopy(this.fLocalpart, 0, lps, 0, this.fLocalpart.length);
            this.fUri = uris;
            this.fLocalpart = lps;
        }
        this.fUri[index] = qname.uri;
        this.fLocalpart[index] = qname.localpart;
    }
    
    protected void scanAttribute(final XMLAttributesImpl attributes) throws IOException, XNIException {
        super.fEntityScanner.scanQName(super.fAttributeQName);
        super.fEntityScanner.skipSpaces();
        if (!super.fEntityScanner.skipChar(61)) {
            this.reportFatalError("EqRequiredInAttribute", new Object[] { super.fAttributeQName.rawname });
        }
        super.fEntityScanner.skipSpaces();
        final int oldLen = attributes.getLength();
        attributes.addAttribute(super.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        if (oldLen == attributes.getLength()) {
            this.reportFatalError("AttributeNotUnique", new Object[] { super.fCurrentElement.rawname, super.fAttributeQName.rawname });
        }
        final boolean isVC = super.fHasExternalDTD && !super.fStandalone;
        this.scanAttributeValue(super.fTempString, super.fTempString2, super.fAttributeQName.rawname, attributes, oldLen, isVC);
        final String value = super.fTempString.toString();
        attributes.setValue(oldLen, value);
        attributes.setNonNormalizedValue(oldLen, super.fTempString2.toString());
        attributes.setSpecified(oldLen, true);
        if (this.fBindNamespaces) {
            final String localpart = super.fAttributeQName.localpart;
            String prefix = (super.fAttributeQName.prefix != null) ? super.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            if (prefix == XMLSymbols.PREFIX_XMLNS || (prefix == XMLSymbols.EMPTY_STRING && localpart == XMLSymbols.PREFIX_XMLNS)) {
                final String uri = super.fSymbolTable.addSymbol(value);
                if (prefix == XMLSymbols.PREFIX_XMLNS && localpart == XMLSymbols.PREFIX_XMLNS) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { super.fAttributeQName }, (short)2);
                }
                if (uri == NamespaceContext.XMLNS_URI) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { super.fAttributeQName }, (short)2);
                }
                if (localpart == XMLSymbols.PREFIX_XML) {
                    if (uri != NamespaceContext.XML_URI) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { super.fAttributeQName }, (short)2);
                    }
                }
                else if (uri == NamespaceContext.XML_URI) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { super.fAttributeQName }, (short)2);
                }
                prefix = ((localpart != XMLSymbols.PREFIX_XMLNS) ? localpart : XMLSymbols.EMPTY_STRING);
                if (uri == XMLSymbols.EMPTY_STRING && localpart != XMLSymbols.PREFIX_XMLNS) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "EmptyPrefixedAttName", new Object[] { super.fAttributeQName }, (short)2);
                }
                super.fNamespaceContext.declarePrefix(prefix, (uri.length() != 0) ? uri : null);
                attributes.setURI(oldLen, super.fNamespaceContext.getURI(XMLSymbols.PREFIX_XMLNS));
                if (super.fDocumentHandler != null) {
                    super.fDocumentHandler.startPrefixMapping(prefix, uri, null);
                }
            }
            else if (super.fAttributeQName.prefix != null) {
                attributes.setURI(oldLen, super.fNamespaceContext.getURI(super.fAttributeQName.prefix));
            }
        }
    }
    
    protected int scanEndElement() throws IOException, XNIException {
        super.fElementStack.popElement(super.fElementQName);
        if (!super.fEntityScanner.skipString(super.fElementQName.rawname)) {
            this.reportFatalError("ETagRequired", new Object[] { super.fElementQName.rawname });
        }
        super.fEntityScanner.skipSpaces();
        if (!super.fEntityScanner.skipChar(62)) {
            this.reportFatalError("ETagUnterminated", new Object[] { super.fElementQName.rawname });
        }
        --super.fMarkupDepth;
        --super.fMarkupDepth;
        if (super.fMarkupDepth < super.fEntityStack[super.fEntityDepth - 1]) {
            this.reportFatalError("ElementEntityMismatch", new Object[] { super.fCurrentElement.rawname });
        }
        if (super.fDocumentHandler != null) {
            super.fDocumentHandler.endElement(super.fElementQName, null);
            if (this.fBindNamespaces) {
                final int count = super.fNamespaceContext.getDeclaredPrefixCount();
                for (int i = count - 1; i >= 0; --i) {
                    final String prefix = super.fNamespaceContext.getDeclaredPrefixAt(i);
                    super.fDocumentHandler.endPrefixMapping(prefix, null);
                }
                super.fNamespaceContext.popContext();
            }
        }
        return super.fMarkupDepth;
    }
    
    public void reset(final XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.fPerformValidation = false;
        this.fBindNamespaces = false;
    }
    
    protected Dispatcher createContentDispatcher() {
        return new NSContentDispatcher();
    }
    
    protected final class NSContentDispatcher extends ContentDispatcher
    {
        protected boolean scanRootElementHook() throws IOException, XNIException {
            if (XMLNSDocumentScannerImpl.this.fDTDValidator == null) {
                XMLNSDocumentScannerImpl.this.fBindNamespaces = true;
            }
            else if (!XMLNSDocumentScannerImpl.this.fDTDValidator.hasGrammar()) {
                XMLNSDocumentScannerImpl.this.fBindNamespaces = true;
                XMLNSDocumentScannerImpl.this.fPerformValidation = XMLNSDocumentScannerImpl.this.fDTDValidator.validate();
                final XMLDocumentSource source = XMLNSDocumentScannerImpl.this.fDTDValidator.getDocumentSource();
                final XMLDocumentHandler handler = XMLNSDocumentScannerImpl.this.fDTDValidator.getDocumentHandler();
                source.setDocumentHandler(handler);
                if (handler != null) {
                    handler.setDocumentSource(source);
                }
                XMLNSDocumentScannerImpl.this.fDTDValidator.setDocumentSource(null);
                XMLNSDocumentScannerImpl.this.fDTDValidator.setDocumentHandler(null);
            }
            if (XMLNSDocumentScannerImpl.this.scanStartElement()) {
                XMLNSDocumentScannerImpl.this.setScannerState(12);
                XMLNSDocumentScannerImpl.this.setDispatcher(XMLNSDocumentScannerImpl.this.fTrailingMiscDispatcher);
                return true;
            }
            return false;
        }
    }
}
