// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import org.jfree.util.Log;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.ui.Size2D;
import java.awt.Insets;
import org.jfree.util.ShapeUtils;
import org.jfree.text.TextMeasurer;
import org.jfree.text.TextUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.chart.plot.CategoryPlot;
import java.util.ArrayList;
import org.jfree.chart.entity.EntityCollection;
import java.awt.Shape;
import org.jfree.text.TextBlock;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.TickLabelEntity;
import org.jfree.ui.RectangleAnchor;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.Plot;
import java.awt.Graphics2D;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.event.AxisChangeEvent;
import java.util.HashMap;
import org.jfree.util.LogContext;
import java.util.Map;
import java.io.Serializable;

public class CategoryAxis extends Axis implements Cloneable, Serializable
{
    public static final double DEFAULT_AXIS_MARGIN = 0.05;
    public static final double DEFAULT_CATEGORY_MARGIN = 0.2;
    private double lowerMargin;
    private double upperMargin;
    private double categoryMargin;
    private float maxCategoryLabelWidthRatio;
    private int categoryLabelPositionOffset;
    private CategoryLabelPositions categoryLabelPositions;
    private Map categoryLabelToolTips;
    protected static final LogContext logger;
    static /* synthetic */ Class class$org$jfree$chart$axis$CategoryAxis;
    
    public CategoryAxis() {
        this(null);
    }
    
    public CategoryAxis(final String label) {
        super(label);
        this.lowerMargin = 0.05;
        this.upperMargin = 0.05;
        this.categoryMargin = 0.2;
        this.maxCategoryLabelWidthRatio = 0.0f;
        this.setTickMarksVisible(false);
        this.categoryLabelPositionOffset = 4;
        this.categoryLabelPositions = CategoryLabelPositions.STANDARD;
        this.categoryLabelToolTips = new HashMap();
    }
    
    public double getLowerMargin() {
        return this.lowerMargin;
    }
    
