// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.io.PrintStream;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Component;
import java.awt.TextField;
import com.act365.awt.Container;

public class GridContainer extends Container
{
    Grid grid;
    LeastCandidatesHybrid strategy;
    TextField[][] textFields;
    Composer composer;
    
    public GridContainer(final Grid grid) {
        this.grid = grid;
        this.strategy = (LeastCandidatesHybrid)Strategy.create(7);
        this.removeAll();
        this.layoutComponents();
        this.validate();
        this.write();
    }
    
    void layoutComponents() {
        this.textFields = new TextField[this.grid.cellsInRow][this.grid.cellsInRow];
        for (int r = 0; r < this.grid.cellsInRow + this.grid.boxesDown - 1; ++r) {
            for (int c = 0; c < this.grid.cellsInRow + this.grid.boxesAcross - 1; ++c) {
                if (r % (this.grid.boxesAcross + 1) < this.grid.boxesAcross && c % (this.grid.boxesDown + 1) < this.grid.boxesDown) {
                    this.addComponent(this.textFields[r / (this.grid.boxesAcross + 1) * this.grid.boxesAcross + r % (this.grid.boxesAcross + 1)][c / (this.grid.boxesDown + 1) * this.grid.boxesDown + c % (this.grid.boxesDown + 1)] = new TextField(1), c, r, 1, 1, 1, 1);
                }
                else {
                    this.addComponent(new Label(), c, r, 1, 1, 1, 1);
                }
            }
        }
    }
    
    public Dimension getBestSize() {
        return new Dimension(40 * (this.grid.cellsInRow + this.grid.boxesDown - 1), 40 * (this.grid.cellsInRow + this.grid.boxesAcross - 1));
    }
    
    public void solve() {
        this.read();
        this.grid.solve(this.strategy, 1);
        this.write();
    }
    
    public int evaluate() {
        this.read();
        final IStrategy strategy = Strategy.create(7);
        final int nSolns = this.grid.solve(strategy, 2);
        strategy.reset();
        return nSolns;
    }
    
    public void unsolve() {
        this.unsolve(0);
    }
    
    public void unsolve(final int move) {
        this.strategy.reset(move);
        this.write();
    }
    
    public void reset() {
        this.grid.reset();
        try {
            this.strategy.setup(this.grid);
        }
        catch (Exception ex) {}
        this.write();
    }
    
    public synchronized void setGrid(final Grid grid) {
        final boolean redraw = this.grid.boxesAcross != grid.boxesAcross || this.grid.boxesDown != grid.boxesDown;
        this.grid = (Grid)grid.clone();
        if (redraw) {
            this.removeAll();
            this.layoutComponents();
            this.validate();
        }
        this.write();
    }
    
    public void setBoxes(final int boxesAcross, final int boxesDown) {
        final boolean redraw = boxesAcross != this.grid.boxesAcross || boxesDown != this.grid.boxesDown;
        this.grid.resize(boxesAcross, boxesDown);
        if (redraw) {
            this.removeAll();
            this.layoutComponents();
            this.validate();
        }
    }
    
    public void paste(final String s) {
        final int oldBoxesAcross = this.grid.boxesAcross;
        final int oldBoxesDown = this.grid.boxesDown;
        this.grid.populate(s);
        if (this.grid.boxesAcross != oldBoxesAcross || this.grid.boxesDown != oldBoxesDown) {
            this.removeAll();
            this.layoutComponents();
            this.validate();
        }
        this.write();
    }
    
    public synchronized void startComposer(final int filledCells) {
        this.read();
        try {
            (this.composer = new Composer(this, this.grid.boxesAcross, 1, 0, 50, Integer.MAX_VALUE, new MaskFactory(this.grid.cellsInRow, filledCells, this.grid.boxesAcross), 3, 0, null, false, this.grid.cellsInRow >= 12, 0, 0, 0, 0, 0, true, false)).start();
        }
        catch (Exception ex) {}
    }
    
    public void stopComposer() {
        if (this.composer instanceof Composer) {
            this.composer.interrupt();
        }
    }
    
    public int getBoxesAcross() {
        return this.grid.boxesAcross;
    }
    
    public int getBoxesDown() {
        return this.grid.boxesDown;
    }
    
    public int getComplexity() {
        return this.grid.complexity;
    }
    
    public LeastCandidatesHybrid getStrategy() {
        return this.strategy;
    }
    
    void write() {
        for (int c = 0; c < this.grid.cellsInRow; ++c) {
            for (int r = 0; r < this.grid.cellsInRow; ++r) {
                this.textFields[r][c].setText(SuDokuUtils.toString(this.grid.data[r][c]));
            }
        }
    }
    
    void read() {
        for (int r = 0; r < this.grid.cellsInRow; ++r) {
            for (int c = 0; c < this.grid.cellsInRow; ++c) {
                this.grid.data[r][c] = SuDokuUtils.parse(this.textFields[r][c].getText());
            }
        }
    }
    
    public String toString() {
        return this.grid.toString();
    }
}
