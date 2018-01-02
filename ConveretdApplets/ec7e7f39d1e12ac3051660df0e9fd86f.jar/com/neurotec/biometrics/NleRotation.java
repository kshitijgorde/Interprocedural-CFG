// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.sun.jna.Structure;

public final class NleRotation
{
    private NleRotationData data;
    
    NleRotation(final NleRotationData structure) {
        this.data = structure;
    }
    
    public NleRotation(final short yaw, final short pitch, final short roll) {
        this.data = new NleRotationData(yaw, pitch, roll);
    }
    
    public short getYaw() {
        return this.data.yaw;
    }
    
    public short getPitch() {
        return this.data.pitch;
    }
    
    public short getRoll() {
        return this.data.roll;
    }
    
    NleRotationData getData() {
        return this.data;
    }
    
    protected static class NleRotationData extends Structure
    {
        public short yaw;
        public short pitch;
        public short roll;
        
        public NleRotationData() {
        }
        
        NleRotationData(final short yaw, final short pitch, final short roll) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.roll = roll;
        }
        
        protected ByReference newByReference() {
            return new ByReference();
        }
        
        protected ByValue newByValue() {
            return new ByValue();
        }
        
        protected NleRotationData newInstance() {
            return new NleRotationData();
        }
        
        public static class ByReference extends NleRotationData implements Structure.ByReference
        {
        }
        
        public static class ByValue extends NleRotationData implements Structure.ByValue
        {
        }
    }
}
