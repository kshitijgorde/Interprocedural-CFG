// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.SWTEventListener;

public interface ProgressListener extends SWTEventListener
{
    void changed(final ProgressEvent p0);
    
    void completed(final ProgressEvent p0);
}
