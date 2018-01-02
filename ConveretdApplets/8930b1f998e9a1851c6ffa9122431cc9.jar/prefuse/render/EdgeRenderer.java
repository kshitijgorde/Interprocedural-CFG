// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import prefuse.util.StrokeLib;
import java.awt.BasicStroke;
import java.awt.geom.Rectangle2D;
import java.awt.Paint;
import prefuse.util.ColorLib;
import java.awt.Graphics2D;
import prefuse.visual.NodeItem;
import prefuse.util.GraphicsLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;

public class EdgeRenderer extends AbstractShapeRenderer
{
    public static final String EDGE_TYPE = "edgeType";
    protected static final double HALF_PI = 1.5707963267948966;
    protected Line2D m_line;
    protected CubicCurve2D m_cubic;
    protected int m_edgeType;
    protected int m_xAlign1;
    protected int m_yAlign1;
    protected int m_xAlign2;
    protected int m_yAlign2;
    protected double m_width;
    protected float m_curWidth;
    protected Point2D[] m_tmpPoints;
    protected Point2D[] m_ctrlPoints;
    protected Point2D[] m_isctPoints;
    protected int m_edgeArrow;
    protected int m_arrowWidth;
    protected int m_arrowHeight;
    protected Polygon m_arrowHead;
    protected AffineTransform m_arrowTrans;
    protected Shape m_curArrow;
    
    public EdgeRenderer() {
        this.m_line = new Line2D.Float();
        this.m_cubic = new CubicCurve2D.Float();
        this.m_edgeType = 0;
        this.m_xAlign1 = 2;
        this.m_yAlign1 = 2;
        this.m_xAlign2 = 2;
        this.m_yAlign2 = 2;
        this.m_width = 1.0;
        this.m_curWidth = 1.0f;
        this.m_tmpPoints = new Point2D[2];
        this.m_ctrlPoints = new Point2D[2];
        this.m_isctPoints = new Point2D[2];
        this.m_edgeArrow = 1;
        this.m_arrowWidth = 8;
        this.m_arrowHeight = 12;
        this.m_arrowHead = this.updateArrowHead(this.m_arrowWidth, this.m_arrowHeight);
        this.m_arrowTrans = new AffineTransform();
        this.m_tmpPoints[0] = new Point2D.Float();
        this.m_tmpPoints[1] = new Point2D.Float();
        this.m_ctrlPoints[0] = new Point2D.Float();
        this.m_ctrlPoints[1] = new Point2D.Float();
        this.m_isctPoints[0] = new Point2D.Float();
        this.m_isctPoints[1] = new Point2D.Float();
    }
    
    public EdgeRenderer(final int n) {
        this(n, 1);
    }
    
    public EdgeRenderer(final int edgeType, final int arrowType) {
        this();
        this.setEdgeType(edgeType);
        this.setArrowType(arrowType);
    }
    
    public int getRenderType(final VisualItem visualItem) {
        return 1;
    }
    
    protected Shape getRawShape(final VisualItem visualItem) {
        final EdgeItem edgeItem = (EdgeItem)visualItem;
        final NodeItem sourceItem = edgeItem.getSourceItem();
        final NodeItem targetItem = edgeItem.getTargetItem();
        final int edgeType = this.m_edgeType;
        getAlignedPoint(this.m_tmpPoints[0], sourceItem.getBounds(), this.m_xAlign1, this.m_yAlign1);
        getAlignedPoint(this.m_tmpPoints[1], targetItem.getBounds(), this.m_xAlign2, this.m_yAlign2);
        this.m_curWidth = (float)(this.m_width * this.getLineWidth(visualItem));
        final EdgeItem edgeItem2 = (EdgeItem)visualItem;
        if (edgeItem2.isDirected() && this.m_edgeArrow != 0) {
            final int n = (this.m_edgeArrow == 1) ? 1 : 0;
            final Point2D point2D = this.m_tmpPoints[n == 0];
            Point2D point2D2 = this.m_tmpPoints[n];
            if (GraphicsLib.intersectLineRectangle(point2D, point2D2, ((n != 0) ? edgeItem2.getTargetItem() : edgeItem2.getSourceItem()).getBounds(), this.m_isctPoints) > 0) {
                point2D2 = this.m_isctPoints[0];
            }
            final AffineTransform arrowTrans = this.getArrowTrans(point2D, point2D2, this.m_curWidth);
            this.m_curArrow = arrowTrans.createTransformedShape(this.m_arrowHead);
            final Point2D point2D3 = this.m_tmpPoints[n];
            point2D3.setLocation(0.0, -this.m_arrowHeight);
            arrowTrans.transform(point2D3, point2D3);
        }
        else {
            this.m_curArrow = null;
        }
        final double x = this.m_tmpPoints[0].getX();
        final double y = this.m_tmpPoints[0].getY();
        final double x2 = this.m_tmpPoints[1].getX();
        final double y2 = this.m_tmpPoints[1].getY();
        Shape shape = null;
        switch (edgeType) {
            case 0: {
                this.m_line.setLine(x, y, x2, y2);
                shape = this.m_line;
                break;
            }
            case 1: {
                this.getCurveControlPoints(edgeItem, this.m_ctrlPoints, x, y, x2, y2);
                this.m_cubic.setCurve(x, y, this.m_ctrlPoints[0].getX(), this.m_ctrlPoints[0].getY(), this.m_ctrlPoints[1].getX(), this.m_ctrlPoints[1].getY(), x2, y2);
                shape = this.m_cubic;
                break;
            }
            default: {
                throw new IllegalStateException("Unknown edge type");
            }
        }
        return shape;
    }
    
