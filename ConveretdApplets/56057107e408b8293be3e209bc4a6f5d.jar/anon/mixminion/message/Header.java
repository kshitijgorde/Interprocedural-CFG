// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import org.bouncycastle.crypto.Digest;
import anon.crypto.MyRSA;
import org.bouncycastle.crypto.digests.SHA1Digest;
import anon.util.ByteArrayUtil;
import logging.LogHolder;
import logging.LogType;
import anon.mixminion.mmrdescription.MMRDescription;
import anon.crypto.MyRSAPublicKey;
import java.util.Vector;

public class Header
{
    private final int HEADER_LEN = 2048;
    private final int PK_ENC_LEN = 256;
    private final int PK_OVERHEAD_LEN = 42;
    private final int PK_MAX_DATA_LEN = 214;
    private final int HASH_LEN = 20;
    private final int MIN_SH = 42;
    private byte[] VERSION_MAJOR;
    private byte[] m_header;
    
    public Header(final Vector vector, final Vector vector2, final ExitInformation exitInformation) {
        this.VERSION_MAJOR = new byte[] { 0, 3 };
        this.m_header = this.buildHeader(vector, vector2, exitInformation);
    }
    
    private byte[] buildHeader(final Vector vector, final Vector vector2, final ExitInformation exitInformation) {
        final Vector<RoutingInformation> vector3 = new Vector<RoutingInformation>();
        final Vector<MyRSAPublicKey> vector4 = new Vector<MyRSAPublicKey>();
        final Vector<byte[]> vector5 = new Vector<byte[]>();
        final Vector<byte[]> vector6 = new Vector<byte[]>();
        final int size = vector.size();
        final int[] array = new int[size + 1];
        vector4.addElement(null);
        vector3.addElement(null);
        vector5.addElement(null);
        vector6.addElement(null);
        for (int i = 1; i <= size; ++i) {
            final MMRDescription mmrDescription = vector.elementAt(i - 1);
            vector4.addElement(mmrDescription.getPacketKey());
            vector5.addElement(this.subKey(vector2.elementAt(i - 1), "RANDOM JUNK"));
            vector6.addElement(this.subKey(vector2.elementAt(i - 1), "HEADER SECRET KEY"));
            vector3.addElement(mmrDescription.getRoutingInformation());
        }
        int n = 0;
        for (int j = 1; j <= size; ++j) {
            if (j == size) {
                array[j] = exitInformation.m_Content.length;
            }
            else {
                array[j] = vector3.elementAt(j + 1).m_Content.length;
            }
            final int[] array2 = array;
            final int n2 = j;
            array2[n2] += 84;
            n += array[j];
        }
        final int n3 = 2048 - n;
        if (n > 2048) {
            LogHolder.log(3, LogType.MISC, "[Calculating HEADERSIZE]: Subheaders don't fit into HEADER_LEN ");
        }
        final Vector<byte[]> vector7 = new Vector<byte[]>();
        vector7.addElement("".getBytes());
        for (int k = 1; k <= size; ++k) {
            final byte[] array3 = vector7.elementAt(k - 1);
            final byte[] conc = ByteArrayUtil.conc(array3, MixMinionCryptoUtil.createPRNG(vector5.elementAt(k), array[k]));
            vector7.addElement(MixMinionCryptoUtil.xor(conc, ByteArrayUtil.copy(MixMinionCryptoUtil.createPRNG(vector6.elementAt(k), 2048 + array[k]), 1792 - array3.length, conc.length)));
        }
        final Vector vector8 = new Vector<byte[]>();
        vector8.setSize(size + 2);
        vector8.setElementAt(MixMinionCryptoUtil.randomArray(n3), size + 1);
        for (int l = size; l >= 1; --l) {
            ForwardInformation forwardInformation;
            if (l == size) {
                forwardInformation = exitInformation;
            }
            else {
                forwardInformation = vector3.elementAt(l + 1);
            }
            final byte[] shs = this.makeSHS(this.VERSION_MAJOR, vector2.elementAt(l - 1), new byte[20], ByteArrayUtil.inttobyte(forwardInformation.m_Content.length, 2), forwardInformation.m_Type, forwardInformation.m_Content);
            final int length = shs.length;
            final byte[] conc2 = ByteArrayUtil.conc(shs, vector8.elementAt(l + 1));
            final byte[] encrypt = MixMinionCryptoUtil.Encrypt(vector6.elementAt(l), ByteArrayUtil.copy(conc2, 214, conc2.length - 214));
            final byte[] shs2 = this.makeSHS(this.VERSION_MAJOR, vector2.elementAt(l - 1), MixMinionCryptoUtil.hash(ByteArrayUtil.conc(encrypt, vector7.elementAt(l - 1))), ByteArrayUtil.inttobyte(forwardInformation.m_Content.length, 2), forwardInformation.m_Type, forwardInformation.m_Content);
            final int max = this.max(214 - length, 0);
            vector8.setElementAt(ByteArrayUtil.conc(this.pk_encrypt(vector4.elementAt(l), ByteArrayUtil.conc(shs2, ByteArrayUtil.copy(conc2, 214 - max, max))), encrypt), l);
        }
        return vector8.elementAt(1);
    }
    
    public byte[] getAsByteArray() {
        return this.m_header;
    }
    
    private byte[] subKey(final byte[] array, final String s) {
        return ByteArrayUtil.copy(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(array, s.getBytes())), 0, 16);
    }
    
    private byte[] makeFSHS(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final short n) {
        return ByteArrayUtil.conc(array, array2, array3, array4, ByteArrayUtil.inttobyte(n, 2));
    }
    
    private byte[] makeSHS(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final short n, final byte[] array5) {
        return ByteArrayUtil.conc(this.makeFSHS(array, array2, array3, array4, n), array5);
    }
    
    private int max(int n, final int n2) {
        if (n < n2) {
            n = n2;
        }
        return n;
    }
    
    private byte[] pk_encrypt(final MyRSAPublicKey myRSAPublicKey, final byte[] array) {
        final byte[] bytes = "He who would make his own liberty secure, must guard even his enemy from oppression.".getBytes();
        final SHA1Digest sha1Digest = new SHA1Digest();
        sha1Digest.update(bytes, 0, bytes.length);
        final MyRSA myRSA = new MyRSA(sha1Digest);
        try {
            myRSA.init(myRSAPublicKey);
            return myRSA.processBlockOAEP(array, 0, array.length);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.CRYPTO, ex);
            return null;
        }
    }
}
