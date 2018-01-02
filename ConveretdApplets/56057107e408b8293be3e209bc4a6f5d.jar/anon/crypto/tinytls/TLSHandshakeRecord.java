// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls;

public final class TLSHandshakeRecord extends AbstractTLSRecord
{
    public static final int HEADER_LENGTH = 4;
    
    public TLSHandshakeRecord(final byte[] array, final int n) {
        System.arraycopy(array, n, super.m_Header = new byte[4], 0, 4);
        super.m_Type = super.m_Header[0];
        super.m_dataLen = ((super.m_Header[1] & 0xFF) << 16 | (super.m_Header[2] & 0xFF) << 8 | (super.m_Header[3] & 0xFF));
        System.arraycopy(array, n + 4, super.m_Data = new byte[super.m_dataLen], 0, super.m_dataLen);
    }
    
    public int getHeaderLength() {
        return 4;
    }
}
