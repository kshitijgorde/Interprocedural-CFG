// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IDataObject extends IUnknown
{
    public IDataObject(final int n) {
        super(n);
    }
    
    public int EnumFormatEtc(final int n, final int[] array) {
        return OS.VtblCall(8, this.address, n, array);
    }
    
    public int GetData(final FORMATETC formatetc, final STGMEDIUM stgmedium) {
        return COM.VtblCall(3, this.address, formatetc, stgmedium);
    }
    
    public int GetDataHere(final FORMATETC formatetc, final STGMEDIUM stgmedium) {
        return COM.VtblCall(4, this.address, formatetc, stgmedium);
    }
    
    public int QueryGetData(final FORMATETC formatetc) {
        return COM.VtblCall(5, this.address, formatetc);
    }
    
    public int SetData(final FORMATETC formatetc, final STGMEDIUM stgmedium, final boolean b) {
        return COM.VtblCall(7, this.address, formatetc, stgmedium, b);
    }
}
