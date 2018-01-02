// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.awt.Shape;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.ChartRenderingInfo;
import java.io.Serializable;

public class PlotRenderingInfo implements Cloneable, Serializable
{
    private transient ChartRenderingInfo owner;
    private transient Rectangle2D plotArea;
    private transient Rectangle2D dataArea;
    private List subplotInfo;
    
    public PlotRenderingInfo(final ChartRenderingInfo owner) {
        this.owner = owner;
        this.dataArea = new Rectangle2D.Double();
        this.subplotInfo = new ArrayList();
    }
    
    public ChartRenderingInfo getOwner() {
        return this.owner;
    }
    
    public Rectangle2D getPlotArea() {
        return this.plotArea;
    }
    
    public void setPlotArea(final Rectangle2D area) {
        this.plotArea = area;
    }
    
    public Rectangle2D getDataArea() {
        return this.dataArea;
    }
    
    public void setDataArea(final Rectangle2D area) {
        this.dataArea = area;
    }
    
    public void addSubplotInfo(final PlotRenderingInfo info) {
        this.subplotInfo.add(info);
    }
    
    public PlotRenderingInfo getSubplotInfo(final int index) {
        return this.subplotInfo.get(index);
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlotRenderingInfo) {
            final PlotRenderingInfo i = (PlotRenderingInfo)obj;
            return ObjectUtils.equal(this.dataArea, i.dataArea) && ObjectUtils.equal(this.plotArea, i.plotArea) && ObjectUtils.equal(this.subplotInfo, i.subplotInfo);
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeShape(this.dataArea, stream);
        SerialUtilities.writeShape(this.plotArea, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.dataArea = (Rectangle2D)SerialUtilities.readShape(stream);
        this.plotArea = (Rectangle2D)SerialUtilities.readShape(stream);
    }
}
