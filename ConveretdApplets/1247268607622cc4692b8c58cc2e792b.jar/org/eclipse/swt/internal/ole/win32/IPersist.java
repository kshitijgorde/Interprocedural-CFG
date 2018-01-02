// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IPersist extends IUnknown
{
    public IPersist(final int n) {
        super(n);
    }
    
    public int GetClassID(final GUID guid) {
        return COM.VtblCall(3, this.address, guid);
    }
}
