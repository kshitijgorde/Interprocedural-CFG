// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Graphics;
import java.awt.Color;
import xfunctions.functions.Variable;
import xfunctions.functions.Expression;

public class DirectionField extends Drawable
{
    private Expression dxdt;
    private Expression dydt;
    private Variable xVar;
    private Variable yVar;
    private int interArrowSpacing;
    int[][] dx;
    int[][] dy;
    int rows;
    int columns;
    int start_x;
    int start_y;
    private static final Color color;
    
    static {
        color = new Color(255, 180, 255);
    }
    
    public DirectionField() {
        this.interArrowSpacing = 25;
    }
    
    public DirectionField(final Variable xVar, final Variable yVar) {
        this.interArrowSpacing = 25;
        this.xVar = xVar;
        this.yVar = yVar;
    }
    
    public DirectionField(final Expression dxdt, final Expression dydt, final Variable xVar, final Variable yVar) {
        this.interArrowSpacing = 25;
        this.dxdt = dxdt;
        this.dydt = dydt;
        this.xVar = xVar;
        this.yVar = yVar;
    }
    
    public void setExpressions(final Expression dxdt, final Expression dydt) {
        this.dxdt = dxdt;
        this.dydt = dydt;
        this.reset();
    }
    
    public void setInterArrowSpacing(int interArrowSpacing) {
        if (interArrowSpacing < 5) {
            interArrowSpacing = 5;
        }
        else if (interArrowSpacing > 100) {
            interArrowSpacing = 100;
        }
        this.interArrowSpacing = interArrowSpacing;
        this.reset();
    }
    
    public void reset() {
        final int[][] array = null;
        this.dy = array;
        this.dx = array;
    }
    
    public void releaseResources() {
        this.reset();
    }
    
    public void draw(final Graphics graphics, final CoordinateRect coordinateRect) {
        if (this.dxdt == null || this.dydt == null || this.xVar == null || this.yVar == null) {
            return;
        }
        if (this.dx == null) {
            this.setup(coordinateRect);
        }
        graphics.setColor(DirectionField.color);
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                this.drawArrow(graphics, this.start_x + i * this.interArrowSpacing, this.start_y + j * this.interArrowSpacing, this.dx[i][j], this.dy[i][j]);
            }
        }
    }
    
    private void drawArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n3 != Integer.MAX_VALUE) {
            final int n5 = n + n3;
            final int n6 = n2 + n4;
            final int n7 = n3 / 5;
            final int n8 = n4 / 5;
            final int n9 = n5 + n8 - n7;
            final int n10 = n6 - n7 - n8;
            final int n11 = n5 - n7 - n8;
            final int n12 = n6 + n7 - n8;
            graphics.drawLine(n, n2, n5, n6);
            graphics.drawLine(n5, n6, n9, n10);
            graphics.drawLine(n5, n6, n11, n12);
        }
    }
    
    private void setup(final CoordinateRect coordinateRect) {
        this.rows = (coordinateRect.getWidth() - 2 * coordinateRect.getGap()) / this.interArrowSpacing + 1;
        this.columns = (coordinateRect.getHeight() - 2 * coordinateRect.getGap()) / this.interArrowSpacing + 1;
        this.start_x = coordinateRect.getLeft() + (coordinateRect.getWidth() - (this.rows - 1) * this.interArrowSpacing) / 2;
        this.start_y = coordinateRect.getTop() + (coordinateRect.getHeight() - (this.columns - 1) * this.interArrowSpacing) / 2;
        this.dx = new int[this.rows][this.columns];
        this.dy = new int[this.rows][this.columns];
        final double n = 1.0 / coordinateRect.getPixelWidth();
        final double n2 = 1.0 / coordinateRect.getPixelHeight();
        final double n3 = 4.0 * this.interArrowSpacing / 5.0;
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                final int n4 = this.start_x + i * this.interArrowSpacing;
                final int n5 = this.start_y + j * this.interArrowSpacing;
                final double pixelToX = coordinateRect.pixelToX(n4);
                final double pixelToY = coordinateRect.pixelToY(n5);
                this.xVar.setValue(pixelToX);
                this.yVar.setValue(pixelToY);
                final double value = this.dxdt.value();
                final double value2 = this.dydt.value();
                if (Double.isNaN(value) || Double.isInfinite(value) || Double.isNaN(value2) || Double.isInfinite(value2)) {
                    this.dx[i][j] = Integer.MAX_VALUE;
                }
                else {
                    final double n6 = value * n;
                    final double n7 = value2 * n2;
                    final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
                    this.dx[i][j] = (int)Math.round(n6 * n3 / sqrt);
                    this.dy[i][j] = -(int)Math.round(n7 * n3 / sqrt);
                }
            }
        }
    }
}
