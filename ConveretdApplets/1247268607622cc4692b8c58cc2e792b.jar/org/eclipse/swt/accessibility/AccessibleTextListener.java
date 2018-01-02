// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleTextListener extends SWTEventListener
{
    void getCaretOffset(final AccessibleTextEvent p0);
    
    void getSelectionRange(final AccessibleTextEvent p0);
}
