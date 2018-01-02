// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.security;

import com.aviva.framework.util.GenericException;

public class CryptoException extends GenericException
{
    public CryptoException(final String msg, final String msgKey, final Throwable rootCause) {
        super(msg, msgKey, rootCause);
    }
    
    public CryptoException(final String msg, final String msgKey) {
        super(msg, msgKey);
    }
}
