// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.cells;

import anon.util.ByteArrayUtil;
import anon.crypto.MyAES;
import org.bouncycastle.crypto.digests.SHA1Digest;

public class RelayCell extends Cell
{
    public static final byte RELAY_BEGIN = 1;
    public static final byte RELAY_DATA = 2;
    public static final byte RELAY_END = 3;
    public static final byte RELAY_CONNECTED = 4;
    public static final byte RELAY_SENDME = 5;
    public static final byte RELAY_EXTEND = 6;
    public static final byte RELAY_EXTENDED = 7;
    public static final byte RELAY_TRUNCATE = 8;
    public static final byte RELAY_TRUNCATED = 9;
    public static final byte RELAY_DROP = 10;
    public static final byte RELAY_RESOLVE = 11;
    public static final byte RELAY_RESOLVED = 12;
    private byte m_relayCommand;
    private Integer m_streamID;
    private boolean m_digestGenerated;
    
    public RelayCell() {
        super(3);
        this.m_streamID = new Integer(0);
    }
    
    public RelayCell(final int n) {
        super(3, n);
        this.m_streamID = new Integer(0);
        this.m_digestGenerated = false;
    }
    
    public RelayCell(final int n, final byte[] array, final int n2) {
        super(3, n, array, n2);
        this.m_relayCommand = array[0];
        this.m_streamID = new Integer((array[3] & 0xFF) << 8 | (array[4] & 0xFF));
        this.m_digestGenerated = false;
    }
    
    public RelayCell(final int n, final byte relayCommand, final int n2, final byte[] array) {
        super(3, n, createPayload(relayCommand, n2, array));
        this.m_relayCommand = relayCommand;
        this.m_streamID = new Integer(n2);
        this.m_digestGenerated = false;
    }
    
    public byte getRelayCommand() {
        return this.m_relayCommand;
    }
    
    public Integer getStreamID() {
        return this.m_streamID;
    }
    
    public void generateDigest(final SHA1Digest sha1Digest) {
        if (!this.m_digestGenerated) {
            sha1Digest.update(super.m_payload, 0, super.m_payload.length);
            final SHA1Digest sha1Digest2 = new SHA1Digest(sha1Digest);
            final byte[] array = new byte[sha1Digest2.getDigestSize()];
            sha1Digest2.doFinal(array, 0);
            for (int i = 0; i < 4; ++i) {
                super.m_payload[i + 5] = array[i];
            }
            this.m_digestGenerated = true;
        }
    }
    
    public void checkDigest(final SHA1Digest sha1Digest) throws Exception {
        sha1Digest.update(super.m_payload, 0, 5);
        sha1Digest.update(new byte[4], 0, 4);
        sha1Digest.update(super.m_payload, 9, super.m_payload.length - 9);
        final SHA1Digest sha1Digest2 = new SHA1Digest(sha1Digest);
        final byte[] array = new byte[sha1Digest2.getDigestSize()];
        sha1Digest2.doFinal(array, 0);
        for (int i = 0; i < 4; ++i) {
            if (super.m_payload[i + 5] != array[i]) {
                throw new Exception("Wrong Digest detected");
            }
        }
        this.m_digestGenerated = true;
    }
    
    public void doCryptography(final MyAES myAES) throws Exception {
        final byte[] payload = new byte[super.m_payload.length];
        myAES.processBytesCTR(super.m_payload, 0, payload, 0, 509);
        super.m_payload = payload;
        this.m_relayCommand = super.m_payload[0];
        this.m_streamID = new Integer((super.m_payload[3] & 0xFF) << 8 | (super.m_payload[4] & 0xFF));
    }
    
    private static byte[] createPayload(final byte b, final int n, byte[] array) {
        final byte[] conc = ByteArrayUtil.conc(new byte[] { b, 0, 0 }, ByteArrayUtil.inttobyte(n, 2), new byte[4]);
        if (array == null) {
            array = new byte[498];
        }
        byte[] array2;
        if (array.length < 499) {
            array2 = ByteArrayUtil.conc(conc, ByteArrayUtil.inttobyte(array.length, 2), array);
        }
        else {
            array2 = ByteArrayUtil.conc(conc, ByteArrayUtil.inttobyte(498L, 2), ByteArrayUtil.copy(array, 0, 498));
        }
        return array2;
    }
    
    public byte[] getCellData() {
        if (this.m_digestGenerated) {
            return super.getCellData();
        }
        return null;
    }
    
    public byte[] getRelayPayload() {
        return ByteArrayUtil.copy(super.m_payload, 11, (super.m_payload[9] & 0xFF) << 8 | (super.m_payload[10] & 0xFF));
    }
}
