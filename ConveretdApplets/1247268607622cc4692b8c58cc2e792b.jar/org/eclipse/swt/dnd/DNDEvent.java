// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;

class DNDEvent extends Event
{
    public TransferData dataType;
    public TransferData[] dataTypes;
    public int operations;
    public int feedback;
    public Image image;
    public int offsetX;
    public int offsetY;
}
