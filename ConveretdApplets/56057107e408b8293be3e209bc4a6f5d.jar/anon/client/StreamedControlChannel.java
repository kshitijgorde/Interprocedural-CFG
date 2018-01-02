// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import anon.IServiceContainer;

public abstract class StreamedControlChannel extends AbstractControlChannel
{
    private byte[] m_messageBuffer;
    private int m_currentIndex;
    private byte[] m_lengthBuffer;
    private boolean m_bIsEncrypted;
    
    public StreamedControlChannel(final int n, final Multiplexer multiplexer, final IServiceContainer serviceContainer, final boolean bIsEncrypted) {
        super(n, multiplexer, serviceContainer);
        this.m_messageBuffer = new byte[0];
        this.m_currentIndex = -2;
        this.m_lengthBuffer = new byte[2];
        this.m_bIsEncrypted = bIsEncrypted;
    }
    
    public int sendByteMessage(final byte[] array) {
        if (array.length > 65535) {
            return -31;
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(array.length);
            dataOutputStream.flush();
            byte[] array2;
            if (this.m_bIsEncrypted && super.m_parentMultiplexer.getControlChannelCipher() != null) {
                array2 = new byte[super.m_parentMultiplexer.getControlChannelCipher().getEncryptedOutputSize(array.length)];
                super.m_parentMultiplexer.getControlChannelCipher().encryptGCM1(array, 0, array2, 0, array.length);
            }
            else {
                array2 = array;
            }
            byteArrayOutputStream.write(array2);
            byteArrayOutputStream.flush();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.sendRawMessage(byteArrayOutputStream.toByteArray());
    }
    
    protected void processPacketData(final byte[] array) {
        int i = 0;
        while (i < array.length) {
            if (this.m_currentIndex < 0) {
                final int min = Math.min(-this.m_currentIndex, array.length - i);
                System.arraycopy(array, i, this.m_lengthBuffer, this.m_lengthBuffer.length + this.m_currentIndex, min);
                this.m_currentIndex += min;
                i += min;
                if (this.m_currentIndex == 0) {
                    try {
                        int n = new DataInputStream(new ByteArrayInputStream(this.m_lengthBuffer)).readUnsignedShort();
                        if (this.m_bIsEncrypted && super.m_parentMultiplexer.getControlChannelCipher() != null) {
                            n = super.m_parentMultiplexer.getControlChannelCipher().getEncryptedOutputSize(n);
                        }
                        this.m_messageBuffer = new byte[n];
                    }
                    catch (IOException ex) {}
                }
            }
            if (this.m_currentIndex >= 0 && this.m_currentIndex < this.m_messageBuffer.length) {
                final int min2 = Math.min(this.m_messageBuffer.length - this.m_currentIndex, array.length - i);
                System.arraycopy(array, i, this.m_messageBuffer, this.m_currentIndex, min2);
                this.m_currentIndex += min2;
                i += min2;
            }
            if (this.m_currentIndex == this.m_messageBuffer.length) {
                byte[] messageBuffer;
                if (this.m_bIsEncrypted && super.m_parentMultiplexer.getControlChannelCipher() != null) {
                    messageBuffer = new byte[super.m_parentMultiplexer.getControlChannelCipher().getDecryptedOutputSize(this.m_messageBuffer.length)];
                    try {
                        super.m_parentMultiplexer.getControlChannelCipher().decryptGCM2(this.m_messageBuffer, 0, messageBuffer, 0, this.m_messageBuffer.length);
                    }
                    catch (Exception ex2) {
                        messageBuffer = null;
                    }
                }
                else {
                    messageBuffer = this.m_messageBuffer;
                }
                this.processMessage(messageBuffer);
                this.m_messageBuffer = new byte[0];
                this.m_currentIndex = -2;
            }
        }
    }
    
    protected abstract void processMessage(final byte[] p0);
}
