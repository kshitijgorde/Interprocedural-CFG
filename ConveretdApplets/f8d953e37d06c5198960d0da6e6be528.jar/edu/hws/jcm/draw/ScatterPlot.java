// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import edu.hws.jcm.awt.JCMError;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.data.Value;
import java.awt.Color;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.awt.DataTableInput;
import edu.hws.jcm.awt.Computable;

public class ScatterPlot extends Drawable implements Computable
{
    public static final int INTERCEPT = 0;
    public static final int SLOPE = 1;
    public static final int DATACT = 2;
    public static final int MISSINGCT = 3;
    public static final int STANDARDERROR = 4;
    public static final int CORRELATION = 5;
    private DataTableInput table;
    private long lastTableSN;
    private boolean autoChangeLimits;
    private int column1;
    private int column2;
    private Expression exp1;
    private Expression exp2;
    private boolean showRegressionLine;
    private boolean missingValueIsError;
    private double slope;
    private double intercept;
    private int dataCt;
    private int missingCt;
    private double correlation;
    private double standardError;
    private double[][] data;
    private Color lineColor;
    private Color dataColor;
    private static final int crossHalfSize = 2;
    
    public ScatterPlot() {
        this(null, 0, 1);
    }
    
    public ScatterPlot(final DataTableInput dataTableInput) {
        this(dataTableInput, 0, 1);
    }
    
    public ScatterPlot(final DataTableInput table, final int column1, final int column2) {
        this.autoChangeLimits = true;
        this.showRegressionLine = true;
        this.missingValueIsError = true;
        this.slope = Double.NaN;
        this.intercept = Double.NaN;
        this.correlation = Double.NaN;
        this.standardError = Double.NaN;
        this.lineColor = Color.black;
        this.dataColor = Color.red;
        this.table = table;
        this.column1 = column1;
        this.column2 = column2;
    }
    
    public ScatterPlot(final DataTableInput table, final Expression exp1, final Expression exp2) {
        this.autoChangeLimits = true;
        this.showRegressionLine = true;
        this.missingValueIsError = true;
        this.slope = Double.NaN;
        this.intercept = Double.NaN;
        this.correlation = Double.NaN;
        this.standardError = Double.NaN;
        this.lineColor = Color.black;
        this.dataColor = Color.red;
        this.table = table;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.column1 = 0;
        this.column2 = 1;
    }
    
    public void setTable(final DataTableInput table) {
        if (table == this.table) {
            return;
        }
        this.table = table;
        this.lastTableSN = 0L;
        this.column1 = 0;
        this.column2 = 1;
        this.checkData();
    }
    
    public DataTableInput getTable() {
        return this.table;
    }
    
    public void setColumns(final int column1, final int column2) {
        this.column1 = column1;
        this.column2 = column2;
        final Expression expression = null;
        this.exp2 = expression;
        this.exp1 = expression;
        this.lastTableSN = 0L;
        this.checkData();
    }
    
    public void setExpressions(final Expression exp1, final Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.lastTableSN = 0L;
        this.checkData();
    }
    
    public void setShowRegressionLine(final boolean showRegressionLine) {
        if (showRegressionLine != this.showRegressionLine) {
            this.showRegressionLine = showRegressionLine;
            this.needsRedraw();
        }
    }
    
    public boolean getShowRegressionLine() {
        return this.showRegressionLine;
    }
    
    public void setMissingValueIsError(final boolean missingValueIsError) {
        this.missingValueIsError = missingValueIsError;
    }
    
    public boolean getMissingValueIsError() {
        return this.missingValueIsError;
    }
    
    public void setAutoChangeLimits(final boolean autoChangeLimits) {
        this.autoChangeLimits = autoChangeLimits;
    }
    
    public boolean getAutoChangeLimits() {
        return this.autoChangeLimits;
    }
    
    public Color getDataColor() {
        return this.dataColor;
    }
    
    public void setDataColor(final Color dataColor) {
        if (dataColor != null) {
            this.dataColor = dataColor;
        }
    }
    
    public Color getLineColor() {
        return this.lineColor;
    }
    
    public void setLineColor(final Color lineColor) {
        if (lineColor != null) {
            this.lineColor = lineColor;
        }
    }
    
    public Value getValueObject(final int n) {
        if (n < 0 || n > 5) {
            throw new IllegalArgumentException("Unknown code (" + n + ") for type of value object.");
        }
        return new SPV(n);
    }
    
    private void checkData() {
        if (this.table != null && this.lastTableSN == this.table.getSerialNumber()) {
            return;
        }
        try {
            this.compute();
        }
        catch (JCMError jcmError) {
            super.canvas.setErrorMessage(null, jcmError.getMessage());
        }
    }
    
