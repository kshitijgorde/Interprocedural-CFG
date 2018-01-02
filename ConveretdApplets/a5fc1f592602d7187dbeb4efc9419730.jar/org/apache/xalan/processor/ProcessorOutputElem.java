// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.util.Hashtable;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.SystemIDResolver;
import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.SourceLocator;
import org.xml.sax.Attributes;
import org.apache.xml.utils.QName;
import java.util.Vector;
import org.apache.xalan.templates.OutputProperties;

class ProcessorOutputElem extends XSLTElementProcessor
{
    private OutputProperties m_outputProperties;
    
    public void setCdataSectionElements(final Vector newValue) {
        this.m_outputProperties.setQNameProperties("cdata-section-elements", newValue);
    }
    
    public void setDoctypePublic(final String newValue) {
        this.m_outputProperties.setProperty("doctype-public", newValue);
    }
    
    public void setDoctypeSystem(final String newValue) {
        this.m_outputProperties.setProperty("doctype-system", newValue);
    }
    
    public void setEncoding(final String newValue) {
        this.m_outputProperties.setProperty("encoding", newValue);
    }
    
    public void setIndent(final boolean newValue) {
        this.m_outputProperties.setBooleanProperty("indent", newValue);
    }
    
    public void setMediaType(final String newValue) {
        this.m_outputProperties.setProperty("media-type", newValue);
    }
    
    public void setMethod(final QName newValue) {
        this.m_outputProperties.setQNameProperty("method", newValue);
    }
    
    public void setOmitXmlDeclaration(final boolean newValue) {
        this.m_outputProperties.setBooleanProperty("omit-xml-declaration", newValue);
    }
    
    public void setStandalone(final boolean newValue) {
        this.m_outputProperties.setBooleanProperty("standalone", newValue);
    }
    
    public void setVersion(final String newValue) {
        this.m_outputProperties.setProperty("version", newValue);
    }
    
    public void setForeignAttr(final String attrUri, final String attrLocalName, final String attrRawName, final String attrValue) {
        final QName key = new QName(attrUri, attrLocalName);
        this.m_outputProperties.setProperty(key, attrValue);
    }
    
    public void addLiteralResultAttribute(final String attrUri, final String attrLocalName, final String attrRawName, final String attrValue) {
        final QName key = new QName(attrUri, attrLocalName);
        this.m_outputProperties.setProperty(key, attrValue);
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        (this.m_outputProperties = new OutputProperties()).setDOMBackPointer(handler.getOriginatingNode());
        this.m_outputProperties.setLocaterInfo(handler.getLocator());
        this.m_outputProperties.setUid(handler.nextUid());
        this.setPropertiesFromAttributes(handler, rawName, attributes, this);
        final String entitiesFileName = ((Hashtable<K, String>)this.m_outputProperties.getProperties()).get("{http://xml.apache.org/xalan}entities");
        if (null != entitiesFileName) {
            try {
                final String absURL = SystemIDResolver.getAbsoluteURI(entitiesFileName, handler.getBaseIdentifier());
                ((Hashtable<String, String>)this.m_outputProperties.getProperties()).put("{http://xml.apache.org/xalan}entities", absURL);
            }
            catch (TransformerException te) {
                handler.error(te.getMessage(), te);
            }
        }
        handler.getStylesheet().setOutput(this.m_outputProperties);
        final ElemTemplateElement parent = handler.getElemTemplateElement();
        parent.appendChild(this.m_outputProperties);
        this.m_outputProperties = null;
    }
}
