// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Insets;
import xfunctions.graphs.CoordinateRect;
import xfunctions.graphs.NumberInputPanel;
import xfunctions.graphs.DisplayCanvas;
import java.awt.Panel;

abstract class GenericPanel extends Panel
{
    DisplayCanvas canvas;
    NumberInputPanel numberInput;
    ButtonPanel buttons;
    
    void aboutToHide() {
        if (this.canvas != null) {
            this.canvas.releaseResources();
            final CoordinateRect coords = this.canvas.getCoords();
            if (coords != null) {
                coords.reset();
            }
        }
    }
    
    void aboutToShow() {
    }
    
    boolean doButtonCommand(final String s) {
        return false;
    }
    
    public Insets insets() {
        return new Insets(3, 3, 3, 3);
    }
    
    void installExample(final Object[] array) {
    }
}
