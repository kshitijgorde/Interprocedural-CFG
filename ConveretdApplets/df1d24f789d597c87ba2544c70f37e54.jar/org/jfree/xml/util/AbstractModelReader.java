// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import org.jfree.xml.ElementDefinitionException;
import org.xml.sax.Attributes;
import java.util.Stack;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;
import java.io.InputStream;
import org.xml.sax.InputSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.jfree.util.Log;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.net.URL;
import org.jfree.xml.CommentHandler;

public abstract class AbstractModelReader
{
    private static final int STATE_START = 0;
    private static final int IN_OBJECT = 1;
    private static final int IGNORE_OBJECT = 2;
    private static final int MAPPING_STATE = 3;
    private static final int CONSTRUCTOR_STATE = 4;
    private CommentHandler commentHandler;
    private String[] closeComment;
    private String[] openComment;
    
    public AbstractModelReader() {
        this.commentHandler = new CommentHandler();
    }
    
    private CommentHandler getCommentHandler() {
        return this.commentHandler;
    }
    
    protected String[] getCloseComment() {
        return this.closeComment;
    }
    
    protected String[] getOpenComment() {
        return this.openComment;
    }
    
    protected void setCloseComment(final String[] closeComment) {
        this.closeComment = closeComment;
    }
    
    protected void setOpenComment(final String[] openComment) {
        this.openComment = openComment;
    }
    
    protected void parseXml(final URL url) throws ObjectDescriptionException {
        this.parseXmlDocument(url, false);
    }
    
    protected void parseXmlDocument(final URL url, final boolean b) throws ObjectDescriptionException {
        try {
            final BufferedInputStream byteStream = new BufferedInputStream(url.openStream());
            final XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            final SAXModelHandler errorHandler = new SAXModelHandler(url, b);
            try {
                xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", this.getCommentHandler());
            }
            catch (SAXException ex2) {
                Log.debug("Comments are not supported by this SAX implementation.");
            }
            xmlReader.setContentHandler(errorHandler);
            xmlReader.setDTDHandler(errorHandler);
            xmlReader.setErrorHandler(errorHandler);
            xmlReader.parse(new InputSource(byteStream));
            byteStream.close();
        }
        catch (Exception ex) {
            Log.warn("Unable to load factory specifications", ex);
            throw new ObjectDescriptionException("Unable to load object factory specs.", ex);
        }
    }
    
    protected void startRootDocument() {
    }
    
    protected void endRootDocument() {
    }
    
    protected void startIncludeHandling(final URL url) {
    }
    
    protected void endIncludeHandling() {
    }
    
    protected void handleIgnoredProperty(final String s) {
    }
    
    protected abstract boolean handleManualMapping(final String p0, final String p1, final String p2) throws ObjectDescriptionException;
    
    protected abstract boolean startObjectDefinition(final String p0, final String p1, final boolean p2) throws ObjectDescriptionException;
    
    protected abstract void handleAttributeDefinition(final String p0, final String p1, final String p2) throws ObjectDescriptionException;
    
    protected abstract void handleElementDefinition(final String p0, final String p1) throws ObjectDescriptionException;
    
    protected abstract void handleLookupDefinition(final String p0, final String p1) throws ObjectDescriptionException;
    
    protected abstract void endObjectDefinition() throws ObjectDescriptionException;
    
    protected abstract void startMultiplexMapping(final String p0, final String p1);
    
    protected abstract void handleMultiplexMapping(final String p0, final String p1) throws ObjectDescriptionException;
    
    protected abstract void endMultiplexMapping() throws ObjectDescriptionException;
    
    protected abstract void handleConstructorDefinition(final String p0, final String p1) throws ObjectDescriptionException;
    
    protected Class loadClass(final String s) {
        if (s == null) {
            return null;
        }
        if (s.startsWith("::")) {
            return BasicTypeSupport.getClassRepresentation(s);
        }
        try {
            return this.getClass().getClassLoader().loadClass(s);
        }
        catch (Exception ex) {
            Log.warn("Unable to load class", ex);
            return null;
        }
    }
    
    private class SAXModelHandler extends DefaultHandler
    {
        private URL resource;
        private int state;
        private Stack openComments;
        private boolean isInclude;
        
        public SAXModelHandler(final URL resource, final boolean isInclude) {
            if (resource == null) {
                throw new NullPointerException();
            }
            this.resource = resource;
            this.openComments = new Stack();
            this.isInclude = isInclude;
        }
        
