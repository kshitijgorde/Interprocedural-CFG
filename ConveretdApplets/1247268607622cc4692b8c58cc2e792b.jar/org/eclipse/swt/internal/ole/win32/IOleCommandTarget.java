// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IOleCommandTarget extends IUnknown
{
    public IOleCommandTarget(final int n) {
        super(n);
    }
    
    public int Exec(final GUID guid, final int n, final int n2, final int n3, final int n4) {
        return COM.VtblCall(4, this.address, guid, n, n2, n3, n4);
    }
    
    public int QueryStatus(final GUID guid, final int n, final OLECMD olecmd, final OLECMDTEXT olecmdtext) {
        if (n > 1) {
            return -2147024809;
        }
        return COM.VtblCall(3, this.address, guid, n, olecmd, olecmdtext);
    }
}