    public void setLowerMargin(final double margin) {
        this.lowerMargin = margin;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public double getUpperMargin() {
        return this.upperMargin;
    }
    
    public void setUpperMargin(final double margin) {
        this.upperMargin = margin;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public double getCategoryMargin() {
        return this.categoryMargin;
    }
    
    public void setCategoryMargin(final double margin) {
        this.categoryMargin = margin;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public float getMaxCategoryLabelWidthRatio() {
        return this.maxCategoryLabelWidthRatio;
    }
    
    public void setMaxCategoryLabelWidthRatio(final float ratio) {
        this.maxCategoryLabelWidthRatio = ratio;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public int getCategoryLabelPositionOffset() {
        return this.categoryLabelPositionOffset;
    }
    
    public void setCategoryLabelPositionOffset(final int offset) {
        this.categoryLabelPositionOffset = offset;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public CategoryLabelPositions getCategoryLabelPositions() {
        return this.categoryLabelPositions;
    }
    
    public void setCategoryLabelPositions(final CategoryLabelPositions positions) {
        if (positions == null) {
            throw new IllegalArgumentException("Null 'positions' argument.");
        }
        this.categoryLabelPositions = positions;
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public void addCategoryLabelToolTip(final Comparable category, final String tooltip) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        this.categoryLabelToolTips.put(category, tooltip);
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public void removeCategoryLabelToolTip(final Comparable category) {
        if (category == null) {
            throw new IllegalArgumentException("Null 'category' argument.");
        }
        this.categoryLabelToolTips.remove(category);
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public void clearCategoryLabelToolTips() {
        this.categoryLabelToolTips.clear();
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public double getCategoryJava2DCoordinate(final CategoryAnchor anchor, final int category, final int categoryCount, final Rectangle2D area, final RectangleEdge edge) {
        double result = 0.0;
        if (anchor == CategoryAnchor.START) {
            result = this.getCategoryStart(category, categoryCount, area, edge);
        }
        else if (anchor == CategoryAnchor.MIDDLE) {
            result = this.getCategoryMiddle(category, categoryCount, area, edge);
        }
        else if (anchor == CategoryAnchor.END) {
            result = this.getCategoryEnd(category, categoryCount, area, edge);
        }
        return result;
    }
    
    public double getCategoryStart(final int category, final int categoryCount, final Rectangle2D area, final RectangleEdge edge) {
        double result = 0.0;
        if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
            result = area.getX() + area.getWidth() * this.getLowerMargin();
        }
        else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
            result = area.getMinY() + area.getHeight() * this.getLowerMargin();
        }
        final double categorySize = this.calculateCategorySize(categoryCount, area, edge);
        final double categoryGapWidth = this.calculateCategoryGapSize(categoryCount, area, edge);
        result += category * (categorySize + categoryGapWidth);
        return result;
    }
    
    public double getCategoryMiddle(final int category, final int categoryCount, final Rectangle2D area, final RectangleEdge edge) {
        return this.getCategoryStart(category, categoryCount, area, edge) + this.calculateCategorySize(categoryCount, area, edge) / 2.0;
    }
    
    public double getCategoryEnd(final int category, final int categoryCount, final Rectangle2D area, final RectangleEdge edge) {
        return this.getCategoryStart(category, categoryCount, area, edge) + this.calculateCategorySize(categoryCount, area, edge);
    }
    
    protected double calculateCategorySize(final int categoryCount, final Rectangle2D area, final RectangleEdge edge) {
        double result = 0.0;
        double available = 0.0;
        if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
            available = area.getWidth();
        }
        else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
            available = area.getHeight();
        }
        if (categoryCount > 1) {
            result = available * (1.0 - this.getLowerMargin() - this.getUpperMargin() - this.getCategoryMargin());
            result /= categoryCount;
        }
        else {
            result = available * (1.0 - this.getLowerMargin() - this.getUpperMargin());
        }
        return result;
    }
    
    protected double calculateCategoryGapSize(final int categoryCount, final Rectangle2D area, final RectangleEdge edge) {
        double result = 0.0;
        double available = 0.0;
        if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
            available = area.getWidth();
        }
        else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
            available = area.getHeight();
        }
        if (categoryCount > 1) {
            result = available * this.getCategoryMargin() / (categoryCount - 1);
        }
        return result;
    }
    
    public AxisSpace reserveSpace(final Graphics2D g2, final Plot plot, final Rectangle2D plotArea, final RectangleEdge edge, AxisSpace space) {
        if (space == null) {
            space = new AxisSpace();
        }
        if (!this.isVisible()) {
            return space;
        }
        double tickLabelHeight = 0.0;
        double tickLabelWidth = 0.0;
        if (this.isTickLabelsVisible()) {
            g2.setFont(this.getTickLabelFont());
            final AxisState state = new AxisState();
            this.refreshTicks(g2, state, plotArea, plotArea, edge);
            if (edge == RectangleEdge.TOP) {
                tickLabelHeight = state.getMax();
            }
            else if (edge == RectangleEdge.BOTTOM) {
                tickLabelHeight = state.getMax();
            }
            else if (edge == RectangleEdge.LEFT) {
                tickLabelWidth = state.getMax();
            }
            else if (edge == RectangleEdge.RIGHT) {
                tickLabelWidth = state.getMax();
            }
        }
        final Rectangle2D labelEnclosure = this.getLabelEnclosure(g2, edge);
        double labelHeight = 0.0;
        double labelWidth = 0.0;
        if (RectangleEdge.isTopOrBottom(edge)) {
            labelHeight = labelEnclosure.getHeight();
            space.add(labelHeight + tickLabelHeight + this.categoryLabelPositionOffset, edge);
        }
        else if (RectangleEdge.isLeftOrRight(edge)) {
            labelWidth = labelEnclosure.getWidth();
            space.add(labelWidth + tickLabelWidth + this.categoryLabelPositionOffset, edge);
        }
        return space;
    }
    
    public void configure() {
    }
    
    public AxisState draw(final Graphics2D g2, final double cursor, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final PlotRenderingInfo plotState) {
        if (CategoryAxis.logger.isDebugEnabled()) {
            CategoryAxis.logger.debug("Entering draw() method, cursor = " + cursor);
        }
        if (!this.isVisible()) {
            return new AxisState(cursor);
        }
        if (this.isAxisLineVisible()) {
            this.drawAxisLine(g2, cursor, dataArea, edge);
        }
        AxisState state = new AxisState(cursor);
        state = this.drawCategoryLabels(g2, plotArea, dataArea, edge, state, plotState);
        state = this.drawLabel(this.getLabel(), g2, plotArea, dataArea, edge, state);
        return state;
    }
    
    protected AxisState drawCategoryLabels(final Graphics2D g2, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge, final AxisState state, final PlotRenderingInfo plotState) {
        if (CategoryAxis.logger.isDebugEnabled()) {
            CategoryAxis.logger.debug("Entering drawCategoryLabels() method, cursor = " + state.getCursor());
        }
        if (state == null) {
            throw new IllegalArgumentException("Null 'state' argument.");
        }
        if (this.isTickLabelsVisible()) {
            g2.setFont(this.getTickLabelFont());
            g2.setPaint(this.getTickLabelPaint());
            final List ticks = this.refreshTicks(g2, state, plotArea, dataArea, edge);
            state.setTicks(ticks);
            int categoryIndex = 0;
            for (final CategoryTick tick : ticks) {
                g2.setPaint(this.getTickLabelPaint());
                final CategoryLabelPosition position = this.categoryLabelPositions.getLabelPosition(edge);
                double x0 = 0.0;
                double x2 = 0.0;
                double y0 = 0.0;
                double y2 = 0.0;
                if (edge == RectangleEdge.TOP) {
                    x0 = this.getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
                    x2 = this.getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
                    y2 = state.getCursor() - this.categoryLabelPositionOffset;
                    y0 = y2 - state.getMax();
                }
                else if (edge == RectangleEdge.BOTTOM) {
                    x0 = this.getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
                    x2 = this.getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
                    y0 = state.getCursor() + this.categoryLabelPositionOffset;
                    y2 = y0 + state.getMax();
                }
                else if (edge == RectangleEdge.LEFT) {
                    y0 = this.getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
                    y2 = this.getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
                    x2 = state.getCursor() - this.categoryLabelPositionOffset;
                    x0 = x2 - state.getMax();
                }
                else if (edge == RectangleEdge.RIGHT) {
                    y0 = this.getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
                    y2 = this.getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
                    x0 = state.getCursor() + this.categoryLabelPositionOffset;
                    x2 = x0 - state.getMax();
                }
                final Rectangle2D area = new Rectangle2D.Double(x0, y0, x2 - x0, y2 - y0);
                final double[] anchorPoint = RectangleAnchor.coordinates(area, position.getCategoryAnchor());
                final TextBlock block = tick.getLabel();
                block.draw(g2, (float)anchorPoint[0], (float)anchorPoint[1], position.getLabelAnchor(), (float)anchorPoint[0], (float)anchorPoint[1], position.getAngle());
                final Shape bounds = block.calculateBounds(g2, (float)anchorPoint[0], (float)anchorPoint[1], position.getLabelAnchor(), (float)anchorPoint[0], (float)anchorPoint[1], position.getAngle());
                if (plotState != null) {
                    final EntityCollection entities = plotState.getOwner().getEntityCollection();
                    if (entities != null) {
                        final String tooltip = this.categoryLabelToolTips.get(tick.getCategory());
                        entities.addEntity(new TickLabelEntity(bounds, tooltip, null));
                    }
                }
                ++categoryIndex;
            }
            if (edge.equals(RectangleEdge.TOP)) {
                final double h = state.getMax();
                state.cursorUp(h);
            }
            else if (edge.equals(RectangleEdge.BOTTOM)) {
                final double h = state.getMax();
                state.cursorDown(h);
            }
            else if (edge == RectangleEdge.LEFT) {
                final double w = state.getMax();
                state.cursorLeft(w);
            }
            else if (edge == RectangleEdge.RIGHT) {
                final double w = state.getMax();
                state.cursorRight(w);
            }
        }
        return state;
    }
    
    public List refreshTicks(final Graphics2D g2, final AxisState state, final Rectangle2D plotArea, final Rectangle2D dataArea, final RectangleEdge edge) {
        final List ticks = new ArrayList();
        if (dataArea.getHeight() <= 0.0 || dataArea.getWidth() < 0.0) {
            return ticks;
        }
        final CategoryPlot plot = (CategoryPlot)this.getPlot();
        final List categories = plot.getCategories();
        double max = 0.0;
        if (categories != null) {
            final CategoryLabelPosition position = this.categoryLabelPositions.getLabelPosition(edge);
            float r = this.maxCategoryLabelWidthRatio;
            if (r <= 0.0) {
                r = position.getWidthRatio();
            }
            float l = 0.0f;
            if (position.getWidthType() == CategoryLabelWidthType.CATEGORY) {
                l = (float)this.calculateCategorySize(categories.size(), dataArea, edge);
            }
            else if (RectangleEdge.isLeftOrRight(edge)) {
                l = (float)dataArea.getWidth();
            }
            else {
                l = (float)dataArea.getHeight();
            }
            int categoryIndex = 0;
            for (final Comparable category : categories) {
                final TextBlock label = this.createLabel(category, l * r, edge, g2);
                if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
                    max = Math.max(max, this.calculateTextBlockHeight(label, position, g2));
                }
                else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
                    max = Math.max(max, this.calculateTextBlockWidth(label, position, g2));
                }
                final Tick tick = new CategoryTick(category, label, position.getLabelAnchor(), position.getRotationAnchor(), position.getAngle());
                ticks.add(tick);
                ++categoryIndex;
            }
        }
        state.setMax(max);
        return ticks;
    }
    
