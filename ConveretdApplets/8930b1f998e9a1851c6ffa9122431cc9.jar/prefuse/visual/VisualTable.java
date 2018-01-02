// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import prefuse.data.util.ColumnProjection;
import prefuse.visual.tuple.TableVisualItem;
import prefuse.data.Schema;
import prefuse.data.expression.Predicate;
import prefuse.data.Table;
import prefuse.Visualization;
import prefuse.data.CascadedTable;

public class VisualTable extends CascadedTable implements VisualTupleSet
{
    private Visualization m_vis;
    private String m_group;
    
    public VisualTable(final Table table, final Visualization visualization, final String s) {
        this(table, visualization, s, null, VisualItem.SCHEMA);
    }
    
    public VisualTable(final Table table, final Visualization visualization, final String s, final Predicate predicate) {
        this(table, visualization, s, predicate, VisualItem.SCHEMA);
    }
    
    public VisualTable(final Table table, final Visualization visualization, final String s, final Predicate predicate, final Schema schema) {
        super(table, predicate, null, TableVisualItem.class);
        this.init(visualization, s, schema);
    }
    
    public VisualTable(final Visualization visualization, final String s) {
        super(TableVisualItem.class);
        this.init(visualization, s, VisualItem.SCHEMA);
    }
    
    public VisualTable(final Visualization visualization, final String s, final Schema schema) {
        super(TableVisualItem.class);
        this.init(visualization, s, schema);
    }
    
    public VisualTable(final Visualization visualization, final String s, final Schema schema, final Class clazz) {
        super(clazz);
        this.init(visualization, s, schema);
    }
    
    protected void init(final Visualization visualization, final String group, final Schema schema) {
        this.setVisualization(visualization);
        this.setGroup(group);
        this.addColumns(schema);
        if (this.canGetBoolean(VisualItem.VISIBLE)) {
            this.index(VisualItem.VISIBLE);
        }
        if (this.canGetBoolean(VisualItem.STARTVISIBLE)) {
            this.index(VisualItem.STARTVISIBLE);
        }
        if (this.canGetBoolean(VisualItem.VALIDATED)) {
            this.index(VisualItem.VALIDATED);
        }
    }
    
    protected void fireTableEvent(final int n, final int n2, final int n3, final int n4) {
        if (n4 == 0) {
            if (n3 != VisualItem.IDX_VALIDATED) {
                for (int i = n; i <= n2; ++i) {
                    this.setValidated(i, false);
                }
            }
            else {
                for (int j = n; j <= n2; ++j) {
                    if (!this.isValidated(j)) {
                        this.m_vis.damageReport(this.getItem(j), this.getBounds(j));
                    }
                }
            }
        }
        else if (n4 == -1 && n3 == -1) {
            for (int k = n; k <= n2; ++k) {
                if (this.isVisible(k) && this.isValidated(k)) {
                    this.m_vis.damageReport((VisualItem)this.getTuple(k), this.getBounds(k));
                }
            }
        }
        super.fireTableEvent(n, n2, n3, n4);
    }
    
    public Visualization getVisualization() {
        return this.m_vis;
    }
    
    public void setVisualization(final Visualization vis) {
        this.m_vis = vis;
    }
    
    public String getGroup() {
        return this.m_group;
    }
    
    public void setGroup(final String group) {
        this.m_group = group;
    }
    
    public VisualItem getItem(final int n) {
        return (VisualItem)this.getTuple(n);
    }
    
    public VisualItem addItem() {
        return this.getItem(this.addRow());
    }
    
    public boolean isValidated(final int n) {
        return this.getBoolean(n, VisualItem.VALIDATED);
    }
    
