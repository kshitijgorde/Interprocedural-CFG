// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.transform;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;
import org.jdom.input.SAXHandler;
import java.util.Collection;
import org.jdom.Element;
import org.jdom.DefaultJDOMFactory;
import java.util.ArrayList;
import org.jdom.Document;
import java.util.Collections;
import java.util.List;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.jdom.JDOMFactory;
import javax.xml.transform.sax.SAXResult;

public class JDOMResult extends SAXResult
{
    private static final String CVS_ID = "@(#) $RCSfile: JDOMResult.java,v $ $Revision: 1.24 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
    public static final String JDOM_FEATURE = "http://org.jdom.transform.JDOMResult/feature";
    private Object result;
    private boolean queried;
    private JDOMFactory factory;
    
    public JDOMResult() {
        this.result = null;
        this.queried = false;
        this.factory = null;
        final DocumentBuilder builder = new DocumentBuilder();
        super.setHandler(builder);
        super.setLexicalHandler(builder);
    }
    
    public void setResult(final List result) {
        this.result = result;
        this.queried = false;
    }
    
    public List getResult() {
        List nodes = Collections.EMPTY_LIST;
        this.retrieveResult();
        if (this.result instanceof List) {
            nodes = (List)this.result;
        }
        else if (this.result instanceof Document && !this.queried) {
            final List content = ((Document)this.result).getContent();
            nodes = new ArrayList(content.size());
            while (content.size() != 0) {
                final Object o = content.remove(0);
                nodes.add(o);
            }
            this.result = nodes;
        }
        this.queried = true;
        return nodes;
    }
    
    public void setDocument(final Document document) {
        this.result = document;
        this.queried = false;
    }
    
    public Document getDocument() {
        Document doc = null;
        this.retrieveResult();
        if (this.result instanceof Document) {
            doc = (Document)this.result;
        }
        else if (this.result instanceof List && !this.queried) {
            try {
                JDOMFactory f = this.getFactory();
                if (f == null) {
                    f = new DefaultJDOMFactory();
                }
                doc = f.document(null);
                doc.setContent((Collection)this.result);
                this.result = doc;
            }
            catch (RuntimeException ex) {}
        }
        this.queried = true;
        return doc;
    }
    
    public void setFactory(final JDOMFactory factory) {
        this.factory = factory;
    }
    
    public JDOMFactory getFactory() {
        return this.factory;
    }
    
    private void retrieveResult() {
        if (this.result == null) {
            this.setResult(((DocumentBuilder)this.getHandler()).getResult());
        }
    }
    
    public void setHandler(final ContentHandler handler) {
    }
    
    public void setLexicalHandler(final LexicalHandler handler) {
    }
    
    private static class FragmentHandler extends SAXHandler
    {
        private Element dummyRoot;
        
        public FragmentHandler(final JDOMFactory factory) {
            super(factory);
            this.pushElement(this.dummyRoot = new Element("root", null, null));
        }
        
        public List getResult() {
            try {
                this.flushCharacters();
            }
            catch (SAXException ex) {}
            return this.getDetachedContent(this.dummyRoot);
        }
        
        private List getDetachedContent(final Element elt) {
            final List content = elt.getContent();
            final List nodes = new ArrayList(content.size());
            while (content.size() != 0) {
                final Object o = content.remove(0);
                nodes.add(o);
            }
            return nodes;
        }
    }
    
    private class DocumentBuilder extends XMLFilterImpl implements LexicalHandler
    {
        private FragmentHandler saxHandler;
        private boolean startDocumentReceived;
        
        public DocumentBuilder() {
            this.saxHandler = null;
            this.startDocumentReceived = false;
        }
        
        public List getResult() {
            List result = null;
            if (this.saxHandler != null) {
                result = this.saxHandler.getResult();
                this.saxHandler = null;
                this.startDocumentReceived = false;
            }
            return result;
        }
        
        private void ensureInitialization() throws SAXException {
            if (!this.startDocumentReceived) {
                this.startDocument();
            }
        }
        
        public void startDocument() throws SAXException {
            this.startDocumentReceived = true;
            JDOMResult.this.setResult(null);
            super.setContentHandler(this.saxHandler = new FragmentHandler(JDOMResult.this.getFactory()));
            super.startDocument();
        }
        
        public void startElement(final String nsURI, final String localName, final String qName, final Attributes atts) throws SAXException {
            this.ensureInitialization();
            super.startElement(nsURI, localName, qName, atts);
        }
        
        public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
            this.ensureInitialization();
            super.startPrefixMapping(prefix, uri);
        }
        
        public void characters(final char[] ch, final int start, final int length) throws SAXException {
            this.ensureInitialization();
            super.characters(ch, start, length);
        }
        
        public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
            this.ensureInitialization();
            super.ignorableWhitespace(ch, start, length);
        }
        
        public void processingInstruction(final String target, final String data) throws SAXException {
            this.ensureInitialization();
            super.processingInstruction(target, data);
        }
        
        public void skippedEntity(final String name) throws SAXException {
            this.ensureInitialization();
            super.skippedEntity(name);
        }
        
        public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
            this.ensureInitialization();
            this.saxHandler.startDTD(name, publicId, systemId);
        }
        
        public void endDTD() throws SAXException {
            this.saxHandler.endDTD();
        }
        
        public void startEntity(final String name) throws SAXException {
            this.ensureInitialization();
            this.saxHandler.startEntity(name);
        }
        
        public void endEntity(final String name) throws SAXException {
            this.saxHandler.endEntity(name);
        }
        
        public void startCDATA() throws SAXException {
            this.ensureInitialization();
            this.saxHandler.startCDATA();
        }
        
        public void endCDATA() throws SAXException {
            this.saxHandler.endCDATA();
        }
        
        public void comment(final char[] ch, final int start, final int length) throws SAXException {
            this.ensureInitialization();
            this.saxHandler.comment(ch, start, length);
        }
    }
}
