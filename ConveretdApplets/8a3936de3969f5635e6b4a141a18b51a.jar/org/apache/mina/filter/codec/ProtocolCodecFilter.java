// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

import org.apache.mina.core.write.WriteRequestWrapper;
import java.net.SocketAddress;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.slf4j.LoggerFactory;
import com.masystem.beergame.debug.Log;
import java.util.Queue;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;

public class ProtocolCodecFilter extends IoFilterAdapter
{
    private static final Logger LOGGER;
    private static final IoBuffer EMPTY_BUFFER;
    private final AttributeKey ENCODER;
    private final AttributeKey DECODER;
    private final AttributeKey DECODER_OUT;
    private final AttributeKey ENCODER_OUT;
    private final ProtocolCodecFactory factory;
    
    public ProtocolCodecFilter(final ProtocolCodecFactory factory) {
        this.ENCODER = new AttributeKey(ProtocolCodecFilter.class, "encoder");
        this.DECODER = new AttributeKey(ProtocolCodecFilter.class, "decoder");
        this.DECODER_OUT = new AttributeKey(ProtocolCodecFilter.class, "decoderOut");
        this.ENCODER_OUT = new AttributeKey(ProtocolCodecFilter.class, "encoderOut");
        if (factory == null) {
            throw new IllegalArgumentException("factory");
        }
        this.factory = factory;
    }
    
    @Override
    public final void onPreAdd$64777341(final IoFilterChain ioFilterChain) throws Exception {
        if (ioFilterChain.contains(this)) {
            throw new IllegalArgumentException("You can't add the same filter instance more than once.  Create another instance and add it.");
        }
    }
    
    @Override
    public final void onPostRemove$64777341(final IoFilterChain ioFilterChain) throws Exception {
        this.disposeCodec(ioFilterChain.getSession());
    }
    
    @Override
    public final void messageReceived(final IoFilter.NextFilter nextFilter, final IoSession ioSession, Object o) throws Exception {
        if (!(o instanceof IoBuffer)) {
            nextFilter.messageReceived(ioSession, o);
            return;
        }
        o = o;
        final ProtocolDecoder decoder$7e2ed8b4 = this.factory.getDecoder$7e2ed8b4();
        final ProtocolDecoderOutput decoderOut$2f5f9c7f = this.getDecoderOut$2f5f9c7f(ioSession);
        while (((IoBuffer)o).hasRemaining()) {
            final int position = ((IoBuffer)o).position();
            try {
                synchronized (decoderOut$2f5f9c7f) {
                    decoder$7e2ed8b4.decode(ioSession, (IoBuffer)o, decoderOut$2f5f9c7f);
                }
                decoderOut$2f5f9c7f.flush(nextFilter, ioSession);
            }
            catch (Throwable t) {
                final ProtocolDecoderException ex = (ProtocolDecoderException)t;
                ProtocolDecoderException ex2;
                if (t instanceof ProtocolDecoderException) {
                    ex2 = ex;
                }
                else {
                    ex2 = new ProtocolDecoderException(ex);
                }
                if (ex2.getHexdump() == null) {
                    final int position2 = ((IoBuffer)o).position();
                    ((IoBuffer)o).position(position);
                    ex2.setHexdump(((IoBuffer)o).getHexDump());
                    ((IoBuffer)o).position(position2);
                }
                decoderOut$2f5f9c7f.flush(nextFilter, ioSession);
                nextFilter.exceptionCaught(ioSession, ex2);
                if (!(ex instanceof RecoverableProtocolDecoderException) || ((IoBuffer)o).position() == position) {
                    return;
                }
                continue;
            }
        }
    }
    
    @Override
    public final void messageSent(final IoFilter.NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
        if (writeRequest instanceof EncodedWriteRequest) {
            return;
        }
        if (writeRequest instanceof MessageWriteRequest) {
            nextFilter.messageSent(ioSession, ((MessageWriteRequest)writeRequest).getParentRequest());
            return;
        }
        nextFilter.messageSent(ioSession, writeRequest);
    }
    
