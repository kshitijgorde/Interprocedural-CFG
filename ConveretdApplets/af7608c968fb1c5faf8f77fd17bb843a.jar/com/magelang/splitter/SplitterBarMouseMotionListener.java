// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class SplitterBarMouseMotionListener extends MouseMotionAdapter
{
    private SplitterBar s;
    
    public SplitterBarMouseMotionListener(final SplitterBar s) {
        this.s = s;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.s.mouseDrag(mouseEvent);
    }
}
