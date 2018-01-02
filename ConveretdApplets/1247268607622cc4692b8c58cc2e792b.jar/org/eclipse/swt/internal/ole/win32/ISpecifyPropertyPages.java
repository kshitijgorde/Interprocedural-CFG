// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class ISpecifyPropertyPages extends IUnknown
{
    public ISpecifyPropertyPages(final int n) {
        super(n);
    }
    
    public int GetPages(final CAUUID cauuid) {
        return COM.VtblCall(3, this.address, cauuid);
    }
}
