// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class TYPEATTR
{
    public int guid_Data1;
    public short guid_Data2;
    public short guid_Data3;
    public byte[] guid_Data4;
    public int lcid;
    public int dwReserved;
    public int memidConstructor;
    public int memidDestructor;
    public int lpstrSchema;
    public int cbSizeInstance;
    public int typekind;
    public short cFuncs;
    public short cVars;
    public short cImplTypes;
    public short cbSizeVft;
    public short cbAlignment;
    public short wTypeFlags;
    public short wMajorVerNum;
    public short wMinorVerNum;
    public int tdescAlias_unionField;
    public short tdescAlias_vt;
    public int idldescType_dwReserved;
    public short idldescType_wIDLFlags;
    public static final int sizeof;
    
    static {
        sizeof = COM.TYPEATTR_sizeof();
    }
    
    public TYPEATTR() {
        this.guid_Data4 = new byte[8];
    }
}