    protected TextBlock createLabel(final Comparable category, final float width, final RectangleEdge edge, final Graphics2D g2) {
        final TextBlock label = TextUtilities.createTextBlock(category.toString(), this.getTickLabelFont(), this.getTickLabelPaint(), width, new G2TextMeasurer(g2));
        return label;
    }
    
    protected double calculateTextBlockWidth(final TextBlock block, final CategoryLabelPosition position, final Graphics2D g2) {
        final Insets insets = this.getTickLabelInsets();
        final Size2D size = block.calculateDimensions(g2);
        final Rectangle2D box = new Rectangle2D.Double(0.0, 0.0, size.getWidth(), size.getHeight());
        final Shape rotatedBox = ShapeUtils.rotateShape(box, position.getAngle(), 0.0f, 0.0f);
        final double w = rotatedBox.getBounds2D().getWidth() + insets.top + insets.bottom;
        return w;
    }
    
    protected double calculateTextBlockHeight(final TextBlock block, final CategoryLabelPosition position, final Graphics2D g2) {
        final Insets insets = this.getTickLabelInsets();
        final Size2D size = block.calculateDimensions(g2);
        final Rectangle2D box = new Rectangle2D.Double(0.0, 0.0, size.getWidth(), size.getHeight());
        final Shape rotatedBox = ShapeUtils.rotateShape(box, position.getAngle(), 0.0f, 0.0f);
        final double h = rotatedBox.getBounds2D().getHeight() + insets.top + insets.bottom;
        return h;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final Object clone = super.clone();
        return clone;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CategoryAxis && super.equals(obj)) {
            final CategoryAxis axis = (CategoryAxis)obj;
            final boolean b0 = axis.lowerMargin == this.lowerMargin;
            final boolean b2 = axis.upperMargin == this.upperMargin;
            final boolean b3 = axis.categoryMargin == this.categoryMargin;
            final boolean b4 = axis.maxCategoryLabelWidthRatio == this.maxCategoryLabelWidthRatio;
            final boolean b5 = axis.categoryLabelPositionOffset == this.categoryLabelPositionOffset;
            final boolean b6 = ObjectUtils.equal(axis.categoryLabelPositions, this.categoryLabelPositions);
            final boolean b7 = ObjectUtils.equal(axis.categoryLabelToolTips, this.categoryLabelToolTips);
            return b0 && b2 && b3 && b4 && b5 && b6 && b7;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }
    