        public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
            AbstractModelReader.this.setOpenComment(AbstractModelReader.this.getCommentHandler().getComments());
            this.openComments.push(AbstractModelReader.this.getOpenComment());
            AbstractModelReader.this.setCloseComment(null);
            try {
                if (!this.isInclude && s3.equals("objects")) {
                    AbstractModelReader.this.startRootDocument();
                    return;
                }
                if (this.getState() == 0) {
                    this.startRootElement(s3, attributes);
                }
                else {
                    if (this.getState() == 2) {
                        return;
                    }
                    if (this.getState() == 1) {
                        this.startObjectElement(s3, attributes);
                    }
                    else if (this.getState() == 3) {
                        if (!s3.equals("type")) {
                            throw new SAXException("Expected 'type' tag");
                        }
                        AbstractModelReader.this.handleMultiplexMapping(attributes.getValue("name"), attributes.getValue("class"));
                    }
                    else if (this.getState() == 4) {
                        if (!s3.equals("parameter")) {
                            throw new SAXException("Expected 'parameter' tag");
                        }
                        AbstractModelReader.this.handleConstructorDefinition(attributes.getValue("property"), attributes.getValue("class"));
                    }
                }
            }
            catch (ObjectDescriptionException e) {
                throw new SAXException(e);
            }
            finally {
                AbstractModelReader.this.commentHandler.clearComments();
            }
        }
        
        public void endElement(final String s, final String s2, final String s3) throws SAXException {
            AbstractModelReader.this.setOpenComment(this.openComments.pop());
            AbstractModelReader.this.setCloseComment(AbstractModelReader.this.getCommentHandler().getComments());
            try {
                if (!this.isInclude && s3.equals("objects")) {
                    AbstractModelReader.this.endRootDocument();
                    return;
                }
                if (s3.equals("object")) {
                    if (this.getState() != 2) {
                        AbstractModelReader.this.endObjectDefinition();
                    }
                    this.setState(0);
                }
                else if (s3.equals("mapping")) {
                    this.setState(0);
                    AbstractModelReader.this.endMultiplexMapping();
                }
                else if (s3.equals("constructor") && this.getState() != 2) {
                    this.setState(1);
                }
            }
            catch (ObjectDescriptionException e) {
                throw new SAXException(e);
            }
            finally {
                AbstractModelReader.this.commentHandler.clearComments();
            }
        }
        
        private void startObjectElement(final String s, final Attributes attributes) throws ObjectDescriptionException {
            if (s.equals("constructor")) {
                this.setState(4);
            }
            else if (s.equals("lookup")) {
                AbstractModelReader.this.handleLookupDefinition(attributes.getValue("name"), attributes.getValue("lookup"));
            }
            else if (s.equals("ignore")) {
                AbstractModelReader.this.handleIgnoredProperty(attributes.getValue("name"));
            }
            else if (s.equals("element-property")) {
                AbstractModelReader.this.handleElementDefinition(attributes.getValue("name"), attributes.getValue("element"));
            }
            else if (s.equals("attribute-property")) {
                AbstractModelReader.this.handleAttributeDefinition(attributes.getValue("name"), attributes.getValue("attribute"), attributes.getValue("handler"));
            }
        }
        
        private void startRootElement(final String s, final Attributes attributes) throws SAXException, ObjectDescriptionException {
            if (s.equals("include")) {
                if (this.isInclude) {
                    Log.warn("Ignored nested include tag.");
                    return;
                }
                final String value = attributes.getValue("src");
                try {
                    final URL url = new URL(this.resource, value);
                    AbstractModelReader.this.startIncludeHandling(url);
                    AbstractModelReader.this.parseXmlDocument(url, true);
                    AbstractModelReader.this.endIncludeHandling();
                    return;
                }
                catch (Exception ex) {
                    throw new ElementDefinitionException(ex, "Unable to include file from " + value);
                }
            }
            if (s.equals("object")) {
                this.setState(1);
                final String value2 = attributes.getValue("class");
                String value3 = attributes.getValue("register-name");
                if (value3 != null && value3.length() == 0) {
                    value3 = null;
                }
                if (!AbstractModelReader.this.startObjectDefinition(value2, value3, "true".equals(attributes.getValue("ignore")))) {
                    this.setState(2);
                }
            }
            else if (s.equals("manual")) {
                AbstractModelReader.this.handleManualMapping(attributes.getValue("class"), attributes.getValue("read-handler"), attributes.getValue("write-handler"));
            }
            else if (s.equals("mapping")) {
                this.setState(3);
                AbstractModelReader.this.startMultiplexMapping(attributes.getValue("base-class"), attributes.getValue("type-attribute"));
            }
        }
        
        private int getState() {
            return this.state;
        }
        
        private void setState(final int state) {
            this.state = state;
        }
    }
}
