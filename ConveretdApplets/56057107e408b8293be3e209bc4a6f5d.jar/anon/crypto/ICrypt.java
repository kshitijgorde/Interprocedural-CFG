// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.NoSuchAlgorithmException;

public interface ICrypt
{
    String crypt(final String p0) throws NoSuchAlgorithmException;
    
    String crypt(final String p0, final String p1) throws NoSuchAlgorithmException;
}
