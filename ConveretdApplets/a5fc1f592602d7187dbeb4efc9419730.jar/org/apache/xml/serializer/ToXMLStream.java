// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import org.apache.xml.res.XMLMessages;
import java.io.Writer;
import java.io.IOException;
import org.xml.sax.SAXException;

public class ToXMLStream extends ToStream
{
    boolean m_cdataTagOpen;
    protected static CharInfo m_xmlcharInfo;
    
    public ToXMLStream() {
        this.m_cdataTagOpen = false;
        super.m_charInfo = ToXMLStream.m_xmlcharInfo;
        this.initCDATA();
        super.m_prefixMap = new NamespaceMappings();
    }
    
    public void CopyFrom(final ToXMLStream xmlListener) {
        super.m_writer = xmlListener.m_writer;
        final String encoding = xmlListener.getEncoding();
        this.setEncoding(encoding);
        this.setOmitXMLDeclaration(xmlListener.getOmitXMLDeclaration());
        super.m_ispreserve = xmlListener.m_ispreserve;
        super.m_preserves = xmlListener.m_preserves;
        super.m_isprevtext = xmlListener.m_isprevtext;
        super.m_doIndent = xmlListener.m_doIndent;
        this.setIndentAmount(xmlListener.getIndentAmount());
        super.m_startNewLine = xmlListener.m_startNewLine;
        super.m_needToOutputDocTypeDecl = xmlListener.m_needToOutputDocTypeDecl;
        this.setDoctypeSystem(xmlListener.getDoctypeSystem());
        this.setDoctypePublic(xmlListener.getDoctypePublic());
        this.setStandalone(xmlListener.getStandalone());
        this.setMediaType(xmlListener.getMediaType());
        super.m_maxCharacter = xmlListener.m_maxCharacter;
        super.m_spaceBeforeClose = xmlListener.m_spaceBeforeClose;
        super.m_cdataStartCalled = xmlListener.m_cdataStartCalled;
    }
    
    public void startDocumentInternal() throws SAXException {
        if (super.m_needToCallStartDocument) {
            super.startDocumentInternal();
            super.m_needToCallStartDocument = false;
            if (super.m_inEntityRef) {
                return;
            }
            super.m_needToOutputDocTypeDecl = true;
            super.m_startNewLine = false;
            if (!this.getOmitXMLDeclaration()) {
                final String encoding = Encodings.getMimeEncoding(this.getEncoding());
                String version = this.getVersion();
                if (version == null) {
                    version = "1.0";
                }
                String standalone;
                if (super.m_standaloneWasSpecified) {
                    standalone = " standalone=\"" + this.getStandalone() + "\"";
                }
                else {
                    standalone = "";
                }
                try {
                    final Writer writer = super.m_writer;
                    writer.write("<?xml version=\"");
                    writer.write(version);
                    writer.write("\" encoding=\"");
                    writer.write(encoding);
                    writer.write(34);
                    writer.write(standalone);
                    writer.write("?>");
                    if (super.m_doIndent) {
                        writer.write(super.m_lineSep, 0, super.m_lineSepLen);
                    }
                }
                catch (IOException e) {
                    throw new SAXException(e);
                }
            }
        }
    }
    
    public void endDocument() throws SAXException {
        this.flushPending();
        if (super.m_doIndent && !super.m_isprevtext) {
            try {
                this.outputLineSep();
            }
            catch (IOException e) {
                throw new SAXException(e);
            }
        }
        this.flushWriter();
        if (super.m_tracer != null) {
            super.fireEndDoc();
        }
    }
    
    public void startPreserving() throws SAXException {
        super.m_preserves.push(true);
        super.m_ispreserve = true;
    }
    
    public void endPreserving() throws SAXException {
        super.m_ispreserve = (!super.m_preserves.isEmpty() && super.m_preserves.pop());
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (super.m_inEntityRef) {
            return;
        }
        this.flushPending();
        if (target.equals("javax.xml.transform.disable-output-escaping")) {
            this.startNonEscaping();
        }
        else if (target.equals("javax.xml.transform.enable-output-escaping")) {
            this.endNonEscaping();
        }
        else {
            try {
                if (super.m_elemContext.m_startTagOpen) {
                    this.closeStartTag();
                    super.m_elemContext.m_startTagOpen = false;
                }
                if (this.shouldIndent()) {
                    this.indent();
                }
                final Writer writer = super.m_writer;
                writer.write("<?");
                writer.write(target);
                if (data.length() > 0 && !Character.isSpaceChar(data.charAt(0))) {
                    writer.write(32);
                }
                final int indexOfQLT = data.indexOf("?>");
                if (indexOfQLT >= 0) {
                    if (indexOfQLT > 0) {
                        writer.write(data.substring(0, indexOfQLT));
                    }
                    writer.write("? >");
                    if (indexOfQLT + 2 < data.length()) {
                        writer.write(data.substring(indexOfQLT + 2));
                    }
                }
                else {
                    writer.write(data);
                }
                writer.write(63);
                writer.write(62);
                if (super.m_elemContext.m_currentElemDepth <= 0) {
                    writer.write(super.m_lineSep, 0, super.m_lineSepLen);
                }
                super.m_startNewLine = true;
            }
            catch (IOException e) {
                throw new SAXException(e);
            }
        }
        if (super.m_tracer != null) {
            super.fireEscapingEvent(target, data);
        }
    }
    
