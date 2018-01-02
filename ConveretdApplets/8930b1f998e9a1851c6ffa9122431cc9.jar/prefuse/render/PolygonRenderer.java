// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import prefuse.util.GraphicsLib;
import java.awt.Shape;
import prefuse.visual.VisualItem;
import java.awt.geom.GeneralPath;
import prefuse.data.Schema;

public class PolygonRenderer extends AbstractShapeRenderer
{
    public static final String POLYGON = "_polygon";
    public static final Schema POLYGON_SCHEMA;
    private int m_polyType;
    private float m_slack;
    private float m_epsilon;
    private boolean m_closed;
    private String m_polyfield;
    private GeneralPath m_path;
    
    public PolygonRenderer() {
        this(0);
    }
    
    public PolygonRenderer(final int polyType) {
        this.m_polyType = 0;
        this.m_slack = 0.08f;
        this.m_epsilon = 0.1f;
        this.m_closed = true;
        this.m_polyfield = "_polygon";
        this.m_path = new GeneralPath();
        this.m_polyType = polyType;
    }
    
    public int getPolyType() {
        return this.m_polyType;
    }
    
    public void setPolyType(final int polyType) {
        if (polyType < 0 || polyType >= 3) {
            throw new IllegalArgumentException("Unknown edge type: " + polyType);
        }
        this.m_polyType = polyType;
    }
    
    public boolean isClosePath() {
        return this.m_closed;
    }
    
    public void setClosePath(final boolean closed) {
        this.m_closed = closed;
    }
    
    public float getCurveSlack() {
        return this.m_slack;
    }
    
    public void setCurveSlack(final float slack) {
        this.m_slack = slack;
    }
    
    protected Shape getRawShape(final VisualItem visualItem) {
        final float[] array = (float[])visualItem.get(this.m_polyfield);
        if (array == null) {
            return null;
        }
        final float n = (float)visualItem.getX();
        final float n2 = (float)visualItem.getY();
        this.m_path.reset();
        this.m_path.moveTo(n + array[0], n2 + array[1]);
        if (this.m_polyType == 0) {
            for (int n3 = 2; n3 < array.length && !Float.isNaN(array[n3]); n3 += 2) {
                this.m_path.lineTo(n + array[n3], n2 + array[n3 + 1]);
            }
        }
        else {
            if (this.m_polyType == 1) {
                return GraphicsLib.cardinalSpline(this.m_path, array, this.m_slack, this.m_closed, n, n2);
            }
            if (this.m_polyType == 2) {
                return GraphicsLib.stackSpline(this.m_path, array, this.m_epsilon, this.m_slack, this.m_closed, n, n2);
            }
        }
        if (this.m_closed) {
            this.m_path.closePath();
        }
        return this.m_path;
    }
    
    static {
        (POLYGON_SCHEMA = new Schema()).addColumn("_polygon", float[].class);
    }
}
