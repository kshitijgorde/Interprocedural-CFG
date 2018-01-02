// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IServiceProvider extends IUnknown
{
    public IServiceProvider(final int n) {
        super(n);
    }
    
    public int QueryService(final GUID guid, final GUID guid2, final int[] array) {
        return COM.VtblCall(3, this.address, guid, guid2, array);
    }
}
