// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface SelectionListener extends SWTEventListener
{
    void widgetSelected(final SelectionEvent p0);
    
    void widgetDefaultSelected(final SelectionEvent p0);
}