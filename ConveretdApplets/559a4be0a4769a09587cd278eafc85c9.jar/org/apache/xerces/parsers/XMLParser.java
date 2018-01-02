// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class XMLParser
{
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String[] RECOGNIZED_PROPERTIES;
    protected XMLParserConfiguration fConfiguration;
    
    protected XMLParser(final XMLParserConfiguration config) {
        (this.fConfiguration = config).addRecognizedProperties(XMLParser.RECOGNIZED_PROPERTIES);
    }
    
    public void parse(final XMLInputSource inputSource) throws XNIException, IOException {
        this.reset();
        this.fConfiguration.parse(inputSource);
    }
    
    protected void reset() throws XNIException {
    }
    
    static {
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-handler" };
    }
}
