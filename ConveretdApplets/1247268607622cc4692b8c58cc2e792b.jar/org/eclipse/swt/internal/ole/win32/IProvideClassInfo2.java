// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IProvideClassInfo2 extends IProvideClassInfo
{
    public IProvideClassInfo2(final int n) {
        super(n);
    }
    
    public int GetGUID(final int n, final GUID guid) {
        return COM.VtblCall(4, this.address, n, guid);
    }
}
