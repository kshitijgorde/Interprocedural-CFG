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
import org.xml.sax.SAXException;
import java.util.Vector;
import org.w3c.dom.Node;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;
import java.io.IOException;
import org.xml.sax.ContentHandler;

public class EmptySerializer implements SerializationHandler
{
    protected static final String ERR = "EmptySerializer method not over-ridden";
    
    private static void throwUnimplementedException() {
    }
    
    public ContentHandler asContentHandler() throws IOException {
        throwUnimplementedException();
        return null;
    }
    
    public void setContentHandler(final ContentHandler ch) {
        throwUnimplementedException();
    }
    
    public void close() {
        throwUnimplementedException();
    }
    
    public Properties getOutputFormat() {
        throwUnimplementedException();
        return null;
    }
    
    public OutputStream getOutputStream() {
        throwUnimplementedException();
        return null;
    }
    
    public Writer getWriter() {
        throwUnimplementedException();
        return null;
    }
    
    public boolean reset() {
        throwUnimplementedException();
        return false;
    }
    
    public void serialize(final Node node) throws IOException {
        throwUnimplementedException();
    }
    
    public void setCdataSectionElements(final Vector URI_and_localNames) {
        throwUnimplementedException();
    }
    
    public boolean setEscaping(final boolean escape) throws SAXException {
        throwUnimplementedException();
        return false;
    }
    
    public void setIndent(final boolean indent) {
        throwUnimplementedException();
    }
    
    public void setIndentAmount(final int spaces) {
        throwUnimplementedException();
    }
    
    public void setOutputFormat(final Properties format) {
        throwUnimplementedException();
    }
    
    public void setOutputStream(final OutputStream output) {
        throwUnimplementedException();
    }
    
    public void setVersion(final String version) {
        throwUnimplementedException();
    }
    
    public void setWriter(final Writer writer) {
        throwUnimplementedException();
    }
    
    public void setTransformer(final Transformer transformer) {
        throwUnimplementedException();
    }
    
    public Transformer getTransformer() {
        throwUnimplementedException();
        return null;
    }
    
    public void flushPending() throws SAXException {
        throwUnimplementedException();
    }
    
    public void addAttribute(final String uri, final String localName, final String rawName, final String type, final String value) throws SAXException {
        throwUnimplementedException();
    }
    
    public void addAttributes(final Attributes atts) throws SAXException {
        throwUnimplementedException();
    }
    
    public void addAttribute(final String name, final String value) {
        throwUnimplementedException();
    }
    
    public void characters(final String chars) throws SAXException {
        throwUnimplementedException();
    }
    
    public void endElement(final String elemName) throws SAXException {
        throwUnimplementedException();
    }
    
    public void startDocument() throws SAXException {
        throwUnimplementedException();
    }
    
    public void startElement(final String uri, final String localName, final String qName) throws SAXException {
        throwUnimplementedException();
    }
    
    public void startElement(final String qName) throws SAXException {
        throwUnimplementedException();
    }
    
    public void namespaceAfterStartElement(final String uri, final String prefix) throws SAXException {
        throwUnimplementedException();
    }
    
    public boolean startPrefixMapping(final String prefix, final String uri, final boolean shouldFlush) throws SAXException {
        throwUnimplementedException();
        return false;
    }
    
    public void entityReference(final String entityName) throws SAXException {
        throwUnimplementedException();
    }
    
    public NamespaceMappings getNamespaceMappings() {
        throwUnimplementedException();
        return null;
    }
    
    public String getPrefix(final String uri) {
        throwUnimplementedException();
        return null;
    }
    
    public String getNamespaceURI(final String name, final boolean isElement) {
        throwUnimplementedException();
        return null;
    }
    
    public String getNamespaceURIFromPrefix(final String prefix) {
        throwUnimplementedException();
        return null;
    }
    
    public void setDocumentLocator(final Locator arg0) {
        throwUnimplementedException();
    }
    
    public void endDocument() throws SAXException {
        throwUnimplementedException();
    }
    
    public void startPrefixMapping(final String arg0, final String arg1) throws SAXException {
        throwUnimplementedException();
    }
    
