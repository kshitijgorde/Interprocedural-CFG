// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import anon.util.ByteArrayUtil;

public class SingleBlockMessage extends MessageImplementation
{
    static final int SINGLETON_HEADER_LEN = 22;
    private byte[] m_payload;
    
    public SingleBlockMessage(final byte[] payload) {
        this.m_payload = payload;
    }
    
    public byte[][] buildPayload() {
        final byte[] randomArray = MixMinionCryptoUtil.randomArray(28672 - this.m_payload.length - 22);
        final byte[] inttobyte = ByteArrayUtil.inttobyte(this.m_payload.length, 2);
        final byte[] hash = MixMinionCryptoUtil.hash(ByteArrayUtil.conc(this.m_payload, randomArray));
        final byte[][] array = new byte[1][28672];
        array[0] = ByteArrayUtil.conc(inttobyte, hash, this.m_payload, randomArray);
        return array;
    }
}
