// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.AttributeKey;

public abstract class CumulativeProtocolDecoder extends ProtocolDecoder
{
    private final AttributeKey BUFFER;
    
    protected CumulativeProtocolDecoder() {
        this.BUFFER = new AttributeKey(this.getClass(), "buffer");
    }
    
    @Override
    public final void decode(final IoSession ioSession, IoBuffer ioBuffer, final ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        if (!ioSession.getTransportMetadata().hasFragmentation()) {
            while (ioBuffer.hasRemaining() && this.doDecode$1f3018df(ioBuffer, protocolDecoderOutput)) {}
            return;
        }
        boolean b = true;
        IoBuffer ioBuffer2;
        if ((ioBuffer2 = (IoBuffer)ioSession.getAttribute(this.BUFFER)) != null) {
            boolean b2 = false;
            if (ioBuffer2.isAutoExpand()) {
                try {
                    ioBuffer2.put(ioBuffer);
                    b2 = true;
                }
                catch (IllegalStateException ex) {}
                catch (IndexOutOfBoundsException ex2) {}
            }
            if (b2) {
                ioBuffer2.flip();
            }
            else {
                ioBuffer2.flip();
                final IoBuffer setAutoExpand;
                (setAutoExpand = IoBuffer.allocate(ioBuffer2.remaining() + ioBuffer.remaining()).setAutoExpand(true)).order(ioBuffer2.order());
                setAutoExpand.put(ioBuffer2);
                setAutoExpand.put(ioBuffer);
                setAutoExpand.flip();
                ioBuffer2 = setAutoExpand;
                ioSession.setAttribute(this.BUFFER, ioBuffer2);
            }
        }
        else {
            ioBuffer2 = ioBuffer;
            b = false;
        }
        do {
            final int position = ioBuffer2.position();
            if (!this.doDecode$1f3018df(ioBuffer2, protocolDecoderOutput)) {
                break;
            }
            if (ioBuffer2.position() == position) {
                throw new IllegalStateException("doDecode() can't return true when buffer is not consumed.");
            }
        } while (ioBuffer2.hasRemaining());
        if (!ioBuffer2.hasRemaining()) {
            if (b) {
                this.removeSessionBuffer(ioSession);
            }
            return;
        }
        if (b && ioBuffer2.isAutoExpand()) {
            ioBuffer2.compact();
            return;
        }
        ioBuffer = ioBuffer2;
        final IoBuffer setAutoExpand2;
        (setAutoExpand2 = IoBuffer.allocate(ioBuffer.capacity()).setAutoExpand(true)).order(ioBuffer.order());
        setAutoExpand2.put(ioBuffer);
        ioSession.setAttribute(this.BUFFER, setAutoExpand2);
    }
    
    protected abstract boolean doDecode$1f3018df(final IoBuffer p0, final ProtocolDecoderOutput p1) throws Exception;
    
    @Override
    public final void dispose(final IoSession ioSession) throws Exception {
        this.removeSessionBuffer(ioSession);
    }
    
    private void removeSessionBuffer(final IoSession ioSession) {
        ioSession.removeAttribute(this.BUFFER);
    }
}