    public void render(final Graphics2D graphics2D, final VisualItem visualItem) {
        super.render(graphics2D, visualItem);
        if (this.m_curArrow != null) {
            graphics2D.setPaint(ColorLib.getColor(visualItem.getFillColor()));
            graphics2D.fill(this.m_curArrow);
        }
    }
    
    protected AffineTransform getArrowTrans(final Point2D point2D, final Point2D point2D2, final double n) {
        this.m_arrowTrans.setToTranslation(point2D2.getX(), point2D2.getY());
        this.m_arrowTrans.rotate(-1.5707963267948966 + Math.atan2(point2D2.getY() - point2D.getY(), point2D2.getX() - point2D.getX()));
        if (n > 1.0) {
            final double n2 = n / 4.0;
            this.m_arrowTrans.scale(n2, n2);
        }
        return this.m_arrowTrans;
    }
    
    protected Polygon updateArrowHead(final int n, final int n2) {
        if (this.m_arrowHead == null) {
            this.m_arrowHead = new Polygon();
        }
        else {
            this.m_arrowHead.reset();
        }
        this.m_arrowHead.addPoint(0, 0);
        this.m_arrowHead.addPoint(-n / 2, -n2);
        this.m_arrowHead.addPoint(n / 2, -n2);
        this.m_arrowHead.addPoint(0, 0);
        return this.m_arrowHead;
    }
    
    protected AffineTransform getTransform(final VisualItem visualItem) {
        return null;
    }
    
    public boolean locatePoint(final Point2D point2D, final VisualItem visualItem) {
        final Shape shape = this.getShape(visualItem);
        if (shape == null) {
            return false;
        }
        final double max = Math.max(2.0, this.getLineWidth(visualItem));
        final double n = max / 2.0;
        return shape.intersects(point2D.getX() - n, point2D.getY() - n, max, max);
    }
    
    public void setBounds(final VisualItem visualItem) {
        if (!this.m_manageBounds) {
            return;
        }
        final Shape shape = this.getShape(visualItem);
        if (shape == null) {
            visualItem.setBounds(visualItem.getX(), visualItem.getY(), 0.0, 0.0);
            return;
        }
        GraphicsLib.setBounds(visualItem, shape, this.getStroke(visualItem));
        if (this.m_curArrow != null) {
            final Rectangle2D rectangle2D = (Rectangle2D)visualItem.get(VisualItem.BOUNDS);
            Rectangle2D.union(rectangle2D, this.m_curArrow.getBounds2D(), rectangle2D);
        }
    }
    
    protected double getLineWidth(final VisualItem visualItem) {
        return visualItem.getSize();
    }
    
    protected BasicStroke getStroke(final VisualItem visualItem) {
        return StrokeLib.getDerivedStroke(visualItem.getStroke(), this.m_curWidth);
    }
    
    protected void getCurveControlPoints(final EdgeItem edgeItem, final Point2D[] array, final double n, final double n2, final double n3, final double n4) {
        final double n5 = n3 - n;
        final double n6 = n4 - n2;
        array[0].setLocation(n + 2.0 * n5 / 3.0, n2);
        array[1].setLocation(n3 - n5 / 8.0, n4 - n6 / 8.0);
    }
    
    protected static void getAlignedPoint(final Point2D point2D, final Rectangle2D rectangle2D, final int n, final int n2) {
        double x = rectangle2D.getX();
        double y = rectangle2D.getY();
        final double width = rectangle2D.getWidth();
        final double height = rectangle2D.getHeight();
        if (n == 2) {
            x += width / 2.0;
        }
        else if (n == 1) {
            x += width;
        }
        if (n2 == 2) {
            y += height / 2.0;
        }
        else if (n2 == 3) {
            y += height;
        }
        point2D.setLocation(x, y);
    }
    
    public int getEdgeType() {
        return this.m_edgeType;
    }
    
    public void setEdgeType(final int edgeType) {
        if (edgeType < 0 || edgeType >= 2) {
            throw new IllegalArgumentException("Unrecognized edge curve type: " + edgeType);
        }
        this.m_edgeType = edgeType;
    }
    
    public int getArrowType() {
        return this.m_edgeArrow;
    }
    
    public void setArrowType(final int edgeArrow) {
        if (edgeArrow < 0 || edgeArrow >= 3) {
            throw new IllegalArgumentException("Unrecognized edge arrow type: " + edgeArrow);
        }
        this.m_edgeArrow = edgeArrow;
    }
    
    public void setArrowHeadSize(final int arrowWidth, final int arrowHeight) {
        this.m_arrowWidth = arrowWidth;
        this.m_arrowHeight = arrowHeight;
        this.m_arrowHead = this.updateArrowHead(arrowWidth, arrowHeight);
    }
    
    public int getArrowHeadHeight() {
        return this.m_arrowHeight;
    }
    
    public int getArrowHeadWidth() {
        return this.m_arrowWidth;
    }
    
    public int getHorizontalAlignment1() {
        return this.m_xAlign1;
    }
    
    public int getVerticalAlignment1() {
        return this.m_yAlign1;
    }
    
    public int getHorizontalAlignment2() {
        return this.m_xAlign2;
    }
    
    public int getVerticalAlignment2() {
        return this.m_yAlign2;
    }
    
    public void setHorizontalAlignment1(final int xAlign1) {
        this.m_xAlign1 = xAlign1;
    }
    
    public void setVerticalAlignment1(final int yAlign1) {
        this.m_yAlign1 = yAlign1;
    }
    
    public void setHorizontalAlignment2(final int xAlign2) {
        this.m_xAlign2 = xAlign2;
    }
    
    public void setVerticalAlignment2(final int yAlign2) {
        this.m_yAlign2 = yAlign2;
    }
    
    public void setDefaultLineWidth(final double width) {
        this.m_width = width;
    }
    
    public double getDefaultLineWidth() {
        return this.m_width;
    }
}
