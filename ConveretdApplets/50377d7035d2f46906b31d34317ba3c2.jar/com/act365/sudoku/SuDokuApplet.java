// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.awt.Component;
import java.applet.Applet;

public class SuDokuApplet extends Applet
{
    int boxesAcross;
    int boxesDown;
    
    public SuDokuApplet() {
        this.boxesAcross = 3;
        this.boxesDown = 3;
    }
    
    public void init() {
        final String acrossString = this.getParameter("ACROSS");
        final String downString = this.getParameter("DOWN");
        if (acrossString instanceof String) {
            try {
                this.boxesAcross = Integer.parseInt(acrossString);
            }
            catch (NumberFormatException e) {
                System.err.println("Illegal ACROSS vaue: " + acrossString);
            }
        }
        if (downString instanceof String) {
            try {
                this.boxesDown = Integer.parseInt(downString);
            }
            catch (NumberFormatException e) {
                System.err.println("Illegal DOWN vaue: " + downString);
            }
        }
        final GridContainer grid = new GridContainer(new Grid(this.boxesAcross, this.boxesDown));
        final ControlContainer control = new ControlContainer(grid, this.getClass());
        final SuDokuContainer suDoku = new SuDokuContainer(grid, control);
        this.add(suDoku);
        this.setSize(suDoku.getBestSize());
    }
}