    public void compute() {
        final double[] data = this.getData();
        if (this.table != null) {
            this.lastTableSN = this.table.getSerialNumber();
        }
        if (data == null || !this.needsNewLimits(data, super.coords)) {
            this.needsRedraw();
        }
        else {
            super.coords.setLimits(data);
        }
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        graphics.setColor(this.dataColor);
        if (this.table == null) {
            graphics.drawString("No table has been specified.", 20, 27);
            return;
        }
        if (this.column1 < 0 || this.column1 >= this.table.getColumnCount() || this.column2 < 0 || this.column2 >= this.table.getColumnCount()) {
            graphics.drawString("Illegal column numbers.", 20, 27);
            return;
        }
        if (this.data == null || this.data.length == 0) {
            graphics.drawString("No data available.", 20, 27);
            return;
        }
        this.checkData();
        for (int i = 0; i < this.data.length; ++i) {
            final int xToPixel = super.coords.xToPixel(this.data[i][0]);
            final int yToPixel = super.coords.yToPixel(this.data[i][1]);
            graphics.drawLine(xToPixel - 2, yToPixel, xToPixel + 2, yToPixel);
            graphics.drawLine(xToPixel, yToPixel - 2, xToPixel, yToPixel + 2);
        }
        if (this.showRegressionLine && !Double.isNaN(this.slope)) {
            graphics.setColor(this.lineColor);
            if (Double.isInfinite(this.slope)) {
                final int xToPixel2 = super.coords.xToPixel(this.data[0][0]);
                graphics.drawLine(xToPixel2, super.coords.getTop(), xToPixel2, super.coords.getTop() + super.coords.getHeight());
            }
            else {
                final double pixelToX = super.coords.pixelToX(super.coords.getLeft());
                final double pixelToX2 = super.coords.pixelToX(super.coords.getLeft() + super.coords.getWidth());
                graphics.drawLine(super.coords.xToPixel(pixelToX), super.coords.yToPixel(this.slope * pixelToX + this.intercept) - 1, super.coords.xToPixel(pixelToX2), super.coords.yToPixel(this.slope * pixelToX2 + this.intercept) - 1);
            }
        }
    }
    
    private double[] getData() {
        final int n = (this.table == null) ? 0 : this.table.getNonEmptyRowCount();
        double[] computeDesiredLimits = null;
        if (this.table == null || n == 0 || ((this.exp1 == null || this.exp2 == null) && (this.column1 < 0 || this.column1 >= this.table.getColumnCount() || this.column2 < 0 || this.column2 >= this.table.getColumnCount()))) {
            this.data = new double[0][2];
            this.dataCt = 0;
            this.missingCt = 0;
            this.slope = Double.NaN;
            this.intercept = Double.NaN;
            this.correlation = Double.NaN;
            this.standardError = Double.NaN;
            return null;
        }
        this.data = new double[n][2];
        this.dataCt = 0;
        this.missingCt = 0;
        if (this.exp1 == null || this.exp2 == null) {
            for (int i = 0; i < n; ++i) {
                final double cellContents = this.table.getCellContents(i + 1, this.column1);
                final double cellContents2 = this.table.getCellContents(i + 1, this.column2);
                if (Double.isNaN(cellContents) || Double.isNaN(cellContents2) || Double.isInfinite(cellContents) || Double.isInfinite(cellContents2)) {
                    if (this.missingValueIsError) {
                        throw new JCMError("Missing data in row " + this.table.getCurrentRowNumber() + " of table.", this);
                    }
                    ++this.missingCt;
                }
                else {
                    this.data[this.dataCt][0] = cellContents;
                    this.data[this.dataCt][1] = cellContents2;
                    ++this.dataCt;
                }
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                this.table.setCurrentRowNumber(j + 1);
                final double val = this.exp1.getVal();
                final double val2 = this.exp2.getVal();
                if (Double.isNaN(val) || Double.isNaN(val2) || Double.isInfinite(val) || Double.isInfinite(val2)) {
                    if (this.missingValueIsError) {
                        throw new JCMError("Missing data or undefined expression value for row " + this.table.getCurrentRowNumber() + " of table.", this);
                    }
                    ++this.missingCt;
                }
                else {
                    this.data[this.dataCt][0] = val;
                    this.data[this.dataCt][1] = val2;
                    ++this.dataCt;
                }
            }
        }
        if (this.dataCt < this.data.length) {
            final double[][] data = new double[this.dataCt][2];
            for (int k = 0; k < this.dataCt; ++k) {
                data[k] = this.data[k];
            }
            this.data = data;
        }
        this.getRegressionStats();
        if (this.autoChangeLimits) {
            computeDesiredLimits = this.computeDesiredLimits();
        }
        return computeDesiredLimits;
    }
    
