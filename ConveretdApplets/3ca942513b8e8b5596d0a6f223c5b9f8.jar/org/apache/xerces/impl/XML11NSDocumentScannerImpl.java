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
import org.apache.xerces.xni.XNIException;
import java.io.IOException;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;

public class XML11NSDocumentScannerImpl extends XML11DocumentScannerImpl
{
    protected boolean fBindNamespaces;
    protected boolean fPerformValidation;
    private XMLDTDValidatorFilter fDTDValidator;
    private boolean fSawSpace;
    
    public void setDTDValidator(final XMLDTDValidatorFilter fdtdValidator) {
        this.fDTDValidator = fdtdValidator;
    }
    
    protected boolean scanStartElement() throws IOException, XNIException {
        super.fEntityScanner.scanQName(super.fElementQName);
        final String rawname = super.fElementQName.rawname;
        if (this.fBindNamespaces) {
            super.fNamespaceContext.pushContext();
            if (super.fScannerState == 6 && this.fPerformValidation) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", null, (short)1);
                if (super.fDoctypeName == null || !super.fDoctypeName.equals(rawname)) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[] { super.fDoctypeName, rawname }, (short)1);
                }
            }
        }
        super.fCurrentElement = super.fElementStack.pushElement(super.fElementQName);
        boolean b = false;
        super.fAttributes.removeAllAttributes();
        while (true) {
            final boolean skipSpaces = super.fEntityScanner.skipSpaces();
            final int peekChar = super.fEntityScanner.peekChar();
            if (peekChar == 62) {
                super.fEntityScanner.scanChar();
                break;
            }
            if (peekChar == 47) {
                super.fEntityScanner.scanChar();
                if (!super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("ElementUnterminated", new Object[] { rawname });
                }
                b = true;
                break;
            }
            if ((!this.isValidNameStartChar(peekChar) || !skipSpaces) && (!this.isValidNameStartHighSurrogate(peekChar) || !skipSpaces)) {
                this.reportFatalError("ElementUnterminated", new Object[] { rawname });
            }
            this.scanAttribute(super.fAttributes);
        }
        if (this.fBindNamespaces) {
            if (super.fElementQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { super.fElementQName.rawname }, (short)2);
            }
            super.fElementQName.uri = super.fNamespaceContext.getURI((super.fElementQName.prefix != null) ? super.fElementQName.prefix : XMLSymbols.EMPTY_STRING);
            super.fCurrentElement.uri = super.fElementQName.uri;
            if (super.fElementQName.prefix == null && super.fElementQName.uri != null) {
                super.fElementQName.prefix = XMLSymbols.EMPTY_STRING;
                super.fCurrentElement.prefix = XMLSymbols.EMPTY_STRING;
            }
            if (super.fElementQName.prefix != null && super.fElementQName.uri == null) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { super.fElementQName.prefix, super.fElementQName.rawname }, (short)2);
            }
            final int length = super.fAttributes.getLength();
            for (int i = 0; i < length; ++i) {
                super.fAttributes.getName(i, super.fAttributeQName);
                final String s = (super.fAttributeQName.prefix != null) ? super.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
                final String uri = super.fNamespaceContext.getURI(s);
                if (super.fAttributeQName.uri == null || super.fAttributeQName.uri != uri) {
                    if (s != XMLSymbols.EMPTY_STRING) {
                        if ((super.fAttributeQName.uri = uri) == null) {
                            super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { super.fElementQName.rawname, super.fAttributeQName.rawname, s }, (short)2);
                        }
                        super.fAttributes.setURI(i, uri);
                    }
                }
            }
            if (length > 1) {
                final QName checkDuplicatesNS = super.fAttributes.checkDuplicatesNS();
                if (checkDuplicatesNS != null) {
                    if (checkDuplicatesNS.uri != null) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { super.fElementQName.rawname, checkDuplicatesNS.localpart, checkDuplicatesNS.uri }, (short)2);
                    }
                    else {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[] { super.fElementQName.rawname, checkDuplicatesNS.rawname }, (short)2);
                    }
                }
            }
        }
        if (super.fDocumentHandler != null) {
            if (b) {
                --super.fMarkupDepth;
                if (super.fMarkupDepth < super.fEntityStack[super.fEntityDepth - 1]) {
                    this.reportFatalError("ElementEntityMismatch", new Object[] { super.fCurrentElement.rawname });
                }
                super.fDocumentHandler.emptyElement(super.fElementQName, super.fAttributes, null);
                if (this.fBindNamespaces) {
                    super.fNamespaceContext.popContext();
                }
                super.fElementStack.popElement(super.fElementQName);
            }
            else {
                super.fDocumentHandler.startElement(super.fElementQName, super.fAttributes, null);
            }
        }
        return b;
    }
    
    protected void scanStartElementName() throws IOException, XNIException {
        super.fEntityScanner.scanQName(super.fElementQName);
        this.fSawSpace = super.fEntityScanner.skipSpaces();
    }
    
    protected boolean scanStartElementAfterName() throws IOException, XNIException {
        final String rawname = super.fElementQName.rawname;
        if (this.fBindNamespaces) {
            super.fNamespaceContext.pushContext();
            if (super.fScannerState == 6 && this.fPerformValidation) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_GRAMMAR_NOT_FOUND", new Object[] { rawname }, (short)1);
                if (super.fDoctypeName == null || !super.fDoctypeName.equals(rawname)) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RootElementTypeMustMatchDoctypedecl", new Object[] { super.fDoctypeName, rawname }, (short)1);
                }
            }
        }
        super.fCurrentElement = super.fElementStack.pushElement(super.fElementQName);
        boolean b = false;
        super.fAttributes.removeAllAttributes();
        while (true) {
            final int peekChar = super.fEntityScanner.peekChar();
            if (peekChar == 62) {
                super.fEntityScanner.scanChar();
                break;
            }
            if (peekChar == 47) {
                super.fEntityScanner.scanChar();
                if (!super.fEntityScanner.skipChar(62)) {
                    this.reportFatalError("ElementUnterminated", new Object[] { rawname });
                }
                b = true;
                break;
            }
            if ((!this.isValidNameStartChar(peekChar) || !this.fSawSpace) && (!this.isValidNameStartHighSurrogate(peekChar) || !this.fSawSpace)) {
                this.reportFatalError("ElementUnterminated", new Object[] { rawname });
            }
            this.scanAttribute(super.fAttributes);
            this.fSawSpace = super.fEntityScanner.skipSpaces();
        }
        if (this.fBindNamespaces) {
            if (super.fElementQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { super.fElementQName.rawname }, (short)2);
            }
            super.fElementQName.uri = super.fNamespaceContext.getURI((super.fElementQName.prefix != null) ? super.fElementQName.prefix : XMLSymbols.EMPTY_STRING);
            super.fCurrentElement.uri = super.fElementQName.uri;
            if (super.fElementQName.prefix == null && super.fElementQName.uri != null) {
                super.fElementQName.prefix = XMLSymbols.EMPTY_STRING;
                super.fCurrentElement.prefix = XMLSymbols.EMPTY_STRING;
            }
            if (super.fElementQName.prefix != null && super.fElementQName.uri == null) {
                super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { super.fElementQName.prefix, super.fElementQName.rawname }, (short)2);
            }
            final int length = super.fAttributes.getLength();
            for (int i = 0; i < length; ++i) {
                super.fAttributes.getName(i, super.fAttributeQName);
                final String s = (super.fAttributeQName.prefix != null) ? super.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
                final String uri = super.fNamespaceContext.getURI(s);
                if (super.fAttributeQName.uri == null || super.fAttributeQName.uri != uri) {
                    if (s != XMLSymbols.EMPTY_STRING) {
                        if ((super.fAttributeQName.uri = uri) == null) {
                            super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { super.fElementQName.rawname, super.fAttributeQName.rawname, s }, (short)2);
                        }
                        super.fAttributes.setURI(i, uri);
                    }
                }
            }
            if (length > 1) {
                final QName checkDuplicatesNS = super.fAttributes.checkDuplicatesNS();
                if (checkDuplicatesNS != null) {
                    if (checkDuplicatesNS.uri != null) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { super.fElementQName.rawname, checkDuplicatesNS.localpart, checkDuplicatesNS.uri }, (short)2);
                    }
                    else {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNotUnique", new Object[] { super.fElementQName.rawname, checkDuplicatesNS.rawname }, (short)2);
                    }
                }
            }
        }
        if (super.fDocumentHandler != null) {
            if (b) {
                --super.fMarkupDepth;
                if (super.fMarkupDepth < super.fEntityStack[super.fEntityDepth - 1]) {
                    this.reportFatalError("ElementEntityMismatch", new Object[] { super.fCurrentElement.rawname });
                }
                super.fDocumentHandler.emptyElement(super.fElementQName, super.fAttributes, null);
                if (this.fBindNamespaces) {
                    super.fNamespaceContext.popContext();
                }
                super.fElementStack.popElement(super.fElementQName);
            }
            else {
                super.fDocumentHandler.startElement(super.fElementQName, super.fAttributes, null);
            }
        }
        return b;
    }
    
    protected void scanAttribute(final XMLAttributesImpl xmlAttributesImpl) throws IOException, XNIException {
        super.fEntityScanner.scanQName(super.fAttributeQName);
        super.fEntityScanner.skipSpaces();
        if (!super.fEntityScanner.skipChar(61)) {
            this.reportFatalError("EqRequiredInAttribute", new Object[] { super.fCurrentElement.rawname, super.fAttributeQName.rawname });
        }
        super.fEntityScanner.skipSpaces();
        int n;
        if (this.fBindNamespaces) {
            n = xmlAttributesImpl.getLength();
            xmlAttributesImpl.addAttributeNS(super.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        }
        else {
            final int length = xmlAttributesImpl.getLength();
            n = xmlAttributesImpl.addAttribute(super.fAttributeQName, XMLSymbols.fCDATASymbol, null);
            if (length == xmlAttributesImpl.getLength()) {
                this.reportFatalError("AttributeNotUnique", new Object[] { super.fCurrentElement.rawname, super.fAttributeQName.rawname });
            }
        }
        final boolean scanAttributeValue = this.scanAttributeValue(super.fTempString, super.fTempString2, super.fAttributeQName.rawname, super.fHasExternalDTD && !super.fStandalone, super.fCurrentElement.rawname);
        final String string = super.fTempString.toString();
        xmlAttributesImpl.setValue(n, string);
        if (!scanAttributeValue) {
            xmlAttributesImpl.setNonNormalizedValue(n, super.fTempString2.toString());
        }
        xmlAttributesImpl.setSpecified(n, true);
        if (this.fBindNamespaces) {
            final String localpart = super.fAttributeQName.localpart;
            final String s = (super.fAttributeQName.prefix != null) ? super.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            if (s == XMLSymbols.PREFIX_XMLNS || (s == XMLSymbols.EMPTY_STRING && localpart == XMLSymbols.PREFIX_XMLNS)) {
                final String addSymbol = super.fSymbolTable.addSymbol(string);
                if (s == XMLSymbols.PREFIX_XMLNS && localpart == XMLSymbols.PREFIX_XMLNS) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", null, (short)2);
                }
                if (addSymbol == NamespaceContext.XMLNS_URI) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", null, (short)2);
                }
                if (localpart == XMLSymbols.PREFIX_XML) {
                    if (addSymbol != NamespaceContext.XML_URI) {
                        super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", null, (short)2);
                    }
                }
                else if (addSymbol == NamespaceContext.XML_URI) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", null, (short)2);
                }
                super.fNamespaceContext.declarePrefix((localpart != XMLSymbols.PREFIX_XMLNS) ? localpart : XMLSymbols.EMPTY_STRING, (addSymbol.length() != 0) ? addSymbol : null);
                xmlAttributesImpl.setURI(n, super.fNamespaceContext.getURI(XMLSymbols.PREFIX_XMLNS));
            }
            else if (super.fAttributeQName.prefix != null) {
                xmlAttributesImpl.setURI(n, super.fNamespaceContext.getURI(super.fAttributeQName.prefix));
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
                super.fNamespaceContext.popContext();
            }
        }
        return super.fMarkupDepth;
    }
    
    public void reset(final XMLComponentManager xmlComponentManager) throws XMLConfigurationException {
        super.reset(xmlComponentManager);
        this.fPerformValidation = false;
        this.fBindNamespaces = false;
    }
    
    protected Dispatcher createContentDispatcher() {
        return new NS11ContentDispatcher();
    }
    
    protected final class NS11ContentDispatcher extends ContentDispatcher
    {
        protected boolean scanRootElementHook() throws IOException, XNIException {
            if (XML11NSDocumentScannerImpl.this.fExternalSubsetResolver != null && !XML11NSDocumentScannerImpl.this.fSeenDoctypeDecl && !XML11NSDocumentScannerImpl.this.fDisallowDoctype && (XML11NSDocumentScannerImpl.this.fValidation || XML11NSDocumentScannerImpl.this.fLoadExternalDTD)) {
                XML11NSDocumentScannerImpl.this.scanStartElementName();
                this.resolveExternalSubsetAndRead();
                this.reconfigurePipeline();
                if (XML11NSDocumentScannerImpl.this.scanStartElementAfterName()) {
                    XML11NSDocumentScannerImpl.this.setScannerState(12);
                    XML11NSDocumentScannerImpl.this.setDispatcher(XML11NSDocumentScannerImpl.this.fTrailingMiscDispatcher);
                    return true;
                }
            }
            else {
                this.reconfigurePipeline();
                if (XML11NSDocumentScannerImpl.this.scanStartElement()) {
                    XML11NSDocumentScannerImpl.this.setScannerState(12);
                    XML11NSDocumentScannerImpl.this.setDispatcher(XML11NSDocumentScannerImpl.this.fTrailingMiscDispatcher);
                    return true;
                }
            }
            return false;
        }
        
        private void reconfigurePipeline() {
            if (XML11NSDocumentScannerImpl.this.fDTDValidator == null) {
                XML11NSDocumentScannerImpl.this.fBindNamespaces = true;
            }
            else if (!XML11NSDocumentScannerImpl.this.fDTDValidator.hasGrammar()) {
                XML11NSDocumentScannerImpl.this.fBindNamespaces = true;
                XML11NSDocumentScannerImpl.this.fPerformValidation = XML11NSDocumentScannerImpl.this.fDTDValidator.validate();
                final XMLDocumentSource documentSource = XML11NSDocumentScannerImpl.this.fDTDValidator.getDocumentSource();
                final XMLDocumentHandler documentHandler = XML11NSDocumentScannerImpl.this.fDTDValidator.getDocumentHandler();
                documentSource.setDocumentHandler(documentHandler);
                if (documentHandler != null) {
                    documentHandler.setDocumentSource(documentSource);
                }
                XML11NSDocumentScannerImpl.this.fDTDValidator.setDocumentSource(null);
                XML11NSDocumentScannerImpl.this.fDTDValidator.setDocumentHandler(null);
            }
        }
    }
}
