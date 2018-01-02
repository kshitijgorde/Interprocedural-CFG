// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import java.io.InputStream;
import java.io.BufferedInputStream;
import org.xml.sax.XMLReader;
import java.io.IOException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import java.net.URL;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.jfree.util.Log;
import javax.xml.parsers.SAXParser;
import org.xml.sax.EntityResolver;
import javax.xml.parsers.SAXParserFactory;

public class ParserFrontend
{
    private Parser defaulthandler;
    private SAXParserFactory factory;
    private EntityResolver entityResolver;
    private boolean validateDTD;
    
    protected ParserFrontend(final Parser defaulthandler) {
        if (defaulthandler == null) {
            throw new NullPointerException();
        }
        this.defaulthandler = defaulthandler;
    }
    
    public boolean isValidateDTD() {
        return this.validateDTD;
    }
    
    public void setValidateDTD(final boolean validateDTD) {
        this.validateDTD = validateDTD;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }
    
    protected SAXParser getParser() throws ParserConfigurationException, SAXException {
        if (this.factory == null) {
            this.factory = SAXParserFactory.newInstance();
            if (this.isValidateDTD()) {
                try {
                    this.factory.setValidating(true);
                }
                catch (Exception ex) {
                    Log.debug("The parser will not validate the xml document.", ex);
                }
            }
        }
        return this.factory.newSAXParser();
    }
    
    public void setDefaultHandler(final Parser defaulthandler) {
        if (defaulthandler == null) {
            throw new NullPointerException();
        }
        this.defaulthandler = defaulthandler;
    }
    
    public Parser getDefaultHandler() {
        return this.defaulthandler;
    }
    
    protected Parser createDefaultHandler(final URL url) {
        final Parser instance = this.getDefaultHandler().getInstance();
        if (url != null) {
            instance.setConfigProperty("content-base", url.toExternalForm());
        }
        return instance;
    }
    
    protected Object parse(final InputSource inputSource, final URL url) throws ElementDefinitionException {
        try {
            final XMLReader xmlReader = this.getParser().getXMLReader();
            try {
                xmlReader.setFeature("http://xml.org/sax/features/validation", this.isValidateDTD());
            }
            catch (SAXException ex) {
                Log.debug("The XMLReader will not validate the xml document.", ex);
            }
            final Parser defaultHandler = this.createDefaultHandler(url);
            this.configureReader(xmlReader, defaultHandler);
            try {
                xmlReader.setContentHandler(defaultHandler);
                xmlReader.setDTDHandler(defaultHandler);
                xmlReader.setEntityResolver(this.getEntityResolver());
                xmlReader.setErrorHandler(defaultHandler);
                xmlReader.parse(inputSource);
                return defaultHandler.getResult();
            }
            catch (IOException ex2) {
                throw new ElementDefinitionException(ex2);
            }
        }
        catch (ParserConfigurationException ex3) {
            throw new ElementDefinitionException(ex3);
        }
        catch (SAXException ex4) {
            throw new ElementDefinitionException(ex4);
        }
    }
    
    protected void configureReader(final XMLReader xmlReader, final Parser parser) {
        try {
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", parser.getCommentHandler());
        }
        catch (SAXException ex) {
            Log.debug("Comments are not supported by this SAX implementation.");
        }
    }
    
    public Object parse(final URL url, final URL url2) throws ElementDefinitionException, IOException {
        if (url == null) {
            throw new NullPointerException("File may not be null");
        }
        final BufferedInputStream byteStream = new BufferedInputStream(url.openStream());
        final InputSource inputSource = new InputSource(byteStream);
        inputSource.setSystemId(url.toString());
        final Object parse = this.parse(inputSource, url2);
        byteStream.close();
        return parse;
    }
}
