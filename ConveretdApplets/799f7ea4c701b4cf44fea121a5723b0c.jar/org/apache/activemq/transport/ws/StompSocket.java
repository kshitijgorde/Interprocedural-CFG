// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.ws;

import java.security.cert.X509Certificate;
import java.io.IOException;
import org.apache.activemq.command.Command;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.transport.stomp.FrameTranslator;
import org.apache.activemq.transport.stomp.LegacyFrameTranslator;
import org.apache.activemq.transport.stomp.StompWireFormat;
import org.apache.activemq.transport.stomp.ProtocolConverter;
import org.apache.activemq.transport.stomp.StompTransport;
import org.eclipse.jetty.websocket.WebSocket;
import org.apache.activemq.transport.TransportSupport;

class StompSocket extends TransportSupport implements WebSocket, StompTransport
{
    WebSocket.Outbound outbound;
    ProtocolConverter protocolConverter;
    StompWireFormat wireFormat;
    
    StompSocket() {
        this.protocolConverter = new ProtocolConverter(this, new LegacyFrameTranslator(), null);
        this.wireFormat = new StompWireFormat();
    }
    
    public void onConnect(final WebSocket.Outbound outbound) {
        this.outbound = outbound;
    }
    
    public void onMessage(final byte frame, final byte[] data, final int offset, final int length) {
    }
    
    public void onMessage(final byte frame, final String data) {
        try {
            this.protocolConverter.onStompCommand((StompFrame)this.wireFormat.unmarshal(new ByteSequence(data.getBytes("UTF-8"))));
        }
        catch (Exception e) {
            this.onException(IOExceptionSupport.create(e));
        }
    }
    
    public void onDisconnect() {
    }
    
    protected void doStart() throws Exception {
    }
    
    protected void doStop(final ServiceStopper stopper) throws Exception {
    }
    
    public int getReceiveCounter() {
        return 0;
    }
    
    public String getRemoteAddress() {
        return "StompSocket_" + this.hashCode();
    }
    
    public void oneway(final Object command) throws IOException {
        try {
            this.protocolConverter.onActiveMQCommand((Command)command);
        }
        catch (Exception e) {
            this.onException(IOExceptionSupport.create(e));
        }
    }
    
    public X509Certificate[] getPeerCertificates() {
        return null;
    }
    
    public void sendToActiveMQ(final Command command) {
        this.doConsume(command);
    }
    
    public void sendToStomp(final StompFrame command) throws IOException {
        this.outbound.sendMessage((byte)0, command.toString());
    }
}
