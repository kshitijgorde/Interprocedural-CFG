// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import anon.client.crypto.MixCipherChain;
import anon.client.crypto.DefaultMixCipher;
import anon.client.crypto.FirstMixCipher;
import anon.client.crypto.KeyPool;
import anon.client.crypto.SymCipher;
import anon.client.crypto.IMixCipher;

public class DefaultDataChannelFactory implements IDataChannelFactory
{
    private static final int SYMMETRIC_CIPHER_KEY_LENGTH = 16;
    private static final int SYMMETRIC_CIPHER_BLOCK_LENGTH = 16;
    private KeyExchangeManager m_keyExchangeManager;
    private Multiplexer m_multiplexer;
    
    public DefaultDataChannelFactory(final KeyExchangeManager keyExchangeManager, final Multiplexer multiplexer) {
        this.m_keyExchangeManager = keyExchangeManager;
        this.m_multiplexer = multiplexer;
    }
    
    public AbstractDataChannel createDataChannel(final int n, final AbstractDataChain abstractDataChain) {
        final IMixCipher[] array = new IMixCipher[this.m_keyExchangeManager.getMixParameters().length];
        for (int i = 0; i < array.length; ++i) {
            if (i == 0 && this.m_keyExchangeManager.getFirstMixSymmetricCipher() != null) {
                final SymCipher symCipher = new SymCipher();
                byte[] encryptionKeysAES;
                if (this.m_keyExchangeManager.isProtocolWithEnhancedChannelEncryption()) {
                    encryptionKeysAES = new byte[32];
                }
                else {
                    encryptionKeysAES = new byte[16];
                    KeyPool.getKey(encryptionKeysAES);
                }
                symCipher.setEncryptionKeysAES(encryptionKeysAES);
                final byte[] iv2 = new byte[16];
                for (int j = 0; j < iv2.length; ++j) {
                    iv2[j] = -1;
                }
                symCipher.setIV2(iv2);
                array[i] = new FirstMixCipher(this.m_keyExchangeManager.getFirstMixSymmetricCipher(), symCipher);
            }
            else {
                final SymCipher symCipher2 = new SymCipher();
                byte[] encryptionKeysAES2;
                if (this.m_keyExchangeManager.isProtocolWithEnhancedChannelEncryption()) {
                    encryptionKeysAES2 = new byte[32];
                }
                else {
                    encryptionKeysAES2 = new byte[16];
                    KeyPool.getKey(encryptionKeysAES2);
                }
                symCipher2.setEncryptionKeysAES(encryptionKeysAES2);
                array[i] = new DefaultMixCipher(this.m_keyExchangeManager.getMixParameters()[i], symCipher2);
            }
        }
        final FixedRatioChannelsDescription fixedRatioChannelsDescription = this.m_keyExchangeManager.getFixedRatioChannelsDescription();
        if (fixedRatioChannelsDescription == null) {
            return new UnlimitedDataChannel(n, this.m_multiplexer, abstractDataChain, new MixCipherChain(array));
        }
        return new SimulatedLimitedDataChannel(n, this.m_multiplexer, abstractDataChain, new MixCipherChain(array), fixedRatioChannelsDescription.getChannelDownstreamPackets(), fixedRatioChannelsDescription.getChannelTimeout());
    }
}