    public void endPrefixMapping(final String arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public void startElement(final String arg0, final String arg1, final String arg2, final Attributes arg3) throws SAXException {
        throwUnimplementedException();
    }
    
    public void endElement(final String arg0, final String arg1, final String arg2) throws SAXException {
        throwUnimplementedException();
    }
    
    public void characters(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        throwUnimplementedException();
    }
    
    public void ignorableWhitespace(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        throwUnimplementedException();
    }
    
    public void processingInstruction(final String arg0, final String arg1) throws SAXException {
        throwUnimplementedException();
    }
    
    public void skippedEntity(final String arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public void comment(final String comment) throws SAXException {
        throwUnimplementedException();
    }
    
    public void startDTD(final String arg0, final String arg1, final String arg2) throws SAXException {
        throwUnimplementedException();
    }
    
    public void endDTD() throws SAXException {
        throwUnimplementedException();
    }
    
    public void startEntity(final String arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public void endEntity(final String arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public void startCDATA() throws SAXException {
        throwUnimplementedException();
    }
    
    public void endCDATA() throws SAXException {
        throwUnimplementedException();
    }
    
    public void comment(final char[] arg0, final int arg1, final int arg2) throws SAXException {
        throwUnimplementedException();
    }
    
    public String getDoctypePublic() {
        throwUnimplementedException();
        return null;
    }
    
    public String getDoctypeSystem() {
        throwUnimplementedException();
        return null;
    }
    
    public String getEncoding() {
        throwUnimplementedException();
        return null;
    }
    
    public boolean getIndent() {
        throwUnimplementedException();
        return false;
    }
    
    public int getIndentAmount() {
        throwUnimplementedException();
        return 0;
    }
    
    public String getMediaType() {
        throwUnimplementedException();
        return null;
    }
    
    public boolean getOmitXMLDeclaration() {
        throwUnimplementedException();
        return false;
    }
    
    public String getStandalone() {
        throwUnimplementedException();
        return null;
    }
    
    public String getVersion() {
        throwUnimplementedException();
        return null;
    }
    
    public void setCdataSectionElements(final Hashtable h) throws Exception {
        throwUnimplementedException();
    }
    
    public void setDoctype(final String system, final String pub) {
        throwUnimplementedException();
    }
    
    public void setDoctypePublic(final String doctype) {
        throwUnimplementedException();
    }
    
    public void setDoctypeSystem(final String doctype) {
        throwUnimplementedException();
    }
    
    public void setEncoding(final String encoding) {
        throwUnimplementedException();
    }
    
    public void setMediaType(final String mediatype) {
        throwUnimplementedException();
    }
    
    public void setOmitXMLDeclaration(final boolean b) {
        throwUnimplementedException();
    }
    
    public void setStandalone(final String standalone) {
        throwUnimplementedException();
    }
    
    public void elementDecl(final String arg0, final String arg1) throws SAXException {
        throwUnimplementedException();
    }
    
    public void attributeDecl(final String arg0, final String arg1, final String arg2, final String arg3, final String arg4) throws SAXException {
        throwUnimplementedException();
    }
    
    public void internalEntityDecl(final String arg0, final String arg1) throws SAXException {
        throwUnimplementedException();
    }
    
    public void externalEntityDecl(final String arg0, final String arg1, final String arg2) throws SAXException {
        throwUnimplementedException();
    }
    
    public void warning(final SAXParseException arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public void error(final SAXParseException arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public void fatalError(final SAXParseException arg0) throws SAXException {
        throwUnimplementedException();
    }
    
    public DOMSerializer asDOMSerializer() throws IOException {
        throwUnimplementedException();
        return null;
    }
    
    public void setNamespaceMappings(final NamespaceMappings mappings) {
        throwUnimplementedException();
    }
    
    public void setSourceLocator(final SourceLocator locator) {
        throwUnimplementedException();
    }
    
    public void addUniqueAttribute(final String name, final String value, final int flags) throws SAXException {
        throwUnimplementedException();
    }
}
