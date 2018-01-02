// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import javax.xml.transform.SourceLocator;
import org.xml.sax.SAXParseException;
import java.util.Hashtable;
import org.xml.sax.Locator;
import org.xml.sax.Attributes;
import javax.xml.transform.Transformer;
import java.util.Vector;
import org.w3c.dom.Node;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import java.io.IOException;

public class EmptySerializer implements SerializationHandler
{
    protected static final String ERR = "EmptySerializer method not over-ridden";
    
    protected void couldThrowIOException() throws IOException {
    }
    
    protected void couldThrowSAXException() throws SAXException {
    }
    
    protected void couldThrowSAXException(final char[] chars, final int off, final int len) throws SAXException {
    }
    
    protected void couldThrowSAXException(final String elemQName) throws SAXException {
    }
    
    protected void couldThrowException() throws Exception {
    }
    
    void aMethodIsCalled() {
    }
    
    public ContentHandler asContentHandler() throws IOException {
        this.couldThrowIOException();
        return null;
    }
    
    public void setContentHandler(final ContentHandler ch) {
        this.aMethodIsCalled();
    }
    
    public void close() {
        this.aMethodIsCalled();
    }
    
    public Properties getOutputFormat() {
        this.aMethodIsCalled();
        return null;
    }
    
    public OutputStream getOutputStream() {
        this.aMethodIsCalled();
        return null;
    }
    
    public Writer getWriter() {
        this.aMethodIsCalled();
        return null;
    }
    
    public boolean reset() {
        this.aMethodIsCalled();
        return false;
    }
    
    public void serialize(final Node node) throws IOException {
        this.couldThrowIOException();
    }
    
    public void setCdataSectionElements(final Vector URI_and_localNames) {
        this.aMethodIsCalled();
    }
    
    public boolean setEscaping(final boolean escape) throws SAXException {
        this.couldThrowSAXException();
        return false;
    }
    
    public void setIndent(final boolean indent) {
        this.aMethodIsCalled();
    }
    
    public void setIndentAmount(final int spaces) {
        this.aMethodIsCalled();
    }
    
    public void setOutputFormat(final Properties format) {
        this.aMethodIsCalled();
    }
    
    public void setOutputStream(final OutputStream output) {
        this.aMethodIsCalled();
    }
    
    public void setVersion(final String version) {
        this.aMethodIsCalled();
    }
    
    public void setWriter(final Writer writer) {
        this.aMethodIsCalled();
    }
    
    public void setTransformer(final Transformer transformer) {
        this.aMethodIsCalled();
    }
    
    public Transformer getTransformer() {
        this.aMethodIsCalled();
        return null;
    }
    
    public void flushPending() throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void addAttribute(final String uri, final String localName, final String rawName, final String type, final String value, final boolean XSLAttribute) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void addAttributes(final Attributes atts) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void addAttribute(final String name, final String value) {
        this.aMethodIsCalled();
    }
    
    public void characters(final String chars) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void endElement(final String elemName) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startDocument() throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startElement(final String uri, final String localName, final String qName) throws SAXException {
        this.couldThrowSAXException(qName);
    }
    
    public void startElement(final String qName) throws SAXException {
        this.couldThrowSAXException(qName);
    }
    
