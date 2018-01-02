// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IConnectionPointContainer extends IUnknown
{
    public IConnectionPointContainer(final int n) {
        super(n);
    }
    
    public int FindConnectionPoint(final GUID guid, final int[] array) {
        return COM.VtblCall(4, this.address, guid, array);
    }
}
