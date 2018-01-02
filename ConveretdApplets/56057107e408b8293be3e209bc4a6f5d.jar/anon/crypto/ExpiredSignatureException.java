// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.SignatureException;

public class ExpiredSignatureException extends SignatureException
{
    public ExpiredSignatureException(final String s) {
        super(s);
    }
}
