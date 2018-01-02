// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import prefuse.util.PrefuseLib;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import prefuse.render.Renderer;
import java.awt.Graphics2D;
import prefuse.data.tuple.TupleSet;
import prefuse.Visualization;
import prefuse.data.Schema;
import prefuse.data.Tuple;

public interface VisualItem extends Tuple
{
    public static final String VALIDATED = PrefuseLib.FIELD_PREFIX + "validated";
    public static final String VISIBLE = PrefuseLib.FIELD_PREFIX + "visible";
    public static final String STARTVISIBLE = PrefuseLib.getStartField(VisualItem.VISIBLE);
    public static final String ENDVISIBLE = PrefuseLib.getEndField(VisualItem.VISIBLE);
    public static final String INTERACTIVE = PrefuseLib.FIELD_PREFIX + "interactive";
    public static final String EXPANDED = PrefuseLib.FIELD_PREFIX + "expanded";
    public static final String FIXED = PrefuseLib.FIELD_PREFIX + "fixed";
    public static final String HIGHLIGHT = PrefuseLib.FIELD_PREFIX + "highlight";
    public static final String HOVER = PrefuseLib.FIELD_PREFIX + "hover";
    public static final String X = PrefuseLib.FIELD_PREFIX + "x";
    public static final String Y = PrefuseLib.FIELD_PREFIX + "y";
    public static final String STARTX = PrefuseLib.getStartField(VisualItem.X);
    public static final String STARTY = PrefuseLib.getStartField(VisualItem.Y);
    public static final String ENDX = PrefuseLib.getEndField(VisualItem.X);
    public static final String ENDY = PrefuseLib.getEndField(VisualItem.Y);
    public static final String BOUNDS = PrefuseLib.FIELD_PREFIX + "bounds";
    public static final String STROKECOLOR = PrefuseLib.FIELD_PREFIX + "strokeColor";
    public static final String STARTSTROKECOLOR = PrefuseLib.getStartField(VisualItem.STROKECOLOR);
    public static final String ENDSTROKECOLOR = PrefuseLib.getEndField(VisualItem.STROKECOLOR);
    public static final String FILLCOLOR = PrefuseLib.FIELD_PREFIX + "fillColor";
    public static final String STARTFILLCOLOR = PrefuseLib.getStartField(VisualItem.FILLCOLOR);
    public static final String ENDFILLCOLOR = PrefuseLib.getEndField(VisualItem.FILLCOLOR);
    public static final String TEXTCOLOR = PrefuseLib.FIELD_PREFIX + "textColor";
    public static final String STARTTEXTCOLOR = PrefuseLib.getStartField(VisualItem.TEXTCOLOR);
    public static final String ENDTEXTCOLOR = PrefuseLib.getEndField(VisualItem.TEXTCOLOR);
    public static final String SIZE = PrefuseLib.FIELD_PREFIX + "size";
    public static final String STARTSIZE = PrefuseLib.getStartField(VisualItem.SIZE);
    public static final String ENDSIZE = PrefuseLib.getEndField(VisualItem.SIZE);
    public static final String SHAPE = PrefuseLib.FIELD_PREFIX + "shape";
    public static final String STROKE = PrefuseLib.FIELD_PREFIX + "stroke";
    public static final String FONT = PrefuseLib.FIELD_PREFIX + "font";
    public static final String STARTFONT = PrefuseLib.getStartField(VisualItem.FONT);
    public static final String ENDFONT = PrefuseLib.getEndField(VisualItem.FONT);
    public static final String DOI = PrefuseLib.FIELD_PREFIX + "doi";
    public static final Schema SCHEMA = PrefuseLib.getVisualItemSchema().lockSchema();
    public static final int IDX_VALIDATED = VisualItem.SCHEMA.getColumnIndex(VisualItem.VALIDATED);
    public static final String LABEL = PrefuseLib.FIELD_PREFIX + "label";
    public static final String VALUE = PrefuseLib.FIELD_PREFIX + "value";
    public static final String POLYGON = PrefuseLib.FIELD_PREFIX + "polygon";
    public static final String STARTPOLYGON = PrefuseLib.getStartField(VisualItem.POLYGON);
    public static final String ENDPOLYGON = PrefuseLib.getStartField(VisualItem.POLYGON);
    public static final String X2 = PrefuseLib.FIELD_PREFIX + "x2";
    public static final String Y2 = PrefuseLib.FIELD_PREFIX + "y2";
    public static final String STARTX2 = PrefuseLib.getStartField(VisualItem.X2);
    public static final String STARTY2 = PrefuseLib.getStartField(VisualItem.Y2);
    public static final String ENDX2 = PrefuseLib.getEndField(VisualItem.X2);
    public static final String ENDY2 = PrefuseLib.getEndField(VisualItem.Y2);
    
    Visualization getVisualization();
    
    String getGroup();
    
    boolean isInGroup(final String p0);
    
    TupleSet getSourceData();
    
    Tuple getSourceTuple();
    
    void render(final Graphics2D p0);
    
    Renderer getRenderer();
    
    Rectangle2D validateBounds();
    
    boolean isValidated();
    
    void setValidated(final boolean p0);
    
    boolean isVisible();
    
    void setVisible(final boolean p0);
    
    boolean isStartVisible();
    
    void setStartVisible(final boolean p0);
    
    boolean isEndVisible();
    
    void setEndVisible(final boolean p0);
    
    boolean isInteractive();
    
    void setInteractive(final boolean p0);
    
    boolean isExpanded();
    
    void setExpanded(final boolean p0);
    
    boolean isFixed();
    
    void setFixed(final boolean p0);
    
    boolean isHighlighted();
    
    void setHighlighted(final boolean p0);
    
    boolean isHover();
    
    void setHover(final boolean p0);
    
    double getX();
    
    void setX(final double p0);
    
    double getY();
    
    void setY(final double p0);
    
    double getStartX();
    
    void setStartX(final double p0);
    
    double getStartY();
    
    void setStartY(final double p0);
    
    double getEndX();
    
    void setEndX(final double p0);
    
    double getEndY();
    
    void setEndY(final double p0);
    
    Rectangle2D getBounds();
    
    void setBounds(final double p0, final double p1, final double p2, final double p3);
    
    int getStrokeColor();
    
    void setStrokeColor(final int p0);
    
    int getStartStrokeColor();
    
    void setStartStrokeColor(final int p0);
    
    int getEndStrokeColor();
    
    void setEndStrokeColor(final int p0);
    
    int getFillColor();
    
    void setFillColor(final int p0);
    
    int getStartFillColor();
    
    void setStartFillColor(final int p0);
    
    int getEndFillColor();
    
    void setEndFillColor(final int p0);
    
    int getTextColor();
    
    void setTextColor(final int p0);
    
    int getStartTextColor();
    
    void setStartTextColor(final int p0);
    
    int getEndTextColor();
    
    void setEndTextColor(final int p0);
    
    double getSize();
    
    void setSize(final double p0);
    
    double getStartSize();
    
    void setStartSize(final double p0);
    
    double getEndSize();
    
    void setEndSize(final double p0);
    
    int getShape();
    
    void setShape(final int p0);
    
    BasicStroke getStroke();
    
    void setStroke(final BasicStroke p0);
    
    Font getFont();
    
    void setFont(final Font p0);
    
    Font getStartFont();
    
    void setStartFont(final Font p0);
    
    Font getEndFont();
    
    void setEndFont(final Font p0);
    
    double getDOI();
    
    void setDOI(final double p0);
}
