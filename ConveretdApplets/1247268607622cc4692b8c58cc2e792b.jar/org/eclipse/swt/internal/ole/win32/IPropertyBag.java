// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IPropertyBag extends IUnknown
{
    public IPropertyBag(final int n) {
        super(n);
    }
    
    public int Read(final int n, final int n2, final int[] array) {
        return COM.VtblCall(3, this.getAddress(), n, n2, array);
    }
}
