// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.ui.TextAnchor;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.ValueAxisPlot;
import java.text.DecimalFormat;
import org.jfree.data.Range;
import java.text.NumberFormat;

public class LogarithmicAxis extends NumberAxis
{
    public static final double LOG10_VALUE;
    public static final double SMALL_LOG_VALUE = 1.0E-100;
    protected boolean allowNegativesFlag;
    protected boolean strictValuesFlag;
    protected final NumberFormat numberFormatterObj;
    protected boolean expTickLabelsFlag;
    protected boolean log10TickLabelsFlag;
    protected boolean smallLogFlag;
    
    public LogarithmicAxis(final String label) {
        super(label);
        this.allowNegativesFlag = false;
        this.strictValuesFlag = true;
        this.numberFormatterObj = NumberFormat.getInstance();
        this.expTickLabelsFlag = false;
        this.log10TickLabelsFlag = true;
        this.smallLogFlag = false;
        this.setupNumberFmtObj();
    }
    
    public void setAllowNegativesFlag(final boolean flgVal) {
        this.allowNegativesFlag = flgVal;
    }
    
    public boolean getAllowNegativesFlag() {
        return this.allowNegativesFlag;
    }
    
    public void setStrictValuesFlag(final boolean flgVal) {
        this.strictValuesFlag = flgVal;
    }
    
    public boolean getStrictValuesFlag() {
        return this.strictValuesFlag;
    }
    
    public void setExpTickLabelsFlag(final boolean flgVal) {
        this.expTickLabelsFlag = flgVal;
        this.setupNumberFmtObj();
    }
    
    public boolean getExpTickLabelsFlag() {
        return this.expTickLabelsFlag;
    }
    
    public void setLog10TickLabelsFlag(final boolean flag) {
        this.log10TickLabelsFlag = flag;
    }
    
    public boolean getLog10TickLabelsFlag() {
        return this.log10TickLabelsFlag;
    }
    
    public void setRange(final Range range) {
        super.setRange(range);
        this.setupSmallLogFlag();
    }
    
    protected void setupSmallLogFlag() {
        final double lowerVal = this.getRange().getLowerBound();
        this.smallLogFlag = (!this.allowNegativesFlag && lowerVal < 10.0 && lowerVal > 0.0);
    }
    
    protected void setupNumberFmtObj() {
        if (this.numberFormatterObj instanceof DecimalFormat) {
            ((DecimalFormat)this.numberFormatterObj).applyPattern(this.expTickLabelsFlag ? "0E0" : "0.###");
        }
    }
    
    protected double switchedLog10(final double val) {
        return this.smallLogFlag ? (Math.log(val) / LogarithmicAxis.LOG10_VALUE) : this.adjustedLog10(val);
    }
    
    public double adjustedLog10(double val) {
        final boolean negFlag = val < 0.0;
        if (negFlag) {
            val = -val;
        }
        if (val < 10.0) {
            val += (10.0 - val) / 10.0;
        }
        return negFlag ? (-(Math.log(val) / LogarithmicAxis.LOG10_VALUE)) : (Math.log(val) / LogarithmicAxis.LOG10_VALUE);
    }
    
    protected double computeLogFloor(final double lower) {
        double logFloor;
        if (this.allowNegativesFlag) {
            if (lower > 10.0) {
                logFloor = Math.log(lower) / LogarithmicAxis.LOG10_VALUE;
                logFloor = Math.floor(logFloor);
                logFloor = Math.pow(10.0, logFloor);
            }
            else if (lower < -10.0) {
                logFloor = Math.log(-lower) / LogarithmicAxis.LOG10_VALUE;
                logFloor = Math.floor(-logFloor);
                logFloor = -Math.pow(10.0, -logFloor);
            }
            else {
                logFloor = Math.floor(lower);
            }
        }
        else if (lower > 0.0) {
            logFloor = Math.log(lower) / LogarithmicAxis.LOG10_VALUE;
            logFloor = Math.floor(logFloor);
            logFloor = Math.pow(10.0, logFloor);
        }
        else {
            logFloor = Math.floor(lower);
        }
        return logFloor;
    }
    
