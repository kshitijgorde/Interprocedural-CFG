// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.framework.XMLContentSpec;
import org.apache.xerces.utils.QName;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.Parser;
import org.apache.xerces.framework.XMLDocumentHandler;
import org.apache.xerces.framework.XMLParser;

public class SAXParser extends XMLParser implements XMLDocumentHandler, DTDHandler, Parser, XMLReader
{
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final boolean DEBUG_CALLBACKS = false;
    private DocumentHandler fDocumentHandler;
    private org.xml.sax.DTDHandler fDTDHandler;
    private ContentHandler fContentHandler;
    private DeclHandler fDeclHandler;
    private LexicalHandler fLexicalHandler;
    private boolean fNamespacePrefixes;
    private transient AttributesImpl fAttributes;
    
    public SAXParser() {
        this.fNamespacePrefixes = false;
        this.fAttributes = new AttributesImpl();
        this.initHandlers(true, this, this);
    }
    
    protected SAXParser(final StringPool stringPool) {
        super(stringPool);
        this.fNamespacePrefixes = false;
        this.fAttributes = new AttributesImpl();
        this.initHandlers(true, this, this);
    }
    
    public String[] getFeaturesRecognized() {
        final String[] featuresRecognized = super.getFeaturesRecognized();
        final String[] recognized_FEATURES = SAXParser.RECOGNIZED_FEATURES;
        final int length = recognized_FEATURES.length;
        if (length == 0) {
            return featuresRecognized;
        }
        final int length2 = featuresRecognized.length;
        if (length2 == 0) {
            return recognized_FEATURES;
        }
        final String[] array = new String[length2 + length];
        System.arraycopy(featuresRecognized, 0, array, 0, length2);
        System.arraycopy(recognized_FEATURES, 0, array, length2, length);
        return array;
    }
    
    public String[] getPropertiesRecognized() {
        final String[] propertiesRecognized = super.getPropertiesRecognized();
        final String[] recognized_PROPERTIES = SAXParser.RECOGNIZED_PROPERTIES;
        final int length = recognized_PROPERTIES.length;
        if (length == 0) {
            return propertiesRecognized;
        }
        final int length2 = propertiesRecognized.length;
        if (length2 == 0) {
            return recognized_PROPERTIES;
        }
        final String[] array = new String[length2 + length];
        System.arraycopy(propertiesRecognized, 0, array, 0, length2);
        System.arraycopy(recognized_PROPERTIES, 0, array, length2, length);
        return array;
    }
    
