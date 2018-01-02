// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.exception;

public class CharacterPropertyException extends EncodingException
{
    public CharacterPropertyException(final String message) {
        super(message);
    }
    
    public CharacterPropertyException(final String message, final String str) {
        super(message, str);
    }
    
    public CharacterPropertyException(final String message, final byte[] bytes, final int p, final int end) {
        super(message, bytes, p, end);
    }
}