    protected double computeLogCeil(final double upper) {
        double logCeil;
        if (this.allowNegativesFlag) {
            if (upper > 10.0) {
                logCeil = Math.log(upper) / LogarithmicAxis.LOG10_VALUE;
                logCeil = Math.ceil(logCeil);
                logCeil = Math.pow(10.0, logCeil);
            }
            else if (upper < -10.0) {
                logCeil = Math.log(-upper) / LogarithmicAxis.LOG10_VALUE;
                logCeil = Math.ceil(-logCeil);
                logCeil = -Math.pow(10.0, -logCeil);
            }
            else {
                logCeil = Math.ceil(upper);
            }
        }
        else if (upper > 0.0) {
            logCeil = Math.log(upper) / LogarithmicAxis.LOG10_VALUE;
            logCeil = Math.ceil(logCeil);
            logCeil = Math.pow(10.0, logCeil);
        }
        else {
            logCeil = Math.ceil(upper);
        }
        return logCeil;
    }
    
    public void autoAdjustRange() {
        final Plot plot = this.getPlot();
        if (plot == null) {
            return;
        }
        if (plot instanceof ValueAxisPlot) {
            final ValueAxisPlot vap = (ValueAxisPlot)plot;
            Range r = vap.getDataRange(this);
            double lower;
            if (r == null) {
                r = new Range(0.0, 1.0);
                lower = r.getLowerBound();
            }
            else {
                lower = r.getLowerBound();
                if (this.strictValuesFlag && !this.allowNegativesFlag && lower <= 0.0) {
                    throw new RuntimeException("Values less than or equal to zero not allowed with logarithmic axis");
                }
            }
            lower = this.computeLogFloor(lower);
            if (!this.allowNegativesFlag && lower >= 0.0 && lower < 1.0E-100) {
                lower = r.getLowerBound();
            }
            double upper = r.getUpperBound();
            if (!this.allowNegativesFlag && upper < 1.0 && upper > 0.0 && lower > 0.0) {
                double expVal = Math.log(upper) / LogarithmicAxis.LOG10_VALUE;
                expVal = Math.ceil(-expVal + 0.001);
                expVal = Math.pow(10.0, expVal);
                upper = ((expVal > 0.0) ? (Math.ceil(upper * expVal) / expVal) : Math.ceil(upper));
            }
            else {
                upper = this.computeLogCeil(upper);
            }
            final double minRange = this.getAutoRangeMinimumSize();
            if (upper - lower < minRange) {
                upper = (upper + lower + minRange) / 2.0;
                lower = (upper + lower - minRange) / 2.0;
                if (upper - lower < minRange) {
                    final double absUpper = Math.abs(upper);
                    final double adjVal = (absUpper > 1.0E-100) ? (absUpper / 100.0) : 0.01;
                    upper = (upper + lower + adjVal) / 2.0;
                    lower = (upper + lower - adjVal) / 2.0;
                }
            }
            this.setRange(new Range(lower, upper), false, false);
            this.setupSmallLogFlag();
        }
    }
    
