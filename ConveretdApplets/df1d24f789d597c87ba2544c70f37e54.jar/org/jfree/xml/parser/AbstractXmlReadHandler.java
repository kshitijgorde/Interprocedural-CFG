// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public abstract class AbstractXmlReadHandler implements XmlReadHandler
{
    protected RootXmlReadHandler rootHandler;
    protected String tagName;
    private boolean firstCall;
    
    public AbstractXmlReadHandler() {
        this.firstCall = true;
    }
    
    public void init(final RootXmlReadHandler rootHandler, final String tagName) {
        if (rootHandler == null) {
            throw new NullPointerException("Root handler must not be null");
        }
        if (tagName == null) {
            throw new NullPointerException("Tag name must not be null");
        }
        this.rootHandler = rootHandler;
        this.tagName = tagName;
    }
    
    public final void startElement(final String s, final Attributes attributes) throws XmlReaderException, SAXException {
        if (this.firstCall) {
            if (!this.tagName.equals(s)) {
                throw new SAXException("Expected <" + this.tagName + ">, found <" + s + ">");
            }
            this.firstCall = false;
            this.startParsing(attributes);
        }
        else {
            final XmlReadHandler handlerForChild = this.getHandlerForChild(s, attributes);
            if (handlerForChild == null) {
                System.err.println("Unknown tag <" + s + ">");
                return;
            }
            handlerForChild.init(this.getRootHandler(), s);
            this.rootHandler.recurse(handlerForChild, s, attributes);
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public final void endElement(final String s) throws SAXException {
        if (this.tagName.equals(s)) {
            try {
                this.doneParsing();
                this.rootHandler.unwind(s);
            }
            catch (XmlReaderException e) {
                throw new SAXException(e);
            }
        }
    }
    
    protected void startParsing(final Attributes attributes) throws SAXException {
    }
    
    protected void doneParsing() throws SAXException, XmlReaderException {
    }
    
    protected XmlReadHandler getHandlerForChild(final String s, final Attributes attributes) throws XmlReaderException, SAXException {
        return null;
    }
    
    public String getTagName() {
        return this.tagName;
    }
    
    public RootXmlReadHandler getRootHandler() {
        return this.rootHandler;
    }
}