    public void entityReference(final String name) throws SAXException {
        if (super.m_elemContext.m_startTagOpen) {
            this.closeStartTag();
            super.m_elemContext.m_startTagOpen = false;
        }
        try {
            if (this.shouldIndent()) {
                this.indent();
            }
            final Writer writer = super.m_writer;
            writer.write(38);
            writer.write(name);
            writer.write(59);
        }
        catch (IOException e) {
            throw new SAXException(e);
        }
        if (super.m_tracer != null) {
            super.fireEntityReference(name);
        }
    }
    
    public void addUniqueAttribute(final String name, final String value, final int flags) throws SAXException {
        if (super.m_elemContext.m_startTagOpen) {
            try {
                final String patchedName = this.patchName(name);
                final Writer writer = super.m_writer;
                if ((flags & 0x1) > 0 && ToXMLStream.m_xmlcharInfo.onlyQuotAmpLtGt) {
                    writer.write(32);
                    writer.write(patchedName);
                    writer.write("=\"");
                    writer.write(value);
                    writer.write(34);
                }
                else {
                    writer.write(32);
                    writer.write(patchedName);
                    writer.write("=\"");
                    this.writeAttrString(writer, value, this.getEncoding());
                    writer.write(34);
                }
            }
            catch (IOException e) {
                throw new SAXException(e);
            }
        }
    }
    
    public void addAttribute(final String uri, final String localName, String rawName, final String type, final String value) throws SAXException {
        if (super.m_elemContext.m_startTagOpen) {
            if (!rawName.startsWith("xmlns")) {
                final String prefixUsed = this.ensureAttributesNamespaceIsDeclared(uri, localName, rawName);
                if (prefixUsed != null && rawName != null && !rawName.startsWith(prefixUsed)) {
                    rawName = prefixUsed + ":" + localName;
                }
            }
            this.addAttributeAlways(uri, localName, rawName, type, value);
        }
        else {
            final String msg = XMLMessages.createXMLMessage("ER_ILLEGAL_ATTRIBUTE_POSITION", new Object[] { localName });
            try {
                final Transformer tran = super.getTransformer();
                final ErrorListener errHandler = tran.getErrorListener();
                if (null != errHandler && super.m_sourceLocator != null) {
                    errHandler.warning(new TransformerException(msg, super.m_sourceLocator));
                }
                else {
                    System.out.println(msg);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void endElement(final String elemName) throws SAXException {
        this.endElement(null, null, elemName);
    }
    
    public void namespaceAfterStartElement(final String prefix, final String uri) throws SAXException {
        if (super.m_elemContext.m_elementURI == null) {
            final String prefix2 = SerializerBase.getPrefixPart(super.m_elemContext.m_elementName);
            if (prefix2 == null && "".equals(prefix)) {
                super.m_elemContext.m_elementURI = uri;
            }
        }
        this.startPrefixMapping(prefix, uri, false);
    }
    
    protected boolean pushNamespace(final String prefix, final String uri) {
        try {
            if (super.m_prefixMap.pushNamespace(prefix, uri, super.m_elemContext.m_currentElemDepth)) {
                this.startPrefixMapping(prefix, uri);
                return true;
            }
        }
        catch (SAXException ex) {}
        return false;
    }
    
    public boolean reset() {
        boolean wasReset = false;
        if (super.reset()) {
            this.resetToXMLStream();
            wasReset = true;
        }
        return wasReset;
    }
    
    private void resetToXMLStream() {
        this.m_cdataTagOpen = false;
    }
    
    static {
        ToXMLStream.m_xmlcharInfo = CharInfo.getCharInfo(CharInfo.XML_ENTITIES_RESOURCE, "xml");
    }
}
