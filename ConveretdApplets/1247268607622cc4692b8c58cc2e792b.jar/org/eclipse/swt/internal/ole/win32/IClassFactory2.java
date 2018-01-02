// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.OS;

public class IClassFactory2 extends IUnknown
{
    public IClassFactory2(final int n) {
        super(n);
    }
    
    public int CreateInstanceLic(final int n, final int n2, final GUID guid, final int n3, final int[] array) {
        return COM.VtblCall(7, this.address, n, n2, guid, n3, array);
    }
    
    public int GetLicInfo(final LICINFO licinfo) {
        return COM.VtblCall(5, this.address, licinfo);
    }
    
    public int RequestLicKey(final int n, final int[] array) {
        return OS.VtblCall(6, this.address, n, array);
    }
}
