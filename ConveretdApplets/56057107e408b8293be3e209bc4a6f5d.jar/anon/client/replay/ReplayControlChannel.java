// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.replay;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.util.Vector;
import anon.util.XMLParseException;
import logging.LogHolder;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import logging.LogType;
import org.w3c.dom.Document;
import java.util.Observable;
import anon.IServiceContainer;
import anon.client.Multiplexer;
import anon.client.XmlControlChannel;

public class ReplayControlChannel extends XmlControlChannel
{
    private MessageDistributor m_messageDistributor;
    private Object m_internalSynchronization;
    
    public ReplayControlChannel(final Multiplexer multiplexer, final IServiceContainer serviceContainer) {
        super(3, multiplexer, serviceContainer, false);
        this.m_messageDistributor = new MessageDistributor();
        this.m_internalSynchronization = new Object();
    }
    
    public Observable getMessageDistributor() {
        return this.m_messageDistributor;
    }
    
    protected void processXmlMessage(final Document document) {
        try {
            LogHolder.log(7, LogType.NET, "Received a message: " + XMLUtil.toString(document));
            final Element documentElement = document.getDocumentElement();
            if (documentElement == null) {
                throw new XMLParseException("##__root__##", "No document element in received XML structure.");
            }
            if (!documentElement.getNodeName().equals("Mixes")) {
                throw new XMLParseException("##__root__##", "Mixes node expected in received XML structure.");
            }
            final Vector<ReplayTimestamp> vector = new Vector<ReplayTimestamp>();
            final NodeList elementsByTagName = documentElement.getElementsByTagName("Mix");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final Element element = (Element)elementsByTagName.item(i);
                final String attribute = XMLUtil.parseAttribute(element, "id", null);
                if (attribute == null) {
                    throw new XMLParseException("##__null__##", "XML structure of Mix " + Integer.toString(i) + " does not contain a Mix-ID.");
                }
                final NodeList elementsByTagName2 = element.getElementsByTagName("Replay");
                if (elementsByTagName2.getLength() == 0) {
                    throw new XMLParseException("##__null__##", "XML structure of Mix " + Integer.toString(i) + " does not contain a Replay node.");
                }
                final NodeList elementsByTagName3 = ((Element)elementsByTagName2.item(0)).getElementsByTagName("ReplayTimestamp");
                if (elementsByTagName3.getLength() == 0) {
                    throw new XMLParseException("##__null__##", "XML structure of Mix " + Integer.toString(i) + " does not contain a ReplayTimestamp node.");
                }
                final int attribute2 = XMLUtil.parseAttribute(elementsByTagName3.item(0), "offset", -1);
                if (attribute2 == -1) {
                    throw new XMLParseException("##__null__##", "XML structure of Mix " + Integer.toString(i) + " does not contain a valid ReplayTimestamp offset.");
                }
                final int attribute3 = XMLUtil.parseAttribute(elementsByTagName3.item(0), "interval", -1);
                if (attribute3 == -1) {
                    throw new XMLParseException("##__null__##", "XML structure of Mix " + Integer.toString(i) + " does not contain a valid ReplayTimestamp interval.");
                }
                vector.addElement(new ReplayTimestamp(attribute, attribute3, attribute2));
            }
            this.m_messageDistributor.publishTimestamps(vector);
        }
        catch (Exception ex) {
            this.getServiceContainer().keepCurrentService(false);
            LogHolder.log(3, LogType.NET, ex);
            this.m_messageDistributor.publishException(ex);
        }
    }
    
    public void requestTimestamps() {
        try {
            final Document document = XMLUtil.createDocument();
            if (document == null) {
                throw new Exception("ReplayControlChannel: requestTimestamps(): Cannot create XML document for request.");
            }
            document.appendChild(document.createElement("GetTimestamps"));
            int sendXmlMessage = 0;
            synchronized (this.m_internalSynchronization) {
                sendXmlMessage = this.sendXmlMessage(document);
            }
            if (sendXmlMessage != 0) {
                throw new Exception("ReplayControlChannel: requestTimestamps(): Errorcode '" + Integer.toString(sendXmlMessage) + "' while sending request.");
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.NET, ex);
            this.m_messageDistributor.publishException(ex);
        }
    }
    
    private class MessageDistributor extends Observable
    {
        public void publishTimestamps(final Vector vector) {
            this.publishObject(vector);
        }
        
        public void publishException(final Exception ex) {
            this.publishObject(ex);
        }
        
        private void publishObject(final Object o) {
            synchronized (this) {
                this.setChanged();
                this.notifyObservers(o);
            }
        }
    }
}
