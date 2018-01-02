// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.validation;

import java.net.URL;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import org.xml.sax.SAXException;
import javax.xml.transform.Source;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;

public abstract class SchemaFactory
{
    private static final String DEFAULT_SCHEMA_FACTORY_W3C = "org.apache.xerces.jaxp.validation.XMLSchemaFactory";
    private static final String PROPERTY_NAME = "javax.xml.validation.SchemaFactory";
    
    public static final SchemaFactory newInstance(final String s) {
        if (s == null) {
            throw new NullPointerException("schemaLanguage should not be null in SchemaFactory.newInstance(String schemaLanguage)");
        }
        return (SchemaFactory)SchemaFactoryFinder.find("javax.xml.validation.SchemaFactory", s, "org.apache.xerces.jaxp.validation.XMLSchemaFactory");
    }
    
    public abstract boolean isSchemaLanguageSupported(final String p0);
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        throw new SAXNotRecognizedException(s);
    }
    
    public abstract void setErrorHandler(final ErrorHandler p0);
    
    public abstract ErrorHandler getErrorHandler();
    
    public abstract void setResourceResolver(final LSResourceResolver p0);
    
    public abstract LSResourceResolver getResourceResolver();
    
    public abstract Schema newSchema(final Source[] p0) throws SAXException;
    
    public abstract Schema newSchema() throws SAXException;
    
    public Schema newSchema(final Source source) throws SAXException {
        if (source == null) {
            throw new NullPointerException();
        }
        return this.newSchema(new Source[] { source });
    }
    
    public Schema newSchema(final File file) throws SAXException {
        if (file == null) {
            throw new NullPointerException();
        }
        return this.newSchema(new StreamSource(file));
    }
    
    public Schema newSchema(final URL url) throws SAXException {
        if (url == null) {
            throw new NullPointerException();
        }
        return this.newSchema(new StreamSource(url.toString()));
    }
}
