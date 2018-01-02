// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.SWTEventListener;

public interface DropTargetListener extends SWTEventListener
{
    void dragEnter(final DropTargetEvent p0);
    
    void dragLeave(final DropTargetEvent p0);
    
    void dragOperationChanged(final DropTargetEvent p0);
    
    void dragOver(final DropTargetEvent p0);
    
    void drop(final DropTargetEvent p0);
    
    void dropAccept(final DropTargetEvent p0);
}
