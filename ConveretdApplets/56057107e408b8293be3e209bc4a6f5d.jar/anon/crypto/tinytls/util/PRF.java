// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.util;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import anon.util.ByteArrayUtil;

public class PRF
{
    private byte[] m_secret;
    private byte[] m_seed;
    private byte[] m_label;
    
    public PRF(final byte[] secret, final byte[] label, final byte[] seed) {
        this.m_secret = secret;
        this.m_seed = seed;
        this.m_label = label;
    }
    
    public byte[] calculate(final int n) {
        final byte[] array = new byte[n];
        int n2 = this.m_secret.length / 2;
        if (n2 * 2 < this.m_secret.length) {
            ++n2;
        }
        final byte[] copy = ByteArrayUtil.copy(this.m_secret, 0, n2);
        final byte[] copy2 = ByteArrayUtil.copy(this.m_secret, this.m_secret.length - n2, n2);
        final byte[] hash = new P_Hash(copy, ByteArrayUtil.conc(this.m_label, this.m_seed), new MD5Digest()).getHash(n);
        final byte[] hash2 = new P_Hash(copy2, ByteArrayUtil.conc(this.m_label, this.m_seed), new SHA1Digest()).getHash(n);
        for (int i = 0; i < n; ++i) {
            array[i] = (byte)((hash[i] ^ hash2[i]) & 0xFF);
        }
        return array;
    }
}
