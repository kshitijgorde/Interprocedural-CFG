// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class IOleControl extends IUnknown
{
    public IOleControl(final int n) {
        super(n);
    }
    
    public int GetControlInfo(final CONTROLINFO controlinfo) {
        return COM.VtblCall(3, this.address, controlinfo);
    }
}
