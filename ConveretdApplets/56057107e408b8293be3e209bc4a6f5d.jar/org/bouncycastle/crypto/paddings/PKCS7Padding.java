// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.paddings;

import org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

public class PKCS7Padding implements BlockCipherPadding
{
    public void init(final SecureRandom secureRandom) throws IllegalArgumentException {
    }
    
    public String getPaddingName() {
        return "PKCS7";
    }
    
    public int addPadding(final byte[] array, int i) {
        final byte b = (byte)(array.length - i);
        while (i < array.length) {
            array[i] = b;
            ++i;
        }
        return b;
    }
    
    public int padCount(final byte[] array) throws InvalidCipherTextException {
        final int n = array[array.length - 1] & 0xFF;
        if (n > array.length || n == 0) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        for (byte b = 1; b <= n; ++b) {
            if (array[array.length - b] != n) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
        }
        return n;
    }
}
