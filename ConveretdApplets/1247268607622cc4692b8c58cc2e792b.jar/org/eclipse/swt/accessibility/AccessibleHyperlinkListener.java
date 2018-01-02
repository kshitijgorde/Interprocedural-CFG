// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleHyperlinkListener extends SWTEventListener
{
    void getAnchor(final AccessibleHyperlinkEvent p0);
    
    void getAnchorTarget(final AccessibleHyperlinkEvent p0);
    
    void getStartIndex(final AccessibleHyperlinkEvent p0);
    
    void getEndIndex(final AccessibleHyperlinkEvent p0);
}
