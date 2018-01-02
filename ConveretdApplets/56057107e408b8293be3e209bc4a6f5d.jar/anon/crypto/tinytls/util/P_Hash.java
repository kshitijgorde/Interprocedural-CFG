// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.util;

import anon.util.ByteArrayUtil;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.Digest;

public class P_Hash
{
    private byte[] m_secret;
    private byte[] m_seed;
    private Digest m_digest;
    
    public P_Hash(final byte[] secret, final byte[] seed, final Digest digest) {
        this.m_secret = secret;
        this.m_seed = seed;
        this.m_digest = digest;
    }
    
    public byte[] getHash(final int n) {
        byte[] conc = null;
        final HMac hMac = new HMac(this.m_digest);
        hMac.reset();
        hMac.init(new KeyParameter(this.m_secret));
        hMac.update(this.m_seed, 0, this.m_seed.length);
        byte[] array = new byte[hMac.getMacSize()];
        hMac.doFinal(array, 0);
        do {
            hMac.reset();
            hMac.init(new KeyParameter(this.m_secret));
            hMac.update(ByteArrayUtil.conc(array, this.m_seed), 0, array.length + this.m_seed.length);
            final byte[] array2 = new byte[hMac.getMacSize()];
            hMac.doFinal(array2, 0);
            if (conc == null) {
                conc = array2;
            }
            else {
                conc = ByteArrayUtil.conc(conc, array2);
            }
            hMac.reset();
            hMac.init(new KeyParameter(this.m_secret));
            hMac.update(array, 0, array.length);
            array = new byte[hMac.getMacSize()];
            hMac.doFinal(array, 0);
        } while (conc.length < n);
        return ByteArrayUtil.copy(conc, 0, n);
    }
}
