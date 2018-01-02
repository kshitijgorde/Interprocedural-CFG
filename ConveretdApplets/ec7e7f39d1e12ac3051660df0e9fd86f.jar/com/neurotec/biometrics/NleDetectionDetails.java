// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NleDetectionDetails
{
    private NleDetectionDetailsData data;
    
    NleDetectionDetails() {
        this.data = new NleDetectionDetailsData();
    }
    
    NleDetectionDetails(final boolean faceAvailable, final boolean eyesAvailable, final NleFace face, final NleEyes eyes) {
        this.data = new NleDetectionDetailsData(faceAvailable, face.getData().newByValue(), eyesAvailable, eyes.getData().newByValue());
    }
    
    public boolean isFaceAvailable() {
        return this.data.faceAvailable;
    }
    
    public boolean isEyesAvailable() {
        return this.data.eyesAvailable;
    }
    
    public NleFace getFace() {
        return new NleFace(this.data.face);
    }
    
    public NleEyes getEyes() {
        return new NleEyes(this.data.eyes);
    }
    
    NleDetectionDetailsData getData() {
        return this.data;
    }
    
    protected static class NleDetectionDetailsData extends Structure
    {
        public boolean faceAvailable;
        public NleFace.NleFaceData face;
        public boolean eyesAvailable;
        public NleEyes.NleEyesData eyes;
        
        NleDetectionDetailsData() {
        }
        
        NleDetectionDetailsData(final boolean faceAvailable, final NleFace.NleFaceData face, final boolean eyesAvailable, final NleEyes.NleEyesData eyes) {
            this.faceAvailable = faceAvailable;
            this.face = face;
            this.eyesAvailable = eyesAvailable;
            this.eyes = eyes;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NleDetectionDetailsData newInstance() {
            return new NleDetectionDetailsData();
        }
        
        public static class ByReference extends NleDetectionDetailsData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NleDetectionDetailsData implements Structure.ByValue
        {
        }
    }
}
