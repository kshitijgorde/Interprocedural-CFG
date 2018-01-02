// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Component;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

public final class k extends cP
{
    private int a;
    private DragSource b;
    
    public k(final DragGestureListener dragGestureListener, final DragSourceListener dragSourceListener) {
        this.a = 1;
        (this.b = DragSource.getDefaultDragSource()).createDefaultDragGestureRecognizer(this, this.a, dragGestureListener);
    }
}
