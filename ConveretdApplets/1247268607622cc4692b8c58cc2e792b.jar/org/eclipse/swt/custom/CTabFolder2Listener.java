// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.internal.SWTEventListener;

public interface CTabFolder2Listener extends SWTEventListener
{
    void close(final CTabFolderEvent p0);
    
    void minimize(final CTabFolderEvent p0);
    
    void maximize(final CTabFolderEvent p0);
    
    void restore(final CTabFolderEvent p0);
    
    void showList(final CTabFolderEvent p0);
}
