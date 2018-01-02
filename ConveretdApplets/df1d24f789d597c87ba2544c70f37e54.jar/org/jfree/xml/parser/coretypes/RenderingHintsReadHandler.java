// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser.coretypes;

import java.util.Map;
import org.jfree.xml.parser.XmlReaderException;
import org.jfree.xml.parser.XmlReadHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import java.awt.RenderingHints;
import java.util.ArrayList;
import org.jfree.xml.parser.AbstractXmlReadHandler;

public class RenderingHintsReadHandler extends AbstractXmlReadHandler
{
    private ArrayList handlers;
    private RenderingHints renderingHints;
    
    protected void startParsing(final Attributes attributes) throws SAXException {
        this.handlers = new ArrayList();
    }
    
    protected XmlReadHandler getHandlerForChild(final String s, final Attributes attributes) throws XmlReaderException, SAXException {
        if (!s.equals("entry")) {
            throw new SAXException("Expected 'entry' tag.");
        }
        final RenderingHintValueReadHandler renderingHintValueReadHandler = new RenderingHintValueReadHandler();
        this.handlers.add(renderingHintValueReadHandler);
        return renderingHintValueReadHandler;
    }
    
    protected void doneParsing() throws SAXException, XmlReaderException {
        this.renderingHints = new RenderingHints(null);
        for (int i = 0; i < this.handlers.size(); ++i) {
            final RenderingHintValueReadHandler renderingHintValueReadHandler = this.handlers.get(i);
            this.renderingHints.put(renderingHintValueReadHandler.getKey(), renderingHintValueReadHandler.getValue());
        }
    }
    
    public Object getObject() throws XmlReaderException {
        return this.renderingHints;
    }
}
