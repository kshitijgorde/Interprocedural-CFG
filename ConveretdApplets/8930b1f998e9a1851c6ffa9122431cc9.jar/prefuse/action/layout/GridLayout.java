// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import prefuse.data.Node;
import java.util.Iterator;
import prefuse.data.tuple.TupleSet;
import java.awt.geom.Rectangle2D;
import prefuse.visual.VisualItem;

public class GridLayout extends Layout
{
    protected int rows;
    protected int cols;
    protected boolean analyze;
    
    public GridLayout(final String s) {
        super(s);
        this.analyze = false;
        this.analyze = true;
    }
    
    public GridLayout(final String s, final int rows, final int cols) {
        super(s);
        this.analyze = false;
        this.rows = rows;
        this.cols = cols;
        this.analyze = false;
    }
    
    public void run(final double n) {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double minX = layoutBounds.getMinX();
        final double minY = layoutBounds.getMinY();
        final double width = layoutBounds.getWidth();
        final double height = layoutBounds.getHeight();
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        int rows = this.rows;
        int cols = this.cols;
        if (this.analyze) {
            final int[] analyzeGraphGrid = analyzeGraphGrid(group);
            rows = analyzeGraphGrid[0];
            cols = analyzeGraphGrid[1];
        }
        final Iterator tuples = group.tuples();
        for (int n2 = 0; tuples.hasNext() && n2 < rows * cols; ++n2) {
            final VisualItem visualItem = tuples.next();
            visualItem.setVisible(true);
            final double n3 = minX + width * (n2 % cols / (cols - 1));
            final double n4 = minY + height * (n2 / cols / (rows - 1));
            this.setX(visualItem, null, n3);
            this.setY(visualItem, null, n4);
        }
        while (tuples.hasNext()) {
            tuples.next().setVisible(false);
        }
    }
    
    public static int[] analyzeGraphGrid(final TupleSet set) {
        final Iterator tuples = set.tuples();
        tuples.next();
        int n = 2;
        while (tuples.hasNext() && tuples.next().getDegree() != 2) {
            ++n;
        }
        return new int[] { set.getTupleCount() / n, n };
    }
    
    public int getNumCols() {
        return this.cols;
    }
    
    public void setNumCols(final int cols) {
        this.cols = cols;
    }
    
    public int getNumRows() {
        return this.rows;
    }
    
    public void setNumRows(final int rows) {
        this.rows = rows;
    }
}
