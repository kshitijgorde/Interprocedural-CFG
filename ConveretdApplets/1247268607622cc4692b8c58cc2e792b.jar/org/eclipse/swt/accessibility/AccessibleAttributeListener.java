// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleAttributeListener extends SWTEventListener
{
    void getAttributes(final AccessibleAttributeEvent p0);
    
    void getTextAttributes(final AccessibleTextAttributeEvent p0);
}