    public void namespaceAfterStartElement(final String uri, final String prefix) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        this.couldThrowSAXException();
        return false;
    }
    
    public void entityReference(final String entityName) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public NamespaceMappings getNamespaceMappings() {
        this.aMethodIsCalled();
        return null;
    }
    
    public String getPrefix(final String uri) {
        this.aMethodIsCalled();
        return null;
    }
    
    public String getNamespaceURI(final String name, final boolean isElement) {
        this.aMethodIsCalled();
        return null;
    }
    
    public String getNamespaceURIFromPrefix(final String prefix) {
        this.aMethodIsCalled();
        return null;
    }
    
    public void setDocumentLocator(final Locator arg0) {
        this.aMethodIsCalled();
    }
    
    public void endDocument() throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startPrefixMapping(final String arg0, final String arg1) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void endPrefixMapping(final String arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startElement(final String arg0, final String arg1, final String arg2, final Attributes arg3) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void endElement(final String arg0, final String arg1, final String arg2) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void characters(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        this.couldThrowSAXException(arg0, arg1, arg2);
    }
    
    public void ignorableWhitespace(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void processingInstruction(final String arg0, final String arg1) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void skippedEntity(final String arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void comment(final String comment) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startDTD(final String arg0, final String arg1, final String arg2) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void endDTD() throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startEntity(final String arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void endEntity(final String arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void startCDATA() throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void endCDATA() throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void comment(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public String getDoctypePublic() {
        this.aMethodIsCalled();
        return null;
    }
    
    public String getDoctypeSystem() {
        this.aMethodIsCalled();
        return null;
    }
    
    public String getEncoding() {
        this.aMethodIsCalled();
        return null;
    }
    
    public boolean getIndent() {
        this.aMethodIsCalled();
        return false;
    }
    
    public int getIndentAmount() {
        this.aMethodIsCalled();
        return 0;
    }
    
    public String getMediaType() {
        this.aMethodIsCalled();
        return null;
    }
    
    public boolean getOmitXMLDeclaration() {
        this.aMethodIsCalled();
        return false;
    }
    
    public String getStandalone() {
        this.aMethodIsCalled();
        return null;
    }
    
    public String getVersion() {
        this.aMethodIsCalled();
        return null;
    }
    
    public void setCdataSectionElements(final Hashtable h) throws Exception {
        this.couldThrowException();
    }
    
    public void setDoctype(final String system, final String pub) {
        this.aMethodIsCalled();
    }
    
    public void setDoctypePublic(final String doctype) {
        this.aMethodIsCalled();
    }
    
    public void setDoctypeSystem(final String doctype) {
        this.aMethodIsCalled();
    }
    
    public void setEncoding(final String encoding) {
        this.aMethodIsCalled();
    }
    
    public void setMediaType(final String mediatype) {
        this.aMethodIsCalled();
    }
    
    public void setOmitXMLDeclaration(final boolean b) {
        this.aMethodIsCalled();
    }
    
    public void setStandalone(final String standalone) {
        this.aMethodIsCalled();
    }
    
    public void elementDecl(final String arg0, final String arg1) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void attributeDecl(final String arg0, final String arg1, final String arg2, final String arg3, final String arg4) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void internalEntityDecl(final String arg0, final String arg1) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void externalEntityDecl(final String arg0, final String arg1, final String arg2) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void warning(final SAXParseException arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void error(final SAXParseException arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void fatalError(final SAXParseException arg0) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public DOMSerializer asDOMSerializer() throws IOException {
        this.couldThrowIOException();
        return null;
    }
    
    public void setNamespaceMappings(final NamespaceMappings mappings) {
        this.aMethodIsCalled();
    }
    
    public void setSourceLocator(final SourceLocator locator) {
        this.aMethodIsCalled();
    }
    
    public void addUniqueAttribute(final String name, final String value, final int flags) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void characters(final Node node) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void addXSLAttribute(final String qName, final String value, final String uri) {
        this.aMethodIsCalled();
    }
    
    public void addAttribute(final String uri, final String localName, final String rawName, final String type, final String value) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void notationDecl(final String arg0, final String arg1, final String arg2) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void unparsedEntityDecl(final String arg0, final String arg1, final String arg2, final String arg3) throws SAXException {
        this.couldThrowSAXException();
    }
    
    public void setDTDEntityExpansion(final boolean expand) {
        this.aMethodIsCalled();
    }
    
    public void setNewLine(final char[] nl) {
        this.aMethodIsCalled();
    }
    
    public Object asDOM3Serializer() throws IOException {
        this.couldThrowIOException();
        return null;
    }
}
