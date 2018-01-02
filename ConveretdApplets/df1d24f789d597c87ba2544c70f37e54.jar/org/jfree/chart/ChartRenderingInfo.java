// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.awt.Shape;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class ChartRenderingInfo implements Cloneable, Serializable
{
    private transient Rectangle2D chartArea;
    private PlotRenderingInfo plotInfo;
    private transient Rectangle2D plotArea;
    private EntityCollection entities;
    
    public ChartRenderingInfo() {
        this(new StandardEntityCollection());
    }
    
    public ChartRenderingInfo(final EntityCollection entities) {
        this.chartArea = new Rectangle2D.Double();
        this.plotArea = new Rectangle2D.Double();
        this.plotInfo = new PlotRenderingInfo(this);
        this.entities = entities;
    }
    
    public Rectangle2D getChartArea() {
        return this.chartArea;
    }
    
    public void setChartArea(final Rectangle2D area) {
        this.chartArea.setRect(area);
    }
    
    public Rectangle2D getPlotArea() {
        return this.plotArea;
    }
    
    public void setPlotArea(final Rectangle2D area) {
        this.plotArea.setRect(area);
    }
    
    public EntityCollection getEntityCollection() {
        return this.entities;
    }
    
    public void setEntityCollection(final EntityCollection entities) {
        this.entities = entities;
    }
    
    public void clear() {
        this.chartArea.setRect(0.0, 0.0, 0.0, 0.0);
        this.plotArea.setRect(0.0, 0.0, 0.0, 0.0);
        this.plotInfo = new PlotRenderingInfo(this);
        if (this.entities != null) {
            this.entities.clear();
        }
    }
    
    public PlotRenderingInfo getPlotInfo() {
        return this.plotInfo;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ChartRenderingInfo) {
            final ChartRenderingInfo cri = (ChartRenderingInfo)obj;
            return ObjectUtils.equal(this.chartArea, cri.chartArea);
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeShape(this.chartArea, stream);
        SerialUtilities.writeShape(this.plotArea, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.chartArea = (Rectangle2D)SerialUtilities.readShape(stream);
        this.plotArea = (Rectangle2D)SerialUtilities.readShape(stream);
    }
}
