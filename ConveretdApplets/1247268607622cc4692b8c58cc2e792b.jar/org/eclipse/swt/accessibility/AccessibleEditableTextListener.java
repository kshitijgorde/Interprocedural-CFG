// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleEditableTextListener extends SWTEventListener
{
    void copyText(final AccessibleEditableTextEvent p0);
    
    void cutText(final AccessibleEditableTextEvent p0);
    
    void pasteText(final AccessibleEditableTextEvent p0);
    
    void replaceText(final AccessibleEditableTextEvent p0);
    
    void setTextAttributes(final AccessibleTextAttributeEvent p0);
}