    protected void setDeclHandler(final DeclHandler fDeclHandler) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (super.fParseInProgress) {
            throw new SAXNotSupportedException("PAR011 Feature: http://xml.org/sax/properties/declaration-handler is not supported during parse.\nhttp://xml.org/sax/properties/declaration-handler");
        }
        this.fDeclHandler = fDeclHandler;
    }
    
    protected DeclHandler getDeclHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fDeclHandler;
    }
    
    protected void setLexicalHandler(final LexicalHandler fLexicalHandler) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (super.fParseInProgress) {
            throw new SAXNotSupportedException("PAR011 Feature: http://xml.org/sax/properties/lexical-handler is not supported during parse.\nhttp://xml.org/sax/properties/lexical-handler");
        }
        this.fLexicalHandler = fLexicalHandler;
    }
    
    protected LexicalHandler getLexicalHandler() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fLexicalHandler;
    }
    
    public void setDocumentHandler(final DocumentHandler fDocumentHandler) {
        this.fDocumentHandler = fDocumentHandler;
    }
    
    public void setDTDHandler(final org.xml.sax.DTDHandler fdtdHandler) {
        this.fDTDHandler = fdtdHandler;
    }
    
    public org.xml.sax.DTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }
    
    protected void setNamespacePrefixes(final boolean fNamespacePrefixes) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (super.fParseInProgress) {
            throw new SAXNotSupportedException("PAR004 Cannot setFeature(http://xml.org/sax/features/namespace-prefixes): parse is in progress.\nhttp://xml.org/sax/features/namespace-prefixes");
        }
        this.fNamespacePrefixes = fNamespacePrefixes;
    }
    
    protected boolean getNamespacePrefixes() throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.fNamespacePrefixes;
    }
    
    public void setFeature(final String s, final boolean namespacePrefixes) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/features/")) {
            final String substring = s.substring("http://xml.org/sax/features/".length());
            if (substring.equals("namespace-prefixes")) {
                this.setNamespacePrefixes(namespacePrefixes);
                return;
            }
            if (substring.equals("string-interning")) {
                if (namespacePrefixes) {
                    throw new SAXNotSupportedException("PAR018 " + namespacePrefixes + " state for feature \"" + s + "\" is not supported.\n" + namespacePrefixes + '\t' + s);
                }
                return;
            }
        }
        super.setFeature(s, namespacePrefixes);
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/features/")) {
            final String substring = s.substring("http://xml.org/sax/features/".length());
            if (substring.equals("namespace-prefixes")) {
                return this.getNamespacePrefixes();
            }
            if (substring.equals("string-interning")) {
                return false;
            }
        }
        return super.getFeature(s);
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/properties/")) {
            final String substring = s.substring("http://xml.org/sax/properties/".length());
            if (substring.equals("lexical-handler")) {
                try {
                    this.setLexicalHandler((LexicalHandler)o);
                }
                catch (ClassCastException ex) {
                    throw new SAXNotSupportedException("PAR012 For propertyID \"" + s + "\", the value \"" + o + "\" cannot be cast to LexicalHandler." + '\n' + s + '\t' + o + "\tLexicalHandler");
                }
                return;
            }
            if (substring.equals("declaration-handler")) {
                try {
                    this.setDeclHandler((DeclHandler)o);
                }
                catch (ClassCastException ex2) {
                    throw new SAXNotSupportedException("PAR012 For propertyID \"" + s + "\", the value \"" + o + "\" cannot be cast to DeclHandler." + '\n' + s + '\t' + o + "\tDeclHandler");
                }
                return;
            }
            if (substring.equals("dom-node")) {
                throw new SAXNotSupportedException("PAR013 Property \"" + s + "\" is read only." + '\n' + s);
            }
        }
        super.setProperty(s, o);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.startsWith("http://xml.org/sax/properties/")) {
            final String substring = s.substring("http://xml.org/sax/properties/".length());
            if (substring.equals("lexical-handler")) {
                return this.getLexicalHandler();
            }
            if (substring.equals("declaration-handler")) {
                return this.getDeclHandler();
            }
            if (substring.equals("dom-node")) {
                throw new SAXNotSupportedException("PAR014 Cannot getProperty(\"" + s + "\". No DOM Tree exists.\n" + s);
            }
        }
        return super.getProperty(s);
    }
    
    public void setContentHandler(final ContentHandler fContentHandler) {
        if (fContentHandler == null) {
            throw new NullPointerException();
        }
        this.fContentHandler = fContentHandler;
    }
    
    public ContentHandler getContentHandler() {
        return this.fContentHandler;
    }
    
    public void startDTD(final QName qName, final int n, final int n2) throws Exception {
        if (this.fLexicalHandler != null) {
            final String string = super.fStringPool.toString(qName.rawname);
            final String string2 = super.fStringPool.toString(n);
            final String string3 = super.fStringPool.toString(n2);
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.startDTD(string, string2, string3);
            }
        }
    }
    
    public void endDTD() throws Exception {
        if (this.fLexicalHandler != null) {
            this.fLexicalHandler.endDTD();
        }
    }
    
    public void elementDecl(final QName qName, final int n, final int n2, final XMLContentSpec.Provider provider) throws Exception {
        if (this.fDeclHandler != null) {
            final String string = super.fStringPool.toString(qName.rawname);
            String string2;
            if (n == 1) {
                string2 = "ANY";
            }
            else if (n == 0) {
                string2 = "EMPTY";
            }
            else {
                string2 = XMLContentSpec.toString(provider, super.fStringPool, n2);
            }
            if (this.fDeclHandler != null) {
                this.fDeclHandler.elementDecl(string, string2);
            }
        }
    }
    
    public void attlistDecl(final QName qName, final QName qName2, final int n, final boolean b, final String s, final int n2, final int n3) throws Exception {
        if (this.fDeclHandler != null) {
            final String string = super.fStringPool.toString(qName.rawname);
            final String string2 = super.fStringPool.toString(qName2.rawname);
            String string3 = s;
            if (n != 2) {
                switch (n) {
                    case 0: {
                        string3 = "CDATA";
                        break;
                    }
                    case 1: {
                        string3 = (b ? "ENTITIES" : "ENTITY");
                        break;
                    }
                    case 3: {
                        string3 = "ID";
                        break;
                    }
                    case 4: {
                        string3 = (b ? "IDREFS" : "IDREF");
                        break;
                    }
                    case 5: {
                        string3 = (b ? "NMTOKENS" : "NMTOKEN");
                        break;
                    }
                    case 6: {
                        string3 = "NOTATION " + s;
                        break;
                    }
                }
            }
            String s2 = null;
            switch (n2) {
                case 1: {
                    s2 = "#FIXED";
                    break;
                }
                case 0: {
                    s2 = "#IMPLIED";
                    break;
                }
                case 2: {
                    s2 = "#REQUIRED";
                    break;
                }
            }
            final String string4 = super.fStringPool.toString(n3);
            if (this.fDeclHandler != null) {
                this.fDeclHandler.attributeDecl(string, string2, string3, s2, string4);
            }
        }
    }
    
    public void internalPEDecl(final int n, final int n2) throws Exception {
        if (this.fDeclHandler != null) {
            final String string = "%" + super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            if (this.fDeclHandler != null) {
                this.fDeclHandler.internalEntityDecl(string, string2);
            }
        }
    }
    
    public void externalPEDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDeclHandler != null) {
            final String string = "%" + super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            final String string3 = super.fStringPool.toString(n3);
            if (this.fDeclHandler != null) {
                this.fDeclHandler.externalEntityDecl(string, string2, string3);
            }
        }
    }
    
    public void internalEntityDecl(final int n, final int n2) throws Exception {
        if (this.fDeclHandler != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            if (this.fDeclHandler != null) {
                this.fDeclHandler.internalEntityDecl(string, string2);
            }
        }
    }
    
    public void externalEntityDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDeclHandler != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            final String string3 = super.fStringPool.toString(n3);
            if (this.fDeclHandler != null) {
                this.fDeclHandler.externalEntityDecl(string, string2, string3);
            }
        }
    }
    
    public void unparsedEntityDecl(final int n, final int n2, final int n3, final int n4) throws Exception {
        if (this.fDTDHandler != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            final String string3 = super.fStringPool.toString(n3);
            final String string4 = super.fStringPool.toString(n4);
            if (this.fDTDHandler != null) {
                this.fDTDHandler.unparsedEntityDecl(string, string2, string3, string4);
            }
        }
    }
    
    public void notationDecl(final int n, final int n2, final int n3) throws Exception {
        if (this.fDTDHandler != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            final String string3 = super.fStringPool.toString(n3);
            if (this.fDTDHandler != null) {
                this.fDTDHandler.notationDecl(string, string2, string3);
            }
        }
    }
    
    public void startDocument() throws Exception {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.setDocumentLocator(this.getLocator());
            this.fDocumentHandler.startDocument();
        }
        if (this.fContentHandler != null) {
            this.fContentHandler.setDocumentLocator(this.getLocator());
            this.fContentHandler.startDocument();
        }
    }
    
    public void endDocument() throws Exception {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endDocument();
        }
        if (this.fContentHandler != null) {
            this.fContentHandler.endDocument();
        }
    }
    
    public void xmlDecl(final int n, final int n2, final int n3) throws Exception {
        super.fStringPool.releaseString(n);
        super.fStringPool.releaseString(n2);
        super.fStringPool.releaseString(n3);
    }
    
    public void textDecl(final int n, final int n2) throws Exception {
        super.fStringPool.releaseString(n);
        super.fStringPool.releaseString(n2);
    }
    
    public void startNamespaceDeclScope(final int n, final int n2) throws Exception {
        if (this.fContentHandler != null) {
            final String string = super.fStringPool.toString(n);
            final String string2 = super.fStringPool.toString(n2);
            if (this.fContentHandler != null) {
                this.fContentHandler.startPrefixMapping(string, string2);
            }
        }
    }
    
    public void endNamespaceDeclScope(final int n) throws Exception {
        if (this.fContentHandler != null) {
            final String string = super.fStringPool.toString(n);
            if (this.fContentHandler != null) {
                this.fContentHandler.endPrefixMapping(string);
            }
        }
    }
    
    public void internalSubset(final int n) {
    }
    
    public void startElement(final QName qName, final XMLAttrList list, final int n) throws Exception {
        final String string = super.fStringPool.toString(qName.rawname);
        final AttributeList attributeList = list.getAttributeList(n);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(string, attributeList);
        }
        if (this.fContentHandler != null) {
            final boolean namespaces = this.getNamespaces();
            final int uri = qName.uri;
            final String s = (uri != 0 && namespaces) ? super.fStringPool.toString(uri) : "";
            final int localpart = qName.localpart;
            final String s2 = (localpart != -1 && namespaces) ? super.fStringPool.toString(localpart) : "";
            final String s3 = string;
            this.fAttributes.clear();
            for (int i = list.getFirstAttr(n); i != -1; i = list.getNextAttr(i)) {
                final int attrName = list.getAttrName(i);
                final int attrURI = list.getAttrURI(i);
                final String s4 = (attrURI != -1 && namespaces) ? super.fStringPool.toString(attrURI) : "";
                final int attrLocalpart = list.getAttrLocalpart(i);
                final String s5 = (attrLocalpart != -1 && namespaces) ? super.fStringPool.toString(attrLocalpart) : "";
                final String string2 = super.fStringPool.toString(attrName);
                final String string3 = super.fStringPool.toString(list.getAttType(i));
                final String string4 = super.fStringPool.toString(list.getAttValue(i));
                final int attrPrefix = list.getAttrPrefix(i);
                final boolean namespacePrefixes = this.getNamespacePrefixes();
                if (!namespaces || namespacePrefixes || (attrPrefix != super.fStringPool.addSymbol("xmlns") && attrLocalpart != super.fStringPool.addSymbol("xmlns"))) {
                    this.fAttributes.addAttribute(s4, s5, string2, string3, string4);
                }
            }
            this.fContentHandler.startElement(s, s2, s3, this.fAttributes);
        }
        list.releaseAttrList(n);
    }
    
    public void endElement(final QName qName) throws Exception {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endElement(super.fStringPool.toString(qName.rawname));
        }
        if (this.fContentHandler != null) {
            final boolean namespaces = this.getNamespaces();
            final int uri = qName.uri;
            final String s = (uri != 0 && namespaces) ? super.fStringPool.toString(uri) : "";
            final int localpart = qName.localpart;
            this.fContentHandler.endElement(s, (localpart != -1 && namespaces) ? super.fStringPool.toString(localpart) : "", super.fStringPool.toString(qName.rawname));
        }
    }
    
    public void startEntityReference(final int n, final int n2, final int n3) throws Exception {
        if (this.fLexicalHandler != null) {
            switch (n2) {
                case 0:
                case 1: {
                    if (this.fLexicalHandler != null) {
                        this.fLexicalHandler.startEntity("%" + super.fStringPool.toString(n));
                        break;
                    }
                    break;
                }
                case 2:
                case 3: {
                    if (this.fLexicalHandler != null) {
                        this.fLexicalHandler.startEntity(super.fStringPool.toString(n));
                        break;
                    }
                    break;
                }
                case 4: {
                    throw new RuntimeException("PAR015 startEntityReference(): ENTITYTYPE_UNPARSED");
                }
                case 6: {
                    if (this.fLexicalHandler != null) {
                        this.fLexicalHandler.startEntity("[dtd]");
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void endEntityReference(final int n, final int n2, final int n3) throws Exception {
        if (this.fLexicalHandler != null) {
            switch (n2) {
                case 0:
                case 1: {
                    if (this.fLexicalHandler != null) {
                        this.fLexicalHandler.endEntity("%" + super.fStringPool.toString(n));
                        break;
                    }
                    break;
                }
                case 2:
                case 3: {
                    if (this.fLexicalHandler != null) {
                        this.fLexicalHandler.endEntity(super.fStringPool.toString(n));
                        break;
                    }
                    break;
                }
                case 4: {
                    throw new RuntimeException("PAR016 endEntityReference(): ENTITYTYPE_UNPARSED");
                }
                case 6: {
                    if (this.fLexicalHandler != null) {
                        this.fLexicalHandler.endEntity("[dtd]");
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public void startCDATA() throws Exception {
        if (this.fLexicalHandler != null) {
            this.fLexicalHandler.startCDATA();
        }
    }
    
    public void endCDATA() throws Exception {
        if (this.fLexicalHandler != null) {
            this.fLexicalHandler.endCDATA();
        }
    }
    
    public void characters(final int n) throws Exception {
        throw new RuntimeException("PAR017 cannot happen 5\n5");
    }
    
    public void ignorableWhitespace(final int n) throws Exception {
        throw new RuntimeException("PAR017 cannot happen 6\n6");
    }
    
    public void processingInstruction(final int n, final int n2) throws Exception {
        if (this.fDocumentHandler == null && this.fContentHandler == null) {
            super.fStringPool.releaseString(n);
            super.fStringPool.releaseString(n2);
        }
        else {
            final String orphanString = super.fStringPool.orphanString(n);
            final String s = (n2 == -1) ? "" : super.fStringPool.orphanString(n2);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.processingInstruction(orphanString, s);
            }
            if (this.fContentHandler != null) {
                this.fContentHandler.processingInstruction(orphanString, s);
            }
        }
    }
    
    public void comment(final int n) throws Exception {
        if (this.fLexicalHandler == null) {
            super.fStringPool.releaseString(n);
        }
        else {
            final String orphanString = super.fStringPool.orphanString(n);
            if (this.fLexicalHandler != null) {
                this.fLexicalHandler.comment(orphanString.toCharArray(), 0, orphanString.length());
            }
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws Exception {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.characters(array, n, n2);
        }
        if (this.fContentHandler != null) {
            this.fContentHandler.characters(array, n, n2);
        }
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws Exception {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(array, n, n2);
        }
        if (this.fContentHandler != null) {
            this.fContentHandler.ignorableWhitespace(array, n, n2);
        }
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://xml.org/sax/features/namespace-prefixes", "http://xml.org/sax/features/string-interning" };
        RECOGNIZED_PROPERTIES = new String[] { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/properties/declaration-handler", "http://xml.org/sax/properties/dom-node" };
    }
}
