// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class SplitterBarMouseListener extends MouseAdapter
{
    private SplitterBar s;
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.s.mouseExit(mouseEvent);
    }
    
    public SplitterBarMouseListener(final SplitterBar s) {
        this.s = s;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.s.mouseRelease(mouseEvent);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.s.mouseEnter(mouseEvent);
    }
}
