// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import java.io.OutputStream;
import java.io.DataOutputStream;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import anon.infoservice.ListenerInterface;
import forward.ForwardUtils;
import anon.infoservice.MixCascade;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

public class DefaultProtocolHandler implements IProtocolHandler
{
    private static final int PROTOCOL_VERSION = 2;
    private static final int MAXIMUM_PROTOCOLMESSAGE_SIZE = 100000;
    private static final byte[] MESSAGE_START_SIGNATURE;
    private static final byte[] MESSAGE_END_SIGNATURE;
    private static final int STATE_WAIT_FOR_CLIENT_REQUEST = 0;
    private static final int STATE_WAIT_FOR_CASCADE_SELECTION = 1;
    private static final int STATE_CONNECTED_TO_MIX = 2;
    private static final int STATE_CONNECTION_CLOSED = 3;
    private static final int STATE_WAIT_FOR_INFOSERVICE_CLOSE = 4;
    private Socket m_serverConnection;
    private ByteArrayOutputStream m_incomingMessageBuffer;
    private int m_incomingMessageLength;
    private ByteArrayInputStream m_outgoingMessageBuffer;
    private int m_currentState;
    private ForwardConnection m_parentConnection;
    
    public DefaultProtocolHandler(final ForwardConnection parentConnection) throws Exception {
        this.m_incomingMessageBuffer = new ByteArrayOutputStream();
        this.m_incomingMessageLength = -1;
        this.m_outgoingMessageBuffer = new ByteArrayInputStream(new byte[0]);
        this.m_parentConnection = parentConnection;
        this.m_serverConnection = null;
        this.m_currentState = 0;
    }
    
    public int available() throws Exception {
        int n = 0;
        if (this.m_serverConnection != null) {
            n = this.m_serverConnection.getInputStream().available();
        }
        else {
            synchronized (this.m_outgoingMessageBuffer) {
                n = this.m_outgoingMessageBuffer.available();
            }
        }
        return n;
    }
    
    public int read(final byte[] array) throws Exception {
        int n = 0;
        if (this.m_serverConnection != null) {
            n = this.m_serverConnection.getInputStream().read(array);
        }
        else {
            synchronized (this.m_outgoingMessageBuffer) {
                n = this.m_outgoingMessageBuffer.read(array);
            }
            if (n == -1) {
                n = 0;
            }
        }
        return n;
    }
    
    public void write(final byte[] array) throws Exception {
        if (this.m_serverConnection != null) {
            this.m_serverConnection.getOutputStream().write(array);
            this.m_serverConnection.getOutputStream().flush();
        }
        else {
            this.messageHandler(array);
        }
    }
    
    public void close() {
        if (this.m_serverConnection != null) {
            try {
                this.m_serverConnection.close();
            }
            catch (Exception ex) {}
            this.m_serverConnection = null;
        }
        this.m_incomingMessageBuffer = null;
        this.m_outgoingMessageBuffer = null;
        this.m_currentState = 3;
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
    
    private void messageHandler(final byte[] array) throws Exception {
        if (this.m_incomingMessageBuffer.size() < DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4) {
            if (this.m_incomingMessageBuffer.size() < DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length) {
                this.m_incomingMessageBuffer.write(array);
                if (this.m_incomingMessageBuffer.size() >= DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length) {
                    final byte[] array2 = new byte[DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length];
                    System.arraycopy(this.m_incomingMessageBuffer.toByteArray(), 0, array2, 0, DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length);
                    if (!this.checkSignature(array2, DefaultProtocolHandler.MESSAGE_START_SIGNATURE)) {
                        throw new Exception("DefaultProtocolHandler: messageHandler: Protocol error (invalid start signature).");
                    }
                }
            }
            else {
                this.m_incomingMessageBuffer.write(array);
            }
            if (this.m_incomingMessageBuffer.size() >= DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4) {
                final byte[] array3 = new byte[4];
                System.arraycopy(this.m_incomingMessageBuffer.toByteArray(), DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length, array3, 0, 4);
                try {
                    this.m_incomingMessageLength = new DataInputStream(new ByteArrayInputStream(array3)).readInt();
                }
                catch (Exception ex) {
                    throw new Exception("DefaultProtocolHandler: messageHandler: Error while reading message length.");
                }
                if (this.m_incomingMessageLength < 0 || this.m_incomingMessageLength > 100000) {
                    this.m_incomingMessageLength = -1;
                    throw new Exception("DefaultProtocolHandler: messageHandler: Protocol error (invalid length).");
                }
            }
        }
        else {
            this.m_incomingMessageBuffer.write(array);
        }
        if (this.m_incomingMessageLength != -1 && this.m_incomingMessageBuffer.size() >= DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + this.m_incomingMessageLength + DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length) {
            final byte[] array4 = new byte[DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length];
            System.arraycopy(this.m_incomingMessageBuffer.toByteArray(), DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + this.m_incomingMessageLength, array4, 0, DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length);
            if (!this.checkSignature(array4, DefaultProtocolHandler.MESSAGE_END_SIGNATURE)) {
                throw new Exception("DefaultProtocolHandler: messageHandler: Protocol error (invalid end signature).");
            }
            final byte[] array5 = new byte[this.m_incomingMessageLength];
            System.arraycopy(this.m_incomingMessageBuffer.toByteArray(), DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4, array5, 0, this.m_incomingMessageLength);
            final byte[] array6 = new byte[this.m_incomingMessageBuffer.size() - DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length - 4 - this.m_incomingMessageLength - DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length];
            System.arraycopy(this.m_incomingMessageBuffer.toByteArray(), DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + this.m_incomingMessageLength + DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length, array6, 0, array6.length);
            this.m_incomingMessageBuffer.reset();
            this.m_incomingMessageLength = -1;
            this.m_incomingMessageBuffer.write(array6);
            this.messageReceived(array5);
            if (this.m_incomingMessageBuffer.size() > 0) {
                this.m_incomingMessageBuffer.reset();
                this.messageHandler(array6);
            }
        }
    }
    
    private void messageReceived(final byte[] array) throws Exception {
        final NodeList elementsByTagName = XMLUtil.toXMLDocument(array).getElementsByTagName("JAPRouting");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("DefaultProtocolHandler: messageReceived: Error in XML structure (JAPRouting node).");
        }
        this.handleProtocol((Element)elementsByTagName.item(0));
    }
    