    @Override
    public final void filterWrite(final IoFilter.NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
        final Object message;
        if ((message = writeRequest.getMessage()) instanceof IoBuffer || message instanceof FileRegion) {
            nextFilter.filterWrite(ioSession, writeRequest);
            return;
        }
        final ProtocolEncoder encoder$3a35968c = this.factory.getEncoder$3a35968c();
        ProtocolEncoderOutput protocolEncoderOutput;
        if ((protocolEncoderOutput = (ProtocolEncoderOutput)ioSession.getAttribute(this.ENCODER_OUT)) == null) {
            protocolEncoderOutput = new ProtocolEncoderOutputImpl(ioSession, nextFilter, writeRequest);
            ioSession.setAttribute(this.ENCODER_OUT, protocolEncoderOutput);
        }
        final ProtocolEncoderOutput protocolEncoderOutput2 = protocolEncoderOutput;
        if (encoder$3a35968c == null) {
            throw new ProtocolEncoderException("The encoder is null for the session " + ioSession);
        }
        if (protocolEncoderOutput2 == null) {
            throw new ProtocolEncoderException("The encoderOut is null for the session " + ioSession);
        }
        try {
            encoder$3a35968c.encode$a41eff(message, protocolEncoderOutput2);
            final Queue<Object> messageQueue = protocolEncoderOutput2.getMessageQueue();
            IoBuffer poll;
            while (!messageQueue.isEmpty() && (poll = messageQueue.poll()) != null) {
                if (!(poll instanceof IoBuffer) || poll.hasRemaining()) {
                    nextFilter.filterWrite(ioSession, new EncodedWriteRequest(poll, null, writeRequest.getDestination()));
                }
            }
            nextFilter.filterWrite(ioSession, new MessageWriteRequest(writeRequest));
        }
        catch (Throwable t) {
            final ProtocolEncoderException ex = (ProtocolEncoderException)t;
            ProtocolEncoderException ex2;
            if (t instanceof ProtocolEncoderException) {
                ex2 = ex;
            }
            else {
                ex2 = new ProtocolEncoderException(ex);
            }
            throw ex2;
        }
    }
    
    @Override
    public final void sessionClosed(final IoFilter.NextFilter nextFilter, final IoSession ioSession) throws Exception {
        this.factory.getDecoder$7e2ed8b4();
        final ProtocolDecoderOutput decoderOut$2f5f9c7f = this.getDecoderOut$2f5f9c7f(ioSession);
        this.disposeCodec(ioSession);
        decoderOut$2f5f9c7f.flush(nextFilter, ioSession);
        nextFilter.sessionClosed(ioSession);
    }
    
    private void disposeCodec(final IoSession ioSession) {
        if (ioSession.removeAttribute(this.ENCODER) != null) {}
        final ProtocolDecoder protocolDecoder;
        if ((protocolDecoder = (ProtocolDecoder)ioSession.removeAttribute(this.DECODER)) != null) {
            try {
                protocolDecoder.dispose(ioSession);
            }
            catch (Throwable t) {
                final Logger logger = ProtocolCodecFilter.LOGGER;
                Log.warn("Failed to dispose: " + protocolDecoder.getClass().getName() + " (" + protocolDecoder + ')');
            }
        }
        ioSession.removeAttribute(this.DECODER_OUT);
    }
    
    private ProtocolDecoderOutput getDecoderOut$2f5f9c7f(final IoSession ioSession) {
        ProtocolDecoderOutput protocolDecoderOutput;
        if ((protocolDecoderOutput = (ProtocolDecoderOutput)ioSession.getAttribute(this.DECODER_OUT)) == null) {
            protocolDecoderOutput = new ProtocolDecoderOutputImpl();
            ioSession.setAttribute(this.DECODER_OUT, protocolDecoderOutput);
        }
        return protocolDecoderOutput;
    }
    
    static {
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
        EMPTY_BUFFER = IoBuffer.wrap(new byte[0]);
    }
    
    static final class ProtocolEncoderOutputImpl extends ProtocolEncoderOutput
    {
        public ProtocolEncoderOutputImpl(final IoSession ioSession, final IoFilter.NextFilter nextFilter, final WriteRequest writeRequest) {
        }
    }
    
    static final class EncodedWriteRequest extends DefaultWriteRequest
    {
        public EncodedWriteRequest(final Object o, final WriteFuture writeFuture, final SocketAddress socketAddress) {
            super(o, null, socketAddress);
        }
        
        @Override
        public final boolean isEncoded() {
            return true;
        }
    }
    
    static final class ProtocolDecoderOutputImpl extends ProtocolDecoderOutput
    {
        @Override
        public final void flush(final IoFilter.NextFilter nextFilter, final IoSession ioSession) {
            final Queue<Object> messageQueue = this.getMessageQueue();
            while (!messageQueue.isEmpty()) {
                nextFilter.messageReceived(ioSession, messageQueue.poll());
            }
        }
    }
    
    static final class MessageWriteRequest extends WriteRequestWrapper
    {
        public MessageWriteRequest(final WriteRequest writeRequest) {
            super(writeRequest);
        }
        
        @Override
        public final Object getMessage() {
            return ProtocolCodecFilter.EMPTY_BUFFER;
        }
        
        @Override
        public final String toString() {
            return "MessageWriteRequest, parent : " + super.toString();
        }
    }
}
