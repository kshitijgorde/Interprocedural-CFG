// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.awt.NRectangleData;
import com.sun.jna.Structure;
import java.awt.Rectangle;

public final class NleFace
{
    private NleFaceData data;
    
    public NleFace() {
        this.data = new NleFaceData();
    }
    
    NleFace(final NleFaceData data) {
        this.data = data;
    }
    
    public double getConfidence() {
        return this.data.confidence;
    }
    
    public NleRotation getRotation() {
        return new NleRotation(this.data.rotation);
    }
    
    public Rectangle getRectangle() {
        return new Rectangle(this.data.rectangle.x, this.data.rectangle.y, this.data.rectangle.width, this.data.rectangle.height);
    }
    
    NleFaceData getData() {
        return this.data;
    }
    
    protected static class NleFaceData extends Structure
    {
        public NRectangleData rectangle;
        public NleRotation.NleRotationData rotation;
        public double confidence;
        
        public NleFaceData() {
        }
        
        NleFaceData(final NleRotation.NleRotationData rotation, final double confidence, final NRectangleData rectangle) {
            this.rotation = rotation;
            this.confidence = confidence;
            this.rectangle = rectangle;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NleFace newInstance() {
            return new NleFace();
        }
        
        public static class ByReference extends NleFaceData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NleFaceData implements Structure.ByValue
        {
        }
    }
}
