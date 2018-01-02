// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.internal.SWTEventListener;

public interface TextChangeListener extends SWTEventListener
{
    void textChanging(final TextChangingEvent p0);
    
    void textChanged(final TextChangedEvent p0);
    
    void textSet(final TextChangedEvent p0);
}