    public boolean isVerticalCategoryLabels() {
        return false;
    }
    
    public void setVerticalCategoryLabels(final boolean flag) {
        if (flag) {
            this.categoryLabelPositions = CategoryLabelPositions.UP_90;
        }
        else {
            this.categoryLabelPositions = CategoryLabelPositions.STANDARD;
        }
        this.notifyListeners(new AxisChangeEvent(this));
    }
    
    public boolean getSkipCategoryLabelsToFit() {
        return false;
    }
    
    public void setSkipCategoryLabelsToFit(final boolean flag) {
    }
    
    public CategoryLabelPosition getTopCategoryLabelPosition() {
        return this.getCategoryLabelPositions().getLabelPosition(RectangleEdge.TOP);
    }
    
    public void setTopCategoryLabelPosition(final CategoryLabelPosition position) {
        this.setCategoryLabelPositions(new CategoryLabelPositions(position, this.categoryLabelPositions.getLabelPosition(RectangleEdge.BOTTOM), this.categoryLabelPositions.getLabelPosition(RectangleEdge.LEFT), this.categoryLabelPositions.getLabelPosition(RectangleEdge.RIGHT)));
    }
    
    public CategoryLabelPosition getBottomCategoryLabelPosition() {
        return this.getCategoryLabelPositions().getLabelPosition(RectangleEdge.BOTTOM);
    }
    
