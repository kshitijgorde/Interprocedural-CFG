// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls;

public abstract class AbstractTLSRecord
{
    protected int m_Type;
    protected int m_dataLen;
    protected byte[] m_Data;
    protected byte[] m_Header;
    
    public byte[] getHeader() {
        return this.m_Header;
    }
    
    public byte[] getData() {
        return this.m_Data;
    }
    
    public void setType(final int type) {
        this.m_Type = type;
        this.m_Header[0] = (byte)(type & 0xFF);
    }
    
    public int getType() {
        return this.m_Type;
    }
    
    public int getLength() {
        return this.m_dataLen;
    }
    
    public abstract int getHeaderLength();
}
