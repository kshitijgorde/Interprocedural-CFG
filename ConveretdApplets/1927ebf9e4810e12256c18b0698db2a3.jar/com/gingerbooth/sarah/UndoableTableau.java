// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class UndoableTableau implements Undoable
{
    private boolean completed;
    private boolean undone;
    Vector shapes;
    TableauState stateBefore;
    TableauState stateAfter;
    Tableau tableau;
    
    public UndoableTableau(final Tableau tableau, final ShapeGame shapeGame) {
        this.completed = false;
        this.tableau = tableau;
        this.stateBefore = new TableauState(tableau, shapeGame);
    }
    
    public boolean isRedoable() {
        return this.completed && this.undone;
    }
    
    public boolean isUndoable() {
        return this.completed && !this.undone;
    }
    
    public void redo() {
        if (!this.completed) {
            return;
        }
        if (!this.undone) {
            return;
        }
        this.undone = false;
        this.stateAfter.restore(this.tableau);
    }
    
    public void setStateAfter(final Tableau tableau, final ShapeGame shapeGame) {
        if (tableau != this.tableau) {
            System.err.println("WARNING! Tableau switched in mid-edit?!?");
        }
        this.stateAfter = new TableauState(tableau, shapeGame);
        if ((this.tableau = tableau) != null) {
            this.completed = true;
        }
    }
    
    public void undo() {
        if (!this.completed) {
            return;
        }
        if (this.undone) {
            return;
        }
        this.undone = true;
        this.stateBefore.restore(this.tableau);
    }
    
    class TableauState
    {
        ShapeGame controller;
        Vector shapes;
        double edgeLength;
        double scale;
        double minx;
        double miny;
        double maxx;
        double maxy;
        boolean useOutline;
        int outlineColor;
        int backgroundColorID;
        int vizPixelTolerance;
        int pickPixelTolerance;
        
        TableauState(final Tableau tableau, final ShapeGame controller) {
            this.controller = controller;
            this.shapes = tableau.shapes;
            this.edgeLength = Tableau.edgeLength;
            this.scale = tableau.getScale();
            this.minx = tableau.getMinX();
            this.miny = tableau.getMinY();
            this.maxx = tableau.getMaxX();
            this.maxy = tableau.getMaxY();
            this.useOutline = tableau.useOutline;
            this.outlineColor = tableau.outlineColor;
            this.backgroundColorID = tableau.backgroundColorID;
            this.vizPixelTolerance = Tableau.vizPixelTolerance;
            this.pickPixelTolerance = Tableau.pickPixelTolerance;
        }
        
        void restore(final Tableau tableau) {
            tableau.shapes = this.shapes;
            Tableau.setEdgeLength(this.edgeLength);
            tableau.setScale(this.scale);
            tableau.setMinX(this.minx);
            tableau.setMinY(this.miny);
            tableau.setMaxX(this.maxx);
            tableau.setMaxY(this.maxy);
            tableau.useOutline = this.useOutline;
            this.controller.setOutlineNoUndo(this.outlineColor);
            this.controller.setBackgroundColorNoUndo(this.backgroundColorID);
            Tableau.vizPixelTolerance = this.vizPixelTolerance;
            Tableau.pickPixelTolerance = this.pickPixelTolerance;
        }
    }
}