    public double valueToJava2D(double value, final Rectangle2D plotArea, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = this.switchedLog10(range.getLowerBound());
        final double axisMax = this.switchedLog10(range.getUpperBound());
        double min = 0.0;
        double max = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            min = plotArea.getMinX();
            max = plotArea.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            min = plotArea.getMaxY();
            max = plotArea.getMinY();
        }
        value = this.switchedLog10(value);
        if (this.isInverted()) {
            return max - (value - axisMin) / (axisMax - axisMin) * (max - min);
        }
        return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
    }
    
    public double java2DToValue(final double java2DValue, final Rectangle2D plotArea, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = this.switchedLog10(range.getLowerBound());
        final double axisMax = this.switchedLog10(range.getUpperBound());
        double plotMin = 0.0;
        double plotMax = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            plotMin = plotArea.getX();
            plotMax = plotArea.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            plotMin = plotArea.getMaxY();
            plotMax = plotArea.getMinY();
        }
        if (this.isInverted()) {
            return Math.pow(10.0, axisMax - (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
        }
        return Math.pow(10.0, axisMin + (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
    }
    
    public List refreshTicks(final Graphics2D g2, final AxisState state, final Rectangle2D drawArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        List ticks = new ArrayList();
        if (RectangleEdge.isTopOrBottom(edge)) {
            ticks = this.refreshTicksHorizontal(g2, state.getCursor(), drawArea, dataArea, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            ticks = this.refreshTicksVertical(g2, state.getCursor(), drawArea, dataArea, edge);
        }
        return ticks;
    }
    
    public List refreshTicksHorizontal(final Graphics2D g2, final double cursor, final Rectangle2D drawArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List ticks = new ArrayList();
        final Range range = this.getRange();
        double lowerBoundVal = range.getLowerBound();
        if (this.smallLogFlag && lowerBoundVal < 1.0E-100) {
            lowerBoundVal = 1.0E-100;
        }
        final double upperBoundVal = range.getUpperBound();
        int iBegCount = (int)Math.rint(this.switchedLog10(lowerBoundVal));
        final int iEndCount = (int)Math.rint(this.switchedLog10(upperBoundVal));
        if (iBegCount == iEndCount && iBegCount > 0 && Math.pow(10.0, iBegCount) > lowerBoundVal) {
            --iBegCount;
        }
        boolean zeroTickFlag = false;
        for (int i = iBegCount; i <= iEndCount; ++i) {
            for (int j = 0; j < 10; ++j) {
                double currentTickValue;
                String tickLabel;
                if (this.smallLogFlag) {
                    currentTickValue = Math.pow(10.0, i) + Math.pow(10.0, i) * j;
                    if (this.expTickLabelsFlag || (i < 0 && currentTickValue > 0.0 && currentTickValue < 1.0)) {
                        if (j == 0 || (i > -4 && j < 2) || currentTickValue >= upperBoundVal) {
                            this.numberFormatterObj.setMaximumFractionDigits(-i);
                            tickLabel = this.makeTickLabel(currentTickValue, true);
                        }
                        else {
                            tickLabel = "";
                        }
                    }
                    else {
                        tickLabel = ((j < 1 || (i < 1 && j < 5) || j < 4 - i || currentTickValue >= upperBoundVal) ? this.makeTickLabel(currentTickValue) : "");
                    }
                }
                else {
                    if (zeroTickFlag) {
                        --j;
                    }
                    currentTickValue = ((i >= 0) ? (Math.pow(10.0, i) + Math.pow(10.0, i) * j) : (-(Math.pow(10.0, -i) - Math.pow(10.0, -i - 1) * j)));
                    if (!zeroTickFlag) {
                        if (Math.abs(currentTickValue - 1.0) < 1.0E-4 && lowerBoundVal <= 0.0 && upperBoundVal >= 0.0) {
                            currentTickValue = 0.0;
                            zeroTickFlag = true;
                        }
                    }
                    else {
                        zeroTickFlag = false;
                    }
                    tickLabel = (((this.expTickLabelsFlag && j < 2) || j < 1 || (i < 1 && j < 5) || j < 4 - i || currentTickValue >= upperBoundVal) ? this.makeTickLabel(currentTickValue) : "");
                }
                if (currentTickValue > upperBoundVal) {
                    return ticks;
                }
                if (currentTickValue >= lowerBoundVal - 1.0E-100) {
                    TextAnchor anchor = null;
                    TextAnchor rotationAnchor = null;
                    double angle = 0.0;
                    if (this.isVerticalTickLabels()) {
                        anchor = TextAnchor.CENTER_RIGHT;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
                        if (edge == RectangleEdge.TOP) {
                            angle = 1.5707963267948966;
                        }
                        else {
                            angle = -1.5707963267948966;
                        }
                    }
                    else if (edge == RectangleEdge.TOP) {
                        anchor = TextAnchor.BOTTOM_CENTER;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
                    }
                    else {
                        anchor = TextAnchor.TOP_CENTER;
                        rotationAnchor = TextAnchor.TOP_CENTER;
                    }
                    final Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
                    ticks.add(tick);
                }
            }
        }
        return ticks;
    }
    
    public List refreshTicksVertical(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List ticks = new ArrayList();
        double lowerBoundVal = this.getRange().getLowerBound();
        if (this.smallLogFlag && lowerBoundVal < 1.0E-100) {
            lowerBoundVal = 1.0E-100;
        }
        final double upperBoundVal = this.getRange().getUpperBound();
        int iBegCount = (int)Math.rint(this.switchedLog10(lowerBoundVal));
        final int iEndCount = (int)Math.rint(this.switchedLog10(upperBoundVal));
        if (iBegCount == iEndCount && iBegCount > 0 && Math.pow(10.0, iBegCount) > lowerBoundVal) {
            --iBegCount;
        }
        boolean zeroTickFlag = false;
        for (int i = iBegCount; i <= iEndCount; ++i) {
            int jEndCount = 10;
            if (i == iEndCount) {
                jEndCount = 1;
            }
            for (int j = 0; j < jEndCount; ++j) {
                double tickVal;
                String tickLabel;
                if (this.smallLogFlag) {
                    tickVal = Math.pow(10.0, i) + Math.pow(10.0, i) * j;
                    if (j == 0) {
                        if (this.log10TickLabelsFlag) {
                            tickLabel = "10^" + i;
                        }
                        else if (this.expTickLabelsFlag) {
                            tickLabel = "1e" + i;
                        }
                        else if (i >= 0) {
                            final NumberFormat format = this.getNumberFormatOverride();
                            if (format != null) {
                                tickLabel = format.format(tickVal);
                            }
                            else {
                                tickLabel = Long.toString((long)Math.rint(tickVal));
                            }
                        }
                        else {
                            this.numberFormatterObj.setMaximumFractionDigits(-i);
                            tickLabel = this.numberFormatterObj.format(tickVal);
                        }
                    }
                    else {
                        tickLabel = "";
                    }
                }
                else {
                    if (zeroTickFlag) {
                        --j;
                    }
                    tickVal = ((i >= 0) ? (Math.pow(10.0, i) + Math.pow(10.0, i) * j) : (-(Math.pow(10.0, -i) - Math.pow(10.0, -i - 1) * j)));
                    if (j == 0) {
                        if (!zeroTickFlag) {
                            if (i > iBegCount && i < iEndCount && Math.abs(tickVal - 1.0) < 1.0E-4) {
                                tickVal = 0.0;
                                zeroTickFlag = true;
                                tickLabel = "0";
                            }
                            else if (this.log10TickLabelsFlag) {
                                tickLabel = ((i < 0) ? "-" : "") + "10^" + Math.abs(i);
                            }
                            else if (this.expTickLabelsFlag) {
                                tickLabel = ((i < 0) ? "-" : "") + "1e" + Math.abs(i);
                            }
                            else {
                                final NumberFormat format = this.getNumberFormatOverride();
                                if (format != null) {
                                    tickLabel = format.format(tickVal);
                                }
                                else {
                                    tickLabel = Long.toString((long)Math.rint(tickVal));
                                }
                            }
                        }
                        else {
                            tickLabel = "";
                            zeroTickFlag = false;
                        }
                    }
                    else {
                        tickLabel = "";
                        zeroTickFlag = false;
                    }
                }
                if (tickVal > upperBoundVal) {
                    return ticks;
                }
                if (tickVal >= lowerBoundVal - 1.0E-100) {
                    TextAnchor anchor = null;
                    TextAnchor rotationAnchor = null;
                    double angle = 0.0;
                    if (this.isVerticalTickLabels()) {
                        if (edge == RectangleEdge.LEFT) {
                            anchor = TextAnchor.BOTTOM_CENTER;
                            rotationAnchor = TextAnchor.BOTTOM_CENTER;
                            angle = -1.5707963267948966;
                        }
                        else {
                            anchor = TextAnchor.BOTTOM_CENTER;
                            rotationAnchor = TextAnchor.BOTTOM_CENTER;
                            angle = 1.5707963267948966;
                        }
                    }
                    else if (edge == RectangleEdge.LEFT) {
                        anchor = TextAnchor.CENTER_RIGHT;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
                    }
                    else {
                        anchor = TextAnchor.CENTER_LEFT;
                        rotationAnchor = TextAnchor.CENTER_LEFT;
                    }
                    ticks.add(new NumberTick(new Double(tickVal), tickLabel, anchor, rotationAnchor, angle));
                }
            }
        }
        return ticks;
    }
    
    protected String makeTickLabel(final double val, final boolean forceFmtFlag) {
        if (this.expTickLabelsFlag || forceFmtFlag) {
            return this.numberFormatterObj.format(val).toLowerCase();
        }
        return this.getTickUnit().valueToString(val);
    }
    
    protected String makeTickLabel(final double val) {
        return this.makeTickLabel(val, false);
    }
    
    public double translateValueToJava2D(double value, final Rectangle2D plotArea, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = this.switchedLog10(range.getLowerBound());
        final double axisMax = this.switchedLog10(range.getUpperBound());
        double min = 0.0;
        double max = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            min = plotArea.getMinX();
            max = plotArea.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            min = plotArea.getMaxY();
            max = plotArea.getMinY();
        }
        value = this.switchedLog10(value);
        if (this.isInverted()) {
            return max - (value - axisMin) / (axisMax - axisMin) * (max - min);
        }
        return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
    }
    
    public double translateJava2DToValue(final double java2DValue, final Rectangle2D plotArea, final RectangleEdge edge) {
        final Range range = this.getRange();
        final double axisMin = this.switchedLog10(range.getLowerBound());
        final double axisMax = this.switchedLog10(range.getUpperBound());
        double plotMin = 0.0;
        double plotMax = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            plotMin = plotArea.getX();
            plotMax = plotArea.getMaxX();
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            plotMin = plotArea.getMaxY();
            plotMax = plotArea.getMinY();
        }
        if (this.isInverted()) {
            return Math.pow(10.0, axisMax - (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
        }
        return Math.pow(10.0, axisMin + (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
    }
    
    static {
        LOG10_VALUE = Math.log(10.0);
    }
}
