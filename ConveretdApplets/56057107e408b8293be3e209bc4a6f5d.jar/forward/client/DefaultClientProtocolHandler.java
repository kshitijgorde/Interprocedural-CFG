// 
// Decompiled by Procyon v0.5.30
// 

package forward.client;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import anon.util.XMLParseException;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import anon.infoservice.MixCascade;
import anon.transport.connection.IStreamConnection;

public class DefaultClientProtocolHandler
{
    private static final int PROTOCOL_VERSION = 2;
    private static final int MAXIMUM_PROTOCOLMESSAGE_SIZE = 1000000;
    private static final int STATE_INITIALIZE = 0;
    private static final int STATE_OFFER_RECEIVED = 1;
    private static final int STATE_CASCADE_SELECTED = 2;
    private static final int STATE_FORWARDING = 3;
    private static final int STATE_CLOSED_AFTER_ERROR = 4;
    private static final byte[] MESSAGE_START_SIGNATURE;
    private static final byte[] MESSAGE_END_SIGNATURE;
    private IStreamConnection m_connection;
    private int m_state;
    private int m_minDummyTrafficInterval;
    private MixCascade m_selectedMixCascade;
    
    public DefaultClientProtocolHandler(final IStreamConnection connection) {
        this.m_connection = connection;
        this.m_state = 0;
    }
    
    public ForwardConnectionDescriptor getConnectionDescriptor() throws ClientForwardException {
        final ForwardConnectionDescriptor forwardConnectionDescriptor = new ForwardConnectionDescriptor();
        if (this.m_state != 0) {
            throw new ClientForwardException(2, "Wrong protocol state to call this method (current state: " + Integer.toString(this.m_state) + ").");
        }
        byte[] xmlToProtocolPacket;
        try {
            xmlToProtocolPacket = this.xmlToProtocolPacket(this.generateConnectionRequest());
        }
        catch (Exception ex) {
            throw new ClientForwardException(255, "XML transforming error (" + ex.toString() + ").");
        }
        this.sendProtocolMessage(xmlToProtocolPacket);
        final byte[] protocolMessage = this.readProtocolMessage();
        Document xmlDocument;
        try {
            xmlDocument = XMLUtil.toXMLDocument(protocolMessage);
        }
        catch (Exception ex2) {
            throw new ClientForwardException(255, "Error while parsing XML message (" + ex2.toString() + ").");
        }
        final NodeList elementsByTagName = xmlDocument.getElementsByTagName("JAPRouting");
        if (elementsByTagName.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (JAPRouting node).");
        }
        final Element element = (Element)elementsByTagName.item(0);
        final NodeList elementsByTagName2 = element.getElementsByTagName("Protocol");
        if (elementsByTagName2.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (Protocol node).");
        }
        final Element element2 = (Element)elementsByTagName2.item(0);
        int int1;
        try {
            int1 = Integer.parseInt(element2.getAttribute("version"));
        }
        catch (Exception ex4) {
            throw new ClientForwardException(255, "Error in XML structure (Protocol node -> version info).");
        }
        if (int1 != 2) {
            throw new ClientForwardException(3, "Forwarder is using protocol version " + Integer.toString(int1) + ", but we use version " + Integer.toString(2) + ".");
        }
        final NodeList elementsByTagName3 = element.getElementsByTagName("Request");
        if (elementsByTagName3.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (Request node).");
        }
        final Element element3 = (Element)elementsByTagName3.item(0);
        if (!element3.getAttribute("subject").equals("connection")) {
            throw new ClientForwardException(255, "Error in XML structure (Request node -> subject).");
        }
        if (!element3.getAttribute("msg").equals("offer")) {
            throw new ClientForwardException(255, "Error in XML structure (Request node -> msg).");
        }
        final NodeList elementsByTagName4 = element3.getElementsByTagName("AllowedCascades");
        if (elementsByTagName4.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (AllowedCascades node).");
        }
        final NodeList elementsByTagName5 = ((Element)elementsByTagName4.item(0)).getElementsByTagName("MixCascade");
        for (int i = 0; i < elementsByTagName5.getLength(); ++i) {
            final Element element4 = (Element)elementsByTagName5.item(i);
            try {
                final MixCascade mixCascade = new MixCascade(element4);
                if (mixCascade.isVerified()) {
                    forwardConnectionDescriptor.addMixCascade(mixCascade);
                }
                else {
                    LogHolder.log(3, LogType.MISC, "Signature check for a MixCascade failed.");
                }
            }
            catch (XMLParseException ex3) {
                LogHolder.log(3, LogType.MISC, "Error while parsing MixCascade", ex3);
            }
        }
        final NodeList elementsByTagName6 = element3.getElementsByTagName("QualityOfService");
        if (elementsByTagName6.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (QualityOfService node).");
        }
        final Element element5 = (Element)elementsByTagName6.item(0);
        final NodeList elementsByTagName7 = element5.getElementsByTagName("MaximumBandwidth");
        if (elementsByTagName7.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (MaximumBandwidth node).");
        }
        final Element element6 = (Element)elementsByTagName7.item(0);
        int int2 = -1;
        try {
            int2 = Integer.parseInt(element6.getFirstChild().getNodeValue());
        }
        catch (Exception ex5) {}
        if (int2 < 0) {
            throw new ClientForwardException(255, "Error in XML structure (MaximumBandwidth has illegal value).");
        }
        forwardConnectionDescriptor.setMaximumBandwidth(int2);
        final NodeList elementsByTagName8 = element5.getElementsByTagName("GuaranteedBandwidth");
        if (elementsByTagName8.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (GuaranteedBandwidth node).");
        }
        final Element element7 = (Element)elementsByTagName8.item(0);
        int int3 = -1;
        try {
            int3 = Integer.parseInt(element7.getFirstChild().getNodeValue());
        }
        catch (Exception ex6) {}
        if (int3 < 0) {
            throw new ClientForwardException(255, "Error in XML structure (GuaranteedBandwidth has illegal value).");
        }
        forwardConnectionDescriptor.setGuaranteedBandwidth(int3);
        final NodeList elementsByTagName9 = element.getElementsByTagName("DummyTraffic");
        if (elementsByTagName9.getLength() == 0) {
            throw new ClientForwardException(255, "Error in XML structure (DummyTraffic node).");
        }
        final Element element8 = (Element)elementsByTagName9.item(0);
        try {
            this.m_minDummyTrafficInterval = Integer.parseInt(element8.getAttribute("interval"));
            if (this.m_minDummyTrafficInterval < -1) {
                throw new Exception("Illegal value.");
            }
        }
        catch (Exception ex7) {
            throw new ClientForwardException(255, "Error in XML structure (DummyTraffic node -> interval info).");
        }
        forwardConnectionDescriptor.setMinDummyTrafficInterval(this.m_minDummyTrafficInterval);
        this.m_state = 1;
        return forwardConnectionDescriptor;
    }
    
