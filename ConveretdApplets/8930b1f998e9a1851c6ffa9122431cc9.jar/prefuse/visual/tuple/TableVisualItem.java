// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.tuple;

import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import prefuse.render.Renderer;
import java.awt.Graphics2D;
import prefuse.data.Tuple;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualTable;
import prefuse.Visualization;
import prefuse.data.Graph;
import prefuse.data.Table;
import prefuse.visual.VisualItem;
import prefuse.data.tuple.TableTuple;

public class TableVisualItem extends TableTuple implements VisualItem
{
    protected void init(final Table table, final Graph graph, final int n) {
        this.m_table = table;
        this.m_row = (this.m_table.isValidRow(n) ? n : -1);
    }
    
    public Visualization getVisualization() {
        return ((VisualTable)this.m_table).getVisualization();
    }
    
    public String getGroup() {
        return ((VisualTable)this.m_table).getGroup();
    }
    
    public boolean isInGroup(final String s) {
        return this.getVisualization().isInGroup(this, s);
    }
    
    public TupleSet getSourceData() {
        final VisualTable visualTable = (VisualTable)this.m_table;
        return visualTable.getVisualization().getSourceData(visualTable.getGroup());
    }
    
    public Tuple getSourceTuple() {
        return ((VisualTable)this.m_table).getVisualization().getSourceTuple(this);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("VisualItem[").append(this.getGroup());
        sb.append(",").append(this.m_row).append(',');
        final VisualTable visualTable = (VisualTable)this.m_table;
        final int localColumnCount = visualTable.getLocalColumnCount();
        for (int n = visualTable.getColumnCount() - localColumnCount, i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append(',');
            }
            final String columnName = visualTable.getColumnName(localColumnCount + i);
            sb.append(columnName);
            sb.append('=');
            if (visualTable.canGetString(columnName)) {
                sb.append(visualTable.getString(this.m_row, columnName));
            }
            else {
                sb.append(visualTable.get(this.m_row, columnName).toString());
            }
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void render(final Graphics2D graphics2D) {
        this.getRenderer().render(graphics2D, this);
    }
    
    public Renderer getRenderer() {
        return this.getVisualization().getRenderer(this);
    }
    
    public Rectangle2D validateBounds() {
        if (this.isValidated()) {
            return this.getBounds();
        }
        final Visualization visualization = this.getVisualization();
        this.getRenderer().setBounds(this);
        this.setValidated(true);
        final Rectangle2D bounds = this.getBounds();
        visualization.damageReport(this, bounds);
        return bounds;
    }
    
    public boolean isValidated() {
        return ((VisualTable)this.m_table).isValidated(this.m_row);
    }
    
    public void setValidated(final boolean b) {
        ((VisualTable)this.m_table).setValidated(this.m_row, b);
    }
    
    public boolean isVisible() {
        return ((VisualTable)this.m_table).isVisible(this.m_row);
    }
    
    public void setVisible(final boolean b) {
        ((VisualTable)this.m_table).setVisible(this.m_row, b);
    }
    
    public boolean isStartVisible() {
        return ((VisualTable)this.m_table).isStartVisible(this.m_row);
    }
    
    public void setStartVisible(final boolean b) {
        ((VisualTable)this.m_table).setStartVisible(this.m_row, b);
    }
    
    public boolean isEndVisible() {
        return ((VisualTable)this.m_table).isEndVisible(this.m_row);
    }
    
    public void setEndVisible(final boolean b) {
        ((VisualTable)this.m_table).setEndVisible(this.m_row, b);
    }
    
    public boolean isInteractive() {
        return ((VisualTable)this.m_table).isInteractive(this.m_row);
    }
    
    public void setInteractive(final boolean b) {
        ((VisualTable)this.m_table).setInteractive(this.m_row, b);
    }
    
    public boolean isExpanded() {
        return ((VisualTable)this.m_table).isExpanded(this.m_row);
    }
    
    public void setExpanded(final boolean b) {
        ((VisualTable)this.m_table).setExpanded(this.m_row, b);
    }
    
    public boolean isFixed() {
        return ((VisualTable)this.m_table).isFixed(this.m_row);
    }
    
    public void setFixed(final boolean b) {
        ((VisualTable)this.m_table).setFixed(this.m_row, b);
    }
    
    public boolean isHighlighted() {
        return ((VisualTable)this.m_table).isHighlighted(this.m_row);
    }
    
    public void setHighlighted(final boolean b) {
        ((VisualTable)this.m_table).setHighlighted(this.m_row, b);
    }
    
    public boolean isHover() {
        return ((VisualTable)this.m_table).isHover(this.m_row);
    }
    
    public void setHover(final boolean b) {
        ((VisualTable)this.m_table).setHover(this.m_row, b);
    }
    
    public double getX() {
        return ((VisualTable)this.m_table).getX(this.m_row);
    }
    
    public void setX(final double n) {
        ((VisualTable)this.m_table).setX(this.m_row, n);
    }
    
    public double getY() {
        return ((VisualTable)this.m_table).getY(this.m_row);
    }
    
    public void setY(final double n) {
        ((VisualTable)this.m_table).setY(this.m_row, n);
    }
    
    public double getStartX() {
        return ((VisualTable)this.m_table).getStartX(this.m_row);
    }
    
    public void setStartX(final double n) {
        ((VisualTable)this.m_table).setStartX(this.m_row, n);
    }
    
    public double getStartY() {
        return ((VisualTable)this.m_table).getStartY(this.m_row);
    }
    
    public void setStartY(final double n) {
        ((VisualTable)this.m_table).setStartY(this.m_row, n);
    }
    
    public double getEndX() {
        return ((VisualTable)this.m_table).getEndX(this.m_row);
    }
    
    public void setEndX(final double n) {
        ((VisualTable)this.m_table).setEndX(this.m_row, n);
    }
    
    public double getEndY() {
        return ((VisualTable)this.m_table).getEndY(this.m_row);
    }
    
    public void setEndY(final double n) {
        ((VisualTable)this.m_table).setEndY(this.m_row, n);
    }
    
    public Rectangle2D getBounds() {
        if (!this.isValidated()) {
            return this.validateBounds();
        }
        return ((VisualTable)this.m_table).getBounds(this.m_row);
    }
    
    public void setBounds(final double n, final double n2, final double n3, final double n4) {
        ((VisualTable)this.m_table).setBounds(this.m_row, n, n2, n3, n4);
    }
    
    public int getStrokeColor() {
        return ((VisualTable)this.m_table).getStrokeColor(this.m_row);
    }
    
    public void setStrokeColor(final int n) {
        ((VisualTable)this.m_table).setStrokeColor(this.m_row, n);
    }
    
    public int getStartStrokeColor() {
        return ((VisualTable)this.m_table).getStartStrokeColor(this.m_row);
    }
    
    public void setStartStrokeColor(final int n) {
        ((VisualTable)this.m_table).setStartStrokeColor(this.m_row, n);
    }
    
    public int getEndStrokeColor() {
        return ((VisualTable)this.m_table).getEndStrokeColor(this.m_row);
    }
    
    public void setEndStrokeColor(final int n) {
        ((VisualTable)this.m_table).setEndStrokeColor(this.m_row, n);
    }
    
    public int getFillColor() {
        return ((VisualTable)this.m_table).getFillColor(this.m_row);
    }
    
    public void setFillColor(final int n) {
        ((VisualTable)this.m_table).setFillColor(this.m_row, n);
    }
    
    public int getStartFillColor() {
        return ((VisualTable)this.m_table).getStartFillColor(this.m_row);
    }
    
    public void setStartFillColor(final int n) {
        ((VisualTable)this.m_table).setStartFillColor(this.m_row, n);
    }
    
    public int getEndFillColor() {
        return ((VisualTable)this.m_table).getEndFillColor(this.m_row);
    }
    
    public void setEndFillColor(final int n) {
        ((VisualTable)this.m_table).setEndFillColor(this.m_row, n);
    }
    
    public int getTextColor() {
        return ((VisualTable)this.m_table).getTextColor(this.m_row);
    }
    
    public void setTextColor(final int n) {
        ((VisualTable)this.m_table).setTextColor(this.m_row, n);
    }
    
    public int getStartTextColor() {
        return ((VisualTable)this.m_table).getStartTextColor(this.m_row);
    }
    
    public void setStartTextColor(final int n) {
        ((VisualTable)this.m_table).setStartTextColor(this.m_row, n);
    }
    
    public int getEndTextColor() {
        return ((VisualTable)this.m_table).getEndTextColor(this.m_row);
    }
    
    public void setEndTextColor(final int n) {
        ((VisualTable)this.m_table).setEndTextColor(this.m_row, n);
    }
    
    public double getSize() {
        return ((VisualTable)this.m_table).getSize(this.m_row);
    }
    
    public void setSize(final double n) {
        ((VisualTable)this.m_table).setSize(this.m_row, n);
    }
    
    public double getStartSize() {
        return ((VisualTable)this.m_table).getStartSize(this.m_row);
    }
    
    public void setStartSize(final double n) {
        ((VisualTable)this.m_table).setStartSize(this.m_row, n);
    }
    
    public double getEndSize() {
        return ((VisualTable)this.m_table).getEndSize(this.m_row);
    }
    
    public void setEndSize(final double n) {
        ((VisualTable)this.m_table).setEndSize(this.m_row, n);
    }
    
    public int getShape() {
        return ((VisualTable)this.m_table).getShape(this.m_row);
    }
    
    public void setShape(final int n) {
        ((VisualTable)this.m_table).setShape(this.m_row, n);
    }
    
    public BasicStroke getStroke() {
        return ((VisualTable)this.m_table).getStroke(this.m_row);
    }
    
    public void setStroke(final BasicStroke basicStroke) {
        ((VisualTable)this.m_table).setStroke(this.m_row, basicStroke);
    }
    
    public Font getFont() {
        return ((VisualTable)this.m_table).getFont(this.m_row);
    }
    
    public void setFont(final Font font) {
        ((VisualTable)this.m_table).setFont(this.m_row, font);
    }
    
    public Font getStartFont() {
        return ((VisualTable)this.m_table).getStartFont(this.m_row);
    }
    
    public void setStartFont(final Font font) {
        ((VisualTable)this.m_table).setStartFont(this.m_row, font);
    }
    
    public Font getEndFont() {
        return ((VisualTable)this.m_table).getEndFont(this.m_row);
    }
    
    public void setEndFont(final Font font) {
        ((VisualTable)this.m_table).setEndFont(this.m_row, font);
    }
    
    public double getDOI() {
        return ((VisualTable)this.m_table).getDOI(this.m_row);
    }
    
    public void setDOI(final double n) {
        ((VisualTable)this.m_table).setDOI(this.m_row, n);
    }
}
