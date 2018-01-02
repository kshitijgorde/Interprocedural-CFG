// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls;

public final class TLSPlaintextRecord extends AbstractTLSRecord
{
    public static final int CONTENTTYPE_HANDSHAKE = 22;
    public static final int HEADER_LENGTH = 5;
    public static final int MAX_PAYLOAD_SIZE = 16384;
    private int m_nextHandshakeRecordOffset;
    
    public TLSPlaintextRecord() {
        (super.m_Header = new byte[5])[1] = TinyTLS.PROTOCOLVERSION[0];
        super.m_Header[2] = TinyTLS.PROTOCOLVERSION[1];
        super.m_Data = new byte[16384];
        super.m_dataLen = 0;
        super.m_Type = 0;
        this.m_nextHandshakeRecordOffset = 0;
    }
    
    public void clean() {
        super.m_dataLen = 0;
        super.m_Type = 0;
        this.m_nextHandshakeRecordOffset = 0;
    }
    
    public int getHeaderLength() {
        return 5;
    }
    
    public int getMaxPayloadSize() {
        return 16384;
    }
    
    public void setLength(final int dataLen) {
        super.m_dataLen = dataLen;
        super.m_Header[3] = (byte)(dataLen >> 8 & 0xFF);
        super.m_Header[4] = (byte)(dataLen & 0xFF);
    }
    
    public boolean hasMoreHandshakeRecords() {
        return super.m_Type == 22 && this.m_nextHandshakeRecordOffset < super.m_dataLen;
    }
    
    public TLSHandshakeRecord getNextHandshakeRecord() {
        final TLSHandshakeRecord tlsHandshakeRecord = new TLSHandshakeRecord(super.m_Data, this.m_nextHandshakeRecordOffset);
        this.m_nextHandshakeRecordOffset += tlsHandshakeRecord.getLength() + 4;
        return tlsHandshakeRecord;
    }
}
