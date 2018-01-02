// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.awt.NPointData;
import com.sun.jna.Structure;
import java.awt.Point;

public final class NleEyes
{
    private NleEyesData data;
    
    NleEyes(final NleEyesData data) {
        this.data = data;
    }
    
    public NleEyes(final double firstConfidence, final double secondConfidence) {
        this.data = new NleEyesData(firstConfidence, secondConfidence);
    }
    
    public double getFirstConfidence() {
        return this.data.firstConfidence;
    }
    
    public double getSecondConfidence() {
        return this.data.secondConfidence;
    }
    
    public Point getFirst() {
        return new Point(this.data.first.x, this.data.first.y);
    }
    
    public Point getSecond() {
        return new Point(this.data.second.x, this.data.second.y);
    }
    
    NleEyesData getData() {
        return this.data;
    }
    
    protected static class NleEyesData extends Structure
    {
        public NPointData first;
        public double firstConfidence;
        public NPointData second;
        public double secondConfidence;
        
        public NleEyesData() {
        }
        
        NleEyesData(final double firstConfidence, final double secondConfidence) {
            this.firstConfidence = firstConfidence;
            this.secondConfidence = secondConfidence;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NleEyesData newInstance() {
            return new NleEyesData();
        }
        
        public static class ByReference extends NleEyesData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NleEyesData implements Structure.ByValue
        {
        }
    }
}
