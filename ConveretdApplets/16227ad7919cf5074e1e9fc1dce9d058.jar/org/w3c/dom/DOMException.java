// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom;

public abstract class DOMException extends RuntimeException
{
    public short code;
    public static final short INDEX_SIZE_ERR = 1;
    public static final short DOMSTRING_SIZE_ERR = 2;
    public static final short HIERARCHY_REQUEST_ERR = 3;
    public static final short WRONG_DOCUMENT_ERR = 4;
    public static final short INVALID_CHARACTER_ERR = 5;
    public static final short NO_DATA_ALLOWED_ERR = 6;
    public static final short NO_MODIFICATION_ALLOWED_ERR = 7;
    public static final short NOT_FOUND_ERR = 8;
    public static final short NOT_SUPPORTED_ERR = 9;
    public static final short INUSE_ATTRIBUTE_ERR = 10;
    
    public DOMException(final short code, final String s) {
        super(s);
        this.code = code;
    }
}
