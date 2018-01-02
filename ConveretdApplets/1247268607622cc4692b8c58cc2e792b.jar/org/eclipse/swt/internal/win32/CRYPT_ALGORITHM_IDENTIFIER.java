// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CRYPT_ALGORITHM_IDENTIFIER
{
    public int pszObjId;
    public CRYPT_OBJID_BLOB Parameters;
    public static final int sizeof;
    
    static {
        sizeof = OS.CRYPT_ALGORITHM_IDENTIFIER_sizeof();
    }
    
    public CRYPT_ALGORITHM_IDENTIFIER() {
        this.Parameters = new CRYPT_OBJID_BLOB();
    }
}
