// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import org.slf4j.LoggerFactory;
import org.apache.activemq.transport.tcp.SslTransport;
import java.security.cert.X509Certificate;
import org.apache.activemq.transport.TransportListener;
import java.io.IOException;
import javax.jms.JMSException;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.command.Command;
import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.transport.Transport;
import org.slf4j.Logger;
import org.apache.activemq.transport.TransportFilter;

public class StompTransportFilter extends TransportFilter implements StompTransport
{
    private static final Logger LOG;
    private final ProtocolConverter protocolConverter;
    private final FrameTranslator frameTranslator;
    private boolean trace;
    
    public StompTransportFilter(final Transport next, final FrameTranslator translator, final BrokerContext brokerContext) {
        super(next);
        this.frameTranslator = translator;
        this.protocolConverter = new ProtocolConverter(this, translator, brokerContext);
    }
    
    @Override
    public void oneway(final Object o) throws IOException {
        try {
            final Command command = (Command)o;
            this.protocolConverter.onActiveMQCommand(command);
        }
        catch (JMSException e) {
            throw IOExceptionSupport.create(e);
        }
    }
    
    @Override
    public void onCommand(final Object command) {
        try {
            if (this.trace) {
                StompTransportFilter.LOG.trace("Received: \n" + command);
            }
            this.protocolConverter.onStompCommand((StompFrame)command);
        }
        catch (IOException e) {
            this.onException(e);
        }
        catch (JMSException e2) {
            this.onException(IOExceptionSupport.create(e2));
        }
    }
    
    @Override
    public void sendToActiveMQ(final Command command) {
        final TransportListener l = this.transportListener;
        if (l != null) {
            l.onCommand(command);
        }
    }
    
    @Override
    public void sendToStomp(final StompFrame command) throws IOException {
        if (this.trace) {
            StompTransportFilter.LOG.trace("Sending: \n" + command);
        }
        final Transport n = this.next;
        if (n != null) {
            n.oneway(command);
        }
    }
    
    public FrameTranslator getFrameTranslator() {
        return this.frameTranslator;
    }
    
    @Override
    public X509Certificate[] getPeerCertificates() {
        if (this.next instanceof SslTransport) {
            final X509Certificate[] peerCerts = ((SslTransport)this.next).getPeerCertificates();
            if (this.trace && peerCerts != null) {
                StompTransportFilter.LOG.debug("Peer Identity has been verified\n");
            }
            return peerCerts;
        }
        return null;
    }
    
    public boolean isTrace() {
        return this.trace;
    }
    
    public void setTrace(final boolean trace) {
        this.trace = trace;
    }
    
    static {
        LOG = LoggerFactory.getLogger(StompTransportFilter.class);
    }
}
