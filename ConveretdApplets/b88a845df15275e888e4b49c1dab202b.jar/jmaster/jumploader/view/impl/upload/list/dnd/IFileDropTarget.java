// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.dnd;

import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.io.File;

public interface IFileDropTarget
{
    void filesDropped(final File[] p0);
    
    void dragEnter(final DropTargetDragEvent p0);
    
    void dragOver(final DropTargetDragEvent p0);
    
    void dropActionChanged(final DropTargetDragEvent p0);
    
    void dragExit(final DropTargetEvent p0);
}
