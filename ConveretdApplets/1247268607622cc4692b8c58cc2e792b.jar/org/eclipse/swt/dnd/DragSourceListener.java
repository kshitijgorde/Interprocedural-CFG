// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.SWTEventListener;

public interface DragSourceListener extends SWTEventListener
{
    void dragStart(final DragSourceEvent p0);
    
    void dragSetData(final DragSourceEvent p0);
    
    void dragFinished(final DragSourceEvent p0);
}
