// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import java.util.HashMap;
import org.jfree.util.DefaultConfiguration;
import java.util.Stack;
import org.jfree.util.Configuration;
import org.xml.sax.helpers.DefaultHandler;

public abstract class Parser extends DefaultHandler implements Configuration
{
    public static final String CONTENTBASE_KEY = "content-base";
    private Stack activeFactories;
    private ElementDefinitionHandler initialFactory;
    private DefaultConfiguration parserConfiguration;
    private HashMap parserHelperObjects;
    private Locator locator;
    private final CommentHandler commentHandler;
    
    public Parser() {
        this.activeFactories = new Stack();
        this.parserConfiguration = new DefaultConfiguration();
        this.parserHelperObjects = new HashMap();
        this.commentHandler = new CommentHandler();
    }
    
    public CommentHandler getCommentHandler() {
        return this.commentHandler;
    }
    
    public String[] getComments() {
        return this.getCommentHandler().getComments();
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
    }
    
    public Locator getLocator() {
        return this.locator;
    }
    
    public void pushFactory(final ElementDefinitionHandler elementDefinitionHandler) {
        this.activeFactories.push(elementDefinitionHandler);
    }
    
    public ElementDefinitionHandler peekFactory() {
        return this.activeFactories.peek();
    }
    
    public ElementDefinitionHandler popFactory() {
        this.activeFactories.pop();
        return this.peekFactory();
    }
    
    public void endDocument() throws SAXException {
    }
    
    public void startDocument() throws SAXException {
        this.activeFactories.clear();
        this.pushFactory(this.getInitialFactory());
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        try {
            this.peekFactory().characters(array, n, n2);
        }
        catch (ParseException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ParseException(ex2, this.getLocator());
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        try {
            this.peekFactory().endElement(s3);
        }
        catch (ParseException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ParseException(ex2, this.getLocator());
        }
        finally {
            this.getCommentHandler().clearComments();
        }
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        try {
            this.peekFactory().startElement(s3, attributes);
        }
        catch (ParseException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ParseException(ex2, this.getLocator());
        }
        finally {
            this.getCommentHandler().clearComments();
        }
    }
    
    public void setInitialFactory(final ElementDefinitionHandler initialFactory) {
        this.initialFactory = initialFactory;
    }
    
    public ElementDefinitionHandler getInitialFactory() {
        return this.initialFactory;
    }
    
    public String getConfigProperty(final String s) {
        return this.getConfigProperty(s, null);
    }
    
    public String getConfigProperty(final String s, final String s2) {
        return this.parserConfiguration.getConfigProperty(s, s2);
    }
    
    public void setConfigProperty(final String s, final String s2) {
        if (s2 == null) {
            this.parserConfiguration.remove(s);
        }
        else {
            this.parserConfiguration.setProperty(s, s2);
        }
    }
    
    public void setHelperObject(final String s, final Object o) {
        if (o == null) {
            this.parserHelperObjects.remove(s);
        }
        else {
            this.parserHelperObjects.put(s, o);
        }
    }
    
    public Object getHelperObject(final String s) {
        return this.parserHelperObjects.get(s);
    }
    
    public abstract Parser getInstance();
    
    public abstract Object getResult();
}
