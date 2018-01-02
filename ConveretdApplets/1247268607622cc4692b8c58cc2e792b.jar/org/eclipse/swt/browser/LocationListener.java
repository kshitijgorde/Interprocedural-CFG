// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.SWTEventListener;

public interface LocationListener extends SWTEventListener
{
    void changing(final LocationEvent p0);
    
    void changed(final LocationEvent p0);
}
