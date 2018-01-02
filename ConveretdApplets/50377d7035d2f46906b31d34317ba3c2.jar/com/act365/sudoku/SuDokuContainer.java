// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.awt.Dimension;
import java.awt.Component;
import com.act365.awt.Container;

public class SuDokuContainer extends Container
{
    GridContainer grid;
    ControlContainer control;
    
    public SuDokuContainer(final GridContainer grid, final ControlContainer control) {
        this.grid = grid;
        this.control = control;
        this.addComponent(grid, 0, 0, 1, 1, 1, 1);
        this.addComponent(control, 0, 1, 1, 1, 1, 1);
    }
    
    public Dimension getBestSize() {
        return new Dimension(Math.max(this.grid.getBestSize().width, this.control.getBestSize().width), this.grid.getBestSize().height + this.control.getBestSize().height);
    }
}
