// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

public class InternalChannelMessage
{
    public static final int CODE_PACKET_RECEIVED = 1;
    public static final int CODE_CHANNEL_CLOSED = 2;
    public static final int CODE_CHANNEL_EXCEPTION = 3;
    private int m_messageCode;
    private byte[] m_messageData;
    
    public InternalChannelMessage(final int messageCode, final byte[] messageData) {
        this.m_messageCode = messageCode;
        this.m_messageData = messageData;
    }
    
    public int getMessageCode() {
        return this.m_messageCode;
    }
    
    public byte[] getMessageData() {
        return this.m_messageData;
    }
}
