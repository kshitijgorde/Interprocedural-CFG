// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;
import com.neurotec.awt.NPointData;
import java.awt.Point;

public final class NeeSegmentationDetails
{
    private static final int NEE_BOUNDARY_POINT_COUNT = 32;
    private Point[] outerBoundaryPoints;
    private NeeSegmentationDetailsData data;
    
    public NeeSegmentationDetails() {
        this.data = new NeeSegmentationDetailsData();
        this.outerBoundaryPoints = new Point[32];
    }
    
    public boolean isOuterBoundaryAvailable() {
        return this.data.outerBoundaryAvailable;
    }
    
    public void setOuterBoundaryAvailable(final boolean value) {
        this.data.outerBoundaryAvailable = value;
    }
    
    public Point[] getOuterBoundaryPoints() {
        for (int i = 0; i < this.data.outerBoundaryPoints.length; ++i) {
            final NPointData pd = this.data.outerBoundaryPoints[i];
            this.outerBoundaryPoints[i] = new Point(pd.x, pd.y);
        }
        return this.outerBoundaryPoints;
    }
    
    public NeeSegmentationDetailsData getData() {
        return this.data;
    }
    
    protected static class NeeSegmentationDetailsData extends Structure
    {
        public boolean outerBoundaryAvailable;
        public NPointData[] outerBoundaryPoints;
        
        public NeeSegmentationDetailsData() {
            this.outerBoundaryPoints = new NPointData[32];
        }
        
        public NeeSegmentationDetailsData(final boolean outerBoundaryAvailable, final NPointData[] outerBoundaryPoints) {
            this.outerBoundaryAvailable = outerBoundaryAvailable;
            this.outerBoundaryPoints = outerBoundaryPoints;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NeeSegmentationDetailsData newInstance() {
            return new NeeSegmentationDetailsData();
        }
        
        public static class ByReference extends NeeSegmentationDetailsData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NeeSegmentationDetailsData implements Structure.ByValue
        {
        }
    }
}
