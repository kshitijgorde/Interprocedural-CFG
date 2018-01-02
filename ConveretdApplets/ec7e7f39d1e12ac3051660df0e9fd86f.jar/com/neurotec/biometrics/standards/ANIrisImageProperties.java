// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import com.sun.jna.Structure;

public final class ANIrisImageProperties
{
    private ANIrisImagePropertiesData data;
    
    ANIrisImageProperties() {
        this.data = new ANIrisImagePropertiesData();
    }
    
    public ANIrisImageProperties(final BdifIrisOrientation horzOrientation, final BdifIrisOrientation vertOrientation, final BdifIrisScanType scanType) {
        this.data = new ANIrisImagePropertiesData(horzOrientation.getValue(), vertOrientation.getValue(), scanType.getValue());
    }
    
    public BdifIrisOrientation getHorzOrientation() {
        return BdifIrisOrientation.get(this.data.horzOrientation);
    }
    
    public void setHorzOrientation(final BdifIrisOrientation horzOrientation) {
        this.data.horzOrientation = horzOrientation.getValue();
    }
    
    public BdifIrisOrientation getVertOrientation() {
        return BdifIrisOrientation.get(this.data.vertOrientation);
    }
    
    public void setVertOrientation(final BdifIrisOrientation vertOrientation) {
        this.data.vertOrientation = vertOrientation.getValue();
    }
    
    public BdifIrisScanType getScanType() {
        return BdifIrisScanType.get(this.data.scanType);
    }
    
    public void setScanType(final BdifIrisScanType scanType) {
        this.data.scanType = scanType.getValue();
    }
    
    ANIrisImagePropertiesData getData() {
        return this.data;
    }
    
    protected static class ANIrisImagePropertiesData extends Structure
    {
        public int horzOrientation;
        public int vertOrientation;
        public int scanType;
        
        public ANIrisImagePropertiesData() {
        }
        
        public ANIrisImagePropertiesData(final int horzOrientation, final int vertOrientation, final int scanType) {
            this.horzOrientation = horzOrientation;
            this.vertOrientation = vertOrientation;
            this.scanType = scanType;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected ANIrisImagePropertiesData newInstance() {
            return new ANIrisImagePropertiesData();
        }
        
        public static class ByReference extends ANIrisImagePropertiesData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends ANIrisImagePropertiesData implements Structure.ByValue
        {
        }
    }
}