    private void getRegressionStats() {
        if (this.dataCt == 0) {
            final double n = Double.NaN;
            this.standardError = n;
            this.correlation = n;
            this.intercept = n;
            this.slope = n;
            return;
        }
        boolean b = true;
        boolean b2 = true;
        double n2 = this.data[0][0];
        double n3 = this.data[0][1];
        double n4 = this.data[0][0] * this.data[0][1];
        double n5 = this.data[0][0] * this.data[0][0];
        double n6 = this.data[0][1] * this.data[0][1];
        for (int i = 1; i < this.dataCt; ++i) {
            if (this.data[0][0] != this.data[i][0]) {
                b = false;
            }
            if (this.data[0][1] != this.data[i][1]) {
                b2 = false;
            }
            n2 += this.data[i][0];
            n3 += this.data[i][1];
            n4 += this.data[i][0] * this.data[i][1];
            n5 += this.data[i][0] * this.data[i][0];
            n6 += this.data[i][1] * this.data[i][1];
        }
        final double n7 = this.dataCt * n5 - n2 * n2;
        final double n8 = this.dataCt * n6 - n3 * n3;
        final double n9 = this.dataCt * n4 - n2 * n3;
        if (b && b2) {
            this.slope = 0.0;
            this.intercept = this.data[0][1];
            final double n10 = Double.NaN;
            this.standardError = n10;
            this.correlation = n10;
        }
        else if (b) {
            this.slope = Double.POSITIVE_INFINITY;
            final double intercept = Double.NaN;
            this.standardError = intercept;
            this.correlation = intercept;
            this.intercept = intercept;
        }
        else if (n7 == 0.0) {
            final double n11 = Double.NaN;
            this.standardError = n11;
            this.correlation = n11;
            this.intercept = n11;
            this.slope = n11;
        }
        else {
            this.slope = n9 / n7;
            this.intercept = (n3 - this.slope * n2) / this.dataCt;
            if (n8 == 0.0) {
                this.correlation = Double.NaN;
            }
            else {
                this.correlation = n9 / Math.sqrt(n7 * n8);
            }
            if (this.dataCt <= 2) {
                this.standardError = Double.NaN;
            }
            else {
                double n12 = 0.0;
                for (int j = 0; j < this.dataCt; ++j) {
                    final double n13 = this.data[j][1] - (this.slope * this.data[j][0] + this.intercept);
                    n12 += n13 * n13;
                }
                this.standardError = Math.sqrt(n12 / (this.dataCt - 2));
            }
        }
    }
    
    private double[] computeDesiredLimits() {
        if (this.data.length == 0) {
            return null;
        }
        double n = Double.MAX_VALUE;
        double n2 = -1.7976931348623157E308;
        double n3 = Double.MAX_VALUE;
        double n4 = -1.7976931348623157E308;
        for (int i = 0; i < this.dataCt; ++i) {
            final double n5 = this.data[i][0];
            final double n6 = this.data[i][1];
            if (n5 > n2) {
                n2 = n5;
            }
            if (n5 < n) {
                n = n5;
            }
            if (n6 > n4) {
                n4 = n6;
            }
            if (n6 < n3) {
                n3 = n6;
            }
        }
        if (n > 0.0 && n2 - n > n2 / 2.0) {
            n = 0.0;
        }
        if (n3 > 0.0 && n4 - n3 > n4 / 2.0) {
            n3 = 0.0;
        }
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        if (n2 < 0.0) {
            n2 = 0.0;
        }
        double n7;
        double n8;
        if (n2 == n) {
            n7 = n2 + 1.0;
            n8 = n - 1.0;
        }
        else {
            final double n9 = (n2 - n) / 15.0;
            n7 = n2 + n9;
            n8 = n - n9;
        }
        double n10;
        double n11;
        if (n4 == n3) {
            n10 = n4 + 1.0;
            n11 = n3 - 1.0;
        }
        else {
            final double n12 = (n4 - n3) / 15.0;
            n10 = n4 + n12;
            n11 = n3 - n12;
        }
        return new double[] { n8, n7, n11, n10 };
    }
    
    private boolean needsNewLimits(final double[] array, final CoordinateRect coordinateRect) {
        final double[] array2 = { coordinateRect.getXmin(), coordinateRect.getXmax(), coordinateRect.getYmin(), coordinateRect.getYmax() };
        return array[0] < array2[0] || array[1] > array2[1] || array[2] < array2[2] || array[3] > array2[3] || array2[1] - array2[0] > 1.3 * (array[1] - array[0]) || array2[3] - array2[2] > 1.3 * (array[3] - array[2]) || array2[1] - array2[0] < (array[1] - array[0]) / 1.3 || array2[3] - array2[2] < (array[3] - array[2]) / 1.3;
    }
    
    private class SPV implements Value
    {
        private int code;
        
        SPV(final int code) {
            this.code = code;
        }
        
        public double getVal() {
            ScatterPlot.this.checkData();
            switch (this.code) {
                case 0: {
                    return ScatterPlot.this.intercept;
                }
                case 1: {
                    return ScatterPlot.this.slope;
                }
                case 2: {
                    return ScatterPlot.this.dataCt;
                }
                case 3: {
                    return ScatterPlot.this.missingCt;
                }
                case 4: {
                    return ScatterPlot.this.standardError;
                }
                default: {
                    return ScatterPlot.this.correlation;
                }
            }
        }
    }
}