    public void selectMixCascade(final MixCascade mixCascade) throws ClientForwardException {
        if (this.m_state == 1) {
            Document document;
            try {
                document = XMLUtil.createDocument();
            }
            catch (Exception ex) {
                throw new ClientForwardException(255, "XML DocumentBuilder error (" + ex.toString() + ").");
            }
            final Element element = document.createElement("JAPRouting");
            final Element element2 = document.createElement("Request");
            element2.setAttribute("subject", "cascade");
            element2.setAttribute("msg", "select");
            final Element element3 = document.createElement("MixCascade");
            element3.setAttribute("id", mixCascade.getId());
            element2.appendChild(element3);
            element.appendChild(element2);
            document.appendChild(element);
            byte[] xmlToProtocolPacket;
            try {
                xmlToProtocolPacket = this.xmlToProtocolPacket(document);
            }
            catch (Exception ex2) {
                throw new ClientForwardException(255, "XML transforming error (" + ex2.toString() + ").");
            }
            this.sendProtocolMessage(xmlToProtocolPacket);
            this.m_state = 2;
            return;
        }
        throw new ClientForwardException(2, "Wrong protocol state to call this method (current state: " + Integer.toString(this.m_state) + ").");
    }
    
    private Document generateConnectionRequest() throws ClientForwardException {
        Document document;
        try {
            document = XMLUtil.createDocument();
        }
        catch (Exception ex) {
            throw new ClientForwardException(255, "XML DocumentBuilder error (" + ex.toString() + ").");
        }
        final Element element = document.createElement("JAPRouting");
        final Element element2 = document.createElement("Protocol");
        element2.setAttribute("version", Integer.toString(2));
        element.appendChild(element2);
        final Element element3 = document.createElement("Request");
        element3.setAttribute("subject", "connection");
        element3.setAttribute("msg", "request");
        element.appendChild(element3);
        document.appendChild(element);
        return document;
    }
    
