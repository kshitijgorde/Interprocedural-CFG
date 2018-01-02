// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import anon.mixminion.fec.FECCodeFactory;
import logging.LogHolder;
import logging.LogType;
import anon.util.ByteArrayUtil;
import anon.mixminion.mmrdescription.MMRDescription;

public class FragmentedMessage extends MessageImplementation
{
    static final int KEY_LEN = 16;
    static final int OVERHEAD = 0;
    static final int FRAGMENT_HEADER_LEN = 47;
    String[] m_recipient;
    byte[] m_payload;
    
    public FragmentedMessage(final String[] recipient, final byte[] payload) {
        this.m_payload = payload;
        this.m_recipient = recipient;
    }
    
    public byte[][] buildPayload() {
        this.m_payload = MixMinionCryptoUtil.compressData(this.m_payload);
        final ExitInformation exitInformation = MMRDescription.getExitInformation(this.m_recipient, null);
        exitInformation.m_Content = this.m_recipient[0].getBytes();
        this.m_payload = ByteArrayUtil.conc(ByteArrayUtil.conc(ByteArrayUtil.inttobyte(256L, 2), ByteArrayUtil.inttobyte(exitInformation.m_Content.length, 2), exitInformation.m_Content), this.m_payload);
        LogHolder.log(7, LogType.MISC, "[Message] Fragmented, new Compressed Size = " + this.m_payload.length);
        if (this.m_payload.length + 22 <= 28672) {
            throw new RuntimeException("Fragmented Header nach Neukomprimierung mit Single-Laenge");
        }
        this.m_payload = this.whiten(this.m_payload);
        final byte[][] divideIntoFragments = this.divideIntoFragments(this.m_payload);
        final byte[] randomArray = MixMinionCryptoUtil.randomArray(20);
        final byte[] inttobyte = ByteArrayUtil.inttobyte(this.m_payload.length, 4);
        final byte[][] array = new byte[divideIntoFragments.length][28672];
        for (int i = 0; i < divideIntoFragments.length; ++i) {
            final byte[] array2 = divideIntoFragments[i];
            final byte[] array3 = new byte[3];
            array[i] = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(8388608 + i, 3), MixMinionCryptoUtil.hash(ByteArrayUtil.conc(randomArray, inttobyte, array2)), randomArray, inttobyte, array2);
        }
        return array;
    }
    
    byte[][] divideIntoFragments(byte[] conc) {
        final int n = 28625;
        final double n2 = 1.3333333333333333;
        final double ceil = Math.ceil(conc.length / n);
        final int n3 = (int)Math.min(16.0, Math.pow(2.0, Math.ceil(Math.log(ceil) / Math.log(2.0))));
        final int n4 = (int)Math.ceil(ceil / n3);
        final byte[] randomArray = MixMinionCryptoUtil.randomArray(16);
        final int n5 = conc.length - n4 * n * n3;
        System.out.println(n5);
        conc = ByteArrayUtil.conc(conc, MixMinionCryptoUtil.createPRNG(randomArray, Math.abs(n5)));
        final byte[][] array = new byte[n4][n];
        for (int i = 1; i <= n4; ++i) {
            array[i - 1] = ByteArrayUtil.copy(conc, (i - 1) * n * n3, n * n3);
        }
        final int n6 = (int)Math.ceil(n2 * n3);
        System.out.println("   N,num " + n6 + " " + n4);
        final byte[][] array2 = new byte[n4 * n6][28672];
        for (int j = 0; j <= n4 - 1; ++j) {
            for (int k = 0; k <= n6 - 1; ++k) {
                array2[j * n6 + k] = this.FRAGMENT(array[j], n3, n6, k, n);
            }
        }
        return array2;
    }
    
    byte[] FRAGMENT(final byte[] array, final int n, final int n2, final int n3, final int n4) {
        final byte[] array2 = new byte[n2 * n4];
        final byte[][] array3 = new byte[n][];
        final byte[][] array4 = new byte[n2][];
        final int[] array5 = new int[array3.length];
        final int[] array6 = new int[array4.length];
        for (int i = 0; i < array3.length; ++i) {
            array3[i] = array;
            array5[i] = i * n4;
        }
        for (int j = 0; j < array4.length; ++j) {
            array4[j] = array2;
            array6[j] = j * n4;
        }
        final int[] array7 = new int[n2];
        for (int k = 0; k < array7.length; ++k) {
            array7[k] = k;
        }
        FECCodeFactory.getDefault().createFECCode(n, n2).encode(array3, array5, array4, array6, array7, n4);
        return ByteArrayUtil.copy(array4[n3], array6[n3], n4);
    }
    
    private byte[] whiten(final byte[] array) {
        return MixMinionCryptoUtil.SPRP_Encrypt(MixMinionCryptoUtil.hash(ByteArrayUtil.conc(new byte[] { 87, 72, 73, 84, 69, 78 }, "WHITEN".getBytes())), array);
    }
}
