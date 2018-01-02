// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public abstract class AbstractMessage
{
    private int m_messageCode;
    private Object m_messageData;
    
    protected AbstractMessage(final int n) {
        this(n, null);
    }
    
    protected AbstractMessage(final int messageCode, final Object messageData) {
        this.m_messageCode = messageCode;
        this.m_messageData = messageData;
    }
    
    public int getMessageCode() {
        return this.m_messageCode;
    }
    
    public Object getMessageData() {
        return this.m_messageData;
    }
}
