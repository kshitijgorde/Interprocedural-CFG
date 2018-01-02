// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto;

public interface AsymmetricCipherKeyPairGenerator
{
    void init(final KeyGenerationParameters p0);
    
    AsymmetricCipherKeyPair generateKeyPair();
}