    private byte[] readProtocolMessage() throws ClientForwardException {
        final byte[] array = new byte[DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length + 4];
        byte[] array6;
        try {
            int read;
            for (int i = 0; i < array.length; i += read) {
                read = this.m_connection.getInputStream().read(array, i, array.length - i);
                if (read == -1) {
                    throw new IOException("Read error: connection was closed.");
                }
            }
            final byte[] array2 = new byte[DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length];
            System.arraycopy(array, 0, array2, 0, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length);
            if (!this.checkSignature(array2, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE)) {
                throw new ClientForwardException(2, "Protocol error (invalid start signature).");
            }
            final byte[] array3 = new byte[4];
            System.arraycopy(array, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length, array3, 0, 4);
            int int1;
            try {
                int1 = new DataInputStream(new ByteArrayInputStream(array3)).readInt();
            }
            catch (Exception ex2) {
                throw new IOException("Error while reading message length.");
            }
            if (int1 < 0) {
                throw new ClientForwardException(2, "Protocol error (invalid length).");
            }
            final byte[] array4 = new byte[int1 + DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE.length];
            int read2;
            for (int j = 0; j < array4.length; j += read2) {
                read2 = this.m_connection.getInputStream().read(array4, j, array4.length - j);
                if (read2 == -1) {
                    throw new IOException("Read error: connection was closed.");
                }
            }
            final byte[] array5 = new byte[DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE.length];
            System.arraycopy(array4, int1, array5, 0, DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE.length);
            if (!this.checkSignature(array5, DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE)) {
                throw new ClientForwardException(2, "Protocol error (invalid end signature).");
            }
            array6 = new byte[int1];
            System.arraycopy(array4, 0, array6, 0, int1);
        }
        catch (IOException ex) {
            throw new ClientForwardException(1, "Connection error (" + ex.toString() + ").");
        }
        return array6;
    }
    
    private void sendProtocolMessage(final byte[] array) throws ClientForwardException {
        try {
            this.m_connection.getOutputStream().write(array);
            this.m_connection.getOutputStream().flush();
        }
        catch (IOException ex) {
            throw new ClientForwardException(1, "Connection error (" + ex.toString() + ").");
        }
    }
    
    private boolean checkSignature(final byte[] array, final byte[] array2) {
        boolean b = false;
        try {
            if (array.length == array2.length) {
                int n;
                for (n = 0, b = true; n < array.length && b; ++n) {
                    if (array[n] != array2[n]) {
                        b = false;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private byte[] xmlToProtocolPacket(final Document document) throws Exception {
        return this.createProtocolPacket(XMLUtil.toByteArray(document));
    }
    
    private byte[] createProtocolPacket(final byte[] array) {
        final byte[] array2 = new byte[DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + array.length + DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE.length];
        System.arraycopy(DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE, 0, array2, 0, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
        try {
            new DataOutputStream(byteArrayOutputStream).writeInt(array.length);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, array2, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length, 4);
        }
        catch (Exception ex) {
            System.arraycopy(new byte[] { -1, -1, -1, -1 }, 0, array2, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length, 4);
        }
        System.arraycopy(array, 0, array2, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length + 4, array.length);
        System.arraycopy(DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE, 0, array2, DefaultClientProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + array.length, DefaultClientProtocolHandler.MESSAGE_END_SIGNATURE.length);
        return array2;
    }
    
    static {
        MESSAGE_START_SIGNATURE = new byte[] { -1, 0, -16, 15 };
        MESSAGE_END_SIGNATURE = new byte[] { -1, 0, -31, 30 };
    }
}
