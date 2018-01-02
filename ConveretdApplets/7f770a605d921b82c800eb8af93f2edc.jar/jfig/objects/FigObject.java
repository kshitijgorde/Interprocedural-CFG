// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import java.io.PrintWriter;
import java.awt.event.KeyEvent;
import java.awt.Point;
import jfig.canvas.ObjectPainter;
import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;
import jfig.canvas.FigDrawable;

public interface FigObject extends FigDrawable
{
    void paint(final Graphics p0);
    
    void paint(final Graphics p0, final FigTrafo2D p1);
    
    void paintSave(final Graphics p0, final FigTrafo2D p1);
    
    boolean isVisible(final FigBbox p0);
    
    boolean initialize(final String p0);
    
    void setObjectPainter(final ObjectPainter p0);
    
    void setVisible(final boolean p0);
    
    boolean isVisible();
    
    void setTrafo(final FigTrafo2D p0);
    
    FigTrafo2D getTrafo();
    
    int getLayer();
    
    FigBbox getBbox();
    
    FigBbox get_sc_bbox();
    
    Point getPosition();
    
    FigAttribs getAttributes();
    
    void setAttributes(final FigAttribs p0);
    
    void updateAttributes(final String p0);
    
    void set_debug();
    
    void reset_debug();
    
    void move(final int p0, final int p1);
    
    FigObject copy();
    
    void mirrorX(final int p0, final int p1);
    
    void mirrorY(final int p0, final int p1);
    
    void scale(final Point p0, final double p1, final double p2);
    
    void rotate(final Point p0, final double p1) throws Exception;
    
    boolean canRotate(final double p0);
    
    void update(final FigAttribs p0);
    
    void rebuild();
    
    void showPoints();
    
    void select();
    
    void deselect();
    
    boolean isSelected();
    
    boolean supportsPointOps();
    
    int numPoints();
    
    boolean isClosed();
    
    Point[] getPoints();
    
    void setPoints(final Point[] p0);
    
    Point deletePoint(final Point p0);
    
    Point getNearestPoint(final Point p0);
    
    Point[] getNeighborPoints(final Point p0);
    
    Point[] getMovePointNeighbors(final Point p0);
    
    void appendPoint(final Point p0);
    
    void insertPoint(final Point p0, final Point p1);
    
    void movePoint(final Point p0, final Point p1);
    
    String getText();
    
    void setText(final String p0);
    
    double minDistance(final Point p0);
    
    double minDistanceEuclid(final Point p0);
    
    void keyPressed(final KeyEvent p0);
    
    void writeAsResource(final PrintWriter p0);
    
    String toString();
    
    String getComment();
    
    void setComment(final String p0);
}