    private void handleProtocol(final Element element) throws Exception {
        switch (this.m_currentState) {
            case 0: {
                this.handleInitialRequestMessage(element);
                break;
            }
            case 1: {
                this.handleClientCascadeSelectMessage(element);
                break;
            }
            default: {
                throw new Exception("DefaultProtocolHandler: handleProtocol: Protocol error.");
            }
        }
    }
    
    private void handleInitialRequestMessage(final Element element) throws Exception {
        final NodeList elementsByTagName = element.getElementsByTagName("Request");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("DefaultProtocolHandler: handleInitialRequestMessage: Error in XML structure (Request node).");
        }
        final Element element2 = (Element)elementsByTagName.item(0);
        if (!element2.getAttribute("subject").equals("connection")) {
            throw new Exception("DefaultProtocolHandler: handleInitialRequestMessage: Error in XML structure (Request node, wrong subject).");
        }
        final String attribute = element2.getAttribute("msg");
        if (attribute.equals("request")) {
            this.m_currentState = 1;
            this.sendProtocolDataToClient(this.xmlToProtocolPacket(this.generateConnectionOfferXml()));
        }
        else {
            if (!attribute.equals("verify")) {
                throw new Exception("DefaultProtocolHandler: handleInitialRequestMessage: Error in XML structure (Request node, wrong msg).");
            }
            this.m_currentState = 4;
            this.sendProtocolDataToClient(this.xmlToProtocolPacket(this.generateConnectionAcknowledgement()));
        }
    }
    
    private void handleClientCascadeSelectMessage(final Element element) throws Exception {
        final NodeList elementsByTagName = element.getElementsByTagName("Request");
        if (elementsByTagName.getLength() == 0) {
            throw new Exception("DefaultProtocolHandler: handleClientCascadeSelectMessage: Error in XML structure (Request node).");
        }
        final Element element2 = (Element)elementsByTagName.item(0);
        if (!element2.getAttribute("subject").equals("cascade")) {
            throw new Exception("DefaultProtocolHandler: handleClientCascadeSelectMessage: Error in XML structure (Request node, wrong subject).");
        }
        if (!element2.getAttribute("msg").equals("select")) {
            throw new Exception("DefaultProtocolHandler: handleClientCascadeSelectMessage: Error in XML structure (Request node, wrong msg).");
        }
        final NodeList elementsByTagName2 = element2.getElementsByTagName("MixCascade");
        if (elementsByTagName2.getLength() == 0) {
            throw new Exception("DefaultProtocolHandler: handleClientCascadeSelectMessage: Error in XML structure (MixCascade node).");
        }
        final MixCascade mixCascadeById = ForwardServerManager.getInstance().getAllowedCascadesDatabase().getMixCascadeById(((Element)elementsByTagName2.item(0)).getAttribute("id"));
        if (mixCascadeById == null) {
            throw new Exception("DefaultProtocolHandler: handleClientCascadeSelectMessage: Selected cascade not available.");
        }
        if (this.connectTo(mixCascadeById)) {
            this.emptyBuffers();
            this.m_currentState = 2;
            return;
        }
        this.close();
        throw new Exception("DefaultProtocolHandler: handleClientCascadeSelectMessage: Error connecting the selected cascade.");
    }
    
    private boolean connectTo(final MixCascade mixCascade) {
        for (int n = 0; n < mixCascade.getNumberOfListenerInterfaces() && this.m_serverConnection == null; ++n) {
            final ListenerInterface listenerInterface = mixCascade.getListenerInterface(n);
            try {
                (this.m_serverConnection = ForwardUtils.getInstance().createConnection(listenerInterface.getHost(), listenerInterface.getPort())).setSoTimeout(0);
            }
            catch (Exception ex) {
                this.m_serverConnection = null;
            }
        }
        return this.m_serverConnection != null;
    }
    
    private void emptyBuffers() throws Exception {
        this.m_serverConnection.getOutputStream().write(this.m_incomingMessageBuffer.toByteArray());
    }
    
    private Document generateConnectionOfferXml() throws Exception {
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement("JAPRouting");
        final Element element2 = document.createElement("Protocol");
        element2.setAttribute("version", Integer.toString(2));
        element.appendChild(element2);
        final Element element3 = document.createElement("Request");
        element3.setAttribute("subject", "connection");
        element3.setAttribute("msg", "offer");
        element3.appendChild(ForwardServerManager.getInstance().getAllowedCascadesDatabase().toXmlNode(document));
        final Element element4 = document.createElement("QualityOfService");
        final Element element5 = document.createElement("MaximumBandwidth");
        element5.appendChild(document.createTextNode(Integer.toString(this.m_parentConnection.getParentScheduler().getMaximumBandwidth())));
        element4.appendChild(element5);
        final Element element6 = document.createElement("GuaranteedBandwidth");
        element6.appendChild(document.createTextNode(Integer.toString(this.m_parentConnection.getParentScheduler().getGuaranteedBandwidth())));
        element4.appendChild(element6);
        element3.appendChild(element4);
        final Element element7 = document.createElement("DummyTraffic");
        element7.setAttribute("interval", Integer.toString(ForwardServerManager.getInstance().getDummyTrafficInterval()));
        element3.appendChild(element7);
        element.appendChild(element3);
        document.appendChild(element);
        return document;
    }
    
    private Document generateConnectionAcknowledgement() throws Exception {
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement("JAPRouting");
        final Element element2 = document.createElement("Request");
        element2.setAttribute("subject", "connection");
        element2.setAttribute("msg", "acknowledge");
        element.appendChild(element2);
        document.appendChild(element);
        return document;
    }
    
    private void sendProtocolDataToClient(final byte[] array) {
        synchronized (this.m_outgoingMessageBuffer) {
            final byte[] array2 = new byte[this.m_outgoingMessageBuffer.available() + array.length];
            this.m_outgoingMessageBuffer.read(array2, 0, this.m_outgoingMessageBuffer.available());
            System.arraycopy(array, 0, array2, array2.length - array.length, array.length);
            this.m_outgoingMessageBuffer = new ByteArrayInputStream(array2);
        }
    }
    
    private byte[] xmlToProtocolPacket(final Document document) throws Exception {
        return this.createProtocolPacket(XMLUtil.toByteArray(document));
    }
    
    private byte[] createProtocolPacket(final byte[] array) {
        final byte[] array2 = new byte[DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + array.length + DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length];
        System.arraycopy(DefaultProtocolHandler.MESSAGE_START_SIGNATURE, 0, array2, 0, DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
        try {
            new DataOutputStream(byteArrayOutputStream).writeInt(array.length);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, array2, DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length, 4);
        }
        catch (Exception ex) {
            System.arraycopy(new byte[] { -1, -1, -1, -1 }, 0, array2, DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length, 4);
        }
        System.arraycopy(array, 0, array2, DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4, array.length);
        System.arraycopy(DefaultProtocolHandler.MESSAGE_END_SIGNATURE, 0, array2, DefaultProtocolHandler.MESSAGE_START_SIGNATURE.length + 4 + array.length, DefaultProtocolHandler.MESSAGE_END_SIGNATURE.length);
        return array2;
    }
    
    static {
        MESSAGE_START_SIGNATURE = new byte[] { -1, 0, -16, 15 };
        MESSAGE_END_SIGNATURE = new byte[] { -1, 0, -31, 30 };
    }
}
