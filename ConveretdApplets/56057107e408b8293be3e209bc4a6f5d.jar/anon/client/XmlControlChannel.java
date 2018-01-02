// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.util.XMLParseException;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.IServiceContainer;

public abstract class XmlControlChannel extends StreamedControlChannel
{
    public XmlControlChannel(final int n, final Multiplexer multiplexer, final IServiceContainer serviceContainer, final boolean b) {
        super(n, multiplexer, serviceContainer, b);
    }
    
    public int sendXmlMessage(final Document document) {
        return this.sendByteMessage(XMLUtil.toByteArray(document));
    }
    
    protected void processMessage(final byte[] array) {
        try {
            this.processXmlMessage(XMLUtil.toXMLDocument(array));
        }
        catch (XMLParseException ex) {
            LogHolder.log(3, LogType.NET, "Error while parsing XML document!", ex);
        }
    }
    
    protected abstract void processXmlMessage(final Document p0);
}
