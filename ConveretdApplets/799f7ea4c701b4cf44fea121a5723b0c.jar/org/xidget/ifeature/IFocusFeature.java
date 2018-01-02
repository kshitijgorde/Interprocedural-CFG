// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xidget.IXidget;

public interface IFocusFeature
{
    void setFocus(final IXidget p0);
    
    IXidget getFocus();
    
    void addFocusListener(final IFocusListener p0);
    
    void removeFocusListener(final IFocusListener p0);
    
    public interface IFocusListener
    {
        void notifyFocus(final IXidget p0, final IXidget p1);
    }
}
