// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import anon.crypto.MyRSAPublicKey;
import org.w3c.dom.Element;
import java.math.BigInteger;

public interface IASymMixCipher
{
    int encrypt(final byte[] p0, final int p1, final byte[] p2, final int p3);
    
    int getOutputBlockSize();
    
    int getInputBlockSize();
    
    int getPaddingSize();
    
    int setPublicKey(final BigInteger p0, final BigInteger p1);
    
    int setPublicKey(final Element p0);
    
    MyRSAPublicKey getPublicKey();
}