    public void setValidated(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.VALIDATED, b);
    }
    
    public boolean isVisible(final int n) {
        return this.getBoolean(n, VisualItem.VISIBLE);
    }
    
    public void setVisible(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.VISIBLE, b);
    }
    
    public boolean isStartVisible(final int n) {
        return this.getBoolean(n, VisualItem.STARTVISIBLE);
    }
    
    public void setStartVisible(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.STARTVISIBLE, b);
    }
    
    public boolean isEndVisible(final int n) {
        return this.getBoolean(n, VisualItem.ENDVISIBLE);
    }
    
    public void setEndVisible(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.ENDVISIBLE, b);
    }
    
    public boolean isInteractive(final int n) {
        return this.getBoolean(n, VisualItem.INTERACTIVE);
    }
    
    public void setInteractive(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.INTERACTIVE, b);
    }
    
    public boolean isExpanded(final int n) {
        return this.getBoolean(n, VisualItem.EXPANDED);
    }
    
    public void setExpanded(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.EXPANDED, b);
    }
    
    public boolean isFixed(final int n) {
        return this.getBoolean(n, VisualItem.FIXED);
    }
    
    public void setFixed(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.FIXED, b);
    }
    
    public boolean isHighlighted(final int n) {
        return this.getBoolean(n, VisualItem.HIGHLIGHT);
    }
    
    public void setHighlighted(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.HIGHLIGHT, b);
    }
    
    public boolean isHover(final int n) {
        return this.getBoolean(n, VisualItem.HOVER);
    }
    
    public void setHover(final int n, final boolean b) {
        this.setBoolean(n, VisualItem.HOVER, b);
    }
    
    public double getX(final int n) {
        return this.getDouble(n, VisualItem.X);
    }
    
    public void setX(final int n, final double n2) {
        this.setDouble(n, VisualItem.X, n2);
    }
    
    public double getY(final int n) {
        return this.getDouble(n, VisualItem.Y);
    }
    
    public void setY(final int n, final double n2) {
        this.setDouble(n, VisualItem.Y, n2);
    }
    
    public double getStartX(final int n) {
        return this.getDouble(n, VisualItem.STARTX);
    }
    
    public void setStartX(final int n, final double n2) {
        this.setDouble(n, VisualItem.STARTX, n2);
    }
    
    public double getStartY(final int n) {
        return this.getDouble(n, VisualItem.STARTY);
    }
    
    public void setStartY(final int n, final double n2) {
        this.setDouble(n, VisualItem.STARTY, n2);
    }
    
    public double getEndX(final int n) {
        return this.getDouble(n, VisualItem.ENDX);
    }
    
    public void setEndX(final int n, final double n2) {
        this.setDouble(n, VisualItem.ENDX, n2);
    }
    
    public double getEndY(final int n) {
        return this.getDouble(n, VisualItem.ENDY);
    }
    
    public void setEndY(final int n, final double n2) {
        this.setDouble(n, VisualItem.ENDY, n2);
    }
    
    public Rectangle2D getBounds(final int n) {
        return (Rectangle2D)this.get(n, VisualItem.BOUNDS);
    }
    
    public void setBounds(final int n, final double n2, final double n3, final double n4, final double n5) {
        this.getBounds(n).setRect(n2, n3, n4, n5);
        this.fireTableEvent(n, n, this.getColumnNumber(VisualItem.BOUNDS), 0);
    }
    
    public int getStrokeColor(final int n) {
        return this.getInt(n, VisualItem.STROKECOLOR);
    }
    
    public void setStrokeColor(final int n, final int n2) {
        this.setInt(n, VisualItem.STROKECOLOR, n2);
    }
    
    public int getStartStrokeColor(final int n) {
        return this.getInt(n, VisualItem.STARTSTROKECOLOR);
    }
    
    public void setStartStrokeColor(final int n, final int n2) {
        this.setInt(n, VisualItem.STARTSTROKECOLOR, n2);
    }
    
    public int getEndStrokeColor(final int n) {
        return this.getInt(n, VisualItem.ENDSTROKECOLOR);
    }
    
    public void setEndStrokeColor(final int n, final int n2) {
        this.setInt(n, VisualItem.ENDSTROKECOLOR, n2);
    }
    
    public int getFillColor(final int n) {
        return this.getInt(n, VisualItem.FILLCOLOR);
    }
    
    public void setFillColor(final int n, final int n2) {
        this.setInt(n, VisualItem.FILLCOLOR, n2);
    }
    
    public int getStartFillColor(final int n) {
        return this.getInt(n, VisualItem.STARTFILLCOLOR);
    }
    
    public void setStartFillColor(final int n, final int n2) {
        this.setInt(n, VisualItem.STARTFILLCOLOR, n2);
    }
    
    public int getEndFillColor(final int n) {
        return this.getInt(n, VisualItem.ENDFILLCOLOR);
    }
    
    public void setEndFillColor(final int n, final int n2) {
        this.setInt(n, VisualItem.ENDFILLCOLOR, n2);
    }
    
    public int getTextColor(final int n) {
        return this.getInt(n, VisualItem.TEXTCOLOR);
    }
    
    public void setTextColor(final int n, final int n2) {
        this.setInt(n, VisualItem.TEXTCOLOR, n2);
    }
    
    public int getStartTextColor(final int n) {
        return this.getInt(n, VisualItem.STARTTEXTCOLOR);
    }
    
    public void setStartTextColor(final int n, final int n2) {
        this.setInt(n, VisualItem.STARTTEXTCOLOR, n2);
    }
    
    public int getEndTextColor(final int n) {
        return this.getInt(n, VisualItem.ENDTEXTCOLOR);
    }
    
    public void setEndTextColor(final int n, final int n2) {
        this.setInt(n, VisualItem.ENDTEXTCOLOR, n2);
    }
    
    public double getSize(final int n) {
        return this.getDouble(n, VisualItem.SIZE);
    }
    
    public void setSize(final int n, final double n2) {
        this.setDouble(n, VisualItem.SIZE, n2);
    }
    
    public double getStartSize(final int n) {
        return this.getDouble(n, VisualItem.STARTSIZE);
    }
    
    public void setStartSize(final int n, final double n2) {
        this.setDouble(n, VisualItem.STARTSIZE, n2);
    }
    
    public double getEndSize(final int n) {
        return this.getDouble(n, VisualItem.ENDSIZE);
    }
    
    public void setEndSize(final int n, final double n2) {
        this.setDouble(n, VisualItem.ENDSIZE, n2);
    }
    
    public int getShape(final int n) {
        return this.getInt(n, VisualItem.SHAPE);
    }
    
    public void setShape(final int n, final int n2) {
        this.setInt(n, VisualItem.SHAPE, n2);
    }
    
    public BasicStroke getStroke(final int n) {
        return (BasicStroke)this.get(n, VisualItem.STROKE);
    }
    
    public void setStroke(final int n, final BasicStroke basicStroke) {
        this.set(n, VisualItem.STROKE, basicStroke);
    }
    
    public Font getFont(final int n) {
        return (Font)this.get(n, VisualItem.FONT);
    }
    
    public void setFont(final int n, final Font font) {
        this.set(n, VisualItem.FONT, font);
    }
    
    public Font getStartFont(final int n) {
        return (Font)this.get(n, VisualItem.STARTFONT);
    }
    
    public void setStartFont(final int n, final Font font) {
        this.set(n, VisualItem.STARTFONT, font);
    }
    
    public Font getEndFont(final int n) {
        return (Font)this.get(n, VisualItem.ENDFONT);
    }
    
    public void setEndFont(final int n, final Font font) {
        this.set(n, VisualItem.ENDFONT, font);
    }
    
    public double getDOI(final int n) {
        return this.getDouble(n, VisualItem.DOI);
    }
    
    public void setDOI(final int n, final double n2) {
        this.setDouble(n, VisualItem.DOI, n2);
    }
}