    public void setBottomCategoryLabelPosition(final CategoryLabelPosition position) {
        this.setCategoryLabelPositions(new CategoryLabelPositions(this.categoryLabelPositions.getLabelPosition(RectangleEdge.TOP), position, this.categoryLabelPositions.getLabelPosition(RectangleEdge.LEFT), this.categoryLabelPositions.getLabelPosition(RectangleEdge.RIGHT)));
    }
    
    public CategoryLabelPosition getLeftCategoryLabelPosition() {
        return this.getCategoryLabelPositions().getLabelPosition(RectangleEdge.LEFT);
    }
    
    public void setLeftCategoryLabelPosition(final CategoryLabelPosition position) {
        this.setCategoryLabelPositions(new CategoryLabelPositions(this.categoryLabelPositions.getLabelPosition(RectangleEdge.TOP), this.categoryLabelPositions.getLabelPosition(RectangleEdge.BOTTOM), position, this.categoryLabelPositions.getLabelPosition(RectangleEdge.RIGHT)));
    }
    
    public CategoryLabelPosition getRightCategoryLabelPosition() {
        return this.getCategoryLabelPositions().getLabelPosition(RectangleEdge.RIGHT);
    }
    
    public void setRightCategoryLabelPosition(final CategoryLabelPosition position) {
        this.setCategoryLabelPositions(new CategoryLabelPositions(this.categoryLabelPositions.getLabelPosition(RectangleEdge.TOP), this.categoryLabelPositions.getLabelPosition(RectangleEdge.BOTTOM), this.categoryLabelPositions.getLabelPosition(RectangleEdge.LEFT), position));
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        logger = Log.createContext((CategoryAxis.class$org$jfree$chart$axis$CategoryAxis == null) ? (CategoryAxis.class$org$jfree$chart$axis$CategoryAxis = class$("org.jfree.chart.axis.CategoryAxis")) : CategoryAxis.class$org$jfree$chart$axis$CategoryAxis);
    }
}
